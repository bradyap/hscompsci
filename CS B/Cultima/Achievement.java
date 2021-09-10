import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.Scanner;

public class Achievement
{
   public static final byte ADMIRAL_OF_THE_NAVY = 0;
   public static final byte ANIMALS_AS_LEADERS = 1;
   public static final byte BELLY_OF_THE_BEAST = 2;
   public static final byte BRUCE_IS_LOOSE = 3;
   public static final byte CAPTAIN_CRUNCH = 4;
   public static final byte DEADLY_WHEN_DIZZY = 5;
   public static final byte DEAR_DIARY = 6;
   public static final byte DOROTHY_GALES_REVENGE = 7;
   public static final byte DOUBLE_UP = 8;
   public static final byte DRAGONSLAYER = 9;
   public static final byte EVERYBODYS_HERO = 10;
   public static final byte GHOST_TOWN = 11;
   public static final byte GLOBETROTTER = 12;
   public static final byte GOLD_DIGGER = 13;
   public static final byte FERMENTED_IN_YOUR_MIND = 14;
   public static final byte HANDS_OF_STONE = 15;
   public static final byte HMS_BOUNTY = 16;
   public static final byte HOLD_YER_GROUND = 17;
   public static final byte HUMBLE_BUMBLE = 18;
   public static final byte INDY_2500 = 19;
   public static final byte INFECTIOUS_GROOVES = 20;
   public static final byte KAIJU_KILLER = 21;
   public static final byte KILLED_BY_DEATH = 22;
   public static final byte KNOW_WHEN_TO_HOLD_EM = 23;
   public static final byte LEGENDARY_CRAFTER = 24;
   public static final byte LIVING_PROOF = 25;
   public static final byte MARRYING_UP = 26;
   public static final byte MAXIMUS_OVERDRIVE = 27;
   public static final byte MERCHANT_MAC = 28;
   public static final byte MINER_2049ER = 29;
   public static final byte MORE_HUMAN_THAN_HUMAN = 30;
   public static final byte NAME_OF_THE_GAME = 31;
   public static final byte OH_GOOD_DOG = 32;
   public static final byte ONE_ROUND_DOWN = 33;
   public static final byte PHILANTHROPIST = 34;
   public static final byte POTION_CONTROL = 35;
   public static final byte PUZZLE_SLAYER = 36;
   public static final byte RETURN_TO_CASTLE_WOLFENSTEIN = 37;
   public static final byte RETURN_TO_SENDER = 38;
   public static final byte SCENE_24 = 39;
   public static final byte SCRAMBLED_SMAUG = 40;
   public static final byte SPEAK_AND_SPELL = 41;
   public static final byte TASK_MASTER = 42;
   public static final byte THE_LUNCH_THAT_TIME_FORGOT = 43;
   public static final byte THE_RIDDLER = 44;
   public static final byte THE_RIDE_THE_RAINBOW_ACHIEVEMENT = 45;
   public static final byte TRAPPER_KEEPER = 46;
   public static final byte TWISTED_SISTER = 47;
   public static final byte WALKING_PAPERS = 48;
   public static final byte ZOMBIE_PETE = 49;
   public static final byte NUM_ACHIEVEMENTS = 50;

   public static String getAchievement(int index)
   {
      if(index == ADMIRAL_OF_THE_NAVY)  
         return "Admiral of the Navy - acquire a fleet of 5 ships";
      if(index == ANIMALS_AS_LEADERS)  
         return "Animals as Leaders - simultaneously travel with a horse and a dog";
      if(index == BELLY_OF_THE_BEAST)  
         return "Belly of the Beast - carve thy way out of the innards of a beast";
      if(index == BRUCE_IS_LOOSE)  
         return "Bruce is Loose - slay a shark whilst in the water with it";
      if(index == CAPTAIN_CRUNCH)  
         return "Captain Crunch - destroy another ship with cannon fire";
      if(index == DEADLY_WHEN_DIZZY)  
         return "Deadly when Dizzy - defeat an enemy in battle when seduced or sullied";
      if(index == DEAR_DIARY)  
         return "Dear Diary - complete thy Journal";
      if(index == DOROTHY_GALES_REVENGE)  
         return "Dorothy Gale's revenge - kill a large beast with a vortex";
      if(index == DOUBLE_UP)  
         return "Double Up - win a game of swine with less than 50 gold to start";
      if(index == DRAGONSLAYER)  
         return "Dragonslayer - slay a dragon and don its scales as armor";
      if(index == EVERYBODYS_HERO)  
         return "Everybody's Hero - Rescue 50 civilians from captivity";
      if(index == GHOST_TOWN)  
         return "Ghost Town - completely empty a town or castle of its inhabitants, positive reputation intact";
      if(index == GLOBETROTTER)  
         return "Globetrotter - visit 50 locations";
      if(index == GOLD_DIGGER)  
         return "Gold Digger - use a Brigand's treasure map to find a buried treasure";
      if(index == FERMENTED_IN_YOUR_MIND)  
         return "Fermented in your Mind - ferment a Wisdom Egg in a portal";
      if(index == HANDS_OF_STONE)  
         return "Hands of Stone - defeat an enemy with thy fists";
      if(index == HMS_BOUNTY)  
         return "HMS Bounty - pay off a bounty of 100 to clear thy name";
      if(index == HOLD_YER_GROUND)  
         return "Hold yer Ground - slay a thief in thy home";
      if(index == HUMBLE_BUMBLE)  
         return "Humble Bumble - tame the Abominable One with rations";
      if(index == INDY_2500)  
         return "Indy 2500 - discover a hidden temple dungeon";
      if(index == INFECTIOUS_GROOVES)  
         return "Infectious Grooves - move a royal guard with music";
      if(index == KAIJU_KILLER)  
         return "Kaiju Killer - Slay a monster king attacking a city";
      if(index == KILLED_BY_DEATH)  
         return "Killed by Death - slay lady death";
      if(index == KNOW_WHEN_TO_HOLD_EM)  
         return "Know when to Hold 'em - defeat a demon in a game of swine";
      if(index == LEGENDARY_CRAFTER)  
         return "Legendary Crafter - upgrade a weapon to legendary status";
      if(index == LIVING_PROOF)  
         return "Living Proof - survive a mine collapse";
      if(index == MARRYING_UP)  
         return "Marrying Up - marry a person with higher reputation";
      if(index == MAXIMUS_OVERDRIVE)  
         return "Maximus Overdrive - defeat all arena challenges";
      if(index == MERCHANT_MAC)  
         return "Merchant Mac - sell a legendary item to an armsmith";
      if(index == MINER_2049ER)  
         return "Miner 2049er - discover a gem while mining for gold";
      if(index == MORE_HUMAN_THAN_HUMAN)  
         return "More Human than Human - purge a Vampyric or Wolfen curse";
      if(index == NAME_OF_THE_GAME)  
         return "Name of the Game - meet the creator of Ultima";
      if(index == OH_GOOD_DOG)  
         return "Oh Good Dog - command thy dog to chase down a city thief";
      if(index == ONE_ROUND_DOWN)  
         return "One Round Down - survive one year in game time";
      if(index == PHILANTHROPIST)  
         return "Philanthropist - donate 500 gold to the needy";
      if(index == POTION_CONTROL)  
         return "Potion Control - possess at least one of every kind of potion";
      if(index == PUZZLE_SLAYER)  
         return "Puzzle Slayer - solve each of the three puzzles";
      if(index == RETURN_TO_CASTLE_WOLFENSTEIN)  
         return "Return to Castle Wolfenstein - escape the future";
      if(index == RETURN_TO_SENDER)  
         return "Return to Sender - steal back an item from a city thief that they stole from you";
      if(index == SCENE_24)  
         return "Scene 24 - pass the Keeper of the Bridge";
      if(index == SCRAMBLED_SMAUG)  
         return "Scrambled Smaug - eat a dragon's egg";
      if(index == SPEAK_AND_SPELL)  
         return "Speak and Spell - learn every magical spell";
      if(index == TASK_MASTER)  
         return "Task Master - complete 50 missions";
      if(index == THE_LUNCH_THAT_TIME_FORGOT)  
         return "The Lunch that Time Forgot - cleave rations from a beast of another time";
      if(index == THE_RIDDLER)  
         return "The Riddler - learn all riddles";
      if(index == THE_RIDE_THE_RAINBOW_ACHIEVEMENT)  
         return "The Ride The Rainbow Achievement - tame and ride a unicorn";
      if(index == TRAPPER_KEEPER)  
         return "Trapper Keeper - possess one of each kind of pelt";
      if(index == TWISTED_SISTER)  
         return "Twisted Sister - slay thy demonic mimic";
      if(index == WALKING_PAPERS)  
         return "Walking Papers - have a spouse decide to leave you";
      if(index == ZOMBIE_PETE)  
         return "Zombie Puff - bring a Dragon raised from the dead onto a battlefield";
      return "? - ?";
   }
   
   public static String getAchievementName(int index)
   {
      String ach = getAchievement(index);
      String [] parts = ach.split(" - ");
      if(parts.length >= 2)
         return parts[0].trim();
      return "?";
   }
   
   public static String getAchievementUnlockMessage(int index)
   {
      String ach = getAchievementName(index);
      if(ach.toLowerCase().startsWith("the "))  //remove "the" from start of achievement name
         ach = ach.substring(4);
      String ans = "You unlocked the " + ach + " achievement!";
      return ans;
   }
        
   public static void earnAchievement(int index)
   {
      if(index < 0 || index >= NUM_ACHIEVEMENTS)
         return;
      if(CultimaPanel.achievements[index] == 0)
      {
         CultimaPanel.achievements[index] = 1;
         CultimaPanel.player.addExperience(Math.max(100,Math.abs(CultimaPanel.player.getExpToLevelUp() - CultimaPanel.player.getExperience())/2));
         CultimaPanel.sendMessage(getAchievementUnlockMessage(index));
         writeOutAchievements("data/achievements.txt");
      
         if(CultimaPanel.time >=8 && CultimaPanel.time <= 16 && CultimaPanel.rainbowAlpha==0 && !TerrainBuilder.indoors() && CultimaPanel.weather <= 2)
         {
            CultimaPanel.rainbowFrame = CultimaPanel.numFrames + ((CultimaPanel.animDelay*3) * Player.rollDie(50,100));
            CultimaPanel.rainbowAlpha = 1;
         }
         if(index == NAME_OF_THE_GAME)    //your reputation goes up by meeting with Garriott
            CultimaPanel.player.addReputation(50);
      }
   }     


//****************Achievements
   public static byte[] readAchievements(String fileName)
   {
      try{
         Scanner input = new Scanner(new FileReader(fileName));
         CultimaPanel.achievements = new byte[Achievement.NUM_ACHIEVEMENTS];
         boolean encoded = false;
         if(input.hasNextLine())
         {
            String line = input.nextLine().trim();       //read in whether or not the file is encoded, and needs to be decoded
            int pos = line.indexOf("**");                //this will be a random integer.  If it is even, then the file is encoded.
            if(pos >= 0)
               line = line.substring(0,pos).trim();
            int enc = 0;
            if(FileManager.wordIsInt(line))
               enc = Integer.parseInt(line);
            if(enc % 2 == 0)
               encoded = true;  
         }
         if(input.hasNextLine())       //read in achievements
         {
            String line = FileManager.decode(input.nextLine().trim(), encoded);       //read in achievements earned, 0 or 1 for false or true
            int pos = line.indexOf("**");
            if(pos >= 0)
               line = line.substring(0,pos).trim();
            String [] parts = line.split(" ");
            if(parts.length == Achievement.NUM_ACHIEVEMENTS)
            {
               for(int i=0; i<parts.length; i++)
               {
                  String s = parts[i].trim();
                  if(!FileManager.wordIsInt(s))
                     break;
                  CultimaPanel.achievements[i] = Byte.parseByte(s);
               }
            }
            else
            {
               System.out.println("readAchievements error: wrong number of values in file");
               return new byte[Achievement.NUM_ACHIEVEMENTS];
            }      
         }
         input.close();
         return CultimaPanel.achievements;
      }
      catch (IOException ex)			//file is corrupted or doesn't exist - remake the file
      {
         System.out.println("readAchievements error:"+ex);
         return new byte[Achievement.NUM_ACHIEVEMENTS];
      }		
   }

   public static boolean writeOutAchievements(String fileName)
   {
      File imageFile = new File(fileName);
      if(imageFile.exists())		//make sure to write over old file
      {
         imageFile.delete();
         imageFile = new File(fileName);
      }
      PrintWriter writer = null;
      try{
         writer = new PrintWriter(imageFile);
         if(CultimaPanel.ENCODE_FILES)
            writer.println(Player.rollDie(1,1000)*2);    //starting with an even number means that we want to encode the strings out to the file
         else
            writer.println((Player.rollDie(1,1000)*2)+1);
         for(byte num:CultimaPanel.achievements)
            writer.print(num+" ");
         writer.println("**achievements earned");   
         writer.close();
      }
      catch(Exception ex){
         System.out.println("writeOutAchievements error:"+ex);
         return false;
      }
      return true;
   }

}