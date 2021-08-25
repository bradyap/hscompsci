import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public class SandArtDriver						//Driver Program
{
   public static SandArtPanel screen;		//Game window


   public static void main(String[]args)
   {
      screen = new SandArtPanel();
      JFrame frame = new JFrame("DUE TO INCLEMENT WEATHER, ALL FAIRFAX COUNTY PUBLIC SCHOOLS WILL BE CLOSED TODAY");	//window title
      frame.setSize(700, 700);				//Size of game window
      frame.setLocation(50, 50);				//location of game window on the screen
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(screen);		
      frame.setVisible(true);
      frame.addKeyListener(new listen());	//Get input from the keyboard
   	//make the regular mouse cursor transparent so we can use a custom one in the MMMPanel that changes depending on direction
      BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);  
      Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "MMM cursor");  
      frame.getContentPane().setCursor(blankCursor);
   
   }
   
   private static boolean shiftIsPressed=false; 
   
   public static class listen implements KeyListener 
   {
   
      public void keyTyped(KeyEvent e)
      {
      
      }
      
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_SHIFT)
            shiftIsPressed=true;
         screen.processUserInput(e.getKeyCode(), shiftIsPressed);
      }
   
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_SHIFT)
            shiftIsPressed=false;
      }
   }

}
