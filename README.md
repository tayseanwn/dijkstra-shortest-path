# dijkstra-shortest-path
Dijkstra's Shortest Path Algorithm

<h1>Language</h1>
Java

<h2>Overview</h2>

Read a graph representation and use it to implement Dijkstra’s algorithm for finding shortest paths

<h2>Implementation</h2>

<h3>PART 1 - Building A Grap</h3>

 * Implement a graph representation.
 * The operations to the MyGraph class follow the implementation of the Graph interface. 
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
 
<h3>PART 2 - Finding Shortest Path</h3>
	
* Part 2
* Compute shortest paths. 
* method shortestPath returns the lowest-cost path from its first argument to its second argument.
* Returns a Path object as follows: 
* 		• If there is no path, return null. 
* 		• If the start and end vertex are equal, return a path containing one vertex and a cost of 0. 
* 		• Otherwise, path contains at least 2 vertices– start and end vertices and any other along the lowest-cost path. 
* The vertices are in the order they appear on the path. 
* 
* Dijkstra’s algorithm is used (see description inside of Dijkstra's Algorithm)
*  		
*  Output:
*  	• If the start and end vertices are X and Y, first print a line Shortest path from X to Y: 
*  	• If there is no path from the start to end vertex, print exactly one more line does not exist 
*  	• Else print exactly two more lines. 
*  
*  	  On the first additional line, print the path with vertices separated by spaces. The FindPaths code expects two input files in a particular format. 
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
