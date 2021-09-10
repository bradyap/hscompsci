import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Terrain extends AnimatedEntity
{
   private HashMap<String, Integer> neighbors;  //neighbor Terrain name mapped to the percentage that the neighbor will be of that type
   private String description;                  //terrain description
   private int mapIndex;                        //to which map in the List of maps does this Terrain piece live?
   private byte value;                          //value to be stored in a binary file - unique for each different type of Terrain (negative for visited, positive for not)
   private String extension;                    //file type extension for this terrain piece (.jpg or .GIF with transparency)

   //ARGS:  name, fileNames collection, neighbors collection, description, map index, unique byte value
   public Terrain(String name, ArrayList<String> fileNames, HashMap<String, Integer> nei, String d,  int mi, byte v)
   {
      super(name, fileNames);
      extension = "";
      if(fileNames!=null && fileNames.size() > 0)
      {
         String firstFile = fileNames.get(0).toUpperCase();
         int pos = firstFile.indexOf(".");
         if(pos>=0)
            extension = firstFile.substring(pos+1);
      }
      neighbors = nei;
      description = d;
      mapIndex = mi;
      value = v;
   }
   
   public Terrain clone()
   {
      Terrain ans = new Terrain(this.getName(), this.getFileNames(), this.neighbors, this.description, this.mapIndex, this.value);
      return ans;
   }
         
   public HashMap<String, Integer> getNeighbors()
   {
      return neighbors;
   }
   
   public void setNeighbors(HashMap<String, Integer> nei)
   {
      neighbors = nei;
   }
   
   public void addNeighbor(String n, Integer i)
   {
      neighbors.put(n, i);
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public void setDescription(String d)
   {
      description = d;
   }
   
   public String getFileExtension()
   {
      return extension;
   }
         
   public int getMapIndex()
   {
      return mapIndex;
   }
   
   public void setMapIndex(int mi)
   {
      mapIndex = mi;
   }
      
   public byte getValue()
   {
      return value;
   }
   
   public void setValue(byte v)
   {
      value = v;
   }
   
   public String toString()
   {
      return getName() + ":" + mapIndex + ":" + value;
   }
   
   
   /*
   **     Terrain Information can not have more than 127 elements
   **     TER_ Terrain
   **     LOC_ Location
   **     INR_ Indoor
   **     I_   Impassalbe     
   **     V_   Very slow movement
   **     S_   Slow movement
   **     B_   Block View
   **     L_   Cast a light (followed by int of light radius)
   **     D_   Destroyable
   **     T_   Trap
   **     $Description
   */
   //post: creates and returns an ArrayList of all the Terrain tiles used to build the world
   public static ArrayList<Terrain> loadTerrain()
   {
      ArrayList<Terrain> terrain = new ArrayList<Terrain>();
      String name="";
      ArrayList<String> fileNames = null;
      HashMap<String, Integer> neighbors = null;
      String description = "";
      byte value = 1;
      int pos = -1;
      
      //ARGS:  name, fileNames collection, neighbors collection, description, map index, unique byte value
      terrain.add(new Terrain("TEMP", null, null, "",  0, (byte)(0)));  //temporary value so that index 0 is filled, and first index is 1
   
      //***********WORLD TERRAIN********************************************
      name = "TER_I_B_$Mountains";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/mountain1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_I_B_$Mountains",60);
      neighbors.put("TER_V_B_$Mountain",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_V_B_$Mountain";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/mountain2.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_S_$Hills",40);
      neighbors.put("TER_V_B_$Mountain",60);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_S_$Hills";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/hills1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_S_$Hills",60);
      neighbors.put("TER_$High_grassland",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$High_grassland";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$High_grassland",60);
      neighbors.put("TER_$Grassland",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",56);
      neighbors.put("TER_$Grassland_Red_Flowers",2);
      neighbors.put("TER_$Grassland_Blue_Flowers",2);
      neighbors.put("TER_$Grassland_Yellow_Flowers",2);
      neighbors.put("TER_$Grassland_Violet_Flowers",2);
      neighbors.put("TER_$Grassland_Green_Flowers",1);
      neighbors.put("TER_$Forest",35);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland_Red_Flowers";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2red.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland_Blue_Flowers";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2blue.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland_Yellow_Flowers";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2yellow.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland_Violet_Flowers";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2violet.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Grassland_Green_Flowers";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass2green.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "TER_$Dry_grass";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/grass3.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Dry_grass",60);
      neighbors.put("TER_S_$Sand",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Sand";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/sand1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Sand",60);
      neighbors.put("TER_$Dry_grass",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_S_B_$Dense_forest";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/forest1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_S_B_$Dense_forest",60);
      neighbors.put("TER_$Forest",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Forest";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/forest2.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Forest",60);
      neighbors.put("TER_$High_grassland",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_S_$Bog";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/bog1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_S_$Bog",60);
      neighbors.put("TER_V_$Shallow_water",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_V_$Shallow_water";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/water/water2.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_$Shallow_water",70);
      neighbors.put("TER_I_$Deep_water",30);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_I_$Deep_water";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/water/waterS1.jpg");
      fileNames.add("images/terrain/water/waterS2.jpg");
      fileNames.add("images/terrain/water/waterS3.jpg");
      fileNames.add("images/terrain/water/waterS4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_I_$Deep_water",50);
      neighbors.put("TER_I_$Rapids",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_I_$Rapid_water";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/water/waterR1.jpg");
      fileNames.add("images/terrain/water/waterR2.jpg");
      fileNames.add("images/terrain/water/waterR3.jpg");
      fileNames.add("images/terrain/water/waterR4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_I_$Deep_water",50);
      neighbors.put("TER_I_$Rapids",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_I_L_3_$Lava";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/magma/magma1.jpg");
      fileNames.add("images/terrain/magma/magma2.jpg");
      fileNames.add("images/terrain/magma/magma3.jpg");
      fileNames.add("images/terrain/magma/magma4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Dead_forest",60);
      neighbors.put("TER_I_L_3_$Lava",30);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "TER_$Dead_forest";
      fileNames = new ArrayList<String>();
      fileNames.add("images/terrain/dead1.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Dead_forest",60);
      neighbors.put("TER_$Dry_grass",40);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      //********************LOCATIONS***************************
      name = "LOC_L_2_$Large_castle";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/castle1.GIF");
      fileNames.add("images/locations/castle2.GIF");
      fileNames.add("images/locations/castle3.GIF");
      fileNames.add("images/locations/castle4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_2_$Small_castle";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/fort1.GIF");
      fileNames.add("images/locations/fort2.GIF");
      fileNames.add("images/locations/fort3.GIF");
      fileNames.add("images/locations/fort4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_2_$Tower";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/tower1.GIF");
      fileNames.add("images/locations/tower2.GIF");
      fileNames.add("images/locations/tower3.GIF");
      fileNames.add("images/locations/tower4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",10);
      neighbors.put("TER_$High_grassland",10);
      neighbors.put("TER_S_$Hills",10);
      neighbors.put("TER_$Dry_grass",10);
      neighbors.put("TER_$Dead_forest",10);
      neighbors.put("TER_S_B_$Dense_forest",10);
      neighbors.put("TER_$Forest",10);
      neighbors.put("TER_S_$Sand",10);
      neighbors.put("TER_S_$Bog",10);
      neighbors.put("TER_$Dead_forest",10);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_2_$Temple";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/temple.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",10);
      neighbors.put("TER_$High_grassland",10);
      neighbors.put("TER_S_$Hills",10);
      neighbors.put("TER_$Dry_grass",10);
      neighbors.put("TER_$Dead_forest",10);
      neighbors.put("TER_S_B_$Dense_forest",10);
      neighbors.put("TER_$Forest",10);
      neighbors.put("TER_S_$Sand",10);
      neighbors.put("TER_S_$Bog",10);
      neighbors.put("TER_$Dead_forest",10);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_3_$City_West";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/cityL.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_3_$City_Center";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/cityM1.GIF");
      fileNames.add("images/locations/cityM2.GIF");
      fileNames.add("images/locations/cityM3.GIF");
      fileNames.add("images/locations/cityM4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_3_$City_East";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/cityR.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_1_$Arena";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/arena.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_3_$Fortress_West";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/fortL.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_3_$Fortress_Center";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/fortM.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
             
      name = "LOC_L_3_$Fortress_East";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/fortR.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "LOC_L_1_$Domicile";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/hut.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",10);
      neighbors.put("TER_$High_grassland",10);
      neighbors.put("TER_S_$Hills",10);
      neighbors.put("TER_$Dry_grass",10);
      neighbors.put("TER_$Dead_forest",10);
      neighbors.put("TER_S_B_$Dense_forest",10);
      neighbors.put("TER_$Forest",10);
      neighbors.put("TER_S_$Sand",10);
      neighbors.put("TER_S_$Bog",10);
      neighbors.put("TER_$Dead_forest",10);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "LOC_L_1_$Village";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/village.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",40);
      neighbors.put("TER_$High_grassland",20);
      neighbors.put("TER_S_$Hills",15);
      neighbors.put("TER_$Dry_grass",15);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_2_$Port";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/portTown.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",50);
      neighbors.put("TER_$High_grassland",25);
      neighbors.put("TER_S_$Bog",25);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "LOC_$Cave";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/mountainCave.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",40);
      neighbors.put("TER_S_$Hills",40);
      neighbors.put("TER_I_B_$Mountains",20);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_1_$Cave_insanity";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/insanityCave1.GIF");
      fileNames.add("images/locations/insanityCave2.GIF");
      fileNames.add("images/locations/insanityCave3.GIF");
      fileNames.add("images/locations/insanityCave4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",40);
      neighbors.put("TER_S_$Hills",40);
      neighbors.put("TER_I_B_$Mountains",20);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_L_1_$Mountain_Lair";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/mountainLair.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",60);
      neighbors.put("TER_S_$Hills",20);
      neighbors.put("TER_I_B_$Mountains",20);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_$Mine";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/mountainMine.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",50);
      neighbors.put("TER_S_$Hills",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_$Dungeon_enterance";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/dungeonDoor.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",50);
      neighbors.put("TER_S_$Hills",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_$Dungeon_enterance_strange";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/portal1.jpg");
      fileNames.add("images/indoor/doors/portal2.jpg");
      fileNames.add("images/indoor/doors/portal3.jpg");
      fileNames.add("images/indoor/doors/portal4.jpg");
      fileNames.add("images/indoor/doors/portal5.jpg");
      fileNames.add("images/indoor/doors/portal6.jpg");
      fileNames.add("images/indoor/doors/portal7.jpg");
      fileNames.add("images/indoor/doors/portal8.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_V_B_$Mountain",50);
      neighbors.put("TER_S_$Hills",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "LOC_$Battlefield";
      fileNames = new ArrayList<String>();
      fileNames.add("images/locations/battlefield1.GIF");
      fileNames.add("images/locations/battlefield2.GIF");
      fileNames.add("images/locations/battlefield3.GIF");
      fileNames.add("images/locations/battlefield4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("TER_$Grassland",50);
      neighbors.put("TER_S_$Hills",50);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      //**********************INDOOR*WALLS**************************************
      name = "INR_I_B_$Blue_Brick";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/blueBrick.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Blue_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Blue_Brick_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/blueBrickSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Blue_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_L_1_$Blue_Brick_Window";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/blueBrickWindow.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Blue_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$Blue_Brick_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/blueBrickTorch1.jpg");
      fileNames.add("images/indoor/walls/blueBrickTorch2.jpg");
      fileNames.add("images/indoor/walls/blueBrickTorch3.jpg");
      fileNames.add("images/indoor/walls/blueBrickTorch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Blue_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Red_Brick";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redBrick.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Red_Brick_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redBrickSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
       
      name = "INR_I_L_1_$Red_Brick_Window";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redBrickWindow.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
       
      name = "INR_I_B_L_4_$Red_Brick_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redBrickTorch1.jpg");
      fileNames.add("images/indoor/walls/redBrickTorch2.jpg");
      fileNames.add("images/indoor/walls/redBrickTorch3.jpg");
      fileNames.add("images/indoor/walls/redBrickTorch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$White_Brick";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteBrick.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
              
      name = "INR_D_B_$White_Brick_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteBrickSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_L_1_$White_Brick_Window";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteBrickWindow.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$White_Brick_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteBrickTorch1.jpg");
      fileNames.add("images/indoor/walls/whiteBrickTorch2.jpg");
      fileNames.add("images/indoor/walls/whiteBrickTorch3.jpg");
      fileNames.add("images/indoor/walls/whiteBrickTorch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Brick",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Earth_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/earth1.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Earth_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Earth_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/earth1Secret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Earth_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$Earth_Wall_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/earth1Torch1.jpg");
      fileNames.add("images/indoor/walls/earth1Torch2.jpg");
      fileNames.add("images/indoor/walls/earth1Torch3.jpg");
      fileNames.add("images/indoor/walls/earth1Torch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Earth_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Earth_Wall_Gold";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/earth1Gold.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Earth_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Gray_Rock";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/grayRocks.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Gray_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Gray_Rock";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/grayRocksSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Gray_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$Gray_Rock_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/grayRocksTorch1.jpg");
      fileNames.add("images/indoor/walls/grayRocksTorch2.jpg");
      fileNames.add("images/indoor/walls/grayRocksTorch3.jpg");
      fileNames.add("images/indoor/walls/grayRocksTorch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Gray_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Gray_Rock_Gold";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/grayRocksGold.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Gray_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "INR_I_B_$White_Rock";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteRocks.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "INR_D_B_$White_Rock";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteRocksSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$White_Rock_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteRocksTorch1.jpg");
      fileNames.add("images/indoor/walls/whiteRocksTorch2.jpg");
      fileNames.add("images/indoor/walls/whiteRocksTorch3.jpg");
      fileNames.add("images/indoor/walls/whiteRocksTorch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$White_Rock_Gold";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/whiteRocksGold.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$White_Rock",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Red_Tile_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redTile.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Tile_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Tile_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/redTileSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Tile_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_L_4_$Tile_Wall_Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/earth1Torch1.jpg");
      fileNames.add("images/indoor/walls/earth1Torch2.jpg");
      fileNames.add("images/indoor/walls/earth1Torch3.jpg");
      fileNames.add("images/indoor/walls/earth1Torch4.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Red_Tile_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Straw_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/strawWall.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Straw_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Straw_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/strawWallSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Straw_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Wood_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/woodPlank.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Wood_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_D_B_$Wood_Wall";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/woodPlankSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Wood_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_L_1_$Wood_Wall_Window";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/walls/woodPlankWindow.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_I_B_$Wood_Wall",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      //***************INDOOR*FLOORS****************************
      name = "INR_D_L_5_$Torch";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/torch1.GIF");
      fileNames.add("images/indoor/torch2.GIF");
      fileNames.add("images/indoor/torch3.GIF");
      fileNames.add("images/indoor/torch4.GIF");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Black_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/black.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "INR_D_T_$Black_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blackTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++)); 
      
      name = "INR_$Blue_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blue.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "INR_D_T_$Blue_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blueTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++)); 
      
      name = "INR_$Red_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/red.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
     
      name = "INR_D_T_$Red_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/redTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",34);
      neighbors.put("INR_$Blue_floor",33);
      neighbors.put("INR_$Red_floor",33);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++)); 
      
      name = "INR_$Black_Stone_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blackStone.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_Stone_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_T_$Black_Stone_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blackStoneTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_Stone_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Blue_Brick_floor_inside";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blueBrickInside.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Blue_Brick_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Blue_Brick_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blueBrick.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Blue_Brick_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_D_T_$Blue_Brick_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/blueBrickSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Blue_Brick_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Red_Brick_floor_inside";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/redBrickInside.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Red_Brick_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Red_Brick_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/redBrick.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Red_Brick_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_T_$Red_Brick_floor_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/redBrickSecret.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Red_Brick_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_$Wood_Plank_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/woodPlankFloor.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Wood_Plank_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Wood_Plank_floor_inside";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/woodPlankFloorInside.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Wood_Plank_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_$Purple_floor_inside";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/purple.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Purple_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_$PurpleDirty_floor_inside";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/floors/purpleDirty.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Purple_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      
      //*******************INDOOR*DOORS*********************
      name = "INR_D_B_$Wood_door";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorWood.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_T_$Wood_door_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorWoodTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Wood_door_locked";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorWoodLocked.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_$Iron_door";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorIron.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_D_B_T_$Iron_door_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorIronTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Iron_door_locked";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/doorIronLocked.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_B_$Night_door_closed";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/nightDoorClosed.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Night_door_floor";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/doors/nightDoorOpen.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      //****************INDOOR*SIGNS************************
      name = "INR_I_L_3_$Sign_armory";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/signs/armory.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      name = "INR_I_L_3_$Sign_tavern";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/signs/tavern.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_L_3_$Sign_rations";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/signs/rations.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_I_L_3_$Sign_magic";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/signs/magic.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      //*********************INDOOR*FURNITURE*CHESTS*************************
      name = "INR_I_L_2_$Counter_lighted";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/furniture/counter.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "ITM_D_$Chest";
      fileNames = new ArrayList<String>();
      fileNames.add("images/items/chest.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "ITM_I_D_$Chest_locked";
      fileNames = new ArrayList<String>();
      fileNames.add("images/items/chestLocked.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "ITM_D_T_$Chest_trap";
      fileNames = new ArrayList<String>();
      fileNames.add("images/items/chestTrap.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "ITM_D_$Book";
      fileNames = new ArrayList<String>();
      fileNames.add("images/items/book2.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Black_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Bed";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/furniture/bed.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Wood_Plank_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_$Coffin_bed";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/furniture/coffin.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Wood_Plank_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_L_3_$Ship_helm";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/furniture/helm.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Red_floor",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
      
      name = "INR_L_2_$Wardrobe";
      fileNames = new ArrayList<String>();
      fileNames.add("images/indoor/furniture/wardrobe.jpg");
      neighbors = new HashMap<String, Integer>();
      neighbors.put("INR_$Purple_floor_inside",100);
      pos = name.indexOf("$");
      if(pos >= 0)
         description = name.substring(pos+1); 
      terrain.add(new Terrain(name,fileNames,neighbors,description,0,value++));
   
      return terrain;
   }
}