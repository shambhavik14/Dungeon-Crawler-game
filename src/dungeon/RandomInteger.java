package dungeon;

/**
 * This interface is used for the random number generation.
 */

public interface RandomInteger {

  /**
   * Gives the next random integer value.
   *
   * @param bound upperlimit of the number
   * @return bound
   */
  int nextInt(int bound);

}
