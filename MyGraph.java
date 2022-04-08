
import java.util.*;



/**
 * A representation of a graph. No negative cost edges in the graph.
 */

//**--------------------------------------------------------------------------------------------**
// PART 1 - Building A Graph
//**--------------------------------------------------------------------------------------------**

/*
 * Implement a graph representation.
 * The operations to the MyGraph class follow the implementation of the Graph interface. 
 * Vertex, Edge, and MyGraph classes
 * Should implement a graph and efficient (in particular, good asymptotic complexity for the operations)
 * Needs a good graph representation for computing shortest paths according to Dijkstra’s algorithm
 * Should protect its abstraction from bad clients.
 * 
 * The Constructor
 * Checks that the arguments make sense and throw an appropriate exception otherwise. 
 * 		– The edges should involve only vertices with labels that are in the vertices of the graph.
 * 			Ex.There should be no edge from or to a vertex labeled A if there is no vertex with label A.
 * 		– Edge weights should not be negative.
 * 		– Do not throw an exception if:
 * 			A. the collection of vertices has repeats in it:
 * 			Example: If two vertices in the collection have the same label, ignore the second redundant info
 *		– Do throw an exception if:
 *			A. the collection of edges has the same directed edge more than once with a different weight. 
 *			Note: in a directed graph an edge from A to B is not the same as an edge from B to A. 
 *		- Do not throw an exception if:
 *			A. an edge appears redundantly with the same weight; ignore the redundant edge info
 */

public class MyGraph implements Graph {
	
	private Collection<Vertex> collectionV;
	
	private Collection<Edge> collectionE;
	
	private Path p;
	
	private int n; // number of vertices found in dijktra's algorithm
	
	private boolean covered; // if all edges have been relaxed and the graph is covered
	
	//------------------------------------------------------------------------------------------------
	// Constructor
	//------------------------------------------------------------------------------------------------

	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		/**
		 * Creates a MyGraph object with the given collection of vertices and the given collection of edges.
		 * @param v
		 *            a collection of the vertices in this graph
		 * @param e
		 *            a collection of the edges in this graph
		 */
		this.collectionV = v;
		this.collectionE = e;
		this.n=0;
		this.covered = false;
	}

	//------------------------------------------------------------------------------------------------
	// Getter Methods
	//------------------------------------------------------------------------------------------------
	
	@Override
	public Collection<Vertex> vertices() {
		/**
		 * Returns the collection of vertices of this graph
		 * @return the vertices as a collection (which is anything iterable)
		 */
		
		
		Collection<Vertex> vertices = this.collectionV;
		if (vertices == null)
			System.out.println("Collection empty.");
		else {
			// create iterator to traverse
			Iterator<Vertex> traverse = vertices.iterator();
			//output collection
			String output = "";
			while (traverse.hasNext()) {
				output += traverse.next().getLabel();
				if (traverse.hasNext())
					output += ", ";
				
			}
			System.out.println("Vertices: " + output);
		}
		
		
		// return collection
		return this.collectionV;
	}


	@Override
	public Collection<Edge> edges() {
		/**
		 * Returns the collection of edges of this graph
		 * @return the edges as a collection (which is anything iterable)
		 */
		Collection<Edge> edges = this.collectionE;
		if (edges == null)
			System.out.println("Collection empty.");
		else {
			// create iterator to traverse
			Iterator<Edge> traverse = edges.iterator();
			//output collection
			String output = "";
			while (traverse.hasNext()) {
				output += traverse.next().toString();
				if (traverse.hasNext())
					output += ", ";
				
			}
			System.out.println("Edges: " + output);
		}
		
		return this.collectionE;
	}
	

	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) throws IllegalArgumentException {
		/**
		 * Returns a collection of vertices adjacent to a given vertex v. i.e., the
		 * set of all vertices w where edges v -> w exist in the graph. Return an
		 * empty collection if there are no adjacent vertices.
		 * @param v
		 *            one of the vertices in the graph
		 * @return an iterable collection of vertices adjacent to v in the graph
		 * @throws IllegalArgumentException
		 *             if v does not exist.
		 */
		
		/*
		 * Steps:
		 * 1. Traverse through the collection of edges (this.collectionE)
		 * 2. if source vertex equals 'v' then add destination to collection
		 * 		- if the adjacent vertex with [label] already exist then ignore
		 * 3. if destination vertex equals 'v' then add source to collection
		 * 		- if the adjacent vertex with [label] already exist then ignore
		 * 
		 */
		
//		System.out.print("Performing Adjacent Vertices Function: ");
		
		Collection<Vertex> adjacentVertices = new ArrayList();
		int countAdV = 0;
		
		
		// create iteration of collection edges (collectionE) and vertices (collectionV)
		Collection<Edge> edges = this.collectionE;
		Iterator<Edge> traverseEdges = edges.iterator(); 
		
		Collection<Vertex> vertex = this.collectionV;
		Iterator<Vertex> traverseVertices = vertex.iterator();
		
		// boolean to find v
		boolean foundV = false;
		
		// before method starts check if the collection is valid (all the same directed edges has same weight)
		boolean differentWeight = checkDirectedEdgesWeight(edges);
		if (differentWeight) {
			throw new IllegalArgumentException("Error");
		}
		
		while (traverseVertices.hasNext()) {
			Vertex currentV = traverseVertices.next();
			if (currentV.getLabel().equals(v.getLabel())) {
				foundV = true;
				System.out.print(v.getLabel() + " exist in the collection. ");
				break;
			}
		}
		
		if (!foundV) {
			throw new IllegalArgumentException("Error");
		}
		
		// traverse edges
		while (traverseEdges.hasNext()) {
			Edge incidentEdge = traverseEdges.next();
			Vertex destination = incidentEdge.getDestination();
			Vertex source = incidentEdge.getSource();
			// create iteration of adjacentVertices 
			Iterator<Vertex> traverseAV = adjacentVertices.iterator(); 
			
			// if destination vertex equals 'v' then add source to collection
			if (destination.getLabel().equals(v.getLabel()) ) {
				// check if the adjacent vertex with label already exist
				boolean duplicateFound = false;
				while (traverseAV.hasNext()) {
					if (source.getLabel().equals(traverseAV.next().getLabel())) {
						duplicateFound = true; // if exist then found is true and we will not add the source
					}
				}
				if (!duplicateFound) {
					adjacentVertices.add(source);
					countAdV++;
				}
			}
			
			// if source vertex equals 'v' then add destination to collection
			if (source.getLabel().equals(v.getLabel()) ) {
				// check if the adjacent vertex with label already exist
				boolean duplicateFound = false;
				while (traverseAV.hasNext()) {
					if (destination.getLabel().equals(traverseAV.next().getLabel())) {
						duplicateFound = true; // if exist then found is true and we will not add the source
					}
				}
				if (!duplicateFound) {
					adjacentVertices.add(destination);
					countAdV++;
				}
			}
		}	
		
		// final traversal to print out the adjacentVertices
		if (countAdV > 0 ) {
			String displayVertices = "";
			Iterator traverseAdjacent = adjacentVertices.iterator();
			while (traverseAdjacent.hasNext()) {
				// need to an initialize the object as a vertex before the getLabel method is accessible
				Vertex temp = new Vertex(traverseAdjacent.next().toString());
				displayVertices += temp.getLabel();
				if (traverseAdjacent.hasNext())
					displayVertices += ", ";
			}
			System.out.println("All vertices adjacent to " + v.getLabel() + " are: " + displayVertices);
		}
		else
			System.out.println("No vertices are adjacent to " + v.getLabel());

		
		return adjacentVertices;
	}
	

	@Override
	public int edgeCost(Vertex a, Vertex b) throws IllegalArgumentException {
		/**
		 * Tests whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph. 
		 * Assumes that we do not have negative cost edges in the graph.
		 * @param a
		 *            one vertex
		 * @param b
		 *            another vertex
		 * @return cost of edge if there is a directed edge from a to b in the
		 *         graph, return -1 otherwise.
		 * @throws IllegalArgumentException
		 *             if a or b do not exist.
		 */
		
		/*
		 * Steps:
		 * 1. Traverse through the provided collection of edges (collectionE)
		 * 2. if a is source and b is destination or b is source and a is destination then TRUE
		 * 		FALSE otherwise
		 * 3. if either a or b does not exist then throw EXCEPTION
		 * 4. find the first directed edge and return the cost (weight)
		 */
		
//		System.out.print("Performing Edge Cost Function: ");
		// initialize variables
		int cost = -1;
		boolean foundBothVertices = false;
		boolean foundVertexA = false;
		boolean foundVertexB = false;
		Edge incidentEdge = null;
		
		// create iteration of collection edges (collectionE)
		Collection<Edge> edges = this.collectionE;
		Iterator<Edge> traverseEdges = edges.iterator(); 
		
		if (a == null || b == null) {
			return -1;
		}
		
		// before method starts check if the collection is valid (all the same directed edges has same weight)
		boolean differentWeight = checkDirectedEdgesWeight(edges);
		if (differentWeight) {
			throw new IllegalArgumentException("Error");
		}
		
		// traverse through the edges
		while (traverseEdges.hasNext()) {
			Edge directedEdge = traverseEdges.next();
			Vertex destination = directedEdge.getDestination();
			Vertex source = directedEdge.getSource();
			
			
			// find Vertex A
			if (destination.getLabel().equals(a.getLabel()) || source.getLabel().equals(a.getLabel())) { foundVertexA = true;}
			
			// find Vertex B
			if (destination.getLabel().equals(b.getLabel()) || source.getLabel().equals(b.getLabel())) { foundVertexB = true;}
			
			// if both are found return true
			if (foundVertexA && foundVertexB && !foundBothVertices) { 
				foundBothVertices = true;
//				System.out.println("Both vertices were found in the collection. ");
				}
			
			// if we find an edge
//			if (destination.getLabel().equals(a.getLabel()) && source.getLabel().equals(b.getLabel()) || destination.getLabel().equals(b.getLabel()) && source.getLabel().equals(a.getLabel())) {
			if (destination.getLabel().equals(b.getLabel()) && source.getLabel().equals(a.getLabel())) {
				incidentEdge = directedEdge;
				cost = directedEdge.getWeight();
//				System.out.print("We've found a directed edge! ");
			}
			
		}
		
		if (!foundBothVertices) {
			throw new IllegalArgumentException("Error");
		}
		
		if (incidentEdge != null) {
			System.out.println(incidentEdge.toStringWithoutWeight()+" cost: " + cost);
		}
		else
			System.out.println("No edge found from " + a.getLabel() + " to " +b.getLabel());
		return cost;

	}
	
	//**--------------------------------------------------------------------------------------------**
	// PART 2 - Finding Shortest Path
	//**--------------------------------------------------------------------------------------------**
	
	/*
	 * Part 2
	 * Compute shortest paths. 
	 * method shortestPath returns the lowest-cost path from its first argument to its second argument.
	 * Returns a Path object as follows: 
	 * 		• If there is no path, return null. 
	 * 		• If the start and end vertex are equal, return a path containing one vertex and a cost of 0. 
	 * 		• Otherwise, path contains at least 2 vertices– start and end vertices and any other along the lowest-cost path. 
	 * The vertices should be in the order they appear on the path. 
	 * 
	 * Dijkstra’s algorithm is used (see description inside of Dijkstra's Algorithm)
	 * 
	 *  Additional implementation notes: 
	 *  	• The program FindPaths.java: 
	 *  		reads two data files and creates a representation of the graph.
	 *  		It then prints out the graph’s vertices and edges, 
	 *  		which can be helpful for debugging to help ensure that the graph has been read and stored properly. 
	 *  		Once the graph has been built, the program loops repeatedly 
	 *  		and allows the user to ask shortest-path questions by entering two vertex names. 
	 *  		The part you need to add is to take these vertex names, call shortestPath, and print out the result. 
	 *  		
	 *  Your output should be as follows: 
	 *  	• If the start and end vertices are X and Y, first print a line Shortest path from X to Y: 
	 *  	• If there is no path from the start to end vertex, print exactly one more line does not exist 
	 *  	• Else print exactly two more lines. 
	 *  
	 *  	  On the first additional line, print the path with vertices separated by spaces. 
	 *  	  Example, you might print X Foo Bar Baz Y 
	 *  	  On the second additional line, print the cost of the path (i.e., just a single number)
	 *        The FindPaths code expects two input files in a particular format. 
	 *        The names of the files are passed as command-line arguments. 
	 *        
	 *        The files vertex.txt and edge.txt 
	 *        have the format to serve as one (small) data set where the vertices are 3-letter airport codes. 
	 *        Here is the file format
	 *        	• The file of vertices (the first argument to the program) has one line per vertex and each line contains a string with the name of a vertex. 
	 *        	• The file of edges (the second argument to the program) has three lines per directed edge (so lines 1-3 describe the first edge, lines 4-6 describe the second edge, etc.) 
	 *        		The first line gives the source vertex. The second line gives the destination vertex. 
	 *        		The third line is a string of digits that give the weight of the edge 
	 *        		(this line should be converted to a number to be stored in the graph). 
	 *        		Note data files represent directed graphs, 
	 *        		so if there is an edge from A to B there may or may not be an edge from B to A. 
	 *        		Moreover, if there is an edge from A to B and an edge from B to A, 
	 *       		the edges may or may not have the same weight.
	 */
	
	public Path shortestPath(Vertex a, Vertex b) {
		
		// infinity is represented as the maximum value of integer type
		int infinity = Integer.MAX_VALUE;
		
		/*
		 * Dijkstra’s algorithm
		 * 1. The distance of vertex 'v' from vertex 's' is the length of the shorted path between 'v' and 's'
		 * 2. Computes distances of all vertices from given start vertex 's'
		 * 3. Assumptions: graph is connected, edge weights are nonnegative
		 * 4. Grows a "cloud" of vertices beginning with 's' and covering all vertices
		 * 5. Store each vertex 'v' a label d(v) representing the distance of 'v' to 'v's in subgraph consisting of the cloud and its adjacent vertices
		 * 6. Each step:
		 * 		a. we add to the cloud the vertex 'u' outside the cloud with the smallest distance label, d(u)
		 * 		b. we update the labels of the vertices adjacent to 'u'
		 */
		
//		If the start and end vertex are equal, return a path containing one vertex and a cost of 0.
		
		Path shortest;
		if (a.toString().equals(b.toString())) {
			List<Vertex> equal = new ArrayList();
			equal.add(a);
			shortest = new Path(equal, 0);
		}
		else {
			shortest = dijkstra(a,b);
		}
		
		/**
		 * Returns the shortest path from a to b in the graph, or null if there is
		 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
		 * algorithm.
		 * @param a
		 *            the starting vertex
		 * @param b
		 *            the destination vertex
		 * @return a Path where the vertices indicate the path from a to b in order
		 *         and contains a (first) and b (last) and the cost is the cost of
		 *         the path. Returns null if b is not reachable from a.
		 * @throws IllegalArgumentException
		 *             if a or b does not exist.
		 */
		
		if (shortest == null) {
			System.out.println("Error: There is no path between the vertices you entered.");
			return null;
		}
		//print shortest
		
		String output = "";
		Iterator traverseShortestPath = shortest.vertices.iterator();
		while (traverseShortestPath.hasNext()) {
			Vertex v = (Vertex)traverseShortestPath.next();
			output += v.toString() + " ";
		}
		System.out.println("\nShortest Path from "+a.getLabel()+" to "+b.getLabel()+": " + output + "\nPath cost: " + shortest.cost);
		
		return shortest;

	}
	
	//------------------------------------------------------------------------------------------------
	// Private Methods
	//------------------------------------------------------------------------------------------------

	/*
	 * Helper Method
	 * Algorithm
	 * 
	 */
	
	private Path dijkstra (Vertex a, Vertex b) {
		
		//----------------------------------------------------------------------------------------------------
		/*
		 * First half
		 * Strategy
		 * We will create first collection of paths 
		 * Then we will store paths from a to b and return this final collection
		 * We will compare the cost of each path and determine the shortest in the shortestPath method
		 * 
		 * In conclusion: once we reached Vertex B we've found the shortest path given the current situation
		 * This is true since the Dijkstra's algorithm will always move to the next (shortest) spot
		 * 
		 * DIJKSTRA ALGORITHM
		 * IN: weighted connected graph G and vertex s
		 * OUT: shortest path from 's' to 'v'
		 */
		//----------------------------------------------------------------------------------------------------
		
		
		int infinity = Integer.MAX_VALUE;
		
		// initialize a list of vertices that we will build
		List<Vertex> vertices = new ArrayList();
		// initialie a path cost on the vertices
		int pathCost = 0;
		
		// graph G
		MyGraph G = new MyGraph(this.collectionV, this.collectionE);
		Vertex A = a; Vertex B = b;
//		int n = 0;
		// all paths
		
		//IF NOT COVERED PERFORM ALGORITHM
		if (!covered) {
			System.out.println("\nBeginning Dijkstra's algorithm...");
			
			//for each vertex in G do
			Collection<Vertex> Gvertices = this.collectionV;
			Iterator traverseGV = Gvertices.iterator();
			while (traverseGV.hasNext()) {
				Vertex u = (Vertex)traverseGV.next();
				n++; // count the amount of vertices in graph
				
				// distance is begins at infinity
				u.setDistance(infinity);
				// set predecessor to null
				u.setPredecessor(null);
				// set marked to false
				u.marked(false);
			}
			System.out.println("All vertices have had their distances, predecessors, and marks initialized");
			
			// set distance for A to 0
//			Vertex A = a; Vertex B = b;
			A.setDistance(0);
			
			// begin marking all vertices and relaxing edges
//			System.out.println("\nPerforming Algorithm on Graph G");
			for (int i=0; i<n; i++) {
				// set minimum to infinity
				int min = infinity;
				
				// for every vertex in G do
				Iterator verticesInG = this.collectionV.iterator();
				Vertex U = null;
				while (verticesInG.hasNext()) {
					// if the vertex is not marked and it's distance is less than min (at first A will be min)
					Vertex currV = (Vertex)verticesInG.next();
					if (currV.isMarked() == false && currV.getDistance() < min) {
						min = currV.getDistance();
						U = currV;
					}
				}			
				// relax all edges incident on vertex U
				if (U != null) { 
					U.marked(true);
					System.out.println("\n"+U.getLabel() + " with distance " + min + " is the start/min. This vertex is now marked");
				
					// ----------------------------------------------------------------------------------------------------
	//				System.out.println("\nNow relaxing edges...");
					// relax all edges incident on Vertex U 
					Iterator edgesInG = this.collectionE.iterator();
					while (edgesInG.hasNext()) {
						Edge currE = (Edge)edgesInG.next();
						
						// check if incident before relaxing
						boolean incident = currE.getDestination().getLabel().equals(U.getLabel()) || currE.getSource().getLabel().equals(U.getLabel());
						if (incident) {
							// if U distance + weight (length) of edge is less than vertex distance then
								// if U is destination
							if ((U.getDistance() + currE.getWeight()) < currE.getSource().getDistance()) {
	//							int updatedDistance = U.getDistance() + currE.getWeight();
	//							currE.getSource().setDistance(updatedDistance);
								// successor only if U is the destination (we will ignore this)
								currE.getSource().addDescendant(U);
								System.out.print("(" +currE.getSource().toString() +"->"+U.toString()+"): ");
								System.out.print(U.getLabel()+" is now a descendant of " + currE.getSource().getLabel()+". ");
								System.out.println("Since " + currE.getSource().getLabel() + " is a source than we don't update it's distance.");
							}
							// if U is source
							if ((U.getDistance() + currE.getWeight()) < currE.getDestination().getDistance()) {
								int updatedDistance = U.getDistance() + currE.getWeight(); 
								currE.getDestination().setDistance(updatedDistance); // update distance
								// update predecessor only if U is the source
								currE.getDestination().setPredecessor(U);
								// also add to descendant
								U.addDescendant(currE.getDestination());
								System.out.print("(" +U.toString() +"->"+currE.getDestination().toString()+"): ");
								System.out.print("Updating the distance of " + currE.getDestination().getLabel() + " to: " + updatedDistance +" and changing its predecessor to ("+U.toString()+"). ");
								System.out.println(currE.getDestination().getLabel()+" is now a descendant of " + U.getLabel());
							}
						}
					}
				}
				else {
					break;
				}
			} System.out.println("\nAll edges in the graph have been relaxed. Our cloud has covered the entire graph G!");
			covered = true;
		}
		//------------------------------------------------------------------------------------------------
		/*
		 * Second half of customized algorithms.
		 * Parts:
		 * a. start with a vertex v (begin with vertex A)
		 * b. find all vertices that are adjacent (and destinations) of v
		 * c. find all the descendants of v
		 * d. if v itself or it's descendants are equal to vertex B then continue, else there is no path
		 * e. find which vertex among them all has the smallest total path cost (sum of all it's distances + costs)
		 * 		do not add the following distances to the sum
		 * 			I. vertices that do not have B has their descendant (they won't lead to be so their distance doesn't count) 
		 * 			II. 
		 * f. at the end of this iteration choose the one with the smallest path cost and assign it to v
		 * g. continue until our v becomes vertex B
		 */
		//------------------------------------------------------------------------------------------------
		
		System.out.println("\nMeasuring shortest path from "+ A.getLabel()+ " to " + B.getLabel());
		Iterator vertexDesc = B.getDescendants().iterator();
		while (vertexDesc.hasNext()) {
			Vertex desc = (Vertex)vertexDesc.next();
//			System.out.print(desc.getLabel() + " ");
		}
		
		// check all potential paths
		List<Vertex> verticesAtoB = new ArrayList();
		Vertex minVertex = null;
		int minVertexCost = 0;
		// begin with A then traverse
		Vertex v = A;
		verticesAtoB.add(v);
		
		for (int i=0; i<n; i++) {
			// find all vertices adjacent with v
			Collection<Vertex> adjacent = this.getDestinationVertices(v);
			Iterator traverseAdj = adjacent.iterator();

			Vertex goNext = null;
			
			int minPathDistance = infinity;
			
			// for all vertices adjacent with v
			while (traverseAdj.hasNext()) {
				Vertex u = (Vertex)traverseAdj.next(); 
				// check if B is a descendant of v (there is a path leading to B)
				Collection<Vertex> descendants = u.getDescendants();
				Iterator traverseDescendants = descendants.iterator();
				
				boolean pathToBExist = false;
				int prevEdge = edgeCostNoOutput(v, u);
				int totalDistance = u.getDistance() + prevEdge;
				
				while (traverseDescendants.hasNext()) {
					Vertex desc = (Vertex)traverseDescendants.next();
					boolean equalsB = false; boolean alsoLeadsToB = false;
					if (desc.toString().equals(B.toString())) {
						pathToBExist = true;
						equalsB = true;
					}
					// calculate total distance at the same time (distance of destination + edge cost of destination and it's source
					
					// IMPORTANT
					// Only add this descendant distance to sum if it leads to B 
					// i.e (has B as a descendant or is B itself)
					
					// create iterator for the descendants of desc
					Iterator traverseD2 = desc.getDescendants().iterator();
					while (traverseD2.hasNext()) {
						Vertex comp = (Vertex)traverseD2.next();
						if (comp.getLabel().equals(B.getLabel())) {
							alsoLeadsToB = true;
						}
					}
					if (equalsB || alsoLeadsToB) {
						int descendantDistance = desc.getDistance();
						int Ecost = edgeCostNoOutput(desc.getPredecessor(), desc);
						if (Ecost == -1) { totalDistance += descendantDistance; } // if there is no edge (starting node)
						else { totalDistance += descendantDistance + Ecost; }
					}
				}
				// if the adjacent vertex or one of its descends is equal to B
				if (u.toString().equals(B.toString()) || pathToBExist) {
					// the next goNext (branch) will begin with the adjacent vertex
					goNext = u;
					// if the total distance is less than the previous minimum distance then update vertex with minimum distance
					if (totalDistance < minPathDistance) {
						minPathDistance = totalDistance;
						minVertex = goNext;
					}
				}
				
			}
			// if we've found a goNext Vertex that leads to B then add this to collection and update
			if ( minVertex != null ) {
				System.out.print(minVertex.getLabel() + " (d: "+minVertex.getDistance()+") is the next vertex in our Path. ");
				verticesAtoB.add(minVertex);
				v = minVertex;
				if (v.getLabel().equals(B.getLabel()))
					break;
			}
			else {
//				System.out.println("There is no path from " + A.getLabel() + " to " + B.getLabel());
				break;
			}
		}
		
		// the cost should be the sum of the weight of all edges leading from A to B of minimum length
		
		if (minVertex != null) {
			System.out.println("Shortest path found.");
			int returnCost = 0;
			Iterator AtoB = verticesAtoB.iterator();
			Vertex u = (Vertex)AtoB.next();
			while (AtoB.hasNext()) {
				Vertex U = (Vertex)AtoB.next();
				returnCost += edgeCostNoOutput(u,U);
				u = U;
			}
			return new Path (verticesAtoB, returnCost);
		}
		
		return null;
	}
	
	
	
	/*
	 * Helper Method
	 *  Do throw an exception if:
	 *  A. the collection of edges has the same directed edge more than once with a different weight. 
	 *  Note: in a directed graph an edge from A to B is not the same as an edge from B to A. 
	 */
	
	private boolean checkDirectedEdgesWeight(Collection<Edge> edges) {
		boolean differentWeight = false;
		
		// create iterator to traverse through edges
		Iterator<Edge> traverseE = edges.iterator(); 
		
		// begin traversal
		while (traverseE.hasNext()) {
			Edge current = traverseE.next();
			
			// create a copy of the collection and reinitialize every time so we begin at start of collection
			Collection<Edge> copy = edges;
			Iterator<Edge> traverseECopy = copy.iterator();
			
			// compare this with every edge in list
			while (traverseECopy.hasNext()) {
				Edge comparison = traverseECopy.next();
				
				// if their source and destination are the same
				if (current.getSource().getLabel().equals(comparison.getSource().getLabel()) && current.getDestination().getLabel().equals(comparison.getDestination().getLabel())) {
					if (current.getWeight() != comparison.getWeight()) {
						differentWeight = true;
						return differentWeight; // return true and end method
					}
				}
			}
		}
		return differentWeight;
	}
	
	public int edgeCostNoOutput(Vertex a, Vertex b) throws IllegalArgumentException {
		/**
		 * Tests whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph. 
		 * Assumes that we do not have negative cost edges in the graph.
		 * @param a
		 *            one vertex
		 * @param b
		 *            another vertex
		 * @return cost of edge if there is a directed edge from a to b in the
		 *         graph, return -1 otherwise.
		 * @throws IllegalArgumentException
		 *             if a or b do not exist.
		 */
		
		/*
		 * Performs edgecost function but does not give a print statement
		 */
		
//		System.out.print("Performing Edge Cost Function: ");
		// initialize variables
		int cost = -1;
		boolean foundBothVertices = false;
		boolean foundVertexA = false;
		boolean foundVertexB = false;
		Edge incidentEdge = null;
		
		// create iteration of collection edges (collectionE)
		Collection<Edge> edges = this.collectionE;
		Iterator<Edge> traverseEdges = edges.iterator(); 
		
		if (a == null || b == null) {
			return -1;
		}
		
		// before method starts check if the collection is valid (all the same directed edges has same weight)
		boolean differentWeight = checkDirectedEdgesWeight(edges);
		if (differentWeight) {
			throw new IllegalArgumentException("Error");
		}
		
		// traverse through the edges
		while (traverseEdges.hasNext()) {
			Edge directedEdge = traverseEdges.next();
			Vertex destination = directedEdge.getDestination();
			Vertex source = directedEdge.getSource();
			
			
			// find Vertex A
			if (destination.getLabel().equals(a.getLabel()) || source.getLabel().equals(a.getLabel())) { foundVertexA = true;}
			
			// find Vertex B
			if (destination.getLabel().equals(b.getLabel()) || source.getLabel().equals(b.getLabel())) { foundVertexB = true;}
			
			// if both are found return true
			if (foundVertexA && foundVertexB && !foundBothVertices) { 
				foundBothVertices = true;
//				System.out.println("Both vertices were found in the collection. ");
				}
			
			// if we find an edge
//			if (destination.getLabel().equals(a.getLabel()) && source.getLabel().equals(b.getLabel()) || destination.getLabel().equals(b.getLabel()) && source.getLabel().equals(a.getLabel())) {
			if (destination.getLabel().equals(b.getLabel()) && source.getLabel().equals(a.getLabel())) {
				incidentEdge = directedEdge;
				cost = directedEdge.getWeight();
//				System.out.print("We've found a directed edge! ");
			}
			
		}
		
		if (!foundBothVertices) {
			throw new IllegalArgumentException("Error");
		}
		
		if (incidentEdge != null) {
//			System.out.println(incidentEdge.toStringWithoutWeight()+" cost: " + cost);
		}
		else {
//			System.out.println("No edge found from " + a.getLabel() + " to " +b.getLabel());
		}
		return cost;
	}
	
	private Collection<Vertex> getDestinationVertices(Vertex v) throws IllegalArgumentException {
		/**
		 * Same as adjacentVertices Function but only returns the vertices that are destinations 
		 * Avoids sources 
		 * Used in dijkstra to find shortest path
		 * When finding incident vertices, we don't want to consider vertices pointing to our vertex
		 * */
		
//		System.out.print("Performing get Destination Vertices Function: ");
		
		Collection<Vertex> destinationVertices = new ArrayList();
		int countAdV = 0;
		
		
		// create iteration of collection edges (collectionE) and vertices (collectionV)
		Collection<Edge> edges = this.collectionE;
		Iterator<Edge> traverseEdges = edges.iterator(); 
		
		Collection<Vertex> vertex = this.collectionV;
		Iterator<Vertex> traverseVertices = vertex.iterator();
		
		// boolean to find v
		boolean foundV = false;
		
		// before method starts check if the collection is valid (all the same directed edges has same weight)
		boolean differentWeight = checkDirectedEdgesWeight(edges);
		if (differentWeight) {
			throw new IllegalArgumentException("Error");
		}
		
		while (traverseVertices.hasNext()) {
			Vertex currentV = traverseVertices.next();
			if (currentV.getLabel().equals(v.getLabel())) {
				foundV = true;
//				System.out.println(v.getLabel() + " exist in the collection.");
				break;
			}
		}
		
		if (!foundV) {
			throw new IllegalArgumentException("Error");
		}
		
		// traverse edges
		while (traverseEdges.hasNext()) {
			Edge incidentEdge = traverseEdges.next();
			Vertex destination = incidentEdge.getDestination();
			Vertex source = incidentEdge.getSource();
			// create iteration of adjacentVertices 
			Iterator<Vertex> traverseAV = destinationVertices.iterator(); 
			
//			// if destination vertex equals 'v' then add source to collection
//			if (destination.getLabel().equals(v.getLabel()) ) {
//				// check if the adjacent vertex with label already exist
//				boolean duplicateFound = false;
//				while (traverseAV.hasNext()) {
//					if (source.getLabel().equals(traverseAV.next().getLabel())) {
//						duplicateFound = true; // if exist then found is true and we will not add the source
//					}
//				}
//				if (!duplicateFound) {
//					adjacentVertices.add(source);
//					countAdV++;
//				}
//			}
			
			// if source vertex equals 'v' then add destination to collection
			if (source.getLabel().equals(v.getLabel()) ) {
				// check if the adjacent vertex with label already exist
				boolean duplicateFound = false;
				while (traverseAV.hasNext()) {
					if (destination.getLabel().equals(traverseAV.next().getLabel())) {
						duplicateFound = true; // if exist then found is true and we will not add the source
					}
				}
				if (!duplicateFound) {
					destinationVertices.add(destination);
					countAdV++;
				}
			}
		}	
		
		// final traversal to print out the adjacentVertices
		if (countAdV > 0 ) {
			String displayVertices = "";
			Iterator traverseAdjacent = destinationVertices.iterator();
			while (traverseAdjacent.hasNext()) {
				// need to an initialize the object as a vertex before the getLabel method is accessible
				Vertex temp = new Vertex(traverseAdjacent.next().toString());
				displayVertices += temp.getLabel();
				if (traverseAdjacent.hasNext())
					displayVertices += ", ";
			}
			System.out.println("All destination vertices of " + v.getLabel() + " are: " + displayVertices);
		}
		else
			System.out.println("No vertices are destinations of " + v.getLabel());

		
		return destinationVertices;
	}
	

	
}



