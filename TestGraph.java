
import java.util.*;

public class TestGraph {
	
	/*
	 * Class for testing the graph and how it uses Dijkstra’s algorithm for finding shortest paths
	 */
	
	public static void main (String[] args) {
		
 	// -------------------------------------------------------------------------------------------
	// Test Part 1 - Created A Graph
	// -------------------------------------------------------------------------------------------
	
		FindPaths find = new FindPaths();
		System.out.println("Reading file 1 for vertices and file 2 for edges.");
//		MyGraph G = find.readGraph("/Users/tayseanwilson-nolan/eclipse-workspace/CS2210/src/vertex.txt", "/Users/tayseanwilson-nolan/eclipse-workspace/CS2210/src/edge.txt");
		MyGraph G = find.readGraph("vertex.txt", "edge.txt"); // INSERT PATH TO USES VERTEX.TXT and EDGE.TXT

		System.out.println("\nDisplay vertices and edges in G.");
		Iterator traverseV = G.vertices().iterator();
		G.edges();
		// Get a few Vertices vertices in G to test

		Vertex A = (Vertex)traverseV.next();
		Vertex B = (Vertex)traverseV.next();
		Vertex C = (Vertex)traverseV.next();
		Vertex D = (Vertex)traverseV.next();
		Vertex E = (Vertex)traverseV.next();
		Vertex F = (Vertex)traverseV.next();
		Vertex Gv = (Vertex)traverseV.next();
		Vertex H = (Vertex)traverseV.next();
		Vertex I = (Vertex)traverseV.next();
		Vertex J = (Vertex)traverseV.next();
		Vertex K = (Vertex)traverseV.next();
		// ...
		
		System.out.println("\nPerforming Adjacent Vertices Function: ");
		G.adjacentVertices(A);
		
//		System.out.println("\nFinding a couple instances for edge cost.");
		System.out.println("\nPerforming Edge Cost Function: ");
		G.edgeCost(D, A);
		G.edgeCost(A, D);
		G.edgeCost(A, C);
		// ...
		
		
	// -------------------------------------------------------------------------------------------
	// Test Part 2 - Find Shortest Path Using (Testing Implementation of Dijkstra’s algorithm)
	// -------------------------------------------------------------------------------------------
	 
		
		G.shortestPath(A, J);
		G.shortestPath(B, B);
		
		// USER INPUT
		System.out.println("\nNow receiving user input");
		Scanner input = new Scanner(System.in);
		int N = 1;
		while (N != 0) {
			System.out.print("\nEnter a non-zero integer (continue) or 0 (end): ");
			N = input.nextInt();
			if ( N==0) {break;}
			
//			MyGraph graph = find.readGraph("/Users/tayseanwilson-nolan/eclipse-workspace/CS2210/src/vertex.txt", "/Users/tayseanwilson-nolan/eclipse-workspace/CS2210/src/edge.txt");
			MyGraph graph = find.readGraph("vertex.txt", "edge.txt"); // INSERT PATH TO USES VERTEX.TXT and EDGE.TXT

			Iterator vertices = graph.vertices().iterator(); // show user all vertices
			
			input.nextLine(); // avoid skipping the next input
			
			System.out.print("Choose a source vertex (e.g. to choose v(ATL) enter \"ATL\"): ");
			String aTemp = input.nextLine();
			
			System.out.print("Choose a destination vertex (e.g. to choose v(DEN) enter \"DEN\"): ");
			String bTemp = input.nextLine();
			
			// LOCATE the two vertices
			
			Vertex a=null; Vertex b=null;
			boolean aFound = false; boolean bFound = false;
			
			// search the list of vertices
			while (vertices.hasNext()) {
				Vertex search = (Vertex)vertices.next();
				// if we found a then store search in A
				if (search.toString().equals(aTemp) && !aFound) {
					a = search;
				}
				// if we found b then store search in B
				if (search.toString().equals(bTemp)  && !bFound) {
					b = search;
				}
			}
			if (a == null || b == null) {
				System.out.println("Error: The source and destination vertex must exist in the collection of vertices.");
			}
			else {
				graph.shortestPath(a, b);
			} 

		}
		System.out.println("Program ending. Thank you for participating.");
	
	}

}

//Reading file 1 for vertices and file 2 for edges.
//
//Display vertices and edges in G.
//Vertices: v(ATL), v(ORD), v(DEN), v(IAH), v(IAD), v(MKC), v(LAX), v(JFK), v(SFO), v(SEA), v(IND)
//Edges: (ATL -> IAD, 143), (IAD -> ATL, 125), (ATL -> IND, 122), (IND -> ATL, 120), (ATL -> IAH, 174), (IAH -> ATL, 170), (ORD -> IND, 43), (IND -> ORD, 45), (ORD -> JFK, 189), (JFK -> ORD, 190), (DEN -> MKC, 140), (MKC -> DEN, 133), (DEN -> SFO, 246), (SFO -> DEN, 241), (DEN -> SEA, 250), (SEA -> DEN, 251), (IAH -> MKC, 173), (MKC -> IAH, 170), (IAH -> LAX, 333), (LAX -> IAH, 330), (SEA -> LAX, 300), (IND -> MKC, 115), (MKC -> IND, 110), (LAX -> SFO, 90), (SFO -> LAX, 92), (JFK -> IAD, 46), (IAD -> JFK, 49), (SFO -> SEA, 193), (SEA -> SFO, 195)
//
//Performing Adjacent Vertices Function: 
//v(ATL) exist in the collection. All vertices adjacent to v(ATL) are: v(IAD), v(IND), v(IAH)
//
//Performing Edge Cost Function: 
//<IAH, ATL> cost: 170
//<ATL, IAH> cost: 174
//No edge found from v(ATL) to v(DEN)
//
//Beginning Dijkstra's algorithm...
//All vertices have had their distances, predecessors, and marks initialized
//
//v(ATL) with distance 0 is the start/min. This vertex is now marked
//(ATL->IAD): v(IAD).distance=143, v(IAD).predecessor=(ATL), v(ATL).descendant=v(IAD)
//(IAD->ATL): v(IAD).descendant=v(ATL)
//(ATL->IND): v(IND).distance=122, v(IND).predecessor=(ATL), v(ATL).descendant=v(IND)
//(IND->ATL): v(IND).descendant=v(ATL)
//(ATL->IAH): v(IAH).distance=174, v(IAH).predecessor=(ATL), v(ATL).descendant=v(IAH)
//(IAH->ATL): v(IAH).descendant=v(ATL)
//
//v(IND) with distance 122 is the start/min. This vertex is now marked
//(ORD->IND): v(ORD).descendant=v(IND)
//(IND->ORD): v(ORD).distance=167, v(ORD).predecessor=(IND), v(IND).descendant=v(ORD)
//(IND->MKC): v(MKC).distance=237, v(MKC).predecessor=(IND), v(IND).descendant=v(MKC)
//(MKC->IND): v(MKC).descendant=v(IND)
//
//v(IAD) with distance 143 is the start/min. This vertex is now marked
//(JFK->IAD): v(JFK).descendant=v(IAD)
//(IAD->JFK): v(JFK).distance=192, v(JFK).predecessor=(IAD), v(IAD).descendant=v(JFK)
//
//v(ORD) with distance 167 is the start/min. This vertex is now marked
//
//v(IAH) with distance 174 is the start/min. This vertex is now marked
//(IAH->LAX): v(LAX).distance=507, v(LAX).predecessor=(IAH), v(IAH).descendant=v(LAX)
//(LAX->IAH): v(LAX).descendant=v(IAH)
//
//v(JFK) with distance 192 is the start/min. This vertex is now marked
//
//v(MKC) with distance 237 is the start/min. This vertex is now marked
//(DEN->MKC): v(DEN).descendant=v(MKC)
//(MKC->DEN): v(DEN).distance=370, v(DEN).predecessor=(MKC), v(MKC).descendant=v(DEN)
//
//v(DEN) with distance 370 is the start/min. This vertex is now marked
//(DEN->SFO): v(SFO).distance=616, v(SFO).predecessor=(DEN), v(DEN).descendant=v(SFO)
//(SFO->DEN): v(SFO).descendant=v(DEN)
//(DEN->SEA): v(SEA).distance=620, v(SEA).predecessor=(DEN), v(DEN).descendant=v(SEA)
//
//v(LAX) with distance 507 is the start/min. This vertex is now marked
//(LAX->SFO): v(SFO).distance=597, v(SFO).predecessor=(LAX), v(LAX).descendant=v(SFO)
//
//v(SFO) with distance 597 is the start/min. This vertex is now marked
//
//v(SEA) with distance 620 is the start/min. This vertex is now marked
//
//All edges in the graph have been relaxed. Our cloud has covered the entire graph G!
//
//Measuring shortest path from v(ATL) to v(SEA)
//All destination vertices of v(ATL) are: v(IAD), v(IND), v(IAH)
//v(IND) (d: 122) is the next vertex in our Path. All destination vertices of v(IND) are: v(ATL), v(ORD), v(MKC)
//v(MKC) (d: 237) is the next vertex in our Path. All destination vertices of v(MKC) are: v(DEN), v(IAH), v(IND)
//v(DEN) (d: 370) is the next vertex in our Path. All destination vertices of v(DEN) are: v(MKC), v(SFO), v(SEA)
//v(SEA) (d: 620) is the next vertex in our Path. Shortest path found.
//
//Shortest Path from v(ATL) to v(SEA): ATL IND MKC DEN SEA 
//Path cost: 620
//
//Shortest Path from v(ORD) to v(ORD): ORD 
//Path cost: 0
//
//Now receiving user input
//
//Enter a non-zero integer (continue) or 0 (end): 1
//Vertices: v(ATL), v(ORD), v(DEN), v(IAH), v(IAD), v(MKC), v(LAX), v(JFK), v(SFO), v(SEA), v(IND)
//Choose a source vertex (e.g. to choose v(ATL) enter "ATL": ATL
//Choose a destination vertex (e.g. to choose v(DEN) enter "DEN": DEN
//
//Beginning Dijkstra's algorithm...
//All vertices have had their distances, predecessors, and marks initialized
//
//v(ATL) with distance 0 is the start/min. This vertex is now marked
//(ATL->IAD): v(IAD).distance=143, v(IAD).predecessor=(ATL), v(ATL).descendant=v(IAD)
//(IAD->ATL): v(IAD).descendant=v(ATL)
//(ATL->IND): v(IND).distance=122, v(IND).predecessor=(ATL), v(ATL).descendant=v(IND)
//(IND->ATL): v(IND).descendant=v(ATL)
//(ATL->IAH): v(IAH).distance=174, v(IAH).predecessor=(ATL), v(ATL).descendant=v(IAH)
//(IAH->ATL): v(IAH).descendant=v(ATL)
//
//v(IND) with distance 122 is the start/min. This vertex is now marked
//(ORD->IND): v(ORD).descendant=v(IND)
//(IND->ORD): v(ORD).distance=167, v(ORD).predecessor=(IND), v(IND).descendant=v(ORD)
//(IND->MKC): v(MKC).distance=237, v(MKC).predecessor=(IND), v(IND).descendant=v(MKC)
//(MKC->IND): v(MKC).descendant=v(IND)
//
//v(IAD) with distance 143 is the start/min. This vertex is now marked
//(JFK->IAD): v(JFK).descendant=v(IAD)
//(IAD->JFK): v(JFK).distance=192, v(JFK).predecessor=(IAD), v(IAD).descendant=v(JFK)
//
//v(ORD) with distance 167 is the start/min. This vertex is now marked
//
//v(IAH) with distance 174 is the start/min. This vertex is now marked
//(IAH->LAX): v(LAX).distance=507, v(LAX).predecessor=(IAH), v(IAH).descendant=v(LAX)
//(LAX->IAH): v(LAX).descendant=v(IAH)
//
//v(JFK) with distance 192 is the start/min. This vertex is now marked
//
//v(MKC) with distance 237 is the start/min. This vertex is now marked
//(DEN->MKC): v(DEN).descendant=v(MKC)
//(MKC->DEN): v(DEN).distance=370, v(DEN).predecessor=(MKC), v(MKC).descendant=v(DEN)
//
//v(DEN) with distance 370 is the start/min. This vertex is now marked
//(DEN->SFO): v(SFO).distance=616, v(SFO).predecessor=(DEN), v(DEN).descendant=v(SFO)
//(SFO->DEN): v(SFO).descendant=v(DEN)
//(DEN->SEA): v(SEA).distance=620, v(SEA).predecessor=(DEN), v(DEN).descendant=v(SEA)
//
//v(LAX) with distance 507 is the start/min. This vertex is now marked
//(LAX->SFO): v(SFO).distance=597, v(SFO).predecessor=(LAX), v(LAX).descendant=v(SFO)
//
//v(SFO) with distance 597 is the start/min. This vertex is now marked
//
//v(SEA) with distance 620 is the start/min. This vertex is now marked
//
//All edges in the graph have been relaxed. Our cloud has covered the entire graph G!
//
//Measuring shortest path from v(ATL) to v(DEN)
//All destination vertices of v(ATL) are: v(IAD), v(IND), v(IAH)
//v(IND) (d: 122) is the next vertex in our Path. All destination vertices of v(IND) are: v(ATL), v(ORD), v(MKC)
//v(MKC) (d: 237) is the next vertex in our Path. All destination vertices of v(MKC) are: v(DEN), v(IAH), v(IND)
//v(DEN) (d: 370) is the next vertex in our Path. Shortest path found.
//
//Shortest Path from v(ATL) to v(DEN): ATL IND MKC DEN 
//Path cost: 370
//
//Enter a non-zero integer (continue) or 0 (end): 1
//Vertices: v(ATL), v(ORD), v(DEN), v(IAH), v(IAD), v(MKC), v(LAX), v(JFK), v(SFO), v(SEA), v(IND)
//Choose a source vertex (e.g. to choose v(ATL) enter "ATL": MKC
//Choose a destination vertex (e.g. to choose v(DEN) enter "DEN": IAH
//
//Beginning Dijkstra's algorithm...
//All vertices have had their distances, predecessors, and marks initialized
//
//v(MKC) with distance 0 is the start/min. This vertex is now marked
//(DEN->MKC): v(DEN).descendant=v(MKC)
//(MKC->DEN): v(DEN).distance=133, v(DEN).predecessor=(MKC), v(MKC).descendant=v(DEN)
//(IAH->MKC): v(IAH).descendant=v(MKC)
//(MKC->IAH): v(IAH).distance=170, v(IAH).predecessor=(MKC), v(MKC).descendant=v(IAH)
//(IND->MKC): v(IND).descendant=v(MKC)
//(MKC->IND): v(IND).distance=110, v(IND).predecessor=(MKC), v(MKC).descendant=v(IND)
//
//v(IND) with distance 110 is the start/min. This vertex is now marked
//(ATL->IND): v(ATL).descendant=v(IND)
//(IND->ATL): v(ATL).distance=230, v(ATL).predecessor=(IND), v(IND).descendant=v(ATL)
//(ORD->IND): v(ORD).descendant=v(IND)
//(IND->ORD): v(ORD).distance=155, v(ORD).predecessor=(IND), v(IND).descendant=v(ORD)
//
//v(DEN) with distance 133 is the start/min. This vertex is now marked
//(DEN->SFO): v(SFO).distance=379, v(SFO).predecessor=(DEN), v(DEN).descendant=v(SFO)
//(SFO->DEN): v(SFO).descendant=v(DEN)
//(DEN->SEA): v(SEA).distance=383, v(SEA).predecessor=(DEN), v(DEN).descendant=v(SEA)
//
//v(ORD) with distance 155 is the start/min. This vertex is now marked
//(ORD->JFK): v(JFK).distance=344, v(JFK).predecessor=(ORD), v(ORD).descendant=v(JFK)
//
//v(IAH) with distance 170 is the start/min. This vertex is now marked
//(IAH->LAX): v(LAX).distance=503, v(LAX).predecessor=(IAH), v(IAH).descendant=v(LAX)
//(LAX->IAH): v(LAX).descendant=v(IAH)
//
//v(ATL) with distance 230 is the start/min. This vertex is now marked
//(ATL->IAD): v(IAD).distance=373, v(IAD).predecessor=(ATL), v(ATL).descendant=v(IAD)
//(IAD->ATL): v(IAD).descendant=v(ATL)
//
//v(JFK) with distance 344 is the start/min. This vertex is now marked
//
//v(IAD) with distance 373 is the start/min. This vertex is now marked
//
//v(SFO) with distance 379 is the start/min. This vertex is now marked
//(LAX->SFO): v(LAX).descendant=v(SFO)
//(SFO->LAX): v(LAX).distance=471, v(LAX).predecessor=(SFO), v(SFO).descendant=v(LAX)
//
//v(SEA) with distance 383 is the start/min. This vertex is now marked
//
//v(LAX) with distance 471 is the start/min. This vertex is now marked
//
//All edges in the graph have been relaxed. Our cloud has covered the entire graph G!
//
//Measuring shortest path from v(MKC) to v(IAH)
//All destination vertices of v(MKC) are: v(DEN), v(IAH), v(IND)
//v(IAH) (d: 170) is the next vertex in our Path. Shortest path found.
//
//Shortest Path from v(MKC) to v(IAH): MKC IAH 
//Path cost: 170
//
//Enter a non-zero integer (continue) or 0 (end): 1
//Vertices: v(ATL), v(ORD), v(DEN), v(IAH), v(IAD), v(MKC), v(LAX), v(JFK), v(SFO), v(SEA), v(IND)
//Choose a source vertex (e.g. to choose v(ATL) enter "ATL": IAD
//Choose a destination vertex (e.g. to choose v(DEN) enter "DEN": IAD
//
//Shortest Path from v(IAD) to v(IAD): IAD 
//Path cost: 0
//
//Enter a non-zero integer (continue) or 0 (end): 1
//Vertices: v(ATL), v(ORD), v(DEN), v(IAH), v(IAD), v(MKC), v(LAX), v(JFK), v(SFO), v(SEA), v(IND)
//Choose a source vertex (e.g. to choose v(ATL) enter "ATL": sourceDNE
//Choose a destination vertex (e.g. to choose v(DEN) enter "DEN": destinationDNE
//Error: The source and destination vertex must exist in the collection of vertices.
//
//Enter a non-zero integer (continue) or 0 (end): 0
//Program ending. Thank you for participating.


