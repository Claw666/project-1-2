package application;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text;

class ColLines //will contain the x and y values for the edges
			{
			int xs;
			int ys;
			int xe;
			int ye;
			}
         
public class preview{ 
   private int width = 1000;
   private int height = 1000;
   int[][] adjacency;
   int edges;
   int Nvertices;
	
   public preview(int[][] matrix,int vertices, int edges){
		adjacency = matrix;
		this.edges = edges;
		Nvertices = vertices;
   }
   public void display() { 
	   Stage stage = new Stage();
	   stage.setWidth(1000);
	   stage.setHeight(1000);
       
   	   //boolean matrix to check which edges have been used or not
   	   boolean[][] edgesstate = new boolean[Nvertices][Nvertices];
   	   
   	   //some weird magic
   	   ColLines coordinates[] = null;
   	   ColCircle vertices[] = null;
   	   coordinates = new ColLines[edges];
   	   vertices = new ColCircle[Nvertices];
   	   
   	   double angle = 2*Math.PI/Nvertices;
   	   // giving x and y values to the circles
   	   for(int i=0; i<Nvertices;i++){
   	   	   vertices[i] = new ColCircle();
   	   	   vertices[i].x = (int)(height/3.0*Math.cos(i*angle)+height/2.0);
   	   	   vertices[i].y = (int)(width/3.0*Math.sin(i*angle)+width/2.0);
   	   }
   	   //place counter
   	   int p = 0;
   	   /* in these for loops we check which vertices are adjacent
   	   and we put the coordinates of these vertices as a value for the
   	   start and end for the lines */
   	   
   	   for(int i=0; i<Nvertices;i++){
   	   	   for(int j =0; j<Nvertices;j++){
   	   	   	   if(adjacency[i][j]==1 && edgesstate[i][j]==false){
   	   	   coordinates[p] = new ColLines();
   	   	   coordinates[p].xs = vertices[j].x;
   	   	   coordinates[p].ys = vertices[j].y;
   	   	   coordinates[p].xe = vertices[i].x;
   	   	   coordinates[p].ye = vertices[i].y;
   	   	   edgesstate[i][j] = true; //we also make the place in the edgesstate true, this means we have used this edge
   	   	   edgesstate[j][i] = true;
   	   	   p++;
   	   	   	   }
   	   	   }
   	   }
   	   
      //Creating a group (? dont aks me wtf a group is)
      Group root = new Group();
      //we add the lines to the group
      for(int i=0;i<p;i++){
      	  Line line = new Line();
      	  line.setStartX(coordinates[i].xs); 
          line.setStartY(coordinates[i].ys); 
          line.setEndX(coordinates[i].xe); 
          line.setEndY(coordinates[i].ye);
      	  root.getChildren().add(line); 
      }
      //we add the vertices/nodes and text to the group
      for(int i=0;i<Nvertices;i++){
      Text text = new Text();
      text.setFont(new Font(30));
      text.setX(vertices[i].x-10); 
      text.setY(vertices[i].y+10);
      text.setText(String.valueOf(i));
      Circle circle = new Circle(vertices[i].x,vertices[i].y,vertices[i].rad); //x,y,radius
      circle.setFill(javafx.scene.paint.Color.RED);
      root.getChildren().add(circle); 
      root.getChildren().add(text);
      } 
      
      //Creating a scene object 
      Scene scene = new Scene(root, width, height);  
      
      //Setting title to the Stage )
      stage.setTitle("Preview");
      
      //Adding scene to the stage 
      stage.setScene(scene);
      
      //Displaying the contents of the stage 
      stage.show();        
   } 
}