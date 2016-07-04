package main;
import java.util.Date;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.graphhopper.GraphHopper;
import com.graphhopper.storage.GraphStorage;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.StopWatch;

import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;
import model.Edge;
import model.Graph;
import model.Node;
import util.NumberUtils;
import util.OSMToGraphHopperReader;

public class GraphGenarator {
	
	//Conversion
	public static int LAT_LONG_CONVERTION_FACTOR = 1000000;
	
	public GraphGenarator(){
		
	}
	
	public Graph generateExample1() {
	
		
		Graph graph = new Graph();

		Edge e;
		Node v1,v2,v3,v4,v5,v6,v7;

		v1 = new Node(-3.74077, -38.55735, 0, "v1");

		v2 = new Node(-3.74003, -38.55693, 1, "v2");

		v3 = new Node(-3.74049, -38.5563, 2, "v3");

		v4 = new Node(-3.74035, -38.55526, 4, "v4");

		v5 = new Node(-3.73958, -38.55479, 0, "v5");

		v6 = new Node(-3.74001, -38.55415, 1, "v6");

		v7 = new Node(-3.7412, -38.55388, 2, "v7");

		e = new Edge(v1,v2,1, "Rua A");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v2.addEdgeIn(e);

		e = new Edge(v3,v4,1, "Rua B");
		graph.addEdge(e);
		v3.addEdgeOut(e);
		v4.addEdgeIn(e);

		e = new Edge(v1,v4,1, "Rua C");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v4.addEdgeIn(e);

		e = new Edge(v7,v6,1, "Rua D");
		graph.addEdge(e);
		v7.addEdgeOut(e);
		v6.addEdgeIn(e);

		e = new Edge(v5,v6,1, "Rua E");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v6.addEdgeIn(e);

		e = new Edge(v1,v6,1, "Rua F");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v6.addEdgeIn(e);

		e = new Edge(v5,v2, 1,"Rua G");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v2.addEdgeIn(e);

		e = new Edge(v3,v7,1,"Rua H");
		graph.addEdge(e);
		v3.addEdgeOut(e);
		v7.addEdgeIn(e);
		
		graph.addNode(v1);
		graph.addNode(v2);
		graph.addNode(v3);
		graph.addNode(v4);
		graph.addNode(v5);
		graph.addNode(v6);
		graph.addNode(v7);
		
		for (long node = 0; node < graph.getNumberOfNodes(); node++) {
			Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
			graph.setRTree(graph.getRTree().add(node, p));
		}
		
		//Put here for now
		return graph;
	}

	//small
	public Graph generateExample2(){
		
		Graph graph = new Graph();
		
		Edge e;
		Node v1,v2,v3,v4,v5,v6,v7;

		v1 = new Node(-3.74077, -38.55735, 0, "v1");

		v2 = new Node(-3.74003, -38.55693, 1, "v2");

		v3 = new Node(-3.74049, -38.5563, 2, "v3");

		v4 = new Node(-3.74035, -38.55526, 4, "v4");

		v5 = new Node(-3.73958, -38.55479, 0, "v5");

		v6 = new Node(-3.74001, -38.55415, 1, "v6");

		v7 = new Node(-3.7412, -38.55388, 2, "v7");

		e = new Edge(v1,v2,1, "Rua A");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v2.addEdgeIn(e);

		e = new Edge(v1,v3,5, "Rua B");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v3.addEdgeIn(e);

		e = new Edge(v2,v3,3, "Rua C");
		graph.addEdge(e);
		v2.addEdgeOut(e);
		v3.addEdgeIn(e);


		e = new Edge(v3,v4,3, "Rua D");
		graph.addEdge(e);
		v3.addEdgeOut(e);
		v4.addEdgeIn(e);

		
		e = new Edge(v4,v5,3, "Rua E");
		graph.addEdge(e);
		v4.addEdgeOut(e);
		v5.addEdgeIn(e);


		e = new Edge(v4,v6,4, "Rua F");
		graph.addEdge(e);
		v4.addEdgeOut(e);
		v6.addEdgeIn(e);


		e = new Edge(v5,v6, 2,"Rua G");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v6.addEdgeIn(e);


		e = new Edge(v6,v7,3,"Rua H");
		graph.addEdge(e);
		v6.addEdgeOut(e);
		v7.addEdgeIn(e);

		graph.addNode(v1);
		graph.addNode(v2);
		graph.addNode(v3);
		graph.addNode(v4);
		graph.addNode(v5);
		graph.addNode(v6);
		graph.addNode(v7);
		
		//Put here for now
		for (long node = 0; node < graph.getNumberOfNodes(); node++) {
			Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
			graph.setRTree(graph.getRTree().add(node, p));
		}
		
		return graph;
	}
	
	//small
	public Graph generateExample3(){
		
		Graph graph = new Graph();
		
		Edge e;
		Node v0,v1,v2,v3,v4,v5;

		v0 = new Node(-3.74077, -38.55735, 0, "v0");

		v1 = new Node(-3.74003, -38.55693, 1, "v1");

		v2 = new Node(-3.74049, -38.5563, 2, "v2");

		v3 = new Node(-3.74035, -38.55526, 4, "v3");

		v4 = new Node(-3.73958, -38.55479, 0, "v4");
		
		v5 = new Node(-3.74001, -38.55415, 4, "v5");

		e = new Edge(v0,v3,3, "Rua A");
		graph.addEdge(e);
		v0.addEdgeOut(e);
		v3.addEdgeIn(e);

		
		e = new Edge(v3,v0,3, "Rua B");
		graph.addEdge(e);
		v3.addEdgeOut(e);
		v0.addEdgeIn(e);


		e = new Edge(v1,v5,1, "Rua C");
		graph.addEdge(e);
		v1.addEdgeOut(e);
		v5.addEdgeIn(e);


		e = new Edge(v5,v1,1, "Rua D");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v1.addEdgeIn(e);


		e = new Edge(v5,v4,7, "Rua E");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v4.addEdgeIn(e);


		e = new Edge(v4,v2,6, "Rua F");
		graph.addEdge(e);
		v4.addEdgeOut(e);
		v2.addEdgeIn(e);


		e = new Edge(v2,v4, 6,"Rua G");
		graph.addEdge(e);
		v2.addEdgeOut(e);
		v4.addEdgeIn(e);


		e = new Edge(v5,v0,2,"Rua H");
		graph.addEdge(e);
		v5.addEdgeOut(e);
		v0.addEdgeIn(e);
		

		e = new Edge(v3,v2,4,"Rua J");
		graph.addEdge(e);
		v3.addEdgeOut(e);
		v2.addEdgeIn(e);
		

		e = new Edge(v2,v3,5,"Rua I");
		graph.addEdge(e);
		v2.addEdgeOut(e);
		v3.addEdgeIn(e);		
		
		graph.addNode(v0);
		graph.addNode(v1);
		graph.addNode(v2);
		graph.addNode(v3);
		graph.addNode(v4);
		graph.addNode(v5);
		
		//Put here for now
		for (long node = 0; node < graph.getNumberOfNodes(); node++) {
			Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
			graph.setRTree(graph.getRTree().add(node, p));
		}
		
		return graph;
	}
	
	//Monaco
	public Graph generateMonaco(){
		
		StopWatch sw = new StopWatch();
		sw.start();
		Date date = new Date();
		String initialDate = date.toString();
		
		Graph graph = new Graph();
		
		//Linux
		GraphHopper gh = OSMToGraphHopperReader.createGraph("/home/lucasvasconcelos/Downloads/monaco-150112.osm.pbf", "/home/lucasvasconcelos/Monaco", false, false);
		
		//Windows
		//GraphHopper gh = OSMToGraphHopperReader.createGraph("/users/vasco/Downloads/berlin-latest.osm.pbf", "/home/lucasvasconcelos/", false, false);
		
		//Windows com arquivo Monaco Graphast 
		//GraphHopper gh = OSMToGraphHopperReader.createGraph("/users/vasco/Downloads/monaco-150112.osm.pbf", "/users/vasco/Downloads/Graphast-Graph-Test/Monaco", false, false);
		
		GraphStorage gs = gh.getGraph();
		EdgeIterator edgeIterator = gs.getAllEdges();
		
		//To know which node has already check
		Int2LongOpenHashMap hashExternalIdToId = new Int2LongOpenHashMap();
		
		//Statistics
		int count = 0;
		int countInvalidDirection = 0;
		int countBidirectional= 0;
		int countOneWay = 0;
		int countOneWayInverse = 0;
		
		//EdgeIterator
		while(edgeIterator.next()) {
			count++;
			
			int externalFromNodeId = edgeIterator.getBaseNode();
			int externalToNodeId = edgeIterator.getAdjNode();
			
			int distance = (int)NumberUtils.round(edgeIterator.getDistance() * 1000, 0); // Convert distance from meters to millimeters
			
			String label = edgeIterator.getName();
			
			double latitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalFromNodeId)));
			double longitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalFromNodeId)));	

			double latitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalToNodeId)));
			double longitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalToNodeId)));
			
			Node fromNode = null,toNode = null;
			
			long fromNodeId, toNodeId;
			
			if(!hashExternalIdToId.containsKey(externalFromNodeId)){

				fromNode = new Node(externalFromNodeId, latitudeFrom, longitudeFrom);
			} 
			else {
				fromNodeId = hashExternalIdToId.get(externalFromNodeId);
				
				//Nodes search for the complete structure (test used to maintain the structure) 
				for(int i=0; i< graph.getNodes().size() ; i++){
					if(graph.getNodes().get(i).getId() == fromNodeId){
						fromNode = graph.getNodes().get(i);
						graph.getNodes().remove(i);
						break;
					}
				}
			}

			if(!hashExternalIdToId.containsKey(externalToNodeId)){
				toNode = new Node(externalToNodeId, latitudeTo, longitudeTo);
			} 
			else {
				toNodeId = hashExternalIdToId.get(externalToNodeId);
				
				//Nodes search for the complete structure (test used to maintain the structure)
				for(int i=0; i< graph.getNodes().size() ; i++){
					if(graph.getNodes().get(i).getId() == toNodeId){
						toNode = graph.getNodes().get(i);
						graph.getNodes().remove(i);
						break;
					}
				}
			}
			
			if(externalFromNodeId == externalToNodeId) {
				System.out.println("Edge not created, because externalFromNodeId:"+ externalFromNodeId + "== externalToNodeId:" +externalToNodeId);
				continue;
			}
			
			
			//Direction (To Do)
			int direction = 9999;
			try {
				direction = getDirection(edgeIterator.getFlags());
			} catch (Exception e) {
				countInvalidDirection++;
			}
			
			if(direction == 0) {          // Bidirectional
				Edge edge = new Edge(fromNode, toNode, distance, label, true);
				graph.addEdge(edge);
				fromNode.addEdgeOut(edge);
				toNode.addEdgeIn(edge);
				fromNode.addEdgeIn(edge);
				toNode.addEdgeOut(edge);
				countBidirectional++;
				
			} else if(direction == 1) {   // One direction: base -> adj
				Edge edge = new Edge(fromNode, toNode, distance, label, false);
				graph.addEdge(edge);
				fromNode.addEdgeOut(edge);
				toNode.addEdgeIn(edge);
				countOneWay++;
			} else if(direction == -1) {  // One direction: adj -> base
				Edge edge = new Edge(toNode, fromNode, distance, label, false);
				graph.addEdge(edge);
				fromNode.addEdgeIn(edge);
				toNode.addEdgeOut(edge);
				countOneWayInverse++;
			} else {
				System.out.println("Edge not created. Invalid direction: " + direction);
			}
			if(fromNode != null){
				graph.addNode(fromNode);
				fromNodeId = (long)fromNode.getId();
				hashExternalIdToId.put(externalFromNodeId, fromNodeId);
			}
			if(toNode != null){
				graph.addNode(toNode);
				toNodeId = (long)toNode.getId();
				hashExternalIdToId.put(externalToNodeId, toNodeId);
			}	
		}
		sw.stop();
		//Results
		
		System.out.println("----------Dados do Grafo de Monaco gerado----------");
		System.out.println("Number of Nodes: " + graph.getNumberOfNodes());
		System.out.println("Number of Edges: " + graph.getNumberOfEdges());
		System.out.println("Count: " + count);
		System.out.println("Number of invalid direction in original edges: " + countInvalidDirection);
		System.out.println("Number of Bidirectional edges: " + countBidirectional);
		System.out.println("Number of OneWay edges: " + countOneWay);
		System.out.println("Number of OneWayInverse edges: " + countOneWayInverse);

		System.out.println("Initial date: " + initialDate);
		System.out.println("Final date: " + new Date());
		System.out.println("Total time(Without Geometry): " + sw.getTime() + "ms");

		//Create RTree
		for (int  i = 0; i < graph.getNumberOfNodes(); i++) {
			Point p = Geometries.point(graph.getNodes().get(i).getLatitude(), graph.getNodes().get(i).getLongitude());
			graph.setRTree(graph.getRTree().add(graph.getNodes().get(i).getId(), p));
		}
		
		return graph;
	}
	
	//Berlin
	public Graph generateBerlin(){
			
			Date date = new Date();
			String initialDate = date.toString();
			double initialTime = System.currentTimeMillis();
			
			Graph graph = new Graph();
			
			//Linux
			GraphHopper gh = OSMToGraphHopperReader.createGraph("/home/lucasvasconcelos/Downloads/berlin-latest.osm.pbf", "/home/lucasvasconcelos/Berlin", false, false);

			//Linux (Bremen)
			//GraphHopper gh = OSMToGraphHopperReader.createGraph("/home/lucasvasconcelos/Downloads/bremen-latest.osm.pbf", "/home/lucasvasconcelos/Bremen", false, false);
			
			//Windows
			//GraphHopper gh = OSMToGraphHopperReader.createGraph("/users/vasco/Downloads/berlin-latest.osm.pbf", "/users/vasco/Downloads/Graphast-Graph-Test/Berlin", false, false);
			
			
			GraphStorage gs = gh.getGraph();
			EdgeIterator edgeIterator = gs.getAllEdges();
			
			//To know which node has already check
			Int2LongOpenHashMap hashExternalIdToId = new Int2LongOpenHashMap();
			
			//Statistics
			int count = 0;
			int countInvalidDirection = 0;
			int countBidirectional= 0;
			int countOneWay = 0;
			int countOneWayInverse = 0;
			
			while(edgeIterator.next()) {
				count++;
				
				int externalFromNodeId = edgeIterator.getBaseNode();
				int externalToNodeId = edgeIterator.getAdjNode();
				
				int distance = (int)NumberUtils.round(edgeIterator.getDistance() * 1000, 0); // Convert distance from meters to millimeters
				
				String label = edgeIterator.getName();
				
				double latitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalFromNodeId)));
				double longitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalFromNodeId)));	

				double latitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalToNodeId)));
				double longitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalToNodeId)));
				
				Node fromNode = null,toNode = null;
				
				long fromNodeId, toNodeId;
				
				if(!hashExternalIdToId.containsKey(externalFromNodeId)){

					fromNode = new Node(externalFromNodeId, latitudeFrom, longitudeFrom);
					graph.addNode(fromNode);
					fromNodeId = (long)fromNode.getId();
					hashExternalIdToId.put(externalFromNodeId, fromNodeId);
				} 
				else {
					fromNodeId = hashExternalIdToId.get(externalFromNodeId);
					
					//Nodes search for the complete structure (test used to maintain the structure) 
					for(int i=0; i< graph.getNodes().size() ; i++){
						if(graph.getNodes().get(i).getId() == fromNodeId){
							fromNode = graph.getNodes().get(i);
						}
					}
				}

				if(!hashExternalIdToId.containsKey(externalToNodeId)){
					toNode = new Node(externalToNodeId, latitudeTo, longitudeTo);
					graph.addNode(toNode);
					toNodeId = (long)toNode.getId();
					hashExternalIdToId.put(externalToNodeId, toNodeId);
				} 
				else {
					toNodeId = hashExternalIdToId.get(externalToNodeId);
					
					//Nodes search for the complete structure (test used to maintain the structure)
					for(int i=0; i< graph.getNodes().size() ; i++){
						if(graph.getNodes().get(i).getId() == toNodeId){
							toNode = graph.getNodes().get(i);
						}
					}
				}
				
				if(fromNodeId == toNodeId) {
					System.out.println("Edge not created, because fromNodeId:"+ fromNodeId + "== toNodeId:" +toNodeId);
					continue;
				}
				
				
				//Direction (To Do)
				int direction = 9999;
				try {
					direction = getDirection(edgeIterator.getFlags());
				} catch (Exception e) {
					countInvalidDirection++;
				}
				
				if(direction == 0) {          // Bidirectional
					Edge edge = new Edge(fromNode, toNode, distance, label, true);
					graph.addEdge(edge);
					fromNode.addEdgeOut(edge);
					toNode.addEdgeIn(edge);
					fromNode.addEdgeIn(edge);
					toNode.addEdgeOut(edge);
					countBidirectional++;
					
				} else if(direction == 1) {   // One direction: base -> adj
					Edge edge = new Edge(fromNode, toNode, distance, label, false);
					graph.addEdge(edge);
					fromNode.addEdgeOut(edge);
					toNode.addEdgeIn(edge);
					countOneWay++;
				} else if(direction == -1) {  // One direction: adj -> base
					Edge edge = new Edge(toNode, fromNode, distance, label, false);
					graph.addEdge(edge);
					fromNode.addEdgeIn(edge);
					toNode.addEdgeOut(edge);
					countOneWayInverse++;
				} else {
					System.out.println("Edge not created. Invalid direction: " + direction);
				}
				
				if(fromNode != null){
					graph.addNode(fromNode);
					fromNodeId = (long)fromNode.getId();
					hashExternalIdToId.put(externalFromNodeId, fromNodeId);
				}
				if(toNode != null){
					graph.addNode(toNode);
					toNodeId = (long)toNode.getId();
					hashExternalIdToId.put(externalToNodeId, toNodeId);
				}
				
			}
			//Results
			
			System.out.println("----------Dados do Grafo de Berlin gerado----------");
			System.out.println("Number of Nodes: " + graph.getNumberOfNodes());
			System.out.println("Number of Edges: " + graph.getNumberOfEdges());
			System.out.println("Count: " + count);
			System.out.println("Number of invalid direction in original edges: " + countInvalidDirection);
			System.out.println("Number of Bidirectional edges: " + countBidirectional);
			System.out.println("Number of OneWay edges: " + countOneWay);
			System.out.println("Number of OneWayInverse edges: " + countOneWayInverse);

			double finalTime = System.currentTimeMillis();
			double total = finalTime - initialTime;
			System.out.println("Initial date: " + initialDate);
			System.out.println("Final date: " + new Date());
			System.out.println("Total time: " + total);
			
			for(Node node : graph.getNodes()){
				Point p = Geometries.point(node.getLatitude(), node.getLongitude());
				graph.setRTree(graph.getRTree().add(node.getId(), p));
				//graph.setRStarTree(graph.getRStarTree().add(node.getId(), p));
			}

/*			for (long node = 0; node < graph.getNumberOfNodes(); node++) {
				Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
				graph.setRTree(graph.getRTree().add(node, p));
			}*/
			
			return graph;
		}
	
	//Bremen
	public Graph generateBremen(){
				
				Date date = new Date();
				String initialDate = date.toString();
				double initialTime = System.currentTimeMillis();
				
				Graph graph = new Graph();
				
				//Linux (Bremen)
				GraphHopper gh = OSMToGraphHopperReader.createGraph("/home/lucasvasconcelos/Downloads/bremen-latest.osm.pbf", "/home/lucasvasconcelos/Bremen", false, false);
				
				//Windows
				//GraphHopper gh = OSMToGraphHopperReader.createGraph("/users/vasco/Downloads/bremen-latest.osm.pbf", "/users/vasco/Downloads/Graphast-Graph-Test/Bremen", false, false);
				
				
				GraphStorage gs = gh.getGraph();
				EdgeIterator edgeIterator = gs.getAllEdges();
				
				//To know which node has already check
				Int2LongOpenHashMap hashExternalIdToId = new Int2LongOpenHashMap();
				
				//Statistics
				int count = 0;
				int countInvalidDirection = 0;
				int countBidirectional= 0;
				int countOneWay = 0;
				int countOneWayInverse = 0;
				
				while(edgeIterator.next()) {
					count++;
					
					int externalFromNodeId = edgeIterator.getBaseNode();
					int externalToNodeId = edgeIterator.getAdjNode();
					
					int distance = (int)NumberUtils.round(edgeIterator.getDistance() * 1000, 0); // Convert distance from meters to millimeters
					
					String label = edgeIterator.getName();
					
					double latitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalFromNodeId)));
					double longitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalFromNodeId)));	

					double latitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalToNodeId)));
					double longitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalToNodeId)));
					
					Node fromNode = null,toNode = null;
					
					long fromNodeId, toNodeId;
					
					if(!hashExternalIdToId.containsKey(externalFromNodeId)){

						fromNode = new Node(externalFromNodeId, latitudeFrom, longitudeFrom);
						graph.addNode(fromNode);
						fromNodeId = (long)fromNode.getId();
						hashExternalIdToId.put(externalFromNodeId, fromNodeId);
					} 
					else {
						fromNodeId = hashExternalIdToId.get(externalFromNodeId);
						
						//Nodes search for the complete structure (test used to maintain the structure) 
						for(int i=0; i< graph.getNodes().size() ; i++){
							if(graph.getNodes().get(i).getId() == fromNodeId){
								fromNode = graph.getNodes().get(i);
							}
						}
					}

					if(!hashExternalIdToId.containsKey(externalToNodeId)){
						toNode = new Node(externalToNodeId, latitudeTo, longitudeTo);
						graph.addNode(toNode);
						toNodeId = (long)toNode.getId();
						hashExternalIdToId.put(externalToNodeId, toNodeId);
					} 
					else {
						toNodeId = hashExternalIdToId.get(externalToNodeId);
						
						//Nodes search for the complete structure (test used to maintain the structure)
						for(int i=0; i< graph.getNodes().size() ; i++){
							if(graph.getNodes().get(i).getId() == toNodeId){
								toNode = graph.getNodes().get(i);
							}
						}
					}
					
					if(fromNodeId == toNodeId) {
						//System.out.println("Edge not created, because fromNodeId:"+ fromNodeId + "== toNodeId:" +toNodeId);
						continue;
					}
					
					
					//Direction (To Do)
					int direction = 9999;
					try {
						direction = getDirection(edgeIterator.getFlags());
					} catch (Exception e) {
						countInvalidDirection++;
					}
					
					if(direction == 0) {          // Bidirectional
						Edge edge = new Edge(fromNode, toNode, distance, label, true);
						graph.addEdge(edge);
						fromNode.addEdgeOut(edge);
						toNode.addEdgeIn(edge);
						fromNode.addEdgeIn(edge);
						toNode.addEdgeOut(edge);
						countBidirectional++;
						
					} else if(direction == 1) {   // One direction: base -> adj
						Edge edge = new Edge(fromNode, toNode, distance, label, false);
						graph.addEdge(edge);
						fromNode.addEdgeOut(edge);
						toNode.addEdgeIn(edge);
						countOneWay++;
					} else if(direction == -1) {  // One direction: adj -> base
						Edge edge = new Edge(toNode, fromNode, distance, label, false);
						graph.addEdge(edge);
						fromNode.addEdgeIn(edge);
						toNode.addEdgeOut(edge);
						countOneWayInverse++;
					} else {
						//System.out.println("Edge not created. Invalid direction: " + direction);
					}
					
					if(fromNode != null){
						graph.addNode(fromNode);
						fromNodeId = (long)fromNode.getId();
						hashExternalIdToId.put(externalFromNodeId, fromNodeId);
					}
					if(toNode != null){
						graph.addNode(toNode);
						toNodeId = (long)toNode.getId();
						hashExternalIdToId.put(externalToNodeId, toNodeId);
					}
					
				}
				//Results
				
				System.out.println("----------Dados do Grafo de Berlin gerado----------");
				System.out.println("Number of Nodes: " + graph.getNumberOfNodes());
				System.out.println("Number of Edges: " + graph.getNumberOfEdges());
				System.out.println("Count: " + count);
				System.out.println("Number of invalid direction in original edges: " + countInvalidDirection);
				System.out.println("Number of Bidirectional edges: " + countBidirectional);
				System.out.println("Number of OneWay edges: " + countOneWay);
				System.out.println("Number of OneWayInverse edges: " + countOneWayInverse);

				double finalTime = System.currentTimeMillis();
				double total = finalTime - initialTime;
				System.out.println("Initial date: " + initialDate);
				System.out.println("Final date: " + new Date());
				System.out.println("Total time: " + total);
				
				for(Node node : graph.getNodes()){
					Point p = Geometries.point(node.getLatitude(), node.getLongitude());
					graph.setRTree(graph.getRTree().add(node.getId(), p));
					//graph.setRStarTree(graph.getRStarTree().add(node.getId(), p));
				}

	/*			for (long node = 0; node < graph.getNumberOfNodes(); node++) {
					Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
					graph.setRTree(graph.getRTree().add(node, p));
				}*/
				
				return graph;
			
	}

	//Berlin or Bremen
		public Graph generate(String name){
				
				Date date = new Date();
				String initialDate = date.toString();
				double initialTime = System.currentTimeMillis();
				
				Graph graph = new Graph();
				
				//Linux
				GraphHopper gh = OSMToGraphHopperReader.createGraph("/home/lucasvasconcelos/Downloads/"+name.toLowerCase()+"-latest.osm.pbf", "/home/lucasvasconcelos/"+name, false, false);
	
				//Windows
				//GraphHopper gh = OSMToGraphHopperReader.createGraph("/users/vasco/Downloads/"+name.toLowerCase()+"-latest.osm.pbf", "/users/vasco/Downloads/Graphast-Graph-Test/"+name, false, false);
				
				
				GraphStorage gs = gh.getGraph();
				EdgeIterator edgeIterator = gs.getAllEdges();
				
				//To know which node has already check
				Int2LongOpenHashMap hashExternalIdToId = new Int2LongOpenHashMap();
				
				//Statistics
				int count = 0;
				int countInvalidDirection = 0;
				int countBidirectional= 0;
				int countOneWay = 0;
				int countOneWayInverse = 0;
				
				while(edgeIterator.next()) {
					count++;
					
					int externalFromNodeId = edgeIterator.getBaseNode();
					int externalToNodeId = edgeIterator.getAdjNode();
					
					int distance = (int)NumberUtils.round(edgeIterator.getDistance() * 1000, 0); // Convert distance from meters to millimeters
					
					String label = edgeIterator.getName();
					
					double latitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalFromNodeId)));
					double longitudeFrom = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalFromNodeId)));	

					double latitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLatitude(externalToNodeId)));
					double longitudeTo = latLongToDouble(latLongToInt(gs.getNodeAccess().getLongitude(externalToNodeId)));
					
					Node fromNode = null,toNode = null;
					
					long fromNodeId, toNodeId;
					
					if(!hashExternalIdToId.containsKey(externalFromNodeId)){

						fromNode = new Node(externalFromNodeId, latitudeFrom, longitudeFrom);
						graph.addNode(fromNode);
						fromNodeId = (long)fromNode.getId();
						hashExternalIdToId.put(externalFromNodeId, fromNodeId);
					} 
					else {
						fromNodeId = hashExternalIdToId.get(externalFromNodeId);
						
						//Nodes search for the complete structure (test used to maintain the structure) 
						for(int i=0; i< graph.getNodes().size() ; i++){
							if(graph.getNodes().get(i).getId() == fromNodeId){
								fromNode = graph.getNodes().get(i);
							}
						}
					}

					if(!hashExternalIdToId.containsKey(externalToNodeId)){
						toNode = new Node(externalToNodeId, latitudeTo, longitudeTo);
						graph.addNode(toNode);
						toNodeId = (long)toNode.getId();
						hashExternalIdToId.put(externalToNodeId, toNodeId);
					} 
					else {
						toNodeId = hashExternalIdToId.get(externalToNodeId);
						
						//Nodes search for the complete structure (test used to maintain the structure)
						for(int i=0; i< graph.getNodes().size() ; i++){
							if(graph.getNodes().get(i).getId() == toNodeId){
								toNode = graph.getNodes().get(i);
							}
						}
					}
					
					if(fromNodeId == toNodeId) {
						System.out.println("Edge not created, because fromNodeId:"+ fromNodeId + "== toNodeId:" +toNodeId);
						continue;
					}
					
					
					//Direction (To Do)
					int direction = 9999;
					try {
						direction = getDirection(edgeIterator.getFlags());
					} catch (Exception e) {
						countInvalidDirection++;
					}
					
					if(direction == 0) {          // Bidirectional
						Edge edge = new Edge(fromNode, toNode, distance, label, true);
						graph.addEdge(edge);
						fromNode.addEdgeOut(edge);
						toNode.addEdgeIn(edge);
						fromNode.addEdgeIn(edge);
						toNode.addEdgeOut(edge);
						countBidirectional++;
						
					} else if(direction == 1) {   // One direction: base -> adj
						Edge edge = new Edge(fromNode, toNode, distance, label, false);
						graph.addEdge(edge);
						fromNode.addEdgeOut(edge);
						toNode.addEdgeIn(edge);
						countOneWay++;
					} else if(direction == -1) {  // One direction: adj -> base
						Edge edge = new Edge(toNode, fromNode, distance, label, false);
						graph.addEdge(edge);
						fromNode.addEdgeIn(edge);
						toNode.addEdgeOut(edge);
						countOneWayInverse++;
					} else {
						System.out.println("Edge not created. Invalid direction: " + direction);
					}
					
					if(fromNode != null){
						graph.addNode(fromNode);
						fromNodeId = (long)fromNode.getId();
						hashExternalIdToId.put(externalFromNodeId, fromNodeId);
					}
					if(toNode != null){
						graph.addNode(toNode);
						toNodeId = (long)toNode.getId();
						hashExternalIdToId.put(externalToNodeId, toNodeId);
					}
					
				}
				//Results
				
				System.out.println("----------Dados do Grafo de "+name+" gerado----------");
				System.out.println("Number of Nodes: " + graph.getNumberOfNodes());
				System.out.println("Number of Edges: " + graph.getNumberOfEdges());
				System.out.println("Count: " + count);
				System.out.println("Number of invalid direction in original edges: " + countInvalidDirection);
				System.out.println("Number of Bidirectional edges: " + countBidirectional);
				System.out.println("Number of OneWay edges: " + countOneWay);
				System.out.println("Number of OneWayInverse edges: " + countOneWayInverse);

				double finalTime = System.currentTimeMillis();
				double total = finalTime - initialTime;
				System.out.println("Initial date: " + initialDate);
				System.out.println("Final date: " + new Date());
				System.out.println("Total time: " + total);
				
				for(Node node : graph.getNodes()){
					Point p = Geometries.point(node.getLatitude(), node.getLongitude());
					graph.setRTree(graph.getRTree().add(node.getId(), p));
					//graph.setRStarTree(graph.getRStarTree().add(node.getId(), p));
				}

	/*			for (long node = 0; node < graph.getNumberOfNodes(); node++) {
					Point p = Geometries.point(graph.getNode(node).getLatitude(), graph.getNode(node).getLongitude());
					graph.setRTree(graph.getRTree().add(node, p));
				}*/
				
				return graph;
			}
	
	
	private int getDirection(long flags) {
		
		long direction = (flags & 3);

		if(direction ==  1) {
			return 1;   // One direction: From --> To 
		} else if(direction ==  2) {
			return -1;  // One direction: To --> From
		} else if(direction == 3) {
			return 0;   // Bidirectional: To <--> From
		}
		else {
			throw new IllegalArgumentException("Invalid flag: " + direction);
		}
	}
	
	//Methods extract from Graphast.util.GeoUtils
	public static int latLongToInt(double number) {
		return (int) NumberUtils.convert(number, LAT_LONG_CONVERTION_FACTOR);
	}
	
	public static double latLongToDouble(int number) {
		return number / (double) LAT_LONG_CONVERTION_FACTOR;
	}
	
	
}
