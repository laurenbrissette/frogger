package src;
import java.util.ArrayList;

// represents a River row which characteristically contains logs floating across it 
// either right to left or vice versa
public class LogRiver extends River {
  private final ArrayList<Log> logs;
  private final int speed; // in range [1,3], represents the number of ticks between movement


  LogRiver(ArrayList<Log> logs, int speed) {
    if(speed < 1) {
      throw new IllegalArgumentException("log speed must be a positive integer");
    }
    this.logs = logs;
    this.speed = speed;
  }

  LogRiver(ArrayList<Log> logs) {
    this(logs, 1);
  }

  public void moveOnTick(int count) {  
    if(count % speed == 0) {
      for(Log l : this.logs) {
        l.step();
      }
    }
    else {
      return;
    }
  }

  // determines whether the frog can move to this x value on this LogRiver without 
  // losing the game
  public boolean stableGround(int xVal) {
    for(Log l : this.logs) {
      if(l.overlaps(xVal)) {
        return true;
      }
    }
    return false;
  }
}
