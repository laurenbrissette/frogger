package src;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// represents the full board of the game 
public class Board extends JPanel implements KeyListener{
  private final ArrayList<Row> rows;
  private final int height; // the number of rows displayed on the screen at a time
  private final int width;
  private int count; // represents the number of times moveOnTick has been called on this Board
  private final int tilesize;
  final Frog frog;

  Board(ArrayList<Row> rows, int height, int width) {
    /* VARIABLES AND FIELDS */
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
    this.tilesize = 60;
    this.frog = new Frog(new Position(this.width / 2, 0));
    this.addKeyListener(this);
    // make final adjustments for result configuration 
    
    this.render();
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
    // move the x position of the frog if it is on a log
    Row target = this.rows.get(this.frog.position.y); // get row
    this.frog.moveWithRow(this.count, target); // move frog based on this row
    // goes through every row and changes the locations of the cars and logs if applicable
    for(int x = 0; x < this.height; x += 1) { 
      this.rows.get(x).moveOnTick(this.count, this.width);
    }
    // increases the counter used for adjusting speeds of cars and logs 
    this.count = this.count + 1;
    // redoes this jpanel configuration based on the updates to the images
    this.render();
  }
  
  // sets the images for this JPanel
  public void render() {
    this.removeAll(); // remove b/c new stuff will overlay old stuff

    int titleStripHeight = 28;
    this.setLayout(null);
    this.setSize(this.tilesize * this.width, this.tilesize * this.height + titleStripHeight); //set size of panel 
    // add image for frog to board
    JLabel frogImg;
    if(this.gameLost()) { // if lost, frog is displayed as a splat
      frogImg = this.frog.render(this.tilesize, true);
    }
    else { // if still playing, frog displayed as a frog
      frogImg = this.frog.render(this.tilesize, false);
    }
    frogImg.setBounds(this.frog.position.x * this.tilesize, 
      (this.height - this.frog.position.y - 1) * this.tilesize,
      this.tilesize, 
      this.tilesize);
    this.add(frogImg);
    // if number of rows is less than height, throw 
    if(this.rows.size() < this.height) {
      throw new IllegalArgumentException("Cannot display because the number of rows is less than the height.");
    }
    // add the rows to the screen
    for(int x = 0; x < this.height; x += 1) {
      JPanel item = this.rows.get(x).render(this.width, this.tilesize);
      item.setBounds(0, 
        ((this.height - 1) * this.tilesize) - x * this.tilesize,
        this.width * this.tilesize, 
        this.tilesize);
      this.add(item, JLayeredPane.DEFAULT_LAYER);
    }
    this.setVisible(true);
  }
  
  // respond to key press input from the used
  public void keyPressed(KeyEvent e) {
    // moves the frog one space left if possible
    if(e.getKeyCode() == KeyEvent.VK_LEFT && this.frog.position.x != 0) {
      this.frog.moveLeft();
      render();
    }
    // moves the frog one space right if possible
    else if(e.getKeyCode() == KeyEvent.VK_RIGHT && this.frog.position.x != this.width - 1) {
      this.frog.moveRight();
      render();
    }
    // moves the frog one space up if possible
    else if(e.getKeyCode() == KeyEvent.VK_UP) {
      // remove the bottom row if doing so won't make number of available rows less than height
      if(this.height <= this.rows.size() - 1) {
        this.rows.remove(0);
      }
      else {
        this.frog.moveUp();
      }
      render();
    }
  } 

  // determines whether this Board has been won.
  // occurs when the frog's position is the uppermost row
  public boolean gameWon() {
    if(this.frog.position.y == this.height - 1) {
      return true;
    }
    else {
      return false;
    }
  }

  // determines whether the game has been lost because the frog has entered "unstable ground"
  // either by falling into the lake or by getting hit by a car
  public boolean gameLost() {
    Row target = this.rows.get(this.frog.position.y); // get the row the frog is on 
    if(!target.stableGround(this.frog.position.x)) { // if not safe area
      return true;
    }
    else {
      return false;
    }
  }

  public void keyTyped(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}
}
