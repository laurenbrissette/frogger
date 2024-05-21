import java.util.ArrayList;

// represents the full board of the game 
class Board {
  private final ArrayList<Row> rows;
  private final int height; // the number of rows displayed on the screen at a time

  Board(ArrayList<Row> rows, int height) {
    if(rows.size() < height) {
      throw new IllegalArgumentException("Cannot create a board with a larger height than number of rows");
    }
    this.rows = rows;
    this.height = height;
  }

}

// represents a horizontal strip/row in the game for the frog to 
// step on 
interface Row {
  /* BRAINSTORMING: 
   * - render 
   * - moveOnTick -> moves cars and logs 
   * - check whether the frog can move to a particular x value on that row 
   */
}

// represents a horizontal strip that the frog is safe to step on 
// the width of the gameboard without any threat 
class SafeStrip implements Row {}

// represents a street the frog must cross with moving car obstacles 
class Road implements Row {}

// represents a river the frog must cross with moving logs and 
// stable lily pads
class River implements Row {}

// represents a row of the river with logs 
class LogRiver extends River {
  private final ArrayList<Log> logs;
  LogRiver(ArrayList<Log> logs) {
    this.logs = logs;
  }
}

// represents a row of the river with lily pads 
class LilyRiver extends River {
  private final ArrayList<Lily> lilypads;
  LilyRiver(ArrayList<Lily> lilypads) {
    this.lilypads = lilypads;
  }
}

// represents the horizontal strip the player needs to navigate the 
// frog to in order to win 
class EndZone implements Row {}

// represents a moving log in the river 
class Log {
  private int xVal; // represents the distance in blocks from the left of the screen 
  private final int size; // represents the length of the log
  Log(int xVal, int size) {
    this.xVal = xVal;
    this.size = size;
  }

  // move on tick --> move one to the left 
  // could affect size/display as a log is moving off screen 
}

// represents a moving lilypad in the river 
class Lily {
  private final int xVal; // represents the distance in blocks from the left of the screen 
  Lily(int xVal) {
    this.xVal = xVal;
  }
}

// represents the game piece (frog) the player navigates through the game  
class Frog {
  private final Position position; // represents the position of the frog as an x,y coordinate
  Frog(Position position) {
    this.position = position;
  }
}

// represents a moving vehicle on a road row
class Vehicle {
  private int xVal;
  private final int size; // represents the length of the vehicle in blocks;
  Vehicle(int xVal, int size) {
    this.xVal = xVal;
    this.size = size;
  }
  // move on tick --> move one to the left 
  // could affect size/display as a log is moving off screen 
}

// represents a position as a Cartesian Coordinate
class Position {
  private final int x;
  private final int y;
  
  Position(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
