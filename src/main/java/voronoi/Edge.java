package voronoi;

import geometry.Point;

public class Edge {
	
	private Point start;
	private Point end;
	private Point left_site;
	private Point right_site;
	
	private Point direction;
	
	private Edge twin; //A mesma aresta com direção opostas
	
	private double slope;
	private double yint;
	
	public Edge(){
		
	}
	
	public Edge(Point first, Point left, Point right){
		
		if(first == null){
			System.out.println("Edge problem...first null");
		}else if(left == null){
			System.out.println("Edge problem...left null");
		}else if(right == null){
			System.out.println("Edge problem...right null");
		}
		
		
		
		
		this.start = first;
		this.left_site = left;
		this.right_site = right;
		
		direction = new Point(right.getLongitude() - left.getLongitude(), - (right.getLatitude() - left.getLatitude()));
		end = null;		
		System.out.println("O ponto start é: "+start.getLatitude()+"--"+start.getLongitude());
		System.out.println("O ponto right é: "+right.getLatitude()+"--"+right.getLongitude());
		System.out.println("O ponto left é: "+left.getLatitude()+"--"+left.getLongitude());
		
/*		//For |dy|>|dx|, the slope of the bisector is -dx/dy
		if(Math.abs(right.getLongitude() - left.getLongitude()) > Math.abs(right.getLatitude() - left.getLatitude()) ){
			System.out.println("Deu zero");
			slope = -(right.getLatitude() - left.getLatitude()) / right.getLongitude() - left.getLongitude();
		}
		else{
			slope = -(right.getLongitude() - left.getLongitude())/(right.getLatitude() - left.getLatitude());
		}*/
		
		if(right.getLongitude() - left.getLongitude() == 0){
			System.out.println("Entra no probs do slope");
			slope =(right.getLatitude()+left.getLatitude())/right.getLongitude()*2;
			slope=0;
			//é o mid ou o start( Serenaz mid, cpp start)
			Point mid = new Point ((right.getLatitude() + left.getLatitude())/2, (left.getLongitude()+right.getLongitude())/2);
			//yint = mid.getLongitude() - slope*mid.getLatitude();
			yint = this.start.getLongitude() - slope*start.getLatitude();
		}
		//c++
		else{
			slope = (right.getLatitude() - left.getLatitude())/(left.getLongitude() - right.getLongitude());
			//é o mid ou o start( Serenaz mid, cpp start)
			Point mid = new Point ((right.getLatitude() + left.getLatitude())/2, (left.getLongitude()+right.getLongitude())/2);
			//yint = mid.getLongitude() - slope*mid.getLatitude();
			yint = this.start.getLongitude() - slope*start.getLatitude();
			System.out.println("Yint: "+yint);
		}
		System.out.println("Slope: "+slope);
		
		//é o mid ou o start( Serenaz mid, cpp start)
		Point mid = new Point ((right.getLatitude() + left.getLatitude())/2, (left.getLongitude()+right.getLongitude())/2);
		//yint = mid.getLongitude() - slope*mid.getLatitude();
		yint = this.start.getLongitude() - slope*start.getLatitude();
		System.out.println("Yint: "+yint);
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Point getLeft_site() {
		return left_site;
	}

	public void setLeft_site(Point left_site) {
		this.left_site = left_site;
	}

	public Point getRight_site() {
		return right_site;
	}

	public void setRight_site(Point right_site) {
		this.right_site = right_site;
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}

	public Edge getTwin() {
		return twin;
	}

	public void setTwin(Edge twin) {
		this.twin = twin;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}

	public double getYint() {
		return yint;
	}

	public void setYint(double yint) {
		this.yint = yint;
	}
	
	
	
	
}
