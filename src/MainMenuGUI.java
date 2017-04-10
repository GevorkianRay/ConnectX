import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GUI class for the main menu
 */
public class MainMenuGUI
{
   private JLabel logoLabel;
   private JTextField boardSize;
   private JTextField boardConn;
   private JButton startButton;
   private JLabel boardSizeDisplay;
   private JPanel menuPanel;
   private JLabel errorLabel;

   public static void main(String[] args)
   {
      if (args.length == 0) {createFrame();}
      else
      {
         if (args.length != 2)
         {
            String errorMessage =
                  "Requires exactly 2 arguments or no arguments";
            System.out.println(errorMessage);
         }
         else
         {
            try
            {
               int size = Integer.parseInt(args[0]);
               int conn = Integer.parseInt(args[1]);
               if (checkErrorsCMD(size, conn))
               {ConnectFourGui.createFrame(size, conn);}
            }
            catch (NumberFormatException e)
            {
               System.out.println("Please enter numbers only.");
            }
         }
      }
   }

   /**
    * Creates JFrame that contains the main menu
    *
    * @param args size and conn, if they exist
    */
   public static void createFrame(int... args)
   {
      JFrame frame = new JFrame("Connect Four Menu");
      frame.setContentPane(new MainMenuGUI(args).menuPanel);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   /**
    * Initializes action listeners and ensures any arguments are used
    *
    * @param args size and conn, if they exist
    */
   public MainMenuGUI(int... args)
   {
      $$$setupUI$$$();
      if (args.length == 2)
      {
         setBoardSize(args[0]);
         setBoardConn(args[1]);
      }

      /**
       * Launches the game GUI provided that nothing goes wrong
       */
      startButton.addActionListener(e -> {
         int size = 0;
         try {size = Integer.parseInt(boardSize.getText());}
         catch (NumberFormatException ex) {ex.printStackTrace();}
         int conn = 0;
         try {conn = Integer.parseInt(boardConn.getText());}
         catch (NumberFormatException ex) {ex.printStackTrace();}
         if (checkErrors(size, conn))
         {
            ConnectFourGui.createFrame(size, conn);
            ((JFrame) menuPanel.getTopLevelAncestor()).dispose();
         }
      });

      /**
       * Updates UI and clears unnecessary characters
       */
      boardSize.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyReleased(KeyEvent e)
         {
            int size = 0;
            String bs = boardSize.getText();
            bs = bs.replaceAll("\\D", "");
            if (bs.length() > 0)
            {
               try {size = Integer.parseInt(bs);}
               catch (NumberFormatException ex) {ex.printStackTrace();}
            }
            setBoardSize(size);
            super.keyReleased(e);
         }
      });

      /**
       * Clears unnecessary characters
       */
      boardConn.addKeyListener(new KeyAdapter()
      {
         @Override
         public void keyReleased(KeyEvent e)
         {
            String bc = boardConn.getText();
            bc = bc.replaceAll("\\D", "");
            setBoardConn(bc);
            super.keyReleased(e);
         }
      });
   }

   /**
    * Sets text of boardSize and boardSizeDisplay
    *
    * @param size text to set boardConn to
    */
   private void setBoardSize(int size)
   {
      setBoardSize(String.valueOf(size));
   }

   /**
    * Sets text of boardSize and boardSizeDisplay
    *
    * @param size text to set boardConn to
    */
   private void setBoardSize(String size)
   {
      boardSize.setText(size);
      boardSizeDisplay.setText(size + "x" + size);
   }

   /**
    * Sets text of boardConn
    *
    * @param conn text to set boardConn to
    */
   private void setBoardConn(int conn)
   {
      setBoardConn(String.valueOf(conn));
   }

   /**
    * Sets text of boardConn
    *
    * @param conn text to set boardConn to
    */
   private void setBoardConn(String conn)
   {
      boardConn.setText(conn);
   }

   /**
    * Checks for errors that could cause the program not to run
    * Sets errorLabel accordingly
    *
    * @param size size of board
    * @param conn number of pieces in a row to win
    * @return false if any problems arise
    */
   private boolean checkErrors(int size, int conn)
   {
      boolean chkSize = ConnectFour.checkSize(size);
      boolean chkConn = ConnectFour.checkConn(conn);
      if (size < conn)
      {
         errorLabel.setText("Connections exceeds size");
         return false;
      }
      else if (!chkSize)
      {
         errorLabel.setText("Modify board size!");
         return false;
      }
      else if (!chkConn)
      {
         errorLabel.setText("Modify connections!");
         return false;
      }
      else
      {
         errorLabel.setText("");
         return true;
      }
   }

   /**
    * Checks for errors that could cause the program not to run
    *
    * @param size size of board
    * @param conn number of pieces in a row to win
    * @return false if any problems arise
    */
   private static boolean checkErrorsCMD(int size, int conn)
   {
      boolean chkSize = ConnectFour.checkSize(size);
      boolean chkConn = ConnectFour.checkConn(conn);
      if (size < conn)
      {
         System.out.println("Connections exceeds size");
         return false;
      }
      else if (!chkSize)
      {
         System.out.printf("Board size should be between %s and %s\n", ConnectFour.MIN_SIZE, ConnectFour.MAX_SIZE);
         return false;
      }
      else if (!chkConn)
      {
         System.out.printf("# of Connections should be between %s and %s\n", ConnectFour.MIN_CONN, ConnectFour.MAX_CONN);
         return false;
      }
      else {return true;}
   }

   /**
    * Creates UI logo
    */
   private void createUIComponents()
   {
      try
      {
         BufferedImage connectFour =
               ImageIO.read(getClass().getResource("Connect4Logo.png"));
         logoLabel = new JLabel(new ImageIcon(connectFour));
      }
      catch (IOException ex) {ex.printStackTrace();}
   }

   /**
    * Method generated by IntelliJ IDEA GUI Designer
    * >>> IMPORTANT!! <<<
    * DO NOT edit this method OR call it in your code!
    */
   private void $$$setupUI$$$()
   {
      createUIComponents();
      menuPanel = new JPanel();
      menuPanel.setLayout(new GridLayoutManager(4, 5, new Insets(5, 5, 5, 5),
            -1, -1));
      menuPanel.setEnabled(true);
      final JLabel label1 = new JLabel();
      label1.setText("Board Size:");
      menuPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints
            .ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints
            .SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null,
            null, 0, false));
      final JLabel label2 = new JLabel();
      label2.setText("Connections");
      menuPanel.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints
            .ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints
            .SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null,
            null, 0, false));
      boardConn = new JTextField();
      boardConn.setText("4");
      menuPanel.add(boardConn, new GridConstraints(2, 2, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
      logoLabel.setText("");
      menuPanel.add(logoLabel, new GridConstraints(0, 0, 1, 5,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, new Dimension(400, 144), null, 0, false));
      boardSize = new JTextField();
      boardSize.setText("8");
      menuPanel.add(boardSize, new GridConstraints(1, 2, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
      startButton = new JButton();
      startButton.setText("Start Game");
      menuPanel.add(startButton, new GridConstraints(3, 3, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      boardSizeDisplay = new JLabel();
      boardSizeDisplay.setText("8x8");
      menuPanel.add(boardSizeDisplay, new GridConstraints(1, 3, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      final Spacer spacer1 = new Spacer();
      menuPanel.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints
            .ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null,
            null, 0, false));
      final Spacer spacer2 = new Spacer();
      menuPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints
            .ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null,
            null, 0, false));
      errorLabel = new JLabel();
      errorLabel.setForeground(new Color(-65536));
      errorLabel.setText("");
      menuPanel.add(errorLabel, new GridConstraints(3, 0, 1, 3,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
   }

   /** @noinspection ALL */
   public JComponent $$$getRootComponent$$$() { return menuPanel; }
}
