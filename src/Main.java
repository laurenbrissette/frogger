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
    Row one = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(2), new Lily(4), new Lily(6), new Lily(7)
    )));
    Row two = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(0, 3, true), new Log(5, 2, true)
    )), 6);
    Row three = new Road(7, new ArrayList<Vehicle>(Arrays.asList(
      new Vehicle(2, 1, false), new Vehicle(4, 1, false)
    )));
    Row four = new Road(5, new ArrayList<Vehicle>(Arrays.asList(
      new Vehicle(2, 1, true), new Vehicle(4, 1, true)
    )));
    Row five = new SafeStrip();
    Row six = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(0), new Lily(2), new Lily(4), new Lily(6)
    )));
    Row seven = new SafeStrip();
    Row eight = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(1, 2, false), new Log(4, 2, false),
      new Log(6, 1, false)
    )), 4);
    Row nine = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(1, 2, true), new Log(4, 2, true),
      new Log(6, 1, true)
    )), 7);
    Row ten = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(1), new Lily(2), new Lily(5), new Lily(7)
    )));
    Row eleven = new SafeStrip();
    Row twelve = new Road(4, new ArrayList<Vehicle>(Arrays.asList(
      new Vehicle(0, 2, false), new Vehicle(3, 4, false)
    )));
    Row thirteen = new Road(4, new ArrayList<Vehicle>(Arrays.asList(
      new Vehicle(0, 4, false), new Vehicle(6, 2, false)
    )));
    Row fourteen = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(1), new Lily(2), new Lily(5), new Lily(7)
    )));
    Row fifteen = new EndZone();
    Board b = new Board(new ArrayList<Row>(Arrays.asList(zero, one, two, three, four, five, six, seven, eight, 
      nine, ten, eleven, twelve, thirteen, fourteen, fifteen)), 8, 8);
    JFrame frame = new JFrame("Frogger");
    frame.add(b);

    Timer t = new Timer(100, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
        if(b.gameWon() || b.gameLost()) {
          try {
            b.render();
            wait(5000);
            frame.removeAll();
            
          } catch (InterruptedException e1) {
            e1.printStackTrace();
          }
        }
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
 * - A way to end the game and display a you won! result 
 */