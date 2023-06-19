package controller;

import dungeon.DungeonModel;
import dungeon.RandomInteger;

import java.io.IOException;

/**
 * This interface is for the controller when we want to display the game.
 */

public interface ViewController {

  /**
   * This method is responsible for the functionalities of playing the game.
   *
   * @param m model of the game
   */
  void playGame(DungeonModel m);

  /**
   * This method is used to move the player in the game.
   *
   * @param direction direction
   */
  void handleKeyPressed(String direction);

  /**
   * This method is used to shoot an arrow.
   *
   * @param arrowDirection direction
   * @param distance       distance
   */

  void handleKeyPressed1(String arrowDirection, int distance);

  /**
   * This method is used to create a new model.
   *
   * @param height            height
   * @param width             width
   * @param interconnectivity interconnectivity
   * @param isWrapping        wrapping
   * @param otyughNumber      otyughs
   * @param percent           cave percantage
   * @param random            random
   */

  void makeModel(int height, int width, int interconnectivity, boolean isWrapping,
                 int otyughNumber, int percent,
                 RandomInteger random);


  /**
   * This method is used to pick the treasure.
   */
  void handleTreasurePressed();

  /**
   * This method creates new game.
   *
   * @throws IOException io exception
   */

  void newGame();

  /**
   * This method moves the player on mouse click.
   *
   * @param i row
   * @param j column
   */

  void clickMove(int i, int j);

  /**
   * Resets the dungeon with same values.
   */
  void reset();
}
