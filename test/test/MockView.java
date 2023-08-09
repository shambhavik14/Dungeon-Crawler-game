package test;

import controller.ViewController;
import dungeon.ReadOnlyModel;
import view.DungeonView;

import java.io.IOException;

/**
 * This is a mock view to test the view of the game.
 */

public class MockView implements DungeonView {
  private Appendable log;

  public MockView(Appendable log) {
    this.log = log;
  }

  @Override
  public void addClickListener(ViewController listener) {
    try {
      log.append("controller added\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void addKeyListener(ViewController listener) {
    try {
      log.append("key listener\n");
    }
    catch (IOException e) {
      //empty
    }
  }

  @Override
  public void refresh() {
    try {
      log.append("refresh\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void makeVisible() {
    try {
      log.append("visible\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void showMessage(String sc) {
    try {
      log.append("message is showing\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void shutDown() {
    try {
      log.append("disposing\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void addActionListener(ViewController listener) {
    try {
      log.append("action triggered\n");
    } catch (IOException e) {
      //empty
    }
  }

  @Override
  public void update(ReadOnlyModel model1) {
    try {
      log.append("updating model\n");
    } catch (IOException e) {
      //empty
    }
  }
}
