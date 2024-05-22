package src;
import java.util.ArrayList;

// represents a row of the river with lily pads 
public class LilyRiver extends River {
  private final ArrayList<Lily> lilypads;
  LilyRiver(ArrayList<Lily> lilypads) {
    this.lilypads = lilypads;
  }

  public void moveOnTick(int count) {
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
}
