package application;

import javafx.stage.Stage;

public class Mode2 {
	   private int[][] adjacency;
	   private int edges;
	   private int Nvertices;
	   private int[] colorSol;
	   
	   public Mode2(int[][] matrix,int vertices,int Edges) {
		   adjacency = matrix;
		   edges= Edges;
		   Nvertices = vertices;
	   }
	   
	   public void start(Stage primaryStage){
		   
		   Main main = new Main(adjacency,Nvertices,edges,2);
		   Greedy upperBound = new Greedy(Nvertices);
			
		   //Upperbound is a greedy algorithme to find an upperbound
		   int upper = upperBound.Upper(adjacency);
		   Backtracking coloring = new Backtracking(Nvertices);

			//initial number of colours we are trying to colour the graph
			int x = upper; 
			
			boolean test = true;
			while (test && x >= 2) {
				x--;
				//Calling the graphColoring function
				//until we can not reduce the Chromatic number any more
				test = coloring.graphColoring(adjacency, x);
			}
			//add 1 to x, because we can not colour the graph with x colours
			x++;
			//x is the chromatic number
			
			//redo the last working testing because if not the color array will be filled with 0´s
			test = coloring.graphColoring(adjacency, x);
			
			colorSol = coloring.getColor();
			
			main.setSol(colorSol);
			
			System.out.println("The upperbound number is: " + upper);
			System.out.println("The chromatic number is: " + x);
			main.setCN(upper);
			main.start(primaryStage);
	   }
}
