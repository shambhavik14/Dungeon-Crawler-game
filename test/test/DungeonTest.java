package test;

import controller.DungeonViewController;
import controller.ViewController;
import dungeon.Dungeon;
import controller.DungeonController;
import controller.DungeonConsoleController;
import dungeon.DungeonModel;
import dungeon.Edge;
import dungeon.FakeRandom;
import dungeon.Graph;
import dungeon.Location;
import dungeon.RandomInteger;
import dungeon.TrueRandom;

import org.junit.Before;
import org.junit.Test;
import view.DungeonView;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the Dungeon model and dungeon controller.
 */
public class DungeonTest {

  DungeonModel dungeonModel;
  DungeonModel dungeon;
  RandomInteger trueRandom;
  RandomInteger fakeRandom;

  @Before
  public void setUp() {

    trueRandom = new TrueRandom();
    fakeRandom = new FakeRandom(0, 1, 2, 3, 4, 0, 3, 2, 0,
        1, 2, 1, 1, 1, 1, 0, 2, 1, 3, 1, 1, 1, 0, 1, 2, 0, 1,
        3, 2, 1, 2, 0, 1, 1, 0, 1, 3, 2, 0, 1, 0, 2, 0);
    dungeonModel = new Dungeon(4, 4, 5, false, 2,
        50, fakeRandom);
    dungeon = new Dungeon(4, 4, 5, false, 2,
        50, trueRandom);
  }

  @Test
  public void testWrapping() {
    List<Edge> edgesList1;
    List<Edge> result;
    DungeonModel dungeon1 = new Dungeon(4, 4, 5, true, 2, 50, fakeRandom);
    edgesList1 = dungeon1.getWrappingEdges();
    Graph graph1 = new Graph(4,4, 16, edgesList1.size(), edgesList1, 5,fakeRandom);
    result = graph1.kruskalMinSpT();
    assertEquals(result,dungeon1.getResultEdge());
  }

  @Test
  public void testNonWrapping() {
    List<Edge> edgesList1;
    List<Edge> result;
    edgesList1 = dungeonModel.getEdgesList();
    Graph graph1 = new Graph(4, 4, 16, edgesList1.size(), edgesList1, 5,fakeRandom);
    result = graph1.kruskalMinSpT();
    assertEquals(result, dungeonModel.getResultEdge());
  }

  @Test
  public void testStartDestinationDistance() {
    int distance = dungeon.getStartDestination();
    assertEquals(5, distance);

  }


  @Test
  public void testStartLocation() {
    int id = dungeonModel.getPlayerStartLocation();
    assertEquals(dungeonModel.getStart().getId(), id);
  }

  @Test
  public void testPlayerDestination() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    dungeonModel.setDirection("East");
    assertEquals(dungeonModel.getLocation(), dungeonModel.getDestination());
  }

  @Test
  public void testPlayerMove() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    int id = dungeonModel.getLocation().getId();
    assertEquals(8, id);
  }

  @Test
  public void testAllDirections() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    assertTrue(dungeonModel.getLocation().isNorth());
    assertTrue(dungeonModel.getLocation().isEast());
    assertTrue(dungeonModel.getLocation().isWest());
    assertTrue(dungeonModel.getLocation().isSouth());
  }

  @Test
  public void testPickTreasure() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    assertEquals(2, dungeonModel.getSapphireCount());
  }

  @Test
  public void testPickArrow() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    assertEquals(4, dungeonModel.getArrowCount());
  }

  @Test
  public void testStart() {
    int id = dungeonModel.getStart().getId();
    assertEquals(12, id);
  }

  @Test
  public void testDestination() {
    int id = dungeonModel.getDestination().getId();
    assertEquals(2, id);
  }

  @Test
  public void findOtyugh() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    assertTrue(dungeonModel.getLocation().hasOtyugh());
  }

  @Test
  public void otyughDestination() {
    assertTrue(dungeonModel.getDestination().hasOtyugh());
  }

  @Test
  public void otyughStart() {
    assertFalse(dungeonModel.getStart().hasOtyugh());
  }


  @Test
  public void shootArrow() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setArrow();
    Location arrowLocation = dungeonModel.arrowTravel("North",
        2);
    assertEquals(arrowLocation.getId(), 4);
  }

  @Test
  public void arrowDisappear() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setArrow();
    Location arrowLocation = dungeonModel.arrowTravel("South",
        2);
    assertNull(arrowLocation);
  }

  @Test
  public void arrowTunnel() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setArrow();
    Location arrowLocation = dungeonModel.arrowTravel("North",
        3);
    assertEquals(arrowLocation.getId(), 1);
  }

  @Test
  public void otyughInjured() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    dungeonModel.setArrow();
    Location arrowLocation = dungeonModel.arrowTravel("East",
        1);
    String result = dungeonModel.arrowMonster(arrowLocation);
    assertEquals("Great shot! Otyugh is injured!", result);
  }

  @Test
  public void otyughDead() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("North");
    dungeonModel.setArrow();
    Location arrowLocation = dungeonModel.arrowTravel("East",
        1);
    dungeonModel.arrowMonster(arrowLocation);
    dungeonModel.setArrow();
    Location arrowLocation1 = dungeonModel.arrowTravel("East",
        1);
    String result = dungeonModel.arrowMonster(arrowLocation1);
    assertEquals("Great shot again! Otyugh is dead!", result);
  }

  @Test
  public void LessSmell() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    int smell = dungeonModel.getLocation().hasSmell();
    assertEquals(1, smell);
  }

  @Test
  public void strongSmell() {
    dungeonModel.getPlayerStartLocation();
    dungeonModel.setDirection("North");
    dungeonModel.setDirection("East");
    dungeonModel.setDirection("North");
    int smell = dungeonModel.getLocation().hasSmell();
    assertEquals(2, smell);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidModel() {
    DungeonModel dungeonModel1 = null;
    Readable input = new StringReader("1 1");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel1);
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    DungeonController d = new DungeonConsoleController(input, gameLog);
    d.playGame(dungeonModel);
  }

  @Test
  public void testPlayerWins() {
    Readable input = new StringReader("1 North Yes 1 East 1 North Yes 1 "
        + "North Yes 2 East 1 2 East 1 1 East Yes");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);

    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "\n"
        + "Do you want to pick the treasure?\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "No Arrow in this cave\n"
        + "Do you want to pick the treasure?\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your South\n"
        + "\n"
        + "No treasure in this cave\n"
        + "Player found an arrow!\n"
        + "\n"
        + "Do you want to pick the treasure?\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "Great shot! Otyugh is injured!\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "Great shot again! Otyugh is dead!\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "No Arrow in this cave\n"
        + "Do you want to pick the treasure?\n"
        + "No smell\n"
        + "You Won! Player has reached the destination\n";

    assertEquals(expected, output.toString());

  }

  @Test
  public void testPlayerSurvival() {
    Readable input = new StringReader("2 North 50 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Enter the height of the dungeon\n"
        + "4\n"
        + "Enter the width of the dungeon\n"
        + "4\n"
        + "Enter the interconnectivity of the dungeon\n"
        + "5\n"
        + "Is your dungeon wrapping?(true or false)\n"
        + "false\n"
        + "Percentage of caves with treasure\n"
        + "50\n"
        + "Number of Otyugh\n"
        + "2\n"
        + "2\n"
        + "4\n"
        + "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "1\n"
        + "Enter the direction to move the player\n"
        + "North\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "1\n"
        + "Enter the direction to move the player\n"
        + "East\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "1\n"
        + "Enter the direction to move the player\n"
        + "North\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "No Arrow in this cave\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "1\n"
        + "Enter the direction to move the player\n"
        + "North\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your South\n"
        + "\n"
        + "No treasure in this cave\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "2\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "East\n"
        + "Enter distance\n"
        + "1\n"
        + "You injured an Otyugh!\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "1\n"
        + "Enter the direction to move the player\n"
        + "East\n"
        + "You Lost! Player was killed by injured Otyugh\n";
    assertEquals(expected,output.toString());
  }

  @Test
  public void testPlayerArrows() {
    Readable input = new StringReader("1 North 4 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Arrow count: 4\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected, output.toString());
  }

  @Test
  public void testPlayerTreasure() {
    Readable input = new StringReader("1 North 3 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Diamond collected: 0\n"
        + "Sapphires collected: 1\n"
        + "Rubies collected: 0\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected, output.toString());
  }

  @Test
  public void testQuit() {
    Readable input = new StringReader("5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected, output.toString());

  }

  @Test
  public void testStrongSmell() {
    Readable input = new StringReader("1 North 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected,output.toString());

  }

  @Test
  public void testInjuredOtyugh() {
    Readable input = new StringReader("2 North 2 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "You injured an Otyugh!\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected,output.toString());
  }

  @Test
  public void testDeadOtyugh() {
    Readable input = new StringReader("2 North 2 2 North 2 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "You injured an Otyugh!\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "Otyugh is dead! You are free to move\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected,output.toString());
  }

  @Test
  public void testArrowShoot() {
    Readable input = new StringReader("2 East 2 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "Arrow disappeared in space\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";

    assertEquals(expected,output.toString());
  }

  @Test
  public void testPlayerLoses() {
    Readable input = new StringReader("1 North 1 North");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "You Lost! Player was killed by Otyugh\n";
    assertEquals(expected,output.toString());
  }

  @Test
  public void testPlayerNavigating() {
    Readable input = new StringReader("1 North 1 East 1 North 1 East 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "No Arrow in this cave\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a door to your East\n"
        + "There is a door to your West\n"
        + "There is a path to your North\n"
        + "There is a path to your South\n"
        + "\n"
        + "Player found SAPPHIRES\n"
        + "Player found an arrow!\n"
        + "Strong pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected,output.toString());
  }

  @Test
  public void testInvalidMove() {
    Readable input = new StringReader("1 South 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction to move the player\n"
        + "Not a valid direction\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected,output.toString());
  }


  @Test
  public void testInvalidDistance() {
    Readable input = new StringReader("2 North 50 5");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel);
    String expected = "Starting point: 12\n"
        + "Player location: 12\n"
        + "\n"
        + "********Doors available******\n"
        + "There is a path to your North\n"
        + "\n"
        + "No treasure in this cave\n"
        + "No Arrow in this cave\n"
        + "Less pungent smell\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n"
        + "Enter the direction you want to shoot an arrow\n"
        + "Enter distance\n"
        + "Arrow disappeared in space\n"
        + "------------------------\n"
        + "Select one option\n"
        + "------------------------\n"
        + "1. Move the player\n"
        + "2. Shoot an arrow\n"
        + "3. View treasure collected\n"
        + "4. View arrows\n"
        + "5. Quit\n"
        + "------------------------\n";
    assertEquals(expected, output.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDimensions() {
    Dungeon dungeonModel1 = new Dungeon(2, 4,
        5, false, 2, 50, fakeRandom);
    Readable input = new StringReader("2");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEntry() {
    Dungeon dungeonModel1 = new Dungeon(4, 4,
        -5, false, 2, 50, fakeRandom);
    Readable input = new StringReader("2");
    Appendable output = new StringBuilder();
    DungeonController d = new DungeonConsoleController(input, output);
    d.playGame(dungeonModel1);
  }

  @Test
  public void testGraphicalController() {
    StringBuilder log = new StringBuilder();
    DungeonView view = new MockView(log);
    ViewController controller = new DungeonViewController(dungeonModel, view);
    controller.handleKeyPressed("North");
    controller.handleKeyPressed1("North",1);
    controller.clickMove(1,2);
    controller.newGame();
    String expected = "controller added\n"
        + "key listener\naction triggered\nmessage is showing\nrefresh"
        + "\nmessage is showing\nrefresh\n"
        + "message is showing\nrefresh\n"
        + "disposing\n";
    assertEquals(expected,log.toString());
  }

}