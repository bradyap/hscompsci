//chest, door and egg object to keep track of to be restored after a certain time
public class RestoreItem
{
   private int mapIndex;            //the index in the List of maps where the chest/door is
   private int row, col;            //the (row,col) position of where the chest/door is
   private byte value;              //the Terrain value that the chest/door has
                                    //for an egg, this is negative if it has been harvested and waiting to be restored - stores the NPC.getCharIndex of the creature that it is from
   private int restoreDay;          //after days hits restoreDay, we add the chest back to the map and remove it from the collection of those to be restored
                                    //-1 means it is a door, otherwise, it is a chest
   public RestoreItem(int m, int r, int c, byte v, int rd)
   {
      mapIndex = m;
      row = r;
      col = c;
      value = v;
      restoreDay = rd;
   }
   
   public RestoreItem(int m, int r, int c, byte v)
   {
      mapIndex = m;
      row = r;
      col = c;
      value = v;
      restoreDay = -1;
   }

   
   public int getMapIndex()
   {
      return mapIndex;
   }

   public int getRow()
   {
      return row;
   }

   public int getCol()
   {
      return col;
   }
   
   public byte getValue()
   {
      return value;
   }
   
   public void setValue(byte v)
   {
      value = v;
   }
   
   public int getRestoreDay()
   {
      return restoreDay;
   }
   
   public void setRestoreDay(int v)
   {
      restoreDay = v;
   }

   public String toString()
   {
      return "("+mapIndex+" "+row+" "+col+" "+value+" "+restoreDay+")";
   }
}