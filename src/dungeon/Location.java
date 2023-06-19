package dungeon;

/**
 * Location interface has all the operations supported by a cave or a tunnel.
 */
public interface Location {

  /**
   * Sets a connection to the location east of the current location.
   *
   * @param east if location exists to the east.
   */
  void setEast(boolean east);

  /**
   * Sets a connection to the location west of the current location.
   *
   * @param west if location exists to the east.
   */
  void setWest(boolean west);

  /**
   * Sets a connection to the location north of the current location.
   *
   * @param north if location exists to the east.
   */
  void setNorth(boolean north);

  /**
   * Sets a connection to the location south of the current location.
   *
   * @param south if location exists to the east.
   */
  void setSouth(boolean south);

  /**
   * Returns id of the location.
   *
   * @return id of the location
   */

  int getId();

  /**
   * Returns boolean value if there is a connection to the north.
   *
   * @return boolean value for north location
   */
  boolean isNorth();

  /**
   * Returns boolean value if there is a connection to the south.
   *
   * @return boolean value for south location
   */

  boolean isSouth();

  /**
   * Returns boolean value if there is a connection to the east.
   *
   * @return boolean value for east location
   */
  boolean isEast();

  /**
   * Returns boolean value if there is a connection to the west.
   *
   * @return boolean value for west location
   */

  boolean isWest();

  /**
   * Sets a cave as a tunnel if it only has two exits.
   *
   * @param tunnel boolean value for tunnel
   */

  void setTunnel(boolean tunnel);

  /**
   * Returns existence of tunnel in boolean form.
   *
   * @return boolean value for tunnel
   */

  boolean isTunnel();

  /**
   * Sets treasure in the caves.
   *
   * @param treasure type of treasure
   */

  void setTreasure(Treasure treasure);

  /**
   * Returns the treasure in the cave.
   *
   * @return treasure in the cave
   */

  Treasure getTreasure();

  /**
   * Check if location has arrow.
   *
   * @return boolean value
   */

  boolean hasArrow();

  /**
   * Set arrow for the cave.
   *
   * @param arrow boolean value
   */

  void setArrow(boolean arrow);

  /**
   * Set otyugh for the cave.
   *
   * @param otyugh boolean value
   */

  void setOtyugh(boolean otyugh);

  /**
   * Check if location has otyugh.
   *
   * @return boolean value
   */

  boolean hasOtyugh();

  /**
   * Monster at that location.
   *
   * @return Monster at the location
   */

  Monster getMonster();

  /**
   * Add monster to the location.
   */

  void addMonster();

  /**
   * Set smell to the location.
   *
   * @param smell degree
   */

  void setSmell(int smell);

  /**
   * Get the smell of the location.
   *
   * @return smell degree
   */

  int hasSmell();

  /**
   * Set he locations visited by the player.
   *
   * @param visited locations visited
   */

  void setVisited(boolean visited);

  /**
   * Get the locations that player visited.
   *
   * @return location
   */
  boolean getVisited();

  /**
   * Get if the location has treasure.
   *
   * @return boolean value
   */

  boolean hasTreasure();

  /**
   * Set treasure chest in the location.
   *
   * @param chest boolean value
   */

  void setChest(boolean chest);

  /**
   * Set the pit in the game.
   *
   * @param pit pit
   */
  void setPit(boolean pit);

  /**
   * Check if location has pit.
   *
   * @return boolean
   */

  boolean hasPit();

  /**
   * Set wind in the pit.
   *
   * @param wind wind
   */

  void setWind(boolean wind);

  /**
   * Check if location has wind.
   *
   * @return boolean
   */

  boolean getWind();

}
