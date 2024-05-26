package src;

// represents the horizontal strip the player needs to navigate the 
// frog to in order to win 
public class EndZone implements Row {

  @Override
  // determines whether this EndZone is the same as Object O
  public boolean equals(Object o) {
    if(!(o instanceof EndZone)) {
      throw new IllegalArgumentException();
    }
    return true;
  }

  // moves all of the elements in this EndZone the necessary distance per tick 
  public void moveOnTick(int count, int rowLength) {
    return;
  }

  // determines whether the frog can move to this x value on this Row without 
  // losing the game
  public boolean stableGround(int xVal) {
    return true;
  }

  // determines whether this EndZone is valid. A Row is valid when 
  // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
  // (b) the last and only the last Row is an EndZone
  public boolean validRow(int width, int thisY, int finalY) {
    if(thisY != finalY) {
      return false;
    }
    else {
      return true;
    }
  }
}
