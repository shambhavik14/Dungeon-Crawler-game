package controller;

import dungeon.DungeonModel;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the controller for the dungeon model game.
 */

public class DungeonConsoleController implements DungeonController {

  private final Appendable out;
  private final Scanner scan;


  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(DungeonModel d) {
    if (d == null) {
      throw new IllegalArgumentException("model can't be null");
    }


    try {
      this.out.append("Starting point: ").append(String.valueOf(d.getStart().getId()));
      this.out.append("\n");
      this.out.append("Player location: ").append(String.valueOf(d.getPlayerStartLocation()));
      this.out.append("\n");
      this.out.append(d.getPaths());
      this.out.append("\n");
      this.out.append(d.getTreasureAtLocation());
      this.out.append("\n");
      d.collectStartTreasure();
      if (d.getStart().hasSmell() == 2) {
        this.out.append("Strong pungent smell");
        this.out.append("\n");
      } else if (d.getStart().hasSmell() == 1) {
        this.out.append("Less pungent smell");
        this.out.append("\n");
      } else {
        this.out.append("No smell");
        this.out.append("\n");
      }
      if (d.getStart().getWind()) {
        this.out.append("There is a pit nearby");
        this.out.append("\n");
      }

      boolean run;
      while (!d.getDestination().equals(d.getLocation())) {
        this.out.append("------------------------");
        this.out.append("\n");
        this.out.append("Select one option");
        this.out.append("\n");
        this.out.append("------------------------");
        this.out.append("\n");
        this.out.append("1. Move the player");
        this.out.append("\n");
        this.out.append("2. Shoot an arrow");
        this.out.append("\n");
        this.out.append("3. View treasure collected");
        this.out.append("\n");
        this.out.append("4. View arrows");
        this.out.append("\n");
        this.out.append("5. Quit");
        this.out.append("\n");
        this.out.append("------------------------");
        this.out.append("\n");

        String choice = scan.next();
        //scan.nextLine();

        switch (choice) {
          case "1":
            this.out.append("Enter the direction to move the player");
            this.out.append("\n");
            try {
              String direction = scan.next();
              d.setDirection(direction);
            } catch (IllegalStateException is) {
              this.out.append("Not a valid direction");
              this.out.append("\n");
            }
            if (d.getLocation().hasPit()) {
              this.out.append(d.playerPit());
              this.out.append("\n");
              return;
            }

            if (d.getLocation().hasOtyugh()) {
              if (d.getLocation().getMonster().halfInjured()
                  && d.getLocation().getMonster().isAlive()) {
                int life = d.playerSurvival();
                if (life == 1) {
                  this.out.append("Player survived the injured Otyugh");
                  this.out.append("\n");
                } else {
                  this.out.append("You Lost! Player was killed by injured Otyugh");
                  this.out.append("\n");
                  return;
                }
              } else if (!d.getLocation().getMonster().halfInjured()
                  && d.getLocation().getMonster().isAlive()) {
                this.out.append("You Lost! Player was killed by Otyugh");
                this.out.append("\n");
                return;
              }
            }
            this.out.append(d.getPaths());
            this.out.append("\n");
            this.out.append(d.getTreasureAtLocation());
            this.out.append("\n");
            if (d.getLocation().hasTreasure() || d.getLocation().hasArrow()) {
              this.out.append("Do you want to pick the treasure?");
              this.out.append("\n");
              String answer = scan.next();
              if (answer.equals("yes") || answer.equals("Yes")) {
                d.collectTreasure();
              }
            }
            if (d.getLocation().hasSmell() == 2) {
              this.out.append("Strong pungent smell");
              this.out.append("\n");
            } else if (d.getLocation().hasSmell() == 1) {
              this.out.append("Less pungent smell");
              this.out.append("\n");
            } else {
              this.out.append("No smell");
              this.out.append("\n");
            }
            if (d.getLocation().getWind()) {
              this.out.append("There is a pit nearby");
              this.out.append("\n");
            }
            break;
          case "2":
            this.out.append("Enter the direction you want to shoot an arrow");
            this.out.append("\n");
            final String arrowDirection = scan.next();
            this.out.append("Enter distance");
            this.out.append("\n");
            int arrowDistance = scan.nextInt();
            this.out.append(d.arrowHit(arrowDirection, arrowDistance));
            this.out.append("\n");
            break;
          case "3":
            this.out.append("Diamond collected: ").append(String.valueOf(d.getDiamondCount()));
            this.out.append("\n");
            this.out.append("Sapphires collected: ").append(String.valueOf(d.getSapphireCount()));
            this.out.append("\n");
            this.out.append("Rubies collected: ").append(String.valueOf(d.getRubiesCount()));
            this.out.append("\n");
            break;
          case "4":
            this.out.append("Arrow count: ").append(String.valueOf(d.getArrowCount()));
            this.out.append("\n");
            break;
          case "5":
            return;
          default:
            return;
        }
      }
      this.out.append("You Won! Player has reached the destination");
      this.out.append("\n");
      return;
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

  }


}