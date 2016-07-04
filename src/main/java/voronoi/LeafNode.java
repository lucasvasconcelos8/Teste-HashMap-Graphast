package voronoi;

import geometry.Point;

public class LeafNode extends Node{

	public LeafNode(Node parent, Node left, Node right, Point value) {
		super(parent, left, right, value);
		
	}

	Point circleEvent;
}
