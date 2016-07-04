package voronoi;

import geometry.Point;

public class Node {

	private Node parent;
	private Node left;
	private Node right;
	private Point value;
	
	public Node(Node parent, Node left, Node right, Point value) {
		super();
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.value = value;
	}
	
	public Node(Node parent, Point value) {
		super();
		this.parent = parent;
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
	
	
	
}
