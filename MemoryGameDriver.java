import java.util.*;
class MemoryGameDriver{
   public static void main(String argv[]){
      Scanner sc = new Scanner(System.in);
   
   
      System.out.println("What size board do you want? 2,4, or 6?");
      int s=sc.nextInt();
      
      MemoryGame mem= new MemoryGame(s);
     
      //mem.printPrettysecretBoard();
      while (mem.isWinner()==false){ 
      
         System.out.println("For your first card; what row do you want?");
         int row = sc.nextInt();
         System.out.println("For your first card; what column do you want?");
         int col = sc.nextInt();
      
         int[] choice_one = {row, col};
          
         
         if(mem.isValidInput(choice_one)==true){
            mem.takeTurn(choice_one);
            mem.printPretty();
            System.out.println("For your second card; what row do you want?");
            int row_2 = sc.nextInt();
            System.out.println("For your second card; what column do you want?");
            int col_2 = sc.nextInt();
          
            int[] choice_two = {row_2, col_2};
         
            if(mem.isValidInput(choice_two)==true){
            
               mem.takeTurn(choice_two);
            //mem.printPretty();
               mem.isWinner();
               //System.out.println (mem.isWinner());
            //System.out.println(mem.getSym());
            }
            else{ 
               System.out.println("wrong input");} 
         }
         else{
            System.out.println("wrong input");} 
      }
   }
   
}
 
         
         
       
         
         
         

