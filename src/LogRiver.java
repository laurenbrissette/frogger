package src;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// represents a River row which characteristically contains logs floating across it 
// either right to left or vice versa
public class LogRiver extends River {
  private final ArrayList<Log> logs;
  private final int speed; // in range [1,3], represents the number of ticks between movement

  LogRiver(ArrayList<Log> logs, int speed) {
    if(speed < 1) {
      throw new IllegalArgumentException("log speed must be a positive integer");
    }
    // confirms that every Log in the given list is moving in the same direction 
    // before constructing a LogRiver s
    for(int x = 1; x < logs.size() && !logs.isEmpty(); x += 1) {
      if(!logs.get(x).sameDirection(logs.get(0))) {
        throw new IllegalArgumentException("Cannot construct LogRiver when Log contents " 
          + "are not all moving in the same direction");
      }
    }
    this.logs = logs;
    this.speed = speed;
  }

  LogRiver(ArrayList<Log> logs) {
    this(logs, 1);
  }

  @Override 
  // determines whether this LogRiver is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof LogRiver)) {
      throw new IllegalArgumentException();
    }
    LogRiver other = (LogRiver)o;
    return new Utils().equalsWithoutOrder(this.logs, other.logs) 
      && this.speed == other.speed;
  }

  // moves all of the elements in this LogRiver the necessary distance per tick 
  public void moveOnTick(int count, int rowLength) {  
    if(count % speed == 0) {
      for(int x = 0; x < this.logs.size(); x += 1) {
        Log operator = this.logs.get(x);
        operator.step(rowLength);
        // if log now off screen, remove it and generate a new one
        if(operator.size == 0) {
          this.logs.remove(x); 
          x = x - 1;
        }
        if(operator.size == 0) {
          int xVal;
          boolean movesRight;
          int lowerBound = 1;
          int upperBound = rowLength / 4;
          int size = new Random().nextInt(lowerBound + upperBound) + lowerBound;
          if(operator.movesRight) {
            movesRight = true;
            xVal = -1;
          }
          else {
            movesRight = false;
            xVal = rowLength - 1;
          }
          this.logs.add(new Log(xVal, size, movesRight));
        }
        
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

  // determines whether this LogRiver is valid. A Row is valid when 
   // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
   // (b) the last and only the last Row is an EndZone
  public boolean validRow(int width, int thisY, int finalY) {
    // LogRiver cannot be final Row
    if(thisY == finalY) {
      return false;
    }
    // you should always be able to cross the river
    if(this.logs.size() == 0) {
      return false;
    }
    // confirm all logs are vaild 
    for(Log l : this.logs) {
      if(!l.within(width)) {
        return false;
      }
    }
    return true;
  }

  // renders this LogRiver as a JPanel
  public JPanel render(int width, int tilesize) {
    JPanel result = new JPanel();
    result.setBackground(new Color(179, 229, 252));
    result.setSize(width * tilesize, tilesize);
    for(Log l : this.logs) {
      JPanel item = new JPanel();
      item.setBackground(new Color(65, 44, 40));
      item.setBounds(l.xVal * tilesize, 0, tilesize * l.size, tilesize);
      result.add(item, JLayeredPane.PALETTE_LAYER);
    }
    result.setLayout(null);
    return result;
  }

  // determines the distance/direction moved at x location which a frog would be 
  // carried (nonzero only applicable for log rivers)
  public int amountCarriedAt(int count, int x) {
    if(this.stableGround(x) && count % this.speed == 0) {
      if(this.logs.get(0).movesRight) {
        return 1;
      }
      else {
        return -1;
      }
    }
    return 0;
  }

}
