package application;

import java.util.Set;
import javafx.application.Application; 
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.stage.Stage; 
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

import java.util.HashSet;


import javafx.event.ActionEvent;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.util.Random;

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
	private int[] colorSol;
   private int width;
   private int height;
   private int[][] adjacency;
   private int edges;
   private static int Nvertices;
   private static ColCircle[] vertices;
   private dragNode[] arr;
   private Line[] lines; 
   private ToggleGroup tg;
   private int CN;
   private boolean finished = false;
   private ToggleButton hint;
   private Label Win;
   private  Color [] color;
   private int pressed;

/*	
   //constructor for a file
   public test10(String filename){
   	   ReadGraphClass G = new ReadGraphClass(filename);
	   adjacency = G.ReadFile();
	   edges = G.getEdges();
	   Nvertices = G.getVertices();
   }
*/
   //constructor for the random generator
   public test10(int[][] matrix,int vertices,int Edges) {
	   adjacency = matrix;
	   edges= Edges;
	   Nvertices = vertices;
   }
   
   public void start(Stage stage) { 
	   
	   VBox layout2=new VBox(10);
	   BorderPane layout=new BorderPane();
	   layout.setRight(layout2);
	   Group c=new Group();
	   
	   color=new Color[CN];
	   Random RND = new Random();
	   for(int i=0; i<CN; i++) {
		   int red = RND.nextInt(13)*20;
		   int green = RND.nextInt(13)*20;
		   int blue = RND.nextInt(13)*20;
		   Color colo = Color.rgb(red, green, blue);
		   color[i] = colo;
	   }
	   //Creating array to store colors
/*	   Color[] color=new Color[10];
	   color[0]=Color.BLUE;
	   color[1]=Color.GREEN;
	   color[2]=Color.YELLOW;
	   color[3]=Color.BLACK;
	   color[4]=Color.PINK;
	   color[5]=Color.ORANGE;
	   color[6]=Color.AQUA;
	   color[7]=Color.CHOCOLATE;
	   color[8]=Color.GREY;
	   color[9]=Color.CRIMSON;
	   String[] name=new String[10];
	   //Creating Array to strore name
	   name[0]="BLUE";
	   name[1]="GREEN";
	   name[2]="YELLOW";
	   name[3]="BLACK";
	   name[4]="PINK";
	   name[5]="ORANGE";
	   name[6]="AQUA";
	   name[7]="CHOCOLATE";
	   name[8]="GREY";
	   name[9]="CRIMSON";
	   */
	   tg=new ToggleGroup();
		 //Creatubg toggle buttons for the collers based on the chromatic number
		 for(int i=0; i<CN; i++){
	
			 //ToggleButton togle=new ToggleButton(name[i]);
			 ToggleButton togle=new ToggleButton();
			 if (i==0) togle.setSelected(true);
			 
			 //Give the togle userData
			 togle.setUserData(color[i]);
			 //add the togle to the group
			 togle.setToggleGroup(tg);
			 //togle.setStyle("-fx-base: "+name[i]);
			 String colo = color[i].toString();
			 String colo2 = colo.substring(2);
			 //colo2 = isOn ? OFF_COLOR : ON_COLOR;
			 System.out.println(colo2);
			 togle.setStyle("-fx-background-color: #"+colo2);
			 togle.setMaxSize(90, 90);
			 togle.setMinSize(90,90);
			 togle.setTranslateX(stage.getWidth()-150);
			 //add the group to the layout
			 layout2.getChildren().add(togle);
		 }
	   
	   
   	   //boolean matrix to check which edges have been used or not
   	   boolean[][] edgesstate = new boolean[Nvertices][Nvertices];
   	   //some weird magic
   	   ColCircle vertices[] = null;
   	   arr = null;
   	   
   	   vertices = new ColCircle[Nvertices];
   	   arr = new dragNode[Nvertices];
       width = (int) stage.getWidth();
       height = (int) stage.getHeight();
   	   
   	   double angle = 2*Math.PI/Nvertices;
   	   // giving x and y values to the circles
   	   for(int i=0; i<Nvertices;i++){
   	   	   vertices[i] = new ColCircle();
   	   	   vertices[i].x = (int)(height/3.0*Math.cos(i*angle)+width/3.0);
   	   	   vertices[i].y = (int)(height/3.0*Math.sin(i*angle)+height/2.0);
   	   }
   	   //place counter
   	   int p = 0;
   	     	   
      //Creating a root group
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
      //dragNode[] arr = new dragNode[Nvertices];
      
      //make the dragNodes
      for(int i = 0; i<Nvertices;i++){
    	  arr[i] = new dragNode(Color.WHITE,startX[i],startY[i],20);
      }
      
      //mane an array to put the Boundes Lines in
       lines = new fixedLine[edges];
       
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
      
      Button btnNext = new Button("Next");
      btnNext.setTranslateX(20);
      btnNext.setTranslateY(stage.getHeight()-100);
      btnNext.setOnAction(
              new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent e) {
      					RandomGraph rnd= new RandomGraph();
      					adjacency = rnd.createMatrix();
      					int numberofVertices = rnd.getVertices();
      					int numberofEdges = rnd.getEdges();
      					Mode1 test = new Mode1(adjacency,numberofVertices,numberofEdges);
                     	test.start(stage);
                  }
				});
      
      hint = new ToggleButton("Hint");
      hint.setStyle("-fx-background-color: #D3D3D3");
      hint.setTranslateX(20);
      hint.setTranslateY(20);
      hint.setSelected(false);
      hint.setOnAction(
              new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent e) {
                	  if(hint.isSelected()) {
                		  hint.setStyle("-fx-background-color: #90EE90");
                		  checkedges();
                	  }
                	  else {
                		  hint.setStyle("-fx-background-color: #D3D3D3");
                		  for(int p =0; p<edges;p++) {
                			  lines[p].setStroke(Color.GRAY);  
                		  }
                	  }
                  }
              });
      TextField Input = new TextField();
      Input.setTranslateX(20);
	  Input.setTranslateY(stage.getHeight()-400);
	  
      Button BackTrack = new Button("Solve it");
      BackTrack.setTranslateX(20);
      BackTrack.setTranslateY(stage.getHeight()-500);
		 BackTrack.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			       // greedy();
			    	String amount = Input.getText();
			    	pressed = (int) Double.parseDouble(amount)-1;
			    	backtracking();
			        checkcolors();
	        		//checkedges();
			    }
			});
 /*     
      Button btnBack = new Button("Home");
      btnBack.setTranslateX(20);
      btnBack.setTranslateY(50);
      btnBack.setOnAction(
              new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent e) {
      					//Main home = new Main();
                    // 	home.start(stage);
                	  String[] args = {};
                     	Main.main(null);;
                  }
				});
      //->not working
      */
      
      Win = new Label();
      Win.setFont(new Font("Arial",150));
      Win.setTextFill(Color.GREEN);

      
      //add the arrays to the gro up
      root.getChildren().add(layout);
      root.getChildren().addAll(lines);
      root.getChildren().addAll(arr);
      root.getChildren().add(btnNext);
      root.getChildren().add(hint);
      root.getChildren().add(Win);
      root.getChildren().add(BackTrack);
      root.getChildren().add(Input);
 //     root.getChildren().add(btnBack);
      
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
   public dragNode[] getdragNode() {
	   return arr;
   }
   public void setCN(int Chron) {
	   CN = Chron;
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
	      setFill(color);
	      setStroke(Color.BLACK);
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
	        	MouseButton button = mouseEvent.getButton();
	        	if (button == MouseButton.SECONDARY) {
		          // record a delta distance for the drag and drop operation.
		          dragDelta.x = getCenterX() - mouseEvent.getX();
		          dragDelta.y = getCenterY() - mouseEvent.getY();
		          getScene().setCursor(Cursor.MOVE);
	        	}
	        	if (button == MouseButton.PRIMARY) {
	        		setFill((Color)tg.getSelectedToggle().getUserData());
	        		checkcolors();
	        		if(hint.isSelected()) checkedges();
	        	}
	        }
	      });
	      setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          getScene().setCursor(Cursor.HAND);
	        }
	      });
	      setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	        	MouseButton button = mouseEvent.getButton();
	        	if (button == MouseButton.SECONDARY) {
		          double newX = mouseEvent.getX() + dragDelta.x;
		          if (newX > 0 && newX < getScene().getWidth()) {
		            setCenterX(newX);
		          }  
		          double newY = mouseEvent.getY() + dragDelta.y;
		          if (newY > 0 && newY < getScene().getHeight()) {
		            setCenterY(newY);
		          }
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
	  
	  
	  private void checkcolors() {
		boolean check=true;
		Set<String> set = new HashSet<String>(); 
		for(int i = 0; i<Nvertices; i++) {
			for(int j=0; j<Nvertices; j++){
				if(adjacency[i][j]==1) {
					if(arr[i].getFill().equals(arr[j].getFill())||arr[i].getFill().equals(Color.WHITE)) {
						check = false;
					}else {
						set.add(arr[i].getFill().toString());
					}
				}
			}
		}
		int size = set.size();
		if(check && size==CN) finished = true;
		if(finished) {
			Win.setText("Finished");
			Win.setTranslateX(width/2-300);							//Win.getWidth always gives 0.0?
		    Win.setTranslateY(height/2-Win.getHeight()/2);
		}

	 }
	  
	  private void checkedges(){
		  for(int p =0; p<edges;p++) {
			  lines[p].setStroke(Color.GREEN);  
		  }
		  for(int i = 0; i<Nvertices; i++) {
				for(int j=0; j<Nvertices; j++){
					if(adjacency[i][j]==1) {
						for(int p =0; p<edges;p++) {
							double xe = lines[p].getEndX();
							double xb = lines[p].getStartX();
							
							double ye = lines[p].getEndY();
							double yb = lines[p].getStartY();
						
						if(arr[i].getFill().equals(arr[j].getFill())){
							if((xe==arr[i].getCenterX() && ye == arr[i].getCenterY())||(xb==arr[i].getCenterX() && yb == arr[i].getCenterY()))
								if((xe==arr[j].getCenterX() && ye == arr[j].getCenterY())||(xb==arr[j].getCenterX() && yb == arr[j].getCenterY())){
										lines[p].setStroke(Color.RED);
										}
							}
						}
					}
				}
		  }
	  }
	   private void backtracking() {
		  for(int i = 0; i<Nvertices;i++){
			  if(colorSol[i]-1<=pressed) {
			  arr[i].setFill(color[colorSol[i]-1]); 
			  }
			  else {arr[i].setFill(Color.WHITE);}
		  }
	  }
	  public void setSol(int[] sol) {
		 this.colorSol = sol;
	  }
}