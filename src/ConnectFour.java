import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UI class for a game of Connect 4.
 */
public class ConnectFour extends JPanel
{
   ConnectFourGui gui;
   JButton[][] buttons;
   int size;
   Board board;
   final static Color P1 = Color.RED;
   final static Color P2 = Color.BLACK;
   boolean gameOver;

   final static int MIN_SIZE = 1;
   final static int MAX_SIZE = 20;
   final static int MIN_CONN = 1;
   final static int MAX_CONN = 20;

   /**
    * Initializes the game of Connect 4.
    *
    * @param size       the size of the game board (size x size matrix).
    * @param connection the amount of connection(s) needed to win the game.
    */
   public ConnectFour(int size, int connection, ConnectFourGui gui)
   {
      this.gui = gui;
      this.setLayout(new GridLayout(size, size));
      buttons = new JButton[size][size];
      this.size = size;
      board = new Board(size, connection);
      gameOver = false;

      for (int i = 0; i < size; i++)
      {
         for (int j = 0; j < size; j++)
         {
            buttons[i][j] = new JButton();
            buttons[i][j].addActionListener(new ButtonListener());
            this.add(buttons[i][j]);
         }
      }
   }

   /**
    * A ButtonListener class to detect button actions.
    */
   public class ButtonListener implements ActionListener
   {
      /**
       * Updates button properties and the game board to reflect button
       * interaction.
       *
       * @param event an event which triggers a button.
       */
      public void actionPerformed(ActionEvent event)
      {
         // Winner has been decided, do nothing.
         if (gameOver) {return;}
         String victoryDialog = "Player %s (%s) wins.";
         String drawDialog =
               "The board is filled and nobody wins; this game is a draw.";
         for (int i = size - 1; i >= 0; i--)
         {
            for (int j = size - 1; j >= 0; j--)
            {
               if (buttons[i][j] == event.getSource())
               {
                  int row = board.addPiece(j);
                  if (row != -1)
                  {
                     if (board.changePlayer())
                     {
                        buttons[row][j].setBackground(P1);
                        gui.changePlayer(P2);
                        if (board.victory())
                        {
                           JOptionPane.showMessageDialog(null, String.format
                                 (victoryDialog, "1", "red"));
                           gameOver = true;
                        }
                     }
                     else
                     {
                        buttons[row][j].setBackground(P2);
                        gui.changePlayer(P1);
                        if (board.victory())
                        {
                           JOptionPane.showMessageDialog(null, String.format
                                 (victoryDialog, "2", "black"));
                           gameOver = true;
                        }
                     }
                  }
                  if (!gameOver && board.fullColumns())
                  {
                     JOptionPane.showMessageDialog(null, drawDialog);
                     gameOver = true;
                  }
               }
            }
         }
      }
   }


   public static boolean checkSize(int size)
   {
      return size >= MIN_SIZE && size <= MAX_SIZE;
   }

   public static boolean checkConn(int conn)
   {
      return conn >= MIN_CONN && conn <= MAX_CONN;
   }
}
