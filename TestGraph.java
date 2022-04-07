
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
	 
		G.shortestPath(A, C);
	
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
//Performing Algorithm on Graph G
//
//v(ATL) with distance 0 is the start/min. This vertex is now marked
//(ATL->IAD): Updating the distance of v(IAD) to: 143 and changing its predecessor to (ATL). v(IAD) is now a descendant of v(ATL)
//(IAD->ATL): v(ATL) is now a descendant of v(IAD). Since v(IAD) is a source than we don't update it's distance.
//(ATL->IND): Updating the distance of v(IND) to: 122 and changing its predecessor to (ATL). v(IND) is now a descendant of v(ATL)
//(IND->ATL): v(ATL) is now a descendant of v(IND). Since v(IND) is a source than we don't update it's distance.
//(ATL->IAH): Updating the distance of v(IAH) to: 174 and changing its predecessor to (ATL). v(IAH) is now a descendant of v(ATL)
//(IAH->ATL): v(ATL) is now a descendant of v(IAH). Since v(IAH) is a source than we don't update it's distance.
//
//v(IND) with distance 122 is the start/min. This vertex is now marked
//(ORD->IND): v(IND) is now a descendant of v(ORD). Since v(ORD) is a source than we don't update it's distance.
//(IND->ORD): Updating the distance of v(ORD) to: 167 and changing its predecessor to (IND). v(ORD) is now a descendant of v(IND)
//(IND->MKC): Updating the distance of v(MKC) to: 237 and changing its predecessor to (IND). v(MKC) is now a descendant of v(IND)
//(MKC->IND): v(IND) is now a descendant of v(MKC). Since v(MKC) is a source than we don't update it's distance.
//
//v(IAD) with distance 143 is the start/min. This vertex is now marked
//(JFK->IAD): v(IAD) is now a descendant of v(JFK). Since v(JFK) is a source than we don't update it's distance.
//(IAD->JFK): Updating the distance of v(JFK) to: 192 and changing its predecessor to (IAD). v(JFK) is now a descendant of v(IAD)
//
//v(ORD) with distance 167 is the start/min. This vertex is now marked
//
//v(IAH) with distance 174 is the start/min. This vertex is now marked
//(IAH->LAX): Updating the distance of v(LAX) to: 507 and changing its predecessor to (IAH). v(LAX) is now a descendant of v(IAH)
//(LAX->IAH): v(IAH) is now a descendant of v(LAX). Since v(LAX) is a source than we don't update it's distance.
//
//v(JFK) with distance 192 is the start/min. This vertex is now marked
//
//v(MKC) with distance 237 is the start/min. This vertex is now marked
//(DEN->MKC): v(MKC) is now a descendant of v(DEN). Since v(DEN) is a source than we don't update it's distance.
//(MKC->DEN): Updating the distance of v(DEN) to: 370 and changing its predecessor to (MKC). v(DEN) is now a descendant of v(MKC)
//
//v(DEN) with distance 370 is the start/min. This vertex is now marked
//(DEN->SFO): Updating the distance of v(SFO) to: 616 and changing its predecessor to (DEN). v(SFO) is now a descendant of v(DEN)
//(SFO->DEN): v(DEN) is now a descendant of v(SFO). Since v(SFO) is a source than we don't update it's distance.
//(DEN->SEA): Updating the distance of v(SEA) to: 620 and changing its predecessor to (DEN). v(SEA) is now a descendant of v(DEN)
//
//v(LAX) with distance 507 is the start/min. This vertex is now marked
//(LAX->SFO): Updating the distance of v(SFO) to: 597 and changing its predecessor to (LAX). v(SFO) is now a descendant of v(LAX)
//
//v(SFO) with distance 597 is the start/min. This vertex is now marked
//
//v(SEA) with distance 620 is the start/min. This vertex is now marked
//
//All edges in the graph have been relaxed. Our cloud has covered the entire graph G!
//
//Measuring shortest path from v(ATL) to v(DEN)
//v(MKC) v(SFO) v(SEA) All destination vertices of v(ATL) are: v(IAD), v(IND), v(IAH)
//v(IND) (d: 122) is the next vertex in our Path. All destination vertices of v(IND) are: v(ATL), v(ORD), v(MKC)
//v(MKC) (d: 237) is the next vertex in our Path. All destination vertices of v(MKC) are: v(DEN), v(IAH), v(IND)
//v(DEN) (d: 370) is the next vertex in our Path. Shortest path found.
//
//Shortest Path from v(ATL) to v(DEN): ATL IND MKC DEN 
//Path cost: 370
