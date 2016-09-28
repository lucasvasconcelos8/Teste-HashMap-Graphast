package voronoi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


import geometry.Point;
import model.Graph;

public class Voronoi {
	
	private PriorityQueue<Event> events; //lista de prioridades de eventos
	private Parabola root; //Raiz da arvore binária balanceada
	private Graph graph; //grafo para receber do graphast
	private List<Edge> edges; //voronoi edges
	
	int width= 15;
	int height= 15;
	
	//longitude no momento da sweep line
	private double ycurrSweep;

	public Voronoi(Graph graph){
		//Initialize
		
		events = new PriorityQueue<Event>(1000);
		
		edges = new ArrayList<Edge>();
		
		this.graph = graph;
	}
	
	
	public void execute(){
		
		//Recebendo os pontos dos grafos e transformando eles em eventos para ser usado no voronoi
		for(int i =0 ; i < graph.getNodes().size() ; i++){
			Event e = new Event(graph.getNodes().get(i).getLatitude(),graph.getNodes().get(i).getLongitude(),Event.SITE_EVENT);
			events.add(e);
		}
		
		while(events.size() != 0){
			//Remover evento da lista de prioridade com maior longitude ("y")
			Event e;
			e = removeEvent();
			
			//Sweep line caminha pelo ponto de maior longitude que acaba de ser removido da queue
			ycurrSweep = e.getPoint().getLongitude();
			
			
			if(e.getType() == Event.SITE_EVENT){
				System.out.println("Handle SITE Event ("+e.getPoint().getLatitude()+", "+e.getPoint().getLongitude()+")");
				handleSiteEvent(e);
			}
			else{
				System.out.println("Handle CIRCLE Event("+e.getPoint().getLatitude()+", "+e.getPoint().getLongitude()+")");
				handleCircleEvent(e);
			}
		}
		
		endEdges(root); // close off any dangling edges
		
		// get rid of those crazy inifinte lines
		for (Edge e: edges){
			if (e.getTwin() != null) {
				e.setStart(e.getTwin().getEnd());
				e.setTwin(null);
			}
		}
		
	}
	

	//Tratamento para quando o evento é um circle
	private void handleCircleEvent(Event e) {
		
		
		// find p0, p1, p2 that generate this event from left to right
		Parabola p1 = e.getArcCircle();
		Parabola xl = Parabola.getLeftParent(p1);
		Parabola xr = Parabola.getRightParent(p1);
		Parabola p0 = Parabola.getLeftChild(xl);
		Parabola p2 = Parabola.getRightChild(xr);
		
		// remove associated events since the points will be altered
		if (p0.getEvent() != null) {
			events.remove(p0.getEvent());
			p0.setEvent(null);
		}
		if (p2.getEvent() != null) {
			events.remove(p2.getEvent());
			p2.setEvent(null);
		}
		
		Point p = new Point(e.getPoint().getLatitude(), getY(p1.getPoint(), e.getPoint().getLatitude())); // new vertex
	
		// end edges!
		xl.getEdge().setEnd(p);
		xr.getEdge().setEnd(p);
		//edges.add(xl.getEdge());
		//edges.add(xr.getEdge());

		// start new bisector (edge) from this vertex on which ever original edge is higher in tree
		Parabola higher = new Parabola();
		Parabola par = p1;
		while (par != root) {
			par = par.getParent();
			if (par == xl){
				higher = xl;
			}
			if (par == xr){
				higher = xr;
			}
		}
		higher.setEdge(new Edge(p, p0.getPoint(), p2.getPoint()));
		edges.add(higher.getEdge());
		//System.out.println("EdgeCircleEvent: StartX: "+higher.getEdge().getStart().getLatitude()+"StartY:"+higher.getEdge().getStart().getLongitude()+"EndX: "+higher.getEdge().getEnd().getLatitude()+"EndY:"+higher.getEdge().getEnd().getLongitude()+"RightX:"+higher.getEdge().getRight_site().getLatitude()+"RightY:"+higher.getEdge().getRight_site().getLongitude()+"LeftX:"+higher.getEdge().getLeft_site().getLatitude()+"LeftY:"+higher.getEdge().getLeft_site().getLongitude());
		
		// delete p1 and parent (boundary edge) from beach line
		Parabola gparent = p1.getParent().getParent();
		if (p1.getParent().getChildLeft() == p1) {
			if(gparent.getChildLeft()  == p1.getParent()) {
				gparent.setLeft( p1.getParent().getChildRight());
			}
			if(gparent.getChildRight() == p1.getParent()) {
				gparent.setRight(p1.getParent().getChildRight());
			}
		}
		else {
			if(gparent.getChildLeft()  == p1.getParent()) {
				gparent.setLeft( p1.getParent().getChildLeft());
			}
			if(gparent.getChildRight() == p1.getParent()) {
				gparent.setRight(p1.getParent().getChildLeft());
			}
		}

		
		p1.setParent(null);
		p1 = null;
		
		checkCircleEvent(p0);
		checkCircleEvent(p2);
		
	}


	//Tratamento para quando o evento é um site
	private void handleSiteEvent(Event e) {
		
		//Teste para saber se a arvore binaria balanceada está vazia
		if(root == null){
			root = new Parabola(e.getPoint());
			System.out.println("Único Evento");
			return;
		}
		
		//Buscar a Parabola na beach line que coincide na mesma latitude do ponto de evento que está sendo tratado
		Parabola par = getParabolaByX(e.getPoint().getLatitude());

		System.out.println("Beach line"+par.getPoint().getLatitude()+"--"+par.getPoint().getLongitude());
		
		//Significa que o novo valor ta em cima da beach e da sweep line (colineares)
		if(par.getPoint().getLongitude() == ycurrSweep){
			System.out.println("Colineares!");
			
			//Se a parabola atual tiver um evento(Creio que só pode ser circleEvent), ele deve ser apagado, pois a parabola está ativando um falso evento
			if (par.getEvent() != null) {
				System.out.println("Tinha um falso evento");
				events.remove(par.getEvent());
				par.setEvent(null);
			}

			Point start = new Point((e.getPoint().getLatitude()+par.getPoint().getLatitude())/2,height);
			Edge el = new Edge(start, par.getPoint(), e.getPoint());
			Edge er = new Edge(start, e.getPoint(), par.getPoint());
			el.setTwin(er);
			
			edges.add(el);

			par.setEdge(er);
			par.setType(Parabola.IS_VERTEX);
			
			
			Parabola p0 = new Parabola (par.getPoint());
			Parabola p1 = new Parabola (e.getPoint());
			Parabola p2 = new Parabola (par.getPoint());

			par.setRight(p2);
			par.setLeft(new Parabola());
			par.getChildLeft().setEdge(el);
			par.getChildLeft().setLeft(p0);
			par.getChildLeft().setRight(p1);
			

			checkCircleEvent(p0);
			checkCircleEvent(p2);

			return;
		}

		
		if(root.getType() == Parabola.IS_POINT_FOCUS && root.getPoint().getLongitude() - e.getPoint().getLongitude() < 1 ){
			
			System.out.println("Entrei segundo caso handle site");
			
			root.setType(Parabola.IS_VERTEX);
			root.setLeft(new Parabola(root.getPoint()));
			root.setRight(new Parabola(e.getPoint()));
			
			Point start = new Point((e.getPoint().getLatitude() + root.getPoint().getLatitude())/2 , height);
			
			if(e.getPoint().getLatitude() > root.getPoint().getLatitude()){
				root.setEdge(new Edge(start, root.getPoint(),e.getPoint()));
			}else{
				root.setEdge(new Edge(start,e.getPoint(),root.getPoint()));
			}
			
			edges.add(root.getEdge());
			
			return;
			
		}
		
				
		//Se a parabola atual tiver um evento(Creio que só pode ser circleEvent), ele deve ser apagado, pois a parabola está ativando um falso evento
		if (par.getEvent() != null) {
			System.out.println("Tinha um falso evento");
			events.remove(par.getEvent());
			par.setEvent(null);
		}
		
		/*A parabola encontrada será quebrada em 3 novos :  pelo novo ponto do evento e os outros dois com o ponto da parabola que vai sumir.
		  Começa a criação dos tres novos nós da arvore*/
		
		
		Point start = new Point(e.getPoint().getLatitude(), getY(par.getPoint(), e.getPoint().getLatitude()));
		Edge el = new Edge(start, par.getPoint(), e.getPoint());
		Edge er = new Edge(start, e.getPoint(), par.getPoint());
		el.setTwin(er);
		//er.setTwin(el);
		//System.out.println("EdgeSiteEvent: StartX: "+el.getStart().getLatitude()+"StartY:"+el.getStart().getLongitude()+"EndX: "+el.getEnd().getLatitude()+"EndY:"+el.getEnd().getLongitude()+"RightX:"+el.getRight_site().getLatitude()+"RightY:"+el.getRight_site().getLongitude()+"LeftX:"+el.getLeft_site().getLatitude()+"LeftY:"+el.getLeft_site().getLongitude());
		edges.add(el);
		
		par.setEdge(er);
		par.setType(Parabola.IS_VERTEX);
		
		
		Parabola p0 = new Parabola (par.getPoint());
		Parabola p1 = new Parabola (e.getPoint());
		Parabola p2 = new Parabola (par.getPoint());

		par.setRight(p2);
		par.setLeft(new Parabola());
		par.getChildLeft().setEdge(el);
		
		par.getChildLeft().setLeft(p0);
		par.getChildLeft().setRight(p1);

		checkCircleEvent(p0);
		checkCircleEvent(p2);
		
	}


	//Checar se a parabola p mais o nó folha mais a esquerda e o nó folha mais a direita estão no mesmo circulo
	private void checkCircleEvent(Parabola p) {
		
		
		Parabola lp = Parabola.getLeftParent(p);
		Parabola rp = Parabola.getRightParent(p);
		
/*		if (lp == null || rp == null) {
			System.out.println("Não possui parente mais a esquerda ou direita");
			return;
		}*/
		
		//Consecutivos de p
		Parabola a = Parabola.getLeftChild(lp);
		Parabola c = Parabola.getRightChild(rp);
	
		if (a == null || c == null || a.getPoint() == c.getPoint()){
			System.out.println("Não possui consecutivos mais a esquerda ou direita");
			return;
		}

/*		//Teste para saber se eles possuem um angulo no sentido anti-horario que caracteriza um circle event.
		if (ccw(a.getPoint(),p.getPoint(),c.getPoint()) == 1){
			System.out.println("Não caracteriza um circle event");
			return;
		}*/
		
		// edges will intersect to form a vertex for a circle event
		Point start = getEdgeIntersection(lp.getEdge(), rp.getEdge());
		if (start == null) {
			System.out.println("A intersecção dos edges não existe");
			return;
		}
		
		// compute radius 
		double dx = a.getPoint().getLatitude() - start.getLatitude();
		double dy = a.getPoint().getLongitude() - start.getLongitude();
		double d = Math.sqrt((dx*dx) + (dy*dy));
		
		System.out.println("DX: "+dx+" DY: "+dy);
		
		if (start.getLongitude() - d >= ycurrSweep) {
			System.out.println("Não tocou a sweep line, logo não é um circle event");
			return; // must be after sweep line
		}

		Point ep = new Point(start.getLatitude(), start.getLongitude() - d);
		//System.out.println("added circle event "+ ep);

		// adiciona circle event
		Event e = new Event (ep, Event.CIRCLE_EVENT);
		e.setArcCircle(p);
		p.setEvent(e);
		System.out.println("Adicionando a circle Event: Latitude: "+e.getPoint().getLatitude()+" Longitude: "+e.getPoint().getLongitude());
		events.add(e);
	}

	// returns intersection of the lines of with vectors a and b
	private Point getEdgeIntersection(Edge a, Edge b) {


		double x = (b.getYint() - a.getYint())/(a.getSlope() - b.getSlope());
		double y = a.getSlope()*x + a.getYint();

		System.out.println("b.getYint : "+b.getYint()+" - a.getYint:"+a.getYint()+" - a.getSlope():"+a.getSlope()+" - b.getSlope():"+b.getSlope());

		
		if( (x - a.getStart().getLatitude())/a.getDirection().getLatitude() < 0 ){
			return null;
		}
		if( (y - a.getStart().getLongitude())/a.getDirection().getLongitude() < 0){
			return null;
		}
		if((x - b.getStart().getLatitude())/b.getDirection().getLatitude() < 0){
			return null;
		}
		if((y - b.getStart().getLongitude())/b.getDirection().getLongitude() < 0){
			return null;
		}
		
		return new Point(x, y);
	}
		
	//Counterclockwise if 1 , a->b->c counterclockwise angle
	public int ccw(Point a, Point b, Point c) {
        double area = (b.getLatitude()-a.getLatitude())*(c.getLongitude()-a.getLongitude()) - (b.getLongitude()-a.getLongitude())*(c.getLatitude()-a.getLatitude());
        if (area < 0) return -1;
        else if (area > 0) return 1;
        else return  0;
    }
	
	
	//Formula da equacao de 2 grau para encontrarmos o y a partir do x em comum
	// find corresponding y-coordinate to x on parabola with focus p
	private double getY(Point p, double x) {
		// determine equation for parabola around focus p
		double dp = 2*(p.getLongitude() - ycurrSweep);
		if(dp == 0){
			System.out.println("Entra no probs getY");
			return height;
		}
		double a1 = 1/dp;
		double b1 = -2*p.getLatitude()/dp;
		double c1 = ycurrSweep + dp/4 + p.getLatitude()*p.getLatitude()/dp;
		System.out.println("GETY RETURN: "+(a1*x*x + b1*x + c1));
		return (a1*x*x + b1*x + c1);
	}
	
	//Método que retorna a parabola que está na beach line naquela latitude
	private Parabola getParabolaByX (double latitude) {
		Parabola par = root;
		double x = 0;
		while (par.getType() == Parabola.IS_VERTEX) {
			x = getXofEdge(par);
			
			if (x>latitude) {
				par = par.getChildLeft();
			}
			else{
				par = par.getChildRight();
			}
		}
		return par;
	}


	//Retorna a latitude de uma aresta ainda não finalizada
	private double getXofEdge(Parabola par) {
		
		//Pegar os pontos mais próximos dessa parabola
		Parabola left = Parabola.getLeftChild(par);
		Parabola right = Parabola.getRightChild(par);
		
		Point l = left.getPoint();
		Point r = right.getPoint();
		
		//##################################
		//Calculos matemáticos que ainda preciso analisar
		double dp = 2*(l.getLongitude() - ycurrSweep);
		double a1 = 1/dp;
		double b1 = -2*l.getLatitude()/dp;
		double c1 = ycurrSweep + dp/4 + l.getLatitude()*l.getLatitude()/dp;
		
		double dp2 = 2*(r.getLongitude() - ycurrSweep);
		double a2 = 1/dp2;
		double b2 = -2*r.getLatitude()/dp2;
		double c2 = ycurrSweep + dp2 /4 + r.getLatitude()*r.getLatitude()/dp2;
		
		double a = a1-a2;
		double b = b1-b2;
		double c = c1-c2;
		
		double disc = b*b - 4*a*c;
		double x1 = (-b + Math.sqrt(disc))/(2*a);
		double x2 = (-b - Math.sqrt(disc))/(2*a);
		
		//change 
		double ry;
		if (l.getLongitude() < r.getLongitude()){
			ry = Math.max(x1, x2);
		}
		else {
			ry = Math.min(x1, x2);
		}
		
		return ry;
	}

	// termina as arestas ainda não finalizadas
	private void endEdges(Parabola p) {
		System.out.println("END EGDES:");
		if (p.getType() == Parabola.IS_POINT_FOCUS) {
			p = null;
			return;
		}

		double x;
		
		System.out.println("EdgeStartX: "+p.getEdge().getStart().getLatitude()+"EdgeStartY:"+p.getEdge().getStart().getLongitude());
		
		//Serenaz
		//x = getXofEdge(p);
		
		//c++
		if(p.getEdge().getDirection().getLatitude() > 0){
			x = Math.max(width, p.getEdge().getStart().getLatitude()+10);
		}else{
			x = Math.min(0, p.getEdge().getStart().getLatitude()-10);
		}
		
		System.out.println("GetEdge.GetSlopeX: "+p.getEdge().getSlope()*x+"GetEdge.GedtYint:"+p.getEdge().getYint());
		p.getEdge().setEnd(new Point (x, p.getEdge().getSlope()*x + p.getEdge().getYint()));
		System.out.println("EdgeEndX: "+p.getEdge().getEnd().getLatitude()+"EdgeEndY:"+p.getEdge().getEnd().getLongitude());
		
		
		//edges.add(p.getEdge());
		
		endEdges(p.getChildLeft());
		endEdges(p.getChildRight());
		
		p = null;
	}

	//Método que retira da lista de prioridade o com maior longitude.
	public Event removeEvent(){
		Event event = null;
		
		event = events.remove();
		
		return event;
	}
	
	
	//Desenha o diagrama
	public void draw(){
		
		StdDraw.setCanvasSize(800, 600);
		
		//Qual escala será usada no desenho
		
		//Monaco example 
		StdDraw.setXscale(43.723,43.756);
		StdDraw.setYscale(7.405,7.44);
		
		//Example Monaco (tests)
		//StdDraw.setXscale(43.74,43.75);
		//StdDraw.setYscale(7.42,7.437);
		
		//Padrao
		StdDraw.setXscale(43.725,43.755);	
		StdDraw.setYscale(7.4,7.44);
						
		//teste Infinito
		//StdDraw.setXscale(43.736,43.739);
		//StdDraw.setYscale(7.412,7.42);
		
		System.out.println("Voce possui "+edges.size()+"edges");
		
		//StdDraw.setXscale(0,12);
		//StdDraw.setYscale(0,12);
		
		StdDraw.setPenRadius(.005);
		for(int i =0 ; i < graph.getNodes().size() ; i++){
			StdDraw.point(graph.getNodes().get(i).getLatitude(),graph.getNodes().get(i).getLongitude());
		}
		int count = 0;
		int infinitos = 0;
		StdDraw.setPenRadius(.002);
		for (Edge e: this.edges) {
			if(count == 50) return;
			StdDraw.setPenColor(StdDraw.BLACK);
/*			if(count == 23){
				StdDraw.setPenColor(StdDraw.YELLOW);
			}
			if(count == 24){
				StdDraw.setPenColor(StdDraw.PINK);
			}
			if(count == 25){
				StdDraw.setPenColor(StdDraw.GREEN);
			}
			if(count == 26){
				StdDraw.setPenColor(StdDraw.ORANGE);
			}
			if(count == 27){
				StdDraw.setPenColor(StdDraw.RED);
			}
			if(count == 28){
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
			}
			if(count == 29){
				StdDraw.setPenColor(StdDraw.WHITE);
			}if(count == 30){
				StdDraw.setPenColor(StdDraw.BLACK);
			}*/
			
			//Pinta o next Edge com Amarelo
			if(Double.isNaN(e.getStart().getLatitude())){
				infinitos++;
			}
			
			System.out.println(e.getStart().getLatitude()+"-"+e.getStart().getLongitude()+"-"+e.getEnd().getLatitude()+"-"+e.getEnd().getLongitude());
			StdDraw.line(e.getStart().getLatitude(), e.getStart().getLongitude(), e.getEnd().getLatitude(), e.getEnd().getLongitude());
			count++;
		}
	}
	
	//Informations of voronoi edges
	public void report(){

		for(Edge e : this.edges){
			System.out.println("Edges:");
			System.out.println("StartX: "+e.getStart().getLatitude()+" StartY: "+e.getStart().getLongitude());
			System.out.println("EndX: "+e.getEnd().getLatitude()+" EndY: "+e.getEnd().getLongitude());
			System.out.println("Has Neighbor: "+e.getTwin());
			System.out.println("SiteRightX: "+e.getRight_site().getLatitude()+" SiteRightY: "+e.getRight_site().getLongitude());
			System.out.println("SiteLeftX: "+e.getLeft_site().getLatitude()+" SiteLeftY: "+e.getLeft_site().getLongitude());
			System.out.println("Has Slope: "+e.getSlope());
			System.out.println("Has Yint: "+e.getYint());
			System.out.println("DirectionX: "+e.getDirection().getLatitude()+" DirectionY: "+e.getDirection().getLongitude());
			System.out.println();
		}
	}
	
	public static double normalizar(double valor){
		double max = 12;
		double min = 0;
		
		double normalizado;
		
		normalizado = (valor - min)/(max-min);
		
		return normalizado;
	}

}
