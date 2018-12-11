package application;

import java.util.*;

/**
* A class to represent a Graph.
* The Graph is an array of Vertex objects.
* @author Celia Busquets Quilis.
* @version 0.0, 04-12-2018.
*/

public class Graph {
	
	private int[][] adjMatrix;
	private Vertex[] vertices;


	public Graph(int n, int[][] connections){
	  this.adjMatrix = new int[n][n];
	  this.adjMatrix = connections;
	  this.vertices = new Vertex[n];
	}

	public void createVertices(){
	  for (int i = 0; i < vertices.length; i++){
	    vertices[i] = vertexGenerator(i);
	  }

	}

	/**
	* A method that creates a default vertex without color.
	* @param name is the index/"name" we assign to the vertex.
	* @param connections is the adjacency matrix of the graph.
	*/
	public Vertex vertexGenerator(int name){
	  Vertex x = new Vertex(name);
	  return x;
	}

	public void printGraph(){
	  for(int i = 0; i < vertices.length; i++){
	    System.out.println("Vertex name: " + vertices[i].getIndex());
	    System.out.println("Vertex color: " +  vertices[i].getColor());
	  }
	}

	public Vertex getVertex(int index){
	  return this.vertices[index];
	}

	/**
	* Method to reset all colors of the graph.
	*/

	public void resetAllColors(){
	  for (int i = 0; i < vertices.length; i++){
	    vertices[i].reset();
	  }
	}

	public int[][] getAdjMatrix(){
	  return adjMatrix;
	}

	/**
	* Method to reset the color of a specific vertex.
	* Used by the back button in Mode 3.
	* @param vertexNumber is the index/"name" of the current vertex.
	*/
	public void resetVertexColor(int vertexNumber){
	  vertices[vertexNumber].reset();
	}
}
