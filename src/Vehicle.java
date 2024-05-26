package src;
import java.awt.Color;

// represents a moving vehicle on a road row
public class Vehicle extends Mover {
  Color color;

  Vehicle(int xVal, int size, boolean movesRight, Color color) {
    super(xVal, size, movesRight);
    this.color = color;
  }

  Vehicle(int xVal, int size, boolean movesRight) {
    this(xVal, size, movesRight, Color.RED);
  }

  @Override
  // determines whether this Vehicle is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Vehicle)) {
      throw new IllegalArgumentException();
    }
    Vehicle other = (Vehicle)o;
    return this.xVal == other.xVal 
      && this.size == other.size 
      && this.movesRight == other.movesRight
      && this.color == other.color;
  }
}
