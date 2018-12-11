package application;

import java.util.*;

/**
* A class to create individual objects for every vertex in a graph.
* @author Celia Busquets Quilis.
* @version 1.0, 04-12-2018.
*/

public class Vertex {
	
	private int index;
	private int color;


	/** A constructor for a default vertex.
	* @param theName is the index/"name" of the vertex
	*
	*/

	public Vertex(int theName){
	  this.index = theName;
	  this.color = 0;
	}

	/** A method that sets the color to the Vertex once
	* it is clicked in the game.
	* @param theColor is the color passed by the click.
	*
	*/

	public void setColor(int theColor){
	  if (theColor != 0){
	    this.color = theColor;
	  }
	}

	/**
	* Access the color of the vertex.
	* @return the vertex's color.
	*/
	public int getColor(){
	  return this.color;
	}

	/**
	* Access the name of the vertex.
	* @return the vertex's index/"name".
	*/
	public int getIndex(){
	  return this.index;
	}

	/**
	* Delet the color of the vertices.
	* (Maybe more useful in the Graph class or in the Action of the undo button).
	*/
	public void reset(){
	  this.color = 0;
	}
}
