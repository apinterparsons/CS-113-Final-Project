import java.util.Random;

class MemoryGame implements Game{
   private int[][] board;
   private int[][] secretBoard;
   private int turnCnt = 0; 
   private int[] choices = new int[4];
   private int boardSize;
   
   public MemoryGame(int size){
      boardSize = size;
      setUp();
   }
   
   public void setUp(){
      
      board = new int[boardSize][boardSize];
      secretBoard = new int[boardSize][boardSize];
      
      //make the symbols
      int[] symbols = new int[boardSize*boardSize];
      for(int i = 0; i<(boardSize*boardSize); i++){
         symbols[i] = i/2;
         System.out.println(symbols[i]);
      }
      shuffle(symbols);
      
      int i = 0;
      for (int r=0; r<boardSize; r++){
         for (int c=0; c<boardSize; c++){
            board[r][c] = 0;
            secretBoard[r][c] = symbols[i];
            i++;
          
         }//nested for
      }//for
      
      printPretty();  
   } 
   
   public void shuffle(int[] array){
      int length = array.length;
      Random rnd = new Random();
      
      for (int i =0; i<length; i++){
         int randomIndex = i+rnd.nextInt(length-i);
         int randomElement = array[randomIndex];
         array[randomIndex] = array[i];
         array[i] = randomElement;
      }
   }
   
   public void takeTurn(int [] choice){
      //if it's their first card
      if (turnCnt%2==0){
         choices[0] = choice[0];
         choices[1] = choice[1];
         
         board[choices[0]][choices[1]] = secretBoard[choices[0]][choices[1]];
      
         turnCnt++;
      }//if 
      //second card
      else{
         choices[2] = choice[0];
         choices[3] = choice[1];
         
         //if they match 
         if(isMatch()){
            board[choices[2]][choices[3]] = secretBoard[choices[2]][choices[3]];
            
            System.out.println("It's a match");
          
            printPretty();  
            turnCnt++;
         }
         
         //if they don't match
         else{
            board[choices[2]][choices[3]] = secretBoard[choices[2]][choices[3]];
            printPretty();
          
            System.out.println("Not a match");
          
          //reset flipped cards
            board[choices[0]][choices[1]] = '#';
            board[choices[2]][choices[3]] = '#';
          
            turnCnt++;
            printPretty();  
         }//nested else
      }//else
   }
   
   public boolean isMatch(){
      if(secretBoard[choices[0]][choices[1]] == secretBoard[choices[2]][choices[3]]){
         return(true);
      }//if
      else{
         return(false);
      }//else
   }
   
   public boolean isWinner(){
      boolean winner = true;
      
      for (int r=0; r<boardSize; r++){
         for (int c=0; c<boardSize; c++){
            if (board[r][c] != secretBoard[r][c]){
               winner = false; 
            }
         }//nested for
      }//for
      
      return(winner);
   } 
   public int getTurnCnt(){
      return(turnCnt);
   }
   
   public String gameOverStatus(){
      if (isWinner()){
         return("Winner!");
      }
      else if (turnCnt<5){
         return("In progress");
      }
      else{
         return("Loser");
      }
   }
   
   public boolean isValidInput(int [] x){
      if(x[0]>=0 && x[0]<boardSize){
         if(x[1]>=0 && x[0]<boardSize){
            return(true);
         }//nested if
         else{
            return(false);
         }
      }//if
         return(false);
      }
   
   public void printPretty(){
      for (int r=0; r<boardSize; r++){
         System.out.println();
         for (int c=0; c<boardSize; c++){
            System.out.print(secretBoard[r][c]); 
         }//nested for
      }//for
      System.out.println();
   }
   
   public void setBoardSize(int size){
      boardSize = size;
   }
   
   public int getBoardSize(){
      return(boardSize);
   }
   
   public int getSecretBoard(int r, int c){
      return(secretBoard[r][c]);
   }
   

   

}// MemoryGame class