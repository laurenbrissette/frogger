package src;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
  GameFrame() {
    this.setTitle("Frogger");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(500, 500);
    this.setVisible(true);
  }
}
