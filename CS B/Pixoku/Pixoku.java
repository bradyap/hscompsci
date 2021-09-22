  //Rev. Dr. Douglas R Oberle, March 2013
  //Complete the following methods below:  invert(), mirrorFlip(), checkForWin()

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Pixoku extends JPanel implements MouseListener, MouseMotionListener
{
   protected static final ImageIcon BLANK     = new ImageIcon("images/blank.jpg");		//blank spot - not painted nor ex-ed out
   protected static final ImageIcon PAINTED   = new ImageIcon("images/paint.jpg");		//painted pixel
   protected static final ImageIcon EXOUT     = new ImageIcon("images/ex.jpg");			//ex out a pixel
   protected static final ImageIcon MISTAKE   = new ImageIcon("images/mistake.jpg");	//marks mistake found by computer
   protected static final ImageIcon BLACK     = new ImageIcon("images/black.jpg");		//black pixel, used once board is solved
   protected static final ImageIcon WHITE     = new ImageIcon("images/white.jpg");		//white spot, used once board is solved
   protected static final ImageIcon RED       = new ImageIcon("images/red.jpg");			//red pixel, placed by the computer when the user gets hint
   protected static final ImageIcon GREY	    = new ImageIcon("images/numbers.jpg");	//background for the number field
   protected static final ImageIcon CROSSHAIR = new ImageIcon("images/crossHair.GIF");	//user crosshair/cursor
   protected static final ImageIcon TITLE 	 = new ImageIcon("themes/misc1.GIF");		//title screen
   protected static ImageIcon solution;				//image that may appear on top of the board when solved

   protected static int size=30;							//size of cell being drawn

//This array will be represented graphically on the screen
   protected static int[][] board;						//the board that contains the solution
                                             	//	(0 for unpainted, 1 for painted)
   protected static int[][] guess;						//the board that the user guesses the solution on
                                             	//	(0 for unpainted, 2 for unpainted but x-ed out, 3 for unpainted but marked as a mistake, 1 for painted, -1 for painted but given as a hint)
   protected static int score;							//1 point for each correct pixel, -2 for each mistake
   protected static int numErrors;						//counts the number of mistakes
   protected static int hint;								//3 hints given for each game 
   protected static boolean titleScreen;				//should the title screen be displayed?
   protected static boolean solved;						//has the board been solved or not?  used for display
   protected static boolean paintMode;					//are we in paint mode (to create puzzles)
   protected static boolean textInput;					//are we typing in a file name or theme?
   protected static boolean updateScores;				//do we need to write the high scores back to the file when the program ends?
   protected static boolean highScore;					//did we get a high score this round?
   protected static boolean flipped;					//has the image been flipped horizontally?  Used to inform which solution image should be drawn if there is one
   protected static int scoreIndex;						//stores the index of the score in the fileNames array that is to be updated, -1 if not one
   protected static boolean[] whichText;				//which field are we modifying (TEXT_HINT, TEXT_DESCRIPTION, TEXT_FILENAME)?
   protected static final byte TEXT_THEME = 0;		//indexes for whichText array - theme
   protected static final byte TEXT_DESCR = 1;		//description
   protected static final byte TEXT_FILE  = 2;		//filename
   protected static final byte TEXT_NAME  = 3;		//name of player for high score
   protected static boolean[] whichOption;			//which option is highlighted by the mouse?
   protected static final byte OPTION_SPACE = 0;	//indexes for whichOption array
   protected static final byte OPTION_PLUS = 1;
   protected static final byte OPTION_MINUS = 2;
   protected static final byte OPTION_T = 3;
   protected static final byte OPTION_D = 4;
   protected static final byte OPTION_F = 5;
   protected static final byte OPTION_P = 6;
   protected static final byte OPTION_ESC = 7;
   protected static final byte OPTION_R = 8;
   protected static final byte OPTION_ENTER = 9;
   protected static final byte OPTION_X = 10;
   protected static final byte OPTION_E = 11;
   protected static final byte OPTION_H = 12;

   protected static String message;							//displays in game messages
   protected static String theme;							//hint for the player
   protected static String description;					//the title of the image when it is solved
   protected static String filename;						//file name for user made puzzle
   protected static String solutionImage;					//the solution image that is displayed afer the puzzle is solved
   protected static String player;							//player name for high score
   protected static ArrayList<Score> fileNames;			//list of all themed file names and their high scores
   protected static ArrayList<Score> randomHighScores;//list of high scores from randomly generated boards (cateloged by size)
   protected static ArrayList<Integer> [] rowCount;	//an array of ArrayLists for each row to store the pixel count for rows
   protected static ArrayList<Integer> [] colCount;	//an array of ArrayLists for each col to store the pixel count for columns
   protected static int maxRowCount;						//the max list size from rowCount, used to set the number fileds displayed
   protected static int maxColCount;						//the max list size from colCount, used to set the number fileds displayed

   protected static final String[] levels = {"OPEN", "EASY", "MEDIUM", "HARD"};
   protected static int difficulty;									//index to set the difficulty level of the puzzles as an index for levels array

//A moveable cursor will start in the center of the field
   protected static int playerR;			//start row for the player cursor
   protected static int playerC;			//start col for the player cursor

   protected static int mouseX;			//locations for the mouse pointer
   protected static int mouseY;

   protected static long startTime;		//record how long it took to solve the puzzle
   protected static long endTime;
   protected static int completionTime;	

//sound elements						
//intervals of scales						Major							minor						Blues				Harmonic minor
   protected static int [][] intervals = {{0,2,4,5,7,9,11,12}, {0,2,3,5,7,9,11,12}, {0,3,5,6,7,10,12}, {0,2,3,5,7,8,11,12}};
   protected static MidiChannel[] channels=null;		//MIDI channels
   protected static Instrument[] instr;					//MIDI instrument bank
   protected static int [] instrumentList = {0,1,2,3,4,5,8,9,10,12,13,14,45,108,112,113,114,115};
   protected static int instrument;							//current instrument to be played
   protected static int [] scale;							//scale to be played


   public Pixoku()							//constructor
   {
      try 										//sound elements
      {
         Synthesizer synth = MidiSystem.getSynthesizer();
         synth.open();
         channels = synth.getChannels();
         instr = synth.getDefaultSoundbank().getInstruments();
      }
      catch (Exception ignored) 
      {}
      addMouseListener( this );
      addMouseMotionListener( this );
      mouseX = 0;
      mouseY = 0;
      scoreIndex = -1;
      difficulty = 0;
      updateScores = false;
      highScore = false;
      titleScreen = true;
      fileNames = new ArrayList<Score>();
      randomHighScores = new ArrayList<Score>();
      Utilities.readHighScores("themes/fileList.txt", fileNames);
      Utilities.readHighScores("themes/randomScores.txt", randomHighScores);
      if(Math.random() < .5)  
         createRandomBoard();				//start with a random pixel board
      else
      {		//start with a themed board read from a randomly picked file
         boolean state = createThemedBoard(null);
         if(!state)
            message = "Not found.  Selecting random board.";
      }
   }

//post:  initialize the board and data fields
   private static void setupBoard()
   {
      mouseX = 0;
      mouseY = 0;
      Utilities.initBoard(board);
   //ex out any row or column that has no pixels colored in it
   
      for(int r=0; r<rowCount.length; r++)
         if(rowCount[r].isEmpty())   
            for(int c=0; c<guess[0].length; c++)
               guess[r][c] = 3;		//computer marked ex
      for(int c=0; c<colCount.length; c++)
         if(colCount[c].isEmpty())   
            for(int r=0; r<guess.length; r++)
               guess[r][c] = 3;		//computer marked ex
   
      score = 0;
      numErrors = 0;
      hint = 3;
      solved = false;
      paintMode = false;
      textInput = false;
      solution = null;
   
   //sound elements
      channels[0].allNotesOff();
      int instrIndex = (int)(Math.random()*instrumentList.length);
      instrument = instrumentList[instrIndex];							//pick random instrument	
      int scaleIndex = (int)(Math.random()*intervals.length);		
      scale = (intervals[scaleIndex]).clone();										//pick random scale
      int key = (int)(Math.random()*12) + 48;							//pick random key
      for(int i=0; i<scale.length; i++)									//adjust scale to random key
         scale[i] += key;
      channels[0].programChange(instr[instrument].getPatch().getProgram());
      Utilities.playStartScale();
   }

//*****COMPLETE THIS METHOD********	
//pre:   grid is a nonempty array comprised of 0s and 1s
//post:  returns a new int array that is an inverted image of the argument grid - 0's become 1's and 1's become 0's
//       given 1 0 0, returns 0 1 1
//             0 1 1          1 0 0
//			grid is to remain unchanged
   public static int [][] invert(int [][] grid) {
      int height = grid.length;
      int width = grid[0].length;
      int [][] newGrid = new int[height][width]; //new array of same size
      //iterate through and copy to new array, swapping 1s and 0s
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            if (grid[i][j] == 0) {
               newGrid[i][j] = 1;
            } else {
               newGrid[i][j] = 0;
            }
         }
      }
      return newGrid; //retun modified array
   }

//*****COMPLETE THIS METHOD********
//pre:   grid is a nonempty array comprised of 0s and 1s
//post:  returns a new int array that is the horizontal mirror image of the argument grid
//       given 1 0 0, returns 0 0 1
//             0 1 1          1 1 0
//			grid is to remain unchanged
   public static int [][] mirrorFlip(int [][] grid) {
      int height = grid.length;
      int width = grid[0].length;
      int center = width % 2;
      int [][] newGrid = new int[height][width]; //new array of same size
      //iterate through and copy to new array and flip
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width / 2; j++) {
            newGrid[i][j] = grid[i][width - 1 - j];
            newGrid[i][width - 1 - j] = grid[i][j];
         }
         //set center to certer of other array if it is not covered by the other loop
         if (center != 0) {
            newGrid[i][center] = grid[i][center];
         }
      }
      return newGrid; //retun modified array
   }

//*****COMPLETE THIS METHOD********
//pre:  guess and board are the same dimensions
//post: returns false if, for any element of board that is painted (value 1), the same location in guess is unpainted (value 0, 2 or 3)
//      returns false if, for any element of board that is unpainted (value 0), the same location in guess is painted (value 1 or -1)  
//      computes the score, with 1 point for each correctly painted pixel (value 1 in both board and guess), minus (the number of errors * 2), plus the (number of unused hints *2)
//NOTE: painted pixels are value 1 for regular, -1 for painted pixel given as a hint
//      unpainted pixels are 0 for regular, 2 for X'ed out or 3 for a pixel marked as a mistake.
//      the data field numErrors will have the correct value for the number of errors made during the game when the method is called
//      the data field hint will have the correct value for the number of unused hints when the method is called
   private static boolean checkForWin() {
      int height = board.length;
      int width = board[0].length;
      Boolean result = true;
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            if ((board[i][j] == 1 && (guess[i][j] == 0 || guess[i][j] == 2 || guess[i][j] == 3)) || (board[i][j] == 0 && (guess[i][j] == 1 || guess[i][j] == -1))) {
               result = false;
               numErrors++;
            } 
            if (board[i][j] == 1 && guess[i][j] == 1) {
               score++;
            }
         }
      }
      score = score - (numErrors * 2) + (hint * 2);
      return result;   //temporary statement to keep things compiling
   }

   //NOTE:  only called in MethodTester.java to see if checkForWin works properly
   public static boolean testCheckForWin(int[][] tempBoard, int[][]tempGuess)
   {
      board = tempBoard;
      guess = tempGuess;
      return checkForWin();
   }


//pre:	rowCount has as many elements as there are in grid.length.  All ArrayLists in rowCount have been constructed.
//			colCount has as many elements as there are in grid[0].length.  All elements have been constructed.
//    	grid is either the solution board (board) or the guessing board (guess - which is also the user-defined puzzle canvas)
//post:  for each row, there is an array of ArrayLists that will store the number of painted pixels (value 1) in a row without a break
//			this method will fill the array called rowCount with the appropriate values.  The same is done for each column.
//			EXAMPLE:  consider the following board ->			1 1 0 1 0 1 1 1
//																			1 0 1 1 1 1 0 0
//																			0 0 0 0 0 0 0 0
//																			1 1 0 1 0 0 1 0
//			rowCount[0] represents the number of continuous 1 values (painted pixels) without a break
//			so rowCount[0] will contain [2,1,3].  There are two 1's, then one 1 value, then three 1 values.
//			rowCount[1] will be [1,4].  rowCount[2] will be empty because there are no 1 values
//			rowCount[3] will be [2,1,1], because there are two 1's, then one 1, then one 1.
//			FOR THE COLUMNS,
//			colCount[0] will be [2,1].		colCount[1] will be [1,1].		colCount[2] will be [1].		colCount[3] will be [2,1].
//			colCount[4] will be [1].		colCount[5] will be [2]  		colCount[6] will be [1,1] and colCount[7] will be [1].
   private static void countPixels(int[][] grid)
   {
   //count the pixels in each row   
      for(int r=0;r<grid.length;r++)	
      {
         int count=0;
         for(int c=0;c<grid[0].length;c++)
         {
            if(grid[r][c]==1)	//colored pixel
               count++;
            else 
               if(count>0)
               {   
                  rowCount[r].add(count);
                  count=0;
               }
         }
         if(count>0)
            rowCount[r].add(count);
      }
   
   //count the pixels in each col  
      for(int c=0;c<grid[0].length;c++) 
      {
         int count=0;
         for(int r=0;r<grid.length;r++)	
         {
            if(grid[r][c]==1)	//colored pixel
               count++;
            else 
               if(count>0)
               {   
                  colCount[c].add(count);
                  count=0;
               }
         }
         if(count>0)
            colCount[c].add(count);
      } 
   }

//pre:  grid is either the solution board (board) or the guessing board (guess - which is also the user-defined puzzle canvas)
//post: initializes pixel count lists, fills them and determines the max dimensions to properly display them on the board
   public static void pixelCount(int[][] grid)
   {
      rowCount = new ArrayList[grid.length];
      colCount = new ArrayList[grid[0].length];
      for(int i=0; i<rowCount.length; i++)
         rowCount[i] = new ArrayList<Integer>();
      for(int i=0; i<colCount.length; i++)
         colCount[i] = new ArrayList<Integer>();
      countPixels(grid);
      maxRowCount = 0;
      for(ArrayList<Integer> list:rowCount)
         if(list.size() > maxRowCount)
            maxRowCount = list.size();
      maxColCount = 0;
      for(ArrayList<Integer> list:colCount)
         if(list.size() > maxColCount)
            maxColCount = list.size();
   }

//post:  fills up the board with random 0s and 1s and initializes the guessing board to all 0s
   public static void createRandomBoard()	
   {
      int min = 4;						//OPEN - 4x4 to 15x15
      int range = 12;
      if(difficulty == 1)				//EASY - 4x4 to 7x7
      {
         min = 4;
         range = 4;
      }
      else if(difficulty == 2)		//MEDIUM - 6x6 to 11x11
      {
         min = 6;
         range = 6;
      }
      else if(difficulty == 3)		//HARD - 8x8 to 15x15
      {
         min = 8;
         range = 8;
      }
      int numRows = (int)(Math.random()*range)+min;		//array has a random size
      int numColumns = (int)(Math.random()*range)+min;  
      board = new int[numRows][numColumns];
      guess = new int[numRows][numColumns];
      for(int r=0;r<board.length;r++)					//fill with random values (0,1)
      {
         for(int c=0;c<board[0].length;c++)
         {
            if(Math.random() < .6)
               board[r][c] = 1;
            else
               board[r][c] = 0;
            guess[r][c] = 0;
         }
      }
      theme = "random";
      description = "random pattern";
      solutionImage = "none";
   //see if there is a high score entry for a puzzle of this size
      scoreIndex = -1;
      for(int i=0; i<randomHighScores.size(); i++)
      {
         Score current = randomHighScores.get(i);
      //random puzzle score names are composed with the word "random" followed with the size of the puzzle (# total cells)
         int size = Integer.parseInt(current.getFileName().substring(6));
         if(size == numRows * numColumns)
         {
            scoreIndex = i;
            break;
         }
      }
      setupBoard();
      filename = "random";
      startTime = System.currentTimeMillis();
   }

//post:  loads in a file with a theme, returns false if it had to pick a random board
   public static boolean createThemedBoard(String forceFile)
   {
      boolean state = true;
      flipped = false;
      description = "";
      filename = null;
      int min = 0;						//used to pick randoms within a range depending on difficulty level
      int range = fileNames.size();
   
      if(difficulty == 1)				//EASY - pick within the first half of the list, start to half way through the list
      {
         min = 0;
         range = fileNames.size() / 2 + 1;
      }
      else if(difficulty == 2)		//MEDIUM - pick within the middle half of the list, 1/4 way to 3/4 way through the list
      {
         min = fileNames.size() / 4;
         range = fileNames.size() / 2 + 1;
      }
      else if(difficulty == 3)		//HARD - pick within the last half of the list, 1/2 way to the end of the list
      {
         min = fileNames.size() / 2;
         range = fileNames.size() / 2 + 1;
      }
   
   
      scoreIndex = (int)(Math.random()*range) + min;
      while (scoreIndex >= fileNames.size())
         scoreIndex = (int)(Math.random()*range) + min;
   
      if(forceFile==null || forceFile.length()==0)
         filename = fileNames.get(scoreIndex).getFileName();
      else
      {
         scoreIndex = -1;
         filename  = forceFile.toLowerCase();  
         for(int i=0; i<fileNames.size(); i++)
            if(filename.equals(fileNames.get(i).getFileName()))
            {
               scoreIndex = i;
               break;
            }    
         if(scoreIndex == -1)		//we could not find that file
         {
         
            scoreIndex = (int)(Math.random()*range) + min;
            while (scoreIndex >= fileNames.size())
               scoreIndex = (int)(Math.random()*range) + min;
         
            filename = fileNames.get(scoreIndex).getFileName();
            state = false;
         }    
      }
      if(filename!=null)
      {
         if(!Utilities.readFileToBoard("themes/"+filename))
         {
            createRandomBoard();
            state = false; 
         }
         else
         {
            theme = Utilities.trimTheme(filename);
         //don't flip the theme if it is a word
            if(Math.random() < .5 && !theme.equals("word"))	//50% of the time, make the image its horizontal mirror image for more variety
            {
               board = mirrorFlip(board);
               flipped = true;
            }
            if(Math.random() < .5)	//50% of the time, make the image its photographic negative
               board = invert(board);    
            solutionImage = fileNames.get(scoreIndex).getSolutionImage();
         }
      }
      else
      {
         createRandomBoard(); 
         state = false;
      }
      setupBoard();
      startTime = System.currentTimeMillis();
      return state;
   }

//THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
//pre:   arg key is a valid keycode
//post:  changes the cursor's position depending on the key that was pressed (sent from the driver)
//			keeps the player in the bounds of the size of the array board
//			calls appropriate method depending on key hit
   public void userCommand(int key)
   {
      ArrayList<Score> list = fileNames;
      boolean state = true;		//did the intended file load properly?  Used for message sent if not
   
      if(titleScreen)
      {
         titleScreen = false;
         repaint();
         return;
      }
      if(theme.equals("random"))
         list = randomHighScores;
   
      if(key==java.awt.event.KeyEvent.VK_ESCAPE)
      {
         if(updateScores)
         {
            Utilities.selSortSize(randomHighScores);
            Utilities.updateScores(randomHighScores, "themes/randomScores.txt");    
            Utilities.updateScores(fileNames, "themes/fileList.txt");    
         }
         System.exit(1);
      }   
      if(!textInput)	//not in text input mode
      {
         message = "";
      }
      else				//we are in text mode to enter file name and description for user made puzzles
      {
         if((key>='A' && key<='Z') || (key>='0' && key<='9') || key==java.awt.event.KeyEvent.VK_SPACE)
         {
            if((whichText[TEXT_THEME] || whichText[TEXT_NAME] || whichText[TEXT_FILE]) && key==java.awt.event.KeyEvent.VK_SPACE)
            {}			//don't allow a space character when typing the theme since this becomes the file name
            else
            {
               if(whichText[TEXT_NAME])	//only 3 characters for initials
               {
                  if(message.length() < 3)
                     message += (char)(key);
               }
               else
                  if(message.length() < 30)	//max 30 characters
                     message += (char)(key);
            }
         }
         else if(key==java.awt.event.KeyEvent.VK_BACK_SPACE && message.length() > 0) 
            message = message.substring(0, message.length()-1);
         else if(key==java.awt.event.KeyEvent.VK_ENTER)
         {
            if(whichText[TEXT_THEME])
               theme = message;
            else if(whichText[TEXT_DESCR])
               description = message;
            else if(whichText[TEXT_FILE])		//we want to load a file
            {
               state = createThemedBoard(message+".TXT");
               if(!state)
                  message = "Not found.  Selecting random board.";
            }
            else if(whichText[TEXT_NAME])
            {
               player = message;
               if(player.length()==0)
                  player = "AAA";
               if(scoreIndex>=0 && scoreIndex < list.size())
               {
                  if(theme.equals("random"))
                     randomHighScores.get(scoreIndex).setScoreInfo(player, score, completionTime, guess.length*guess[0].length);
                  else
                     fileNames.get(scoreIndex).setScoreInfo(filename, solutionImage, player, score, completionTime, guess.length*guess[0].length);
                  updateScores = true;
               }
            }   
            textInput = false;
            for(int i=0; i<whichText.length; i++)
               whichText[i] = false;
            if(state)
               message = "";
         }
         repaint();			//refresh the screen
         return;
      }
   
      if(key == java.awt.event.KeyEvent.VK_R)				//reset random game or invert painted image
      {
         if(paintMode)
         {
            guess = invert(guess);
            pixelCount(guess);
         }
         else
            createRandomBoard();;
      }
      else if(key == java.awt.event.KeyEvent.VK_T)			
      {
         if(paintMode)		//user types in theme of their user made puzzle - used to generate the file name
         {
            textInput = true;
            whichText[TEXT_THEME] = true;
         }
         else
         {
            state = createThemedBoard(null);
            if(!state)
               message = "Not found.  Selecting random board.";
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_UP && playerR>0 && !solved)
         playerR--;
      else if(key == java.awt.event.KeyEvent.VK_DOWN && playerR<guess.length-1 && !solved)
         playerR++;
      else if(key == java.awt.event.KeyEvent.VK_LEFT && playerC>0  && !solved)
         playerC--;
      else if(key == java.awt.event.KeyEvent.VK_RIGHT && playerC<guess[0].length-1  && !solved)
         playerC++;
      else if(key == java.awt.event.KeyEvent.VK_E && !solved)				//check for errors
      {
         Utilities.checkForErrors();
         solved = checkForWin();
         if(solved && completionTime == -1)
         {
            endTime = System.currentTimeMillis();
            completionTime = (int)((endTime - startTime) / 1000);
            Utilities.playWinScale();
            highScore = Utilities.checkForHighScore(list);
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_H)
      {
         if(paintMode)
         {
            guess = mirrorFlip(guess);								//horiz flip
            flipped = !flipped;
            pixelCount(guess);
         }
         else if(!solved)
            Utilities.helpPlayer();									//use a help ticket
         else if(solved && scoreIndex >= 0 && scoreIndex < list.size())
         {																	//enter high score initials
            textInput = true;
            whichText[TEXT_NAME] = true;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_P)				//toggle paint mode
      {
         if(paintMode)			//we are exiting paint mode before writing to a file
         {
            if(Math.random() < .5)  
               createRandomBoard();				//start with a random pixel board
            else
            {
            //start with a themed board read from a randomly picked file
               state = createThemedBoard(null);
               if(!state)
                  message = "Not found.  Selecting random board.";
            }
            paintMode = false;
         }
         else
         {
            Utilities.clearCanvas(8,8);
            theme = "";
            description = "";
            filename = "";
            paintMode = true;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_ADD || key == java.awt.event.KeyEvent.VK_PLUS || key == java.awt.event.KeyEvent.VK_EQUALS)	
      {
         if(paintMode)
         {
            if(guess.length < 16)
               Utilities.clearCanvas(guess.length+1,guess[0].length+1);	
         }
         else	//game mode - resize screen
         {
            if(size < 64)
               size++;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_SUBTRACT || key == java.awt.event.KeyEvent.VK_MINUS || key == java.awt.event.KeyEvent.VK_UNDERSCORE)	
      {
         if(paintMode)
         {
            if(guess.length > 3)
               Utilities.clearCanvas(guess.length-1,guess[0].length-1);	
         }
         else	//game mode - resize screen
         {
            if(size > 12)
               size--;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_D)
      {	
         if(paintMode)
         {
            textInput = true;												//enter description for user made puzzle
            whichText[TEXT_DESCR] = true;
         }
         else
            difficulty = (difficulty+1) % levels.length;			//change difficulty level
      }
      else if(key == java.awt.event.KeyEvent.VK_F)
      {	
         if(paintMode)
         {
            if(theme.length() == 0 || description.length()==0)	//write to file
               message = "Please enter theme and description first.";
            else
            {
               filename = Utilities.setFileName(theme);
               solutionImage = "none";
               fileNames.add(new Score(filename, solutionImage, "AAA", 0, 0, guess.length*guess[0].length));
               Utilities.selSortSize(fileNames);
               Utilities.writeToFile(filename);	
               updateScores = true;	
               paintMode = false;
               state = createThemedBoard(filename);
               if(!state)
                  message = "Not found.  Selecting random board.";
            }
         }
         else											//we want to load up a specific puzzle
         {
            textInput = true;
            whichText[TEXT_FILE] = true;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_X)				//toggle whether or not the space is ex-ed out or not
      {
         if(paintMode)								//clear cell
         {
            guess[playerR][playerC] = 0;
            pixelCount(guess);
         }
         else if(solved)							//reset high scores
         {
            for(int i=0; i<fileNames.size(); i++)
               fileNames.get(i).setScoreInfo("AAA", 0, 0);
            for(int i=0; i<randomHighScores.size(); i++)
               randomHighScores.get(i).setScoreInfo("AAA", 0, 0);  
            updateScores = true;
         }
         else if( guess[playerR][playerC]!=-1 && guess[playerR][playerC]!=3 && !solved)
         {//you can't alter what the computer places - hint or showing a mistake
            if( guess[playerR][playerC]==2)
               guess[playerR][playerC] = 0;
            else 
               guess[playerR][playerC] = 2;
         }
      }
      else if(key == java.awt.event.KeyEvent.VK_ENTER)		//toggle whether or not the pixel is colored in or not
      {
         if( guess[playerR][playerC]!=-1 && guess[playerR][playerC]!=3 && !solved)
         {//you can't alter what the computer places - hint or showing a mistake
            if( guess[playerR][playerC]==1)
            {
               channels[0].allNotesOff();						//sound elements
               guess[playerR][playerC] = 0;
            }
            else 
            {
               int note = (int)(Math.random()*scale.length);	//sound elements
               channels[0].noteOn(scale[note], 80);			
               guess[playerR][playerC] = 1;
            }
            solved = checkForWin();
            if(solved && completionTime == -1)
            {
               endTime = System.currentTimeMillis();
               completionTime = (int)((endTime - startTime) / 1000);
               Utilities.playWinScale();
               highScore = Utilities.checkForHighScore(list);
            }
         }
         if(paintMode)
            pixelCount(guess);
      }
      else if(key == java.awt.event.KeyEvent.VK_SPACE)		//toggle the value of the array at that position
      {
         if(paintMode)						//just toggle painted or clear when in paint mode
         {
            if( guess[playerR][playerC]==1)
            {
               channels[0].allNotesOff();							//sound elements
               guess[playerR][playerC] = 0;
            }
            else 
            { 
               int note = (int)(Math.random()*scale.length);	//sound elements
               channels[0].noteOn(scale[note], 80);	
               guess[playerR][playerC] = 1;
            }
            pixelCount(guess);
         }
         else if( guess[playerR][playerC]!=-1 && guess[playerR][playerC]!=3 && !solved)
         {//you can't alter what the computer places - hint or showing a mistake
            guess[playerR][playerC] = (guess[playerR][playerC]+1) % 3;
         //sound elements
            if(guess[playerR][playerC] == 1)
            {
               int note = (int)(Math.random()*scale.length);	//sound elements
               channels[0].noteOn(scale[note], 80);	
            }
            else
               channels[0].allNotesOff();						//sound elements
         
            solved = checkForWin();
            if(solved && completionTime == -1)
            {
               endTime = System.currentTimeMillis();
               completionTime = (int)((endTime - startTime) / 1000);
               Utilities.playWinScale();
               highScore = Utilities.checkForHighScore(list);
            }
         }
      }
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      Utilities.drawPixelCount(g);
      Utilities.showBoard(g);					//draw the contents of the array board on the screen
      Utilities.drawInstructions(g);
   }

//***BEGIN MOUSE STUFF***
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration
      {
         repaint();
      }
   }

   public void mouseClicked( MouseEvent e )
   {
      int button = e.getButton();
      if(titleScreen)
      {
         titleScreen = false;
         repaint();
         return;
      }
      int option=Utilities.thereIsOptionHighlighted();
      if(option >= 0)	//if the mouse is hovering over a highlighted option, run the appropriate command
      {
         if(option == OPTION_ESC)
            userCommand(java.awt.event.KeyEvent.VK_ESCAPE);
         else if(option == OPTION_SPACE)
            userCommand(java.awt.event.KeyEvent.VK_SPACE);
         else if(option == OPTION_PLUS)
            userCommand(java.awt.event.KeyEvent.VK_PLUS);
         else if(option == OPTION_MINUS)
            userCommand(java.awt.event.KeyEvent.VK_MINUS);
         else if(option == OPTION_T)
            userCommand(java.awt.event.KeyEvent.VK_T);
         else if(option == OPTION_D)
            userCommand(java.awt.event.KeyEvent.VK_D);
         else if(option == OPTION_F)
            userCommand(java.awt.event.KeyEvent.VK_F);
         else if(option == OPTION_P)
            userCommand(java.awt.event.KeyEvent.VK_P);
         else if(option == OPTION_R)
            userCommand(java.awt.event.KeyEvent.VK_R);
         else if(option == OPTION_ENTER)
            userCommand(java.awt.event.KeyEvent.VK_ENTER);
         else if(option == OPTION_X)
            userCommand(java.awt.event.KeyEvent.VK_X);
         else if(option == OPTION_E)
            userCommand(java.awt.event.KeyEvent.VK_E);
         else if(option == OPTION_H)
            userCommand(java.awt.event.KeyEvent.VK_H);
      }
      else
      {
         if(button == MouseEvent.BUTTON1)
            userCommand(java.awt.event.KeyEvent.VK_SPACE);
         else if(button == MouseEvent.BUTTON3)
            userCommand(java.awt.event.KeyEvent.VK_X);
         if(!textInput)	//not in text input mode
         {
            message = "";
            solved = checkForWin();
            if(solved && completionTime == -1)
            {
               endTime = System.currentTimeMillis();
               completionTime = (int)((endTime - startTime) / 1000);
               Utilities.playWinScale();
               if(theme.equals("random"))
                  highScore = Utilities.checkForHighScore(randomHighScores);
               else
                  highScore = Utilities.checkForHighScore(fileNames);
            }
         }
      }
      repaint();
   }

   public void mousePressed( MouseEvent e )
   {}

   public void mouseReleased( MouseEvent e )
   {}

   public void mouseEntered( MouseEvent e )
   {}

   public void mouseMoved( MouseEvent e)
   {
      mouseX = e.getX();
      mouseY = e.getY();
      int mouseR = (mouseY/size - maxColCount);
      int mouseC = (mouseX/size - maxRowCount);
   
      if((mouseX >= (maxRowCount*size) && mouseX<=(maxRowCount*size+(guess[0].length*size)))
      && (mouseY >= (maxColCount*size) && mouseY<=(maxColCount*size+(guess.length*size)))
      && mouseR>=0 && mouseR<guess.length && mouseC>=0 && mouseC<guess[0].length)
      {
         Utilities.clearHighlightedOptions();	//we are in the grid, so clear the highlighted options
         if(!solved || paintMode)
         {
            playerR = mouseR;								//move the visible game cursor (crosshair) to the mouse location
            playerC = mouseC;
         }
      }
      else
      {
         playerR = guess.length/2;							//put player position in the middle
         playerC = guess[0].length/2;   
      }
      repaint();			//refresh the screen
   }

   public void mouseDragged( MouseEvent e)
   {}

   public void mouseExited( MouseEvent e )
   {}


}
