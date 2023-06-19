package dungeon;

/**
 * This class implements the Player interface. A player moves from one location to another
 * in order to reach the destination.
 */

public class GamePlayer implements Player {

  private int treasureCount;
  private Location playerLocation;

  /**
   * Constructor of the GamePlayer class.
   */

  @Override
  public void setPlayerLocation(Location location) {
    this.playerLocation = location;
  }

  @Override
  public Location getPlayerLocation() {
    return playerLocation;
  }

}
