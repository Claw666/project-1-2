package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.control.*;
import java.io.FileInputStream;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application {
	
	private int[][] adjacency;
	private int numberofEdges;
	private int numberofVertices;
	
  @Override
  public void start(Stage primaryStage) throws Exception {
    
    //Define Window title
    primaryStage.setTitle("Team 8 - Graph Game");
    
    
    VBox sliderBox = new VBox();
    sliderBox.setAlignment(Pos.CENTER);
    sliderBox.setPrefHeight(500);
    Text title = new Text("JOIN FOR THE ADVENTURE");
    title.getStyleClass().add("title");
    
    Button settings = new Button("SETTINGS");
    settings.getStyleClass().add("menu_items");
    Button rules = new Button("RULES");
    rules.getStyleClass().add("menu_items");
    Button credits = new Button("CREDITS");
    credits.getStyleClass().add("menu_items");
    
    HBox menuBox = new HBox();
    menuBox.setAlignment(Pos.CENTER);
    menuBox.setPrefWidth(400);
    menuBox.setSpacing(10);
    menuBox.getChildren().addAll(settings, rules, credits);
    
    sliderBox.getChildren().addAll(title, menuBox);
    
    
    
    
        //Add game mode images
    ImageView iv_1 = new ImageView();
    iv_1.setImage(new Image(new FileInputStream("src/application/mode-one.png")));
    ImageView iv_2 = new ImageView();
    iv_2.setImage(new Image(new FileInputStream("src/application/mode-two.png")));
    ImageView iv_3 = new ImageView();
    iv_3.setImage(new Image(new FileInputStream("src/application/mode-three.png")));
    
        //Add game mode labels
    Label modeOneLabel = new Label("Mode 1");
    Label modeTwoLabel = new Label("Mode 2");
    Label modeThreeLabel = new Label("Mode 3");
    
        //Defining layouts 
    VBox mainContainer = new VBox();
    VBox modeOneContainer = new VBox();
    VBox modeTwoContainer = new VBox();
    VBox modeThreeContainer = new VBox();
    
    
    HBox modeBox = new HBox();

    
    modeOneContainer.getStyleClass().add("modeButton");
    modeOneContainer.setPadding(new Insets(10,10,10,10));
    modeTwoContainer.getStyleClass().add("modeButton");
    modeTwoContainer.setPadding(new Insets(10,10,10,10));
    modeThreeContainer.getStyleClass().add("modeButton");
    modeThreeContainer.setPadding(new Insets(10,10,10,10));
    
        modeOneContainer.setPickOnBounds(true); // allows click on transparent areas
        modeOneContainer.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Clicked!"); // change functionality
          });
        
        modeTwoContainer.setPickOnBounds(true); // allows click on transparent areas
        modeTwoContainer.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Clicked!"); // change functionality
          });
        
        modeThreeContainer.setPickOnBounds(true); // allows click on transparent areas
        modeThreeContainer.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Clicked!"); // change functionality
          });
        
        modeOneContainer.setOnMouseEntered(new EventHandler <MouseEvent>() {

          @Override
          public void handle(MouseEvent t) {
           iv_1.setStyle("-fx-background-color:#dae7f3;");
         }
       });
        
        modeOneContainer.getChildren().addAll(iv_1,modeOneLabel);
        modeOneContainer.setAlignment(Pos.CENTER);
        modeTwoContainer.getChildren().addAll(iv_2,modeTwoLabel);
        modeTwoContainer.setAlignment(Pos.CENTER);
        modeThreeContainer.getChildren().addAll(iv_3,modeThreeLabel);
        modeThreeContainer.setAlignment(Pos.CENTER);
        
        modeBox.getChildren().addAll(createSpacer(),modeOneContainer,createSpacer(), modeTwoContainer,createSpacer(), modeThreeContainer,createSpacer());
        modeBox.setPadding(new Insets(0,0,0,0));
        
        
        
        mainContainer.getChildren().addAll(sliderBox, modeBox);

        Scene scene = new Scene(mainContainer);
        scene.getStylesheets().add("application/Main.css");
        primaryStage.setScene(scene);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        
        
        //Show stage
        primaryStage.show();
        
        Button back = new Button("BACK");
        HBox backCont = new HBox();
        backCont.getChildren().add(back);
        backCont.setAlignment(Pos.CENTER_RIGHT);
        backCont.setPadding(new Insets(50, 50, 50, 50));
        //Credits
        
        VBox creditsMainCont = new VBox();
        
        HBox credTitleCont = new HBox();
        credTitleCont.setAlignment(Pos.CENTER);
        Text credTitle = new Text("CREDITS");
        credTitle.setStyle("-fx-font-size: 30px;");
        credTitleCont.getChildren().add(credTitle);
        credTitleCont.setPrefHeight(100);
        
        HBox credSubTitleCont = new HBox();
        credSubTitleCont.setAlignment(Pos.CENTER);
        Text credSubTitle = new Text("This is a sub credit title for showcasing the work of team 8. We are very proud of what we made over the weeks and can't wait for period 3 to finish this shit up.");
        credSubTitleCont.getChildren().add(credSubTitle);
        credSubTitleCont.setPrefHeight(50);
        credSubTitle.setStyle("-fx-font-size: 20px;");
        GridPane credMainCont = new GridPane();
        credMainCont.setStyle("-fx-font-size: 18px;");
        credMainCont.setPadding(new Insets(50, 50, 50, 50)); 
        //Setting the vertical and horizontal gaps between the columns 
        credMainCont.setVgap(50); 
        credMainCont.setHgap(50); 
        
        Text DavidTitle = new Text("David");
        Text CeliaTitle = new Text("Celia");
        Text PedroTitle = new Text("Pedro");
        Text NickTitle = new Text("Nick");
        Text DevTitle = new Text("Devdutt");
        Text TommyTitle = new Text("Csongor Kocsis");
        
        StackPane davidTitleBG = new StackPane();
        StackPane celiaTitleBG = new StackPane();
        StackPane pedroTitleBG = new StackPane();
        StackPane nickTitleBG = new StackPane();
        StackPane devTitleBG = new StackPane();
        StackPane tommyTitleBG = new StackPane();

        davidTitleBG.getChildren().add(DavidTitle);
        celiaTitleBG.getChildren().add(CeliaTitle);
        pedroTitleBG.getChildren().add(PedroTitle);
        nickTitleBG.getChildren().add(NickTitle);
        devTitleBG.getChildren().add(DevTitle);
        tommyTitleBG.getChildren().add(TommyTitle);
        
        davidTitleBG.setPadding(new Insets(10, 10, 10, 10));
        celiaTitleBG.setPadding(new Insets(10, 10, 10, 10));
        pedroTitleBG.setPadding(new Insets(10, 10, 10, 10));
        nickTitleBG.setPadding(new Insets(10, 10, 10, 10));
        devTitleBG.setPadding(new Insets(10, 10, 10, 10));
        tommyTitleBG.setPadding(new Insets(10, 10, 10, 10));
        
        davidTitleBG.setStyle("-fx-background-color: #dae7f3;");
        celiaTitleBG.setStyle("-fx-background-color: #dae7f3;");
        pedroTitleBG.setStyle("-fx-background-color: #dae7f3;");
        nickTitleBG.setStyle("-fx-background-color: #dae7f3;");
        devTitleBG.setStyle("-fx-background-color: #dae7f3;");
        tommyTitleBG.setStyle("-fx-background-color: #dae7f3;");
        
        
        Text DavidContent = new Text("David was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow DavidCont = new TextFlow(); 
        //Retrieving the observable list of the TextFlow Pane 
        ObservableList list = DavidCont.getChildren(); 
        list.addAll(DavidContent);
        
        Text CeliaContent = new Text("Celia was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow CeliaCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list2 = CeliaCont.getChildren(); 
        list2.addAll(CeliaContent);
        
        Text PedroContent = new Text("Pedro was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have."
          + "Pedro was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow PedroCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list3 = PedroCont.getChildren(); 
        list3.addAll(PedroContent);

        Text NickContent = new Text("Nick was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow NickCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list4 = NickCont.getChildren(); 
        list4.addAll(NickContent);

        Text DevContent = new Text("Dev was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow DevCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list5 = DevCont.getChildren(); 
        list5.addAll(DevContent);

        Text TommyContent = new Text("Tommy was responsible for creating the random matrix, adding multiple whatever to containers. On top of that, he was also helping out with this and that. Lastly, he created something new that no one else could have.");
        TextFlow TommyCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list6 = TommyCont.getChildren(); 
        list6.addAll(TommyContent);
        
        
        DavidCont.setPadding(new Insets(10, 10, 10, 10));
        CeliaCont.setPadding(new Insets(10, 10, 10, 10));
        PedroCont.setPadding(new Insets(10, 10, 10, 10));
        NickCont.setPadding(new Insets(10, 10, 10, 10));
        DevCont.setPadding(new Insets(10, 10, 10, 10));
        TommyCont.setPadding(new Insets(10, 10, 10, 10));

        DavidCont.setStyle("-fx-background-color: #dae7f3;");
        CeliaCont.setStyle("-fx-background-color: #dae7f3;");
        PedroCont.setStyle("-fx-background-color: #dae7f3;");
        NickCont.setStyle("-fx-background-color: #dae7f3;");
        DevCont.setStyle("-fx-background-color: #dae7f3;");
        TommyCont.setStyle("-fx-background-color: #dae7f3;");
        
        
        credMainCont.add(davidTitleBG, 0, 0);
        credMainCont.add(celiaTitleBG, 0, 1);
        credMainCont.add(pedroTitleBG, 0, 2);
        credMainCont.add(nickTitleBG, 0, 3);
        credMainCont.add(devTitleBG, 0, 4);
        credMainCont.add(tommyTitleBG, 0, 5);
        
        credMainCont.add(DavidCont, 1, 0);
        credMainCont.add(CeliaCont, 1, 1);
        credMainCont.add(PedroCont, 1, 2);
        credMainCont.add(NickCont, 1, 3);
        credMainCont.add(DevCont, 1, 4);
        credMainCont.add(TommyCont, 1, 5);
        
        
        creditsMainCont.getChildren().addAll(credTitleCont, credSubTitleCont, credMainCont, backCont);
        Scene creditScene = new Scene(creditsMainCont);
        
        Button back2 = new Button("BACK");
        HBox backCont2 = new HBox();
        backCont2.getChildren().add(back2);
        backCont2.setAlignment(Pos.CENTER_RIGHT);
        backCont2.setPadding(new Insets(50, 50, 50, 50));
        
        VBox rulesMainCont = new VBox();
        HBox rulesTitleCont = new HBox();
        rulesTitleCont.setAlignment(Pos.CENTER);
        Text rulesTitle = new Text("RULES");
        rulesTitle.setStyle("-fx-font-size: 30px;");
        rulesTitleCont.getChildren().add(rulesTitle);
        rulesTitleCont.setPrefHeight(100);

        HBox rulSubTitleCont = new HBox();
        rulSubTitleCont.setAlignment(Pos.CENTER);
        Text ruleSubTitle = new Text("This is a sub credit title for showcasing the work of team 8. We are very proud of what we made over the weeks and can't wait for period 3 to finish this shit up.");
        rulSubTitleCont.getChildren().add(ruleSubTitle);
        rulSubTitleCont.setPrefHeight(50);
        ruleSubTitle.setStyle("-fx-font-size: 20px;");
        GridPane ruleMainCont = new GridPane();
        ruleMainCont.setStyle("-fx-font-size: 18px;");
        ruleMainCont.setPadding(new Insets(50, 50, 50, 50)); 
        //Setting the vertical and horizontal gaps between the columns 
        ruleMainCont.setVgap(50); 
        ruleMainCont.setHgap(50);
        
        Text ruleOneName = new Text("Game Mode 1");
        Text ruleTwoName = new Text("Game Mode 2");
        Text ruleThreeName = new Text("Game Mode 3");


        StackPane ruleOneBG = new StackPane();
        StackPane ruleTwoBG = new StackPane();
        StackPane ruleThreeBG = new StackPane();

        ruleOneBG.getChildren().add(ruleOneName);
        ruleTwoBG.getChildren().add(ruleTwoName);
        ruleThreeBG.getChildren().add(ruleThreeName);

        ruleOneBG.setPadding(new Insets(10, 10, 10, 10));
        ruleTwoBG.setPadding(new Insets(10, 10, 10, 10));
        ruleThreeBG.setPadding(new Insets(10, 10, 10, 10));

        ruleOneBG.setStyle("-fx-background-color: #dae7f3;");
        ruleTwoBG.setStyle("-fx-background-color: #dae7f3;");
        ruleThreeBG.setStyle("-fx-background-color: #dae7f3;");
        
        Text ruleOneContent = new Text("To the bitter end. The player simply has to colour the graph as quickly as possible. The computer does not allow the player to finish until the minimum number of colours has been reached.");
        TextFlow ruleOneCont = new TextFlow(); 
                //Retrieving the observable list of the TextFlow Pane 
        ObservableList list7 = ruleOneCont.getChildren(); 
        list7.addAll(ruleOneContent);

        Text ruleTwoContent = new Text("Best upper bound in a fixed time frame. The player is given a fixed amount of time and they have to find a colouring with as few colours as possible in the given time. Here is not necessary that the user finds the minimum number of colours.");
        TextFlow ruleTwoCont = new TextFlow(); 
                        //Retrieving the observable list of the TextFlow Pane 
        ObservableList list8 = ruleTwoCont.getChildren(); 
        list8.addAll(ruleTwoContent);

        Text ruleThreeContent = new Text("Random order. Here the computer generates a random ordering of the vertices and the player has to pick the colours of the vertices in exactly that order. Once the colour of a vertex has been chosen, it cannot be changed again. The goal for the player is to use as few colours as possible.");
        TextFlow ruleThreeCont = new TextFlow(); 
                        //Retrieving the observable list of the TextFlow Pane 
        ObservableList list9 = ruleThreeCont.getChildren(); 
        list9.addAll(ruleThreeContent);
        
        ruleOneCont.setPadding(new Insets(10, 10, 10, 10));
        ruleTwoCont.setPadding(new Insets(10, 10, 10, 10));
        ruleThreeCont.setPadding(new Insets(10, 10, 10, 10));

        ruleOneCont.setStyle("-fx-background-color: #dae7f3;");
        ruleTwoCont.setStyle("-fx-background-color: #dae7f3;");
        ruleThreeCont.setStyle("-fx-background-color: #dae7f3;");
        
        ruleMainCont.add(ruleOneBG, 0, 0);
        ruleMainCont.add(ruleTwoBG, 0, 1);
        ruleMainCont.add(ruleThreeBG, 0, 2);

        ruleMainCont.add(ruleOneCont, 1, 0);
        ruleMainCont.add(ruleTwoCont, 1, 1);
        ruleMainCont.add(ruleThreeCont, 1, 2);
        
        
        
        
        rulesMainCont.getChildren().addAll(rulesTitleCont, rulSubTitleCont, ruleMainCont,backCont2);
        Scene ruleScene = new Scene(rulesMainCont);
        
        
        Button back3 = new Button("BACK");
        HBox backCont3 = new HBox();
        backCont3.getChildren().add(back3);
        backCont3.setAlignment(Pos.CENTER_RIGHT);
        backCont3.setPadding(new Insets(50, 50, 50, 50));
        
        VBox setMainCont = new VBox();
        HBox settingsTitleCont = new HBox();
        settingsTitleCont.setAlignment(Pos.CENTER);
        Text settingsTitle = new Text("SETTINGS");
        settingsTitle.setStyle("-fx-font-size: 30px;");
        settingsTitleCont.getChildren().add(settingsTitle);
        settingsTitleCont.setPrefHeight(100);
        HBox setSubTitleCont = new HBox();
        setSubTitleCont.setAlignment(Pos.CENTER);
        Text setSubTitle = new Text("This is a sub credit title for showcasing the work of team 8. We are very proud of what we made over the weeks and can't wait for period 3 to finish this shit up.");
        setSubTitleCont.getChildren().add(setSubTitle);
        setSubTitleCont.setPrefHeight(50);
        setSubTitle.setStyle("-fx-font-size: 20px;");
        
        Label  labelVertices  = new Label();
        Slider sliderVertices = new Slider(5, 15, 1);
        sliderVertices.setBlockIncrement(1);
        sliderVertices.setMajorTickUnit(1);
        sliderVertices.setMinorTickCount(0);
        sliderVertices.setSnapToTicks(true);
        
        labelVertices.textProperty().bind(
                Bindings.format(
                    "%.2f",
                    sliderVertices.valueProperty()
                )
        );
        
        Label  labelEdges  = new Label();
        Slider sliderEdges = new Slider(5, 10, 1);
        sliderVertices.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    double numVertices = sliderVertices.getValue();
                    int max = (int) (numVertices*(numVertices-1)/2);
                    sliderEdges.setMax(max);
            }
        });
        
        sliderEdges.setBlockIncrement(1);
        sliderEdges.setMajorTickUnit(1);
        sliderEdges.setMinorTickCount(0);
        sliderEdges.setSnapToTicks(true);
        
        labelEdges.textProperty().bind(
                Bindings.format(
                    "%.2f",
                    sliderEdges.valueProperty()
                )
        );
        
        Button createGraph = new Button("GENERATE");
        
        createGraph.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                       numberofEdges = (int) sliderEdges.getValue();
                       numberofVertices = (int) sliderVertices.getValue();
                       RandomGraph randomG = new RandomGraph(numberofVertices,numberofEdges);
                       adjacency = randomG.createMatrix();
                       preview test = new preview(adjacency,numberofVertices,numberofEdges);
                       test.display();
                    }
                });
        Button infoBttn = new Button("INFO");
        Button openBttn = new Button("OPEN");
        
        final FileChooser fileChooser = new FileChooser();
        
        
        openBttn.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    ReadGraphClass G = new ReadGraphClass(file.getAbsolutePath());
             	   	adjacency = G.ReadFile();
             	   	numberofEdges = G.getEdges();
             	   	numberofVertices = G.getVertices();
             	   	preview test = new preview(adjacency,numberofVertices,numberofEdges);
                    if (file != null) {
                       System.out.println("Name of file: " + file);
                       test.display();
                    }
                }
            });
        HBox setBtnCont = new HBox(createGraph,infoBttn,openBttn);
        
        
        GridPane settingsMainCont = new GridPane();
        settingsMainCont.setStyle("-fx-font-size: 18px;");
        settingsMainCont.setPadding(new Insets(50, 50, 50, 50)); 
        //Setting the vertical and horizontal gaps between the columns 
        settingsMainCont.setHgap(150);
        settingsMainCont.setVgap(20);
        
        HBox vertixCtrCont = new HBox(sliderVertices,labelVertices);
        HBox edgeCtrCont = new HBox(sliderEdges,labelEdges);
        
        Label labelVertix = new Label("Number of Vertices: ");
        Label labelEdge = new Label("Number of Edges: ");
        
        settingsMainCont.add(labelVertix, 0, 0);
        settingsMainCont.add(labelEdge, 1, 0);
        settingsMainCont.add(vertixCtrCont, 0, 1);
        settingsMainCont.add(edgeCtrCont, 1, 1);
        settingsMainCont.add(setBtnCont, 2, 1);
        
        
        HBox centerSlider = new HBox(settingsMainCont);
        centerSlider.setAlignment(Pos.CENTER);
        
        vertixCtrCont.setAlignment(Pos.CENTER);
        edgeCtrCont.setAlignment(Pos.CENTER);
        
        
        
        RadioButton radioButton1 = new RadioButton("Circle");
        radioButton1.setAlignment(Pos.CENTER);
        RadioButton radioButton2 = new RadioButton("Star");
        RadioButton radioButton3 = new RadioButton("Special");
        RadioButton radioButton4 = new RadioButton("Magic");

        ToggleGroup radioGroup = new ToggleGroup();

        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);
        
        GridPane chooseType = new GridPane();
        chooseType.setStyle("-fx-font-size: 18px;");
        chooseType.setPadding(new Insets(50, 50, 50, 50)); 
        //Setting the vertical and horizontal gaps between the columns 
        chooseType.setHgap(100);
        chooseType.add(radioButton1, 0, 0);
        chooseType.add(radioButton2, 1, 0);
        chooseType.add(radioButton3, 2, 0);
        chooseType.add(radioButton4, 3, 0);
        
        HBox centerType = new HBox(chooseType);
        centerType.setAlignment(Pos.CENTER);
        
        setMainCont.getChildren().addAll(settingsTitleCont, setSubTitleCont,centerSlider,centerType, backCont3);
        Scene setScene = new Scene(setMainCont);
        
        modeOneContainer.setOnMouseClicked(
        		new EventHandler<MouseEvent>() {
        	        @Override
        	        public void handle(MouseEvent event) {
        	        	if (numberofVertices>0){
                       		Mode1 test = new Mode1(adjacency,numberofVertices,numberofEdges);
                       		test.start(primaryStage);	
        				}else {
        					primaryStage.setScene(setScene);
        				    back3.setOnAction(f-> {
        				    	primaryStage.setScene(scene);
        				    });
        				}
                    }
  				});
        
       
        
        credits.setOnAction(e ->{
          primaryStage.setScene(creditScene);
          back.setOnAction(f-> {
            primaryStage.setScene(scene);
          });
        });
        
        settings.setOnAction(e ->{
          primaryStage.setScene(setScene);
          back3.setOnAction(f-> {
            primaryStage.setScene(scene);
          });
        });
        
        rules.setOnAction(e ->{
          primaryStage.setScene(ruleScene);
          back2.setOnAction(f-> {
            primaryStage.setScene(scene);
          });
        });
                
      }
  	  private Node createSpacer() {
  		  final Region spacer = new Region();
  		  // Make it always grow or shrink according to the available space
  		  HBox.setHgrow(spacer, Priority.ALWAYS);
  		  return spacer;
    }	

      public static void main(String[] args) {
        Application.launch(args);
      }
      
    }
