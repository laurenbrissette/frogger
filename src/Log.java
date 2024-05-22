package src;

// represents a moving log in the river 
public class Log extends Mover{
  Log(int xVal, int size, boolean movesRight) {
    super(xVal, size, movesRight);
  }

  // move on tick --> move one to the left 
  // could affect size/display as a log is moving off screen 
}
