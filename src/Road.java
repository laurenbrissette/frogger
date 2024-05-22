package src;

import java.util.ArrayList;

// represents a street the frog must cross with moving car obstacles 
public class Road implements Row {
  private final int speed;
  private ArrayList<Vehicle> vehicles;
  Road(int speed) {
    this.speed = speed;
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
        return true;
      }
    }
    return false;
  }
}
