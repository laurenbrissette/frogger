package src;

// represents a horizontal strip/row in the game for the frog to 
// step on 
public interface Row {
  /* BRAINSTORMING: 
   * - render 
   * - moveOnTick -> moves cars and logs 
   * - check whether the frog can move to a particular x value on that row 
   */

   // moves all of the elements in the Row the necessary distance per tick 
   public void moveOnTick(int count);

   // determines whether the frog can move to this x value on this Row without 
   // losing the game
   public boolean stableGround(int xVal);
}