import java.awt.Point;
import java.util.ArrayList;

public class Location implements Comparable
{
   private String name;
   private int fromMapIndex, toMapIndex;
   private int row, col;
   private Terrain terrain;
   private Teleporter teleporter;
   private ArrayList<Point> monsterFreq;  //x is the monster index, y is the number of monsters of that type
   private ArrayList<Point> riddlePoints; //x is the row, y is the column of critical locations for riddles (like 3-doors and 3-wells riddles)

   public Location(String n, int r, int c, int mi, Terrain t, Teleporter tele)
   {
      name = n;
      fromMapIndex = mi;
      toMapIndex = -1;
      row = r;
      col = c;
      terrain = t;
      teleporter = tele;
      monsterFreq = new ArrayList<Point>();
      riddlePoints = new ArrayList<Point>();
   }
   
   public int compareTo(Object other)
   {
      Location that = (Location)(other);
      int dist = (int)(Display.wrapDistance(this.row, this.col, that.row, that.col));
      if(this.row == that.row && this.col == that.col)
         return 0;
      if(this.row < that.row)
         return -1*dist;
      if(this.col < that.col)
         return -1*dist;
      return dist;
   }
   
   public ArrayList<Point> getMonsterFreq()
   {
      return monsterFreq;
   }
   
   public void setMonsterFreq(ArrayList<Point> mf)
   {
      monsterFreq = mf;
   }
   
   public ArrayList<Point> getRiddlePoints()
   {
      return riddlePoints;
   }
   
   public void setRiddlePoints(ArrayList<Point> rp)
   {
      riddlePoints = rp;
   }
   
   public void clearRiddlePoints()
   {
      riddlePoints.clear();
   }
   
   //returns the total number of monsters
   public int getMonsterTotal()
   {
      int sum=0;
      for(Point p:monsterFreq)
         sum += (int)(p.getY());
      return sum;
   }
   
   //return the probability that the monster in monsterFreq with charIndex mi will spawn out of the total # monsters
   public int getMonsterProb(int mi)
   {
      int total = getMonsterTotal();
      if(total > 0)
         for(Point p:monsterFreq)
            if(p.getX()==mi)
               return (int)((p.getY() / total)*100);
      return 0;
   }
   
   public void addMonster(byte mi)
   {
      for(Point p:monsterFreq)
      {
         if(p.getX()==mi)
            p.setLocation(mi, p.getY()+1);
         return;
      }
      monsterFreq.add(new Point(mi, 1));
   }
   
   public boolean removeMonster(byte mi)
   {
      for(int i=0; i<monsterFreq.size(); i++)
      {
         Point p = monsterFreq.get(i);
         if(p.getX()==mi || (NPC.isBrigand((byte)(p.getX())) && NPC.isBrigand(mi)))
         {
            if(p.getY() == 1)
            {
               monsterFreq.remove(i);
               return true;
            }
            p.setLocation(mi, p.getY()-1);
            return true;
         }
      }
      return false;
   }
   
   public void clearMonsters()
   {
      monsterFreq.clear();
   }
   
   public String getName()
   {
      return name;
   }
      
   public int getMapIndex()
   {
      return toMapIndex;
   }
   
   public void setMapIndex(int mi)
   {
      toMapIndex = mi;
   }
   
   public int getFromMapIndex()
   {
      return fromMapIndex;
   }
   
   public void setFromMapIndex(int mi)
   {
      fromMapIndex = mi;
   }

   public int getToMapIndex()
   {
      return toMapIndex;
   }
      
   public int getRow()
   {
      return row;
   }
   
   public int getCol()
   {
      return col;
   }
   
   public Terrain getTerrain()
   {
      return terrain;
   }
   
   public Teleporter getTeleporter()
   {
      return teleporter;
   }

   public void setTeleporter(Teleporter tele)
   {
      teleporter = tele;
   }

   public String toString()
   {
      return name+":("+row+","+col+")";
   }
   
   public String toString2()
   {
      return name+" at "+col+" East and "+row+" South";
   }

}