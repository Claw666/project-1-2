package application;

/**
* A class to create individual objects for every vertex in a graph.
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
	* Delete the color of the vertices.
	*/
	public void reset(){
	  this.color = 0;
	}
}
