package voronoi;

import geometry.Point;

public class Node {


	
	private Node parent;
	private Node left;
	private Node right;
	private Point value;
	private Edge edge;
	private Event event;
	private int type;
	
	public Node(Node parent, Node left, Node right, Point value, int type) {
		super();
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.value = value;
		this.type = type;
	}
	
	public Node(Node parent, Point value) {
		super();
		this.parent = parent;
		this.left = null;
		this.right = null;
		this.value = value;
	}
	
	public Node(Point value) {
		super();
		this.parent = null;
		this.left = null;
		this.right = null;
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Point getValue() {
		return value;
	}

	public void setValue(Point value) {
		this.value = value;
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
	
	
	
	
}
