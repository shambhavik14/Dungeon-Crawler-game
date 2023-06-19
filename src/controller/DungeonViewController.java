package controller;

import dungeon.Dungeon;
import dungeon.DungeonModel;
import dungeon.RandomInteger;
import view.DungeonSwingView;
import view.DungeonView;


/**
 * This is the controller class which is used for displaying the game on screen.
 */

public class DungeonViewController implements ViewController {

  private DungeonModel model;
  private DungeonModel model1;
  private DungeonModel reset;
  private DungeonView view;

  /**
   * The constructor of the class takes model and view as the arguments.
   *
   * @param model model of the game
   * @param view  view of the game
   */

  public DungeonViewController(DungeonModel model, DungeonView view) {
    this.model = model;
    this.view = view;
    model1 = model;
    reset = new Dungeon((Dungeon)model);
    view.addClickListener(this);
    view.addKeyListener(this);
    view.addActionListener(this);
  }


  @Override
  public void playGame(DungeonModel m) {
    view.makeVisible();
    view.refresh();
  }

  @Override
  public void handleKeyPressed(String direction) {
    model.setDirection(direction);
    String sc = model.playerMonster();
    view.showMessage(sc);
    view.refresh();
  }

  @Override
  public void handleKeyPressed1(String arrowDirection, int distance) {
    String sc = model.arrowHit(arrowDirection, distance);
    view.showMessage(sc);
    view.refresh();
  }

  @Override
  public void handleTreasurePressed() {
    model.collectTreasure();
    view.refresh();
  }

  @Override
  public void makeModel(int height, int width, int interconnectivity, boolean isWrapping,
                        int otyughNumber, int percent, RandomInteger random) {
    try {
      model = new Dungeon(height, width, interconnectivity,
          isWrapping, otyughNumber, percent, random);
    }
    catch (IllegalArgumentException ioe) {
      throw new IllegalArgumentException(ioe.getMessage());
    }
    view.update(model);
    view.makeVisible();
    view.refresh();
  }

  @Override
  public void newGame() {
    model1 = new Dungeon();
    view.shutDown();
    view = new DungeonSwingView(model1);
    view.addClickListener(this);
    view.addKeyListener(this);
    view.addActionListener(this);
    view.makeVisible();
    view.refresh();
  }

  @Override
  public void clickMove(int i, int j) {
    String direction = model.giveDirection(i, j);
    model.setDirection(direction);
    String sc = model.playerMonster();
    view.showMessage(sc);
    view.refresh();
  }

  @Override
  public void reset() {
    model = reset;
    view.refresh();
  }

}
