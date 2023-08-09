package dungeon;

/**
 * This is a player interface that has all the operations supported by the player in the game.
 */
interface Player {

  /**
   * Sets the current location of the player.
   *
   * @param location position where the player is currently
   */
  void setPlayerLocation(Location location);

  /**
   * Returns the current location of the player.
   *
   * @return location
   */
  Location getPlayerLocation();

}
