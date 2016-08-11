package voronoi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import geometry.Point;
import model.Graph;

public class Voronoi {

	private ArrayList<Point> events;
	private BalancedBinaryTree bbt;
	private LinkedList<Result> results;
	private Graph graph;

	public Voronoi(Graph graph){
		//Initialize
		events = new ArrayList<Point>();
		bbt = new BalancedBinaryTree();
		results = new LinkedList<Result>();
		this.graph = graph;
	}
	
	
	public void execute(){
		//Initialize events
		for(int i =0 ; i < graph.getNodes().size() ; i++){
			Point p = new Point(graph.getNodes().get(i).getLatitude(),graph.getNodes().get(i).getLongitude());
			events.add(p);
		}
		
		while(events.size() != 0){
			//Remove event from  Q with largest y-coordinate
			Point e;
			e = removeEvent();
			
			handleEvent(e,bbt,results);
		}
	}
	
	private void handleEvent(Point e, BalancedBinaryTree bbt2, LinkedList<Result> results2) {
		// TODO Auto-generated method stub
		
	}

	//largest y-coordinate
	public Point removeEvent(){
		Point event = null;
		
		event = events.get(0);
		for(int i=0; i < events.size() ; i++){
			if(events.get(i).getLongitude() > event.getLongitude()){
				event = events.get(i);
			}
		}
		
		return event;
	}

}
