import java.util.ArrayList;

public class Story
{
  //Each possible player-class starting story upon character generation:
  //Each string is a line of text - can be no more than 40 characters long but can be less.
  //Each story can have no more than 16 lines of text, but could have less.
  //characters start at the entrace to a city.  Narratives should end with that.

   //a rare player that starts with no weapons and no gold
   public static final String[] storyUnarmed = {
   "Mine is a tale of sorrow:           ",
   "'Tis the way the tides turn: thy    ",
   "life has placed me with nothing     ",
   "but the threads on thy frame.  Is it",
   "revenge that I seek? Redemption?    ",
   "It seems that thy head has spun and ",
   "confused the two so many times that ",
   "my memory escapes me.  What is it   ",
   "in the aft of thy mind?  A number...",
   "What is the number that is calling  ",
   "to thee in a voice that echoes as in",
   "a chamber...with a red square...and ",
   "red lines that cross in the center? ",
   "With bare hands and empty satchel,  ",
   "I find myself upon the gates of     ",
   "a bustling city.                    "
   };
   
   //a well rounded player where all stats are within 4 points of each other
   public static final String[] storyExplorer = {
   "The explorer's story:               ",
   "The world around me has inspired a  ",
   "thirst for discovery and adventure. ",
   "Throughout thy life, others describe",
   "me as a Jack of all trades, master  ",
   "of none. But a strange deam has     ",
   "pushed thee to seek greatness and   ",
   "challenge. There is a red square,   ",
   "made from four, and a number whose  ",
   "weight I feel but name I can not    ",
   "say. I know not it's meaning, but I ",
   "feel the urgency of it's call. With ",
   "nothing but an explorer's torch and ",
   "a satchel of gold coins, I set out  ",
   "and find myself upon the gates of   ",
   "a bustling city.                    "
   };
   
   //an agile player that starts with a bow (agility>=might && agility>=mind && mind>=might)
   public static final String[] storyBow = {
   "The hunter's story:                 ",
   "'Tis a fine day to be an archer:    ",
   "thy aim is steady, and thy game does",
   "dash in and out of the thickened    ",
   "forest as if the Spring has dawned  ",
   "twice over.  But thy innards have   ",
   "pulled me over the hill, and the    ",
   "hill over the hill, to new places.  ",
   "There is a number I see in thy      ",
   "mind: it cannot be placed, but it   ",
   "seems to direct thee to lands anew. ",
   "Is it a vision from Skara Brae?     ",
   "I imagine a reddened square, lines  ",
   "crossing the center, with shuffling ",      
   "feet that finds me at the gates of  ",
   "a bustling city.                    "
   };
   
   //a rare player that starts with a dagger
   public static final String[] storyDagger = {
   "The assassin's story:               ",
   "A life of danger and excitement that",
   "once sent thy pulse racing is now   ",
   "casting weight on my mind like the  ",
   "chains of the imprisoned.  'Tis the ",
   "most difficult hinge of the job:    ",
   "the mind wonders if thy target      ",
   "deserves their fate at the end of   ",
   "my blade. Is the coin worth it all? ",
   "Thy mind has shifted from thy deadly",
   "work: the number...the red square...",
   "two lines intersecting their center.",
   "What does it mean, this vision?     ",
   "What are these strange thoughts that",
   "pulls me to wander into the gates   ",
   "of a strange, bustling city..."
   };
               
   //a rare strong and agile player that starts with a brigand king's dual blades
   public static final String[] storyDual = {
   "The Brigandking's story:            ",
   "It was time for thee to make my     ",
   "escape.  After 60 moons of serving  ",
   "thy general in the Brigand Army, the",
   "visions became stronger, and my     ",
   "time under command did wilt to the  ",
   "calling of Skara Brae.  She has     ",
   "shown thee a vision!  A number cast ",
   "upon a redened square, with lines   ",
   "meeting in thy center.  The number  ",
   "and thy meaning escapes thee, but   ",
   "drives me to escape.  I steal       ",
   "thy Brigandking's dual blades and   ",
   "slip into the night, traveling, when",
   "I find myself upon the gates of     ",
   "a bustling city."
   };
//TO DO: axe and dual-axe story
   public static final String[] storyAxe = {
   "The lumberman's story:              ",
   "Swinging my axe at the tree's base  ",
   "has made a fine life for me. I have ",
   "raised a house and a family on coin ",
   "earned by felling lumber. But upon  ",
   "the creaking of the last great pine ",
   "to fall by my blade, a voice called ",
   "out from the very wood - a strange  ",
   "language that cast a vision in my   ",
   "mind. There is a red square, made of",
   "four squares, and a number of great ",
   "import that calls me to travel - a  ",
   "mystery that demands a journey to   ",
   "places far from my pine built home. ",
   "I find myself upon the gates of     ",
   "a bustling city."
   };
   
   public static final String[] storyDualAxe = {
   "The wood crafter's story:           ",
   "'Tis not without merrit - swinging  ",
   "one-handed axes, with haste and     ",
   "precision to shape thy wooden pieces",
   "before the master crafter's hands do",
   "the finishing work. But my mind has ",
   "taken a strange turn - a recurring  ",
   "vision from childhood has echoed    ",
   "back into my mind. The red square,  ",
   "lines intersecting in its center... ",
   "and the number my mind can not see  ",
   "but weight I feel. I am called to   ",
   "strange lands to seek out their     ",
   "meaning, and after 12 moons of brisk",
   "travel, I find myself upon the      ",
   "gates of a bustling city."
   };
   
  //a strong player that starts with a hammer (might>mind && might>agility && agility>mind)
   public static final String[] storyHammer = {
   "The miner's story:                  ",
   "My arms have grown mighty from      ",
   "years of swinging the hammer. 'Tis  ",
   "good work in the mines, and the     ",
   "hills bring enough gold to keep thy ",
   "city prosperous.  But all changed   ",
   "when falling ore did cast a potent  ",
   "image into my mind: a redened       ",
   "square, two lines meeting in its    ",
   "center, and a number that thee can  ",
   "not trace. 'Tis too important to    ",
   "carry on in the mines: it is travel ",
   "that calls thee to find the meaning ",
   "of these strange visions.           ",
   "I find myself upon the gates of     ",
   "a bustling city."
   };

   //a smart player that starts with a longstaff (mind>might && mind>agility && might>agility)
   public static final String[] storyLongstaff = {
   "The monk's story:                   ",
   "20 moons with the scrolls has lifted",
   "thy mind into new waters: the school",
   "has served to prime my body to      ",
   "wield thy longstaff, and focus my   ",
   "mind to accept the strange powers of",
   "the mages.  But a new light has     ",
   "pulsed into thy visions, scattered  ",
   "as if behind waters, unclear as to  ",
   "their meaning. The number I hear    ",
   "the mages whispering about: I feel  ",
   "its weight, calling me to leave thy ",
   "school and travel to new lands. 'Tis",
   "swift travel that casts me, where   ",
   "I find myself upon the gates of     ",
   "a bustling city."
   };

   //a strong player that starts with a sword (might>=mind && might>=agility && mind>=agility)
   public static final String[] storySword = {
   "The adventurer's story:             ",
   "The sharp notes of clashing blades  ",
   "fills thee with promise, training   ",
   "battle with the village guards.  The",
   "life of combat drives thy purpose,  ",
   "but my once scattered dreams are    ",
   "taking similar form from one night  ",
   "to the next: crossing lines, held   ",
   "within a red square, and a number,  ",
   "so important, but whose weight is   ",
   "beyond thy reach. 'Tis time to test ",
   "my skills by thrusting into the     ",
   "unknown: travel to strange lands to ",
   "uncover the meaning of my dreams.   ",
   "I find myself upon the gates of     ",
   "a bustling city.                    "
   };

   //an agile player that starts with a spear (agility>might && agility>mind && might>mind)
   public static final String[] storySpear = {
   "The guard's story:                  ",
   "'Tis hard to find one's place: my   ",
   "time on the ships has passed, now   ",
   "on solid footing. But standing all  ",
   "day at the doors of thy local armory",
   "to shield thy city from brigands and",
   "thieves, my mind wanders with the   ",
   "days that seem to grow longer. 'Twas",
   "sudden when a cloaked traveler,     ",
   "moving like the wind, whispers a    ",
   "strange language into thy ear. Is it",
   "a sign from Skara Brae? The number, ",
   "the red square: strange visions fill",
   "thy mind and call me to travel.     ",
   "I soon find myself upon the gates of",
   "a bustling city.                    "
   };

   //a smart player that starts with a magic spell casting staff (mind>=might && mind>=agility && agility>=might)
   public static final String[] storyMagic = {
   "The wizard's story:                 ",
   "Scrolls and books were always a     ",
   "closer distance to thy center than  ",
   "people and adventure. My master has ",
   "strengthened my mind to accept the  ",
   "incantations of the earth. The      ",
   "banner of thy temple, a red square  ",
   "with lines meeting at the center,   ",
   "seems to mean more to me now than   ",
   "ever before.  The master calls upon ",
   "thy service in the temple center:   ",
   "a quest to discover the forgotten   ",
   "number of Skara Brae, thy godess who",
   "left this plane before time itself. ",
   "I soon find myself upon the gates of",
   "a bustling city.                    "
   };

   //a rare player that starts with a royal guard's sword and shield
   public static final String[] storySwordShield = {
   "The Royal Guard's story:            ",
   "A prodigy of battle, they called me ",
   "over the years. Best of the best,   ",
   "life would seem easy for those that ",
   "are chosen to be the Royal Guard.   ",
   "Why should my heart weigh so heavy? ",
   "My love's mind has fallen to        ",
   "madness: obsessed with discovering  ",
   "the number of Skara Brae.  Drawings ",
   "of red squares, with lines meeting  ",
   "in the middle, litter thy chambers. ",
   "Thy good King has granted me time   ",
   "in travel, to seek answers to the   ",
   "strange visions that haunt my love. ",
   "I find myself upon the gates of     ",
   "a bustling city.                    "
   };
   
   //a rare player that starts with the Lute Of Destiny
   public static final String[] storyLute = {
   "The Royal Musicians's story:        ",
   "I am perceived by many to have a    ",
   "charmed life - born with nimbleness ",
   "of finger, and ear for music and the",
   "fever to learn. I was gifted the    ",
   "fabled Lute Of Destiny by the King  ",
   "himself, to play in the court to the",
   "adoration of all. But my muse over  ",
   "the years has been a secret madness:",
   "visions that plague me of a reddened",
   "square made of four, and a powerful ",
   "number that weighs on my mind. 'Tis ",
   "time to end my suffering and seek   ",
   "answers to my maddening questions.  ",
   "After much travel, I find myself at ",
   "the gates of a strange bustling city"
   };
   
    //an agile and strong player that starts with a crossbow 
   public static final String[] storyCrossbow = {
   "The Enforcer's story:               ",
   "'Twas another day on the brute squad:",
   "life as the ranged enforcer is grim ",
   "work, but my aim is in high demand. ",
   "'Tis we that are called when thy    ",
   "king needs action that is deemed    ",
   "beneath the dignity of the royal    ",
   "guard. Then the last words of one of",
   "our victims has clouded my mind with",
   "strange visions. The number of Skara",
   "Brae, mysterious and potent, and the",
   "reddened square with lines crossing ",
   "the center...All focus is lost until",
   "I find answers to calm my mind. I set",      
   "off in travel, and soon find myself ",
   "upon the gates of a bustling city.  "
   };

//a rare player that starts as a vampire
   public static final String[] storyVampire = {
   "The Vampire's story:                ",
   "Hated, feared, now hunted - my very ",
   "humanity taken from me, and what is ",
   "left? A bloodthirsty savage, a beast",
   "that hides in shadow to drink the   ",
   "life from my own kind. My eyes have ",
   "not seen the sun in years, and it is",
   "in shadow that I escape the torches ",
   "and swords of the city that was once",
   "my home. A chance at redemption has ",
   "been shown to me in strange visions:",
   "a number...a reddened square...with ",
   "red lines that cross in the center? ",
   "Travel at night, sleeping in caves, ",
   "and now I find myself upon the gates",
   "of a strange bustling city.         "
   };
   
   //a rare player that starts as a werewolf
   public static final String[] storyWerewolf = {
   "The Wolfen's story:                ",
   "'Tis been 20 moons since that night",
   "on the moors. I survived the beast's",
   "attack, but left with a curse that ",
   "has taken my family and driven me  ",
   "from my very home. 'Tis on clear   ",
   "nights that I become animal, vicious",
   "but powerful. I am starting to learn",
   "control, but thy burden has forced ",
   "my escape. And each morning after, ",
   "I awake from the same strange dream:",
   "a mysterious number, a reddened     ",
   "square made of four. I seek answers,",
   "I seek survival, I hide, I run,     ",
   "and now I find myself upon the gates",
   "of a strange bustling city.         "
   };
   
   //a rare player that starts as framed for murder
   public static final String[] storyFramed = {
   "The story of the accused:          ",
   "I have been living on the land for ",
   "20 moons now, a bounty on thy head ",
   "for crimes I do not own. Each town,",
   "the same - my misplaced reputation ",
   "follows thee from place to place.  ",
   "Now the strangest of all, visions  ",
   "and voices reach out to me from thy",
   "campfire each night. There is a red",
   "square, made of four, and a voice  ",
   "that echoes a mysterious number in ",
   "a way that can not be traced. It is",
   "answers I seek, to clear my name, my",
   "reputation, and solve the mystery of",
   "thy strange visions. And now I find",
   "myself at the gates of a far city. "
   };
   
   //a rare player that starts with a bounty on their head
   public static final String[] storyBounty = {
   "The story of the Bounty Hunter:    ",
   "Fate it seems is not without irony:",
   "I swore to the code of the Bounty  ",
   "Hunters, and broke ranks when thy  ",
   "very mind cleared with a vision from",
   "Skara Brae herself. She has shown me",
   "red lines intersecting, whispered to",
   "me a sacred number so faint as to  ",
   "escape my vision. I took leave of my",
   "guild and broke ranks, now hunted by",
   "my own kind. I dare not walk city  ",
   "streets at night and watch my every",
   "corner for poisoned blades that turn",
   "towards me. Now seeking answers to ",
   "thy visions, I travel on and find  ",
   "myself at the gates of a far city. "
   };

   //the ordering of the story/start weapon needs to coincide with the weapon number ordering from Player 
   private static String[][] allStories;
   
   public Story()
   {
      intitializeStories();
   }
   
   public static void intitializeStories()
   {
      allStories = new String[Player.FLIGHT+1][16]; 
      allStories[Player.NONE] = storyUnarmed;
      allStories[Player.TORCH] = storyExplorer;
      allStories[Player.STAFF] = storyMagic;
      allStories[Player.LONGSTAFF] = storyLongstaff;
      allStories[Player.SPEAR] = storySpear;
      allStories[Player.BOW] = storyBow;
      allStories[Player.CROSSBOW] = storyCrossbow;
      allStories[Player.AXE] = storyAxe;
      allStories[Player.DUALAXE] = storyDualAxe;
      allStories[Player.HAMMER] = storyHammer;
      allStories[Player.DAGGER] = storyDagger;
      allStories[Player.SABER] = storySword;
      allStories[Player.SWORDSHIELD] = storySwordShield;
      allStories[Player.DUAL] = storyDual;
      allStories[Player.LUTE] = storyLute;
      allStories[Player.BAT] = storyVampire;
      allStories[Player.WOLF] = storyWerewolf;
      allStories[Player.CAMP] = storyFramed;
      allStories[Player.FLIGHT] = storyBounty;
   }
   
   public static String[] getStory(int imageIndex)
   {
      intitializeStories();
      return allStories[imageIndex];
   }
   
   public static ArrayList<String> getStoryList(int imageIndex)
   {
      ArrayList<String> retVal = new ArrayList<String>();
      intitializeStories();
      for(String line: allStories[imageIndex])
         retVal.add(line);
      return retVal;
   }

}