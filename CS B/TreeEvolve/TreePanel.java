//Rev. Dr. Douglas R Oberle - Nicholas Hodgman 2015
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

/**************************************************
Given the Tree class, use an array of Trees (forest) to evolve them into a best fit

Tree Object:

Tree makeRandomTree() 				returns a random fractal
Tree mutate(int mutationRate)		given the current tree, return a mutation of the tree where each field can vary by mutationRate
int compareTo(Object other)		Trees compare to one another by how fit they are (one closer to the best fit is > one that is further away)

**************************************************/

public class TreePanel extends JPanel
{
   private static Tree [] forest;             //sample set of Trees (most fit is at forest[0], sorted by best fit)
   private static int SAMPLE_SIZE = 10;       //number of trees in population to mutate (2-25) 
   private static int MUTATION_RATE = 5;      //percentage a tree can mutate per field from one generation to the next (1-100)
   private static int numGenerations;         //count the number of generations
   private Timer t;							       //used to set the speed of generation refresh
   private static boolean evolve;             //have we started auto evolution?

   public TreePanel()
   {
      numGenerations = 0;
      evolve = false;
      forest = new Tree[SAMPLE_SIZE];
      forest[0] = forest[0].makeRandomTree();
      for(int i=1; i<forest.length; i++)
         forest[i] = forest[0];
      setFocusable(true);
      addKeyListener(new Keyboard());
      addMouseListener(new Mouse());
   
      t = new Timer(0, new Listener());	   //0 delay between frames
      t.start(); 
   }

//post: finds the most fit and repopulates forest with mutations of the most fit 
   private static void advanceGeneration()
   {
      numGenerations++;
   //***COMPLETE THE CODE HERE*****************************************
      Tree mostFit = forest[0];
      for(int i=1; i<forest.length; i++)
      {
         if(forest[i].compareTo(mostFit) > 0)
            mostFit = forest[i];
      }
      for(int i=0; i<forest.length; i++)
      {
         forest[i] = mostFit.mutate(MUTATION_RATE);
      }
   //******************************************************************/
   }

   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration
      {
         if(evolve && clipNum(forest[0].howFit()) < 0.99)
         {
            advanceGeneration();
            repaint();
         }
      }
   }

//post:  returns x clipped to two decimal places, used for Panel display
   private double clipNum(double x)
   {
      return ((int)(x*100))/100.0;
   }

/**
* Draws first tree in forest array to a Graphics object, as well as key commands
*
* @param g The awt Graphics object to draw itself on to
*/
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
   
      ArrayList<TreePart> points = forest[0].getPoints();
      for(int i = 0; i < points.size(); i++)
         points.get(i).paint(g);
   //draw the controls and stats
      Color previous=g.getColor();
      g.setColor(Color.white);
      g.fillRect(0,0,1500,50);
      g.setColor(Color.black);
      int red = forest[0].getLeafColor().getRed();
      int green = forest[0].getLeafColor().getGreen();
      int blue = forest[0].getLeafColor().getBlue();
      g.drawString("SampleSize ("+SAMPLE_SIZE+"):1+2     MutationRate("+MUTATION_RATE+"):3+4     Leaf RGB("+red+","+green+","+blue+"):5+6,7+8,9+0                                                (Width):"+forest[0].getPixelWidth()+"        (Height):"+forest[0].getPixelHeight() + "     (Number Generations):"+numGenerations + "     (How fit):"+clipNum(forest[0].howFit()),0,20);   
      g.drawString("TrunkLength("+clipNum(forest[0].getLength())+"):Q+W     LengthChange("+clipNum(forest[0].getLengthChange())+"):E+R      TrunkWidth("+clipNum(forest[0].getWidth())+"):A+S     WidthChange("+clipNum(forest[0].getWidthChange())+"):D+F     Degrees("+clipNum(forest[0].getDegreesChange())+"): Z+X     NumBranches("+forest[0].getNumBranches()+"): C+V     Smaller Increments:SHIFT     Move:Arrow Keys     BackColor:B+N",0,40);  
      g.setColor(previous);
   }

   public class Mouse extends MouseAdapter
   {
   
      public void mouseClicked(MouseEvent e)
      {
         if(e.getButton()==MouseEvent.BUTTON1)           //create a new random fractal
         {
            forest[0] = forest[0].makeRandomTree();
            for(int i=1; i<forest.length; i++)
               forest[i] = forest[0];
            numGenerations = 0;
            evolve = false;
            repaint();
         }
         else if(e.getButton()==MouseEvent.BUTTON3)      //start evolution
         {
            evolve = !evolve;
            repaint();
         }
      
      }
   }


   private boolean shiftIsPressed=false;  //if shift is held it moves in finer increments

   public class Keyboard implements KeyListener
   {
   /**
   * Handles when a key is pressed
   *
   * @param e Standard KeyEvent
   */
      public void keyPressed(KeyEvent e)
      {
         requestFocusInWindow();
         double change=0;
         evolve = false;
      
         if(e.getKeyCode()==KeyEvent.VK_ESCAPE) //end the program
            System.exit(1);
      
         if(e.getKeyCode()==KeyEvent.VK_SPACE)
         {
            advanceGeneration();
            repaint();
            return;
         }
         if(e.getKeyCode()==KeyEvent.VK_ENTER)  //toggle auto-evolution on
         {  
            evolve = true;
            return;
         }
         if(e.getKeyCode()==KeyEvent.VK_1)      //Sample Size (2-25)
         {
            if(SAMPLE_SIZE <= 2)
               return;
            SAMPLE_SIZE--;    
         //***COMPLETE THE CODE HERE*****************************************
            forest = new Tree[SAMPLE_SIZE];
            forest[0] = forest[0].makeRandomTree();
            for(int i=1; i<forest.length; i++)
               forest[i] = forest[0];
         //******************************************************************/
            repaint();
            return;
         }
         if(e.getKeyCode()==KeyEvent.VK_2)      //Sample Size (2-25)
         {
            if(SAMPLE_SIZE >= 25)
               return;
            SAMPLE_SIZE++;
         //***COMPLETE THE CODE HERE*****************************************
            forest = new Tree[SAMPLE_SIZE];
            forest[0] = forest[0].makeRandomTree();
            for(int i=1; i<forest.length; i++)
               forest[i] = forest[0];
         //******************************************************************/
            repaint();
            return;
         }
         if(e.getKeyCode()==KeyEvent.VK_3)      //Mutation Rate (1-100)
         {
            MUTATION_RATE--;
            if(MUTATION_RATE < 1)
               MUTATION_RATE = 1;   
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_4)      //Mutation Rate (1-100)
         {
            MUTATION_RATE++;
            if(MUTATION_RATE > 100)
               MUTATION_RATE = 100;   
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_5)      //subtract RED (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(red > 0)
               red--;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_6)      //add RED (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(red < 255)
               red++;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_7)      //subtract GREEN (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(green > 0)
               green--;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_8)      //add GREEN (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(green < 255)
               green++;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_9)      //subtract BLUE (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(blue > 0)
               blue--;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
         if(e.getKeyCode()==KeyEvent.VK_0)      //add BLUE (0-255)
         {
            int red = forest[0].getLeafColor().getRed();
            int green = forest[0].getLeafColor().getGreen();
            int blue = forest[0].getLeafColor().getBlue();
            if(blue < 255)
               blue++;
            forest[0].setLeafColor(new Color(red, green, blue));
            forest[0].generateData();
            repaint();
            return;   
         }
      
         if(e.getKeyCode()==KeyEvent.VK_SHIFT)
            shiftIsPressed=true;
      
         if(shiftIsPressed)
            change=.1;
         else
            change=5;
         if(e.getKeyCode()==KeyEvent.VK_Z)      //degreesChange
            forest[0].setDegreesChange(forest[0].getDegreesChange() + change);
         else if(e.getKeyCode()==KeyEvent.VK_X)
            forest[0].setDegreesChange(forest[0].getDegreesChange() - change);
      
         if(shiftIsPressed)
            change=forest[0].getNumBranches()/4;
         else
            change=forest[0].getNumBranches()/2;
         if(change <= 0)
            change = 1;   
         if(e.getKeyCode()==KeyEvent.VK_C)      //numBranches
            forest[0].setNumBranches(forest[0].getNumBranches() + (int)(change));
         else if(e.getKeyCode()==KeyEvent.VK_V)
            forest[0].setNumBranches(forest[0].getNumBranches() - (int)(change));
      
         if(shiftIsPressed)
            change=.01;
         else
            change=.025;
         if(e.getKeyCode()==KeyEvent.VK_E)      //lengthChange
            forest[0].setLengthChange(forest[0].getLengthChange() + change);
         else if(e.getKeyCode()==KeyEvent.VK_R)
            forest[0].setLengthChange(forest[0].getLengthChange() - change);
      
         if(shiftIsPressed)
            change=1.05;
         else
            change=1.1;
         if(e.getKeyCode()==KeyEvent.VK_Q)      //trunk length
            forest[0].setLength(forest[0].getLength()*change);
         else if(e.getKeyCode()==KeyEvent.VK_W)
            forest[0].setLength(forest[0].getLength()/change);
      
         if(shiftIsPressed)
            change=.01;
         else
            change=.025;
         if(e.getKeyCode()==KeyEvent.VK_D)      //withChange
            forest[0].setWidthChange(forest[0].getWidthChange() + change);
         else if(e.getKeyCode()==KeyEvent.VK_F)
            forest[0].setWidthChange(forest[0].getWidthChange() - change);
      
         if(shiftIsPressed)
            change=1.1; 
         else
            change=1.5;
         if(e.getKeyCode()==KeyEvent.VK_A)      //trunk width
            forest[0].setWidth(forest[0].getWidth()*change);
         else if(e.getKeyCode()==KeyEvent.VK_S)
            forest[0].setWidth(forest[0].getWidth()/change);
         
         else if(e.getKeyCode()==KeyEvent.VK_UP) //move
            forest[0].setInitialY(forest[0].getInitialY()-10);
         else if(e.getKeyCode()==KeyEvent.VK_DOWN)
            forest[0].setInitialY(forest[0].getInitialY()+10);
         else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            forest[0].setInitialX(forest[0].getInitialX()+10);
         else if(e.getKeyCode()==KeyEvent.VK_LEFT)
            forest[0].setInitialX(forest[0].getInitialX()-10);
          
         else if(e.getKeyCode()==KeyEvent.VK_N) //random background color
         { 
            Color randomColor=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
            setBackground(randomColor);
         }
         else if(e.getKeyCode()==KeyEvent.VK_B) //sets background color to light gray
            setBackground(Color.gray.brighter());
      
         forest[0].generateData();
         repaint();
      }
   
      public void keyReleased(KeyEvent e) 
      {
         requestFocusInWindow();
         if(e.getKeyCode()==KeyEvent.VK_SHIFT){
            shiftIsPressed=false;
         }
      }
   
      public void keyTyped(KeyEvent e){}  //not used
   
   
   
   }


}