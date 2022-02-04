//Rev. Dr. Douglas R Oberle - Nicholas Hodgman 2015
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TreePart
{
   private int x;  						//its x coordinate in space
   private int y; 						//its y coordinate in space
   private Color color; 				//its color
   private double degrees; 			//the direction it is from its parent
   private double distance; 			//how far it is from its parent
   private double distanceChange; 	//how much less far it traveled than its parent as a percent
   private double degreeChange; 		//how many degrees it changed from its parent
   private int generation;  			//what generation the number is
   private TreePart parent; 			//its parent TreePart
   private boolean leaf=true; 		//true if it is a leaf
   private Color leafColor;          
   private double width;            //width of the branch
   private double widthChange;      //what percent does the width change from its parent

   /**
   *  Standard TreePart constructor
   *
   *  @param newX The x coordinate of the point.
   *  @param newY The y coordinate of the point.
   *  @param newColor   The color of the point.
   *  @param deg  The direction of the point in degrees.
   *  @param degChange  The amount the point is changing.
   *  @param dist  The distance the point is traveling
   *  @param distChange  The percent the point traveled compared to its parent
   *  @param w The width of the parallelogram the point will draw
   *  @param wChange The percent the width is compared to its parent
   *  @param gen The generation of the point
   *  @param lColor The leaf color
*/
   public TreePart(int newX, int newY, Color newColor, double deg, double degChange, double dist, double distChange, double w, double wChange, int gen, Color lColor)
   {
      x=newX;
      y=newY;
      color=newColor;
      degrees=deg;
      degreeChange=degChange;
      distance=dist;
      distanceChange=distChange;
      width=w;
      widthChange=wChange;
      generation=gen;
      leafColor = lColor;
      parent=null;
   }

/**
   * TreePart constructor with a parent
   *
   *  @param newX The x coordinate of the point.
   *  @param newY The y coordinate of the point.
   *  @param newColor   The color of the point.
   *  @param deg  The direction of the point in degrees.
   *  @param degChange  The amount the point is changing.
   *  @param dist  The distance the point is traveling
   *  @param distChange  The percent the point traveled compared to its parent
   *  @param w The width of the parallelogram the point will draw
   *  @param wChange The percent the width is compared to its parent
   *  @param gen The generation of the point
   *  @param lColor The leaf color
   *  @param  newParent The parent of the point
   *  
   *  @see #TreePart(int, int, Color, double, double, double, double, double, double, int)
*/
   public TreePart(int newX, int newY, Color newColor, double deg, double degChange, double dist, double distChange, double w, double wChange, int gen, Color lColor, TreePart newParent)
   {
      this(newX, newY, newColor, deg, degChange, dist, distChange, w, wChange, gen, lColor);
      parent=newParent;
   }

   public int getX()       
   {
      return x;
   }
		
   public int getY()      
   {
      return y;
   }
		
   public Color getColor() 
   {
      return color;
   }
		
   public void setColor(Color a) 
   {
      color=a;
   }
   
   public double getDegrees() 
   {
      return degrees;
   }
		
   public double getDistance()  
   {
      return distance;
   }
		
   public double getDistanceChange()  
   {
      return distanceChange;
   }
		
   public int getGeneration()  
   {
      return generation;
   }
		
   public boolean isLeaf() 
   {
      return leaf;
   }
   
   private double getWidth()  
   {
      return width;
   }
		
   private double getWidthChange()
   {
      return widthChange;
   }

/**
   * Create an array that holds two points representing its children
   * 
   * @return Returns an array of two generated TreePart children
*/
   public TreePart[] generateChildren()
   {
      leaf = false;
      TreePart[] children   = new TreePart[2]; 
      double newDistance = distance*distanceChange;
      double rightRadians= Math.toRadians(degrees+degreeChange);
      double leftRadians = Math.toRadians(degrees-degreeChange);
      int rightXChange   = (int)(newDistance*Math.cos(rightRadians));
      int rightYChange   = (int)(newDistance*Math.sin(rightRadians));
      int leftXChange    = (int)(newDistance*Math.cos(leftRadians));
      int leftYChange    = (int)(newDistance*Math.sin(leftRadians));
      double newWidth    = width*widthChange;
   
      TreePart rightChild = new TreePart(x+rightXChange, y+rightYChange, color, degrees+degreeChange, degreeChange, newDistance, distanceChange, newWidth, widthChange, generation+1, leafColor, this);
      TreePart leftChild  = new TreePart(x+leftXChange,  y+leftYChange,  color, degrees-degreeChange, degreeChange, newDistance, distanceChange, newWidth, widthChange, generation+1, leafColor, this);
      children[0] = rightChild;
      children[1] = leftChild;
      return children;  
   }

//returns a variation of the leafColor
   private Color varyLeafColor()
   {
      if(Math.random() < .5)
         return this.leafColor.darker();
      return this.leafColor;
   }

/**
   * Paints itself as a parallelogram to its parent with its width
   *
   * @param g Standard awt Graphics object to paint itself on to 
*/
   public void paint(Graphics g)
   {
      if(parent==null)
         return;
      if(isLeaf())         //draw a leaf at the end of a branch
      {
         g.setColor(varyLeafColor());
         g.fillOval(x, y, 10, 15);
         g.setColor(g.getColor().darker().darker());
         g.drawOval(x, y, 10, 15);
      }   
      Color previousColor=g.getColor();
      g.setColor(color);
   
      if(width>2)
      {
      //points for this
         int x1=x-(int)(width/2);
         int x2=x+(int)(width/2);
         int y1=y;
         int y2=y;
      //points for parent
         int x3=parent.getX()-(int)(width/2);
         int x4=parent.getX()+(int)(width/2);
         int y3=parent.getY();
         int y4=parent.getY();
      
         int[] xCoords={x1,x2,x4,x3}; //1243 because thats order of circumference counterclockwise
         int[] yCoords={y1,y2,y4,y3};
         g.fillPolygon(xCoords, yCoords, 4);
      }
      else
      {
         g.drawLine(x,y,parent.getX(),parent.getY());
      }
      g.setColor(previousColor);
   }
}