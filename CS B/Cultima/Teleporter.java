public class Teleporter
{
   private int toIndex;             //the index in the List of maps that we will teleport to
   private int toRow, toCol;        //the (row,col) position of where we will teleport to
   private String locType;          //the location type that this Teleporter leads to (world, cave, mine, city, etc)

   public Teleporter(int ti, int tr, int tc, String lt)
   {
      toIndex = ti;
      toRow = tr;
      toCol = tc;
      locType = lt;
   }
   
   //toRow and toCol values of -1 will be the flag that we have not procedurally generated the world yet
   public Teleporter(int ti)
   {
      toIndex = ti;
      toRow = -1;
      toCol = -1;
      locType = "?";
   }
   
   public String getLocType()
   {
      return locType;
   }
   
   public void setLocType(String lt)
   {
      locType = lt;
   }
   
   public int toIndex()
   {
      return toIndex;
   }

   public int toRow()
   {
      return toRow;
   }

   public int toCol()
   {
      return toCol;
   }

   public void setTo(int ti, int tr, int tc)
   {
      toIndex = ti;
      toRow = tr;
      toCol = tc;
   }
   
   //reset the teleporter to a state that is like one that hasn't been visited yet
   public void resetTo()
   {
      toRow = -1;
      toCol = -1;
   }

   
   public String toString()
   {
      return "("+toIndex+" "+toRow+" "+toCol+" "+locType+")";
   }
}