package dungeon;

/**
 * This interface represents the monsters in the game.
 */

public interface Monster {

  /**
   * Checks if the monster in the cave is alive.
   * @return boolean value
   */

  boolean isAlive();

  /**
   * Check how many times the monster has been hit.
   * @return number of hits
   */

  String takeHit();

  /**
   * Check if the monster is injured.
   * @return boolean value
   */

  boolean halfInjured();

}
