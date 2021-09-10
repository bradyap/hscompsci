import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.io.File;

public class CharacterBuilder
{
   protected static final byte NAME = 0;
   protected static final byte IMAGEICON = 1;
   protected static final byte SUGGEST_WEAPON = 2;
   protected static final byte MIGHT = 3;
   protected static final byte MIND = 4;
   protected static final byte AGILITY = 5;
   protected static final byte AWARENESS = 6;
   protected static final byte AFFLICTION = 7;
   protected static final byte RANDOM = 8;
   protected static final byte NUM_CHOICES = 9;
   
   private static final String [] choices = {
   "thy character's name",                                          /*NAME*/
   "thy character's weapon of choice",                              /*IMAGEICON*/
   "set to suggest weapon based on current attributes",             /*SUGGESTED_WEAPON*/
   "attack/defence of melee weapons, max health, 1 point",          /*MIGHT*/
   "quanitiy and quality of magic and resistence to, 1 point",      /*MIND*/
   "attack/defence of ranged weapons, speed, theft, 1 point",       /*AGILITY*/
   "mapping, trap detection, knowledge of others, 10 points",       /*AWARENESS*/
   "starting circumstances to overcome as a mission",               /*AFFLICTION*/
   "create a random character"                                      /*RANDOM*/
   };
   
   protected static final byte NONE = 0;
   protected static final byte BOUNTY = 1;
   protected static final byte FRAMED = 2;
   protected static final byte VAMPIRE = 3;
   protected static final byte WEREWOLF = 4;
   protected static final byte NUM_AFFLICTIONS = 5;
   
   private static final String [] afflictionTypes = {"none", "bounty", "framed", "vampire", "werewolf"};
   
   public static void scrollLeft()
   {
      if(CultimaPanel.selAttribute == NAME)
         CultimaPanel.CBname = Utilities.nameGenerator("character");
      else if(CultimaPanel.selAttribute == IMAGEICON)
      {
         CultimaPanel.CBimageIndex--;
         if(CultimaPanel.CBimageIndex<0)
            CultimaPanel.CBimageIndex = 0;
      }
      else if(CultimaPanel.selAttribute == SUGGEST_WEAPON)
      {
         CultimaPanel.CBimageIndex = getSuggestedCharacter(CultimaPanel.CBmight, CultimaPanel.CBmind, CultimaPanel.CBagility);
      }
      else if(CultimaPanel.selAttribute == MIGHT)
      {
         CultimaPanel.CBmight--;
         if(CultimaPanel.CBmight<3)
            CultimaPanel.CBmight = 3;
      } 
      else if(CultimaPanel.selAttribute == MIND)
      {
         CultimaPanel.CBmind--;
         if(CultimaPanel.CBmind<3)
            CultimaPanel.CBmind = 3;
      }
      else if(CultimaPanel.selAttribute == AGILITY)
      {
         CultimaPanel.CBagility--;
         if(CultimaPanel.CBagility<3)
            CultimaPanel.CBagility = 3;
      } 
      else if(CultimaPanel.selAttribute == AWARENESS)
      {
         CultimaPanel.CBawareness--;
         if(CultimaPanel.CBawareness<0)
            CultimaPanel.CBawareness = 0;
      }
      else if(CultimaPanel.selAttribute == AFFLICTION)
      {
         CultimaPanel.CBaffliction--;
         if(CultimaPanel.CBaffliction<0)
            CultimaPanel.CBaffliction = 0;
      } 
      else if(CultimaPanel.selAttribute == RANDOM)
      {
         CultimaPanel.player = null;
         File imageFile = new File("data/player.txt");
         imageFile.delete();
         CultimaPanel.missionStack = new LinkedList<Mission>();
         imageFile = new File("data/missions.txt");
         imageFile.delete();
         Utilities.clearNPCMemory();
         CultimaPanel.restartGame();
      }
   }
   
   public static void scrollRight()
   {
      if(CultimaPanel.selAttribute == NAME)
         CultimaPanel.CBname = Utilities.nameGenerator("character");
      else if(CultimaPanel.selAttribute == IMAGEICON)
      {
         CultimaPanel.CBimageIndex++;
         if(CultimaPanel.CBimageIndex>Player.LUTE)
            CultimaPanel.CBimageIndex = Player.LUTE;
      } 
      else if(CultimaPanel.selAttribute == SUGGEST_WEAPON)
      {
         CultimaPanel.CBimageIndex = getSuggestedCharacter(CultimaPanel.CBmight, CultimaPanel.CBmind, CultimaPanel.CBagility);
      }  
      else if(CultimaPanel.selAttribute == MIGHT)
      {
         if(CultimaPanel.CBmight + (CultimaPanel.CBmind-3) + (CultimaPanel.CBagility-3) + (CultimaPanel.CBawareness*10) < 50)
            CultimaPanel.CBmight++;
      } 
      else if(CultimaPanel.selAttribute == MIND)
      {
         if((CultimaPanel.CBmight-3) + CultimaPanel.CBmind + (CultimaPanel.CBagility-3) + (CultimaPanel.CBawareness*10) < 50)
            CultimaPanel.CBmind++;
      }
      else if(CultimaPanel.selAttribute == AGILITY)
      {
         if((CultimaPanel.CBmight-3) + (CultimaPanel.CBmind-3) + CultimaPanel.CBagility + (CultimaPanel.CBawareness*10) < 50)
            CultimaPanel.CBagility++;
      } 
      else if(CultimaPanel.selAttribute == AWARENESS)
      {
         if((CultimaPanel.CBmight-3) + (CultimaPanel.CBmind-3) + (CultimaPanel.CBagility-3) + (CultimaPanel.CBawareness*10) <= 40)
            CultimaPanel.CBawareness++;
         if(CultimaPanel.CBawareness > 4)
            CultimaPanel.CBawareness = 4;   
      }
      else if(CultimaPanel.selAttribute == AFFLICTION)
      {
         CultimaPanel.CBaffliction++;
         if(CultimaPanel.CBaffliction >= NUM_AFFLICTIONS)
            CultimaPanel.CBaffliction = NUM_AFFLICTIONS-1;
      }
      else if(CultimaPanel.selAttribute == RANDOM)
      {
         CultimaPanel.player = null;
         File imageFile = new File("data/player.txt");
         imageFile.delete();
         CultimaPanel.missionStack = new LinkedList<Mission>();
         imageFile = new File("data/missions.txt");
         imageFile.delete();
         Utilities.clearNPCMemory();
         CultimaPanel.restartGame();
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

   public static void show(Graphics g)
   {	 
      int SIZE = CultimaPanel.SIZE;
      int SCREEN_SIZE = CultimaPanel.SCREEN_SIZE;
      Font readable = new Font("Serif", Font.BOLD | Font.ITALIC, SIZE);
      Font readableP = new Font("Serif", Font.PLAIN | Font.ITALIC, SIZE);  
      Font handWriten = new Font("Pristina", Font.BOLD | Font.ITALIC, SIZE);
      Font title = new Font("Old English Text MT", Font.BOLD, SIZE);
      Font oldP = new Font("Old English Text MT", Font.PLAIN, SIZE);
   
      g.setColor(new Color(0, 0, 127));		         //draw a blue boarder around the board
      g.fillRect(0,0, SIZE*SCREEN_SIZE, SIZE*SCREEN_SIZE);
      int x = SIZE;
      int y = SIZE;  
   
      g.setFont(oldP);
      if(CultimaPanel.selAttribute == NAME)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Name:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == NAME)
         g.setColor(Color.white);
      else
         g.setColor(Color.red);
      g.drawString(CultimaPanel.CBname, x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      //g.drawString(choices[NAME], x, y);
      //y += SIZE;
      y += SIZE;
      
      if(CultimaPanel.selAttribute == IMAGEICON)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Weapon preference:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == IMAGEICON)
         g.setColor(Color.white);
      else
         g.setColor(Color.red);
      String weapName = Weapon.getWeaponType(CultimaPanel.CBimageIndex);
      weapName = weapName.substring(0,weapName.length()-1);
      if(CultimaPanel.CBimageIndex==0)
         weapName = "none";
      g.drawString(weapName, x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      //g.drawString(choices[IMAGEICON], x, y);
      //y += SIZE;
      y += SIZE;
   
      if(CultimaPanel.selAttribute == SUGGEST_WEAPON)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Suggest weapon:    ", x, y);
      y += SIZE;
      g.setColor(Color.orange);
      g.drawString(choices[SUGGEST_WEAPON], x, y);
      y += SIZE;
      y += SIZE;
   
      if(CultimaPanel.selAttribute == MIGHT)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Might:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == MIGHT)
         g.setColor(Color.white);
      else if((CultimaPanel.CBmight) + (CultimaPanel.CBmind) + (CultimaPanel.CBagility) + (CultimaPanel.CBawareness*10) < 50)
         g.setColor(Color.green);
      else
         g.setColor(Color.red);
      g.drawString(""+(CultimaPanel.CBmight), x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      g.drawString(choices[MIGHT], x, y);
      y += SIZE;
      y += SIZE;
      
      if(CultimaPanel.selAttribute == MIND)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Mind:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == MIND)
         g.setColor(Color.white);
      else if((CultimaPanel.CBmight) + (CultimaPanel.CBmind) + (CultimaPanel.CBagility) + (CultimaPanel.CBawareness*10) < 50)
         g.setColor(Color.green);
      else
         g.setColor(Color.red);
      g.drawString(""+(CultimaPanel.CBmind), x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      g.drawString(choices[MIND], x, y);
      y += SIZE;
      y += SIZE;
   
      if(CultimaPanel.selAttribute == AGILITY)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Agility:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == AGILITY)
         g.setColor(Color.white);
      else if((CultimaPanel.CBmight) + (CultimaPanel.CBmind) + (CultimaPanel.CBagility) + (CultimaPanel.CBawareness*10) < 50)
         g.setColor(Color.green);
      else
         g.setColor(Color.red);
      g.drawString(""+(CultimaPanel.CBagility), x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      g.drawString(choices[AGILITY], x, y);
      y += SIZE;
      y += SIZE;
      
      if(CultimaPanel.selAttribute == AWARENESS)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Awareness:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == AWARENESS)
         g.setColor(Color.white);
      else if((CultimaPanel.CBmight) + (CultimaPanel.CBmind) + (CultimaPanel.CBagility) + (CultimaPanel.CBawareness*10) < 50)
         g.setColor(Color.green);
      else
         g.setColor(Color.red);
      g.drawString(""+(CultimaPanel.CBawareness), x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      g.drawString(choices[AWARENESS], x, y);
      y += SIZE;
      y += SIZE;
      
      if(CultimaPanel.selAttribute == AFFLICTION)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Starting affliction:    ", x, y);
      g.setFont(readable);
      if(CultimaPanel.selAttribute == AFFLICTION)
         g.setColor(Color.white);
      else
         g.setColor(Color.red);
      g.drawString(afflictionTypes[CultimaPanel.CBaffliction], x+(SIZE*8), y);
      y += SIZE;
      g.setColor(Color.orange);
      g.setFont(oldP);
      g.drawString(choices[AFFLICTION], x, y);
      y += SIZE;
      y += SIZE;
      
      if(CultimaPanel.selAttribute == RANDOM)
         g.setColor(Color.white);
      else
         g.setColor(Color.orange);
      g.drawString("Random Build", x, y);
      y += SIZE;
      g.setColor(Color.orange);
      g.drawString(choices[RANDOM], x, y);
      y += SIZE;
      y += SIZE;
      
      //draw the player's image in the right info-bar if you have information on it                                     
      ImageIcon temp = null;
      if(CultimaPanel.CBimageIndex>=0 && CultimaPanel.CBimageIndex<=Player.LUTE)
         temp = new ImageIcon(Player.characters[CultimaPanel.CBimageIndex][0]);
      drawPictureInFrame(g, temp);
      x = SIZE * SCREEN_SIZE;
      y = SIZE * 11;
      g.setColor(new Color(0, 0, 127));
      g.drawString(getWeaponInfo(CultimaPanel.CBimageIndex), x, y+=SIZE);
      g.drawString(getAfflictionInfo(CultimaPanel.CBaffliction), x, y+=SIZE);
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
      g.drawString("(C)ommit to this character and start.", x, y);
      y += SIZE;
   
      y = (SIZE * SCREEN_SIZE) - (SIZE * 7);
      g.setColor(new Color(0, 0, 0));
      g.setFont(readableP);
      g.drawString("(UP-DOWN)", x, y);
      g.setFont(new Font("Old English Text MT", Font.PLAIN, SIZE));
      g.drawString("                         to select attribute", x, y);
      y += SIZE;
      g.setFont(readableP);
      g.drawString("(LEFT-RIGHT)", x, y);
      g.setFont(new Font("Old English Text MT", Font.PLAIN, SIZE));
      g.drawString("                         to change values", x, y);
      y += SIZE;
      g.setFont(readableP);
      g.drawString("(M)ap  (I)nventory  (J)ournal(ESC)", x, y);
   //show scrolling message array
      y = (SIZE * SCREEN_SIZE) - (SIZE * 4);
      if(CultimaPanel.talkSel && CultimaPanel.selected!=null)
         g.setColor(new Color(30, 255, 30));
      else
         g.setColor(new Color(0, 0, 127));
   
      g.fillRect(x, y-SIZE ,  (SIZE * SCREEN_SIZE), (SIZE * 5));
      if(CultimaPanel.talkSel && CultimaPanel.selected!=null)
         g.setColor(new Color(127, 0, 0));
      else
         g.setColor(new Color(255, 0, 0));
      g.setFont(readable);
      y = y - (SIZE/4);
      for(int i=0; i<CultimaPanel.message.length; i++)
      {
         String sentence =CultimaPanel.message[i];
         if(i == CultimaPanel.message.length-1 && CultimaPanel.typed.length() > 0)
            sentence = "~"+CultimaPanel.typed;
         String tempSent = sentence.toLowerCase();
         if(!tempSent.startsWith("~"))           //dialogue with NPCs will start with a ~
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

   public static byte getSuggestedCharacter(int might, int mind, int agility)
   {
      if(Math.abs(might - mind) <= 4 && Math.abs(might - agility) <= 4 && Math.abs(mind - agility) <= 4)
         return Player.TORCH;
      if(might>mind && might>agility && agility>mind)       //might, agility, mind
         return Player.HAMMER;
      if(might>=mind && might>=agility && mind>=agility)    //might, mind, agility
         return Player.SABER;
      if(agility>=might && agility>=mind && mind>=might)    //agility, mind, might
         return Player.BOW;
      if(agility>might && agility>mind && might>mind)       //agility, might, mind
         return Player.SPEAR;
      if(mind>might && mind>agility && might>agility)       //mind, might, agility
         return Player.LONGSTAFF;
      if(mind>=might && mind>=agility && agility>=might)    //mind, agility, might
         return Player.STAFF;
      return Player.DAGGER;
   }
   
   public static String getWeaponInfo(byte imageIndex)
   {
      if(imageIndex == Player.NONE) 
         return "No gold-Awareness,knowledge bonus";
      if(imageIndex == Player.TORCH) 
         return "Awareness,gold,floretbox bonus";
      if(imageIndex == Player.STAFF) 
         return "One magic spell added";
      if(imageIndex == Player.LONGSTAFF) 
         return "One magic spell added";
      if(imageIndex == Player.SPEAR) 
         return "";
      if(imageIndex == Player.BOW) 
         return "25 arrows added";
      if(imageIndex == Player.CROSSBOW) 
         return "25 arrows added";
      if(imageIndex == Player.AXE)
         return "";
      if(imageIndex == Player.DUALAXE)
         return "";   
      if(imageIndex == Player.HAMMER) 
         return "";
      if(imageIndex == Player.DAGGER) 
         return "";
      if(imageIndex == Player.SABER) 
         return "";
      if(imageIndex == Player.SWORDSHIELD) 
         return "";
      if(imageIndex == Player.DUAL) 
         return "";
      if(imageIndex == Player.LUTE) 
         return "Gold bonus";
      return "";
   }

   public static String getAfflictionInfo(int affliction)
   {
      if(affliction == BOUNTY) 
         return "-50 reputation,100 bounty,+1 to all stats";
      if(affliction == FRAMED) 
         return "-750 reputation,25 bounty,+2 to all stats";
      if(affliction == VAMPIRE) 
         return "Vampyric curse,4 spells added,awareness bonus";
      if(affliction == WEREWOLF) 
         return "Wolfen curse,x2 strength,awareness as wolf";
      return "";
   }
}