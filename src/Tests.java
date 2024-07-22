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
    Assert.assertTrue(end.stableGround(1));
    Assert.assertTrue(end.stableGround(0));

    // Test LilyRiver, true if there is a Lily at the given int xPosition
    LilyRiver lily = new LilyRiver(new ArrayList<Lily>(Arrays.asList(new Lily(0), 
    new Lily(2), new Lily(4))));
    LilyRiver emptyLily = new LilyRiver(new ArrayList<Lily>());
    Assert.assertFalse(lily.stableGround(1));
    Assert.assertTrue(lily.stableGround(0));
    Assert.assertTrue(lily.stableGround(2));
    Assert.assertFalse(emptyLily.stableGround(1));

    // Test LogRiver, true if there is a Log at the given int xPosition 
    LogRiver logRight = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, true),
    new Log(6, 3, true))));
    LogRiver logLeft = new LogRiver(new ArrayList<Log>(Arrays.asList(new Log(1, 2, false),
    new Log(6, 3, false))));
    // semi open interval 
    // note opposite behavior from Road
    Assert.assertFalse(logRight.stableGround(0));
    Assert.assertFalse(logLeft.stableGround(0));
    Assert.assertTrue(logRight.stableGround(1));
    Assert.assertTrue(logLeft.stableGround(1));
    Assert.assertTrue(logRight.stableGround(2));
    Assert.assertTrue(logLeft.stableGround(2));
    Assert.assertFalse(logRight.stableGround(3));
    Assert.assertFalse(logLeft.stableGround(3));

    // Test SafeStrip, always true
    SafeStrip safe = new SafeStrip();
    Assert.assertTrue(safe.stableGround(1));
    Assert.assertTrue(safe.stableGround(0));

    // Test Road, true if there is NOT a Vehicle at the given int xPosition 
    Road roadRight = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, true),
      new Vehicle(6, 3, true))));
    Road roadLeft = new Road(1, new ArrayList<Vehicle>(Arrays.asList(new Vehicle(1, 2, false),
      new Vehicle(6, 3, false))));
    // semi open interval 
    // direction of car irrelevant to this computation 
    // note opposite behavior from LogRiver 
    Assert.assertTrue(roadRight.stableGround(0));
    Assert.assertTrue(roadLeft.stableGround(0));
    Assert.assertFalse(roadRight.stableGround(1));
    Assert.assertFalse(roadLeft.stableGround(1));
    Assert.assertFalse(roadRight.stableGround(2));
    Assert.assertFalse(roadLeft.stableGround(2));
    Assert.assertTrue(roadRight.stableGround(3));
    Assert.assertTrue(roadLeft.stableGround(3));
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
    Assert.assertTrue(l.overlaps(0)); // on value
    Assert.assertTrue(m.overlaps(8));
    Assert.assertFalse(l.overlaps(1)); // upper bound 
    Assert.assertFalse(m.overlaps(7)); // lower bound
   }

  @Test 
  // test the method overlaps in Mover class
  public void testOverlapsMover() {
    Mover v = new Vehicle(2, 2, true);
    Assert.assertFalse(v.overlaps(1)); // lower val 
    Assert.assertTrue(v.overlaps(2)); // start of space 
    Assert.assertTrue(v.overlaps(3));
    Assert.assertFalse(v.overlaps(4)); // semi open interval [2, 4)
    Mover l = new Log(5, 1, false);
    Assert.assertFalse(l.overlaps(4));
    Assert.assertTrue(l.overlaps(5));
    Assert.assertFalse(l.overlaps(6));
  }

  @Test 
  // test the method step in Mover class
  public void testStepMover() {
    // does not move off screen and moves right 
    Mover one = new Vehicle(2, 3, true);
    Assert.assertEquals(new Vehicle(2, 3, true), one);
    one.step(10);
    Assert.assertEquals(new Vehicle(3, 3, true), one);
    // does not move off screen and moves left 
    Mover two = new Vehicle(3, 1, false);
    Assert.assertEquals(new Vehicle(3, 1, false), two);
    two.step(5);
    Assert.assertEquals(new Vehicle(2, 1, false), two);
    // moves off of screen and moves right 
    Mover three = new Vehicle(2, 4, true);
    Assert.assertEquals(new Vehicle(2, 4, true), three);
    Assert.assertTrue(three.within(6));
    Assert.assertTrue(three.movesOffScreen(6));
    three.step(6);
    Assert.assertEquals(new Vehicle(3, 3, true), three);
    // moves off screen and moves left
    Mover four = new Vehicle(0, 5, false);
    Assert.assertEquals(new Vehicle(0, 5, false), four);
    Assert.assertTrue(four.within(9));
    Assert.assertTrue(four.movesOffScreen(9));
    four.step(9);
    Assert.assertEquals(new Vehicle(0,4,false), four);
  }

  @Test 
  // test movesOffScreen in Mover class
  public void testMovesOffScreen() {
    Mover aRight = new Vehicle(8, 3, true);
    Mover aLeft = new Vehicle(0, 4, false);
    Mover aSafeLeft = new Vehicle(1, 2, false);
    Assert.assertTrue(aRight.movesOffScreen(11));
    Assert.assertFalse(aRight.movesOffScreen(12));
    Assert.assertTrue(aLeft.movesOffScreen(8));
    Assert.assertFalse(aSafeLeft.movesOffScreen(10));
  }

  @Test
  // test validRow in EndZone class
  public void testValidRowEndZone() {
    EndZone one = new EndZone();
    Assert.assertFalse(one.validRow(10, 2, 3));
    Assert.assertTrue(one.validRow(9, 5, 5));
  }

  @Test 
  // test validRow in LilyRiver class
  public void testValidRowLilyRiver() {
    LilyRiver one = new LilyRiver(new ArrayList<Lily>());
    LilyRiver two = new LilyRiver(new ArrayList<Lily>(
      Arrays.asList(new Lily(3), new Lily(0), new Lily(-2))));
    LilyRiver three = new LilyRiver(new ArrayList<Lily>(
      Arrays.asList(new Lily(2), new Lily(6), new Lily(9))));
    Assert.assertFalse(one.validRow(4, 2, 5));
    Assert.assertFalse(two.validRow(5, 3, 7));
    Assert.assertTrue(three.validRow(10, 7, 8));
    Assert.assertFalse(three.validRow(9, 2, 4));
  }
  
  @Test
  // test validRow in LogRiver class
  public void testValidRowLogRiver() {
    LogRiver one = new LogRiver(new ArrayList<Log>());
    LogRiver two = new LogRiver(new ArrayList<Log>(
      Arrays.asList(new Log(4, 2, true))));
    Assert.assertFalse(one.validRow(5, 2, 4));
  }

  @Test
  // test validRow in SafeStrip class
  public void testValidRowSafeStrip() {
    SafeStrip one = new SafeStrip();
    Assert.assertTrue(one.validRow(8, 3, 4));
    Assert.assertFalse(one.validRow(3, 4, 4));
  }

  @Test
  // test validRow in Road class
  public void testValidRowRoad() {}

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