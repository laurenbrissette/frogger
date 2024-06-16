package src;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// represents a row of the river with lily pads 
public class LilyRiver extends River {
  private final ArrayList<Lily> lilypads;
  private JPanel image;
  LilyRiver(ArrayList<Lily> lilypads) {
    this.lilypads = lilypads;
  }

  @Override
  // determines whether this Lily is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof LilyRiver)) {
      throw new IllegalArgumentException();
    }
    LilyRiver other = (LilyRiver)o;
    return new Utils().equalsWithoutOrder(this.lilypads, other.lilypads);
  }

  // moves all of the elements in this LilyRiver the necessary distance per tick 
  public void moveOnTick(int count, int rowLength) {
    return; 
  }

  // determines whether the frog can move to this x value on this Row without 
  // losing the game
  public boolean stableGround(int xVal) {
    for(Lily l : this.lilypads) {
      if(l.overlaps(xVal)) {
        return true;
      }
    }
    return false;
  }

  // determines whether this LilyRiver is valid. A Row is valid when 
   // (a) no Rows initially have invalid items (Logs, Vehicles, Lilies, etc.)
   // (b) the last and only the last Row is an EndZone
  public boolean validRow(int width, int thisY, int finalY) {
    // LilyRiver cannot be the final Row
    if(thisY == finalY) {
      return false;
    }
    // you should always be able to cross the river
    if(this.lilypads.size() == 0) {
      return false;
    }
    // All lily pads must be within [0, finalY)
    for(Lily l : this.lilypads) {
      if(!l.within(width)) {
        return false;
      }
    }
    return true;
  }

  // renders this LilyRiver as a JPanel
  public JPanel render(int width, int tilesize) {
    if(this.image != null) {
      return this.image;
    }
    JPanel result = new JPanel();
    result.setBackground(new Color(179, 229, 252));
    result.setSize(width * tilesize, tilesize);
    ImageIcon img = new ImageIcon(new ImageIcon("src/images/lilypad.png").getImage().getScaledInstance(tilesize, tilesize, Image.SCALE_SMOOTH));
    for(Lily l : this.lilypads) {
      JLabel item = new JLabel();
      item.setIcon(img);
      item.setBounds(l.xVal * tilesize, 0, tilesize, tilesize);
      result.add(item, JLayeredPane.PALETTE_LAYER);
    }
    result.setLayout(null);
    this.image = result;
    return result;
  }

   // determines the distance/direction moved at x location which a frog would be 
   // carried (nonzero only applicable for log rivers)
  public int amountCarriedAt(int count, int x) {
    return 0;
  }
}
