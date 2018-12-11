package application;

import javafx.stage.Stage;

public class Mode2 {
	   private int[][] adjacency;
	   private int edges;
	   private int Nvertices;
	   private static ColCircle[] vertices;
	   private int[] colorSol;
	   
	   public Mode2(int[][] matrix,int vertices,int Edges) {
		   adjacency = matrix;
		   edges= Edges;
		   Nvertices = vertices;
	   }
	   
	   public void start(Stage primaryStage){
		   
		   test10 test10 = new test10(adjacency,Nvertices,edges,2);
		   vertices = test10.getColCircle();
		   Greedy Upperbound = new Greedy(Nvertices);
			
		   //Upperbound is a greedy algorithme to find an upperbound
		   int upper = Upperbound.Upper(adjacency);
		   Backtracking Coloring = new Backtracking(Nvertices);

			//initial number of colours we are trying to colour the graph
			int x = upper; 
			
			boolean test = true;
			while (test && x >= 2) {
				x--;
				//Calling the graphColoring function
				//until we can not reduce the Chromatic number any more
				test = Coloring.graphColoring(adjacency, x);
			}
			//add 1 to x, because we can not colour the graph with x colours
			x++;
			//x is the chromatic number
			
			//redo the last working testing because if not the color array will be filled with 0´s
			test = Coloring.graphColoring(adjacency, x);
			
			colorSol = Coloring.getColor();
			
			test10.setSol(colorSol);
			
			System.out.println(upper);
			test10.setCN(upper);
			test10.start(primaryStage);
	   }
}
