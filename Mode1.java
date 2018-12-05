package application;

import application.test10.dragNode;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Mode1 { 
	   private int[][] adjacency;
	   private int edges;
	   private int Nvertices;
	   private static ColCircle[] vertices;
	   private int[] colorSol;
	   
	   public Mode1(int[][] matrix,int vertices,int Edges) {
		   adjacency = matrix;
		   edges= Edges;
		   Nvertices = vertices;
	   }
	   
	   public void start(Stage primaryStage){
		   test10 test10 = new test10(adjacency,Nvertices,edges);
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
			
			//redo the last working testing becouse ifnot the color array will be filled with 0´s
			test = Coloring.graphColoring(adjacency, x);
			
			colorSol = Coloring.getColor();
			
			test10.setSol(colorSol);
			
			
/*			dragNode[] arr = test10.getdragNode();
			Label  labelCoo  = new Label();
			labelCoo.textProperty().bind(
	                Bindings.format(
	                    "%.2f",
	                    arr[1].getCenterX()
	                )
	        );*/
			System.out.println(x);
			test10.setCN(x);
			test10.start(primaryStage);
	   }
}
