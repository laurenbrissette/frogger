package src;

import java.util.ArrayList;

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
    if(count % this.speed == 0) {
      for(Vehicle v : this.vehicles) {
        v.step(rowLength);
      }
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
    // check not final Row
    if(thisY == finalY) {
      throw new IllegalArgumentException("Road cannot be final Row");
    }
    // note, road may have no vehicles
    // confirm all vehicle locations are valid 
    for(Vehicle v : this.vehicles) {
      if(!v.within(width)) {
        return false;
      }
    }
    return true;
  }
}
