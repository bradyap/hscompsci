import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Journal
{
//chapter indexes
   public static final byte START_STORY = 0;    //Player's starting story
   public static final byte LOCATIONS = 1;
   public static final byte SHOPS = 2;
   public static final byte MONSTERS = 3;       //list of monster info player aquires
   public static final byte WEAPONS = 4;        //list of weapon info player aquires
   public static final byte SPELLS = 5;         //list of spell info player aquires
   public static final byte TOWNS_FOLK = 6;     //list of townsfolk info player aquires
   public static final byte AFFLICTIONS = 7; 
   public static final byte TRAINING = 8;       //list of advice for training dogs, horses and commendeering ships
   public static final byte RIDDLES = 9;        //list of riddles leaned
   public static final byte PUZZLES = 10;
   public static final byte MISSIONS = 11;
   public static final byte VISITED_LOCS = 12;   //list of name sorted locations that have been visited
   public static final byte STATS = 13;          //list of game stats
   public static final byte ACHIEVEMENTS = 14;
   public static final byte NUM_CHAPTERS = 15;

//topic breakdowns
  //SHOPS
   public static final byte ARMS = 0;
   public static final byte MAGIC = 1;
   public static final byte RATIONS = 2;
   public static final byte TAVERN = 3;
   public static final byte NUM_SHOPS = 4;
   //POTIONS   
   public static final byte STRENGTH = 0;
   public static final byte ALPHAMIND = 1;
   public static final byte SPEED = 2;
   public static final byte PROTECTION = 3;
   public static final byte FIRESKIN = 4;
   public static final byte INVISIBILITY = 5;
   //BOOK OR POTION
   public static final byte HEAL = 6;
   public static final byte CURE = 7;
   public static final byte FOCUS = 8;
   //BOOK SPELLS
   public static final byte FEAR = 9;
   public static final byte DISARM = 10;
   public static final byte LIGHT = 11;
   public static final byte CHARM = 12;
   public static final byte RESTORE = 13;
   public static final byte KNOWING = 14;
   public static final byte EAGLEEYE = 15;
   public static final byte FLIGHT = 16;
   public static final byte TEMPEST = 17;
   public static final byte RAISEEARTH = 18;
   public static final byte RAISEWATER = 19;
   public static final byte TIMESTOP = 20;
   public static final byte FIRESTORM = 21;
   public static final byte FLORETLADE = 22;
   public static final byte UNSEEN = 23;
   public static final byte TELEPORT = 24;
   public static final byte SHIFTWIND = 25;
   public static final byte RAISEDEAD = 26;
   public static final byte REPEL = 27;
   public static final byte FLAMEBLAST = 28;
   public static final byte TAMEANIMAL = 29;
   public static final byte ENCHANTSTONE = 30;
   public static final byte MAGICMIST = 31;

//STAFF SPELLS
   public static final byte STAFF = 32;
   public static final byte FIREBALL = 33;
   public static final byte ICESTRIKE = 34;
   public static final byte PHASEWALL = 35;
   public static final byte CURSE = 36;
   public static final byte POSSESS = 37;
   public static final byte LIGHTNING = 38;
   public static final byte DEATHTOUCH = 39;
   public static final byte FIRESHIELD = 40;
   public static final byte SUMMONVORTEX = 41;
   public static final byte ADVANCE = 42;
   public static final byte STONECAST = 43;
   public static final byte SPIDERSWEB = 44;
   public static final byte BLINDINGLIGHT = 45;
   public static final byte RAISESTONE = 46;
 //MAGIC ITEMS  
   public static final byte CLOAKS = 47;
   public static final byte ITEMS = 48;
   public static final byte SALEDATES = 49;
   public static final byte NUM_SPELLS = 50;
   //topic breakdown for training section
   public static final byte MAPPING = 0;
   public static final byte TRAPPING = 1;
   public static final byte HOME = 2;
   public static final byte DOG = 3;
   public static final byte HORSE = 4;
   public static final byte UNICORN = 5;
   public static final byte SHIP = 6;  
   public static final byte VAMPIRE = 7;
   public static final byte WEREWOLF = 8;
   public static final byte SWINE = 9;
   public static final byte JEM = 10;
   public static final byte NUM_TOPICS = 11;
   //topics for puzzles
   public static final byte TOWERS_PUZZLE = 0;
   public static final byte DOORS_PUZZLE = 1;
   public static final byte WELLS_PUZZLE = 2;
   public static final byte NUM_PUZZLES = 3;
   //topics for afflictions
   public static final byte BLESSED = 0;
   public static final byte CURSED = 1;
   public static final byte FIRE = 2;
   public static final byte FROZEN = 3;
   public static final byte POISONED = 4;
   public static final byte SULLIED = 5;
   public static final byte NUM_AFFLICTIONS = 6;
   //topics for locations
   public static final byte ARENA = 0;
   public static final byte BATTLEFIELD = 1;
   public static final byte CAPITAL = 2;
   public static final byte CASTLE = 3;
   public static final byte CITY = 4;
   public static final byte HUT = 5;  
   public static final byte CAVE = 6;
   public static final byte MINE = 7;
   public static final byte LAIR = 8;
   public static final byte PORT = 9;
   public static final byte TEMPLE = 10;
   public static final byte VILLAGE = 11;
   public static final byte NUM_LOCATIONS = 12;

   private static final ImageIcon potion = new ImageIcon("images/items/potion.Gif");
   private static final ImageIcon book = new ImageIcon("images/items/book.Gif");
   private static final ImageIcon bookAndPotion = new ImageIcon("images/items/bookAndPotion.Gif");
   private static final ImageIcon items = new ImageIcon("images/items/items.Gif");

   private static final ImageIcon armor = new ImageIcon("images/items/armor.Gif");
   private static final ImageIcon glove = new ImageIcon("images/items/glove.Gif");

   private static final ImageIcon [] shopSigns = {
   new ImageIcon("images/indoor/signs2/armory.Gif"),    /*0 - ironsmith*/
   new ImageIcon("images/indoor/signs2/magic.Gif"),     /*1 - mage's shop*/
   new ImageIcon("images/indoor/signs2/rations.Gif"),   /*2 - rations*/
   new ImageIcon("images/indoor/signs2/tavern.Gif")     /*3 - tavern*/
   };

   //Location types for locations ImageIcon array    
   private static final ImageIcon [] locations = {
   new ImageIcon("images/locations2/battlefield.Gif"),    /*0 - battlefield*/
   new ImageIcon("images/locations2/castle.Gif"),        /*1 - large_castle*/
   new ImageIcon("images/locations2/cityM.Gif"),         /*2 - city_center*/
   new ImageIcon("images/locations2/fort.Gif"),          /*3 - small_castle*/
   new ImageIcon("images/locations2/fortM.Gif"),         /*4 - fortress_center*/
   new ImageIcon("images/locations2/hut.Gif"),           /*5 - domicile*/
   new ImageIcon("images/locations2/insanityCave.Gif"),  /*6 - cave_insanity*/
   new ImageIcon("images/locations2/mountainCave.Gif"),  /*7 - cave*/
   new ImageIcon("images/locations2/mountainLair.Gif"),  /*8 - mountain_lair*/
   new ImageIcon("images/locations2/mountainMine.Gif"),  /*9 - mine*/
   new ImageIcon("images/locations2/temple.Gif"),        /*10 - temple*/
   new ImageIcon("images/locations2/tower.Gif"),         /*11 - tower*/
   new ImageIcon("images/locations2/village.Gif"),       /*12 - village*/
   new ImageIcon("images/locations2/dungeonDoor.Gif"),   /*13 - dungeon_enterance*/
   new ImageIcon("images/locations2/portTown.Gif"),       /*14 - port town*/
   new ImageIcon("images/locations2/capital.Gif"),         /*15 - capital city*/
   new ImageIcon("images/locations2/arena.Gif")         /*16 - arena*/
   };
      
//returns an Object array of journal entries
//each chapter can be a different type of container
//index 0 (START_STORY) is a String [] of each line from the characters starting story
//index 1 (MONSTERS) is an ArrayList<String> [],   where each array index is the monster's charIndex, 
//                                                 with an Arraylist of multiple sentences for each monster type

   public static Object[] buildJournal()
   {
      Object [] chapters = new Object[NUM_CHAPTERS];
   //START STORY
      chapters[START_STORY] = Story.getStory(CultimaPanel.player.getStartStoryIndex());
      //SHOPS   
      ArrayList<String> [] shops = new ArrayList[NUM_SHOPS];  
      for(int i=0; i < shops.length; i++)
         shops[i] = new ArrayList<String>(); 
      for(int i=0; i < NPC.shopInfo.length; i++)
      {
         String temp = NPC.shopInfo[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               String topic = parts[1].trim();
               byte shopIndex = 0;
               if(topic.equals("MAGIC"))
                  shopIndex = 1;
               else if(topic.equals("RATIONS"))
                  shopIndex = 2;
               else if(topic.equals("TAVERN"))
                  shopIndex = 3;
               if(shopIndex>=0 && shopIndex<shops.length)   
                  shops[shopIndex].add(info);
            }
         }
      }  
      chapters[SHOPS] = shops;
      //AFFLICTIONS   
      ArrayList<String> [] afflictions = new ArrayList[NUM_AFFLICTIONS];  
      for(int i=0; i < afflictions.length; i++)
         afflictions[i] = new ArrayList<String>(); 
      for(int i=0; i < NPC.afflictionInfo.length; i++)
      {
         String temp = NPC.afflictionInfo[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               String topic = parts[1].trim();
               byte afflictionIndex = 0;  //BLESSED
               if(topic.equals("CURSED"))
                  afflictionIndex = 1;
               else if(topic.equals("FIRE"))
                  afflictionIndex = 2;
               else if(topic.equals("FROZEN"))
                  afflictionIndex = 3;
               else if(topic.equals("POISONED"))
                  afflictionIndex = 4;
               else if(topic.equals("SULLIED"))
                  afflictionIndex = 5;
               if(afflictionIndex>=0 && afflictionIndex<afflictions.length)   
                  afflictions[afflictionIndex].add(info);
            }
         }
      }  
      chapters[AFFLICTIONS] = afflictions;
       //LOCATIONS   
      ArrayList<String> [] locations = new ArrayList[NUM_LOCATIONS];  
      for(int i=0; i < locations.length; i++)
         locations[i] = new ArrayList<String>(); 
      for(int i=0; i < NPC.locationInfo.length; i++)
      {
         String temp = NPC.locationInfo[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               String topic = parts[1].trim();
               byte locationIndex = 0;  //ARENA
               if(topic.equals("BATTLEFIELD"))
                  locationIndex = 1;
               else if(topic.equals("CAPITAL"))
                  locationIndex = 2;
               else if(topic.equals("CASTLE"))
                  locationIndex = 3;
               else if(topic.equals("CITY"))
                  locationIndex = 4;
               else if(topic.equals("HUT"))
                  locationIndex = 5;
               else if(topic.equals("CAVE"))
                  locationIndex = 6;
               else if(topic.equals("MINE"))
                  locationIndex = 7;
               else if(topic.equals("LAIR"))
                  locationIndex = 8;
               else if(topic.equals("PORT"))
                  locationIndex = 9;
               else if(topic.equals("TEMPLE"))
                  locationIndex = 10;
               else if(topic.equals("VILLAGE"))
                  locationIndex = 11;
            
               if(locationIndex>=0 && locationIndex<locations.length)   
                  locations[locationIndex].add(info);
            }
         }
      }  
      chapters[LOCATIONS] = locations;
   
   //MONSTER MANUAL   
      ArrayList<String> [] monsterInfo = new ArrayList[NPC.NUM_NPCS + 1];  //last element will be for general info
      for(int i=0; i < monsterInfo.length; i++)
         monsterInfo[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getMonsterInfo() && i < NPC.monsters.length; i++)
      {
         String temp = NPC.monsters[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               byte monsterIndex = NPC.monsterNameToIndex(parts[1].trim());
               if(parts[1].trim().equals("MISC"))                       //MISC is the flag that it is general monster info
                  monsterInfo[NPC.NUM_NPCS].add(info);
               else if(monsterIndex >=0 && monsterIndex<monsterInfo.length)
                  monsterInfo[monsterIndex].add(info);         //add to the index for that monster's charIndex
            }
         }
      }  
      chapters[MONSTERS] = monsterInfo;
   //ARMS AND ARMORMENTS
      ArrayList<String> [] weaponInfo = new ArrayList[Player.LUTE + 3];  //first element will be for armor, last 2 elements will be for gloves and general info
      for(int i=0; i < weaponInfo.length; i++)
         weaponInfo[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getArmsInfo() && i < NPC.armsInfo.length; i++)
      {
         String temp = NPC.armsInfo[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               byte armsIndex = Weapon.getWeaponIndexFromName(parts[1].trim());
               if(parts[1].trim().equals("GLOVE"))                       
                  weaponInfo[Player.LUTE+1].add(info);
               else if(parts[1].trim().equals("MISC"))                   //MISC is the flag that it is general monster info
                  weaponInfo[Player.LUTE+2].add(info);
               else if(parts[1].trim().equals("ARMOR"))                  //place armor info in NONE slot
                  weaponInfo[Player.NONE].add(info);   
               else if(armsIndex >=0 && armsIndex<weaponInfo.length)
                  weaponInfo[armsIndex].add(info);         //add to the index for that monster's charIndex
            }
         }
      }  
      chapters[WEAPONS] = weaponInfo;
   //SPELL INFORMATION
      ArrayList<String> [] spellInformation = new ArrayList[NUM_SPELLS + 1];  //last element will be for general info
      for(int i=0; i < spellInformation.length; i++)
         spellInformation[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getSpellIndex() && i < NPC.spellInfo.length; i++)
      {
         String temp = NPC.spellInfo[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               byte spellIndex = getSpellIndexFromName(parts[1].trim());
               if(parts[1].trim().equals("MISC"))                       //MISC is the flag that it is general monster info
                  spellInformation[NUM_SPELLS].add(info);
               else if(spellIndex >=0 && spellIndex<spellInformation.length)
                  spellInformation[spellIndex].add(info);         //add to the index for that monster's charIndex
            }
         }
      }
      chapters[SPELLS] = spellInformation;
   //TOWN NOTES  
      ArrayList<String> [] townInfo = new ArrayList[NPC.KING + 2];  //last element will be for general info
      for(int i=0; i < townInfo.length; i++)
         townInfo[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getTavernInfo() && i < NPC.tavernTown.length; i++)
      {
         String temp = NPC.tavernTown[i];
         if(temp.contains("~~"))                            //dialogue we want recorded in the journal ends with "~~"
         {
            String [] parts = temp.split("~~");
            if(parts.length == 2)
            {
               String info = parts[0].trim();
               byte npcIndex = NPC.monsterNameToIndex(parts[1].trim());
               if(parts[1].trim().equals("MISC"))                       //MISC is the flag that it is general monster info
                  townInfo[NPC.KING+1].add(info);
               else if(npcIndex >=NPC.BEGGER && npcIndex<townInfo.length && npcIndex<=NPC.KING)
                  townInfo[npcIndex].add(info);         //add to the index for that monster's charIndex
            }
         }
      }  
      chapters[TOWNS_FOLK] = townInfo; 
   //MISSIONS   
      ArrayList<String> missions = new ArrayList<String>();
      for(Mission miss: CultimaPanel.missionStack)
      {
         String temp = miss.getInfo().trim();
         if(miss.isFinished())
            temp = "~"+temp;
         missions.add(temp);
      }
      chapters[MISSIONS] = missions;
      
   //VISITED LOCATIONS
      float numLocs = -1;     //start at -1 because we don't want to count the TEMP location
      ArrayList<Location> visited = new ArrayList<Location>(); 
      for(Location loc : CultimaPanel.allLocations)
      {
         String locType = loc.getTeleporter().getLocType().toLowerCase();
         if(!locType.contains("dungeon")) //don't include dungeons because their coordinates are internal for temples/castles
         {   
            numLocs++;
            if(loc.getMapIndex() != -1) 
               visited.add(loc);
         }  
      }
      sortLocs(visited);
      chapters[VISITED_LOCS] = visited;
   //RIDDLES  
      ArrayList<String> riddles = new ArrayList<String>();
      for(int i=0; i < CultimaPanel.player.getRiddleInfo() && i < NPC.riddleQst.length; i++)
      {
         String temp = NPC.riddleQst[i][0];
         riddles.add(temp.trim());
      }
      chapters[RIDDLES] = riddles;
      
      //PUZZLES
      ArrayList<String> [] puzzleInfo = new ArrayList[NUM_PUZZLES];  
      for(int i=0; i < puzzleInfo.length; i++)
         puzzleInfo[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getTowersPuzzleInfo() && i < NPC.towersPuzzleInfo.length; i++)
         puzzleInfo[TOWERS_PUZZLE].add(NPC.towersPuzzleInfo[i]);
      for(int i=0; i < CultimaPanel.player.getDoorsPuzzleInfo() && i < NPC.doorsPuzzleInfo.length; i++)
         puzzleInfo[DOORS_PUZZLE].add(NPC.doorsPuzzleInfo[i]);
      for(int i=0; i < CultimaPanel.player.getWellsPuzzleInfo() && i < NPC.wellsPuzzleInfo.length; i++)
         puzzleInfo[WELLS_PUZZLE].add(NPC.wellsPuzzleInfo[i]);
      chapters[PUZZLES] = puzzleInfo;
   
   //TRAINING
      ArrayList<String> [] trainingInfo = new ArrayList[NUM_TOPICS];  
      for(int i=0; i < trainingInfo.length; i++)
         trainingInfo[i] = new ArrayList<String>(); 
      for(int i=0; i < CultimaPanel.player.getDogInfo() && i < NPC.dogInfo.length; i++)
         trainingInfo[DOG].add(NPC.dogInfo[i]);
      for(int i=0; i < CultimaPanel.player.getHorseInfo() && i < NPC.horseInfo.length/2; i++)
         trainingInfo[HORSE].add(NPC.horseInfo[i]);
      for(int i=NPC.horseInfo.length/2; i < CultimaPanel.player.getHorseInfo() && i < NPC.horseInfo.length; i++)
         trainingInfo[UNICORN].add(NPC.horseInfo[i]);
      for(int i=0; i < CultimaPanel.player.getShipInfo() && i < NPC.shipInfo.length; i++)
         trainingInfo[SHIP].add(NPC.shipInfo[i]);
      for(int i=0; i < CultimaPanel.player.getVampireInfo() && i < NPC.vampireInfo.length; i++)
         trainingInfo[VAMPIRE].add(NPC.vampireInfo[i]);
      for(int i=0; i < CultimaPanel.player.getWerewolfInfo() && i < NPC.werewolfInfo.length; i++)
         trainingInfo[WEREWOLF].add(NPC.werewolfInfo[i]);
      for(int i=0; i < CultimaPanel.player.getMappingInfo() && i < NPC.mappingInfo.length; i++)
         trainingInfo[MAPPING].add(NPC.mappingInfo[i]);
      for(int i=0; i < CultimaPanel.player.getTrappingInfo() && i < NPC.trappingInfo.length; i++)
         trainingInfo[TRAPPING].add(NPC.trappingInfo[i]);
      for(int i=0; i < NPC.homeInfo.length; i++)
         trainingInfo[HOME].add(NPC.homeInfo[i]);
      for(int i=0; i < NPC.swineInfo.length; i++)
         trainingInfo[SWINE].add(NPC.swineInfo[i]);
      if(CultimaPanel.player.hasItem("crafting-manual"))   
         for(int i=0; i < NPC.gemInfo.length; i++)
            trainingInfo[JEM].add(NPC.gemInfo[i]);
   
      chapters[TRAINING] = trainingInfo;
    //STATS
      int num = (Math.min(NPC.tavernTown.length, CultimaPanel.player.getTavernInfo()) +
                 Math.min(NPC.monsters.length, CultimaPanel.player.getMonsterInfo()) +
                 Math.min(NPC.armsInfo.length, CultimaPanel.player.getArmsInfo()) + 
                 Math.min(NPC.spellInfo.length, CultimaPanel.player.getSpellIndex()) + 
                 Math.min(NPC.dogInfo.length, CultimaPanel.player.getDogInfo()) + 
                 Math.min(NPC.horseInfo.length, CultimaPanel.player.getHorseInfo()) + 
                 Math.min(NPC.shipInfo.length, CultimaPanel.player.getShipInfo()) + 
                 Math.min(NPC.vampireInfo.length, CultimaPanel.player.getVampireInfo()) + 
                 Math.min(NPC.werewolfInfo.length, CultimaPanel.player.getWerewolfInfo()) +
                 Math.min(NPC.mappingInfo.length, CultimaPanel.player.getMappingInfo()) + 
                 Math.min(NPC.trappingInfo.length, CultimaPanel.player.getTrappingInfo()));
      double denom = (NPC.tavernTown.length + NPC.monsters.length + NPC.armsInfo.length + NPC.spellInfo.length 
                    + NPC.dogInfo.length + NPC.horseInfo.length + NPC.shipInfo.length + NPC.vampireInfo.length + NPC.werewolfInfo.length + NPC.mappingInfo.length + NPC.trappingInfo.length);
      Player.stats[Player.JOURNAL_COMPLETED] = (int)(Math.round(num/denom * 100));
      
      double numRevealed = CultimaPanel.revealedMap;
      int mapSize = ((CultimaPanel.map.get(0)).length * (CultimaPanel.map.get(0))[0].length);
      double percentComplete = numRevealed / mapSize * 100;
      Player.stats[Player.MAP_COMPLETED] = (int)(Math.round(percentComplete));
      
      int locsFound = (int)((visited.size()/numLocs)*100);
      Player.stats[Player.LOCATIONS_FOUND] = locsFound;
   
      chapters[STATS] = Player.stats;
      
       //ACHIEVEMENTS   
      ArrayList<String> achievements = new ArrayList<String>();
      for(int i=0; i<Achievement.NUM_ACHIEVEMENTS && i<CultimaPanel.achievements.length; i++)
      {
         byte ach = CultimaPanel.achievements[i];
         String temp = Achievement.getAchievement(i).trim();
         if(ach == 1)
            temp = "~"+temp;     //completed achievement
         achievements.add(temp);
      }
      chapters[ACHIEVEMENTS] = achievements;
      
      CultimaPanel.journalChapterIndex = 0;   		  	//what chapter are we viewing
      CultimaPanel.journalPageIndex = 0;
      return chapters;
   }

   private static void swap(ArrayList<Location> list, int a, int b)
   {
      Location temp = list.get(a);
      list.set(a, list.get(b));
      list.set(b, temp);
   }

   public static void sortLocs(ArrayList<Location> list)
   {
      for(int i=0; i<list.size(); i++)
      {
         int min = i;
         for(int j=i+1; j<list.size(); j++)
         {
            if(list.get(j).getName().compareTo(list.get(min).getName()) < 0)
               min = j;
         }
         swap(list, i, min);
      }
   } 

   //draws image on the right page of the journal for monsters, weapons, townsfolk, etc
   //draws a ? in a frame if picture is null
   public static void drawPictureInFrame(Graphics g, ImageIcon picture)
   {
      int SIZE = CultimaPanel.SIZE;
      int SCREEN_SIZE = CultimaPanel.SCREEN_SIZE;
      int xStart = 5;
      int yStart = 4;
      g.setColor(Color.orange.darker().darker());        		//place a frame around our portrait
      g.fillRect((int)((SIZE*SCREEN_SIZE) + (SIZE*(xStart + 0.5))), (int)(SIZE*(yStart+0.25)), SIZE*7, SIZE*7);    
      g.setColor(new Color(245, 230, 220));							//draw canvas in frame
      g.fillRect((int)((SIZE*SCREEN_SIZE) + (SIZE*(xStart + 1))), (int)(SIZE*(yStart+0.75)), SIZE*6, SIZE*6);                                        
      g.setColor(new Color(10, 10, 10));
      if(picture != null)
      {
         g.drawImage(picture.getImage(), (SIZE*SCREEN_SIZE) + (SIZE*(xStart + 1)), (int)(SIZE*(yStart+0.75)), SIZE*6, SIZE*6, null);
      }
      else
      {
         g.setColor(Color.orange.darker().darker());        		//place a frame around our portrait
         g.setFont(new Font("Old English Text MT", Font.BOLD, SIZE*4));
         g.drawString("?", (SIZE*SCREEN_SIZE) + (SIZE*(xStart + 3)), (SIZE*(yStart+5)));
         g.setFont(new Font("Old English Text MT", Font.PLAIN, SIZE));
         g.setColor(new Color(10, 10, 10));
      }
   }

//after the title of the chapter, we can get 20 lines of info per page
   public static void showJournal(Graphics g)
   {	 
      int SIZE = CultimaPanel.SIZE;
      int SCREEN_SIZE = CultimaPanel.SCREEN_SIZE;
      Font readable = new Font("Serif", Font.BOLD | Font.ITALIC, SIZE);
      Font readableP = new Font("Serif", Font.PLAIN | Font.ITALIC, SIZE);  
      Font handWriten = new Font("Pristina", Font.BOLD | Font.ITALIC, SIZE);
      Font title = new Font("Old English Text MT", Font.BOLD, SIZE);
      Font oldP = new Font("Old English Text MT", Font.PLAIN, SIZE);
   
      g.setColor(new Color(245, 230, 220));
      g.fillRect(0,0, SIZE*SCREEN_SIZE, SIZE*SCREEN_SIZE);
      int x = SIZE;
      int y = SIZE*2;  
   
      g.setFont(title);
      g.setColor(new Color(10, 10, 10)); 
   
      if(CultimaPanel.journalChapterIndex == START_STORY)
      { 
         String name = CultimaPanel.player.getName();
         if(name.startsWith("~"))   //vampire
            name = name.substring(1);
         else if(name.endsWith("~"))
            name = name.substring(0, name.length()-1);
         g.drawString(name+"'s Journal", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         String[]story = (String[])(CultimaPanel.journal[START_STORY]);
         for(String line: story)
         {
            g.drawString(line, x, y);
            y += SIZE;
         }
         //draw the player's image in the right info-bar if you have information on it                                     
         ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
         if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
            temp = new ImageIcon(Player.characters[Player.NONE][0]);
         drawPictureInFrame(g, temp);
      }
      else if(CultimaPanel.journalChapterIndex == SHOPS)
      { 
         g.setFont(title);
         g.drawString("Shoppes: 100%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] shops = (ArrayList<String> [])(CultimaPanel.journal[SHOPS]);
      
         if(CultimaPanel.journalPageIndex > TAVERN)
            CultimaPanel.journalPageIndex = TAVERN;
         for(byte charIndex=CultimaPanel.journalPageIndex; charIndex<shops.length && charIndex<=TAVERN; charIndex++)
         {
            if(charIndex==CultimaPanel.journalPageIndex)	   //make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currShop = shops[charIndex];
            
            String name = "Ironsmith";
            if(charIndex==1)
               name = "Mage's Shoppe";
            else if(charIndex==2)
               name = "Rations";             
            else if(charIndex==3)
               name = "Tavern";
            g.drawString(name+":", x, y);
            y += SIZE;
           
            for(String sentence: currShop)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }  
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
      //draw frame to put NPC image into   
         g.setFont(oldP);
         byte topIndex =  CultimaPanel.journalPageIndex;
         if(topIndex >=0 && topIndex <= TAVERN && topIndex<shopSigns.length)   //draw the shop sign picture
            drawPictureInFrame(g, shopSigns[topIndex]);
         else        //draw ? in frame
            drawPictureInFrame(g, null);
      }
      else if(CultimaPanel.journalChapterIndex == AFFLICTIONS)
      { 
         g.setFont(title);
         g.drawString("Afflictions: 100%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] afflictions = (ArrayList<String> [])(CultimaPanel.journal[AFFLICTIONS]);
      
         if(CultimaPanel.journalPageIndex >= NUM_AFFLICTIONS-1)
            CultimaPanel.journalPageIndex = NUM_AFFLICTIONS-1;
         for(byte charIndex=CultimaPanel.journalPageIndex; charIndex<afflictions.length && charIndex<NUM_AFFLICTIONS; charIndex++)
         {
            if(charIndex==CultimaPanel.journalPageIndex)	   //make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currAffliction = afflictions[charIndex];
            
            String name = "Blessed";
            if(charIndex==1)
               name = "Cursed";
            else if(charIndex==2)
               name = "Fire";             
            else if(charIndex==3)
               name = "Frozen";
            else if(charIndex==4)
               name = "Poisoned";
            else if(charIndex==5)
               name = "Sullied";
            g.drawString(name+":", x, y);
            y += SIZE;
           
            for(String sentence: currAffliction)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }   
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         
         //draw frame to put NPC image into   
            //g.setFont(oldP);
            byte topIndex =  CultimaPanel.journalPageIndex;
            ImageIcon picture = null;
            byte monster = -1;
            if(topIndex == CURSED)
               monster = NPC.DEMON;
            else if(topIndex == FIRE)
               monster = NPC.DRAGON;
            else if(topIndex == FROZEN)
               monster = NPC.SEAMONSTER;
            if(topIndex==BLESSED)
               picture = shopSigns[ARMS];
            else if((topIndex>=CURSED && topIndex<=FROZEN) && monster != -1)
            {   
               NPCPlayer temp = new NPCPlayer(monster, 0, 0, 0, "");
               temp.setDirection(AnimatedEntity.LEFT);  
               picture = temp.getPicture();
            }
            else if(topIndex==SULLIED)
               picture = shopSigns[TAVERN];
            else if(topIndex==POISONED)
               picture = shopSigns[MAGIC];
            if(picture != null && topIndex >=0 && topIndex < NUM_AFFLICTIONS) 
               drawPictureInFrame(g, picture);
            else        //draw ? in frame
               drawPictureInFrame(g, null);
         }
      }
      else if(CultimaPanel.journalChapterIndex == LOCATIONS)
      { 
         g.setFont(title);
         g.drawString("Locations: 100%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] locationsArray = (ArrayList<String> [])(CultimaPanel.journal[LOCATIONS]);
      
         if(CultimaPanel.journalPageIndex >= NUM_LOCATIONS-1)
            CultimaPanel.journalPageIndex = NUM_LOCATIONS-1;
         for(byte charIndex=CultimaPanel.journalPageIndex; charIndex<locationsArray.length && charIndex<NUM_LOCATIONS; charIndex++)
         {
            if(charIndex==CultimaPanel.journalPageIndex)	   //make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currLocation = locationsArray[charIndex];
            String name = "The Arena";
            if(charIndex==1)
            {
               name = "The Battlefield";
            }
            else if(charIndex==2)
            {
               name = "The Capital"; 
            }            
            else if(charIndex==3)
            {
               name = "The Castle";
            }
            else if(charIndex==4)
            {
               name = "The City";
            }
            else if(charIndex==5)
            {
               name = "The Hut";
            }
            else if(charIndex==6)
            {
               name = "The Cave";
            }
            else if(charIndex==7)
            {
               name = "The Mine"; 
            }            
            else if(charIndex==8)
            {
               name = "The Mountain Lair";
            }
            else if(charIndex==9)
            {
               name = "The Port";
            }
            else if(charIndex==10)
            {
               name = "The Temple";
            }
            else if(charIndex==11)
            {
               name = "The Village";
            }
            g.drawString(name+":", x, y);
            y += SIZE;
           
            for(String sentence: currLocation)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  } 
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         
         //draw frame to put NPC image into   
            //g.setFont(oldP);
            byte topIndex =  CultimaPanel.journalPageIndex;
            int imageIndex = 16; //index of the picture of the current location in ImageIcon[]locations defined above
            if(topIndex==1)
               imageIndex = 0;
            else if(topIndex==2)
               imageIndex = 15;           
            else if(topIndex==3)
               imageIndex = 1;
            else if(topIndex==4)
               imageIndex = 2;
            else if(topIndex==5)
               imageIndex = 5;
            else if(topIndex==6)
               imageIndex = 7;
            else if(topIndex==7)
               imageIndex = 9;           
            else if(topIndex==8)
               imageIndex = 8;
            else if(topIndex==9)
               imageIndex = 14;
            else if(topIndex==10)
               imageIndex = 10;
            else if(topIndex==11)
               imageIndex = 12;
         
            ImageIcon picture = null;
            if(imageIndex >= 0 && imageIndex < locations.length)
               picture = locations[imageIndex];
            if(picture != null && topIndex >=0 && topIndex < NUM_LOCATIONS) 
               drawPictureInFrame(g, picture);
            else        //draw ? in frame
               drawPictureInFrame(g, null);
         }
      }
      
      else if(CultimaPanel.journalChapterIndex == MONSTERS)
      {
         g.setFont(title);
         double complete = (((double)(Math.min(NPC.monsters.length, CultimaPanel.player.getMonsterInfo()))) / NPC.monsters.length) * 100;
         g.drawString("Monster Manual: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] monsterInfo = (ArrayList<String> [])(CultimaPanel.journal[MONSTERS]);
         if(CultimaPanel.journalPageIndex > NPC.BRIGAND_SWORD - (NPC.BUGBEAR-1))
            CultimaPanel.journalPageIndex = NPC.BRIGAND_SWORD - (NPC.BUGBEAR-1); 
         for(byte charIndex=(byte)(NPC.BUGBEAR + CultimaPanel.journalPageIndex); charIndex<monsterInfo.length && charIndex<=NPC.BRIGAND_SWORD+1; charIndex++)
         {
            if(charIndex==NPC.BUGBEAR + CultimaPanel.journalPageIndex)	//make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currMonster = monsterInfo[charIndex];
            if(charIndex == NPC.BRIGAND_SWORD+1)						
               currMonster = monsterInfo[monsterInfo.length-1];	//the last index is where we put our miscelaneous monster info  
            
            String name = NPC.characterDescription(charIndex);
            if(name.contains("?") || charIndex == NPC.BRIGAND_SWORD+1)
               name = "Misc notes";
         	
            if(currMonster == null || currMonster.size() == 0)		//no info for that monster yet
            {
               g.drawString(name+":?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               g.drawString(name+":", x, y);
               y += SIZE;
            }
            for(String sentence: currMonster)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
      //draw frame to put monster image into   
         g.setFont(oldP);
         byte topIndex =  (byte)(NPC.BUGBEAR + CultimaPanel.journalPageIndex);
         if(topIndex <= NPC.BRIGAND_SWORD)
         {
            if(topIndex >=0 && topIndex<monsterInfo.length && monsterInfo[topIndex]!=null && monsterInfo[topIndex].size() > 0)
            {//draw the monster's image in the right info-bar if you have information on it                                     
               NPCPlayer temp = new NPCPlayer(topIndex, 0, 0, 0, "");
               if(NPC.isBrigand(topIndex))
                  temp = new NPCPlayer(NPC.BRIGAND_DAGGER, 0, 0, 0, "");
               temp.setDirection(AnimatedEntity.LEFT);   
               if(topIndex==NPC.GRABOID || topIndex==NPC.DEATH)
                  drawPictureInFrame(g, null);  //keep these monster's images a mystery
               else
                  drawPictureInFrame(g, temp.getPicture());
               x = SIZE * SCREEN_SIZE;
               y = SIZE * 11;
               g.setColor(new Color(0, 0, 127));
               String minMight = getMightName(NPC.stats[topIndex][NPC.MIN_MIGHT]);
               String maxMight = getMightName(NPC.stats[topIndex][NPC.MAX_MIGHT]);
               String avgMight = getMightName((NPC.stats[topIndex][NPC.MIN_MIGHT] + NPC.stats[topIndex][NPC.MAX_MIGHT])/2);
               String minMind = getMindName(NPC.stats[topIndex][NPC.MIN_MIND]);
               String maxMind = getMindName(NPC.stats[topIndex][NPC.MAX_MIND]);
               String avgMind = getMindName((NPC.stats[topIndex][NPC.MIN_MIND] + NPC.stats[topIndex][NPC.MAX_MIND])/2);                  
               String minAgil = getAgilityName(NPC.stats[topIndex][NPC.MIN_AGIL]);
               String maxAgil = getAgilityName(NPC.stats[topIndex][NPC.MAX_AGIL]);
               String avgAgil = getAgilityName((NPC.stats[topIndex][NPC.MIN_AGIL] + NPC.stats[topIndex][NPC.MAX_AGIL])/2); 
               int demeanor = NPC.stats[topIndex][NPC.DEMEANOR];
               Weapon weap = temp.getWeapon();
               //only special monk-sorcers have an icestaff, so change the one displayed to lightning-bolt
               if(weap.getName().toLowerCase().equals("bright-icestaff"))
                  weap = Weapon.getLightningbolt();
               if(minMight.equals(maxMight))
                  g.drawString("Might - "+avgMight, x, y+=SIZE);
               else
                  g.drawString("Might - "+minMight+" to "+maxMight, x, y+=SIZE);
               if(minMind.equals(maxMind))
                  g.drawString("Mind - "+avgMind, x, y+=SIZE);
               else
                  g.drawString("Mind - "+minMind+" to "+maxMind, x, y+=SIZE);     
               if(minAgil.equals(maxAgil))      
                  g.drawString("Agility - "+avgAgil, x, y+=SIZE);
               else
                  g.drawString("Agility - "+minAgil+" to "+maxAgil, x, y+=SIZE);     
               if(topIndex == NPC.BRIGAND_SWORD)
                  g.drawString("Varied weapon: range 1-5", x, y+=SIZE);
               else if(NPC.getSize(topIndex) >= 1.5 && weap.getRange()==1)
                  g.drawString(weap.getName()+": range 2", x, y+=SIZE);
               else
                  g.drawString(weap.getName()+": range "+weap.getRange(), x, y+=SIZE);
               g.drawString("Size - "+getSizeName(topIndex), x, y+=SIZE);
               g.drawString("Demeanor - "+getDemeanorName(demeanor), x, y+=SIZE);
            }
            else
            {//draw ? in frame
               drawPictureInFrame(g, null);
            }
         }
         else
         {
            ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
            NPCPlayer tempPlayer = null;
            if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
               temp = new ImageIcon(Player.characters[Player.NONE][0]);  
            drawPictureInFrame(g, temp);
         }
      }
      else if(CultimaPanel.journalChapterIndex == WEAPONS)
      { 
         g.setFont(title);
         double complete = (((double)(Math.min(NPC.armsInfo.length, CultimaPanel.player.getArmsInfo()))) / NPC.armsInfo.length) * 100;
         g.drawString("Weapons Information: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] weaponInfo = (ArrayList<String> [])(CultimaPanel.journal[WEAPONS]);
      
         if(CultimaPanel.journalPageIndex > Player.LUTE + 2)
            CultimaPanel.journalPageIndex = Player.LUTE + 2;
         for(byte imageIndex=CultimaPanel.journalPageIndex; imageIndex<weaponInfo.length && imageIndex<=Player.LUTE+2; imageIndex++)
         {
            if(imageIndex == CultimaPanel.journalPageIndex)	//make selected (top) text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currWeapon = weaponInfo[imageIndex];
            
            String name = Weapon.getWeaponType(imageIndex);
            if(imageIndex==Player.LUTE+1)
               name = "Gloves:";	
            if(currWeapon == null || currWeapon.size() == 0)		//no info for that weapon yet
            {
               g.drawString(name+"?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               g.drawString(name, x, y);
               y += SIZE;
            }
            for(String sentence: currWeapon)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
         g.setFont(oldP);
         byte topIndex =  CultimaPanel.journalPageIndex;
         if(topIndex >=0 && topIndex<=Player.LUTE+1)
         {
            if(topIndex<weaponInfo.length && weaponInfo[topIndex]!=null && weaponInfo[topIndex].size() > 0)
            {//draw the weapon's image in the right info-bar if you have information on it   
               ImageIcon temp = new ImageIcon(Player.characters[topIndex][0]);
               if(topIndex == 0)
                  temp = armor;
               else if(topIndex == Player.LUTE+1)
                  temp = glove;
               drawPictureInFrame(g, temp);
               x = SIZE * SCREEN_SIZE;
               y = SIZE * 11;
               if(topIndex > 0)
               {
                  g.setColor(new Color(0, 0, 127));
                  Weapon weap = getWeapon(topIndex);                                  
                  if(weap != null)
                  {//damage, range, speed, price
                     g.drawString("Damage - "+getDamageName(weap), x, y+=SIZE);
                     g.drawString("Range - "+weap.getRange(), x, y+=SIZE);
                     g.drawString("Speed between attacks - "+getWeaponSpeedName(weap.getReloadFrames()), x, y+=SIZE);
                     g.drawString("Price - "+getPriceName(weap.getValue()), x, y+=SIZE);
                  }
               }
            }
            else
            {//draw ? in frame
               drawPictureInFrame(g, null);
            }
         }
         else
         {
            ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
            NPCPlayer tempPlayer = null;
            if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
               temp = new ImageIcon(Player.characters[Player.NONE][0]);  
            drawPictureInFrame(g, temp);
         }
      }
      else if(CultimaPanel.journalChapterIndex == SPELLS)
      { 
         g.setFont(title);
         double complete = (((double)(Math.min(NPC.spellInfo.length, CultimaPanel.player.getSpellIndex()))) / NPC.spellInfo.length) * 100;
         g.drawString("Spell Advice: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
      
         ArrayList<String> [] spellInformation = (ArrayList<String> [])(CultimaPanel.journal[SPELLS]);
         if(CultimaPanel.journalPageIndex > NUM_SPELLS)
            CultimaPanel.journalPageIndex = NUM_SPELLS;
         byte topIndex =  CultimaPanel.journalPageIndex;
         Weapon weap = null;
         Spell spell = null;
         if(topIndex >= 0 && topIndex <= ITEMS)
         {
            if(topIndex==PHASEWALL || topIndex==CURSE || topIndex==POSSESS || topIndex==FIREBALL || topIndex==ADVANCE || topIndex==STONECAST || topIndex==SPIDERSWEB || topIndex==RAISESTONE
                || topIndex==ICESTRIKE || topIndex==STAFF || topIndex==LIGHTNING || topIndex==DEATHTOUCH || topIndex==FIRESHIELD || topIndex==SUMMONVORTEX || topIndex==BLINDINGLIGHT)
               weap = getSpellWeapon(topIndex);
            spell = getSpellWithName(topIndex);
         }
      
         for(byte i=CultimaPanel.journalPageIndex; i<spellInformation.length && i<=NUM_SPELLS; i++)
         {
            if(i == CultimaPanel.journalPageIndex)	//make selected (top) spell text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currSpell = spellInformation[i];
            
            String name = getSpellName(i);
            if(currSpell == null || currSpell.size() == 0)		//no info for that spell yet
            {
               g.drawString(name+"?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               if((weap!=null && CultimaPanel.player.hasItem(weap.getName())) 
               || (spell!=null && CultimaPanel.player.hasSpell(spell.getName()))
               || (name.toLowerCase().contains("magic staff") && CultimaPanel.player.hasMagicStaff()))
                  g.drawString(name+"(acquired)", x, y);
               else
               {
                  g.drawString(name, x, y);
               }
               y += SIZE;
            }
            for(String sentence: currSpell)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
         g.setFont(oldP);
         if(topIndex >= 0 && topIndex <= ITEMS) 
         {
            if(topIndex<spellInformation.length && spellInformation[topIndex]!=null && spellInformation[topIndex].size() > 0)
            {//draw the weapon's image in the right info-bar if you have information on it   
               ImageIcon temp = book;  
            
               if(topIndex==PHASEWALL || topIndex==CURSE || topIndex==POSSESS || topIndex==FIREBALL || topIndex==ADVANCE || topIndex==STONECAST || topIndex==SPIDERSWEB
                || topIndex==ICESTRIKE || topIndex==STAFF || topIndex==LIGHTNING || topIndex==DEATHTOUCH || topIndex==FIRESHIELD || topIndex==SUMMONVORTEX || topIndex==BLINDINGLIGHT)
                  temp = new ImageIcon(Player.characters[Player.STAFF][0]);
               
               if(topIndex==INVISIBILITY || topIndex==PROTECTION || topIndex==SPEED || topIndex==STRENGTH || topIndex==ALPHAMIND || topIndex==FIRESKIN)
                  temp = potion; 
               else if(topIndex==HEAL || topIndex==CURE || topIndex==FOCUS)
                  temp = bookAndPotion;   
               else if(topIndex ==ITEMS)
                  temp = items;
               else if(topIndex == CLOAKS)
                  temp = new ImageIcon(Player.characters[Player.STAFF][0]);
                   
               if(topIndex <= ITEMS)
               {
                  drawPictureInFrame(g, temp);
                  if(topIndex < ITEMS)
                  {
                     x = SIZE * SCREEN_SIZE;
                     y = SIZE * 11;
                     g.setColor(new Color(0, 0, 127));
                     if(weap != null)
                     {
                        g.drawString("Damage - "+getDamageName(weap), x, y+=SIZE);
                        g.drawString("Range - "+weap.getRange(), x, y+=SIZE);
                        if(!weap.getEffect().equals("none"))
                           g.drawString("Effect - "+weap.getEffect(), x, y+=SIZE);  
                        if(topIndex==ADVANCE)
                           g.drawString("Manna - "+getMannaCostName(weap.getMannaCost())+" with recovery time", x, y+=SIZE);
                        else 
                           g.drawString("Manna - "+getMannaCostName(weap.getMannaCost()), x, y+=SIZE);
                     }
                     else if(spell != null)
                     {  //potions don't need manna, but have a price of purchase
                        if(spell.getDuration() > 0)
                           g.drawString("Duration - "+getDurationName(spell.getDuration()), x, y+=SIZE);
                        if(topIndex==INVISIBILITY || topIndex==PROTECTION || topIndex==SPEED || topIndex==STRENGTH || topIndex==ALPHAMIND || topIndex==FIRESKIN)
                           g.drawString("Price - "+getPriceName(spell.getValue()), x, y+=SIZE);
                        else
                        {
                           if(topIndex==TELEPORT || topIndex==FLAMEBLAST)
                              g.drawString("Manna - "+getMannaCostName(spell.getMannaCost())+" with recovery time", x, y+=SIZE);
                           else
                              g.drawString("Manna - "+getMannaCostName(spell.getMannaCost()), x, y+=SIZE);
                        }
                     }
                  }
               }
            }
            else
            {//draw ? in frame
               drawPictureInFrame(g, null);
            }
         }
         else
         {
            ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
            NPCPlayer tempPlayer = null;
            if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
               temp = new ImageIcon(Player.characters[Player.NONE][0]);  
            drawPictureInFrame(g, temp);
         }
      }
      else if(CultimaPanel.journalChapterIndex == TOWNS_FOLK)
      { 
         g.setFont(title);
         double complete = (((double)(Math.min(NPC.tavernTown.length, CultimaPanel.player.getTavernInfo()))) / NPC.tavernTown.length) * 100;
         g.drawString("Townsfolk: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] townInfo = (ArrayList<String> [])(CultimaPanel.journal[TOWNS_FOLK]);  //size [NPC.KING + 2];  //last element will be for general info
      
         if(CultimaPanel.journalPageIndex > NPC.KING+1)
            CultimaPanel.journalPageIndex = NPC.KING+1;
         for(byte charIndex=CultimaPanel.journalPageIndex; charIndex<townInfo.length && charIndex<=NPC.KING+1; charIndex++)
         {
            if(charIndex==CultimaPanel.journalPageIndex)	   //make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currNPC = townInfo[charIndex];
            if(charIndex == NPC.KING+1)						
               currNPC = townInfo[townInfo.length-1];	//the last index is where we put our miscelaneous monster info  
            
            String name = NPC.characterDescription(charIndex);
            if(name.contains("?") || charIndex == NPC.KING+1)
               name = "Misc notes";
         	
            if(currNPC == null || currNPC.size() == 0)		//no info for that monster yet
            {
               g.drawString(name+":?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               g.drawString(name+":", x, y);
               y += SIZE;
            }
            for(String sentence: currNPC)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
      //draw frame to put NPC image into   
         g.setFont(oldP);
         byte topIndex =  CultimaPanel.journalPageIndex;
         if(topIndex <= NPC.KING)
         {
            if(topIndex >=0 && topIndex<townInfo.length && townInfo[topIndex]!=null && townInfo[topIndex].size() > 0)
            {//draw the NPC's image in the right info-bar if you have information on it                                     
               NPCPlayer temp = new NPCPlayer(topIndex, 0, 0, 0, "");
               if(topIndex==NPC.GUARD_SPEAR || topIndex==NPC.GUARD_SWORD || topIndex==NPC.GUARD_FIST)
                  temp = new NPCPlayer(NPC.GUARD_FIST, 0, 0, 0, "");
               if(topIndex==NPC.TAXMAN)
                  temp.setDirection(AnimatedEntity.DOWN);    
               drawPictureInFrame(g, temp.getPicture());
               x = SIZE * SCREEN_SIZE;
               y = SIZE * 11;
               g.setColor(new Color(0, 0, 127));
               String minMight = getMightName(NPC.stats[topIndex][NPC.MIN_MIGHT]);
               String maxMight = getMightName(NPC.stats[topIndex][NPC.MAX_MIGHT]);
               String avgMight = getMightName((NPC.stats[topIndex][NPC.MIN_MIGHT] + NPC.stats[topIndex][NPC.MAX_MIGHT])/2);
               String minMind = getMindName(NPC.stats[topIndex][NPC.MIN_MIND]);
               String maxMind = getMindName(NPC.stats[topIndex][NPC.MAX_MIND]);
               String avgMind = getMindName((NPC.stats[topIndex][NPC.MIN_MIND] + NPC.stats[topIndex][NPC.MAX_MIND])/2);                  
               String minAgil = getAgilityName(NPC.stats[topIndex][NPC.MIN_AGIL]);
               String maxAgil = getAgilityName(NPC.stats[topIndex][NPC.MAX_AGIL]);
               String avgAgil = getAgilityName((NPC.stats[topIndex][NPC.MIN_AGIL] + NPC.stats[topIndex][NPC.MAX_AGIL])/2); 
               int demeanor = NPC.stats[topIndex][NPC.DEMEANOR];
               Weapon weap = temp.getWeapon();
               if(minMight.equals(maxMight))
                  g.drawString("Might - "+avgMight, x, y+=SIZE);
               else
                  g.drawString("Might - "+minMight+" to "+maxMight, x, y+=SIZE);
               if(minMind.equals(maxMind))
                  g.drawString("Mind - "+avgMind, x, y+=SIZE);
               else
                  g.drawString("Mind - "+minMind+" to "+maxMind, x, y+=SIZE);     
               if(minAgil.equals(maxAgil))      
                  g.drawString("Agility - "+avgAgil, x, y+=SIZE);
               else
                  g.drawString("Agility - "+minAgil+" to "+maxAgil, x, y+=SIZE);     
               if(topIndex == NPC.GUARD_SPEAR)
                  g.drawString("Varied weapon: range 1-2", x, y+=SIZE);
               else
                  g.drawString(weap.getName()+": range "+weap.getRange(), x, y+=SIZE);
               g.drawString("Size - "+getSizeName(topIndex), x, y+=SIZE);
               g.drawString("Demeanor - "+getDemeanorName(demeanor), x, y+=SIZE);
            }
            else
            {//draw ? in frame
               drawPictureInFrame(g, null);
            }
         }
         else
         {
            ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
            NPCPlayer tempPlayer = null;
            if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
               temp = new ImageIcon(Player.characters[Player.NONE][0]);  
            drawPictureInFrame(g, temp);
         }
      }
      else if(CultimaPanel.journalChapterIndex == MISSIONS)
      { 
         g.setFont(title);
         g.drawString("Missions in progress:", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> missions = (ArrayList<String>)(CultimaPanel.journal[MISSIONS]); 
         if(missions != null && missions.size() > 0)
         {
            if(CultimaPanel.journalPageIndex >= missions.size())
               CultimaPanel.journalPageIndex = (byte)(missions.size()-1);
            for(int i=CultimaPanel.journalPageIndex; i<missions.size(); i++)
            {
               String sentence = missions.get(i);
            
               if(i==CultimaPanel.journalPageIndex && !sentence.startsWith("~"))	   //make selected (top) riddle text a different color
                  g.setColor(new Color(0, 0, 127));
               else if(sentence.startsWith("~"))         //completed mission
                  g.setColor(new Color(100, 100, 100));
               else
                  g.setColor(new Color(10, 10, 10)); 
                  
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += (SIZE);
            }
         }
         //draw the player's image in the right info-bar if you have information on it                                     
         ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
         if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
            temp = new ImageIcon(Player.characters[Player.NONE][0]);
         drawPictureInFrame(g, temp);
      }
      else if(CultimaPanel.journalChapterIndex == VISITED_LOCS)
      { 
         g.setFont(title);
         ArrayList<Location> visited = (ArrayList<Location>)(CultimaPanel.journal[VISITED_LOCS]);
         g.drawString("Locations Found: "+Player.stats[Player.LOCATIONS_FOUND]+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         if(visited != null && visited.size() > 0)
         {
            if(CultimaPanel.journalPageIndex >= visited.size())
               CultimaPanel.journalPageIndex = (byte)(visited.size()-1);
            for(int i=CultimaPanel.journalPageIndex; i<visited.size(); i++)
            {
               if(i==CultimaPanel.journalPageIndex)	   //make selected (top) riddle text a different color
                  g.setColor(new Color(0, 0, 127));
               else
                  g.setColor(new Color(10, 10, 10)); 
               Location loc = visited.get(i);
               g.drawString(loc.toString2(), x, y);
               y += SIZE;
            }
            y += SIZE;
            byte topIndex =  CultimaPanel.journalPageIndex;
            Location loc = visited.get(topIndex);
            g.setFont(oldP);
            if(topIndex >=0 && topIndex<visited.size() && loc!=null)
            {
            //draw the location's image in the right info-bar if you have information on it   
               ImageIcon temp = getLocationImage(loc);
               drawPictureInFrame(g, temp);
               String sentence = getLocationDescription(loc);
               x = SIZE * SCREEN_SIZE;
               y = SIZE * 12;
               g.setColor(new Color(0, 0, 127));
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE) - (SIZE * 8))
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
            }
         }
         else
            drawPictureInFrame(g, null);            
      }
      else if(CultimaPanel.journalChapterIndex == RIDDLES)
      { 
         g.setFont(title);
         double complete = (((double)(Math.min(CultimaPanel.player.getRiddleInfo(),NPC.riddleQst.length))) / NPC.riddleQst.length) * 100;
         g.drawString("Riddles to remember: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> riddles = (ArrayList<String>)(CultimaPanel.journal[RIDDLES]); 
         if(riddles != null && riddles.size() > 0)
         {
            if(CultimaPanel.journalPageIndex >= riddles.size())
               CultimaPanel.journalPageIndex = (byte)(riddles.size()-1);
            for(int i=CultimaPanel.journalPageIndex; i<riddles.size(); i++)
            {
               if(i==CultimaPanel.journalPageIndex)	   //make selected (top) riddle text a different color
                  g.setColor(new Color(0, 0, 127));
               else
                  g.setColor(new Color(10, 10, 10)); 
               String sentence = riddles.get(i);
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += (SIZE*2);
            }
         }
         if(riddles != null && riddles.size() > 0)
         {
            NPCPlayer temp = new NPCPlayer(NPC.JESTER, 0, 0, 0, "");
            drawPictureInFrame(g, temp.getPicture());
         }
         else
            drawPictureInFrame(g, null);
      }
      else if(CultimaPanel.journalChapterIndex == PUZZLES)
      {
         g.setFont(title);
         double numerator = (Math.min(NPC.wellsPuzzleInfo.length, CultimaPanel.player.getWellsPuzzleInfo()))
                          + (Math.min(NPC.doorsPuzzleInfo.length, CultimaPanel.player.getDoorsPuzzleInfo()))
                          + (Math.min(NPC.towersPuzzleInfo.length, CultimaPanel.player.getTowersPuzzleInfo()));
         double denom = NPC.wellsPuzzleInfo.length + NPC.doorsPuzzleInfo.length + NPC.towersPuzzleInfo.length;
         double complete = (numerator / denom) * 100;
         g.drawString("Daunting Puzzles: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] puzzleInfo = (ArrayList<String> [])(CultimaPanel.journal[PUZZLES]);
         if(CultimaPanel.journalPageIndex >= NUM_PUZZLES)
            CultimaPanel.journalPageIndex = NUM_PUZZLES-1;
         for(byte i=CultimaPanel.journalPageIndex; i<puzzleInfo.length; i++)
         {
            if(i==CultimaPanel.journalPageIndex)	//make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currSection = puzzleInfo[i];
                         
            String name = "Misc Information";
            if(i==TOWERS_PUZZLE)
               name = "The Three Towers Puzzle";
            else if(i==DOORS_PUZZLE)
               name = "The Three Doors Puzzle";
            else if(i==WELLS_PUZZLE)
               name = "The Three Wells Puzzle";
         
            if(currSection == null || currSection.size() == 0)		//no info for that section yet
            {
               g.drawString(name+":?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               g.drawString(name+":", x, y);
               y += SIZE;
            }
            for(String sentence: currSection)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
      //draw frame to put image into   
         g.setFont(oldP);
         byte topIndex =  NPC.WISE;
         if(CultimaPanel.journalPageIndex==WELLS_PUZZLE)
            topIndex = NPC.DEMON;
         else if(CultimaPanel.journalPageIndex==DOORS_PUZZLE)
            topIndex = NPC.SORCERER;
         else if(CultimaPanel.journalPageIndex==TOWERS_PUZZLE)
            topIndex = NPC.WISE;
      
         if(puzzleInfo[CultimaPanel.journalPageIndex] != null && puzzleInfo[CultimaPanel.journalPageIndex].size() > 0)
         {//draw the monster's image in the right info-bar if you have information on it                                     
            NPCPlayer temp = new NPCPlayer(topIndex, 0, 0, 0, "");
            temp.setDirection(AnimatedEntity.LEFT);
            drawPictureInFrame(g, temp.getPicture());
         }
         else
         {//draw ? in frame
            drawPictureInFrame(g, null);
         }
      }
      
      else if(CultimaPanel.journalChapterIndex == TRAINING)
      {
         g.setFont(title);
         double numerator = (Math.min(NPC.dogInfo.length, CultimaPanel.player.getDogInfo()) + 
                            Math.min(NPC.horseInfo.length, CultimaPanel.player.getHorseInfo()) + 
                            Math.min(NPC.shipInfo.length, CultimaPanel.player.getShipInfo()) + 
                            Math.min(NPC.vampireInfo.length, CultimaPanel.player.getVampireInfo()) + 
                            Math.min(NPC.werewolfInfo.length, CultimaPanel.player.getWerewolfInfo()) + 
                            Math.min(NPC.mappingInfo.length, CultimaPanel.player.getMappingInfo()) +
                            Math.min(NPC.trappingInfo.length, CultimaPanel.player.getTrappingInfo()));
         double denom = NPC.dogInfo.length + NPC.horseInfo.length + NPC.shipInfo.length + NPC.vampireInfo.length + NPC.werewolfInfo.length + NPC.mappingInfo.length + NPC.trappingInfo.length;
         double complete = (numerator / denom) * 100;
         g.drawString("Strategy Guide: "+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> [] trainingInfo = (ArrayList<String> [])(CultimaPanel.journal[TRAINING]);
         if(CultimaPanel.journalPageIndex >= NUM_TOPICS)
            CultimaPanel.journalPageIndex = NUM_TOPICS-1;
         for(byte i=CultimaPanel.journalPageIndex; i<trainingInfo.length; i++)
         {
            if(i==CultimaPanel.journalPageIndex)	//make selected (top) monster text a different color
               g.setColor(new Color(0, 0, 127));
            else
               g.setColor(new Color(10, 10, 10)); 
                 
            ArrayList<String> currSection = trainingInfo[i];
                         
            String name = "Misc Information";
            if(i==DOG)
               name = "Dog Training";
            else if(i==HORSE)
               name ="Horse Training";
            else if(i==UNICORN)
               name ="Unicorn Information";
            else if(i==SHIP)
               name = "Ship Commandeering";
            else if(i==VAMPIRE)
               name = "Vampire Information";
            else if(i==WEREWOLF)
               name = "Werewolf Information";
            else if(i==MAPPING)
               name = "Mapping Advice";
            else if(i==TRAPPING)
               name = "Trapping Advice";
            else if(i==HOME)
               name = "House and Home Advice";   
            else if(i==SWINE)
               name = "Swine Game Rules"; 
            else if(i==JEM)
               name = "Magic Gem Arms Upgrades"; 
            if(currSection == null || currSection.size() == 0)		//no info for that section yet
            {
               g.drawString(name+":?", x, y);
               y += SIZE;
               y += SIZE;
               continue;
            }
            else
            {
               g.drawString(name+":", x, y);
               y += SIZE;
            }
            for(String sentence: currSection)
            {
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("I") && !tempWord.equals("A"))
                  {
                     if(j==0 || tempWord.contains("GARRIOTT")  || tempWord.contains("SKARA"))    //proper names should be capitalized
                        tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                     else
                        tempWord = tempWord.toLowerCase();
                  }
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += SIZE;
            }
            y += SIZE;
         }
      //draw frame to put image into   
         g.setFont(oldP);
         byte topIndex =  NPC.DOG;
         if(CultimaPanel.journalPageIndex==HORSE)
            topIndex = NPC.HORSE;
         else if(CultimaPanel.journalPageIndex==UNICORN)
            topIndex = NPC.UNICORN;
         else if(CultimaPanel.journalPageIndex==SHIP)
            topIndex = NPC.BRIGANDSHIP;
         else if(CultimaPanel.journalPageIndex==VAMPIRE)
            topIndex = NPC.BAT;
         else if(CultimaPanel.journalPageIndex==WEREWOLF)
            topIndex = NPC.WOLF;
         else if(CultimaPanel.journalPageIndex==MAPPING)
            topIndex = NPC.MAN;
         else if(CultimaPanel.journalPageIndex==TRAPPING)
            topIndex = NPC.ELK;
         else if(CultimaPanel.journalPageIndex==HOME)
            topIndex = NPC.MAN;  
         else if(CultimaPanel.journalPageIndex==SWINE)
            topIndex = NPC.PIG;  
         else if(CultimaPanel.journalPageIndex==JEM)
            topIndex = NPC.MAN;  
         if(trainingInfo[CultimaPanel.journalPageIndex] != null && trainingInfo[CultimaPanel.journalPageIndex].size() > 0)
         {//draw the monster's image in the right info-bar if you have information on it                                     
            NPCPlayer temp = new NPCPlayer(topIndex, 0, 0, 0, "");
            temp.setDirection(AnimatedEntity.LEFT);
            drawPictureInFrame(g, temp.getPicture());
         }
         else
         {//draw ? in frame
            drawPictureInFrame(g, null);
         }
      }
      else if(CultimaPanel.journalChapterIndex == STATS)
      {
         g.setFont(title);
         if(CultimaPanel.player.getBody() <= 0)
         {
            g.drawString("The accomplishments of "+CultimaPanel.player.getName(), x, y);
            g.drawString("Fell on the "+Display.ordinalNum((int)(CultimaPanel.time))+" hour of the "+Display.ordinalNum(CultimaPanel.days)+" day.", x, (y+SIZE));
         }
         else
            g.drawString("My progress:", x, y);
         y += SIZE*2;
      
            //draw the player's image in the right info-bar if you have information on it                                     
         ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
         if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF) //this is the vampire starting story
            temp = new ImageIcon(Player.characters[Player.NONE][0]);
         drawPictureInFrame(g, temp);
         
         g.setFont(handWriten);
         g.setColor(new Color(10, 10, 10));
         g.drawString("Level / Experience:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + CultimaPanel.player.getLevel()+"/"+CultimaPanel.player.getExperience(), x, y);
         y += SIZE;
         g.setColor(Color.black);
         g.drawString("Repuation:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + CultimaPanel.player.getReputationRaw()+"/"+CultimaPanel.player.getPureReputationName(), x, y);
         y += SIZE;
      
         g.setColor(Color.black);
      
         y += SIZE;
      
         g.setColor(Color.black);
         int nextCol = SIZE*SCREEN_SIZE/2;
         g.drawString("Monsters felled:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.MONSTERS_KILLED], x, y);
         g.setColor(Color.black);
         g.drawString("Dragons slain:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.DRAGONS_SLAIN], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Giants slain:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.GIANTS_SLAIN], x, y);
         g.setColor(Color.black);
         g.drawString("Beastkings killed:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.ALPHAMONSTERS_KILLED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Battles fought:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.BATTLES_FOUGHT], x, y);
         g.setColor(Color.black);
         g.drawString("Battles won:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.BATTLES_WON], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Arena level:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.ARENA_LEVEL], x, y);
         g.setColor(Color.black);
         g.drawString("Ships commandeered:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.SHIPS_COMMANDEERED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Game hunted:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.GAME_KILLED], x, y); 
         g.setColor(Color.black);
         g.drawString("Pelts collected:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.PELTS_COLLECTED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Horses tamed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.HORSES_TAMED], x, y); 
         g.setColor(Color.black);
         g.drawString("Dogs befriended:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.DOGS_TRAINED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Missions completed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.MISSIONS_COMPLETED], x, y);
         g.setColor(Color.black);
         g.drawString("Gold Mined:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.GOLD_MINED], nextCol, y);
         y += SIZE;
       
         g.setColor(Color.black);
         g.drawString("Swine games played:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.SWINE_GAMES_PLAYED], x, y);
         g.setColor(Color.black);
         g.drawString("Swine games won:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.SWINE_GAMES_WON], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("People met:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.PEOPLE_MET], x, y);
         g.setColor(Color.black);
         g.drawString("People rescued:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.PEOPLE_RESCUED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("People murdered:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.PEOPLE_MURDERED], x, y);
         g.setColor(Color.black);
         g.drawString("Bounties forgiven:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.BOUNTIES_FORGIVEN], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Bounties collected:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.BOUNTIES_COLLECTED], x, y);
         g.setColor(Color.black);
         g.drawString("Bounties against:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + CultimaPanel.player.getBounty(), nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Assassins killed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.ASSASSINS_KILLED], x, y);
         g.setColor(Color.black);
         g.drawString("Royalty slain:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.ROYALTY_SLAIN], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Guards bribed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.GUARDS_BRIBED], x, y);
         g.setColor(Color.black);
         g.drawString("Items stolen:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.ITEMS_STOLEN], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Gold donated:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.MONEY_DONATED], x, y);
         g.setColor(Color.black);
         g.drawString("Spells learned:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + ((CultimaPanel.player.getWeapons()[Player.STAFF]).size() + CultimaPanel.player.getSpells().size()), nextCol, y);
         y += SIZE; 
         
         g.setColor(Color.black);
         g.drawString("Locks picked:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.LOCKS_PICKED], x, y);
         g.setColor(Color.black);
         g.drawString("Puzzles solved:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.PUZZLES_SOLVED], nextCol, y);
         y += SIZE;
      
         g.setColor(Color.black);
         g.drawString("Houses owned:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.HOUSES_BOUGHT], x, y);
         g.setColor(Color.black);
         g.drawString("Times Married:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.TIMES_MARRIED], nextCol, y);
         y += SIZE;  
      
         g.setColor(Color.black);
         g.drawString("Map completed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.MAP_COMPLETED]+"%", x, y);
         g.setColor(Color.black);
         g.drawString("Locations found:", nextCol, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.LOCATIONS_FOUND]+"%", nextCol, y);
         y += SIZE;
         
         g.setColor(Color.black);
         g.drawString("Journal completed:", x, y);
         g.setColor(new Color(0, 0, 127));
         g.drawString("                                  " + Player.stats[Player.JOURNAL_COMPLETED]+"%", nextCol, y);
         y += SIZE;
      }
      else if(CultimaPanel.journalChapterIndex == ACHIEVEMENTS)
      { 
         g.setFont(title);
         double sum=0;
         for(byte ach:CultimaPanel.achievements)
            sum += ach;
         double complete = (sum / CultimaPanel.achievements.length) * 100;
         g.drawString("Achievements:"+(int)(complete)+"%", x, y);
         y = SIZE*4;
         g.setFont(handWriten);
         ArrayList<String> achievements = (ArrayList<String>)(CultimaPanel.journal[ACHIEVEMENTS]); 
         if(achievements != null && achievements.size() > 0)
         {
            if(CultimaPanel.journalPageIndex >= achievements.size())
               CultimaPanel.journalPageIndex = (byte)(achievements.size()-1);
            for(int i=CultimaPanel.journalPageIndex; i<achievements.size(); i++)
            {
               String sentence = achievements.get(i);
            
               if(i==CultimaPanel.journalPageIndex && !sentence.startsWith("~"))	   //make unearned achievements a different color
                  g.setColor(new Color(0, 0, 127));
               else if(sentence.startsWith("~"))         
                  g.setColor(new Color(100, 100, 100));
               else
                  g.setColor(new Color(10, 10, 10)); 
                  
            //**********WORD WRAP SENTENCE
               String [] words = sentence.split(" ");
               int xAdd = 0;
               for(int j=0; j<words.length; j++)
               {
                  String tempWord = new String(words[j].trim());
                  if(j == words.length - 1)
                     tempWord += ".";
                  else
                     tempWord += " ";
                  int width = g.getFontMetrics().stringWidth(tempWord);
                  if(xAdd + width >= (SIZE * SCREEN_SIZE)-SIZE)
                  {
                     y += SIZE;
                     xAdd = SIZE;
                  }	
                  g.drawString(tempWord, x+xAdd, y);
                  xAdd += width;
               }
            //***************************
               y += (SIZE*2);
            }
            //draw the player's image in the right info-bar if you have information on it                                     
            ImageIcon temp = new ImageIcon(Player.characters[CultimaPanel.player.getStartStoryIndex()][0]);
            NPCPlayer tempPlayer = null;
            if(CultimaPanel.player.getStartStoryIndex()==Player.BAT || CultimaPanel.player.getStartStoryIndex()==Player.WOLF || CultimaPanel.player.getStartStoryIndex()==Player.CAMP || CultimaPanel.player.getStartStoryIndex()==Player.FLIGHT)
               temp = new ImageIcon(Player.characters[Player.NONE][0]);  
            
            if(CultimaPanel.journalPageIndex==Achievement.BRUCE_IS_LOOSE)
               tempPlayer = new NPCPlayer(NPC.SHARK, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.DRAGONSLAYER || CultimaPanel.journalPageIndex==Achievement.KAIJU_KILLER || CultimaPanel.journalPageIndex==Achievement.SCRAMBLED_SMAUG || CultimaPanel.journalPageIndex==Achievement.ZOMBIE_PETE)
               tempPlayer = new NPCPlayer(NPC.DRAGON, 0, 0, 0, ""); 
            else if(CultimaPanel.journalPageIndex==Achievement.ADMIRAL_OF_THE_NAVY || CultimaPanel.journalPageIndex==Achievement.CAPTAIN_CRUNCH)
               tempPlayer = new NPCPlayer(NPC.GREATSHIP, 0, 0, 0, "");  
            else if(CultimaPanel.journalPageIndex==Achievement.ANIMALS_AS_LEADERS || CultimaPanel.journalPageIndex==Achievement.OH_GOOD_DOG)
               tempPlayer = new NPCPlayer(NPC.DOG, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.TRAPPER_KEEPER)
               tempPlayer = new NPCPlayer(NPC.ELK, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.THE_RIDE_THE_RAINBOW_ACHIEVEMENT)
               tempPlayer = new NPCPlayer(NPC.UNICORN, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.SCENE_24 || CultimaPanel.journalPageIndex==Achievement.SPEAK_AND_SPELL || CultimaPanel.journalPageIndex==Achievement.POTION_CONTROL)
               tempPlayer = new NPCPlayer(NPC.WISE, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.PUZZLE_SLAYER)
               tempPlayer = new NPCPlayer(NPC.SORCERER, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.NAME_OF_THE_GAME)
               tempPlayer = new NPCPlayer(NPC.KING, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.MORE_HUMAN_THAN_HUMAN)
               tempPlayer = new NPCPlayer(NPC.BAT, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.KNOW_WHEN_TO_HOLD_EM)
               tempPlayer = new NPCPlayer(NPC.DEMON, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.INFECTIOUS_GROOVES)
               tempPlayer = new NPCPlayer(NPC.ROYALGUARD, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.GHOST_TOWN)
               tempPlayer = new NPCPlayer(NPC.GHOST, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.DOUBLE_UP)
               tempPlayer = new NPCPlayer(NPC.PIG, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.DOROTHY_GALES_REVENGE)
               tempPlayer = new NPCPlayer(NPC.TORNADO, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.BELLY_OF_THE_BEAST)
               tempPlayer = new NPCPlayer(NPC.SEAMONSTER, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.GOLD_DIGGER)
               tempPlayer = new NPCPlayer(NPC.BRIGANDSHIP, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.MARRYING_UP || CultimaPanel.journalPageIndex==Achievement.WALKING_PAPERS)
               tempPlayer = new NPCPlayer(NPC.WOMAN, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.EVERYBODYS_HERO || CultimaPanel.journalPageIndex==Achievement.PHILANTHROPIST)
               tempPlayer = new NPCPlayer(NPC.BEGGER, 0, 0, 0, "");
            else if(CultimaPanel.journalPageIndex==Achievement.RETURN_TO_SENDER || CultimaPanel.journalPageIndex==Achievement.THE_RIDDLER || CultimaPanel.journalPageIndex==Achievement.HOLD_YER_GROUND)
               tempPlayer = new NPCPlayer(NPC.JESTER, 0, 0, 0, "");
            if(tempPlayer != null)
            {
               tempPlayer.setDirection(AnimatedEntity.RIGHT);   
               temp = tempPlayer.getPicture();   
            }   
            else
            {
               if(CultimaPanel.journalPageIndex==Achievement.MINER_2049ER || CultimaPanel.journalPageIndex==Achievement.LIVING_PROOF)
                  temp = new ImageIcon(Player.characters[Player.HAMMER][0]); 
               else if(CultimaPanel.journalPageIndex==Achievement.HANDS_OF_STONE)
                  temp = new ImageIcon(Player.characters[Player.NONE][0]);
               else if(CultimaPanel.journalPageIndex==Achievement.HMS_BOUNTY)
                  temp = new ImageIcon(Player.characters[Player.CROSSBOW][0]);
               else if(CultimaPanel.journalPageIndex==Achievement.SPEAK_AND_SPELL || CultimaPanel.journalPageIndex==Achievement.DEAR_DIARY)
                  temp = book;
               else if(CultimaPanel.journalPageIndex==Achievement.POTION_CONTROL)
                  temp = potion;
               else if(CultimaPanel.journalPageIndex==Achievement.INDY_2500)
                  temp = locations[10];
               else if(CultimaPanel.journalPageIndex==Achievement.MAXIMUS_OVERDRIVE)
                  temp = locations[16];
               else if(CultimaPanel.journalPageIndex==Achievement.RETURN_TO_CASTLE_WOLFENSTEIN)
                  temp = locations[1];
               else if(CultimaPanel.journalPageIndex==Achievement.DEADLY_WHEN_DIZZY)
                  temp = shopSigns[3];
               else if(CultimaPanel.journalPageIndex==Achievement.HUMBLE_BUMBLE || CultimaPanel.journalPageIndex==Achievement.THE_LUNCH_THAT_TIME_FORGOT)
                  temp = shopSigns[2];
               else if(CultimaPanel.journalPageIndex==Achievement.LEGENDARY_CRAFTER || CultimaPanel.journalPageIndex==Achievement.MERCHANT_MAC)
                  temp = shopSigns[0];
            }
            drawPictureInFrame(g, temp);
         }
      }
   
   
   //RIGHT info bar
      x = SIZE * SCREEN_SIZE;
      y = (SIZE); 
      g.setFont(title);
      g.setColor(new Color(0, 0, 127));
      if(CultimaPanel.player.getBody() > 0)
         g.drawString("(S) to save your progress.", x, y);
      else
         g.drawString("(L) to load your last save.", x, y);
      y += SIZE;
      g.drawString("(Q) to quit and save the game.", x, y);
      y += SIZE;
      g.drawString("(B) to build a new world.", x, y);
      y += SIZE;
      g.drawString("(C) to create a new character.", x, y);
      y += SIZE;
   
      y = (SIZE * SCREEN_SIZE) - (SIZE * 7);
      g.setColor(new Color(0, 0, 0));
      g.setFont(readableP);
      g.drawString("(UP-DOWN)", x, y);
      g.setFont(new Font("Old English Text MT", Font.PLAIN, SIZE));
      g.drawString("                         to scan page", x, y);
      y += SIZE;
      g.setFont(readableP);
      g.drawString("(LEFT-RIGHT)", x, y);
      g.setFont(new Font("Old English Text MT", Font.PLAIN, SIZE));
      g.drawString("                         to flip chapters", x, y);
      y += SIZE;
      g.setFont(readableP);
      g.drawString("(M)ap  (I)nventory  (J)ournal(ESC)", x, y);
   //show scrolling message array
      y = (SIZE * SCREEN_SIZE) - (SIZE * 4);
      g.setColor(Color.orange.darker().darker());
      g.fillRect(x, y-SIZE ,  (SIZE * SCREEN_SIZE), (SIZE * 5));
      g.setColor(new Color(10, 10, 10));
      g.setFont(readable);
      y = y - (SIZE/4);
      for(int i=0; i<CultimaPanel.message.length; i++)
      {
         String sentence =CultimaPanel.message[i];
         if(i == CultimaPanel.message.length-1 && CultimaPanel.typed.length() > 0)
            sentence = "~"+CultimaPanel.typed;
         String temp = sentence.toLowerCase();
         if(!temp.startsWith("~"))           //dialogue with NPCs will start with a ~
         {
            g.setFont(oldP);
            g.drawString(sentence, x, y);
         }
         else
         {
            g.setFont(readable);
            String [] words = sentence.split(" ");
            int xAdd = 0;
            for(int j=0; j<words.length; j++)
            {
               g.setColor(new Color(127, 0, 0));
               String tempWord = new String(words[j]);
               if(tempWord.startsWith("~"))
                  tempWord = tempWord.substring(1);
               if(tempWord.length()>0 && tempWord.equals(tempWord.toUpperCase()) && !tempWord.equals("~I") && !tempWord.equals("~A") && !tempWord.equals("I") && !tempWord.equals("A"))
               {
                  g.setColor(new Color(60, 0, 60));
                  if(j==0) 
                     tempWord = tempWord.charAt(0) + tempWord.substring(1).toLowerCase();
                  else
                     tempWord = tempWord.toLowerCase();
               }
               tempWord += " ";
               g.drawString(tempWord, x+xAdd, y);
               int width = g.getFontMetrics().stringWidth(tempWord);
               xAdd += width;
            }
         }
         y += SIZE;
      }
   
   }

   public static String getSpellName(byte i)
   {
      if(i==STAFF) 
         return "Magic Staff:";
      if(i==REPEL)
         return "Repel Spell:";
      if(i==FLAMEBLAST)
         return "Flameblast Spell:";
      if(i==TAMEANIMAL)
         return "Tame-Animal Spell:";
      if(i==SHIFTWIND) 
         return "Shiftwind Spell:";
      if(i==RAISEDEAD) 
         return "Raise-dead Spell:";
      if(i==ENCHANTSTONE)
         return "Enchant-stone Spell:";   
      if(i==FEAR) 
         return "Fear Spell:";
      if(i==DISARM) 
         return "Disarm Spell:";
      if(i==LIGHT) 
         return "Light Spell:";
      if(i==MAGICMIST) 
         return "Magicmist Spell:";
      if(i==CHARM) 
         return "Charm Spell:";
      if(i==HEAL) 
         return "Heal Spell:";
      if(i==CURE) 
         return "Cure Spell:";
      if(i==RESTORE) 
         return "Restore Spell:";
      if(i==KNOWING) 
         return "Knowing Spell:";
      if(i==FOCUS) 
         return "Focus Spell:";
      if(i==EAGLEEYE) 
         return "Eagle-eye Spell:";
      if(i==UNSEEN) 
         return "Unseen Spell:";
      if(i==PROTECTION) 
         return "Protection Potion:";
      if(i==SPEED) 
         return "Speed Potion:";
      if(i==STRENGTH) 
         return "Strength Potion:";
      if(i==FLIGHT) 
         return "Flight Spell:";
      if(i==BLINDINGLIGHT)
         return "Blindinglight Staff Spell:";
      if(i==PHASEWALL) 
         return "Phasewall Staff Spell:";
      if(i==CURSE) 
         return "Curse Staff Spell:";
      if(i==POSSESS) 
         return "Possess Staff Spell:";
      if(i==FIREBALL) 
         return "Fireball Staff Spell:";
      if(i==SPIDERSWEB) 
         return "Spidersweb Staff Spell:";
      if(i==ICESTRIKE) 
         return "Icestrike Staff Spell:";
      if(i==INVISIBILITY) 
         return "Invisibility Potion:";
      if(i==ALPHAMIND) 
         return "Alphamind Potion:";
      if(i==FIRESKIN) 
         return "Fireskin Potion:";
      if(i==LIGHTNING) 
         return "Lightning Staff Spell:";
      if(i==DEATHTOUCH) 
         return "Deathtouch Staff Spell:";
      if(i==ADVANCE) 
         return "Advance Staff Spell:";
      if(i==TEMPEST)
         return "Tempest Spell:";  
      if(i==RAISEEARTH)
         return "Raise-earth Spell:";
      if(i==RAISEWATER)
         return "Raise-water Spell:"; 
      if(i==TIMESTOP)
         return "Timestop Spell:";
      if(i==FIRESTORM)
         return "Firestorm Spell:";
      if(i==FLORETLADE)
         return "Floretlade Spell:";
      if(i==FIRESHIELD)
         return "Fireshield Spell:";
      if(i==TELEPORT)
         return "Teleport Spell:";   
      if(i==SUMMONVORTEX)
         return "Summon-Vortex Spell:";
      if(i==STONECAST)
         return "Stonecast Staff Spell:";   
      if(i==RAISESTONE)
         return "Raise-stone Staff Spell";   
      if(i==ITEMS)
         return "Magic Items:";
      if(i==CLOAKS)
         return "Cloaks and Robes:";  
      if(i==SALEDATES)
         return "Sale Dates:";
      return "Misc notes:";
   }
   
   public static Spell getSpellWithName(byte i)
   {
      if(i==REPEL)
         return Spell.getRepel();
      if(i==FLAMEBLAST)
         return Spell.getFlameBlast();
      if(i==TAMEANIMAL)
         return Spell.getTameAnimal();
      if(i==SHIFTWIND) 
         return Spell.getShiftWind();
      if(i==RAISEDEAD) 
         return Spell.getRaiseDead();
      if(i==DISARM) 
         return Spell.getDisarm();
      if(i==LIGHT) 
         return Spell.getLight();
      if(i==MAGICMIST) 
         return Spell.getMagicMist();
      if(i==CHARM) 
         return Spell.getCharm();
      if(i==FEAR) 
         return Spell.getFear();
      if(i==HEAL) 
         return Spell.getHeal();
      if(i==CURE) 
         return Spell.getCure();
      if(i==RESTORE) 
         return Spell.getRestore();
      if(i==KNOWING) 
         return Spell.getKnowing();
      if(i==FOCUS) 
         return Spell.getFocus();
      if(i==EAGLEEYE) 
         return Spell.getEagleEye();
      if(i==PROTECTION) 
         return Spell.getProtection();
      if(i==SPEED) 
         return Spell.getSpeed();
      if(i==STRENGTH) 
         return Spell.getStrength();
      if(i==INVISIBILITY) 
         return Spell.getInvisibility();
      if(i==ALPHAMIND) 
         return Spell.getAlphaMind();
      if(i==FIRESKIN) 
         return Spell.getFireSkin();
      if(i==FLIGHT) 
         return Spell.getFlight();
      if(i==TEMPEST)
         return Spell.getTempest();   
      if(i==RAISEEARTH)
         return Spell.getRaiseEarth();
      if(i==RAISEWATER)
         return Spell.getRaiseWater();
      if(i==TIMESTOP)
         return Spell.getTimeStop();
      if(i==FIRESTORM)
         return Spell.getFirestorm();
      if(i==FLORETLADE)
         return Spell.getFloretLade();
      if(i==UNSEEN)
         return Spell.getUnseen();
      if(i==TELEPORT)
         return Spell.getTeleport(); 
      if(i==ENCHANTSTONE)
         return Spell.getEnchantStone();
      return null;
   }
   
   public static Weapon getSpellWeapon(byte i)
   {
      if(i==BLINDINGLIGHT)
         return Weapon.getBlindingLight();
      if(i==PHASEWALL) 
         return Weapon.getPhasewall();
      if(i==CURSE) 
         return Weapon.getCurse();
      if(i==POSSESS) 
         return Weapon.getPossess();
      if(i==FIREBALL) 
         return Weapon.getFireball();
      if(i==SPIDERSWEB) 
         return Weapon.getSpidersWeb();
      if(i==ICESTRIKE) 
         return Weapon.getIcestrike();
      if(i==LIGHTNING) 
         return Weapon.getLightning();
      if(i==DEATHTOUCH) 
         return Weapon.getDeathtouch();
      if(i==FIRESHIELD) 
         return Weapon.getFireShield();
      if(i==SUMMONVORTEX) 
         return Weapon.getSummonVortex();
      if(i==ADVANCE) 
         return Weapon.getAdvance();
      if(i==STONECAST)
         return Weapon.getStonecast();  
      if(i==RAISESTONE)
         return Weapon.getRaiseStone(); 
      return null;
   }
   
   public static byte getSpellIndexFromName(String name)
   {
      if(name.equals("STRENGTH")) 
         return 0;
      if(name.equals("ALPHAMIND")) 
         return 1;
      if(name.equals("SPEED")) 
         return 2;
      if(name.equals("PROTECTION")) 
         return 3;
      if(name.equals("FIRESKIN")) 
         return 4;
      if(name.equals("INVISIBILITY")) 
         return 5;
      
      if(name.equals("HEAL")) 
         return 6;
      if(name.equals("CURE")) 
         return 7;
      if(name.equals("FOCUS")) 
         return 8;
   
      if(name.equals("FEAR")) 
         return 9;
      if(name.equals("DISARM")) 
         return 10;
      if(name.equals("LIGHT")) 
         return 11;
      if(name.equals("CHARM")) 
         return 12;
      if(name.equals("RESTORE")) 
         return 13;
      if(name.equals("KNOWING")) 
         return 14;
      if(name.equals("EAGLEEYE")) 
         return 15;
      if(name.equals("FLIGHT")) 
         return 16;
      if(name.equals("TEMPEST")) 
         return 17;
      if(name.equals("RAISEEARTH")) 
         return 18;
      if(name.equals("RAISEWATER")) 
         return 19;
      if(name.equals("TIMESTOP")) 
         return 20;
      if(name.equals("FIRESTORM")) 
         return 21;
      if(name.equals("FLORETLADE")) 
         return 22;
      if(name.equals("UNSEEN")) 
         return 23;
      if(name.equals("TELEPORT")) 
         return 24;
      if(name.equals("SHIFTWIND")) 
         return 25;
      if(name.equals("RAISEDEAD")) 
         return 26;      
      if(name.equals("REPEL")) 
         return 27;
      if(name.equals("FLAMEBLAST")) 
         return 28;
      if(name.equals("TAMEANIMAL")) 
         return 29;
      if(name.equals("ENCHANTSTONE")) 
         return 30;
      if(name.equals("MAGICMIST")) 
         return 31;
         
      if(name.equals("STAFF")) 
         return 32;
      if(name.equals("FIREBALL")) 
         return 33;
      if(name.equals("ICESTRIKE")) 
         return 34;
      if(name.equals("PHASEWALL")) 
         return 35;
      if(name.equals("CURSE")) 
         return 36;
      if(name.equals("POSSESS")) 
         return 37;
      if(name.equals("LIGHTNING")) 
         return 38;
      if(name.equals("DEATHTOUCH")) 
         return 39;
      if(name.equals("FIRESHIELD")) 
         return 40;
      if(name.equals("SUMMONVORTEX")) 
         return 41;
      if(name.equals("ADVANCE")) 
         return 42;
      if(name.equals("STONECAST")) 
         return 43;
      if(name.equals("SPIDERSWEB")) 
         return 44;
      if(name.equals("BLINDINGLIGHT"))
         return 45;
      if(name.equals("RAISESTONE")) 
         return 46;
           
      if(name.equals("CLOAKS")) 
         return 47;
      if(name.equals("ITEMS")) 
         return 48;
         
      if(name.equals("SALEDATES"))
         return 49;
      return -1;
   }

   //returns the size of the NPC as a word for the Journal
   public static String getSizeName(byte type)      
   {
      double size = NPC.getSize(type);
      if(size == 1.5)
         return "big";
      if(size == 2)   
         return "giant";
      return "medium";
   }
   public static String getDemeanorName(int demeanor)
   {
      if(demeanor == -1)
         return "hostile";
      if(demeanor == 1)
         return "varied";
      return "none";
   }
   
   public static String getMightName(int might)
   {
      if(might == 0)
         return "none";
      if(might < 10)
         return "weak";
      if(might < 30)
         return "average";
      if(might < 50)
         return "strong";
      if(might < 70)
         return "very strong";
      return "colossal";
   }
   
   public static String getMindName(int mind)
   {
      if(mind == 0)
         return "none";
      if(mind < 10)
         return "dim";
      if(mind < 20)
         return "average";
      if(mind < 30)
         return "bright";
      if(mind < 40)
         return "very bright";
      return "brilliant";
   }

   public static String getAgilityName(int agil)
   {
      if(agil == 0)
         return "none";
      if(agil < 10)
         return "slow";
      if(agil < 30)
         return "average";
      if(agil < 50)
         return "swift";
      if(agil < 70)
         return "very swift";
      return "blinding";
   }

   public static Weapon getWeapon(byte index)
   {
      if(index==Player.BOW)
         return Weapon.getBow();
      if(index==Player.TORCH)
         return Weapon.getTorch();
      if(index==Player.DAGGER)
         return Weapon.getDagger();
      if(index==Player.DUAL)
         return Weapon.getDualblades();
      if(index==Player.DUALAXE)
         return Weapon.getDualAxe();
      if(index==Player.AXE)
         return Weapon.getAxe();
      if(index==Player.HAMMER)
         return Weapon.getHammer();
      if(index==Player.LONGSTAFF)
         return Weapon.getLongstaff();
      if(index==Player.SABER)
         return Weapon.getSword();
      if(index==Player.SPEAR)
         return Weapon.getSpear();
      if(index==Player.STAFF)
         return Weapon.getStaff();
      if(index==Player.SWORDSHIELD)
         return Weapon.getSwordBuckler();
      if(index==Player.LUTE)
         return Weapon.getLute();
      if(index==Player.CROSSBOW)
         return Weapon.getCrossbow();
      return null;
   }
   
   public static String getDamageName(Weapon weap)
   {
      String min = getDamageName(weap.getMinDamage());
      String max = getDamageName(weap.getMaxDamage());
      String avg = getDamageName((weap.getMinDamage()+weap.getMaxDamage())/2);
      if(min.equals(max))
         return avg;
      return min + " to " + max;
   }
   
   public static String getDamageName(int dam)
   {
      if(dam == 0)
         return "none";
      if(dam <= 15)
         return "light";
      if(dam <= 35)
         return "medium";
      if(dam <= 55)
         return "high";
      if(dam <= 75)
         return "very high";
      return "devastating";
   }
   
   public static String getWeaponSpeedName(int agil)
   {
      if(agil == 0)
         return "none";
      if(agil <= (CultimaPanel.animDelay/8))
         return "swift";
      if(agil <= (CultimaPanel.animDelay/4))
         return "normal";
      if(agil <= (CultimaPanel.animDelay/2))
         return "slow";
      if(agil <= (CultimaPanel.animDelay))
         return "very slow";
      return "dismal";
   }

   public static String getPriceName(int price)
   {
      if(price <=40)
         return "cheap";
      if(price <=60)
         return "affordable";
      if(price <=80)
         return "expensive";
      return "very expensive";
   }
   
   public static String getMannaCostName(int price)
   {
      if(price <= 20)
         return "very low";
      if(price <=40)
         return "low";
      if(price <=60)
         return "medium";
      if(price <=100)
         return "high";
      if(price <=200)
         return "very high";
      return "massive";
   }

   public static String getDurationName(int time)
   {
      if(time <= 0)
         return "none";
      if(time <= 25)
         return "short";  
      if(time <= 50)
         return "medium"; 
      if(time <= 100)
         return "long";
      return "very long";
   }

   private static ImageIcon getLocationImage(Location location)
   {
      String loc = location.getTeleporter().getLocType().toLowerCase();
      if(Utilities.isCapitalCity(location))
         loc = "capital";
      if(loc.contains("battlefield"))
         return locations[0];
      if(loc.contains("large_castle"))
         return locations[1];
      if(loc.contains("city"))
         return locations[2];
      if(loc.contains("castle"))
         return locations[3];
      if(loc.contains("fortress"))
         return locations[4];
      if(loc.contains("domicile"))
         return locations[5];
      if(loc.contains("insanity"))
         return locations[6];
      if(loc.contains("cave"))
         return locations[7];
      if(loc.contains("lair"))
         return locations[8];
      if(loc.contains("mine"))
         return locations[9];
      if(loc.contains("temple"))
         return locations[10];
      if(loc.contains("tower"))
         return locations[11];
      if(loc.contains("village"))
         return locations[12];
      if(loc.contains("dungeon_enterance"))
         return locations[13];
      if(loc.contains("port"))
         return locations[14];
      if(loc.contains("capital"))
         return locations[15];      
      return null;
   }
   
   private static String getLocationDescription(Location location)
   {
      String loc = location.getTeleporter().getLocType().toLowerCase();
   
      if(Utilities.isCapitalCity(location))
         loc = "capital";
   
      if(loc.contains("battlefield"))
         return "A blood-soaked plain, banners marking the ranks of opposing armies";
      if(loc.contains("large_castle"))
         return "A grand castle, fortified with new, finely cut stone";
      if(loc.contains("city"))
         return "A large city, well traveled, with buildings old and new";
      if(loc.contains("castle"))
         return "A lower castle with barracks for a small squad";
      if(loc.contains("fortress"))
         return "A smaller, walled city: once likely a massive fort for armies past";
      if(loc.contains("domicile"))
         return "A solitary thatch hut: the domicile of a hermit";
      if(loc.contains("insanity"))
         return "A strange green cave: a light beyond nature glows within";
      if(loc.contains("cave"))
         return "A darkened mountain cave on a cold, desolate mountain";
      if(loc.contains("lair"))
         return "A frightening beast lair, flickering torches shine within";
      if(loc.contains("mine"))
         return "A worn mine cut into rocky hills";
      if(loc.contains("temple"))
         return "An ancient temple with great, marbled columns";
      if(loc.contains("tower"))
         return "A small castle with a high lookout for scouting advancing hordes";
      if(loc.contains("village"))
         return "A small village of thatched huts, roaming swine and foul";
      if(loc.contains("dungeon_enterance"))
         return "A mysterious, stone entrance to a torch-lit dungeon";
      if(loc.contains("port"))
         return "A muddy but bustling port town along the waters edge";    
      if(loc.contains("capital"))
         return "A bustling capital city with a port, shoppes and a castle in its center";    
      return "?";
   }
   
   public static String getChapterDescription(byte chapterIndex)
   {
      if(chapterIndex ==START_STORY)
         return "The story of "+CultimaPanel.player.getShortName()+" in the world of Cultima";
      if(chapterIndex ==LOCATIONS)
         return "A list of locations found in the world of Cultima";
      if(chapterIndex ==SHOPS)
         return "A description of shoppes found within cities, villages and castles";
      if(chapterIndex ==MONSTERS)
         return "List of monster information acquired from local adventurers and books";
      if(chapterIndex ==WEAPONS)
         return "List of weapon information learned from Ironsmiths";
      if(chapterIndex ==SPELLS)
         return "List of spell and potion information learned from Magic shoppe mages";
      if(chapterIndex ==TOWNS_FOLK)
         return "Information on the townsfolk learned from the Barkeep";
      if(chapterIndex ==AFFLICTIONS)
         return "A description of the conditions that affect the body";
      if(chapterIndex ==TRAINING)
         return "Advice learned from locals about various topics to aid in survival";
      if(chapterIndex ==RIDDLES)
         return "A list of riddles leaned from city and castle jesters";
      if(chapterIndex ==PUZZLES)
         return "The rules for devious puzzles encountered in dungeons, caves and the underworld";
      if(chapterIndex ==MISSIONS)
         return "A list of active missions taken on from the citizens of Cultima";
      if(chapterIndex ==VISITED_LOCS)
         return "A list of locations that have been visited in the world of Cultima";
      if(chapterIndex ==STATS)
         return "A list of statistics kept on various activities and achievements";
      if(chapterIndex ==ACHIEVEMENTS)
         return "The running list of amazing achievements that have and need to be accomplished";
      return CultimaPanel.player.getShortName()+"'s Journal of the world of Cultima";
   }
   
   public static boolean copyFromBook(Item book)
   {
      //check to see if this is a tax-ledger for the FIND_LEDGER mission
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(m.getWorldRow()==CultimaPanel.player.getWorldRow() && m.getWorldCol()==CultimaPanel.player.getWorldCol() && m.getStartStory().contains("ledger"))
         {
            book.setName("tax-ledger");
            book.setValue(0);
            CultimaPanel.player.addItem("tax-ledger");
            return false;
         }
      }
   
      String itemName = book.getName().toLowerCase();
      int value = book.getValue();
      if(itemName.contains("beastbook"))
      {
         if(CultimaPanel.player.getMonsterInfo() < NPC.monsters.length)
         {
            int toAdd = (int)(NPC.monsters.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextMonsterInfo();
            return true;
         }
      }
      if(itemName.contains("armsbook"))
      {
         if(CultimaPanel.player.getArmsInfo() < NPC.armsInfo.length)
         {
            int toAdd = (int)(NPC.armsInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextArmsInfo();
            return true;
         }
      }
      if(itemName.contains("spellbook"))
      {
         if(CultimaPanel.player.getSpellIndex() < NPC.spellInfo.length)
         {
            int toAdd = (int)(NPC.spellInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextSpellInfo();
            return true;
         }
      }
      if(itemName.contains("gossipbook"))
      {
         if(CultimaPanel.player.getTavernInfo() < NPC.tavernTown.length)
         {
            int toAdd = (int)(NPC.tavernTown.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextTavernInfo();
            return true;
         }
      }
      if(itemName.contains("riddlebook"))
      {
         if(CultimaPanel.player.getRiddleInfo() < NPC.riddleQst.length)
         {
            int toAdd = (int)(NPC.riddleQst.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextRiddleInfo();
            return true;
         }
      }
      if(itemName.contains("dogbook"))
      {
         if(CultimaPanel.player.getDogInfo() < NPC.dogInfo.length)
         {
            int toAdd = (int)(NPC.dogInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextDogInfo();
            return true;
         }
      }
      if(itemName.contains("horsebook"))
      {
         if(CultimaPanel.player.getHorseInfo() < NPC.horseInfo.length)
         {
            int toAdd = (int)(NPC.horseInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextHorseInfo();
            return true;
         }
      }
      if(itemName.contains("shipbook"))
      {
         if(CultimaPanel.player.getShipInfo() < NPC.shipInfo.length)
         {
            int toAdd = (int)(NPC.shipInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextShipInfo();
            return true;
         }
      }
      if(itemName.contains("vampirebook"))
      {
         if(CultimaPanel.player.getVampireInfo() < NPC.vampireInfo.length)
         {
            int toAdd = (int)(NPC.vampireInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextVampireInfo();
            return true;
         }
      }
      if(itemName.contains("wolfenbook"))
      {
         if(CultimaPanel.player.getWerewolfInfo() < NPC.werewolfInfo.length)
         {
            int toAdd = (int)(NPC.werewolfInfo.length*(value/100.0));
            for(int i=0; i<toAdd; i++)
               CultimaPanel.player.nextWerewolfInfo();
            return true;
         }
      }   
      if(itemName.contains("3-wells-puzzle"))
      {
         if(CultimaPanel.player.getWellsPuzzleInfo() < NPC.wellsPuzzleInfo.length)
         {
            for(int i=0; i<NPC.wellsPuzzleInfo.length; i++)
               CultimaPanel.player.nextWellsPuzzleInfo();
            return true;
         }
      } 
      if(itemName.contains("3-doors-puzzle"))
      {
         if(CultimaPanel.player.getDoorsPuzzleInfo() < NPC.doorsPuzzleInfo.length)
         {
            for(int i=0; i<NPC.doorsPuzzleInfo.length; i++)
               CultimaPanel.player.nextDoorsPuzzleInfo();
            return true;
         }
      }
      if(itemName.contains("3-towers-puzzle"))
      {
         if(CultimaPanel.player.getTowersPuzzleInfo() < NPC.towersPuzzleInfo.length)
         {
            for(int i=0; i<NPC.towersPuzzleInfo.length; i++)
               CultimaPanel.player.nextTowersPuzzleInfo();
            return true;
         }
      }
      return false;   
   }

   public static void learnAllBooks()
   {
      if(CultimaPanel.player.getMonsterInfo() < NPC.monsters.length)
      {
         int toAdd = NPC.monsters.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextMonsterInfo();
      }
      
      if(CultimaPanel.player.getArmsInfo() < NPC.armsInfo.length)
      {
         int toAdd = NPC.armsInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextArmsInfo();
      }
         
      if(CultimaPanel.player.getSpellIndex() < NPC.spellInfo.length)
      {
         int toAdd = NPC.spellInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextSpellInfo();
      }
                    
      if(CultimaPanel.player.getTavernInfo() < NPC.tavernTown.length)
      {
         int toAdd = NPC.tavernTown.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextTavernInfo();
      }
      
      if(CultimaPanel.player.getRiddleInfo() < NPC.riddleQst.length)
      {
         int toAdd = NPC.riddleQst.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextRiddleInfo();
      }
      
      if(CultimaPanel.player.getDogInfo() < NPC.dogInfo.length)
      {
         int toAdd = NPC.dogInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextDogInfo();
      }
      
      if(CultimaPanel.player.getHorseInfo() < NPC.horseInfo.length)
      {
         int toAdd = NPC.horseInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextHorseInfo();
      }
     
      if(CultimaPanel.player.getShipInfo() < NPC.shipInfo.length)
      {
         int toAdd = NPC.shipInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextShipInfo();
      }
      
      if(CultimaPanel.player.getVampireInfo() < NPC.vampireInfo.length)
      {
         int toAdd = NPC.vampireInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextVampireInfo();
      }
      
      if(CultimaPanel.player.getWerewolfInfo() < NPC.werewolfInfo.length)
      {
         int toAdd = NPC.werewolfInfo.length;
         for(int i=0; i<toAdd; i++)
            CultimaPanel.player.nextWerewolfInfo();
      }
         
      if(CultimaPanel.player.getWellsPuzzleInfo() < NPC.wellsPuzzleInfo.length)
      {
         for(int i=0; i<NPC.wellsPuzzleInfo.length; i++)
            CultimaPanel.player.nextWellsPuzzleInfo();
      }
       
      if(CultimaPanel.player.getDoorsPuzzleInfo() < NPC.doorsPuzzleInfo.length)
      {
         for(int i=0; i<NPC.doorsPuzzleInfo.length; i++)
            CultimaPanel.player.nextDoorsPuzzleInfo();
      }
         
      if(CultimaPanel.player.getTowersPuzzleInfo() < NPC.towersPuzzleInfo.length)
      {
         for(int i=0; i<NPC.towersPuzzleInfo.length; i++)
            CultimaPanel.player.nextTowersPuzzleInfo();
      }
   }
   
   public static void resetStats()
   {
      boolean diaryComplete = false;   //remember if diary is complete so it can be regiven when achievements are reset
      if(CultimaPanel.achievements[Achievement.DEAR_DIARY] == 1)
         diaryComplete = true;
      CultimaPanel.achievements = new byte[Achievement.NUM_ACHIEVEMENTS];
      if(diaryComplete)
         CultimaPanel.achievements[Achievement.DEAR_DIARY] = 1;
      Achievement.writeOutAchievements("data/achievements.txt");
      Player.stats = new int[Player.NUM_STATS];
   }
}