import javax.swing.ImageIcon;
import java.util.ArrayList;

//********CORPSE OBJECT IS DROPPED IN THE WORLD WHEN AN NPC/MONSTER IS KILLED

public class Corpse
{
   private ImageIcon picture;       //picture used for the corpse   
   private byte charIndex;          //the NPC type it was while alive
   private int row, col, mapIndex;  //location of the corpse
   private int gold;                //gold that can be looted from the corpse
   private Weapon weapon;           //weapon that can be looted from the corpse
   private Armor armor;             //armor that can be looted from the corpse
   private ArrayList<Item> items;   //items that can be looted from the corpse
   private int numArrows;           //arrows that can be retrieved from the corpse
   private int removalDay;          //the day that the corpse will purged from the world (death day + 3)
   private double hitTime;          //used to show hit graphic when the NPC gets damaged
   private double size;             //size of the corpse to draw

   //ARGS:       picture,     charIndex   row,   col,   mapInd, gold,  weapon,   armor,   items,         removeal-day size
   public Corpse(ImageIcon p, byte ci, int r, int c, int mi, int g, Weapon w, Armor a, ArrayList<Item> i, int rd, double z)
   {
      picture = p;
      charIndex = ci;
      row = r;
      col = c;
      mapIndex = mi;
      gold = g;
      weapon = w;
      armor = a;
      items = i;
      removalDay = rd;
      hitTime = -1;
      numArrows = 0;
      size = z;
   }
   
   public byte getCharIndex()
   {
      return charIndex;
   }
   
   public double getHitTime()
   {
      return hitTime;
   }
   
   public void setHitTime(double ht)
   {
      hitTime = ht;
   }

  //post: clears all items, gold and arrows from the corpse
   public void clearItems()
   {
      gold = 0;
      if(items != null)
         items.clear();
      numArrows = 0;   
   }
   
   public boolean hasItems()
   {
      return (gold > 0 || (items != null && items.size() > 0));
   }
   
   public boolean hasItem(Item it)
   {
      for(Item i:items)
         if(it.getName().equalsIgnoreCase(i.getName()))
            return true;
      return false;
   }
   
   public int getGold()
   {
      return gold;
   }
   
   public ArrayList<Item> getItems()
   {
      return items;
   }
   
   public void addItem(Item it)
   {
      if(!this.hasItem(it))
         items.add(it);
   }
   
   public int getRemovalDay()
   {
      return removalDay;
   }
   
   public ImageIcon getPicture()
   {
      return picture;
   }
   
   public int getRow()
   {
      return row;
   }
   
   public int getCol()
   {
      return col;
   }
   
   public int getMapIndex()
   {
      return mapIndex;
   }
   
   public Armor getArmor()
   {
      return armor;
   }
   
   public Weapon getWeapon()
   {
      return weapon;
   }
   
   public void setArmor(Armor arm)
   {
      armor = arm;
   }
   
   public void setWeapon(Weapon weap)
   {
      weapon = weap;
   }
   
   public int getNumArrows()
   {
      return numArrows;
   }

   public void setNumArrows(int na)
   {
      numArrows = na;
   }
   
   public void setSize(double s)
   {
      size = s;
   }
   
   public double getSize()
   {
      return size;
   }
}