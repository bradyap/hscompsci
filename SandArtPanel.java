import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

@SuppressWarnings("serial")
public class SandArtPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener
{	
   private static int delay;		         			//#miliseconds delay between each screen refresh for the timer        
   private Timer t;											//used to set the speed
   private int numFrames;                          //keep track of total # frames for timing events in Listener
   private static int screenWidth, screenHeight;	//dimensions of the screen
   private static int textSize;

   protected static int mouseX;							//locations for the mouse pointer
   protected static int mouseY;
   protected static ImageIcon paintMouse = new ImageIcon("mouseCursors/paint.GIF");
   protected static ImageIcon eraseMouse = new ImageIcon("mouseCursors/erase.GIF");
   protected static ImageIcon faucetMouse = new ImageIcon("mouseCursors/faucet.GIF");
   protected static ImageIcon arrowMouse = new ImageIcon("mouseCursors/arrow.GIF");

   private static Color[][] snow;					

   private static final byte DRAW=0, ERASE=1, FAUCET=2, NUM_MODES=3;
   private static byte cursorMode;						//DRAW, ERASE r FAUCET
   private static String[] brushes = {"  DRAW"," ERASE","FAUCET"};
   private static int brushSize;							//size of brush to paint walls
   private static boolean rain;							//do we rain snow from the top?
   private static int rainFreq;							//density of snow
   private static final String[] forcast = {"  STEADY", "VARIABLE", "  RANDOM"};
   private static int freqIndex;                   //will the frequency of snow change or not
   private static final String[] winds = {"  STEADY", "VARIABLE", "  RANDOM"};
   private static int windIndex;                   //will the winds change?
   private static int windPower;                   //-10 to +10

   private static int colorVariation;					//how much color variation
   private static Color currentColor;	            //current color of snow
   private static Color backgroundColor;           //color of the background
   private static int red, green, blue;            //for assigning currentColor
   private static final String[] colorChange = {"NONE", "SUBTLE", "RANDOM"};
   private static int changeIndex;                 //will the current color not change, change subtlely or randomly (0, 1, 2)
   private static final String[] weight = {" LIGHT", "MEDIUM", " HEAVY"};
   private static int weightIndex;                 //weight of snow - determines how they settle

   public SandArtPanel()
   {
      numFrames = 0;
      screenWidth = 500; 
      screenHeight = 500;    
      textSize = 15;
   
      snow = new Color[500][500];
      cursorMode = DRAW;
      brushSize = 5;
      rain = false;
      rainFreq = 9;
      freqIndex = 0;
      windPower = 0;
      windIndex = 0;
      colorVariation = 20;
      currentColor = randomColor();
      backgroundColor = Color.black;
      changeIndex = 2;
      weightIndex = 0;
   
      addMouseListener( this );
      addMouseMotionListener( this );
      addMouseWheelListener(this);
      mouseX = 0;
      mouseY = 0;
   
      delay = 0;
      t = new Timer(delay, new Listener());			//the higher the value of DELAY, the slower the enemy will move
      t.start();
   }

//post:  returns true if x and y are valid screen coordinates
   private static boolean isValid(double r, double c)
   {
      return (r >= 0 && c >= 0 && r < snow.length && c < snow[0].length);
   }

//post: returns a random color
   public static Color randomColor()
   {
      red = (int)(Math.random()*254)+1;
      green = (int)(Math.random()*254)+1;
      blue = (int)(Math.random()*254)+1;
      return new Color(red, green, blue);
   }
   
    //post: returns a random color
   public static Color makeRandomColor()
   {
      int red = (int)(Math.random()*256);
      int green = (int)(Math.random()*256);
      int blue = (int)(Math.random()*256);
      return new Color(red, green, blue);
   }

//pre: c != null
//post:returns a slight variation of the color c
   public static Color varyColor(Color c)
   {
      int redX = Math.abs(c.getRed() + ((int)(Math.random()*(colorVariation*2))-colorVariation));
      if(redX > 255)
         redX = 255;
      int greenX = Math.abs(c.getGreen() + ((int)(Math.random()*(colorVariation*2))-colorVariation));
      if(greenX > 255)
         greenX = 255;
      int blueX = Math.abs(c.getBlue() + ((int)(Math.random()*(colorVariation*2))-colorVariation));
      if(blueX > 255)
         blueX = 255;
      return new Color(redX, greenX, blueX);
   }
   
 //pre:  c != null
 //post: given a color, returns a 1, 2 or 3 to determine snow size
   public static int colorToSize(Color c)
   {
      return ((c.getRed() + c.getGreen() + c.getBlue()) % 4) + 1;
   }

 //post:  removes all plaforms (White colored particles)
   public static void clearPlatforms()
   {
      for(int r = 0; r < snow.length; r++)
      {
         for(int c = 0; c < snow[0].length; c++)
         {
            Color snowColor = snow[r][c];
            if(snowColor != null && snowColor.equals(Color.white))
               snow[r][c] = null;
         }
      }
   }

  //post:  display all snow, platforms and instructions on the screen
   public static void showBoard(Graphics g)	
   {
      for(int r = 0; r < snow.length; r++)
      {
         for(int c = 0; c < snow[0].length; c++)
         {
            Color snowColor = snow[r][c];
            if(snowColor != null)
            {
               int size = colorToSize(snowColor);
               g.setColor(snowColor);
               if(snowColor.equals(Color.white))
                  size = 1;    
               g.fillRect(c, r, size, size);
            }
         }
      }
      int x = screenWidth +  textSize/2;
      int y = textSize/2;
      g.setColor(Color.black);
      g.fillRect(screenWidth, 0, screenWidth+200, screenHeight);
      g.setFont(new Font("Monospaced", Font.PLAIN,textSize));
      g.setColor(Color.lightGray);
      g.drawString("******SAND ART*****", x, y+=textSize);
      g.drawString("ESC:          quit", x, y+=textSize);
      g.drawString("</>:       DELAY:" +delay, x, y+=textSize);
      g.drawString("+/-:  BRUSH SIZE:"+brushSize, x, y+=textSize);
      g.drawString("INS:    TYPE:"+brushes[cursorMode], x, y+=textSize);
      
      g.drawString("******FORCAST******", x, y+=textSize);
      g.drawString("SPACE:   start/stop", x, y+=textSize);
      g.drawString("0-9:    FREQUENCY:" +rainFreq, x, y+=textSize);
      g.drawString("F: FORCAST:" +forcast[freqIndex], x, y+=textSize);
      
      g.drawString("*******WINDS*******", x, y+=textSize);     
      g.drawString("WIND: " +winds[windIndex] + " at "+windPower, x, y+=textSize);
      g.drawString("UP:    change winds", x, y+=textSize);
      g.drawString("DOWN: disable winds", x, y+=textSize);
      g.drawString("LEFT/RIGHT:set wind", x, y+=textSize);
   
      g.drawString("W:    WEIGHT:" +weight[weightIndex], x, y+=textSize);
      
      g.drawString("*****PLATFORMS*****", x, y+=textSize);
      g.drawString("V: REMOVE PLATFORMS", x, y+=textSize);
      g.drawString("F1-F3: flip mult 90", x, y+=textSize);
          
      g.drawString("******COLORS*******", x, y+=textSize);
      g.drawString("A:AUTO MORPH:" +colorChange[changeIndex], x, y+=textSize);
      g.drawString("ENTER: random color", x, y+=textSize);
      g.setColor(currentColor);
      g.fillRect(x+(textSize*3), y+textSize/2, textSize*2, textSize*2);
      g.setColor(Color.lightGray);
      g.drawString("r/R:        Red:"+red, x, y+=textSize);
      g.drawString("g/G:      Green:"+green, x, y+=textSize);
      g.drawString("b/B:       Blue:"+blue, x, y+=textSize);
      g.drawString("I:    invert colors", x, y+=textSize);
      g.drawString("Z:random background", x, y+=textSize);
      g.drawString("X: clear background", x, y+=textSize);
      
      g.drawString("**MOUSE CONTROLS***", x, y+=textSize);
      g.drawString("WHEEL:   brush size", x, y+=textSize);
      g.drawString("LEFT:   paint/erase", x, y+=textSize);
      g.drawString("RIGHT:       "+brushes[cursorMode], x, y+=textSize);    
      
              
      if(mouseX >= x)
         g.drawImage(arrowMouse.getImage(), mouseX-(brushSize), mouseY-(brushSize), 16, 16, null);  //scaled image
      else
      {
         if(cursorMode==ERASE)
            g.drawImage(eraseMouse.getImage(), mouseX-(brushSize), mouseY-(brushSize), brushSize*2, brushSize*2, null);  //scaled image
         else if(cursorMode==FAUCET)
            g.drawImage(faucetMouse.getImage(), mouseX-(brushSize), mouseY-(brushSize), brushSize*2, brushSize*2, null);  //scaled image
         else
            g.drawImage(paintMouse.getImage(), mouseX-(brushSize), mouseY-(brushSize), brushSize*2, brushSize*2, null);  //scaled image
      }
   }

   public static void moveSnow()
   {
      for(int c = 0; c < snow[0].length; c++)
      {
      
         for(int r = snow.length-1; r >= 0; r--)             //bottom row moves first
         {
            Color type = snow[r][c];                         //type: color of current snow to possibly move
            Color typeBelow = null;
            if(r+1 < snow.length)                            //typeBelow: color of snow (if any) of the spot below current snow 
               typeBelow = snow[r+1][c];
            Color belowLeft = null;
            if(r+1 < snow.length && c-1 >= 0)                //belowLeft: color of snow (if any) of the spot below and left of the current snow
               belowLeft = snow[r+1][c-1];
            Color belowRight = null;
            if(r+1 < snow.length && c+1 < snow[0].length)    //belowRight: color of snow (if any) of the spot below and right of the current snow
               belowRight = snow[r+1][c+1];
            Color left = null;
            if(c-1 >= 0)                                     //left: color of snow (if any) of the spot left of the current snow
               left = snow[r][c-1];
            Color right = null;
            if(c+1 < snow[0].length)                         //right: color of snow (if any) of the spot right of the current snow
               right = snow[r][c+1];   
            
            if(type!=null)
            {
               boolean windPushEast = (windPower > 0 && Math.random() < windPower/11.0);
               double rand = Math.random();     //allow possibility that the snow wanders left or right as it falls 
               if(weight[weightIndex].contains("MEDIUM"))
                  rand *= 4;
               else if(weight[weightIndex].contains("HEAVY"))
                  rand *= 8;
               if((type.equals(Color.white)) || (typeBelow!=null && typeBelow.equals(Color.white)) || (typeBelow!=null && belowLeft!=null && belowRight!=null))
               {
               //snow stays put:  its a wall, or there is a wall beneath it or other snow below, below left and below right
                  if(!type.equals(Color.white))
                  {
                     if(weight[weightIndex].contains("HEAVY") || ((weight[weightIndex].contains("MEDIUM")  && Math.random() < .5) || (windPushEast && right==null)))
                     {
                        if(left==null && right==null)
                        {                             //nothing left or right, so randomly move either left or right
                           snow[r][c] = null;
                           if(Math.random() < .5 || windPushEast)
                           {
                              if(isValid(r, c+1))
                                 snow[r][c+1] = type;
                           }
                           else
                           {
                              if(isValid(r, c-1))
                                 snow[r][c-1] = type;
                           }
                        }
                        else if(right==null)
                        {
                           snow[r][c] = null;
                           if(isValid(r, c+1))
                              snow[r][c+1] = type;
                        }
                        else if(left==null)
                        {
                           snow[r][c] = null;
                           if(isValid(r, c-1))
                              snow[r][c-1] = type;
                        }
                     }
                  }
               }
               else if(r+1>=snow.length || snow[r+1][c] == null)	
               {
                  snow[r][c] = null;	               //nothing below it, so drop down a step
                  if(belowLeft==null && belowRight==null)
                  {
                     if(rand < .33 || windPushEast)
                     {
                        if(isValid(r+1, c+1))
                           snow[r+1][c+1] = type;
                     }
                     else if(rand < .66)
                     {
                        if(isValid(r+1, c-1))
                           snow[r+1][c-1] = type;
                     }
                     else if(isValid(r+1, c))
                        snow[r+1][c] = type;
                  }
                  else if(belowRight!=null && belowLeft==null)
                  {                                   //nothing below, nothing below left, something below right
                     if(rand < .33 && isValid(r+1, c-1))
                        snow[r+1][c-1] = type;
                     else if(isValid(r+1, c))
                        snow[r+1][c] = type;
                  }
                  else if(belowLeft!=null && belowRight==null) 
                  {                                   //nothing below, nothing below right, something below left
                     if((rand < .33 || windPushEast) && isValid(r+1, c+1))
                        snow[r+1][c+1] = type;
                     else if(isValid(r+1, c))
                        snow[r+1][c] = type;
                  }
                  else if(isValid(r+1, c))
                     snow[r+1][c] = type;
               }
               else
                  if(typeBelow!=null && belowLeft==null && belowRight==null)
                  {											//if there is something below it but not below left and right, randomly move below left or below right
                     snow[r][c] = null;
                     if(rand < .5 || windPushEast)
                     {
                        if(isValid(r+1, c+1))
                           snow[r+1][c+1] = type;
                     }
                     else
                     {
                        if(isValid(r+1, c-1))
                           snow[r+1][c-1] = type;
                     }
                  }
                  else										//snow below and below left, so move below right
                     if(typeBelow!=null && belowLeft!=null && belowRight==null)
                     {
                        snow[r][c] = null;
                        if(isValid(r+1, c+1))
                           snow[r+1][c+1] = type;
                     }
                     else									//snow below and below right, so move below left
                        if(typeBelow!=null && belowLeft==null && belowRight!=null)
                        {
                           snow[r][c] = null;
                           if(isValid(r+1, c-1))
                              snow[r+1][c-1] = type;
                        }
            }
         }
      }
   }

//THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
//pre:   k is a valid keyCode
//post:  performs action depending on what key the user hit
   public void processUserInput(int k, boolean shiftPressed)
   {
      if(k==KeyEvent.VK_ESCAPE)	//End the program	
         System.exit(1);
      
      if(k==KeyEvent.VK_ENTER)								//change snow to a random color
         currentColor = randomColor();
      else if(k==KeyEvent.VK_A)								//change value for how colors change automatically
         changeIndex = (changeIndex+1)%colorChange.length;
      else if(k==KeyEvent.VK_F1)                      //flip the screen 90
         SandUtilities.rotateRight(snow);
      else if(k==KeyEvent.VK_F2)                      //flip the screen 180
         SandUtilities.flipUpsideDown(snow);
      else if(k==KeyEvent.VK_F3)                      //flip the screen 270
         SandUtilities.rotateLeft(snow);
      else if(k==KeyEvent.VK_B)
      {
         if(shiftPressed && blue > 1)
            blue--;
         else if(blue < 254)
            blue++;
         currentColor = new Color(red, green, blue);  
      }
      else if(k==KeyEvent.VK_G)
      {
         if(shiftPressed && green > 1)
            green--;
         else if(green < 254)
            green++;
         currentColor = new Color(red, green, blue);  
      }
      else if(k==KeyEvent.VK_R)
      {
         if(shiftPressed && red > 1)
            red--;
         else if(red < 254)
            red++;
         currentColor = new Color(red, green, blue);  
      }
      else if(k==KeyEvent.VK_I)                      //Invert colors
      {
         backgroundColor = Color.black;
         SandUtilities.invertColors(snow, backgroundColor, Color.white);
         currentColor = SandUtilities.invert(currentColor);
         red = currentColor.getRed();
         green = currentColor.getGreen();
         blue = currentColor.getBlue();
      }
      else if(k==KeyEvent.VK_Z)							   //change background to a random color
         backgroundColor = makeRandomColor();
      else if(k==KeyEvent.VK_X)
         backgroundColor = Color.black;
      
      else if(k==KeyEvent.VK_V)
         clearPlatforms();
      
      else if(k==KeyEvent.VK_W)						      //change value for weight of snow
         weightIndex = (weightIndex+1)%weight.length;
      
      else if(k==KeyEvent.VK_UP)						      //change value for wind change
         windIndex = (windIndex+1)%winds.length;
      else if(k==KeyEvent.VK_DOWN)						   //eliminate winds
      {
         freqIndex = 0;
         windIndex = 0;
         windPower = 0;
      }
      else if(k==KeyEvent.VK_LEFT && windPower > 0)
         windPower--;
      else if(k==KeyEvent.VK_RIGHT && windPower < 9)
         windPower++;
      
      else if((k == KeyEvent.VK_PLUS || k == KeyEvent.VK_ADD  || k == KeyEvent.VK_EQUALS) && brushSize <= 19)                                               
         brushSize += 1;                              //change size of the brush
      else if((k == KeyEvent.VK_MINUS || k == KeyEvent.VK_SUBTRACT  || k == KeyEvent.VK_UNDERSCORE) && brushSize >= 4)
         brushSize -= 1;
      else if(k==KeyEvent.VK_INSERT)                  //change brush type
         cursorMode = (byte)((cursorMode+1)%NUM_MODES);
      
      else if(k==KeyEvent.VK_F)							   //change value for forcast (steady, variable, random)
         freqIndex = (freqIndex+1)%forcast.length;
      else if(k==KeyEvent.VK_SPACE)							//toggle rain on and off
         rain = !rain;
      else if(k == KeyEvent.VK_0)
         rainFreq = 0; 
      else if(k == KeyEvent.VK_1)							//change frequency of rain
      {
         rain = true;
         rainFreq = 1;
      }
      else if(k == KeyEvent.VK_2)
      {
         rain = true;
         rainFreq = 2;
      }
      else if(k == KeyEvent.VK_3)
      {
         rain = true;
         rainFreq = 3;
      }
      else if(k == KeyEvent.VK_4)
      {
         rain = true;
         rainFreq = 4;
      }
      else if(k == KeyEvent.VK_5)
      {
         rain = true;
         rainFreq = 5;
      }
      else if(k == KeyEvent.VK_6)
      {
         rain = true;
         rainFreq = 6;
      }
      else if(k == KeyEvent.VK_7)
      {
         rain = true;
         rainFreq = 7;
      }
      else if(k == KeyEvent.VK_8)
      {
         rain = true;
         rainFreq = 8;
      }
      else if(k == KeyEvent.VK_9)
      {
         rain = true;
         rainFreq = 9;
      }
      if(k==KeyEvent.VK_COMMA && delay > 0)			//speed up
      {
         delay--;
         if(delay < 0)
            delay = 0;
         t.setDelay(delay);   
         return;
      }
      if(k==KeyEvent.VK_PERIOD && delay < 99)	   //slow down		
      {
         delay++;
         t.setDelay(delay); 
         return;
      }
   
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      g.setColor(backgroundColor);		//draw the background
      g.fillRect(0, 0, screenWidth, screenHeight);
      showBoard(g);						//draw the snow particles on the screen
   }

 //***BEGIN MOUSE STUFF***
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration
      {
         numFrames++;
         if(numFrames == Integer.MAX_VALUE)			//roll over frame count and reset shot times if we get to max int value
            numFrames = 0;
         if(rain)
         {
            for(int i=0; i<rainFreq; i++)          //make snow fall from the top or from faucet
            {
               int row = 0;
               int col = (int)(Math.random()*snow[0].length);
               if(cursorMode==FAUCET)
               {
                  col = (int)(Math.random()*((mouseX-brushSize)-(mouseX+brushSize)+1)) + (mouseX-brushSize) + brushSize*2;
                  row = mouseY-brushSize;
               }
               if(isValid(row, col))
                  snow[row][col] = varyColor(currentColor);
            }
            if(cursorMode!=FAUCET && windPower > 0)      //east winds - add snow from the left side
            {
               for(int i=0; i<windPower/2; i++)          //make snow fall from the left
               {
                  int randY = (int)(Math.random()*(snow.length-2))+1;
                  snow[randY][0] = varyColor(currentColor);
               }
            }
         }
         if(changeIndex > 0 && rain)
         {
            if(Math.random() < 0.005)                       //subtle color change
            {
               currentColor = varyColor(currentColor);
               red = currentColor.getRed();
               green = currentColor.getGreen();
               blue = currentColor.getBlue();
            }
            if(changeIndex == 2 && Math.random() < 0.002)  //random color change is less frequent
            {
               currentColor = randomColor();
               red = currentColor.getRed();
               green = currentColor.getGreen();
               blue = currentColor.getBlue();
            }
         }
         if(freqIndex > 0)                                 //forcast might change
         {
            if(Math.random() < 0.002)	
            {	  
               if(forcast[freqIndex].contains("RANDOM"))
                  rainFreq = (int)(Math.random()*9) + 1;
               else
               {
                  rainFreq += (int)(Math.random()*3)-1;
                  if(rainFreq < 1)
                     rainFreq = 1;
                  else if(rainFreq > 9)
                     rainFreq = 9;
               }
            }
         }
         if(!winds[windIndex].contains("STEADY"))
         {
            if(winds[windIndex].contains("VARIABLE"))
            {
               if(numFrames % 500 == 0 && Math.random() < .5)		//allow possibility for wind to change
                  windPower += (int)(Math.random()*4) - 2;
            }
            else //if(winds[windIndex].contains("RANDOM"))
            {
               if(numFrames % 500 == 0 && Math.random() < .5)	
                  windPower = (int)(Math.random()*10);
            }
            if(windPower < 0)
               windPower = 0;
            else if(windPower > 9)
               windPower = 9;
         }
         moveSnow();
      
         repaint();
      }
   }

   public void mouseClicked( MouseEvent e )
   {
      int button = e.getButton();
   
      if(button == MouseEvent.BUTTON3)       //toggle between paint/erase/faucet tool
         cursorMode = (byte)((cursorMode+1)%NUM_MODES);
      if( cursorMode==FAUCET && button == MouseEvent.BUTTON1)  
      {
         rain = !rain;
      }
      repaint();
   }

   public void mousePressed( MouseEvent e )
   {
   }

   public void mouseReleased( MouseEvent e )
   {}

   public void mouseEntered( MouseEvent e )
   {}

   public void mouseMoved( MouseEvent e)
   {
      mouseX = e.getX();               //update mouse position do draw mouse icon on the screen
      mouseY = e.getY();
   
      repaint();		               	//refresh the screen
   }

   public void mouseDragged( MouseEvent e)
   {
      mouseX = e.getX();
      mouseY = e.getY();
      if(cursorMode==DRAW && isValid(mouseY, mouseX))        //paint tool to make platforms
      {
         for(int i = mouseY - brushSize; i < mouseY + brushSize; i++)
            for(int j = mouseX - brushSize; j < mouseX + brushSize; j++)
               if(isValid(i, j))
                  snow[i][j] =  Color.white;
      }
      else if(cursorMode==ERASE)                               //erase tool to remove platforms
      {
         for(int i = mouseY - brushSize; i < mouseY + brushSize; i++)
            for(int j = mouseX - brushSize; j < mouseX + brushSize; j++)
               if(isValid(i, j) && snow[i][j]!=null)
                  snow[i][j] = null;
      }
   }

   public void mouseExited( MouseEvent e )
   {}

   public void mouseWheelMoved(MouseWheelEvent e) 
   {
      int WHEEL_SENSITIVITY = 1;
      int notches = e.getWheelRotation();
      if (notches >= WHEEL_SENSITIVITY && brushSize >= 4)
         brushSize -= 1;                           //change brush size of paint/erase/faucet tool
      else if (notches <= -WHEEL_SENSITIVITY && brushSize <= 19)
         brushSize += 1; 
   }
}



