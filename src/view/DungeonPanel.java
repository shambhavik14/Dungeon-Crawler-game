package view;

import dungeon.ReadOnlyModel;
import dungeon.Treasure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is responsible for displaying the dungeon in the view.
 */

public class DungeonPanel extends JPanel {

  private ReadOnlyModel model;

  /**
   * The constructor of the class takes read only model as argument.
   *
   * @param model model of the game.
   */
  public DungeonPanel(ReadOnlyModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    this.setLayout(new FlowLayout());
    this.setBackground(Color.BLACK);
    int wid = model.getWidth();
    int hei = model.getHeight();
    this.setPreferredSize(new Dimension(wid * 60, hei * 60));
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        String sb = helper(i, j);
        try {
          BufferedImage image = null;
          if (model.getCaveLoc(i, j).getVisited()) {
            image = ImageIO.read(new File("icons\\" + sb + ".png"));
          }
          BufferedImage image1;
          BufferedImage image2;
          BufferedImage image3;
          BufferedImage image4;
          BufferedImage image5;
          BufferedImage image6;
          BufferedImage image7;
          BufferedImage image8;
          BufferedImage image9;
          BufferedImage image10;

          Image newImage = null;
          Image newImage1 = null;
          Image newImage2 = null;
          Image newImage3 = null;
          Image newImage4 = null;
          Image newImage5 = null;
          Image newImage6 = null;
          Image newImage7 = null;
          Image newImage8 = null;
          Image newImage9 = null;


          if (model.getCaveLoc(i, j).getVisited() && model.getCaveLoc(i, j).hasOtyugh()
              && model.getCaveLoc(i, j).getMonster().isAlive()) {
            image1 = ImageIO.read(new File("icons\\otyugh.png"));
            newImage = image1.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          }

          if (model.getCaveLoc(i, j) == model.getLocation()) {
            image2 = ImageIO.read(new File("icons\\player.jpg"));
            newImage1 = image2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
          }

          if (model.getCaveLoc(i, j).getVisited()
              && model.getCaveLoc(i, j).getTreasure() == Treasure.RUBY) {
            image3 = ImageIO.read(new File("res\\icons\\ruby.png"));
            newImage2 = image3.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i, j).getVisited()
              && model.getCaveLoc(i, j).getTreasure() == Treasure.SAPPHIRES) {
            image4 = ImageIO.read(new File("res\\icons\\sapphire.png"));
            newImage3 = image4.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i, j).getVisited()
              && model.getCaveLoc(i, j).getTreasure() == Treasure.DIAMONDS) {
            image5 = ImageIO.read(new File("res\\icons\\diamond.png"));
            newImage4 = image5.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i, j).getVisited() && model.getCaveLoc(i, j).hasArrow()) {
            image6 = ImageIO.read(new File("res\\icons\\arrow.png"));
            newImage5 = image6.getScaledInstance(10, 20, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i, j).getVisited() && model.getCaveLoc(i, j).hasSmell() == 1) {
            image7 = ImageIO.read(new File("res\\icons\\lesspungent.png"));
            newImage6 = image7.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i, j).getVisited() && model.getCaveLoc(i, j).hasSmell() == 2) {
            image8 = ImageIO.read(new File("res\\icons\\strongsmell.png"));
            newImage7 = image8.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          }
          if (model.getCaveLoc(i,j).getVisited() && model.getCaveLoc(i,j).hasPit()) {
            image9 = ImageIO.read(new File("res\\icons\\pit.png"));
            newImage8 = image9.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          }

          if (model.getCaveLoc(i,j).getVisited() && model.getCaveLoc(i,j).getWind()) {
            image10 = ImageIO.read(new File("res\\icons\\windy.png"));
            newImage9 = image10.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
          }

          g.drawImage(image, j * (60), i * (60), this);
          g.drawImage(newImage, (j * 60) + 20, (i * 60) + 15, this);
          g.drawImage(newImage1, (j * 60) + 20, (i * 60) + 15, this);
          g.drawImage(newImage2, (j * 60) + 30, i * (60) + 40, this);
          g.drawImage(newImage3, (j * 60) + 40, i * (60) + 40, this);
          g.drawImage(newImage4, (j * 60) + 20, i * (60) + 40, this);
          g.drawImage(newImage5, (j * 60) + 10, i * (60) + 5, this);
          g.drawImage(newImage6, (j * 60) + 20, (i * 60) + 15, this);
          g.drawImage(newImage7, (j * 60) + 20, (i * 60) + 15, this);
          g.drawImage(newImage8, (j * 60) + 20, (i * 60) + 10, this);
          g.drawImage(newImage9, (j * 60) + 40, (i * 60) + 10, this);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private String helper(int i, int j) {
    StringBuilder s = new StringBuilder();
    if (model.getCaveLoc(i, j).isNorth()) {
      s.append("N");
    }
    if (model.getCaveLoc(i, j).isSouth()) {
      s.append("S");
    }
    if (model.getCaveLoc(i, j).isEast()) {
      s.append("E");
    }
    if (model.getCaveLoc(i, j).isWest()) {
      s.append("W");
    }
    return s.toString();
  }

  /**
   * Updates the readonly model.
   *
   * @param model1 model of the game
   */
  public void updateModel(ReadOnlyModel model1) {
    this.model = model1;
  }
}

