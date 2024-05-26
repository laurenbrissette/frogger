package src;

import java.util.ArrayList;

// represents the full board of the game 
public class Board {
  private final ArrayList<Row> rows;
  private final int height; // the number of rows displayed on the screen at a time
  private final int width;
  private int count; // represents the number of times moveOnTick has been called on this Board

  Board(ArrayList<Row> rows, int height, int width) {
    // checks that the row to height ratio is valid 
    if(rows.size() < height) {
      throw new IllegalArgumentException("Cannot create a board with a larger height than number of rows");
    }
    // checks to confirm that every row being added to the Board is valid 
    for(int y = 0; y < rows.size(); y += 1) {
      if(!rows.get(y).validRow(width, y, rows.size() - 1)) {
        throw new IllegalArgumentException("Failed to create board because row " + y + " is invalid");
      }
    }

    this.rows = rows;
    this.height = height;
    this.count = 0;
    this.width = width;
  }

  @Override
  // determines whether this Board is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Board)) {
      throw new IllegalArgumentException();
    }
    Board other = (Board)o;
    return this.height == other.height 
           && this.width == other.width
           && this.count == other.count
           && this.rows == other.rows;

  }

  // moves all of the auto-moving pieces in this Board one space 
  public void moveOnTick() {
    for(Row r : this.rows) {
      r.moveOnTick(this.count, this.width);
    }
  }
}
