package query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import model.Edge;
import model.Graph;
import model.Node;


public class ShortestPath {
	
	//Structure for store distance during Dijkstra
	private HashMap<Long, Integer> distance;
	
	//Structure for store the last node for origin path
	private HashMap<Long, Integer> shortestDistances;
	
	//Sets to separates settled nodes and unsettled nodes
	private Set<Long> settledNodes;
	private PriorityQueue<QueueEntry> unsettledNodes;
	
	//Structure for reconstruct the path (he saves a node with your Neighbor with minimal cost)
	private HashMap<Node, Node> origin;
	
	//Graph received
	Graph graph;
		
	//Initiate the ShortestPath with the Graph that will be used 
	public ShortestPath(Graph graph) {
		this.graph = graph;
		//this.nodes = graph.getNodes();
		//this.edges = graph.getEdges();
	}

	public Path executeDijkstra(Long source, Long target){
		
		
		//initiate
		distance = new HashMap<Long, Integer>();
		shortestDistances = new HashMap<Long, Integer>();
		settledNodes = new HashSet<Long>();
		unsettledNodes = new PriorityQueue<QueueEntry>();
		origin = new HashMap<Node,Node>();
		
		shortestDistances.put(source, 0);;
		distance.put(source, 0);
		QueueEntry e = new QueueEntry(source, 0);
		
		unsettledNodes.add(e);
		
		//Series of edges relaxations
		while(!unsettledNodes.isEmpty()){
			
			e = unsettledNodes.poll();
			if(!settledNodes.contains(e.getId())){
				settledNodes.add(e.getId());
				
				distance.put(e.getId(), e.getTravelTime());
				
				//Done because i need the whole structure
				Node min = graph.getNode(e.getId());
				
				//find minimal distances
				List<Node> adjacentNodes = getNeighbors(min);

				
				if(adjacentNodes != null){
					for(Node node : adjacentNodes){
						if(!settledNodes.contains(node.getId())){
							if(getShortestDistance(node.getId(), shortestDistances) > getShortestDistance(min.getId(), shortestDistances) + getDistance(min,node)){
								
								//Save the best node for now
								origin.put(node, min);
								
								QueueEntry l = new QueueEntry(node.getId(), getShortestDistance(min.getId(), shortestDistances) + getDistance(min,node));
								unsettledNodes.remove(l);
								unsettledNodes.add(l);
								
								shortestDistances.put(node.getId(), getShortestDistance(min.getId(), shortestDistances) + getDistance(min,node) );
							}
						}	
					}
				}	
			}
			
		}	
			
		return getPath(graph.getNode(target));
	}
	


	//Method to pick the shortest distance
	public int getShortestDistance(long id, HashMap<Long, Integer> minCost)
    {
		if(minCost.containsKey(id))
			return minCost.get(id);
		else
         return Integer.MAX_VALUE;
    }
	
	
	//Method to create a List with all neighbors
	private List<Node> getNeighbors(Node min) {
		List<Node> neighbors = new ArrayList<Node>();
	    if(min == null){
	    	//System.out.println("Null node.Path error");
	    }
	    else{   	
	    	if(min.getEdgeout().isEmpty()){
				neighbors = null;
			}
			else{
				for(Edge edge : min.getEdgeout()){
					if(edge.isBidirectional()){
						//Need check if is reverse edge
						
						//Normal edge
						if(edge.getFromNode() == min){
							if(edge.getToNode() != null){
								neighbors.add(edge.getToNode());
							}
						}
						//reverse edge
						else{
							//Test to confirm
							if(edge.getToNode() == min){
								if(edge.getFromNode() != null){
									neighbors.add(edge.getFromNode());
								}
							}
						}
					}
					else{
						if(edge.getToNode() != null){
							neighbors.add(edge.getToNode());
						}
					}	
				}
			}
	    }
		return neighbors;
	}
	
	private int getDistance(Node min, Node node) {
		
		for (Edge edge : graph.getEdges()) {
			
			if (edge.getFromNode().equals(node) && edge.getToNode().equals(min)) {
		        
				return edge.getDistance();
			}
			if (edge.getFromNode().equals(min) && edge.getToNode().equals(node)) {
	        
				return edge.getDistance();
			}
	    }
	    return -1;

	}
	
	//Method to get the path for a determinate node target
	public Path getPath(Node target) {
		Path path = new Path();
		Node step = target;
		// check if a path exists
		if (origin.get(step) == null) {
			return null;
	    }
	    path.getPathMin().add(step);
	    while (origin.get(step) != null) {
	    	step = origin.get(step);
	    	path.getPathMin().add(step);
	    }
	    
	    // Put it into the correct order
	    Collections.reverse(path.getPathMin());
	    return path;
	}

}
