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
		Graph graphTest4 = generator.generateMonaco();
		
		//Luxembourg
		//Graph graphLux = generator.generate("Luxembourg");
		
		//Andorra
		//Graph graphAndorra = generator.generate("Andorra");
		
		//Berlin
		//Graph graphTest5 = generator.generateBerlin();
		
		
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

		StopWatch sw = new StopWatch();
		sw.start();
		test4(graphTest4);
		sw.stop();
		System.out.println("Time path Monaco with StopWatch: "+sw.getTime()+"ms");			
		
/*		StopWatch sw1 = new StopWatch();
		sw1.start();
		testLuxemburgo(graphLux);
		sw.stop();
		System.out.println("Time path Luxembourg with StopWatch: "+sw.getTime()+"ms");
*/		
/*		StopWatch sw1 = new StopWatch();
		sw1.start();
		testAndorra(graphAndorra);
		sw.stop();
		System.out.println("Time path Andorra with StopWatch: "+sw.getTime()+"ms");*/
		
		/*StopWatch sw2 = new StopWatch();
		sw2.start();
		test5(graphTest5);
		sw2.stop();
		System.out.println("Time path Berlin: "+ sw2.getTime()+"ms");*/
	}

	private static void testAndorra(Graph graphAndorra) {
		ShortestPath path5 = new ShortestPath(graphAndorra);
		
		
/*		Long source = graphLux.getNodes().get(100).getId();
		Long target = graphLux.getNodes().get(120).getId();
		
		System.out.println("Target: "+graphLux.getNodes().get(100).getLatitude()+","+graphLux.getNodes().get(100).getLongitude());
		System.out.println("Source: "+graphLux.getNodes().get(120).getLatitude()+","+graphLux.getNodes().get(120).getLongitude());
*/
		StopWatch st = new StopWatch();
		st.start();
		Long source = graphAndorra.getNodeId(42.509500, 1.537765);
		st.stop();
		StopWatch st2 = new StopWatch();
		st2.start();
		Long target = graphAndorra.getNodeId(42.506441, 1.529298);
		st2.stop();
		
		System.out.println("######Tempo para pegar o nó do grafo a partir da geoLocalização######");
		System.out.println("Tempo source : "+st.getTime()+"ms");
		System.out.println("Tempo target : "+st2.getTime()+"ms");
		
		StopWatch sw = new StopWatch();

		sw.start();
		Path shortestPath = new Path();
		shortestPath = path5.executeDijkstra(source,target);
	
		
		System.out.println("Path feito");
		
		System.out.println("----------Test ANdorra----------");
		
		if(shortestPath == null){
			System.out.println("Path impossible");
			sw.stop();
		}
		else{		
			Edge edge = null;
			int totalDistance = 0;
			
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
		System.out.println("Execution time of ShortestPathAndorra-HashMapTest: "+sw.getTime()+"ms");

		
	}
	
	private static void testLuxemburgo(Graph graphLux) {
		ShortestPath path5 = new ShortestPath(graphLux);
		
		
/*		Long source = graphLux.getNodes().get(100).getId();
		Long target = graphLux.getNodes().get(120).getId();
		
		System.out.println("Target: "+graphLux.getNodes().get(100).getLatitude()+","+graphLux.getNodes().get(100).getLongitude());
		System.out.println("Source: "+graphLux.getNodes().get(120).getLatitude()+","+graphLux.getNodes().get(120).getLongitude());
*/
		
		StopWatch st = new StopWatch();
		st.start();
		Long source = graphLux.getNodeId(49.607423, 6.115862);
		st.stop();
		StopWatch st2 = new StopWatch();
		st2.start();
		Long target = graphLux.getNodeId(49.610963, 6.107624);
		st2.stop();
		
		System.out.println("######Tempo para pegar o nó do grafo a partir da geoLocalização######");
		System.out.println("Tempo source R*Tree: "+st.getTime()+"ms");
		System.out.println("Tempo target R*Tree: "+st2.getTime()+"ms");
		
		StopWatch sw = new StopWatch();

		sw.start();
		Path shortestPath = new Path();
		shortestPath = path5.executeDijkstra(source,target);
	
		
		System.out.println("Path feito");
		
		System.out.println("----------Test Luxembourg----------");
		
		if(shortestPath == null){
			System.out.println("Path impossible");
			sw.stop();
		}
		else{		
			Edge edge = null;
			int totalDistance = 0;
			
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
		System.out.println("Execution time of ShortestPathLuxembourg-HashMapTest: "+sw.getTime()+"ms");
		System.out.println("Foi com R*Tree");

		
	}

	private static void test5(Graph graphTest5){
		
		ShortestPath path5 = new ShortestPath(graphTest5);
		
		
/*		Long source = graphTest5.getNodes().get(100).getId();
		Long target = graphTest5.getNodes().get(120).getId();
		
		System.out.println("Target: "+graphTest5.getNodes().get(100).getLatitude()+","+graphTest5.getNodes().get(100).getLongitude());
		System.out.println("Source: "+graphTest5.getNodes().get(120).getLatitude()+","+graphTest5.getNodes().get(120).getLongitude()); */
		
		StopWatch st = new StopWatch();
		st.start();
		Long source = graphTest5.getNodeId(52.532358, 13.396231);
		st.stop();
		StopWatch st2 = new StopWatch();
		st2.start();
		Long target = graphTest5.getNodeId(52.530528, 13.447217);
		st2.stop();
		
		System.out.println("######Tempo para pegar o nó do grafo a partir da geoLocalização######");
		System.out.println("Tempo source : "+st.getTime()+"ms");
		System.out.println("Tempo target : "+st2.getTime()+"ms");

		StopWatch sw = new StopWatch();

		sw.start();
		Path shortestPath = new Path();
		shortestPath = path5.executeDijkstra(source,target);
	
		
		System.out.println("Path feito");
		
		System.out.println("----------Test Berlin----------");
		
		if(shortestPath == null){
			System.out.println("Path impossible");
			sw.stop();
		}
		else{		
			Edge edge = null;
			int totalDistance = 0;
			
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
		System.out.println("Execution time of ShortestPathBerlin-HashMapTest: "+sw.getTime()+"ms");
		System.out.println("Feito com R*Tree");
		
	}

	
	//Monaco Test
	private static void test4(Graph graphTest4) {

		ShortestPath path4 = new ShortestPath(graphTest4);
		
		/* TESTES GRAPHAST X HASHMAPTEST */
		
		//Padrao Graphast(usa NearestNode)
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

	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
