package src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Main {
  public static void main(String[] args) {
    Row zero = new SafeStrip();
    Row one = new Road(4, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(1, 2, true))));
    Row two = new Road(3, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(10, 2, false))));
    Row three = new EndZone();
    Board b = new Board(new ArrayList<Row>(Arrays.asList(zero, one, two, three)), 3, 20);

    JFrame frame = new JFrame("Frogger");
    frame.add(b);

    Timer t = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
        b.moveOnTick();
      }

  });
  t.start();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addKeyListener(b);
    frame.setResizable(false);
    frame.isFocusable();
    frame.setSize(b.getWidth(), b.getHeight());
    frame.setVisible(true);
  }
}
/**
 * What needs to be done to finish this project?
 * - Key Listeners to move the frog 
 * - Timer to implement Movers 
 * - A way to end the game and display a you won! result 
 */