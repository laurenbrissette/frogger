package src;

// represents a game board element that moves horizontally across the screen in relation to the tick 
abstract public class Mover {
  private int xVal; // represents the distance in blocks from the left of the screen 
  private final int size; // represents the length of the log
  private boolean movesRight; // true if the object moves left to right, else opposite direction 

  Mover(int xVal, int size, boolean movesRight) {
    if(size < 1) {
      throw new IllegalArgumentException("Mover size must be a positive integer");
    }
    this.xVal = xVal;
    this.size = size;
    this.movesRight = movesRight;
  }

  // moves the mover one square in its alotted direction, allowing it to move off screen  
  public void step() {
    if(movesRight) {

    }
    else { // moves left 
      // what are we going to do if one of these things moves off of the screen?
      // how are new things generated?  is there a pattern?
      if(this.xVal == 0) {

      }
    }
    return;
  }

  // determines whether this Mover overlaps with the given x coordinate in its Row
  // semi open interval 
  // input x should always be a non negative integer
  public boolean overlaps(int x) {
    if(x < 0) {
      throw new IllegalArgumentException("x position should not be negative");
    }
    if(this.xVal <= x && x < this.xVal + this.size) {
      return true;
    }
    else {
      return false;
    }
  }
}
