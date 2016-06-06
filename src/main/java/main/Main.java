package main;
import com.graphhopper.util.StopWatch;

import model.Edge;
import model.Graph;
import model.Node;
import query.Path;
import query.ShortestPath;
import util.MemMon;


public class Main {

	static long start;
	static long now;
	static double end;
	
	public Main() {
	}

	public static void main(String[] args) {

		GraphGenarator generator = new GraphGenarator();
		
		
		//Small examples create for tests
		/*Graph graphTest1 = generator.generateExample1();
		Graph graphTest2 = generator.generateExample2();
		Graph graphTest3 = generator.generateExample3();
		*/
		
		//Medium example
		
		//Monaco
		Graph graphTest4 = generator.generateExample4();
		
		//Berlin
		//Graph graphTest5 = generator.generateExample5();
		
		
		//Execution and time for each small test
		/*StopWatch sw1 = new StopWatch();
		StopWatch sw2 = new StopWatch();
		StopWatch sw3 = new StopWatch();
		
		sw1.start();
		test1(graphTest1);
		sw1.stop();
		System.out.println("Test GraphTest1 , Time: "+ sw1.getTime()+"ms");
		
		sw2.start();
		test2(graphTest2);
		sw2.stop();
		System.out.println("Test GraphTest2 , Time: "+ sw2.getTime()+"ms");
		
		sw3.start();
		test3(graphTest3);
		sw3.stop();
		System.out.println("Test GraphTest3 , Time: "+ sw3.getTime()+"ms");
		*/
		
		for(int i=0; i<graphTest4.getEdges().size() ; i++){
			if(graphTest4.getEdges().get(i).getLabel().equalsIgnoreCase("Avenue des Ligures")){
				System.out.println("Latitude : "+graphTest4.getEdges().get(i).getFromNode().getLatitude());
				System.out.println("Longitude : "+graphTest4.getEdges().get(i).getFromNode().getLongitude());
			}
			if(graphTest4.getEdges().get(i).getLabel().equalsIgnoreCase("Lacets Saint-Léon")){
				System.out.println("LatitudeV : "+graphTest4.getEdges().get(i).getFromNode().getLatitude());
				System.out.println("LongitudeV : "+graphTest4.getEdges().get(i).getFromNode().getLongitude());
			}
		}
		StopWatch sw = new StopWatch();
		sw.start();
		test4(graphTest4);
		sw.stop();
		System.out.println("Time path Monaco with StopWatch: "+sw.getTime()+"ms");	
		
		
		/*start = System.currentTimeMillis();
		test4(graphTest5);
		now = System.currentTimeMillis();
		end = (now-start)/1000.0;
		System.out.println("Time path Berlin: "+ end);*/
	}

	private static void test4(Graph graphTest4) {

		ShortestPath path4 = new ShortestPath(graphTest4);
		
		/* TESTES GRAPHAST X HASHMAPTEST */
		
		//Padrao Graphast(Provavel que use funcao NearestNode)
		//Long source = graphTest4.getNodeId(43.740174,7.424376);
		//Long target = graphTest4.getNodeId(43.735554,7.416147);
		
		//Teste na mesma rua
		//source = (long) 1628;
		//target = (long) 1847;
		
		//Teste duas ruas
		//source = (long) 1219;
		//target = (long) 450;

		//Teste tres ruas
		//source = (long) 1219;
		//target = (long) 1534;
		
		//Teste HashMapTest
		//Long source = graphTest4.getNodeId(43.743175,7.428688);
		//Long target = graphTest4.getNodeId(43.737646,7.419599);
		
		//teste2 - HashMapTest
		//Long source = graphTest4.getNodeId(43.727421, 7.4133).longValue();
		//Long target = graphTest4.getNodeId(43.730947, 7.415229).longValue();
		
		//Teste 3 - HashMapTest
		//Long source = graphTest4.getNodeId(43.743543, 7.429805).longValue();
		//Long target = graphTest4.getNodeId(43.728167, 7.416819).longValue();
		
		//Test 4 - HashMapTest
		//Long source = graphTest4.getNodeId(43.725435 ,7.418613).longValue();
		//Long target = graphTest4.getNodeId(43.749232 ,7.433088).longValue();
		
		//Test5 - HashMap-Index-Test (Padrão 2 Graphast)
		Long source = graphTest4.getNodeId(43.72842465479131, 7.414896579419745);
		Long target = graphTest4.getNodeId(43.7354373276704, 7.4212202598427295);
		
		//In this moment, you choose your source and target nodes
		StopWatch sw = new StopWatch();

		sw.start();
		Path shortestPath = new Path();
		shortestPath = path4.executeDijkstra(source,target);
	
		
		System.out.println("Path feito");
		
		System.out.println("----------Test 4----------");
		
		if(shortestPath == null){
			System.out.println("Path impossible");
			sw.stop();
		}
		else{		
			Edge edge = null;
			int totalDistance = 0;
			//Arestas além da conta
			for(int i = 0 ; i< shortestPath.getPathMin().size()-1 ; i++){
				edge = findEdge(shortestPath.getPathMin().get(i),shortestPath.getPathMin().get(i+1));
				if(edge == null){
					System.out.println("Epa main");
				}
				else{
					if(edge.getLabel().isEmpty()){
						System.out.println("{" + edge.getDistance() + "," + edge.isBidirectional() + "}");
					}
					else{
						System.out.println("{" + edge.getLabel() + "," + edge.getDistance() + "," + edge.isBidirectional() + "}");
					}
				}	
				totalDistance = totalDistance + edge.getDistance();
			}
			sw.stop();
			System.out.println("Total de arestas: "+(shortestPath.getPathMin().size()-1));
			shortestPath.setTotalDistance(totalDistance);
			System.out.println("Distância total: "+shortestPath.getTotalDistance());
		}
		System.out.println("Execution time of ShortestPathMonaco-HashMapTest: "+sw.getTime()+"ms");
		
	}

	private static void test3(Graph graphTest3) {
		
		ShortestPath path3 = new ShortestPath(graphTest3);
		
		//In this moment, you choose your source and target nodes
		Path shortestPath = path3.executeDijkstra(graphTest3.getNodes().get(1).getId(), graphTest3.getNodes().get(4).getId());
		
		System.out.println("----------Graph Test 3----------");
		
		if(shortestPath.getPathMin() == null){
			System.out.println("Path impossible");
		}
		else{
			System.out.println("Nodes:");
			
			for(Node node : shortestPath.getPathMin()){
				System.out.println(node.getLabel());
			}
		}
		
	}	
	private static void test2(Graph graphTest2) {
		
		ShortestPath path2 = new ShortestPath(graphTest2);
		
		//In this moment, you choose your source and target nodes
		Path shortestPath = path2.executeDijkstra(graphTest2.getNodes().get(0).getId(), graphTest2.getNodes().get(6).getId());
		
		System.out.println("----------Graph Test 2----------");

		if(shortestPath.getPathMin() == null){
			System.out.println("Path impossible");
		}
		else{
			System.out.println("Nodes:");
			
			for(Node node : shortestPath.getPathMin()){
				System.out.println(node.getLabel());
			}
		}
		
	}

	private static void test1(Graph graphTest1) {
		
		ShortestPath path1 = new ShortestPath(graphTest1);
		
		//In this moment, you choose your source and target nodes
		Path shortestPath = path1.executeDijkstra(graphTest1.getNodes().get(0).getId(), graphTest1.getNodes().get(6).getId());
		
		System.out.println("----------Graph Test 1----------");

		if(shortestPath.getPathMin() == null){
			System.out.println("Path impossible");
		}
		else{
			System.out.println("Nodes:");
			
			for(Node node : shortestPath.getPathMin()){
				System.out.println(node.getLabel());
			}
		}
		
	}
	
	public static Edge findEdge(Node source, Node target){
		for(Edge edge : source.getEdgeout()){
			if(edge.isBidirectional()){
				if(edge.getToNode() == target){
					return edge;
				}
				else if(edge.getFromNode() == target){
					return edge;
				}
			}
			else{
				if(edge.getToNode() == target){
					return edge;
				}
			}
		}
		return null;
	}

	
}
