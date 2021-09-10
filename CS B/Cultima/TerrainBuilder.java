import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.awt.Point;

//builds the world with terrain and fills it with locations
public class TerrainBuilder
{
   public static final byte NORTH = 0;
   public static final byte NORTHEAST = 1;
   public static final byte EAST = 2;
   public static final byte SOUTHEAST = 3;
   public static final byte SOUTH = 4;
   public static final byte SOUTHWEST = 5;
   public static final byte WEST = 6;
   public static final byte NORTHWEST = 7;

   public static boolean buildOrLoadWorld(byte[][] world, String fileName)
   {
      boolean success = false;
      File imageFile = new File(fileName);
      int dot = fileName.indexOf(".");
      String locFileName = fileName.substring(0, dot) + "Locs.txt";
      if(imageFile.exists())		      //read in the file that already exists
      {
         success = FileManager.readMapFromBinFile(fileName, world);
      }
      if(success)                      //end the method if world reads in correctly
      {
         CultimaPanel.allDomiciles = new ArrayList<Location>();
         CultimaPanel.allAdventure = new ArrayList<Location>();
         CultimaPanel.allTemples = new ArrayList<Location>();
         CultimaPanel.allBattlefields = new ArrayList<Location>();
         CultimaPanel.allLocations = FileManager.readLocations(locFileName);
         return true;
      }
      imageFile.delete();
      imageFile = new File(fileName);
                                    //make a new world and write it out to a file
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
         if(t.getName().startsWith("TER") && !t.getName().contains("Flower"))
         {
            terrain.add(t);   //double the number of normal terrain except the more rare lava and rapids
            if(!t.getName().toLowerCase().contains("lava") && !t.getName().toLowerCase().contains("rapid"))
               terrain.add(t);
            if(world.length >= 700 && t.getName().toLowerCase().contains("water") )
            {
               for(int i=0; i<(world.length-700)/100; i++)
                  terrain.add(t);  
            }
         }
       
      seed(world, 0, terrain, ((world.length * world[0].length) / (CultimaPanel.SCREEN_SIZE * CultimaPanel.SCREEN_SIZE)) * 4);
      build(world, terrain, true);
      smooth(world);
   
      CultimaPanel.allLocations = addLocations(world);
   
      makePaths(world);                //make some paths between close towns
   
      FileManager.writeToBinFile(fileName, world);
      FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, locFileName);
   
      return false; 
   }

//returns true if the world has all of its cells filled
   public static boolean worldComplete(byte[][]world)
   {
      for(int r=0; r<world.length; r++)
         for(int c=0; c<world[0].length; c++)
            if(world[r][c]==0)
               return false;
      return true;
   }

//returns true if world[r][c] has a neighbor that is not filled
   public static boolean hasEmptyNeighbors(byte[][]world, int r, int c, boolean wrapAround)
   {
      for(int i=r-1; i<=r+1; i++)
         for(int j=c-1; j<=c+1; j++)
         {
            int nr = i;
            int nc = j;
            if(wrapAround && nr < 0)                  //wrap-around if needed
               nr = world.length-1;
            if(wrapAround && nc < 0)
               nc = world[0].length-1;
            if(wrapAround)
            {   
               nr %= world.length;
               nc %= world[0].length;
            }
            else
            {
               if (nr < 0 || nc < 0 || nr >= world.length || nc >=world[0].length)
                  continue;
            }
            if(nr==r && nc==c)
               continue;
            if(world[nr][nc]==0)
               return true;
         }
      return false;
   }

   //returns List of Points for empty neighbors of world[r][c]
   public static ArrayList<Point> getEmptyNeighbors(byte[][]world, int r, int c, boolean wrapAround)
   {
      ArrayList<Point> ans = new ArrayList<Point>();
      for(int i=r-1; i<=r+1; i++)
         for(int j=c-1; j<=c+1; j++)
         {
            int nr = i;
            int nc = j;
            if(wrapAround && nr < 0)                  //wrap-around if needed
               nr = world.length-1;
            if(wrapAround && nc < 0)
               nc = world[0].length-1;
            if(wrapAround)
            {   
               nr %= world.length;
               nc %= world[0].length;
            }
            else
            {
               if (nr < 0 || nc < 0 || nr >= world.length || nc >=world[0].length)
                  continue;
            }
            if(nr==r && nc==c)
               continue;
            if(world[nr][nc]==0)
               ans.add(new Point(nr,nc));
         }
   //scramble the order of the points so that there is no direction bias   
      ArrayList<Point>scrambled = new ArrayList();
      while(!ans.isEmpty())
         scrambled.add(ans.remove((int)(Math.random()*ans.size()))); 
      return scrambled;
   }

//returns List of Points for full neighbors of world[r][c]
   public static ArrayList<Point> getFullNeighbors(byte[][]world, int r, int c)
   {
      ArrayList<Point> ans = new ArrayList<Point>();
      for(int i=r-1; i<=r+1; i++)
         for(int j=c-1; j<=c+1; j++)
         {
            int nr = i;
            int nc = j;
            if(nr < 0)                  //wrap-around if needed
               nr = world.length-1;
            if(nc < 0)
               nc = world[0].length-1;
            nr %= world.length;
            nc %= world[0].length;
            if(nr==r && nc==c)
               continue;
            if(world[nr][nc]!=0)
               ans.add(new Point(nr,nc));
         }
      return ans;
   }

    //place random terrain elements in random locations of the world
   public static void seed(byte[][]world,  int mapIndex, ArrayList <Terrain> terrain, int numSeeds)
   {
      for(int r=0; r<world.length; r++)
         for(int c=0; c<world[0].length;c++)
            world[r][c] = 0;
         
   //add endgame Temple Skara-Brae at 50x50, surrounded by deep forest      
      if(mapIndex == 0)    //main world
      {
         for(int r=48; r<=52; r++)
            for(int c=48; c<=52;c++)
            {
               if(r==50 && c==50)
                  world[r][c] = getTerrainWithName("LOC_L_2_$Temple").getValue();
               else
                  world[r][c] = getTerrainWithName("TER_S_B_$Dense_forest").getValue();
            }
      }      
         
      for(int i=0; i<numSeeds; i++)
      {
         int r = (int)(Math.random()*world.length);
         int c = (int)(Math.random()*world[0].length);
         while(world[r][c]!=0)  //find an empty cell
         {
            r = (int)(Math.random()*world.length);
            c = (int)(Math.random()*world[0].length);
         }
      //fill the empty cell with a random terrain name
         int rand = (int)(Math.random()*terrain.size());
         Terrain current = terrain.get(rand);
         world[r][c] = current.getValue();     
      }    
   }

//returns the neighbors of a terrain of a specific name within the List of Terrain, null if not found
   public static HashMap<String, Integer> getNeighborsOf(String name)
   {
      for(Terrain t:CultimaPanel.allTerrain)
         if(name.equalsIgnoreCase(t.getName()))
            return t.getNeighbors();
      return null;
   }

//returns the terrain of a specific name within the List of Terrain, null if not found
   public static Terrain getTerrainWithName(String name, ArrayList<Terrain> terrain)
   {
      for(Terrain t:terrain)
         if(name.equalsIgnoreCase(t.getName()))
            return t;
      return null;
   }

 //returns the terrain of a specific name within the List of Terrain, null if not found
   public static Terrain getTerrainWithName(String name)
   {
      for(Terrain t:CultimaPanel.allTerrain)
         if(name.equalsIgnoreCase(t.getName()))
            return t;
      return null;
   }

//returns the terrain of a specific name within the List of Terrain, null if not found
   public static Terrain getTerrainWithValue(Byte v)
   {
      for(Terrain t:CultimaPanel.allTerrain)
         if(Math.abs(t.getValue()) == Math.abs(v))
            return t;
      return null;
   }

//returns the Locaton on the world at (r, c) in mapIndex mi
   public static Location getLocationAt(ArrayList<Location> locations, int r, int c, int mi) 
   {
      for(Location loc:locations)
         if(loc.getRow()==r && loc.getCol()==c && loc.getFromMapIndex()==mi)
            return loc;
      return null;
   }

//pre:  the world has been seeded with some Terrain - some elements of world have Terrain values
   public static void build(byte[][]world, ArrayList<Terrain> terrain, boolean wrapAround)
   {
      for(int i=0; i < (world.length * 100) && !worldComplete(world); i++)
      {   
         int r = (int)(Math.random()*world.length);
         int c = (int)(Math.random()*world[0].length);
         while(world[r][c]==0 || !hasEmptyNeighbors(world, r, c, wrapAround))    //find a cell that is occupied that has empty neighbors
         {
            r = (int)(Math.random()*world.length);
            c = (int)(Math.random()*world[0].length);
         }
      //for any empty neighbor of the terrain at [r][c], fill it in according to the percentages in the Terrain ArrayList
         String name = CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName();
      
         ArrayList<Point> emptySpots = getEmptyNeighbors(world, r, c, wrapAround);
         for(Point spot: emptySpots)
         {
            r = (int)(spot.getX());
            c = (int)(spot.getY());
            int roll = (int)(Math.random()*100);
            int total = 0;
            HashMap<String, Integer> neighbors = getNeighborsOf(name);
            if(!wrapAround)      
            {
               HashMap<String, Integer> tempNeighbors = new HashMap<String, Integer>();
               Set<String> names = neighbors.keySet();
               for(String n: names)
                  if(!n.toLowerCase().contains("water"))
                     tempNeighbors.put(n, neighbors.get(n));
               neighbors = tempNeighbors;      
            }
            if(neighbors != null)
            {
               Set<String> names = neighbors.keySet();
               boolean found = false;
               for(String n: names)
               {
                  int chance = neighbors.get(n);
                  total += chance;
                  if (roll < total)
                  {
                     Terrain current = getTerrainWithName(n);
                     if(current != null)
                     {
                        world[r][c] = current.getValue();
                        found = true;
                        break;
                     }
                  }
               }
               if(!found)     //pick a random terrain name
               {
                  int index = (int)(Math.random()*terrain.size());
                  world[r][c] = (terrain.get(index)).getValue();
               }
            }
         }
      }
      if(!worldComplete(world))   //fill in any empty spots if there are any
      {
         for(int r=0; r<world.length; r++)
            for(int c=0; c<world[0].length; c++)
            {
               if(world[r][c]==0)
               {
                  ArrayList<Point> nextTo = getFullNeighbors(world,  r,  c);
                  if(nextTo==null || nextTo.size() == 0)    //if the empty spot has all empty neighbors, fill with a random terrain
                  {
                     int index = (int)(Math.random()*terrain.size());
                     world[r][c] = terrain.get(index).getValue();
                  }
                  else                                      //if the empty spot has some full neighbors, pick a random full neighbor
                  {                                         //to fill the empty spot with
                     int randNext = (int)(Math.random()*nextTo.size());
                     Point p = nextTo.get(randNext);
                     world[r][c] = world[(int)(p.getX())][(int)(p.getY())];
                  }
               }
            }
      }
   }

   public static void smooth(byte[][]world)
   {
      ArrayList<Point> boatSpawns = new ArrayList();  //while we are traversing the map, record all possible water locations adjacent to land so we can spawn some boats
      
      for(int r=0; r<world.length; r++)          //scan from left->right.  If a high-mountain does not have mountains on either side, put low mountains in
         for(int c=0; c<world[0].length; c++)  //if low mountain does not have mountain or hills on either side, put them in
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName();
            if(world[r][c]==0)
               continue;
            if(current.equals("TER_I_B_$Mountains"))   //high mountain
            {
               String onLeft = "";
               String onRight = "";
               if(c-1 >= 0)
                  onLeft = CultimaPanel.allTerrain.get(Math.abs(world[r][c-1])).getName();
               if(c+1 < world[0].length)
                  onRight = CultimaPanel.allTerrain.get(Math.abs(world[r][c+1])).getName();
               if(!onLeft.equals("") && !onLeft.equals("TER_I_B_$Mountains") && !onLeft.equals("TER_V_B_$Mountain") && !onLeft.equals("TER_S_$Hills"))  
               {
                  world[r][c-1] = getTerrainWithName("TER_V_B_$Mountain").getValue();
               } 
               if(!onRight.equals("") && !onRight.equals("TER_I_B_$Mountains") && !onRight.equals("TER_V_B_$Mountain") && !onRight.equals("TER_S_$Hills"))  
               {
                  world[r][c+1] = getTerrainWithName("TER_V_B_$Mountain").getValue();
               } 
            }
            if(current.equals("TER_V_B_$Mountain"))   //mountain
            {
               String onLeft = "";
               String onRight = "";
               if(c-1 >= 0)
                  onLeft = CultimaPanel.allTerrain.get(Math.abs(world[r][c-1])).getName();
               if(c+1 < world[0].length)
                  onRight = CultimaPanel.allTerrain.get(Math.abs(world[r][c+1])).getName();
               if(!onLeft.equals("") && !onLeft.equals("TER_I_B_$Mountains") && !onLeft.equals("TER_V_B_$Mountain") && !onLeft.equals("TER_S_$Hills"))  
               {
                  world[r][c-1] = getTerrainWithName("TER_S_$Hills").getValue();
               } 
               if(!onRight.equals("") && !onRight.equals("TER_I_B_$Mountains") && !onRight.equals("TER_V_B_$Mountain") && !onRight.equals("TER_S_$Hills"))  
               {
                  world[r][c+1] = getTerrainWithName("TER_S_$Hills").getValue();
               } 
            }
            boolean isMagma = (current.contains("Lava"));
            if(isMagma)   //put dead forest around magma
            {
               String onLeft = "", onRight = "";
               boolean leftIsMagma = false, rightIsMagma = false;
               if(c-1 >= 0)
               {
                  onLeft = CultimaPanel.allTerrain.get(Math.abs(world[r][c-1])).getName();
                  leftIsMagma = (onLeft.contains("Lava"));
               }
               if(c+1 < world[0].length)
               {
                  onRight = CultimaPanel.allTerrain.get(Math.abs(world[r][c+1])).getName();
                  rightIsMagma = (onRight.contains("Lava"));
               }
               if(!onLeft.equals("") && !leftIsMagma && !onLeft.equals("TER_$Dead_forest"))  
               {
                  world[r][c-1] = getTerrainWithName("TER_$Dead_forest").getValue();
               } 
               if(!onRight.equals("") && !rightIsMagma && !onRight.equals("TER_$Dead_forest"))  
               {
                  world[r][c+1] = getTerrainWithName("TER_$Dead_forest").getValue();
               } 
            }
            if(current.toLowerCase().contains("water"))  //record all possible water locations adjacent to land so we can spawn some boats
            {
               if(LocationBuilder.isDryLand(world, r-1, c) || LocationBuilder.isDryLand(world, r+1, c) || LocationBuilder.isDryLand(world, r, c-1) || LocationBuilder.isDryLand(world, r, c+1))
                  boatSpawns.add(new Point(r, c));
            }
         }
      if(boatSpawns.size() > 0)
      {
         
         int numSeeds = ((world.length / CultimaPanel.SCREEN_SIZE)*10)/3;
         for(int i=0; i<numSeeds; i++)
         {
            Point boatSpawn = boatSpawns.get((int)(Math.random()*boatSpawns.size()));
            int r = (int)(boatSpawn.getX());
            int c = (int)(boatSpawn.getY());
            if(!Utilities.NPCAt(r, c, 0))
            {
               NPCPlayer ourBoat = new NPCPlayer(NPC.BOAT, r, c, 0, "world");
               ourBoat.setGold(0);
               ourBoat.clearItems();
               ourBoat.setBody(100);  
               ourBoat.setHasMet(true);
               CultimaPanel.worldMonsters.add(ourBoat);
            }
         }
      }
   }

//returns true if world[r][c] is on the water - used so we don't place a structure there
   public static boolean onWater(byte[][]world, int r, int c)
   {
      if(LocationBuilder.outOfBounds(world, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName().toLowerCase();
      return (current.contains("water") || current.contains("lava"));
   }
   
   public static boolean isFlower(byte[][]world, int r, int c)
   {
      if(LocationBuilder.outOfBounds(world, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName().toLowerCase();
      return (current.contains("flower"));
   }

//returns true if world[r][c] is campable for player that is a vampire
   public static boolean isCampableSpotForVampire()
   {//must be indoors or under a roof or in a coffin
      boolean indoors = false;
      String locType = CultimaPanel.player.getLocationType().toLowerCase();
        
      int r = CultimaPanel.player.getRow();
      int c = CultimaPanel.player.getCol();
      int mi = CultimaPanel.player.getMapIndex();  
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      if(locType.contains("dungeon") || locType.contains("cave") || locType.contains("mine") || locType.contains("lair"))
         return (!current.contains("water"));
      return current.contains("coffin");
   }

//returns true if world[r][c] is campable for player
   public static boolean isCampableSpotForPlayer()
   {
      int r = CultimaPanel.player.getRow();
      int c = CultimaPanel.player.getCol();
      int mi = CultimaPanel.player.getMapIndex();
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      if(current.contains("dense"))       //can't camp in dense forest
         return false;
      return (current.contains("hill") || current.contains("grass") || current.contains("sand") || current.contains("forest") || current.contains("bed"));
   }
   
   public static boolean isGoodSpotForTreasure(int r, int c)
   {
      byte[][] currMap = CultimaPanel.map.get(0);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      return (current.contains("hill") || current.contains("grass") || current.contains("sand") || current.contains("forest") || current.contains("bed"));
   }
   
   //returns true if player is on a bed
   public static boolean onBed()
   {
      int r = CultimaPanel.player.getRow();
      int c = CultimaPanel.player.getCol();
      int mi = CultimaPanel.player.getMapIndex();
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      return (current.contains("bed"));
   }
     
  //returns true if player is likely in a building - used for rain and torch lighting like we are indoors
   public static boolean indoors()
   {
      boolean indoors = false;
      String locType = CultimaPanel.player.getLocationType().toLowerCase();
      if(locType.contains("castle") || locType.contains("tower") || locType.contains("dungeon") || locType.contains("cave") || locType.contains("mine") || locType.contains("lair") || locType.contains("wolfenstein"))
         return true;    
      int r = CultimaPanel.player.getRow();
      int c = CultimaPanel.player.getCol();
      int mi = CultimaPanel.player.getMapIndex();  
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      Terrain current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c]));
      return isIndoorFloor(current);
   }
   
   //returns true if p is likely in a building - used for shielding from firestorm spell
   public static boolean indoors(NPCPlayer p)
   {
      String locType = p.getLocationType().toLowerCase();
      if(locType.contains("castle") || locType.contains("tower") || locType.contains("dungeon") || locType.contains("cave") || locType.contains("mine") || locType.contains("lair") || locType.contains("wolfenstein"))
         return true;    
      int r = p.getRow();
      int c = p.getCol();
      int mi = p.getMapIndex();  
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      Terrain current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c]));
      return isIndoorFloor(current);
   }

//returns true if ter is an inside floor - used to draw rain
   public static boolean isIndoorFloor(Terrain ter)
   {
      String current = ter.getName().toLowerCase();
      return (current.contains("inside") || current.contains("bed")  || current.contains("counter") || current.contains("wardrobe")
           || current.contains("black_floor") || current.contains("black_floor") || current.contains("blue_floor") || current.contains("purple_floor") || current.contains("dirty_floor")
           || current.contains("red_floor") || current.contains("black_stone_floor") || current.contains("chest") || current.contains("book")
           || current.contains("sign") || current.contains("door") || current.contains("wall")
           || current.contains("window") || current.contains("torch") || current.contains("white_brick") || current.contains("dungeon"));
   }
   
 //returns true if current is an inside floor
   public static boolean isInsideFloor(String current)
   {
      current = current.toLowerCase();
      return (current.contains("inside") || current.contains("black_floor") || current.contains("blue_floor") 
                                         || current.contains("red_floor") || current.contains("black_stone_floor"));
   }  
   
   //returns true if world[r][c] is an inside floor
   public static boolean isInsideFloor(int r, int c, int mi)
   {
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      return isInsideFloor(current);
   }

   //returns true if world[r][c] is campable
   public static boolean isCampableSpot(int r, int c, int mi)
   {
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      if(current.contains("dense"))       //can't camp in dense forest
         return false;
      return (current.contains("hill") || current.contains("grass") || current.contains("sand") || current.contains("forest"));
   }
   
      //returns true if world[r][c] is a good place to spawn a city monster
   public static boolean isGoodCityMonsterSpawn(int r, int c, int mi)
   {
      byte[][] currMap = CultimaPanel.map.get(mi);
      if(LocationBuilder.outOfBounds(currMap, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
      if(current.contains("bed"))       
         return false;
      return (current.contains("hill") || current.contains("grass") || current.contains("sand") || current.contains("forest"));
   }

//returns true if world[r][c] is a town, castle, village, etc
   public static boolean habitablePlace(byte[][]world, int r, int c)
   {
      String current = CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName().toLowerCase();
      if (current.contains("castle") || current.contains("tower") || current.contains("city") || current.contains("fortress") 
      || current.contains("village") || current.contains("domicile") || current.contains("port"))
         return true;
      return false;
   }

//returns true if locType is a town, castle, village, etc
   public static boolean habitablePlace(String locType)
   {
      String current = locType.toLowerCase();
      if (current.contains("castle") || current.contains("tower") || current.contains("city")
      || current.contains("fortress") || current.contains("village") || current.contains("domicile") || current.contains("port"))
         return true;
      return false;
   }
   
   public static boolean purgeLocation(String locType)
   {
      String current = locType.toLowerCase();
      if (current.contains("castle") || current.contains("tower") || current.contains("city")
      || current.contains("fortress") || current.contains("village") || current.contains("port"))
         return true;
      return false;
   }

//returns true if locType is a town, village, city, etc, but not a castle
   public static boolean isCity(String locType)
   {
      String current = locType.toLowerCase();
      if (current.contains("city") || current.contains("fortress") || current.contains("village") || current.contains("domicile") || current.contains("port"))
         return true;
      return false;
   }

//returns true if name is a multi-part castle that requires 3 adjacent tiles
   public static boolean multiPartCastle(String name)
   {
      name = name.toLowerCase();
      if (name.contains("city") || name.contains("fortress"))
         return true;
      return false;
   }

//given the terrain tile for a location, returns the location type (town, temple, adventure) 
   public static String locationTerrainType(Terrain ter)
   {
      String name = ter.getName().toLowerCase();
      if(habitablePlace(name))
         return "town";
      if (name.contains("cave") || name.contains("mine") || name.contains("lair") || name.contains("dungeon"))  
         return "adventure";
      if(name.contains("temple"))
         return "temple";
      if(name.contains("battlefield"))
         return "battlefield";   
      return "place"; 
   }

//pre: mapIndex >=0 && mapIndex < CultimaPanel.map.size() and row, col is a valid coordinate of the map at mapIndex
//returns true if there is water within range spaces of the terrain at row, col in the world map
   public static boolean waterInrange(int mapIndex, int row, int col, int range)
   {
      if(mapIndex < 0 || mapIndex >= CultimaPanel.map.size())
         return false;
      byte[][]currMap = CultimaPanel.map.get(mapIndex);   //world map
      for(int r=row-range; r<=row+range; r++)
         for(int c=col-range; c<=col+range; c++)
         {
            int r2 = r;
            int c2 = c;
            if(mapIndex == 0)                            //wrap around for world map
            {
               r2 = CultimaPanel.equalizeWorldRow(r);
               c2 = CultimaPanel.equalizeWorldCol(c);
            }
            if(r2 < 0 || r2 >= currMap.length || c2 < 0 || c2 >= currMap[0].length)
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r2][c2])).getName().toLowerCase();
            if(current.contains("water"))
            {
               return true;
            }
         }
      return false;
   }

//fills up world with locations and returns the list of all Locations added
   public static ArrayList<Location> addLocations(byte[][]world)
   {
      ArrayList <Terrain> location = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
         if(t.getName().startsWith("LOC") && !t.getName().toLowerCase().contains("dungeon") && !t.getName().toLowerCase().contains("arena")) //dungeon entrance is a teleporter location for an indoor place, not the world
            location.add(t);
   
      ArrayList<Location> allLocations = new ArrayList<Location>();
      CultimaPanel.allDomiciles = new ArrayList<Location>();
      CultimaPanel.allAdventure = new ArrayList<Location>();
      CultimaPanel.allTemples = new ArrayList<Location>();
      CultimaPanel.allBattlefields = new ArrayList<Location>();
   
   //make the first element of the Locations list a temporary one, because we want Locations to be at the index of where they are in the map List, and the world map is at index 0
      allLocations.add(new Location("TEMP", -1, -1, -1, getTerrainWithValue(CultimaPanel.windDir), new Teleporter(-1)));
      //make the 2nd element the spot where a ship-grapple location will  be created and used for battles to take over a ship, or ship to ship
      allLocations.add(new Location("ship", -1, -1, -1, getTerrainWithValue(CultimaPanel.windDir), new Teleporter(-1)));
     //add special endgame temple at 50x50
      world[50][50] = getTerrainWithName("LOC_L_2_$Temple").getValue();
      Teleporter teleporter = new Teleporter((CultimaPanel.map).size());
      allLocations.add(new Location("Skara-Brae", 50, 50, 0, CultimaPanel.allTerrain.get(Math.abs(world[50][50])), teleporter));
      CultimaPanel.allTemples.add(new Location("Skara-Brae", 50, 50, 0, CultimaPanel.allTerrain.get(Math.abs(world[50][50])), teleporter));
   
      int numSeeds = (world.length / CultimaPanel.SCREEN_SIZE)*10;
      ArrayList<Terrain> residential = new ArrayList<Terrain>();  //cities, castles, villages, etc
      ArrayList<Terrain> adventure = new ArrayList<Terrain>();    //caves, mines, mountain lairs, etc
      ArrayList<Terrain> rare = new ArrayList<Terrain>();         //battlefields, anything new
      for(Terrain t:location)
      {  //Adventure:
         if(t.getName().contains("Battlefield"))
            rare.add(t);
         else if(t.getName().contains("Cave") || t.getName().contains("Lair") || t.getName().contains("Mine"))
            adventure.add(t);
         //Don't add:  terrain that are the left and right side of large cities   
         else if(!t.getName().contains("East") && !t.getName().contains("West") && !t.getName().contains("Port"))
            residential.add(t);
      }
      adventure.add(getTerrainWithName("LOC_L_2_$Temple"));   //temples can be residential, or lead to dungeons
      boolean isResidential = false;
      boolean hasCapital = false;
      for(int i=0; i<numSeeds; i++)
      {
         Terrain temp = null;
         Terrain arena = null;           //for a possible arena next to a city
         int arenaR = -1, arenaC = -1;   //location for a possible arena next to a city
         double pick = Math.random();
         if(pick < 0.3)
         {
            temp = residential.get((int)(Math.random() * residential.size()));              
            isResidential = true;
         }
         else if(pick < 0.9)
         {
            temp = adventure.get((int)(Math.random() * adventure.size()));
            if(temp.getName().toLowerCase().contains("insanity") && Math.random() < 0.5)  //make most caves regular
               temp = getTerrainWithName("LOC_$Cave");
         }
         else
            temp = rare.get((int)(Math.random() * rare.size()));
      
         int r = (int)(Math.random()*world.length);      //pick spot for a location to go
         int c = (int)(Math.random()*world[0].length);   //pick again if we are on the wrong terrain for this Location or we picked a spot on the water or there is already a Location there
         int numTimes = 0;                               //count how many times we try to find a lood location for the location
         boolean skip = false;                           //skip this location and try another
         while(!(temp.getNeighbors()).containsKey(CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName()) || onWater(world, r, c) 
            || getLocationAt(allLocations, r, c, 0)!=null || (r==50 && c==50) ||  //a special temple will go at 50x50
            getLocationAt(allLocations, CultimaPanel.equalizeWorldRow(r+1), c, 0)!=null || //there is a location next to it
            getLocationAt(allLocations, CultimaPanel.equalizeWorldRow(r-1), c, 0)!=null || 
            getLocationAt(allLocations, r, CultimaPanel.equalizeWorldCol(c+1), 0)!=null || getLocationAt(allLocations, r, CultimaPanel.equalizeWorldCol(c-1), 0)!=null)
         {
            r = (int)(Math.random()*world.length);
            c = (int)(Math.random()*world[0].length);
            numTimes++;
            if(numTimes >= 1000)                         //if we try more than 1000 times, lets abandon this location and try another
            {
               skip = true;
               break;
            }
         }
         if(skip)
            continue;
         ArrayList<Point> waterLocs =  Utilities.waterLocsAdjacentTo(r, c, false);
         ArrayList<Point> waterLocsDiag =  Utilities.waterLocsAdjacentTo(r, c, true);
      //only make possibility for a port town if it is vertcally or horizontally adjacent to water with at least one diagonal water border as well
         if(isResidential && waterLocs.size()>0  && waterLocsDiag.size()>1 && Math.random() < 0.66)  //if there is water in adjacent cell of the location, make it a port 66% of the time 
         {                                             
            temp = getTerrainWithName("LOC_L_2_$Port");
            if(!hasCapital && waterLocsDiag.size()>1 && r-1>=0)      //make sure there is at least one capital city
               temp = getTerrainWithName("LOC_L_3_$City_Center");
         }  
         boolean greatCity = false; 
         if(multiPartCastle(temp.getName()))    //if there is space on both sides, place the other parts of the multi-part castle
         {
            if(c-1>=0 && c+1<world[0].length && world[r][c-1]!=0 && world[r][c+1]!=0)
            {
               if(temp.getName().contains("City"))
               {
                  temp = getTerrainWithName("LOC_L_3_$City_Center");
                  world[r][c-1] = getTerrainWithName("LOC_L_3_$City_West").getValue();
                  world[r][c+1] = getTerrainWithName("LOC_L_3_$City_East").getValue();
                  if(waterLocsDiag.size()>1 && r-1>=0)      //capital city
                  {
                     world[r-1][c] = getTerrainWithName("LOC_L_3_$Fortress_Center").getValue();
                     world[r-1][c-1] = getTerrainWithName("LOC_L_3_$Fortress_West").getValue();
                     world[r-1][c+1] = getTerrainWithName("LOC_L_3_$Fortress_East").getValue();
                     greatCity = true; 
                     hasCapital = true;
                  }
                  if(Math.random() < 0.25 || greatCity)         //maybe place an arena to the side of a city
                  {  //see if there is room on both sides of the city
                     boolean leftClear = (c-2>=0 && world[r][c-2]!=0);
                     boolean rightClear = (c+2<world[0].length && world[r][c+2]!=0);
                     if(leftClear || rightClear)
                     {
                        arena = getTerrainWithName("LOC_L_1_$Arena");
                        if((leftClear && rightClear && Math.random()<0.5) || (leftClear && !rightClear))         //place arena to the left of the city
                        {
                           world[r][c-2] = arena.getValue();
                           arenaR = r;
                           arenaC = c-2;
                        }
                        else if(rightClear)   //place arena to the right of the city
                        {
                           world[r][c+2] = arena.getValue();
                           arenaR = r;
                           arenaC = c+2;
                        }
                     }
                  }
               }
               else
               {
                  temp = getTerrainWithName("LOC_L_3_$Fortress_Center");
                  world[r][c-1] = getTerrainWithName("LOC_L_3_$Fortress_West").getValue();
                  world[r][c+1] = getTerrainWithName("LOC_L_3_$Fortress_East").getValue();
               } 
            }
            else  //no room to fit a 3-part castle, so just make it a regular castle or village
            {
               String [] oneCellTowns = {"LOC_L_2_$Large_castle","LOC_L_2_$Small_castle","LOC_L_2_$Tower","LOC_L_1_$Village"};
               String newTown = oneCellTowns[(int)(Math.random()*oneCellTowns.length)];
               temp = getTerrainWithName(newTown);
            }
         }
      
         world[r][c] = temp.getValue();
         String type = "place";
         if(temp.getName().contains("Port"))
            type = "port";
         else if(greatCity)
            type = "capital";   
         else if(habitablePlace(world,  r,  c))
            type = "town";
         else if(temp.getName().contains("Temple"))
            type = "temple";  
         else if(temp.getName().contains("Cave"))
            type = "cave";
         else if(temp.getName().contains("Mine"))
            type = "mine";
         else if(temp.getName().contains("Lair"))
            type = "lair";
         else if(temp.getName().contains("Battlefield"))
            type = "battlefield";   
         String name = Utilities.nameGenerator(type);
      
      //assign teleporter to a new world or location
         teleporter = new Teleporter((CultimaPanel.map).size());
         allLocations.add(new Location(name, r, c, 0, CultimaPanel.allTerrain.get(Math.abs(world[r][c])), teleporter));
         if(type.equals("town") || type.equals("port") || type.equals("capital"))
            CultimaPanel.allDomiciles.add(new Location(name, r, c, 0, CultimaPanel.allTerrain.get(Math.abs(world[r][c])), teleporter));
         else if (type.equals("cave") || type.equals("mine") || type.equals("lair"))  
            CultimaPanel.allAdventure.add(new Location(name, r, c, 0, CultimaPanel.allTerrain.get(Math.abs(world[r][c])), teleporter));
         else if (type.equals("temple"))  
            CultimaPanel.allTemples.add(new Location(name, r, c, 0, CultimaPanel.allTerrain.get(Math.abs(world[r][c])), teleporter));
         else if (type.equals("battlefield"))  
            CultimaPanel.allBattlefields.add(new Location(name, r, c, 0, CultimaPanel.allTerrain.get(Math.abs(world[r][c])), teleporter));
         
         if(arena != null && arenaR!=-1 && arenaC!=-1)    //we placed an arena at arenaR, arenaC
         {
            type = "arena";
            name = Display.provinceName(name) + " " + Utilities.arenaSuffix[(int)(Math.random()*Utilities.arenaSuffix.length)];
            teleporter = new Teleporter((CultimaPanel.map).size());
            allLocations.add(new Location(name, arenaR, arenaC, 0, CultimaPanel.allTerrain.get(Math.abs(world[arenaR][arenaC])), teleporter));
            CultimaPanel.allDomiciles.add(new Location(name, arenaR, arenaC, 0, CultimaPanel.allTerrain.get(Math.abs(world[arenaR][arenaC])), teleporter));
         }        
      }
      
      int numHarvestPoints = numSeeds - (numSeeds/2);
      for(int i=0; i<numSeeds; i++)
      {
         int r = Player.rollDie(0, world.length-1);
         int c = Player.rollDie(0, world[0].length-1);
         if(!CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName().contains("LOC") && LocationBuilder.isDryLand(world, r, c))
         {
            byte animalType = NPC.CHICKEN;
            if(Math.random() < 0.25)
               animalType = NPC.SNAKE;
            CultimaPanel.eggsToHarvest.add(new RestoreItem(0, r, c, animalType));
         }
      }   
      return allLocations;
   }

//picks the name of a random cave, mine or lair
   public static String randAdventure()
   {
      if(CultimaPanel.allAdventure != null && CultimaPanel.allAdventure.size() > 0)
         return CultimaPanel.allAdventure.get((int)(Math.random()*CultimaPanel.allAdventure.size())).getName();
      return "";
   }

//adds a prisoner (BEGGER) at the location loc for CITY_RESCUE mission
//returns false if we could not add one
   public static boolean addPrisoner(Location loc, String locType)
   {
      byte[][]cave = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         cave=CultimaPanel.map.get(loc.getMapIndex());
      else
      {  
         loc = LocationBuilder.constructAdventureAt(loc, locType);
         cave=CultimaPanel.map.get(loc.getMapIndex());
      }
      ArrayList<Point> floorSpots = new ArrayList<Point>();
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            String spot = (CultimaPanel.allTerrain.get(Math.abs(cave[r][c]))).getName().toLowerCase();
            if(spot.contains("floor") && !spot.contains("trap"))
               floorSpots.add(new Point(r, c));
         }
      if(floorSpots.size() > 0)
      {
         Point randSpot = floorSpots.get((int)(Math.random()*floorSpots.size()));
         int row = (int)(randSpot.getX());
         int col = (int)(randSpot.getY());
         NPCPlayer prisoner = new NPCPlayer(NPC.BEGGER, row, col, loc.getMapIndex(), row, col, "dungeon");          
         CultimaPanel.civilians.get(loc.getMapIndex()).add(prisoner);
         return true;
      }
      return false;
   }

//adds NPCPlayer at the location loc for FIND_WEAPON mission
//returns false if we could not add one
   public static boolean addNPCtoLocation(Location loc, String locType, NPCPlayer p)
   {
      byte[][]cave = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         cave=CultimaPanel.map.get(loc.getMapIndex());
      else
      {  
         loc = LocationBuilder.constructAdventureAt(loc, locType);
         cave=CultimaPanel.map.get(loc.getMapIndex());
      }
      ArrayList<Point> floorSpots = new ArrayList<Point>();
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            String spot = (CultimaPanel.allTerrain.get(Math.abs(cave[r][c]))).getName().toLowerCase();
            if(spot.contains("floor") && !spot.contains("trap"))
               floorSpots.add(new Point(r, c));
         }
      if(floorSpots.size() > 0)
      {
         Point randSpot = floorSpots.get((int)(Math.random()*floorSpots.size()));
         int row = (int)(randSpot.getX());
         int col = (int)(randSpot.getY());
         p.setRow(row);
         p.setCol(col);
         p.setMapIndex(loc.getMapIndex());
         CultimaPanel.civilians.get(loc.getMapIndex()).add(p);
         return true;
      }
      return false;
   }
   
//adds NPCPlayer at the location loc and returns the NPCPlayer's position
//returns null if we could not add one
   public static Point addNPCtoLocationReturnPoint(Location loc, String locType, NPCPlayer p)
   {
      byte[][]cave = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         cave=CultimaPanel.map.get(loc.getMapIndex());
      else
      {  
         loc = LocationBuilder.constructAdventureAt(loc, locType);
         cave=CultimaPanel.map.get(loc.getMapIndex());
      }
      ArrayList<Point> floorSpots = new ArrayList<Point>();
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            String spot = (CultimaPanel.allTerrain.get(Math.abs(cave[r][c]))).getName().toLowerCase();
            if(spot.contains("floor") && !spot.contains("trap"))
               floorSpots.add(new Point(r, c));
         }
      if(floorSpots.size() > 0)
      {
         Point randSpot = floorSpots.get((int)(Math.random()*floorSpots.size()));
         int row = (int)(randSpot.getX());
         int col = (int)(randSpot.getY());
         p.setRow(row);
         p.setCol(col);
         p.setMapIndex(loc.getMapIndex());
         CultimaPanel.civilians.get(loc.getMapIndex()).add(p);
         return new Point(row, col);
      }
      return null;
   }

//to reoccupy temple overrun with monsters for clear mission from NPC.WISE
   public static void addWorshipersToTemple(Location loc)
   {
      byte[][]house = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         house=CultimaPanel.map.get(loc.getMapIndex());
      else
      {  
         loc = LocationBuilder.constructAdventureAt(loc, "temple");
         house=CultimaPanel.map.get(loc.getMapIndex());
      }
      loc.clearMonsters();
   
   
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      byte[] occTypes = {NPC.BEGGER, NPC.WISE, NPC.WOMAN, NPC.MAN};
   
      int numPpl = (int)(Math.random()*10) + 1;
      if(CultimaPanel.numNPCsInLoc == 0)
         numPpl = 1;
      for(int i=0; i<numPpl; i++)
      {
         int r=Player.rollDie(2,house.length-2);
         int c=Player.rollDie(2,house[0].length-2);
         int numTimes = 0;
         boolean found = true;
         while(LocationBuilder.isImpassable(house, r, c) || TerrainBuilder.onWater(house, r, c) || occupants[r][c]!=null)
         {
            r=Player.rollDie(2,house.length-2);
            c=Player.rollDie(2,house[0].length-2);
            numTimes++;
            if(numTimes >= 1000)
            {
               found=false;
               break;
            }
         }
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         if(i==0)
            charIndex = NPC.WISE;      //at least one wise man
         occupants[r][c] = new NPCPlayer(charIndex, r, c, loc.getMapIndex(), r, c, "temple");
      }
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
            if(occupants[r][c] != null)
            {
               if(loc.getMapIndex() >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(loc.getMapIndex(), new ArrayList<NPCPlayer>());
               NPCPlayer toAdd = new NPCPlayer(occupants[r][c].getCharIndex(), r, c, loc.getMapIndex(), occupants[r][c].getHomeRow(),occupants[r][c].getHomeCol(), "temple");   
               CultimaPanel.civilians.get(loc.getMapIndex()).add(toAdd);
            }
   }

//returns the closest location from locations that is a monster lair with prisoners
   public static Location closeLairWithPrisoners(ArrayList<Location> locations)
   {
      if(locations == null || locations.size() == 0)
         return null;
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         String currTerrName = loc.getTerrain().getName().toLowerCase();
         if(!currTerrName.contains("lair") || LocationBuilder.countPrisoners(loc)<=0)
            continue;
         double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
         boolean added = false;
         for(int i=0; i<temp.size(); i++)
         {
            int tr = temp.get(i).getRow();
            int tc = temp.get(i).getCol();
            double distToTemp = Display.wrapDistance(tr, tc, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            if(distToLoc < distToTemp)
            {
               temp.add(i,loc);
               added = true;
               break;
            }
         }
         if(!added)
         {
            temp.add(loc);
         }
      }
      if(temp.size() > 0)
      {
         return temp.get(0);
      }
      return null;
   }

 //returns the closest location from locations that is an arena
   public static Location closeArena(ArrayList<Location> locations)
   {
      if(locations == null || locations.size() == 0)
         return null;
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         String currTerrName = loc.getTerrain().getName().toLowerCase();
         if(!currTerrName.contains("arena"))
            continue;
         double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
         if(distToLoc < CultimaPanel.SCREEN_SIZE)
            temp.add(loc);
      }
      if(temp.size() > 0)
      {
         return temp.get(0);
      }
      return null;
   }

//returns the closest location from locations that is an arena with prisoners
   public static Location closeArenaWithPrisoners(ArrayList<Location> locations)
   {
      if(locations == null || locations.size() == 0)
         return null;
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         String currTerrName = loc.getTerrain().getName().toLowerCase();
         if(!currTerrName.contains("arena") || LocationBuilder.countPrisoners(loc)<=0)
            continue;
         double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
         if(distToLoc < CultimaPanel.SCREEN_SIZE)
            temp.add(loc);
      }
      if(temp.size() > 0)
      {
         return temp.get(0);
      }
      return null;
   }

   //returns the closest location from locations that is a monster lair with prisoners within maxDist steps
   public static Location closeLairWithPrisoners(ArrayList<Location> locations, int maxDist)
   {
      if(locations == null || locations.size() == 0)
         return null;
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         String currTerrName = loc.getTerrain().getName().toLowerCase();
         if(!currTerrName.contains("lair") || LocationBuilder.countPrisoners(loc)<=0)
            continue;
         double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
         if(distToLoc > maxDist)
            continue;
         boolean added = false;
         for(int i=0; i<temp.size(); i++)
         {
            int tr = temp.get(i).getRow();
            int tc = temp.get(i).getCol();
            double distToTemp = Display.wrapDistance(tr, tc, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            if(distToLoc < distToTemp)
            {
               temp.add(i,loc);
               added = true;
               break;
            }
         }
         if(!added)
         {
            temp.add(loc);
         }
      }
      if(temp.size() > 0)
      {
         return temp.get(0);
      }
      return null;
   }

//picks the closest location from locations that is not marked on your map
   public static Location closeLocation(ArrayList<Location> locations)
   {
      if(locations == null || locations.size() == 0)
         return null;
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         if((CultimaPanel.map.get(0))[r][c] > 0)    //not visited yet
         {
            double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            boolean added = false;
            for(int i=0; i<temp.size(); i++)
            {
               int tr = temp.get(i).getRow();
               int tc = temp.get(i).getCol();
               double distToTemp = Display.wrapDistance(tr, tc, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
               if(distToLoc < distToTemp)
               {
                  temp.add(i,loc);
                  added = true;
                  break;
               }
            }
            if(!added)
            {
               temp.add(loc);
            }
         }
      }
      if(temp.size() > 0)
      {
         return temp.get(0);
      }
      return null;
   }

//picks the closest location from locations - if it is not built, it will be built
   public static Location closeLocationAndBuild(ArrayList<Location> locations, String[] locTypes)
   {
      if(locations == null || locations.size() == 0)
         return null;   
      ArrayList<Location>temp = new ArrayList<Location>();
      for(Location loc:locations)
      {
         int r=loc.getRow();
         int c=loc.getCol();
         String currTerrName = loc.getTerrain().getName().toLowerCase();
         if(r==CultimaPanel.player.getWorldRow() && c==CultimaPanel.player.getWorldCol())
            continue;
         boolean found = false;    
         for(String locType:locTypes)    
            if(currTerrName.contains(locType))
               found = true;
         if(!found)
            continue;
         double distToLoc = Display.wrapDistance(r, c, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
         boolean added = false;
         for(int i=0; i<temp.size(); i++)
         {
            int tr = temp.get(i).getRow();
            int tc = temp.get(i).getCol();
            double distToTemp = Display.wrapDistance(tr, tc, CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            if(distToLoc < distToTemp)
            {
               temp.add(i,loc);
               added = true;
               break;
            }
         }
         if(!added)
         {
            temp.add(loc);
         }
      }
      if(temp.size() > 0)
      {
         Location closest = temp.get(0);
         if(closest!=null && closest.getMapIndex() == -1)
         {  //procedurally generate the world at this mapIndex
            String locType = closest.getTerrain().getName().toLowerCase();
            closest = LocationBuilder.constructAdventureAt(closest, locType);
         }
         return closest;
      }
      return null;
   }

 //returns true if the location has a coffin (for SLAY_VAMPIRE mission)
   public static boolean hasCoffin(Location loc)
   {
      byte[][]cave = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         cave=CultimaPanel.map.get(loc.getMapIndex());
      else
         return false;   
      Terrain coffin = TerrainBuilder.getTerrainWithName("INR_$Coffin_bed");
      for(int r=1; r<cave.length-1; r++)
         for(int c=1; c<cave[r].length-1; c++)
            if(cave[r][c] == coffin.getValue())
               return true;
      return false;
   }
   
    //returns true if the current location has a coffin (for BURN_COFFINS mission)
   public static boolean hasCoffin()
   {
      byte[][]cave = CultimaPanel.map.get(CultimaPanel.player.getMapIndex()); 
      Terrain coffin = TerrainBuilder.getTerrainWithName("INR_$Coffin_bed");
      for(int r=1; r<cave.length-1; r++)
         for(int c=1; c<cave[r].length-1; c++)
            if(cave[r][c] == coffin.getValue())
               return true;
      return false;
   }
   
   //(for FIND_LEDGER mission)
   public static Point addBooktoLocation(int mi)
   {
      byte[][]cave = CultimaPanel.map.get(mi);
      if(mi==0)
         return null;
      Terrain book = TerrainBuilder.getTerrainWithName("ITM_D_$Book");
      ArrayList<Point> floorSpots = new ArrayList<Point>();
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            String spot = (CultimaPanel.allTerrain.get(Math.abs(cave[r][c]))).getName().toLowerCase();
            if(spot.contains("book"))
               return new Point(r, c);     //already a book there
            if((spot.contains("floor") || spot.contains("grass")) && !spot.contains("trap"))
               floorSpots.add(new Point(r, c));
         }
      if(floorSpots.size() > 0)
      {
         Point randSpot = floorSpots.get((int)(Math.random()*floorSpots.size()));
         int row = (int)(randSpot.getX());
         int col = (int)(randSpot.getY());
         cave[row][col] = book.getValue();
         return new Point(row, col);
      }
      return null;
   }

   
    //returns true if the location has a shoppe sign (for DESTROY_SIGN mission)
   public static boolean hasSigns(Location loc)
   {
      byte[][]cave = null;
      if(loc.getMapIndex() >= 0 && loc.getMapIndex() < CultimaPanel.map.size())
         cave=CultimaPanel.map.get(loc.getMapIndex());
      else
         return false;   
      for(int r=1; r<cave.length-1; r++)
         for(int c=1; c<cave[r].length-1; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
            if(current.contains("sign"))
               return true;
         }
      return false;
   }
   
    //returns true if our current location has a shoppe sign (for DESTROY_SIGN mission)
   public static boolean hasSigns()
   {
      byte[][]cave = CultimaPanel.map.get(CultimaPanel.player.getMapIndex());  
      for(int r=1; r<cave.length-1; r++)
         for(int c=1; c<cave[r].length-1; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
            if(current.contains("sign"))
               return true;
         }
      return false;
   }

//create a path between two close habitable places
   public static void makePaths(byte[][]world)
   {
      int minDist = CultimaPanel.SCREEN_SIZE * 2;     //minimum distance needed between towns to make a path
      for(int i=1; i<CultimaPanel.allLocations.size(); i++)
      {
         Location loc = CultimaPanel.allLocations.get(i);
         if(loc.getName().contains("City") || loc.getName().contains("Fortress") || loc.getName().contains("Village") || loc.getName().contains("Port")
         || loc.getName().contains("castle") || (loc.getName().contains("Tower") && Math.random() < 0.75) 
         || (loc.getName().contains("Temple") && Math.random() < 0.5)  || (loc.getName().contains("Mine") && Math.random() < 0.25)) 
         {
            if(loc.getName().contains("East") || loc.getName().contains("West"))
               continue;
            for(int j=1; j<CultimaPanel.allLocations.size(); j++)
            {
               Location target = CultimaPanel.allLocations.get(j);   
               if(target.getName().contains("City") || target.getName().contains("Fortress") || target.getName().contains("Village") || loc.getName().contains("Port")
               || target.getName().contains("castle") || (target.getName().contains("Tower") && Math.random() < 0.75) 
               || (target.getName().contains("Temple") && Math.random() < 0.5)  || (target.getName().contains("Mine") && Math.random() < 0.25)) 
               {
               //if(target.getName().contains("East") || target.getName().contains("West") || (i==j))
                  //continue;
                  double distance = Display.findDistance(loc.getRow(), loc.getCol(), target.getRow(), target.getCol());
               
                  boolean skip = false;
                  int numMoves = 0;
                  if(distance < minDist && distance > 1)
                  {
                  //check to see if a path between the two locations doesn't contain _I_, and put down dray-grass on the path if not
                     ArrayList<Point>path = new ArrayList<Point>();
                     int r = loc.getRow();
                     int c = loc.getCol();
                     while(Display.findDistance(r, c, target.getRow(), target.getCol()) > 1)
                     { //which direction we should move
                        byte dir = getDirectionTowards(r, c, target.getRow(), target.getCol());
                     
                     //coordinates we might move into
                        int up = CultimaPanel.equalizeWorldRow(r-1);
                        int down = CultimaPanel.equalizeWorldRow(r+1);
                        int left = CultimaPanel.equalizeWorldCol(c-1);
                        int right = CultimaPanel.equalizeWorldCol(c+1);
                     //Terrain we might move into
                        Terrain terrNorth = (CultimaPanel.allTerrain.get(Math.abs(world[up][c])));
                        Terrain terrNorthEast = (CultimaPanel.allTerrain.get(Math.abs(world[up][right])));
                        Terrain terrEast = (CultimaPanel.allTerrain.get(Math.abs(world[r][right])));
                        Terrain terrSouthEast = (CultimaPanel.allTerrain.get(Math.abs(world[down][right])));
                        Terrain terrSouth = (CultimaPanel.allTerrain.get(Math.abs(world[down][c])));
                        Terrain terrSouthWest = (CultimaPanel.allTerrain.get(Math.abs(world[down][left])));
                        Terrain terrWest = (CultimaPanel.allTerrain.get(Math.abs(world[r][left])));
                        Terrain terrNorthWest = (CultimaPanel.allTerrain.get(Math.abs(world[up][left])));
                     //see if the Terrain we might try to move into is blocked with Impassable or Very Slow
                     
                        boolean blockedNorth = (terrNorth.getName().contains("_I_") || terrNorth.getName().contains("_V_"));
                        boolean blockedNorthEast = (terrNorthEast.getName().contains("_I_") || terrNorthEast.getName().contains("_V_"));
                        boolean blockedEast = (terrEast.getName().contains("_I_") || terrEast.getName().contains("_V_"));
                        boolean blockedSouthEast = (terrSouthEast.getName().contains("_I_") || terrSouthEast.getName().contains("_V_"));
                        boolean blockedSouth = (terrSouth.getName().contains("_I_") || terrSouth.getName().contains("_V_"));
                        boolean blockedSouthWest = (terrSouthWest.getName().contains("_I_") || terrSouthWest.getName().contains("_V_"));
                        boolean blockedWest = (terrWest.getName().contains("_I_") || terrWest.getName().contains("_V_"));
                        boolean blockedNorthWest = (terrNorthWest.getName().contains("_I_") || terrNorthWest.getName().contains("_V_"));
                     
                        if(dir==SOUTH)
                        {
                           if(blockedSouth && blockedSouthEast && blockedSouthWest)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedSouth)
                              r = CultimaPanel.equalizeWorldRow(r+1);
                           else if(!blockedSouthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedSouthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                        }
                        else if(dir==NORTH)
                        {
                           if(blockedNorth && blockedNorthEast && blockedNorthWest)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedNorth)
                              r = CultimaPanel.equalizeWorldRow(r-1);
                           else if(!blockedNorthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedNorthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                        }
                        else if(dir==EAST)
                        {
                           if(blockedEast && blockedNorthEast && blockedSouthEast)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedEast)
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           else if(!blockedNorthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedSouthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                        }
                        else if(dir==WEST)
                        {
                           if(blockedWest && blockedNorthWest && blockedSouthWest)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedWest)
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           else if(!blockedNorthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                           else if(!blockedSouthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                        }
                        else if(dir==NORTHWEST)
                        {
                           if(blockedWest && blockedNorthWest && blockedNorth)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedNorthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                           else if(!blockedWest)
                           {
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                           else if(!blockedNorth)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                           }
                        }
                        else if(dir==SOUTHWEST)
                        {
                           if(blockedWest && blockedSouthWest && blockedSouth)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedSouthWest)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                           else if(!blockedWest)
                           {
                              c = CultimaPanel.equalizeWorldCol(c-1);
                           }
                           else if(!blockedSouth)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                           }
                        }
                        else if(dir==NORTHEAST)
                        {
                           if(blockedEast && blockedNorthEast && blockedNorth)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedNorthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedEast)
                           {
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedNorth)
                           {
                              r = CultimaPanel.equalizeWorldRow(r-1);
                           }
                        }
                        else if(dir==SOUTHEAST)
                        {
                           if(blockedEast && blockedSouthEast && blockedSouth)
                           {
                              skip = true;
                              break;
                           }
                           if(!blockedSouthEast)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedEast)
                           {
                              c = CultimaPanel.equalizeWorldCol(c+1);
                           }
                           else if(!blockedSouth)
                           {
                              r = CultimaPanel.equalizeWorldRow(r+1);
                           }
                        }
                        path.add(new Point(r, c));
                        numMoves ++;
                        if(numMoves > minDist*2)
                        {
                           skip = true;
                           break;
                        }
                     }
                     if(!skip)    //we didn't run into Impassable terrain, so mark the path
                     {
                        for(Point p : path)
                        {
                           int row = (int)(p.getX());
                           int col = (int)(p.getY());
                           Terrain current = (CultimaPanel.allTerrain.get(Math.abs(world[row][col])));
                           if(current.getName().contains("LOC"))
                              break;
                           world[row][col] = getTerrainWithName("TER_$Dry_grass").getValue();
                        }
                     }
                  }
               }
            }
         }   
      }
   
   }

//returns the direction that would get (r1,c1) closer to (r2,c2)
   public static byte getDirectionTowards(int r1, int c1, int r2, int c2)
   {
      double distWrap = Display.wrapDistance(r1, c1, r2, c2);
      double [] distances = new double[8];
      distances[NORTH] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1-1), c1, r2, c2);  
      distances[NORTHEAST] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1-1), CultimaPanel.equalizeWorldCol(c1+1), r2, c2);   
      distances[EAST] = Display.wrapDistance(r1, CultimaPanel.equalizeWorldCol(c1+1), r2, c2);  
      distances[SOUTHEAST] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1+1), CultimaPanel.equalizeWorldCol(c1+1), r2, c2);    
      distances[SOUTH] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1+1), c1, r2, c2);  
      distances[SOUTHWEST] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1+1), CultimaPanel.equalizeWorldCol(c1-1), r2, c2);     
      distances[WEST] = Display.wrapDistance(r1, CultimaPanel.equalizeWorldCol(c1-1), r2, c2);   
      distances[NORTHWEST] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1-1), CultimaPanel.equalizeWorldCol(c1-1), r2, c2); 
      double minDist = Double.MAX_VALUE;
      byte minIndex = NORTH;
      for(byte i=0; i<distances.length; i++)
      {
         if(distances[i] < minDist)
         {
            minDist = distances[i];
            minIndex = i;
         }
      }
      return minIndex;
   }

//returns the direction that would get (r1,c1) closer to (r2,c2)
   public static byte getDirectionTowardsSimple(int r1, int c1, int r2, int c2)
   {
      double distWrap = Display.wrapDistance(r1, c1, r2, c2);
      double [] distances = new double[4];
      distances[LocationBuilder.NORTH] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1-1), c1, r2, c2);  
      distances[LocationBuilder.EAST] = Display.wrapDistance(r1, CultimaPanel.equalizeWorldCol(c1+1), r2, c2);  
      distances[LocationBuilder.SOUTH] = Display.wrapDistance(CultimaPanel.equalizeWorldRow(r1+1), c1, r2, c2);  
      distances[LocationBuilder.WEST] = Display.wrapDistance(r1, CultimaPanel.equalizeWorldCol(c1-1), r2, c2);   
      double minDist = Double.MAX_VALUE;
      byte minIndex = NORTH;
      for(byte i=0; i<distances.length; i++)
      {
         if(distances[i] < minDist)
         {
            minDist = distances[i];
            minIndex = i;
         }
      }
      return minIndex;
   }

//mark a path from the player's world position to the location with townName
   public static int markMapPath(String townName)
   {
      townName = townName.toLowerCase();
      Location target = null;
      for(int i=1; i<CultimaPanel.allLocations.size(); i++)
      {
         Location loc = CultimaPanel.allLocations.get(i);
         String locName = loc.getName().toLowerCase();
         if(locName.contains(townName))
         {
            target = loc;
            break;
         }
      }   
      if(target==null)
         return -1;
      if((CultimaPanel.map.get(0))[target.getRow()][target.getCol()] < 0)     //already marked on the map
         return 0;
   
      Sound.mapFound();
   
      for(int r=target.getRow()-2; r<=target.getRow()+2; r++)                 
         for(int c=target.getCol()-2; c<=target.getCol()+2; c++)
         {
            int markRow = CultimaPanel.equalizeWorldRow(r);
            int markCol = CultimaPanel.equalizeWorldRow(c);
            if((CultimaPanel.map.get(0))[markRow][markCol] > 0)
               (CultimaPanel.map.get(0))[markRow][markCol] *= -1; 
         }
   
      ArrayList<Point>path = new ArrayList<Point>();
      int r1 = CultimaPanel.player.getWorldRow();
      int c1 = CultimaPanel.player.getWorldCol();
      int r2 = target.getRow();
      int c2 = target.getCol();
      while(Display.wrapDistance(r1, c1, r2, c2) > 1)
      {
         byte minIndex = getDirectionTowards(r1, c1, r2, c2);
         if(minIndex == SOUTH)
            r1 = CultimaPanel.equalizeWorldRow(r1+1);
         else if(minIndex == NORTH)
            r1 = CultimaPanel.equalizeWorldRow(r1-1);
         else if(minIndex == EAST)
            c1 = CultimaPanel.equalizeWorldCol(c1+1);
         else if(minIndex == WEST)
            c1 = CultimaPanel.equalizeWorldCol(c1-1);
         else if(minIndex == NORTHWEST)
         {
            r1 = CultimaPanel.equalizeWorldRow(r1-1);
            c1 = CultimaPanel.equalizeWorldCol(c1-1);
         }
         else if(minIndex == SOUTHWEST)
         {
            r1 = CultimaPanel.equalizeWorldRow(r1+1);
            c1 = CultimaPanel.equalizeWorldCol(c1-1);
         }
         else if(minIndex == NORTHEAST)
         {
            r1 = CultimaPanel.equalizeWorldRow(r1-1);
            c1 = CultimaPanel.equalizeWorldCol(c1+1);
         }
         else if(minIndex == SOUTHEAST)
         {
            r1 = CultimaPanel.equalizeWorldRow(r1+1);
            c1 = CultimaPanel.equalizeWorldCol(c1+1);
         }  
         path.add(new Point(r1, c1));
      }
      for(Point p : path)
      {
         int row = (int)(p.getX());
         int col = (int)(p.getY());
         if((CultimaPanel.map.get(0))[row][col] > 0)
            (CultimaPanel.map.get(0))[row][col] *= -1; 
      }
      return 1;
   }

//reveals a random sized portion of the world map
   public static void markMapArea()
   {
      Sound.mapFound();
      int mapSize = ((int)(Math.random() * 4)+1) * CultimaPanel.SCREEN_SIZE;
      int ulRow = (int)(Math.random() * (CultimaPanel.map.get(0).length - mapSize));
      int ulCol = (int)(Math.random() * (CultimaPanel.map.get(0)[0].length - mapSize));
      for(int mapR=ulRow; mapR<=ulRow+mapSize && mapR<CultimaPanel.map.get(0).length; mapR++)
         for(int mapC=ulCol; mapC<=ulCol+mapSize && mapC<CultimaPanel.map.get(0)[0].length; mapC++)
            if(mapR>=0 && mapC>=0 && mapR<(CultimaPanel.map.get(0)).length && mapC < (CultimaPanel.map.get(0))[0].length)
               if((CultimaPanel.map.get(0))[mapR][mapC] > 0)          //visited the location - a negative byte value means that we visited it
                  (CultimaPanel.map.get(0))[mapR][mapC] *= -1;
   }

 //reveals a random sized portion of the world map with center r, c and of dimensions mapSize
   public static void markMapArea(int r, int c, int mapSize)
   {
      Sound.mapFound();
      for(int mapR=r-mapSize/2; mapR<=r+mapSize/2; mapR++)
         for(int mapC=c-mapSize/2; mapC<=c+mapSize; mapC++)
         {
            int row = CultimaPanel.equalizeWorldRow(mapR);
            int col = CultimaPanel.equalizeWorldCol(mapC);
            if((CultimaPanel.map.get(0))[row][col] > 0)          //visited the location - a negative byte value means that we visited it
               (CultimaPanel.map.get(0))[row][col] *= -1;
         }
   }

   public static void markMapArea(int r, int c)
   {
      Sound.mapFound();
      int mapSize = ((int)(Math.random() * 4)+1) * CultimaPanel.SCREEN_SIZE;
      for(int mapR=r-mapSize/2; mapR<=r+mapSize/2; mapR++)
         for(int mapC=c-mapSize/2; mapC<=c+mapSize; mapC++)
         {
            int row = CultimaPanel.equalizeWorldRow(mapR);
            int col = CultimaPanel.equalizeWorldCol(mapC);
            if((CultimaPanel.map.get(0))[row][col] > 0)          //visited the location - a negative byte value means that we visited it
               (CultimaPanel.map.get(0))[row][col] *= -1;
         }
   }

//reveals the entire map for a location at mapIndex        
   public static void markMapArea(int mapIndex)
   {
      Sound.mapFound();      
      if(mapIndex >0 && mapIndex < CultimaPanel.map.size())
         for(int mapR=0; mapR<CultimaPanel.map.get(mapIndex).length; mapR++)
            for(int mapC=0; mapC<CultimaPanel.map.get(mapIndex)[0].length; mapC++)
               if(mapR>=0 && mapC>=0 && mapR<(CultimaPanel.map.get(mapIndex)).length && mapC < (CultimaPanel.map.get(mapIndex))[0].length)
                  if((CultimaPanel.map.get(mapIndex))[mapR][mapC] > 0)          //visited the location - a negative byte value means that we visited it
                     (CultimaPanel.map.get(mapIndex))[mapR][mapC] *= -1;
   }         
 
  //reveals a portion of the map for a location at mapIndex        
   public static void markMapArea(int mapIndex, int ulRow, int ulCol, int numRows, int numCols)
   {
      Sound.mapFound();      
      if(mapIndex >0 && mapIndex < CultimaPanel.map.size() && ulRow>=0 && ulCol >= 0)
         for(int mapR=ulRow; mapR<CultimaPanel.map.get(mapIndex).length && mapR<=ulRow+numRows; mapR++)
            for(int mapC=ulCol; mapC<CultimaPanel.map.get(mapIndex)[0].length && mapC<=ulCol+numCols; mapC++)
               if(mapR>=0 && mapC>=0 && mapR<(CultimaPanel.map.get(mapIndex)).length && mapC < (CultimaPanel.map.get(mapIndex))[0].length)
                  if((CultimaPanel.map.get(mapIndex))[mapR][mapC] > 0)          //visited the location - a negative byte value means that we visited it
                     (CultimaPanel.map.get(mapIndex))[mapR][mapC] *= -1;
   }
        
   public static void show(byte[][]world)
   {
      System.out.println();
      for(int r=0; r<world.length; r++)
      {
         for(int c=0; c<world[0].length; c++)
         {
            if(world[r][c] == 0)
               System.out.print("--- ");
            else
               System.out.print(CultimaPanel.allTerrain.get(Math.abs(world[r][c])).getName()+ " ");
         }
         System.out.println();
      }
   }
}