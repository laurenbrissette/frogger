package src;

// represents a stable lilypad in the river.  Lilies are a square of tile size 1.
public class Lily {
  private final int xVal; // represents the distance in blocks from the left of the screen 
  Lily(int xVal) {
    this.xVal = xVal;
  }

  // determines whether this Lily overlaps with the given x position  
  public boolean overlaps(int x) {
    return x == this.xVal;
  }
}
