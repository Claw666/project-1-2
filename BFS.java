package application;

import java.util.*;

/**
* A class containing Breath First Search (BFS) algorithms.
*
*
*/

public class BFS{

private Graph graph;
private Vertex[] nodeOrder; // For mode 3, which order the algorithm visits the nodes.
private List<Integer> waitingList; // A list containing the nodes that are going to be visited next (according to the node being colored currently).
private boolean[] visited;

/**
* Default constructor that stores the amount of vertices.
* @param vertices of the graph.
*/
public BFS(int vertices){
  nodeOrder = new Vertex[vertices]; // We create an array to store the order of the bfs.
  waitingList = new ArrayList<>(); // We create another array to store the nodes we visit.
  visited = new boolean[vertices]; // An array to check if the nodes have been visited.
}


/**
* Method to print a BFS traversal
* @param theGraph is the given graph we need to search.
*/
public void BFSExact(Graph theGraph){
  this.graph = theGraph;
  this.coloringOrder();
}

/**
* Method that finds the fastest route through the graph.
* Provides an order of nodes, used in Mode 3.
*/

public void coloringOrder(){
  int nodeIndex = 0;
  waitingList.add(0);
  visited[0] = true;
  while (!waitingList.isEmpty()){
    nodeOrder[nodeIndex] = this.graph.getVertex(waitingList.get(0));
    addToWaitingList(waitingList.get(0));
    visited[waitingList.get(0)] = true;
    waitingList.remove(0);
    nodeIndex++;
  }
  findIsolatedNodes(nodeIndex);

}

public void printSolutions(){
  System.out.print("Node order: ");
  for (int i = 0; i < this.nodeOrder.length; i++){
    System.out.print(nodeOrder[i].getIndex() + " ");
  }
  System.out.println(" ");

}

/**
* A method to check the edges of the node being colored.
* @param vertexIndex is the index/"name" of the vertex, helps to locate it on the Adjacency Matrix.
*/

public void addToWaitingList(int vertexIndex){
  int[][] adjacencyMatrix = this.graph.getAdjMatrix();
  for (int j = 0; j < adjacencyMatrix[0].length; j++){
    if (adjacencyMatrix[vertexIndex][j] == 1 && !visited[j]){
      this.waitingList.add(j);
      visited[j] = true;
    }
  }
}

/**
* A method that adds visits the isolated nodes of the graph.
* @param nodeIndex is the position in the array nodeOrder in which we store the first isolated node.
*/

public void findIsolatedNodes(int nodeIndex){
  int newNodeIndex = nodeIndex;
  for (int i = 0; i < visited.length; i++){
    if (visited[i] == false){
      nodeOrder[newNodeIndex] = this.graph.getVertex(i);
      newNodeIndex++;
    }
  }

}

/**
* A method that exports the node order to the interface.
*
*/
public int[] getnodeOrder() {
		int[] result = new int[nodeOrder.length];
		for (int i = 0; i < nodeOrder.length; i++){
		    result[i] = nodeOrder[i].getIndex();
		}
		return result;
}


}
