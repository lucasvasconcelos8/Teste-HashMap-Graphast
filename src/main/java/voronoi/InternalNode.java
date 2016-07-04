package voronoi;

import geometry.Point;

public class InternalNode extends Node{

	Edge trackedEdge;
	Point leftArc;
	Point rightArc;
	
	
	public InternalNode(Node parent, Node left, Node right, Point value, Edge trackedEdge, Point leftArc,
			Point rightArc) {
		super(parent, left, right, value);
		this.trackedEdge = trackedEdge;
		this.leftArc = leftArc;
		this.rightArc = rightArc;
	}


	public InternalNode(Node parent, Node left, Node right, Point value, Edge trackedEdge) {
		super(parent, left, right, value);
		this.trackedEdge = trackedEdge;
	}
	
	
	
	
}
