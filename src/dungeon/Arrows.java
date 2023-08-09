package dungeon;

/**
 * This class extends Weapon interface. This class represents arrow location.
 */

class Arrows implements Weapon {
  Location arrowLocation;

  /**
   * Sets the location of arrows.
   *
   * @param location arrow location
   */

  @Override
  public void setArrowLocation(Location location) {
    this.arrowLocation = location;
  }


  /**
   * Get the location of arrows.
   *
   * @return arrow location
   */
  @Override
  public Location getArrowLocation() {
    return arrowLocation;
  }

}
