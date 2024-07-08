package src;

import java.awt.Color;

import javax.swing.JPanel;

// represents a horizontal strip that the frog is safe to step on 
// the width of the gameboard without any threat 
public class SafeStrip implements Row {

  @Override
  // determines whether this SafeStrip is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof SafeStrip)) {
      throw new IllegalArgumentException();
    }
    return true;
  }

  // moves all of the elements in the SafeStrip the necessary distance per tick 
  public void moveOnTick(int count, int moveOnTick) {
    return;
  }

  // determines whether the frog can move to this x value on this LogRiver without 
  // losing the game
  public boolean stableGround(int xVal) {
    return true;
  }

  // determines whether this SafeStrip is valid. A Row is valid when 
   // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
   // (b) the last and only the last Row is an EndZone
  public boolean validRow(int width, int thisY, int finalY) {
    if(thisY == finalY) {
      return false;
    }
    else {
      return true;
    }
  }

  // renders this SafeStrip as a JPanel
  public JPanel render(int width, int tilesize) {
    JPanel result = new JPanel();
    result.setBackground(new Color(83, 172, 86));
    result.setSize(width * tilesize, tilesize);
    return result;
  }

   // determines the distance/direction moved at x location which a frog would be 
   // carried (nonzero only applicable for log rivers)
  public int amountCarriedAt(int count, int x) {
    return 0;
  }
}
