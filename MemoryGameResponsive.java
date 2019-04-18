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
   private Label player1;
   private Label player2;
   MemoryGame mg;  
      
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage stage)
   {  
      mg = new MemoryGame();
      makeAGrid();
          
      resultLabel= makeLabel("");
     
      headingLabel= makeLabel("The Memory Game");
      VBox vbox2 = new VBox(30,headingLabel);
      descriptionLabel=makeLabel("The player to find the most matching pairs wins!");
      VBox vbox3 = new VBox(10,descriptionLabel);
      VBox vbox1 = new VBox(20,resultLabel);
      vbox1.setAlignment(Pos.CENTER);
      vbox2.setAlignment(Pos.CENTER);
      vbox3.setAlignment(Pos.CENTER);
      VBox vbox= new VBox(20,vbox2,vbox3, gridpane, vbox1);
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
            if(r%2==0){ //fix later so it's more randomized??
               iViews2[r][c] = new ImageView(new Image("file:banana.jpg"));
            }//if
            else{
               iViews2[r][c] = new ImageView(new Image("file:apple.png"));
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
         int turnCount = 0; //eventually get this from Game file
         turnCount++;
      
      //turn counts 1 and 2 are flipping cards over; turn counts 3 and 4 flip them back if not a match
      //turn counts 1 and 2 happen      
      //if they match, turn count is reset to 0 & buttons are nulled
      //if they don't match, turn count
         
         int rowClick = -1;
         int colClick = -1;   
         
         for(int r=0;r<2;r++){
            for(int c=0;c<2;c++){
               if(event.getSource().equals(buttons[r][c])){
                  rowClick = r;
                  colClick = c;             
               }//if
            }//for
         }//for
         
         if(turnCount <=2){
            buttons[rowClick][colClick] = new Button("",iViews2[rowClick][colClick]);
            gridpane.add(buttons[rowClick][colClick],colClick,rowClick);
            
            //here check to see if cards match
            //if(turnCount == 2 && cards do not match)
            //resultLabel.setText("click each card again to flip them over.")
            //else if(turnCount == 2 && cards do match)
            //reset turnCount to 0 (game.setTurnCount(0)??)
            //null the buttons that match
            
         }//if
         
         else if(turnCount == 3 || turnCount == 4){
         //how to click same button twice??
         buttons[rowClick][colClick] = new Button("", iViews1[rowClick][colClick]);
         gridpane.add(buttons[rowClick][colClick],colClick,rowClick);
         if(turnCount==4){ //later get rid of this/make it a lot nicer (:
         turnCount=0;
         }//if
         }//else if
               
      }//handle  
   } //eventhandler
}//memorygameresponsive