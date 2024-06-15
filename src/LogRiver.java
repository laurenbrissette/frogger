package src;
import java.awt.Color;
import java.util.ArrayList;

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
      for(Log l : this.logs) {
        l.step(rowLength);
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

  public JPanel render(int width, int tilesize) {
    JPanel result = new JPanel();
    result.setBackground(new Color(179, 229, 252));
    result.setSize(width * tilesize, tilesize);
    for(Log l : this.logs) {
      JPanel item = new JPanel();
      item.setBackground(new Color(121, 85, 72));
      item.setBounds(l.xVal * tilesize, 0, tilesize * l.size, tilesize);
      result.add(item, JLayeredPane.PALETTE_LAYER);
    }
    result.setLayout(null);
    return result;
  }
}
