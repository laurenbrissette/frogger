package src;

// represents a moving log in the river 
public class Log extends Mover{
  Log(int xVal, int size, boolean movesRight) {
    super(xVal, size, movesRight);
  }

  @Override 
  // determines whether this Log is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Log)) {
      throw new IllegalArgumentException();
    }
    Log other = (Log)o;
    return this.xVal == other.xVal 
      && this.size == other.size 
      && this.movesRight == other.movesRight;
  }
}
