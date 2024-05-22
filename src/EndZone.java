package src;

// represents the horizontal strip the player needs to navigate the 
// frog to in order to win 
public class EndZone implements Row {
  public void moveOnTick(int count) {
    return;
  }

  // determines whether the frog can move to this x value on this Row without 
  // losing the game
  public boolean stableGround(int xVal) {
    return true;
  }

}
