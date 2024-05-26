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
    Assert.assertEquals(true, end.stableGround(1));
    Assert.assertEquals(true, end.stableGround(0));

    // Test LilyRiver, true if there is a Lily at the given int xPosition
    LilyRiver lily = new LilyRiver(new ArrayList<Lily>(Arrays.asList(new Lily(0), 
    new Lily(2), new Lily(4))));
    LilyRiver emptyLily = new LilyRiver(new ArrayList<Lily>());
    Assert.assertEquals(false, lily.stableGround(1));
    Assert.assertEquals(true, lily.stableGround(0));
    Assert.assertEquals(true, lily.stableGround(2));
    Assert.assertEquals(false, emptyLily.stableGround(1));

    // Test LogRiver, true if there is a Log at the given int xPosition 
    LogRiver logRight = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, true),
    new Log(6, 3, true))));
    LogRiver logLeft = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, false),
    new Log(6, 3, false))));
    // semi open interval 
    // note opposite behavior from Road
    Assert.assertEquals(false, logRight.stableGround(0));
    Assert.assertEquals(false, logLeft.stableGround(0));
    Assert.assertEquals(true, logRight.stableGround(1));
    Assert.assertEquals(true, logLeft.stableGround(1));
    Assert.assertEquals(true, logRight.stableGround(2));
    Assert.assertEquals(true, logLeft.stableGround(2));
    Assert.assertEquals(false, logRight.stableGround(3));
    Assert.assertEquals(false, logLeft.stableGround(3));

    // Test SafeStrip, always true
    SafeStrip safe = new SafeStrip();
    Assert.assertEquals(true, safe.stableGround(1));
    Assert.assertEquals(true, safe.stableGround(0));

    // Test Road, true if there is NOT a Vehicle at the given int xPosition 
    Road roadRight = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, true),
    new Vehicle(6, 3, true))));
    Road roadLeft = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, false),
    new Vehicle(6, 3, false))));
    // semi open interval 
    // direction of car irrelevant to this computation 
    // note opposite behavior from LogRiver 
    Assert.assertEquals(true, roadRight.stableGround(0));
    Assert.assertEquals(true, roadLeft.stableGround(0));
    Assert.assertEquals(false, roadRight.stableGround(1));
    Assert.assertEquals(false, roadLeft.stableGround(1));
    Assert.assertEquals(false, roadRight.stableGround(2));
    Assert.assertEquals(false, roadLeft.stableGround(2));
    Assert.assertEquals(true, roadRight.stableGround(3));
    Assert.assertEquals(true, roadLeft.stableGround(3));
  }

  @Test
  // test the method moveOnTick in EndZone class
  public void testMoveOnTickEndZone() {
    EndZone initial = new EndZone();
    EndZone mod = new EndZone();
    Assert.assertEquals(initial, mod);
    mod.moveOnTick(1, 20);
    Assert.assertEquals(initial, mod);
  }

  @Test 
  // test the method moveOnTick in LilyRiver class
  public void testMoveOnTickLilyRiver() {
    LilyRiver initial = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(2), new Lily(5))));
    LilyRiver mod = new LilyRiver(new ArrayList<Lily>(Arrays.asList(
      new Lily(2), new Lily(5))));
    Assert.assertEquals(initial, mod);
    mod.moveOnTick(1, 20);
    Assert.assertEquals(initial, mod);
  }

  @Test 
  // test the method overlaps in Lily class
  public void testOverlapsLily() {
    Lily l = new Lily(0);
    Lily m = new Lily(8);
    Assert.assertEquals(true, l.overlaps(0)); // on value
    Assert.assertEquals(true, m.overlaps(8));
    Assert.assertEquals(false, l.overlaps(1)); // upper bound 
    Assert.assertEquals(false, m.overlaps(7)); // lower bound
   }

  @Test 
  // test the method overlaps in Mover class
  public void testOverlapsMover() {
    Mover v = new Vehicle(2, 2, true);
    Assert.assertEquals(false, v.overlaps(1)); // lower val 
    Assert.assertEquals(true, v.overlaps(2)); // start of space 
    Assert.assertEquals(true, v.overlaps(3));
    Assert.assertEquals(false, v.overlaps(4)); // semi open interval [2, 4)

    Mover l = new Log(5, 1, false);
    Assert.assertEquals(false, l.overlaps(4));
    Assert.assertEquals(true, l.overlaps(5));
    Assert.assertEquals(false, l.overlaps(6));
  }

  @Test 
  // test the method step in Mover class
  public void testStepMover() {

  }

  @Test 
  // test movesOffScreen in Mover class
  public void testMovesOffScreen() {
    Mover aRight = new Vehicle(8, 3, true);
    Mover aLeft = new Vehicle(0, 4, false);
    Mover aSafeLeft = new Vehicle(1, 2, false);
    Assert.assertEquals(true, aRight.movesOffScreen(11));
    Assert.assertEquals(false, aRight.movesOffScreen(12));
    Assert.assertEquals(true, aLeft.movesOffScreen(8));
    Assert.assertEquals(false, aSafeLeft.movesOffScreen(10));
  }

  @Test
  // test validRow in Row classes
  public void testValidRow() {
    
  }

  @Test 
  // test within in Lily class
  public void testWithinLily() {
    Lily one = new Lily(1);
    Lily zero = new Lily(0);
    Lily negOne = new Lily(-1);
    Lily eight = new Lily(8);
    Assert.assertTrue(one.within(8));
    Assert.assertTrue(zero.within(2));
    Assert.assertFalse(negOne.within(4));
    Assert.assertFalse(eight.within(8));
    Assert.assertTrue(eight.within(9));

  }

  @Test
  // test within in Mover class 
  public void testWithinMover() {
    Mover one = new Vehicle(1, 2, true);
    Mover zero = new Vehicle(0, 1, false);
    Mover negOne = new Log(-1, 3, false);
    Mover eight = new Vehicle(8, 2, true);
    Assert.assertTrue(one.within(8));
    Assert.assertTrue(zero.within(2));
    Assert.assertFalse(negOne.within(4));
    Assert.assertFalse(eight.within(8));
    Assert.assertFalse(eight.within(9));
    Assert.assertTrue(eight.within(10));
  }

  @Test
  // test moveOnTick in Board class
  public void testMoveOnTick() {
    
  }

  @Test 
  // test equals for EndZone
  public void testEqualsEndZone() {
    EndZone one = new EndZone();
    EndZone two = new EndZone();
    Assert.assertEquals(one, one);
    Assert.assertEquals(one, two);
  }

  @Test 
  // test equals for Frog
  public void testEqualsFrog() {
    Frog one = new Frog(new Position(1, 2));
    Frog two = new Frog(new Position(1, 2));
    Frog three = new Frog(new Position(2, 1));
    Assert.assertEquals(three, three);
    Assert.assertEquals(one, two);
    Assert.assertEquals(two, one);
    Assert.assertNotEquals(one, three);
  }

  @Test 
  // test equals for Lily 
  public void testEqualsLily() {
    Lily one = new Lily(3);
    Lily two = new Lily(3);
    Lily three = new Lily(0);
    Assert.assertEquals(three, three);
    Assert.assertEquals(one, two);
    Assert.assertEquals(two, one);
    Assert.assertNotEquals(one, three);
  }

  @Test 
  // test equals for LilyRiver
  public void testEqualsLilyRiver() {
    LilyRiver one = new LilyRiver(new ArrayList<Lily>(
      Arrays.asList(new Lily(2), new Lily(3))));
    LilyRiver two = new LilyRiver(new ArrayList<Lily>(
      Arrays.asList(new Lily(2), new Lily(3))));
    LilyRiver three = new LilyRiver(new ArrayList<Lily>(
      Arrays.asList(new Lily(3), new Lily(2))));
    Assert.assertEquals(one, one);
    Assert.assertEquals(one, two);
    Assert.assertEquals(one, three);
  }

  @Test 
  // test equals for Log 
  public void testEqualsLog() {
    Log one = new Log(1, 2, true);
    Log two = new Log(1, 2, false);
    Log three = new Log(1, 2, false);
    Log four = new Log(2, 2, true);
    Assert.assertNotEquals(one, two);
    Assert.assertEquals(one, one);
    Assert.assertEquals(three, two);
    Assert.assertEquals(two, three);
    Assert.assertNotEquals(two, four);
  }

  @Test 
  // test equals for LogRiver
  public void testEqualsLogRiver() {
    LogRiver one = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(2, 3, true), new Log(8, 2, true))));
    LogRiver two = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(8, 2, true), new Log(2, 3, true))));
    LogRiver three = new LogRiver(new ArrayList<Log>(Arrays.asList(
      new Log(8, 2, true))));
    Assert.assertEquals(one, one);
    Assert.assertEquals(one, two);
    Assert.assertEquals(two, one);
    Assert.assertNotEquals(two, three);
  }

  @Test
  // test equals for Position
  public void testEqualsPosition() {
    Position one = new Position(1, 2);
    Position two = new Position(2, 1);
    Position three = new Position(1, 2);
    Assert.assertEquals(one, three);
    Assert.assertEquals(one, one);
    Assert.assertNotEquals(one, two);
  }

  @Test 
  // test equals for Road 
  public void testEqualsRoad() {
    Road one = new Road(1, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(1, 2, true), 
        new Vehicle(5, 3, true))));
    Road two = new Road(1, new ArrayList<Vehicle>(
      Arrays.asList(new Vehicle(5, 3, true),
        new Vehicle(1, 2, true))));    
    Road three = new Road(1, new ArrayList<Vehicle>(
          Arrays.asList(new Vehicle(5, 3, true))));    
    Assert.assertEquals(one, one); // reflexive 
    Assert.assertEquals(one, two); // diff order 
    Assert.assertEquals(two, one); // symmetric
    Assert.assertNotEquals(two, three); // subset
  }

  @Test
  // test equals for SafeStrip
  public void testEqualsSafeStrip() {
    SafeStrip one = new SafeStrip();
    SafeStrip two = new SafeStrip();
    Assert.assertEquals(one, one);
    Assert.assertEquals(one, two);
    Assert.assertEquals(two, one);
  }

  @Test 
  // test equals for Vehicle
  public void testEqualsVehicle() {
    Vehicle one = new Vehicle(1, 2, true);
    Vehicle two = new Vehicle(1, 2, false);
    Vehicle three = new Vehicle(1, 2, true);
    Vehicle four = new Vehicle(1, 4, true);
    Assert.assertEquals(one, one);
    Assert.assertNotEquals(one, two);
    Assert.assertEquals(one, three);
    Assert.assertNotEquals(one, four);
  }

}