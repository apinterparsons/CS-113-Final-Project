import java.util.*;
class MemoryGameDriver{
   public static void main(String argv[]){
      Scanner sc = new Scanner(System.in);
   
   
      MemoryGame mg= new MemoryGame();
      mg.setUp();

      
      
      System.out.println("For your first card; what row do you want?");
      int row = sc.nextInt();
      System.out.println("For your first card; what column do you want?");
      int col = sc.nextInt();
      
      int[] choice_one = {row, col};
          
         
      if(mg.isValidInput(choice_one)==true){
         mg.takeTurn(choice_one);
         
         System.out.println("For your second card; what row do you want?");
         int row_2 = sc.nextInt();
         System.out.println("For your second card; what column do you want?");
         int col_2 = sc.nextInt();
          
         int[] choice_two = {row_2, col_2};
         
         if(mg.isValidInput(choice_two)==true){
           
            mg.takeTurn(choice_two);
            mg.isWinner();
            System.out.println (mg.isWinner());
            //System.out.println(mg.getSym());
         }
         else{ 
            System.out.println("wrong input");} 
      }
   }
   
}
 
         
         
       
         
         
         

