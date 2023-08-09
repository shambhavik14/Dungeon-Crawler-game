package dungeon;

import java.util.Random;

/**
 * This class implements RandomInteger and random number.
 */

public class TrueRandom implements RandomInteger {

  private final Random random;

  /**
   * Constructor of TrueRandom class.
   */
  public TrueRandom() {
    this.random = new Random();
  }

  @Override
  public int nextInt(int bound) {
    return random.nextInt(bound);
  }


}
