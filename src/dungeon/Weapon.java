package dungeon;

/**
 * This interface is used to represent the arrows in the dungeon.
 */
interface Weapon {
  /**
   * Sets the arrow location.
   * @param location arrow location
   */

  void setArrowLocation(Location location);

  /**
   * Get the arrow location.
   * @return arrow location
   */

  Location getArrowLocation();
}