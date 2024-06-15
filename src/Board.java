package src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// represents the full board of the game 
public class Board implements KeyListener, ActionListener {
  private final ArrayList<Row> rows;
  private final int height; // the number of rows displayed on the screen at a time
  private final int width;
  private int count; // represents the number of times moveOnTick has been called on this Board
  private final int tilesize;
  private final Frog frog;
  private final Timer timer;
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
    this.tilesize = 60;
    this.frog = new Frog(new Position(this.width / 2, this.height - 1));
    this.timer = new Timer(1000, null);
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
  
  public JFrame render() {
    JFrame result = new JFrame();
    result.setLayout(null);
    // create frog image and set add to frame
    JLabel frogImg = this.frog.render(this.tilesize);
    frogImg.setBounds(this.frog.position.x * this.tilesize, this.frog.position.y * this.tilesize,
      this.tilesize, this.tilesize);
    result.add(frogImg, JLayeredPane.PALETTE_LAYER);
    // if the number of rows available is less than the height (the number that is supposed to be displayed)
    // for some reason, throw an error 
    if(this.rows.size() < this.height) {
      throw new IllegalArgumentException("Cannot display because the number of rows is less than the height.");
    }
    // add the rows to the screen
    for(int x = 0; x < this.height; x += 1) {
      JPanel item = this.rows.get(x).render(this.width, this.tilesize);
      item.setBounds(0, ((this.height - 1) * this.tilesize) - x * this.tilesize,
        this.width * this.tilesize, 
        this.tilesize);
      result.add(item, JLayeredPane.DEFAULT_LAYER);
    }
    // make final adjustments for result configuration 
    int titleStripHeight = 28;
    result.setSize(this.tilesize * this.width, this.tilesize * this.height + titleStripHeight);
    result.setTitle("Frogger");
    result.setResizable(true);
    result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    result.setVisible(true);
    result.setLocationRelativeTo(null);
    result.addKeyListener(this);
    return result;
  }

  // moves the frog accordingly when key is pressed
  public void keyTyped(KeyEvent e) {
    if(e.getKeyCode() == 37 && this.frog.position.x != 0) { // left and valid move 
      // update frog position 
      this.frog.moveLeft();
    }
    else if(e.getKeyCode() == 38) {// up (always valid)
      // update frog position
      this.frog.moveUp();
      // determine whether you can afford to delete this row for the display
      // if so, remove
      if(this.rows.size() - 1 >= height) {
        this.rows.remove(0);
      }
    }
    else if(e.getKeyCode() == 39 && this.frog.position.x != this.width - 1) { // right and valid move
      // update frog position 
      this.frog.moveRight();
    }
  }

  public void keyPressed(KeyEvent e) {
    return;
  }

  public void keyReleased(KeyEvent e) {
    return;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for(Row r : this.rows) {
      r.moveOnTick(this.count, this.width);
    }
    this.count = this.count + 1;
  }

}
