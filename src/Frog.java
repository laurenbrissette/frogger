package src;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// represents the game piece (frog) the player navigates through the game  
public class Frog {
  Position position; // represents the position of the frog as an x,y coordinate
  JLabel image;
  Frog(Position position) {
    this.position = position;
  }

  @Override
  // determines whether this Frog is the same as Object o
  public boolean equals(Object o) {
    if(!(o instanceof Frog)) {
      throw new IllegalArgumentException();
    }
    Frog other = (Frog)o;
    return this.position.equals(other.position);
  }

  public JLabel render(int tilesize, boolean splat) {
    if(this.image == null && !splat) {
      JLabel result = new JLabel();
      result.setSize(tilesize, tilesize);
      result.setIcon(new ImageIcon(new ImageIcon("src/images/frogCharacter.PNG").getImage().getScaledInstance(tilesize, tilesize, Image.SCALE_SMOOTH)));
      result.setVerticalAlignment(JLabel.CENTER);
      result.setHorizontalAlignment(JLabel.CENTER);
      this.image = result;
    }
    else if(splat) {
      JLabel result = new JLabel();
      result.setIcon(new ImageIcon(new ImageIcon("src/images/splat.png").getImage().getScaledInstance(tilesize, tilesize, Image.SCALE_SMOOTH)));
      result.setVerticalAlignment(JLabel.CENTER);
      result.setHorizontalAlignment(JLabel.CENTER);
      this.image = result;

    }
    
    return this.image;
  }

  // moves the position of this Frog one space to the left
  public void moveLeft() {
    this.position = new Position(this.position.x - 1, this.position.y);
  }

  // moves the position of this Frog one space to the left 
  public void moveRight() {
    this.position = new Position(this.position.x + 1, this.position.y);
  }

  // moves the position of this Frog one space up 
  public void moveUp() {
    this.position = new Position(this.position.x, this.position.y + 1);
  }

  public void moveWithRow(int count, Row location) {
    this.position.x = this.position.x + location.amountCarriedAt(count, this.position.x);
  }
   
}
