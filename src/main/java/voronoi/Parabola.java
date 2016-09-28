package voronoi;

import geometry.Point;

public class Parabola {

	//Classe usada para fazer os arcos criados na beach line e armazená-los em uma arvore binaria balanceada
	
	//Distinguir nós internos e nós folhas
	public static int IS_POINT_FOCUS = 0;
	public static int IS_VERTEX = 1;
	
	private Point point; //focus
	private Edge edge; //vertex
	private Event event; 
	private int type;
	
	private Parabola parent;
	private Parabola left;
	private Parabola right;
	
	public Parabola(){
		type = IS_VERTEX;
	}
	
	public Parabola(Point point){
		this.point = point;
		this.type = IS_POINT_FOCUS;
	}

	public Parabola getParent() {
		return parent;
	}

	public void setParent(Parabola parent) {
		this.parent = parent;
	}

	public Parabola getLeft(Parabola p) {
		return getLeftChild(getLeftParent(p));
	}

	public void setLeft(Parabola left) {
		this.left = left;
		left.setParent(this);
	}

	public Parabola getRight(Parabola p) {
		return getRightChild(getRightParent(p));
	}

	public void setRight(Parabola right) {
		this.right = right;
		right.setParent(this);
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	// Retorna o site(focus) mais próxima dessa parabola que esteja a esquerda dela (Lembrando que não pode ser vértice)
	public static Parabola getLeftChild(Parabola p) {
		if (p == null){
			return null;
		}
		Parabola child = p.getChildLeft();
		while(child.type == IS_VERTEX){
			child = child.getChildRight();
		}
		return child;
	}
	
	// Retorna o site(focus) mais próxima dessa parabola que esteja a direita dela (Lembrando que não pode ser vértice)
	public static Parabola getRightChild(Parabola p) {
		if (p == null){
			return null;
		}
		Parabola child = p.getChildRight();
		while(child.type == IS_VERTEX){
			child = child.getChildLeft();
		}
		return child;
	}
	
	// Método que retorna o nó pai mais próximo pela esquerda
	public static Parabola getLeftParent(Parabola p) {
		Parabola parent = p.getParent();
		if (parent == null){
			return null;
		}
		Parabola last = p;
		while (parent.getChildLeft() == last) {
			if(parent.getParent() == null) {
				return null;
			}
			last = parent;
			parent = parent.getParent();
		}
		return parent;
	}
	
	// Método que retorna o nó pai mais próximo pela direita
	public static Parabola getRightParent(Parabola p) {
		Parabola parent = p.getParent();
		if (parent == null) return null;
		Parabola last = p;
		while (parent.getChildRight() == last) {
			if(parent.getParent() == null) return null;
			last = parent;
			parent = parent.getParent();
		}
		return parent;
	}
	
	public Parabola getChildLeft(){
		return this.left;
	}
	
	public Parabola getChildRight(){
		return this.right;
	}
	
	
}
