package dungeon;

import java.util.List;

/**
 * This is the main interface of the model where all functionalities combine to form
 * a dungeon game. This interface implements all the operations that a dungeon would support.
 */
public interface DungeonModel extends ReadOnlyModel {


  /**
   * Calculates and returns the potential edges for non wrapping dungeon.
   *
   * @return List of potential edges.
   */

  List<Edge> getEdgesList();

  /**
   * Calculates and returns the potential edges for wrapping dungeon.
   *
   * @return List of potential edges.
   */

  List<Edge> getWrappingEdges();

  /**
   * Returns the edge list of the minimum spanning tree.
   */

  List<Edge> getResultEdge();

  /**
   * Creates the dungeon by assigning caves at each location and setting directions for each cave.
   */

  int getStartDestination();


  /**
   * Assigns caves as tunnels where there are only 2 directions.
   */
  void tunnel();


  /**
   * Display paths of the current cave.
   *
   * @return paths available
   */

  String getPaths();


  /**
   * Set arrow location as player location.
   */

  void setArrow();

  /**
   * set player location as start location.
   *
   * @return id of location
   */

  int getPlayerStartLocation();

  /**
   * Get chance of player survival.
   *
   * @return random number
   */

  int playerSurvival();

  /**
   * String for when player reaches a location with pit.
   * @return String
   */
  String playerPit();


}
