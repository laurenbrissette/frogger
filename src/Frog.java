package src;

// represents the game piece (frog) the player navigates through the game  
public class Frog {
  private final Position position; // represents the position of the frog as an x,y coordinate
  Frog(Position position) {
    this.position = position;
  }

  @Override
  // determines whether this Frog is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Frog)) {
      throw new IllegalArgumentException();
    }
    Frog other = (Frog)o;
    return this.position.equals(other.position);
  }
}
