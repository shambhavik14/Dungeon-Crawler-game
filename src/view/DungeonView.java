package view;

import controller.ViewController;
import dungeon.ReadOnlyModel;

/**
 * This is the interface for the view. It is responsible for displaying the game.
 */

public interface DungeonView {

  /**
   * This method is used to click on the screen.
   *
   * @param listener controller
   */

  void addClickListener(ViewController listener);

  /**
   * This method is used for key press.
   *
   * @param listener controller
   */

  void addKeyListener(ViewController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Show the status of the game on the screen.
   *
   * @param sc status
   */

  void showMessage(String sc);

  /**
   * Dispose the view.
   */

  void shutDown();

  /**
   * Performs an action on any event.
   *
   * @param listener controller
   */

  void addActionListener(ViewController listener);

  /**
   * Update the readonly models in panel.
   *
   * @param model1 model of the game
   */

  void update(ReadOnlyModel model1);

}
