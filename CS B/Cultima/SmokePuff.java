import java.awt.Color;
public class SmokePuff
{
   private int x, y, radius, opacity;
   private Color color;

   public static final Color dustCloud = new Color(200,200,200);
   public static final Color waterColor = new Color(200,200,255);
   public static final Color bloodCloud = new Color(200,50,20);

   public SmokePuff(int dX, int dY, int r)
   {
      x = dX;
      y = dY;
      radius = r;
      opacity = 255;
      color = Color.black;
   }
   
   public SmokePuff(int dX, int dY, int r, int op)
   {
      x = dX;
      y = dY;
      radius = r;
      opacity = op;
      color = Color.black;
   }
   
   public SmokePuff(int dX, int dY, int r, int op, Color c)
   {
      x = dX;
      y = dY;
      radius = r;
      opacity = op;
      color = c;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public int getRadius()
   {
      return radius;
   }
   
   public int getOpacity()
   {
      return opacity;
   }
      
   public Color getColor()
   {
      int r = color.getRed();
      int g = color.getGreen();
      int b = color.getBlue();
      return new Color(r, g, b, opacity);
   }
   
   public void setX(int dx)
   {
      x = dx;
   }
   
   public void setY(int dy)
   {
      y = dy;
   }
   
   public void setOpacity(int op)
   {
      opacity = op;
   }
   
   public boolean shouldBeRemoved()
   {
      return (x < 0 || y < 0 || x>=CultimaPanel.SCREEN_SIZE * CultimaPanel.SIZE 
           || y>=CultimaPanel.SCREEN_SIZE * CultimaPanel.SIZE || opacity==0 || radius==0);
   }
   
   public void advance()
   {
      Spell timestop = CultimaPanel.player.getActiveSpell("Timestop");
      if(timestop != null)
         return;
      if(opacity > 0)
         opacity--;
      if(color.equals(waterColor))    //smoke
      {   
         if(radius < CultimaPanel.SIZE * 4)
            radius++;   
         if(radius %2 == 0)
         {
            x -= 1;
            y -= 1;
         }
      }
      else if(color.equals(Color.black) || color.equals(dustCloud))    //smoke
      {   
         if(radius < CultimaPanel.SIZE * 4)
            radius++;   
         y -= Player.rollDie(0,3);
         if(y < 0)
            y = 0;
         if(CultimaPanel.windDir == LocationBuilder.EAST)
            x += Player.rollDie(0,3);
         else if(CultimaPanel.windDir == LocationBuilder.WEST)
            x -= Player.rollDie(0,3);
         else
            x += Player.rollDie(-2,2);
         if(x < 0)
            x = 0;
         if(x > CultimaPanel.SCREEN_SIZE * CultimaPanel.SIZE)
            x = CultimaPanel.SCREEN_SIZE * CultimaPanel.SIZE;
      }
      else                             //flash
      {
         if(radius > 0)
         {
            radius--;
            if(radius %2 == 0)
            {
               x += 1;
               y += 1;
            }
         }
      }
   }
}