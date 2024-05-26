package src;

// represents a stable lilypad in the river.  Lilies are a square of tile size 1.
public class Lily {
  private final int xVal; // represents the distance in blocks from the left of the screen 
  Lily(int xVal) {
    this.xVal = xVal;
  }

  @Override
  // determines whether this Lily is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Lily)) {
      throw new IllegalArgumentException();
    }
    Lily other = (Lily)o;
    return this.xVal == other.xVal;
  }

  // determines whether this Lily overlaps with the given x position  
  public boolean overlaps(int x) {
    return x == this.xVal;
  }

  // determines whether the x position of this Lily falls within the range [0, endpoint)
  // semi open interval
  public boolean within(int endpoint) {
    return 0 <= this.xVal && this.xVal < endpoint;
  }
}
