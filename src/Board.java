package src;

import java.util.ArrayList;

// represents the full board of the game 
public class Board {
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
