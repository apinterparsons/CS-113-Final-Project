import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color; 
import javafx.scene.layout.Border;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.*; 

/*
 *  A JavaFX: Memory Game 
 */
public class MemoryGameResponsive extends Application
{
   Button [][] buttons;
   GridPane gridpane= new GridPane();
   GridPane gridpane2= new GridPane();
   Image ImageView;
   ImageView [][] iViews1; 
   ImageView [][] iViews2; 
   
   Label headingLabel;
   Label descriptionLabel;
   Label resultLabel;
   Label turnLabel;
   Label matchesLabel;
   Label p1Label;
   Label p2Label;
   
   TextField p2Field;
   TextField p1Field;
  
   HBox name1;
   HBox name2;
   
   String p1;
   String p2;
   
   VBox vbox;
   VBox vboxPlayers;
   VBox radioVBoxWt;
   VBox playVBox;
   Button checkButton;
   
   private Player player1;
   private Player player2;
   private MemoryGame mg; 
    
   int card1Row;
   int card2Row;
   int card1Col;
   int card2Col;
   int size=0;
   int keepTrack = 0;
   

   
   private  ToggleGroup radioGroup; 
   private RadioButton [] radioButtons = new RadioButton[3]; 
   private RadioButton smallBoard;
   private RadioButton medBoard;
   private RadioButton largeBoard;


      
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage stage)
   {  
      resultLabel= makeLabel("");
      turnLabel = makeLabel("");
      matchesLabel = makeLabel("");
      headingLabel= makeLabel("The Memory Game");
      VBox vbox2 = new VBox(headingLabel);
      descriptionLabel=makeLabel("The player to find the most matching pairs wins!");
      VBox vbox3 = new VBox(descriptionLabel);
      VBox vbox1 = new VBox(resultLabel, turnLabel, matchesLabel);
      checkButton= new Button("Check");   
      checkButton.setOnAction(new checkButtonHandler());
      VBox vbox4 = new VBox(checkButton);
       
      radioButtons[0] = new RadioButton("2 x 2 Board Size");
      radioButtons[1] = new RadioButton("4 x 4 Board Size");
      radioButtons[2] = new RadioButton("6 x 6 Board Size");
   
      radioButtons[0].setSelected(true);
      
      radioGroup = new ToggleGroup();
      for (int i=0;i<3;i++)
         radioButtons[i].setToggleGroup(radioGroup);
           
      
      Button convertButton = new Button("Play");
      convertButton.setOnAction(new MemoryGameRadioButtons());
   
      radioVBoxWt = new VBox(radioButtons[0], radioButtons[1],radioButtons[2]);
      
      RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
      playVBox = new VBox(convertButton);
      
      p1Label= makeLabel("Player 1");  
      p2Label = makeLabel("Player 2");
      
      p1Field= new TextField();  
      p2Field=new TextField();

   
      HBox name1 = new HBox(10, p1Label, p1Field);
      HBox name2 = new HBox( 10, p2Label, p2Field);
      
      VBox vboxPlayers= new VBox(name1, name2);
          
      player1 = new Player(p1);
      player2 = new Player(p2);
      
     // Button nameButton= new Button ("Submit");
      //createButton.setOnAction(new nameButtonHandler());
       
      
      vbox= new VBox(vbox2,vbox3,vboxPlayers,radioVBoxWt,playVBox, gridpane, vbox1, vbox4);
     
      Scene scene = new Scene(vbox, 1200,850);      
      
      stage.setTitle("Memory Game ");
   
   
     
      // add CSS 
      vbox.getStyleClass().add("vbox");
      scene.getStylesheets().add("css.css");
      vbox2.getStyleClass().add("vbox2");
      vbox3.getStyleClass().add("vbox3");
      playVBox.getStyleClass().add("playVBox");
      name1.getStyleClass().add("name1");
      name2.getStyleClass().add("name2");
      vboxPlayers.getStyleClass().add("vboxPlayers");
      playVBox.getStyleClass().add("playVBox");
      radioVBoxWt.getStyleClass().add("radioVBoxWt");
      vbox1.getStyleClass().add("vbox1");
      vbox4.getStyleClass().add("vbox4");
      checkButton.getStyleClass().add("checkButton");

      stage.setScene(scene);   
      stage.show();
   }
   
   
   Label makeLabel(String s){
      Label label= new Label(s);
      //label.setAlignment(Pos.CENTER);
      return(label); 
   }
      
   Button makeButton(int num){
   // just a stub
      return(new Button()); 
   }

   
   public GridPane makeAGrid(){
      
      gridpane.setAlignment(Pos.BOTTOM_CENTER);
      
      buttons = new Button [size][size];
      iViews1 = new ImageView[size][size]; 
      iViews2 = new ImageView[size][size];
      
      int arraySize = ((size*size)/2);
      Image[] imageArray = new Image[arraySize];
      for(int i=0; i<arraySize; i++){
         imageArray[i] = new Image("file:"+i+".jpg");
      }
      
      for(int r=0;r<size;r++){
         for(int c=0; c<size; c++){
            iViews1[r][c]= new ImageView(new Image("file:fruitbasket.jpg"));
            iViews1[r][c].setFitWidth(70); 
            iViews1[r][c].setFitHeight(70);
            
            int index = mg.getSecretBoard(r,c);
            iViews2[r][c] = new ImageView(imageArray[index]);
            iViews2[r][c].setFitWidth(70); 
            iViews2[r][c].setFitHeight(70);             
         }
      }
               
      for (int r=0; r<size; r++){
         for(int c=0; c<size; c++){
            buttons[r][c]= new Button("", iViews1[r][c]);
            buttons[r][c].setOnAction(new ButtonClickHandler());
            gridpane.add(buttons[r][c],c,r);
                                             
         }
      }
   
      return(gridpane);  // the array of buttons 
   }
  
        
   class ButtonClickHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event){ 
      
         int rowClick = -1;
         int colClick = -1;   
         
         for(int r=0;r<size;r++){
            for(int c=0;c<size;c++){
               if(event.getSource().equals(buttons[r][c])){
                  rowClick = r;
                  colClick = c;
                               
               }//if
            }//for
         }//for
         
         if(mg.getTurnCnt()%2 == 0){
            card1Row = rowClick;
            card1Col = colClick;
         }//if
         
         else if(mg.getTurnCnt()%2 == 1){
            card2Row = rowClick;
            card2Col = colClick;
         }//else if
         
         int [] choice = new int[2];
         choice[0] = rowClick;
         choice[1] = colClick;
         
         //sends to memorygame to determine a match
         mg.takeTurn(choice);
         
         
         buttons[rowClick][colClick] = new Button("",iViews2[rowClick][colClick]);
         gridpane.add(buttons[rowClick][colClick],colClick,rowClick);
          
            
      }//handle  
   } //eventhandler
   
      
   class checkButtonHandler implements EventHandler<ActionEvent>{
      
      @Override
      public void handle(ActionEvent event){    
         
         if(!mg.isMatch()){
            
            keepTrack++;
            
            //"flip" the cards back over
            buttons[card1Row][card1Col] = new Button("",iViews1[card1Row][card1Col]);
            buttons[card2Row][card2Col] = new Button("",iViews1[card2Row][card2Col]); 
            
            buttons[card1Row][card1Col].setOnAction(new ButtonClickHandler());
            buttons[card2Row][card2Col].setOnAction(new ButtonClickHandler());           
              
            gridpane.add(buttons[card1Row][card1Col],card1Col,card1Row);
            gridpane.add(buttons[card2Row][card2Col],card2Col,card2Row);
            
            if(keepTrack%2 == 1){ //player 1
               player1.turnsPlusOne();
               turnLabel.setText(player2.getName() + "'s turn");
            }//if
            
            else if(keepTrack%2 == 0){ //player 2
               player2.turnsPlusOne();
               turnLabel.setText(player1.getName() + "'s turn");
            }//else if
            
            resultLabel.setText("Not a match.");
            
         }//if isMatch
            
         else if(mg.isMatch()){
            buttons[card1Row][card1Col].setOnAction(null);
            buttons[card2Row][card2Col].setOnAction(null);
            resultLabel.setText("That's a match.");
            if (keepTrack%2==0){
               player1.matchesPlusOne();
               matchesLabel.setText(player1.getName() + " matches: " + player1.getMatches() + "\n" + player2.getName() + "matches: " + player2.getMatches());
            }//nested if
            else{
               player2.matchesPlusOne();
               matchesLabel.setText(player1.getName() + " matches: " + player1.getMatches() + "\n" + player2.getName() + "matches: " + player2.getMatches());
            }//nested else
            
         }//else if
         
         if(mg.isWinner()){
         
            if(player1.getMatches()>player2.getMatches() || player2.getMatches() == 0){
               resultLabel.setText(player1.getName() + ", you won!");
               
            }//if
            
            else if (player1.getMatches()<player2.getMatches() || player1.getMatches() == 0){
               resultLabel.setText(player2.getName() + ", you won!");
            }
            else{
               resultLabel.setText("You ties.");
            }
            //else
            turnLabel.setText("");
         }//if
         
      }//checkbutton handle
   }//checkbutton 

 
   class MemoryGameRadioButtons implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event){
      
      player1.setName(p1Field.getText());
      player2.setName(p2Field.getText());
         
         if(radioButtons[0].isSelected()){
            size = 2;
         }
         
         else if(radioButtons[1].isSelected()){
            size = 4;
         }
           
         else if(radioButtons[2].isSelected()){
            size = 6;
         }
         vbox.getChildren().removeAll(radioVBoxWt, playVBox);
         mg = new MemoryGame(size);
         makeAGrid();
       
      
      }//radiobuttons handler
   } //memorygameradiobuttons

}//memorygameresponsive