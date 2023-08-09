package view;

import controller.ViewController;
import dungeon.RandomInteger;
import dungeon.ReadOnlyModel;
import dungeon.TrueRandom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 * This is the view class which implements all the functionalities of the view.
 */

public class DungeonSwingView extends JFrame implements DungeonView {

  String height;
  String width;
  String wrapping;
  String interconnectivity;
  String treasure;
  String otyughs;
  private final DungeonPanel dungeonPanel;
  private final InformationPanel infoPanel;
  JButton createD;
  JPanel myPanel;
  JLabel myLabel1;
  JMenu exitMenu;
  JMenuItem m1;
  JMenuItem m2;
  JMenuItem m3;


  /**
   * This is the constructor of the view class which takes read only model as argument.
   *
   * @param model model of the game
   */
  public DungeonSwingView(ReadOnlyModel model) {
    super("Dungeon Traveler");

    this.setSize(800, 800);
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.getContentPane().setBackground(new Color(204, 229, 255));

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    createD = new JButton("Create Dungeon");

    height = "0";
    width = "0";
    wrapping = "false";
    interconnectivity = "0";
    treasure = "0";
    otyughs = "0";

    JMenuBar myMenu = new JMenuBar();
    myMenu.add(settingsBar());
    exitMenu = new JMenu("Exit");
    m1 = new JMenuItem("Quit");
    m2 = new JMenuItem("New Game");
    m3 = new JMenuItem("Reuse Game");

    exitMenu.add(m1);
    exitMenu.add(m2);
    exitMenu.add(m3);
    myMenu.add(exitMenu);
    myMenu.add(createD);
    myPanel = new JPanel();
    myLabel1 = new JLabel();
    this.setJMenuBar(myMenu);
    dungeonPanel = new DungeonPanel(model);
    infoPanel = new InformationPanel(model);
    InstructionPanel instructionPanel = new InstructionPanel();
    JScrollPane scrollArea = new JScrollPane(dungeonPanel);
    scrollArea.setPreferredSize(new Dimension(400, 400));
    scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    myPanel.add(scrollArea);
    this.add(myPanel);
    this.add(infoPanel);
    this.add(instructionPanel);
  }


  private JMenu settingsBar() {
    JMenu gameMenu = new JMenu("Game Settings");

    JMenuItem m1 = new JMenuItem(new AbstractAction("Settings") {
      public void actionPerformed(ActionEvent e) {
        JTextField hei = new JTextField();
        JTextField wid = new JTextField();
        JTextField inter = new JTextField();
        JTextField wra = new JTextField();
        JTextField otyu = new JTextField();
        JTextField trea = new JTextField();
        Object[] fields = {
            "Enter height", hei,
            "Enter width", wid,
            "Is your dungeon wrapping?(true or false)", wra,
            "Enter interconnectivity", inter,
            "Enter percentage of caves with treasure", trea,
            "Enter number of Otyughs", otyu
        };
        JOptionPane.showConfirmDialog(null, fields, "Inputs", JOptionPane.OK_CANCEL_OPTION);
        height = hei.getText();
        width = wid.getText();
        wrapping = wra.getText();
        interconnectivity = inter.getText();
        otyughs = otyu.getText();
        treasure = trea.getText();
      }
    });
    gameMenu.add(m1);
    return gameMenu;
  }


  private int getheight() {
    return Integer.parseInt(height);
  }

  private int getwidth() {
    return Integer.parseInt(width);
  }

  private boolean getWrapping() {
    return Boolean.parseBoolean(wrapping);
  }

  private int getInterconnectivity() {
    return Integer.parseInt(interconnectivity);
  }

  private int getTreasure() {
    return Integer.parseInt(treasure);
  }

  private int getOtyugh() {
    return Integer.parseInt(otyughs);
  }


  @Override
  public void addClickListener(ViewController listener) {
    //this.listener = listener;
    JFrame myFrame = new JFrame();
    MouseAdapter clickAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        try {
          if (e.getSource() == createD) {
            RandomInteger random = new TrueRandom();
            listener.makeModel(getheight(), getwidth(), getInterconnectivity(),
                getWrapping(), getOtyugh(), getTreasure(), random);
            requestFocus();
          }
        }
        catch (IllegalArgumentException ia) {
          JOptionPane.showMessageDialog(myFrame, ia.getMessage());
        }

        try {
          if (e.getSource() == dungeonPanel) {
            int x = e.getX() / 60;
            int y = e.getY() / 60;
            listener.clickMove(x, y);
          }
        } catch (IllegalStateException ioe) {
          JOptionPane.showMessageDialog(myFrame, ioe.getMessage());
        } catch (IllegalArgumentException io) {
          JOptionPane.showMessageDialog(myFrame, "Game is Over!");
        }
      }
    };
    createD.addMouseListener(clickAdapter);
    dungeonPanel.addMouseListener(clickAdapter);
  }

  @Override
  public void addKeyListener(ViewController listener) {
    //this.listener = listener;
    JFrame myFrame = new JFrame();
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        // super.keyPressed(e);
        int key = e.getKeyCode();
        String direction = "";
        String arrowDirection = "";
        String distance = "0";

        try {
          if (key == KeyEvent.VK_LEFT && !e.isControlDown()) {
            direction = "West";
            listener.handleKeyPressed(direction);
          } else if (key == KeyEvent.VK_RIGHT && !e.isControlDown()) {
            direction = "East";
            listener.handleKeyPressed(direction);
          } else if (key == KeyEvent.VK_UP && !e.isControlDown()) {
            direction = "North";
            listener.handleKeyPressed(direction);
          } else if (key == KeyEvent.VK_DOWN && !e.isControlDown()) {
            direction = "South";
            listener.handleKeyPressed(direction);
          }

        } catch (IllegalStateException ioe) {
          JOptionPane.showMessageDialog(myFrame, ioe.getMessage());
        } catch (IllegalArgumentException io) {
          JOptionPane.showMessageDialog(myFrame, "Game is Over!");
        }

        try {
          if (key == KeyEvent.VK_UP && e.isControlDown()) {
            arrowDirection = "North";
            distance = JOptionPane.showInputDialog("Enter distance");
            listener.handleKeyPressed1(arrowDirection, Integer.parseInt(distance));
          } else if (key == KeyEvent.VK_DOWN && e.isControlDown()) {
            arrowDirection = "South";
            distance = JOptionPane.showInputDialog("Enter distance");
            listener.handleKeyPressed1(arrowDirection, Integer.parseInt(distance));
          } else if (key == KeyEvent.VK_LEFT && e.isControlDown()) {
            arrowDirection = "West";
            distance = JOptionPane.showInputDialog("Enter distance");
            listener.handleKeyPressed1(arrowDirection, Integer.parseInt(distance));
          } else if (key == KeyEvent.VK_RIGHT && e.isControlDown()) {
            arrowDirection = "East";
            distance = JOptionPane.showInputDialog("Enter distance");
            listener.handleKeyPressed1(arrowDirection, Integer.parseInt(distance));
          }

        } catch (IllegalArgumentException ioe) {
          JOptionPane.showMessageDialog(myFrame,
              ioe.getMessage());
        }

        try {
          if (key == KeyEvent.VK_P) {
            listener.handleTreasurePressed();
          }
        } catch (IllegalArgumentException io) {
          JOptionPane.showMessageDialog(myFrame, io.getMessage());
        }
      }
    });
  }

  @Override
  public void showMessage(String sc) {
    myLabel1.setText(sc);
    myLabel1.setForeground(Color.white);
    Font f = new Font("Georgia", Font.BOLD, 17);
    myLabel1.setFont(f);
    myLabel1.setBounds(10, 240, 350, 50);
    infoPanel.add(myLabel1);

  }


  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void shutDown() {
    this.dispose();
  }

  @Override
  public void addActionListener(ViewController listener) {
    ActionListener actionListener = e -> {
      if (e.getActionCommand().equals(m1.getText())) {
        System.exit(0);
      } else if (e.getActionCommand().equals(m2.getText())) {
        listener.newGame();
      }
    };
    m1.addActionListener(actionListener);
    m2.addActionListener(actionListener);
  }

  @Override
  public void update(ReadOnlyModel model1) {
    dungeonPanel.updateModel(model1);
    infoPanel.updateModel(model1);

  }

}

