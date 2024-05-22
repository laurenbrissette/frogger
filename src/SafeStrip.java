package src;

// represents a horizontal strip that the frog is safe to step on 
// the width of the gameboard without any threat 
public class SafeStrip implements Row {
  public void moveOnTick(int count) {
    return;
  }

  // determines whether the frog can move to this x value on this LogRiver without 
  // losing the game
  public boolean stableGround(int xVal) {
    return true;
  }
}
