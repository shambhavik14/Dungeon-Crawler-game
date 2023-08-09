package view;

import dungeon.ReadOnlyModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is view class that displays the player statistics on the view.
 */
public class InformationPanel extends JPanel {

  private ReadOnlyModel model;
  private final JLabel introLabel;
  private final JLabel diamondCountLabel;
  private final JLabel rubyCountLabel;
  private final JLabel sapphireCountLabel;
  private final JLabel arrowCountLabel;

  /**
   * The constructor of this class takes read only model as the argument.
   *
   * @param model model of the game
   */

  public InformationPanel(ReadOnlyModel model) {
    this.model = model;
    JLabel myLabel = new JLabel();
    introLabel = new JLabel();
    diamondCountLabel = new JLabel();
    rubyCountLabel = new JLabel();
    sapphireCountLabel = new JLabel();
    arrowCountLabel = new JLabel();
    this.add(introLabel);
    this.add(diamondCountLabel);
    this.add(rubyCountLabel);
    this.add(sapphireCountLabel);
    this.add(arrowCountLabel);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    this.setPreferredSize(new Dimension(350, 400));
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.setOpaque(true);
    String ss = "";

    Font f = new Font("Georgia", Font.BOLD, 19);
    introLabel.setFont(f);
    introLabel.setForeground(Color.white);
    introLabel.setBounds(10, 10, 350, 50);
    introLabel.setText("PLAYER STATISTICS");
    try {
      Image diamondImage = ImageIO.read(new File("icons\\diamond1.png"));
      Image diamondImage1 = diamondImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
      g2d.drawImage(diamondImage1, 10, 80, null);
      int diamondCount = model.getDiamondCount();
      diamondCountLabel.setFont(f);
      diamondCountLabel.setForeground(Color.white);
      diamondCountLabel.setBounds(100, 80, 50, 30);
      diamondCountLabel.setText(diamondCount + "");
      Image rubyImage = ImageIO.read(new File("icons\\ruby1.png"));
      Image rubyImage1 = rubyImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
      g2d.drawImage(rubyImage1, 10, 120, null);
      int rubyCount = model.getRubiesCount();
      rubyCountLabel.setFont(f);
      rubyCountLabel.setForeground(Color.white);
      rubyCountLabel.setBounds(100, 120, 50, 30);
      rubyCountLabel.setText(rubyCount + "");
      Image sapphireImage = ImageIO.read(new File("icons\\sapphire1.png"));
      Image sapphireImage1 = sapphireImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
      g2d.drawImage(sapphireImage1, 10, 160, null);
      int sapphireCount = model.getSapphireCount();
      sapphireCountLabel.setFont(f);
      sapphireCountLabel.setForeground(Color.white);
      sapphireCountLabel.setBounds(100, 160, 50, 30);
      sapphireCountLabel.setText(sapphireCount + "");
      Image arrowImage = ImageIO.read(new File("icons\\arrow1.png"));
      Image arrowImage1 = arrowImage.getScaledInstance(50, 10, Image.SCALE_DEFAULT);
      g2d.drawImage(arrowImage1, 10, 210, null);
      int arrowCount = model.getArrowCount();
      arrowCountLabel.setFont(f);
      arrowCountLabel.setForeground(Color.white);
      arrowCountLabel.setBounds(100, 195, 50, 30);
      arrowCountLabel.setText(arrowCount + "");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Updates the read only model of the class.
   *
   * @param model1 model of the class
   */
  public void updateModel(ReadOnlyModel model1) {
    this.model = model1;
  }

}
