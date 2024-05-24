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
    this.speed = speed;
    this.vehicles = vehicles;
  }

  Road(int speed) {
    this(speed, new ArrayList<Vehicle>());
  }

  Road() {
    this(1);
  }

  public void moveOnTick(int count) {
    if(count % this.speed == 0) {
      for(Vehicle v : this.vehicles) {
        v.step();
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
}
