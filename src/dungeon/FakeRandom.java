package dungeon;

import java.util.ArrayList;

/**
 * This class implements RandomInteger and generates random number from list.
 */

public class FakeRandom implements RandomInteger {

  ArrayList<Integer> arrayList;
  private int count;

  /**
   * Constructor of FakeRandom class.
   *
   * @param number array of elements
   */

  public FakeRandom(int... number) {
    arrayList = new ArrayList<Integer>();
    for (int i : number) {
      arrayList.add(i);
    }
    count = 0;
  }


  @Override
  public int nextInt(int bound) {
    return arrayList.get(count++);
  }


}
