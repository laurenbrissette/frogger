package src;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;


public class Main {
  public static void main(String[] args) {
    Row zero = new SafeStrip();
    Row one = new Road(1, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(1, 2, true))));
    Row two = new Road(2, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(10, 2, false))));
    Row three = new EndZone();
    Board b = new Board(new ArrayList<Row>(Arrays.asList(zero, one, two, three)), 3, 20);
    b.render();

  }
}
