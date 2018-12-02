package application;

import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;
import javafx.beans.property.*;
import javafx.stage.Stage; 
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


class ColCircle // will contain the x,y and rad values for the circles
			{
			int x;
			int y;
			int rad = 20;
			}
class ColEdge
			{
			int u;
			int v;
			}
         
public class test10 extends Application { 
   private int[][] adjacency;
   private int edges;
   private int Nvertices;
   private static ColCircle[] vertices;
	
   //constructor for a file
   public test10(String filename){
   	   ReadGraphClass G = new ReadGraphClass(filename);
	   adjacency = G.ReadFile();
	   edges = G.getEdges();
	   Nvertices = G.getVertices();
   }
   
   //constructor for the random generator
   public test10(int[][] matrix,int vertices,int Edges) {
	   adjacency = matrix;
	   edges= Edges;
	   Nvertices = vertices;
   }
   
   public void start(Stage stage) { 
   	   //boolean matrix to check which edges have been used or not
   	   boolean[][] edgesstate = new boolean[Nvertices][Nvertices];
   	   //some weird magic
   	   ColCircle vertices[] = null;
   	   
   	   vertices = new ColCircle[Nvertices];
   	   
       int height = (int) stage.getWidth();
       int width = (int) stage.getHeight();
   	   
   	   double angle = 2*Math.PI/Nvertices;
   	   // giving x and y values to the circles
   	   for(int i=0; i<Nvertices;i++){
   	   	   vertices[i] = new ColCircle();
   	   	   vertices[i].x = (int)(width/3.0*Math.cos(i*angle)+height/2.0);
   	   	   vertices[i].y = (int)(width/3.0*Math.sin(i*angle)+width/2.0);
   	   }
   	   //place counter
   	   int p = 0;
   	     	   
      //Creating aroot group
      Group root = new Group();
      
      //make arrays to store the DoubleProperty values      
      DoubleProperty[] startX = new SimpleDoubleProperty[Nvertices];
      DoubleProperty[] startY = new SimpleDoubleProperty[Nvertices];
      DoubleProperty[] endX = new SimpleDoubleProperty[Nvertices];
      DoubleProperty[] endY = new SimpleDoubleProperty[Nvertices];
      
      //get the x,y values of the vertices and put them in the arrays
      for(int i = 0; i<Nvertices;i++){
      	startX[i] = new SimpleDoubleProperty(vertices[i].x);
      	startY[i] = new SimpleDoubleProperty(vertices[i].y);
      	endX[i] = new SimpleDoubleProperty(vertices[i].x);
      	endY[i] = new SimpleDoubleProperty(vertices[i].y);
      }
      
      //make an array to put the dragNodes in, these will be the vertices
      dragNode[] arr = new dragNode[Nvertices];
      
      //make the dragNodes
      for(int i = 0; i<Nvertices;i++){
    	  arr[i] = new dragNode(Color.LIGHTSEAGREEN,startX[i],startY[i],20);
      }
      
      //mane an array to put the Boundes Lines in
       Line[] lines = new fixedLine[edges];
       
       //make all the lines
      for(int i=0;i<Nvertices;i++){
      	for(int j =0; j<Nvertices;j++){
      		if(adjacency[i][j]==1 && edgesstate[i][j]==false){
      			lines[p] = new fixedLine(startX[i], startY[i], startX[j], startY[j]);
      			p++;
      			edgesstate[i][j] = true; //we also make the place in the edgesstate true, this means we have used this edge
      			edgesstate[j][i] = true;
      		}
      	}
      }
      
      //add the arrays to the group
      root.getChildren().addAll(arr);
      root.getChildren().addAll(lines);
      
      //Creating a scene object 
      Scene scene = new Scene(root, width, height);  
      
      //Setting title to the Stage )
      stage.setTitle("graph from matrix");
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();         
   } 
   public static ColCircle[] getColCircle() {
	   return vertices;
   }
   class fixedLine extends Line {
	      fixedLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
	      startXProperty().bind(startX);
	      startYProperty().bind(startY);
	      endXProperty().bind(endX);
	      endYProperty().bind(endY);
	      setStrokeWidth(2);
	      setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
	      setStrokeLineCap(StrokeLineCap.ROUND);
	      getStrokeDashArray().setAll(10.0, 5.0);
	      setMouseTransparent(true);
	    }
	  }

	  // a draggable dragNode displayed around a point.
	  class dragNode extends Circle { 
	      dragNode(Color color, DoubleProperty x, DoubleProperty y, double rad) {
	      super(x.get(), y.get(), rad);
	      setFill(color.deriveColor(1, 1, 1, 0.5));
	      setStroke(color);
	      setStrokeWidth(2);
	      setStrokeType(StrokeType.OUTSIDE);
	      x.bind(centerXProperty());
	      y.bind(centerYProperty());
	      enableDrag();
	    }

	    // make a node movable by dragging it around with the mouse.
	    private void enableDrag() {
	      final Delta dragDelta = new Delta();
	      //action to perform if mouse gets pressed
	      setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          // record a delta distance for the drag and drop operation.
	          dragDelta.x = getCenterX() - mouseEvent.getX();
	          dragDelta.y = getCenterY() - mouseEvent.getY();
	          getScene().setCursor(Cursor.MOVE);
	        }
	      });
	      setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          getScene().setCursor(Cursor.HAND);
	        }
	      });
	      setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          double newX = mouseEvent.getX() + dragDelta.x;
	          if (newX > 0 && newX < getScene().getWidth()) {
	            setCenterX(newX);
	          }  
	          double newY = mouseEvent.getY() + dragDelta.y;
	          if (newY > 0 && newY < getScene().getHeight()) {
	            setCenterY(newY);
	          }
	        }
	      });
	      //action if mouse hovers over a node
	      setOnMouseEntered(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          if (!mouseEvent.isPrimaryButtonDown()) {
	            getScene().setCursor(Cursor.HAND);
	          }
	        }
	      });
	      //action if mouse doesnt hover over a node
	      setOnMouseExited(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          if (!mouseEvent.isPrimaryButtonDown()) {
	            getScene().setCursor(Cursor.DEFAULT);
	          }
	        }
	      });
	    }
	    private class Delta { double x, y; }
	  }
}