package voronoi;

import geometry.Point;

public class Event implements Comparable <Event>{

	// Quanto o ponto é um site
	public static int SITE_EVENT = 0;
	
	// Quando o ponto é um vértice do diagrama de voronoi
	public static int CIRCLE_EVENT = 1;
	
	private Point value;
	private int type;
	private Parabola arcCircle; //Apenas circle event
	
	public Event(double x,double y, int type) {
		super();
		this.value = new Point(x,y);
		this.type = type;
		this.arcCircle = null;
	}
	
	public Event(Point point, int type) {
		super();
		this.value = point;
		this.type = type;
		this.arcCircle = null;
	}

	public Point getPoint() {
		return value;
	}

	public void setPoint(Point value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Point getValue() {
		return value;
	}

	public void setValue(Point value) {
		this.value = value;
	}

	public Parabola getArcCircle() {
		return arcCircle;
	}

	public void setArcCircle(Parabola arcCircle) {
		this.arcCircle = arcCircle;
	}

	public int compareTo(Event other) {
		return this.value.compareTo(other.value);
	}

	
	
	
}
