class MemoryGame implements Game{
   private char[][] board;;
   private char[] symbols = {'*', '$'};
   private char[][] secretBoard = new char[2][2];
   private int turnCnt = 0;
   private int[] choices = new int[4];
   private int boardSize;
   
   public void setUp(){
      boardSize = 2;
      board = new char[boardSize][boardSize];
      
      for (int r=0; r<boardSize; r++){
         for (int c=0; c<boardSize; c++){
            board[r][c] = '#';
         }//nested for
      }//for
      
      for (int r=0; r<boardSize; r++){
         for (int c=0; c<boardSize; c++){
            secretBoard[r][c] = symbols[c%2];
         }//nested for
      }//for
      
      printPretty();  
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
         if(secretBoard[choices[0]][choices[1]] == secretBoard[choices[2]][choices[3]]){
            board[choices[2]][choices[3]] = secretBoard[choices[2]][choices[3]];
          
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
      if(x[0]>0 && x[0]<boardSize){
         if(x[1]>0 && x[0]<boardSize){
            return(true);
         }//nested if
         else{
            return(false);
         }
      }//if
         return(false);
      }
   
   public void printPretty(){
      for (int r=0; r<2; r++){
         System.out.println();
         for (int c=0; c<2; c++){
            System.out.print(board[r][c]); 
         }//nested for
      }//for
      System.out.println();
   }
   

}// MemoryGame class