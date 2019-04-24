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
   int size;
      
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
      VBox vbox2 = new VBox(30,headingLabel);
      descriptionLabel=makeLabel("The player to find the most matching pairs wins!");
      VBox vbox3 = new VBox(10,descriptionLabel);
      VBox vbox1 = new VBox(20,resultLabel, turnLabel);
      checkButton= new Button("Check");   
      checkButton.setOnAction(new checkButtonHandler());
   
      VBox vbox4 = new VBox(checkButton);
      
   
      vbox1.setAlignment(Pos.CENTER);
      vbox2.setAlignment(Pos.CENTER);
      vbox3.setAlignment(Pos.CENTER);
      vbox4.setAlignment(Pos.CENTER);
      
      
      VBox vbox= new VBox(20,vbox2,vbox3, gridpane, vbox1, vbox4);
      
      vbox.setPadding(new Insets(50));
      Scene scene = new Scene(vbox, 1000,800);      
      // Set the stage title.
      stage.setTitle("Memory Game ");
      
    
      // Show the window.
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
      
      buttons = new Button [2][2];
      iViews1 = new ImageView[2][2]; 
      
      iViews2 = new ImageView[2][2];
      
      for(int r=0;r<2;r++){
         for(int c=0; c<2; c++){
            iViews1[r][c]= new ImageView(new Image("file:fruitbasket.jpg"));
            iViews1[r][c].setFitWidth(200); 
            iViews1[r][c].setFitHeight(200);
            
            
            //arbitrarily adding so i can implement more stuff in handler
            if(c==0){ 
               iViews2[r][c] = new ImageView(new Image("file:apple.jpeg"));
            }//if
            else{
               iViews2[r][c] = new ImageView(new Image("file:carrot.png"));
            }//else
            
            iViews2[r][c].setFitWidth(200);
            iViews2[r][c].setFitHeight(200);
            //
            
         }
      }
               
      for (int r=0; r<2; r++){
         for(int c=0; c<2; c++){
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
            buttons[card1Row][card1Col] = new Button("",iViews1[card1Row][card1Col]);
            buttons[card2Row][card2Col] = new Button("",iViews1[card2Row][card2Col]); 
            
            buttons[card1Row][card1Col].setOnAction(new ButtonClickHandler());
            buttons[card2Row][card2Col].setOnAction(new ButtonClickHandler());           
              
            gridpane.add(buttons[card1Row][card1Col],card1Col,card1Row);
            gridpane.add(buttons[card2Row][card2Col],card2Col,card2Row);
            
            resultLabel.setText("Not a match."); 
         }//if
            
         else if(mg.isMatch()){
            buttons[card1Row][card1Col].setOnAction(null);
            buttons[card2Row][card2Col].setOnAction(null);
            resultLabel.setText("That's a match.");
            
         }//else if
         
         if(mg.isWinner()){
            resultLabel.setText("You won!");
            turnLabel.setText("");
         }//if
         
         else{
            turnLabel.setText("You have taken " + mg.getTurnCnt()/2 + " turn(s).");
         }//else
         
      
      }//checkbutton handle
   }//checkbutton 
     
      /* 
      this is to activate the radio buttons for sizing the board
      we will need to create an attribute of the size to implement in the other handler
      the attribute will equal the number of rows/columns
      
      to get to this handler, have two radio buttons & a select button that is set on action to come here (an hbox maybe??)
      */

  class MemoryGameRadioButtons implements EventHandler<ActionEvent>
 {
      @Override
      public void handle(ActionEvent event){
         
         if (firstBoard.isSelected())
            size = 2;
         
         if (secondBoard.isSelected())
            size = 4;
            
            //from here, this will go back to the GUI that'll make the board the correct size
            //so iViews1 and 2 will then utilize the value of size determined here 
      
      }//radiobuttons handler
   } //memorygameradiobuttons

}//memorygameresponsive