/**
 * A record-keeping board for a game of Connect 4.
 */
public class Board
{
   private int[][] grid;
   private int connection;
   static final int EMPTY = 0;
   static final int P1 = 1;
   static final int P2 = 2;
   private int player;
   private boolean endGame;

   /**
    * Creates a game board for a Connect 4 game.
    *
    * @param size       the size of the board (size x size matrix).
    * @param connection the amount of connection(s) needed to win the game.
    */
   public Board(int size, int connection)
   {
      grid = new int[size][size];
      this.connection = connection;
      player = P1;
      endGame = false;
      for (int[] ints : grid) {for (int i : ints) {i = EMPTY;}}
   }

   /**
    * Adds a piece to the grid.
    *
    * @param column the column to place the piece.
    * @return the row the piece landed
    */
   public int addPiece(int column)
   {
      if (endGame) {return -1;}
      return addPiece(column, player);
   }

   /**
    * @param column the column to place the piece.
    * @param player which player's turn.
    * @return the row the piece landed
    */
   private int addPiece(int column, int player)
   {
      int row = grid.length - 1;
      boolean success = false;
      for (; !success && row >= 0; row--)
      {
         if (hasSpace(row, column))
         {
            grid[row][column] = player;
            success = true;
         }
      }
      return !success ? -1 : row + 1;
   }

   /**
    * Checks if all columns in the grid are fully filled.
    *
    * @return true if all of the columns are fully filled.
    */
   public boolean fullColumns()
   {
      int fullColumns = 0;
      for (int i = 0; i < grid[0].length; i++)
      {
         if (grid[0][i] > EMPTY) {fullColumns++;}
      }
      return fullColumns == grid[0].length && (endGame = true);
   }

   /**
    * Checks if the specified tile can be filled.
    *
    * @param row    the row to check.
    * @param column the column to check.
    * @return true if the specified space is allowed to be filled.
    */
   public boolean hasSpace(int row, int column)
   {
      return grid[row][column] == EMPTY;
   }

   /**
    * Shifts to the next player's turn.
    *
    * @return true if it was player 1's turn before shifting.
    */
   public boolean changePlayer()
   {
      if (player == P1)
      {
         this.player = P2;
         return true;
      }
      else
      {
         this.player = P1;
         return false;
      }
   }

   /**
    * Checks for the 4 win conditions
    * ( horizontal, vertical, diagonals of / and \ )
    *
    * @return if victory is achieved or not.
    */
   public boolean victory()
   {
      if (connection == 1) {return endGame = true;}
      return victoryH() || victoryV() || victoryDR() || victoryDL();
   }

   /**
    * Checks horizontal ( - ) victory condition
    *
    * @return if victory is achieved or not.
    */
   private boolean victoryH()
   {
      for (int i = grid.length - 1; i >= 0; i--)
      {
         for (int j = grid[0].length - 1; j >= 0; j--)
         {
            if (j >= connection - 1 && grid[i][j] > EMPTY)
            {
               int k = 1;
               while (grid[i][j] == grid[i][j - k])
               {
                  k++;
                  if (k == connection) {return endGame = true;}
               }
            }
         }
      }
      return false;
   }

   /**
    * Checks vertical ( | ) victory condition
    *
    * @return if victory is achieved or not.
    */
   private boolean victoryV()
   {
      for (int j = grid[0].length - 1; j >= 0; j--)
      {
         for (int i = grid.length - 1; i >= 0; i--)
         {
            if (i >= connection - 1 && grid[i][j] > EMPTY)
            {
               int k = 1;
               while (grid[i][j] == grid[i - k][j])
               {
                  k++;
                  if (k == connection) {return endGame = true;}
               }
            }
         }
      }
      return false;
   }

   /**
    * Checks diagonal ( / ) victory condition
    *
    * @return if victory is achieved or not.
    */
   private boolean victoryDR()
   {
      for (int i = grid.length - 1; i >= 0; i--)
      {
         for (int j = 0; j < grid[0].length; j++)
         {
            if (j <= grid[0].length - connection && i >= connection - 1
                  && grid[i][j] > EMPTY)
            {
               int k = 1;
               while (grid[i][j] == grid[i - k][j + k])
               {
                  k++;
                  if (k == connection) {return endGame = true;}
               }
            }
         }
      }
      return false;
   }

   /**
    * Checks diagonal ( \ ) victory condition
    *
    * @return if victory is achieved or not.
    */
   private boolean victoryDL()
   {
      for (int i = grid.length - 1; i >= 0; i--)
      {
         for (int j = grid[0].length - 1; j >= 0; j--)
         {
            if (j >= connection - 1 && i >= connection - 1
                  && grid[i][j] > EMPTY)
            {
               int k = 1;
               while (grid[i][j] == grid[i - k][j - k])
               {
                  k++;
                  if (k == connection) {return endGame = true;}
               }
            }
         }
      }
      return false;
   }
}