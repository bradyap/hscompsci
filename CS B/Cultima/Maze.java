import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

//Creates perfect 2D maze.

public class Maze
{
// Walls start out as UNDECIDED, then become either WALL or NO_WALL as maze is built.  
// Wall currently being considering is CURRENT
   public static final short UNDECIDED = 0;
   public static final short WALL = 1;
   public static final short NO_WALL = 2;
   public static final short CURRENT_WALL = 3;

   private int numRows;
   private int numCols;
   private int[][] board;
   protected short[][] vertWalls;
   protected short[][] horizWalls;

   private boolean isNowInitialized = false;  //true once the maze is finished being built

   public Maze(int rows, int cols)
   {
      numRows = rows;
      numCols = cols;
      board = new int[numRows][numCols];
      vertWalls = new short[numRows][numCols + 1];
      horizWalls = new short[numRows + 1][numCols];
   // Give each room its own value
      int count = 0;
      for (int row = 0; row < numRows; row++)
      {
         for (int col = 0; col < numCols; col++) 
         {
            board[row][col] = count;
            count++;
         }
      }
   // Set outer walls to WALL
   // -Top and bottom of horizWalls
      for (int col = 0; col < numCols; col++) 
      {
         horizWalls[0][col] = WALL;       // top
         horizWalls[numRows][col] = WALL; // bottom
      }
   // -Left and right of vertWalls
      for (int row = 0; row < numRows; row++) 
      {
         vertWalls[row][0] = WALL;        // left side
         vertWalls[row][numCols] = WALL;  // right side
      }
   // Open top left and bottom right as entrace/exit
      vertWalls[0][0] = NO_WALL;
      vertWalls[numRows - 1][numCols] = NO_WALL;
   }

   public Maze() 
   {
      this(10, 10);
   }

   public boolean isInitialized() 
   { 
      return isNowInitialized; 
   }
		
   public int getRows() 
   { 
      return numRows; 
   }
		
   public int getCols() 
   { 
      return numCols; 
   }
		
   public int getRoom(int row, int col) 
   { 
      return board[row][col]; 
   }

//Initialize the maze by randomly picking undecided walls:
//if the two rooms on either side are the same number,
//then the wall is set to WALL; otherwise it's set to NO_WALL.
   public void initialize() 
   {
      if (isNowInitialized) 
         return;
   // Make a list of undecided vWalls and shuffle them
      ArrayList<Point> vWalls = new ArrayList<Point>(numRows * (numCols - 2));
      for (int row = 0; row < numRows; row++) 
      {
         for (int col = 0; col <= numCols; col++) 
         {
            if (vertWalls[row][col] == UNDECIDED) 
               vWalls.add(new Point(row, col));
         }
      }
      Collections.shuffle(vWalls);
   // Make a list of undecided hWalls and shuffle them
      ArrayList<Point> hWalls = new ArrayList<Point>((numRows - 2) * numCols);
      for (int row = 0; row <= numRows; row++) 
      {
         for (int col = 0; col < numCols; col++) 
         {
            if (horizWalls[row][col] == UNDECIDED) 
               hWalls.add(new Point(row, col));
         }
      }
      Collections.shuffle(hWalls);
   // Go through each list to pick a random wall
      int hWallIdx = 0;
      int vWallIdx = 0;
      while (hWallIdx < hWalls.size() || vWallIdx < vWalls.size()) 
      {
         if (Math.random() < .5) 
         {
         // handle next horizontal wall
            if (hWallIdx == hWalls.size()) 
               continue;
            handleHorizontalWall(hWalls.get(hWallIdx));
            hWallIdx++;
         } 
         else 
         {
         // handle next vertical wall
            if (vWallIdx == vWalls.size()) 
               continue;
            handleVerticalWall(vWalls.get(vWallIdx));
            vWallIdx++;
         }
      }
      isNowInitialized = true;
   }

   private void handleHorizontalWall(Point point) 
   {
      int row = point.x;
      int col = point.y;
      horizWalls[row][col] = CURRENT_WALL;
      int roomColorAbove = board[row - 1][col];
      int roomColorBelow = board[row][col];
      if (roomColorAbove == roomColorBelow) 
      {
         horizWalls[row][col] = WALL;
      } 
      else 
      {
         horizWalls[row][col] = NO_WALL;
         changeColors(roomColorBelow, roomColorAbove, row, col);
      }
   }

   private void handleVerticalWall(Point point) 
   {
      int row = point.x;
      int col = point.y;
      vertWalls[row][col] = CURRENT_WALL;
      int roomColorLeft = board[row][col - 1];
      int roomColorRight = board[row][col];
      if (roomColorLeft == roomColorRight) 
      {
         vertWalls[row][col] = WALL;
      } 
      else 
      {
         changeColors(roomColorRight, roomColorLeft, row, col);
         vertWalls[row][col] = NO_WALL;
      }
   }

//Changes all rooms in the maze with given fromColor to given toColor.
//row and col give hint as to where to start.  (Recursive version.)
   private void changeColors(int fromColor, int toColor, int row, int col) 
   {
      if (board[row][col] == fromColor) 
      {
         board[row][col] = toColor;
      // Go up, right, down, left if possible and not blocked
      // UP
         if (row > 0 && horizWalls[row][col] == NO_WALL) 
            changeColors(fromColor, toColor, row - 1, col);
      // RIGHT
         if (col < numCols - 1 && vertWalls[row][col + 1] == NO_WALL) 
            changeColors(fromColor, toColor, row, col + 1);
      // DOWN
         if (row < numRows - 1 && horizWalls[row + 1][col] == NO_WALL) 
            changeColors(fromColor, toColor, row + 1, col);
      // LEFT
         if (col > 0 && vertWalls[row][col] == NO_WALL) 
            changeColors(fromColor, toColor, row, col - 1);
      }
   }

   public String toString()
   {
      String res = "+";
   // Top wall
      for (int col = 0; col < numCols; col++) 
      {
         res += "-+";
      }
      res += "\n";
   // Body
      for (int row = 0; row < numRows; row++) 
      {
         for (int col = 0; col < numCols; col++) 
         {
            if(vertWalls[row][col] == WALL)
               res += "|";
            else
               res += " ";
            res += " ";
         }
         if(vertWalls[row][numCols] == WALL)		//far right wall
            res += "|";
         else
            res += " ";								//maze exit
         res += "\n";
         res += "+";
         for (int col = 0; col < numCols; col++) 
         {
            if(horizWalls[row+1][col] == WALL)
               res += "-";
            else
               res += " ";
            res += "+";
         }
         res += "\n";
      }
      return res;
   }
   
   public String[][] toArray()
   {
      String res = "+";
   // Top wall
      for (int col = 0; col < numCols; col++) 
      {
         res += "-+";
      }
      res += "\n";
   // Body
      for (int row = 0; row < numRows; row++) 
      {
         for (int col = 0; col < numCols; col++) 
         {
            if(vertWalls[row][col] == WALL)
               res += "|";
            else
               res += " ";
            res += " ";
         }
         if(vertWalls[row][numCols] == WALL)		//far right wall
            res += "|";
         else
            res += " ";								//maze exit
         res += "\n";
         res += "+";
         for (int col = 0; col < numCols; col++) 
         {
            if(horizWalls[row+1][col] == WALL)
               res += "-";
            else
               res += " ";
            res += "+";
         }
         res += "\n";
      }
      String[][] ans = new String[getRows()*2+1][getCols()*2+1];
      int r=0, c=0;
      for(int i=0; i<res.length(); i++)
      {
         char curr = res.charAt(i);
         if(curr == '\n')
         {
            r++;
            c=0;
         }
         else
         {
            ans[r][c] = ""+curr;
            c++;
         }
      }
      return ans;
   }

   public static String[][] buildMaze(int rows, int cols)
   {
      Maze test = new Maze(rows/2, cols/2);
      test.initialize();
        String[][]ans = test.toArray();
        ans[1][0] = "S";                             //mark the start
        ans[ans.length-2][ans[0].length-1] = "E";    //mark the exit
      return ans;
   }

   public static void main(String[] args) 
   {
      String[][] array = buildMaze(11,11);
      for(int r=0; r<array.length; r++)
      {
         for(int c=0; c<array[0].length; c++)
         {
            System.out.print(array[r][c]);
         }
         System.out.println();
      }
   }
}