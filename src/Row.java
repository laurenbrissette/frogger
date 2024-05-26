package src;

// represents a horizontal strip/row in the game for the frog to 
// step on 
public interface Row {
  /* BRAINSTORMING: 
   * - render 
   * - moveOnTick -> moves cars and logs 
   * - check whether the frog can move to a particular x value on that row 
   */

   @Override
   // determines whether this Row is the same as Object o
   public boolean equals(Object o);

   // moves all of the elements in the Row the necessary distance per tick 
   public void moveOnTick(int count, int rowLength);

   // determines whether the frog can move to this x value on this Row without 
   // losing the game
   public boolean stableGround(int xVal);

   // determines whether this Row is valid. A Row is valid when 
   // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
   // (b) the last and only the last Row is an EndZone
   public boolean validRow(int width, int thisY, int finalY);
}