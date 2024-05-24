package src;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Tests {
  @Test
  // test the method stableGround in classes that implement Row interface
  public void testStableGround() {
    // Test EndZone, always true
    EndZone end = new EndZone();
    Assert.assertEquals(end.stableGround(1), true);
    Assert.assertEquals(end.stableGround(0), true);

    // Test LilyRiver, true if there is a Lily at the given int xPosition
    LilyRiver lily = new LilyRiver(new ArrayList<Lily>(Arrays.asList(new Lily(0), 
    new Lily(2), new Lily(4))));
    LilyRiver emptyLily = new LilyRiver(new ArrayList<Lily>());
    Assert.assertEquals(lily.stableGround(1), false);
    Assert.assertEquals(lily.stableGround(0), true);
    Assert.assertEquals(lily.stableGround(2), true);
    Assert.assertEquals(emptyLily.stableGround(1), false);

    // Test LogRiver, true if there is a Log at the given int xPosition 
    LogRiver logRight = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, true),
    new Log(6, 3, true))));
    LogRiver logLeft = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, false),
    new Log(6, 3, false))));
    // semi open interval 
    // note opposite behavior from Road
    Assert.assertEquals(logRight.stableGround(0), false);
    Assert.assertEquals(logLeft.stableGround(0), false);
    Assert.assertEquals(logRight.stableGround(1), true);
    Assert.assertEquals(logLeft.stableGround(1), true);
    Assert.assertEquals(logRight.stableGround(2), true);
    Assert.assertEquals(logLeft.stableGround(2), true);
    Assert.assertEquals(logRight.stableGround(3), false);
    Assert.assertEquals(logLeft.stableGround(3), false);

    // Test SafeStrip, always true
    SafeStrip safe = new SafeStrip();
    Assert.assertEquals(safe.stableGround(1), true);
    Assert.assertEquals(safe.stableGround(0), true);

    // Test Road, true if there is NOT a Vehicle at the given int xPosition 
    Road roadRight = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, true),
    new Vehicle(6, 3, true))));
    Road roadLeft = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, false),
    new Vehicle(6, 3, false))));
    // semi open interval 
    // direction of car irrelevant to this computation 
    // note opposite behavior from LogRiver 
    Assert.assertEquals(roadRight.stableGround(0), true);
    Assert.assertEquals(roadLeft.stableGround(0), true);
    Assert.assertEquals(roadRight.stableGround(1), false);
    Assert.assertEquals(roadLeft.stableGround(1), false);
    Assert.assertEquals(roadRight.stableGround(2), false);
    Assert.assertEquals(roadLeft.stableGround(2), false);
    Assert.assertEquals(roadRight.stableGround(3), true);
    Assert.assertEquals(roadLeft.stableGround(3), true);
  }

  @Test
  // test the method moveOnTick in classes that implement Row
  public void testMoveOnTickRow() {}

  @Test 
  // test the method overlaps in Lily class
  public void testOverlapsLily() {}

  @Test 
  // test the method overlaps in Mover class
  public void testOverlapsMover() {}

  @Test 
  // test the method step in Mover class
  public void testStepMover() {}

}