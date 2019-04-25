import java.util.*;
class MemoryGameDriver{
   public static void main(String argv[]){
      Scanner sc = new Scanner(System.in);
   
   
      MemoryGame mem= new MemoryGame();
      mem.setUp();

      
      
      System.out.println("For your first card; what row do you want?");
      int row = sc.nextInt();
      System.out.println("For your first card; what column do you want?");
      int col = sc.nextInt();
      
      int[] choice_one = {row, col};
          
         
      if(mem.isValidInput(choice_one)==true){
         mem.takeTurn(choice_one);
         
         System.out.println("For your second card; what row do you want?");
         int row_2 = sc.nextInt();
         System.out.println("For your second card; what column do you want?");
         int col_2 = sc.nextInt();
          
         int[] choice_two = {row_2, col_2};
         
         if(mem.isValidInput(choice_two)==true){
           
            mem.takeTurn(choice_two);
            mem.isWinner();
            System.out.println (mem.isWinner());
            //System.out.println(mem.getSym());
         }
         else{ 
            System.out.println("wrong input");} 
      }
   }
   
}
 
         
         
       
         
         
         

