package main;

import com.graphhopper.util.StopWatch;

import model.Edge;
import model.Graph;
import model.Node;
import query.Path;
import query.ShortestPath;

/*Class for test graphs fast*/

/*	TO DO
 * -Atualizar com a main
 * -Encontrar uma forma que os testes possam ficar aqui, sem erros.
 */

public class Test {

	private String graphName;
	private Graph graphTest;
	
	
	public Test(String graphName, Graph graphTest) {
		super();
		this.graphName = graphName;
		this.graphTest = graphTest;
	}
	
	private void pickTest(){
		if(this.graphName.equalsIgnoreCase("Berlin")){
			testBerlin();
		}else if(this.graphName.equalsIgnoreCase("Monaco")){
			testMonaco();
		}else if(this.graphName.equalsIgnoreCase("Bremen")){
			testBremen();
		}else if(this.graphName.equalsIgnoreCase("Example1")){
			test1();
		}
	}

	//teste Monaco
	private void testMonaco() {

		ShortestPath path4 = new ShortestPath(this.graphTest);
		
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
		Long source = this.graphTest.getNodeId(43.72842465479131, 7.414896579419745);
		Long target = this.graphTest.getNodeId(43.7354373276704, 7.4212202598427295);
		
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

	private void testBerlin(){
		
		ShortestPath path5 = new ShortestPath(this.graphTest);
		
		Long source = this.graphTest.getNodes().get(100).getId();
		Long target = this.graphTest.getNodes().get(120).getId();
		
		System.out.println("Target: "+this.graphTest.getNodes().get(100).getLatitude()+","+this.graphTest.getNodes().get(100).getLongitude());
		System.out.println("Source: "+this.graphTest.getNodes().get(120).getLatitude()+","+this.graphTest.getNodes().get(120).getLongitude());
		

		StopWatch sw = new StopWatch();

		sw.start();
		Path shortestPath = new Path();
		shortestPath = path5.executeDijkstra(source,target);
	
		
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
		System.out.println("Execution time of ShortestPathBerlin-HashMapTest: "+sw.getTime()+"ms");
		
	}

	
	private void testBremen() {
		// TODO Auto-generated method stub
		
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

	private void test1() {
		
		ShortestPath path1 = new ShortestPath(this.graphTest);
		
		//In this moment, you choose your source and target nodes
		Path shortestPath = path1.executeDijkstra(this.graphTest.getNodes().get(0).getId(), this.graphTest.getNodes().get(6).getId());
		
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
