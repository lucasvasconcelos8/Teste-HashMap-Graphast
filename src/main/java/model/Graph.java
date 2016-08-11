package model;
import java.util.ArrayList;
import java.util.List;

import model.Node;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Point;
import com.graphhopper.util.StopWatch;



public class Graph {

	private List<Node> nodes; //Indide do array identifica o id do node
	private List<Edge> edges; //Indice do array identifica o id do edge
	private long id = 1;
	private long idEdge = 1;
	private RTree<Long, Point> tree;
	private RTree<Long, Point> stree;
	
	//For round latitude and longitude
	public static int LAT_LONG_CONVERTION_FACTOR = 100000;
	
	
	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
		this.tree = RTree.star().create();
	}
	
	public void addNode(Node node){
		node.setId(id);
		id++;
		nodes.add(node);
	}
	
	public Node getNode(Long id){
		for(int i=0; i < nodes.size() ; i++){
			if(nodes.get(i).getId().longValue()==id){
				return nodes.get(i);
			}
		}
		return null;
	}
	
	public Edge getEdge(Long e){
		for(int i=0; i < edges.size() ; i++){
			if(edges.get(i).getId() == id){
				return edges.get(i);
			}
		}
		return null;
	}
	
	public void addEdge(Edge edge){
		
		edge.setId(idEdge);
		idEdge++;
		edges.add(edge);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public long getNumberOfNodes() {
		return getNodes().size();
	}

	public long getNumberOfEdges() {
		return getEdges().size();
	}
	
	public Long getNodeId(double latitude, double longitude){

		Node nearest_node;
		
		int test = 0;
		
		nearest_node = getNearestNode(this.tree, latitude, longitude);
		
		return nearest_node.getId();
	}
	
	//RTree is coming from GraphGenerator
	public <T> Node getNearestNode(RTree <T, Point> tree, double latitude, double longitude) {
		
		double maxDistance = 0.001;
		int maxCount = 1;
		Point query = Geometries.point(latitude, longitude);
		List<Entry<T, Point>> list = tree.nearest(query, maxDistance, maxCount).toList().toBlocking().single();
		
		System.out.println("Entrando no while");
		while(list.isEmpty()){
			maxDistance = maxDistance * 2;
			list = tree.nearest(query, maxDistance, maxCount).toList().toBlocking().single();
		}
		System.out.println("Sai do while");
		
		Node nearestNode = new Node((Long) (list.get(0).value()),list.get(0).geometry().x(),list.get(0).geometry().y());

		return nearestNode;
	}
	
	
	public RTree<Long,Point> getRTree(){
		return this.tree;
	}
	
	public void setRTree(RTree<Long,Point> tree){
		this.tree = tree;
	}
	
	public RTree<Long,Point> getRStarTree(){
		return this.stree;
	}
	
	public void setRStarTree(RTree<Long,Point> tree){
		this.stree = tree;
	}
	
	//For round latitude and longitude
	public static long convert(double number, int factor) {
        number = number * factor;
        if (number > 0) {
            number = Math.round(number);
        } else {
            number = -Math.round(-number);
        }
        System.out.println(number);
        return (long)number;
	}
}
