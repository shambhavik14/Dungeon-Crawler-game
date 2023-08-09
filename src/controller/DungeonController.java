package controller;

import dungeon.DungeonModel;

/**
 * Represents a Controller for Dungeon game: handle user entries by executing them using the model;
 * convey outcomes to the user in some form.
 */

public interface DungeonController {

  /**
   * Execute a single game of dungeon given a dungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param model a non-null dungeon Model
   */
  void playGame(DungeonModel model);

}
