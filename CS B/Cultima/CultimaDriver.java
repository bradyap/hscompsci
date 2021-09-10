import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class CultimaDriver						         //Driver Program
{
   public static CultimaPanel screen;				   //Game window
   public static JFrame frame;
   public static int width, height;                //resizable dimensions

   public static void main(String[]args)
   {
      screen = new CultimaPanel();
      frame = new JFrame("");	                     //window title
      width = 50*screen.SIZE+30;
      height = 26*screen.SIZE+30;
      frame.setSize(width, height);					   //Size of game window
      frame.setLocation(0, 0);				         //location of game window on the screen
      frame.setExtendedState(JFrame.NORMAL);  	   //MAXIMIZED_BOTH, MAXIMIZED_VERT, or ICONIFIED
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(screen);		
      frame.setVisible(true);
      frame.addKeyListener(new listen());		      //Get input from the keyboard
   }

   public static class listen implements KeyListener 
   {
      public void keyTyped (KeyEvent e)
      { }
   
      public void keyPressed(KeyEvent e)
      { 
         if(e.getKeyCode()==KeyEvent.VK_SHIFT)
            screen.shiftPressed=true;
      }
   
      public void keyReleased(KeyEvent e)
      {
         int k = e.getKeyCode();
         if(k==KeyEvent.VK_PLUS || k==KeyEvent.VK_EQUALS || k==KeyEvent.VK_ADD)		//larger screen size
         {
            screen.SIZE += 2;
            width = 50*screen.SIZE+30;
            height = 26*screen.SIZE+30;
            frame.setSize(width, height);				
            frame.setExtendedState(JFrame.NORMAL); 
            frame.repaint();
         }
         else if(k==KeyEvent.VK_MINUS || k==KeyEvent.VK_SUBTRACT)							//smaller screen size
         {
            screen.SIZE -= 2;
            width = 50*screen.SIZE+30;
            height = 26*screen.SIZE+30;
            frame.setSize(width, height);				
            frame.setExtendedState(JFrame.NORMAL); 
            frame.repaint();
         }
         else if(k==KeyEvent.VK_SHIFT)
            screen.shiftPressed=false;
         else
            screen.processUserInput(k);
      }
   }
}
