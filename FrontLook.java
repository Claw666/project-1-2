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
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FrontLook extends Application {
  
  private int[][] adjacency;
  private int numberofEdges;
  private int numberofVertices;
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    
    //Define Window title
    primaryStage.setTitle("Team 8 - Graph Game");
    
    //Defining layouts 
    VBox mainContainer = new VBox();
    VBox modeOneContainer = new VBox();
    VBox modeTwoContainer = new VBox();
    VBox modeThreeContainer = new VBox();
    HBox menuBox = new HBox();
    VBox sliderBox = new VBox();
    HBox modeBox = new HBox();
    HBox backCont = new HBox();
    HBox backCont2 = new HBox();
    HBox backCont3 = new HBox();
    VBox creditsMainCont = new VBox();
    HBox credTitleCont = new HBox();
    HBox credSubTitleCont = new HBox();
    GridPane credMainCont = new GridPane();
    VBox rulesMainCont = new VBox();
    HBox rulesTitleCont = new HBox();
    HBox rulSubTitleCont = new HBox();
    GridPane ruleMainCont = new GridPane();
    VBox setMainCont = new VBox();
    HBox settingsTitleCont = new HBox();
    HBox setSubTitleCont = new HBox();
    
    //Create title for the home page
    Text title = new Text("JOIN FOR THE ADVENTURE");
    title.getStyleClass().add("title");
    
    //Create home page menu buttons
    Button settings = new Button("SETTINGS");
    settings.getStyleClass().add("menu_items");
    Button rules = new Button("RULES");
    rules.getStyleClass().add("menu_items");
    Button credits = new Button("CREDITS");
    credits.getStyleClass().add("menu_items");
    
    //Add menu items to container  
    menuBox.setAlignment(Pos.CENTER);
    menuBox.setPrefWidth(400);
    menuBox.setSpacing(10);
    menuBox.getChildren().addAll(settings, rules, credits);
    
    //Create top part of the home page
    sliderBox.setAlignment(Pos.CENTER);
    sliderBox.setPrefHeight(500);
    sliderBox.getChildren().addAll(title, menuBox);
    
    //Game modes on home page
    ImageView iv_1 = new ImageView();
    iv_1.setImage(new Image(new FileInputStream("src/application/mode-one.png"))); //Add game mode image
    Label modeOneLabel = new Label("Mode 1"); //Add game mode label
    modeOneLabel.setStyle("-fx-font-size: 2em;");
    modeOneLabel.setPadding(new Insets(20,0,0,0));
    
    ImageView iv_2 = new ImageView();
    iv_2.setImage(new Image(new FileInputStream("src/application/mode-two.png"))); //Add game mode image
    Label modeTwoLabel = new Label("Mode 2"); //Add game mode label
    modeTwoLabel.setStyle("-fx-font-size: 2em;");
    modeTwoLabel.setPadding(new Insets(20,0,0,0));
    
    ImageView iv_3 = new ImageView();
    iv_3.setImage(new Image(new FileInputStream("src/application/mode-three.png"))); //Add game mode image
    Label modeThreeLabel = new Label("Mode 3");  //Add game mode label
    modeThreeLabel.setStyle("-fx-font-size: 2em;");
    modeThreeLabel.setPadding(new Insets(20,0,0,0));
    
    
    //Mode 1 Container: add style to it and set function on mouse click
    modeOneContainer.getStyleClass().add("modeButton");
    modeOneContainer.setPadding(new Insets(10,10,10,10));
    modeOneContainer.setPickOnBounds(true); // allows click on transparent areas
    modeOneContainer.setOnMouseClicked( new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (numberofVertices > 0) {
          Mode1 test = new Mode1(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage); 
        } else {
          RandomGraph rnd= new RandomGraph(10,19);
          adjacency = rnd.createMatrix();
          numberofVertices = rnd.getVertices();
          numberofEdges = rnd.getEdges();
          Mode1 test = new Mode1(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage);
        }
      }
    });
    modeOneContainer.getChildren().addAll(iv_1,modeOneLabel);
    modeOneContainer.setAlignment(Pos.CENTER);
    
    //Mode 2 Container: add style to it and set function on mouse click
    modeTwoContainer.getStyleClass().add("modeButton");
    modeTwoContainer.setPadding(new Insets(10,10,10,10));
    modeTwoContainer.setPickOnBounds(true); // allows click on transparent areas
    modeTwoContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (numberofVertices>0){
          Mode2 test = new Mode2(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage); 
        }else {
          RandomGraph rnd= new RandomGraph(10,19);
          adjacency = rnd.createMatrix();
          numberofVertices = rnd.getVertices();
          numberofEdges = rnd.getEdges();
          Mode2 test = new Mode2(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage);
        }
      }
    });
    modeTwoContainer.getChildren().addAll(iv_2,modeTwoLabel);
    modeTwoContainer.setAlignment(Pos.CENTER);
    
    //Mode 3 Container: add style to it and set function on mouse click
    modeThreeContainer.getStyleClass().add("modeButton");
    modeThreeContainer.setPadding(new Insets(10,10,10,10));
    modeThreeContainer.setPickOnBounds(true); // allows click on transparent areas
    modeThreeContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (numberofVertices>0){
          Mode3 test = new Mode3(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage); 
        }else {
          RandomGraph rnd= new RandomGraph(10,19);
          adjacency = rnd.createMatrix();
          numberofVertices = rnd.getVertices();
          numberofEdges = rnd.getEdges();
          Mode3 test = new Mode3(adjacency,numberofVertices,numberofEdges);
          test.start(primaryStage);
        }
      }
    });
    modeThreeContainer.getChildren().addAll(iv_3,modeThreeLabel);
    modeThreeContainer.setAlignment(Pos.CENTER);    
    

    modeBox.getChildren().addAll(createSpacer(),modeOneContainer,createSpacer(), modeTwoContainer,createSpacer(), modeThreeContainer,createSpacer());
    modeBox.setPadding(new Insets(0,0,0,0));
    
    mainContainer.getChildren().addAll(sliderBox, modeBox);
    
    //Add homepage to scene
    Scene scene = new Scene(mainContainer);
    
    //Add external stylesheet
    scene.getStylesheets().add("application/Main.css");

    primaryStage.setScene(scene);

    //set Stage boundaries to visible bounds of the main screen
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX(primaryScreenBounds.getMinX());
    primaryStage.setY(primaryScreenBounds.getMinY());
    primaryStage.setWidth(primaryScreenBounds.getWidth());
    primaryStage.setHeight(primaryScreenBounds.getHeight());

    //Show stage
    primaryStage.show();
    
    //Back buttons
    Button back = new Button("BACK");
    backCont.getChildren().add(back);
    backCont.setAlignment(Pos.CENTER_RIGHT);
    backCont.setPadding(new Insets(50, 50, 50, 50));
    
    Button back2 = new Button("BACK");
    backCont2.getChildren().add(back2);
    backCont2.setAlignment(Pos.CENTER_RIGHT);
    backCont2.setPadding(new Insets(50, 50, 50, 50));
    
    Button back3 = new Button("BACK");
    backCont3.getChildren().add(back3);
    backCont3.setAlignment(Pos.CENTER_RIGHT);
    backCont3.setPadding(new Insets(50, 50, 50, 50));

    //Credit page - title
    Text credTitle = new Text("CREDITS");
    credTitle.setStyle("-fx-font-size: 30px;");
    credTitleCont.getChildren().add(credTitle);
    credTitleCont.setPrefHeight(100);
    credTitleCont.setAlignment(Pos.CENTER);
    
    // Credit page - subtitle 
    Text credSubTitle = new Text("Great computer scientist are always trying to solve problems in a clever way and not the hard way. We tried to do the same so for the credit please enjoy some Lorum Ipsum");
    credSubTitleCont.getChildren().add(credSubTitle);
    credSubTitleCont.setPrefHeight(50);
    credSubTitleCont.setAlignment(Pos.CENTER);
    credSubTitle.setStyle("-fx-font-size: 20px;");
    
    //Style credit page container
    credMainCont.setStyle("-fx-font-size: 18px;");
    credMainCont.setPadding(new Insets(50, 50, 50, 50)); 
    //Setting the vertical and horizontal gaps between the columns 
    credMainCont.setVgap(50); 
    credMainCont.setHgap(50); 
    
    
    //David's credit part
    Text DavidTitle = new Text("David");
    StackPane davidTitleBG = new StackPane();
    davidTitleBG.getChildren().add(DavidTitle);
    davidTitleBG.setPadding(new Insets(10, 10, 10, 10));
    davidTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text DavidContent = new Text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    TextFlow DavidCont = new TextFlow(); 
    ObservableList<Node> list = DavidCont.getChildren(); 
    list.addAll(DavidContent);
    DavidCont.setPadding(new Insets(10, 10, 10, 10));
    DavidCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Celia's credit part
    Text CeliaTitle = new Text("Celia");
    StackPane celiaTitleBG = new StackPane();
    celiaTitleBG.getChildren().add(CeliaTitle);
    celiaTitleBG.setPadding(new Insets(10, 10, 10, 10));
    celiaTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text CeliaContent = new Text("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.");
    TextFlow CeliaCont = new TextFlow(); 
    ObservableList<Node> list2 = CeliaCont.getChildren(); 
    list2.addAll(CeliaContent);
    CeliaCont.setPadding(new Insets(10, 10, 10, 10));
    CeliaCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Pedro's credit part
    Text PedroTitle = new Text("Pedro");
    StackPane pedroTitleBG = new StackPane();
    pedroTitleBG.getChildren().add(PedroTitle);
    pedroTitleBG.setPadding(new Insets(10, 10, 10, 10));
    pedroTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text PedroContent = new Text("Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.");
    TextFlow PedroCont = new TextFlow(); 
    ObservableList<Node> list3 = PedroCont.getChildren(); 
    list3.addAll(PedroContent);
    PedroCont.setPadding(new Insets(10, 10, 10, 10));
    PedroCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Nick's credit part
    Text NickTitle = new Text("Nick");
    StackPane nickTitleBG = new StackPane();
    nickTitleBG.getChildren().add(NickTitle);
    nickTitleBG.setPadding(new Insets(10, 10, 10, 10));
    nickTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text NickContent = new Text("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.");
    TextFlow NickCont = new TextFlow(); 
    ObservableList<Node> list4 = NickCont.getChildren(); 
    list4.addAll(NickContent);
    NickCont.setPadding(new Insets(10, 10, 10, 10));
    NickCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Devdutt's credit part
    Text DevTitle = new Text("Devdutt");
    StackPane devTitleBG = new StackPane();
    devTitleBG.getChildren().add(DevTitle);
    devTitleBG.setPadding(new Insets(10, 10, 10, 10));
    devTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text DevContent = new Text("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.");
    TextFlow DevCont = new TextFlow(); 
    ObservableList<Node> list5 = DevCont.getChildren(); 
    list5.addAll(DevContent);
    DevCont.setPadding(new Insets(10, 10, 10, 10));
    DevCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Tommy's credit part
    Text TommyTitle = new Text("Tommy"); 
    StackPane tommyTitleBG = new StackPane();
    tommyTitleBG.getChildren().add(TommyTitle);
    tommyTitleBG.setPadding(new Insets(10, 10, 10, 10));
    tommyTitleBG.setStyle("-fx-background-color: #dae7f3;");
    Text TommyContent = new Text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. ");
    TextFlow TommyCont = new TextFlow(); 
    ObservableList<Node> list6 = TommyCont.getChildren(); 
    list6.addAll(TommyContent);
    TommyCont.setPadding(new Insets(10, 10, 10, 10));
    TommyCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Adjusting each credit block in the grid
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
    
    //Add elements to the credit container
    creditsMainCont.getChildren().addAll(credTitleCont, credSubTitleCont, credMainCont, backCont);
    
    Scene creditScene = new Scene(creditsMainCont);
    
    //Rules page
    rulesTitleCont.setAlignment(Pos.CENTER);
    Text rulesTitle = new Text("RULES");
    rulesTitle.setStyle("-fx-font-size: 30px;");
    rulesTitleCont.getChildren().add(rulesTitle);
    rulesTitleCont.setPrefHeight(100);
    rulSubTitleCont.setAlignment(Pos.CENTER);
    Text ruleSubTitle = new Text("On this page the user can generate or use their own graph for the game. By setting more vertices and more edges, the level of difficulty may increase accordingly.");
    rulSubTitleCont.getChildren().add(ruleSubTitle);
    rulSubTitleCont.setPrefHeight(50);
    ruleSubTitle.setStyle("-fx-font-size: 20px;");
    ruleMainCont.setStyle("-fx-font-size: 18px;");
    ruleMainCont.setPadding(new Insets(50, 50, 50, 50)); 
    ruleMainCont.setVgap(50); 
    ruleMainCont.setHgap(50);
    
    //Rule page - rule 1 
    Text ruleOneName = new Text("Game Mode 1");
    StackPane ruleOneBG = new StackPane();
    ruleOneBG.getChildren().add(ruleOneName);
    ruleOneBG.setPadding(new Insets(10, 10, 10, 10));
    ruleOneBG.setStyle("-fx-background-color: #dae7f3;");
    Text ruleOneContent = new Text("To the bitter end. The player simply has to colour the graph as quickly as possible. The computer does not allow the player to finish until the minimum number of colours has been reached.");
    TextFlow ruleOneCont = new TextFlow(); 
    ObservableList<Node> list7 = ruleOneCont.getChildren(); 
    list7.addAll(ruleOneContent);
    ruleOneCont.setPadding(new Insets(10, 10, 10, 10));
    ruleOneCont.setStyle("-fx-background-color: #dae7f3;");

    //Rule page - rule 2 
    Text ruleTwoName = new Text("Game Mode 2");
    StackPane ruleTwoBG = new StackPane();
    ruleTwoBG.getChildren().add(ruleTwoName);
    ruleTwoBG.setPadding(new Insets(10, 10, 10, 10));
    ruleTwoBG.setStyle("-fx-background-color: #dae7f3;");
    Text ruleTwoContent = new Text("Best upper bound in a fixed time frame. The player is given a fixed amount of time and they have to find a colouring with as few colours as possible in the given time. Here is not necessary that the user finds the minimum number of colours.");
    TextFlow ruleTwoCont = new TextFlow(); 
    ObservableList<Node> list8 = ruleTwoCont.getChildren(); 
    list8.addAll(ruleTwoContent);
    ruleTwoCont.setPadding(new Insets(10, 10, 10, 10));
    ruleTwoCont.setStyle("-fx-background-color: #dae7f3;");

    //Rule page - rule 3 
    Text ruleThreeName = new Text("Game Mode 3");
    StackPane ruleThreeBG = new StackPane();
    ruleThreeBG.getChildren().add(ruleThreeName);
    ruleThreeBG.setPadding(new Insets(10, 10, 10, 10));
    ruleThreeBG.setStyle("-fx-background-color: #dae7f3;");
    Text ruleThreeContent = new Text("Random order. Here the computer generates a random ordering of the vertices and the player has to pick the colours of the vertices in exactly that order. Once the colour of a vertex has been chosen, it cannot be changed again. The goal for the player is to use as few colours as possible.");
    TextFlow ruleThreeCont = new TextFlow(); 
    ObservableList<Node> list9 = ruleThreeCont.getChildren(); 
    list9.addAll(ruleThreeContent);
    ruleThreeCont.setPadding(new Insets(10, 10, 10, 10));
    ruleThreeCont.setStyle("-fx-background-color: #dae7f3;");
    
    //Adjust each element position in the ruleMainCont (container)
    ruleMainCont.add(ruleOneBG, 0, 0);
    ruleMainCont.add(ruleTwoBG, 0, 1);
    ruleMainCont.add(ruleThreeBG, 0, 2);

    ruleMainCont.add(ruleOneCont, 1, 0);
    ruleMainCont.add(ruleTwoCont, 1, 1);
    ruleMainCont.add(ruleThreeCont, 1, 2);    
    
    //Add elements to the rules container
    rulesMainCont.getChildren().addAll(rulesTitleCont, rulSubTitleCont, ruleMainCont,backCont2);
    
    Scene ruleScene = new Scene(rulesMainCont);
    
    //Settings page
    
    settingsTitleCont.setAlignment(Pos.CENTER);
    Text settingsTitle = new Text("SETTINGS");
    settingsTitle.setStyle("-fx-font-size: 30px;");
    settingsTitleCont.getChildren().add(settingsTitle);
    settingsTitleCont.setPrefHeight(100);

    setSubTitleCont.setAlignment(Pos.CENTER);
    Text setSubTitle = new Text("This is a sub credit title for showcasing the work of team 8. We are very proud of what we made over the weeks and can't wait for period 3 to finish this shit up.");
    setSubTitleCont.getChildren().add(setSubTitle);
    setSubTitleCont.setPrefHeight(50);
    setSubTitle.setStyle("-fx-font-size: 20px;");
    
    //Options for choosing number of vertices and edges before generating a random graph    
    
    //Slider for choosing number of vertices
    Label  labelVertices  = new Label();
    Label labelVertix = new Label("Number of Vertices: ");
    Slider sliderVertices = new Slider(5, 15, 1);
    sliderVertices.setBlockIncrement(1);
    sliderVertices.setMajorTickUnit(1);
    sliderVertices.setMinorTickCount(0);
    sliderVertices.setSnapToTicks(true);

    labelVertices.textProperty().bind(Bindings.format("%.2f", sliderVertices.valueProperty()));
    HBox vertixCtrCont = new HBox(sliderVertices,labelVertices);
    
    
    //Slider for choosing number of edges
    Label  labelEdges  = new Label();
    Label labelEdge = new Label("Number of Edges: ");
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

    labelEdges.textProperty().bind(Bindings.format("%.2f",sliderEdges.valueProperty()));
    HBox edgeCtrCont = new HBox(sliderEdges,labelEdges);
    
    //Generate a random graph within the settings page
    Button createGraph = new Button("GENERATE");

    createGraph.setOnAction(
      new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent e) {
         numberofEdges = (int) sliderEdges.getValue();
         numberofVertices = (int) sliderVertices.getValue();
         RandomGraph randomG = new RandomGraph(numberofVertices,numberofEdges);
         adjacency = randomG.createMatrix();
         Preview generateRandom = new Preview(adjacency,numberofVertices,numberofEdges);
         generateRandom.display();
       }
     });
    
    //Open an existing graph within the settings page
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
          Preview test = new Preview(adjacency,numberofVertices,numberofEdges);
          if (file != null) {
           System.out.println("Name of file: " + file);
           test.display();
         }
       }
     });
    
    HBox setBtnCont = new HBox(createGraph,openBttn);
    
    GridPane settingsMainCont = new GridPane();
    settingsMainCont.setStyle("-fx-font-size: 18px;");
    settingsMainCont.setPadding(new Insets(50, 50, 50, 50)); 
    settingsMainCont.setHgap(150);
    settingsMainCont.setVgap(20);
    settingsMainCont.add(labelVertix, 0, 0);
    settingsMainCont.add(labelEdge, 1, 0);
    settingsMainCont.add(vertixCtrCont, 0, 1);
    settingsMainCont.add(edgeCtrCont, 1, 1);
    settingsMainCont.add(setBtnCont, 2, 1);


    HBox centerSlider = new HBox(settingsMainCont);
    centerSlider.setAlignment(Pos.CENTER);

    vertixCtrCont.setAlignment(Pos.CENTER);
    edgeCtrCont.setAlignment(Pos.CENTER);

    //Adding all elements for the settings page
    setMainCont.getChildren().addAll(settingsTitleCont, setSubTitleCont,centerSlider, backCont3);
    
    Scene setScene = new Scene(setMainCont);   
    
    //When clicking on 'back' at the pages, open the main scene -> the home page
    
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
  
  //A method for creating a spacer for the mode containers so they can be equally distributed
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
