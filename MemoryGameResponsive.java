import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color; 
import javafx.scene.layout.*;
import javafx.scene.control.Label;
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

/**
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
   Button checkButton;
   private Label player1;
   private Label player2;
   MemoryGame mg;  
    
   int card1Row;
   int card2Row;
   int card1Col;
   int card2Col;
   int size=0;
   
   int p1count = 0;
   int p2count = 0;
   int keepTrack = 0;
   int p1matches = 0;
   int p2matches = 0;
   
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
      mg = new MemoryGame();
      mg.setUp();
      makeAGrid();
          
      resultLabel= makeLabel("");
      turnLabel = makeLabel("");
     
      headingLabel= makeLabel("The Memory Game");
      VBox vbox2 = new VBox(headingLabel);
      descriptionLabel=makeLabel("The player to find the most matching pairs wins!");
      VBox vbox3 = new VBox(descriptionLabel);
      VBox vbox1 = new VBox(resultLabel, turnLabel);
      checkButton= new Button("Check");   
      checkButton.setOnAction(new checkButtonHandler());
   
      VBox vbox4 = new VBox(checkButton);
       
      radioButtons[0] = new RadioButton("2 x 2 Board Size");
      radioButtons[1] = new RadioButton("4 x 4 Board Size");
      radioButtons[2] = new RadioButton("6 x 6 Board Size");
      
     // Select the milesButton control.
      radioButtons[0].setSelected(true);
      
      // Add the RadioButton controls to a ToggleGroup.
      //ToggleGroup 
      radioGroup = new ToggleGroup();
      for (int i=0;i<3;i++)
         radioButtons[i].setToggleGroup(radioGroup);
         
      
      Button convertButton = new Button("Play");
      convertButton.setOnAction(new MemoryGameRadioButtons());
   
      VBox radioVBoxWt = new VBox(radioButtons[0], radioButtons[1],radioButtons[2]);
      
      RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
      VBox playVBox = new VBox(convertButton);
   
      vbox1.setAlignment(Pos.CENTER);
      vbox2.setAlignment(Pos.CENTER);
      vbox3.setAlignment(Pos.CENTER);
      vbox4.setAlignment(Pos.CENTER);
      radioVBoxWt.setAlignment(Pos.CENTER);
      playVBox.setAlignment(Pos.CENTER);
      
      VBox vbox= new VBox(vbox2,vbox3, radioVBoxWt,playVBox, gridpane, vbox1, vbox4);
      
      //vbox.setPadding(new Insets(10));
      Scene scene = new Scene(vbox, 1500,900);      
      // Set the stage title.
      stage.setTitle("Memory Game ");
      
    
      // Show the window.
      //scene.getStylesheets().add("css1.css");
   
      stage.setScene(scene);   
      stage.show();
   }
   
   Label makeLabel(String s){
      Label label= new Label(s);
      label.setFont(new Font("Helvetica", 20));
      label.setAlignment(Pos.CENTER);
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
      
      int x=0;
      for(int r=0;r<size;r++){
         for(int c=0; c<size; c++){
            x++;
            iViews1[r][c]= new ImageView(new Image("file:fruitbasket.jpg"));
            iViews1[r][c].setFitWidth(100); 
            iViews1[r][c].setFitHeight(100);
            
            iViews2[r][c]= new ImageView(new Image("file:"+x+".jpg"));
            iViews2[r][c].setFitWidth(100); 
            iViews2[r][c].setFitHeight(100);           
            
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
         
            buttons[card1Row][card1Col] = new Button("",iViews1[card1Row][card1Col]);
            buttons[card2Row][card2Col] = new Button("",iViews1[card2Row][card2Col]); 
            
            buttons[card1Row][card1Col].setOnAction(new ButtonClickHandler());
            buttons[card2Row][card2Col].setOnAction(new ButtonClickHandler());           
              
            gridpane.add(buttons[card1Row][card1Col],card1Col,card1Row);
            gridpane.add(buttons[card2Row][card2Col],card2Col,card2Row);
            
            if(keepTrack%2 == 1){ //player 1
               p1count++;
               turnLabel.setText("Player 1, you have taken " + p1count + " turn(s). \nPlayer 2, it's your turn.");
            }//if
            
            else if(keepTrack%2 == 0){ //player 2
               p2count++;
               turnLabel.setText("Player 2, you have taken " + p2count + " turn(s). \nPlayer 1, it's your turn.");
            }//else if
            
            resultLabel.setText("Not a match." + keepTrack);
            
         }//if isMatch
            
         else if(mg.isMatch()){
            buttons[card1Row][card1Col].setOnAction(null);
            buttons[card2Row][card2Col].setOnAction(null);
            resultLabel.setText("That's a match.");
            
         }//else if
         
         if(mg.isWinner()){
         
            if(p1count>p2count || p2count == 0){
               resultLabel.setText("Player 2, you won!");
               p2matches++;
            }//if
            
            else{
               resultLabel.setText("Player 1, you won!");
               p1matches++;
            }//else
            turnLabel.setText("");
         }//if
         
      }//checkbutton handle
   }//checkbutton 

 
   class MemoryGameRadioButtons implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event){
         
         if(radioButtons[0].isSelected()){
            size = 2;
         }
         
         else if(radioButtons[1].isSelected()){
            size = 4;
         }
           
         else if(radioButtons[2].isSelected()){
            size = 6;
         }
         makeAGrid();
      
      }//radiobuttons handler
   } //memorygameradiobuttons

}//memorygameresponsive