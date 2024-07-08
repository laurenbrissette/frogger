package src;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// represents a street the frog must cross with moving car obstacles 
public class Road implements Row {
  private final int speed;
  private ArrayList<Vehicle> vehicles;
  Road(int speed, ArrayList<Vehicle> vehicles) {
    if(speed < 1) {
      throw new IllegalArgumentException("Road must have a positive integer speed");
    }
    // confirms that every Vehicle in the given list is moving in the same direction 
    // before constructing a Road
    for(int x = 1; x < vehicles.size() && !vehicles.isEmpty(); x += 1) {
      if(!vehicles.get(x).sameDirection(vehicles.get(0))) {
        throw new IllegalArgumentException("Cannot construct Road when Vehicle contents " 
          + "are not all moving in the same direction");
      }
    }
    this.speed = speed;
    this.vehicles = vehicles;
  }

  Road(int speed) {
    this(speed, new ArrayList<Vehicle>());
  }

  Road() {
    this(1);
  }

  @Override
  // determines whether this Road is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Road)) {
      throw new IllegalArgumentException();
    }
    Road other = (Road)o;
    return this.speed == other.speed 
      && new Utils().equalsWithoutOrder(this.vehicles, other.vehicles);

  }

  // moves all of the elements in the Road the necessary distance per tick 
  public void moveOnTick(int count, int rowLength) {
     if(count % speed == 0) {
      for(int x = 0; x < this.vehicles.size(); x += 1) {
        Vehicle operator = this.vehicles.get(x);
        operator.step(rowLength);
        // if log now off screen, remove it and generate a new one
        if(operator.size == 0) {
          this.vehicles.remove(x); 
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
          this.vehicles.add(new Vehicle(xVal, size, movesRight));
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
    for(Vehicle v : this.vehicles) {
      if(v.overlaps(xVal)) {
        return false;
      }
    }
    return true;
  }

  // determines whether this Road is valid. A Row is valid when 
   // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
   // (b) the last and only the last Row is an EndZone
  public boolean validRow(int width, int thisY, int finalY) {
    // LogRiver cannot be final Row
    if(thisY == finalY) {
      return false;
    }
    // confirm all logs are vaild 
    for(Vehicle l : this.vehicles) {
      if(!l.within(width)) {
        return false;
      }
    }
    return true;
  }

  // renders this Road
  public JPanel render(int width, int tilesize) {
    JPanel result = new JPanel();
    result.setBackground(new Color(125, 130, 126));
    result.setSize(width * tilesize, tilesize);
    for(Vehicle l : this.vehicles) {
      JPanel item = new JPanel();
      item.setBackground(Color.RED);
      item.setBounds(l.xVal * tilesize, 0, tilesize * l.size, tilesize);
      result.add(item, JLayeredPane.PALETTE_LAYER);
    }
    result.setLayout(null);
    return result;
  }

   // determines the distance/direction moved at x location which a frog would be 
   // carried (nonzero only applicable for log rivers)
   public int amountCarriedAt(int count, int x) {
    return 0;
  }
}
