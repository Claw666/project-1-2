package application;

import java.util.*;
import java.io.*;

/**
* A class containing Breath First Search (BFS) algorithms.
* @author Celia Busquets Quilis.
* @version 1.2, 05-12-2018.
*/

public class BFS {

	private Graph graph;
	private int[] color;
	private Vertex[] nodeOrder; // For mode 3, which order the algorithm visits the nodes.
	private List<Integer> waitingList;
	private boolean[] visited;

	// private int vertices; // Not needed anymore because the amount of verticecs is visited.length already.
	private int lowerbound;
	private int upperbound;


	/**
	* Default constructor that stores the amount of vertices.
	* @param vertices of the graph.
	*/
	public BFS(int vertices){
	  nodeOrder = new Vertex[vertices]; // We create an array to store the order of the bfs.
	  waitingList = new ArrayList<>(); // We create another array to store the nodes we visit.
	  color = new int[vertices];
	  visited = new boolean[vertices]; // An array to check if the nodes have been visited.
	}


	/**
	* Method to print a BFS traversal
	* @param theGraph is the given graph we need to search.
	*/
	public void BFSExact(Graph theGraph){
	  this.graph = theGraph;
	  this.coloringOrder();
	  // this.computeChromNum();
	  //this.printSolutions();
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
	    waitingList.remove(0);
	    nodeIndex++;
	  }
	}

	public void printSolutions(){
	  System.out.print("Node order: ");
	  for (int i = 0; i < nodeOrder.length; i++){
	    System.out.print(nodeOrder[i].getIndex() + " ");
	  }
	  System.out.println();

	}

	public void addToWaitingList(int vertexIndex){
	  int[][] adjacencyMatrix = this.graph.getAdjMatrix();
	  for (int j = 0; j < adjacencyMatrix[0].length; j++){
	    if (adjacencyMatrix[vertexIndex][j] == 1 && !visited[j]){
	      this.waitingList.add(j);
	      visited[j] = true;
	    }
	  }
	}
	
	public int[] getnodeOrder() {
		int[] result = new int[nodeOrder.length];
		for (int i = 0; i < nodeOrder.length; i++){
		    result[i] = nodeOrder[i].getIndex();
		}
		return result;
	}


	/**
	* A method that finds the chromatic number.
	* @param lowerbound is the lower limit of colors needed.
	* @param upperbound is the upper limit of colors needed.
	*/
	// public void computeChromNum(int aLowerbound, int anUpperbound){
	//   this.upperbound = anUpperbound;
	//   this.lowerbound = aLowerbound;
	// }
}
