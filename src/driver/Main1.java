package driver;

import controller.DungeonConsoleController;
import controller.DungeonViewController;
import controller.ViewController;
import dungeon.Dungeon;
import dungeon.DungeonModel;
import dungeon.FakeRandom;
import dungeon.RandomInteger;
import dungeon.TrueRandom;
import view.DungeonSwingView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class for the game which uses mvc model architecture.
 */

public class Main1 {

  /**
   * Constructor of the class.
   * @param args arguments
   * @throws IOException exception
   */

  public static void main(String[] args) throws IOException {

    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    Scanner sc = new Scanner(input);
    RandomInteger randomInteger = new FakeRandom(0, 1, 2, 3, 4, 0, 3, 2, 0,
        1, 2, 1, 1, 1, 1, 0, 2, 1, 3, 1, 1, 1, 0, 1, 2, 0, 1,
        3, 2, 1, 2, 0, 1, 1, 0, 1, 3, 2, 0, 1, 0, 2, 0);
    RandomInteger random = new TrueRandom();

    if (args.length > 0) {
      if (args[0].equals("console")) {
        while (true) {
          try {
            System.out.println("Enter the height of the dungeon");
            final int height = Integer.parseInt(sc.next());
            sc.nextLine();

            System.out.println("Enter the width of the dungeon");
            final int width = Integer.parseInt(sc.next());

            System.out.println("Enter the interconnectivity of the dungeon");
            final int interconnectivity = Integer.parseInt(sc.next());

            System.out.println("Is your dungeon wrapping?(true or false)");
            final boolean wrapping = sc.nextBoolean();

            sc.nextLine();
            System.out.println("Percentage of caves with treasure");
            final int percent = Integer.parseInt(sc.next());

            System.out.println("Number of Otyugh");
            final int number = Integer.parseInt(sc.next());

            new DungeonConsoleController(input, output).playGame(new Dungeon(height,
                width, interconnectivity, wrapping, number, percent, random));
          } catch (InputMismatchException im) {
            output.append("Invalid!");
            output.append("\n");
          } catch (IllegalArgumentException ioe) {
            output.append("Invalid entry");
            output.append("\n");
            output.append(ioe.getMessage());
            output.append("\n");
            sc.reset();
          }
        }
      } else if (args[0].equals("gui")) {
        DungeonModel model = new Dungeon();
        DungeonSwingView view = new DungeonSwingView(model);
        ViewController controller = new DungeonViewController(model, view);
        controller.playGame(model);
      }
    }
    else {
      DungeonModel model = new Dungeon();
      DungeonSwingView view = new DungeonSwingView(model);
      ViewController controller = new DungeonViewController(model, view);
      controller.playGame(model);
    }
  }
}