package view;

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
 * This class is responsible for the instruction panel in the view.
 */

public class InstructionPanel extends JPanel {

  private final JLabel title;
  private final JLabel move;
  private final JLabel shoot;
  private final JLabel pick;

  /**
   * This is the constructor of the class.
   */
  public InstructionPanel() {
    title = new JLabel();
    move = new JLabel();
    shoot = new JLabel();
    pick = new JLabel();
    this.add(title);
    this.add(move);
    this.add(shoot);
    this.add(pick);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    this.setPreferredSize(new Dimension(770, 200));
    this.setLayout(null);
    this.setBackground(new Color(0, 38, 77));
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    title.setBounds(320, 10, 200, 30);
    Font f = new Font("Georgia", Font.BOLD, 20);
    title.setFont(f);
    title.setForeground(Color.white);
    title.setText("HOW TO PLAY");
    move.setBounds(20, 30, 200, 50);
    Font f1 = new Font("Georgia", Font.BOLD, 17);
    move.setFont(f1);
    move.setForeground(Color.white);
    move.setText("MOVE PLAYER: ");
    shoot.setBounds(20, 90, 200, 50);
    shoot.setFont(f1);
    shoot.setForeground(Color.white);
    shoot.setText("SHOOT ARROW: ");
    pick.setBounds(20, 150, 200, 50);
    pick.setFont(f1);
    pick.setForeground(Color.white);
    pick.setText("PICK TREASURE: ");

    try {
      Image keys = ImageIO.read(new File("icons\\keys.png"));
      Image keys1 = keys.getScaledInstance(60, 40, Image.SCALE_DEFAULT);
      Image ctrl = ImageIO.read(new File("icons\\ctrl.jpg"));
      Image ctrl1 = ctrl.getScaledInstance(35, 20, Image.SCALE_DEFAULT);
      Image key = ImageIO.read(new File("icons\\keys.png"));
      Image key1 = keys.getScaledInstance(60, 40, Image.SCALE_DEFAULT);
      Image pick = ImageIO.read(new File("icons\\P.jpg"));
      Image pick1 = pick.getScaledInstance(30, 20, Image.SCALE_DEFAULT);
      g.drawImage(keys1, 210, 35, this);
      g.drawImage(ctrl1, 210, 100, this);
      g.drawImage(key1, 280, 85, this);
      g.setColor(Color.white);
      g.drawString("+", 260, 115);

      g.drawImage(pick1, 210, 165, this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
