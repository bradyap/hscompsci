   //Rev. Dr. Douglas R Oberle, March 2013
   import javax.swing.*;
   import java.awt.*;
   import java.util.ArrayList;
   import java.io.*;

   public class Utilities extends Pixoku		//has access to items in Pixoku.java
   {
      //pre:   g != null
      //post:  displays the pixel count for each row and column
      public static void drawPixelCount(Graphics g)
      {
         int x =(maxRowCount*size), y=0;		//upper left corner location of where image will be drawn
      //draw the col pixel numbers
         for(int c=0;c<board[0].length;c++)
         {
            y =0;										//reset the row distance
            for(int i=0; i<maxColCount; i++)
            {
               g.drawImage(GREY.getImage(), x, y, size, size, null);  //scaled image
               if(i<(colCount[c]).size())
               {
                  int value = (colCount[c]).get(i);
                  g.setFont(new Font("Monospaced", Font.BOLD,(int)(size*.75)));
                  g.setColor(Color.yellow);
                  if(solutionCheckCol(c))
                     g.setColor(Color.red.darker());
                  if(value < 10)					//single digit, shift right so it is centered   
                     g.drawString(""+value, x+(size/4), y+size);
                  else
                     g.drawString(""+value, x, y+size);
               }
               y+=size;
            }
            x+=size;
         }
         y=(maxColCount*size);	
         for(int r=0;r<board.length;r++)
         {
            x =0;										//reset the row distance
          												//draw the row pixel numbers
            for(int i=0; i<maxRowCount; i++)
            {
               g.drawImage(GREY.getImage(), x, y, size, size, null);  //scaled image
               if(i<(rowCount[r]).size())
               {
                  int value = (rowCount[r]).get(i);
                  g.setFont(new Font("Monospaced", Font.BOLD,(int)(size*.75)));
                  g.setColor(Color.yellow);
                  if(solutionCheckRow(r))
                     g.setColor(Color.red.darker());
                  g.drawString(""+value, x, y+(size)-(size/4));
               }
               x+=size;
            }
            y+=size;
         
         }
      }
   
      //pre:   g != null
   	//post:  shows different pictures on the screen in grid format depending on the values stored in the array board
   	//			-1:red, 0:white, 1:black, 2:X, 3:square and gives priority to drawing the cursor on top
      public static void showBoard(Graphics g)	
      {   
         int x, y = (maxColCount*size);
      
         for(int r=0;r<guess.length;r++)
         {
            x = (maxRowCount*size);						//reset the row distance
            for(int c=0;c<guess[0].length;c++)
            {
               if(paintMode)	//show the user canvas
               {
                  if(guess[r][c]==0)					//non-painted space
                     g.drawImage(BLANK.getImage(), x, y, size, size, null);  
                  else										//painted pixel
                     g.drawImage(BLACK.getImage(), x, y, size, size, null);  
               }
               else
                  if(solved)	//show the original board with black and white pixels without borders
                  {
                     if(board[r][c]==0)					//non-painted space
                     {
                        if(solutionImage!=null && solutionImage.length() > 0 && !solutionImage.equals("none"))
                           g.drawImage(BLANK.getImage(), x, y, size, size, null);  
                        else										//painted pixel
                           g.drawImage(WHITE.getImage(), x, y, size, size, null);  
                     }
                     else			
                     {							//painted pixel
                        if(solutionImage!=null && solutionImage.length() > 0 && !solutionImage.equals("none"))
                           g.drawImage(PAINTED.getImage(), x, y, size, size, null);
                        else
                           g.drawImage(BLACK.getImage(), x, y, size, size, null);  	
                        
                     }
                  }
                  else			//show the guessing board with bordered blank, painted, ex-ed out and mistake pixels 
                     if(guess[r][c]==0)					//unmarked spaces
                        g.drawImage(BLANK.getImage(), x, y, size, size, null);  
                     else
                        if(guess[r][c]==1)				//painted spaces
                           g.drawImage(PAINTED.getImage(), x, y, size, size, null);  
                        else 
                           if(guess[r][c]==2)			//spaces marked with an X
                              g.drawImage(EXOUT.getImage(), x, y, size, size, null);  
                           else 
                              if(guess[r][c]==3)		//spaces marked by the computer as a mistake
                                 g.drawImage(MISTAKE.getImage(), x, y, size, size, null);  
                              else							//spaces marked by the computer as a hint
                                 g.drawImage(RED.getImage(), x, y, size, size, null); 		 
                                     			
               if(!solved && r==playerR && c==playerC)	//draw the crosshair on the guess after the cell has been drawn
                  g.drawImage(CROSSHAIR.getImage(), x, y, size, size, null);  
               
               x+=size;
            }
            y+=size;
         }
         if(titleScreen)
         {
            x =(maxRowCount*size);	
            y =(maxColCount*size); 
            int width = guess[0].length*size;
            int height = guess.length*size;    
            g.drawImage(TITLE.getImage(), x, y, width, height, null);            
            return;
         }  
         if(!paintMode && solved && solutionImage!=null && solutionImage.length() > 0 && !solutionImage.equals("none")) 
         {	//draw a picture on top of the board
            long currTime = (System.currentTimeMillis() - startTime) / 1000;
            if(currTime % 2 == 0)
            {
               x =(maxRowCount*size);	
               y =(maxColCount*size); 
               int width = guess[0].length*size;
               int height = guess.length*size;    
               if(solution == null)
               {
                  if(flipped)
                     solution = new ImageIcon("themes/Flip"+solutionImage);
                  else
                     solution = new ImageIcon("themes/"+solutionImage);
               }
               g.drawImage(solution.getImage(), x, y, width, height, null);   
            }
         }
      }
   
     //pre: x & y are the current position that an option will be written out to the screen.  Option is a valid index of whichOption array, g != null
     //post:if the mouse is hovering over a game option, highlight it with a different color and set the command in the whichOption array
      public static void highlightMousePosition(Graphics g, int x, int y, int option)
      {
         if(option < 0 || option >= whichOption.length)
            return;
         if(mouseX>=x && mouseX<=x+(19*size*.75) && mouseY>=y && mouseY<=y+size)
         {
            g.setFont(new Font("Monospaced", Font.BOLD,(int)(size*.75)));
            g.setColor(Color.red.brighter());
            whichOption[option] = true;
         }
         else
         {
            g.setFont(new Font("Monospaced", Font.PLAIN,(int)(size*.75)));
            g.setColor(Color.red.darker().darker());
            whichOption[option] = false;
         }
      }
   
      //pre:   g != null
      //post:  displays the key commands and score info
      public static void drawInstructions(Graphics g)
      {
         int x =(maxRowCount*size + board[0].length*size), 
             y =(maxColCount*size);		//upper left corner location of where image will be drawn
         ArrayList<Score> list = fileNames;
         if(theme.equals("random"))
            list = randomHighScores;
         g.setFont(new Font("Monospaced", Font.PLAIN,(int)(size*.75)));
         g.setColor(Color.red.darker());
         if(titleScreen)
         {
            g.drawString("PIXOKU:", x, y+=size);
            g.drawString("a pixel number game", x, y+=size);
            g.drawString("-------------------", x, y+=size);
            g.drawString("Numbers give clues ", x, y+=size);
            g.drawString("for the sequential ", x, y+=size);
            g.drawString("pixels in a row or ", x, y+=size);
            g.drawString("column.            ", x, y+=size);
            g.drawString("Paint the entire   ", x, y+=size);
            g.drawString("picture to complete", x, y+=size);
            g.drawString("the puzzle.        ", x, y+=size);
            g.drawString("-------------------", x, y+=size);				
            g.drawString("CLICK/KEY to begin.", x, y+=size);
            return;
         }
         if(paintMode)
         {
            highlightMousePosition(g,x,y,OPTION_SPACE);
            g.drawString("SPACE: toggle pixel", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_PLUS);
            g.drawString("+:    increase size", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_MINUS);
            g.drawString("-:    decrease size", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_H);
            g.drawString("H:  horizontal flip", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_R);
            g.drawString("R:     invert image", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_T);
            g.drawString("T:      enter theme", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_D);
            g.drawString("D:enter description", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_F);
            g.drawString("F:    write to file", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_P);
            g.drawString("P:  exit paint mode", x, y+=size);
            highlightMousePosition(g,x,y,OPTION_ESC);
            g.drawString("ESC:           quit", x, y+=size);
            g.setFont(new Font("Monospaced", Font.PLAIN,(int)(size*.75)));
            g.setColor(Color.red.darker());
            if(theme!= null && theme.length() > 0)
               g.drawString("THEME:      "+theme, x, y+=size);
            else
               g.drawString("THEME:       none chosen yet", x, y+=size);
            if(description!= null && description.length() > 0)
               g.drawString("DESCRIPTION:"+description, x, y+=size);
            else
               g.drawString("DESCRIPTION: none chosen yet", x, y+=size);
            if(filename== null || filename.length() == 0)
               g.drawString("STATUS:        not saved yet", x, y+=size);
            else
               g.drawString("STATUS-saved as "+filename, x, y+=size);
            if(textInput)
            {
               g.setColor(Color.yellow);
               g.fillRect(x, y+(size/2) ,(size*13), size);
            }
            if(message.length() > 0)
            {
               g.setColor(Color.red.darker());
               g.drawString(message, x, y+=size);
            }
         }
         else
            if(solved)
            {
               //g.setColor(Color.red.darker());
               g.drawString(description, x, y+=size);
               if(numErrors==0 && hint==3)
                  g.drawString("Perfect game in "+completionTime+" seconds.", x, y+=size);
               else
                  g.drawString("Solved in "+completionTime+" seconds.", x, y+=size);
               g.drawString("SCORE: " + score, x, y+=size);
               g.drawString("ERRORS:" + numErrors, x, y+=size);
               g.drawString("UNUSED HINTS:" + hint, x, y+=size);
               if(numErrors==0 && hint==3)
                  g.drawString("PERFECT GAME!", x, y+=size);
               if(highScore && scoreIndex >= 0 && scoreIndex < list.size())
                  g.drawString("HIGH SCORE ACHIEVED!", x, y+=size);
               else if(scoreIndex >= 0 && scoreIndex < list.size())
                  g.drawString(list.get(scoreIndex).showScore(), x, y+=size);
               
               //g.setColor(Color.yellow.darker());
               if(highScore && scoreIndex >= 0 && scoreIndex < list.size() && player.equals("AAA"))
               {
                  highlightMousePosition(g,x,y,OPTION_H);
                  g.drawString("H:   enter initials", x, y+=size);
               }
               highlightMousePosition(g,x,y,OPTION_R);
               g.drawString("R:reset random game", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_T);
               g.drawString("T:reset themed game", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_D);
               g.drawString("D: difficulty level", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_P);
               g.drawString("P:toggle paint mode", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_F);
               g.drawString("F:   load game file", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_X);
               g.drawString("X:reset high scores", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_ESC);
               g.drawString("ESC:           quit", x, y+=size);
               g.setFont(new Font("Monospaced", Font.PLAIN,(int)(size*.75)));
               g.setColor(Color.red.darker());
               g.drawString("LEVEL:"+levels[difficulty], x, y+=size);
               if(textInput)
               {
                  g.setColor(Color.yellow);
                  g.fillRect(x, y+(size/2) ,(size*13), size);
               }
               if(message.length() > 0)
               {
                  g.setColor(Color.red.darker());
                  g.drawString(message, x, y+=size);
               }
            }
            else
            {
               //g.setColor(Color.yellow.darker());
               long currTime = (System.currentTimeMillis() - startTime) / 1000;
               highlightMousePosition(g,x,y,OPTION_ENTER);
               g.drawString("ENTER:  paint pixel", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_SPACE);
               g.drawString("SPACE: toggle pixel", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_X);
               g.drawString("X:   mark with an X", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_E);
               g.drawString("E:      error check", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_H);
               g.drawString("H:     use a hint:"+hint, x, y+=size);
               highlightMousePosition(g,x,y,OPTION_R);
               g.drawString("R:reset random game", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_T);
               g.drawString("T:reset themed game", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_D);
               g.drawString("D: difficulty level", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_P);
               g.drawString("P:toggle paint mode", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_F);
               g.drawString("F:   load game file", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_PLUS);
               g.drawString("+:    larger screen", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_MINUS);
               g.drawString("-:   smaller screen", x, y+=size);
               highlightMousePosition(g,x,y,OPTION_ESC);
               g.drawString("ESC:           quit", x, y+=size);
               g.setFont(new Font("Monospaced", Font.PLAIN,(int)(size*.75)));
               g.setColor(Color.red.darker());
               g.drawString("LEVEL:"+levels[difficulty], x, y+=size);
               g.drawString("THEME:"+theme, x, y+=size);
               g.drawString("TIME: "+currTime, x, y+=size);
               if(textInput)
               {
                  g.setColor(Color.yellow);
                  g.fillRect(x, y+(size/2) ,(size*13), size);
               }
               if(message.length() > 0)
               {
                  g.setColor(Color.red.darker());
                  g.drawString(message, x, y+=size);
               }
            }
      }
      
   	//pre:  fileName exists as a text file and contains 0 and 1 separated by a space, every row has the same # characters
   	// 	first two values in the file are the #rows and #columns - returns true if read in successfully
    	//post: fills up the board array with data from fineName
      public static boolean readFileToBoard(String fileName)
      {
         int r = 0, c = 0;
         int numRows = 0;
         int numCols = 0;
      
         try
         {
            java.util.Scanner input = new java.util.Scanner(new FileReader(fileName));
            int value = 0;
                   
            try
            {
               description = input.nextLine();								//read image description to be revealed when the puzzle is complete
               numRows = input.nextInt();										//read in image dimensions to set array size
               numCols = input.nextInt();
            }
               catch (java.util.InputMismatchException ex1)			//file is corrupted or doesn't exist
               {
                  System.out.println("File " + fileName + " is corrupt");
                  return false;
               }			
               catch (java.util.NoSuchElementException ex2)			//file is corrupted or doesn't exist
               {
                  System.out.println("File " + fileName + " is corrupt");
                  return false;
               }	
            if(numRows<=0 || numCols<=0)
            {
               System.out.println("File " + fileName + " is corrupt");
               return false;
            }	 		
            board = new int[numRows][numCols];
            guess = new int[numRows][numCols];
            while (r*c <= numRows*numCols && input.hasNextInt())		//while there is another value in the file
            {
               try
               {
                  value = input.nextInt();
               }
                  catch (java.util.InputMismatchException ex1)			//file is corrupted or doesn't exist
                  {
                     System.out.println("File " + fileName + " is corrupt");
                     return false;
                  
                  }			
                  catch (java.util.NoSuchElementException ex2)			//file is corrupted or doesn't exist
                  {
                     System.out.println("File " + fileName + " is corrupt");
                     return false;
                  
                  }	
                  catch (java.lang.ArrayIndexOutOfBoundsException ex3)			//file is corrupted or doesn't exist
                  {
                     System.out.println("File " + fileName + " is corrupt");
                     return false;
                  
                  }	
               if(c>=board[0].length)
               {
                  r++;
                  c=0;
               }   
               if(r< board.length && c < board[0].length)
               {
                  board[r][c] = value;
                  guess[r][c] = 0;	
                  c++;
               }
            }
            input.close();	
         }
            catch (IOException ex3)			//file is corrupted or doesn't exist - clear high scores and remake the file
            {
               System.out.println("File " + fileName + " is corrupt");
               return false;
            }	
         if(r!=numRows-1 || c!=numCols)
         {
            System.out.println("File " + fileName + " is corrupt");
            return false;			
         }
         return true;
      }
   
     //pre:  fileName is the name of a valid file in the themes folder comprised of <puzzle file name> <player name> <score> <time> <total # cells>
     //post: fills up list with puzzle high scores from fileName
      public static void readHighScores(String fileName, ArrayList<Score> list)
      {
         try
         {
            java.util.Scanner input = new java.util.Scanner(new FileReader(fileName));
            while (input.hasNextLine())		//while there is another value in the file
            {
               try
               {
                  String filename = input.next();
                  String solutionImage = input.next();
                  String playername = input.next();
                  int score = input.nextInt();
                  int time = input.nextInt();
                  int size = input.nextInt();
                  list.add(new Score(filename, solutionImage, playername, score, time, size));
               }
                  catch (java.util.InputMismatchException ex1)			//file is corrupted or doesn't exist
                  {
                     System.out.println("File is corrupt");
                     return;
                  
                  }			
                  catch (java.util.NoSuchElementException ex2)			//file is corrupted or doesn't exist
                  {
                     System.out.println("File is corrupt");
                     return;
                  
                  }	
            }
            input.close();	
         }
            catch (IOException ex3)			//file is corrupted or doesn't exist - clear high scores and remake the file
            {
               System.out.println("File does not exist");
               return;
            }		
      }
   
    //pre:  fileName is the name of the user created puzzle theme and is unique to the list of files in fileList.txt
    //post: Writes the image to file
      public static void writeToFile(String fileName)
      {
         PrintStream imageWriter = null;
         File imageFile = new File("themes/"+fileName);
               
         while(imageWriter == null){
            try{
               imageWriter = new PrintStream(new FileOutputStream(imageFile, true));
            }
               catch(Exception E){
                  System.exit(42);
               }
         }
         imageWriter.println(description);
         imageWriter.println(guess.length + " " + guess[0].length);
         for(int r = 0; r < guess.length; r++)
         {
            String StringPlayerGrid = "";
            for(int c = 0; c < guess[0].length; c++)
            {
               if(guess[r][c] != 0 && guess[r][c] != 2 && guess[r][c] != 3)
               {
                  StringPlayerGrid += guess[r][c];
                  if(c + 1 != guess[0].length)
                  {
                     StringPlayerGrid += " ";
                  }
               }
               else
               {
                  StringPlayerGrid += 0;
                  if(c + 1 != guess[0].length)
                  {
                     StringPlayerGrid += " ";
                  }
               }
            }
            imageWriter.println(StringPlayerGrid);
         }
         imageWriter.close();
      }
   
      //pre:  given the name of an image file i.e."themes/arcade3.txt"
   	//post: returns the name of the theme   i.e."arcade" to be displayed in the game info window
      public static String trimTheme(String word)
      {
         //word = "themes/animal11.txt";
         if(word==null || word.length()==0)
            return null;
         if(word.startsWith("themes/"))
            word = word.substring(7);
         if(word.endsWith(".txt"))
            word = word.substring(0,word.length()-4);
         int i=0; 
         for(i=0; i<word.length(); i++)
            if(Character.isDigit(word.charAt(i)))
               break;
         if(i>0)
            word = word.substring(0,i);      
         return word;
      }
     
   //pre:   list contains all theme file names, word is not null and contains the user made puzzle theme name
   //post:  returns true if we can find word within list
      private static boolean search(ArrayList<Score>list, String word)
      {
         for(Score current: list)
            if((current.getFileName()).equals(word))
               return true;
         return false;
      }
     
     //pre:  given the name of a user created puzzle theme
     //post: checks to see if there is a file with that name already - adds a unique number after it if there is 
      public static String setFileName(String word)
      {
         int num = 1;
         word = word.toLowerCase();
         while(search(fileNames, (word+num+".txt")))
            num++;
         return word+num+".txt";
      }
   	
   	//pre:  grid is either the solution board (board) or the guessing board (guess - which is also the user-defined puzzle canvas)
      public static void initBoard(int[][] grid)
      {
         playerR = grid.length/2;							//start player position in the middle
         playerC = grid[0].length/2;   	
         pixelCount(grid);
         message = "";
         player = "AAA";
         highScore = false;
         completionTime = -1;
         whichText = new boolean[4];
         for(int i=0; i<whichText.length; i++)
            whichText[i] = false;   
         whichOption = new boolean[13];
         for(int i=0; i<whichOption.length; i++)
            whichOption[i] = false;   
      }
   
    //pre:  guess and board are the same dimensions
   //post: replaces incorrect painted pixels with error ex's to take away from the score for each one
      public static void checkForErrors()
      {
         int numPixels = 0;
         for(int r=0;r<guess.length;r++)					
            for(int c=0;c<guess[0].length;c++)
            {
               if(Math.abs(guess[r][c])==1 && board[r][c]!=1)//player painted a cell that is not in the solution
               {
                  guess[r][c]=3;										//change to the boxed-exed cell
                  numPixels++;  
               }
               else
                  if(guess[r][c]==2 && board[r][c]==1)		//player exed out a cell that is in the solution
                  {
                     guess[r][c]=-1;								//change to a red cell
                     numPixels++;  
                  }
            }
         if(numPixels==0)
         {
            message = "no errors";
            //System.out.println("There are no errors");
         }
         else
         {
            channels[0].allNotesOff(); 				//turn sounds off
            channels[0].noteOn(scale[0]-12, 80);
            if(numPixels==1)
               message = ""+numPixels+" error";
            else
               message = ""+numPixels+" errors";
            //System.out.println("There are "+numPixels+" errors");
         }
         numErrors += numPixels;
      }
   
    //post:  hint option - reveals 1, 2 or 3 unguessed pixels (random)
      public static void helpPlayer()
      {
         ArrayList<Integer>rows = new ArrayList();	//will store coordinates for painted pixels not yet found
         ArrayList<Integer>cols = new ArrayList();	//we will pick from these to give to the player and paint red (value 4)
         if(hint>0)
         {
            int numFree = 0;							//the number of free random painted pixels we will help the player with
            for(int r=0;r<guess.length;r++)					
               for(int c=0;c<guess[0].length;c++)
               {
                  if(Math.abs(guess[r][c])!=1 && board[r][c]==1)
                  {//store the row and col of the unguessed spot so we can choose some random ones
                     rows.add(r);
                     cols.add(c);
                  }  
               }
            if(rows.size()==0)
               return;
            if(rows.size()<=2)
               numFree=1;
            else
               numFree = (int)(Math.random()*3) + 1;
            for(int i=0; i<numFree; i++)
            {   
               int index = (int)(Math.random()*rows.size());
               int freeRow = rows.get(index);
               int freeCol = cols.get(index);
               guess[freeRow][freeCol] = -1;	
            }   
            channels[0].allNotesOff(); 				//turn sounds off
            channels[0].noteOn(scale[scale.length-1]+12, 80);	   
            hint--;
         }
         else
         {
            channels[0].allNotesOff(); 				//turn sounds off
            channels[0].noteOn(scale[0]-12, 80);
            message = "all hints used";
            //System.out.println("All hints have been used up");
         }
      }
     
     //pre:  0 <= c < guess[0].length
     //post: checks the guess board at a certain column (c) to see if the number of pixels painted matches the pixel count for that column 
      public static boolean solutionCheckCol(int c)
      {
         ArrayList<Integer>temp = new ArrayList();
         int count=0;
         for(int r=0;r<board.length;r++)	
         {
            if(Math.abs(guess[r][c])==1)	//colored pixel
               count++;
            else 
               if(count>0)
               {   
                  temp.add(count);
                  count=0;
               }
         }
         if(count>0)
            temp.add(count);
         if(temp.size() != (colCount[c]).size())
            return false;
         for(int i=0; i<temp.size(); i++)
         {    
            if(!(temp.get(i)).equals((colCount[c]).get(i)))
               return false;
         }
         return true;	
      }
   
     //pre:  0 <= r < guess.length
     //post: checks the guess board at a certain row (r) to see if the number of pixels painted matches the pixel count for that row 
      public static boolean solutionCheckRow(int r)
      {
         ArrayList<Integer>temp = new ArrayList();
         int count=0;
         for(int c=0;c<board[0].length;c++)	
         {
            if(Math.abs(guess[r][c])==1)	//colored pixel
               count++;
            else 
               if(count>0)
               {   
                  temp.add(count);
                  count=0;
               }
         }
         if(count>0)
            temp.add(count);
         if(temp.size() != (rowCount[r]).size())
            return false;
         for(int i=0; i<temp.size(); i++)
         {    
            if(!(temp.get(i)).equals((rowCount[r]).get(i)))
               return false;
         }
         return true;	
      }
   
   //pre:  numRows, numColumns > 0   
   //post:  gets the board ready for paint mode
      public static void clearCanvas(int numRows, int numColumns)
      { 
         board = new int[numRows][numColumns];
         guess = new int[numRows][numColumns];
         for(int r=0;r<board.length;r++)					//fill with values (0)
            for(int c=0;c<board[0].length;c++)
            {
               board[r][c] = 0;
               guess[r][c] = 0;
            }
         initBoard(guess);
      }
   
   //post:  clear the array of highlighted options (for when the mouse moves off of the options list)
      public static void clearHighlightedOptions()
      {
         for(int i=0; i<whichOption.length; i++)
            whichOption[i] = false;
      }
      
   	//post:  return the index of which option is highlighted (to call the correct command when the mouse is clicked)
      public static int thereIsOptionHighlighted()
      {
         for(int i=0; i<whichOption.length; i++)
            if(whichOption[i])
               return i;
         return -1;
      
      }
   
   	//pre:  nums is non-empty and a and b are valid indecies of the array
   	//post: nums[a] swaps values with nums[b]
      public static void swap(ArrayList<Score> nums, int a, int b)
      {
         Score temp = nums.get(a);
         nums.set(a, nums.get(b));
         nums.set(b,temp);
      }
   
   	//pre:  nums is non-empty
   	//post: array is sorted by puzzle name
      public static void selSortName(ArrayList<Score> nums)
      {
         int min, size = nums.size();
         for (int i=0; i < size; i++)
         {
            min = i;
            for (int j = i + 1; j < size; j++)
            {
               if (nums.get(j).getFileName().compareTo(nums.get(min).getFileName()) < 0 )
                  min = j;
            }
            swap (nums, i, min);
         }
      }
      
   		//pre:  nums is non-empty
   	//post: array is sorted by puzzle size (total # of cells)
      public static void selSortSize(ArrayList<Score> nums)
      {
         int min, size = nums.size();
         for (int i=0; i < size; i++)
         {
            min = i;
            for (int j = i + 1; j < size; j++)
            {
               if (nums.get(j).getSize() < nums.get(min).getSize())
                  min = j;
            }
            swap (nums, i, min);
         }
      }
   
   
   //pre:  list != null
   //post: writes to high scores file (fileName) from the high score list
      public static void updateScores(ArrayList<Score>list, String fileName)
      {
         try
         {
            System.setOut(new PrintStream(new FileOutputStream(fileName)));
            for(int i = 0; i < list.size(); i++)
            {
               System.out.print(list.get(i));
               if(i != list.size()-1)
                  System.out.println();
            }
         }
            catch (IOException e)
            {
            }
      }
      
   	//pre:  list != null
   	//post:  looks to see if the player achieved a high score
      public static boolean checkForHighScore(ArrayList<Score>list)
      {
         int size = (board.length*board[0].length);
         if(scoreIndex < 0 || scoreIndex >= list.size())
         {
            if(theme.equals("random"))	//no high score recorded for a random puzzle of this size, so add one
            {
               list.add(new Score("random"+size, "none", "AAA", 0, 0, size));
               scoreIndex = list.size()-1; 
            }
            else
               return false;
         }
         Score temp = new Score("AAA", "none", "AAA", score, completionTime, size);   
         Score current = list.get(scoreIndex);
         if(temp.compareTo(current) > 0)
            return true;
         return false;
      }
   
    //post:  play the randomly selected scale and tone at the start of the game
      public static void playStartScale()
      {
         int WHOLENOTE=192;
         try {
            int noteLength = WHOLENOTE;
            for(int i=0; i<scale.length; i++)
            {
               channels[0].noteOn(scale[i], 80);
               if(i==scale.length-1)
                  Thread.sleep( noteLength * 2);	//make the last note longer
               else
                  Thread.sleep( noteLength );
               channels[0].allNotesOff(); 				//turn sounds off
            }
         }
            catch (Exception e) {
               e.printStackTrace();
            }
         channels[0].allNotesOff(); 				//turn sounds off
      }
   
   //post:  play the randomly selected scale and tone adter the player wins
      public static void playWinScale()
      {
         int WHOLENOTE=192;
         try {
            int noteLength = WHOLENOTE;
            for(int i=scale.length-1; i>=0; i--)
            {
               channels[0].noteOn(scale[i], 80);
               if(i==0)
                  Thread.sleep( noteLength * 2);	//make the last note longer
               else
                  Thread.sleep( noteLength );
               channels[0].allNotesOff(); 				//turn sounds off
            }
         }
            catch (Exception e) {
               e.printStackTrace();
            }
         channels[0].allNotesOff(); 				//turn sounds off
      }
   
   }