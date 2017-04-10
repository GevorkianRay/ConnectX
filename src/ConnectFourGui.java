import javax.swing.*;
import java.awt.*;

/**
 * GUI class for the game
 */
public class ConnectFourGui
{
   private JPanel connectPanel;
   private JMenu menu;
   private JPanel playArea;
   private JLabel sizeLabel;
   private JLabel connLabel;
   private JLabel playerTurn;
   private JMenuItem resetGame;
   private JMenuItem mainMenu;

   /**
    * Creates JFrame that contains the game
    *
    * @param args size and conn, if they exist
    */
   public static void createFrame(int... args)
   {
      JFrame frame = new JFrame("Connect Four");
      frame.setContentPane(new ConnectFourGui(args).connectPanel);
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
   public ConnectFourGui(int... args)
   {
      if (args.length == 2) {createBoard(args);}
      createMenu();

      /**
       * Re-opens main menu, passing in the same arguments used to open this
       */
      mainMenu.addActionListener(e -> {
         MainMenuGUI.createFrame(args);
         ((JFrame) connectPanel.getTopLevelAncestor()).dispose();
      });

      /**
       * Re-opens this game, passing in the same arguments used to open this
       */
      resetGame.addActionListener(e -> {
         ConnectFourGui.createFrame(args);
         ((JFrame) connectPanel.getTopLevelAncestor()).dispose();
      });
   }

   /**
    * Creates and adds items to the menu
    */
   private void createMenu()
   {
      //create
      mainMenu = new JMenuItem("Main Menu");
      resetGame = new JMenuItem("New Game");
      //add
      menu.add(mainMenu);
      menu.add(resetGame);
   }

   /**
    * Creates the game of connect four in the playArea
    *
    * @param args size and conn, if they exist
    */
   private void createBoard(int... args)
   {
      int size = args[0];
      int conn = args[1];
      setSizeLabel(size);
      setConnLabel(conn);
      ConnectFour connectFour = new ConnectFour(size, conn, this);
      int widthMod = 25;
      int heightMod = 32;
      Dimension prefSize = new Dimension(size * widthMod, size * heightMod);
      connectFour.setPreferredSize(prefSize);
      playArea.add(connectFour);
   }

   /**
    * Sets text of sizeLabel
    *
    * @param size text to set sizeLabel to
    */
   public void setSizeLabel(int size)
   {
      setSizeLabel(String.valueOf(size));
   }

   /**
    * Sets text of sizeLabel
    *
    * @param size text to set sizeLabel to
    */
   private void setSizeLabel(String size)
   {
      sizeLabel.setText(size + "x" + size);
   }

   /**
    * Sets text of connLabel
    *
    * @param conn text to set connLabel to
    */
   public void setConnLabel(int conn)
   {
      setConnLabel(String.valueOf(conn));
   }

   /**
    * Sets text of connLabel
    *
    * @param conn text to set connLabel to
    */
   private void setConnLabel(String conn)
   {
      connLabel.setText(conn);
   }

   /**
    * Changes the color of playerTurn
    *
    * @param color color to change to
    */
   public void changePlayer(Color color)
   {
      playerTurn.setForeground(color);
   }

   {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
      $$$setupUI$$$();
   }

   /**
    * Method generated by IntelliJ IDEA GUI Designer
    * >>> IMPORTANT!! <<<
    * DO NOT edit this method OR call it in your code!
    */
   private void $$$setupUI$$$()
   {
      connectPanel = new JPanel();
      connectPanel.setLayout(new com.intellij.uiDesigner.core
            .GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
      final JMenuBar menuBar1 = new JMenuBar();
      menuBar1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager
            (1, 2, new Insets(0, 0, 0, 0), -1, -1));
      connectPanel.add(menuBar1, new com.intellij.uiDesigner.core
            .GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core
            .GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core
            .GridConstraints.FILL_HORIZONTAL,
            com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core
            .GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
      menu = new JMenu();
      menu.setText("Options");
      menuBar1.add(menu, new com.intellij.uiDesigner.core.GridConstraints(0,
            0, 1, 1, com.intellij.uiDesigner.core.GridConstraints
            .ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints
            .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      final com.intellij.uiDesigner.core.Spacer spacer1 =
            new com.intellij.uiDesigner.core.Spacer();
      menuBar1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints
            (0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints
                  .ANCHOR_CENTER, com.intellij.uiDesigner.core
                  .GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner
                  .core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null,
                  null, 0, false));
      final JPanel panel1 = new JPanel();
      panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2,
            7, new Insets(5, 5, 5, 5), -1, -1));
      connectPanel.add(panel1, new com.intellij.uiDesigner.core
            .GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core
            .GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core
            .GridConstraints.FILL_BOTH,
            com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_CAN_GROW,
            com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
      final JLabel label1 = new JLabel();
      label1.setText("Player Turn:");
      panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1,
            5, 1, 1, com.intellij.uiDesigner.core.GridConstraints
            .ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints
            .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      final com.intellij.uiDesigner.core.Spacer spacer2 =
            new com.intellij.uiDesigner.core.Spacer();
      panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1,
            0, 1, 1, com.intellij.uiDesigner.core.GridConstraints
            .ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints
            .FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
      playArea = new JPanel();
      playArea.setLayout(new BorderLayout(0, 0));
      panel1.add(playArea, new com.intellij.uiDesigner.core.GridConstraints
            (0, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints
                  .ANCHOR_CENTER, com.intellij.uiDesigner.core
                  .GridConstraints.FILL_BOTH,
            com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_WANT_GROW,
            com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                  | com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
      final JLabel label2 = new JLabel();
      label2.setText("Size:");
      panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1,
            1, 1, 1, com.intellij.uiDesigner.core.GridConstraints
            .ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints
            .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      sizeLabel = new JLabel();
      sizeLabel.setText("");
      panel1.add(sizeLabel, new com.intellij.uiDesigner.core.GridConstraints
            (1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints
                  .ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints
                  .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core
                  .GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0,
                  false));
      final JLabel label3 = new JLabel();
      label3.setText("Conn:");
      panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1,
            3, 1, 1, com.intellij.uiDesigner.core.GridConstraints
            .ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints
            .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints
            .SIZEPOLICY_FIXED, null, null, null, 0, false));
      connLabel = new JLabel();
      connLabel.setText("");
      panel1.add(connLabel, new com.intellij.uiDesigner.core.GridConstraints
            (1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints
                  .ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints
                  .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core
                  .GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0,
                  false));
      playerTurn = new JLabel();
      playerTurn.setBackground(new Color(-1513240));
      playerTurn.setForeground(new Color(-65536));
      playerTurn.setText("■");
      panel1.add(playerTurn, new com.intellij.uiDesigner.core.GridConstraints
            (1, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints
                  .ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints
                  .FILL_NONE, com.intellij.uiDesigner.core.GridConstraints
                  .SIZEPOLICY_FIXED, com.intellij.uiDesigner.core
                  .GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0,
                  false));
   }

   /** @noinspection ALL */
   public JComponent $$$getRootComponent$$$() { return connectPanel; }
}