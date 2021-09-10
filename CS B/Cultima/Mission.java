import java.util.ArrayList;
import java.awt.Point;

public class Mission
{
   private String type;             //Clear, Rescue, Find, Assassinate, Battle, Remove, Train, Mercy
   private String startStory;       //start narrative
   private String middleStory;      //narrative while mission is in progress
   private String endStory;         //end narrative
   private String info;             //short version of mission information for a mission log
   private int worldRow, worldCol;  //the location where the mission starts
   private int goldReward;    
   private Item itemReward;   
   private String clearLocation;    //name of the location where a clearing mission or battle takes place - clear out monsters/rescue prisoners
   private Item target;             //item we might have to retrieve
   private String targetHolder;     //name of the person that holds the target
   private int targetRow, targetCol;//where the target is placed (might be at targetRow, targetCol within the clearLocation or the greater world if clearLocation is null
   private boolean failed;          //battle lost 

   public static final byte NOT_STARTED=0;
   public static final byte IN_PROGRESS=1;
   public static final byte FINISHED=2;

//**Missions - each defines the index for the missions array
   public static final byte CLEAR_DUNGEON=0;    //given by NPC.KING
   public static final byte CLEAR_MINE=1;       //given by adventureres (NPC.SWORD)
   public static final byte CLEAR_TEMPLE=2;     //given by NPC.WISE
   public static final byte CLEAR_CAVE=3;       //given by NPC.BEGGER

   public static final byte ASSASSINATE_KING = 4;//given by NPC.WISE
   public static final byte ASSASSINATION=5;    //given by NPC.KING
   public static final byte RESCUE_PRISONERS=6; //given by NPC.KING
   public static final byte PURGE_CITY=7;       //given by NPC.KING
   public static final byte PURGE_HOLY = 8;     //given by NPC.WISE
   public static final byte ZOMBIE_PLAGUE=9;    //given by guard

   public static final byte FIND_ITEM=10;        //given by NPC.KING
   public static final byte FIND_WEAPON=11;      //given by adventureres (NPC.SWORD)
   public static final byte FIND_PELT=12;       //given by begger (NPC.BEGGER)
   public static final byte FIND_LUTE = 13;      //given by NPC.LUTE
   public static final byte FIND_SONG = 14;      //given by NPC.LUTE
   public static final byte FIND_PERSON=15;     //given by commoner (NPC.MAN/NPC.WOMAN)
   public static final byte FIND_PARENT = 16;   //given by NPC.CHILD
   public static final byte FIND_PET = 17;      //given by NPC.CHILD
   public static final byte FIND_PEARL = 18;    //given by NPC.WISE
   public static final byte FIND_MAGIC_ITEM = 19;//given by NPC.WISE
   public static final byte STEAL_PICK = 20;     //given by NPC.JESTER 
   public static final byte STEAL_BOOK = 21;     //given by NPC.JESTER
   public static final byte STEAL_DICE = 22;     //given by NPC.JESTER

   public static final byte STEAL_DAGGERS = 23; //given by NPC.JESTER
   public static final byte DESTROY_STATUE = 24;//given by NPC.JESTER
      
   public static final byte CITY_REVENGE=25;    //given by commoner (NPC.MAN/NPC.WOMAN)
   public static final byte MESSAGE=26;         //given by commoner (NPC.MAN/NPC.WOMAN)
   public static final byte LOVE_TRIANGLE=27;   //given by commoner (NPC.MAN/NPC.WOMAN)

   public static final byte BATTLE=28;          //given by guards  
   public static final byte CASTLE_ASSASSIN=29; //given by royal guard
   public static final byte CASTLE_BEAST=30;    //given by royal guard

   public static final byte MOVE_BEGGER = 31;   //begger mission
   public static final byte CLEAR_BEGGERS = 32; //shoppekeep mission

   public static final byte TRAIN_DOG = 33;     //shoppekeep mission    - target will be an item with name "dog" and targetName will be the shoppekeep we bring it to
   public static final byte TRAIN_HORSE = 34;   //given by royal guard  - target will be an item with name "horse" and targetName will be the royal-guard we bring it to

   public static final byte SLAY_MONSTER = 35;  //given by guard
   public static final byte SLAY_ROBERTS = 36;  //given by port guard
   public static final byte ARENA = 37;         //given by Arenamaster
   public static final byte ARENA_BOUNTY = 38;  //given by Arenamaster
   public static final byte ARENA_RESCUE = 39;  //given by begger
   
   public static final byte TEACH_SPELL = 40;   //given by wise
   
   public static final byte CLEAR_BANDITS = 41; //given by shoppekeep
   
   public static final byte BEAT_SWINE_PLAYER = 42;   //given by townsfolk
   
   public static final byte TREASURE_MAP = 43;  //given by adventurers (NPC.SWORD)

   public static final byte FIND_SERPENT_EGGS = 44;   //given by NPC.WISE (mage's shoppe)
   public static final byte FIND_DRAGON_EGG = 45;     //given by NPC.GOYALGUARD
   
   public static final byte FIRE_FIGHTER = 46;        //given by city MAN or WOMAN

   public static final byte CHASE_THIEF = 47;   //given by city GUARD            
   public static final byte FIND_ELK_PELT = 48; //given by hermit
   public static final byte FIND_GEM = 49;      //given by ironsmith

   public static final byte KILL_WEREWOLF = 50; //given by begger
   
   public static final byte MERCY_KILL = 51;    //given by man/woman
   
   public static final byte WEREWOLF_TOWN = 52; //given by adventurers (NPC.SWORD)

   public static final byte SLAY_VAMPIRE = 53;  //given by townsfolk

   public static final byte GET_BOAT = 54;      //given by port adventurer (NPC.SWORD)
   
   public static final byte DESTROY_SIGNS = 55; //given by CHILD
   public static final byte DESTROY_STATUE2 = 56;//given by CHILD

   public static final byte BURN_COFFINS = 57;  //given by townsfolk (MAN/WOMAN)
   
   public static final byte TAX_COLLECTOR = 58; //given by TAXMAN
   public static final byte FIND_LEDGER = 59;   //given by TAXMAN
   public static final byte TAX_IMPOSTER = 60;  //given by TAXMAN
   public static final byte TAX_MOB = 61;       //given by TAXMAN

   public static final byte MARRIAGE = 62;      //given by WISE

   public static final byte NUM_MISSIONS = 63;


   private byte state;              //is it NOT_STARTED, IN_PROGRESS or FINISHED?

   public static final String[][] missions = {
      //CLEAR_DUNGEON
      {"MONSTER_TYPEs have overrun thy dungeon.  Purge them for GOLD_REWARD gold coins.",                         "MONSTER_TYPEs still lurk in thy dungeon.  Purge them with haste!",                          "Thou has done me great service.  Here is thy pay."},
      //CLEAR_MINE
      {"I've troubles clearing MONSTER_TYPEs from LOCATION_NAME for thy miners.  Finish me quest for GOLD_REWARD gold coins.", "Miners need LOCATION_NAME cleared of MONSTER_TYPEs to work.  Purge them with haste!",       "Thank thee. Here is thy half of thee well deserved pay."},
      //CLEAR_TEMPLE
      {"MONSTER_TYPEs have haunted our sacred temple at LOCATION_NAME.  Smite them for GOLD_REWARD gold coins.",  "MONSTER_TYPEs still haunt our temple at LOCATION_NAME. Worshipers have no place to pray!",  "Our worshipers have returned.  Please taketh thy payment as thanks."},
      //CLEAR_CAVE
      {"'Twas forced out mine shanty at LOCATION_NAME by MONSTER_TYPE. I want not to live among filthy people.",  "I nearly died just walkin' by mine own shanty at LOCATION_NAME. I want to move back in.",   "Thank thee. I have nothing to give, but I will tell others of thy deeds on my way out."},
      //ASSASSINATE_KING
      {"The tyrant NPC_NAME of LOCATION_NAME must be thrown! For the love of liberty, assassinate thy ruler!",    "NPC_NAME of LOCATION_NAME rules over all with a cruel fist. Strike them down to liberate thy people!", "Thou has done great works for these people. Take this precious artifact as our thanks."},
      //ASSASSINATION
      {"NPC_NAME of LOCATION_NAME is a threat to the kingdom.  Smite them for GOLD_REWARD bounty.",               "NPC_NAME of LOCATION_NAME still walks.  Smite them with haste!",                            "Thou has done me great service.  Here is thy pay."},
      //RESCUE_PRISONERS
      {"My subjects are held captive at LOCATION_NAME.  Free them for GOLD_REWARD gold coins.",                   "My subjects still remain at LOCATION_NAME.  Free them with haste!",                         "Thou has done me great service.  Here is thy pay."},
      //PURGE_CITY
      {"The scum of LOCATION_NAME plan to march against thee! Purge all that live there for GOLD_REWARD coins!",  "LOCATION_NAME must be purged of all living things! Execute them at once!",                  "Thou has done me and thy kingdom a great service.  Here is thy pay."},
      //PURGE_HOLY
      {"The heathens of LOCATION_NAME do not believe Skara Brae has wings! They must be driven from their city!", "Drive the infidels from LOCATION_NAME so Skara Brae's chosen ones can seed it at once!",    "Skara Brae herself will reward you for this most holy and noble of feats."},
      //ZOMBIE_PLAGUE
      {"The Deadite plague has overrun LOCATION_NAME. Purge the undead for GOLD_REWARD gold!",                    "LOCATION_NAME must be purged of all the undead before they spread to another town!",        "Thow has done grim work, but for the best of the land! Here is thy well deserved pay."},
      //FIND_ITEM
      {"NPC_NAME of LOCATION_NAME carries a fine ITEM_NAME.  Bring it to me for GOLD_REWARD gold coins.",         "NPC_NAME of LOCATION_NAME still holds the ITEM_NAME I desire.  Bring it to me with haste!", "Thou has done me great service.  Here is thy pay."},
      //FIND_WEAPON
      {"Would thee be able to help a battle veteran? I hath lost mine old ITEM_NAME in LOCATION_NAME.",           "'Twould lift my spirits to have mine old ITEM_NAME. Please go with haste and retrieve it.", "By Skara Brae, I didn't think you'd come back with that.  Here is thy pay."},
      //FIND_PELT
      {"My child's has rags for clothes and will freeze this Winter! Can thou spare a ITEM_NAME?",                "We need the ITEM_NAME to survive the winter. Please help us!",                              "My child is saved thanks to your kindness. I will tell others of your deeds!"},
      //FIND_LUTE
      {"NPC_NAME of LOCATION_NAME has thy Lute Of Destiny! I will give thee GOLD_REWARD coins to bring it to me.","NPC_NAME's Lute Of Destiny calls to me. Bring it to me from LOCATION_NAME for thy gold!",   "For the love of music, thank thee!  Take all the gold I have!"},      
      //FIND_SONG
      {"NPC_NAME of LOCATION_NAME has a page from the Songs of Skara Brae. I will pay GOLD_REWARD coins for it!", "Bring me the page of magic music. NPC_NAME at LOCATION_NAME does not deserve to own it!",   "A page from the Songs of Skara Brae! Now all I need is the Lute of Destiny! Here is thy pay."},
      //FIND_PERSON
      {"My love is lost at LOCATION_NAME.  Please find them for GOLD_REWARD coins!",                              "My love is still lost at LOCATION_NAME.  Please bring them back to me!",                    "I thank thee.  Whilst I can't give you much, here is thy pay."},
      //FIND_PARENT
      {"Mine Pama gone!  Mine Pama!  Mine Pama at LOCATION_NAME!  Find mine Pama!",                               "Mine Pama gone at LOCATION_NAME!  Find mine Pama!  Mine Pama!",                             "Thank thee! Mine Pama back! Thank thee! Take mine coin!"},
      //FIND_PET
      {"Puppy! Puppy! Me want mine own puppy!",                                                                   "Please puppy! Me need mine own puppy!",                                                     "A puppy! Thanks thanks! Take mine coin! A puppy!"},
      //FIND_PEARL
      {"The MONSTER_TYPE of legend has been sighted in the bay. Bring me the Lifepearl for GOLD_REWARD gold!",    "The MONSTER_TYPE is elusive. Retrieve its Lifepearl and I will give you GOLD_REWARD coins!", "'Tis the most magic of items, at last! Here is thy pay!"},
      {"Evil has befallen my apprentice. They hide in LOCATION_NAME with my ITEM_NAME. Return it to me with haste!", "My ITEM_NAME is still held by my sorcerous apprentice at LOCATION_NAME. Retrieve it for me.","My glorious ITEM_NAME is returned! I can teach you a SPELL as thanks!"},
      {"NPC_NAME of LOCATION_NAME has thy Magic Lockpick! I will give thee GOLD_REWARD coins to bring it to me.", "NPC_NAME's Magic Lockpick must me mine! Bring it to me from LOCATION_NAME for thy gold!",   "Skara Brae, my Magic Lockpick! No reward for you! Muahahahahaha!"},     
      {"NPC_NAME of LOCATION_NAME has the rarest Riddlebook! Steal it for me and I give you GOLD_REWARD gold!",   "NPC_NAME's Riddlebook must me mine! Bring it to me from LOCATION_NAME for GOLD_REWARD gold!","At last! More riddles! I have no gold for you, but you earn my gratitude! Goodbye!"},      
      {"NPC_NAME of LOCATION_NAME plays Swine with loaded cubes! GOLD_REWARD coins to bring it to me.",           "NPC_NAME's loaded cubes must me mine! Bring it to me from LOCATION_NAME for thy gold!",     "I shall never lose at Swine with thy loaded cubes! No reward for you! Muahahahahaha!"},     
      //STEAL_DAGGERS
      {"Five strangers are in town to deliver magic daggers for the king. Steal all five for a mighty reward!",   "The 5 travelers still hold the magic daggers. Steal them for me for thy reward!",           "The five magic daggers! You are a mighty thief! Your reward is my great respect!"},
      {"The statue of the courtyard 'tis too serious. Knock it down for me for a laugh, and GOLD_REWARD gold!",   "The courtyard statue still stands. Strike it to pieces for GOLD_REWARD coins!",             "The statue has fallen! Too funny! You get no gold, but my priceless respect! Goodbye!"},
      //CITY_REVENGE
      {"NPC_NAME of LOCATION_NAME has done me wrong.  Wilt thou bring me justice for GOLD_REWARD coins?",         "NPC_NAME of LOCATION_NAME still haunts me.  Please end my misery by ending their breath!",  "I thank thee.  Whilst I can't give you much, here is thy pay."},
      {"NPC_NAME of LOCATION_NAME carries a message for me.  Wilt thou deliver it to me for GOLD_REWARD coins?",  "NPC_NAME of LOCATION_NAME still holds my message.  Please deliver it to me.",               "I thank thee.  Whilst I can't give you much, here is thy pay."},
      {"NPC_NAME vies for the hand of my love. Frighten them out of town and I will grant thee GOLD_REWARD gold.","NPC_NAME still courts my love. Frighten them out of our town for GOLD_REWARD coins!",       "My love has tanken to thy arms only. I thank thee, and grant you this reward for your deeds!"},
      //BATTLE
      {"A regiment of beasts is marching to LOCATION_NAME. Wilt thou join the Brute Squad for GOLD_REWARD gold?", "The battle lines are drawn outside the gates of LOCATION_NAME. Join us in battle!",         "The reinforcements have cleaned up the rest at LOCATION_NAME.  Here is thy pay."},
      {"An assassin lurks in our dungeon. I must stay by thy King's side. End this scum for GOLD_REWARD gold!",   "A scum assassin still lurks in thy dungeon.  Kill them for GOLD_REWARD coins!",             "You have done a great service for thy King. I grant you this large sum of gold."},
      {"A massive beast stalks our dungeon. Investigating guards have disappeared. Bring proof of its end for GOLD_REWARD gold!", "We still hear the beast's roar from our dungeons. Slay it for GOLD_REWARD coins!", "You have done a mighty service for thy King. Thee has earned this large sum of gold."},
      //MOVE_BEGGER
      {"NPC_NAME took mine corner: I need that corner back. 'Tis the best in the city. Make 'em leave for me.",   "NPC_NAME still stands at mine corner. Make 'em leave and you'll get what thee deserve.",    "I can give thee no coin, but I will tell all about thee good deeds!"},
      {"Thy beggers are ruining my buisness.  I will pay GOLD_REWARD if ye find a way to 'relocate' them.",       "My buisness still sufferes from all thee beggers about.  Thy GOLD_REWARD awaits thee.",     "'Tis like a new town! I thank thee and grant thy good pay."},
      //TRAIN_DOG
      {"I need a fine dog to help protect my shoppe at night.  Find me a hearty one for GOLD_REWARD gold.",       "I am still in need of a dog to guard my shoppe at night.  GOLD_REWARD coins awaits thee.",  "'Tis a fine guard dog!  Here is thy pay."},
      {"I must stay by thy king's side, but thy stable is in need of a horse. Bring one for GOLD_REWARD coin!",   "I need a horse with haste, but must stay by the king's side.  Retrieve one, quickly!",      "'Tis a very fine horse.  Here is thy well deserved pay."},
      //SLAY_MONSTER
      {"A MONSTER_TYPE is seen close to our gates. I will stay to defend the commoners. Slay it for GOLD_REWARD gold!", "You have no proof of slaying the MONSTER_TYPE outside our gates. I can not give you the bounty's reward.", "The beast is slain. Good work. Here are thy GOLD_REWARD coins."},
      {"The ship of the Dread Brigand Roberts is ravaging our port. Bring me his mask for GOLD_REWARD coins!",    "'Tis the ship of the Dread Pirate Roberts on our shores. Defeat him and bring me his mask", "We are saved! Our ships can return to thy city! Here is thy well earned reward!"},
      {"You have been chosen to fight in The Arena! Slay thy foes or fall! Delight the crowd for GOLD_REWARD coins!",  "The spectators demand action! Fight for the masses for GOLD_REWARD gold!",             "The spectators cheer thee! I salute you, Warrior of The Arena! Here is thy GOLD_REWARD gold!"},
      {"NPC_NAME has escaped thy cell to flee the combat of The Arena, hiding in LOCATION_NAME. End them!",       "GOLD_REWARD awaits thee for ending NPC_NAME for their insolence!",                          "Justice has been served. Here is thy reward. Nobody escapes The Arenamaster!"},
      {"Thy family is being forced fo fight in the Arena! Please free them - they deserve not this fate!",        "The Arenamaster will soon throw my family to the beasts! For the love of Skara Brae, free them!","I have no coin to give thee, but you have my eternal thanks!"},
      //TEACH_SPELL
      {"I must learn the SPELL_NAME spell. Discover its secrets and I will pay thee GOLD_REWARD coins!",          "The SPELL_NAME spell still eludes me. Find its secrets and teach them to me for thy reward!","Here is your well earned gold. I thank thee for thy precious information!"},
      //CLEAR_BANDITS
      {"The NPC_NAME gang is back in thy town! Purge them from thy streets for GOLD_REWARD coins! Hurry!",        "We are under the bow of the NPC_NAME gang! Liberate us for GOLD_REWARD gold as payment!",   "The NPC_NAME gang will haunt us no more! Thank thee, and here is thy GOLD_REWARD gold!"},
      //BEAT_SWINE_PLAYER
      {"NPC_NAME is undefeated in Swine. I suspect they have loaded cubes! Can thee defeat them in Swine?",       "NPC_NAME is still without loss in Swine. Dost thou have the skill to beat them?",           "Huzzah! You have my congratulations for your victory. Well done PLAYER_NAME!"},
      //TREASURE_MAP
      {"I need GOLD_AMOUNT gold to pay off thy bounty. Recover my gold with this treasure map and earn the difference.","I am still in need of GOLD_AMOUNT coins for my bounty. Did you find my treasure?",    "Thank thee. I can clear thy name at last and worry not about bounty hunters!"},
      //FIND_SERPENT_EGGS
      {"The making of cure potions requires serpent eggs from the caves. Bring me three for GOLD_REWARD coins.",  "I am still in the need of three cave serpent eggs to mix cure potions. GOLD_REWARD gold awaits thee.","You have my thanks. Here is thy GOLD_REWARD gold!"},
      //FIND_DRAGON_EGG
      {"I have met thy might's peak and must consume a Dragon egg to grow stronger. Bring one for GOLD_REWARD gold.","GOLD_REWARD coins await thee when a Dragon's egg is laid at my feet. Move with haste!",  "Thou has done good work to help me better protect thy king. Here is thy reward."},
      //FIRE_FIGHTER
      {"'Tis a terrible fire in the DIR part of town! Extinguish it before too much damage is done!",             "Thy fire still rages in the DIR part of the city! Help put it out with haste!",             "The city is saved! We have collected GOLD_REWARD gold to give thee as thanks!"},
      //CHASE_THIEF
      {"The master thief NPC_NAME is in town and too swift to catch! Bring them justice for GOLD_REWARD gold!", "The elusive thief NPC_NAME still evades our capture! Drive them out for GOLD_REWARD coins!","NPC_NAME, that elusive knave, is gone! Here is thy GOLD_REWARD gold!"},
      //FIND_ELK_PELT
      {"Thy roof has born a hole that needs mending. I can use an elk pelt to seal thy breach. Can thou spare one?","Thy rain still breaches thy hut. An elk pelt will help thee mend my domicile.",           "'Tis just what I need to mend thy roof. Thank thee!"},
      //FIND_GEM
      {"I am in need of a ITEM_NAME for the hilt of a magic sword forged for the King. GOLD_REWARD gold awaits thee to bring one!","Thy King's sword still needs a ITEM_NAME for the hilt to be completed!",     "Perfect! Now I can finish the King's magic sword. Here is thy GOLD_REWARD gold as thanks."},
      //KILL_WEREWOLF
      {"A Werewolf is among us! Those without home fear we will be next! Save us! Find the cursed one and drive them out!","We are still terrorized by a Werewolf at night! Kill the monster before more are lost!","We are saved! NAME has saved our town! Three cheers for NAME!"},
      //MERCY_KILL
      {"I confess to be the Werewolf! My guilt of murder is unbearable! End me with a Royal Weapon to save thy soul!","I can not bear the weight of the Wolfen curse! Take thy life with a Royal Weapon with haste!",""},
      //WEREWOLF_TOWN
      {"The Wolfen outcasts have been banished to LOC_NAME, but are ravaging our livestock. GOLD_REWARD gold to purge the Werewolf village!","Thee shall have GOLD_REWARD gold to burn down the Werewolf outcasts of LOC_NAME!","Our livestock is now safe from the Wolfen freaks. Here is thy GOLD_REWARD reward!"},
      //SLAY_VAMPIRE
      {"The vile Vampyre NPC_NAME has cursed our town! Slay them at LOC_NAME and burn out their coffin refuge to save us!","NPC_NAME the Vampyre still haunts our town. Drive them out of LOC_NAME and burn their coffin!","Our town is saved! All hail NAME! NAME has saved our town!"},
      //GET_BOAT
      {"I am in need of a boat or ship! I have a precious gem found in the mines to trade for one!", "A magic gem from the mines is still up for trade for a fine vessel of the seas!", "I thank thee! Here is thy precious gem. I am off to sail the high seas!"},
      //DESTROY_SIGNS
      {"Mine Pama make shoppe signs but no work! Pama so sad - no food! Break shoppe signs so mine Pama gets work!", "Still shoppe signs - mine Pama needs work! Break all shoppe signs! Please, mine Pama! Mine Pama!", "Mine Pama has work! Mine Pama makes signs! Thanks! Thanks!"},
      //DESTROY_STATUE2
      {"Statue scares me! Statue scares me! Make statue go away!", "Statue still there! Make statue leave! Statue scares me!", "Statue gone! Hurray!"},
      //BURN_COFFINS
      {"The Coffinsmith of this town invites death and Vampyres with his craft. Burn the coffins away to drive them out!", "There still be coffin making in this town. 'Tis a bad omen - burn them away!", "The Coffinsmith has left. Our town is relived of his burden! My thanks!"},
      //TAX_COLLECTOR
      {"Thy tax evader NPC_NAME has eluded my sight. Extract their debt of GOLD_AMOUNT gold and thee will earn a percentage as payment!", "NPC_NAME owes tax debt and still eludes me. Collect GOLD_AMOUNT coins from them and earn a percentage.", "Thy collections for this season are finally complete. Thank thee and here is thy ten-percent commission!"},
      //FIND_LEDGER
      {"My tax ledger has gone missing! The King demands complete records of revenue! This will cost me my head if not found!","Thy tax ledger is still missing! For the love of Skara Brae, help me find it!","Huzzah! You have found my tax ledger! I shall cover thy taxes for this season! Thank thee!"},
      //TAX_IMPOSTER
      {"The knavish NPC_NAME has stolen a Tax-collector uniform and fleecing the townsfolk of their money! Stop them!", "NPC_NAME is a Tax-collector imposter! End their reign of swindlery!", "The tax-imposter has been stopped! Thank thee. Your taxes for this season have been pardoned!"},
      //TAX_MOB
      {"The townsfolk have called the NPC_NAME gang to drive me out of town! Save me from their wrath!","The NPC_NAME gang still roams the street, seeking my ousting. Save me!", "You have purged the NPC_NAME gang! Tax collecting in this town shall continue another season! Thank thee!"},
      //MARRIAGE
      {"My own NPC_NAME is without spouse, lonely and lost of spirit. Wilt thou take their hand in marriage for a dowry of home and horse?","A dowry of home and horse awaits thee to take NPC_NAME's hand in marriage","Rejoice! You may call me father! Treat NPC_NAME well, and thee will always have a home here"}
      };

   public Mission()
   {
      state = IN_PROGRESS;
      type = "";
      startStory = "";
      middleStory = "";
      endStory = "";
      info = "";
      worldRow = -1;
      worldCol = -1;
      goldReward = 0;
      itemReward = null;
      clearLocation = "";
      target = null;
      targetHolder = "";
      targetRow = -1;
      targetCol = -1;
      failed = false;
   }
   
   //set starting mission when you create a player
   public Mission(boolean isVampire, boolean isWerewolf, boolean isFramed)
   {
      state = IN_PROGRESS;
      startStory = "none";
      middleStory = "none";
      endStory = "none";
      if(isVampire)
      {
         info = "Purge the Vampyric curse";
         type = "Curse";
      }
      else if(isWerewolf)
      {
         info = "Purge the Wolfen curse";
         type = "Werewolf";
      }
      else if(isFramed)
      {
         info = "Clear thy reputation";
         type = "Framed";
      }
      else
      {
         info = "Discover the number, find the Red Square";
         type = "Main";
      }
      worldRow = -1;
      worldCol = -1;
      goldReward = 0;
      itemReward = null;
      clearLocation = "none";
      target = null;
      targetHolder = "none";
      targetRow = -1;
      targetCol = -1;
      failed = false;
   }

   //set mission when you get the crown
   public Mission(String t)
   {
      state = IN_PROGRESS;
      startStory = "none";
      middleStory = "none";
      endStory = "none";
          
      info = "Purge Death from this plane";
      type = "Death";
    
      worldRow = -1;
      worldCol = -1;
      goldReward = 0;
      itemReward = null;
      clearLocation = "none";
      target = null;
      targetHolder = "none";
      targetRow = -1;
      targetCol = -1;
      failed = false;
   }

  //pre: story.length == 3 where [0] is the startStory, [1] is the middleStory, [2] is the endStory
   public Mission(String t, String[]story, int wRow, int wCol, int gold, String loc)
   {
      type = t;
      state = IN_PROGRESS;
      startStory = story[0];
      middleStory = story[1];
      endStory = story[2];
      worldRow = wRow;
      worldCol = wCol;
      goldReward = gold;
      itemReward = null;
      clearLocation = loc;
      target = null;
      targetHolder = "";
      targetRow = -1;
      targetCol = -1;
      failed = false;
      info = buildInfo();
   }

   public Mission(String t, String[]story, int wRow, int wCol, int gold, Item iw, String loc, Item targ, String targHolder, int targR, int targC)
   {
      type = t;
      state = IN_PROGRESS;
      startStory = story[0];
      middleStory = story[1];
      endStory = story[2];
      worldRow = wRow;
      worldCol = wCol;
      goldReward = gold;
      itemReward = iw;
      clearLocation = loc;
      target = targ;
      targetHolder = targHolder;
      targetRow = targR;
      targetCol = targC;
      failed = false;
      info = buildInfo();
   }

   public Mission(String t, String[]story, int wRow, int wCol, int gold, Item iw, String loc, Item targ, String targHolder, int targR, int targC, boolean fa)
   {
      this(t, story,  wRow,  wCol,  gold,  iw,  loc,  targ,  targHolder,  targR,  targC);
      failed = fa;
      info = buildInfo();
   }

   public boolean getFailed()
   {
      return failed;
   }

   public void setFailed(boolean fa)
   {
      failed = fa;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String t)
   {
      type = t;
   }

   public byte getState()
   {
      return state;
   }

   public void setState(byte s)
   {
      state = s;
   }

   public String getStartStory()
   {
      return startStory;
   }

   public void setStartStory(String ss)
   {
      startStory = ss;
   }

   public String getMiddleStory()
   {
      return middleStory;
   }

   public void setMiddleStory(String ms)
   {
      middleStory = ms;
   }

   public String getEndStory()
   {
      return endStory;
   }

   public void setEndStory(String es)
   {
      endStory = es;
   }

//returns the short info that is displayed to the user in the Map
   public String buildInfo()
   {
      if(type.equals("Main")) //starting vampire mission
         return "Discover the number, find the Red Square";
      if(type.equals("Curse")) //starting vampire mission
         return "Purge the Vampyric curse";
      if(type.equals("Werewolf")) //starting werewolf mission
         return "Purge the Wolfen curse";
      if(type.equals("Framed")) //starting framed mission
         return "Clear thy reputation";
      if(type.equals("Death")) //given when main mission is cleared
         return "Purge Death from this plane";
      if(type.equals("Treasure") && !targetHolder.equals("none"))
      {
         if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):find "+targetHolder+"'s treasure near (" + targetRow + "-" + targetCol + ")";
         if(goldReward > 0)    
            return "("+worldCol+"-"+worldRow+"):find "+goldReward+" gold for "+targetHolder;
         return "("+worldCol+"-"+worldRow+"):find gold for "+targetHolder+"'s bounty";
      }
      else if(type.equals("Boat") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Acquire a boat or ship for "+targetHolder;   
      }
      else if(type.equals("Collect") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Collect overdue taxes from "+targetHolder;   
      }
      else if(type.equals("Break") && clearLocation.length()>0)
      {
         return "("+worldCol+"-"+worldRow+"):Break all shoppe signs of "+clearLocation;   
      }
      else if(type.equals("Burn") && clearLocation.length()>0)
      {
         return "("+worldCol+"-"+worldRow+"):Burn all coffins out of "+clearLocation;   
      }
      else if(type.equals("Save") && clearLocation.length()>0 && !targetHolder.equals("none"))
      {
         if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" the Taxman from the "+targetHolder+" gang";
         return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation+" from the "+targetHolder+" gang";
      }
      else if(type.equals("Save") && clearLocation.length()>0 && targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation+" from the Werewolf";
      }
      else if(type.equals("Mercy") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Mercy kill Werewolf "+targetHolder+" with Royal arms";
      }
      else if(type.equals("Marry") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Marry "+targetHolder+" and inherit a dowry";
      }
      else if(type.equals("Swine") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Defeat "+targetHolder+" in a game of Swine";
      }
      else if(type.equals("Clear") && clearLocation.length()>0)
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation;
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Purge") && clearLocation.length()>0)
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation;
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Extinguish") && clearLocation.length()>0)
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" the fire at "+clearLocation;
      }
      else if(type.equals("Arena") && clearLocation.length()>0)
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):contend in "+clearLocation;
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):contend in "+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Battle") && clearLocation.length()>0)
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+clearLocation;
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Rescue") && clearLocation.length()>0)
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation;
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Harvest") && target!=null && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName()+" for " +targetHolder;
      }
      else if(type.equals("Find") && target!=null && !targetHolder.equals("none"))
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName();   
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName() + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Find") && target!=null && targetHolder.equals("none"))
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName() + " near (" + worldCol + "-" + worldRow + ")";   
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName() + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Steal") && target!=null && !targetHolder.equals("none"))
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName();   
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName() + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Steal") && target!=null && targetHolder.equals("none"))
      {
         if(targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName() + " near (" + worldCol + "-" + worldRow + ")";   
         else if(targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName() + " at (" + targetCol + "-" + targetRow + ")";
      }
      else if(type.equals("Steal") && target!=null)
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName();
      }
      else if(type.equals("Assassinate") && target!=null)
      {
         if(clearLocation.length()==0 && targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","");
         else if(clearLocation.length()>0 && targetRow==-1 && targetCol==-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","") + " at " + clearLocation; 
         else if(clearLocation.length()>0 && targetRow!=-1 && targetCol!=-1)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","") + " at (" + targetCol + "-" + targetRow + ")";     
      }
      else if(type.equals("Remove") && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder;
      }
      else if((type.equals("Frighten") || type.equals("Chase")) && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):Remove "+targetHolder;
      }
      else if(type.equals("Train") && !targetHolder.equals("none") && target!=null)
      {
         return "("+worldCol+"-"+worldRow+"):Bring a "+target.getName()+" to "+targetHolder;
      }
      else if(type.equals("Slay") && target!=null)
      {
         if(clearLocation.length()>0 && !targetHolder.equals("none") && targetRow>=0 && targetCol>=0)
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder + " at " + clearLocation+"(" + targetCol + "-" + targetRow + ")";
         if(clearLocation.length()>0 && !targetHolder.equals("none"))
            return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder + " at " + clearLocation;
         return "("+worldCol+"-"+worldRow+"):"+type+" the "+target.getName().replace("-head","") + " near (" + worldCol + "-" + worldRow + ")";
      }
      else if(type.equals("Roberts") && worldCol!=-1 && worldRow!=-1)
      {
         return "("+worldCol+"-"+worldRow+"):Defeat Dread Brigand Roberts near (" + worldCol + "-" + worldRow + ")";
      }
      else if(type.equals("Destroy") && target!=null)
      {
         if(clearLocation.length()>0 && !targetHolder.equals("none"))
            return "("+worldCol+"-"+worldRow+"):"+type+" the statue at " + clearLocation;
         return "("+worldCol+"-"+worldRow+"):"+type+" the statue near (" + worldCol + "-" + worldRow + ")";
      }
      else if(type.equals("Teach") && target!=null && !targetHolder.equals("none"))
      {
         return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+" the "+target.getName()+" spell";   
      } 
      if(clearLocation.length()>0 && target == null && targetRow==-1 && targetCol==-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation;
      else if(clearLocation.length()>0 && target == null && targetRow!=-1 && targetCol!=-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+clearLocation + " at (" + targetCol + "-" + targetRow + ")";
      else if(target!=null && !targetHolder.equals("none") && targetRow==-1 && targetCol==-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName();   
      else if(target!=null && !targetHolder.equals("none") && targetRow!=-1 && targetCol!=-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+targetHolder+"'s "+target.getName() + " at (" + targetCol + "-" + targetRow + ")";
      else if(target!=null && clearLocation.length()==0 && targetRow==-1 && targetCol==-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","");
      else if(target!=null && clearLocation.length()>0 && targetRow==-1 && targetCol==-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","") + " at " + clearLocation; 
      else if(target!=null && clearLocation.length()>0 && targetRow!=-1 && targetCol!=-1)
         return "("+worldCol+"-"+worldRow+"):"+type+" "+target.getName().replace("-bounty","") + " at (" + targetCol + "-" + targetRow + ")";  
      return "?";
   }

   public String getInfo()
   {
      return info;
   }

   public void setInfo(String i)
   {
      info = i;
   }

   public int getWorldRow()
   {
      return worldRow;
   }

   public void setWorldRow(int wr)
   {
      worldRow = wr;
   }

   public int getWorldCol()
   {
      return worldCol;
   }

   public void setWorldCol(int wc)
   {
      worldCol = wc;
   }

   public int getGoldReward()
   {
      return goldReward;
   }

   public void setGoldReward(int gw)
   {
      goldReward = gw;
   }

   public Item getItemReward()
   {
      return itemReward;
   }

   public void setItemReward(Item ir)
   {
      itemReward = ir;
   }

   public String getClearLocation()
   {
      return clearLocation;
   }

   public void setClearLocation(String cl)
   {
      clearLocation = cl;
   }

   public Item getTarget()
   {
      return target;
   }

   public void setTarget(Item t)
   {
      target = t;
   }

   public String getTargetHolder()
   {
      return targetHolder;
   }

   public void setTargetHolder(String th)
   {
      targetHolder = th;
   }

   public int getTargetRow()
   {
      return targetRow;
   }

   public void setTargetRow(int wr)
   {
      targetRow = wr;
   }

   public int getTargetCol()
   {
      return targetCol;
   }

   public void setTargetCol(int wc)
   {
      targetCol = wc;
   }

   public boolean isFinished()
   {
      if(this.getType().contains("Boat"))   //bring a boat or ship to someone
      {
         for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
         {
            NPCPlayer p = CultimaPanel.worldMonsters.get(i);
            if(NPC.isShip(p) && p.hasMet() && Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), p.getRow(), p.getCol()) < CultimaPanel.SCREEN_SIZE/2)
               return true;
         }
      }
      else if(this.getType().contains("Extinguish") && this.getClearLocation()!=null)
      {
         int endDay = this.getTargetRow();
         int endTime = this.getTargetCol();
         if(CultimaPanel.days > endDay || CultimaPanel.time > endTime)
            return true;
         Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
         if(missLoc!=null)
         {
            int numFires = LocationBuilder.countFires(missLoc);
            if(numFires==0)      
               return true;
         }
      }
      else if(this.getType().equals("Save") && clearLocation.length()>0 && targetHolder.equals("none"))
      {  //KILL_WEREWOLF mission
         int worldRow = this.getWorldRow();
         int worldCol = this.getWorldCol();
         Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
         int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
         boolean werewolf = CultimaPanel.werewolfAbout;
         if(!werewolf)
            return true;
      }
      else if(this.getType().contains("Save") && this.getClearLocation()!=null)
      {
         int numGangMembers = LocationBuilder.countGangMembers(CultimaPanel.getLocation(this.getClearLocation())); 
         if(numGangMembers == 0)
            return true;
      }
      else if((this.getType().contains("Treasure") || this.getType().contains("Collect"))  && this.getGoldReward() > 0)
      {
         if(CultimaPanel.player.getGold() > this.getGoldReward())
            return true; 
      }
      else if(this.getType().contains("Teach")  && this.getTarget() != null)
      {
         Item target = this.getTarget();
         String targetName = target.getName(); 
         if(CultimaPanel.player.hasSpell(targetName) || CultimaPanel.player.hasItem(targetName))
            return true; 
      }
      else if(this.getType().contains("Battle") && this.getClearLocation()!=null)
      {
         Location battleLoc = CultimaPanel.getLocation(this.getClearLocation());
         if(battleLoc!= null)
         {
            Teleporter tele = battleLoc.getTeleporter();
            //mission is done if we have visited the location and there are still monsters left
            if (tele.toRow()!=-1 && tele.toCol()!=-1)
               return true;
         }
      }//end of checking battle mission finish
      else if(this.getType().contains("Purge") && this.getClearLocation()!=null)
      {
         if(this.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
            if(missLoc!=null)
            {
               int numCivs = LocationBuilder.countCivilians(missLoc);
               if(numCivs==0)      //we killed or fightened out all the inhabitants
                  return true;
            }
         }
      }
      else if(this.getType().contains("Arena") && this.getClearLocation()!=null)
      {
         if(this.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
            if(missLoc!=null)
            {
               int numMonsters = LocationBuilder.countMonsters(missLoc);
               if(numMonsters==0)      //we killed all the monsters in the arena
                  return true;
            }
         }
      }
      else if((this.getType().contains("Destroy") || this.getType().contains("Slay") || this.getType().contains("Assassinate") || this.getType().contains("Find") || this.getType().contains("Swine") || this.getType().contains("Roberts")) && this.getTarget()!=null)
      {   //check to see if Slay mission is finished   
         Item target = this.getTarget();
         String targetName = target.getName();
         if(this.getStartStory().toLowerCase().contains("vampyre") && this.getClearLocation().length()>0)  //make sure we also burned the coffin out of the area
         {
            boolean hasHead = (CultimaPanel.player.hasItem(targetName));
            Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
            boolean hasCoffin = TerrainBuilder.hasCoffin(missLoc);
            if(hasHead && !hasCoffin)
               return true;
         }
         else if(CultimaPanel.player.hasItem(targetName))
            return true;
      }
      else if(this.getType().contains("Break") && this.getClearLocation().length()>0)
      {   //check to see if break shoppe sign mission is finished   
         Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
         return !TerrainBuilder.hasSigns(missLoc);
      }
      else if(this.getType().contains("Burn") && this.getClearLocation().length()>0)
      {   //check to see if burn coffins mission is finished   
         Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
         return !TerrainBuilder.hasCoffin(missLoc);
      }
      else if(this.getType().contains("Harvest") && this.getTarget()!=null)
      {   //check to see if harvest egg mission is finished   
         Item target = this.getTarget();
         String targetName = target.getName().toLowerCase();
         if(targetName.contains("3-serpent-eggs") && CultimaPanel.player.numSerpentEggs()>=3)
            return true;
      }
      else if(this.getType().contains("Train") && this.getTarget()!=null)   //bring a trained horse or dog to someone
      {
         Item target = this.getTarget();
         String targetName = target.getName().toLowerCase();
         if(targetName.contains("horse"))
         {
            for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
            {
               NPCPlayer p = CultimaPanel.worldMonsters.get(i);
               if(p.getCharIndex() == NPC.HORSE && p.hasMet())
                  return true;
            }
         }
         else if(targetName.contains("dog"))
         {
            if(!CultimaPanel.player.getDogName().equals("none"))
               return true;
         }  
      }
      else if(this.getType().contains("Clear") && this.getClearLocation()!=null && this.getClearLocation().length()>0)
      {                    //this is a mission to clear a location of monsters
         Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
         if(missLoc!=null)
         {
            ArrayList<Point> monsterFreq = missLoc.getMonsterFreq();
            if(monsterFreq==null || monsterFreq.size()==0)  //we cleared out the monsters here
               return true;
         }
      }
      else if(this.getType().contains("Rescue") && this.getClearLocation().length()>0)
      {
         Location missLoc = CultimaPanel.getLocation(this.getClearLocation());
         if(missLoc!=null)
         {
            if(LocationBuilder.countPrisoners(missLoc)==0)      //we freed all the prisoners
               return true;
         }
      }
      else if(this.getType().contains("Remove") && this.getTargetHolder()!=null && this.getTargetHolder().length()>0)        //remove beggers from a town, or help them to be upright citizens
      {
         int worldRow = this.getWorldRow();
         int worldCol = this.getWorldCol();
         Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
         int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
         if(this.getTargetHolder().contains("all beggers"))
         {
            int numBeggers = LocationBuilder.countPrisoners(thisLoc);   
            if(numBeggers==0)
               return true;
         }
         else
         {
            boolean removed = true;
            for(NPCPlayer p:CultimaPanel.civilians.get(mi))
            {
               String pName = Utilities.shortName(p.getName());
               String targName = Utilities.shortName(this.getTargetHolder());
               if(pName.equalsIgnoreCase(targName) && p.getCharIndex()==NPC.BEGGER)
               {
                  removed = false;
                  break;
               }
            }
            if(removed)
               return true;
         }
      } 
      else if((this.getType().contains("Frighten") || this.getType().contains("Chase")) && this.getTargetHolder()!=null && this.getTargetHolder().length()>0)        //remove beggers from a town, or help them to be upright citizens
      {
         int worldRow = this.getWorldRow();
         int worldCol = this.getWorldCol();
         Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
         int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
         boolean removed = true;
         for(NPCPlayer p:CultimaPanel.civilians.get(mi))
         {
            String pName = Utilities.shortName(p.getName());
            String targName = Utilities.shortName(this.getTargetHolder());
            if(pName.equalsIgnoreCase(targName))
            {
               removed = false;
               break;
            }
         }
         if(removed)
            return true;
      }
      else if((this.getType().contains("Marry")) && this.getTargetHolder()!=null && this.getTargetHolder().length()>0)        //remove beggers from a town, or help them to be upright citizens
      {
         int worldRow = this.getWorldRow();
         int worldCol = this.getWorldCol();
         Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
         int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
         boolean married = false;
         for(NPCPlayer p:CultimaPanel.civilians.get(mi))
         {
            String pName = Utilities.shortName(p.getName());   //starting the name with + means we are married to them
            String targName = Utilities.shortName(this.getTargetHolder());
            if(pName.equalsIgnoreCase("+"+targName))
            {
               married = true;
               break;
            }
         }
         return married;
      }
      else if(this.getType().contains("Steal") && this.getTarget() != null)
      {
         Item target = this.getTarget();
         String targetName = target.getName();                 //steal magic pick or riddle book
         if (target.getName().contains("pick") || target.getName().contains("book") || target.getName().contains("cube"))
         {
            if(CultimaPanel.player.hasItem(targetName))
               return true;
         }
         else if(targetName.toLowerCase().contains("dagger"))  //steal the 5 magic daggers mission
         {
            String [] magicDaggers = {"Poison-dagger","Souldagger","Magmadagger","Frostdagger","Banedagger"};
            boolean hasDaggers = true;
            for(String dag: magicDaggers)
               if(!CultimaPanel.player.hasItem(dag))
                  hasDaggers = false;
            if(hasDaggers)   
               return true;
         }
      }   
      return false;
   }

   public static void spawnMonsters(Location loc, int arenaLevel)
   {
      //see what side of the arena we are likely on so we know which side to spawn the monsters on
      int mapIndex = loc.getMapIndex();
      byte[][]currMap = (CultimaPanel.map.get(mapIndex));
      Teleporter tele = loc.getTeleporter();
      int toRow = tele.toRow();
      int toCol = tele.toCol();
      Terrain monsterSpawnTile =  TerrainBuilder.getTerrainWithName("INR_$Red_floor");
      ArrayList<Point>monsterSpawns = new ArrayList<Point>();
      for(int r=0; r<currMap.length; r++)
         for(int c=0; c<currMap[0].length; c++)
         {
            if(Math.abs(currMap[r][c]) == monsterSpawnTile.getValue())
               monsterSpawns.add(new Point(r,c));
            String curr = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
            if(curr.contains("night_door"))
               currMap[r][c] = TerrainBuilder.getTerrainWithName("INR_I_B_$Night_door_closed").getValue();
         } 
       //arenaLevels for each index (which level it is) contains (monster charIndex, # of monsters)  
      ArrayList<Point> arenaLevels = new ArrayList<Point>();    
      arenaLevels.add(new Point(NPC.SKELETON, 1));
      arenaLevels.add(new Point(NPC.SKELETON, 1));
      arenaLevels.add(new Point(NPC.WOLF, 1));
      arenaLevels.add(new Point(NPC.SKELETON, 3));
      arenaLevels.add(new Point(NPC.WOLF, 4));
      arenaLevels.add(new Point(NPC.ORC, 1));
      arenaLevels.add(new Point(NPC.BUGBEAR, 1));
      arenaLevels.add(new Point(NPC.SKELETON, 6));
      arenaLevels.add(new Point(NPC.BEAR, 1));
      arenaLevels.add(new Point(NPC.BUGBEAR, 3));
      arenaLevels.add(new Point(NPC.SKELETON, 10));
      arenaLevels.add(new Point(NPC.ORC, 6));
      arenaLevels.add(new Point(NPC.BRIGAND_SWORD, 1));
      arenaLevels.add(new Point(NPC.BEAR, 2));
      arenaLevels.add(new Point(NPC.BUGBEAR, 6));
      arenaLevels.add(new Point(NPC.TROLL, 1));
      arenaLevels.add(new Point(NPC.BRIGAND_SWORD, 3));
      arenaLevels.add(new Point(NPC.TROLL, 3));
      arenaLevels.add(new Point(NPC.CYCLOPS, 1));
      arenaLevels.add(new Point(NPC.SKELETON, 20));
      arenaLevels.add(new Point(NPC.BRIGAND_SWORD, 5));
      arenaLevels.add(new Point(NPC.CYCLOPS, 3));
      arenaLevels.add(new Point(NPC.TROLL, 6));
      arenaLevels.add(new Point(NPC.ENFORCER, 1));
      arenaLevels.add(new Point(NPC.WOLF, 8));
      arenaLevels.add(new Point(NPC.BOWASSASSIN, 1));
      arenaLevels.add(new Point(NPC.ORC, 8));
      arenaLevels.add(new Point(NPC.BEAR, 4));
      arenaLevels.add(new Point(NPC.BRIGANDKING, 1));
      arenaLevels.add(new Point(NPC.CYCLOPS, 5));
      arenaLevels.add(new Point(NPC.BRIGAND_SWORD, 8));
      arenaLevels.add(new Point(NPC.ORC, 10));
      arenaLevels.add(new Point(NPC.RATKING, 1));
      arenaLevels.add(new Point(NPC.BUGBEAR, 10));
      arenaLevels.add(new Point(NPC.SNAKEKING, 1));
      arenaLevels.add(new Point(NPC.BOWASSASSIN, 2));
      arenaLevels.add(new Point(NPC.BRIGAND_SWORD, 10));
      arenaLevels.add(new Point(NPC.SPIDERKING, 1));
      arenaLevels.add(new Point(NPC.SKELETON, 30));
      arenaLevels.add(new Point(NPC.WOLFKING, 1));
      arenaLevels.add(new Point(NPC.CYCLOPS, 8));
      arenaLevels.add(new Point(NPC.BUGBEARKING, 1));
      arenaLevels.add(new Point(NPC.ENFORCER, 5));
      arenaLevels.add(new Point(NPC.TROLLKING, 1));
      arenaLevels.add(new Point(NPC.SORCERER, 10));
      arenaLevels.add(new Point(NPC.BOWASSASSIN, 3));
      arenaLevels.add(new Point(NPC.BEARKING, 2));
      arenaLevels.add(new Point(NPC.CYCLOPS, 10));
      arenaLevels.add(new Point(NPC.ENFORCER, 10));
      arenaLevels.add(new Point(NPC.SKELETON, 40));
      arenaLevels.add(new Point(NPC.BOWASSASSIN, 6));
      
      Point level = arenaLevels.get(arenaLevel);
      byte monsterIndex = (byte)(level.getX());
      byte frequency = (byte)(level.getY());
      for(int i=0; i<frequency; i++)
      {
         if(monsterSpawns.size()==0)
            break;
         Point spawn = monsterSpawns.remove((int)(Math.random()*monsterSpawns.size()));
         NPCPlayer monster = null;
         if(monsterIndex==NPC.BRIGAND_SWORD)
            monster = new NPCPlayer(NPC.randomArenaBrigand(), (int)(spawn.getX()), (int)(spawn.getY()), mapIndex, "arena");
         else   
            monster = new NPCPlayer(monsterIndex, (int)(spawn.getX()), (int)(spawn.getY()), mapIndex, "arena");
         monster.setMoveType(NPC.CHASE);
         monster.setGold(0);
         CultimaPanel.worldMonsters.add(monster);
      }
   }

   public static String arenaMission(NPCPlayer selected)
   {
      String [] noMission = {"Excuse me. I have no mission. I just wanted to greet you personally", "You seem the type that is good for a mission. Sadly, I don't know of any", "You have a mission: look for missions from others", "Did thou say mission? There must be a vex in thy matrix"};
      String [] noBattle = {"Come back on day WHICH_DAY for more glory", "Thee has seen enough death for today. Return on day WHICH_DAY for more glory", "On day WHICH_DAY, we will have the arena ready for another victory for thee", "'Tis time for thee to rest. On day WHICH_DAY, return to thrill us with another victory"};
      String [] arenaChampion = {"You are the Champion of the Arena. Your legend requires no more glory", "You have retired as the legendary Arena Champion", "You may rest as the Champion of the Arena", "As Arena Champion, you are too great for any challenges here"};
      String [] tooLate = {"The curtain of night has fallen. Thy arena will wait 'til morn", "You may find glory in the arena at sunrise", "Come thee back when the light of day shines down on this glorious arena", "'Tis the fall of night. Rest thee until morn for more battle"};
      String response = "";
      if(selected.getNumInfo() == 0)
         return arenaChampion[(int)(Math.random()*arenaChampion.length)];
      if(selected.getNumInfo() >= CultimaPanel.days)
         return noBattle[(int)(Math.random()*noBattle.length)].replace("WHICH_DAY", (""+(CultimaPanel.days + 1)));
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if((CultimaPanel.time >=20 || CultimaPanel.time <= 6) && currMission==null)
      {
          
         return tooLate[(int)(Math.random()*tooLate.length)];
      }
   
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Arena"))
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!= null)
            {
               int numMonsters = LocationBuilder.countMonsters(missLoc);
               if(numMonsters==0)      //we killed all the monsters in the arena
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
               
                  CultimaPanel.player.addReputation(10);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Assassinate"))
         {   //check to see if Slay mission is finished   
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               CultimaPanel.player.addReputation(-10);
               CultimaPanel.player.removeItem(target.getName());
               FileManager.saveProgress();
            }
         }
      }//end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else 
         if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
         {
            Sound.applauseWin(Player.rollDie(100,200));
            response = currMission.getEndStory();
            CultimaPanel.player.addExperience(100);
            int goldReward = currMission.getGoldReward();
            Item itemReward = currMission.getItemReward();
            CultimaPanel.player.addGold(goldReward);
            if(itemReward != null)
               CultimaPanel.player.addItem(itemReward.getName());
            Player.stats[Player.ARENA_LEVEL] = Math.max(Player.stats[Player.ARENA_LEVEL], Math.min(50, selected.getNumInfo()));
            selected.setNumInfo(selected.getNumInfo()+1);
            if(selected.getNumInfo() > 50)
            {
               selected.setNumInfo(0);
               CultimaPanel.player.addItem("arenacape");
               Achievement.earnAchievement(Achievement.MAXIMUS_OVERDRIVE);
               int diff = CultimaPanel.player.expNeededForNextLevel() - CultimaPanel.player.getExperience();
               if(diff > 0)
                  CultimaPanel.player.addExperience(diff);
            }
            selected.setMissionInfo("none");
            //open gates to arena to let you out
            int mapIndex = CultimaPanel.player.getMapIndex();
            byte[][]currMap = (CultimaPanel.map.get(mapIndex));
            for(int r=0; r<currMap.length; r++)
               for(int c=0; c<currMap[0].length; c++)
               {
                  String curr = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
                  if(curr.contains("night_door"))
                     currMap[r][c] = TerrainBuilder.getTerrainWithName("INR_$Night_door_floor").getValue();
               } 
            if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
               CultimaPanel.missionStack.remove(currMissionIndex);
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
         } 
         else
         { //create a new mission
            String missionType = "Arena";
            //possibly make a mission to assasinate an escaped prisoner in the next closest town
            boolean civiliansToWrite = false;   //if we build a habitable location for the mission target, write out civilians file as well
            String []locTypes2 = {"city","fortress","port","village"};
            Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
            ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(closeCity.getMapIndex());
            if(inLocNPCs.size() >= 1 && Math.random() < 0.1)
            {
               missionType = "Assassinate";
            }
            if(missionType.equals("Arena"))
            {
               int mapIndex = CultimaPanel.player.getMapIndex();
               Location loc = CultimaPanel.allLocations.get(mapIndex);       
               if(loc!=null)
               {
                  String [] temp = missions[ARENA];
                  String [] story = new String[3];
                  story[0] = new String(temp[0]);
                  story[1] = new String(temp[1]);
                  story[2] = new String(temp[2]);     
                  String locName = Display.provinceName(loc.getName());
               
                  int arenaLevel = selected.getNumInfo();
                  int goldReward = Math.max(250, arenaLevel*10);
               
                  spawnMonsters(loc, arenaLevel);
               
                  if(CultimaPanel.shoppeDiscount)   //guard is charmed
                     goldReward = (int)(goldReward * 1.20);
                     
                  for(int i=0; i<story.length; i++)
                  {
                     story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  }
                  int startRow = CultimaPanel.player.getWorldRow();
                  int startCol = CultimaPanel.player.getWorldCol();
                  int mRow = loc.getRow();
                  int mCol = loc.getCol();
               //public           Mission(String t, String[]story, int wRow, int wCol, int gold, Item iw, String loc, Item targ, String targHolder, int targR, int targC)
                  Mission getMission = new Mission(missionType, story, startRow, startCol, goldReward, null, locName, null, "none", mRow, mCol);
                  selected.setMissionInfo(getMission.getInfo());
                  CultimaPanel.missionStack.add(getMission);
                  if(CultimaPanel.missionsGiven[ARENA]==0)
                     CultimaPanel.missionsGiven[ARENA]++;
                  FileManager.saveProgress();
                  response = getMission.getStartStory();            
               }
            }
            else  //Assassinate escaped prisoner
            {
               int randMission = Mission.ARENA_BOUNTY;
               missionType = "Assassinate";
               NPCPlayer targNPC = null;
               Location loc = null;
               if(inLocNPCs.size() >= 1 && closeCity!=null)
               {
                  ArrayList<NPCPlayer> targets = new ArrayList<NPCPlayer>();
                  for(NPCPlayer pl: inLocNPCs)
                     if(!pl.isShopkeep() && (pl.getCharIndex()==NPC.MAN || pl.getCharIndex()==NPC.WOMAN || pl.getCharIndex()==NPC.BEGGER || pl.getCharIndex()==NPC.WISE || pl.getCharIndex()==NPC.SWORD))
                        targets.add(pl);
                  if(targets.size()==0)
                     targNPC = null;
                  else
                     targNPC = targets.get((int)(Math.random()*targets.size()));
               
                  loc = closeCity;
                  civiliansToWrite = true;
                  if(targNPC!=null && loc!=null)
                  {
                     String [] temp = missions[randMission];
                     String [] story = new String[3];
                     story[0] = new String(temp[0]);
                     story[1] = new String(temp[1]);
                     story[2] = new String(temp[2]);
                  //higher reward for targets further away
                     int goldReward = Math.max(60, targNPC.getLevel()+(int)(Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), loc.getRow(), loc.getCol())));
                     String targName = Utilities.shortName(targNPC.getName());  
                     CultimaPanel.missionTargetNames.add(targName);   
                     String target = targName+"-bounty";
                     int targItemPrice = (int)(goldReward);
                     targNPC.addItem(new Item(target, targItemPrice));
                     String locName = Display.provinceName(loc.getName());
                     for(int i=0; i<story.length; i++)
                     {
                        story[i]=story[i].replace("NPC_NAME", targName);
                        story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                        story[i]=story[i].replace("LOCATION_NAME", locName);
                        story[i]=story[i].replace("ITEM_NAME", target);
                     }
                     int startRow = CultimaPanel.player.getWorldRow();
                     int startCol = CultimaPanel.player.getWorldCol();
                     int mRow = -1, mCol = -1;
                     if(targNPC.getMapIndex() != selected.getMapIndex())
                     {
                        mRow = loc.getRow();
                        mCol = loc.getCol();
                     }
                     TerrainBuilder.markMapPath(loc.getName());
                     Mission getMission = new Mission(missionType, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), targName, mRow, mCol);
                     selected.setMissionInfo(getMission.getInfo());
                     CultimaPanel.missionStack.add(getMission);
                     if(CultimaPanel.missionsGiven[randMission]==0)
                        CultimaPanel.missionsGiven[randMission]++;
                     FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                     FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
                     response = getMission.getStartStory();
                  }
                  else
                  {
                  //no mission
                     response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
                     selected.setNumInfo(0);
                  }   
               }
            }
         }
      if(response.length()==0)
         response = noMission[(int)(Math.random()*noMission.length)];
      return response;
   }

   public static String guardMission(NPCPlayer selected)
   {
      String [] noBattle = {"Excuse me. I have no mission. I just wanted to greet you personally", "<...seek the sacred number...>", "You have a mission: look for missions from others", "Did thou say mission? There must be a vex in thy matrix"};
      String [] lostBattle = {"Thy reinforcements have driven them back, but thy losses were too heavy for pay", "You left the battle before reinforcements cleared the area.  You get no pay"};
   
      byte[][] worldMap = CultimaPanel.map.get(0);
      String response = "";
      boolean civiliansToWrite = false;   //if we build a location, write out civilians to file
      int mi = CultimaPanel.player.getMapIndex();
      byte [][] currMap = CultimaPanel.map.get(mi);
   
      String currLoc = CultimaPanel.player.getLocationType().toLowerCase();
      ArrayList<Point>spawnPoints = new ArrayList<Point>();
      for(int cr = 5; cr<currMap.length-5; cr++)
         for(int cc = 5; cc<currMap[0].length-5; cc++)
         {
            if(currLoc.contains("city") || currLoc.contains("fort"))
            {
               if(TerrainBuilder.isGoodCityMonsterSpawn(cr, cc, mi))
                  spawnPoints.add(new Point(cr, cc));
            }
            else
            {
               if(TerrainBuilder.isInsideFloor(cr, cc, mi))
                  spawnPoints.add(new Point(cr, cc));
            }
         }
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Battle"))
         {
            Location battleLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(battleLoc!= null)
            {
               Teleporter tele = battleLoc.getTeleporter();
            //mission is done if we have visited the location and there are still monsters left
               if (tele.toRow()!=-1 && tele.toCol()!=-1)
               {
                  currMission.setState(Mission.FINISHED);
                  if(currMission.getFailed() == false)
                  {
                     Player.stats[Player.MISSIONS_COMPLETED]++;
                     if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                        Achievement.earnAchievement(Achievement.TASK_MASTER);
                     selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                     CultimaPanel.player.addReputation(5);
                     CultimaPanel.player.stats[Player.BATTLES_FOUGHT]++;
                     CultimaPanel.player.stats[Player.BATTLES_WON]++;
                  }
                  else
                  {
                     CultimaPanel.player.stats[Player.BATTLES_FOUGHT]++;
                     selected.setNumInfo(0);  
                     selected.setMissionInfo("none");
                     if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
                        CultimaPanel.missionStack.remove(currMissionIndex);
                     return lostBattle[(int)(Math.random()*lostBattle.length)];
                  }
                  FileManager.saveProgress();
               }
            }
         }//end of checking battle mission finish
         else if(currMission.getType().contains("Purge"))
         {
            if(currMission.getClearLocation().length()>0)
            {
               Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
               if(missLoc!=null)
               {
                  int numCivs = LocationBuilder.countCivilians(missLoc);
                  if(numCivs==0)      //we killed all the inhabitants
                  {
                     currMission.setState(Mission.FINISHED);
                     Player.stats[Player.MISSIONS_COMPLETED]++;
                     if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                        Achievement.earnAchievement(Achievement.TASK_MASTER);
                     CultimaPanel.player.addReputation(10);
                     selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                     FileManager.saveProgress();
                  }
               }
            }
         }
         else if(currMission.getType().contains("Slay") || currMission.getType().contains("Roberts"))
         {   //check to see if Slay mission is finished   
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               CultimaPanel.player.removeItem(target.getName());
               if(currMission.getType().contains("Roberts"))
                  selected.addItem(target);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Frighten") || currMission.getType().contains("Chase"))
         {
            boolean removed = true;
            for(NPCPlayer p:CultimaPanel.civilians.get(selected.getMapIndex()))
            {
               String pName = Utilities.shortName(p.getName());
               String targName = Utilities.shortName(currMission.getTargetHolder());
               if(pName.equalsIgnoreCase(targName))
               {
                  removed = false;
                  break;
               }
            }
            if(removed)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(-5);
               FileManager.saveProgress();
            }
         }
         //end of checking purge mission finish
         
      }  //end seeing if we finished a mission
      
      if(selected.getNumInfo() <= 0)
         response = NPC.noMission[(int)(Math.random()*NPC.noMission.length)]+".";
      else   
         if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
            response = currMission.getMiddleStory();
         else 
            if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
            {
               response = currMission.getEndStory();
               CultimaPanel.player.addExperience(100);
               int goldReward = currMission.getGoldReward();
               Item itemReward = currMission.getItemReward();
               CultimaPanel.player.addGold(goldReward);
               if(itemReward != null)
                  CultimaPanel.player.addItem(itemReward.getName());
               selected.setNumInfo(0);  
               selected.setMissionInfo("none");
               if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
                  CultimaPanel.missionStack.remove(currMissionIndex);
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            } 
            else
            { //create a new mission
               String missionType = "";
               //see which missions we have yet to be given
               ArrayList<String>missTemp = new ArrayList<String>();
               
               ArrayList<Point>waterLocs =  Utilities.waterInView();
               
               if(CultimaPanel.missionsGiven[BATTLE]==0)
                  missTemp.add("Battle");
               if(CultimaPanel.missionsGiven[SLAY_MONSTER]==0)
                  missTemp.add("Slay");
               if(CultimaPanel.missionsGiven[ZOMBIE_PLAGUE]==0)
                  missTemp.add("Purge");
               if(waterLocs.size() >= 5 && CultimaPanel.missionsGiven[SLAY_ROBERTS]==0)
                  missTemp.add("Roberts"); 
               if(CultimaPanel.missionsGiven[CHASE_THIEF]==0 && spawnPoints.size()>0)
                  missTemp.add("Chase");   
               if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
                  missTemp.add("Mercy");  
               //if we have seen each mission type before, pick a random one   
               if(missTemp.size()==0)  
               {   
                  if(selected.isWerewolf() && Math.random() < 0.66)
                     missionType = "Mercy";
                  else 
                  {
                     int randMission = Player.rollDie(1,10);
                     if(randMission <= 3)
                        missionType = "Battle";
                     else
                        if(randMission <= 6)
                           missionType = "Slay";
                        else if(randMission <= 8 && spawnPoints.size()>0)
                           missionType = "Chase";   
                        else
                        {
                           if(waterLocs.size() >= 5 && Math.random() < 0.5)
                              missionType = "Roberts";
                           else
                              missionType = "Purge";
                        }
                  }
               }
               else
               {  //if there are mission types we have yet to see, pick a random among them
                  missionType = missTemp.get((int)(Math.random()*missTemp.size()));
               }    
               if(missionType.equals("Battle"))
               {
                  int r = CultimaPanel.player.getWorldRow();
                  int c = CultimaPanel.player.getWorldCol();
                  Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations,  r,  c, 0);
                  String thisName = "";
                  if(thisLoc != null)
                     thisName = Display.provinceName(thisLoc.getName());
                  ArrayList <Point> battleSpots = new ArrayList<Point>();
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r-2), CultimaPanel.equalizeWorldCol(c)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r-2), CultimaPanel.equalizeWorldCol(c)));
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r+2), CultimaPanel.equalizeWorldCol(c)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r+2), CultimaPanel.equalizeWorldCol(c)));
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r-3), CultimaPanel.equalizeWorldCol(c)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r-3), CultimaPanel.equalizeWorldCol(c)));
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r+3), CultimaPanel.equalizeWorldCol(c)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r+3), CultimaPanel.equalizeWorldCol(c)));
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r), CultimaPanel.equalizeWorldCol(c-3)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r), CultimaPanel.equalizeWorldCol(c-3)));
                  if(!LocationBuilder.isImpassable(worldMap, CultimaPanel.equalizeWorldRow(r), CultimaPanel.equalizeWorldCol(c+3)))
                     battleSpots.add(new Point(CultimaPanel.equalizeWorldRow(r), CultimaPanel.equalizeWorldCol(c+3)));
                  if(battleSpots.size() == 0)   //no open battle spots
                  {
                     selected.setNumInfo(0);
                     response = noBattle[(int)(Math.random()*noBattle.length)];
                  }
                  else
                  {
                     Point battleSpot = battleSpots.get((int)(Math.random()*battleSpots.size()));
                     r = (int)(battleSpot.getX());
                     c = (int)(battleSpot.getY());
                     Location battlefield = null;
                  //if there has already been a battle at this place, just replace the same location with a new battlefield
                     Location oldBattlefield = CultimaPanel.getLocation(thisName + " battlefield");
                     if(oldBattlefield != null)
                     {
                        r = oldBattlefield.getRow();
                        c = oldBattlefield.getCol();
                        oldBattlefield.getTeleporter().resetTo();
                        battlefield = oldBattlefield;
                     }
                     else
                     {
                        worldMap[r][c] = TerrainBuilder.getTerrainWithName("LOC_$Battlefield").getValue();
                        Teleporter teleporter = new Teleporter((CultimaPanel.map).size());
                        battlefield = new Location(thisName + " battlefield", r, c, 0, CultimaPanel.allTerrain.get(Math.abs(worldMap[r][c])), teleporter);
                        CultimaPanel.allLocations.add(battlefield);
                        CultimaPanel.allBattlefields.add(battlefield);
                     }
                     String [] temp = missions[BATTLE];
                     String [] story = new String[3];
                     story[0] = new String(temp[0]);
                     story[1] = new String(temp[1]);
                     story[2] = new String(temp[2]);
                     int goldReward = 100;
                     if(CultimaPanel.shoppeDiscount)   //guard is charmed
                        goldReward = (int)(goldReward * 1.20);
                     for(int i=0; i<story.length; i++)
                     {
                        story[i]=story[i].replace("LOCATION_NAME", battlefield.getName());
                        story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                     }
                     int startRow = CultimaPanel.player.getWorldRow();
                     int startCol = CultimaPanel.player.getWorldCol();
                     int mRow = battlefield.getRow();
                     int mCol = battlefield.getCol();
                     Mission battleMission = new Mission(missionType, story, startRow, startCol, goldReward, null, battlefield.getName(), null, "none", mRow, mCol);
                     selected.setMissionInfo(battleMission.getInfo());
                     CultimaPanel.missionStack.add(battleMission);
                     if(CultimaPanel.missionsGiven[BATTLE]==0)
                        CultimaPanel.missionsGiven[BATTLE]++;
                     FileManager.saveProgress();
                     response = battleMission.getStartStory();
                  }
               }//end create Battle mission
               else if(missionType.equals("Mercy"))
               {
                  String type = "Mercy";
                  Location loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
                  String [] temp = missions[MERCY_KILL];
                  String [] story = new String[3];
                  story[0] = new String(temp[0]);
                  story[1] = new String(temp[1]);
                  story[2] = new String(temp[2]);
                  int goldReward = 0;
                  String targName = Utilities.shortName(selected.getName());
                  if(targName.contains("~"))
                  {
                     String [] parts = targName.split("~");
                     targName = parts[0];
                  }         
                  String locName = Display.provinceName(loc.getName());
                  int startRow = CultimaPanel.player.getWorldRow();
                  int startCol = CultimaPanel.player.getWorldCol();
                  int mRow = -1, mCol = -1;
                  Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
                  selected.setMissionInfo(getMission.getInfo());
                  CultimaPanel.missionStack.add(getMission);
                  if(CultimaPanel.missionsGiven[MERCY_KILL]==0)
                     CultimaPanel.missionsGiven[MERCY_KILL]++;
                  FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                  FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
                  response = getMission.getStartStory();
               }
               else if(missionType.equals("Chase"))
               { 
                  String locType = CultimaPanel.player.getLocationType();
                  Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
                  int r = (int)(spawn.getX());
                  int c = (int)(spawn.getY());
                  NPCPlayer target = new NPCPlayer(NPC.JESTER, r, c, mi, r, c, locType);
                  target.setAgility(Player.rollDie(50,70));
                  target.setGold(Player.rollDie(25,75));
                  target.setName("Masterthief " + Utilities.shortName(target.getName()));
                  target.setMoveType(NPC.RUN);
                  target.setReputation(-1 * Player.rollDie(500,1500));
                  if(Math.random() < 0.25)
                     target.addItem(Item.getRandomMagicItem()); 
                  else 
                     target.addItem(Item.getViperGlove());  
                  CultimaPanel.civilians.get(mi).add(target);
                  String targName = Utilities.shortName(target.getName());
                  String [] temp = missions[CHASE_THIEF];
                  String [] story = new String[3];
                  story[0] = new String(temp[0]);
                  story[1] = new String(temp[1]);
                  story[2] = new String(temp[2]);
                  int goldReward = Player.rollDie(40,100);
                  for(int i=0; i<story.length; i++)
                  {
                     story[i]=story[i].replace("NPC_NAME", targName);
                     story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  }
                  int startRow = CultimaPanel.player.getWorldRow();
                  int startCol = CultimaPanel.player.getWorldCol();
                  int mRow = -1;
                  int mCol = -1;
                  Mission chaseMission = new Mission(missionType, story, startRow, startCol, goldReward, null, "none", null, targName, mRow, mCol);
                  selected.setMissionInfo(chaseMission.getInfo());
                  CultimaPanel.missionStack.add(chaseMission);
                  if(CultimaPanel.missionsGiven[CHASE_THIEF]==0)
                     CultimaPanel.missionsGiven[CHASE_THIEF]++;
                  FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                  FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
                  response = chaseMission.getStartStory();
               }
               else 
                  if(missionType.equals("Purge"))
                  {
                     Location loc = null;
                  //find next closest castle
                     String [] locTypes = {"castle","tower"};
                     Location closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
                     ArrayList<NPCPlayer> castleNPCs = CultimaPanel.civilians.get(closeCastle.getMapIndex());
                     double distToCastle = Display.wrapDistance(closeCastle.getRow(), closeCastle.getCol(), CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());           
                  //find next closest town
                     String []locTypes2 = {"city","fortress","port","village"};
                     Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
                     ArrayList<NPCPlayer>  cityNPCs = CultimaPanel.civilians.get(closeCity.getMapIndex());
                     double distToCity = Display.wrapDistance(closeCity.getRow(), closeCity.getCol(), CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());           
                  
                     if(castleNPCs.size() > 0 && cityNPCs.size() > 0)
                     {
                        if(distToCastle < distToCity)
                           loc = closeCastle;
                        else
                           loc = closeCity;
                     }
                     else if(castleNPCs.size() > 0)
                        loc = closeCastle;
                     else if(cityNPCs.size() > 0)
                        loc = closeCity;
                     else
                        loc = null;
                  
                     civiliansToWrite = true;
                  
                     if(loc!=null)
                     {
                        for(NPCPlayer p:CultimaPanel.civilians.get(loc.getMapIndex()))
                        {
                           if(p.getMapIndex()==loc.getMapIndex() && NPC.isCivilian(p.getCharIndex()) && !p.isNonPlayer())
                           {
                              p.setNumInfo(-666);
                           }
                        }
                        TerrainBuilder.markMapPath(loc.getName());
                        String [] temp = missions[ZOMBIE_PLAGUE];
                        String [] story = new String[3];
                        story[0] = new String(temp[0]);
                        story[1] = new String(temp[1]);
                        story[2] = new String(temp[2]);
                     
                        String locName = Display.provinceName(loc.getName());
                        int numCivs = LocationBuilder.countCivilians(loc);
                        int goldReward = Math.max(250, (numCivs * 20)+(int)(Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), loc.getRow(), loc.getCol())));
                        if(CultimaPanel.shoppeDiscount)   //guard is charmed
                           goldReward = (int)(goldReward * 1.20);
                     
                        for(int i=0; i<story.length; i++)
                        {
                           story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                           story[i]=story[i].replace("LOCATION_NAME", locName);
                        }
                        int startRow = CultimaPanel.player.getWorldRow();
                        int startCol = CultimaPanel.player.getWorldCol();
                        int mRow = loc.getRow();
                        int mCol = loc.getCol();
                        Mission getMission = new Mission(missionType, story, startRow, startCol, goldReward, null, locName, null, "none", mRow, mCol);
                        selected.setMissionInfo(getMission.getInfo());
                        CultimaPanel.missionStack.add(getMission);
                        if(CultimaPanel.missionsGiven[ZOMBIE_PLAGUE]==0)
                           CultimaPanel.missionsGiven[ZOMBIE_PLAGUE]++;
                        FileManager.saveProgress();
                        response = getMission.getStartStory();
                         
                     } 
                  }//end create Purge Mission
                  else 
                     if(missionType.equals("Slay"))
                     {  //create Slay mission
                        byte monsterIndex = NPC.randomSlayMissionMonster();
                        if(CultimaPanel.weather > 0)
                           monsterIndex = NPC.randomSlayMissionMonsterRaining();
                        Point monsterLoc = Utilities.findMonsterSpawn(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), monsterIndex);
                        if(monsterLoc != null)
                        {
                           int monsterRow = (int)(monsterLoc.getX());
                           int monsterCol = (int)(monsterLoc.getY());
                           if(monsterIndex==NPC.DRAGON || monsterIndex==NPC.DRAGONKING)     
                              Sound.dragonCall();
                           if(!Display.frozenWater() && waterLocs.size() > 5 && Math.random() < 0.25)   //25% chance it is a water monster
                           {
                              monsterIndex = NPC.randomSlayMissionWaterMonster();
                              monsterLoc = waterLocs.get((int)(Math.random()*waterLocs.size()));
                              monsterRow = (int)(monsterLoc.getX());
                              monsterCol = (int)(monsterLoc.getY());
                           }
                           
                           if(Utilities.NPCAt(monsterRow, monsterCol, 0))
                              Utilities.removeNPCat(monsterRow, monsterCol, 0);
                        
                           String [] temp = missions[SLAY_MONSTER];
                           String [] story = new String[3];
                           story[0] = new String(temp[0]);
                           story[1] = new String(temp[1]);
                           story[2] = new String(temp[2]);
                        //higher reward for targets further away
                           String target = NPC.characterDescription(monsterIndex)+"-head";
                           NPCPlayer monster = new NPCPlayer(monsterIndex, monsterRow, monsterCol, 0, "world");
                           int targItemPrice = (monster.getLevel()*5);
                           if(targItemPrice > 250)
                              targItemPrice = 250;
                           int targetRow = monsterIndex;  //record the monster index for spawning a monster of the same type in case we missed it the first time
                           int targetCol = monsterIndex;
                           monster.addItem(new Item(target,targItemPrice));
                           CultimaPanel.worldMonsters.add(monster);
                           for(int i=0; i<story.length; i++)
                           {
                              story[i]=story[i].replace("GOLD_REWARD", ""+targItemPrice);
                              story[i]=story[i].replace("MONSTER_TYPE", ""+NPC.characterDescription(monsterIndex));
                           }
                           int startRow = CultimaPanel.player.getWorldRow();
                           int startCol = CultimaPanel.player.getWorldCol();
                           int mRow = -1, mCol = -1;
                           Mission getMission = new Mission("Slay", story, startRow, startCol, targItemPrice, null, "none", new Item(target, targItemPrice), "none", mRow, mCol);
                           getMission.setTargetRow(targetRow);
                           getMission.setTargetCol(targetCol);
                           selected.setMissionInfo(getMission.getInfo());
                           CultimaPanel.missionStack.add(getMission);
                           if(CultimaPanel.missionsGiven[SLAY_MONSTER]==0)
                              CultimaPanel.missionsGiven[SLAY_MONSTER]++;
                           FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                           response = getMission.getStartStory();
                        }
                        else  
                        {  //could not find valid monster spawn - clear the mission
                           response = "";
                        }
                     }//end create Slay mission
                     else 
                        if(missionType.equals("Roberts"))
                        {  //create Roberts mission
                           byte monsterIndex = NPC.BRIGANDSHIP;
                           if(Math.random()<0.5)
                              monsterIndex = NPC.GREATSHIP;
                           Point monsterLoc =  waterLocs.get((int)(Math.random()*waterLocs.size()));
                           if(monsterLoc != null)
                           {
                              int monsterRow = (int)(monsterLoc.getX());
                              int monsterCol = (int)(monsterLoc.getY());
                           
                              if(Utilities.NPCAt(monsterRow, monsterCol, 0))
                                 Utilities.removeNPCat(monsterRow, monsterCol, 0);
                           
                              String [] temp = missions[SLAY_ROBERTS];
                              String [] story = new String[3];
                              story[0] = new String(temp[0]);
                              story[1] = new String(temp[1]);
                              story[2] = new String(temp[2]);
                           //higher reward for targets further away
                              String target = Item.getRobertsMask().getName();
                              NPCPlayer monster = new NPCPlayer(monsterIndex, monsterRow, monsterCol, 0, "world");
                              monster.setReputation(-1000);
                              int targItemPrice = (monster.getLevel()*5);
                              if(targItemPrice > 250)
                                 targItemPrice = 250;
                              int targetRow = monsterIndex;  //record the monster index for spawning a monster of the same type in case we missed it the first time
                              int targetCol = monsterIndex;
                              monster.addItem(new Item(target,targItemPrice));
                              CultimaPanel.worldMonsters.add(monster);
                              for(int i=0; i<story.length; i++)
                              {
                                 story[i]=story[i].replace("GOLD_REWARD", ""+targItemPrice);
                              }
                              int startRow = CultimaPanel.player.getWorldRow();
                              int startCol = CultimaPanel.player.getWorldCol();
                              int mRow = -1, mCol = -1;
                              Mission getMission = new Mission("Roberts", story, startRow, startCol, targItemPrice, null, "none", new Item(target, targItemPrice), "none", mRow, mCol);
                              getMission.setTargetRow(targetRow);
                              getMission.setTargetCol(targetCol);
                              selected.setMissionInfo(getMission.getInfo());
                              CultimaPanel.missionStack.add(getMission);
                              if(CultimaPanel.missionsGiven[SLAY_ROBERTS]==0)
                                 CultimaPanel.missionsGiven[SLAY_ROBERTS]++;
                              FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                              response = getMission.getStartStory();
                           }
                           else  
                           {  //could not find valid monster spawn - clear the mission
                              response = "";
                           }
                        }//end create Slay mission
            
            }
      if(response.length() == 0)
      {
         response = noBattle[(int)(Math.random()*noBattle.length)]+".";
         selected.setNumInfo(0);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }
   
   //if we have an active Slay mission and the arg monster is a type we are trying to slay,
   //add the mission item to a random spawning monster of the same type if we lose the original
   public static void checkSlayMission(NPCPlayer monster)
   {
     //check world monsters to make sure there doesn't exist one of type monster arg with a head item
      for(NPCPlayer p: CultimaPanel.worldMonsters)
      {
         if(p.getCharIndex()==monster.getCharIndex() && p.hasItem("head"))    //we found it
            return; 
      }
      Mission currMission = null;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(m.getType().toLowerCase().contains("slay") && m.getTarget()!=null)
            if(m.getTargetRow() == monster.getCharIndex())     //we stored the monster index in the targetRow to remember the monster type
            {
               String target = NPC.characterDescription(monster.getCharIndex())+"-head";
               if(!CultimaPanel.player.hasItem(target))
               {
                  int targItemPrice = (monster.getLevel()*10);
                  if(targItemPrice > 150)
                     targItemPrice = 150;
                  monster.addItem(new Item(target,targItemPrice));
               }
               break;
            } 
            else if(m.getType().toLowerCase().contains("roberts") && m.getTarget()!=null)
               if(m.getTargetRow() == monster.getCharIndex())     //we stored the monster index in the targetRow to remember the monster type
               {
                  if(!CultimaPanel.player.hasItem(m.getTarget().getName()))
                  {
                     monster.addItem(m.getTarget());
                  }
                  break;
               }     
      }
   }

   public static String royalGuardMission(NPCPlayer selected)
   {
      String response = "";
      Location loc = LocationBuilder.doesCastleHaveDungeon(CultimaPanel.player.getMapIndex());
      String [] locTypes = {"cave"};
      Location caveLoc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if((currMission.getType().contains("Assassinate") || currMission.getType().contains("Slay") || currMission.getType().contains("Find")) && currMission.getTarget()!=null)
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               if(currMission.getType().contains("Find"))
                  selected.addItem(target);
               CultimaPanel.player.removeItem(target.getName());
               FileManager.saveProgress();
            }
            else if(currMission.getType().contains("Assassinate") && CultimaPanel.player.hasItem("bounty"))
            {  //if we just have an item called bounty and there is only one bounty mission, resolve it.
               int count = 0;
               for(Mission m:CultimaPanel.missionStack)
               {
                  if(m.getType().contains("Assassinate"))
                     count++;
               }
               if (count==1)
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
                  selected.setReputation(CultimaPanel.player.getReputationRaw());  
                  CultimaPanel.player.removeItem("bounty");
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Train"))   //bring a trained horse to the castle
         {
            for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
            {
               NPCPlayer p = CultimaPanel.worldMonsters.get(i);
               if(p.getCharIndex() == NPC.HORSE && p.hasMet())
               {
                  CultimaPanel.player.setHorseBasicInfo("none");
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  CultimaPanel.worldMonsters.remove(i);
                  FileManager.saveProgress();
                  break;
               }
            }
         
         }
      }  //end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(0);    
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {
         //CASTLE_ASSASSIN, TRAIN HORSE, CASTLE_BEAST
         byte randMission = CASTLE_ASSASSIN;
         
         //see which missions we have yet to be given
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         if(CultimaPanel.missionsGiven[CASTLE_ASSASSIN]==0 && loc != null)
            missionTypes.add(CASTLE_ASSASSIN);
         if(CultimaPanel.missionsGiven[CASTLE_BEAST]==0 && loc != null)
            missionTypes.add(CASTLE_BEAST);
         if(CultimaPanel.missionsGiven[TRAIN_HORSE]==0)
            missionTypes.add(TRAIN_HORSE);
         if(CultimaPanel.missionsGiven[FIND_DRAGON_EGG]==0 &&  caveLoc !=null)   
            missionTypes.add(FIND_DRAGON_EGG);          
             
         if(missionTypes.size()==0) //if we have seen all the missions before, pick a random one
         {
            missionTypes.add(TRAIN_HORSE);
            if(loc != null)
            {
               missionTypes.add(CASTLE_ASSASSIN);
               missionTypes.add(CASTLE_BEAST);
            }
            if(caveLoc !=null)   
               missionTypes.add(FIND_DRAGON_EGG);    
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));                
                                         
         if(randMission == TRAIN_HORSE)
         {
            Item horse = new Item("horse", 100);
            String targetName = Utilities.shortName(selected.getName());
            type = "Train";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 100;
            double priceShift = ((Math.random()*51) - 25)/100.0;  //-.25 to +.25
            goldReward = (int)(goldReward + (goldReward * priceShift));            
            if(CultimaPanel.shoppeDiscount)   //charmed
               goldReward = (int)(goldReward * 1.20);
         
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission trainMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", horse, targetName, mRow, mCol);
            selected.setMissionInfo(trainMission.getInfo());
            CultimaPanel.missionStack.add(trainMission);
            if(CultimaPanel.missionsGiven[TRAIN_HORSE]==0)
               CultimaPanel.missionsGiven[TRAIN_HORSE]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = trainMission.getStartStory();
         }
         else if(randMission == CASTLE_ASSASSIN)
         {
         //add an assassin into loc
            int assassinType = Player.rollDie(1,3);
                       
            byte monsterType = NPC.BOWASSASSIN;
            String targName = "She-Assassin";
            if(assassinType == 1)
            {
               monsterType = NPC.VIPERASSASSIN;
               targName = "Viper-Assassin";
            }
            else if(assassinType == 2)
            {
               monsterType = NPC.ENFORCER;
               targName = "Dark-Enforcer";
            }
            byte[][]currMap = (CultimaPanel.map.get(loc.getMapIndex()));
            int r = (int)(Math.random()*currMap.length);
            int c = (int)(Math.random()*currMap[0].length);
            
            ArrayList<Point>spawnPoints = new ArrayList<Point>();
            for(int cr = 0; cr<currMap.length; cr++)
               for(int cc = 0; cc<currMap[0].length; cc++)
                  if(LocationBuilder.canMoveTo(currMap, cr, cc) && !TerrainBuilder.onWater(currMap, cr, cc))
                     spawnPoints.add(new Point(cr, cc));
            if(spawnPoints.size() > 0)
            {
               Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
               r = (int)(spawn.getX());
               c = (int)(spawn.getY());
            } 
            else
            {
               selected.setNumInfo(0);    
               return NPC.guardResponse(selected,"");
            }        
         
            NPCPlayer targNPC = new NPCPlayer(monsterType, r, c, loc.getMapIndex(), "dungeon");
            if(Math.random() < 0.75)
               targNPC.addItem(Item.getViperGlove());
            CultimaPanel.civilians.get(loc.getMapIndex()).add(targNPC);
         
         //Assassinate mission
            type = "Assassinate";
            if(targNPC!=null && loc!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 250;
               String target = targName+"-bounty";
               targNPC.addItem(new Item(target, goldReward));
            
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[CASTLE_ASSASSIN]==0)
                  CultimaPanel.missionsGiven[CASTLE_ASSASSIN]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
         else if(randMission == CASTLE_BEAST)
         {
         //add a beast into loc
            byte monsterType = NPC.randomMonsterKing();
                        
            byte[][]currMap = (CultimaPanel.map.get(loc.getMapIndex()));
            int r = (int)(Math.random()*currMap.length);
            int c = (int)(Math.random()*currMap[0].length);
            
            ArrayList<Point>spawnPoints = new ArrayList<Point>();
            for(int cr = 0; cr<currMap.length; cr++)
               for(int cc = 0; cc<currMap[0].length; cc++)
                  if(LocationBuilder.canMoveTo(currMap, cr, cc) && !TerrainBuilder.onWater(currMap, cr, cc))
                     spawnPoints.add(new Point(cr, cc));
            if(spawnPoints.size() > 0)
            {
               Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
               r = (int)(spawn.getX());
               c = (int)(spawn.getY());
            } 
            else
            {
               selected.setNumInfo(0);    
               return NPC.guardResponse(selected,"");
            }        
         
            NPCPlayer targNPC = new NPCPlayer(monsterType, r, c, loc.getMapIndex(), "dungeon");
            String target = NPC.characterDescription(monsterType)+"-head";
            int goldReward = 250;
            targNPC.addItem(new Item(target, goldReward));
            targNPC.setMoveType(NPC.CHASE);
            CultimaPanel.civilians.get(loc.getMapIndex()).add(targNPC);
         
            type = "Slay";
            if(targNPC!=null && loc!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
                                
               String locName = Display.provinceName(loc.getName());
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), "large beast", mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[CASTLE_BEAST]==0)
                  CultimaPanel.missionsGiven[CASTLE_BEAST]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
         else if (randMission == FIND_DRAGON_EGG && caveLoc !=null)
         {
            type = "Find";
         //add a dragon and egg into loc
            Item target = Item.getDragonEgg();
            byte monsterType = NPC.DRAGON;
            if(Math.random() < 0.05)
               monsterType = NPC.DRAGONKING;
            NPCPlayer targNPC = new NPCPlayer(monsterType, 0, 0, 0, "cave");
            Point spawnPoint = TerrainBuilder.addNPCtoLocationReturnPoint(caveLoc, "cave", targNPC);
            if(targNPC!=null && caveLoc!=null && spawnPoint!=null)
            {
               CultimaPanel.eggsToHarvest.add(new RestoreItem(caveLoc.getMapIndex(), (int)(spawnPoint.getX()), (int)(spawnPoint.getY()), NPC.DRAGON));
               NPCPlayer marker = new NPCPlayer(NPC.BEGGER, (int)(spawnPoint.getX()), (int)(spawnPoint.getY()), caveLoc.getMapIndex(), "temp", "temp,1,1,1,10");
               CultimaPanel.civilians.get(caveLoc.getMapIndex()).add(marker);   //add a nonPlayer NPC marker so we can be directed to the mission item with the Knowing spell
               TerrainBuilder.markMapPath(caveLoc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(150, target.getValue()/3);
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               String targName = Utilities.shortName(selected.getName());         
               String locName = Display.provinceName(caveLoc.getName());
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               mRow = caveLoc.getRow();
               mCol = caveLoc.getCol();
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, target, targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_DRAGON_EGG]==0)
                  CultimaPanel.missionsGiven[FIND_DRAGON_EGG]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }
         }
      }
      if(response.length() == 0)
      {
         response = NPC.kingNoMission[(int)(Math.random()*NPC.kingNoMission.length)]+".";
         selected.setNumInfo(0);
      }
      return response;
   }

   public static String kingMission(NPCPlayer selected)
   {
      String response = "";
      boolean civiliansToWrite = false;   //if we build a location, write out civilians to file
      boolean dungeonWithMonsters = false;
      boolean closeLairWithPrisoners = false;
      Location loc = LocationBuilder.doesCastleHaveDungeon(CultimaPanel.player.getMapIndex());
      if(loc != null && loc.getMonsterFreq().size()>0)
         dungeonWithMonsters = true;
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;
      boolean isGarriott = (selected.getName().contains("Garriott"));//Is this Richard Garriott (Lord British)?
   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Clear") && currMission.getClearLocation().length()>0)
         {                    //this is a mission to clear a location of monsters
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               ArrayList<Point> monsterFreq = missLoc.getMonsterFreq();
               if(monsterFreq==null || monsterFreq.size()==0)  //we cleared out the monsters here
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  FileManager.saveProgress();
               }
            }
         }
         else if((currMission.getType().contains("Assassinate") || currMission.getType().contains("Find")) && (currMission.getTarget()!=null))
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               if(currMission.getType().contains("Assassinate"))
                  CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               if(currMission.getType().contains("Find"))
                  CultimaPanel.player.addReputation(5);
               CultimaPanel.player.removeItem(target.getName());
               if(!target.getName().contains("bounty"))
                  selected.addItem(target);
               FileManager.saveProgress();
            }
            else if(currMission.getType().contains("Assassinate") && CultimaPanel.player.hasItem("bounty"))
            {  //if we just have an item called bounty and there is only one bounty mission, resolve it.
               int count = 0;
               for(Mission m:CultimaPanel.missionStack)
               {
                  if(m.getType().contains("Assassinate"))
                     count++;
               }
               if (count==1)
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  if(currMission.getType().contains("Assassinate"))
                     CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.removeItem("bounty");
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Rescue") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               if(LocationBuilder.countPrisoners(missLoc)==0)      //we freed all the prisoners
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addReputation(10);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Purge") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               int numCivs = LocationBuilder.countCivilians(missLoc);
               if(numCivs==0)      //we killed or scared out all the inhabitants
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addReputation(-10);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  FileManager.saveProgress();
               }
            }
         }
      
      }
      if(selected.getNumInfo() <= 0)
         response = NPC.kingNoMission[(int)(Math.random()*NPC.kingNoMission.length)]+".";
      else if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(selected.getNumInfo()-1);
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {
      //CLEAR_DUNGEON, ASSASSINATION, RESCUE_PRISONERS, FIND, PURGE_CITY
         byte randMission = ASSASSINATION;
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         if(CultimaPanel.missionsGiven[CLEAR_DUNGEON]==0 && dungeonWithMonsters)
            missionTypes.add(CLEAR_DUNGEON);
         if(CultimaPanel.missionsGiven[ASSASSINATION]==0)
            missionTypes.add(ASSASSINATION);
         if(CultimaPanel.missionsGiven[RESCUE_PRISONERS]==0)
            missionTypes.add(RESCUE_PRISONERS);
         if(CultimaPanel.missionsGiven[FIND_ITEM]==0)
            missionTypes.add(FIND_ITEM);
         if(CultimaPanel.missionsGiven[PURGE_CITY]==0 && selected.getReputation() <= -100)
            missionTypes.add(PURGE_CITY);
         
         //if we have seen all the missions before, pick a random one
         if(missionTypes.size()==0)
         {            
            if(dungeonWithMonsters)
               missionTypes.add(Mission.CLEAR_DUNGEON);
            missionTypes.add(Mission.ASSASSINATION);
            if(selected.getReputation() > -100)
               missionTypes.add(Mission.RESCUE_PRISONERS);
            missionTypes.add(Mission.FIND_ITEM);
            if(selected.getReputation() <= -100)
               missionTypes.add(Mission.PURGE_CITY);
         
            randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
            if(selected.getNumInfo()==1)
            {
               if(dungeonWithMonsters)                   //prioritize clearing our own dungeon and rescuing subjects
                  randMission = Mission.CLEAR_DUNGEON;
               else if(closeLairWithPrisoners)
                  randMission = Mission.RESCUE_PRISONERS;
            } 
         }
         else  //pick among the missions we have not seen yet
         {
            randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
         }
         
         if(randMission == Mission.RESCUE_PRISONERS)
         {
            String [] locs = {"lair"};
            Location lairLoc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locs);
            lairLoc = TerrainBuilder.closeLairWithPrisoners(CultimaPanel.allLocations);
            if(lairLoc==null)
            {
               for(int i=missionTypes.size()-1; i>=0; i--)
                  if(missionTypes.get(i) == Mission.RESCUE_PRISONERS)
                  {
                     missionTypes.remove(i);
                     break;
                  }
            }
            if(lairLoc==null)       
            {              
               if(missionTypes.size() == 0)
                  randMission = -1;
               else
                  randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
            }
            else
               loc = lairLoc;
         }
         if(randMission == -1)      //no mission found
         {
            response = NPC.kingNoMission[(int)(Math.random()*NPC.kingNoMission.length)]+".";
         }   
         else if(loc!=null && randMission == Mission.CLEAR_DUNGEON)
         {          
            TerrainBuilder.markMapPath(loc.getName());
            type = "Clear";
            ArrayList<Point> monsterFreq = loc.getMonsterFreq();
            if(monsterFreq.size() > 0)
            {
               byte monsterIndex = (byte)((monsterFreq.get(0)).getX());
               String monsterType = NPC.characterDescription(monsterIndex);
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(250, (int)((monsterFreq.get(0)).getY()));
               if(isGarriott)
                  goldReward *= 2;
               if(CultimaPanel.shoppeDiscount)   //king is charmed
                  goldReward = (int)(goldReward * 1.20);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("MONSTER_TYPE", monsterType);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission clearMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(clearMission.getInfo());
               CultimaPanel.missionStack.add(clearMission);
               if(CultimaPanel.missionsGiven[CLEAR_DUNGEON]==0)
                  CultimaPanel.missionsGiven[CLEAR_DUNGEON]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = clearMission.getStartStory();
            }
            else
            {  //no monsters in the location
               response = NPC.kingNoMission[(int)(Math.random()*NPC.kingNoMission.length)]+".";
            }
         }
         else if(randMission == Mission.ASSASSINATION || randMission == Mission.FIND_ITEM || randMission == Mission.PURGE_CITY)
         {  //Assassinate mission
            if(randMission == Mission.ASSASSINATION)
               type = "Assassinate";
            else if(randMission == Mission.FIND_ITEM)
               type = "Find";
            else
               type = "Purge";  
            int randType = Player.rollDie(10);
         
            ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(selected.getMapIndex());
            NPCPlayer targNPC = null;
            if(inLocNPCs.size() <= 1 || randMission == Mission.FIND_ITEM || randMission == Mission.PURGE_CITY)
               randType = Player.rollDie(5,10);  
            if(randType < 2)           //assassinate someone at the same location
            {
               loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
               ArrayList<NPCPlayer> targets = new ArrayList<NPCPlayer>();
               for(NPCPlayer pl: inLocNPCs)
                  if(!pl.getName().equals(selected.getName()))
                     targets.add(pl);
               if(targets.size()==0)
                  targNPC = null;
               else
                  targNPC = targets.get((int)(Math.random()*targets.size()));    
            }
            else if(randType <= 5)      //assassinate or find someone at the next closest castle
            {
               String [] locTypes = {"castle","tower"};
               Location closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCastle.getMapIndex());
               if(inLocNPCs.size() >= 1)
               {
                  targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
                  loc = closeCastle;
               }
               else
                  loc = null;
            }
            else                       //assassinate or find someone at the next closest town
            {
               String []locTypes2 = {"city","fortress","port","village"};
               Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCity.getMapIndex());
               if(inLocNPCs.size() >= 1)
               {
                  targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
                  loc = closeCity;
               }
               else
                  loc = null;
            }
            if(targNPC!=null && loc!=null)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               if(randMission == Mission.PURGE_CITY)
               {
                  String locName = Display.provinceName(loc.getName());
                  int numCivs = LocationBuilder.countCivilians(loc);
                  int goldReward = Math.max(350, (numCivs * 20)+(int)(Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), loc.getRow(), loc.getCol())));
                  if(isGarriott)
                     goldReward *= 2;
                  if(CultimaPanel.shoppeDiscount)   //king is charmed
                     goldReward = (int)(goldReward * 1.20);
               
                  for(int i=0; i<story.length; i++)
                  {
                     story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                     story[i]=story[i].replace("LOCATION_NAME", locName);
                  }
                  int startRow = CultimaPanel.player.getWorldRow();
                  int startCol = CultimaPanel.player.getWorldCol();
                  int mRow = loc.getRow();
                  int mCol = loc.getCol();
                  Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, "none", mRow, mCol);
                  selected.setMissionInfo(getMission.getInfo());
                  CultimaPanel.missionStack.add(getMission);
                  if(CultimaPanel.missionsGiven[PURGE_CITY]==0)
                     CultimaPanel.missionsGiven[PURGE_CITY]++;
                  FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                  FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
                  response = getMission.getStartStory();
               }
               else
               {
               //higher reward for targets further away
                  int goldReward = Math.max(250, targNPC.getLevel()+(int)(Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), loc.getRow(), loc.getCol())));
                  if(CultimaPanel.shoppeDiscount)   //king is charmed
                     goldReward = (int)(goldReward * 1.20);
                  String targName = Utilities.shortName(targNPC.getName());
                  CultimaPanel.missionTargetNames.add(targName);
                  String target = targName+"-bounty";
                  if(randMission == Mission.FIND_ITEM)
                  {
                     String [] items = {"holdall", "charmring", "focushelm", "pentangle", "mannastone", "powerbands", "swiftboots", "mindtome", "swiftquill"};
                     goldReward = 500;
                     target = items[(int)(Math.random()*items.length)];
                     
                  }
                  if(isGarriott)
                     goldReward *= 2;
               
               //the target might value the item differently if they are willing to sell it
                  double priceShift = ((Math.random()*51) - 25)/100.0;  //-.25 to +.25
                  if(randMission == Mission.ASSASSINATION)
                     priceShift = 0;
                  int targItemPrice = (int)(goldReward + (goldReward * priceShift));
                  targNPC.addItem(new Item(target, targItemPrice));
                
                  String locName = Display.provinceName(loc.getName());
               
                  for(int i=0; i<story.length; i++)
                  {
                     story[i]=story[i].replace("NPC_NAME", targName);
                     story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                     story[i]=story[i].replace("LOCATION_NAME", locName);
                     story[i]=story[i].replace("ITEM_NAME", target);
                  }
                  int startRow = CultimaPanel.player.getWorldRow();
                  int startCol = CultimaPanel.player.getWorldCol();
                  int mRow = -1, mCol = -1;
                  if(targNPC.getMapIndex() != selected.getMapIndex())
                  {
                     mRow = loc.getRow();
                     mCol = loc.getCol();
                  }
                  Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), targName, mRow, mCol);
                  selected.setMissionInfo(getMission.getInfo());
                  CultimaPanel.missionStack.add(getMission);
                  if(randMission==ASSASSINATION && CultimaPanel.missionsGiven[ASSASSINATION]==0)
                     CultimaPanel.missionsGiven[ASSASSINATION]++;
                  else if(randMission==FIND_ITEM && CultimaPanel.missionsGiven[FIND_ITEM]==0)
                     CultimaPanel.missionsGiven[FIND_ITEM]++;
                  FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                  FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
                  response = getMission.getStartStory();
               }
            }   
         } 
         else if(randMission == Mission.RESCUE_PRISONERS)
         {        //rescue mission
            int numPrisoners = LocationBuilder.countPrisoners(loc);
            if(numPrisoners > 0)
            {
               type = "Rescue";
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(250, numPrisoners*20);
               if(isGarriott)
                  goldReward *= 2;
               if(CultimaPanel.shoppeDiscount)   //king is charmed
                  goldReward = (int)(goldReward * 1.20);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", loc.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
            
               TerrainBuilder.markMapPath(loc.getName());      
               Mission rescueMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(rescueMission.getInfo());
               CultimaPanel.missionStack.add(rescueMission);
               if(CultimaPanel.missionsGiven[RESCUE_PRISONERS]==0)
                  CultimaPanel.missionsGiven[RESCUE_PRISONERS]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = rescueMission.getStartStory();
            }
         }
      }
      if(response.length() == 0)
      {
         response = NPC.kingNoMission[(int)(Math.random()*NPC.kingNoMission.length)]+".";
         selected.setNumInfo(0);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String swordMission(NPCPlayer selected)
   {
      String response = "";
      Location loc = null;
      Item target = null;
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;
      boolean civiliansToWrite = false;
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Boat"))   //bring a boat or ship to someone
         {
            for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
            {
               NPCPlayer p = CultimaPanel.worldMonsters.get(i);
               if(NPC.isShip(p) && p.hasMet() && Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), p.getRow(), p.getCol()) < CultimaPanel.SCREEN_SIZE/2)
               {  //see if there is a boat or ship close to the port that we are in
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  CultimaPanel.worldMonsters.remove(i);
                  FileManager.saveProgress();
                  break;
               }
            }
         }
         else if(currMission.getType().contains("Clear") && currMission.getClearLocation().length()>0)
         {                    //this is a mission to clear a location of monsters
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               ArrayList<Point> monsterFreq = missLoc.getMonsterFreq();
               if(monsterFreq==null || monsterFreq.size()==0)  //we cleared out the monsters here
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Purge") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               int numCivs = LocationBuilder.countCivilians(missLoc);
               if(numCivs==0)      //we killed or scared out all the inhabitants
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addReputation(-10);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Find") && currMission.getTarget()!=null)
         {
            target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               if(Weapon.getWeaponWithName(target.getName())!=null)
               {   //remove weapon from player and add to selected
                  Weapon dropped = CultimaPanel.player.discardWeapon(targetName);
                  selected.setWeapon(dropped);
               }
               else if(Armor.getArmorWithName(target.getName())!=null)
               {  //remove armor from player and add to selected
                  Armor dropped = CultimaPanel.player.discardArmor(targetName);
                  selected.setArmor(dropped);
               }
               else
               {
                  CultimaPanel.player.removeItem(target.getName());
                  selected.addItem(target);
               }
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Treasure") && currMission.getGoldReward() > 0)
         {
            if(CultimaPanel.player.getGold() > currMission.getGoldReward())
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               CultimaPanel.player.pay(currMission.getGoldReward());
               selected.addGold(currMission.getGoldReward());
               FileManager.saveProgress();
            }
         }
      }
      if(selected.getNumInfo() != 10)
         response = NPC.noMission[(int)(Math.random()*NPC.noMission.length)]+".";
      else if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         if(!currMission.getType().contains("Treasure"))       //don't add gold or item for Treasure map mission - goldReward is used to store the amount we need to get and give to the adventurer
         {
            CultimaPanel.player.addGold(goldReward);           
            if(itemReward != null)
               CultimaPanel.player.addItem(itemReward.getName());
         }
         selected.setNumInfo(Player.rollDie(1,3));
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {  //CLEAR_MINE, FIND_WEAPON, TREASURE_MAP
         byte randMission = FIND_WEAPON;
         String ourLoc = CultimaPanel.player.getLocationType().toLowerCase();
         boolean inCapital = Utilities.isCapitalCity(CultimaPanel.player.getMapIndex());
         ArrayList<Byte> missionTypes = new ArrayList<Byte>();
         if(CultimaPanel.missionsGiven[CLEAR_MINE]==0)
            missionTypes.add(Mission.CLEAR_MINE);
         if(CultimaPanel.missionsGiven[FIND_WEAPON]==0)
            missionTypes.add(Mission.FIND_WEAPON);
         if(CultimaPanel.missionsGiven[TREASURE_MAP]==0 && selected.getReputation() < -10)
            missionTypes.add(Mission.TREASURE_MAP);
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);   
         if(CultimaPanel.missionsGiven[WEREWOLF_TOWN]==0)
            missionTypes.add(WEREWOLF_TOWN);   
         if(CultimaPanel.missionsGiven[GET_BOAT]==0 && (ourLoc.contains("port") || inCapital))
            missionTypes.add(GET_BOAT);   
      
         if(missionTypes.size()==0)
         {
            missionTypes.add(Mission.CLEAR_MINE);
            missionTypes.add(Mission.FIND_WEAPON);
            if(selected.getReputation() < -10)
               missionTypes.add(Mission.TREASURE_MAP);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);
            missionTypes.add(WEREWOLF_TOWN);
            if(ourLoc.contains("port") || inCapital)
               missionTypes.add(GET_BOAT);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
      
        //try to build a good treasure map
         Item treasuremap = null;
         if(randMission == Mission.TREASURE_MAP)
         {
            byte[][]currMap = (CultimaPanel.map.get(0));   
            int c = CultimaPanel.equalizeWorldCol(CultimaPanel.player.getWorldCol() + Player.rollDie(-100, 100));
            int r = CultimaPanel.equalizeWorldRow(CultimaPanel.player.getWorldRow() + Player.rollDie(-100, 100));
            int numTries = 0;
            while(!TerrainBuilder.isGoodSpotForTreasure(r, c) && numTries < 1000)
            {
               c = CultimaPanel.equalizeWorldCol(CultimaPanel.player.getWorldCol() + Player.rollDie(-100, 100));
               r = CultimaPanel.equalizeWorldRow(CultimaPanel.player.getWorldRow() + Player.rollDie(-100, 100));
               numTries++;
            }
            if(TerrainBuilder.isGoodSpotForTreasure(r, c))
               treasuremap = (new Item("treasuremap:("+c+":"+r+")", 500));
            else
               randMission = FIND_WEAPON;  
         }
         //try to find a good location for CLEAR_MINE
         if(randMission == Mission.CLEAR_MINE)
         {
            String [] locTypes = {"mine"};
            Location mineLoc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
            if(mineLoc==null || mineLoc.getMonsterFreq().size() == 0)
               randMission = FIND_WEAPON;  
            else
               loc = mineLoc;    
         }        
         if(loc!=null && randMission == Mission.CLEAR_MINE)
         {          
            TerrainBuilder.markMapPath(loc.getName());
            type = "Clear";
            ArrayList<Point> monsterFreq = loc.getMonsterFreq();
            if(monsterFreq.size() > 0)
            {
               byte monsterIndex = (byte)((monsterFreq.get(0)).getX());
               String monsterType = NPC.characterDescription(monsterIndex);
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(50, (int)((monsterFreq.get(0)).getY()));
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("MONSTER_TYPE", monsterType);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  if(randMission == Mission.CLEAR_MINE)
                     story[i]=story[i].replace("LOCATION_NAME", loc.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(randMission == Mission.CLEAR_MINE)
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission clearMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(clearMission.getInfo());
               CultimaPanel.missionStack.add(clearMission);
               if(CultimaPanel.missionsGiven[CLEAR_MINE]==0)
                  CultimaPanel.missionsGiven[CLEAR_MINE]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = clearMission.getStartStory();
            }
            else
            {  //no monsters in the location
               response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
            }
         }
         else if(randMission==GET_BOAT)
         {
            type = "Boat";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            Item gem = Item.getRandomStone();
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
         //              public Mission(String t, String[]story, int wRow, int wCol, int gold,    Item iw, String loc,    Item targ,  String targHolder, int targR, int targC)
            Mission getMission = new Mission(type, story,        startRow, startCol, goldReward,     gem, locName,           null,           targName,     mRow,     mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
         else if(randMission==MERCY_KILL)
         {
            type = "Mercy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
         else if(randMission == Mission.WEREWOLF_TOWN)
         {  //Purge a city or town of werewolves
            type = "Purge";  
            ArrayList<NPCPlayer> inLocNPCs = null;               
            String []locTypes2 = {"village", "domicile"};
            Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
            civiliansToWrite = true;
            inLocNPCs = CultimaPanel.civilians.get(closeCity.getMapIndex());
            if(inLocNPCs.size() >= 1)
            {
               loc = closeCity;
               for(NPCPlayer p: inLocNPCs)      //change all to werewolf
               {
                  if(!NPC.isCivilian(p.getCharIndex()) || p.isNonPlayer())
                     continue;
                  if(!p.getName().contains("~"))
                     p.setName(p.getName()+"~"+p.getCharIndex());
               }
            }
            else
               loc = null;
            if(loc!=null)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               String locName = Display.provinceName(loc.getName());
               int numCivs = LocationBuilder.countCivilians(loc);
               int goldReward = Player.rollDie(50,150);             
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("LOC_NAME", locName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, "none", mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[WEREWOLF_TOWN]==0)
                  CultimaPanel.missionsGiven[WEREWOLF_TOWN]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
         else if(treasuremap!=null && randMission == Mission.TREASURE_MAP)
         {          
            type = "Treasure";
            String targHolder = Utilities.shortName(selected.getName());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(50,200);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_AMOUNT", ""+goldReward);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;            
            String itemName = treasuremap.getName();
            CultimaPanel.player.addItem(itemName);
            if(itemName.contains("treasuremap"))
            {
               Point treasureLoc = Item.getCoordFromTeasureMap(itemName);
               int treasureRow = (int)(treasureLoc.getY());
               int treasureCol = (int)(treasureLoc.getX());
               //NPCPlayer marker = new NPCPlayer(NPC.BEGGER, treasureCol, treasureRow, 0, "temp", "temp,1,1,1,10");
               //CultimaPanel.worldMonsters.add(marker);
               mRow = (treasureRow/10)*10;
               mCol = (treasureCol/10)*10;
            }
            //              public Mission(String t,     String[]story, int wRow, int wCol, int gold,    Item iw, String loc,    Item targ,  String targHolder, int targR, int targC)
            Mission treasureMission = new Mission(type,  story,         startRow, startCol, goldReward,  null,    "none",        null,       targHolder,           mRow,       mCol);
            selected.setMissionInfo(treasureMission.getInfo());
            CultimaPanel.missionStack.add(treasureMission);
            if(CultimaPanel.missionsGiven[TREASURE_MAP]==0)
               CultimaPanel.missionsGiven[TREASURE_MAP]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = treasureMission.getStartStory();
         }   
         else if(randMission == Mission.FIND_WEAPON)
         {  
            type = "Find";
            String locType = "place";
            NPCPlayer targNPC = null;
         //pick a close adventure spot or battlefield
            loc =  TerrainBuilder.closeLocation(CultimaPanel.allAdventure);
            if(loc == null)
            {
               String [] locTypes = {"cave"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
               locType = "cave";
            }
            if(loc == null)
            {
               String [] locTypes = {"mine"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
               locType = "mine";
            }
            if(loc == null)
            {
               String [] locTypes = {"lair"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
               locType = "lair";
            }
            if(loc != null)
            {
               String terrType = loc.getTerrain().getName().toLowerCase();
               if(TerrainBuilder.habitablePlace(terrType))
                  locType =  "town";
               else if(terrType.contains("cave"))
                  locType = "cave";
               else if(terrType.contains("mine"))
                  locType = "mine";
               else if(terrType.contains("lair"))
                  locType = "lair";
               else if(terrType.contains("dungeon"))
                  locType = "dungeon";
               else if(terrType.contains("temple"))
                  locType = "temple";   
            }
            byte monsterType = NPC.TROLL;
            target =  Weapon.getRandomWeapon(); 
            if(Math.random() < 0.5)
            {  //make it one of 5 armed brigands
               int brigType = Player.rollDie(1,5);
               if(brigType == 1)
               {
                  monsterType = NPC.BRIGAND_SWORD;
                  target = Weapon.getSword();
                  if(Math.random() < 0.5)
                     target = Weapon.getBlessedSword(); 
               }
               else if(brigType == 2)
               {
                  monsterType = NPC.BRIGAND_SPEAR;
                  int weapType = Player.rollDie(1,4);
                  if(weapType==1)
                     target = Weapon.getHalberd(); 
                  else if(weapType==2)  
                     target = Weapon.getIceStaff();
                  else if(weapType==3)
                     target = Weapon.getBrightHalberd();
                  else
                     target = Weapon.getSpear();
               }
               else if(brigType == 3)
               {
                  monsterType = NPC.BRIGAND_DAGGER;
                  int weapType = Player.rollDie(1,6);
                  if(weapType==1)
                     target = Weapon.getDagger(); 
                  else if(weapType==2)  
                     target = Weapon.getPoisonDagger();
                  else if(weapType==3)
                     target = Weapon.getSoulDagger();
                  else if(weapType==4)
                     target = Weapon.getBaneDagger();
                  else if(weapType==5)
                     target = Weapon.getFrostDagger();
                  else //if(weapType==6)
                     target = Weapon.getMagmaDagger();
               }
               else if(brigType == 4)
               {
                  monsterType = NPC.BRIGAND_HAMMER;
                  int weapType = Player.rollDie(1,4);
                  if(weapType==1)
                     target = Weapon.getHammer(); 
                  else if(weapType==2)  
                     target = Weapon.getSpikedHammer();
                  else if(weapType==3)
                     target = Weapon.getBaneHammer();
                  else
                     target = Weapon.getExoticHammer();
               }
               else if(brigType == 5)
               {
                  monsterType = NPC.BRIGAND_CROSSBOW;
                  target = Weapon.getCrossbow();
               }
            }
            if(Math.random() < 0.5)
               target = Armor.getRandomArmor();
            targNPC = new NPCPlayer(monsterType, 0, 0, 0, locType);  
            if(target instanceof Weapon)
               targNPC.setWeapon((Weapon)(target));  
            else
               targNPC.setArmor((Armor)(target));              
            boolean success = TerrainBuilder.addNPCtoLocation(loc, locType, targNPC);
         
            if(targNPC!=null && loc!=null && success)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(150, target.getValue()/3);
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               String targName = Utilities.shortName(selected.getName());         
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
                  story[i]=story[i].replace("ITEM_NAME", target.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target.getName(),target.getValue()), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_WEAPON]==0)
                  CultimaPanel.missionsGiven[FIND_WEAPON]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         } 
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(Player.rollDie(1,3));
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String taxmanMission(NPCPlayer selected)
   {
      String response = "";  
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      boolean civiliansToWrite = false;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      //***find possible spawn locations for a gang mission
      Location thisLoc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
      int mi = CultimaPanel.player.getMapIndex();
      byte [][] currMap = CultimaPanel.map.get(mi);
      ArrayList<Point>spawnPoints = new ArrayList<Point>();
      for(int cr = 0; cr<currMap.length; cr++)
         for(int cc = 0; cc<currMap[0].length; cc++)
            if(TerrainBuilder.isGoodCityMonsterSpawn(cr, cc, mi))
               spawnPoints.add(new Point(cr, cc));
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Collect") && currMission.getGoldReward() > 0)
         {
            if(CultimaPanel.player.getGold() > currMission.getGoldReward())
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               CultimaPanel.player.pay(currMission.getGoldReward());
               selected.addGold(currMission.getGoldReward());
               selected.setEndChaseDay(CultimaPanel.days + 15);
               selected.setMoveType(NPC.ROAM);
               selected.setHasBeenAttacked(false);
               Utilities.standDownGuards();
               Utilities.standDownTaxmen();
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Find"))
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.addReputation(5);
               CultimaPanel.player.removeItem(target.getName());
               selected.setEndChaseDay(CultimaPanel.days + 15);
               selected.setMoveType(NPC.ROAM);
               selected.setHasBeenAttacked(false);
               Utilities.standDownGuards();
               Utilities.standDownTaxmen();
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Save") && currMission.getClearLocation()!=null)
         {
            int numGangMembers = LocationBuilder.countGangMembers(CultimaPanel.getLocation(currMission.getClearLocation())); 
            if(numGangMembers==0)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               selected.setEndChaseDay(CultimaPanel.days + 15);
               selected.setMoveType(NPC.ROAM);
               selected.setHasBeenAttacked(false);
               Utilities.standDownGuards();
               Utilities.standDownTaxmen();
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Assassinate") && currMission.getTarget()!=null)
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.removeItem(target.getName());
               if(!target.getName().contains("bounty"))
                  selected.addItem(target);
               selected.setEndChaseDay(CultimaPanel.days + 15);
               selected.setMoveType(NPC.ROAM);
               selected.setHasBeenAttacked(false);
               Utilities.standDownGuards();
               Utilities.standDownTaxmen();
               FileManager.saveProgress();
            }
            else if(CultimaPanel.player.hasItem("bounty"))
            {  //if we just have an item called bounty and there is only one bounty mission, resolve it.
               int count = 0;
               for(Mission m:CultimaPanel.missionStack)
               {
                  if(m.getType().contains("Assassinate"))
                     count++;
               }
               if (count==1)
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  if(currMission.getType().contains("Assassinate"))
                     CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.removeItem("bounty");
                  FileManager.saveProgress();
               }
            }
         }
      
      }  //end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward()/10;
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(0);    
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      { 
         byte randMission = TAX_COLLECTOR;
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         //fill list of missions to pick from with the ones we have not seen yet
         if(CultimaPanel.missionsGiven[TAX_COLLECTOR]==0)
            missionTypes.add(TAX_COLLECTOR);
         if(CultimaPanel.missionsGiven[TAX_IMPOSTER]==0)
            missionTypes.add(TAX_IMPOSTER);
         if(CultimaPanel.missionsGiven[TAX_MOB]==0 && spawnPoints.size()>0)
            missionTypes.add(TAX_MOB);
         if(CultimaPanel.missionsGiven[FIND_LEDGER]==0)
            missionTypes.add(FIND_LEDGER);
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);
         if(missionTypes.size()==0)    //we have seen all the mission types before, so pick a random one
         {
            missionTypes.add(TAX_COLLECTOR);
            missionTypes.add(FIND_LEDGER);
            missionTypes.add(TAX_IMPOSTER);
            if(spawnPoints.size()>0)
               missionTypes.add(TAX_MOB);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
         
         NPCPlayer targetRival = null;
         for(NPCPlayer p:Utilities.getNPCsInLoc(selected.getMapIndex()))
         {
            if(!p.getName().toLowerCase().contains(selected.getName().toLowerCase()) && p.getCharIndex()!=NPC.CHILD)
            {
               targetRival = p;
               break;
            }
         }
         String targName = Utilities.shortName(targetRival.getName());
         if(targetRival!=null && randMission == Mission.TAX_COLLECTOR)
         {
            CultimaPanel.missionTargetNames.add(targName);
            String type = "Collect";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(20,200);
            targetRival.addGold(goldReward);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_AMOUNT", ""+goldReward);
               story[i]=story[i].replace("NPC_NAME", targName);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;            
            //         public Mission(String t,     String[]story, int wRow, int wCol, int gold,    Item iw, String loc,    Item targ,  String targHolder, int targR, int targC)
            Mission taxMission = new Mission(type,  story,         startRow, startCol, goldReward,  null,    "none",        null,       targName,           mRow,       mCol);
            selected.setMissionInfo(taxMission.getInfo());
            CultimaPanel.missionStack.add(taxMission);
            if(CultimaPanel.missionsGiven[TAX_COLLECTOR]==0)
               CultimaPanel.missionsGiven[TAX_COLLECTOR]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = taxMission.getStartStory();
         }
         else if(randMission == FIND_LEDGER)
         {
            String [] temp = missions[FIND_LEDGER];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targetName = "tax-ledger";
            int targItemPrice = (int)(goldReward);
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            //place ledger somewhere in the map
            Point bookCoord = TerrainBuilder.addBooktoLocation(CultimaPanel.player.getMapIndex());
            if(bookCoord != null)
            {
               int mRow = -1, mCol = -1;
               int bookRow = (int)(bookCoord.getX());
               int bookCol = (int)(bookCoord.getY());
               //add temp nonPlayer
               NPCPlayer marker = new NPCPlayer(NPC.BEGGER, bookRow, bookCol, CultimaPanel.player.getMapIndex(), "temp", "temp,1,1,1,10");
               CultimaPanel.civilians.get(CultimaPanel.player.getMapIndex()).add(marker);   //add a nonPlayer NPC marker so we can be directed to the mission item with the Knowing spell
               Mission getMission = new Mission("Find", story, startRow, startCol, goldReward, null, "none", new Item(targetName, goldReward), "none", mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_LEDGER]==0)
                  CultimaPanel.missionsGiven[FIND_LEDGER]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               response = getMission.getStartStory();
            }
            else
               response = ""; //no book placed, so clear out mission 
         } 
         else if(randMission == TAX_MOB && spawnPoints.size() > 0)
         { 
            String targetHolder = Utilities.nameGenerator("name");
            String type = "Save";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NPC_NAME", ""+targetHolder);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            selected.setMoveType(NPC.STILL);
            int mRow = selected.getRow();
            int mCol = selected.getCol();
            //***add bandits to the location
            String locType = CultimaPanel.player.getLocationType();
            int numBandits = Player.rollDie(3,7);
            byte indexType = NPC.getRandGangMember();
            for(int i=0; i<numBandits; i++)
            { 
               Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
               int r = (int)(spawn.getX());
               int c = (int)(spawn.getY());
               NPCPlayer bandit = new NPCPlayer(indexType, r, c, mi, r, c, locType);
               bandit.setName("Brute "+targetHolder);
               if(indexType==NPC.BRIGANDKING)
                  bandit.setWeapon(Weapon.getShortSwords());
               else if(indexType==NPC.BOWASSASSIN)
                  bandit.setWeapon(Weapon.getLongbow());
               else if(indexType==NPC.VIPERASSASSIN)
                  bandit.setWeapon(Weapon.getDagger());
               else if(indexType==NPC.ENFORCER)
                  bandit.setWeapon(Weapon.getCrossbow());
               bandit.setMoveType(NPC.CHASE);      
               CultimaPanel.worldMonsters.add(bandit);
            }
            //***                   //Mission(String t, String[]story, int wRow, int wCol, int gold,   Item iw,  String loc,        Item targ, String targHolder, int targR, int targC)
            Mission saveMission = new Mission(type,     story,         startRow, startCol, goldReward, null,     thisLoc.getName(), null,      targetHolder,      mRow,      mCol);
            selected.setMissionInfo(saveMission.getInfo());
            CultimaPanel.missionStack.add(saveMission);
            if(CultimaPanel.missionsGiven[TAX_MOB]==0)
               CultimaPanel.missionsGiven[TAX_MOB]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = saveMission.getStartStory();
         }
         else if(randMission == Mission.TAX_IMPOSTER)
         {
            ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(selected.getMapIndex());
            String type = "Assassinate";
            NPCPlayer targNPC = null;
               
            ArrayList<NPCPlayer> targets = new ArrayList<NPCPlayer>();
            for(NPCPlayer pl: inLocNPCs)
               if(!pl.getName().equals(selected.getName()) && pl.getCharIndex()==NPC.TAXMAN)
                  targets.add(pl);
            if(targets.size()==0)
               for(NPCPlayer pl: inLocNPCs)
                  if(!pl.getName().equals(selected.getName()) && pl.getCharIndex()==NPC.JESTER)
                     targets.add(pl);
            if(targets.size()==0)
               targNPC = null;
            else
               targNPC = targets.get((int)(Math.random()*targets.size()));
         
            if(targNPC!=null && thisLoc!=null)
            {
               if(targNPC.getCharIndex()==NPC.JESTER)
                  targNPC.setCharIndex(NPC.TAXMAN);
               if(targNPC.getReputation() > 0)
                  targNPC.setReputation(targNPC.getReputation()*-1);   
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
            //higher reward for targets further away
               int goldReward = 0;
               targName = Utilities.shortName(targNPC.getName());  
               CultimaPanel.missionTargetNames.add(targName);   
               String target = targName+"-bounty";
               int targItemPrice = (int)(goldReward);
               targNPC.addItem(new Item(target, targItemPrice));
               targNPC.setGold(targNPC.getGold()*5);
               String locName = Display.provinceName(thisLoc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[randMission]==0)
                  CultimaPanel.missionsGiven[randMission]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               civiliansToWrite = true;
               response = getMission.getStartStory();
            }
            else
               response = "";
         }
         else if(randMission==MERCY_KILL)
         {
            String type = "Mercy";
            Location loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
         else
            response = "";
      }
      if(response.length() == 0)
      {
        //no mission
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(0);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String cityMission(NPCPlayer selected)
   {
      String [] lostMission = {"Thy fire raged for too long. Much was lost.", "Too much damage was done by thy fire.", "'Tis a shame. Much was lost in that terrible fire.", "I know thee tried, but much was destroyed by thy fire."};
      String response = "";
      boolean civiliansToWrite = false;   //if we build a habitable location for the mission target, write out civilians file as well
      boolean closeLairWithPrisoners = false;
      boolean hasCoffins = TerrainBuilder.hasCoffin();
      Location loc = TerrainBuilder.closeLairWithPrisoners(CultimaPanel.allLocations, 50);
      Location thisLoc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
      int numBeggers = LocationBuilder.countPrisoners(thisLoc);  
      ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(selected.getMapIndex());
      //***find possible spawn locations for a gang mission
      int mi = CultimaPanel.player.getMapIndex();
      byte [][] currMap = CultimaPanel.map.get(mi);
      ArrayList<Point>spawnPoints = new ArrayList<Point>();
      for(int cr = 0; cr<currMap.length; cr++)
         for(int cc = 0; cc<currMap[0].length; cc++)
            if(TerrainBuilder.isGoodCityMonsterSpawn(cr, cc, mi))
               spawnPoints.add(new Point(cr, cc));
      ArrayList<Point>fireSpawnPoints = new ArrayList<Point>();
      for(int cr = 0; cr<currMap.length; cr++)
         for(int cc = 0; cc<currMap[0].length; cc++)
            if(LocationBuilder.isCombustableStructure(currMap, cr,cc))
               fireSpawnPoints.add(new Point(cr, cc));
     //***
      String locType = "dungeon";
      if(loc == null)
      {
         loc = TerrainBuilder.closeLocation(CultimaPanel.allAdventure);
         if(loc == null)
         {
            String [] locTypes = {"cave"};
            loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
            locType = "cave";
         }
         if(loc == null)
         {
            String [] locTypes = {"mine"};
            loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
            locType = "mine";
         }
         if(loc == null)
         {
            String [] locTypes = {"lair"};
            loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
            locType = "lair";
         }
      }
      else
         closeLairWithPrisoners = true;
   
      if(loc != null)
      {
         String terrType = loc.getTerrain().getName().toLowerCase();
         if(TerrainBuilder.habitablePlace(terrType))
            locType =  "town";
         else if(terrType.contains("cave"))
            locType = "cave";
         else if(terrType.contains("mine"))
            locType = "mine";
         else if(terrType.contains("lair"))
            locType = "lair";
         else if(terrType.contains("dungeon"))
            locType = "dungeon";
         else if(terrType.contains("temple"))
            locType = "temple";   
      }  
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Extinguish") && currMission.getClearLocation()!=null)
         {
            int endDay = currMission.getTargetRow();
            int endTime = currMission.getTargetCol();
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               int numFires = LocationBuilder.countFires(missLoc);
               if(numFires==0)      
               {
                  if(CultimaPanel.days==endDay && CultimaPanel.time <= endTime)
                  {
                     currMission.setState(Mission.FINISHED);
                     Player.stats[Player.MISSIONS_COMPLETED]++;
                     if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                        Achievement.earnAchievement(Achievement.TASK_MASTER);
                     selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                     CultimaPanel.player.addReputation(10);
                     FileManager.saveProgress();
                  }
                  else  //we didn't put out the fire in time
                  {
                     selected.setNumInfo(0);  
                     selected.setMissionInfo("none");
                     if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
                        CultimaPanel.missionStack.remove(currMissionIndex);
                     FileManager.saveProgress();   
                     return lostMission[(int)(Math.random()*lostMission.length)];
                  }
               }
            }
         }
         else if(currMission.getType().contains("Burn") && currMission.getClearLocation().length()>0)
         {   //check to see if break shoppe sign mission is finished   
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if (!hasCoffins)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(-5);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Save") && currMission.getClearLocation()!=null)
         {
            int numGangMembers = LocationBuilder.countGangMembers(CultimaPanel.getLocation(currMission.getClearLocation())); 
            if(numGangMembers==0)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Harvest") && currMission.getTarget()!=null)
         {   //check to see if harvest egg mission is finished   
            Item target = currMission.getTarget();
            String targetName = target.getName().toLowerCase();
            if(targetName.contains("3-serpent-eggs") && CultimaPanel.player.numSerpentEggs()>=3)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               for(int i=0; i<3; i++)
               {
                  CultimaPanel.player.removeItem("serpent-egg");
                  selected.addItem(Item.getSerpentEgg());
               }
               FileManager.saveProgress();
            }            
         }
         else if(currMission.getType().contains("Frighten"))
         {
            boolean removed = true;
            for(NPCPlayer p:CultimaPanel.civilians.get(selected.getMapIndex()))
            {
               String pName = Utilities.shortName(p.getName());
               String targName = Utilities.shortName(currMission.getTargetHolder());
               if(pName.equalsIgnoreCase(targName))
               {
                  removed = false;
                  break;
               }
            }
            if(removed)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(-5);
               FileManager.saveProgress();
            }
         }
         else if((currMission.getType().contains("Slay") || currMission.getType().contains("Assassinate") || currMission.getType().contains("Find") || currMission.getType().contains("Swine")) && (currMission.getTarget()!=null))
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            boolean vampMiss = (currMission.getStartStory().toLowerCase().contains("vampyre")); 
            boolean hasCoffin = true;
            boolean hasHead = (CultimaPanel.player.hasItem(targetName));
            if(vampMiss)  //make sure we also burned the coffin out of the area
            {
               Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
               hasCoffin = TerrainBuilder.hasCoffin(missLoc);
            } 
            if((vampMiss && !hasCoffin && hasHead) || (!vampMiss && hasHead))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               if(currMission.getType().contains("Assassinate"))
                  CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               if(currMission.getType().contains("Assassinate"))
                  CultimaPanel.player.addReputation(-10);
               else
                  CultimaPanel.player.addReputation(5);
               CultimaPanel.player.removeItem(target.getName());
               if(Item.itemIsStone(targetName))
                  selected.addItem(target);
               FileManager.saveProgress();
            }
            else if(currMission.getType().contains("Assassinate") && CultimaPanel.player.hasItem("bounty"))
            {  //if we just have an item called bounty and there is only one bounty mission, resolve it.
               int count = 0;
               for(Mission m:CultimaPanel.missionStack)
               {
                  if(m.getType().contains("Assassinate"))
                     count++;
               }
               if (count==1)
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  if(currMission.getType().contains("Assassinate"))
                     CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.removeItem("bounty");
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Rescue") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               if(LocationBuilder.countPrisoners(missLoc)==0)      //we freed all the prisoners
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(10);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Remove"))        //remove beggers from a town, or help them to be upright citizens
         {
            if(numBeggers==0)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Train"))
         {
            if(!CultimaPanel.player.getDogName().equals("none"))
            {
               NPCPlayer dog = Utilities.getDog();
               dog.setMoveTypeTemp(NPC.ROAM);
               dog.setHasMet(false);
               CultimaPanel.player.setDogBasicInfo("none");
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               FileManager.saveProgress();
            }
         }
      }  //end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(3);    //setting a man, woman or wise value to 3 will make it so they will map for you for free, or teach you a spell as thanks
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else  //start a new mission
      {
      //CITY_REVENGE, FIND_PERSON, MESSAGE, CLEAR_BEGGERS, TRAIN_DOG, CLEAR_BANDITS, FIND_SERPENT_EGGS, FIRE_FIGHTER, FIND_GEM
         ArrayList<Byte> missionTypes = new ArrayList<Byte>();    
         byte randMission = MESSAGE; 
         if(CultimaPanel.missionsGiven[BEAT_SWINE_PLAYER]==0 && inLocNPCs.size()>1)
            missionTypes.add(BEAT_SWINE_PLAYER);
         if(CultimaPanel.missionsGiven[BURN_COFFINS]==0 && hasCoffins)
            missionTypes.add(BURN_COFFINS);
         if(CultimaPanel.missionsGiven[LOVE_TRIANGLE]==0 && inLocNPCs.size()>1)
            missionTypes.add(LOVE_TRIANGLE);
         if(CultimaPanel.missionsGiven[MESSAGE]==0)
            missionTypes.add(MESSAGE);
         if(CultimaPanel.missionsGiven[CITY_REVENGE]==0)
            missionTypes.add(CITY_REVENGE);
         if(CultimaPanel.missionsGiven[FIND_PERSON]==0 && loc != null)
            missionTypes.add(FIND_PERSON);
         if(CultimaPanel.missionsGiven[SLAY_VAMPIRE]==0 && loc != null)
            missionTypes.add(SLAY_VAMPIRE);
         if(CultimaPanel.missionsGiven[CLEAR_BEGGERS]==0 && numBeggers > 0 && selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
            missionTypes.add(CLEAR_BEGGERS); 
         if(CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]==0 && selected.isShopkeep() && selected.getName().contains("mage"))
            missionTypes.add(FIND_SERPENT_EGGS);     
         if(CultimaPanel.missionsGiven[TRAIN_DOG]==0 && selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
            missionTypes.add(TRAIN_DOG);
         if(spawnPoints.size() > 0 && CultimaPanel.missionsGiven[CLEAR_BANDITS]==0 && selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
            missionTypes.add(CLEAR_BANDITS);
         if(CultimaPanel.missionsGiven[FIND_GEM]==0 && selected.isShopkeep() && selected.getName().toLowerCase().contains("ironsmith"))
            missionTypes.add(FIND_GEM);
         if(CultimaPanel.missionsGiven[FIRE_FIGHTER]==0 && CultimaPanel.time >= 0 && CultimaPanel.time <= 18 && CultimaPanel.weather == 0)
            missionTypes.add(FIRE_FIGHTER);
         if(CultimaPanel.missionsGiven[FIND_ELK_PELT]==0 && selected.getLocationType().toLowerCase().contains("village"))
            missionTypes.add(FIND_ELK_PELT);
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);
         if(missionTypes.size()==0)
         {
            if(inLocNPCs.size()>1)
            {
               missionTypes.add(LOVE_TRIANGLE);
               missionTypes.add(BEAT_SWINE_PLAYER);
            }
            if(hasCoffins)
               missionTypes.add(BURN_COFFINS);
            missionTypes.add(MESSAGE);
            missionTypes.add(CITY_REVENGE);
            if(loc != null)
            {
               missionTypes.add(FIND_PERSON);
               missionTypes.add(SLAY_VAMPIRE);
            }
            if(numBeggers > 0 && selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
               missionTypes.add(CLEAR_BEGGERS);     
            if(selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
               missionTypes.add(TRAIN_DOG);
            if(spawnPoints.size() > 0 && selected.isShopkeep() && (selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN))
               missionTypes.add(CLEAR_BANDITS);  
            if(selected.isShopkeep() && selected.getName().contains("mage"))
               missionTypes.add(FIND_SERPENT_EGGS); 
            if(CultimaPanel.time >= 0 && CultimaPanel.time <= 18 && CultimaPanel.weather == 0)
               missionTypes.add(FIRE_FIGHTER);
            if(selected.isShopkeep() && selected.getName().toLowerCase().contains("ironsmith"))
               missionTypes.add(FIND_GEM);
            if(selected.getLocationType().toLowerCase().contains("village"))
               missionTypes.add(FIND_ELK_PELT);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
                        
         if(randMission == FIND_PERSON && !closeLairWithPrisoners)
         {
         //add a BEGGER into loc
            boolean success = TerrainBuilder.addPrisoner(loc, locType);
            if(!success)
            {
               missionTypes.clear();
               missionTypes.add(Mission.CITY_REVENGE);
               missionTypes.add(Mission.MESSAGE); 
               randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
            }
         }
         if(randMission == Mission.SLAY_VAMPIRE && loc!=null)
         {  
            type = "Slay";
            NPCPlayer targNPC = null;
            byte monsterType = NPC.MALEVAMP;
            if(Math.random() < 0.5)
               monsterType = NPC.FEMALEVAMP;
            String target = NPC.characterDescription(monsterType)+"-head";
            targNPC = new NPCPlayer(monsterType, 0, 0, 0, locType);  
            targNPC.modifyStats(1.2);
            targNPC.addItem(new Item(target,0));
            boolean success = TerrainBuilder.addNPCtoLocation(loc, locType, targNPC);
            if(targNPC!=null && success)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 0;
               String targName = Utilities.shortName(selected.getName());         
               String locName = Display.provinceName(loc.getName());
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("LOC_NAME", locName);
                  story[i]=story[i].replace("NPC_NAME", Utilities.shortName(targNPC.getName()));
                  story[i]=story[i].replace("NAME", CultimaPanel.player.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target,0), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[SLAY_VAMPIRE]==0)
                  CultimaPanel.missionsGiven[SLAY_VAMPIRE]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               civiliansToWrite = true;
               response = getMission.getStartStory();
            } 
         }
         else if(randMission == Mission.CITY_REVENGE || randMission == Mission.MESSAGE)
         {  //Assassinate mission
            if(randMission == Mission.CITY_REVENGE)
               type = "Assassinate";
            else //if(randMission == Mission.MESSAGE)
               type = "Find";
            int randType = Player.rollDie(10);
            if(randMission == Mission.MESSAGE)
               randType = Player.rollDie(4,10);
            NPCPlayer targNPC = null;
            if(inLocNPCs.size() <= 1)
               randType = Player.rollDie(5,10);
            if(randType < 5)           //assassinate someone at the same location
            {
               loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
               targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
               
               ArrayList<NPCPlayer> targets = new ArrayList<NPCPlayer>();
               for(NPCPlayer pl: inLocNPCs)
                  if(!pl.getName().equals(selected.getName()))
                     targets.add(pl);
               if(targets.size()==0)
                  targNPC = null;
               else
                  targNPC = targets.get((int)(Math.random()*targets.size()));
            }
            if(targNPC==null || (randType>=5 && randType<=8))       //assassinate or find someone at the next closest town
            {
               String []locTypes2 = {"city","fortress","port","village"};
               Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCity.getMapIndex());
               if(inLocNPCs.size() >= 1)
               {
                  targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
                  loc = closeCity;
               }
               else
                  loc = null;
            }
            if(targNPC==null || randType>8)      //assassinate or find someone at the next closest castle
            {
               String [] locTypes = {"castle","tower"};
               Location closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCastle.getMapIndex());
               if(inLocNPCs.size() >= 1)
               {
                  targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
                  loc = closeCastle;
               }
               else
                  loc = null;
            }
            if(targNPC==null)    //couln't find a target, so try the same city again
            {
               loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
               targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
               
               ArrayList<NPCPlayer> targets = new ArrayList<NPCPlayer>();
               for(NPCPlayer pl: inLocNPCs)
                  if(!pl.getName().equals(selected.getName()))
                     targets.add(pl);
               if(targets.size()==0)
                  targNPC = null;
               else
                  targNPC = targets.get((int)(Math.random()*targets.size()));
            }
         
            if(targNPC!=null && loc!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
            //higher reward for targets further away
               int goldReward = Math.max(60, targNPC.getLevel()+(int)(Display.wrapDistance(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), loc.getRow(), loc.getCol())));
               String targName = Utilities.shortName(targNPC.getName());     
               CultimaPanel.missionTargetNames.add(targName);
               String target = targName+"-bounty";
               if(randMission == Mission.MESSAGE)
               {
                  String selName = selected.getName();
                  if(selName.indexOf(" of ") >= 0)
                  {
                     int pos = selName.indexOf(" of ");
                     selName = selName.substring(0, pos);
                  }
                  if(selName.indexOf(" ") >= 0)
                  {
                     String [] parts = selName.split(" ");
                     selName = parts[parts.length-1];
                  }
                  goldReward = 25;
                  target = selName+"-message";      //make this the short version of selected's name
               }
               int targItemPrice = (int)(goldReward);
               targNPC.addItem(new Item(target, targItemPrice));
                
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
                  story[i]=story[i].replace("ITEM_NAME", target);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               TerrainBuilder.markMapPath(loc.getName());
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[randMission]==0)
                  CultimaPanel.missionsGiven[randMission]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               civiliansToWrite = true;
               response = getMission.getStartStory();
            }
            else
            {
            //no mission
               response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
               selected.setNumInfo(0);
            }   
         } 
         else if(randMission==MERCY_KILL)
         {
            type = "Mercy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = getMission.getStartStory();
         }
         else if(randMission == Mission.FIND_PERSON)
         {        //rescue mission
            int numPrisoners = LocationBuilder.countPrisoners(loc);
            if(numPrisoners > 0)
            {
               type = "Rescue";
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Player.rollDie(40,100);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", loc.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
               TerrainBuilder.markMapPath(loc.getName());      
               Mission rescueMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(rescueMission.getInfo());
               CultimaPanel.missionStack.add(rescueMission);
               if(CultimaPanel.missionsGiven[FIND_PERSON]==0)
                  CultimaPanel.missionsGiven[FIND_PERSON]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               civiliansToWrite = true;
               response = rescueMission.getStartStory();
            }
         }
         else if(randMission == CLEAR_BEGGERS)
         { 
            String targName = " all beggers";
            type = "Remove";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 50 + (5*numBeggers);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            TerrainBuilder.markMapPath(loc.getName());      
            Mission removeMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, targName, mRow, mCol);
            selected.setMissionInfo(removeMission.getInfo());
            CultimaPanel.missionStack.add(removeMission);
            if(CultimaPanel.missionsGiven[CLEAR_BEGGERS]==0)
               CultimaPanel.missionsGiven[CLEAR_BEGGERS]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = removeMission.getStartStory();
         }
         else if(randMission == TRAIN_DOG)
         {
            Item dog = new Item("dog", 25);
            String targetName = Utilities.shortName(selected.getName());
            type = "Train";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 25;
            double priceShift = ((Math.random()*51) - 25)/100.0;  //-.25 to +.25
            goldReward = (int)(goldReward + (goldReward * priceShift));            
            if(CultimaPanel.shoppeDiscount)   //charmed
               goldReward = (int)(goldReward * 1.20);
         
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission trainMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", dog, targetName, mRow, mCol);
            selected.setMissionInfo(trainMission.getInfo());
            CultimaPanel.missionStack.add(trainMission);
            if(CultimaPanel.missionsGiven[TRAIN_DOG]==0)
               CultimaPanel.missionsGiven[TRAIN_DOG]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = trainMission.getStartStory();
         }
         else if(randMission == CLEAR_BANDITS && spawnPoints.size() > 0)
         { 
            String targetHolder = Utilities.nameGenerator("name");
            type = "Save";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(75, 200);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               story[i]=story[i].replace("NPC_NAME", ""+targetHolder);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            //***add bandits to the location
            locType = CultimaPanel.player.getLocationType();
            int numBandits = Player.rollDie(3,7);
            byte indexType = NPC.getRandGangMember();
            for(int i=0; i<numBandits; i++)
            { 
               Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
               int r = (int)(spawn.getX());
               int c = (int)(spawn.getY());
               NPCPlayer bandit = new NPCPlayer(indexType, r, c, mi, r, c, locType);
               bandit.setName(targetHolder+" Sibling");
               if(indexType==NPC.BRIGANDKING)
                  bandit.setWeapon(Weapon.getShortSwords());
               else if(indexType==NPC.BOWASSASSIN)
                  bandit.setWeapon(Weapon.getLongbow());
               else if(indexType==NPC.VIPERASSASSIN)
                  bandit.setWeapon(Weapon.getDagger());
               else if(indexType==NPC.ENFORCER)
                  bandit.setWeapon(Weapon.getCrossbow());
               bandit.setMoveType(NPC.CHASE);      
               //CultimaPanel.civilians.get(mi).add(bandit); 
               CultimaPanel.worldMonsters.add(bandit);
            }
            //***                   //Mission(String t, String[]story, int wRow, int wCol, int gold,   Item iw,  String loc,        Item targ, String targHolder, int targR, int targC)
            Mission saveMission = new Mission(type,     story,         startRow, startCol, goldReward, null,     thisLoc.getName(), null,      targetHolder,      mRow,      mCol);
            selected.setMissionInfo(saveMission.getInfo());
            CultimaPanel.missionStack.add(saveMission);
            if(CultimaPanel.missionsGiven[CLEAR_BANDITS]==0)
               CultimaPanel.missionsGiven[CLEAR_BANDITS]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = saveMission.getStartStory();
         }
         else if(randMission == Mission.FIND_SERPENT_EGGS)
         {  
            type = "Harvest";
            NPCPlayer targNPC = selected;       
            if(targNPC!=null)
            {
               Item ourItem = new Item("3-serpent-eggs",90);
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Player.rollDie(25, 100);
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               String targName = Utilities.shortName(selected.getName());         
               for(int i=0; i<story.length; i++)
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", ourItem, targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]==0)
                  CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               civiliansToWrite = true;
               response = getMission.getStartStory();
            }   
         } 
         else if(randMission == LOVE_TRIANGLE)
         { 
            NPCPlayer targetRival = null;
            for(NPCPlayer p:Utilities.getNPCsInLoc(selected.getMapIndex()))
            {
               if(!p.getName().toLowerCase().contains(selected.getName().toLowerCase()) && p.getCharIndex()!=NPC.CHILD)
               {
                  targetRival = p;
                  break;
               }
            }
            String targName = Utilities.shortName(targetRival.getName());
            CultimaPanel.missionTargetNames.add(targName);
            type = "Frighten";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(40,100);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NPC_NAME", targName);
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            TerrainBuilder.markMapPath(loc.getName());      
            Mission removeMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, targName, mRow, mCol);
            selected.setMissionInfo(removeMission.getInfo());
            CultimaPanel.missionStack.add(removeMission);
            if(CultimaPanel.missionsGiven[LOVE_TRIANGLE]==0)
               CultimaPanel.missionsGiven[LOVE_TRIANGLE]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = removeMission.getStartStory();
         }
         else if(randMission == BEAT_SWINE_PLAYER)
         { 
            NPCPlayer targetRival = null;
            for(NPCPlayer p:Utilities.getNPCsInLoc(selected.getMapIndex()))
            {
               if(!p.getName().toLowerCase().contains(selected.getName().toLowerCase()) && p.getCharIndex()!=NPC.CHILD)
               {
                  targetRival = p;
                  break;
               }
            }
            String targName = Utilities.shortName(targetRival.getName());
            CultimaPanel.missionTargetNames.add(targName);   
            String target = targName+"-swine-bounty";
            targetRival.addItem(new Item(target, 0));
            targetRival.setIsSwinePlayer(true);
            targetRival.addItem(Item.getLoadedCube());
            type = "Swine";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NPC_NAME", targetRival.getName());
               story[i]=story[i].replace("PLAYER_NAME", CultimaPanel.player.getName());
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission swineMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), new Item(target, goldReward), targName, mRow, mCol);
            selected.setMissionInfo(swineMission.getInfo());
            CultimaPanel.missionStack.add(swineMission);
            if(CultimaPanel.missionsGiven[BEAT_SWINE_PLAYER]==0)
               CultimaPanel.missionsGiven[BEAT_SWINE_PLAYER]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = swineMission.getStartStory();
         }
         else if(randMission == FIRE_FIGHTER && fireSpawnPoints.size() > 0)
         { 
            type = "Extinguish";
            Point spawn = fireSpawnPoints.get((int)(Math.random()*fireSpawnPoints.size()));
            //see what part of town the fire is in
            String direction = "center";
            double[]directionDist = new double[4];
            String[]directions = {"North", "East", "South", "West"};
            directionDist[LocationBuilder.NORTH] = (spawn.getX() - currMap.length/2);
            if(directionDist[LocationBuilder.NORTH] < 0)
               directionDist[LocationBuilder.NORTH] = Math.abs(directionDist[LocationBuilder.NORTH]);
            else
               directionDist[LocationBuilder.NORTH] = 0;
            directionDist[LocationBuilder.SOUTH] = (spawn.getX() - currMap.length/2);
            if(directionDist[LocationBuilder.SOUTH] > 0)
               directionDist[LocationBuilder.SOUTH] = Math.abs(directionDist[LocationBuilder.SOUTH]);
            else
               directionDist[LocationBuilder.SOUTH] = 0;
            directionDist[LocationBuilder.WEST] = (spawn.getY() - currMap[0].length/2);
            if(directionDist[LocationBuilder.WEST] < 0)
               directionDist[LocationBuilder.WEST] = Math.abs(directionDist[LocationBuilder.WEST]);
            else
               directionDist[LocationBuilder.WEST] = 0;
            directionDist[LocationBuilder.EAST] = (spawn.getY() - currMap[0].length/2);
            if(directionDist[LocationBuilder.EAST] > 0)
               directionDist[LocationBuilder.EAST] = Math.abs(directionDist[LocationBuilder.EAST]);
            else
               directionDist[LocationBuilder.EAST] = 0;
            int maxDir = 0;
            for(int i=0; i<4; i++)
               if(directionDist[i] > directionDist[maxDir])
                  maxDir = i;      
            direction = directions[maxDir];
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(50, 100);
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               story[i]=story[i].replace("DIR", ""+direction);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = CultimaPanel.days;
            int mCol = (int)(CultimaPanel.time) + 4;
            //***add fire to the location
            locType = CultimaPanel.player.getLocationType();
            byte indexType = NPC.FIRE;
            int r = (int)(spawn.getX());
            int c = (int)(spawn.getY());
            NPCPlayer fire = new NPCPlayer(indexType, r, c, mi, r, c, locType);
            CultimaPanel.worldMonsters.add(fire);
            //***                   //Mission(String t, String[]story, int wRow, int wCol, int gold,   Item iw,  String loc,        Item targ, String targHolder, int targR, int targC)
            Mission saveMission = new Mission(type,     story,         startRow, startCol, goldReward, null,     thisLoc.getName(), null,      "none",            mRow,      mCol);
            selected.setMissionInfo(saveMission.getInfo());
            CultimaPanel.missionStack.add(saveMission);
            if(CultimaPanel.missionsGiven[FIRE_FIGHTER]==0)
               CultimaPanel.missionsGiven[FIRE_FIGHTER]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = saveMission.getStartStory();
         }
         else if(randMission == Mission.FIND_GEM)
         {  
            type = "Find";
            String targName = Utilities.shortName(selected.getName());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = Player.rollDie(25,75);
            Item randGem = Item.getRandomStone();
            String gemType = randGem.getName();
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("ITEM_NAME", gemType);
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
            }             
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", randGem, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[FIND_GEM]==0)
               CultimaPanel.missionsGiven[FIND_GEM]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = getMission.getStartStory();
         } 
         else if(randMission == Mission.FIND_ELK_PELT)
         {  
            type = "Find";
            String targName = Utilities.shortName(selected.getName());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            Armor randPelt = Armor.getElkPelt();
            String peltType = randPelt.getName();
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("ITEM_NAME", peltType);
            }             
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", new Item(peltType, 30), targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[FIND_ELK_PELT]==0)
               CultimaPanel.missionsGiven[FIND_ELK_PELT]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = getMission.getStartStory();
         } 
         else if(randMission == BURN_COFFINS)
         {
            type = "Burn";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;         
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission burnMission = new Mission(type, story, startRow, startCol, goldReward, null, thisLoc.getName(), null, "none", mRow, mCol);
            selected.setMissionInfo(burnMission.getInfo());
            CultimaPanel.missionStack.add(burnMission);
            if(CultimaPanel.missionsGiven[BURN_COFFINS]==0)
               CultimaPanel.missionsGiven[BURN_COFFINS]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = burnMission.getStartStory();
         }
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(Player.rollDie(3));
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String childMission(NPCPlayer selected)
   {
      String response = "";
      Location loc =  TerrainBuilder.closeLocation(CultimaPanel.allAdventure);
      boolean hasSigns = TerrainBuilder.hasSigns();
      NPCPlayer targNPC = null;
      String locType = "";
      if(loc == null)
      {
         String [] locTypes = {"cave"};
         loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
         locType = "cave";
      }
      if(loc == null)
      {
         String [] locTypes = {"mine"};
         loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
         locType = "mine";
      }
      if(loc == null)
      {
         String [] locTypes = {"lair"};
         loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes);
         locType = "lair";
      }
      if(loc != null)
      {
         String terrType = loc.getTerrain().getName().toLowerCase();
         if(TerrainBuilder.habitablePlace(terrType))
            locType =  "town";
         else if(terrType.contains("cave"))
            locType = "cave";
         else if(terrType.contains("mine"))
            locType = "mine";
         else if(terrType.contains("lair"))
            locType = "lair";
         else if(terrType.contains("dungeon"))
            locType = "dungeon";
         else if(terrType.contains("temple"))
            locType = "temple";   
      }
      else
      {
         selected.setNumInfo(0);
         return NPC.childResponse(selected, "");
      }  
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Rescue") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               if(LocationBuilder.countPrisoners(missLoc)==0)      //we freed all the prisoners
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(10);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Train"))
         {
            if(!CultimaPanel.player.getDogName().equals("none"))
            {
               NPCPlayer dog = Utilities.getDog();
               dog.setMoveTypeTemp(NPC.ROAM);
               dog.setHasMet(false);
               CultimaPanel.player.setDogBasicInfo("none");
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Break") && currMission.getClearLocation().length()>0)
         {   //check to see if break shoppe sign mission is finished   
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if (!hasSigns)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Destroy"))
         {   //check to see if Destroy mission is finished   
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(-5);
               CultimaPanel.player.removeItem(target.getName());
               FileManager.saveProgress();
            }
         }
      }  //end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(0);    
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {  
        //add a BEGGER into loc
         boolean success = TerrainBuilder.addPrisoner(loc, locType);
         //see if there is a statue in this location.  If not, pick a steal mission type
         NPCPlayer statue = null;
         Location locTry = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
         ArrayList<NPCPlayer> inLocNPCs = CultimaPanel.civilians.get(selected.getMapIndex());
         for(NPCPlayer p: inLocNPCs)
         {
            if(p.isStatue())
            {
               statue = p;
               targNPC = statue;
               break;
            }
         }
      //FIND_PARENT, FIND_PET                         
         byte randMission = FIND_PARENT;
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         //fill list of missions to pick from with the ones we have not seen yet
         if(success && CultimaPanel.missionsGiven[FIND_PARENT]==0)
            missionTypes.add(FIND_PARENT);
         if(CultimaPanel.missionsGiven[FIND_PET]==0)
            missionTypes.add(FIND_PET);
         if(hasSigns && CultimaPanel.missionsGiven[DESTROY_SIGNS]==0)
            missionTypes.add(DESTROY_SIGNS);
         if(statue!=null && CultimaPanel.missionsGiven[DESTROY_STATUE2]==0)
            missionTypes.add(DESTROY_STATUE2);   
         if(missionTypes.size()==0)    //we have seen all the mission types before, so pick a random one
         {
            if(success)
               missionTypes.add(FIND_PARENT);
            missionTypes.add(FIND_PET);
            if(hasSigns)
               missionTypes.add(DESTROY_SIGNS);
            if(statue!=null)
               missionTypes.add(DESTROY_STATUE2);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
         
         if(randMission == FIND_PET)
         {
            Item dog = new Item("dog", 25);
            String targetName = Utilities.shortName(selected.getName());
            type = "Train";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 1;         
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission trainMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", dog, targetName, mRow, mCol);
            selected.setMissionInfo(trainMission.getInfo());
            CultimaPanel.missionStack.add(trainMission);
            if(CultimaPanel.missionsGiven[FIND_PET]==0)
               CultimaPanel.missionsGiven[FIND_PET]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = trainMission.getStartStory();
         }
         else if(randMission == FIND_PARENT)
         {//rescue mission
            int numPrisoners = LocationBuilder.countPrisoners(loc);
            if(numPrisoners > 0)
            {
               type = "Rescue";
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 1;
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", loc.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
               TerrainBuilder.markMapPath(loc.getName());      
               Mission rescueMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(rescueMission.getInfo());
               CultimaPanel.missionStack.add(rescueMission);
               if(CultimaPanel.missionsGiven[FIND_PARENT]==0)
                  CultimaPanel.missionsGiven[FIND_PARENT]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = rescueMission.getStartStory();
            }
         }
         else if(randMission == DESTROY_SIGNS)
         {
            type = "Break";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;         
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Location thisLoc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            Mission breakMission = new Mission(type, story, startRow, startCol, goldReward, null, thisLoc.getName(), null, "none", mRow, mCol);
            selected.setMissionInfo(breakMission.getInfo());
            CultimaPanel.missionStack.add(breakMission);
            if(CultimaPanel.missionsGiven[DESTROY_SIGNS]==0)
               CultimaPanel.missionsGiven[DESTROY_SIGNS]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = breakMission.getStartStory();
         }
         else if(randMission == DESTROY_STATUE2 && statue!=null)
         {
            targNPC = statue;
            type = "Destroy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            if(targNPC!=null && loc!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 2000;
               String targName = "statue-head";     
               targNPC.addItem(new Item(targName, goldReward));
                
               String locName = Display.provinceName(loc.getName());
            
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               mRow = loc.getRow();
               mCol = loc.getCol();
             
               TerrainBuilder.markMapPath(loc.getName());
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(targName, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[DESTROY_STATUE2]==0)
                  CultimaPanel.missionsGiven[DESTROY_STATUE2]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }
         }
         else
         {
            selected.setNumInfo(0);
            return NPC.childResponse(selected, "");
         }
      }
      if(response.length() == 0)
      {
         response = NPC.childResponse(selected,"");
         selected.setNumInfo(0);
      }
      return response;
   }

   public static String beggerMission(NPCPlayer selected)
   {
      String response = "";
      Location loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
      int numBeggers = 0;
   
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Remove"))
         {
            boolean removed = true;
            for(NPCPlayer p:CultimaPanel.civilians.get(selected.getMapIndex()))
            {
               String pName = Utilities.shortName(p.getName());
               String targName = Utilities.shortName(currMission.getTargetHolder());
               if(pName.equalsIgnoreCase(targName) && p.getCharIndex()==NPC.BEGGER)
               {
                  removed = false;
                  break;
               }
            }
            if(removed)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Clear") && currMission.getClearLocation().length()>0)
         {                    //this is a mission to clear a location of monsters
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               ArrayList<Point> monsterFreq = missLoc.getMonsterFreq();
               if(monsterFreq==null || monsterFreq.size()==0)  //we cleared out the monsters here
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Rescue") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               if(LocationBuilder.countPrisoners(missLoc)==0)      //we freed all the prisoners
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addReputation(10);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Find") && currMission.getTarget()!=null)
         {
            Item target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               if(Armor.getArmorWithName(target.getName())!=null)
               {  //remove armor from player and add to selected
                  Armor dropped = CultimaPanel.player.discardArmor(targetName);
                  selected.setArmor(dropped);
               }
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().equals("Save") && currMission.getClearLocation().length()>0 && currMission.getTargetHolder().equals("none"))
         {  //KILL_WEREWOLF mission
            int worldRow = currMission.getWorldRow();
            int worldCol = currMission.getWorldCol();
            Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
            int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
            boolean werewolf = CultimaPanel.werewolfAbout;
            if(!werewolf)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.addReputation(10);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               FileManager.saveProgress();
            }
         }
      }  //end seeing if we finished a mission
      if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(Player.rollDie(1,3));    
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {                
         if(loc != null)
            numBeggers = LocationBuilder.countPrisoners(loc);
         boolean werewolf = CultimaPanel.werewolfAbout;    
         byte randMission = MOVE_BEGGER; 
         ArrayList<Byte> missionTypes = new ArrayList<Byte>();
         if(numBeggers >=2 && CultimaPanel.missionsGiven[MOVE_BEGGER]==0)
            missionTypes.add(MOVE_BEGGER);
         if(werewolf && CultimaPanel.missionsGiven[KILL_WEREWOLF]==0)
            missionTypes.add(KILL_WEREWOLF);
         if((Display.isFall() || Display.isWinter()) && CultimaPanel.missionsGiven[FIND_PELT]==0)
            missionTypes.add(FIND_PELT); 
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);     
         Location arena = TerrainBuilder.closeArena(CultimaPanel.allLocations);
         boolean arenaWithPrisoners = false;
         if(arena != null)
         { 
            LocationBuilder.constructAdventureAt(arena, "arena");
            if(LocationBuilder.countPrisoners(loc)>0)
               arenaWithPrisoners = true;
            if(arenaWithPrisoners  && CultimaPanel.missionsGiven[ARENA_RESCUE]==0)
            {
               missionTypes.add(ARENA_RESCUE);
            }
         }            
         if(CultimaPanel.missionsGiven[CLEAR_CAVE]==0)
            missionTypes.add(CLEAR_CAVE);
         if(missionTypes.size()==0)
         {
            if(numBeggers >=2)
               missionTypes.add(MOVE_BEGGER);
            if(arenaWithPrisoners)
               missionTypes.add(ARENA_RESCUE);
            missionTypes.add(CLEAR_CAVE);
            if(Display.isFall() || Display.isWinter())
               missionTypes.add(FIND_PELT);
            if(werewolf)
               missionTypes.add(KILL_WEREWOLF);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);  
         }              
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));               
         
         //create KILL_WEREWOLF mission                
         if(randMission == KILL_WEREWOLF)
         { 
            type = "Save";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NAME", CultimaPanel.player.getName());
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            Mission werewolfMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
            selected.setMissionInfo(werewolfMission.getInfo());
            CultimaPanel.missionStack.add(werewolfMission);
            if(CultimaPanel.missionsGiven[KILL_WEREWOLF]==0)
               CultimaPanel.missionsGiven[KILL_WEREWOLF]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = werewolfMission.getStartStory();
         }
         else if(randMission == MOVE_BEGGER)
         { 
            NPCPlayer targetBegger = null;
            for(NPCPlayer p:Utilities.getNPCsInLoc(selected.getMapIndex()))
            {
               if(!p.getName().toLowerCase().contains(selected.getName().toLowerCase()) && p.getCharIndex()==NPC.BEGGER)
               {
                  targetBegger = p;
                  break;
               }
            }
            String targName = Utilities.shortName(targetBegger.getName());
            type = "Remove";
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NPC_NAME", Utilities.shortName(targetBegger.getName()));
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1;
            int mCol = -1;
            TerrainBuilder.markMapPath(loc.getName());      
            Mission removeMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, targName, mRow, mCol);
            selected.setMissionInfo(removeMission.getInfo());
            CultimaPanel.missionStack.add(removeMission);
            if(CultimaPanel.missionsGiven[MOVE_BEGGER]==0)
               CultimaPanel.missionsGiven[MOVE_BEGGER]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = removeMission.getStartStory();
         }
         else if(randMission == CLEAR_CAVE)
         {
            String [] locTypes = {"cave"};
            loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
            if(loc==null || loc.getMonsterFreq().size() == 0)
            {
               selected.setNumInfo(Player.rollDie(1,3));
               return  NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
            }         
            TerrainBuilder.markMapPath(loc.getName());
            type = "Clear";
            ArrayList<Point> monsterFreq = loc.getMonsterFreq();
            byte monsterIndex = (byte)((monsterFreq.get(0)).getX());
            String monsterType = NPC.characterDescription(monsterIndex);
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("MONSTER_TYPE", monsterType);
               story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               story[i]=story[i].replace("LOCATION_NAME", loc.getName());
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            mRow = loc.getRow();
            mCol = loc.getCol();
            Mission clearMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
            selected.setMissionInfo(clearMission.getInfo());
            CultimaPanel.missionStack.add(clearMission);
            if(CultimaPanel.missionsGiven[CLEAR_CAVE]==0)
               CultimaPanel.missionsGiven[CLEAR_CAVE]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = clearMission.getStartStory();
         }
         else if(randMission == Mission.ARENA_RESCUE && arena!=null)
         {        //rescue mission
            loc = arena;
            int numPrisoners = LocationBuilder.countPrisoners(loc);
            if(numPrisoners > 0)
            {
               type = "Rescue";
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 0;
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
               TerrainBuilder.markMapPath(loc.getName());      
               Mission rescueMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(rescueMission.getInfo());
               CultimaPanel.missionStack.add(rescueMission);
               if(CultimaPanel.missionsGiven[ARENA_RESCUE]==0)
                  CultimaPanel.missionsGiven[ARENA_RESCUE]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = rescueMission.getStartStory();
            }
         }
         else if(randMission == Mission.FIND_PELT)
         {  
            type = "Find";
            String targName = Utilities.shortName(selected.getName());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            Armor randPelt = Armor.getRandomPelt();
            String peltType = randPelt.getName();
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("ITEM_NAME", peltType);
            }             
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", new Item(peltType, 30), targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[FIND_PELT]==0)
               CultimaPanel.missionsGiven[FIND_PELT]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         } 
         else if(randMission==MERCY_KILL)
         {
            type = "Mercy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
      
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(Player.rollDie(1,3));
      }
      return response;
   }

   public static String luteMission(NPCPlayer selected)
   {
      String response = "";
      boolean civiliansToWrite = true;    //if we build a location for the mission target, write out civilians file as well
      Location loc = null;
      Item target = null;
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;
   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Find") && currMission.getTarget()!=null)
         {
            target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               if(targetName.toLowerCase().contains("lute"))
               {
                  if(Weapon.getWeaponWithName(target.getName())!=null)
                  {   //remove weapon from player and add to selected
                     Weapon dropped = CultimaPanel.player.discardWeapon(targetName);
                     selected.setWeapon(dropped);
                  }
               }
               else
               {
                  CultimaPanel.player.removeItem(target.getName());
                  selected.addItem(target);
               }
               
               FileManager.saveProgress();
            }
         }
      }
      if(selected.getNumInfo() != 10)
         response = NPC.noMission[(int)(Math.random()*NPC.noMission.length)]+".";
      else if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(Player.rollDie(1,3));
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {  //FIND_SONG, FIND_LUTE
         byte randMission = FIND_LUTE;
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         //fill list of missions to pick from with the ones we have not seen yet
         if(CultimaPanel.missionsGiven[FIND_SONG]==0)
            missionTypes.add(FIND_SONG);
         if(CultimaPanel.missionsGiven[FIND_LUTE]==0)
            missionTypes.add(FIND_LUTE);
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);
         if(missionTypes.size()==0)    //we have seen all the mission types before, so pick a random one
         {
            missionTypes.add(FIND_SONG);
            missionTypes.add(FIND_LUTE);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
         
         if(randMission==MERCY_KILL)
         {
            type = "Mercy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         } 
         else
         {
            type = "Find";
            String locType = "place";
         
            int randType = Player.rollDie(10);
            ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(selected.getMapIndex());
            ArrayList<NPCPlayer> lutes = new ArrayList<NPCPlayer>();
            for(NPCPlayer p: inLocNPCs)
            {
               if(p.getCharIndex()==NPC.LUTE && !p.getName().equals(selected.getName()))
                  lutes.add(p);
               if(randMission == FIND_SONG && (p.getCharIndex()==NPC.WISE || p.getCharIndex()==NPC.MAN || p.getCharIndex()==NPC.WOMAN || p.getCharIndex()==NPC.KING))
                  lutes.add(p);  
            }
            NPCPlayer targNPC = null;
            if(lutes.size() <= 1)
               randType = Player.rollDie(2,10);
            if(randType < 2)           //lute goes to someone at the same location
            {
               loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
               locType = CultimaPanel.player.getLocationType();
               if(lutes.size() == 0)
                  targNPC = null;
               else
                  targNPC = lutes.get((int)(Math.random()*lutes.size()));
            }
            if((randType >=2 && randType <= 5) || targNPC==null)      //lute goes to someone at the next closest castle
            {
               String [] locTypes = {"castle","tower"};
               Location closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCastle.getMapIndex());
               lutes = new ArrayList<NPCPlayer>();
               for(NPCPlayer p: inLocNPCs)
               {
                  if(p.getCharIndex()==NPC.LUTE)
                     lutes.add(p);
                  if(randMission == FIND_SONG && (p.getCharIndex()==NPC.WISE || p.getCharIndex()==NPC.MAN || p.getCharIndex()==NPC.WOMAN || p.getCharIndex()==NPC.KING))
                     lutes.add(p); 
               }
               if(lutes.size() > 0)
               {
                  targNPC = lutes.get((int)(Math.random()*lutes.size()));
                  loc = closeCastle;
               }
            }
            if(targNPC==null)                       //lute goes to  someone at the next closest town
            {
               String []locTypes2 = {"city","fortress","port","village"};
               Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
               civiliansToWrite = true;
               inLocNPCs = Utilities.getNPCsInLoc(closeCity.getMapIndex());
               lutes = new ArrayList<NPCPlayer>();
               for(NPCPlayer p: inLocNPCs)
               {
                  if(p.getCharIndex()==NPC.LUTE)
                     lutes.add(p);
                  if(randMission == FIND_SONG && (p.getCharIndex()==NPC.WISE || p.getCharIndex()==NPC.MAN || p.getCharIndex()==NPC.WOMAN || p.getCharIndex()==NPC.KING))
                     lutes.add(p);
               }
               if(lutes.size() > 0)
               {
                  targNPC = lutes.get((int)(Math.random()*lutes.size()));
                  loc = closeCity;
               }
            }
            if(randMission == FIND_SONG)
               target = new Item("Songpage",500);
            else
               target =  Weapon.getLuteOfDestiny(); 
          
            if(targNPC!=null && loc!=null)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = target.getValue()/10;
               double priceShift = ((Math.random()*51) - 25)/100.0;  //-.25 to +.25
               goldReward = (int)(goldReward + (goldReward * priceShift));
            
               if(randMission == FIND_SONG)
                  targNPC.addItem(target);
               else
                  targNPC.setWeapon((Weapon)(target));
            
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               String targName = Utilities.shortName(targNPC.getName());         
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
                  story[i]=story[i].replace("ITEM_NAME", target.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target.getName(),target.getValue()), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[randMission]==0)
                  CultimaPanel.missionsGiven[randMission]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            } 
         }   
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(0);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String wiseMission(NPCPlayer selected)
   {
      boolean civiliansToWrite = false;
      String response = "";
      Location loc = null;
      Item target = null;
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         if(currMission.getType().contains("Teach") && currMission.getTarget() != null)
         {
            Item thisTarget = currMission.getTarget();
            String targetName = thisTarget.getName(); 
            if(CultimaPanel.player.hasSpell(targetName) || CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(5);
               FileManager.saveProgress();
            } 
         }
         else if((currMission.getType().contains("Marry")) && currMission.getTargetHolder()!=null && currMission.getTargetHolder().length()>0)        //remove beggers from a town, or help them to be upright citizens
         {
            int worldRow = currMission.getWorldRow();
            int worldCol = currMission.getWorldCol();
            Location thisLoc = CultimaPanel.getLocation(CultimaPanel.allLocations, worldRow, worldCol, 0);
            int mi = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, worldRow, worldCol, 0);
            boolean married = false;
            NPCPlayer ourSpouse = null;
            for(NPCPlayer p:CultimaPanel.civilians.get(mi))
            {
               String pName = Utilities.shortName(p.getName());   //starting the name with + means we are married to them
               String targName = Utilities.shortName(currMission.getTargetHolder());
               if(pName.equalsIgnoreCase("+"+targName))
               {
                  married = true;
                  ourSpouse = p;
                  break;
               }
            }
            if(married && ourSpouse!=null)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               //give us a house
               ArrayList<Point> houseLocs = NPC.buyHouse();
               NPCPlayer currOwner = null;
               for(NPCPlayer p:CultimaPanel.civilians.get(mi))
               {
                  Point currOwnerHome = new Point(p.getHomeRow(),p.getHomeCol());
                  if(houseLocs.contains(currOwnerHome))
                  {
                     currOwner = p;
                     break;
                  }
               }
               if(currOwner != null)
               {
                  ArrayList<Point> newLocs = new ArrayList<Point>();
                  byte[][]currMap = (CultimaPanel.map.get(selected.getMapIndex()));   
                  for(int r=0; r<currMap.length; r++)
                     for(int c=0; c<currMap[0].length; c++)
                     {
                        String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
                        if(!current.contains("purple") && TerrainBuilder.isInsideFloor(current))
                           newLocs.add(new Point(r, c));   
                     }
                  if(newLocs.size() > 0)              //move old resident to a new place
                  {
                     Point newHomeLoc = newLocs.get((int)(Math.random()*newLocs.size()));
                     currOwner.setHomeRow((int)(newHomeLoc.getX()));
                     currOwner.setHomeCol((int)(newHomeLoc.getY()));
                  }
               }
              //give us a horse
               int horseRow=CultimaPanel.player.getWorldRow(); 
               int horseCol=CultimaPanel.player.getWorldCol();
               java.util.LinkedList<Teleporter> telepMemory = CultimaPanel.player.getMemory();
               if(telepMemory.size() == 1)
               {  //our getWorldRow and Col might not be updated if we resumed while already in the location
                  //so retrieve it from teleporter memory
                  Teleporter telep = telepMemory.get(0);
                  if(telep.toIndex()==0)
                  {
                     horseRow = telep.toRow();
                     horseCol = telep.toCol();
                  }
               }
               ArrayList<Point>horseLocs = new ArrayList();
               for(int r=horseRow-3; r<=horseRow+3; r++)
                  for(int c=horseCol-3; c<=horseCol+3; c++)
                  {
                     String terrain = CultimaPanel.allTerrain.get(Math.abs((CultimaPanel.map.get(0))[r][c])).getName();
                     if((r==horseRow && c==horseCol) || Utilities.NPCAt(r, c, 0) || terrain.startsWith("LOC_") || terrain.contains("_I_") || terrain.toLowerCase().contains("water"))
                        continue;
                     horseLocs.add(new Point(CultimaPanel.equalizeWorldRow(r),CultimaPanel.equalizeWorldRow(c)));
                  }
               if(horseLocs.size() > 0)
               {
                  Point horseSpawn = horseLocs.get((int)(Math.random()*horseLocs.size()));
                  horseRow=(int)(horseSpawn.getX()); 
                  horseCol=(int)(horseSpawn.getY());
                  NPCPlayer ourHorse = new NPCPlayer(NPC.HORSE, horseRow, horseCol, 0, "world");
                  ourHorse.setHasMet(true);
                  CultimaPanel.worldMonsters.add(ourHorse);
               }   
            }
            FileManager.saveProgress();
         }
         else if(currMission.getType().contains("Clear") && currMission.getClearLocation().length()>0)
         {                    //this is a mission to clear a location of monsters
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               ArrayList<Point> monsterFreq = missLoc.getMonsterFreq();
               if(monsterFreq==null || monsterFreq.size()==0)  //we cleared out the monsters here
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.addReputation(5);
                  TerrainBuilder.addWorshipersToTemple(missLoc);
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Find"))
         {
            target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               CultimaPanel.player.addReputation(5);
               if(Weapon.getWeaponWithName(target.getName())!=null)
               {   //remove weapon from player and add to selected
                  Weapon dropped = CultimaPanel.player.discardWeapon(targetName);
                  selected.setWeapon(dropped);
               }
               else if(Armor.getArmorWithName(target.getName())!=null)
               {  //remove armor from player and add to selected
                  Armor dropped = CultimaPanel.player.discardArmor(targetName);
                  selected.setArmor(dropped);
               }
               else
               {
                  CultimaPanel.player.removeItem(target.getName());
                  selected.addItem(target);
               }
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Assassinate") && currMission.getTarget()!=null)
         {
            target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               if(currMission.getType().contains("Assassinate"))
                  CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.removeItem(target.getName());
               if(!target.getName().contains("bounty"))
                  selected.addItem(target);
               Item reward = currMission.getItemReward();
               if(reward!=null)
               {
                  selected.removeItem(reward.getName());
                  CultimaPanel.player.addItem(reward.getName());
                  CultimaPanel.sendMessage("<Recieved "+reward.getName()+">");
               }   
               FileManager.saveProgress();
            }
            else if(CultimaPanel.player.hasItem("bounty"))
            {  //if we just have an item called bounty and there is only one bounty mission, resolve it.
               int count = 0;
               for(Mission m:CultimaPanel.missionStack)
               {
                  if(m.getType().contains("Assassinate"))
                     count++;
               }
               if (count==1)
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  if(currMission.getType().contains("Assassinate"))
                     CultimaPanel.player.stats[Player.BOUNTIES_COLLECTED]++;
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  CultimaPanel.player.removeItem("bounty");
                  Item reward = currMission.getItemReward();
                  if(reward!=null)
                  {
                     selected.removeItem(reward.getName());
                     CultimaPanel.player.addItem(reward.getName());
                     CultimaPanel.sendMessage("<Recieved "+reward.getName()+">");
                  }
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Purge") && currMission.getClearLocation().length()>0)
         {
            Location missLoc = CultimaPanel.getLocation(currMission.getClearLocation());
            if(missLoc!=null)
            {
               int numCivs = LocationBuilder.countCivilians(missLoc);
               if(numCivs==0)      //we killed or scared out all the inhabitants
               {
                  currMission.setState(Mission.FINISHED);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addReputation(-10);
                  selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
                  FileManager.saveProgress();
               }
            }
         }
         else if(currMission.getType().contains("Harvest") && currMission.getTarget()!=null)
         {   //check to see if harvest egg mission is finished   
            Item target2 = currMission.getTarget();
            String targetName = target2.getName().toLowerCase();
            if(targetName.contains("3-serpent-eggs") && CultimaPanel.player.numSerpentEggs()>=3)
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(10);
               for(int i=0; i<3; i++)
               {
                  CultimaPanel.player.removeItem("serpent-egg");
                  selected.addItem(Item.getSerpentEgg());
               }
               FileManager.saveProgress();
            }            
         }
      }     //end see if we completed a mission
      if(selected.getNumInfo() != 10)
         response = NPC.noMission[(int)(Math.random()*NPC.noMission.length)]+".";
      else if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
         Item itemReward = currMission.getItemReward();
         CultimaPanel.player.addGold(goldReward);
         if(itemReward != null)
            CultimaPanel.player.addItem(itemReward.getName());
         selected.setNumInfo(3);    //setting a man, woman or wise value to 3 will make it so they will map for you for free, or teach you a spell as thanks
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else     //create the mission
      {  
         String [] locTypes = {"castle","tower"};
         Location closeCastle = null;
         String thisLoc = selected.getLocationType().toLowerCase();
         if(thisLoc.contains("castle") || thisLoc.contains("tower"))
            closeCastle =  CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
         else
            closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
         civiliansToWrite = true;
         ArrayList<NPCPlayer> inLocNPCs = Utilities.getNPCsInLoc(closeCastle.getMapIndex());
         NPCPlayer king = null;
         if(inLocNPCs.size() >= 1)
         {
            for(NPCPlayer p:inLocNPCs)
               if(p.getCharIndex()==NPC.KING)
               {
                  if(!p.hasMet())
                     p.setReputation(-1000);
                  if(p.getReputation() < 0)
                  {
                     king = p;
                     break;
                  }
               } 
         }
         inLocNPCs = Utilities.getNPCsInLoc(selected.getMapIndex());
         NPCPlayer single = null;
         if(inLocNPCs.size() >= 2)
         {
            for(NPCPlayer p:inLocNPCs)
            {
               int diff = NPC.allignmentDifference(p);
               if(p.getCharIndex()==NPC.MAN || p.getCharIndex()==NPC.WOMAN)
               {
                  if((CultimaPanel.player.getReputation() < 0 && diff > 100 && p.getReputation() > CultimaPanel.player.getReputation()))
                     continue;
                  if(p.isShopkeep())
                     continue;   
                  if(p.getMaritalStatus() == 'S')
                  {
                     single = p;
                     break;
                  }
               } 
            }
         }
         //of the spells we may be asked to discover and teach, find out which ones we don't already know
         String [] spellNames = {"Curse","Spidersweb","Tempest","Firestorm","Floretlade","Timestop"};
         ArrayList<String> unknownSpells = new ArrayList<String>();
         for(String spell:spellNames)
            if(!CultimaPanel.player.hasItem(spell) && !CultimaPanel.player.hasSpell(spell))
               unknownSpells.add(spell);
         
         ArrayList<Point>waterLocs =  Utilities.waterInView();
         byte randMission = CLEAR_TEMPLE;      
      
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         //fill list of missions to pick from with the ones we have not seen yet
         if(CultimaPanel.missionsGiven[TEACH_SPELL]==0 && unknownSpells.size() > 0)
            missionTypes.add(TEACH_SPELL);
         if(CultimaPanel.missionsGiven[MARRIAGE]==0 && single!=null)
            missionTypes.add(MARRIAGE);
         if(CultimaPanel.missionsGiven[CLEAR_TEMPLE]==0)
            missionTypes.add(CLEAR_TEMPLE);
         if(CultimaPanel.missionsGiven[FIND_PEARL]==0 && !Display.frozenWater() && waterLocs.size() >= 5)
            missionTypes.add(FIND_PEARL);
         if(CultimaPanel.missionsGiven[ASSASSINATE_KING]==0 && closeCastle!=null && king !=null)
            missionTypes.add(ASSASSINATE_KING);  
         if(CultimaPanel.missionsGiven[PURGE_HOLY]==0 && selected.getReputation() <= 0)
            missionTypes.add(PURGE_HOLY);   
         if(CultimaPanel.missionsGiven[FIND_MAGIC_ITEM]==0)
            missionTypes.add(Mission.FIND_MAGIC_ITEM);   
         if(CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]==0 && selected.isShopkeep() && selected.getName().contains("mage"))
            missionTypes.add(FIND_SERPENT_EGGS);  
         if(missionTypes.size()==0)    //we have seen all the mission types before, so pick a random one
         {
            if(unknownSpells.size() > 0)
               missionTypes.add(TEACH_SPELL);
            if(single!=null)
               missionTypes.add(MARRIAGE);
            missionTypes.add(CLEAR_TEMPLE);
            missionTypes.add(Mission.FIND_MAGIC_ITEM);
            if(!Display.frozenWater() && waterLocs.size() >= 5 && Math.random() < 0.25)
               missionTypes.add(FIND_PEARL);
            if(closeCastle!=null && king !=null && Math.random() < 0.5)
               missionTypes.add(ASSASSINATE_KING);   
            if(selected.getReputation() <= 0)
               missionTypes.add(PURGE_HOLY);  
            if(selected.isShopkeep() && selected.getName().contains("mage"))
               missionTypes.add(FIND_SERPENT_EGGS);   
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
      
         if(randMission == CLEAR_TEMPLE)
         {
            locTypes = new String[1];
            locTypes[0] = "temple";
            loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
         
            if(loc!=null)
            {          
               TerrainBuilder.markMapPath(loc.getName());
               type = "Clear";
               ArrayList<Point> monsterFreq = loc.getMonsterFreq();
               if(monsterFreq.size() == 0)
               {
                  ArrayList<Point> mf = new ArrayList<Point>();   //x is the monster index, y is the number of monsters of that type
                  byte monsterType = NPC.randomTempleMonster();
                  int numMonsters = Player.rollDie(3,10);
                  mf.add(new Point(monsterType, numMonsters));
                  loc.setMonsterFreq(mf);
                  monsterFreq = loc.getMonsterFreq();
               }
               byte monsterIndex = (byte)((monsterFreq.get(0)).getX());
               String monsterType = NPC.characterDescription(monsterIndex);
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Math.max(50, (int)((monsterFreq.get(0)).getY()));
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("MONSTER_TYPE", monsterType);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", loc.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               mRow = loc.getRow();
               mCol = loc.getCol();
            
               Mission clearMission = new Mission(type, story, startRow, startCol, goldReward, null, loc.getName(), null, "none", mRow, mCol);
               selected.setMissionInfo(clearMission.getInfo());
               CultimaPanel.missionStack.add(clearMission);
               if(CultimaPanel.missionsGiven[CLEAR_TEMPLE]==0)
                  CultimaPanel.missionsGiven[CLEAR_TEMPLE]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, "maps/worldLocs.txt");
               response = clearMission.getStartStory();
            } 
         }
         else if(randMission == FIND_PEARL)
         {
            String [] temp = missions[FIND_PEARL];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            //higher reward for targets further away
            int goldReward = Math.min(250, Player.rollDie(selected.getLevel()*100));
            String targetName = "life-pearl";
            int targItemPrice = (int)(goldReward);
            Point monsterLoc = waterLocs.get((int)(Math.random()*waterLocs.size()));
            if(monsterLoc != null)
            {
               int monsterRow = (int)(monsterLoc.getX());
               int monsterCol = (int)(monsterLoc.getY());
               if(Utilities.NPCAt(monsterRow, monsterCol, 0))
                  Utilities.removeNPCat(monsterRow, monsterCol, 0);
               NPCPlayer targNPC = null;
               String monsterType = "";
               if(Math.random() < 0.5)
               {
                  targNPC = new NPCPlayer(NPC.SQUIDKING, monsterRow, monsterCol, 0, "world");
                  monsterType = "Gargantuan Squid";
               }
               else
               {
                  targNPC = new NPCPlayer(NPC.HYDRACLOPS, monsterRow, monsterCol, 0, "world");
                  monsterType = "Hydraclops";
               }
               if(!targNPC.hasItem("life-pearl"))
                  targNPC.addItem(new Item(targetName, targItemPrice));
               CultimaPanel.worldMonsters.add(targNPC);
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("MONSTER_TYPE", ""+monsterType);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission("Find", story, startRow, startCol, goldReward, null, "none", new Item(targetName, goldReward), "none", mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_PEARL]==0)
                  CultimaPanel.missionsGiven[FIND_PEARL]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               response = getMission.getStartStory();
            
            }
            else
               response = ""; //no monster spawned, so clear out mission 
         }
         else if(randMission == Mission.ASSASSINATE_KING)
         {  //Assassinate mission
            type = "Assassinate";
                       
            if(king!=null && closeCastle!=null)
            {
               TerrainBuilder.markMapPath(closeCastle.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 0;
               Item [] items = {Item.getItemWithName("mannastone"), Item.getItemWithName("mindtome")};
               Item reward = items[(int)(Math.random()*items.length)];
               String targName = Utilities.shortNameWithTitle(king.getName());
               CultimaPanel.missionTargetNames.add(targName);
               String locName = Display.provinceName(closeCastle.getName());
               
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               targName = targName + "-bounty";
               king.addItem(new Item(targName, goldReward));
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(king.getMapIndex() != selected.getMapIndex())
               {
                  mRow = closeCastle.getRow();
                  mCol = closeCastle.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, reward, locName, new Item(targName, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[ASSASSINATE_KING]==0)
                  CultimaPanel.missionsGiven[ASSASSINATE_KING]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
         else if(randMission == Mission.PURGE_HOLY)
         {  //Purge a city or town of infidels
            type = "Purge";  
            inLocNPCs = null;
            NPCPlayer targNPC = null;
            
            String []locTypes2 = {"city","fortress","port","village"};
            Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
            civiliansToWrite = true;
            inLocNPCs = CultimaPanel.civilians.get(closeCity.getMapIndex());
            if(inLocNPCs.size() >= 1)
            {
               targNPC = inLocNPCs.get((int)(Math.random()*inLocNPCs.size()));
               loc = closeCity;
            }
            else
               loc = null;
            
            if(targNPC!=null && loc!=null)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               String locName = Display.provinceName(loc.getName());
               int numCivs = LocationBuilder.countCivilians(loc);
               int goldReward = 0;
                                  
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = loc.getRow();
               int mCol = loc.getCol();
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, "none", mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[PURGE_HOLY]==0)
                  CultimaPanel.missionsGiven[PURGE_HOLY]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
         else if(randMission == Mission.FIND_MAGIC_ITEM)
         {  
            type = "Find";
            String locType = "place";
            NPCPlayer targNPC = null;
         //pick a close adventure spot or battlefield
            loc =  TerrainBuilder.closeLocation(CultimaPanel.allAdventure);
            if(loc == null)
            {
               String [] locTypes3 = {"cave"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes3);
               locType = "cave";
            }
            if(loc == null)
            {
               String [] locTypes3 = {"mine"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes3);
               locType = "mine";
            }
            if(loc == null)
            {
               String [] locTypes3 = {"lair"};
               loc = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations,locTypes3);
               locType = "lair";
            }
            if(loc != null)
            {
               String terrType = loc.getTerrain().getName().toLowerCase();
               if(TerrainBuilder.habitablePlace(terrType))
                  locType =  "town";
               else if(terrType.contains("cave"))
                  locType = "cave";
               else if(terrType.contains("mine"))
                  locType = "mine";
               else if(terrType.contains("lair"))
                  locType = "lair";
               else if(terrType.contains("dungeon"))
                  locType = "dungeon";
               else if(terrType.contains("temple"))
                  locType = "temple";   
            }
            byte monsterType = NPC.SORCERER;
            target =  Weapon.getIceStaff();
            double targetType = Math.random();
            if(targetType >= 0.33 && targetType< 0.66)
               target = Armor.getRandomRobe();
            else if(targetType >= 0.66)
            {
               Item [] magicItems = {Item.getFloretBox(), Item.getMannastone(), Item.getMindTome()};
               target = magicItems[(int)(Math.random()*magicItems.length)];
            }   
            targNPC = new NPCPlayer(monsterType, 0, 0, 0, locType);  
            targNPC.modifyStats(1.2);
            if(target instanceof Weapon)
               targNPC.setWeapon((Weapon)(target));  
            else if(target instanceof Armor)
               targNPC.setArmor((Armor)(target));              
            else 
               targNPC.addItem(target);   
            boolean success = TerrainBuilder.addNPCtoLocation(loc, locType, targNPC);
         
            if(targNPC!=null && loc!=null && success)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 0;
               String targName = Utilities.shortName(selected.getName());         
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("LOCATION_NAME", locName);
                  story[i]=story[i].replace("ITEM_NAME", target.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target.getName(),target.getValue()), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_MAGIC_ITEM]==0)
                  CultimaPanel.missionsGiven[FIND_MAGIC_ITEM]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         } 
         else if(randMission == Mission.TEACH_SPELL)
         {  
            type = "Teach";
            NPCPlayer targNPC = selected;
            String spellName = unknownSpells.get((int)(Math.random()*unknownSpells.size()));
            target =  new Item(spellName, 0);     
            if(targNPC!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Player.rollDie(50,100);
               String targName = Utilities.shortName(selected.getName());                        
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("SPELL_NAME", spellName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", target, targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[TEACH_SPELL]==0)
                  CultimaPanel.missionsGiven[TEACH_SPELL]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         } 
         else if(randMission == Mission.MARRIAGE && single!=null)
         {  
            type = "Marry";
            NPCPlayer targNPC = single;      
            
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(targNPC.getName());   
            CultimaPanel.missionTargetNames.add(targName);                     
            for(int i=0; i<story.length; i++)
            {
               story[i]=story[i].replace("NPC_NAME", targName);
            }
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            civiliansToWrite = true;
            response = getMission.getStartStory();
         }
         else if(randMission == Mission.FIND_SERPENT_EGGS)
         {  
            type = "Harvest";
            NPCPlayer targNPC = selected;       
            if(targNPC!=null)
            {
               Item ourItem = new Item("3-serpent-eggs",90);
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = Player.rollDie(25, 100);
               if(CultimaPanel.shoppeDiscount)   //charmed
                  goldReward = (int)(goldReward * 1.20);
               String targName = Utilities.shortName(selected.getName());         
               for(int i=0; i<story.length; i++)
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, "none", ourItem, targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]==0)
                  CultimaPanel.missionsGiven[FIND_SERPENT_EGGS]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }   
         }
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(3);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public static String jesterMission(NPCPlayer selected)
   {
      String response = "";
      boolean civiliansToWrite = true;    //if we build a location for the mission target, write out civilians file as well
      Location loc = null;
      Item target = null;
      String type = "";
      String missionInfo = selected.getMissionInfo();
      Mission currMission = null;
      String [] magicDaggers = {"Poison-dagger","Souldagger","Magmadagger","Frostdagger","Banedagger"};
   
      int currMissionIndex = -1;
      for(int i=0; i<CultimaPanel.missionStack.size(); i++)
      {
         Mission m = CultimaPanel.missionStack.get(i);
         if(missionInfo.equals(m.getInfo()))
         {
            currMission = m;
            currMissionIndex = i;
            break;
         }
      }
      if(currMission!=null)   //see if we finished the mission
      {
         target = currMission.getTarget();
         if(currMission.getType().contains("Steal") && (target != null && (target.getName().contains("pick") || target.getName().contains("book") || target.getName().contains("cube"))))
         {
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               if(selected.getReputation() > 0)
                  selected.setReputation(-1*selected.getReputation());   //you completed a mission, but jester runs off - make him evil
               CultimaPanel.player.addReputation(5);
               CultimaPanel.player.removeItem(target.getName());
               selected.addItem(target);
               selected.setMoveTypeTemp(NPC.RUN);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Steal") && target!=null)
         {
            target = currMission.getTarget();
            String targetName = target.getName();
            boolean hasDaggers = true;
            for(String dag: magicDaggers)
               if(!CultimaPanel.player.hasItem(dag))
                  hasDaggers = false;
            if(targetName.toLowerCase().contains("dagger") && hasDaggers)   //steal the 5 magic daggers mission
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               if(selected.getReputation() > 0)
                  selected.setReputation(-1*selected.getReputation());   //you completed a mission, but jester runs off - make him evil
               CultimaPanel.player.addReputation(5);
               for(String dag: magicDaggers)
                  if(Weapon.getWeaponWithName(dag)!=null)
                  {   //remove weapon from player and add to selected
                     Weapon dropped = CultimaPanel.player.discardWeapon(dag);
                     selected.addItem(new Item(dropped.getName(), dropped.getValue()));
                  }                  
               selected.setMoveTypeTemp(NPC.RUN);
               FileManager.saveProgress();
            }
         }
         else if(currMission.getType().contains("Destroy"))
         {   //check to see if Destroy mission is finished   
            target = currMission.getTarget();
            String targetName = target.getName();
            if(CultimaPanel.player.hasItem(targetName))
            {
               currMission.setState(Mission.FINISHED);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               selected.setReputation(CultimaPanel.player.getReputationRaw());   //you completed a mission - make them allignment compatible with you
               CultimaPanel.player.addReputation(-5);
               CultimaPanel.player.removeItem(target.getName());
               FileManager.saveProgress();
            }
         }
      }
      if(selected.getNumInfo() != 10)
         response = NPC.noMission[(int)(Math.random()*NPC.noMission.length)]+".";
      else if(currMission!=null && currMission.getState() == Mission.IN_PROGRESS)
         response = currMission.getMiddleStory();
      else if(currMission!=null && currMission.getState() ==  Mission.FINISHED)
      {
         response = currMission.getEndStory();
         CultimaPanel.player.addExperience(100);
         int goldReward = currMission.getGoldReward();
            //jester stiffs us of the reward
         selected.setNumInfo(0);
         selected.setMissionInfo("none");
         if(currMissionIndex >=0 && currMissionIndex < CultimaPanel.missionStack.size())
            CultimaPanel.missionStack.remove(currMissionIndex);
         FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
      }   
      else
      {  //STEAL_PICK, STEAL_BOOK, STEAL_DICE, STEAL_DAGGERS or DESTROY_STATUE
         //see if there is a statue in this location.  If not, pick a steal mission type
         NPCPlayer statue = null;
         Location locTry = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
         ArrayList<NPCPlayer> inLocNPCs = CultimaPanel.civilians.get(selected.getMapIndex());
         for(NPCPlayer p: inLocNPCs)
         {
            if(p.isStatue())
            {
               statue = p;
               break;
            }
         }
         
         byte randMission = STEAL_BOOK;
         ArrayList<Byte>missionTypes = new ArrayList<Byte>();
         //fill list of missions to pick from with the ones we have not seen yet
         if(CultimaPanel.missionsGiven[STEAL_BOOK]==0)
            missionTypes.add(STEAL_BOOK);
         if(CultimaPanel.missionsGiven[STEAL_PICK]==0)
            missionTypes.add(STEAL_PICK);
         if(CultimaPanel.missionsGiven[STEAL_DICE]==0)
            missionTypes.add(STEAL_DICE);
         if(CultimaPanel.missionsGiven[STEAL_DAGGERS]==0)
            missionTypes.add(STEAL_DAGGERS);
         if(selected.isWerewolf() && CultimaPanel.missionsGiven[MERCY_KILL]==0)   //werewolf tag
            missionTypes.add(MERCY_KILL);
         if(CultimaPanel.missionsGiven[DESTROY_STATUE]==0 && statue != null)
            for(int i=0; i<6; i++)     //make it more likely we get this mission
               missionTypes.add(DESTROY_STATUE);
         if(missionTypes.size()==0)    //we have seen all the mission types before, so pick a random one
         {
            missionTypes.add(STEAL_BOOK);
            missionTypes.add(STEAL_PICK);
            missionTypes.add(STEAL_DICE);
            missionTypes.add(STEAL_DAGGERS);
            if(selected.isWerewolf())   //werewolf tag
               missionTypes.add(MERCY_KILL);
            if(statue != null)         //make it more likely we get this mission
               for(int i=0; i<6; i++)
                  missionTypes.add(DESTROY_STATUE);
         }
         randMission = missionTypes.get((int)(Math.random()*missionTypes.size()));
         
         NPCPlayer targNPC = null;
         if(randMission == DESTROY_STATUE)
         {
            targNPC = statue;
            type = "Destroy";
         }
         else
            type = "Steal";
         String locType = "place";
         if(randMission == STEAL_DAGGERS)
         {
            target = new Item("5 magic daggers",10000);
            locType = CultimaPanel.player.getLocationType();
            int mi = CultimaPanel.player.getMapIndex();
            byte [][] currMap = CultimaPanel.map.get(mi);
            int r = (int)(Math.random()*currMap.length);
            int c = (int)(Math.random()*currMap[0].length);
            ArrayList<Point>spawnPoints = new ArrayList<Point>();
            for(int cr = 0; cr<currMap.length; cr++)
               for(int cc = 0; cc<currMap[0].length; cc++)
                  if(LocationBuilder.canMoveTo(currMap, cr, cc) && !TerrainBuilder.onWater(currMap, cr, cc))
                     spawnPoints.add(new Point(cr, cc));
            if(spawnPoints.size() > 0)
            {
               for(String dag: magicDaggers)
               { 
                  Weapon weap = Weapon.getWeaponWithName(dag);
                  if(weap != null)
                  {
                     byte indexType = NPC.randTraveler();
                  
                     Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
                     r = (int)(spawn.getX());
                     c = (int)(spawn.getY());
                     NPCPlayer traveler = new NPCPlayer(indexType, r, c, mi, r, c, locType);
                     traveler.setWeapon(weap);
                     CultimaPanel.civilians.get(mi).add(traveler);
                  }
                  else
                  {
                     selected.setNumInfo(0);    
                     return NPC.jesterResponse(selected,"");
                  }  
               }
            }
            else
            {
               selected.setNumInfo(0);    
               return NPC.jesterResponse(selected,"");
            }
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);               
         
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
         
            Mission getMission = new Mission(type, story, startRow, startCol, 0, null, "none", new Item(target.getName(),target.getValue()), "none", mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[STEAL_DAGGERS]==0)
               CultimaPanel.missionsGiven[STEAL_DAGGERS]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
         else if(randMission == STEAL_PICK || randMission == STEAL_BOOK || randMission == STEAL_DICE)
         {
            int randType = Player.rollDie(10);
            inLocNPCs = CultimaPanel.civilians.get(selected.getMapIndex());
            ArrayList<NPCPlayer> jesters = new ArrayList<NPCPlayer>();
            for(NPCPlayer p: inLocNPCs)
               targNPC = null;
            if(jesters.size() <= 1)
               randType = Player.rollDie(2,10);
            if(randType < 2)           //pick goes to someone at the same location
            {
               loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
               locType = CultimaPanel.player.getLocationType();
               if(jesters.size()==0)
                  targNPC = null;
               else
                  targNPC = jesters.get((int)(Math.random()*jesters.size()));
            }
            if(randType <= 5 || targNPC==null)      //pick goes to someone at the next closest castle
            {
               String [] locTypes = {"castle","tower"};
               Location closeCastle = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes);
               civiliansToWrite = true;
               inLocNPCs = CultimaPanel.civilians.get(closeCastle.getMapIndex());
               jesters = new ArrayList<NPCPlayer>();
               for(NPCPlayer p: inLocNPCs)
               {
                  if(randMission == STEAL_PICK || randMission == STEAL_DICE)
                  {
                     if(p.getCharIndex()==NPC.JESTER)
                        jesters.add(p);
                  }
                  else  
                     if(p.getCharIndex()==NPC.JESTER || NPC.isGuard(p.getCharIndex()) || p.getCharIndex()==NPC.SWORD || p.getCharIndex()==NPC.KING)
                        jesters.add(p);     
               }
               if(jesters.size() > 0)
               {
                  targNPC = jesters.get((int)(Math.random()*jesters.size()));
                  loc = closeCastle;
               }
            }
            if(targNPC==null)                       //lute goes to  someone at the next closest town
            {
               String []locTypes2 = {"city","fortress","port","village"};
               Location closeCity = TerrainBuilder.closeLocationAndBuild(CultimaPanel.allLocations, locTypes2);
               civiliansToWrite = true;
               inLocNPCs = CultimaPanel.civilians.get(closeCity.getMapIndex());
               jesters = new ArrayList<NPCPlayer>();
               for(NPCPlayer p: inLocNPCs)
               {
                  if(randMission == STEAL_PICK || randMission == STEAL_DICE)
                  {
                     if(p.getCharIndex()==NPC.JESTER)
                        jesters.add(p);
                  }
                  else  
                     if(p.getCharIndex()==NPC.JESTER || NPC.isGuard(p.getCharIndex()) || p.getCharIndex()==NPC.SWORD || p.getCharIndex()==NPC.KING)
                        jesters.add(p); 
               }
               if(jesters.size() > 0)
               {
                  targNPC = jesters.get((int)(Math.random()*jesters.size()));
                  loc = closeCity;
               }
            }
            if(randMission == STEAL_PICK)
               target =  Item.getMagicPick(); 
            else if(randMission == STEAL_DICE)
               target =  Item.getLoadedCube(); 
            else
               target = new Item("riddlebook",50);
          
            if(targNPC!=null && loc!=null)
            {
               TerrainBuilder.markMapPath(loc.getName());
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = target.getValue()*10;
               targNPC.addItem(target);
               String targName = Utilities.shortName(targNPC.getName());         
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("NPC_NAME", targName);
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
                  story[i]=story[i].replace("ITEM_NAME", target.getName());
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               if(targNPC.getMapIndex() != selected.getMapIndex())
               {
                  mRow = loc.getRow();
                  mCol = loc.getCol();
               }
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(target.getName(),target.getValue()), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[randMission]==0)
                  CultimaPanel.missionsGiven[randMission]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }
         }
         else if(randMission==MERCY_KILL)
         {
            type = "Mercy";
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            String [] temp = missions[randMission];
            String [] story = new String[3];
            story[0] = new String(temp[0]);
            story[1] = new String(temp[1]);
            story[2] = new String(temp[2]);
            int goldReward = 0;
            String targName = Utilities.shortName(selected.getName());
            if(targName.contains("~"))
            {
               String [] parts = targName.split("~");
               targName = parts[0];
            }         
            String locName = Display.provinceName(loc.getName());
            int startRow = CultimaPanel.player.getWorldRow();
            int startCol = CultimaPanel.player.getWorldCol();
            int mRow = -1, mCol = -1;
            Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, null, targName, mRow, mCol);
            selected.setMissionInfo(getMission.getInfo());
            CultimaPanel.missionStack.add(getMission);
            if(CultimaPanel.missionsGiven[randMission]==0)
               CultimaPanel.missionsGiven[randMission]++;
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            response = getMission.getStartStory();
         }
         else if(randMission == DESTROY_STATUE)
         {
            loc = CultimaPanel.allLocations.get(CultimaPanel.player.getMapIndex());
            if(targNPC!=null && loc!=null)
            {
               String [] temp = missions[randMission];
               String [] story = new String[3];
               story[0] = new String(temp[0]);
               story[1] = new String(temp[1]);
               story[2] = new String(temp[2]);
               int goldReward = 2000;
               String targName = "statue-head";     
               targNPC.addItem(new Item(targName, goldReward));
                
               String locName = Display.provinceName(loc.getName());
            
               for(int i=0; i<story.length; i++)
               {
                  story[i]=story[i].replace("GOLD_REWARD", ""+goldReward);
                  story[i]=story[i].replace("LOCATION_NAME", locName);
               }
               int startRow = CultimaPanel.player.getWorldRow();
               int startCol = CultimaPanel.player.getWorldCol();
               int mRow = -1, mCol = -1;
               mRow = loc.getRow();
               mCol = loc.getCol();
             
               TerrainBuilder.markMapPath(loc.getName());
               Mission getMission = new Mission(type, story, startRow, startCol, goldReward, null, locName, new Item(targName, goldReward), targName, mRow, mCol);
               selected.setMissionInfo(getMission.getInfo());
               CultimaPanel.missionStack.add(getMission);
               if(CultimaPanel.missionsGiven[DESTROY_STATUE]==0)
                  CultimaPanel.missionsGiven[DESTROY_STATUE]++;
               FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
               FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
               response = getMission.getStartStory();
            }
         }    
      }
      if(response.length() == 0)
      {
         response = NPC.mainMission[(int)(Math.random()*NPC.mainMission.length)]+".";
         selected.setNumInfo(0);
      }
      if(civiliansToWrite)
         FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
      return response;
   }

   public String toString()
   {
      String ans = "";
      ans += type + "     **type\n";
      ans += startStory + "     **startStory\n";
      ans += middleStory + "     **middleStory\n";         
      ans += endStory + "     **endStory\n";               
      ans += "("+worldRow+","+worldCol + ")     **(worldRow,worldCol)\n";  
      ans += goldReward + "     **goldReward\n";    
      ans += itemReward + "     **itemReward\n";   
      ans += clearLocation + "     **clearLocation\n";  
      ans += target + "     **target\n";       
      ans += targetHolder + "     **targetHolder\n";      
      ans += "("+targetRow+","+targetCol + ")     **(targetRow,targetCol)\n";
      ans += failed + "     **failed";
      return ans;
   }
      
}