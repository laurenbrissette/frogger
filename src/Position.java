package src;

// represents an x,y coordinate in tile squares 
public class Position {
  final int x;
  final int y;
  
  Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  // determines whether this Position is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Position)) {
      throw new IllegalArgumentException();
    }
    Position other = (Position)o;
    return this.x == other.x && this.y == other.y;
  }
}
