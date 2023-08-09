package dungeon;

/**
 * This is the read only interface of the model which is used by the view.
 */
public interface ReadOnlyModel {

  /**
   * Returns starting point.
   *
   * @return start location
   */

  Location getStart();

  /**
   * Returns destination point.
   *
   * @return destination location
   */
  Location getDestination();

  /**
   * Sets the direction in which the player can move when in a cave.
   *
   * @param direction which direction player wants to move.
   *                  //   * @param player object of GamePlayer class.
   *                  //   * @param id id of the cave.
   * @return current location of the player.
   */

  int setDirection(String direction);

  /**
   * Count of diamonds collected by the player.
   *
   * @return diamond count.
   */

  int getDiamondCount();

  /**
   * Count of sapphires collected by the player.
   *
   * @return sapphire count.
   */

  int getSapphireCount();

  /**
   * Count of rubies collected by the player.
   *
   * @return ruby count.
   */

  int getRubiesCount();

  /**
   * Count of arrows collected by the player.
   *
   * @return arrow count.
   */
  int getArrowCount();


  /**
   * Implements traversal of arrow based on the direction and distance.
   *
   * @param direction direction in which arrow travels
   * @param distance  distance at which arrow travels
   * @return location of arrow
   */

  Location arrowTravel(String direction, int distance);


  /**
   * Check if the arrow hit the monster.
   *
   * @param location location on arrow
   * @return status of monster
   */
  String arrowMonster(Location location);


  /**
   * Pick up treasure from the cave.
   *
   * @return treasure in the cave
   */

  String getTreasureAtLocation();

  /**
   * Collect treasure from start location.
   */
  void collectStartTreasure();


  /**
   * Get location of the player.
   *
   * @return location of the player
   */

  Location getLocation();

  /**
   * Get cave location at i,j.
   *
   * @param i row of the dungeon
   * @param j column of the dungeon
   * @return location
   */

  Location getCaveLoc(int i, int j);

  /**
   * The status of the player when he enters cave with otyugh.
   *
   * @return status
   */

  String playerMonster();

  /**
   * Get row of player location.
   *
   * @return row
   */

  int getPlayerX();

  /**
   * Get column of player location.
   *
   * @return column
   */

  int getPlayerY();

  /**
   * Get direction in which the player wants to mov.e
   *
   * @param i row
   * @param j column
   * @return direction
   */

  String giveDirection(int i, int j);

  /**
   * Get height of dungeon.
   *
   * @return height
   */

  int getHeight();

  /**
   * Get width of the dungeon.
   *
   * @return width
   */

  int getWidth();

  /**
   * Status of arrow shot by player.
   *
   * @param direction direction
   * @param distance  distance
   * @return status of arrow
   */

  String arrowHit(String direction, int distance);

  /**
   * Pick up the treasure from location.
   */

  void collectTreasure();


}
