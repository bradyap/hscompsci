//Rev. Dr. Douglas R Oberle, March 2013
   import javax.swing.*;
   import java.awt.event.*;
   public class PixokuDriver						//Driver Program
   {
      public static Pixoku screen;				//Game window
   
      public static void main(String[]args)
      {
         screen = new Pixoku();
         JFrame frame = new JFrame("Pixoku");//window title
         frame.setSize(1000, 1000);				//Size of game window
         frame.setLocation(10, 10);				//location of game window on the screen
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(screen);		
         frame.setVisible(true);
         frame.addKeyListener(new listen());	//Get input from the keyboard
      }
   
      public static class listen implements KeyListener 
      {
         public void keyTyped(KeyEvent e)
         {}
      
         public void keyPressed(KeyEvent e)
         {}
      
         public void keyReleased(KeyEvent e)
         {
            int key=e.getKeyCode();
            screen.userCommand(key);
         }
      }
   
   }
