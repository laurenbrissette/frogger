package src;

// represents a game board element that moves horizontally across the screen in relation to the tick 
abstract public class Mover {
  protected int xVal; // represents the distance in blocks from the left of the screen 
  protected int size; // represents the length of the log (decreases as moving off screen)
  protected boolean movesRight; // true if the object moves left to right, else opposite direction 

  Mover(int xVal, int size, boolean movesRight) {
    if(size < 1) {
      throw new IllegalArgumentException("Mover size must be a positive integer");
    }
    this.xVal = xVal;
    this.size = size;
    this.movesRight = movesRight;
  }

  // moves the mover one square in its alotted direction, allowing it to move off screen  
  public void step(int rowLength) {
    if(this.movesOffScreen(rowLength)) {
      if(this.size == 1) {
        this.size = 0;
      }
      else if(this.movesRight) { // right move, off screen partially 
        this.xVal = this.xVal + 1;
        this.size = this.size - 1;
      }
      else { // left move, off screen partially 
        this.size = this.size - 1;
      }
    }
    else {
      if(this.movesRight) { // on screen, right move
        this.xVal = this.xVal + 1;
      }
      else { // on screen, left move
        this.xVal = this.xVal - 1;
      }
    }
  }

  // determines whether moving this one block (either right or left depending on its direction)
  // will bring the mover off the screen.  
  public boolean movesOffScreen(int rowLength) {
    if(movesRight) {
      if(this.xVal + this.size == rowLength) { // if going 1 block off screen on right 
        return true;
      }
    }
    else { // moves left 
      if(this.xVal == 0) {
        return true;
      }
    }
    return false;
  }

  // determines whether this Mover overlaps with the given x coordinate in its Row
  // semi open interval 
  // input x should always be a non negative integer
  public boolean overlaps(int x) {
    if(x < 0) {
      throw new IllegalArgumentException("x position of log should not be negative");
    }
    if(this.xVal <= x && x < this.xVal + this.size) {
      return true;
    }
    else {
      return false;
    }
  }

  // determines whether all of this log is within the semi open interval from 0 to endpoint
  public boolean within(int endpoint) {
    return 0 <= this.xVal && this.xVal + size <= endpoint;
  }

  // determines whether this log moves in the same direction as the given boolean
  public boolean sameDirection(Mover other) {
    return this.movesRight == other.movesRight;
  }
}
