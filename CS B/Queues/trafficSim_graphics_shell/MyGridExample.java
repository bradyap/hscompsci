   package trafficSim_graphics_shell;
   
   import javax.swing.JPanel;
   import javax.swing.ImageIcon;
   import javax.swing.Timer;
   import java.awt.Graphics;
   import java.awt.Color;
   import java.awt.event.KeyEvent;
   import java.awt.event.ActionListener;
   import java.awt.event.ActionEvent;

   public class MyGridExample extends JPanel
   {
        
      private static final int SIZE=40;	//size of cell being drawn
    
      private static final int DELAY=100;	//#miliseconds delay between each time the enemy moves and screen refreshes for the timer
   
      private Timer t;							//used to set the speed of the enemy that moves around the screen
   
   //define 2 queues for lanes of traffic (main & maple)
   //define values for delay and prob for each lane (mainDelay, mainProb, mapleDelay, mapleProb)
   //private boolean mainGreen (true: green on main & red on maple, false: red on main & green on maple)
   //private int frameNum;	count of frames to keep track of time
   //define number of cycles
   //define counter for number of cycles completed (numCycles)
      public MyGridExample(/*add arguments for delay and prob for each lane and number of cycles*/)
      {
      //create 2 queues for lanes of traffic
      //assign values for delay and prob for each lane  and number of cycles
      //mainGreen = true;
      //frameNum = 0;
      //numCycles = 0;
         t = new Timer(DELAY, new Listener());				//the higher the value of the first argument, the slower the enemy will move
         t.start();
      
      }
   
   
   	//post:  shows different pictures on the screen in grid format depending on the values stored in the array board
   	//			0-blank, 1-white, 2-black and gives priority to drawing the player
      public void showBoard(Graphics g)	
      {
         int x =SIZE, y=SIZE;		//upper left corner location of where image will be drawn
         //draw maple from origin to the right x+=SIZE;
      	//droaw main from origin down y+=SIZE;
      }
   
      //THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
   	//pre:   k is a valid keyCode
   	//post:  changes the players position depending on the key that was pressed (sent from the driver)
   	//			keeps the player in the bounds of the size of the array board, then the enemy moves
      public void processUserInput(int k)
      {
         if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program	
            System.exit(1);
         repaint();			//refresh the screen
      }
   
      public void paintComponent(Graphics g)
      {
         super.paintComponent(g); 
         g.setColor(Color.blue);		//draw a blue boarder around the board
         g.fillRect(0, 0, 800, 800);
         showBoard(g);					//draw the contents of the array board on the screen
      }
      
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)	//this is called for each timer iteration - traffic mechanics
         {
           /*
         if(numCycles completed >= cycles)
         {
         show stats (results);
         System.exit(1);
         }
         if(mainGreen)
         {
         if(frameNum >= mainDelay)
         {
            frameNum = 0;
         	mainGreen = false;
         }
         else
         {
         maybe add a car to main and/or maple
         if main has cars
             remove a car from main
         frameNum++;	 
         }
         }
         else	//main is red and maple is green
         {
         if(frameNum >= mapleDelay)
         {
            frameNum = 0;
         	mainGreen = true;
         }
         else
         {
         maybe add a car to main and/or maple
         if main has cars
             remove a car from maple
          frameNum++;	 	 
         }
         }
         */
            repaint();
         }
      }
   
   }
