package dungeon;

/**
 * This class implements Monster interface. It is represented by hits
 * the monster takes and if he is alive or not.
 */

class Otyugh implements Monster {

  boolean alive = true;
  int hitCount = 0;
  boolean halfinjury = false;

  @Override
  public boolean isAlive() {
    return alive;
  }

  @Override
  public boolean halfInjured() {
    return halfinjury;
  }

  @Override
  public String takeHit() {
    String result;
    if (hitCount == 0) {
      hitCount += 1;
      alive = true;
      halfinjury = true;
      result = "Great shot! Otyugh is injured!";
    } else {
      result = "Great shot again! Otyugh is dead!";
      alive = false;
    }
    return result;
  }


}
