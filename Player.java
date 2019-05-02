class Player{
   private String name;
   private int matches;
   private int turns;
   
   public Player(String n){
     name = n;
     matches = 0;
     turns = 0;
   }
   
   public Player(){
     name = "Bob";
     matches = 0;
     turns = 0; 
   }
   
   public String getName(){
      return(name);
   }
   
   public void setName(String n){
      name = n;
   }
   
   public int getMatches(){
      return(matches);
   }
   
   public void setMatches(int number){
      matches = number;
   }
   
   public void matchesPlusOne(){
      int newMatches = matches +1;
      matches = newMatches;
   }
   
   public int getTurns(){
      return(turns);
   }
   
   public void setTurns(int t){
      turns = t;
   }
   
   public void turnsPlusOne(){
      int newTurns = turns +1;
      turns = newTurns;
   }
}//class