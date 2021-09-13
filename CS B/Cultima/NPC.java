import java.util.ArrayList;
import java.awt.Point;

public class NPC
{
//civilians
   public static final byte BEGGER = 0;
   public static final byte CHILD = 1;
   public static final byte GUARD_SPEAR = 2;
   public static final byte JESTER = 3;
   public static final byte LUTE = 4;
   public static final byte MAN = 5;
   public static final byte SWORD = 6;
   public static final byte WISE = 7;
   public static final byte WOMAN= 8;
   public static final byte TAXMAN= 9;
   public static final byte ROYALGUARD = 10;
   public static final byte KING = 11;
   public static final byte GUARD_SWORD = 12;
   public static final byte GUARD_FIST = 13;
//monsters
 //no NPC types before this point will be included in the journal
   public static final byte BUGBEAR = 14;
   public static final byte CYCLOPS = 15;
   public static final byte DEMON = 16;
   public static final byte DRAGON = 17;
   public static final byte GHOST = 18;
   public static final byte GIANT = 19;
   public static final byte ORC = 20;
   public static final byte SEAMONSTER = 21;
   public static final byte SKELETON = 22;
   public static final byte TREE = 23;
   public static final byte GRABOID = 24;
   public static final byte TROLL = 25;
   public static final byte GOBLIN = 26;
   public static final byte RUSTCREATURE = 27;
   public static final byte TRIFFID = 28;
   
   public static final byte SLIME = 29;
   public static final byte BEHOLDER = 30;
   
//animals
   public static final byte CHICKEN = 31;
   public static final byte PIG = 32;
   public static final byte BAT = 33;
   public static final byte RABBIT = 34;
   public static final byte ELK = 35;
   public static final byte HORSE = 36;
   public static final byte RAT = 37;
   public static final byte FISH = 38;
   public static final byte SHARK = 39;
   public static final byte SNAKE = 40;
   public static final byte SPIDER = 41;
   public static final byte SQUID = 42;
   public static final byte WOLF = 43;
   public static final byte BEAR = 44;
   public static final byte DOG = 45;
//nature
   public static final byte TORNADO = 46;
   public static final byte WHIRLPOOL = 47;
   public static final byte MAGMA = 48;
   public static final byte DEATH = 49;
//ships
   public static final byte GREATSHIP = 50;
   public static final byte BRIGANDSHIP = 51;   
//human 
   public static final byte SORCERER = 52;
   public static final byte BRIGAND_SWORD = 53;
//no monsters beyond this point will be included in the journal
   public static final byte BRIGAND_SPEAR = 54;
   public static final byte BRIGAND_HAMMER = 55;
   public static final byte BRIGAND_DAGGER = 56;
   public static final byte BRIGAND_CROSSBOW = 57;
   public static final byte BRIGAND_FIST = 58;

//monster kings and queens
   public static final byte DEMONKING = 59;
   public static final byte DRAGONKING = 60;
   public static final byte TREEKING = 61;
   public static final byte BATKING = 62;
   public static final byte RATKING = 63;
   public static final byte SNAKEKING = 64;
   public static final byte SPIDERKING = 65;
   public static final byte WOLFKING = 66;
   public static final byte BEARKING = 67;
   public static final byte SQUIDKING = 68;
   public static final byte BUGBEARKING = 69;
   public static final byte BRIGANDKING = 70;
   public static final byte BOWASSASSIN = 71;
   public static final byte VIPERASSASSIN = 72;
   public static final byte ENFORCER = 73;
   public static final byte TROLLKING = 74;
   public static final byte PHANTOM = 75;
   public static final byte HYDRACLOPS = 76;
   public static final byte ABOMINABLE = 77;
   public static final byte GOBLINBARREL = 78;
   
   public static final byte BARREL = 79;
   public static final byte BOAT = 80;
   public static final byte LICHE = 81;
   
   public static final byte MALEVAMP = 82;
   public static final byte FEMALEVAMP = 83;
   public static final byte WEREWOLF = 84;

   public static final byte FIRE = 85;
   public static final byte STONE = 86;
   public static final byte MONOLITH = 87;
   public static final byte JAWTRAP = 88;

//dinosaurs
   public static final byte PTEROSAUR = 89;
   public static final byte BRACHIOSAUR = 90;
   public static final byte ALLOSAUR = 91;
   public static final byte STEGOSAUR = 92;
   public static final byte PLESIOSAUR = 93;
   public static final byte DILOPHOSAUR = 94;

//1942/wolfenstein
   public static final byte SOLDIER = 95;
   public static final byte OFFICER = 96;
   public static final byte GUARD_DOG = 97;
   public static final byte TANK = 98;

//new monsters
   public static final byte UNICORN = 99;
   public static final byte BRIGANDRIDER = 100;

   public static final byte NUM_NPCS = 101;

//given the name of a monster upper-case, returns its byte index value
//value returned must match the final bytes for NPCs defined above
//returns -99 if not found
   public static byte monsterNameToIndex(String name)
   {
      if(name.equals("BEGGER")) 
         return 0;
      if(name.equals("CHILD")) 
         return 1;
      if(name.equals("GUARD_SPEAR")) 
         return 2;
      if(name.equals("JESTER")) 
         return 3;
      if(name.equals("LUTE")) 
         return 4;
      if(name.equals("MAN")) 
         return 5;
      if(name.equals("SWORD")) 
         return 6;
      if(name.equals("WISE")) 
         return 7;
      if(name.equals("WOMAN")) 
         return 8;
      if(name.equals("TAXMAN")) 
         return 9;
      if(name.equals("ROYALGUARD")) 
         return 10;
      if(name.equals("KING")) 
         return 11;
      if(name.equals("GUARD_SWORD")) 
         return 12;
      if(name.equals("GUARD_FIST")) 
         return 13;
   //monsters
      if(name.equals("BUGBEAR")) 
         return 14;
      if(name.equals("CYCLOPS")) 
         return 15;
      if(name.equals("DEMON")) 
         return 16;
      if(name.equals("DRAGON")) 
         return 17;
      if(name.equals("GHOST")) 
         return 18;
      if(name.equals("GIANT")) 
         return 19;
      if(name.equals("ORC")) 
         return 20;
      if(name.equals("SEAMONSTER")) 
         return 21;
      if(name.equals("SKELETON")) 
         return 22;
      if(name.equals("TREE")) 
         return 23;
      if(name.equals("GRABOID")) 
         return 24;
      if(name.equals("TROLL")) 
         return 25;
      if(name.equals("GOBLIN"))
         return 26;
      if(name.equals("RUSTCREATURE"))
         return 27;
      if(name.equals("TRIFFID"))
         return 28;
      if(name.equals("SLIME")) 
         return 29;
      if(name.equals("BEHOLDER")) 
         return 30;
   //animals
      if(name.equals("CHICKEN")) 
         return 31;
      if(name.equals("PIG")) 
         return 32;
      if(name.equals("BAT")) 
         return 33;
      if(name.equals("RABBIT")) 
         return 34;
      if(name.equals("ELK")) 
         return 35;
      if(name.equals("HORSE")) 
         return 36;
      if(name.equals("RAT")) 
         return 37;
      if(name.equals("FISH")) 
         return 38;
      if(name.equals("SHARK")) 
         return 39;
      if(name.equals("SNAKE")) 
         return 40;
      if(name.equals("SPIDER")) 
         return 41;
      if(name.equals("SQUID")) 
         return 42;
      if(name.equals("WOLF")) 
         return 43;
      if(name.equals("BEAR")) 
         return 44;
      if(name.equals("DOG")) 
         return 45;
   //nature
      if(name.equals("TORNADO")) 
         return 46;
      if(name.equals("WHIRLPOOL")) 
         return 47;
      if(name.equals("MAGMA")) 
         return 48;
      if(name.equals("DEATH")) 
         return 49;
   //ships
      if(name.equals("GREATSHIP")) 
         return 50;
      if(name.equals("BRIGANDSHIP")) 
         return 51;
   //human 
      if(name.equals("SORCERER")) 
         return 52;
      if(name.equals("BRIGAND_SWORD")) 
         return 53;
      if(name.equals("BRIGAND_SPEAR")) 
         return 54;
      if(name.equals("BRIGAND_HAMMER")) 
         return 55;
      if(name.equals("BRIGAND_DAGGER")) 
         return 56;
      if(name.equals("BRIGAND_CROSSBOW")) 
         return 57;
      if(name.equals("BRIGAND_FIST")) 
         return 58;
   //new monsters   
     //monster kings and queens
      if(name.equals("DEMONKING")) 
         return 59;
      if(name.equals("DRAGONKING")) 
         return 60;
      if(name.equals("TREEKING")) 
         return 61;
      if(name.equals("BATKING")) 
         return 62;
      if(name.equals("RATKING")) 
         return 63;
      if(name.equals("SNAKEKING")) 
         return 64;
      if(name.equals("SPIDERKING")) 
         return 65;
      if(name.equals("WOLFKING")) 
         return 66;
      if(name.equals("BEARKING")) 
         return 67;
      if(name.equals("SQUIDKING")) 
         return 68;
      if(name.equals("BUGBEARKING")) 
         return 69;
      if(name.equals("BRIGANDKING")) 
         return 70;
      if(name.equals("BOWASSASSIN")) 
         return 71;
      if(name.equals("VIPERASSASSIN")) 
         return 72;
      if(name.equals("ENFORCER")) 
         return 73;
      if(name.equals("TROLLKING")) 
         return 74;
      if(name.equals("PHANTOM")) 
         return 75;
      if(name.equals("HYDRACLOPS")) 
         return 76;
      if(name.equals("ABOMINABLE")) 
         return 77;
      if(name.equals("GOBLINBARREL")) 
         return 78;
      if(name.equals("BARREL")) 
         return 79;
      if(name.equals("BOAT")) 
         return 80;
      if(name.equals("LICHE")) 
         return 81;
        
      if(name.equals("MALEVAMP")) 
         return 82;
      if(name.equals("FEMALEVAMP")) 
         return 83;
      if(name.equals("WEREWOLF")) 
         return 84;
   
      if(name.equals("FIRE")) 
         return 85;
      if(name.equals("STONE")) 
         return 86;
      if(name.equals("MONOLITH")) 
         return 87;
      if(name.equals("JAWTRAP")) 
         return 88;
      
      if(name.equals("PTEROSAUR"))
         return 89;
      if(name.equals("BRACHIOSAUR"))
         return 90;
      if(name.equals("ALLOSAUR"))
         return 91;
      if(name.equals("STEGOSAUR"))
         return 92;
      if(name.equals("PLESIOSAUR"))
         return 93;
      if(name.equals("DILOPHOSAUR"))
         return 94;
   
      if(name.equals("SOLDIER"))
         return 95;
      if(name.equals("OFFICER"))
         return 96;
      if(name.equals("GUARD_DOG"))
         return 97;
      if(name.equals("TANK"))
         return 98;
   
      if(name.equals("UNICORN"))
         return 99;
      if(name.equals("BRIGANDRIDER"))
         return 100;    
      return -99;
   }

   //given the byte index value or a character, returns the charcater name as a String
   //returns "?" if sent an invalid character index
   public static String characterDescription(byte charIndex)
   {
   //civilians
      if(charIndex == BEGGER)
         return "Begger";
      if(charIndex == CHILD)
         return "Child";
      if(charIndex==GUARD_SPEAR || charIndex==GUARD_SWORD || charIndex==GUARD_FIST)
         return "Guard";
      if(charIndex==ROYALGUARD)
         return "Royal-guard";   
      if(charIndex==JESTER)
         return "Jester";
      if(charIndex==LUTE)
         return "Musician";
      if(charIndex==MAN || charIndex==WOMAN)
         return "Citizen";
      if(charIndex==SWORD)
         return "Adventurer";
      if(charIndex==WISE)
         return "Elder";
      if(charIndex==TAXMAN)
         return "Taxman";
      if(charIndex==KING)
         return "Royalty";  
   //monsters     
      if(charIndex == BUGBEAR || charIndex==BUGBEARKING)
         return "Bugbear";
      if(charIndex == CYCLOPS)
         return "Cyclops";
      if(charIndex==DEMON || charIndex==DEMONKING)
         return "Demon";
      if(charIndex==DRAGON || charIndex==DRAGONKING)
         return "Dragon";
      if(charIndex==GHOST)
         return "Spectre";
      if(charIndex==PHANTOM)
         return "Phantom";
      if(charIndex==GIANT)
         return "Colossus";
      if(charIndex==ORC)
         return "Orc";
      if(charIndex==SEAMONSTER)
         return "Seaserpent";
      if(charIndex==SKELETON)
         return "Deadite"; 
      if(charIndex==TREE || charIndex==TREEKING)
         return "Roper";
      if(charIndex==TROLL || charIndex==TROLLKING)
         return "Troll";  
      if(charIndex==GOBLIN || charIndex==GOBLINBARREL)
         return "Goblin";
      if(charIndex==GRABOID)
         return "Graboid";  
      if(charIndex==MAGMA)
         return "Magma-mother";  
      if(charIndex==HYDRACLOPS)
         return "Hydraclops";
      if(charIndex==ABOMINABLE)
         return "Abominable";
      if(charIndex==MALEVAMP || charIndex==FEMALEVAMP)
         return "Vampyre";  
      if(charIndex==WEREWOLF)
         return "Werewolf";  
   //animals     
      if(charIndex == CHICKEN)
         return "Fowl";
      if(charIndex == PIG)
         return "Swine";
      if(charIndex == BAT || charIndex == BATKING)
         return "Bat";
      if(charIndex == RABBIT)
         return "Hare";
      if(charIndex == ELK)
         return "Elk";
      if(charIndex==HORSE)
         return "Horse";
      if(charIndex==RAT || charIndex == RATKING)
         return "Rat";
      if(charIndex==FISH)
         return "Fish";   
      if(charIndex==SHARK)
         return "Shark";
      if(charIndex==SNAKE || charIndex==SNAKEKING)
         return "Serpent";
      if(charIndex==SPIDER || charIndex==SPIDERKING)
         return "Spider";
      if(charIndex==SQUID || charIndex==SQUIDKING)
         return "Squid";
      if(charIndex==WOLF || charIndex==WOLFKING)
         return "Wolfen"; 
      if(charIndex==BEAR || charIndex==BEARKING)
         return "Bear"; 
      if(charIndex==DOG)
         return "Dog";   
   //nature    
      if(charIndex==TORNADO)
         return "Tornado";
      if(charIndex==WHIRLPOOL)
         return "Whirlpool"; 
      if(charIndex==FIRE)
         return "Fire";  
   //human
      if(charIndex==BRIGAND_SWORD || charIndex==BRIGANDKING || charIndex==BRIGAND_SPEAR || charIndex==BRIGAND_HAMMER || charIndex==BRIGAND_DAGGER || charIndex==BRIGAND_CROSSBOW || charIndex==BRIGAND_FIST || charIndex==BRIGANDRIDER)
         return "Brigand";
      if(charIndex==SORCERER)
         return "Sorcerer";   
      if(charIndex==BOWASSASSIN || charIndex==VIPERASSASSIN || charIndex==ENFORCER)
         return "Assassin";   
   //ships
      if(charIndex==GREATSHIP)
         return "Great-Ship";
      if(charIndex==BRIGANDSHIP)
         return "Brigand-Ship";   
   //misc
      if(charIndex==STONE)
         return "Stone-spire";
      if(charIndex==MONOLITH)
         return "Monolith";
      if(charIndex==JAWTRAP)
         return "Jaw-trap";    
      if(charIndex==DEATH)
         return "Death";   
         
     //dinosaurs    
      if(charIndex==PTEROSAUR)
         return "Pterosaur";
      if(charIndex==BRACHIOSAUR)
         return "Brachiosaur";
      if(charIndex==ALLOSAUR)
         return "Allosaur";
      if(charIndex==STEGOSAUR)
         return "Stegosaur";
      if(charIndex==PLESIOSAUR)
         return "Plesioosaur";
      if(charIndex==DILOPHOSAUR)
         return "Dilophosaur";
   
      //1942/wolfenstein
      if(charIndex==SOLDIER)
         return "Soldier";
      if(charIndex==OFFICER)
         return "Officer";
      if(charIndex==GUARD_DOG)
         return "German-shepherd";
      if(charIndex==TANK)
         return "Tank";
   
      if(charIndex==RUSTCREATURE)
         return "Rustcreature";
   
      if(charIndex==UNICORN)
         return "Unicorn";
      if(charIndex==TRIFFID)
         return "Triffid";
         
      if(charIndex==LICHE)
         return "Liche";
      if(charIndex==BOAT)
         return "Boat";
      if(charIndex==SLIME)
         return "Slime";
      if(charIndex==BEHOLDER)
         return "Beholder";
         
      return "?";
   }

   //stat index for the array of stats for each character type defined below
   public static final byte MIN_MIGHT = 0;
   public static final byte MAX_MIGHT = 1;
   public static final byte MIN_MIND  = 2;
   public static final byte MAX_MIND  = 3;
   public static final byte MIN_AGIL  = 4;
   public static final byte MAX_AGIL  = 5;
   public static final byte DEMEANOR  = 6;

//each elements index must correspond to the NPC character (row) and stat description (col) finals defined above
   public static final int [][] stats = 
   {  //minMight, maxMight, minMind,  maxMind,  minAgili, maxAgili, demeanor (1 or -1)  
      {3,         10,         8,       20,      3 ,         10,      1},    /*BEGGER */
      {3,         5,          5,       8,       3,          10,      1},    /*CHILD  */
      {20,        50,         8,       30,      10,         50,      1},    /*GUARD_SPEAR  */
      {5,         20,         8,       20,      20,         50,      1},    /*JESTER */
      {5,         20,         8,       20,      10,         30,      1},    /*LUTE  */
      {5,         20,         8,       20,      3,          20,      1},    /*MAN   */
      {15,        50,         8,       50,      15,         50,      1},    /*SWORD  */
      {3,         20,         20,      50,      3,          20,      1},    /*WISE   */
      {5,         15,         8,       25,      3,          20,      1},    /*WOMAN   */
      {5,         20,         8,       20,      40,         50,      1},    /*TAXMAN    */
      {40,        50,         40,      50,      40,         50,      1},    /*ROYALGUARD*/
      {10,        50,         30,      50,      10,         50,      1},    /*KING    */
      {20,        50,         8,       30,      10,         50,      1},    /*GUARD_SWORD */
      {20,        50,         8,       30,      10,         50,      1},    /*GUARD_FIST   */
      
      {20,        50,         3,       10,      20,         20,     -1},    /*BUGBEAR */
      {50,        90,         4,       5,       5,          10,     -1},    /*CYCLOPS */
      {50,        60,         50,      70,      20,         40,     -1},    /*DEMON  */
      {90,        100,        10,      40,      40,         60,      1},    /*DRAGON */
      {3,         10,         30,      50,      1,          10,     -1},    /*GHOST */
      {80,        100,        5,       8,       3,          10,     -1},    /*GIANT */
      {20,        30,         5,       10,      15,         25,     -1},    /*ORC    */
      {90,        100,        10,      40,      2,          5,      -1},    /*SEAMONSTER */
      {3,         10,         1,       3,       3,          5,      -1},    /*SKELETON*/
      {30,        50,         1,       2,       0,          0,       1},    /*TREE   */
      {80,       120,         3,       10,      1,          1,      -1},    /*GRABOID  */
      {30,        60,         5,       10,      15,         20,     -1},    /*TROLL  */
      {10,        15,         6,       9,       20,         25,     -1},    /*GOBLIN    */
      {45,        60,         3,       5,       40,         55,      1},    /*RUSTCREATURE  */
      {75,       100,         1,       2,       1,          1,       1},     /*TRIFFID */  
      {8,         12,         1,       2,       1,          1,      -1},    /*SLIME    */
      {40,        60,         60,      80,      10,         20,     -1},    /*BEHOLDER  */  
           
      {1,         1,          1,       3,       3,          5,       1},    /*CHICKEN */
      {3,         8,          1,       3,       1,          3,       1},    /*PIG   */
      {1,         2,          1,       3,       20,         25,      1},    /*BAT   */
      {1,         1,          1,       3,       10,         15,      1},    /*RABBIT */
      {40,        60,         1,       3,       50,         60,      1},    /*ELK   */
      {30,        50,         1,       3,       60,         70,      1},    /*HORSE */
      {3,         5,          1,       3,       10,         15,      1},    /*RAT    */
      {1,         1,          1,       2,       1,          1,       1},    /*FISH  */
      {30,        40,         1,       2,       10,         20,     -1},    /*SHARK   */
      {5,         10,         1,       2,       5,          10,      1},    /*SNAKE  */
      {1,         2,          1,       2,       5,          10,      1},    /*SPIDER*/
      {60,        90,         1,       3,       2,          5,       1},    /*SQUID */
      {15,        30,         3,       5,       30,         40,      1},    /*WOLF  */
      {45,        60,         3,       5,       40,         55,      1},    /*BEAR  */
      {10,        25,         3,       5,       35,         45,      1},    /*DOG   */
      
      {100,      500,         0,       0,       80,         100,     0},    /*TORNADO */
      {100,      500,         0,       0,       1,          3,       0},    /*WHIRLPOOL*/
      {200,      250,         50,      70,      20,         30,     -1},    /*MAGMA   */
      {300,      350,         60,      80,      50,         60,     -1},    /*DEATH*/
      
      {150,      300,         15,      20,      1,          2,       1},    /*GREATSHIP */
      {100,      200,         3,       10,      2,          3,      -1},    /*BRIGANDSHIP */
      
      {3,         20,         40,      50,      15,         30,     -1},    /*SORCERER  */
      
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_SWORD  */
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_SPEAR*/
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_HAMMER*/
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_DAGGER*/
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_CROSSBOW*/
      {15,        30,         5,       15,      15,         30,     -1},    /*BRIGAND_FIST*/
      
      {200,      250,         50,      70,      20,         30,     -1},    /*DEMONKING*/
      {200,      350,         30,      60,      45,         50,     -1},    /*DRAGONKING*/
      {75,       100,         1,       2,       0,          0,      -1},    /*TREEKING */
      {30,        50,         3,       8,       10,         20,     -1},    /*BATKING  */
      {50,        80,         3,       8,       30,         45,     -1},    /*RATKING  */
      {80,       100,         1,       2,       10,         20,     -1},    /*SNAKEKING */
      {50,        70,         1,       2,       20,         35,     -1},    /*SPIDERKING */
      {50,        70,         10,      30,      30,         50,     -1},    /*WOLFKING */
      {100,      200,         20,      40,      50,         60,     -1},    /*BEARKING */
      {100,      200,         50,      50,      2,          5,       1},    /*SQUIDKING  */
      {100,      150,         30,      40,      10,         30,     -1},    /*BUGBEARKING*/
      {50,        50,         30,      50,      40,         50,     -1},    /*BRIGANDKING */
      {30,        40,         40,      50,      50,         50,     -1},    /*BOWASSASSIN*/
      {30,        40,         40,      50,      50,         50,     -1},    /*VIPERASSASSIN*/
      {30,        40,         40,      50,      50,         50,     -1},    /*ENFORCER*/
      {130,      160,         30,      40,      45,         50,     -1},    /*TROLLKING */
      {3,         8,          50,      60,      1,          2,      -1},    /*PHANTOM   */
      {250,      300,         60,      70,      2,          5,      -1},    /*HYDRACLOPS*/
      {160,       200,        10,      16,      6,          20,     -1},    /*ABOMINABLE */
      {15,        20,         6,       9,       1,          1,      -1},    /*GOBLINBARREL    */
      {5,         5,          0,       0,       0,          0,      -1},    /*BARREL    */
      {5,         5,          0,       0,       0,          0,      -1},    /*BOAT    */
      {30,        50,         50,      60,      30,         40,     -1},    /*LICHE*/
   
      {50,        50,         30,      50,      40,         50,     -1},    /*MALEVAMP*/
      {50,        50,         30,      50,      40,         50,     -1},    /*FEMALEVAMP*/
      {50,        100,        10,      30,      40,         50,     -1},    /*WEREWOLF*/
      
      {20,        60,         0,       0,       0,          0,      -1},    /*FIRE   */
      {250,      500,         0,       0,       0,          0,      -1},    /*STONE*/
      {250,      500,         0,       0,       0,          0,      -1},    /*MONOLITH*/
      {5,          5,         0,       0,       0,          0,      -1},    /*JAWTRAP*/
      
      {10,        30,         3,       8,       10,         20,      1},    /*PTEROSAUR*/
      {100,       200,        5,       8,       3,          8,       1},    /*BRACHIOSAUR*/
      {90,        150,        5,       10,      40,         60,      1},    /*ALLOSAUR*/
      {60,        90,         3,       5,       10,         20,      1},    /*STEGOSAUR*/
      {60,        90,         1,       3,       2,          5,      -1},    /*PLESIOSAUR*/
      {30,        50,         5,       15,      30,         40,     -1},    /*DILOPHOSAUR*/
      
      {10,        25,         8,       50,      15,         50,      -1},   /*SOLDIER*/
      {10,        25,         40,      50,      40,         50,      -1},   /*OFFICER*/
      {10,        25,         4,       8,       40,         50,      -1},   /*GUARD_DOG*/
      {500,       900,        40,      50,      3,          5,       -1},   /*TANK*/
      
      {60,       100,        20,      40,       80,        100,      1},    /*UNICORN */
      {40,        50,        40,      50,       60,         70,      -1}    /*BRIGANDRIDER*/
   
      //minMight, maxMight, minMind,  maxMind,  minAgili, maxAgili, demeanor (1 or -1)
      };

//each row index must correspond to the finals defined above: row is the monster index, the 5 arrays in each row are the animation frames for facing UP, RIGHT, DOWN, LEFT and the DEAD image
   public static final String [][][] characters = 
   {  {{"images/characters/civilians/begger1.Gif", "images/characters/civilians/begger2.Gif", "images/characters/civilians/begger3.Gif", "images/characters/civilians/begger4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/child1.Gif", "images/characters/civilians/child2.Gif", "images/characters/civilians/child3.Gif", "images/characters/civilians/child4.Gif"},{},{},{},{"images/characters/civilians/deadSmall.Gif"}}, 
      {{"images/characters/civilians/guardUp1.Gif", "images/characters/civilians/guardUp2.Gif", "images/characters/civilians/guardUp3.Gif", "images/characters/civilians/guardUp4.Gif"},{"images/characters/civilians/guardRight1.Gif", "images/characters/civilians/guardRight2.Gif", "images/characters/civilians/guardRight3.Gif", "images/characters/civilians/guardRight4.Gif"},{"images/characters/civilians/guardDown1.Gif", "images/characters/civilians/guardDown2.Gif", "images/characters/civilians/guardDown3.Gif", "images/characters/civilians/guardDown4.Gif"},{"images/characters/civilians/guardLeft1.Gif", "images/characters/civilians/guardLeft2.Gif", "images/characters/civilians/guardLeft3.Gif", "images/characters/civilians/guardLeft4.Gif"},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/jester1.Gif", "images/characters/civilians/jester2.Gif", "images/characters/civilians/jester3.Gif", "images/characters/civilians/jester4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/lute1.Gif", "images/characters/civilians/lute2.Gif", "images/characters/civilians/lute3.Gif", "images/characters/civilians/lute4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/man1.Gif", "images/characters/civilians/man2.Gif", "images/characters/civilians/man3.Gif", "images/characters/civilians/man4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/sword1.Gif", "images/characters/civilians/sword2.Gif", "images/characters/civilians/sword3.Gif", "images/characters/civilians/sword4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/wise1.Gif", "images/characters/civilians/wise2.Gif", "images/characters/civilians/wise3.Gif", "images/characters/civilians/wise4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/woman1.Gif", "images/characters/civilians/woman2.Gif", "images/characters/civilians/woman3.Gif", "images/characters/civilians/woman4.Gif"},{},{},{},{"images/characters/civilians/deadSmall.Gif"}},
      {{"images/characters/civilians/taxmanUp1.Gif", "images/characters/civilians/taxmanUp2.Gif"},{"images/characters/civilians/taxmanRight1.Gif", "images/characters/civilians/taxmanRight2.Gif"},{"images/characters/civilians/taxmanDown1.Gif", "images/characters/civilians/taxmanDown2.Gif"},{"images/characters/civilians/taxmanLeft1.Gif", "images/characters/civilians/taxmanLeft2.Gif"},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/swordShield1.Gif", "images/characters/civilians/swordShield2.Gif", "images/characters/civilians/swordShield3.Gif", "images/characters/civilians/swordShield4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/king1.Gif", "images/characters/civilians/king2.Gif", "images/characters/civilians/king3.Gif", "images/characters/civilians/king4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/guardSwordUp1.Gif", "images/characters/civilians/guardSwordUp2.Gif", "images/characters/civilians/guardSwordUp3.Gif", "images/characters/civilians/guardSwordUp4.Gif"},{"images/characters/civilians/guardSwordRight1.Gif", "images/characters/civilians/guardSwordRight2.Gif", "images/characters/civilians/guardSwordRight3.Gif", "images/characters/civilians/guardSwordRight4.Gif"},{"images/characters/civilians/guardSwordDown1.Gif", "images/characters/civilians/guardSwordDown2.Gif", "images/characters/civilians/guardSwordDown3.Gif", "images/characters/civilians/guardSwordDown4.Gif"},{"images/characters/civilians/guardSwordLeft1.Gif", "images/characters/civilians/guardSwordLeft2.Gif", "images/characters/civilians/guardSwordLeft3.Gif", "images/characters/civilians/guardSwordLeft4.Gif"},{"images/characters/civilians/dead.Gif"}}, 
      {{"images/characters/civilians/guardFistUp1.Gif", "images/characters/civilians/guardFistUp2.Gif", "images/characters/civilians/guardFistUp3.Gif", "images/characters/civilians/guardFistUp4.Gif"},{"images/characters/civilians/guardFistRight1.Gif", "images/characters/civilians/guardFistRight2.Gif", "images/characters/civilians/guardFistRight3.Gif", "images/characters/civilians/guardFistRight4.Gif"},{"images/characters/civilians/guardFistDown1.Gif", "images/characters/civilians/guardFistDown2.Gif", "images/characters/civilians/guardFistDown3.Gif", "images/characters/civilians/guardFistDown4.Gif"},{"images/characters/civilians/guardFistLeft1.Gif", "images/characters/civilians/guardFistLeft2.Gif", "images/characters/civilians/guardFistLeft3.Gif", "images/characters/civilians/guardFistLeft4.Gif"},{"images/characters/civilians/dead.Gif"}}, 
      
      {{"images/characters/monsters/bugbearUp1.Gif", "images/characters/monsters/bugbearUp2.Gif", "images/characters/monsters/bugbearUp3.Gif", "images/characters/monsters/bugbearUp4.Gif"},{"images/characters/monsters/bugbear1.Gif", "images/characters/monsters/bugbear2.Gif", "images/characters/monsters/bugbear3.Gif", "images/characters/monsters/bugbear4.Gif"},{"images/characters/monsters/bugbear1.Gif", "images/characters/monsters/bugbear2.Gif", "images/characters/monsters/bugbear3.Gif", "images/characters/monsters/bugbear4.Gif"},{"images/characters/monsters/bugbear1.Gif", "images/characters/monsters/bugbear2.Gif", "images/characters/monsters/bugbear3.Gif", "images/characters/monsters/bugbear4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/monsters/cyclopsUp1.Gif", "images/characters/monsters/cyclopsUp2.Gif", "images/characters/monsters/cyclopsUp3.Gif", "images/characters/monsters/cyclopsUp4.Gif"},{"images/characters/monsters/cyclops1.Gif", "images/characters/monsters/cyclops2.Gif", "images/characters/monsters/cyclops3.Gif", "images/characters/monsters/cyclops4.Gif"},{"images/characters/monsters/cyclops1.Gif", "images/characters/monsters/cyclops2.Gif", "images/characters/monsters/cyclops3.Gif", "images/characters/monsters/cyclops4.Gif"},{"images/characters/monsters/cyclops1.Gif", "images/characters/monsters/cyclops2.Gif", "images/characters/monsters/cyclops3.Gif", "images/characters/monsters/cyclops4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/demon1.Gif", "images/characters/monsters/demon2.Gif", "images/characters/monsters/demon3.Gif", "images/characters/monsters/demon4.Gif"},{},{},{},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/dragonUp1.Gif", "images/characters/monsters/dragonUp2.Gif", "images/characters/monsters/dragonUp3.Gif", "images/characters/monsters/dragonUp4.Gif"},{"images/characters/monsters/dragonRight1.Gif", "images/characters/monsters/dragonRight2.Gif", "images/characters/monsters/dragonRight3.Gif", "images/characters/monsters/dragonRight4.Gif"},{"images/characters/monsters/dragonDown1.Gif", "images/characters/monsters/dragonDown2.Gif", "images/characters/monsters/dragonDown3.Gif", "images/characters/monsters/dragonDown4.Gif"},{"images/characters/monsters/dragonLeft1.Gif", "images/characters/monsters/dragonLeft2.Gif", "images/characters/monsters/dragonLeft3.Gif", "images/characters/monsters/dragonLeft4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/monsters/ghost1.Gif", "images/characters/monsters/ghost2.Gif", "images/characters/monsters/ghost3.Gif", "images/characters/monsters/ghost4.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/monsters/giantUp1.Gif", "images/characters/monsters/giantUp2.Gif", "images/characters/monsters/giantUp3.Gif", "images/characters/monsters/giantUp4.Gif"},{"images/characters/monsters/giant1.Gif", "images/characters/monsters/giant2.Gif", "images/characters/monsters/giant3.Gif", "images/characters/monsters/giant4.Gif"},{"images/characters/monsters/giant1.Gif", "images/characters/monsters/giant2.Gif", "images/characters/monsters/giant3.Gif", "images/characters/monsters/giant4.Gif"},{"images/characters/monsters/giant1.Gif", "images/characters/monsters/giant2.Gif", "images/characters/monsters/giant3.Gif", "images/characters/monsters/giant4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/orcUp1.Gif", "images/characters/monsters/orcUp2.Gif", "images/characters/monsters/orcUp3.Gif", "images/characters/monsters/orcUp4.Gif"},{"images/characters/monsters/orc1.Gif", "images/characters/monsters/orc2.Gif", "images/characters/monsters/orc3.Gif", "images/characters/monsters/orc4.Gif"},{"images/characters/monsters/orc1.Gif", "images/characters/monsters/orc2.Gif", "images/characters/monsters/orc3.Gif", "images/characters/monsters/orc4.Gif"},{"images/characters/monsters/orc1.Gif", "images/characters/monsters/orc2.Gif", "images/characters/monsters/orc3.Gif", "images/characters/monsters/orc4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/seaLeft1.Gif", "images/characters/monsters/seaLeft2.Gif", "images/characters/monsters/seaLeft3.Gif", "images/characters/monsters/seaLeft4.Gif"},{"images/characters/monsters/seaRight1.Gif", "images/characters/monsters/seaRight2.Gif", "images/characters/monsters/seaRight3.Gif", "images/characters/monsters/seaRight4.Gif"},{"images/characters/monsters/seaRight1.Gif", "images/characters/monsters/seaRight2.Gif", "images/characters/monsters/seaRight3.Gif", "images/characters/monsters/seaRight4.Gif"},{"images/characters/monsters/seaLeft1.Gif", "images/characters/monsters/seaLeft2.Gif", "images/characters/monsters/seaLeft3.Gif", "images/characters/monsters/seaLeft4.Gif"},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/monsters/skelUp1.Gif", "images/characters/monsters/skelUp2.Gif", "images/characters/monsters/skelUp3.Gif", "images/characters/monsters/skelUp4.Gif"},{"images/characters/monsters/skel1.Gif", "images/characters/monsters/skel2.Gif", "images/characters/monsters/skel3.Gif", "images/characters/monsters/skel4.Gif"},{"images/characters/monsters/skel1.Gif", "images/characters/monsters/skel2.Gif", "images/characters/monsters/skel3.Gif", "images/characters/monsters/skel4.Gif"},{"images/characters/monsters/skel1.Gif", "images/characters/monsters/skel2.Gif", "images/characters/monsters/skel3.Gif", "images/characters/monsters/skel4.Gif"},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/monsters/tree1.Gif", "images/characters/monsters/tree2.Gif", "images/characters/monsters/tree3.Gif", "images/characters/monsters/tree4.Gif"},{},{},{}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/monsters/graboid1.Gif", "images/characters/monsters/graboid2.Gif", "images/characters/monsters/graboid3.Gif", "images/characters/monsters/graboid4.Gif", "images/characters/monsters/graboid5.Gif", "images/characters/monsters/graboid6.Gif", "images/characters/monsters/graboid7.Gif", "images/characters/monsters/graboid8.Gif"},{},{},{},{"images/characters/monsters/graboidDead.Gif"}},
      {{"images/characters/monsters/trollUp1.Gif", "images/characters/monsters/trollUp2.Gif", "images/characters/monsters/trollUp3.Gif", "images/characters/monsters/trollUp4.Gif"},{"images/characters/monsters/troll1.Gif", "images/characters/monsters/troll2.Gif", "images/characters/monsters/troll3.Gif", "images/characters/monsters/troll4.Gif"},{"images/characters/monsters/troll1.Gif", "images/characters/monsters/troll2.Gif", "images/characters/monsters/troll3.Gif", "images/characters/monsters/troll4.Gif"},{"images/characters/monsters/troll1.Gif", "images/characters/monsters/troll2.Gif", "images/characters/monsters/troll3.Gif", "images/characters/monsters/troll4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/goblinUp1.Gif", "images/characters/monsters/goblinUp2.Gif", "images/characters/monsters/goblinUp3.Gif", "images/characters/monsters/goblinUp4.Gif"},{"images/characters/monsters/goblin1.Gif", "images/characters/monsters/goblin2.Gif", "images/characters/monsters/goblin3.Gif", "images/characters/monsters/goblin4.Gif"},{"images/characters/monsters/goblin1.Gif", "images/characters/monsters/goblin2.Gif", "images/characters/monsters/goblin3.Gif", "images/characters/monsters/goblin4.Gif"},{"images/characters/monsters/goblin1.Gif", "images/characters/monsters/goblin2.Gif", "images/characters/monsters/goblin3.Gif", "images/characters/monsters/goblin4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/rustL1.Gif", "images/characters/monsters/rustL2.Gif", "images/characters/monsters/rustL3.Gif", "images/characters/monsters/rustL4.Gif"},{"images/characters/monsters/rustR1.Gif", "images/characters/monsters/rustR2.Gif", "images/characters/monsters/rustR3.Gif", "images/characters/monsters/rustR4.Gif"},{"images/characters/monsters/rustR1.Gif", "images/characters/monsters/rustR2.Gif", "images/characters/monsters/rustR3.Gif", "images/characters/monsters/rustR4.Gif"},{"images/characters/monsters/rustL1.Gif", "images/characters/monsters/rustL2.Gif", "images/characters/monsters/rustL3.Gif", "images/characters/monsters/rustL4.Gif"},{"images/characters/monsters/deadMisc.Gif"}},    /*RUSTCREATURE*/
      {{"images/characters/monsters/plantosaur1.Gif", "images/characters/monsters/plantosaur2.Gif", "images/characters/monsters/plantosaur3.Gif", "images/characters/monsters/plantosaur4.Gif","images/characters/monsters/plantosaur5.Gif", "images/characters/monsters/plantosaur6.Gif", "images/characters/monsters/plantosaur7.Gif", "images/characters/monsters/plantosaur8.Gif"},{},{},{}, {"images/characters/monsters/deadMisc.Gif"}},  
   
      {{"images/characters/monsters/slime0.Gif", "images/characters/monsters/slime1.Gif", "images/characters/monsters/slime2.Gif", "images/characters/monsters/slime3.Gif"},{},{},{}, {"images/characters/monsters/deadEtheral.Gif"}},  
      {{"images/characters/monsters/beholder0.Gif", "images/characters/monsters/beholder1.Gif", "images/characters/monsters/beholder2.Gif", "images/characters/monsters/beholder3.Gif"},{},{},{}, {"images/characters/monsters/deadMisc.Gif"}},  
   
      {{"images/characters/animals/chickenUp1.Gif", "images/characters/animals/chickenUp2.Gif"}, {"images/characters/animals/chickenRight1.Gif", "images/characters/animals/chickenRight2.Gif"}, {"images/characters/animals/chickenDown1.Gif", "images/characters/animals/chickenDown2.Gif"}, {"images/characters/animals/chickenLeft1.Gif","images/characters/animals/chickenLeft2.Gif"},{"images/characters/animals/deadChicken.Gif"}},
      {{"images/characters/animals/pigUp1.Gif", "images/characters/animals/pigUp2.Gif"}, {"images/characters/animals/pigRight1.Gif", "images/characters/animals/pigRight2.Gif"}, {"images/characters/animals/pigDown1.Gif", "images/characters/animals/pigDown2.Gif"}, { "images/characters/animals/pigLeft1.Gif","images/characters/animals/pigLeft2.Gif"},{"images/characters/animals/deadPig.Gif"}},
      {{"images/characters/animals/bat1.Gif", "images/characters/animals/bat2.Gif", "images/characters/animals/bat3.Gif", "images/characters/animals/bat4.Gif"},{},{},{},{"images/characters/animals/deadChicken.Gif"}},
      {{"images/characters/animals/rabbitUp1.Gif", "images/characters/animals/rabbitUp2.Gif"},{"images/characters/animals/rabbitRight1.Gif","images/characters/animals/rabbitRight2.Gif"},{"images/characters/animals/rabbitDown1.Gif","images/characters/animals/rabbitDown2.Gif"},{"images/characters/animals/rabbitLeft1.Gif","images/characters/animals/rabbitLeft2.Gif"},{"images/characters/animals/deadChicken.Gif"}},
      {{"images/characters/animals/elkUp1.Gif", "images/characters/animals/elkUp2.Gif"}, {"images/characters/animals/elkRight1.Gif", "images/characters/animals/elkRight2.Gif"}, {"images/characters/animals/elkDown1.Gif", "images/characters/animals/elkDown2.Gif"},{"images/characters/animals/elkLeft1.Gif", "images/characters/animals/elkLeft2.Gif"},{"images/characters/monsters/deadGame.Gif"}},
      {{"images/characters/animals/horseUp1.Gif", "images/characters/animals/horseUp2.Gif"},{"images/characters/animals/horseRight1.Gif", "images/characters/animals/horseRight2.Gif"},{"images/characters/animals/horseDown1.Gif", "images/characters/animals/horseDown2.Gif"},{"images/characters/animals/horseLeft1.Gif",   "images/characters/animals/horseLeft2.Gif"},{"images/characters/monsters/deadGame.Gif"}},
      {{"images/characters/animals/ratUp1.Gif", "images/characters/animals/ratUp2.Gif", "images/characters/animals/ratUp3.Gif", "images/characters/animals/ratUp4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/fish2.Gif", "images/characters/animals/fish3.Gif", "images/characters/animals/fish4.Gif", "images/characters/animals/fish1.Gif"},{},{},{},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/animals/shark1.Gif", "images/characters/animals/shark2.Gif", "images/characters/animals/shark3.Gif", "images/characters/animals/shark4.Gif"},{},{},{},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/animals/snakeUp1.Gif", "images/characters/animals/snakeUp2.Gif", "images/characters/animals/snakeUp3.Gif", "images/characters/animals/snakeUp4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/animals/spiderUp1.Gif", "images/characters/animals/spiderUp2.Gif", "images/characters/animals/spiderUp3.Gif", "images/characters/animals/spiderUp4.Gif"},{"images/characters/animals/spider1.Gif", "images/characters/animals/spider2.Gif", "images/characters/animals/spider3.Gif", "images/characters/animals/spider4.Gif"},{"images/characters/animals/spider1.Gif", "images/characters/animals/spider2.Gif", "images/characters/animals/spider3.Gif", "images/characters/animals/spider4.Gif"},{"images/characters/animals/spider1.Gif", "images/characters/animals/spider2.Gif", "images/characters/animals/spider3.Gif", "images/characters/animals/spider4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/animals/squid1.Gif", "images/characters/animals/squid2.Gif", "images/characters/animals/squid3.Gif", "images/characters/animals/squid4.Gif"},{},{},{},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/animals/wolfUp1.Gif", "images/characters/animals/wolfUp2.Gif"},{"images/characters/animals/wolfRight1.Gif", "images/characters/animals/wolfRight2.Gif"},{"images/characters/animals/wolfDown1.Gif", "images/characters/animals/wolfDown2.Gif"},{"images/characters/animals/wolfLeft1.Gif",   "images/characters/animals/wolfLeft2.Gif"},{"images/characters/animals/deadPig.Gif"}},
      {{"images/characters/animals/bearUp1.Gif", "images/characters/animals/bearUp2.Gif", "images/characters/animals/bearUp3.Gif", "images/characters/animals/bearUp4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/dogUp1.Gif", "images/characters/animals/dogUp2.Gif"},{"images/characters/animals/dogRight1.Gif", "images/characters/animals/dogRight2.Gif"},{"images/characters/animals/dogDown1.Gif", "images/characters/animals/dogDown2.Gif"},{"images/characters/animals/dogLeft1.Gif",   "images/characters/animals/dogLeft2.Gif"},{"images/characters/animals/deadPig.Gif"}},
      
      {{"images/characters/nature/tornado1.Gif", "images/characters/nature/tornado2.Gif", "images/characters/nature/tornado3.Gif", "images/characters/nature/tornado4.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/nature/whirlpool1.Gif", "images/characters/nature/whirlpool2.Gif", "images/characters/nature/whirlpool3.Gif", "images/characters/nature/whirlpool4.Gif"},{},{},{},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/monsters/magma1.Gif", "images/characters/monsters/magma2.Gif", "images/characters/monsters/magma3.Gif", "images/characters/monsters/magma4.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/monsters/death1.Gif", "images/characters/monsters/death2.Gif", "images/characters/monsters/death3.Gif", "images/characters/monsters/death4.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
   
      {{"images/characters/ships/shipUp.Gif", "images/characters/ships/shipUp2.Gif"},{"images/characters/ships/shipRight.Gif", "images/characters/ships/shipRight2.Gif"},{"images/characters/ships/shipDown.Gif", "images/characters/ships/shipDown2.Gif"},{"images/characters/ships/shipLeft.Gif",    "images/characters/ships/shipLeft2.Gif"},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/ships/pshipUp.Gif", "images/characters/ships/pshipUp2.Gif"},{"images/characters/ships/pshipRight.Gif", "images/characters/ships/pshipRight2.Gif"},{"images/characters/ships/pshipDown.Gif", "images/characters/ships/pshipDown2.Gif"},{"images/characters/ships/pshipLeft.Gif",  "images/characters/ships/pshipLeft2.Gif"},{"images/characters/monsters/deadWater.Gif"}},
      
      {{"images/characters/civilians/magic1.Gif", "images/characters/civilians/magic2.Gif", "images/characters/civilians/magic3.Gif", "images/characters/civilians/magic4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/saber1.Gif", "images/characters/civilians/saber2.Gif", "images/characters/civilians/saber3.Gif", "images/characters/civilians/saber4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/brigSpear1.Gif", "images/characters/civilians/brigSpear2.Gif", "images/characters/civilians/brigSpear3.Gif", "images/characters/civilians/brigSpear4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/brigHammer1.Gif", "images/characters/civilians/brigHammer2.Gif", "images/characters/civilians/brigHammer3.Gif", "images/characters/civilians/brigHammer4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/brigDagger1.Gif", "images/characters/civilians/brigDagger2.Gif", "images/characters/civilians/brigDagger3.Gif", "images/characters/civilians/brigDagger4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/brigXBow1.Gif", "images/characters/civilians/brigXBow2.Gif", "images/characters/civilians/brigXBow3.Gif", "images/characters/civilians/brigXBow4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/brigFist1.Gif", "images/characters/civilians/brigFist2.Gif", "images/characters/civilians/brigFist3.Gif", "images/characters/civilians/brigFist4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      
      {{"images/characters/monsters/demon1.Gif", "images/characters/monsters/demon2.Gif", "images/characters/monsters/demon3.Gif", "images/characters/monsters/demon4.Gif"},{},{},{},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/dragonUp1.Gif", "images/characters/monsters/dragonUp2.Gif", "images/characters/monsters/dragonUp3.Gif", "images/characters/monsters/dragonUp4.Gif"},{"images/characters/monsters/dragonRight1.Gif", "images/characters/monsters/dragonRight2.Gif", "images/characters/monsters/dragonRight3.Gif", "images/characters/monsters/dragonRight4.Gif"},{"images/characters/monsters/dragonDown1.Gif", "images/characters/monsters/dragonDown2.Gif", "images/characters/monsters/dragonDown3.Gif", "images/characters/monsters/dragonDown4.Gif"},{"images/characters/monsters/dragonLeft1.Gif", "images/characters/monsters/dragonLeft2.Gif", "images/characters/monsters/dragonLeft3.Gif", "images/characters/monsters/dragonLeft4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/monsters/tree1.Gif", "images/characters/monsters/tree2.Gif", "images/characters/monsters/tree3.Gif", "images/characters/monsters/tree4.Gif"},{},{},{}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/animals/bat1.Gif", "images/characters/animals/bat2.Gif", "images/characters/animals/bat3.Gif", "images/characters/animals/bat4.Gif"},{},{},{},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/ratUp1.Gif", "images/characters/animals/ratUp2.Gif", "images/characters/animals/ratUp3.Gif", "images/characters/animals/ratUp4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/animals/rat1.Gif", "images/characters/animals/rat2.Gif", "images/characters/animals/rat3.Gif", "images/characters/animals/rat4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/snakeUp1.Gif", "images/characters/animals/snakeUp2.Gif", "images/characters/animals/snakeUp3.Gif", "images/characters/animals/snakeUp4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"},{"images/characters/animals/snake1.Gif", "images/characters/animals/snake2.Gif", "images/characters/animals/snake3.Gif", "images/characters/animals/snake4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/animals/spider1.Gif", "images/characters/animals/spider2.Gif", "images/characters/animals/spider3.Gif", "images/characters/animals/spider4.Gif"},{},{},{}, {"images/characters/monsters/deadMisc.Gif"}},
      {{"images/characters/animals/wolfUp1.Gif", "images/characters/animals/wolfUp2.Gif"},{"images/characters/animals/wolfRight1.Gif", "images/characters/animals/wolfRight2.Gif"},{"images/characters/animals/wolfDown1.Gif", "images/characters/animals/wolfDown2.Gif"},{"images/characters/animals/wolfLeft1.Gif",   "images/characters/animals/wolfLeft2.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/bearUp1.Gif", "images/characters/animals/bearUp2.Gif", "images/characters/animals/bearUp3.Gif", "images/characters/animals/bearUp4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/animals/bear1.Gif", "images/characters/animals/bear2.Gif", "images/characters/animals/bear3.Gif", "images/characters/animals/bear4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/animals/squidK1.Gif", "images/characters/animals/squidK2.Gif", "images/characters/animals/squidK3.Gif", "images/characters/animals/squidK4.Gif"},{},{},{},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/monsters/bugbearkingUp1.Gif", "images/characters/monsters/bugbearkingUp2.Gif", "images/characters/monsters/bugbearkingUp3.Gif", "images/characters/monsters/bugbearkingUp4.Gif"},{"images/characters/monsters/bugbearking1.Gif", "images/characters/monsters/bugbearking2.Gif", "images/characters/monsters/bugbearking3.Gif", "images/characters/monsters/bugbearking4.Gif"},{"images/characters/monsters/bugbearking1.Gif", "images/characters/monsters/bugbearking2.Gif", "images/characters/monsters/bugbearking3.Gif", "images/characters/monsters/bugbearking4.Gif"},{"images/characters/monsters/bugbearking1.Gif", "images/characters/monsters/bugbearking2.Gif", "images/characters/monsters/bugbearking3.Gif", "images/characters/monsters/bugbearking4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},
      {{"images/characters/player/dual1.Gif", "images/characters/player/dual2.Gif", "images/characters/player/dual3.Gif", "images/characters/player/dual4.Gif"},{},{},{},{"images/characters/civilians/dead.Gif"}},
      {{"images/characters/civilians/bowwoman1.Gif", "images/characters/civilians/bowwoman2.Gif", "images/characters/civilians/bowwoman3.Gif", "images/characters/civilians/bowwoman4.Gif"},{},{},{},{"images/characters/civilians/deadSmall.Gif"}},
      {{"images/characters/civilians/assassin1.Gif", "images/characters/civilians/assassin2.Gif", "images/characters/civilians/assassin3.Gif", "images/characters/civilians/assassin4.Gif"},{},{},{},{"images/characters/civilians/deadSmall.Gif"}},
      {{"images/characters/civilians/enforcer1.Gif", "images/characters/civilians/enforcer2.Gif", "images/characters/civilians/enforcer3.Gif", "images/characters/civilians/enforcer4.Gif"},{},{},{},{"images/characters/civilians/deadSmall.Gif"}},
      {{"images/characters/monsters/trollkingUp1.Gif", "images/characters/monsters/trollkingUp2.Gif", "images/characters/monsters/trollkingUp3.Gif", "images/characters/monsters/trollkingUp4.Gif"},{"images/characters/monsters/trollking1.Gif", "images/characters/monsters/trollking2.Gif", "images/characters/monsters/trollking3.Gif", "images/characters/monsters/trollking4.Gif"},{"images/characters/monsters/trollking1.Gif", "images/characters/monsters/trollking2.Gif", "images/characters/monsters/trollking3.Gif", "images/characters/monsters/trollking4.Gif"},{"images/characters/monsters/trollking1.Gif", "images/characters/monsters/trollking2.Gif", "images/characters/monsters/trollking3.Gif", "images/characters/monsters/trollking4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/characters/monsters/ghost1.Gif", "images/characters/monsters/ghost2.Gif", "images/characters/monsters/ghost3.Gif", "images/characters/monsters/ghost4.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/monsters/hydraL1.Gif", "images/characters/monsters/hydraL2.Gif", "images/characters/monsters/hydraL3.Gif", "images/characters/monsters/hydraL4.Gif"},{"images/characters/monsters/hydraR1.Gif", "images/characters/monsters/hydraR2.Gif", "images/characters/monsters/hydraR3.Gif", "images/characters/monsters/hydraR4.Gif"},{"images/characters/monsters/hydraL1.Gif", "images/characters/monsters/hydraL2.Gif", "images/characters/monsters/hydraL3.Gif", "images/characters/monsters/hydraL4.Gif"},{"images/characters/monsters/hydraL1.Gif", "images/characters/monsters/hydraL2.Gif", "images/characters/monsters/hydraL3.Gif", "images/characters/monsters/hydraL4.Gif"}, {"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/monsters/AbominableDown1.GIF", "images/characters/monsters/AbominableDown2.GIF", "images/characters/monsters/AbominableDown3.GIF", "images/characters/monsters/AbominableDown4.GIF"},{"images/characters/monsters/AbominableDown1.GIF", "images/characters/monsters/AbominableDown2.GIF", "images/characters/monsters/AbominableDown3.GIF", "images/characters/monsters/AbominableDown4.GIF"},{"images/characters/monsters/AbominableDown1.GIF", "images/characters/monsters/AbominableDown2.GIF", "images/characters/monsters/AbominableDown3.GIF", "images/characters/monsters/AbominableDown4.GIF"},{"images/characters/monsters/AbominableDown1.GIF", "images/characters/monsters/AbominableDown2.GIF", "images/characters/monsters/AbominableDown3.GIF", "images/characters/monsters/AbominableDown4.GIF"}, {"images/characters/monsters/abominableDead.Gif"}},
      {{"images/characters/monsters/goblinBarrel1.Gif", "images/characters/monsters/goblinBarrel2.Gif", "images/characters/monsters/goblinBarrel3.Gif", "images/characters/monsters/goblinBarrel4.Gif"},{"images/characters/monsters/goblinBarrel1.Gif", "images/characters/monsters/goblinBarrel2.Gif", "images/characters/monsters/goblinBarrel3.Gif", "images/characters/monsters/goblinBarrel4.Gif"},{"images/characters/monsters/goblinBarrel1.Gif", "images/characters/monsters/goblinBarrel2.Gif", "images/characters/monsters/goblinBarrel3.Gif", "images/characters/monsters/goblinBarrel4.Gif"},{"images/characters/monsters/goblinBarrel1.Gif", "images/characters/monsters/goblinBarrel2.Gif", "images/characters/monsters/goblinBarrel3.Gif", "images/characters/monsters/goblinBarrel4.Gif"},{"images/characters/monsters/deadHumanoid.Gif"}},
      {{"images/items/barrel.Gif", "images/items/barrel.Gif", "images/items/barrel.Gif", "images/items/barrel.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/ships/boatUp.Gif"},{"images/characters/ships/boatRight.Gif"},{"images/characters/ships/boatDown.Gif"},{"images/characters/ships/boatLeft.Gif"},{"images/characters/monsters/deadWater.Gif"}},
      {{"images/characters/monsters/liche0.Gif", "images/characters/monsters/liche1.Gif", "images/characters/monsters/liche2.Gif", "images/characters/monsters/liche3.Gif"},{},{},{}, {"images/characters/civilians/dead.Gif"}},
   
      {{"images/characters/monsters/vman1.Gif", "images/characters/monsters/vman2.Gif", "images/characters/monsters/vman3.Gif", "images/characters/monsters/vman4.Gif"},{},{},{}, {"images/characters/civilians/dead.Gif"}},
      {{"images/characters/monsters/vwoman1.Gif", "images/characters/monsters/vwoman2.Gif", "images/characters/monsters/vwoman3.Gif", "images/characters/monsters/vwoman4.Gif"},{},{},{}, {"images/characters/civilians/deadSmall.Gif"}},
      {{"images/characters/monsters/werewolf1.Gif", "images/characters/monsters/werewolf2.Gif", "images/characters/monsters/werewolf3.Gif", "images/characters/monsters/werewolf4.Gif"},{},{},{}, {"images/characters/civilians/dead.Gif"}},
      
      {{"images/characters/nature/fire1.Gif", "images/characters/nature/fire2.Gif", "images/characters/nature/fire3.Gif", "images/characters/nature/fire0.Gif"},{},{},{},{"images/characters/monsters/deadEtheral.Gif"}},
      {{"images/characters/nature/stoneSpire.Gif", "images/characters/nature/stoneSpire.Gif", "images/characters/nature/stoneSpire.Gif", "images/characters/nature/stoneSpire.Gif"},{},{},{},{"images/characters/nature/monolithBroken.Gif"}},
      {{"images/characters/nature/monolith.Gif", "images/characters/nature/monolith.Gif", "images/characters/nature/monolith.Gif", "images/characters/nature/monolith.Gif"},{},{},{},{"images/characters/nature/monolithBroken.Gif"}},
      {{"images/items/jawtrap.Gif", "images/items/jawtrap.Gif", "images/items/jawtrap.Gif", "images/items/jawtrap.Gif"},{},{},{},{"images/items/jawtrap.Gif"}},
      
      {{"images/characters/dinosaurs/pterosaur1.Gif", "images/characters/dinosaurs/pterosaur2.Gif", "images/characters/dinosaurs/pterosaur3.Gif", "images/characters/dinosaurs/pterosaur4.Gif"},{},{},{},{"images/characters/monsters/deadClawed.Gif"}},    /*PTEROSAUR*/
      {{"images/characters/dinosaurs/brachiosaurUp1.Gif", "images/characters/dinosaurs/brachiosaurUp2.Gif", "images/characters/dinosaurs/brachiosaurUp3.Gif", "images/characters/dinosaurs/brachiosaurUp4.Gif"},{"images/characters/dinosaurs/brachiosaurRight1.Gif", "images/characters/dinosaurs/brachiosaurRight2.Gif", "images/characters/dinosaurs/brachiosaurRight3.Gif", "images/characters/dinosaurs/brachiosaurRight4.Gif"},{"images/characters/dinosaurs/brachiosaurDown1.Gif", "images/characters/dinosaurs/brachiosaurDown2.Gif", "images/characters/dinosaurs/brachiosaurDown3.Gif", "images/characters/dinosaurs/brachiosaurDown4.Gif"},{"images/characters/dinosaurs/brachiosaurLeft1.Gif", "images/characters/dinosaurs/brachiosaurLeft2.Gif", "images/characters/dinosaurs/brachiosaurLeft3.Gif", "images/characters/dinosaurs/brachiosaurLeft4.Gif"}, {"images/characters/monsters/deadMisc.Gif"}},   /*BRACHIOSAUR*/
      {{"images/characters/dinosaurs/allosaurUp1.Gif", "images/characters/dinosaurs/allosaurUp2.Gif", "images/characters/dinosaurs/allosaurUp3.Gif", "images/characters/dinosaurs/allosaurUp4.Gif"},{"images/characters/dinosaurs/allosaurRight1.Gif", "images/characters/dinosaurs/allosaurRight2.Gif", "images/characters/dinosaurs/allosaurRight3.Gif", "images/characters/dinosaurs/allosaurRight4.Gif"},{"images/characters/dinosaurs/allosaurDown1.Gif", "images/characters/dinosaurs/allosaurDown2.Gif", "images/characters/dinosaurs/allosaurDown3.Gif", "images/characters/dinosaurs/allosaurDown4.Gif"},{"images/characters/dinosaurs/allosaurLeft1.Gif", "images/characters/dinosaurs/allosaurLeft2.Gif", "images/characters/dinosaurs/allosaurLeft3.Gif", "images/characters/dinosaurs/allosaurLeft4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},   /*ALLOSAUR*/
      {{"images/characters/dinosaurs/stegosaurUp1.Gif", "images/characters/dinosaurs/stegosaurUp2.Gif", "images/characters/dinosaurs/stegosaurUp3.Gif", "images/characters/dinosaurs/stegosaurUp4.Gif"},{"images/characters/dinosaurs/stegosaurRight1.Gif", "images/characters/dinosaurs/stegosaurRight2.Gif", "images/characters/dinosaurs/stegosaurRight3.Gif", "images/characters/dinosaurs/stegosaurRight4.Gif"},{"images/characters/dinosaurs/stegosaurDown1.Gif", "images/characters/dinosaurs/stegosaurDown2.Gif", "images/characters/dinosaurs/stegosaurDown3.Gif", "images/characters/dinosaurs/stegosaurDown4.Gif"},{"images/characters/dinosaurs/stegosaurLeft1.Gif", "images/characters/dinosaurs/stegosaurLeft2.Gif", "images/characters/dinosaurs/stegosaurLeft3.Gif", "images/characters/dinosaurs/stegosaurLeft4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},   /*STEGOSAUR*/
      {{"images/characters/dinosaurs/plesiosaurLeft1.Gif", "images/characters/dinosaurs/plesiosaurLeft2.Gif", "images/characters/dinosaurs/plesiosaurLeft3.Gif", "images/characters/dinosaurs/plesiosaurLeft4.Gif"},{"images/characters/dinosaurs/plesiosaurRight1.Gif", "images/characters/dinosaurs/plesiosaurRight2.Gif", "images/characters/dinosaurs/plesiosaurRight3.Gif", "images/characters/dinosaurs/plesiosaurRight4.Gif"},{"images/characters/dinosaurs/plesiosaurRight1.Gif", "images/characters/dinosaurs/plesiosaurRight2.Gif", "images/characters/dinosaurs/plesiosaurRight3.Gif", "images/characters/dinosaurs/plesiosaurRight4.Gif"},{"images/characters/dinosaurs/plesiosaurLeft1.Gif", "images/characters/dinosaurs/plesiosaurLeft2.Gif", "images/characters/dinosaurs/plesiosaurLeft3.Gif", "images/characters/dinosaurs/plesiosaurLeft4.Gif"},{"images/characters/monsters/deadWater.Gif"}},    /*PLESIOOSAUR*/
      {{"images/characters/dinosaurs/dilophosaurUp1.Gif", "images/characters/dinosaurs/dilophosaurUp2.Gif", "images/characters/dinosaurs/dilophosaurUp3.Gif", "images/characters/dinosaurs/dilophosaurUp4.Gif"},{"images/characters/dinosaurs/dilophosaurRight1.Gif", "images/characters/dinosaurs/dilophosaurRight2.Gif", "images/characters/dinosaurs/dilophosaurRight3.Gif", "images/characters/dinosaurs/dilophosaurRight4.Gif"},{"images/characters/dinosaurs/dilophosaurDown1.Gif", "images/characters/dinosaurs/dilophosaurDown2.Gif", "images/characters/dinosaurs/dilophosaurDown3.Gif", "images/characters/dinosaurs/dilophosaurDown4.Gif"},{"images/characters/dinosaurs/dilophosaurLeft1.Gif", "images/characters/dinosaurs/dilophosaurLeft2.Gif", "images/characters/dinosaurs/dilophosaurLeft3.Gif", "images/characters/dinosaurs/dilophosaurLeft4.Gif"},{"images/characters/monsters/deadClawed.Gif"}},   /*DILOPHOSAUR*/
      
      {{"images/characters/1942/soldierUp1.Gif", "images/characters/1942/soldierUp2.Gif","images/characters/1942/soldierUp3.Gif", "images/characters/1942/soldierUp4.Gif"}, {"images/characters/1942/soldierRight1.Gif", "images/characters/1942/soldierRight2.Gif","images/characters/1942/soldierRight3.Gif", "images/characters/1942/soldierRight4.Gif"}, {"images/characters/1942/soldierDown1.Gif", "images/characters/1942/soldierDown2.Gif","images/characters/1942/soldierDown3.Gif", "images/characters/1942/soldierDown4.Gif"}, {"images/characters/1942/soldierLeft1.Gif", "images/characters/1942/soldierLeft2.Gif","images/characters/1942/soldierLeft3.Gif", "images/characters/1942/soldierLeft4.Gif"},{"images/characters/1942/soldierDead.Gif"}},     /*SOLDIER*/
      {{"images/characters/1942/officerUp1.Gif", "images/characters/1942/officerUp2.Gif","images/characters/1942/officerUp3.Gif", "images/characters/1942/officerUp4.Gif"}, {"images/characters/1942/officerRight1.Gif", "images/characters/1942/officerRight2.Gif","images/characters/1942/officerRight3.Gif", "images/characters/1942/officerRight4.Gif"}, {"images/characters/1942/officerDown1.Gif", "images/characters/1942/officerDown2.Gif","images/characters/1942/officerDown3.Gif", "images/characters/1942/officerDown4.Gif"}, {"images/characters/1942/officerLeft1.Gif", "images/characters/1942/officerLeft2.Gif","images/characters/1942/officerLeft3.Gif", "images/characters/1942/officerLeft4.Gif"},{"images/characters/1942/soldierDead.Gif"}},     /*OFFICER*/
      {{"images/characters/animals/dogUp1.Gif", "images/characters/animals/dogUp2.Gif"},{"images/characters/animals/dogRight1.Gif", "images/characters/animals/dogRight2.Gif"},{"images/characters/animals/dogDown1.Gif", "images/characters/animals/dogDown2.Gif"},{"images/characters/animals/dogLeft1.Gif",   "images/characters/animals/dogLeft2.Gif"},{"images/characters/animals/deadPig.Gif"}},
      {{"images/characters/1942/tankUp.Gif", "images/characters/1942/tankUp2.Gif"},{"images/characters/1942/tankRight.Gif", "images/characters/1942/tankRight2.Gif"},{"images/characters/1942/tankDown.Gif", "images/characters/1942/tankDown2.Gif"},{"images/characters/1942/tankLeft.Gif", "images/characters/1942/tankLeft2.Gif"},{"images/characters/1942/tankDead.Gif"}},                   /*TANK*/
      
      {{"images/characters/animals/unicornUp1.Gif", "images/characters/animals/unicornUp2.Gif"},{"images/characters/animals/unicornRight1.Gif", "images/characters/animals/unicornRight2.Gif"},{"images/characters/animals/unicornDown1.Gif", "images/characters/animals/unicornDown2.Gif"},{"images/characters/animals/unicornLeft1.Gif",   "images/characters/animals/unicornLeft2.Gif"},{"images/characters/monsters/deadGame.Gif"}},  /*UNICORN*/
      {{"images/characters/civilians/dhorseUp1.Gif", "images/characters/civilians/dhorseUp2.Gif"},{"images/characters/civilians/dhorseRight1.Gif", "images/characters/civilians/dhorseRight2.Gif"},{"images/characters/civilians/dhorseDown1.Gif", "images/characters/civilians/dhorseDown2.Gif"},{"images/characters/civilians/dhorseLeft1.Gif",   "images/characters/civilians/dhorseLeft2.Gif"},{"images/characters/civilians/dead.Gif"}}
      };

 //returns true if the given character index is a common civilian type found in towns, cities, castles
   public static boolean isCivilian(byte type)
   {
      return (type==BEGGER || type==CHILD || type==GUARD_SPEAR || type==JESTER || type==LUTE || type==MAN  || type==TAXMAN
         || type==SWORD || type==WISE || type==WOMAN || type==ROYALGUARD || type==KING || type==GUARD_SWORD || type==GUARD_FIST);
   }
   
   //returns true if the given character is a common civilian type found in towns, cities, castles
   public static boolean isCivilian(NPCPlayer p)
   {
      return (isCivilian(p.getCharIndex()) && !p.isNonPlayer() && !p.isZombie() && !p.isStatue());
   }

//returns true if the given character index is a common guard type found in cities, castles, etc
   public static boolean isGuard(byte type)
   {
      return (type==GUARD_SPEAR || type==ROYALGUARD || type==GUARD_SWORD || type==GUARD_FIST);
   }

//returns true if the given character index is a common brigand type found in battlefields, monster lairs and the world
   public static boolean isBrigand(byte charIndex)
   {
      return (charIndex==BRIGAND_SWORD || charIndex==BRIGAND_SPEAR || charIndex==BRIGAND_HAMMER || charIndex==BRIGAND_DAGGER || charIndex==BRIGAND_CROSSBOW || charIndex==BRIGAND_FIST);
   }
   
   //returns true if the given character index is a common assassin type found in towns, dungeons and the world
   public static boolean isAssassin(byte charIndex)
   {
      return (charIndex==BOWASSASSIN || charIndex==VIPERASSASSIN || charIndex==ENFORCER);
   }

//returns true if the given character index is a common dosile animal
   public static boolean isTameAnimal(byte type)
   {
      return (type==CHICKEN || type==FISH || type==PIG || type==RABBIT || type==ELK || type==HORSE || type==DOG || type==UNICORN);
   }
   
   //returns true if the given character index is a common animal type that can be tamed with a spell
   public static boolean isTameableAnimal(byte type)
   {
      return (isTameAnimal(type) || type==GUARD_DOG || type==WOLF || type==WOLFKING || type==BEAR || type==BEARKING || type==SQUID || type==SQUIDKING || type==ABOMINABLE);
   }

//returns true if the given character index is a creature that a vampire can draw blood from
   public static boolean isHumanoid(byte type)
   {
      return (isCivilian(type) || type==TROLLKING  || type == BRIGANDKING  || type == BRIGANDRIDER || type == BOWASSASSIN || type == VIPERASSASSIN  || type == ENFORCER || type==CYCLOPS || type==GIANT
         || type==SORCERER || type==BRIGAND_SWORD || type==BRIGAND_SPEAR || type==BRIGAND_HAMMER || type==BRIGAND_DAGGER || type==BRIGAND_CROSSBOW || type==BRIGAND_FIST
         || type==TROLL || type==ORC || type==GOBLIN || type==GOBLINBARREL);
   }

   //returns true if the given character index is a type that can be in a city gang for the gang-clearing mission
   public static boolean isGangMember(byte type)
   {
      return (type == BRIGANDKING || type == BOWASSASSIN || type == VIPERASSASSIN  || type == ENFORCER     || type==SORCERER 
           || type==BRIGAND_SWORD || type==BRIGAND_SPEAR || type==BRIGAND_HAMMER   || type==BRIGAND_DAGGER || type==BRIGAND_CROSSBOW || type==BRIGAND_FIST);
   }
   
   //returns a random gang member type for the city gang for the gang-clearing mission
   public static byte getRandGangMember()
   {
      byte [] type = {BRIGANDKING, BOWASSASSIN, VIPERASSASSIN, ENFORCER, SORCERER, BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST};
      return type[(int)(Math.random()*type.length)];
   }

//for setting the color when we examine a monster
   public static boolean isUnusualMonster(byte type)
   {
      return (type==DEMONKING || type==DRAGONKING  || type==TREEKING || type==SPIDERKING
         || type==BATKING || type==RATKING  || type==SNAKEKING || type==WOLFKING || type==BEARKING || type == ABOMINABLE
         || type == TROLLKING  || type == SQUIDKING || type == BUGBEARKING || type == MAGMA || type == HYDRACLOPS
         || type == BRIGANDKING || type == BRIGANDRIDER || type == BOWASSASSIN || type == VIPERASSASSIN || type == ENFORCER || type == DEATH);
   }
   
   //returns true if this is a monster we can feed rations to
   public static boolean isFeedableMonster(byte type)
   {
      if(CultimaPanel.player.getRations()>=5 && (type==WOLF || type==RAT  || type==BEAR || type==GUARD_DOG || type==BUGBEAR  || type==ORC || type==DILOPHOSAUR || type==PIG || type == ABOMINABLE))
         return true;
      if(CultimaPanel.player.numFlowers()>=5 && type == UNICORN)
         return true;
      return false;
   }

//returns true if the monster makes a stomping sound
   public static boolean isStompingMonster(byte type)
   {
      return (type==DEMONKING || type==DRAGONKING  || type==TREEKING || type==SPIDERKING  || type==BEARKING || type==GIANT || type==TROLLKING || type == DEATH || type==BRACHIOSAUR || type==ALLOSAUR || type==STEGOSAUR || type == ABOMINABLE);
   }
   
   //returns true if the monster is undead and runs from life weapons
   public static boolean isUndead(byte type)
   {
      return (type==SKELETON || type==PHANTOM || type==GHOST);
   }
   
   //returns true if the monster is mist form (affects wheter or not they hold arrows fired at them)
   public static boolean isEtheral(byte type)
   {
      return (type==FIRE || type==PHANTOM  || type==GHOST);
   }
   
   //returns true if the character is a vampire type creature
   public static boolean isVampire(NPCPlayer p)
   {
      return (p.getCharIndex()==NPC.MALEVAMP || p.getCharIndex()==NPC.FEMALEVAMP);
   }
   
   //returns true if the character index is a vampire type creature
   public static boolean isVampire(byte type)
   {
      return (type==NPC.MALEVAMP || type==NPC.FEMALEVAMP);
   }
   
   //returns true if the character is a horse type creature
   public static boolean isHorse(NPCPlayer p)
   {
      return (p.getCharIndex()==NPC.HORSE || p.getCharIndex()==NPC.UNICORN);
   }
   
   //returns true if the character is a sailing ship with cannons
   public static boolean isBigShip(NPCPlayer p)
   {
      return (p.getCharIndex()==NPC.BRIGANDSHIP || p.getCharIndex()==NPC.GREATSHIP);
   }
   
   //returns true if the character is any kind of water vessel
   public static boolean isShip(NPCPlayer p)
   {
      return (p.getCharIndex()==NPC.BRIGANDSHIP || p.getCharIndex()==NPC.GREATSHIP || p.getCharIndex()==NPC.BOAT);
   }
   
  //returns true if the character index is any kind of water vessel
   public static boolean isShip(byte p)
   {
      return (p==NPC.BRIGANDSHIP || p==NPC.GREATSHIP || p==NPC.BOAT);
   }
   
   //returns true if the character index can fly
   public static boolean isFlyer(byte type)
   {
      return (type==PHANTOM || type==GHOST || type==BAT || type==BATKING || type==DRAGON || type==DRAGONKING || type==PTEROSAUR);
   }

   //returns true if npc's vision depends on the way it is facing
   public static boolean isDirectionVision(NPCPlayer npc)
   {
      return (npc.getCharIndex()==TAXMAN || npc.getCharIndex()==SOLDIER || npc.getCharIndex()==OFFICER || npc.getCharIndex()==TANK);
   }
   
   public static boolean isMonsterThreat(NPCPlayer p)
   {
      return !(NPC.isCivilian(p.getCharIndex()) || p.getCharIndex() == NPC.DOG  || p.getCharIndex() == NPC.HORSE  || p.getCharIndex() == NPC.ELK  || p.getCharIndex() == NPC.RABBIT || p.getCharIndex() == NPC.PIG  || p.getCharIndex() == NPC.CHICKEN  || p.getCharIndex() == NPC.FISH);
   }
   
   //returns true if the character is a friendly soldier on the battlefield
   public static boolean isBattleFieldFriend(NPCPlayer p)
   {
      return (NPC.isCivilian(p.getCharIndex()) || p.getCharIndex() == NPC.BOWASSASSIN  || p.getCharIndex() == NPC.ENFORCER || p.getCharIndex() == NPC.DOG || NPC.isHorse(p) || p.hasEffect("control"));
   }

   //returns the size of the NPC for drawing to scale in world view depending on distance
   public static double getSize(byte type)      
   {
      if(type==WEREWOLF || type==SOLDIER || type==OFFICER || type==GUARD_DOG || type==ELK || type==HORSE || type==BEAR || type==RUSTCREATURE || type==UNICORN || type==LICHE)
         return 1.25;
      if(type==CYCLOPS || type==DRAGON || type==TREE || type==SQUID || type==BRIGANDSHIP || type==TROLL || type==STONE || type==MONOLITH || type==TRIFFID || type==BEHOLDER || type==BRIGANDRIDER)
         return 1.5;
      if(type==DEMONKING || type==BATKING || type==RATKING || type==WOLFKING  || type==BEARKING || type==BUGBEARKING || type==PLESIOSAUR)
         return 1.5;
      if(type==DRAGONKING || type==TREEKING || type==SNAKEKING || type==SPIDERKING || type==SQUIDKING || type==TROLLKING || type==ALLOSAUR || type==STEGOSAUR)
         return 2;   
      if(type==GIANT || type==SEAMONSTER || type==TORNADO || type==GREATSHIP || type == DEATH || type == TANK)
         return 2;  
      if(type == ABOMINABLE)
         return 2.5;  
      if(type==BRACHIOSAUR || type==HYDRACLOPS)
         return 3;   
      return 1;
   }
   
   //returns the size of the NPC for drawing to scale in world view depending on distance
   public static double getSize(NPCPlayer npc)      
   {
      if(npc.getCharIndex() == FIRE)
      {
         double percentHealth = npc.getPercentHealth();
         double maxSize = 2;
         double minSize = 0.5;
         double size = (percentHealth * (maxSize - minSize) / 100.0) + minSize;
         return size;
      }
      if(npc.getCharIndex() == MAGMA)
      {
         double percentHealth = npc.getPercentHealth();
         double maxSize = 2;
         double minSize = 1;
         double size = (percentHealth * (maxSize - minSize) / 100.0) + minSize;
         return size;
      }
      if(npc.getCharIndex() == RUSTCREATURE)
      {
         return Math.min(3, Math.max(1, npc.getNumInfo()));
      }
   
      return getSize(npc.getCharIndex());
   }

//movement types
   public static final byte STILL = 0;
   public static final byte ROAM = 1;
   public static final byte MARCH_NORTH = 2;
   public static final byte MARCH_EAST = 3;
   public static final byte MARCH_SOUTH = 4;
   public static final byte MARCH_WEST = 5;
   public static final byte CHASE = 6;       //chase after player (to attack)
   public static final byte RUN = 7;         //run away from player
   public static final byte ATTACK = 8;      //to order dog to distract enemy or get rabbit
   public static final byte FOLLOW = 9;      //follow the player

   //common character dialogue responses
   public static final String[] sullied = {"Thou art sullied! Go sleep it off", "I see thee has been spending time in the tavern", "I will not converse with thee when ale laden", "Some rest and a good washing is what thee needs. And no more ale for thee"};
   
   public static final String[] onGuard = {"Put thy WEAPON away, or I will not speak with thee!", "Dost thou indend to rob me? You have thy WEAPON out!", "Sheathe thy WEAPON before I call the guards!", "I will not speak with thee with thy WEAPON out!", "Thy WEAPON in hand does not inspire conversation beyond a cry for help!", "Put down thy WEAPON lest I call the guards!", "Thee has thy WEAPON in hand as if to attack! I will not speak with thee!",
                                          "Put thy WEAP_NAME away, or I will not speak with thee!", "Dost thou indend to rob me? You have thy WEAP_NAME out!", "Sheathe thy WEAP_NAME before I call the guards!", "I will not speak with thee with thy WEAP_NAME out!", "Thy WEAP_NAME in hand does not inspire conversation beyond a cry for help!", "Put down thy WEAP_NAME lest I call the guards!", "Thee has thy WEAP_NAME in hand as if to attack! I will not speak with thee!"};
   public static final String[] onGuardForGuard = {"Put thy WEAPON away, or I will not speak with thee!", "Dost thou indend to challenge me? You have thy WEAPON out!", "Sheathe thy WEAPON before you approach me!", "I will not speak with thee with thy WEAPON out!", "Thy WEAPON in hand seems as if thee wants to test my blade!", "Put down thy WEAPON lest I run you through!", "Thee has thy WEAPON in hand as if to attack! I will not speak with thee!",
                                          "Put thy WEAP_NAME away, or I will not speak with thee!", "Dost thou indend to challenge me? You have thy WEAP_NAME out!", "Sheathe thy WEAP_NAME before you approach me!", "I will not speak with thee with thy WEAP_NAME out!", "Thy WEAP_NAME in hand seems as if thee wants to test my blade!", "Put down thy WEAP_NAME lest I run you through!", "Thee has thy WEAP_NAME in hand as if to attack! I will not speak with thee!"};

   public static final String[] taxesDue = {"Thy taxes are due! TAX_VALUE in gold, please!", "'Tis time to pay thy taxes! TAX_VALUE coins this instant.", "TAX_VALUE gold is due for thy taxes!", "Thy TAX_VALUE coins are overdue to pay thy taxes!"};
   public static final String[] noBuisness = {"Thy buisness is thy own!", "Keep mind to thy own affairs!", "'Tis my affairs and mine alone!", "Keep thyself out of my affairs!"};
   
   public static final String[] diss = {"Hmmmph", "I will not speak with thou", "Leave me be", "You will get nothing from me", "Move on", "Thou art mistaken", "By the crown of SKARA Brae, leave me be"};
   public static final String[] dissEnd = {".", "!", "knave!", "peasant!", "scoundrel!", "monster!", "slime!", "commoner!"};
   public static final String[] monsterAdjective = {"fierce", "beastly", "brutish", "fantastic", "lively", "monstrous", "knavish", "ill-tempered", "livid", "dreadful", "frightful", "grotesque", "cruel", "gruesome", "heinous", "hideous", "horrible","horrendous", "horrifying", "foul-smelling", "foul", "terrible", "viscious", "diabolical", "fiendish", "loathsome", "rank", "villainous", "repugnant"};

   public static final String[] greetings = {"Hello, fine WEATHER this day", "Good day and good WEATHER", "Greetings", "'Tis good to see you", "By the light of SKARA Brae, 'tis good to see you"};
   public static final String[] greetingsCity = {"Hello, fine WEATHER this day", "'Tis a good day in our CITY", "Greetings", "'Tis good to see you in our fine CITY", "By the light of SKARA Brae, 'tis good to see you"};
   public static final String[] greetingsEnd = {".", "!", ", friend.", ", citizen", ", companion.", ", welldoer."}; 

   public static final String[] greetings2 = {"Hello, fine WEATHER this day", "Good day and good WEATHER", "Greetings", "'Tis great to see you", "'Tis an honor to see you", "The honor is mine", "'Tis a true honor"};
   public static final String[] greetings2City = {"Hello, fine WEATHER this day", "Good day and good WEATHER", "Greetings", "'Tis great to see you in our fine CITY", "'Tis an honor to see you in our CITY", "The honor is mine", "'Tis a true honor to have you in our CITY"};
   public static final String[] greetings2End = {"!", ", my liege!", ", my lord!", ", my champion!", ", my master!", ", oh great one!"}; 

   public static final String[] preName = {"NAME", "'Tis NAME that stands before you", "In these lands, you may call me NAME", "You may call be NAME", "I am known as NAME", "I am NAME", "You may call me NAME", "Some call me NAME", "Some people call me NAME", "I answer to NAME", "I was given the name NAME", "I am NAME, not to be confused with NAME, my father", "'Tis myself, NAME", "Thou speaketh to NAME", "You stand before NAME"};

   public static final String[] poisonAttemptFail = {"I can see thy Viperglove. A pittiful attempt to poison me!", "Thy attempt to poison me is transparent! Meet thy end!", "You have not concealed thy Viperglove! Thou shall not poison me this day!", "Try to poison me with thy Viperglove? Now meet death herself!"};

   public static final String[] homeTown = {"I will show you on your map", "My home - it is here on your map", "My place of birth, here on your map", "It is here on your map"};
   public static final String[] noHomeTown = {"It is an unimportant place", "My home - it is a vile place", "My place of birth, I know not where it is", "You need not visit such a wretched place", "'Tis a place abandoned by the crown of SKARA Brae"};
   public static final String[] knowHomeTown = {"'Tis already marked on your map", "I see you know of my home", "My place of birth, it is already on your map", "You have been to my place of birth"};
   public static final String[] thisHomeTown = {"I live here", "'Tis this very place where I live", "You are in my home", "This fine place is my home", "This beautiful CITY under the crown of SKARA Brae, is my home"};

   public static final String[] nameAsk = {"'Tis the name of my father.", "'Tis my name, yes.", "'Tis the name of my kin.", "So you have heard of me?", "'Twas the name given to me.", "'Tis the name of a servant of SKARA Brae."};

   public static final String[] gold = {"Art thou trying to BRIBE me?", "'Tis a BRIBE that you offer?", "Dost thou think I can be BRIBED?", "Do you offer a BRIBE?", "By the crown of SKARA Brae, dost thou mean to BRIBE me?"};
   public static final String[] bribeNo = {"You offend me, sir!", "I will not leave my post!", "My honour is to my king!", "You shall not pass!", "By the crown of SKARA Brae, my honor cannot be purchased!"};
   public static final String[] bribeMaybe = {"'Tis not horable...but...ten gold is just not enough.", "'Tis insufficient.  Ten gold is not enough!", "Thy shall have to do better than ten gold.", "Ten gold?  'Tis too small a price."};
   public static final String[] bribeYes = {"Speak nothing of this...", "'Tis good pay...", "Thy shall not tell my superiors...", "'Tis a fair price..."};
   public static final String[] bribeNoMoney = {"You have not the coin!", "Thy pockets are empty!", "Ye shall have to find the coin first!", "You have no coin to pay!", "For the love of SKARA Brae, you have not the coin!"};
   public static final String[] fight = {"Have at thee!", "Thou shalt pay for thy crimes!", "On guard, knave!", "To the pain!"};

   public static final String[] jester = {"I make merry", "'Tis the life of jest", "I live to hear a hearty laugh", "I work to amuse and delight", "I make lite of life", "I dance, I prance, I LOCK you in a trance", "I will play a trick or two", "I have not many companions", "I wait for my time to end", "I wish to UNLOCK a smile from you"};
   public static final String[] kingNoMission = {"I will send for thee when there is more to be done.", "My kingdom is in good order.", "There will be work for you elsewhere.", "Seek adventures elsewhere:all is sound here.", "SKARA Brae will guide you back here when you are needed."};

   public static final String[] begger = {"Dost thou have COIN to spare", "Willst thou take CHARITY", "GIVE to the downtrodden", "Help me", "Take CHARITY on a servant of Skara Brae", "Spare some RATIONS for the hungry", "Spare RATIONS to feed my starving children"};
   public static final String[] beggerThanks = {"I thank thee", "I shall find a way to repay you", "'Tis very kind of you", "You have my deepest thanks", "By the crown of SKARA Brae, I thank thee"};

   public static final String[] allInfoTold = {"'Tis all I know", "I have shared all I know", "I have told thee what I know", "There is no more that I can tell thee"};
   public static final String[] giveMessage = {"I thank thee for taking this correspondence", "Please see that this message is delivered", "Thank thee for delivering this important message", "I hope your travels are safe in delivering this correspondence", "May the crown of SKARA Brae watch over you on your journey"};

   public static final String[] sellItem = {"I shall sell you this precious item for SELL_PRICE gold", "I can part ways with it for SELL_PRICE coins", "'Twill take SELL_PRICE gold to part ways with it", "'Twill cost you SELL_PRICE coins to trade"};
   public static final String[] sellItemComplete = {"'Tis yours to enjoy", "'Tis difficult to part ways with it, but I need the gold", "'Tis good doing buisness with thee", "I hope it brings thee happiness", "May the crown of SKARA Brae guide you"};

   public static final String[] cheerLesser = {"'Tis the famous adventurer, NAME!", "Is that NAME, in our very town?", "'Tis NAME the famed adventurer!", "'Tis NAME the hero!", "Three cheers for the heroic NAME!", "'Tis NAME! The adventurous tales are true!", "'Tis the famous NAME!", "Is that NAME from the high tales of adventure?"};
   public static final String[] cheer = {"'Tis the legend, NAME!", "Is that NAME, in our very town?", "'Tis NAME the hero of all!", "'Tis NAME the hero!", "Three cheers for NAME, the legend!", "Three cheers for the heroic NAME!", "'Tis NAME! The legends are true!", "'Tis the mighty NAME!"};
   public static final String[] blessedCrown = {"NAME wears the Crown of Skara Brae!", "Look! 'Tis the Crown of Skara Brae!", "My Gods! NAME wears the Crown!", "Bow to NAME who wears the Crown of Skara Brae!", "Is it NAME who wears the Crown of Skara Brae?", "The Crown! The Crown of Skara Brae!"};
   public static final String[] legendaryWeapon = {"NAME carries the WEAPON!", "Look! 'Tis the legendary WEAPON!", "My Gods! NAME has the WEAPON!", "Is it NAME who carries the famed WEAPON?", "The WEAPON! The legendary WEAPON!"};

   public static final String[] screamLesser = {"'Tis the scoundrel NAME. Beware!", "'Tis the knave NAME. Be on guard!", "'Tis the knavish NAME. Beware!", "NAME, the villain is here. Be on guard!", "Be on thy guard, 'tis the ill-famed NAME!", "'Tis NAME, of ill-fame. Beware!", "NAME is here. Be on thy guard!", "NAME, the villain!", "The villanous NAME!", "NAME, the scoundrel!"};
   public static final String[] scream = {"'Tis the villainous NAME! Run!", "'Tis the monster NAME! Run!", "'Tis the beast NAME! Run!", "NAME! The beast is here! Run!", "Run for your lives! 'Tis the vile NAME!", "'Tis NAME, the vile one! Run!", "NAME is here! Run!", "NAME! The beast!", "NAME! The villain!", "The villanous NAME!", "NAME! The monster!"};
   public static final String[] helpMonster = {"Help us NAME - slay the beast!", "'Tis a beast in the city - who will help us!", "Run! A beast!", "A beast in the city! Someone save us!", "Run for your lives! 'Tis a beast in the city!", "A beast in the gates! For the crown of Skara Brae, save us!"};
   public static final String[] helpFire = {"Fire!", "'Tis a fire in the city - who will help us!", "Run! A fire!", "A fire in the city! Someone save us!", "Run for your lives! 'Tis a fire in the city!", "A fire within the gates! For the crown of Skara Brae, save us!"};
   public static final String[] helpShip = {"Brigands!", "'Tis a Brigand ship in the port - who will help us!", "Run! The Brigand ship!", "A Brigand ship in the port! Someone save us!", "Run for your lives! 'Tis a Brigand ship in the port!", "A Brigand ship within the port! For the crown of Skara Brae, save us!"};
   public static final String[] helpGeneral = {"Run!", "Who will help us!", "A threat to the city! Someone save us!", "Run for your lives! 'Tis a threat to the city!", "For the crown of Skara Brae, save us!"};
   public static final String[] helpTrapped = {"I am trapped! Help me!", "What has thee done! I am trapped!", "Free me from this trap!", "I am snared and bleeding! Why has thee done this!"};

   public static final String[] assassinAbout = {"Hide thee well! There is an assassin about!", "Shhhhhh! There is an assassin on the streets!", "Beware! An assassin lurks in the dark!", "Leave while you can! An assassin hunts you here!", "Out there - in the dark: an assassin!", "The assassin targets thee! Run from this place!", "An assassin targets you in this town!", "Assassins target thee - may the crown of SKARA Brae protect you!"};
   public static final String[] werewolfAbout = {"Hide thee well! There is a WEREWOLF about!", "Shhhhhh! One with the Wolfen curse is on the streets!", "Beware! A WEREWOLF lurks in the dark!", "Beware the moors, and stick to the road! A Werewolf is about!", "Out there - in the dark: a WEREWOLF!", "A WEREWOLF is here! Run from this place!", "One with the Wolfen curse is out there!", "A WEREWOLF runs the streets - may the crown of SKARA Brae protect you!"};
   public static final String[] vampireAbout = {"Hide thee well! There is a VAMPYRE about!", "Shhhhhh! One with the Vampyric curse is on the streets!", "Beware! A VAMPYRE lurks in the dark!", "The children of the night! A VAMPYRE is about!", "Out there - in the dark: a VAMPYRE!", "A VAMPYRE is here! Run from this place!", "One with the Vampyric curse is out there!", "A VAMPYRE stalks the streets - may the crown of SKARA Brae protect you!"};

   public static final String[] bountyBribe = {"Please don't slay me! Take VALUE gold, 'tis all I have!", "I know I have done wrong, take VALUE gold as my penance and let me live!", "I want to live! Take VALUE gold to spare me!", "Please take VALUE coins and let me right my wrongs!"};
   public static final String[] bountyBribeTown = {"Please don't slay us! Take VALUE gold, 'tis all we have!", "We are trying to right our wrongs! Take VALUE gold as my penance and let us live!", "We want to live! Take VALUE gold to spare us!", "Please take VALUE coins and let us right my wrongs!"};
   public static final String[] bountyBribeNo = {"I am sorry - I have given thee all I have!", "I know I have done wrong - but I have nothing to give!", "I want to live! But I have no gold to give thee!", "I promise to right my wrongs!"};
   public static final String[] bountyBribeThanks = {"Thank you! I shall undo my wrongdoings!", "By the crown of SKARA Brae, thank you!", "I will mend my ways! Thank you!", "Thank you! May the crown of SKARA Brae guide you!"};
   public static final String[] bountyBribeThanksTown = {"Thank you! We shall try to live responsibly with our curse!", "By the crown of SKARA Brae, thank you!", "We will gain control of our curse! Thank you!", "Thank you! May the crown of SKARA Brae guide you!"};

   public static final String[] mission = {"I have a MISSION for thee if ye shall hear it", "Wilst the brave adventurere take a MISSION", "A MISSION for pay for the brave", "Wilst thou hear me about a MISSION for pay"};
   public static final String[] houseToSell = {"I have a HOUSE to sell to thee", "My HOUSE is for trade, if thee has the gold", "My life calls me elsewhere. I have this HOUSE to sell", "This very HOUSE can be yours if thee has interest"};
   public static final String[] missionPrice = {"Indeed, AMOUNT gold","AMOUNT gold is all I can pay", "'Tis true: AMOUNT gold for this brave task", "AMOUNT gold is a year's pay for me"};
   public static final String[] noMission = {"I have no mission to give.", "My life is in good order.", "Did thou sayeth something about a mission?", "What mission do you speak of?"};
   public static final String[] mainMission = {"<...seek thy NUMBER of Skara Brae...>", "<...seek the red square made of four...>", "<...remember thy visions...the NUMBER...>", "<...SKARA Brae calls thee to the redened square...>"};
   public static final String[] numberResponseNo = {"I have seen a number in my dreams", "A number I feel in my mind, but cannot say nor write", "'Tis strange - I can feel its weight but not say it", "'Tis the number known by CASTLE mages - I know not its name", "'Tis an important number, for you, for us, but I know not why", "I know not about numbers, 'cept average air-speed velocity of an unladen SWALLOW"};
   public static final String[] fiftyResponseNo = {"That number sounds familiar", "I have seen that number...in my dreams", "Thy number rings strange in my ears", "That number...it sound true to me", "I have seen this number, multiplied upon itself in my dreams"};
   public static final String[] horseNo = {"I know not of those beasts - ask the Royal Guard", "Wild horses are a mystery to me, but thy Royal Guard knows", "'Tis the Royal Guard that tames wild horses", "I have seen the Royal Guard tame them in the wild"};
   public static final String[] shipNo = {"Ask thy CITY guards about them, I have not spent time on one", "Some CITY guards use to work the ships", "I know of some CITY guards that have sailed before: ask them", "I have only seen ships from the dock, but some CITY guards are former sailors","The shoppekeep at the Port-town is in the buisness of ships", "Seek the shoppekeep of the Port-town about ships.", "'Tis the Port-town shoppekeep that trades gold for vessels", "Thy Port-town shoppekeep is in ship sales"};
   public static final String[] noText = {"Thy mouth moves, but no sound escapes...", "I know not what you mean with this silence...", "Did thee lose thy voice...", "I did not hear what thee said...", "Speak with a louder tone, please...", "'Twas the quietest sentence I have ever not heard...", "I hear nothing from your voice..."};
   public static final String[] unknown = {"What do you mean by", "I doth not know what you mean by", "What does it mean to", "What is this", "You confuse me with", "I am perplexed by", "I am vexed by", "I am confused by this", "I am confused by", "I do not understand this", "I do not understand", "I do not know this", "I do not know", "'Tis rubbish, this", "'Tis confusing, this", "'Tis unknown to me, this", "I am confused when thee says"};
   public static final String[] unknown2 = {"Hmmmph.", "Hmmmm...", "I see...", "Thou don't say...", "What?", "I do not understand thou.", "I doth not know what you mean to say.", "What by the crown of SKARA Brae do you mean to say?", "I can not help ye with that..."};

   public static final String[] caveInfo = {"The most rare of mountain caves - green with unnatural moss",
                           "A pulsating blue light flickers from the maw of the green cave",
                           "Within the green cave, a door to other worlds beckons the brave",
                           "Portals to other worlds and times await thee in thy green caves",
                           "Thy even days, portals are a gateway across the lands of Cultima",
                           "On odd days, portals send thee to different times and worlds",
                           "Beware! Portals might send thee to the Underworld where DEATH awaits"  
                           };
   public static final String[] luteDestiny = {"Nigel's Lute Of Destiny: 'tis said that songs played on it can CHARM most two-legged beasts", "Nigel's Lute Of Destiny is able to CHARM even thy TROLL", "Nigel's sound is soo sweet, a charging cyclops t'would take time to listen", "Nigel's Lute Of Destiny is the most coveted of instruments: can CHARM even thy giants"};
   public static final String[] wellsPuzzle = {"The Puzzle-Giver of the Three Wells Puzzle rests in the UNDERWORLD", "Thy Three Wells Puzzle of the UNDERWORLD: are there not waters 'round the island", "Perhaps one can trick the Puzzle-Giver of the UNDERWORLD to drink only one poison", "Remebmer, the cure to any poison is a higher poison", "One must think off the island for the Three Wells Puzzle", "What if one filled thy goblet from the waters 'round the island", "Thy UNDERWORLDS Puzzle-Giver will likely draw from Well-Three", "Is it not true that the poisons from Well-Three are the cure to lower poisons"};
   public static final String[] doorsPuzzle = {"The Three Doors Puzzle is given by the Puzzle-Bringer, a cunning Sorcerer", "The Puzzle-Bringer lives in a temple DUNGEON, and offers the Three Doors Challenge", "Thy Three Doors Challenge seems a simple matter of chance, but the wise will know better", "Challengers of strong numerics know where the best chances lie", "There is quite a gap between one-of-three and two-of-three, thy odds that gold thee will see", "The Three Doors Puzzle was first told to me by Daryl Python. Or was it Monty Daryl...maybe Python Hall"};
   public static final String[] towersPuzzle = {"The Three Towers Puzzle is given by the Puzzle-Master, a wise old Mage", "The Puzzle-Master lives in a darkened CAVE, and offers the Three Towers Puzzle", "Hast thou learned of this Puzzle in travels to Hanoi", "Consider first solving a part of the whole puzzle - move a small tower to Tower-Three to get the whole to Tower-Two", "There might be a smaller tower to move on the third day of ten"};
   public static final String[] puzzle = {"Yes...I may have solved the Three Towers Puzzle", "The Three Wells Puzzle is most vexing", "The great Three Doors Puzzle...yes...it is known to me", "I know of the Three Towers Puzzle", "I have heard of this Three Wells Puzzle", "The Three Doors Puzzle is known to me"};
   public static final String[] dungeon = {"Wrought with traps! Take a POISON cure with thee", "A CASTLE may harbor a dungeon underneath", "Seek out temples for dungeon enterances", "Look to thy CASTLE and temples", 
            "Dark and dangerous places", "Beware of traps, and BEAST", "'Tis said that not all walls are sturdy in some", "Some harbor prisoners who yearn to be free"};
 

   //numbers after the "~~" for each item denote the integer value of the NPC it is about (for generating the journal), -1 denotes general knowledge
   public static String[] monsters = {"I have seen a beast wander through the gates of this very town",    
      "Be well rested before fighting beasts - a bed gives the best rest ~~MISC",
      "Dare not rest in a coffin, lest you tempt the Vampyric curse ~~MISC",
      "Set camp at night: thy fire will keep most beasts away ~~MISC",
      "Creatures of more deadly variety come out at night ~~MISC", 
      "A campfire can be impossible to light under a hard rain. Check thy WEATHER before a long journey ~~MISC", 
      "When fighting beasts, seek higher ground to land shots with greater precision ~~MISC", 
      "Some animals would just as soon leave thee alone, but attack fiercely when frightened~~MISC", 
      "Many beasts are reluctant to enter the waters, even shallow ~~MISC", 
      "The mage's CURE potion is handy in a TRAP laden DUNGEON ~~MISC", 
      "Mind the winds: monsters may smell your position if upwind ~~MISC",
      
      "Did thoust say I look like a beast",
      
      "I have scribed a monster's MANUAL for purchase if you like",
      
      "Thy Goblins - not unlike shortened men, but twisted and green ~~GOBLIN", "One can find devilish Goblins in thy DUNGEON, sneaky creatures they are ~~GOBLIN", "Goblins are known to skulk in the dark, and hide within barrels to sneak up on thee ~~GOBLIN", "To a well armed adventurer, one Goblin is an annoyance.  Many Goblins are trouble ~~GOBLIN", "Thy Goblins are fearful of light, even that cast from a SPELL ~~GOBLIN",
      
      "The Bugbear is a ghastly, haired beast fully toothed and clawed, akin to man, but much more animal ~~BUGBEAR", "The open air of the wilderness is preferred by Bugbears - 'tis rare to find them in shelter ~~BUGBEAR", "Bugbears have a powerful fanged bite, and come out in greater numbers in the dark of night ~~BUGBEAR", "A Bugbear may be swayed by tossing it a RATION ~~BUGBEAR", "The forests are prowled by thy Bugbear King, who wields a mighty halberd that casts light into darkness ~~BUGBEAR",
      "The mighty Cyclopse carries a crushing club ~~CYCLOPS", "The one-eyed beast roams the plains and forests, but live in darkened CAVE ~~CYCLOPS", "The hulking Cyclopse, clumsy and dim, but powerful, is quite afraid of water ~~CYCLOPS", "'Tis said the beastly, one-eyed man can be charmed by music ~~CYCLOPS", "While powerful, thy Cyclopse is not terribly graceful, and worse so when rain falls ~~CYCLOPS", "The terrible armies of BRIGAND and TROLL often employ a powerful Cyclopse in their ranks ~~CYCLOPS",
      "Fearsome Demons are said to haunt thy temples erected for evil and thy darkened DUNGEON ~~DEMON", "A Demon's claw can lay a curse on thee ~~DEMON", "Demons are not bothered by flame, but dislike the cold ~~DEMON", "'Tis best to carry a Pentangle if thee must battle grim Demons ~~DEMON", "Thy Demons are of high mind, and will not be fooled by charms, or even invisibility ~~DEMON", "Demons must be close to attack, and can teleport closer to thee in a flash of fire ~~DEMON", "If ye encounter a demon that speaks, dare not take the challenge to a game of Swine, lest thee lose thy life ~~DEMON", "Even powerful Demons answer to a greater Demon King, and possibly more ~~DEMON", "'Tis said the Demon King carries a flaming sword, and can summon demonic minions ~~DEMON", "Legend tells of a Demon King that can summon an evil mimic of its challenger ~~DEMON", "Thy Demons are as vile as they come, but they have strange ties to the Great SKARA Brae ~~DEMON",
      
      "A feast? Why yes, I am quite hungry, thank you",
      "You can purchase all this knowledge at once with my monster's MANUAL",
      
      "Best be not tangle with Dragons: they are swift and mighty ~~DRAGON", "With thy Dragon, stay out of line with its fire, run serpentine, and seek shelter with haste ~~DRAGON", "One can hide from high-flying Dragons in deep forests, or seek refuge in shallow water ~~DRAGON", "While Dragons prefer open air, they have been known to nest in a massive CAVE ~~DRAGON", "The prized Dragon's EGG is said to have magic powers, perhaps if eaten ~~DRAGON", "You might hear the shrieking call of the Dragon before seeing it - if so, seek cover ~~DRAGON", "We have never seen a Dragon in a driving rain ~~DRAGON", "'Tis said Dragons wince from the cold ~~DRAGON", "The city Ironsmith can CRAFT a Dragon's scales to armor: totally resistant to flame ~~DRAGON", "'Tis whispered that even the mighty Dragons have a larger queen that rules them all ~~DRAGON", "Nobody has lived to tell of encountering Dragon royalty, but with charred remains ~~DRAGON",
      "Ethereal Spectres are the wispy remains of adventurer's past ~~GHOST", "Spectres look more frightening than they are dangerous ~~GHOST", "The ghostly Spectres are sometimes seen in thy temples and darkened DUNGEON ~~GHOST", "A swift strike with a staff or sword will cast a Spectre back to its ethereal plane, but arrows pass right through ~~GHOST", "Spectres make no sound and cast no warmth. Those of keen awareness will fail to detect them ~~GHOST", "We can only guess as to why Spectres visit our world, and aim to harm us ~~GHOST", "Some temples may be haunted by vile phantoms - unseen ghosts until they attack ~~GHOST", "'Tis said that phantoms soil the very earth they float over, alerting thee to its presence ~~GHOST", "The Phantom Queen commands the spectres from the ether ~~GHOST", "The very life of the Phantom Queen's victims are consumed by her dreaded Vampyric Axes ~~GHOST",
      "The Colossus is a man with the height of ten, but the mind of a wicked child ~~GIANT", "One swipe of a Colossus' fist can end thee ~~GIANT", "Do not get close enough to the Colossus to fall in its shadow - 'tis too large and dangerous ~~GIANT", "The towering Colossus can flatten forests, but cannot see those hiding within ~~GIANT", "If thee cannot run from a Colossus, attack from range, at least three swords length ~~GIANT",
      
      "What did thou say about my mother..",
      "All this research and more can be yours: we can trade my monster's MANUAL for coin",
      
      "Thy clawed Orcs are likened to beasts and wicked men, but cannot be reasoned with lest you toss a RATION ~~ORC", "A single Orc can be troublesome, but an army of Orcs inspires retreat ~~ORC", "Armed Orcs will not follow thou into shallow water ~~ORC", "An Orc army is sometimes captained by a great Colossus ~~ORC", "'Tis commonly known that Orcs will run with haste at the mere sight of a DOG ~~ORC",
      "The cold dragons of the lake and seas strike fear in all ~~SEAMONSTER", "A Sea serpent can strike thee at a distance with its icy breath ~~SEAMONSTER", "Serpents of seas are mighty enough to swallow thee whole, certainly with thy guard down ~~SEAMONSTER", "Thy terrible Sea serpents do not mind the cold, but rather dislike the flame ~~SEAMONSTER", "Cities protect their ports from sea beasts with the cannon fire from a great sailing SHIP ~~SEAMONSTER", "The dragon of the seas has beautiful scales, 'twould make a strong and light armor ~~SEAMONSTER", "Sea-serpent must be lead to the shallows to collect her scales, lest thee has a SHIP, or flight spell to reach them ~~SEAMONSTER", 
      "Strange magic from ancient temples can cause the dead to rise ~~SKELETON", "The shambling Deadites have mind only enough to walk and grope at thee ~~SKELETON", "The deadite plague may be frightening for the TOWNSFOLK, but they make easy training for thy blade ~~SKELETON", "Deadites roam thy temple and DUNGEON, but mobs of skeletons have been rumored in the plain ~~SKELETON", "Do not dance too close to a Deadite: their bite can sicken thee ~~SKELETON", "Whispered news of late - an entire town fell to the Deadite plague, turning all into murderous ghouls ~~SKELETON", "Those recently risen from the dead have the strength of the living until the stiffness and rot sets in ~~SKELETON",
      
      "I have slain many a great beast in my time...or...at least many a wee beasts...",
      "Did I mention that I have scribed a monster's MANUAL...",
      
      "The tree-like Ropers are gentle and still unless thee gets too close or attacks ~~TREE", "Ropers can grow quite large, with a reach of a spears-length ~~TREE", "Bother not the CAVE growing Ropers, who can easily corner the poor adventure that strikes upon it ~~TREE", "While slow on a dry day, a Roper moves more easily in wet soil - dare not attack them in the rain ~~TREE", "The Roper shares kinship with its hostile cousin under the soil - the Graboid ~~TREE", "Legends tell of the mightiest creature of all these lands, the Hydraclops, a Roper of the seas ~~TREE", "The Hydraclopse 'tis told to be large enough to bring the waters across the lands ~~TREE",
      "There is something strange in the sands - a beast that grabs from below ~~GRABOID", "Thy Sand-grabber is said to descend from the ropers, but 'neath the earth ~~GRABOID", "Sand-grabbers move only through dry grass and loose sand - they cannot pass under wet, rooted or rocky ground ~~GRABOID", "When traveling across desert plains, watch for movement in the sand to avoid thy Grabbers ~~GRABOID", "Thy Sand-grabbers have never been encountered when rain falls, nor at night ~~GRABOID", "'Tis said the hides of digested creatures and treasures of travelers can be found within the gizzards of thy slain Sand-grabber ~~GRABOID", "Tales speak of a mighty adventurer, swallowed whole by a Graboid to cleaved her way out of the beast before being digested alive ~~GRABOID",
      "Massive, trollish men are known to smite travelers caught off-guard in the plains, but live in a torch-lit LAIR ~~TROLL", "Thy Trolls can swing a mighty club ~~TROLL", "Trolls are known to snatch away villagers as prisoners in a darkened, monstrous LAIR ~~TROLL", "Troll armies have been spied marching across the foothills ~~TROLL", "Against the Troll army, 'tis best to move serpentine as to not get cornered ~~TROLL", "Best to engage a Troll army in the rain - thy brutes are quite clumsy on wet ground ~~TROLL", "The Troll army is captained by their fearsome king, massive, who swings the LEGENDARY Banehammer ~~TROLL",                                        
      "Thy local foul are not monsters, they are owned by the villagers ~~CHICKEN", "A foul can make for a fine days RATION ~~CHICKEN", "The village chickens are sometimes killed by DOG ~~CHICKEN", "One learned to train a DOG to stealthily hunt local chicken for her RATIONS ~~CHICKEN",
      
      "Dost thou even slay beasts, brother?",
      "Thy monster's MANUAL is available at all fine retailers. I have one right here for purchase...",
      
      "Village Swine live 'round the town and are quite trusting of strangers, sometimes to a fault ~~PIG", "Swine meat can make for many day's RATION ~~PIG", "If starved, do not strike at thy local swine within sight of the villagers, lest the guards be called ~~PIG", 
      "Thy CAVE bats are swift - sometimes too swift to strike ~~BAT", "Most bats will leave you be unless stricken with the rage sickness ~~BAT", "Bats prefer dark and dampened places, like DUNGEON and CAVE ~~BAT", "Cave bat's eyes are tuned to the dark, and will flee from a light SPELL ~~BAT", "A colossal bat has been written about in legend ~~BAT", "'Tis said the Bat-Queen's bite can cast thy Vampyric curse ~~BAT",
      "The common hare of the plains makes for a good RATION if you are swift enough to catch them ~~RABBIT", "The lady of the CASTLE has been known to train a DOG to hunt rabbits to supplement thy rations ~~RABBIT", "Mind if hunting rabbit, approach from side or downwind to make for an easier catch ~~RABBIT",            
      
      "I am quite fond of roast beast",
      "Thy monster's MANUAL is worth ten times its weight in gold coins",
      
      "The great, antlered elk graze in the plain and hide in the deepened forests ~~ELK", "Hunters prize a felled elk for RATIONS, and the ironsmith might trade for elk hides you cleave ~~ELK", "Hunters clothe in elk hide to get closer to their targets and protection from Winter storms ~~ELK", "Hunt not game with flame nor poison ARMS, 'twill spoil the meat or destroy the fur ~~ELK", "A cornered elk has a fearsome kick that can knock the life out of thee ~~ELK", "Best to hunt great elk from range ~~ELK", "Approach not elk game from upwind: 'twill smell your advance and run ~~ELK",
      "The wild horse of the plains are swift and powerful ~~HORSE", "Thy grazing creatures are not wicked beasts, and 'tis said the Royal Guard knows how to tame them ~~HORSE", "Horse riders do not ride a wild horse into battle - they will discharge their riders if struck upon ~~HORSE", "The Royal Guard on horseback can cover great distances in short time ~~HORSE", "Legend tells of a horned horse that appears when colors arch across the sky ~~HORSE", "The secrets of the Unicorn are known only by the Royal Guard of the Capital ~~HORSE",
      "What about the R.O.U.S.'s ~~RAT","Rodents Of Unusual Size? I don't think they exist ~~RAT",
      
      "I spied a dragon once, made a sound so horrible, it haunts me to this day...",
      "'Tis only 45 coins to trade for my monster's MANUAL",
      
      "Common fish that can be grasped from shallow waters can make for a good RATION ~~FISH", "Take great care when fishing, for the lakes are inhabited by more than slippery trout ~~FISH", "Edible fish have been taken from lakes, seas and even the underground rivers of a great CAVE ~~FISH", 
      "Wade not too long in the waters - beastly fish will thrash at thee ~~SHARK", "Even the water's edge is not safe from sharks - they will thrash at a dragging foot if they can ~~SHARK", "Use a WEAPON of at least a spear's length to hunt the beastly fish of the rivers and seas ~~SHARK", "If thee dares to hunt the beastly fish, lure him to shallow waters so thee can skin its hide and carve meat for RATIONS ~~SHARK",
      "Coiling serpents have been seen many places: forest, temple, CAVE and DUNGEON ~~SNAKE", "Most serpents will pay you no mind if thee keeps good distance ~~SNAKE", "Get not too close to serpents unless thee has a poison's CURE ~~SNAKE", "A serpent EGG are used to make CURE potions, and may even work if eaten in the raw ~~SNAKE", "Thy snakes sleep in winter. 'Tis rare to see them in snow ~~SNAKE", "Serpents the length of twenty men have been told in tales, known to swallow adventurers whole ~~SNAKE","Thy serpents large and small move easily in the rain, much harder to smite ~~SNAKE",
      
      "Beast slaying can bring quite a fortune on a good day, but at the risk of thy very life ~~MISC",
      "We can talk all day, or you can trade gold for my monster's MANUAL",
      
      "Much larger than common house spiders, those of the CAVE range from gentle to insidious ~~SPIDER", "Large CAVE spiders can deliver a poisonous bite, but fear the light ~~SPIDER", "Many spiders will leave thee alone unless attacked ~~SPIDER", "When venturing into a spider-nested CAVE, 'tis best to carry a poison's CURE ~~SPIDER", "The spiders answer to their colossal queen, massive and feared across the land ~~SPIDER",
      "Great squids are intelligent, powerful and not always knavish ~~SQUID", "The giant squids can reach a spear's length with their many arms ~~SQUID", "The most rare of creatures is the gargantuan squid, but not all are evil ~~SQUID",  "'Tis said the legendary garganuan squid holds in its gizzard the most powerful magic pearl in creation ~~SQUID",                  
      "The wild wolfen have a powerful bite, and will swiftly chase down any fool that dares attack ~~WOLF", "A lone wolfen is common in the brush and plain, but packs have been seen from the forests to the temples ~~WOLF", "Some wolfen of the forest will attack when hungry or frightened, but most will leave you be ~~WOLF", "Wearing a wolfen hide might allow you to evade them and shield thee from blowing Winter snow ~~WOLF", "Thy local ironsmith will TRADE you wolf pelts for coin ~~WOLF", "Dare not attack the wolfen when clothed in its hide, lest you tempt the Wolfen curse ~~WOLF", "If hunted by the Wolfen pack, toss a RATION to end their pursuit of thee ~~WOLF", "'Tis said that the wolfen pack is ruled by a much larger queen ~~WOLF", "A hungry wolf will sight thee faster if thee is upwind of the beast ~~WOLF", "They who travel with DOG will find little trouble from Wolfen ~~WOLF",
      "No natural land creature is more powerful than the bear of the plains and forests ~~BEAR", "Great bears sleep the Winter in darkened CAVE ~~BEAR", "Adorning a bear hide will allow thee to walk among them, and protect thee from the blowing snows of Winter ~~BEAR", "Bears be not wicked, but may charge relentlessly if hungry or threatened ~~BEAR", "'Tis possible to end a hungry bear's pursuit by tossing a RATION at them ~~BEAR", "A charging bear can follow thee on land and into the water ~~BEAR", "A bear will leave thee be if with a hunting DOG ~~BEAR",
      
      "The most strange of beasts, the Rustcreature of the CAVE is known to wander into dungeons ~~RUSTCREATURE", "Rustcreatures are harmless to the living, but will dissolve anything made of metal and consume its remnants ~~RUSTCREATURE", "Once can lose their most prized ARMS or gold to a Rustcreature ~~RUSTCREATURE", "Thy Rustcreature will consume metal relentlessly if it smells it close, even treasure chests ~~RUSTCREATURE", "Of some irony, thy Rustcreature doth not mind the water, and will chase iron-clad adventurers into the shallows ~~RUSTCREATURE", "'Tis known the Rustcreature can vary greatly in size, from wee to not-so-wee ~~RUSTCREATURE", "From every DUNGEON to every DRAGON, the Rustcreature is clearly the most annoying in the land ~~RUSTCREATURE",
      
      "This most important research is also in my monster's MANUAL, available for trade",
      
      "Common dogs can be found in cities and villages ~~DOG", "The CITY dogs have varied personalities, but an aggressive one can be charmed by a clever mage ~~DOG", "The lady of the CASTLE knows of training dogs ~~DOG", "'Tis said a trained dog can help with hunting, guarding camps, chasing away Orcs and protecting against CITY thieves ~~DOG",                    
      "Stay clear of the great, twisting vortex of the land - they cannot be stopped ~~TORNADO",  "A towering funnel of vicious winds can tear the very trees from their roots, and flatten even a great Colossus ~~TORNADO", "A vortex from the sky once ravaged this town.  If you spy one, stand clear. ~~TORNADO", "A dexterous adventurer can navigate around a vortex to avoid combat with a pursuing monster ~~TORNADO", 
      "A water vortex is a spinning current that has been the end of many reckless sailors ~~WHIRLPOOL", "The swirling chaos of the water vortex 'tis mindless, and cannot be harmed by any weaponry nor MAGIC ~~WHIRLPOOL", "A water vortex was seen to pull the water from a deep channel, making it able to cross ~~WHIRLPOOL",
      
      "Beware stone monoliths of thy temple and DUNGEON. Strange magic strikes if thee walks between them. ~~MISC",
      "Take a HAMMER to a stone monolith to break its magic chain, or block it with stone ~~MISC", 
      "Thy monster's MANUAL is the definitive volume on beasts, available for trade right now",
      
      "Green mass not solid nor liquid, Slimes think little, but react ~~SLIME", "'Tis a strange creature, these Slimes that inhabit the dungeons and caves ~~SLIME", "The touch of a Slime burns as if by fire, but without heat nor flame ~~SLIME", "Slimes glide so silently across the ground, even those with high awareness may not detect them until seen ~~SLIME", "Be careful around Slimes. They will split in two if struck by most arms, overwhelming thee ~~SLIME", "Only fire of flame and frost of magic can harm the Slimes, all others and they double in number ~~SLIME",
      
      "The Brigands are men, turned evil and banded together to a life of debauchery and violence ~~BRIGAND_SWORD", "Thy Brigands roam everywhere: the plain, forest, sacred temple, DUNGEON and mountain LAIR ~~BRIGAND_SWORD", "A variety of weapons are wielded by Brigands, but they prefer to remain swift by suiting in leather armor ~~BRIGAND_SWORD", "Knavish Brigands are known to steal away TOWNSFOLK, and harbor them in dark prisons ~~BRIGAND_SWORD", "The armies of Brigands have been on the march: beware ~~BRIGAND_SWORD", "Brutish Brigand Captains ride upon powerful horses and wield a greate axe ~~BRIGAND_SWORD", "Beware the Brigand army's deadly Generals: The Brigand King and his three assassins ~~BRIGAND_SWORD", "The Dread Brigand Roberts plunders the seas and rivers, and wears a blackened mask ~~BRIGAND_SWORD", "They who adorn the black mask of the Dread Brigand Roberts might pass through Brigand territory unscathed ~~BRIGAND_SWORD",
      "Not all mages life a life of peace: some turn to the dark arts ~~SORCERER", "With great knowledge of the magic realm, the minds of some mages have collapsed to evil, and become Sorcerers ~~SORCERER", "Some temples of evil attract wicked Sorcerers, where they study and focus their demonic arts ~~SORCERER", "Sorcerers are known to venture into thy DUNGEON in search of magic artifacts, or commune with Demons ~~SORCERER", "Advance on a Sorcerer quickly - their command of lightning can strike at range ~~SORCERER", "'Tis known of a monk sorcerer, agile, with a deadly long-staff of piercing cold ~~SORCERER", "Some Sorcerers carry mannastones - prized among the mages ~~SORCERER", "A highly skilled Sorcerer can raise the dead on command, or worse, summon demons ~~SORCERER", "The most feared of Sorcerer Royalty, the Liche King, is said to wield the darkest of magics ~~SORCERER",                    
      "Little is known about the Magma-mother, since few get close enough to report on her features ~~MAGMA", "Living within thy fissures of molten rock, the Magma-mother controls fire which she can launch from a distance of four spear lengths ~~MAGMA", "Best carve a wide path in the rare event that you see the Magma-mother ~~MAGMA", "Dare not attack the Magma-mother with flame, lest you increase her strength ~~MAGMA", "'Tis logical that she'd wilt in a strong rain, but not yet tested by any brave mage that knows the Tempest SPELL ~~MAGMA", 
      "The shores of port cities are protected by great sailing ships, which house powerful cannons ~~GREATSHIP", "Stay out of line of the great ship's cannons when advancing position ~~GREATSHIP", "All creatures, save flying Dragons, will quickly wilt from cannon fire ~~GREATSHIP", "Thy great ship has a strong wooden hull, but travels more slowly than the lighter Brigand ships ~~GREATSHIP", "Ask thy CITY guards about ships - some have worked on them prior to their post ~~GREATSHIP",
      "Thy Brigands carry their villainy to the seas in swift, cannon wielding ships ~~BRIGANDSHIP", "'Tis said that a great hero once overtook a brigand's ship, all on her own ~~BRIGANDSHIP", "The Brigand's ship moves quickly, but has a lighter armored hull ~~BRIGANDSHIP", "Thy Brigand's ship's hull is made of earthen wood - might be wilten to fire ~~BRIGANDSHIP", "Seafaring Brigands prize the magic holdall to store the goods they steal from villages ~~BRIGANDSHIP", "Some port cities and villages have been plagued by the feared Dread Brigand Roberts ~~BRIGANDSHIP", "Drunken Brigands are known to brag of maps to great treasures on their SHIP ~~BRIGANDSHIP",
      
      "Few know of the bog-dwelling Triffids, massive flowers that move as if animal ~~TRIFFID","�Tis said Triffids can only travel in swamp and are noble defenders of the wetland ~~TRIFFID"," Thy threatened Triffid can lash out with coiling branches and choke thee with a cloud of spore ~~TRIFFID","The Triffid's poison will confuse its victim, and rob thee of balance as if sullied ~~TRIFFID",
      
      "Most rare of the dungeon, only strange tales speak of a floating monstrosity with many eyes ~~BEHOLDER", "Beware, thy Beholders are said to command the most powerful of magics ~~BEHOLDER",
      
      "If thee sees a darkness approaching thee, run! 'Tis Death herself! There is no battle to be fought, only retreat ~~DEATH", "Death can only be evaded! Thou cannot battle her, lest ye be Killed by Death", "If Death comes for thee, run and hide! A CITY, CAVE, SHIP... ~~DEATH", "Tis said that only a crowned agent of SKARA Brae herself can challenge Death ~~DEATH", "In tales of lore, SKARA Brae wounded Death with a silver-forged royal sceptre, charged with a sacred pearl ~~DEATH",      
      "Hmmmm...let thee consult thy monster's MANUAL..."};

   public static boolean isMonster(String typed)
   {
      typed = typed.toUpperCase();
      return (typed.contains("GOBLIN") || typed.contains("BUGBEAR") || typed.contains("CYCLOPS") || typed.contains("DEMON") || typed.contains("DRAGON") || typed.contains("GHOST") || typed.contains("GIANT") || typed.contains("ORC") || typed.contains("SEAMONSTER") || typed.contains("SKELETON") || typed.contains("TRIFFID") ||
              typed.contains("TREE") || typed.contains("GRABOID") || typed.contains("TROLL") || typed.contains("CHICKEN") || typed.contains("PIG") || typed.contains("BAT") || typed.contains("RABBIT") || typed.contains("ELK") || typed.contains("HORSE") || typed.contains("RAT") || typed.contains("FISH") ||
              typed.contains("SHARK") || typed.contains("SNAKE") || typed.contains("SPIDER") || typed.contains("SQUID") || (typed.contains("WOLF") && !typed.contains("WERE")) || typed.contains("BEAR") || typed.contains("RUSTCREATURE") || typed.contains("DOG") || typed.contains("TORNADO") || typed.contains("WHIRLPOOL") ||
              typed.contains("BRIGAND") || typed.contains("SORCERER") || typed.contains("MAGMA") || typed.contains("GREATSHIP") || typed.contains("BRIGANDSHIP") || typed.contains("DEATH") || typed.contains("SLIME") || typed.contains("BEHOLDER")); 
   }

//numbers after the "~~" for each item denote the integer value of the NPC it is about (for generating the journal), -1 denotes general knowledge
   public static String[] tavernTown = {"Harm not these townsfolk, lest ye build a bounty that casts the assassins on thee ~~MISC", 
        "Some mark your MAP if asked of their HOME ~~MISC", 
        "T'would be wise to fill up on our local baker's RATIONS before venturing into the unknown ~~MISC", 
        
        "Some of the downtrodden are worth helping with RATIONS or coin ~~BEGGER", "Trust not a beggar that will not share their name ~~BEGGER", "Helping the downtrodden to their feet again, and they might help with thy MAP for free ~~BEGGER",
        "The ratty children: they are of little use ~~CHILD", "Some townsfolk have gone missing, parents who leave behind frightened children in need of help ~~CHILD",
        "Guards are experienced with all types of ARMS, but specialize in sword and spear ~~GUARD_SPEAR", "Take not that which is other's, including thy village swine and fowl, lest the guards be called ~~GUARD_SPEAR", "Guards of ill-repute can be bribed with coin to leave their posts, or end pursuit ~~GUARD_SPEAR", "Watch thy tongue around guards. Some are short of temper and patience ~~GUARD_SPEAR","Some CITY guards have worked on ships - ask about them to learn more on how to handle thy Brigand SHIP ~~GUARD_SPEAR", "Guards will look for volunteers to fill their ranks against monster hordes - it pays well ~~GUARD_SPEAR", "You can find some guards to TRAIN thy body ~~GUARD_SPEAR", "Some guards TRAIN with assassins. They can clear thy name off the bounty list for a price ~~GUARD_SPEAR",
        
        "Are you going to PAY for a drink", 
        
        "The CITY jesters are always working their LOCKPICK 'round here ~~JESTER", "A fellow thief will trade their LOCKPICK with a jester for gold ~~JESTER", "Among the most cunning jesters is one that is said to have a magic LOCKPICK ~~JESTER", "The CASTLE jesters are the most agile in the land ~~JESTER", "Jesters in some places can TRAIN you to sharpen thy agility ~~JESTER", "A jester might PUZZLE thee with riddles: solving them will build thy character ~~JESTER", "Knavish jesters will try to steal thy goods: beware those that shadow your movements ~~JESTER", "Put thy WEAPON on guard if being shadowed by a knavish jester: they will be less sure about stealing thy goods ~~JESTER", "The lady of the CASTLE has trained a DOG to chase down thieving jesters ~~JESTER","'Twas a thieving jester overheard to boast about stealing chests in a thunderstorm, its distractions making the theft easier ~~JESTER", "The less agile jester thieves will more easily pick the pockets of those slumbering in bed ~~JESTER",
        "The lute player's fine music can chase a curse away ~~LUTE",  "Seek a lute player if suffering a demon's curse ~~LUTE", "Thy lute players are often whispering about the Lute of Destiny, and aim to seek it out ~~LUTE", "The musicians converse about the location of the lost pages of the Songs of SKARA Brae ~~LUTE", "'Tis said the lost pages scribe music that forges magic ~~LUTE", "Legend foretells of a musician with both the Lute of Destiny and all the pages from the Songs of SKARA Brae ~~LUTE", "To play the magic songs on the magic lute would make thee unstoppable ~~LUTE",
        "The local Ironsmith will TRADE animal pelts for gold ~~MAN", "Commoners may mark your MAP of the surrounding territories for five coins ~~MAN", "'Tis a man of the CASTLE that is said to have been cured of the vampyric curse ~~MAN", "Ask the Rations shoppe Butcher about their MAP skills...'tis good advice to be acquired there ~~MAN",
        
        "Put thy WEAPON away before approaching the good townsfolk ~~MISC", 
        "'Tis said that nighttime in the town is when assassins prowl the dark corners ~~MISC", 
        "To strike down folk of divine nature will cast a curse on thee ~~MISC", 
        
        "Adventurers might show thee the monster LAIR on thy MAP ~~SWORD", "The local adventurers can tell you all about the beasts they encounter ~~SWORD", "Knowledge is valuable if traveling these lands.  Ask adventurers about the beasts ~~SWORD", "Many adventurers catalog the monsters they encounter in a manual, available for TRADE ~~SWORD", "'Tis best not to pickpocket an adventurer - they are seasoned with various armaments ~~SWORD",
        "Our local mages make a fine poison CURE for those that stumble into a DUNGEON trap, or run afoul a viper ~~WISE", "The mages of magic shoppes can CURE your ills, or teach you a SPELL for coin ~~WISE", "'Tis said mages in some places can TRAIN you to fortify thy mind ~~WISE", "Thy local wisemen can predict the very rain - ask them about WEATHER before a long journey ~~WISE", "Some Mages can see into the very FUTURE, and will tell you of battles to come when asked ~~WISE", "A poor wiseman revived with coin or rations might teach you for a SPELL ~~WISE", "I hear the local mages mumble something about a NUMBER ~~WISE", "Mages often travel to distant shrines and temples ~~WISE", "For help finding your way to cities, ask temple visitors and mages about their HOME and they will augment thy MAP ~~WISE",
        "The women will mark thy MAP of the town for no more than five coins ~~WOMAN", "I have seen the town DOG respond to the commands of the lady of the CASTLE ~~WOMAN", "There is said a woman at the temple who knows quite well the WOLFEN curses",
        
        "There is one that left the village to live as a hermit, and is known to master awareness of the world around ~~MISC",
        "'Twas a man from a far CITY that claimed to arrive here from a strange, green CAVE ~~MISC", 
        "One that is charmed or of the highest stature can get a discount from the SHOPPES ~~MISC", 
        
        "The Royal Guards carry a fine sword and shield - and are famous for taming wild HORSE ~~ROYALGUARD", "Step not on the toes of a Royal Guard: they are mighty in battle ~~ROYALGUARD", "If drafted to defend a CITY against a BRIGAND army, 'tis an honor if you fight alongside a Royal Guard ~~ROYALGUARD", "The Royal Guard watches closely any who approach the King. Do not unsheathe thy WEAPON near royalty ~~ROYALGUARD", "Thy Royal Guard's sword is forged with silver, the WOLFEN slayer ~~ROYALGUARD", "Any Royal Guard will be impressed enough to talk to thee with a royal sceptre in thy hands ~~ROYALGUARD",
        
        "'Tis but one COIN for a drink",             
        
        "Be sure to pay the Taxman when approached, lest the guards be called, thee soil thy reputation and build a bounty ~~TAXMAN", "Taxes for most towns are due each season, ten percent thy carried gold ~~TAXMAN", "Own a home and thy quarterly property tax will pay for city services - twenty gold per season ~~TAXMAN", "The Taxman collects when the sun shines, rarely out in the rain, so precious is he ~~TAXMAN", "Help the Taxman collect on debters and thy own taxes might be overlooked for the season ~~TAXMAN", "Thy swiftness has graced thy Taxman, built from chasing down those with debt ~~TAXMAN", "When thy Taxman is on march, mind his direction so thee can evade his sight ~~TAXMAN",
        
        "Royalty will pay in gold for dangerous duties, but some common folk might have some work for pay as well ~~KING", "Thy royalty carries a fine staff, 'tis said to have magic qualities ~~KING", "Do not approach Royalty with thy WEAPON in hand, nor use cursed words - well trained guards are sensitive to any threat ~~KING", "Dare not threaten Royalty of the CASTLE, lest you face the guards, and if you survive, assassins ~~KING", "The Vile Duke of distant lands is known to send adventurers on the most wicked of tasks ~~KING", "To carry a royal staff is to be given an audience with any Royalty, regardless of reputation ~~KING", "The King of all Cultima lives in the CASTLE of the capital ~~KING", "King Garriott knows all of Cultima, very wise is he ~~KING",
        
        "Don't forget to tip thee friendly barkeep!", 
        
        "Some shoppekeeps offers ODD items on some days ~~MISC",
        "Protect these fine folk: sometimes a beast wanders through the gates ~~MISC",
        
        "Did thee come here to talk, or come here to DRINK"};
        
   public static boolean isTownsfolk(String typed)
   {
      typed = typed.toUpperCase();
      return (typed.contains("BEGGER") || typed.contains("CHILD") || typed.contains("GUARD") || typed.contains("JESTER") || typed.contains("LUTE") || typed.contains("MAN") || 
              typed.contains("WOMAN") || typed.contains("KING") || typed.contains("TAXMAN"));
   }      
        

//numbers after the "~~" for each item denote the integer value (Player.imageIndex) of the weapon it is about (for generating the journal), 0 for armor, -1 for general knowledge
   public static String[] armsInfo = {"The torch is essential for exploring mine and CAVE to cast light on darkened areas ~~TORCH", "The torch does not offer thee much striking power or defense, but can alert you to foes that advance from the shadows ~~TORCH", "Some creatures are bothered by flame, and a good swipe with a torch may inspire retreat ~~TORCH", "Take care with the torch when close to materials that take to flame - wood, straw, bedding, lest you start a terrible fire ~~TORCH", "Thy torch will offer no light in a driving rain, so do not travel on a stormy night ~~TORCH", "A strong warrior can wield the rare Toothed-torch, which strikes as a mace, but lights with flame ~~TORCH",
      
      "A bow is excellent for those blessed with agility ~~BOW", "A bows range can keep enemies at bay, but offers poor defense against blades, claws, teeth and HAMMER ~~BOW", "A bow is easily knocked away by a foe within a swords length ~~BOW", "If a meleed enemy closes range, put thy bow away and fend them off with a good blade ~~BOW", "A longbow grants additional range over the stock bow ~~BOW", "'Tis said my longbow can send an arrow over a hill and strike an unseen target on the other side ~~BOW", "A falling rain can decrease one's accuracy with a bow ~~BOW", "Send not an arrow into flame - 'twill only fuel the fire ~~BOW", "Some arrows can be fitted with flame. I have sold one on a particular ODD day ~~BOW", "The fire from the flamebow can be extinguished and less effective in a driving rain ~~BOW", "If hunting game for pelts, use not arrows of flame. Thy fire will destroy the prized fur ~~BOW", "Archers of all sorts seek to augment their CRAFT with the Bow-bracer and Swiftquill ~~BOW",
      
      "I can TRADE you a fine bow for gold",
      
      "Daggers can strike quickly, but one must be in very close quarters ~~DAGGER", "A poison dagger is handy for the swift: strike, retreat and wait ~~DAGGER", "If hunting for RATIONS, use not a poison WEAPON ~~DAGGER", "'Tis said the Viper-assassin has one of five dreaded magic daggers ~~DAGGER", "The Souldagger is rumored to possess its victim, causing them to attack their own kind ~~DAGGER", "The souldagger's blade cannot harm a victim whose essence is captured ~~DAGGER", "The vile Banedagger curses its victim as it strikes, and can send commoners running by the sight of it ~~DAGGER", "The glowing Magmadagger burns as it hits its victim, but is less effective in the rain ~~DAGGER", "The frigid Frostdagger can freeze its target, and with greater effect in the rain ~~DAGGER",
      "A sword in each grip can slash at foes faster than one ~~DUAL", "With an advantage of speed, thy short swords hit not with the same damage as a longsword ~~DUAL", "The Briggand King is swift and mighty enough to carry dual swords ~~DUAL",
      "A hammer is a fine WEAPON for a strong adventurer ~~HAMMER", "Hammers are good for mining, smashing foes, or breaking your own path ~~HAMMER", "A spiked-hammer carries the same weight to move stone, but does more damage to flesh ~~HAMMER", "Do not carelessly break CITY walls with thy hammer, lest the guards be called for vandalism ~~HAMMER", "If thee must move a CITY wall with a hammer, the sound of the storm can cover thy activities ~~HAMMER", "The Exotic-hammer is quite rare, forged from a strange metal that fell from the skies ~~HAMMER", "The Troll king wields the LEGENDARY Banehammer ~~HAMMER", "The Banehammer strikes fear among the common who see it in hand and can lay a curse on its foes ~~HAMMER", "When mining, dare not strike a wooden support with thy hammer, lest the mine ceiling comes down upon thee ~~HAMMER",
      
      "Wilt thou TRADE gold for one of these fine weapons",
      
      "Thy monks can strike quickly and at range of two arms lengths with a swift longstaff ~~LONGSTAFF", "One must be agile to wield a longstaff: keeps advancing enemies at length whilst you do harm upon them ~~LONGSTAFF", "The king's sceptre is a thing of beauty: can make one more adept when weilded ~~LONGSTAFF", "Any who wield a royal sceptre will be granted favor by royalty, regardless of reputation ~~LONGSTAFF", "A monk of stark evil is known to travel with sorcerers, and carry a magic long-staff of paralyzing cold ~~LONGSTAFF", "The fearsome Ice-staff casts a cold that can extinguish a raging fire ~~LONGSTAFF",
      "The sword or sabre is a fine WEAPON for a strong and dexterous adventurer ~~SABER", "Blessed swords are highly prized: I have seen them on particular ODD days ~~SABER", "I have head the mighty demon king carries a flaming sword ~~SABER", "A flame-sword is less effective in a heavy rain ~~SABER", "Train thy might to wield a sword, and block other arms blows ~~SABER",
      "A spear has a striking range of two arm's length - good for keeping enemies at bay ~~SPEAR", "One can inflict greater damage with a fine halberd ~~SPEAR", "The Bright Halberd is a LEGENDARY weapon: crescent shaped blades that hold the moon's light ~~SPEAR", "'Tis said the Bright Halberd is carried by the fearsome Bugbear King ~~SPEAR", 
      "Thy two-handed axe is a mighty WEAPON for a strong warrior ~~AXE", "The great axe can damage foes more than thy HAMMER, but less than a great sword ~~AXE", "The axe's favor comes with its ability to break down wooden doors ~~AXE", "The axe's strike on trapped wooden doors can often disable the snare ~~AXE", "A strange, reflective metal is used to forge the coveted Mirrored axe ~~AXE", "The Mirrored Axe has a surface that can reflect back magic attacks upon their casters ~~AXE", 
      "Single hand axe's, one for each arm, is a versatile WEAPON for an agile body ~~DUALAXE", "The dual-axe's strike quickly, and more potent than a dagger ~~DUALAXE", "Repeated strikes with dual-axes will fell a wooden door ~~DUALAXE", "We sell not the Vampyric Axes, which are forged with dark magic ~~DUALAXE", "What life the Vampyric Axes strike from its victim is ingested by the wielder ~~DUALAXE", "Only the sightless Phantom Queen is known to hold the vile Vampyric Axes ~~DUALAXE", 
      
      "This information is free, but I am happy to TRADE steel for gold", 
      
      "Thy needeth a staff to hold spells that are directed at targets ~~STAFF", "The mage's staff has nice range, but is more made for holding spells and unleashing magic ~~STAFF", "Once a magic staff picks thee, thy soul is bound to it for life: it cannot be discarded ~~STAFF", "If a mage falls, the staff will break with their body ~~STAFF", "A staff in the hands of a potent mage can strike a spell that would make the Great SKARA Brae proud ~~STAFF",
      "Thy short sword and buckler - 'tis great for an adventurer that plans to get close to thy enemies ~~SWORDSHIELD", "The short sword doth not hit with quite the same fierceness of its two-handed cousin ~~SWORDSHIELD", "It is the combination of sword and buckler that helps protect thee in close combat ~~SWORDSHIELD", "With a sword in one grip, shield in another, one can still strike with precision and guard against attack ~~SWORDSHIELD", "'Tis said the king's Royal Guard is a HORSE tamer, and carries a finely forged sword and shield ~~SWORDSHIELD", "One who carries a Royal weapon will impress commoners, who will be more apt to help ~~SWORDSHIELD", "The short sword and mirrorshield is prized among warriors ~~SWORDSHIELD", "Mirrorshields are made of a strange metal that reflects lightning attacks ~~SWORDSHIELD", "Poor be the sorcerer who casts a lightning SPELL on one with a mirrorshield, be it reflect back upon them ~~SWORDSHIELD", "The coveted sword and mirrorshield is only sold on odd days of sun",
      "Thy crossbow has an accurate distance of almost three spear's length, and longer with a Bow-bracer ~~CROSSBOW", "Cumbersome crossbows are easily knocked away by foes at swords length, so keep thy distance ~~CROSSBOW", "While 'tis not as far reaching as a hunter's bow, thy crossbow throws a heavier bolt ~~CROSSBOW", "One must be agile and strong to wield a crossbow ~~CROSSBOW", "The crossbow is a favored weapon of enforcer and BRIGAND ~~CROSSBOW",  "Among the deadliest of arms, the Poison-Boltcaster is an assassin's tool not sold by any SHOPPE... ~~CROSSBOW", "<but if thee has strong interests in the poison-boltcaster and good gold, see me on the seventh day of ten> ~~CROSSBOW", "Thy wicked Bane Boltcaster will curse its target ~~CROSSBOW", "Thy commoners will run in fear at the mere sight of the Bane Boltcaster ~~CROSSBOW", "Bane Boltcasters dare not be sold in SHOPPES, but may be carried by Dark Enforcers ~~CROSSBOW", "Little is known about the Soul Crossbow, but that it is carried by Dark Enforcers ~~CROSSBOW",
      "Come by on the 11th day to hear some fine music ~~LUTE", "Thy local lute players know a song that can purge a demon's curse away ~~LUTE", "There is a Lute forged with magic that can charm many man-like beasts to passivity ~~LUTE", "Thy magic Lute will draw common folk from their stance to hear its sweet song ~~LUTE", "Combined with the magic Songs of SKARA Brae, guards can be moved from their posts to dance ~~LUTE", "A musician with both thy magic lute and magic song pages can even charm vile demons ~~LUTE",
      
      "Your mouth is full of questions.  If thy pockets are full of gold, let us TRADE",				
      
      "If thy WEAPON requires close quarters, sheathe thyself in armor ~~ARMOR", 
      "The heavier the armor, the more it protects thee, but can be cumbersome ~~ARMOR", 
      "Scalemail provides a nice balance of protection and mobility ~~ARMOR", 
      "Metalic armor offers no protection from a MAGIC lightning attack, and can enhance the damage ~~ARMOR", 
      "Clothe in the hide of beasts to walk among them, but 'twill provide little protection from attack and invite flame ~~ARMOR", 
      "Animal pelts will warm thee in the driving Winter storms ~~ARMOR",
      "Donning blessed armor can protect thee against curses and impress the gods to show thee favor ~~ARMOR",
      "See the city Ironsmith about CRAFTING armor from the scales of great beasts - DRAGON and Seaserpent ~~ARMOR",
      "DRAGON scale is impervious to flame, and Seaserpent scale is resistant to cold ~~ARMOR", 
                                            
      "You know plenty about arms - now lets TRADE gold for blade",
      
      "Put thy WEAPON on guard before battle: a single strike while defenseless can end thee ~~MISC",
      "Put away thy WEAPON before entering town or CASTLE - 'tis threatening ~~MISC",
      "My weapons work best when rested - sleeping in a bed or camping with a DOG on watch makes for better rest ~~MISC",
      "This ironsmith does TRADE in prized pelts: elk, wolfen, snake, even sharkskin is useful ~~MISC", 
      "When fighting beasts, seek higher ground to land ranged shots with greater precision ~~MISC",
      "You can find some guards and jesters to TRAIN thy body and sharpen thy agility ~~MISC", 
      "Dare thee not strike a teacher of training, lest you incur the wrath of SKARA Brae's agents ~~MISC",
      "Thy flamed and bright weapons will light a dark CAVE ~~MISC",
      "There are many ways to move a locked door: HAMMER, LOCKPICK, SPELL, or if wooden, AXE or fire ~~MISC",
      "Buy a home to store thy weapons, and upgrade them with MAGIC stones and items ~~MISC",
      "Some may forge a LEGENDARY weapon that is more likely to find its target and score a critical hit ~~MISC",
      
      "A warrior who wears an Iron-bracer can deal considerable damage without arms in hand ~~GLOVE",
      "Thy Iron-bracer will also protect an adventurer as an additional piece of armor ~~GLOVE",
      "There is a striking glove made with iron claws rising from its knuckles  ~~GLOVE",
      "A Clawed-glove allows a warrior to strike unarmed with the power of a taloned beast ~~GLOVE",
      "The finest archers seek out a fine Bow-bracer to augment thy bowcraft ~~GLOVE",
      "With a Bow-bracer, an archer gets extended range and can recover more arrows from their targets ~~GLOVE",
      "A master assassin's tool, the Viperglove can quietly poison a victim with the shake of the hand in greetings ~~GLOVE",
      "Beware any that might sell a Viperglove. Ownership of shuch a fiendish weapon speaks of treachery and death ~~GLOVE",
      "A wicked jester thief would not use a Viperglove, but might be able to acquire one ~~GLOVE",
      "When home be sure to stow a Viperglove in thy wardrobe before sleep, lest thee poison thy own spouse ~~GLOVE",
      
      "The Mirrored axe is most expensive, and only sold on ODD sunny days ~~AXE",
      "The Frostdagger is sold on the first day of ten, if raining, and Magmadagger on the fifth day, if sunny ~~DAGGER",
      "We reserve sale of Blessed-armor for the third day of ten ~~ARMOR",
      "The sale of a Blessed-sword is celebrated on the third day of ten ~~SABER",
      "The mighty Flamebow is only for trade on the fifth day of ten if the sun shines ~~BOW",
      "Exotic-plate armor can be ready for you on the seventh day of ten ~~ARMOR",
      "The rare Exotic-hammer can be ready for you on the seventh day of ten ~~HAMMER",
      "The Souldagger and Banedagger are forbidden to sell...but if it rains on the ninth day... ~~DAGGER",
      };
      
   public static boolean isWeapon(String typed)
   {
      typed = typed.toUpperCase();
      return (typed.contains("TORCH") || typed.contains("BOW") || typed.contains("DAGGER") || typed.contains("DUAL") || typed.contains("HAMMER") ||
              typed.contains("SABER") || typed.contains("SPEAR") || typed.contains("AXE") || typed.contains("STAFF") || typed.contains("SWORD") ||
              typed.contains("LUTE") || typed.contains("ARMOR") || typed.contains("GLOVE"));
   }

   public static String[] legendaryWeaponInfo = {
   "Legendary weapons are forged by great ironsmiths and gods, and hewn with MAGIC",
   "The powers of legendary weapons come from MAGIC ruby, jade and azurite, and a secret magic item 'round the hilt",
   "Ask the mages about spells. They may know of the MAGIC in forging legendary weaponry",
   "The weapons of legend grant their owner greater ability, and find their targets with more precision", 
   "Tis written that legendary weapons more often strike their targets most critical anatomy, dealing grave damage",
   "The Golden Axe of legend goes by the name Forseti's Axe",
   "The HAMMER known as Mjolnir is known to be the mightiest in all creation",
   "The Bow of Karna is known only in high tales, said to be carried by the fastest of heroes",
   "The legendary blade of Excalibur is made for the hero of greatest might",
   "Ame No Nuhoko is the most powerful of halberds, fit for the legend warrior that moves like the wind",
   "Legends tell of the Gada Torchmace, held by the mightiest of heroes",
   "Khatvanga is the longstaff of legend, made for the hero of unparalled speed and might",
   "Gungnir is the magic spear of the most agile of legendary heroes",
   "Carnwennan is a legendary dagger, made for a hero of great dexterity"
   };

//only elements that contain "~~" should be added in the journal
   public static String[] spellInfo = {"Thy needeth a staff to hold spells that are directed at targets ~~STAFF", "A mage's staff is bound to the soul: once owned, you cannot drop it ~~STAFF", "Thy staff can know spells beyond your reach: thee will only see that which can be cast ~~STAFF", "Thy mage's staff can strike at a range of two arms lengths, but is made for directing spells ~~STAFF", "When joined with the most rare of magic stones, thy staff can stun enemies and enhance the caster�s directed spells ~~STAFF",
      "Spells learned from the book can be stored in the mind ~~MISC", 
      
      "Thy Fear incantation will make thee appear as a beast, frightening those of lesser mind away ~~FEAR",  "The more potent thy mind, the more fierce a beast a Fear illusion shall produce ~~FEAR", "Thy Fear spell is a potent way to sneak into the most dangerous, beast filled LAIR ~~FEAR", "One could clear a small village with a Fear spell without drawing blood ~~FEAR", "All those with the Vampyric curse possess this terrifying spell ~~FEAR",
      "A Disarm chant can undo that which is locked or set with TRAP ~~DISARM", "Traps can be found on DUNGEON floors, chests and even some doors ~~DISARM", "With a LOCKPICK and nimble fingers, you can save your manna for combat ~~DISARM",
      "An incantation of Light will make bright where it is not ~~LIGHT", "Light is critical for darkened CAVE and DUNGEON to better see advancing beasts ~~LIGHT", 
      "The Charm incantation will make thee more appealing to others ~~CHARM", "'Tis said the Charm incantation even works on some wild animals that need taming ~~CHARM", "A good Charm incantation might get you a discount from less-minded shoppekeeps ~~CHARM", "Not all will be fooled by magic Charm, but it makes many interactions much easier with little manna spent ~~CHARM", "Beware, thy VAMPYRE can charm thee of even strong mind ~~CHARM",
      "The Heal chant will seal thy flesh wounds ~~HEAL", "A Heal spell will cast upon thy animal companions when they are close to thee ~~HEAL", "Healing also comes in potion form, which can let thee save manna for combat ~~HEAL",
      "A modest Shiftwind spell will change the course of the very winds around thee ~~SHIFTWIND", "Mages of thy SHIP are of great use knowing a Shiftwind incantation, making sea travel easier ~~SHIFTWIND", "Many turnings of the world around are driven by winds, like currents, hunting and the spreading of fires ~~SHIFTWIND","A mage lost in the fog can use thy Shiftwind incantation to clear thy path ~~SHIFTWIND","The Shiftwind incantation can only be called when thee can see the skies, not under ceiling ~~SHIFTWIND",
      "Thee who is close to nature can learn the Tame-Animal incantation ~~TAMEANIMAL", "Thy Tame-Animal spell will make allies of natural animals ~~TAMEANIMAL", "Thy natural allies include Wolfen, Bear, Elk, Dog, Squid, even thy Swine and Rabbit ~~TAMEANIMAL", "Tamed animals will follow thee into battle, but the Tame spell requires much manna ~~TAMEANIMAL", "To learn Tame-Animal, meditate with thy staff under clear sky upon an animal corpse, draped in animal furs ~~TAMEANIMAL",
      "A staff may learn the Raise-stone spell: a monolithic rock will rise from the earth ~~RAISESTONE", "Thy Raise-stone spell can be used to defensively block advancing beasts, or block the magic of Monoliths ~~RAISESTONE", "Stones can even be raised from shallow waters with a well trained staff ~~RAISESTONE", "When combined with thy Enchant-stone spell, Raise-stone can be used for clever and powerful offense ~~RAISESTONE",
      "A mystic fog can be summoned by a clever mage to cloak thy presence ~~MAGICMIST", "The Magicmist incantation shifts the very air to raise a dense fog from the ground ~~MAGICMIST", "A pursuer will lose thy track in a Magicmist, lest they be beast that can smell ~~MAGICMIST", "Magicmist can be used to escape guards or armies, or infiltrate monsterous lairs ~~MAGICMIST",
      
      "We can teach you these fine spells for COIN",
      
      "In these lands can be found magic items, whose potent effects require no skill in the magic arts ~~MISC",
      
      "With some risk, a mage armed with a staff can learn the ways of great CAVE spiders ~~SPIDERSWEB", "Thy Spidersweb spell can ensnare a beast in a magic web ~~SPIDERSWEB", "The more powerful and dexterous a creature, the faster they can escape a magic web ~~SPIDERSWEB", "Learning the Spiderwebs spell comes at a price, paid by close kinship with CAVE spiders ~~SPIDERSWEB", "A skilled mage must have the spider's venom in thy veins and study its web to learn thy Spidersweb spell ~~SPIDERSWEB",
      "A lower spell, Blindinglight, will flash thy staff a light so brilliant as to blind and stun an enemy ~~BLINDINGLIGHT", "Thy Blinding light spell can be used to open a moment to attack or run from an enemy ~~BLINDINGLIGHT", "Tis known that a thieving mage may use thy staff's Blindinglight spell to lift anothers possessions ~~BLINDINGLIGHT", "Blindinglight is a silent and focused spell that may not draw the attention of those other than the target ~~BLINDINGLIGHT",
      "The Cure incantation will purge a poison that boils within ~~CURE", "One can be poisoned by vipers, spiders, deadites or insideous DUNGEON traps ~~CURE", "Also seek cure potions to save thy manna to keep the beasts at bay ~~CURE", 
      "The powerful Restore chant will HEAL both thy flesh and blood ~~RESTORE", "Restore requires more manna than thy Heal and Cure spell, so cast it only in the most grave of times ~~RESTORE", "Thy Restore incantaion will use as much manna as needed to heal thy body ~~RESTORE",
      "A Knowing incantation will draw knowledge of others from the ether ~~KNOWING", "Knowing ones disposition or adeptness can let thee make better decisions about trade and battle ~~KNOWING",  "Within a location, thy directions will be guided towards a target of thy mission with the Knowing spell ~~KNOWING", "The Knowing spell can even guide thee to a buried treasure ~~KNOWING",
      "The Focus chant will sense around thee what the eyes cannot ~~FOCUS", "Those with keen awareness can better hear those that are hidden by darkness, or even behind walls ~~FOCUS", "The Focus spell is also available as a potion for thy adventurers untrained in magics ~~FOCUS",
      "The Great Eagle-eye chant will see thy MAP as an eagle at great heights ~~EAGLEEYE", "With Eagle-Eye, thy caster will also have heightened awareness for the duration of the spell ~~EAGLEEYE", "Thy Eagle-eye chant is only for mages with great manna ~~EAGLEEYE", 
      "Thy Protection potion calls an armor from the ether that shields thee ~~PROTECTION", "'Tis best to drink a Protection potion before battle ~~PROTECTION", "Potions take time to make, and are not available for purchase save some odd days ~~PROTECTION",
      "A Speed potion grants agility and control given by the spirits ~~SPEED", "Better agility will aid in thy dodging of ranged attacks, or running from speedy beasts ~~SPEED", "Thy Speed potion also aids in more success in LOCKPICK use and thievery ~~SPEED",
      "A Strength potion draws from the earth to boost thy might ~~STRENGTH", "Higher strength will aid thee in battle, mining, or breaking through walls with a great HAMMER ~~STRENGTH", "Strength potions are difficult to mix, and are only available at odd times ~~STRENGTH",
      "JR's Alphamind potion will sharpen and focus thy magic ~~ALPHAMIND", "With the Alphamind potion, thy spells will be more potent, and expend less manna ~~ALPHAMIND",
      "Fear not Dragon's breath with the Fireskin potion ~~FIRESKIN", "The awesome power of the Fireskin potion makes thee protected from flame ~~FIRESKIN", "Fireskin magic can save thy very life, but expect not protection against tooth and claw ~~FIRESKIN",
      "One cannot cast thy Floretlade incantation without a magic Floretbox ~~FLORETLADE", "Upon collecting enough red, yellow, green, blue and violet florets, place them in thy Floretbox ~~FLORETLADE", "Upon casting the Floretlade incantation, thy manna will be restored ~~FLORETLADE", "For those of weak mind, Floretlade will give thee enough manna to cast a minor spell ~~FLORETLADE", "'Tis wise to have a Floretlade spell ready if thee anticipates a large battle is forthcoming ~~FLORETLADE", "Waste not a full Floretbox in Winter. There is no harvetsing a snow covered ground ~~FLORETLADE", "The Floretlade spell is never taught, but thy Floretbox can only be sold on odd, rainy days ~~FLORETLADE", "To learn Floretlade, set camp to meditate on a clear night amongst the flowers with thy staff and a full floretbox ~~FLORETLADE",
      "If night falls, the Unseen incantation will render you only as shadow ~~UNSEEN", "The Unseen spell will fade the moment thee moves, even a twitch ~~UNSEEN", "'Tis said all with the Vampyric curse have this ability innate ~~UNSEEN",
      "Among the most powerful defensive incantations is Teleport ~~TELEPORT", "Thy Teleport spell will relocate thee when under an open sky ~~TELEPORT","If under ceiling or roof, Teleport will change thy very location to the entrance where the sky is seen ~~TELEPORT", "When invoked under sky, the Teleport's destination will be thy location logged on thy MAP ~~TELEPORT", "Without a location focused upon on thy MAP, the Teleport spell's destination cannot be predicted ~~TELEPORT", "Calling upon Teleport will use all thy manna, even if very little is all you have ~~TELEPORT", "Thou will need time to recover after a Teleport incantation before casting another spell ~~TELEPORT",
      
      "Wilt thou TRADE knowledge of these spells for coin", 
      
      "The Mighty Flight chant will call ethereal wings to pull you over the impassable ~~FLIGHT", "A Flight spell will lift thee over enemies, save giant ones ~~FLIGHT", "Beware crossing oceans in flight, lest it expire before reaching shores ~~FLIGHT", "Thy VAMPYRE will turn to great bats when invoking their flight incantation ~~FLIGHT",
      "A staff's Phasewall command will quietly shift an opening in an impassable wall ~~PHASEWALL", "Thy staff's Phasewall requires a mage with great manna ~~PHASEWALL", "Phasewall can allow thee to escape the unescapable, or access secret rooms in thy CASTLE ~~PHASEWALL",
      "A staff's Curse command will make a target less adept ~~CURSE", "Thy vile demons strike with powerful curses of their own, yet they have strange ties to SKARA Brae ~~CURSE", "Cursing a beast will make it more vulnerable in combat ~~CURSE", "Thy Curse spell is never taught by fellow mages ~~CURSE", "One must win a game of Swine against a grim Demon to learn the Curse spell for thy staff ~~CURSE",
      "A staff's Possess command will render a foe mindless, or attack its own ~~POSSESS", "Curse and Possession spells are only effective against those that have lesser minds than your own ~~POSSESS", "If the possessed is harmed, they will wake from thy spell ~~POSSESS", "A great mage can turn a battle with a well placed possession within the armed ranks of beasts ~~POSSESS", "A mage will not teach the Possess staff spell ~~POSSESS", "To learn Possess, strike down a Temple Demon, and with staff, take camp on its corpse and meditate on a clear night ~~POSSESS",
      "Thy staff's Fireball can launch flame at range, setting a foe ablaze ~~FIREBALL", "To ready one's staff with a Fireball strike will render a darkened CAVE alight ~~FIREBALL", "Set a foe ablaze with Fireball, and retreat while the flames accumulate damage upon their hides ~~FIREBALL", "Thy flame spells are less effective against grim dragons and demons ~~FIREBALL", "Fireball is quite less effective when cast in falling rain ~~FIREBALL", "Take caution: fire will destroy the prized fur of any game that is cast upon ~~FIREBALL",
      "A staff's Icestrike can slow a foe at range with piercing cold ~~ICESTRIKE", "Thy Icestrike does more harm in a driving rain ~~ICESTRIKE", "A HOUSE in flame can be smothered with an Icestrike spell ~~ICESTRIKE", "A sea serpent is less bothered by Icestrike, but withers from thy staff's Fireball ~~ICESTRIKE", "These demons and dragons that roam the land will be tested by the Icestrike spell ~~ICESTRIKE", 
      "Thy potion of invisibility can render one as a ghost, unseen to all but thy vile demons ~~INVISIBILITY", "Invisibility can give one a safe escape, or secured thievery ~~INVISIBILITY",  "Thy mage SHOPPE will sell this powerful potion on a particular odd day ~~INVISIBILITY",
      "Thy staff's Lightning command will summon a great bolt from a storm, striking at range ~~LIGHTNING", "Lightning requires more manna to cast than fireball, but can strike at more damage ~~LIGHTNING",
      "The staff's fearsome Deathtouch requires close proximity, but will pull the very life from its victim ~~DEATHTOUCH", "Thy Deathtouch spell requires a wielder with the strongest of manna ~~DEATHTOUCH",
      "Summon a wall of protective flame with thy Fireshield spell ~~FIRESHIELD", "Thy Fireshield is to only be used defensively to stop an advancing enemy ~~FIRESHIELD", "Dare not invoke the Fireshield in a CITY or VILLAGE, lest you destroy the homes of innocents ~~FIRESHIELD", "Mages will only teach Fireshield on a sunny and odd day ~~FIRESHIELD",
      "Thy staff can learn to change thy location with the Advance spell ~~ADVANCE", "Advance will shift thy position to an open space behind an impassable one ~~ADVANCE", "The unreachable regions become traversable with the mighty Advance spell ~~ADVANCE", "Invoking Advance will use all thy manna and more, requiring rest before casting another spell ~~ADVANCE", 
      "Slabs of rock summoned by Raise-stone can be set to Monoliths with Enchant-stone ~~ENCHANTSTONE", "When enchanting stones to Monoliths, dare not walk between two in close proximity ~~ENCHANTSTONE", "Powerful magic links enchanted Monoliths, reducing those that walk between to cinders ~~ENCHANTSTONE", "Raise-stone and Enchant-stone can be used in combination to set the most potent of traps ~~ENCHANTSTONE", "Enchant-stone will use all thy manna and more, requiring rest before casting again ~~ENCHANTSTONE",
      
      "A mage that finds themselves surrounded by foes should recite the Repel incantation ~~REPEL", "Repel will thrust a force from all directions from the casters center, pushing thy enemies away ~~REPEL", "Some adversaries will be momentarily stunned or confused by the repulsive force, allowing a cunning mage to retreat or attack ~~REPEL",
      "An advanced Repel spell is Flameblast. The concussive force is wrought with flame ~~FLAMEBLAST", "Invoking Flameblast will burn thy enemies as they are forced away from thy center ~~FLAMEBLAST", "The feared Flameblast incantation will not discriminate between friend and foe ~~FLAMEBLAST", "Do not cast Flameblast within three sword's length of brothers and sisters in battle ~~FLAMEBLAST", "Flameblast will use all thy manna, and the caster must rest before casting another spell ~~FLAMEBLAST",
      
      "Training with the Temple Mages will make thy spells more effective ~~MISC", "Aiding an old, poor mage might be at the service of learning a new spell ~~MISC", 
      
      "We would be happy to teach thee spells in TRADE for gold",
      
      "Mind your manna in combat - 'tis best to keep beasts at bay ~~MISC", "Setting camp for rest at night will help restore your manna ~~MISC", "Thy manna will restore faster with a RAINBOW in view ~~MISC", "Temples may hide the entrance to a crypt under a statue  ~~MISC", "Seek thy mages of the CASTLE - they know the NUMBER ~~MISC", 
      
      "You can TRADE gold for knowledge of spells here",
      
      "Try to find a sacred mannastone-'twill restore manna faster ~~ITEMS", "I hear of a magic instrument that charms beasts: ask thy lute players about thy Lute Of Destiny ~~ITEMS", "Wrapping Powerbands 'round the hilt of GEM adorned ARMS may forge a LEGENDARY weapon ~~ITEMS",
      "The holdall is a magic satchel: its inner expanse is ten times its outer, its weight stays light ~~ITEMS", "The coveted focushelm will give thee a more potent awareness of the world around thee ~~ITEMS", "Seek out blessed items: the very Gods will grant thee revival when adorned ~~ITEMS", 
      "Those of ill repute might seek out a charmring: 'twill make those around thee more hospitable ~~ITEMS", "Pentangles protect their wearers from demonic curses, as does the gentle songs of the lute players ~~ITEMS", 
      "The pentangle's rings can shatter from the curse of the Gods ~~ITEMS", "The Talisman allows its wearer to control the very weather: wind, fog and rain ~~ITEMS", "The sacred life-pearl can revive the recently slain, and rumored to grow in the gizzard of the most rare beast in the seas ~~ITEMS",
      
      "Some temples are protected by ancient monoliths - beware walking between them ~~MISC", "My brothers can teach much about the mind, but hermits who live on their own can teach awareness ~~MISC", "There is a magic that is unknown to me, said to be in a strange green CAVE ~~MISC",
      
      "The mage SHOPPE will make a holdall available for trade on the first day of ten ~~SALEDATES",
      "A charmring will be set for trade on the third day of ten ~~SALEDATES",
      "The focushelm is only available for trade on the fifth day of ten ~~SALEDATES",
      "A pentangle is forbidden for sale, save the seventh day of ten ~~SALEDATES",
      "Mannastones will be in the SHOPPE to be traded on the ninth day of ten ~~SALEDATES",
      "Come by on the 11th day to hear some fine music ~~SALEDATES",
      "The Potion of Protection will be ready for sale on the first day of ten ~~PROTECTION",
      "Speed potions will be done brewing on the third day of ten ~~SPEED",
      "Potions of Strength are sold on the fifth day of ten ~~STRENGTH",
      "We dare not sell Potions of Invisibility, but come by the SHOPPE on the seventh day of ten ~~INVISIBILITY",
      "The Mindtome, a sacred book that elevates thy intelligence, is sold in the ninth day when the sun shines ~~SALEDATES",
      "Powerbands - magic armbands that draw might from the earth, are sold on the seventh day when the sun shines ~~SALEDATES",
      "Swiftboots were forged with magic to make one more agile, and are sold on the fifth day if the sun is out ~~SALEDATES",
      "JR's Alphamind potion is formulated on the ninth day of ten ~~ALPHAMIND",
      "The Fireskin potion is available on odd days when the sun shines down upon thee ~~FIRESKIN",
      "To celebrate our sacred day, come by the SHOPPE to see what we have on the day of the NUMBER ~~SALEDATES",
      
      "Thy Tempest incantation will summon a storm, as great as thy mind ~~TEMPEST", "A storm summoned by thy Tempest spell can extinguish a crackling wall of flame ~~TEMPEST", "Tempest incantations can give thee advantage over beasts that dislike the rain ~~TEMPEST", "The distractions of the storm can make shadowy acts easier for a thief ~~TEMPEST", "A Tempest incantation can only be versed where thy hands can reach towards a visible sky ~~TEMPEST", "Thy SHOPPE mages will not teach the Tempest spell ~~TEMPEST", "To learn Tempest, one must set camp to meditate with thy staff upon a hill and under a curtain of rain ~~TEMPEST",
      "The most powerful mages can conjure a great vortex of air ~~SUMMONVORTEX", "'Twill take all thy manna to call upon, but to summon a vortex is to command the very skies ~~SUMMONVORTEX", "A spinning cyclone of air can deal massive injury to the strongest of enemies ~~SUMMONVORTEX", "Take care when summoning a vortex: once unleashed, it is no longer under thy control ~~SUMMONVORTEX",
      "Among the most powerful spells, Raise-Earth will draw surface water back into the ground ~~RAISEEARTH", "With the Raise-Earth spell, that which is submerged will become passable ~~RAISEEARTH", "The Raise-Earth spell is only taught to the highest of mages, and will drain all their manna upon invoking ~~RAISEEARTH", "A clever mage can use Raise-Earth to create a land bridge over that which was impassable by water ~~RAISEEARTH", "Mages will only teach thy Raise-Earth incantation on the first rainy day of ten ~~RAISEEARTH",
      "The Raise-Water chant does summon the ground water back to the surface ~~RAISEWATER", "Low land will flood with shallow water with an incantation of the Raise-Water spell ~~RAISEWATER", "Only the most powerful of mages can call Raise-Water, using all of their manna ~~RAISEWATER", "A shipping lane can be forged with the Raise-Water spell ~~RAISEWATER", "Thy Raise-Water spell is only taught on the third rainy day of ten ~~RAISEWATER",
      "The most potent of spells is the feared Timestop incantation ~~TIMESTOP", "Summoning Timestop will halt the flow of time for all but thee ~~TIMESTOP", "Invoking Timestop will drain all thy manna, but give thee ten-steps when all else is halted ~~TIMESTOP", "Timestop can only be learned by meditating in camp with thy staff in an alternate time realm ~~TIMESTOP", "Seek out thy mysterious green CAVE, flashing with an unworldly blue light ~~TIMESTOP",
      "A most dangerous spell, Firestorm, will cast flames from the very sky ~~FIRESTORM", "Thy Firestorm spell will burn tree, structure, friend and foes without discrimination ~~FIRESTORM", "Like Tempest, one can only invoke Firestorm where thee can reach towards open sky ~~FIRESTORM", "If there was ever a spell to use as a last resort, it is Firestorm ~~FIRESTORM", "Strike down a DRAGON to learn Firestorm, and with staff, camp upon its corpse and meditate on a dry night ~~FIRESTORM",
      "Thy staff's most powerful attack is the dreaded Stonecast ~~STONECAST", "Stonecast will render any living creature as solid stone ~~STONECAST", "Only SHIP, fire, SPECTRE and PHANTOM can escape the Stonecast incantation ~~STONECAST", "Thy Stonecast spell will take all thy manna and more, requiring time to rest before casting again ~~STONECAST", "Five swords length will the Stonecast spell travel", "There is no bounty to salvage from a creature cast to stone, as all they carry follows to rock ~~STONECAST", "Thy foes will have a lasting statue to remember thy deeds, lest someone take a HAMMER to it ~~STONECAST",
      
      "The dreaded Raise-Dead incantation can bring the recently felled back to life, aiding thee in combat ~~RAISEDEAD", "A corpse must have lessened bodily damage to be brought back into life to serve thee ~~RAISEDEAD", "Those raised from the dead only have a limited time back with the living, governed by the caster's strength of mind ~~RAISEDEAD", "No mage will dare teach the Raise-Dead spell. It must be learned at a temple ~~RAISEDEAD", "With staff in hand, set camp at the spot of a felled Spectre to learn the Raise-Dead incantation ~~RAISEDEAD",
      
      "A mage can enhance their magic wielding powers by clothing in Mage's Robes ~~CLOAKS", "Mage's Robes offer no protection against attack and the fabric invites flame, but enhances thy spellcraft ~~CLOAKS", "The powerful Holocaust Cloak will shield thee from flame ~~CLOAKS", "Wearing a Holocaust Cloak casts fire from thee, striking fear in commoners ~~CLOAKS", "The Cloak of Invisibility will render its wearer sightless to all but demons ~~CLOAKS", "Beware, thy very life will be drained by wearing the Cloak of Invisibility too long ~~CLOAKS", "Magic cloaks can be of great benefit, but diminish thy abilities while draped upon thee ~~CLOAKS", "One cannot saddle a HORSE wearing any kind of magic cloak ~~CLOAKS", "The ancient Knowing-Robe gives its wearer great knowledge from the ether, but at tremendous weight ~~CLOAKS", "The abilities of thy friends and foes will all be known by they who don the Knowing-Robes ~~CLOAKS", "Knowing-Robes will direct thee to thy mission targets, or even burried treasure ~~CLOAKS"
      };

   //returns true if the given string is the name of a spell
   public static boolean isSpell(String typed)
   {
      typed = typed.toUpperCase();
      return ( typed.contains("STAFF") || typed.contains("FEAR") ||  typed.contains("DISARM") ||  typed.contains("LIGHT") || 
            typed.contains("CHARM") || typed.contains("HEAL") ||  typed.contains("SHIFT") ||  typed.contains("TAME") || 
            typed.contains("RAISESTONE") || typed.contains("WEB") ||  typed.contains("BLIND") ||  typed.contains("CURE") || 
            typed.contains("RESTORE") || typed.contains("KNOW") ||  typed.contains("FOCUS") ||  typed.contains("EAGLE") ||
            typed.contains("PROTECT") || typed.contains("SPEED") ||  typed.contains("STRENGTH") ||  typed.contains("ALPHAMIND") ||
            typed.contains("FIRESKIN") || typed.contains("FLORETLADE") ||  typed.contains("UNSEEN") ||  typed.contains("TELEPORT") ||
            typed.contains("FLIGHT") || typed.contains("PHASEWALL") ||  typed.contains("CURSE") ||  typed.contains("POSSESS") ||
            typed.contains("FIREBALL") || typed.contains("ICESTRIKE") ||  typed.contains("INVISIBILITY") ||  typed.contains("LIGHTNING") ||
            typed.contains("DEATHTOUCH") || typed.contains("FIRESHIELD") ||  typed.contains("ADVANCE") ||  typed.contains("ENCHANTSTONE") ||
            typed.contains("REPEL") || typed.contains("FLAMEBLAST") ||  typed.contains("TEMPEST") ||  typed.contains("VORTEX") ||
            typed.contains("RAISEEARTH") || typed.contains("RAISEWATER") ||  typed.contains("TIMESTOP") ||  typed.contains("FIRESTORM") ||
            typed.contains("STONECAST") || typed.contains("RAISEDEAD") || typed.contains("CLOAK") || typed.contains("SALE"));
   }


   //riddles given by the gester.  Answers after the "~~"
   public static String[][] riddleQst = 
   {
      {"It has red, blue, purple and green, but no one can reach it, not even the queen. What is it? ~~A rainbow", "rainbow rainbows"},
      {"Ten men had five sons, Each son told riddles fun.  How many riddles were told? ~~50", "50 fifty"},
      {"What has lakes with no water, no travelers but with street, many places and borders, but in one piece? ~~A map", "map maps"},
      {"In morning it will lie at your feet, all day follow slow walk or fast run, yet will shrink in the midday sun. ~~Your shadow", "shadow shadows"},
      {"I crack and snap, yet stay whole. Against wooden things I take the greatest toll.  What am I? ~~Fire", "fire flame"},
      {"What walks with four legs in morning, two legs in the afteroon and three legs in the evening? ~~Man","man person men people"},
      {"Voiceless it cries, wingless flutters, toothless bites, mouthless mutters. What is it? ~~The wind", "wind"},
      {"What 'tis owned by the poor, needed by the rich, and if 'tis all you eat, you perrish? ~~Nothing", "nothing"},
      {"I can cross rivers and streams, but do not move. What am I? ~~A bridge", "bridge bridges"},
      {"If thee has it, thee wants to share it. If thee shares it, thee doesnt have it.  What is it? ~~A secret", "secret secrets"},
      {"What is it 'tis lighter than an feather, yet the strongest of men cannot hold for long? ~~His breath","breath"},
      {"What is it without claw, flesh, feathers or bone, but has fingers and thumbs all its own? ~~Gloves","glove gloves"},
      {"A box without hinges, key, or lid, yet golden treasure inside is hid. What is it? ~~An egg", "egg eggs"},
      {"Feed it,'twill grow, yet give it drink and it dies. What is it? ~~A fire", "fire flame"},
      {"Three lives have I. Gentle to soothe thy skin, hard to split stone, light to caress the sky. What is it? ~~Water", "water"},
      {"If there are three rabbits and you take away two, how many rabbits does thee have? ~~Two rabbits","2 two"},
      {"'Tis at the beginning of the end, and the end of time and space. Vacant to king, present to queen. What is it? ~~The letter 'E'", "e"},
      {"Alive without breath, as cold as death. Never thirsty, ever drinking. All in mail, never clinking. What is it? ~~A fish", "fish"},
      {"I have many tongues, but can not taste. By me, most things are turned to waste.  What am I? ~~Fire", "fire flame"},
      {"'Tis on, on, then off, off, then on, then off. What is it? ~~50", "50 fifty"},
      {"'Tis seen in the water if seen in the sky.'Tis in the rainbow, a jay�s feather, and lapis lazuli.  What is it? ~~Blue", "blue"},
      {"I take thee by night, by day take you back. None suffer to have me, but do from my lack. What am I? ~~Sleep", "sleep rest"},
      {"What is it that after you take away the whole, some still remains? ~~Wholesome", "wholesome"},
      {"What runs around a city, but does not move? ~~A wall","wall walls"},
      {"What has roots as nobody sees, is taller than the trees. Up, up, up it goes, and yet never grows? ~~A mountain", "mountain mountains"},
      {"I have a head and a tail. I have no body, but am not a snake. I am something most want to take.  What am I? ~~A coin", "coin coins"},
      {"Swift you run in a race. You pass the person in second place. What place are you in? ~~Second place", "second 2nd"},
      {"Fifty plus two fish swim in a pond. Two die, four swim away. How many fish are left in the pond? ~~50", "50 fifty"},
      {"I always run, never walk, often murmur, never talk, have a bed, never sleep, have a mouth, never eat. What is it? ~~A river", "river rivers"},
      {"At night we come without being fetched. By day we are lost without being stolen.  What are we? ~~The stars", "star stars"},
      {"I know a word of letters three. Add two, and fewer there will be! What is it? ~~Few", "few"},
      {"I drive men mad for love of me, easily beaten, never free.  What is it? ~~Gold", "gold"},
      {"Its life is measured in hours, serves by being devoured. Thin 'tis quick, fat 'tis slow, wind is its foe. What is it? ~~A candle", "candle candles"},
      {"Say thy name, and it disappears. What is it? ~~Silence", "silence"},
      {"At the sound of it, you may dream or stamp your feet, you may laugh or sometimes weep. What is it? ~~Music", "music song songs"},
      {"Always am I dancing, always must I be fed. The finger I lick will soon turn to red. What am I? ~~Fire", "fire flame"},
      {"I run smoother than most any rhyme, I am easy to fall, but can not climb.  What am I? ~~A river", "river rivers water"},
      {"Ada's mother had children three - one named Winter one named Spring. What is the name of the third child? ~~Ada","ada"},
      {"You saw it where it never was and where it could not be.  Yet within that very place, your face you often see. What is it? ~~A reflection", "reflection mirror reflections mirrors"},
      {"Two in apple but not tart. One in liver but not in heart. Third is in giant and ghost. 'Tis best when roast. ~~A pig", "pig pigs"},
      {"Forward 'tis heavy, but backwards 'tis not. What is it? ~~Ton", "ton"},
      {"We hurt without moving, poison without touching, bear both truth and lies, not judged by size.  What are we? ~~Words","word words"},
      {"Around me, you snuggle and sleep. But oh how you run when I am released from my keep. What am I? ~~Fire", "fire flame"},
      {"What is it that covers the Dragonqueen that makes it easy to know her weight? ~~Scales", "scale scales"},
      {"None in a castle, but one in its tower. One in a room, but two in its corner. What is it? ~~The letter 'R'", "r ration"},/*because "r" autocompletes to "ration"*/
      {"What is it 'tis most useful when broken? ~~An egg", "egg eggs"},
      {"You get it when thee meets thy Vampyre, but made of snow. What is it? ~~frostbite","frostbite"},
      {"dsriaxgloentftierres spells thy doom when you remove six letters. What is it? ~~Dragonfire","dragonfire"},
      {"Ladybird sunset daffodil grass sky aster eggplant: What is it? ~~A rainbow", "rainbow rainbows"},
      {"What is in my pocket? ~~A ring","ring"}
      };

   //information on ships and shipping
   public static String[] shipInfo = {"Stay off line of a hostile ship's cannons", 
                           "'Tis best to attack hostile ships with WEAPON and SPELL of range",
                           "Let a ship move into your firing line, allowing you to attack and move out of harm's way",
                           "A ship's wooden hull is vulnerable to flame-arrows and SPELL",
                           "If a ship is set aflame, retreat out of harm's way while the fire does its damage", 
                           "Once thee is mightier than those left on a hostile ship's crew, you can commandeer their ship", 
                           "When all enemy units are cleared, take the helm", 
                           "Once commanding a ship, you will find its cannons to work impressively, even against mighty Sea-Serpents", 
                           "Cannons will likely destroy thy enemie's ARMS and items, so expect not to collect much from the fallen",
                           "Larger vessels can cut through strong currents, but will be pushed if not tended to",
                           "Even a Greatship can struggle against the powers of a vortex",
                           "If thee cannot commandeer a hostile ship, buy one from the Port-town shoppekeep"};
     
   //information on horses and horse training
   public static String[] horseInfo = {"Wild horses move with great speed, even when mounted", 
                           "Thou must not corner a wild horse - it will kick fiercely", 
                           "Do not approach a wild horse with a WEAPON drawn", 
                           "A horse will not allow saddle of one draped in holocaust or invisibility cloak",
                           "One of our royal mages tames horses with me, and uses a charm SPELL upon them",
                           "The LEGENDARY Lute of Destiny will calm a horse not suited to your temperment", 
                           "Do not strike a tamed horse - it will not allow your saddle", 
                           "Both hands are needed to handle a wild horse - thou cannot wield a WEAPON",
                           "Taking a WEAPON in hand will dismount you from thy horse", 
                           "Dismount your horse before battle - wild horses do not have the temperament for war",
                           "A wild horse will buck you off if struck in battle",
                           "If a horse is injured, a mage's HEAL spell will seal its wounds",
                           //from royal guard of the capital
                           "Thy Unicorn is the most beautiful creature of these lands and among the most powerful",
                           "Only one warrior of lore has ever tamed a Unicorn",
                           "The horned horse of legend appears when colors arch the sky and dot the earth",
                           "Only those with worthy reputation can get close to a Unicorn",
                           "One must be under the RAINBOW and in view of flowers for the Unicorn to appear",
                           "Thy mages celebrate the magic of thy RAINBOW, the Unicorn appears from its light",
                           "The Unicorn can only be tamed by feeding it flowers",
                           "A Floretbox must be in hand to get close to the magical beast",
                           "The Unicorn's patience is fleeting - keep it happy for the short time thee has",
                           "Thy Unicorns possess much strength and agility, far above that of regular horses",
                           "The glowing horn of the Unicorn can inflict damage as a spear, and from range",
                           "Strike not upon the magical Unicorn, lest you tempt its might and power"
                           };

   //information on dogs and dog training
   public static String [] dogInfo = {"A dog must be aligned with thy reputation to trust thee",
                           "A dog that responds with growls will not take unto thee, save a mage's Charm SPELL",
                           "Thy 'treat' command can earn a dog's trust by sharing", 
                           "Thy 'stay' command will set thy dog to stillness", 
                           "Thy 'go' command will send thy dog away, breaking thy bond", 
                           "Thy 'come' command will have thy dog follow thee",
                           "Thy 'attack' command will send thy dog to distract an enemy",
                           "A loud whistle can summon thy dog from a great distance", 
                           "Dogs are fine companions for hunting",
                           "A dog on guard at camp will alert you of dangers, allowing for better rest",
                           "Traveling with thy dog will expend thy RATIONS faster than traveling alone",
                           "Set thy dog on attack if being bothered by knavish jesters",                                                                
                           "Dogs fare not well on the battlefield: 'tis cruel to take them there",
                           "Thy WOLFEN and Bear will leave thee be with a dog at thy side",
                           "The Orcs of the plain are quite afraid of dogs: they will run at sight of them"};

   //information on vampires and vampyric curses
   public static String [] vampireInfo = {"Vampyric curse gives great powers, but with heavy burden",
                           "Vampyres cannot be in sunlight, lest their skin burns to dust",
                           "The low sun burns slower, mid-day sun ends thee with haste",
                           "DRAGON scale armor can shield some light, as can dense forest",
                           "A Vampyre can safely move about in daylight hours when the sun is hidden by rain clouds",
                           "The Vampyre cannot eat RATIONS, but feed on the blood of a living human to survive - the sleeping make for easy fodder",
                           "Vampyres need sleep themselves, and slumber during daylight in places shielded from the sun - DUNGEONS and CAVES",
                           "Vampyres that rest in a coffin will be fully restored, and  DUNGEONS still have coffins left from Vampyres long slain",
                           "Towns and cities have coffin builders, which can offer refuge for Vampyres during day",
                           "The close gaze of a Vampyre can seduce thee, dropping thy guard and confusing thy direction",
                           "A heavy wooden crossbow bolt through the heart will end a Vampyre's reign with haste",
                           "The Vampyric curse can be lifted - seek thy hermit, lives alone in a single hut"};

   //information on werewolves and the wolfen curse
   public static String [] werewolfInfo = {"Those with the Wolfen curse will be cast as beast if thee steps into moonlight",
                           "Thy Wolfen curse will weigh on thee with great heft",
                           "Thy curse will forbid the planting of a home, once the locals find out",
      							"Villagers and hunters will cast thee out: one must constantly travel to survive",
      							"A mage once stricken with the curse invoked the Tempest SPELL to block moonlight",
      							"Upon the rising sun, the beast will turn back to man",
      							"The Wolfen beast has great strength, but difficult to control",
                           "Some semblance of control can be gained over time",
      							"'Tis strong enough to topple men, beasts and wooden doors",
      							"Beware thy Royal Guard: thy sword 'tis made of silver",
      							"Thy silver forged WEAPON will smite a Wolfen cursed soul",     							
      							"Know that the curse can be lifted. Seek the hermit of the single hut"};

   //information on mapping and traveling
   public static String [] mappingInfo = {"Check thy map before travel, 'tis not wise to travel without aim",
                           "'Tis vital to know the closest town, for safety, RATIONS, shelter and ARMS",
                           "Train thy awareness to make mapping easier. Seek the hermit in the single hut for this",
                           "The men of the CITY will mark thy surroundings for five coins. Thy women will fill thy CITY map for the same",
                           "The CITY women know much for thy map, including which houses have valuables",
                           "Help thy downtrodden to their feet with RATIONS or coin. They will fill your map for free as thanks",
                           "Hear the names of the TOWNSFOLK. Those from other cities may reveal their HOME in their names",
                           "For any citizen, ask about their HOME and they might mark it on thy map",
                           "Travelers at temples or in prisons are from surrounding cities",
                           "Finding a well visited temple or full prison and thy map will gain many paths",
                           "Inspect temples carefully - a statue or wall may hide an entrance to a crypt"
                           };
                           
   //information on trapping and hunting
   public static String [] trappingInfo = {"The Shoppekeep Butcher makes a fine trap for catching game, perhaps for sale",
                           "Place a trap down-wind from thy target. Then move upwind to push the game towards the snare",
                           "A well made trap will wound thy game, draining its ability to kick thee over time",
                           "Wait until the game can no longer put up a fight before getting close",
                           "A crafty adventurer can use a trap against some DUNGEON creatures, but not ones of larger size",
                           "Thy massive beasts, like thy TROLL and colossus, will destroy the trap when they trod upon it",
                           "The trap is also quite useless against flying beasts, and cannot be placed in the water",
                           "Against a BUGBEAR or BRIGAND, a trap can prove quite useful",
                           "Never place a trap in a well traveled VILLAGE or CITY. 'Tis a wicked act."                 
                           };
   
   //information on domestic life
   public static String [] homeInfo = {"A home can have a wardrobe to store weapons, armor and items, and let thee upgrade thy ARMS",
                           "Some armaments can be fitted with magic or sacred stones, augmenting thy abilities",
                           "Buying a home in a CITY will give thee a safe place to rest",
                           "Thy strength and manna will restore faster when sleeping in thy own bed",                                                 
                           "One that owns a home can take vows of marriage and live with thy spouse",
                           "Thee will inherit some of thy spouse's reputation - be mindful of to whom thee weds",
                           "Those that are of wicked reputation or cursed will unlikely find one willing to exchange vows",
                           "Health and manna will come back to thee faster with a wife or husband in thy home",
                           "Upon sleeping at home with thy spouse, RATIONS will be ready for thee by morning",
                           "Beware talking on vows with more than one - a thorny path that bears more wounds than fruit"  
                           };
                           
   //information on games of chance
   public static String [] swineInfo = {"Swine is a game of wagering gold against the rolled points from a six sided number cube",
                           "On thy turn, roll the cube and add the top-side number to thy sum so long that it is not a one",
                           "Thy sum is added to thy points when you pass the turn to thy opponent",
                           "Upon rolling a one on its top-side, thy turn is done and thy sum is lost, being cast to zero",
                           "The first player that reaches fifty or more points is the winner of all the wagered gold",
                           "Beware, 'tis said that some players have acquired weighted cubes that resist landing on thy one side",
                           "'Tis commonly known that even vile demons take interest on this game of chance",
                           "Beware agreeing to a game of Swine with a grim demon. Thy stakes are high if thee falls short on rolls"
                           };
                           
   //information on crafting
   public static String [] gemInfo = {"When one owns a home, thy has a safe place to rest and upgrade ARMS in thy inventory",
                           "Some weapons can be fitted with powerful stone found in mines or DUNGEON treasure",
                           "A Moonjem casts light like the harvest moon, and will reflect it within a WEAPON adorned with it",
                           "Thy reddened Ruby is the hue of might, and will strengthen the warrior that carries ruby ladened ARMS",
                           "The glowing green Jade stones will heighten the mind of the owner of a jade jeweled WEAPON",
                           "Blue is the color of thy Azurite stone, and swiftness befalls the hands that carry an Azurite blade",
                           "Arms equipped with Flamejem or Icejem may cast its victim to FIRE or FROZEN",
                           "Only a mage's staff can be fitted with a precious Mannastone, strengthening thy STAFF for combat",
                           "Some gem-adorned ARMS can be forged into a LEGENDARY weapons by wraping Powerbands 'round the hilt",
                           "The most rare of stones, the Life-pearl, can only be fitted to a Royal WEAPON",
                           "Scale armor can be crafted with the scales of DRAGON or seaserpent, impervious to fire or frost"
                           }; 
                                                                                                  
//information on commerce
//only elements that contain "~~" should be added in the journal
   public static String[] shopInfo = {
                           "If it is weapons and armaments that thee requires, seek out the Ironsmith ~~ARMS",
                           "Weapons of great variety may be bought or sold for gold at the Ironsmith, and arrows replenished ~~ARMS",
                           "Special items might be sold on particular odd days ~~ARMS",
                           "The Ironsmith has a great knowledge of weaponry, and asking about arms will be rewarded with advice ~~ARMS",
                           "The Ironsmith will TRADE in animal pelts, and will buy them from fruitful hunters ~~ARMS",
                           "Ask thy Ironsmith for advice on trapping and the use of the TRAP to ensnare game and monsters ~~ARMS",
                           "Most large cities support a local Ironsmith, as well as thy CASTLE and ARENA ~~ARMS",
                           "The city Ironsmith is skilled enough to CRAFT fine armor from the scales of great beasts ~~ARMS",
                           "Seek the castle Ironsmith for their complete scribed volume on CRAFTING weapons ~~ARMS",
                           
                           "The injured and sickened can find CURE for coin at the Mage�s Shoppe ~~MAGIC",
                           "Those that practice the magic arts will want to seek the Mage�s shoppe ~~MAGIC",
                           "One can find and purchase a Mage�s Staff, or as they say, the Mage�s Staff finds thee ~~MAGIC",
                           "The shoppekeep can sell potions to aid thee on thy adventures ~~MAGIC",
                           "The Mage's Shoppe sells HEALING potions, CURE potions for the poisoned, and other powerful magics on odd days ~~MAGIC",
                           "Advice can be given on a variety of SPELLS for free at the Mage's shoppe  ~~MAGIC",
                           "Shoppe inventories will change on a numeric cycle, and some items are only available be it sunny or rain ~~MAGIC",
                           "Mage Shoppes can be found in many cities, and thy CASTLE and VILLAGE ~~MAGIC",
                           
                           "Rations can be bought in preparations for travels for those that do not have the talent to TRAP thy own game ~~RATIONS",
                           "Keep stocked with RATIONS for thyself, the poor or tossed to a BEAST that can be swayed by food ~~RATIONS",
                           "Ask the Butcher about trapping, and TRAP of high quality can TRADE for gold ~~RATIONS",
                           "The Rations shoppekeep knows good advice on mapping and using thy MAP to travel ~~RATIONS",
                           "Ration Shoppes can be found in most villages.  Keep thy DOG outside the shoppe before entering ~~RATIONS",
                           
                           "A drink for a weary traveler awaits thee in the CITY Tavern ~~TAVERN",
                           "Advice about the TOWNSFOLK can be gained from the barkeep, and often a patron looking to play a game of SWINE ~~TAVERN",
                           "Be not reckless with thy bets with some of the locals: �tis said that some play with unfairly weighted number cubes ~~TAVERN",
                           "Gain all the advice thee can from the tavern: knowledge is power in these dangerous times ~~TAVERN",
                           "Most cities, ports and villages support a local tavern ~~TAVERN",
                           "The drinking of the local ale needs moderation. Too much and thou will be SULLIED ~~TAVERN",
                           "'Tis unmistakable, the stumblings of a drunkard. 'Tis difficult to navigate thy own feet ~~TAVERN",
                           "Those laden in ale will soil thy reputation, and locals will not want to talk to thee ~~TAVERN",
                           "The best thing for an ale-SULLIED adventurer is a good night's rest, at camp or in a bed ~~TAVERN"
                           };
                           
   //returns true if the given string is the name of some kind of shop
   public static boolean isShop(String word)
   {
      word = word.toLowerCase();
      return (word.contains("arms") || word.contains("magic") || word.contains("ration") || word.contains("tavern"));
   }
  
   //information on common afflictions that the player can get
   public static String[] afflictionInfo = {
      "Certain armaments carry magical qualities that impress the agents of SKARA Brae ~~BLESSED",  "A blessing may be sent down as the Gods watch over you, supplementing thy health ~~BLESSED",  "The careful CRAFTING of impressive swords make them suitable to be blessed, as well as finely crafted armor ~~BLESSED",
      "The strike of a bane WEAPON or demonic claw may cast a curse upon thee ~~CURSED", "The very Gods may cast a curse unto you for undertaking a profoundly wicked act ~~CURSED", "Movement is confused and difficult when cursed ~~CURSED", "The cursed will suffer random impairments to either might, mind or agility ~~CURSED", "Those of strong mind may be able to will thy curse away, but others will need to seek the help of CITY or CASTLE folk ~~CURSED",
      "Running afoul a DRAGON, Magma Mother or Flamebow carrying assassin might cast thee on fire ~~FIRE", "Thy bodily health will flee from thee quickly when cast in flame ~~FIRE", "Diving into shallow waters will save thee from fire, as will a health SPELL or potion ~~FIRE", "One may smother the flames by donning DRAGON Scale armor or a Holocaust Cloak ~~FIRE",
      "The icy breath of a Sea Serpent can set a devastating chill upon thee, as can the strike from a Sorcerer�s Bright Icestaff ~~FROZEN", "A freezing chill can be levied by the blowing snow of a Winter�s storm ~~FROZEN", "When frozen, thy agility will be hampered and damage is done to thy skin over time ~~FROZEN", "Wearing thick animal furs from BEAR, ELK or WOLF will protect thee from the cold ~~FROZEN", "Cold protection can come from armor forged from the scales of the great dragons of the sea ~~FROZEN",
      "Poisons come from SNAKE, SPIDER, poisoned arms, traps and the bite from a rotting DEADITE ~~POISONED", "When poisoned, thy very life will be slowly drained away from thee ~~POISONED", "While the strong might survive before a poison runs its course, thy only CURE is by SPELL, potion or serpent�s EGG ~~POISONED", "For those without the means of a poison's CURE, seek the owner of a Mage�s Shoppe in cities and villages ~~POISONED", "If thee comes across a SNAKE nest, there lies a chance of a poison's CURE by devouring its EGG in the raw ~~POISONED",
      "One might seek the refreshments and temporary confidence of the VILLAGE Tavern ~~SULLIED","Take in too much and thou will be sullied: ladened by the local ale ~~SULLIED","When sullied, thy movements will be confused and difficult ~~SULLIED","The reputation of the often sullied will be tarnished, and the locals will not want to help thee ~~SULLIED", "The effects of being sullied will pass by time, but a good night�s rest is a guaranteed CURE ~~SULLIED"
   }; 
   
   //returns true if the given string is the name of a common affliction
   public static boolean isAffliction(String word)
   {
      word = word.toLowerCase();
      return (word.contains("bless") || word.contains("curse") || word.contains("fire") || word.contains("frozen") || word.contains("poison") || word.contains("sullied"));
   }
   
   //informaiton on different location types in the world
   public static String[] locationInfo = {
      "The locals of large cities find entertainment in the Arena, where warriors battle fierce BEAST ~~ARENA", "One can find the Arena adjacent to large cities and the CAPITAL ~~ARENA", "The Arenamaster dictates the terms of the challenges and can offer thee a hearty prize for entertaining the crowd ~~ARENA", "In the absence of brave warriors to fight, some Arenamasters are known to enslave the downtrodden of the CITY to fight ~~ARENA", "The back of the Arena houses the prison cells of those desperate to escape ~~ARENA", "The Champion of the Arena wears a MAGIC cape ~~ARENA",  
      "Various armies march across the plain, seeking resources, vengeance or chaos ~~BATTLEFIELD", "Local militias are summoned to defend the villages from BEAST armies, and a brave adventurer can find some work there ~~BATTLEFIELD", "Good experience is to be gained on the battlefield, even by merely witnessing the strategies employed there ~~BATTLEFIELD", "On the battlefield, the ARMS of the fallen can provide a small wealth of gold when traded ~~BATTLEFIELD",
      
      "The largest CITY of Cultima, the Capital is a walled city ~~CAPITAL", 
      "In the Capital is GARRIOTT castle, various shoppes, ample housing and a port ~~CAPITAL", 
      "The Capital has missions to be found and victims for those thieves fast enough to elude a well-trained GUARD ~~CAPITAL",
      
      "Castles are HOME to royalty and a training place for the guards that protect these lands ~~CASTLE", "One can often find an armory or a mages SHOPPE in a castle, and the royalty within might have a mission for thee ~~CASTLE", "Many castles have a DUNGEON built below, some as prisons, some overrun with the wicked denizens of the underworld ~~CASTLE", "GARRIOTT Castle sits in the center of our capital city ~~CASTLE",
      "Many cities can be found across the land, most walled off to protect them from the roaming BEAST of Cultima ~~CITY", "Some cities grew out of abandoned military forts, some matured from a once smaller VILLAGE ~~CITY", "Many cities will support various SHOPPES that travelling adventurers find useful ~~CITY", "One might find a HOUSE that can be purchased in a city, where rest and CRAFTING can commence ~~CITY", "In the city there are missions for the brave and information for the eager ~~CITY", "Beware city thieves, who might shadow your movements looking for an opportunity to strike ~~CITY", "Even fortified city walls can�t keep every BEAST at bay ~~CITY", "A BEAST might walk through the city gates on occasion, and the citizens will cry out for a hero ~~CITY",
      "Those that seek solace and quiet might set up SHOPPE away from the CITY and VILLAGE ~~HUT", "One can find a safe place to camp in remote huts, and possibly RATIONS or mage�s supplies ~~HUT", "The hermits that commune with nature often have special abilities � an awareness not unlike that of the BEAST of the wood ~~HUT",
      "The mountains of Cultima hide entrances to darkened caves ~~CAVE", "Brave adventurers in caves will only find the light they bring, be it torch, flamed WEAPON or MAGIC ~~CAVE", "Many caves are the home to BEAST, from Spiders to Dragons ~~CAVE", "An EGG can make good rations, but some of special qualities can be found within darkened caves ~~CAVE", "Strange tales tell of a rare CAVE with a pulsing light seen from within, hiding portals to other realms ~~CAVE",
      "Bring a HAMMER to the mines of Cultima, and riches await the adventurer thick with muscle ~~MINE", "Active mines glow with torchlight in spots, but their damp and dark corridors can become home to BEAST ~~MINE", "Dare not HAMMER a wooden support, lest ye tempt a mine collapse ~~MINE",
      "Within the mountains rest the sanctuary of beast-like men, and men that act as BEAST ~~LAIR", "TROLL, GOBLIN and BRIGAND can be found in their mountain lairs ~~LAIR", "Within mountain lairs, the cries of villagers carried away can be heard from dark prisons ~~LAIR", "The lair floors are set with traps, but the treasures stolen from the towns can be found within ~~LAIR",
      "The commerce of the seas meet at the Port, a large CITY with muddy streets, taverns and SHOPPES ~~PORT", "In a Port CITY, some shopkeeps can sell thee a SHIP for sailing the waterways of Cultima ~~PORT", "Keep on guard in the Port Towns: thieves roam the streets ~~PORT", "Beyond the Port's piers, 'tis known that BRIGAND ships and beastly creatures of the deep can strike at thee ~~PORT",
      "Some temples are in ruins, still reaching up to ancient gods, and some still active with the worshippers of SKARA Brae ~~TEMPLE", "The Temples of Cultima offer mystery, solace or danger ~~TEMPLE", "Years past of sacrifices to old gods will draw SORCERER, SPECTRE and DEMON to some Temples ~~TEMPLE", "Some Temple statues hide the entrance to a torch-lit DUNGEON, laden with traps and beasts ~~TEMPLE", "Many temples offer a sanctuary of reflection, a bed of flowers to rest, and wise Mages to teach thee ~~TEMPLE",
      "Open and sparsely populated, the villages are peaceful compared to the walled bustle of the CITY ~~VILLAGE", "Once can find a tavern, RATIONS, or a mage SHOPPE in the villages, and dirt streets alive with music ~~VILLAGE", "Be careful not to strike at the village swine and foul that meander about: they are owned by the residents ~~VILLAGE", "One can find a modest HOUSE in a village, and make a safe place to rest, CRAFT and start a family ~~VILLAGE"
   };
   
   //returns true if the given string is the name of some kind of common location
   public static boolean isLocation(String word)
   {
      word = word.toLowerCase();
      return (word.contains("arena") || word.contains("battlefield") || word.contains("capital") || word.contains("castle") || word.contains("city") || word.contains("hut") || word.contains("cave") || word.contains("mine") || word.contains("lair") || word.contains("port") || word.contains("temple") || word.contains("village"));
   }
     
   //information on the puzzle of the 3 wells
   public static String [] wellsPuzzleInfo = {
                           "An island rises from the water, and thee stands before a grim Demon-King, the Puzzle Giver",
                           "Before you are three Wells, each with a different poison. Any who drinks from one and only one will perish",
                           "Drink from Well-One, and thy poison from Well-Two or Well-Three will cure thee",
                           "Drink from Well-Two, and thy poison from Well-Three is thy cure. The poison of Well-Three has no cure",
                           "Only the Puzzle Giver can reach the waters from Well-Three, and offers a grand challenge:",
                           "Each will draw water into a Goblet, meet at the center of the island, swap Goblets and drink",
                           "Thy challenger will drink from the Puzzle Giver's Goblet, thy Puzzle Giver drinks from thy challenger's Goblet",
                           "Thy daunting Puzzle: survive the challenge and defeat the Demon King"
                           };
                           
   //information on the puzzle of the 3 doors
   public static String [] doorsPuzzleInfo = {
                           "Before thee stands Three Doors: Door-One, Door-Two, Door-Three, and a cunning Sorcerer: the Puzzle Bringer",
                           "The magic Doors are immune to pick, spell and HAMMER, to only be moved by the Puzzle Bringer",
                           "Behind two Doors live fierce beasts, coiled to strike anyone who dares open them",
                           "Behind the last Door rests a treasure of great wealth, ready to be claimed by those wise with numerics and chance",
                           "The challenger will pick one Door to open, but before it moves, the Puzzle Bringer will interupt",
                           "Among the two doors not chosen, the Puzzle Bringer will reveal which houses a fierce beast",
                           "The daunting Puzzle: decide if 'tis better to keep thy original choice, or select another"
                           };                         
   
   //information on the puzzle of the 3 towers
   public static String [] towersPuzzleInfo = {
                           "The mage known as the Puzzle Master presents before you three peg towers: Tower-One, Tower-Two and Tower-Three",
                           "On Tower-One sits a stack of disks, decreasing in radius to the smallest on the top",
                           "Thy goal: move the stack of disks from Tower-One to Tower-Two, only one disk at a time",
                           "Thy rule: a smaller disk can only be placed upon a larger disk. No disk can be placed upon one that is smaller"
                           }; 
   
   //returns a weighted random civilian type for populating locations
   public static byte randCivilian()
   {
      byte [] civs = {BEGGER,BEGGER,BEGGER,CHILD,CHILD,CHILD,GUARD_SPEAR,GUARD_SWORD, GUARD_FIST, TAXMAN,
                      JESTER,JESTER,JESTER,LUTE,LUTE,LUTE,MAN,MAN,MAN,MAN,SWORD,SWORD,SWORD,WISE,WISE,WISE,WOMAN,WOMAN,WOMAN,WOMAN};
      int index = (int)(Math.random()*civs.length);
      if(civs[index] == JESTER && Math.random() < 0.5)   //make jesters more rare
         index = (int)(Math.random()*civs.length);
      return civs[index]; 
   }
   
   //returns a weighted random spectator type for populating arenas
   public static byte randArenaSpectator()
   {
      byte [] civs = {CHILD,MAN,WISE,WOMAN,CHILD,MAN,WISE,WOMAN,CHILD,MAN,WISE,WOMAN,CHILD,MAN,WISE,WOMAN,CHILD,MAN,WISE,WOMAN,TAXMAN};
      int index = (int)(Math.random()*civs.length);
      return civs[index]; 
   }

  //returns a weighted random civilian type for populating castles
   public static byte randCastleCivilian()
   {
      byte [] civs = {GUARD_SPEAR,GUARD_SWORD, GUARD_FIST,JESTER,JESTER,JESTER,LUTE,LUTE,LUTE,MAN,MAN,MAN,
                      SWORD,SWORD,SWORD,WISE,WISE,WISE,WOMAN,WOMAN,WOMAN,ROYALGUARD,ROYALGUARD,ROYALGUARD,KING,KING,KING};
      int index = (int)(Math.random()*civs.length);
      return civs[index]; 
   }
   
   //returns a random guard type for populating locations
   public static byte randGuard()
   {
      byte [] civs = {GUARD_SPEAR,GUARD_SWORD, GUARD_FIST};
      int index = (int)(Math.random()*civs.length);
      return civs[index]; 
   }
  
  //returns a weighted random civilian type for populating temples
   public static byte randTraveler()
   {
      byte [] civs = {MAN, MAN, WISE, WISE, WOMAN, WOMAN, SWORD};
      int index = (int)(Math.random()*civs.length);
      return civs[(int)(Math.random()*civs.length)]; 
   }

 //returns a random monster type for making fountain statues in city locations
   public static byte randFountainStatue()
   {
      byte [] statues = {SEAMONSTER, SQUID, GIANT, BEAR, BEARKING, DEMON, DEMONKING, SNAKE, SNAKEKING, DRAGON, DRAGONKING};
      return statues[(int)(Math.random()*statues.length)];
   }
   
   //returns a random weighted character type for making statues in city locations
   public static byte randStatue()
   {
      byte [] statues = {MAN, WOMAN, KING, KING, KING, SWORD, ROYALGUARD, GIANT, BEAR, BEARKING, DEMON, DEMONKING, SNAKE, SNAKEKING, DRAGON, DRAGONKING};
      return statues[(int)(Math.random()*statues.length)];
   }

   //returns a random character type for making statues in arenas
   public static byte randArenaStatue()
   {
      byte [] statues = {KING, SWORD, ROYALGUARD};
      return statues[(int)(Math.random()*statues.length)];
   }

    //returns a weighted random monster type for attacking cities
   public static byte randomCityAttackingMonster()
   {
      byte [] cityMonsters = {TORNADO, TORNADO, TORNADO, BUGBEAR, CYCLOPS, GIANT, ORC, TROLL, WOLFKING, BEARKING, DEMONKING, SKELETON, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING, DRAGON, DRAGONKING};
      byte [] cityMonstersWinter = {BUGBEAR, CYCLOPS, GIANT, ORC, TROLL, WOLFKING, SKELETON, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING};
      if(Display.isWinter())
         return cityMonstersWinter[(int)(Math.random()*cityMonstersWinter.length)];
      return cityMonsters[(int)(Math.random()*cityMonsters.length)];
   }

  //returns a weighted random monster type for attacking cities when the sun is out
   public static byte randomCityAttackingMonsterSunny()
   {
      byte [] cityMonsters = {BUGBEAR, CYCLOPS, GIANT, ORC, TROLL, WOLFKING, BEARKING, DEMONKING, SKELETON, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING, DRAGON, DRAGONKING};
      return cityMonsters[(int)(Math.random()*cityMonsters.length)];
   }

   //returns a weighted random monster type for attacking cities when it is raining
   public static byte randomCityAttackingMonsterRaining()
   {
      byte [] cityMonsters = {TORNADO, TORNADO, TORNADO, BUGBEAR, CYCLOPS, GIANT, ORC, TROLL, WOLFKING, BEARKING, SKELETON, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING};
      byte [] cityMonstersWinter = {BUGBEAR, CYCLOPS, GIANT, ORC, TROLL, WOLFKING, BEARKING, SKELETON, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING};
      if(Display.isWinter())
         return cityMonstersWinter[(int)(Math.random()*cityMonstersWinter.length)];
      return cityMonsters[(int)(Math.random()*cityMonsters.length)];
   }
   
  //returns a weighted random water monster type for attacking port cities
   public static byte randomPortAttackingWaterMonster()
   {
      byte [] waterMonsters = {SEAMONSTER, SEAMONSTER,
                              SHARK, SHARK,
                              SQUID, SQUID,
                              GREATSHIP, GREATSHIP,
                              BRIGANDSHIP,BRIGANDSHIP,
                              HYDRACLOPS };
      return waterMonsters[(int)(Math.random()*waterMonsters.length)];
   }

   //returns a weighted random monster type for an enemy battlefield soldier
   public static byte randomBattlefieldMonster()
   {
      byte [] battlefieldMonsters = {SKELETON, SKELETON, ORC, ORC, ORC, ORC, TROLL, TROLL, BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER, CYCLOPS, GIANT};
      return battlefieldMonsters[(int)(Math.random()*battlefieldMonsters.length)];
   }

   //returns a weighted random monster type for an enemy battlefield soldier
   public static byte randomBattlefieldMonsterLimited()
   {
      byte [] battlefieldMonsters = {ORC, ORC,ORC,ORC,ORC,ORC,BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER, SKELETON, SKELETON, SKELETON, SKELETON, SKELETON, SKELETON};
      return battlefieldMonsters[(int)(Math.random()*battlefieldMonsters.length)];
   }

   //returns a weighted random monster type for an enemy battlefield soldier
   public static byte randomBattlefieldWarrior()
   {
      byte [] battlefieldWarrior = {SWORD, SWORD, SWORD, SWORD, GUARD_SPEAR, GUARD_SPEAR, GUARD_SWORD, GUARD_SWORD, GUARD_FIST, ROYALGUARD, BOWASSASSIN, ENFORCER};
      return battlefieldWarrior[(int)(Math.random()*battlefieldWarrior.length)];
   }
   
   //returns a random character type for an arena master
   public static byte randomArenaMaster()
   {
      byte [] battlefieldWarrior = {SWORD, GUARD_SPEAR, GUARD_SWORD, GUARD_FIST, ROYALGUARD, MAN, KING, WOMAN};
      return battlefieldWarrior[(int)(Math.random()*battlefieldWarrior.length)];
   }
   
   //returns a weighted random character type for a friendly battlefield soldier
   public static byte randomBattlefieldWarriorLimited()
   {
      byte [] battlefieldWarrior = {SWORD,  SWORD, SWORD, GUARD_SPEAR, GUARD_SWORD, GUARD_FIST};
      return battlefieldWarrior[(int)(Math.random()*battlefieldWarrior.length)];
   }
   
   //returns a weighted random monster type for a world monster spawn
   public static byte randomWorldMonster()
   {
      byte [] worldMonsters = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               DRAGON, DRAGON,DRAGON,DRAGON,DRAGON,DRAGON,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TREE, TREE,TREE,TREE,TREE,TREE,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                               WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                               BEAR,BEAR,BEAR,BEAR,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER,
                               TORNADO,TORNADO,TORNADO,TORNADO,TORNADO,TORNADO};
      return worldMonsters[(int)(Math.random()*worldMonsters.length)];
   }

    //returns a weighted random monster type for a world monster spawn when the sun is out
   public static byte randomWorldMonsterSunny()
   {
      byte [] worldMonsters = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               DRAGON, DRAGON,DRAGON,DRAGON,DRAGON,DRAGON,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TREE, TREE,TREE,TREE,TREE,TREE,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                               WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                               BEAR,BEAR,BEAR,BEAR,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER};
      
      //returns a weighted random monster type for a world monster spawn when it is winter time
      byte [] worldMonstersWinter = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TREE, TREE,TREE,TREE,TREE,TREE,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER,BRIGAND_FIST};    
      
      //returns a weighted random monster type for a world monster spawn when it is night time
      byte [] worldMonstersNight = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               DRAGON, DRAGON,DRAGON,DRAGON,DRAGON,DRAGON,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TREE, TREE,TREE,TREE,TREE,TREE,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                               WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                               BEAR,BEAR,BEAR,BEAR,
                               WEREWOLF};
                               
      //returns a weighted random monster type for a world monster spawn for a winter night                       
      byte [] worldMonstersWinterNight = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TREE, TREE,TREE,TREE,TREE,TREE,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                               WEREWOLF};    
      if(CultimaPanel.time >=22 || CultimaPanel.time <= 2)
      {
         if(Display.isWinter())
            return worldMonstersWinterNight[(int)(Math.random()*worldMonstersWinterNight.length)];                          
         return worldMonstersNight[(int)(Math.random()*worldMonstersNight.length)];
      }
      if(Display.isWinter())
         return worldMonstersWinter[(int)(Math.random()*worldMonstersWinter.length)];                          
      return worldMonsters[(int)(Math.random()*worldMonsters.length)];
   }

      //returns a weighted random monster type for a world monster spawn when it is raining
   public static byte randomWorldMonsterRaining()
   {
      byte [] worldMonsters = {BUGBEAR, CYCLOPS, GIANT, ORC, TREE, TROLL, SNAKE, WOLF, BEAR, TORNADO, BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER,BRIGAND_FIST};
      byte [] worldMonstersWinter = {BUGBEAR, CYCLOPS, GIANT, ORC, TREE, TROLL, WOLF, BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER, BRIGAND_FIST};
      if(Display.isWinter())
         return worldMonstersWinter[(int)(Math.random()*worldMonstersWinter.length)]; 
      return worldMonsters[(int)(Math.random()*worldMonsters.length)];
   }

   //returns a weighted random monster type for slay missions
   public static byte randomSlayMissionMonster()
   {
      byte [] worldMonsters = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               DRAGON, DRAGON,DRAGON,DRAGON,DRAGON,DRAGON,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER, BRIGAND_FIST};
      byte [] worldMonstersNight = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               DRAGON, DRAGON,DRAGON,DRAGON,DRAGON,DRAGON,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               WEREWOLF};
      byte [] kingMonsters = {WOLFKING, BEARKING, DEMONKING, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING, DRAGONKING};
      if(Math.random() < 0.2)
         return kingMonsters[(int)(Math.random()*kingMonsters.length)];
      if(CultimaPanel.time >=22 || CultimaPanel.time <= 2)
         return worldMonstersNight[(int)(Math.random()*worldMonstersNight.length)];
      return worldMonsters[(int)(Math.random()*worldMonsters.length)];
   }

      //returns a weighted random monster type for slay missions when raining
   public static byte randomSlayMissionMonsterRaining()
   {
      byte [] worldMonsters = {BUGBEAR, BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,BUGBEAR,
                               CYCLOPS, CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,CYCLOPS,
                               GIANT, GIANT,GIANT,GIANT,GIANT,GIANT,
                               ORC, ORC,ORC,ORC,ORC,ORC,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER, BRIGAND_FIST};
      byte [] kingMonsters = {WOLFKING, BEARKING, SNAKEKING, RATKING, SPIDERKING, BUGBEARKING, TROLLKING};
      if(Math.random() < 0.8)
         return worldMonsters[(int)(Math.random()*worldMonsters.length)];
      return kingMonsters[(int)(Math.random()*kingMonsters.length)];
   }
   
   //returns a weighted random water monster type for slay missions
   public static byte randomSlayMissionWaterMonster()
   {
      byte [] waterMonsters = {SEAMONSTER, SEAMONSTER, SEAMONSTER, SEAMONSTER, SEAMONSTER, SEAMONSTER, SEAMONSTER, SEAMONSTER, SQUIDKING, HYDRACLOPS};
      return waterMonsters[(int)(Math.random()*waterMonsters.length)];
   }
 
   //returns a random dosile animal type for world spawns
   public static byte randomGrazingMonster()
   {
      byte [] grazingMonsters = {RABBIT, ELK, HORSE};
      return grazingMonsters[(int)(Math.random()*grazingMonsters.length)];
   }

   //returns a weighted random water character type for world spawns
   public static byte randomWaterMonster()
   {
      byte [] waterMonsters = {FISH, FISH, SEAMONSTER, SHARK, SQUID, WHIRLPOOL, GREATSHIP, BRIGANDSHIP};
      return waterMonsters[(int)(Math.random()*waterMonsters.length)];
   }

   //returns a weighted random sand dune character type for world spawns
   public static byte randomSandMonster()
   {
      byte [] sandMonsters = {GRABOID};
      return sandMonsters[(int)(Math.random()*sandMonsters.length)];
   }

   //returns a weighted random lava/magma character type for world spawns
   public static byte randomLavaMonster()
   {
      byte [] lavaMonsters = {FIRE, FIRE, FIRE, FIRE, FIRE, FIRE, FIRE, MAGMA};
      return lavaMonsters[(int)(Math.random()*lavaMonsters.length)];
   }
   
   //returns a weighted random swamp/bog character type for world spawns
   public static byte randomSwampMonster()
   {
      byte [] swampMonsters = {SNAKE, TRIFFID};
      return swampMonsters[(int)(Math.random()*swampMonsters.length)];
   }
   
    //returns a weighted random water character type for cave spawns
   public static byte randomIndoorWaterMonster()
   {
      byte [] waterMonsters = {FISH, SQUID, WHIRLPOOL};
      return waterMonsters[(int)(Math.random()*waterMonsters.length)];
   }

   //returns a weighted random character type for temple spawns
   public static byte randomTempleMonster()
   {
      byte [] templeMonsters = {DEMON, DEMON,DEMON,DEMON,DEMON,DEMON,
                                GHOST, GHOST,GHOST,GHOST,GHOST,GHOST,
                                SKELETON, SKELETON,SKELETON,SKELETON,SKELETON,SKELETON,
                                WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                                SORCERER, SORCERER,SORCERER,SORCERER,SORCERER,SORCERER,
                                SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                                PHANTOM,PHANTOM,PHANTOM,PHANTOM,PHANTOM,PHANTOM,
                                BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER};
      byte [] templeMonstersWinter = {GHOST, GHOST,GHOST,GHOST,GHOST,GHOST,
                                SKELETON, SKELETON,SKELETON,SKELETON,SKELETON,SKELETON,
                                WOLF, WOLF,WOLF,WOLF,WOLF,WOLF,
                                SORCERER, SORCERER,SORCERER,SORCERER,SORCERER,SORCERER,
                                PHANTOM,PHANTOM,PHANTOM,PHANTOM,PHANTOM,PHANTOM,
                                BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER};
      if(Display.isWinter())
         return templeMonstersWinter[(int)(Math.random()*templeMonstersWinter.length)];
      return templeMonsters[(int)(Math.random()*templeMonsters.length)];
   }

    //returns a weighted random undead type for summon spell spawns
   public static byte randomSummonUndead()
   {
      byte [] summonMonsters = {GHOST, GHOST, GHOST, SKELETON, SKELETON, SKELETON, PHANTOM, DEMON, LICHE};
      return summonMonsters[(int)(Math.random()*summonMonsters.length)];
   }

    //returns a random monster type for underworld spawns
   public static byte randomUnderworldMonster()
   {
      byte [] underworldMonsters = {GHOST, SKELETON, PHANTOM, DEMON, MAGMA};
      return underworldMonsters[(int)(Math.random()*underworldMonsters.length)];
   }
   
   //returns a weighted random creature type for jurassica spawns
   public static byte randomJurassicMonster()
   {  
      byte [] jurassicMonsters = {SNAKE, SNAKE, SNAKE, SNAKE, SNAKE, 
                                 SNAKEKING, 
                                 PTEROSAUR, PTEROSAUR, PTEROSAUR, PTEROSAUR, PTEROSAUR,
                                 BRACHIOSAUR, BRACHIOSAUR, BRACHIOSAUR,
                                 ALLOSAUR, ALLOSAUR, ALLOSAUR,
                                 STEGOSAUR, STEGOSAUR, STEGOSAUR,
                                 DILOPHOSAUR, DILOPHOSAUR, DILOPHOSAUR, DILOPHOSAUR, DILOPHOSAUR};
      return jurassicMonsters[(int)(Math.random()*jurassicMonsters.length)];
   }
   
   //returns a weighted random water creature type for jurassica spawns
   public static byte randomJurassicWaterMonster()
   {
      byte [] waterMonsters = {FISH, FISH, PLESIOSAUR, SHARK, WHIRLPOOL};
      return waterMonsters[(int)(Math.random()*waterMonsters.length)];
   }
   
   //returns a weighted random character type for 1942 spawns
   public static byte random1942Monster()
   {  
      byte [] ww2Monsters = { SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER, 
                              OFFICER,  OFFICER, OFFICER,
                              TANK};
      return ww2Monsters[(int)(Math.random()*ww2Monsters.length)];
   }
   
   //returns a weighted random character type for castle wolfenstein spawns
   public static byte randomWolfensteinMonster()
   { 
      byte [] ww2Monsters = { SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,SOLDIER,
                              GUARD_DOG, GUARD_DOG,
                              OFFICER};
      return ww2Monsters[(int)(Math.random()*ww2Monsters.length)];
   }
   
    //returns a random boss monster type for underwold spawn
   public static byte randomUnderworldKing()
   {
      byte [] underworldMonsters = {DEMONKING, DEATH};
      return underworldMonsters[(int)(Math.random()*underworldMonsters.length)];
   }
   
    //returns a weighted random character type for cave spawns
   public static byte randomCaveMonster()
   {
      byte [] caveMonsters = {BUGBEAR, CYCLOPS, DRAGON, GIANT, ORC, TREE, TROLL, BAT, RAT, SNAKE, SPIDER, BEAR, RUSTCREATURE, SLIME};
      byte [] caveMonstersNonWinter = {BUGBEAR, CYCLOPS, DRAGON, GIANT, ORC, TREE, TROLL, BAT, RAT, SNAKE, SPIDER, RUSTCREATURE, SLIME};
   
      if(!Display.isWinter())
         return caveMonstersNonWinter[(int)(Math.random()*caveMonstersNonWinter.length)];
      return caveMonsters[(int)(Math.random()*caveMonsters.length)];
   }    

   //returns a weighted random character type for mine spawns
   public static byte randomMineMonster()
   {
      byte [] mineMonsters = {RUSTCREATURE,
                              ORC, ORC,ORC,ORC,ORC,ORC,
                              TROLL,  TROLL, TROLL, TROLL, TROLL, TROLL,
                              GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN,
                              GOBLINBARREL, GOBLINBARREL,
                              BAT, BAT,BAT,BAT,BAT,BAT,
                              RAT,  RAT, RAT, RAT, RAT, RAT,
                              SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                              SPIDER, SPIDER,SPIDER,SPIDER,SPIDER,SPIDER,
                              BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST};
      return mineMonsters[(int)(Math.random()*mineMonsters.length)];
   }
   
   //returns a weighted random character type for mine spawns
   public static byte randomDominantMineMonster()
   {
      byte [] mineMonsters = {RUSTCREATURE,
                              ORC, ORC,ORC,ORC,ORC,ORC,
                              TROLL,  TROLL, TROLL, TROLL, TROLL, TROLL,
                              GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN,
                              BAT, BAT,BAT,BAT,BAT,BAT,
                              RAT,  RAT, RAT, RAT, RAT, RAT,
                              SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                              SPIDER, SPIDER,SPIDER,SPIDER,SPIDER,SPIDER,
                              BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST};
      return mineMonsters[(int)(Math.random()*mineMonsters.length)];
   }

   //returns a weighted random character type for monster lair spawns
   public static byte randomLairMonster()
   {
      byte [] lairMonsters =   {ORC, ORC,ORC,ORC,ORC,ORC,ORC, ORC,ORC,ORC,ORC,ORC,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST,
                               GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN,
                               GOBLINBARREL, GOBLINBARREL,
                               MALEVAMP, FEMALEVAMP};
      return lairMonsters[(int)(Math.random()*lairMonsters.length)];
   }
   
    //returns a weighted random character type for monster lair spawns
   public static byte randomDominantLairMonster()
   {
      byte [] lairMonsters =   {ORC, ORC,ORC,ORC,ORC,ORC,ORC, ORC,ORC,ORC,ORC,ORC,
                               TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST,
                               BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST,
                               GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN};
      return lairMonsters[(int)(Math.random()*lairMonsters.length)];
   }
   
   //returns a weighted random character type for dungeon spawns
   public static byte randomDungeonMonster()
   {
      byte [] dungeonMonsters = {RUSTCREATURE,
                                 SLIME, SLIME,SLIME,SLIME,SLIME,SLIME,
                                 DEMON, DEMON,DEMON,DEMON,DEMON,DEMON,
                                 GHOST, GHOST,GHOST,GHOST,GHOST,GHOST,
                                 ORC, ORC,ORC,ORC,ORC,ORC,
                                 SKELETON, SKELETON,SKELETON,SKELETON,SKELETON,SKELETON,
                                 TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                                 GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN,
                                 GOBLINBARREL, GOBLINBARREL,
                                 BAT,  BAT, BAT, BAT, BAT, BAT,
                                 RAT, RAT,RAT,RAT,RAT,RAT,
                                 SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                                 SPIDER, SPIDER,SPIDER,SPIDER,SPIDER,SPIDER,
                                 BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, 
                                 SORCERER, SORCERER,SORCERER,SORCERER,SORCERER,SORCERER,
                                 MALEVAMP, FEMALEVAMP, LICHE, BEHOLDER};
      return dungeonMonsters[(int)(Math.random()*dungeonMonsters.length)];
   }
   
   //returns a weighted random character type for dungeon spawns
   public static byte randomDominantDungeonMonster()
   {
      byte [] dungeonMonsters = {DEMON, DEMON,DEMON,DEMON,DEMON,DEMON,
                                 SLIME, SLIME,SLIME,SLIME,SLIME,SLIME,
                                 GHOST, GHOST,GHOST,GHOST,GHOST,GHOST,
                                 ORC, ORC,ORC,ORC,ORC,ORC,
                                 SKELETON, SKELETON,SKELETON,SKELETON,SKELETON,SKELETON,
                                 TROLL, TROLL,TROLL,TROLL,TROLL,TROLL,
                                 GOBLIN, GOBLIN,GOBLIN,GOBLIN,GOBLIN,GOBLIN,
                                 BAT,  BAT, BAT, BAT, BAT, BAT,
                                 RAT, RAT,RAT,RAT,RAT,RAT,
                                 SNAKE, SNAKE,SNAKE,SNAKE,SNAKE,SNAKE,
                                 SPIDER, SPIDER,SPIDER,SPIDER,SPIDER,SPIDER,
                                 BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, 
                                 SORCERER, SORCERER,SORCERER,SORCERER,SORCERER,SORCERER};
      return dungeonMonsters[(int)(Math.random()*dungeonMonsters.length)];
   }

    //returns a weighted brigaind character type for locations
   public static byte randomBrigand()
   {
      byte [] brigands = {BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST};
      return brigands[(int)(Math.random()*brigands.length)];
   }
   
   //returns a weighted brigaind character type for world spawns
   public static byte randomWorldBrigand()
   {
      byte [] brigands = {BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGAND_FIST, BRIGANDRIDER};
      return brigands[(int)(Math.random()*brigands.length)];
   }
   
   //returns a weighted brigaind character type for arena spawns
   public static byte randomArenaBrigand()
   {
      byte [] brigands = {BRIGAND_SWORD, BRIGAND_SPEAR, BRIGAND_HAMMER, BRIGAND_DAGGER, BRIGAND_CROSSBOW, BRIGANDRIDER, BRIGANDRIDER};
      return brigands[(int)(Math.random()*brigands.length)];
   }

   //returns an assassin character type for spawns
   public static byte randomAssassin()
   {
      byte [] assassins = {BOWASSASSIN,VIPERASSASSIN,ENFORCER};
      return assassins[(int)(Math.random()*assassins.length)];
   }

    //returns a weighted boss monster type for spawns
   public static byte randomMonsterKing()
   {
      byte [] monsterKings = {DEMONKING, DRAGONKING, TREEKING, BATKING, RATKING, SNAKEKING, SPIDERKING, WOLFKING, BEARKING, BUGBEARKING, TROLLKING, DEATH};
      return monsterKings[(int)(Math.random()*monsterKings.length)];
   }
   
   //returns a water boss monster type for spawns
   public static byte randomWaterMonsterKing()
   {
      byte [] monsterKings = {SQUIDKING, HYDRACLOPS};
      return monsterKings[(int)(Math.random()*monsterKings.length)];
   }

  //returns true if the character index is a boss monster
   public static boolean isMonsterKing(byte charInd)
   {
      return (charInd==DEMONKING || charInd==DRAGONKING || charInd==TREEKING || charInd==BATKING || charInd==RATKING || charInd==SNAKEKING || charInd==SPIDERKING || charInd==WOLFKING || charInd==BEARKING || charInd==BRIGANDKING  || charInd==BUGBEARKING || charInd==TROLLKING || charInd==MAGMA || charInd==DEATH || charInd==HYDRACLOPS || charInd==LICHE);
   }
   
   //true if the character type can leap around when in combat
   public static boolean isJumpy(NPCPlayer p)
   {
      byte pc = p.getCharIndex();
      return (pc==JESTER || pc==GOBLIN || pc==VIPERASSASSIN || pc==BOWASSASSIN || pc==BAT || pc==LICHE);
   }

   //returns true if the character index is a weather or nature event
   public static boolean isNatural(byte charInd)
   {
      return (charInd==TORNADO || charInd==WHIRLPOOL || charInd==FIRE);
   }
   
   //returns true if the character index is made of stone
   public static boolean isStone(NPCPlayer p)
   {
      return (p.isStatue() || p.getCharIndex()==MONOLITH || p.getCharIndex()==STONE);
   }

  //given a monster type, returns the king/boss version
   public static byte getMonsterKing(byte monster)
   {
      if(monster==SORCERER)
      {
         if(Math.random() < 0.5)
            return LICHE;
         return BEHOLDER;
      }
      if(monster==DEMON)
         return DEMONKING;
      if(monster==DRAGON)
         return DRAGONKING;
      if(monster==TREE)
         return TREEKING;
      if(monster==BAT)
         return BATKING;
      if(monster==RAT)
         return RATKING;
      if(monster==SNAKE)
         return SNAKEKING;
      if(monster==SPIDER)
         return SPIDERKING;
      if(monster==WOLF)
         return WOLFKING;
      if(monster==BEAR)
         return BEARKING;
      if(monster==SQUID)
         return SQUIDKING;   
      if(monster==BRIGAND_SWORD || monster==BRIGAND_DAGGER  || monster==BRIGAND_SPEAR  || monster==BRIGAND_HAMMER || monster==BRIGAND_CROSSBOW || monster==BRIGAND_FIST)
      {
         int pick = (int)(Math.random()*4);
         if(pick== 0)
            return BOWASSASSIN;
         if(pick == 1)
            return VIPERASSASSIN; 
         if(pick == 2)
            return ENFORCER;   
         return BRIGANDKING; 
      }
      if(monster==ORC)
         return TROLL;
      if(monster==GOBLIN)
         return GOBLINBARREL;
      if(monster==TROLL)
         return TROLLKING;  
      if(monster==BUGBEAR)
         return BUGBEARKING;  
      if(monster==GHOST)
         return PHANTOM; 
      if(monster==SKELETON)
         return DEATH; 
      if(monster==GIANT && Display.isWinter())
         return ABOMINABLE;    
      return monster;
   }

  //returns true if the character sent is close enough in alignment to be helpful
   public static boolean allignmentCompatable(NPCPlayer selected)
   {
      if(selected.getCharIndex()==NPC.LUTE && CultimaPanel.player.getImageIndex()==Player.LUTE)          //lute players will always talk to someone with the Lute Of Destiny
         return true;
      if((selected.getCharIndex()==NPC.KING || selected.getCharIndex()==NPC.ROYALGUARD) && CultimaPanel.player.getWeapon().getName().toLowerCase().contains("royal"))
         return true;
      if(selected.getName().equals("Skara Brae"))                                                        //we can always talk to endgame entity "Skara Brae"
         return true;
      if(selected.getName().toLowerCase().contains("arenamaster") )
         return true;
      if(selected.getNumInfo() == 3)                                                                     //numInfo of 3 means we rescued them from a prison
         return true;
      if(selected.getNumInfo()==10)                                                                      //they have a mission to give
         return true;   
      if(selected.isShopkeep())                                                                          //shopkeeps always want to talk to you to sell
         return true;
      if(selected.swinePlayer())                                                                        //gamblers always want to talk to you to win your gold
         return true;
      if(CultimaPanel.player.getReputation() >= 500)                                                     //everyone knows a hero
         return true;
      if(selected.hasItem("message"))                                                                    //they have a message to be delivered
         return true;
      if(!selected.getMissionInfo().equals("none"))                                                      //they are expecting us to complete a mission for them
         return true;
      if(!TerrainBuilder.habitablePlace(selected.getLocationType()) && selected.getCharIndex()==BEGGER)  //prisoners will always let you help them out of a prison cell
         return true;                                                                                    
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring") || CultimaPanel.player.getImageIndex()==Player.LUTE) && selected.getReputation() >= (-500 - (CultimaPanel.player.getMind()*10)) )
         return true; 
      if(CultimaPanel.player.getReputation() >= 0 && selected.getReputation() >= 0)                      //both players good
         return true;
      if(CultimaPanel.player.getReputation() < 0 && allignmentDifference(selected) <= 50)                //both players close
         return true;
      if(!TerrainBuilder.habitablePlace(selected.getLocationType()))                                     //commrades in battle and rescued prisoners will converse
         return true;
      return false;
   } 

//returns the difference in reputation:  
//negative value if player is lower reputation than selected, positive value if player has higher reputation
   public static int allignmentDifference(NPCPlayer selected)
   {
      return CultimaPanel.player.getReputation() - selected.getReputation();
   }  

//returns a random element from a list of Strings
   public static String getRandomFrom(String [] list)
   {
      if(list==null || list.length == 0)
         return "?";
      return list[(int)(Math.random()*list.length)];
   }
   
   //returns a random element from the first limit elements from a list of Strings
   public static String getRandomFrom(String [] list, int limit)
   {
      if(list==null || list.length == 0)
         return "?";
      if(limit >= list.length)    
         return list[(int)(Math.random()*list.length)];
      return list[(int)(Math.random()*limit)];
   }
   
   //returns a random element from a list of Strings
   public static String getRandomFrom(ArrayList<String> list)
   {
      if(list==null || list.size() == 0)
         return "?";
      String ans = list.get((int)(Math.random()*list.size()));
      return ans;
   }

   public static String getInfoFrom(String [] allInfo, String typed)
   {
      String temp = typed.toUpperCase();
      ArrayList<String> info = new ArrayList();
      for(String x:allInfo)
         if(x.contains("~~") && x.contains(temp))
            info.add(x);
      String ans = getRandomFrom(info);  
      int pos = ans.indexOf("~~");
      if(pos >= 0)
         ans = ans.substring(0, pos).trim();
      return ans;
   }

   public static String talk(NPCPlayer selected, String typed)
   {
      if(selected==null)
         return "";
      typed = typed.toLowerCase();
      String response = "";
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      String selectedLoc = selected.getLocationType().toLowerCase();
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      boolean bountyOnSelected = false;
      boolean purgeTownBounty = false;
      int bountyValue = 0;
      
      boolean poisonAttempt = false;
      if(typed.contains("<handshake>") && CultimaPanel.player.hasItem("viperglove"))
         poisonAttempt = true;
      boolean isGarriott = (selected.getName().contains("Garriott"));//Is this Richard Garriott (Lord British)?
      
      if(poisonAttempt)    //see if attempt to poison target with viperglove works
      { 
         boolean caught = false;
         int successRoll = Player.rollDie(CultimaPanel.player.getAgility()+selected.getMind());
         if(CultimaPanel.player.getAgility()*2 < successRoll)
            caught = true;
         if (caught || selected.getCharIndex()==DEMONKING || selected.getCharIndex()==DEMON || isGarriott)
         {
            if(selected.getCharIndex()==KING)
               Utilities.alertGuards();
            selected.setHasBeenAttacked(true);   
            if(selected.getCharIndex()==DEMONKING || selected.getCharIndex()==DEMON)
            {
               CultimaPanel.player.removeItem("viperglove");
               CultimaPanel.sendMessage("Thy glove burns off thy hand!");
            }
            CultimaPanel.player.addReputation(-5);
            return getRandomFrom(poisonAttemptFail);
         }
         else
         {
            selected.addEffect("poison");
            if(Math.random() < 0.25)
            {
               CultimaPanel.player.removeItem("viperglove");
               CultimaPanel.sendMessage("The poison of the Viperglove is spent!");
            }
         }
      }
         
      if(selected.getBounty()>0)
      {
         bountyOnSelected = true;
         bountyValue = selected.getBounty() / 2;
         selected.setGold(bountyValue);
      }
      if(selected.isWerewolf())     //see if we have a purge werewolf village mission here
      {
         for(int i=CultimaPanel.missionStack.size()-1; i>=0; i--)
         {
            Mission m = CultimaPanel.missionStack.get(i);
            if(m.getType().equals("Purge") && CultimaPanel.player.getWorldRow()==m.getTargetRow() && CultimaPanel.player.getWorldCol()==m.getTargetCol())
            {
               bountyOnSelected = true;
               purgeTownBounty = true;
               bountyValue = m.getGoldReward() / 4;
               selected.setGold(bountyValue);
            }
         }
      }
      if(selected.getName().startsWith("+"))
      {  
         if(selected.getNumInfo()==13)
            return infidelity(selected, typed); 
      }
      if(selected.getCharIndex()==NPC.DOG)
      {
         return dogResponse(selected, typed);
      }
      if(selected.isTrapped())
         return getRandomFrom(helpTrapped);
   
      if(selected.getMoveType()==NPC.CHASE && selected.getCharIndex()!=NPC.TAXMAN)     //bribe is the only option if someone is chasing you
      {
         if(typed.contains("gold") || typed.contains("money") || typed.contains("coin") || typed.contains("pay"))
         {
            if(selected.getCharIndex()==JESTER && selected.getNumInfo()==10)
            {//jester has a riddle quest in which the answer is gold
               return jesterResponse(selected, typed);
            }
            response = getRandomFrom(gold);
         }
         else if(typed.contains("bribe"))
         {
            if(selected.getReputation() >= 100 && !charmed)
               response = getRandomFrom(bribeNo);
            else if(selected.getReputation() >= -10)
            {
               if(CultimaPanel.player.getGold() < 10)
                  response = getRandomFrom(bribeNoMoney);
               else
               {
                  int roll = Player.rollDie(100);
                  if(charmed)
                     roll = Player.rollDie(200);
                  if(roll <= selected.getReputation() || Math.random()<0.5)
                  {
                     response = getRandomFrom(bribeMaybe);
                     CultimaPanel.player.pay(10);
                     selected.addGold(10);
                  }
                  else
                  {
                     response = getRandomFrom(bribeYes);
                     NPCPlayer temp = selected.clone();
                     temp.setRestoreDay(CultimaPanel.days+1);
                     CultimaPanel.NPCsToRestore.add(temp);
                     selected.setMoveTypeTemp(NPC.ROAM);
                     if(isGuard(selected.getCharIndex()))
                        selected.setNumInfo(13);   //flag that the guard has been bribed
                     selected.setHasMet(true);
                     CultimaPanel.player.pay(10);
                     selected.addGold(10);
                     if(isGuard(selected.getCharIndex()))
                        Player.stats[Player.GUARDS_BRIBED]++;
                     CultimaPanel.talkSel = false;
                     selected.setTalking(false);
                     CultimaPanel.selected = null;
                     CultimaPanel.selectedTerrain = null;
                     CultimaPanel.typed = "";
                     if(!selected.hasMet())
                     {
                        Player.stats[Player.PEOPLE_MET]++;
                        CultimaPanel.player.specificExperience[Player.AWARENESS]++;
                     
                     }
                     selected.setHasMet(true);
                     return response;
                  }
               }
            }
            else
            {
               if(Math.random()<0.5)
               {
                  response = getRandomFrom(bribeMaybe);
                  CultimaPanel.player.pay(10);
                  selected.addGold(10);
               }
               else
               {
                  response = getRandomFrom(bribeYes);
                  NPCPlayer temp = selected.clone();
                  temp.setRestoreDay(CultimaPanel.days+1);
                  CultimaPanel.NPCsToRestore.add(temp);
                  selected.setMoveTypeTemp(NPC.ROAM);
                  if(isGuard(selected.getCharIndex()))
                     selected.setNumInfo(13);   //flag that the guard has been bribed
                  selected.setHasMet(true);
                  CultimaPanel.player.pay(10);
                  selected.addGold(10);
                  CultimaPanel.talkSel = false;
                  selected.setTalking(false);
                  CultimaPanel.selected = null;
                  CultimaPanel.selectedTerrain = null;
                  CultimaPanel.typed = "";
                  if(!selected.hasMet())
                  {
                     Player.stats[Player.PEOPLE_MET]++;
                     CultimaPanel.player.specificExperience[Player.AWARENESS]++;
                  
                  }
                  selected.setHasMet(true);
                  return response;
               }
            }
         }
         else
         {
            if(selected.getCharIndex()==JESTER)
            {
               if(!selected.hasMet())
               {
                  Player.stats[Player.PEOPLE_MET]++;
                  CultimaPanel.player.specificExperience[Player.AWARENESS]++;
               
               }
               selected.setHasMet(true);
               return jesterResponse(selected, typed);
            }
            else
               response = getRandomFrom(fight);
         }
         if(!selected.hasMet())
         {
            Player.stats[Player.PEOPLE_MET]++;
            CultimaPanel.player.specificExperience[Player.AWARENESS]++;
         
         }
         selected.setHasMet(true);
         return response;
      }
      String name = selected.getName().toLowerCase();
      if( selected.getCharIndex()==CHILD)
         return childResponse(selected, typed);
      if(selected.getCharIndex()==SORCERER)
         return doorsPuzzleResponse(selected, typed);   
      if( selected.getCharIndex()==DEMONKING)      //speaking to endgame character Skara Brae
      {
         if(!selected.hasMet())
         {
            Player.stats[Player.PEOPLE_MET]++;
            CultimaPanel.player.specificExperience[Player.AWARENESS]++; 
         }
         selected.setHasMet(true);
         if(selected.getName().contains("Skara Brae"))
            return endGameResponse(selected, typed);
         else if(selected.getName().contains("Puzzle-Giver"))
            return wellsPuzzleResponse(selected, typed);
      }
      if(selected.getCharIndex()==DEMON && selected.swinePlayer())
         return demonSwinePlayer(selected,  typed);
      if(typed.length()==0 && TerrainBuilder.habitablePlace(selected.getLocationType()))
         return getRandomFrom(noText);
      if(bountyOnSelected && (typed.contains("bounty") || typed.contains("kill") || typed.contains("assassin") || typed.contains("murder") || typed.contains("die") || typed.contains("dead") || typed.contains("purge")))
      {
         if(selected.getGold() > 0)
         {
            selected.setGold(Math.max(bountyValue, selected.getGold()));
            if(purgeTownBounty)
               return getRandomFrom(bountyBribeTown).replace("VALUE", ""+bountyValue);
            return getRandomFrom(bountyBribe).replace("VALUE", ""+bountyValue);
         }
         return getRandomFrom(bountyBribeNo);
      }
      if(bountyOnSelected && typed.equals(""+bountyValue) && selected.getGold() > 0)
      {
         if(CultimaPanel.missionTargetNames.contains(Utilities.shortName(selected.getName())))
         {
            CultimaPanel.missionTargetNames.remove(Utilities.shortName(selected.getName()));
         }
         CultimaPanel.player.addGold(bountyValue);
         selected.setGold(0);
         selected.removeBounty();
         CultimaPanel.player.stats[Player.BOUNTIES_FORGIVEN]++;
         CultimaPanel.player.addReputation(10);
         if((selected.getCharIndex()==NPC.WISE || selected.getCharIndex()==NPC.MAN || selected.getCharIndex()==NPC.WOMAN) && selected.getNumInfo()<3)
            selected.setNumInfo(3);
         if((selected.getCharIndex()==NPC.JESTER) && selected.getNumInfo()<3)
            selected.setNumInfo(10);   
         if(purgeTownBounty) 
         {
            for(int i=CultimaPanel.missionStack.size()-1; i>=0; i--)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(m.getType().equals("Purge") && CultimaPanel.player.getWorldRow()==m.getTargetRow() && CultimaPanel.player.getWorldCol()==m.getTargetCol())
               {
                  CultimaPanel.missionStack.remove(i);
                  break;
               }
            }
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            return getRandomFrom(bountyBribeThanksTown);
         }
         return getRandomFrom(bountyBribeThanks);
      }
      if(TerrainBuilder.habitablePlace(selectedLoc) && CultimaPanel.player.threateningWeaponDrawn())
      {   
         if(isGuard(selected.getCharIndex()))
         {
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            CultimaPanel.player.addReputation(-1);
            return getRandomFrom(onGuardForGuard).replace("WEAP_NAME",CultimaPanel.player.getWeapon().getName());
         }
         else
         {
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            CultimaPanel.player.addReputation(-1);
            return getRandomFrom(onGuard).replace("WEAP_NAME",CultimaPanel.player.getWeapon().getName());
         }
      }
      if(!selected.isShopkeep() && CultimaPanel.player.hasEffect("sullied") && !CultimaPanel.player.hasEffect("curse") && !CultimaPanel.player.hasEffect("seduced"))
      {
         CultimaPanel.player.addReputation(-1);               
         return getRandomFrom(sullied);
      }
      if(allignmentCompatable(selected) || charmed || (Math.abs(allignmentDifference(selected))) < (Math.random()*100) || selected.getCharIndex()==TAXMAN)
      {
         byte monsterType = CultimaPanel.assassinAbout;
         if(TerrainBuilder.habitablePlace(selectedLoc) && CultimaPanel.isNight && monsterType!=-1)
         {
            if(!selected.hasMet())
            {
               Player.stats[Player.PEOPLE_MET]++;
               CultimaPanel.player.specificExperience[Player.AWARENESS]++;
            
            }
            selected.setHasMet(true);
            if(monsterType==WEREWOLF)
               return getRandomFrom(werewolfAbout);
            else
               if(monsterType==MALEVAMP || monsterType==FEMALEVAMP)
                  return getRandomFrom(vampireAbout);
               else
                  return getRandomFrom(assassinAbout);
         }
      //ask about NPC's name or home town
         String home = "";
         if(selected.getName().contains(" of ") && selected.getName().length() >= 5)
         {
            int pos = selected.getName().indexOf(" of ");
            home = selected.getName().substring(pos+4).toLowerCase();
         }
      //buy an item from the NPC
         if(selected.hasItem(typed) && !(selected.getCharIndex()==JESTER && typed.contains("lockpick"))) //we handle lockpick selling from jesterResponse method
         {
            Item item = selected.getItem(typed);
            if(item.getName().toLowerCase().contains("message"))
            {
               CultimaPanel.player.addItem(item.getName());
               selected.removeItem(item.getName());
               response = getRandomFrom(giveMessage);
               if(diff > 100  && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
                  response += getRandomFrom(greetings2End); 
               else
                  response += ".";
            }
            else
            {
               selected.setSellPrice(item.getValue());
               response = getRandomFrom(sellItem).replace("SELL_PRICE", (""+selected.getSellPrice()));
               if(diff > 100  && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
                  response += getRandomFrom(greetings2End); 
               else
                  response += ".";
            }
         }
         else if(selected.getItems().size()>0 && typed.equals(""+selected.getSellPrice()) && selected.getSellPrice()!=-1) 
         {
            if(CultimaPanel.player.getGold() >= selected.getSellPrice())
            {
               Item item = selected.getItemForSale();
               CultimaPanel.player.pay(selected.getSellPrice());
               selected.addGold(selected.getSellPrice());
               CultimaPanel.player.addItem(item.getName());
               if(item.getName().contains("book"))
               {
                  boolean success = Journal.copyFromBook(item);
                  if(success)
                     CultimaPanel.sendMessage("Found " + item.getName() + " and copied items into your journal");
                  else
                     CultimaPanel.sendMessage("Found " + item.getName());
                  CultimaPanel.player.addItem(item.getName());   
               }
               selected.removeItem(item.getName());
               response = getRandomFrom(sellItemComplete);
               if(diff > 100  && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
                  response += getRandomFrom(greetings2End); 
               else
                  response += ".";
            }
            else
            {
               response = getRandomFrom(bribeNoMoney)+".";
            }
         }   
         else if(typed.contains("name"))
         {
            String selName = selected.getName();
            if(selName.contains("~"))  //werewolf
            {
               String [] nameParts = selName.split("~");
               if(nameParts.length == 2)
                  selName = nameParts[0];
            }
            if(selName.startsWith("+"))
            {
               selName = selName.substring(1);
               String [] ourSpouseEnding = {"", "my love", "my beloved", "my sweet", "my flower", "my petal"};
               String [] ourSpouseName = {"'Tis NAME, thy very spouse END!", "Dost thou not know the name of thy love END!", "NAME, of course. I am thy spouse END!", "'Tis silly to ask the name of thy spouse eternal END!"}; 
               response = getRandomFrom(ourSpouseName).replace("NAME",selName).replace("END",getRandomFrom(ourSpouseEnding));  
            }
            else
            {
               response = getRandomFrom(preName).replace("NAME",selName);  
               if(diff > 100  && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
                  response += getRandomFrom(greetings2End); 
               else
                  response += ".";
            }
         }
         else if(typed.contains("home") || (selected.getName().contains(" of ") && home.equals(typed)))
         {
         //try to trace a path to that town
            if(selected.getName().contains(" of "))
            {
               int found = TerrainBuilder.markMapPath(home);
               if(found==1)
                  response = getRandomFrom(homeTown);
               else if(found==-1)
                  response = getRandomFrom(noHomeTown);
               else
                  response =getRandomFrom(knowHomeTown);
            }
            else
               response = getRandomFrom(thisHomeTown);
            if(diff > 100 && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
               response += getRandomFrom(greetings2End); 
            else
               response += ".";
         }     
         else if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
         {  //general greeting of NPC
            if(diff > 100 && selected.getCharIndex()!=KING && CultimaPanel.player.getReputation() >= 500)
            {
               if(!selected.hasMet() || Math.random() < 0.5)
               {
                  if(selectedLoc.contains("city"))
                     response = getRandomFrom(greetings2City) + getRandomFrom(greetings2End);
                  else
                     response = getRandomFrom(greetings2) + getRandomFrom(greetings2End);
               }
               else
               {
                  if(selectedLoc.contains("city"))
                     response = getRandomFrom(greetings2City) + " " + CultimaPanel.player.getShortName()+".";         
                  else
                     response = getRandomFrom(greetings2) + " " + CultimaPanel.player.getShortName()+".";         
               }
            }
            else
            {
               if(!selected.hasMet() || Math.random() < 0.5)
               {
                  if(selectedLoc.contains("city"))
                     response = getRandomFrom(greetingsCity) + getRandomFrom(greetingsEnd);
                  else
                     response = getRandomFrom(greetings) + getRandomFrom(greetingsEnd);
               }
               else
               {
                  if(selectedLoc.contains("city"))
                     response = getRandomFrom(greetingsCity) + " " + CultimaPanel.player.getShortName()+".";
                  else
                     response = getRandomFrom(greetings) + " " + CultimaPanel.player.getShortName()+".";
               }
            }
         }
         else if(selected.isSellingHouse() && selected.getNumInfo()==10)
            response = generalResponse(selected, typed);
         else if(name.contains("arenamaster"))
            response = arenamasterResponse(selected, typed);    
         else if(isGuard(selected.getCharIndex()))
            response = guardResponse(selected, typed);
         else if(selected.getCharIndex()==NPC.TAXMAN)
            response = taxmanResponse(selected, typed);   
         else if(selected.getCharIndex()==BEGGER)
            response = beggerResponse(selected, typed);
         else if(selected.getCharIndex()==JESTER)
            response = jesterResponse(selected, typed);
         else if(selected.getCharIndex()==LUTE)
            response = luteResponse(selected, typed); 
         else if(selected.getLocationType().toLowerCase().contains("village") && CultimaPanel.numNPCsInLoc==1)
            response = hermitResponse(selected, typed);  
         else if(name.contains("ironsmith"))
            response = ironsmithResponse(selected, typed);
         else if(name.contains("mage"))
            response = mageResponse(selected, typed);
         else if(name.contains("butcher") || name.contains("baker"))  
            response = butcherBakerResponse(selected, typed);
         else if(name.contains("barkeep"))
            response = barkeepResponse(selected, typed);
         else if(selected.getCharIndex()==MAN || selected.getCharIndex()==WOMAN)
            response = manWomanResponse(selected, typed);
         else if(selected.getCharIndex()==WISE)
            response = wiseResponse(selected, typed);
         else if(selected.getCharIndex()==KING)
            response = kingResponse(selected, typed);
         else if(selected.getCharIndex()==SWORD)
            response = swordResponse(selected, typed);
         else     //NPC does not understand
            response = generalResponse(selected, typed);
      }
      else        //NPC does not want to talk to you
      {
         if(typed.contains("bribe") && isGuard(selected.getCharIndex()))  //but guards are still open to being bribed
         {
            if((selected.getReputation() >= 100 && !charmed) || selected.getCharIndex()==ROYALGUARD)
               response = getRandomFrom(bribeNo);
            else
            {
               if(CultimaPanel.player.getGold() < 10)
                  response = getRandomFrom(bribeNoMoney);
               else
               {
                  if(Math.random()<0.66 && !charmed)
                  {
                     response = getRandomFrom(bribeMaybe);
                     CultimaPanel.player.pay(10);
                     selected.addGold(10);
                  }
                  else
                  {
                     response = getRandomFrom(bribeYes);
                     NPCPlayer temp = selected.clone();
                     temp.setRestoreDay(CultimaPanel.days+1);
                     CultimaPanel.NPCsToRestore.add(temp);
                     selected.setMoveTypeTemp(NPC.ROAM);
                     selected.setNumInfo(13);   //flag that the guard has been bribed
                     selected.setHasMet(true);
                     CultimaPanel.player.pay(10);
                     selected.addGold(10);
                     Player.stats[Player.GUARDS_BRIBED]++;
                     CultimaPanel.talkSel = false;
                     selected.setTalking(false);
                     CultimaPanel.selected = null;
                     CultimaPanel.selectedTerrain = null;
                     CultimaPanel.typed = "";
                     if(!selected.hasMet())
                     {
                        Player.stats[Player.PEOPLE_MET]++;
                        CultimaPanel.player.specificExperience[Player.AWARENESS]++;
                     
                     }
                     selected.setHasMet(true);
                     return response;
                  }
               }
            }
         }
         else if(selected.getCharIndex()==BEGGER)     //beggers will always accept charity, even if they are alignment opposed to player
         {
            if((typed.contains("coin") || typed.contains("charity") || typed.contains("give") || typed.contains("gold") || typed.contains("pay")) && CultimaPanel.player.getGold() >= 1)
            {
               CultimaPanel.player.pay(1);
               selected.addGold(1);
               Player.stats[Player.MONEY_DONATED]++;
               if(CultimaPanel.player.stats[Player.MONEY_DONATED]>=500)
                  Achievement.earnAchievement(Achievement.PHILANTHROPIST);
               if(selected.getNumInfo() > 0)
               {  //don't get more than 3 reputation points for giving to a begger
                  CultimaPanel.player.addReputation(1);
                  selected.setNumInfo(selected.getNumInfo()-1);
               }
               response = getRandomFrom(beggerThanks);
               if(diff > 100)
                  response += getRandomFrom(greetings2End); 
               else
                  response += "!";
            }
            else if(typed.contains("ration") && CultimaPanel.player.getRations() >= 5 && !CultimaPanel.player.isVampire())
            {
               for(int i=0; i<5; i++)
                  CultimaPanel.player.useRation();
               Player.stats[Player.MONEY_DONATED]++;
               if(selected.getNumInfo() > 0)
               {  //don't get more than 3 reputation points for giving to a begger
                  CultimaPanel.player.addReputation(1);
                  selected.setNumInfo(selected.getNumInfo()-1);
               }
               response = getRandomFrom(beggerThanks);
               if(diff > 100)
                  response += getRandomFrom(greetings2End); 
               else
                  response += "!";
            }
            else
            {
               response = getRandomFrom(begger);
               if(diff > 100)
                  response += getRandomFrom(greetings2End); 
               else
                  response += "?";
            }
         }
         else
         {
            if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
            {
               if(!selected.hasMet())     //greeting an oppositional NPC will warm them 10 points to your favor
               {
                  if(CultimaPanel.player.getReputation() > selected.getReputation())
                     selected.setReputation(selected.getReputation() + 10);
                  else
                     selected.setReputation(selected.getReputation() - 10);
               }
            }
            String end = getRandomFrom(dissEnd);
            if(end.length() > 1)
            {
               if(Math.random() < 0.5)
                  response = getRandomFrom(diss) + ", you "+getRandomFrom(monsterAdjective)+" "+end;
               else
                  response = getRandomFrom(diss) + ", "+end;
            }
            else
               response = getRandomFrom(diss) + end;
         }
      }  
      if(!selected.hasMet())
      {
         Player.stats[Player.PEOPLE_MET]++;
         CultimaPanel.player.specificExperience[Player.AWARENESS]++;
      
      }
      selected.setHasMet(true);
      if(selected.getCharIndex()==TAXMAN && CultimaPanel.days > selected.getEndChaseDay() && selected.getNumInfo()!=10)
         return response + ".. "+CultimaPanel.player.getTaxesOwed()+" gold, please";
      return response;
   }

    //post:  returns the price of the house that selected is willing to sell
   public static int housePrice(NPCPlayer selected)
   {
      byte[][]currMap = (CultimaPanel.map.get(selected.getMapIndex()));   
      int houseSize = 0;
      for(int r=0; r<currMap.length; r++)
         for(int c=0; c<currMap[0].length; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
            if(current.contains("purple"))
               houseSize++;
         }
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      int amount = Math.min(5000, (houseSize * 10 + 9));
      if(charmed)
         amount = (int)(amount * 0.8);      
      return amount;
   }
   
   public static void buyHouse(NPCPlayer selected)
   {
      buyHouse();
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
         selected.setHomeRow((int)(newHomeLoc.getX()));
         selected.setHomeCol((int)(newHomeLoc.getY()));  
      }
      selected.setMoveType(NPC.ROAM);
   }
   
   public static ArrayList<Point> buyHouse()
   {
      byte[][]currMap = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex()));   
      int houseSize = 0;
      ArrayList<Point> floorLocs = new ArrayList<Point>();
      ArrayList<Point> wardrobeLocs = new ArrayList<Point>();
      for(int r=0; r<currMap.length; r++)
         for(int c=0; c<currMap[0].length; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
            if(current.contains("purple"))
            {
               currMap[r][c] = TerrainBuilder.getTerrainWithName("INR_$Purple_floor_inside").getValue();
               floorLocs.add(new Point(r, c));  
               if(!LocationBuilder.nextToADoor(currMap, r, c) && !LocationBuilder.nextToDirt(currMap, r, c))
                  wardrobeLocs.add(new Point(r, c));
            }
         }
      if(CultimaPanel.player.isVampire())    //place a coffin for a vampire
      {
         if(floorLocs.size() > 0)
         {
            Point coffinLoc = floorLocs.get((int)(Math.random()*floorLocs.size()));
            currMap[(int)(coffinLoc.getX())][(int)(coffinLoc.getY())] = TerrainBuilder.getTerrainWithName("INR_$Coffin_bed").getValue();
         }
      }
      if(wardrobeLocs.size() > 0)
      {
         Point wardrobeLoc = wardrobeLocs.get((int)(Math.random()*wardrobeLocs.size()));
         currMap[(int)(wardrobeLoc.getX())][(int)(wardrobeLoc.getY())] = TerrainBuilder.getTerrainWithName("INR_L_2_$Wardrobe").getValue();
      }
      Player.stats[Player.HOUSES_BOUGHT]++;
      return floorLocs;
   }

   public static String sellingHouse(NPCPlayer selected, String typed)
   {
      String [] sellAmount = {"T'will cost thee AMOUNT gold", "This beautiful property for AMOUNT coins", "This can be all yours for AMOUNT gold", "AMOUNT gold and this lovely home is yours"};
      String [] soldThanks = {"I thank thee for our trade", "Thank thee. I hope this HOME brings thee happiness", "May SKARA Brae shine on thy new home eternal", "This fine HOME is now yours. I thank thee"};
      int price = housePrice(selected);
      if(typed.contains("house"))
         return getRandomFrom(sellAmount).replace("AMOUNT", ""+price);
      if(typed.equals(""+price))
      {
         if(CultimaPanel.player.getGold() < price)
            return getRandomFrom(bribeNoMoney);
         buyHouse(selected);
         CultimaPanel.player.pay(price);
         selected.addGold(price);
         selected.setNumInfo(3);
         if(selected.getCharIndex()==NPC.BEGGER)      //begger given a lot of money, so restore them to their feet
         {
            if(!allignmentCompatable(selected))       //we gave them money to restore them, so make them alignment compatible with us
               selected.setReputation(CultimaPanel.player.getReputationRaw());
            int restoreType = (int)(Math.random()*7);
            if(restoreType<=2)
            {
               selected.setCharIndex(MAN);
               selected.setNumInfo(3);    //a man with a numInfo of 3 is the flag that they will map for free
            }
            else if(restoreType<=5)
            {
               selected.setCharIndex(WOMAN);
               selected.setNumInfo(3);    //a woman with a numInfo of 3 is the flag that they will map for free
            }
            else //if restoreType==6
            {
               selected.setCharIndex(WISE);
               selected.setNumInfo(3);    //a wise-man with a numInfo of 3 is the flag that they will teach us a spell
            }   
            selected.setMoveType(ROAM);
         //remove any current missions with players in this location since they are no longer a begger
            String NPCmiss = selected.getMissionInfo();
            if(!NPCmiss.equals("none"))
            {
               for(int i=0; i<CultimaPanel.missionStack.size(); i++)
               {
                  Mission m = CultimaPanel.missionStack.get(i);
                  if(NPCmiss.equals(m.getInfo()))
                  {
                     CultimaPanel.missionStack.remove(i);
                     break;
                  }
               }
            }
         }
         return getRandomFrom(soldThanks);
      }  
      return getRandomFrom(houseToSell);
   }
         
   public static int amountToTrainJester(NPCPlayer selected)
   {
      int diff = allignmentDifference(selected);
      int amount = 1000 * CultimaPanel.player.getAgilityRaw()/10;
      if(Math.abs(diff) > 1000)        //discount if your allignment is higher than the jester
         amount /= 2;
      else if(Math.abs(diff) > 500)
         amount = amount - (amount/3);
      else if(Math.abs(diff) > 100)
         amount = amount - (amount/4);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      if(charmed)
         amount = (int)(amount * 0.8);   
      return Math.max(500,amount);
   }

   public static int amountToTrainGuard(NPCPlayer selected)
   {
      int diff = allignmentDifference(selected);
      int amount = 1000 * (CultimaPanel.player.getMightRaw()/10);
      if(Math.abs(diff) > 1000)        //discount if your allignment is higher than the guard
         amount /= 2;
      else if(Math.abs(diff) > 500)
         amount = amount - (amount/3);
      else if(Math.abs(diff) > 100)
         amount = amount - (amount/4);   
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      if(charmed)
         amount = (int)(amount * 0.8);   
      return Math.max(500,amount);
   }

   public static int amountToTrainWise(NPCPlayer selected)
   {
      int diff = allignmentDifference(selected);
      int amount = 1000 * Math.max(CultimaPanel.player.getAwareness()+1, CultimaPanel.player.getMind()/10);
      if(CultimaPanel.player.getMind() < 50)
         amount = 1000 * (CultimaPanel.player.getMind()/10);
      if(Math.abs(diff) > 1000)        //discount if your allignment is higher than the wiseman
         amount /= 2;
      else if(Math.abs(diff) > 500)
         amount = amount - (amount/3);
      else if(Math.abs(diff) > 100)
         amount = amount - (amount/4);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      if(charmed)
         amount = (int)(amount * 0.8);      
      return Math.max(500,amount);
   }

   public static String marriage(NPCPlayer selected, String typed)
   {
      String [] married = {"I have been happily married for YEARS seasons POST", "My heart already belongs to my loyal spouse POST", "YEARS seasons have passed since I spoke my marriage vows POST", "I am flattered, but my hand has already been taken for YEARS seasons now POST"};
      String [] ourSpouse = {"I am bound to thee", "Tis my heart thee has 'til the end", "Our vows bind us forever", "My hand is yours - SKARA Brae rejoice"};
      String [] widowed = {"My love has passed...I cannot let their memory go POST", "'Tis been YEARS seasons since my love perished POST", "I still mourn the loss of my love POST", "I lost my love YEARS seasons ago. I mourn still POST"};
      String [] notInterested = {"I am flattered, but I prefer the company of a different...type POST", "'Tis said crassly by others that I am not the marrying kind POST", "I seek the attention of another kind POST", "We are not of the same preferences POST"};
      String [] single = {"Does thou seek my hand in VOWS of marriage POST", "Is it my hand you would like to hold in VOWS of marriage POST", "Is it VOWS of marriage thee offers POST", "Dost thou ask to recite marrige VOWS with thee POST"};
      String [] singleWerewolf = {"Before asking my VOWS of marriage, know I carry the Wolfen curse POST", "Is it my Wolfen cursed hand you would like to hold in VOWS of marriage POST", "Is it VOWS of marriage thee offers, despite my Wolfen curse POST", "Dost thou ask to recite marrige VOWS with thee, even with my Wolfen curse POST"};
      String [] cursed = {"I sense a curse on thee. I fear thy company POST", "Your presence makes thee afraid. I cannot POST", "Thy curse makes thee a danger. I feel fear from thee POST", "Purge thy curse and I will consider POST"};
      String [] reputation = {"Thou has a soiled reputation. I cannot marry thee!", "Thy reputation casts you a REPUTATION! You may not have my hand!", "I shall not exchange vows with a REPUTATION!", "Improve thy deeds, REPUTATION, or nobody will take thy hand in marriage!"};
      String [] noHouse = {"Thee needs a HOUSE in this town to offer marriage POST", "We need a HOUSE before speaking vows POST", "Raise a HOUSE in this town before offering thy hand POST", "My hand is only for one who owns a HOUSE in this town POST"};
      String [] alreadySpouse = {"Thou is already wed to our very own NAME", "Thy boldness! Thee is married to my friend NAME!", "What does NAME think of thee breaking thy vows!", "Thee is already wed to NAME! Outrage!"};
      String [] divorce = {"Does thou intend to SEVER our bonds", "Is it thy intent to SEVER our bonds of marriage", "Dost thou wish to SEVER thy eternal bonds of love", "What's this?  Does thou mean to SEVER our bonds of marriage"};
      String [] highStatusEnd = {"", "my liege", "my lord", "my champion", "my master", "oh great one"}; 
      String [] ourSpouseEnding = {"", "my love", "my beloved", "my sweet", "my flower", "my petal"}; 
      String ending = "";
    
      int diff = allignmentDifference(selected);
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         ending = getRandomFrom(highStatusEnd);
      int timeMarried = (Math.abs(selected.getName().hashCode())%30)+2;
      String response = "";
      if(selected.getMaritalStatus() == 'M')
      {
         if(selected.getName().startsWith("+")) //it is our own spouse
         {
            if(typed.startsWith("i") && typed.endsWith("you"))
            {
               String [] parts = typed.split(" ");
               if(parts.length == 3)
               {
                  String middleWord = parts[1].trim();
                  return ("I "+middleWord+" thee as well POST").replace("POST", getRandomFrom(ourSpouseEnding));
               }
               return ("I love thee as well POST").replace("POST", getRandomFrom(ourSpouseEnding));
            }
            if(typed.contains("divorce") || typed.contains("annulment") || typed.contains("separat"))
               return getRandomFrom(divorce).replace("POST", ending);
            if(typed.contains("sever"))
            {
               selected.setNumInfo(13);
               return infidelity(selected, typed);   
            }
            return getRandomFrom(ourSpouse).replace("POST", getRandomFrom(ourSpouseEnding));
         }
         return getRandomFrom(married).replace("YEARS", ""+timeMarried).replace("POST", ending);
      }
      if(selected.getMaritalStatus() == 'W')
         return getRandomFrom(widowed).replace("YEARS", ""+timeMarried).replace("POST", ending);
      if(selected.getMaritalStatus() == 'N')
         return getRandomFrom(notInterested).replace("POST", ending); 
      if(selected.getMaritalStatus() == 'S')
      {
         if(CultimaPanel.player.isVampire() || CultimaPanel.player.isWerewolf() || CultimaPanel.player.hasEffect("curse"))
         {
            if(CultimaPanel.player.isWerewolf() && selected.isWerewolf())
            {}
            else
               return getRandomFrom(cursed).replace("POST", ending); 
         }
         int reput = CultimaPanel.player.getReputation();
         int repIndex = 3;
         if(reput >= 1000)
            repIndex = 6;
         else if(reput >= 500)
            repIndex = 5;
         else if(reput >= 10)   
            repIndex = 4;
         else if(reput >= -10)
            repIndex = 3;
         else if(reput >= -500)
            repIndex = 2;
         else if(reput >= -1000)
            repIndex = 1;
         else
            repIndex = 0;
         String repName = Player.reputationNames[repIndex];
         if(CultimaPanel.player.getReputation() < -100 || (CultimaPanel.player.getReputation() < 0 && diff > 100 && selected.getReputation() > CultimaPanel.player.getReputation()))
            return getRandomFrom(reputation).replace("REPUTATION", repName); 
         Point ourBed = LocationBuilder.findOurBed();    //see if we own a house in this city
         Point ourBed2 = LocationBuilder.findOurBed2();  //see if we could own a house in this city, in case this is for a Marry mission
         //see if we have a mission to marry this person to bypass ourBed
         boolean marryMission = false;
         if(CultimaPanel.missionTargetNames.contains(Utilities.shortName(selected.getName())))
         {
         
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(m.getType().equals("Marry") && m.getTargetHolder().contains(Utilities.shortName(selected.getName())))
               {
                  marryMission = true;
                  break;
               }
            }
         } 
         if((ourBed == null && !marryMission) || (marryMission && ourBed2==null))
            return getRandomFrom(noHouse).replace("POST", ending);  
         if(marryMission && ourBed==null && ourBed2!=null)
            ourBed = ourBed2;   
         if(typed.contains("vows"))
         {
            NPCPlayer spouse = CultimaPanel.player.spouseInLocation();
            if(spouse != null)            //we are asking someone to marry when already married to someone in the same town
            {
               CultimaPanel.player.addReputation(-1);
               if(Math.random() < 0.25)   //spouse finds out
                  spouse.setNumInfo(13);  //set value to trigger infidelity event
               return getRandomFrom(alreadySpouse).replace("NAME", Utilities.shortName(spouse.getName().substring(1)));
            } 
            if(Player.countSpouses()==0) //inherit half the spouse's reputation points
            {
               if(selected.getReputation() > CultimaPanel.player.getReputation())
                  Achievement.earnAchievement(Achievement.MARRYING_UP);
               CultimaPanel.player.addReputation(selected.getReputation()/2);  
            }         
            selected.setHomeRow((int)(ourBed.getX()));
            selected.setHomeCol((int)(ourBed.getY()));
            selected.setName("+"+selected.getName());
            selected.setMaritalStatus('M');
            selected.setMoveType(NPC.ROAM);
            selected.setNumInfo(3);
            Player.stats[Player.TIMES_MARRIED]++;
            return getRandomFrom(ourSpouse).replace("POST", getRandomFrom(ourSpouseEnding));
         }
         if(selected.isWerewolf() && (selected.getReputation() >= 500 || CultimaPanel.player.isWerewolf()))
            return getRandomFrom(singleWerewolf).replace("POST", ending);   
         return getRandomFrom(single).replace("POST", ending);   
      }
      return response;
   }

   public static String infidelity(NPCPlayer selected, String typed)
   {
      String [] caught = {"I know you have taken vows with another. We are done END", "You have broken our vows! I know of your other love END", "I hear of thy romances outside of our bond! I take my leave END", "I am leaving! I know of thy other spouse END"};
      String [] divorce = {"I know you aim to take vows with another. We are done END", "You have broken our vows! I am leaving END", "Thee must have romances outside of our bond! I take my leave END", "I am leaving END"};
      String[] dissEnd = {"!", ", knave!", ", peasant!", ", scoundrel!", ", monster!", ", slime!"};
      ArrayList<Point> newLocs = new ArrayList<Point>();
      byte[][]currMap = (CultimaPanel.map.get(selected.getMapIndex()));   
      for(int r=0; r<currMap.length; r++)
         for(int c=0; c<currMap[0].length; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(currMap[r][c])).getName().toLowerCase();
            if(!current.contains("purple") && TerrainBuilder.isInsideFloor(current))
               newLocs.add(new Point(r, c));   
         }
      if(newLocs.size() > 0)              //move spouse to a new place
      {   
         Point newHomeLoc = newLocs.get((int)(Math.random()*newLocs.size()));
         selected.setHomeRow((int)(newHomeLoc.getX()));
         selected.setHomeCol((int)(newHomeLoc.getY()));  
         if(Math.random() < 0.66)
            selected.setMoveTypeTemp(NPC.RUN);
         else
            selected.setMoveTypeTemp(NPC.CHASE);
      }
      else
         selected.setMoveTypeTemp(NPC.CHASE);
    
      selected.setName(selected.getName().substring(1));    //remove the + character at the front of the name (tagged as your spouse)
      selected.setMaritalStatus('N');
      if(typed.contains("sever"))
         selected.setReputation(-1*CultimaPanel.player.getReputation());
      else
         selected.setReputation(-10*CultimaPanel.player.getReputation());
      CultimaPanel.player.addReputation(-10);
      if(Math.random() < 0.25)
         CultimaPanel.player.setBounty(CultimaPanel.player.getBounty()+Player.rollDie(1,10));
      CultimaPanel.talkSel = false;
      selected.setTalking(false);
      CultimaPanel.selected = null;
      CultimaPanel.selectedTerrain = null;
            
      String response = "";
      if(typed.contains("sever"))
         response = getRandomFrom(divorce).replace("END", getRandomFrom(dissEnd));
      else
      {
         response = getRandomFrom(caught).replace("END", getRandomFrom(dissEnd));
         Achievement.earnAchievement(Achievement.WALKING_PAPERS);
      }
      CultimaPanel.typed = "";
   
      return response;
   }

   public static String generalResponse(NPCPlayer selected, String typed)
   {
   //general responses before the unknown response
      String[] SkaraBrae = {"She is the tie to the etherworld, above and below, FUTURE and past", "She holds the crown over all that is known and unknown", "Lifted her wings from our plane some 50 by 50 years before time itself", "She left out ancestors on this earth to rule the worlds beyond", "'Tis said she can be found still, in some place in this land, by some NUMBER"};
      String[] goldGeneral = {"'Tis what we TRADE for goods", "'Tis what we make our coins", "'Tis a precious metal", "'Tis a pretty color", "'Tis our currency"};
      String[] adventureGeneral = {"'Tis too dangerous for me", "I will not tread there", "I hear some adventurers have not returned", "Only the bold tread there", "'Tis wrought with peril", "May the crown of SKARA Brae protect those that tread in such a vile place"};
      String[] dogGeneral = {"The lady of the CASTLE trains the dogs around here", "Seek the lady of the CASTLE to learn of training thy dog", "A lady of the CASTLE has good knowledge of training thy dogs", "'Tis the lady of the CASTLE that can train thy dog"};
      String[] monsterGeneral = {"Thy adventureres can tell you more of beasts", "Advernurers know all about the beasts", "Seek those with swords to tell you of beasts", "'Tis the swordsmen that know about monsters"};
      String[] lairGeneral = {"Thy swordsmen know of the location of monster lairs", "The adventurers in town know where the lairs are", "Ask those with swords - they have been to the monster lairs", "'Tis the swordsmen that have the information you seek"};
      String[] spellGeneral = {"Seek one with a staff: they know of spells and thy FUTURE", "'Tis the wiseman you seek to learn magic, and know thy FUTURE", "The mage with the staff can tell you of magic and WEATHER", "The magic is held in a staff: the staff is held by the wiseman. The wisemen know of thy FUTURE"};
      String[] townGeneral = {"Thy barkeep knows of everyone in town", "The townsfolk are known by the barkeep", "If you want to know of the townsfolk, ask the barkeep", "Thy pub has a barkeep who knows everyone in town"};
      String[] tradeGeneral = {"Seek thee a shoppekeep to trade", "I am not a shoppekeep", "I have nothing to trade: find thee a shoppekeep", "Perhaps you are looking for a shoppe of some kind"};
      String[] cureGeneral = {"Seek thee a mage's SHOPPE to get a cure for thy poison", "Thy mage's SHOPPE offers cures and HEALING", "Look for the sign with the blue flask for cures and HEALING", "Seek thee a mage's SHOPPE for curing and HEALING"};
      String[] curseGeneral = {"Seek thee a lute player", "Thy lute player knows music that can cast out thy curse", "Look for the man with the lute: his song can cast out a curse", "Seek thee a lute player and request a song"};
      String[] trainGeneral = {"There are those who can focus thy skill with training: I am not among them", "Guards have great might: ask them to train thy strength", "Mages may be able to train thee to focus thy mind", "Castle jesters train with great agility", "'Tis said the hermits train their awareness to thy world around"};
      String[] mapGeneral = {"Those from other lands might mark their HOME on your map when asked", "Thy general citizens can aquaint you with thy map's unknown regions for a price", "Thy Butcher at the Rations SHOPPE knows much about mapping", "I am not skilled at mapping, but others might help. Ask the Butcher in the Rations SHOPPE"};
      String[] rationGeneral = {"Thy local butcher sells rations.", "Some get thy rations from hunting game.", "A DOG makes for a fine companion when hunting game for rations.", "Thy baker sells five days ration for one gold coin: 'tis a bargain!"};
      String[] bribeGeneral = {"Why does thou want to bribe me...","I have nothing for thee to bribe","I am not in need of bribing", "A bribe? I think thou to be confused"};
      String[] dogCommands = {"Does thou look like a trained DOG to thee? I think not","I am not a trained DOG","I will not answer to a DOG command","You mistake me for a DOG of the CASTLE","The lady of the CASTLE uses these commands with dogs"};
      String[] armsGeneral = {"Thy FUTURE is with our local ironsmith: they know of weapons", "'Tis the ironsmith you seek to learn of arms", "The ironsmith at the SHOPPE can tell you of weaponry", "The SHOPPE with the shield on the sign: there you can learn of armaments"};
      String[] trapGeneral = {"Thy local ironsmith knows much about traps", "Seek thy weapons SHOPPE. The ironsmith knows of traps", "The ironsmith can tell thee how to best use traps", "Ask the ironsmith about traps"};
      String[] bountyGeneral = {"There's no bounty on my head, so far as I can tell","Those that do wicked things earn a bounty","The assassins will seek those with a high bounty", "A bounty? On me? I think thou to be confused"};
      String[] pickGeneral = {"I am not a thief","The last I saw a lockpick, 'twas in the hands of a jester","Ask me not of tools of knaves","You mistake me for a thief, or a jester"};
      String[] messageGeneral = {"I have no message for you","I have no need to send a message","There are those that need messages delivered. I am not among them","You mistake me for someone else: I have no message"};
      String[] weatherGeneral = {"Thy weather? Ask the wiseman", "The mages are fine at predicting the weather", "The weather's prediction is best made by the mages", "Try the wiseman - thy mages know the weather's future", "Mind the weather before travel - the wise with staff knows", "Spring and Autumn bring higher chances of fog, making travel and combat difficult", "Mind the coming of Winter's cold and have beast furs to protect thee", "Thy torch will not work in a driving rain - mind the weather before travel", "Thieves take advantage of the blanket of fog and distraction of storm to work their CRAFT"};
      String[] cityAsk = {"Have you been to our grand CAPITAL before?", "Our greatest city is our very CAPITAL", "Thy CAPITAL is the most grand city", "The largest of cities in the land is our very CAPITAL"};  
      String[] capitalLoc = {"Our Capital is in the province of PROVINCE_NAME", "Our Capital is at CAPITAL_COL East and CAPITAL_ROW South", "Go CAPITAL_COL East and CAPITAL_ROW South and thee will find PROVINCE_NAME", "You can travel to PROVINCE_NAME by going to CAPITAL_COL East and CAPITAL_ROW South", "ASSYRIA 'tis a place whose capital is quite lovely", "Our capital is home to King GARRIOTT", "You must travel to our capital, where King GARRIOTT lives"};
      String[] capitalNo = {"Our great Capital: 'tis very far away", "I cannot tell thee how to get there", "I know not the way to our Capital", "I cannot remember the way to the Capital"};
      String[] assyriaCapital = {"Nineveh is the capital of Assyira, of course...", "The capital of Assyria? 'Tis Kalhu", "Assur! 'Tis well known to be the capital of Assyria", "Harran is Assyria's capital, for SKARA Brae's sake", "'Tis commonly known to all! Kar-Tukulti-Ninurta is the capital of Assyria","Dur-Sharrukin is the well known Capital of Assyria", "Carchemish is the name of Assyria's capital! How does thee not know that", "Ekallatum! 'Tis known across the land to be the capital of Assyria", "Tell-leilan, 'tis easy to remember as the capital of Assyria"};
      String[] swallowGeneral = {"'Tis 11, or 24...not sure which, nor what it means...", "'Tis 24...no...11...", "I know 'tis either 11 or 24, but know not the units...", "What it means, 'tis unknown to me, but 11 and 24 strike thy memory"};
      String[] vampireGeneral = {"Ask the man of the CASTLE about Vampyres", "Vampyres you say? Ask thy man of the CASTLE", "The man of the CASTLE knows of Vampyres. Ask him", "'Tis the man of the CASTLE that has experience with Vampyres"};
      String[] werewolfGeneral = {"Thy woman of the temple has knowledge of Wolfen", "Did thou say Wolfen? Ask thy woman of the temple", "The woman of the temple has experience with Wolfen", "Were-wolf?  There-wolf."};
      String[] werewolfInTown = {"Me thinks one in this very town has the Wolfen curse", "Some in this town have gone missing to be found half-eaten - 'tis the work of a Werewolf", "One among us has the Wolfen curse, becoming a murderous BEAST by night", "This CITY is cursed at night by a ravaging monster: a Werewolf", "NPCNAME went missing, another victim of the cursed Werewolf"};
      String[] isWerewolfPurge = {"We did not chose thy Wolfen curse. We just want to be left be", "We Wolfen cursed have outcast ourselves as to not threaten others. Let us be in peace", "We deserve to be left in peace. Thy Wolfen curse was not asked for", "Our cuse is a heavy burden. We live far from others as to not be a threat"};
      String[] isWerewolfDeny = {"I...a WEREWOLF?  I don't know what you mean to say...", "WEREWOLF!?! Say nothing more of it", "I...will not speak on such things as werewolves", "How dare you accuse me of such ghoulish things! Werewolves...'tis rubbish..."};
      String[] isWerewolfAccept = {"I...know not what to say on werewolves...", "...the Wolfen curse...'tis unbearable weight to carry...", "They with the Wolfen curse...'tis not their fault...I...they need help", "Most terrible, the Wolfen curse...so much guilt..."};
      String[] arenaGeneral = {"Some large cities, like our CAPITAL, have an arena for the commoner's entertainment", "Brave warriors fight beasts in the arena for coin and glory", "They who can complete the fify arena challenges will be crowned the Arena Champion", "The Arena Champion is granted the silken Arenacape, said to have magic woven within"};
      String[] houseGeneral = {"One gets better rest with slumber in thy own home", "A home with a spouse will allow thee to fill with RATIONS before long travels", "A home is needed before one can take a vow of marriage", "Some that look to travel will sell their home when asked"};
      String[] rollPassGeneral = {"Rolling and passing are moves for the game we call SWINE", "Thy pass and roll are done in games of SWINE", "One can pass and roll in a good game of SWINE", "Tis the game SWINE where one might roll or pass"}; 
      String[] deathGeneral = {"Say not her name, lest she comes for thee", "Still thy tongue! Invoke not her name, lest ye invite her visit", "Say not her name aloud! If she comes for thee, run", "Her name spoken is an invitation! Speak it only in whispers", "Thy Adventurer speaks of her in whispers"};
      String[] legend = {"I have heard the tales of legendary weapons, yes", "Seek the ironsmith and the keeper of the mage's SHOPPE to learn of legendary weapons", "Legendary weapons combine the finest iron forging and powerful magic", "The ironsmith knows of forging, the keeper of the mage's SHOPPE knows of MAGIC. Both will know of legendary weapons"};
      String[] puzzle = {"I have not the skills for puzzles", "Those older and wiser often know of puzzles", "I have not the years to contemplate puzzles well", "A wise one, older than I will know of puzzles", "For aid with puzzles, seek those older and wiser"};
      String[] lordBritish = {"The King of all Cultima lives in the castle of the CAPITAL", "King Garriott knows all of Cultima, very wise is he", "Lord and King Garriott the Wise has a CASTLE in the CAPITAL", "'Tis said that King Garriott is the most wise in the land"};
      String[] eggGeneral = {"Bird eggs found around these lands make for a good day's RATION", "'Tis known that a serpent's egg might cure an adventurer whose blood is sickened with poision", "The Royal Guard speaks of a Dragon's egg, and the strength it gives when consumed", "He who knows all of Cultima, King GARRIOTT, is said to have the Wisdomegg", "The Mages might know more about the legendary Wisdomegg"};
      String[] kingChallenge = {"How dare thee profane thy Royalty! Guards!", "Such words in the presence of thy Masters! Outrage! Guards!", "Cursed words in the face of Royalty! Sieze the profaner!", "Outrage, such words! Guards! Teach this profaner a lesson!"};
      String[] guardChallenge = {"How dare thee speak such words to me! You shall test my blade!", "Thy filthy mouth needs rearranging! On guard!", "You speak filth! You tested thy patience, now test thy balde!", "You speak as thy sailors! Prepare to regret thy words!"};
      String[] jesterChallenge = {"Insult me? You empty-headed animal food trough wiper!", "Time for insults? Your mother was a hamster and your father smelt of elderberries!", "Right back to you! No, now go away or I shall taunt you a second time!", "Go and boil your bottoms, you sons of a silly person!"};
      String[] commonChallenge = {"Such language! Thy conversation has ended!", "I will not hear such filth! Speak to thy dust as I exit this conversation!", "Watch thy tongue! I am leaving!", "These words are not befitting my presence! Thee is out!", "You mocked me once, never do it again!"};
      String[] rainbowGeneral = {"'Tis glorious to see the colors when the rainfall ends", "I hear that magics are more potent when one casts it under a rainbow", "Dost thou know my cousin, Roy G Biv?", "Thy mages spells come at less of a cost when the colors arch across the sky", "The wise ones and mages know more of rainbows and WEATHER", "Seek the wise ones with staff to learn more of WEATHER and rainbows", "If one is lucky enough, thee might see the UNICORN when the rainbow is above", "I love thy rainbows as much as the fabled UNICORN", "'Tis all rainbows and UNICORNS"};
      String[] unicornGeneral = {"Thy horned horse? I think they are only of legend", "Legend says the Unicorn uses the RAINBOW as a path to this realm", "The Royal Guard of the CAPITAL speaks of Unicorns", "Thy Capital's Royal Guard knows more of this colorful myth"};
      String[] craftingGeneral = {"Seek the CASTLE Ironsmith. They know of crafting arms", "Thy city Ironsmith is skilled enough to craft armor from the scales of beasts", "Thy Ironsmith of the CASTLE is known to craft weapons", "For those mighty enough to slay scaled beasts, the city Ironsmith can craft the scales to armor", "Knowledge of arms and gems rests in the mind of the CASTLE Ironsmith", "'Tis known the Ironsmith of the city has crafted armor from the scales of great beasts", "In the CASTLE is the finest of crafters: the Ironsmith", "Beast scales are a rare find. The city Ironsmith aims to craft them into the finest armor"};
      String[] taxGeneral = {"Thy taxes are so high these days, 'tis a burden", "Thy TAXMAN does take a bite out of thy wages, doesn't he", "Declare the gold coins on thy eyes... TAXMAN!", "Be thankful thy TAXMAN doth not take it all", "Thy home and gold on hand can be taxed now! Sneak past when thy TAXMAN is about"};
      String[] futureGeneralGood = {"Ask the old mages about thy future. I can not see it", "'Tis easy. Thy TAXMAN is in your future. 'Tis so for all of us", "I know not...from the fierce looks of thee, possibly fighting a beast or two...", "A mage can tell thee of thy near futures, but far off 'tis more difficult"};
      String[] futureGeneralBad = {"Ask the old mages about thy future. I can not see it", "'Tis easy. Thy TAXMAN is in your future. 'Tis so for all of us", "I know not...from the scruffy looks of thee, possibly getting sullied in the tavern...", "A mage can tell thee of thy near futures, but far off 'tis more difficult"};
      String[] futureGeneralSingle = {"I see in thy future a candle lit table, good food on two plates, and thy good company", "I see MARRIAGE in thy future! Dost thou have someone at home", "I see in thy future a cozy, darkened room, thee and me", "Thy future is clear to me, to be near to me"};
      String[] inconceivableResp = {"You keep using that word, I don't think you know what it means", "Thou sounds like Vizzini", "You only think I guessed wrong! That's what's so funny", "Never get involved in a land war in Asia", "Stop that, I mean it", "You've fallen for one of the two classic blunders"};
      int diff = allignmentDifference(selected);
      String response = "";
      if(typed.contains("number"))
         response =  getRandomFrom(numberResponseNo);
      else if(typed.contains("city"))
      {
         if(Math.random() < 0.5)
            response =  getInfoFrom(locationInfo, typed);
         else
            response =  getRandomFrom(cityAsk);
      }
      else if(typed.contains("capital"))
      {
         if(Math.random() < 0.5)
            response =  getInfoFrom(locationInfo, typed);
         else
         {
            String temp = getRandomFrom(capitalLoc);
            Location capital = Utilities.getClosestCapital(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            TerrainBuilder.markMapPath(capital.getName());
            if(capital != null)
            {
               temp = temp.replace("PROVINCE_NAME", Display.provinceName(capital.getName()));
               temp = temp.replace("CAPITAL_COL", ""+capital.getCol());
               temp = temp.replace("CAPITAL_ROW", ""+capital.getRow());
               response = temp;
            }
            else
            {
               response =  getRandomFrom(capitalNo);
            }
         }
      }
      else if(typed.contains("assyria"))
         response = getRandomFrom(assyriaCapital);
      else if(typed.contains("swallow"))
         response = getRandomFrom(swallowGeneral); 
      else if(typed.contains("skara"))
         response =  getRandomFrom(SkaraBrae);   
      else if(typed.equals("50") || typed.contains("fifty"))
         response =  getRandomFrom(fiftyResponseNo);
      else if(typed.contains("arena"))
      {
         if(Math.random() < 0.5)
            response =  getInfoFrom(locationInfo, typed);
         else
            response =  getRandomFrom(arenaGeneral);
      }   
      else if(typed.contains("craft") || typed.contains("gem") || typed.contains("upgrade"))
         response =  getRandomFrom(craftingGeneral);
      else if(typed.contains("swine"))
         response = getRandomFrom(swineInfo);
      else if(typed.contains("shop") || typed.contains("shoppe") || typed.contains("shoppes"))
      {
         String temp =  getRandomFrom(shopInfo);
         String[]parts = temp.split(" ~");
         if(parts.length > 0)
            return parts[0].trim();
         else
            response = getRandomFrom(unknown2);
      }
      else if(typed.contains("affliction"))
      {
         String temp =  getRandomFrom(afflictionInfo);
         String[]parts = temp.split(" ~");
         if(parts.length > 0)
            return parts[0].trim();
         else
            response = getRandomFrom(unknown2);
      }
      else if(typed.contains("location"))
      {
         String temp =  getRandomFrom(locationInfo);
         String[]parts = temp.split(" ~");
         if(parts.length > 0)
            return parts[0].trim();
         else
            response = getRandomFrom(unknown2);
      }
      else if(typed.contains("hearth"))
         response =  getRandomFrom(homeInfo);
      else if(typed.contains("future"))
      {
         if(selected.getMaritalStatus() == 'S' && !(CultimaPanel.player.getReputation() < -100 || (CultimaPanel.player.getReputation() < 0 && diff > 100 && selected.getReputation() > CultimaPanel.player.getReputation())) && Math.random() < 0.5)
            response = getRandomFrom(futureGeneralSingle);
         else if(CultimaPanel.player.getReputation() > 0)
            response =  getRandomFrom(futureGeneralGood);
         else
            response =  getRandomFrom(futureGeneralBad);
      }
      else if(typed.contains("legend") || typed.contains("legendary"))
         response =  getRandomFrom(legend);
      else if(typed.contains("ship") || typed.contains("boat") || typed.contains("sail") || typed.contains("pirate"))
         response =  getRandomFrom(shipNo);
      else if(typed.contains("horse") || typed.contains("tame"))
         response =  getRandomFrom(horseNo);
      else if(typed.contains("gold") || typed.contains("money") || typed.contains("coin")|| typed.contains("pay"))
         response =  getRandomFrom(goldGeneral);
      else if(typed.contains("adventure") || typed.contains("dungeon"))
         response =  getRandomFrom(adventureGeneral);
      else if(typed.contains("cave") || typed.contains("mine"))
      {
         if(Math.random() < 0.5)
            response =  getInfoFrom(locationInfo, typed);
         else
            response =  getRandomFrom(adventureGeneral);
      }
      else if(typed.contains("dog"))
         response =  getRandomFrom(dogGeneral);
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            response = sellingHouse(selected, typed);
         else
         {
            response =  getRandomFrom(noMission);
            if(selected.getNumInfo()==10)
               selected.setNumInfo(3);
         }
      }
      else if(typed.contains("garriott") || typed.contains("richard") || (typed.contains("lord") && typed.contains("british")) || typed.contains("king"))
         response = getRandomFrom(lordBritish);
      else if(typed.contains("trade") || typed.contains("buy") || typed.contains("purchase"))
         response =  getRandomFrom(tradeGeneral);
      else if(typed.contains("cure") || typed.contains("heal") || typed.contains("healing") || typed.contains("poison"))
      {
         if(typed.contains("poison") && Math.random() < 0.5)
            response =  getInfoFrom(afflictionInfo, typed);
         else
            response =  getRandomFrom(cureGeneral);
      } 
      else if(typed.contains("curse"))
      {
         if(CultimaPanel.player.hasEffect("curse") || Math.random() < 0.5)
            response =  getRandomFrom(curseGeneral);
         else
            response =  getInfoFrom(afflictionInfo, typed);
      }
      else if(typed.contains("train"))
         response =  getRandomFrom(trainGeneral);
      else if(typed.contains("map"))
         response =  getRandomFrom(mapGeneral);
      else if(typed.contains("bribe"))
         response =  getRandomFrom(bribeGeneral);
      else if(typed.contains("lockpick"))
         response =  getRandomFrom(pickGeneral);
      else if(typed.contains("message"))
         response =  getRandomFrom(messageGeneral);
      else if(typed.contains("bounty"))
         response =  getRandomFrom(bountyGeneral);
      else if(typed.equals("come")||typed.equals("go")||typed.equals("stay")||typed.equals("attack")||typed.equals("treat"))
         response =  getRandomFrom(dogCommands);
      else if(typed.contains("weather") || typed.equals("rain") || typed.contains("storm") || typed.contains("sun") || typed.contains("sky"))
         response =  getRandomFrom(weatherGeneral);
      else if(typed.contains("trap"))
         response =  getRandomFrom(trapGeneral);
      else if(typed.contains("vamp"))
         response = getRandomFrom(vampireGeneral);   
      else if(typed.contains("wolf"))
      {
         boolean inPurgeTown = false;
         for(int i=CultimaPanel.missionStack.size()-1; i>=0; i--)
         {
            Mission m = CultimaPanel.missionStack.get(i);
            if(m.getType().equals("Purge") && CultimaPanel.player.getWorldRow()==m.getTargetRow() && CultimaPanel.player.getWorldCol()==m.getTargetCol())
               inPurgeTown = true;
         }
         String randName = Utilities.nameGenerator("name");
         if(selected.isWerewolf())
         {
            if(inPurgeTown)
               response = getRandomFrom(isWerewolfPurge);
            else if(selected.getReputation () >= 100)
               response = getRandomFrom(isWerewolfAccept);
            else
               response = getRandomFrom(isWerewolfDeny);
         }
         else if(CultimaPanel.werewolfAbout)
            response = getRandomFrom(werewolfInTown).replace("NPCNAME", randName);
         else
            response = getRandomFrom(werewolfGeneral);
      }
      else if(typed.contains("death"))
         response = getRandomFrom(deathGeneral);
      else if(typed.contains("vows") || typed.contains("marry") || typed.contains("marriage") || typed.contains("spouse") || typed.contains("husband") || typed.contains("wife") || (typed.contains("love") && !typed.contains("glove")) || typed.contains("divorce") || typed.contains("annulment") || typed.contains("separat") || typed.contains("sever"))
      {
         response = marriage(selected, typed);  
      }
      else if(!selected.isSellingHouse() && (typed.contains("house") || typed.contains("home") || typed.contains("realtor") || typed.contains("estate")))
         response = getRandomFrom(houseGeneral); 
      else if(typed.length()>=3 && selected.getName().toLowerCase().contains(typed))
         return getRandomFrom(nameAsk); 
      else if(selected.isSellingHouse() && selected.getNumInfo()==10)
         return sellingHouse(selected, typed);
      else if(typed.contains("swine") || typed.equals("roll") || typed.equals("pass") || selected.swinePlayer())
         response =  swine(selected, typed); 
      else if(typed.contains("puzzle"))
         response =  getRandomFrom(puzzle);
      else if(typed.contains("egg"))
         response =  getRandomFrom(eggGeneral); 
      else if(typed.contains("rainbow"))
         response =  getRandomFrom(rainbowGeneral);
      else if(typed.contains("unicorn"))
         response =  getRandomFrom(unicornGeneral);
      else if(typed.contains("town") || typed.contains("person") || typed.contains("people") || typed.contains("info") || typed.contains("help") || isTownsfolk(typed))
      {
         if(isTownsfolk(typed))
         {
            String [] littleInfo = {"I only know a little of this ", "There are those that know much more than I about thy ", "Thy barkeep can tell thee more about this ", "I have only heard from others information about thy "};
            if(Math.random() < 0.25)
               response =  getInfoFrom(tavernTown, typed);
            else
               response = getRandomFrom(littleInfo) + typed;
         }
         else
            response =  getRandomFrom(townGeneral);
      } 
      else if(typed.contains("arms") || typed.contains("weapon") || isWeapon(typed))
      {
         if(typed.contains("arms") && Math.random() < 0.5)
            response =  getInfoFrom(shopInfo, typed);
         else if(isWeapon(typed))
         { 
            String [] littleInfo = {"I only know a little of this ", "There are those that know much more than I about thy ", "Thy ironsmith can tell thee more about this ", "I have only heard from others information about thy "};
            if(Math.random() < 0.25)
               response =  getInfoFrom(armsInfo, typed);
            else
               response = getRandomFrom(littleInfo) + typed;
         }
         else
            response =  getRandomFrom(armsGeneral);
      }    
      else if(typed.contains("ration") || typed.contains("rations"))
      {
         if(Math.random() < 0.5)
            response =  getInfoFrom(shopInfo, typed);
         else
            response =  getRandomFrom(rationGeneral);
      }
      else if(typed.contains("monster") || typed.contains("beast") || isMonster(typed))
      {
         String [] littleInfo = {"I only know a little of this ", "There are those that know much more than I about thy ", "Thy sword wielding adventurers can tell thee more about this ", "I have only heard from others information about thy "};
         double respRoll = Math.random();
         if(isMonster(typed) && respRoll < 0.25)
            response =  getInfoFrom(monsters, typed);
         else if(respRoll < 0.5)
            response = getRandomFrom(littleInfo) + typed;
         else
            response =  getRandomFrom(monsterGeneral);
      }
      else if(typed.contains("roper")  || typed.contains("hydra"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "tree");  
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("sand"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "graboid");  
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("spectre") || typed.contains("specter") || typed.contains("phantom"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "ghost");
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("colossus"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "giant");
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("deadite") || typed.contains("zombie"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "skeleton");
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("sea") && typed.contains("serpent"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(monsters, "seamonster");
         else
            response =  getRandomFrom(monsterGeneral);  
      }
      else if(typed.contains("lair"))
      {
         if(Math.random() < 0.25)
            response =  getInfoFrom(locationInfo, typed);
         else
            response =  getRandomFrom(lairGeneral);
      }
      else if(typed.contains("spell") || typed.contains("spells") || typed.contains("magic") || isSpell(typed))
      {
         if(typed.contains("magic") && Math.random() < 0.5)
            response =  getInfoFrom(shopInfo, typed);
         else if(isSpell(typed))
         {
            if(Math.random() < 0.25)
               response =  getInfoFrom(spellInfo, typed);
            else
            {
               String [] littleInfo = {"I only know a little of this ", "There are those that know much more than I about thy ", "Thy mages can tell thee more about this ", "I have only heard from others information about thy "};
               response = getRandomFrom(littleInfo) + typed;
            }
         }
         else
            response =  getRandomFrom(spellGeneral);
      }   
      else if(isLocation(typed))
         response =  getInfoFrom(locationInfo, typed);
      else if(isAffliction(typed))
         response =  getInfoFrom(afflictionInfo, typed);
      else if(isShop(typed))
         response =  getInfoFrom(shopInfo, typed);
      else if(typed.contains("tax"))
      {
         if(CultimaPanel.missionTargetNames.contains(Utilities.shortName(selected.getName())))
            response = getRandomFrom(noBuisness);
         else
            response = getRandomFrom(taxGeneral);
      }
      else if(typed.contains("inconceivable") || typed.contains("inconcievable"))
      {
         if(Math.random() < 0.5)
         {
            if(Math.random() < 0.5)
               return getRandomFrom(unknown) + " " + typed + "...";
            else
               response =  getRandomFrom(unknown2);
         }
         else
            response = getRandomFrom(inconceivableResp);
      }
      else if(isChallenge(typed))
      {
         CultimaPanel.player.addReputation(-1);
         if(selected.getCharIndex()==NPC.KING)
         {
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            response =  getRandomFrom(kingChallenge);
            Utilities.alertGuards();
         }
         else if(isGuard(selected.getCharIndex()) && selected.getReputation()-50 < (Math.random()*100))
         {
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            response =  getRandomFrom(guardChallenge);
            selected.setMoveTypeTemp(NPC.CHASE);
         }
         else if(selected.getCharIndex()==NPC.JESTER)
         {
            response =  getRandomFrom(jesterChallenge);
         }
         else
         {
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            response =  getRandomFrom(commonChallenge);
         }
      
      }   
      else if(typed.contains("you are "))
      {
         String[] insultResp = {"Nay, thou", "I know thou is, but what is thee...", "'Twas about to say the same of thou", "May thou look in a mirror and see the same"};
         if(typed.indexOf("you are ") == 0)
         {
            String[] insultResp2 = {"Nay, tis thou that is ", "Tis thou that I find ", "Tis well known that it is thou that is ", "Thy mother is the one that is "};
            String rest = typed.substring(7).trim();
            if(rest.length() > 0 && Math.random() < 0.5)
               response = getRandomFrom(insultResp2) + rest;
            else
               response =  getRandomFrom(insultResp);
         }
         else
            response =  getRandomFrom(insultResp);  
      }
      else if(typed.contains("will you "))
      {
         String[] requestResp = {"I am unsure how to do that", "I don't know if I can do that", "Does thou really require this of me", "Will it take long"};
         if(typed.indexOf("will you ") == 0)
         {
            if(selected.getName().startsWith("+") && Math.random() < 0.5)    //this is our spouse
            {
               return "As you wish.";
            }
            else
            {
               String[] requestResp2 = {"How does one ", "Will thou be pleased if I ", "What does it mean to ", "Is it difficult to "};
               String rest = typed.substring(8).trim();
               if(rest.startsWith("please"))
                  rest = rest.substring(6);
               if(rest.contains(" me "))
                  rest = rest.replace(" me ", " you ");   
               else if(rest.endsWith(" me"))
                  rest = rest.replace(" me", " you");
               if(rest.length() > 0 && Math.random() < 0.5)
                  response = getRandomFrom(requestResp2) + rest;
               else
                  response =  getRandomFrom(requestResp);
            }
         }
         else
            response =  getRandomFrom(requestResp);  
      }
      else if(typed.contains("can you "))
      {
         String[] requestResp = {"I am unsure how to do that", "I don't know if I can do that", "Does thou really require this of me", "Will it take long"};
         if(typed.indexOf("can you ") == 0)
         {
            if(selected.getName().startsWith("+") && Math.random() < 0.5)    //this is our spouse
            {
               return "As you wish.";
            }
            else
            {
               String[] requestResp2 = {"How does one ", "Will thou be pleased if I ", "What does it mean to ", "Is it difficult to "};
               String rest = typed.substring(7).trim();
               if(rest.startsWith("please"))
                  rest = rest.substring(6);
               if(rest.contains(" me "))
                  rest = rest.replace(" me ", " you ");
               else if(rest.endsWith(" me"))
                  rest = rest.replace(" me", " you");
               if(rest.length() > 0 && Math.random() < 0.5)
                  response = getRandomFrom(requestResp2) + rest;
               else
                  response =  getRandomFrom(requestResp);
            }
         }
         else
            response =  getRandomFrom(requestResp);  
      }
             //NPC does not understand
      else
      {
         if(Math.random() < 0.5)
            return getRandomFrom(unknown) + " " + typed + "...";
         response =  getRandomFrom(unknown2);
      }
      if(diff > 100 && CultimaPanel.player.getReputation() >=500 && selected.getCharIndex()!=NPC.KING && selected.getCharIndex()!=NPC.ROYALGUARD)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String arenamasterResponse(NPCPlayer selected, String typed)
   {
      String[] arenaJob = {"I arrange battles in the arena for thee spectators to revel", "'Tis victory I give to gladiators hungry for glory", "The arena is my canvas for glory and victory", "I give the crowd the thrill of victory, the agony of defeat", "I arrange battles in the arena to thrill the spectators"};
      String[] futureGeneral = {"Thy future is before thee, in this very arena!", "'Tis easy. Glory in battle before an adoring crowd is in thy future!", "I see from the fierce looks of thee, fighting a beast in thy arena is in thy future", "Thy mages know little of thy future. I can see that it is here, fighting for glory in my arena!"};
   
      String response = "";
      if(typed.contains("mission"))
         response = Mission.arenaMission(selected); 
      else if(typed.contains("job"))
         response = getRandomFrom(arenaJob); 
      else if(typed.contains("future"))
         response = getRandomFrom(futureGeneral);
      if(response.length()==0)
         response = generalResponse(selected, typed);      
      return response;
   }

   public static String childResponse(NPCPlayer selected, String typed)
   {
      String[] childGood = {"Wheeeee!", "Da da da da!", "Watch me jump!", "I like blue!", "I love you!", "Bud-z-z-z!", "Where's my Papa?", "Skara Brae!"};
      String[] childBad = {"Whaaah!", "I bite you!", "Bad man! Bad man!", "Noooooooo!", "Stranger!", "I fot you!"};
      String[] giveMessageChild = {"Ssshhhhh! Take secret message", "Tell this message to Pama", "Take this message to Pama", "Take paper to them - quickly!"}; 
   
      if(!selected.hasMet())
      {
         Player.stats[Player.PEOPLE_MET]++;
         CultimaPanel.player.specificExperience[Player.AWARENESS]++;           
      }
      selected.setHasMet(true);
      if(selected.hasItem(typed)) 
      {
         Item item = selected.getItem(typed);
         if(item.getName().toLowerCase().contains("message"))
         {
            CultimaPanel.player.addItem(item.getName());
            selected.removeItem(item.getName());
            return getRandomFrom(giveMessageChild);
         }
      }
      if(selected.getNumInfo()==10)
         return Mission.childMission(selected);
      if(allignmentCompatable(selected))
      {
         return getRandomFrom(childGood);
      }
      return getRandomFrom(childBad);
   }

   public static String guardResponse(NPCPlayer selected, String typed)
   {
      String[] guardRegularJob = {"I stand watch for COIN.", "'Tis good pay in GOLD.", "Dost thou even LIFT, brother?", "'Tis hard on the feet, but the GOLD is good.", "'Tis better pay than working on a smelly SHIP", "I'm on the Brute Squad", "Tis not my fault being the strongest. I don't even exercise"};
      String[] guardRoyalJob = {"I protect the King!", "I watch for assassins in the CASTLE.", "Dost thou even LIFT, brother?", "I stand watch for COIN.", "I protect the King, and tame wild HORSES for his stable."};
      String[] guardLift = {"The VILLAGE guards have time to train", "VILLAGE guards are quite boored, they might train you", "The guards in the VILLAGE are always training", "You might seek the VILLAGE guards"};
      String[] guardVillage = {"You will have to learn the lands well", "Explore the lands to find them", "I cannot explore these lands for you", "Learn the countryside well...you will find them"};
      String[] guardTrain = {"To train with the best will cost GOLD_AMOUNT gold", "Only the best trade GOLD_AMOUNT gold for training here", "Thy new muscles will be worth their weight in GOLD_AMOUNT gold", "GOLD_AMOUNT gold is needed to train with the best"};
      String[] guardTrainYes = {"Thy muscles have gotten some work!", "Thou dost lift after all, my brother!", "Thy arms grow with hard work!", "Thy core is like a column of stone!"};
      String[] guardTrainNo = {"You are stronger than I - training is not needed", "You are too fit to be training here", "Thee should be training me", "I know not what I can show thee to improve"};
      String[] guardNoBounty = {"The assassins have heard of thee, but you have not enough enemies to spark their interest", "Your bounty is negligable. The assassins are not interested in thy head", "Thy bounty is low. You need not worry about the assassins", "Thy assassins would indeed be desperate to seek such a low bounty"};
      String[] guardClearBounty = {"Your name is on the assassin's bounty for BOUNTY_VALUE gold. Pay me and I will mark it claimed", "BOUNTY_VALUE gold awaits the assassin that takes thy head. Pay me to clear your name", "Thy head is worth BOUNTY_VALUE gold to an assassin. I will clear thy name for that sum", "The assassins value your head at BOUNTY_VALUE gold. Pay me and your name shall be cleared"};
      String[] guardClearBountyDone = {"Thy name shall be removed from the assassin's list with haste", "I shall remove thy name from the bounty list the next time I visit the barracks", "Thy assassins shall seek other bountys after this TRADE", "Thy name will be purged from thy bounty list before long"};
      String[] eggResponse = {"I seek the DRAGON egg to supplement thy might", "The egg of the DRAGON is known by few to make thee stronger", "If thee comes across a DRAGON egg in thy travels, I will trade it for gold", "Gold awaits thee in trade if thou has a DRAGON egg"};
      String[] buyEgg = {"Thy coveted DRAGON egg! Take 150 gold coins in trade!", "I have 150 gold to trade for thy Dragon's egg!", "I need that DRAGON egg! Take 150 gold coins to trade!", "Take these 150 gold coins in trade for that Dragon's egg!"};
      String [] soldThanks = {"I thank thee for our trade", "Thank thee. I hope this gold brings thee happiness", "May SKARA Brae shine on thy new riches", "At last, I can grow stronger! I thank thee"};
   
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      int ourBounty = CultimaPanel.player.getBounty()*10;
      if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               return  getRandomFrom(missionPrice).replace("AMOUNT",typed);
            if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            return getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
         {
            if(selected.getCharIndex()==ROYALGUARD)
               return Mission.royalGuardMission(selected);
            return Mission.guardMission(selected);
         }
         else
            return getRandomFrom(noMission);  
      }
   
      if(typed.contains("guard") || typed.contains("job"))
      {
         if(selected.getCharIndex()==ROYALGUARD)
            return getRandomFrom(guardRoyalJob);
         else
            return getRandomFrom(guardRegularJob);
      }
      else if(typed.contains("horse") || typed.contains("tame"))
      {
         if(selected.getCharIndex()==ROYALGUARD)
         {
            if(Utilities.isCapitalCity(selected.getMapIndex()))
            {
               if(CultimaPanel.player.getHorseInfo() < horseInfo.length)
                  response = horseInfo[CultimaPanel.player.nextHorseInfo()];
               else
                  response = getRandomFrom(horseInfo);
            }
            else
            {
               if(CultimaPanel.player.getHorseInfo() < horseInfo.length/2)
                  response = horseInfo[CultimaPanel.player.nextHorseInfo()];
               else
                  response = getRandomFrom(horseInfo, horseInfo.length/2);
            }
         }
         else
            response = getRandomFrom( horseNo);
      }
      else if(typed.contains("unicorn") && selected.getCharIndex()==ROYALGUARD && Utilities.isCapitalCity(selected.getMapIndex()))
      {
         if(CultimaPanel.player.getHorseInfo() < horseInfo.length)
            response = horseInfo[CultimaPanel.player.nextHorseInfo()];
         else
            response = getRandomFrom(horseInfo);
      }
      else if(selected.getCharIndex()==ROYALGUARD && typed.contains("egg"))
      {
         if(!CultimaPanel.player.hasItem("dragon-egg"))
            response = getRandomFrom(eggResponse);
         else   
         {
            response = getRandomFrom(buyEgg);
            selected.setGold(selected.getGold()+150);
         }
      }
      else if(selected.getCharIndex()==ROYALGUARD && CultimaPanel.player.hasItem("dragon-egg") &&  typed.contains("150"))
      {
         selected.setGold(Math.max(0, selected.getGold()-150));
         CultimaPanel.player.addGold(150);
         CultimaPanel.player.removeItem("dragon-egg");
         selected.addItem(Item.getDragonEgg());
         response = getRandomFrom(soldThanks);
      }
      else if(typed.contains("ship") || typed.contains("boat") || typed.contains("sail") || typed.contains("pirate"))
      {
         if(selectedLoc.contains("city") || selectedLoc.contains("port"))
         {
            if(CultimaPanel.player.getShipInfo() < shipInfo.length)
               response = shipInfo[CultimaPanel.player.nextShipInfo()];
            else
               response = getRandomFrom(shipInfo);
         }
         else
            response =getRandomFrom( shipNo);
      }   
      else if(!selectedLoc.contains("village") && (typed.contains("lift") || typed.contains("train") || typed.contains("might") || typed.contains("strength")))
         response = getRandomFrom(guardLift);
      else if(selectedLoc.contains("village") && (typed.contains("lift") || typed.contains("train") || typed.contains("might") || typed.contains("strength")))
      {
         int amount = amountToTrainGuard(selected);
         String tempResponse="";
         tempResponse = getRandomFrom(guardTrain);
         tempResponse = tempResponse.replace("GOLD_AMOUNT",""+amount);
         response = tempResponse;   
      }
      else if(selectedLoc.contains("village") && typed.contains(""+amountToTrainGuard(selected)))
      {
         int amount = amountToTrainGuard(selected);
         if(CultimaPanel.player.getAgilityRaw() >= 50 && CultimaPanel.player.getMightRaw() >= 50)
            response = getRandomFrom(guardTrainNo);
         else if(CultimaPanel.player.getGold() < amount)
            return getRandomFrom(bribeNoMoney)+".";
         else
         {
            selected.setHasTrained(true);
            response = getRandomFrom(guardTrainYes)+".";
            CultimaPanel.player.pay(amount);
            selected.addGold(amount);
            if(CultimaPanel.player.getMightRaw() < 50)
               CultimaPanel.player.setMight(Math.min(50, CultimaPanel.player.getMightRaw()+Player.rollDie(3)));
            else
               CultimaPanel.player.setAgility(Math.min(50, CultimaPanel.player.getAgilityRaw()+Player.rollDie(3)));
            return response;
         }                
      }
      else if(typed.contains("bounty") || typed.contains("assassin"))
      {
         if(ourBounty <= 10)
            response = getRandomFrom(guardNoBounty)+".";
         else   
            return  getRandomFrom(guardClearBounty).replace("BOUNTY_VALUE", ""+ourBounty)+".";
      }
      else if(typed.equals(""+ourBounty) && ourBounty > 10)
      {
         if(CultimaPanel.player.getGold() < ourBounty)
            return getRandomFrom(bribeNoMoney)+".";
         CultimaPanel.player.pay(ourBounty);
         selected.addGold(ourBounty);
         if(ourBounty >= 100)
            Achievement.earnAchievement(Achievement.HMS_BOUNTY);
         CultimaPanel.player.setBounty(1);
         return getRandomFrom(guardClearBountyDone)+".";
      }
      else if(typed.contains("village"))
         response = getRandomFrom(guardVillage);
      else if(typed.contains("gold") || typed.contains("money") || typed.contains("coin") || typed.contains("pay"))
         response = getRandomFrom(gold);
      else if(typed.contains("bribe"))
      {
         if((selected.getReputation() >= 100 && !charmed) || selected.getCharIndex()==ROYALGUARD)
            response =getRandomFrom( bribeNo);
         else if(selected.getReputation() >= -10)
         {
            if(CultimaPanel.player.getGold() < 10)
               response = getRandomFrom(bribeNoMoney);
            else
            {
               int roll = Player.rollDie(100);
               if(charmed)
                  roll = Player.rollDie(200);
               if(roll <= selected.getReputation() || Math.random()<0.5)
               {
                  response = getRandomFrom(bribeMaybe);
                  CultimaPanel.player.pay(10);
                  selected.addGold(10);
               }
               else
               {
                  response = getRandomFrom(bribeYes);
                  NPCPlayer temp = selected.clone();
                  temp.setRestoreDay(CultimaPanel.days+1);
                  CultimaPanel.NPCsToRestore.add(temp);
                  selected.setMoveTypeTemp(NPC.ROAM);
                  selected.setNumInfo(13);   //flag that the guard has been bribed
                  selected.setHasMet(true);
                  CultimaPanel.player.pay(10);
                  selected.addGold(10);
                  Player.stats[Player.GUARDS_BRIBED]++;
                  CultimaPanel.talkSel = false;
                  selected.setTalking(false);
                  CultimaPanel.selected = null;
                  CultimaPanel.selectedTerrain = null;
                  CultimaPanel.typed = "";
                  return response;
               }
            }
         }
         else
         {
            if(Math.random()<0.5)
            {
               response = getRandomFrom(bribeMaybe);
               CultimaPanel.player.pay(10);
               selected.addGold(10);
            }
            else
            {
               response = getRandomFrom(bribeYes);
               NPCPlayer temp = selected.clone();
               temp.setRestoreDay(CultimaPanel.days+1);
               CultimaPanel.NPCsToRestore.add(temp);
               selected.setMoveTypeTemp(NPC.ROAM);
               selected.setNumInfo(13);   //flag that the guard has been bribed
               selected.setHasMet(true);
               CultimaPanel.player.pay(10);
               selected.addGold(10);
               CultimaPanel.talkSel = false;
               selected.setTalking(false);
               CultimaPanel.selected = null;
               CultimaPanel.selectedTerrain = null;
               CultimaPanel.typed = "";
               return response;
            }
         }
      }
      else 
         return generalResponse(selected, typed);
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String beggerResponse(NPCPlayer selected, String typed)
   {
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(!TerrainBuilder.habitablePlace(selected.getLocationType()))     
      {                                            //we are rescuing a person in a dungeon, monster lair, cave, etc
         if(!allignmentCompatable(selected))       //we freed a prisoner, so make them alignment compatible with us
            selected.setReputation(CultimaPanel.player.getReputationRaw());
         int restoreType = (int)(Math.random()*7);
         if(restoreType<=2)
         {
            selected.setCharIndex(MAN);
            selected.setNumInfo(3);                //a man with a numInfo of 3 is the flag that they will map for free
         }
         else if(restoreType<=5)
         {
            selected.setCharIndex(WOMAN);
            selected.setNumInfo(3);                //a woman with a numInfo of 3 is the flag that they will map for free
         }
         else //if restoreType==6
         {
            selected.setCharIndex(WISE);
            selected.setNumInfo(3);                //a wise-man with a numInfo of 3 is the flag that they will teach us a spell
         }  
         if(selectedLoc.contains("arena") || CultimaPanel.brigandInSight)
         {
            CultimaPanel.sendMessage("The prisoners are escaping!");
            Utilities.alertGuards();
         } 
         selected.setMoveType(ROAM);
         Player.stats[Player.PEOPLE_RESCUED]++;
         if(Player.stats[Player.PEOPLE_RESCUED]>=50)
            Achievement.earnAchievement(Achievement.EVERYBODYS_HERO);
         selected.setHasBeenRescued(true);
         if(Math.random() < 0.25)                  //25% chance your bounty goes down for each person rescued
            CultimaPanel.player.setBounty(CultimaPanel.player.getBounty()-1);
         CultimaPanel.player.addReputation(5);
         CultimaPanel.player.addExperience(selected.getLevel()); 
         response =getRandomFrom( beggerThanks);
         if(diff > 100 && CultimaPanel.player.getReputation() >=500)
            response += getRandomFrom(greetings2End); 
         else
            response += "!";
         return response;    
      }
   
      if(((typed.contains("coin") || typed.contains("charity") || typed.contains("give") || typed.contains("gold") || typed.contains("pay")) && CultimaPanel.player.getGold() >= 1) || (typed.contains("ration") && CultimaPanel.player.getRations()>=5 && !CultimaPanel.player.isVampire()))
      {
         if(typed.contains("coin") || typed.contains("charity") || typed.contains("give") || typed.contains("gold") || typed.contains("pay"))
         {
            CultimaPanel.player.pay(1);
            selected.addGold(1);
            if(CultimaPanel.player.stats[Player.MONEY_DONATED]>=500)
               Achievement.earnAchievement(Achievement.PHILANTHROPIST);
         
            if(selected.getNumInfo() > 0)
            {  //don't get more than 3 reputation points for giving to a begger
               CultimaPanel.player.addReputation(1);
               selected.setNumInfo(selected.getNumInfo()-1);
            }
         }
         else if (!CultimaPanel.player.isVampire())
         {
            for(int i=0; i<5; i++)
               CultimaPanel.player.useRation();
            if(selected.getNumInfo() > 0)
            {  //don't get more than 3 reputation points for giving to a begger
               CultimaPanel.player.addReputation(1);
               selected.setNumInfo(0);
            }   
         }  
         Player.stats[Player.MONEY_DONATED]++;
      
         response = getRandomFrom(beggerThanks);
         if(diff > 100 && CultimaPanel.player.getReputation() >=500)
            response += getRandomFrom(greetings2End); 
         else
            response += "!";
         if(Math.random() < .1 || typed.contains("ration"))     //restore begger to walking
         {
            if(!allignmentCompatable(selected))       //we freed a prisoner, so make them alignment compatible with us
               selected.setReputation(CultimaPanel.player.getReputationRaw());
            int restoreType = (int)(Math.random()*7);
            if(restoreType<=2)
            {
               selected.setCharIndex(MAN);
               selected.setNumInfo(3);    //a man with a numInfo of 3 is the flag that they will map for free
            }
            else if(restoreType<=5)
            {
               selected.setCharIndex(WOMAN);
               selected.setNumInfo(3);    //a woman with a numInfo of 3 is the flag that they will map for free
            }
            else //if restoreType==6
            {
               selected.setCharIndex(WISE);
               selected.setNumInfo(3);    //a wise-man with a numInfo of 3 is the flag that they will teach us a spell
            }   
            selected.setMoveType(ROAM);
            CultimaPanel.player.addReputation(3);
         //remove any current missions with players in this location since they are dead
            String NPCmiss = selected.getMissionInfo();
            if(!NPCmiss.equals("none"))
            {
               for(int i=0; i<CultimaPanel.missionStack.size(); i++)
               {
                  Mission m = CultimaPanel.missionStack.get(i);
                  if(NPCmiss.equals(m.getInfo()))
                  {
                     CultimaPanel.missionStack.remove(i);
                     break;
                  }
               }
            }
         }  
         return response; 
      }
      else if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               response = getRandomFrom(missionPrice).replace("AMOUNT",typed);
            else  if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            response = getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
            return Mission.beggerMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      else
      {
         response = getRandomFrom(begger);
         if(diff > 100 && CultimaPanel.player.getReputation() >=500)
            response += getRandomFrom(greetings2End); 
         else
            response += "?";
      }
      return response;    
   }

   public static boolean riddleAnsCorrect(String guess, String ans)
   {
      if(guess==null || ans==null || guess.length()==0 || ans.length()==0)
         return false;
      guess = guess.toLowerCase().trim();   
      String [] answers = ans.split(" ");
      for(int i=0; i<answers.length; i++)
         answers[i] = answers[i].toLowerCase().trim();
      for(int i=0; i<answers.length; i++)
         if(answers[i].equals(guess))
            return true;
      return false;
   }

   public static String jesterResponse(NPCPlayer selected, String typed)
   {
      String[] jesterLock = {"Did thou say LOCKPICK?", "Did thee say something about a LOCKPICK?", "Did we hearest MAGICPICK?", "LOCKPICK, did you say?"};
      String[] jesterPick = {"Shhhhh...I have an extra for 10 coins", "'Twill cost you 10 coins for my extra", "Keep silent: 10 gold for my extra", "Shhhhh. 10 gold and this is yours"};
      String[] jesterBuyPick = {"Wilt thou sell me your lockpick for 10 coins?", "May I buy your lockpick for 10 coins?", "I will trade thee your lockpick for 10 coins.", "Wilt thou trade your lockpick for 10 coins?"};
      String[] jesterSell = {"Shhhhh...speak nothing of this", "Do not tell others where you got this", "Keep this between you and me", "We must keep silent about thy purchase"};
      String[] jesterNoPick = {"I have not a pick, neither do you","No pick for me, no pick for you","Pick a pick?  I think not","Pick a pick a pickle pick, pick pick"};
      String[] jesterBothHave = {"Wait...we both have one", "I see we both own one", "Lets compare lockpicks", "We both hold them! Let's compare"};
      String[] jesterHavePick = {"Wait...you already have one.", "I see you own one already.", "Why buy another?", "You hold one already."};
   
      String[] magicPick = {"There is a magic lockpick, precious to us!", "'Tis said thy magic lockpick makes thee more nimble!", "I will give all for a magic lockpick!", "Does thou know the keeper of the magic lockpick?"};
      String[] buyMagicPick = {"You have the magic pick! I will BUY it from you for all the gold I have!", "My magic pick! Let me BUY it back for 1000 gold!", "The most precious pick! I shall BUY it from you for all the gold I own!", "'Tis incredible! You have the magic pick! 1000 gold for me to BUY it from thee!"};
      String[] stoleMagicPick = {"Thank you for the magic pick! Here is your zero gold!", "A fine transaction. I have zero gold! Thanks for the pick!", "I take the pick, and here is your zero gold! Goodbye!", "An excellent trade: your pick for my zero gold. Good-day!"};
   
      String[] jesterCastle = {"I trained agility with the castle jesters", "The castle jesters can TRAIN thee to move swiftly", "The jesters of the castle TRAIN for swiftness", "You can TRAIN with the castle jesters for agility"};
   
      String[] jesterTrain = {"Your GOLD_AMOUNT gold weighs you down: hand it here to train with me", "Give GOLD_AMOUNT gold to me: I shall train thee and more swift thee shall be", "Agility requires training: training requires GOLD_AMOUNT gold", "I will train fleet of foot for GOLD_AMOUNT gold"};
      String[] jesterTrainYes = {"You learn quick...", "Quickly you learn to be more quickly", "Thy new swiftless complements thee", "See how well I teach"};
      String[] jesterTrainNo = {"You are quicker than I", "Thee should be teaching me", "I can train you no more", "I know not what else I can teach thee"};
   
      String[] winAPick = {"Well done. You win my extra lockpick!", "Correct! Take my extra lockpick!", "You are right. You may have my extra lockpick.", "Tremendous! I give thee my extra lockpick!"};
      String[] charmRing = {"You are right! I have a precious charmring in my pocket!", "How did you know? I have a precious charmring in my pocket! Don't try to snatch it!", "Yes! I have a precious charmring in my pocket! Don't try to take it!", "'Tis true! I have a precious charmring in my pocket! Don't rob me!"};
      String[] correctAns = {"Well done. I hope thee enjoys the experience!", "Correct! I hope thee enjoys the experience!", "You are right. I hope thee enjoys the experience!", "Tremendous! I hope thee enjoys the experience!"};
   
      String[] viperNo = {"I know of the deadly Viperglove, but have no use for it", "We thieves do not use the Viperglove, but a wicked thief might be able to get you one", "Not all thieves are so wicked as to steal a poisonous Viperglove", "Thieves are not assassins, but some may be able to acquire a Viperglove"};
      String[] viperLater = {"I know where one can be acquired! Find me at night and we shall trade", "Sssshhh! Speak not of this now! Seek me out in the dark of night to trade", "Quiet! Not here, not now! We shall trade when the curatin of night falls", "Speak not of this in the light of day. Find me tonight and we might trade"};
      String[] viperNowGood = {"We can agree to trade this very rare Viperglove for 3000 gold", "3000 gold to trade for this rare Viperglove", "Trade 3000 gold for this Viperglove, so hard to acquire", "This Viperglove was hard to steal. It is yours for 3000 gold"};
      String[] viperNowBad = {"We can agree to trade this Viperglove for 3000 gold with a <HANDSHAKE>", "3000 gold to trade for this Viperglove, sealed with a <HANDSHAKE>", "Trade 3000 gold for this Viperglove. Seal the deal with a <HANDSHAKE>", "This Viperglove is yours for 3000 gold and a <HANDSHAKE>"};
      String[] viperThanks = {"It is yours. Speak nothing of this, and seek thee a cure", "A fair trade. Tell noone of our meeting, and find thee a poison's cure", "'Tis done. Keep our meeting in confidence and seek thee a poison's cure", "A fair trade. Keep our transaction a secret and treat thyself to a poison's cure"};
      String[] viperHandshake = {"'Twill cost thee coin and a <HANDSHAKE>", "I demand a <HANDSHAKE> for our transaction", "Coin and a <HANDSHAKE> is required to complete our trade", "Thou needeth coin, and I need a <HANDSHAKE> to seal the deal"};
   
      String[] futureResponse = {"I can see thy future: thee shall ask me of thy future", "Thy future? 'Tis that which will be but has yet come to pass", "I doth not think thee has a future in comedy", "Thy future is clear: thee will drop thy trousers and whistle a fine tune"};
   
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(selected.getNumInfo()==10)
      {                                               //column 0 is the riddle, col 1 is the answer
         String riddleAns = riddleQst[CultimaPanel.player.getRiddleInfo()%riddleQst.length][1];     
         boolean correct = (typed.length()> 0 && riddleAnsCorrect(typed, riddleAns));
            
         if(!selected.getMissionInfo().equals("none") || (selected.getMissionInfo().equals("none") && !correct && Math.random() < 0.075))
         {
            if(selected.getReputation() < -10)//make it so the jester won't chase you any more
               selected.setReputation(-5);
            selected.setMoveTypeTemp(ROAM);
            return Mission.jesterMission(selected);
         }
         else if(selected.getMissionInfo().equals("none") && correct)
         {                                      //solve riddle
            selected.setNumInfo(0);
            CultimaPanel.player.nextRiddleInfo();
            CultimaPanel.player.addExperience(100);
            CultimaPanel.player.specificExperience[Player.MIND]++;
            if(selected.getReputation() < -10)//make it so the jester won't chase you any more
               selected.setReputation(-5);
            selected.setMoveTypeTemp(ROAM);
            if(typed.contains("ring"))
            {
               if(CultimaPanel.player.getRiddleInfo()==riddleQst.length)
                  Achievement.earnAchievement(Achievement.THE_RIDDLER);
               selected.addItem(new Item("charmring",1000));
               return getRandomFrom(charmRing);
            }
            if(selected.hasItem("lockpick") && !CultimaPanel.player.hasItem("lockpick"))
            {
               CultimaPanel.player.addItem("lockpick");
               return getRandomFrom(winAPick);
            }
            FileManager.saveProgress();
            return getRandomFrom(correctAns);
         }
         else
         { //ask riddle
            String temp = riddleQst[CultimaPanel.player.getRiddleInfo()%riddleQst.length][0];
            if(temp.contains("~~"))
            {
               String [] parts = temp.split("~~");
               return parts[0].trim();
            }
            else
               return temp;
         }
      }
      else if(typed.contains("viper") || typed.contains("glove") || typed.contains("poison"))
      {
         if(selected.getReputation() >= 0)
            response = getRandomFrom(viperNo);
         else
         {
            if(CultimaPanel.isNight)
            {
               if(selected.getReputation() <= -100)
                  response = getRandomFrom(viperNowBad);
               else
                  response = getRandomFrom(viperNowGood);
               if(!selected.hasItem("viperglove"))
                  selected.addItem(Item.getViperGlove());
            }
            else
               response = getRandomFrom(viperLater);
         }
      }
      else if(selected.hasItem("viperglove") && CultimaPanel.isNight)
      {
         if(typed.contains("3000"))
         { 
            if(selected.getReputation() <= -100)
               response = getRandomFrom(viperHandshake);
            else
            {
               if(CultimaPanel.player.getGold() < 3000)     //we don't have the money to buy one
                  response = getRandomFrom(bribeNoMoney); 
               else
               {
                  response = getRandomFrom(viperThanks); 
                  selected.removeItem("viperglove");
                  CultimaPanel.player.addItem("viperglove");
                  CultimaPanel.player.pay(3000);
                  selected.addGold(3000);
                  selected.setMoveTypeTemp(NPC.RUN);
               }
            }
         }
         else if(typed.contains("handshake") && selected.getReputation() <= -100)
         {
            if(CultimaPanel.player.getGold() < 3000)     //we don't have the money to buy one
               response = getRandomFrom(bribeNoMoney); 
            else
            {
               response = getRandomFrom(viperThanks); 
               selected.removeItem("viperglove");
               CultimaPanel.player.addItem("viperglove");
               CultimaPanel.player.pay(3000);
               selected.addGold(3000);
               CultimaPanel.player.addEffect("poison");
               selected.setMoveTypeTemp(NPC.RUN);
            } 
         }
      }
      else if(typed.contains("jester") || typed.contains("job"))
         response = getRandomFrom(jester);
      else if(typed.contains("future"))
         response = getRandomFrom(futureResponse);
      else if(typed.equals("lock") || typed.equals("unlock"))
         return getRandomFrom(jesterLock);   
      else if(typed.contains("magic"))
      {
         if(CultimaPanel.player.hasItem("magicpick"))
            return getRandomFrom(buyMagicPick);
         else
            return getRandomFrom(magicPick);
      }
      else if((typed.contains("buy") || typed.contains("1000")) && CultimaPanel.player.hasItem("magicpick"))
      {
         CultimaPanel.player.removeItem("magicpick");
         selected.addItem(new Item("magicpick",1000));
         selected.setMoveTypeTemp(NPC.RUN);
         return getRandomFrom(stoleMagicPick);
      }
      else if(typed.contains("pick"))
      {
         if(selected.hasItem("lockpick") && !CultimaPanel.player.hasItem("lockpick"))
            return getRandomFrom(jesterPick);
         if(CultimaPanel.player.hasItem("lockpick") && !selected.hasItem("lockpick"))
            return getRandomFrom(jesterBuyPick)+".";
         if(selected.hasItem("lockpick") && CultimaPanel.player.hasItem("lockpick"))
            return getRandomFrom(jesterBothHave);
         return getRandomFrom(jesterNoPick);
      }
      else if(typed.contains("ten") || typed.equals("10"))
      {
         if(selected.hasItem("lockpick") && !CultimaPanel.player.hasItem("lockpick"))
         {  //we are trying to buy a lockpick from a jester
            if(CultimaPanel.player.getGold() < 10)     //we don't have a pick, out we don't have the money to buy one
               response = getRandomFrom(bribeNoMoney);
            else
            {
               CultimaPanel.player.pay(10);
               selected.addGold(10);
               response = getRandomFrom(jesterSell);
               CultimaPanel.player.addItem("lockpick");   
            }
         }
         else if(!selected.hasItem("lockpick") && CultimaPanel.player.hasItem("lockpick"))
         {  //jester is buying a lockpick from us
            CultimaPanel.player.removeItem("lockpick");   
            selected.addItem(new Item("lockpick", 10));
            CultimaPanel.player.addGold(10);
            response = getRandomFrom(jesterSell);
         }
         else if(selected.hasItem("lockpick") && CultimaPanel.player.hasItem("lockpick")) 
            return getRandomFrom(jesterHavePick);
         else
            response = getRandomFrom(jesterNoPick);
      }
      else if(typed.contains("castle"))
         response = getRandomFrom(jesterCastle);
      //~~**JESTER training
      else if((selectedLoc.contains("castle") || selectedLoc.contains("tower") ) && (typed.contains("agil") || typed.contains("train") || typed.contains("speed") || typed.contains("quick")))
      {
         int amount = amountToTrainJester(selected);
         String tempResponse = "";
         tempResponse = getRandomFrom(jesterTrain);
         tempResponse = tempResponse.replace("GOLD_AMOUNT",""+amount);
         response = tempResponse;  
      }
      else if((selectedLoc.contains("castle") || selectedLoc.contains("tower") ) && typed.contains(""+amountToTrainJester(selected)))
      {
         int amount = amountToTrainJester(selected);
         if(CultimaPanel.player.getAgilityRaw() >= 50)
            response = getRandomFrom(jesterTrainNo);
         else if(CultimaPanel.player.getGold() < amount)
            return getRandomFrom(bribeNoMoney)+".";
         else
         {
            selected.setHasTrained(true);
            response = getRandomFrom(jesterTrainYes)+".";
            CultimaPanel.player.pay(amount);
            selected.addGold(amount);
            CultimaPanel.player.setAgility(Math.min(50, CultimaPanel.player.getAgilityRaw()+Player.rollDie(3)));
            return response;
         }                
      } //~~*~~end JESTER training
      else 
         return generalResponse(selected, typed);    
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String luteResponse(NPCPlayer selected, String typed)
   {
      String[] lute = {"I make SONG", "'Tis the life of MUSIC for me", "Shall I play thee a SONG", "I compose merry MUSIC", "I play MUSIC for the weary traveler", "I strum this LUTE to ease my troubles", "I will play a SONG or two", "We are going to put the band back together", "I seek the Lute Of DESTINY"};
      String[] luteSong = {"Music sweet enough to lift the CURSES away", "Songs warm enough to chase thy DEMONS away", "DEMONS run from my sweet song", "CURSES carried away by my joyful music", "To hear my song is as good as wearing a pentangle"};
      String[] luteCurse = {"That song is difficult: 'twill require 60 coins", "I will play that song for 60 gold", "60 coins are all I ask to play that song", "You want the best, you got the best: 60 gold, please"};
      String[] lutePay = {"My sweet music has lifted your curse!", "Thy demons have left you", "This the sound of the song that lifts thy curse", "'Tis the music that purges your demons"};
      String[] luteNoCurse = {"Your soul is clean: 'twould be a waste to play it now", "You have no curse: 'tis not the time", "You carry not the demon's burden: seek this song when thee does", "This song is not for a clean spirit such as thee"};
      String[] luteDestinyBuy = {"You have the lute of Destiny.  Will you sell it for 200 coins?", "Thy lute is magestic! I will buy it from thee for 200 coins.", "'Tis the lute of LEGEND! Wilt thou sell it for 200 coins?", "200 coins! I will give you 200 coins for your lute!"};
   //to do: sell the Lute Of Destiny for 200 coins		
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               return  getRandomFrom(missionPrice).replace("AMOUNT",typed);
            if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            return getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
            return Mission.luteMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      else if(CultimaPanel.player.getImageIndex()==Player.LUTE)
      {
         if(typed.equals("200"))
         {
            Weapon dropped = CultimaPanel.player.discardWeapon("Lute-O-Destiny");
            selected.setWeapon(dropped);
            CultimaPanel.player.addGold(200);
            selected.setGold(0);
            return getRandomFrom(beggerThanks);
         }
         else
            return getRandomFrom(luteDestinyBuy);
      }
      else if(typed.contains("instrument") || typed.contains("strum") || typed.contains("lute") || typed.contains("guitar") || typed.contains("job"))
         response = getRandomFrom(lute);
      else if(typed.contains("song") || typed.contains("music") || typed.contains("band")  || typed.contains("trouble"))
         response = getRandomFrom(luteSong);
      else if(typed.contains("curse") || typed.contains("demon"))
         response = getRandomFrom(luteCurse);
      else if(typed.contains("destiny"))
         response = getRandomFrom(luteDestiny);   
      else if(typed.contains("sixty") || typed.contains("60"))
      {
         if(CultimaPanel.player.getGold() < 60)
            return getRandomFrom(bribeNoMoney);
         else if(!CultimaPanel.player.hasEffect("curse"))
         {
            response = getRandomFrom(luteNoCurse);
         }
         else
         {
            response = getRandomFrom(lutePay);
            CultimaPanel.player.removeEffect("curse");
            CultimaPanel.player.removeEffect("sullied");
            CultimaPanel.player.removeEffect("seduced");
            CultimaPanel.player.pay(60);
            selected.addGold(60);
         }  
      }
      else
         return generalResponse(selected, typed);    
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String hermitResponse(NPCPlayer selected, String typed)
   {
      String[] hermitTrain = {"I can TRAIN you to heighten your awareness of the world around you", "'Tis TRAINING I offer to increase awareness of the natural world", "'Tis the connection to the world around that we TRAIN", "I can TRAIN thee to be more aware of the world that surrounds us"};
      String[] hermitPayment = {"Thou must AGREE to relinquish all unnatural possessions", "AGREE you must to relieve yourself of all unnatural possessions", "Your metal possessions: they are unnatural.  Thou must AGREE to dismiss them", "You must AGREE to relieve yourself of all unnatural possessions"};
      String[] hermitTrainNo = {"I can no longer share my training with you", "I cannot train you any more", "Further training is forbidden by me", "You must seek other teachers than myslef", "Beat it or I'll call the Brute Squad"};
      String[] hermitTrainYes = {"You respond well to nature and are now more aware", "You train well, and will see further into the world around us", "Thee has become more aware of the world around", "You are an excellent student, and have become more aware of the world around us"};
      String[] hermitCurseHelp = {"I can PURGE thy terrible curse", "Allow me to PURGE thy curse", "Your heart will be restored if I PURGE thy curse", "Thy curse weighs heavy on thee. I can PURGE thy curse for you"};
      String[] hermitCureCurseYes = {"Your curse has been lifted. Live thy life in peace.", "Thee is human once again. Go back into the world with peace.", "The curse is cast out. Return to the world in peace.", "Your heart can rest as a human again. You may go in peace."};
      String name = selected.getName().toLowerCase();
   
      if(typed.contains("aware"))
      {
         if(selected.hasTrained() || CultimaPanel.player.getAwareness() >= 3)
            return getRandomFrom(hermitTrainNo);
         return getRandomFrom(hermitTrain);
      }
      if(typed.contains("train"))
      {
         if(selected.hasTrained() || CultimaPanel.player.getAwareness() >= 3)
            return getRandomFrom(hermitTrainNo);
         return getRandomFrom(hermitPayment);
      }
      if((CultimaPanel.player.isVampire() || CultimaPanel.player.isWerewolf()) && (typed.contains("vamp") || typed.contains("bat") || typed.contains("blood") || typed.contains("curse") || typed.contains("wolf")))
      {
         return getRandomFrom(hermitCurseHelp);
      }
      if((CultimaPanel.player.isVampire() || CultimaPanel.player.isWerewolf()) && typed.contains("purge"))
      {
         return getRandomFrom(hermitPayment);
      }
      if(!CultimaPanel.player.isVampire() && !CultimaPanel.player.isWerewolf() && typed.contains("agree"))
      {
         if(selected.hasTrained() || CultimaPanel.player.getAwareness() >= 3)
            return getRandomFrom(hermitTrainNo);
         CultimaPanel.player.setGold(0);
         CultimaPanel.player.clearUnnaturalItems();
         CultimaPanel.player.clearWeapons();
         CultimaPanel.player.clearUnnaturalArmor();
         CultimaPanel.player.increaseAwareness();
         selected.setHasTrained(true);
         return getRandomFrom(hermitTrainYes);
      }
      if(CultimaPanel.player.isVampire() && typed.contains("agree"))
      {
         CultimaPanel.player.setGold(0);
         CultimaPanel.player.clearItemsExceptBounties();
         CultimaPanel.player.clearWeapons();
         CultimaPanel.player.clearArmor();
         Utilities.removeVampireCurse();
         return getRandomFrom(hermitCureCurseYes);
      }
      if(CultimaPanel.player.isWerewolf() && typed.contains("agree"))
      {
         CultimaPanel.player.setGold(0);
         CultimaPanel.player.clearItemsExceptBounties();
         CultimaPanel.player.clearWeapons();
         CultimaPanel.player.clearArmor();
         Utilities.removeWerewolfCurse();
         return getRandomFrom(hermitCureCurseYes);
      }
      if(selected.getCharIndex()==WISE && typed.contains("cave"))
         return getRandomFrom(caveInfo);
      if(name.contains("butcher") || name.contains("baker"))  
         return butcherBakerResponse(selected, typed);
      if(name.contains("mage"))
         return mageResponse(selected, typed);
      if(selected.getCharIndex()==WISE)
         return wiseResponse(selected, typed);
      return manWomanResponse(selected, typed);
   }

   public static String wiseResponse(NPCPlayer selected, String typed)
   {
      String[] wiseCityJob = {"We teach heightening of MIND and awareness", "We teach expansion of the MIND and awareness", "'Tis the MIND that we fill, and awareness that is achieved", "AWARENESS of mind awaits those willing to study", "We will take a DONATION of 10 gold for the temple", "We seek the NUMBER hidden in thy mind", "We can see thy very FUTURE and guide thee to be better prepared", "We can predict thy very WEATHER before it strikes"};
      String[] wiseAwareJob = {"TRAIN mind with us, seek awareness from they who lives alone", "TRAIN with the monks, but seek awareness from the hermit", "We monks TRAIN the mind, thy hermits teach awareness", "The mind TRAINS with us, but seek they who live alone to learn awareness"};
      String[] wiseCityFind = {"Seek out my kin at the great TEMPLE", "'Tis the TEMPLE you must find", "Look for my bretheren at the TEMPLE", "My brothers at the TEMPLE can help you"};
      String[] wiseTemple = {"Let your senses guide you there", "Learn these great lands to find it", "Wander the lands to earn your entrance", "Travel far and you shall find what you seek"};
      String[] wiseTempleSecret = {"A sacred temple's location is a SECRET", "'Tis a SECRET place that must be discovered", "A SECRET location only for those worthy", "SECRET kept, our temple's location"};
      String[] wiseTempleBribe = {"'Twill require 300 gold!", "'Twill be revealed by 300 gold", "Dotate 300 gold to be guided to it", "Thou shalt give 300 gold to learn a temple's location"};
      String[] wiseThanks = {"I thank thee", "May the spirits of the temple guide you", "'Tis very kind of you", "You have my deepest thanks"};
      String[] wiseTempleMarked = {"'Tis revealed on your map", "This sacred place is here on your map", "A sacred place, here on your map", "'Tis a sacred place, revealed here on your map"};
      String[] wiseTempleNotMarked = {"I know not how to get there", "My memory escapes me", "It is not known", "You must seek it out yourself"};
      String[] wiseKnowTemple = {"'Tis already marked on your map", "I see you know of this place", "A sacred place that is already on your map", "You have been to this place"};
      String[] wiseSpell = {"Some spells are drawn from thy mind, some are drawn from thy staff", "The magic SHOPPE can teach an open mind a new spell for gold", "You are bound to thy staff: it carries part of your soul", "Thy staff carries the spells thy mind cannot", "Seek our downtrodden brothers: help them for a spell", "You may not drop thy staff: it is bound to you"};
      String[] wiseTempleJob = {"Relieve yourself thy weight of GOLD_AMOUNT gold", "'Twill take GOLD_AMOUNT gold to study with us", "GOLD_AMOUNT gold is in need for thy mind to feed", "Sacrifice GOLD_AMOUNT gold to thy mind's expanse"};
      String[] wiseTempleNo = {"Thy paltry gold is insufficient!", "Your gold is too meager for the Gods!", "The Spirits demand much more!", "Too slender a sum for this great temple!"};
      String[] wiseMind = {"Study with us...thy mind is awakened", "Chant with us...thy mind opens", "Read with us...thy mind responds", "Study with me...thy mind is awakened", "Chant with me...thy mind opens", "Read with me...thy mind responds"};
      String[] wiseAware = {"Study with us...thy awareness is sharpened", "Chant with us...thy awareness grows", "Read with us...thy awareness heightens", "Study with me...thy awareness is sharpened", "Chant with me...thy awareness grows", "Read with me...thy awareness heightens"};
      String[] wiseMindSpell = {"Study with me: I will teach you SPELL_NAME", "Chant with me: learn the SPELL_NAME spell", "Read with me to learn a new spell - SPELL_NAME", "Focus with me: I will teach you a new spell called SPELL_NAME"};
      String[] wiseMindWeapon = {"Teach thy staff this new spell, SPELL_NAME", "Thy staff can now envoke SPELL_NAME", "Thy staff is empowered with this new spell called SPELL_NAME", "Focus with me to enchant thy staff with this new spell called SPELL_NAME"};
      String[] wiseMindPotion = {"Take this potion for your kindness", "A potion is all I can give to repay you", "Take thee this potion for your grace", "A potion is all I can offer for your great deeds"};
      String[] numberResponseYes = {"'Tis 50 by 50 at 50", "I see three of 50: 50 by 50 at 50", "'Thy number you seek is 50: 50 by 50 at 50", "'Tis there - 50 by 50 at 50", "See what I point towards: 50 by 50 at 50"};
      String[] fiftyResponseYes = {"One needs to be adept enough - adept by thy number", "One needs to be Southward - Southward by thy number", "One needs to be Eastward - Eastward by thy number", "One needs to be legend, or monster"};
      String[] weatherResponse = {"The FUTURE holds a RAIN_CHANCE in 10 chance that it will PRECIP_TYPE", "The pain in my knee predicts a RAIN_CHANCE in 10 chance that PRECIP_TYPE will fall", "The skies will open and thy PRECIP_TYPE will fall in RAIN_CHANCE of 10 spins of the wheel", "All those under the sky will feel PRECIP_TYPE in RAIN_CHANCE of 10 tosses of the coin"};
      String[] bridgeKeeperLastQuestion = {"What...is your favorite colour", "What...is the capital of Assyria", "What... is the average air-speed velocity of an unladen swallow?"};
      String[] quests = {"number", "red", "square", "skara", "brae", "grail"};
      String[] colors = {"red", "orange", "yellow", "green", "blue", "indigo", "violet", "pink", "purple", "white", "gray", "grey", "black", "brown", "cyan", "magenta", "mahogany"};
      String[] capitals = {"nineveh", "kalhu", "assur", "harran", "kar-tukulti-ninurta","kar tukulti ninurta","dur-sharrukin","dur sharrukin","carchemish","ekallatum","tell leilan","tell-leilan"};
      String[] swallowBestAns = {"africa", "europe"};
      String[] swallowGoodAns = {"11", "24"};
      String[] caves = {"The hermit wiseman knows of the mysterious green caves", "Seek the hermit wiseman and ask of the green caves", "Knowledge of the moss caves is known by the hermit wiseman", "The strange magic of the moss caves is known to the hermit wiseman"};
      String[] eggInfo = {"A DRAGON egg is difficult to harvest, lest thee tangle with fire and death", "King GARRIOTT, King of all Cultima, is said to have the Wisdomegg", "Seek King GARRIOTT of the CAPITAL to learn the secrets of the Wisdomegg", "A DRAGON egg will supplement the might of the lucky warrior to consume it", "A Wisdomegg is not from this time, but brought here will give its owner power of the mind"};
      String[] rainbowInfo = {"Thy arching colors lift better the magic of the mages", "Thy mage shoppe items may be at a discount when the rainbow colors fall across the sky", "One casting magic under the rainbow will find it takes less manna", "Thy manna will recover quite faster when the colors of the rainbow are cast overhead", "Use thy potent spells when the rainbow is above, and less energy will be spent"};
      String[] futureResponse = {"I can see in thy future a battle with a ADJECTIVE MONSTERTYPE", "In my eye I can see thy future. Thee shall meet in thy plains a ADJECTIVE MONSTERTYPE", "A ADJECTIVE MONSTERTYPE awaits thee in thy near future", "Thy future has a struggle against a ADJECTIVE MONSTERTYPE"};
      String[] futureGeneral = {"Thy future is clouded. I can not see it for thee", "In my eye I can see thy future. Thou shall seek the NUMBER and thy reddened square made of four", "Seeking thy red square awaits thee in thy near future", "Thy future has a quest of greatest import...to seek the NUMBER"};
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
   
      if(selectedLoc.contains("temple") && selected.getName().equalsIgnoreCase("Keeper of the Bridge"))
      { 
         if(selected.getNumInfo()==4)
         {
            selected.setNumInfo(selected.getNumInfo()-1);
            return "Who would cross the bridge must answer me these QUESTIONS three";
         }
         if(typed.contains("question"))            //what is your name question
         {
            selected.setNumInfo(3);
            return "What...is your name";
         }
         if(selected.getNumInfo()==3)              //what is your name answer, what is your quest question
         {
            if(CultimaPanel.player.getName().toLowerCase().contains(typed.toLowerCase()))
            {
               selected.setNumInfo(selected.getNumInfo()-1);
               return "What...is your quest";
            }
            else                                   //name answer wrong
            {
               CultimaPanel.player.damage(CultimaPanel.player.getBody());
               selected.setAttackHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               selected.setNumInfo(4);
               return "Heh heh heh";
            }
         }
         if(selected.getNumInfo()==2)        //what is your quest answer
         {
            boolean goodResponse = false;
            for(String c:quests)
               if(typed.contains(c))
               {
                  goodResponse = true;
                  break;
               }
            if(goodResponse)
            {
               int lastQuestion = Player.rollDie(0,2); 
               selected.setNumInfo(lastQuestion + 20);//20 for color, 21 for capital, 22 for swallow
               return bridgeKeeperLastQuestion[lastQuestion];
            }
            else                             //quest answer wrong
            {
               CultimaPanel.player.damage(CultimaPanel.player.getBody());
               selected.setAttackHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               selected.setNumInfo(4);
               return "Heh heh heh";
            }
         }
         if(selected.getNumInfo()==20)                //what is your favorite color answer
         {
            boolean goodResponse = false;
            for(String c:colors)
               if(typed.equalsIgnoreCase(c))
               {
                  goodResponse = true;
                  break;
               }
            if(goodResponse)
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               selected.setNumInfo(0);
               selected.setMoveType(NPC.RUN);
               CultimaPanel.player.addExperience(100);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               FileManager.saveProgress();
               Achievement.earnAchievement(Achievement.SCENE_24);
               return "Right. Off you go.";
            }
            else
            {
               CultimaPanel.player.damage(CultimaPanel.player.getBody());
               selected.setAttackHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               selected.setNumInfo(4);
               return "Heh heh heh";
            }
         }
         if(selected.getNumInfo()==21)                //what is the capital of assiria answer
         {
            boolean goodResponse = false;
            for(String c:capitals)
               if(typed.contains(c))
               {
                  goodResponse = true;
                  break;
               }
            if(goodResponse)
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               selected.setNumInfo(0);
               selected.setMoveType(NPC.RUN);
               CultimaPanel.player.addExperience(100);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               FileManager.saveProgress();
               Achievement.earnAchievement(Achievement.SCENE_24);
               return "Right. Off you go.";
            }
            else
            {
               CultimaPanel.player.damage(CultimaPanel.player.getBody());
               selected.setAttackHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               selected.setNumInfo(4);
               return "Heh heh heh";
            }
         }
         if(selected.getNumInfo()==22)                //what is the air velocity answer
         {
            boolean goodResponse = false;
            for(String c:swallowGoodAns)
               if(typed.contains(c))
               {
                  goodResponse = true;
                  break;
               }
            boolean bestResponse = false;
            for(String c:swallowBestAns)
               if(typed.contains(c))
               {
                  bestResponse = true;
                  break;
               }
         
            if(bestResponse)
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               selected.damage(selected.getBody(),"");
               selected.setHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               CultimaPanel.player.addExperience(selected.getLevel());
               Corpse dead = selected.getCorpse();
               dead.setHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               CultimaPanel.corpses.get(selected.getMapIndex()).add(dead);
               Utilities.removeNPCat(selected.getRow(), selected.getCol(), selected.getMapIndex());
               CultimaPanel.player.addExperience(100);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               FileManager.saveProgress();
               Achievement.earnAchievement(Achievement.SCENE_24);
               return "Huh? I don't know that! EEEEEEAAAARRGH!";
            }
            else if(goodResponse)
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               selected.setNumInfo(0);
               selected.setMoveType(NPC.RUN);
               CultimaPanel.player.addExperience(100);
               Player.stats[Player.MISSIONS_COMPLETED]++;
               if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                  Achievement.earnAchievement(Achievement.TASK_MASTER);
               FileManager.saveProgress();
               Achievement.earnAchievement(Achievement.SCENE_24);
               return "Right. Off you go.";
            }
            else
            {
               CultimaPanel.player.damage(CultimaPanel.player.getBody());
               selected.setAttackHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
               selected.setNumInfo(4);
               return "Heh heh heh";
            }
         }
      }
      if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               response = getRandomFrom(missionPrice).replace("AMOUNT",typed);
            else  if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            response = getRandomFrom(mission);  
      }
      else if(selectedLoc.contains("cave") && selected.getName().contains("Puzzle"))
      {
         return towersPuzzleResponse(selected, typed);
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
      
         if(selected.getNumInfo()==10)
            return Mission.wiseMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      else if(typed.contains("number"))
      {
         if(selected.getLocationType().toLowerCase().contains("castle") || selected.getLocationType().toLowerCase().contains("tower"))
            response = getRandomFrom(numberResponseYes);
         else
            response = getRandomFrom(numberResponseNo);
      }
      else if(typed.contains("job"))
      {
         response = getRandomFrom(wiseCityJob);
      }
      else if(typed.contains("future"))
      {
         if(selectedLoc.contains("dungeon") || selectedLoc.contains("cave") || selectedLoc.contains("mine") || selectedLoc.contains("lair"))
         {
            int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, CultimaPanel.player.getRow(), CultimaPanel.player.getCol(), CultimaPanel.player.getMapIndex());
            Location currentLoc = null;
            if(currentLocIndex >= 1)
            {
               currentLoc = CultimaPanel.allLocations.get(currentLocIndex);   
               ArrayList<Point> monsterFreq = currentLoc.getMonsterFreq();
               if(monsterFreq.size() > 0)
                  CultimaPanel.nextMonsterSpawn = (byte)(monsterFreq.get(0).getX());
               else
                  response = getRandomFrom(futureGeneral);
            }
         }
         else if(CultimaPanel.nextMonsterSpawn == -1)
         {
            CultimaPanel.nextMonsterSpawn = NPC.randomWorldMonsterSunny();
            if(CultimaPanel.weather > 0)
               CultimaPanel.nextMonsterSpawn = NPC.randomWorldMonsterRaining();                              
            if(Player.rollDie(125 - ((CultimaPanel.player.getLevel()%10)*10)) <= 1)    //chance we will spawn a monster king
            {
               CultimaPanel.nextMonsterSpawn = NPC.randomMonsterKing();
               if(CultimaPanel.player.hasDeathMission() && Math.random() < 0.5)
                  CultimaPanel.nextMonsterSpawn = NPC.DEATH;
            } 
         }
         response = getRandomFrom(futureResponse).replace("ADJECTIVE", getRandomFrom(monsterAdjective)).replace("MONSTERTYPE",characterDescription(CultimaPanel.nextMonsterSpawn));
      }
      else if(typed.contains("egg"))
      {
         response = getRandomFrom(eggInfo);
      }
      else if (typed.contains("rainbow"))
         response = getRandomFrom(rainbowInfo);
      else if(typed.contains("cave") || typed.contains("underworld"))
      {
         response = getRandomFrom(caves);
      }
      else if(typed.contains("weather") || typed.equals("rain") || typed.contains("storm") || typed.contains("sun") || typed.contains("sky"))
      {
         String preciptype = "rain";
         if(Display.isWinter())
            preciptype = "snow";
         int rainChance = (int)(CultimaPanel.rain_probability * 20);
         if(CultimaPanel.weather > 0)
            rainChance = 10;
         String tempResp = getRandomFrom(weatherResponse).replace("RAIN_CHANCE", ""+rainChance).replace("PRECIP_TYPE", ""+preciptype);
         response = tempResp;
      }
      else if(typed.contains("well") && typed.contains("puzzle"))
      {
         response = getRandomFrom(wellsPuzzle);
      }
      else if(typed.contains("door") && typed.contains("puzzle"))
      {
         response = getRandomFrom(doorsPuzzle);
      }
      else if(typed.contains("tower") && typed.contains("puzzle"))
      {
         response = getRandomFrom(towersPuzzle);
      }
      else if(typed.contains("puzzle"))
      {
         response = getRandomFrom(puzzle);
      }
      else if(selected.getNumInfo() == 3 && typed.contains("spell"))   
      {  //a begger that has been restored and asked about a spell
         selected.setNumInfo(0);
         if (CultimaPanel.player.hasMagicStaff())
         {
         //find a spell that we don't know and teach it.  If we know them all, give the player a random potion
            boolean gifted = false;
            for(Item gift:CultimaPanel.allMagicInventory)
            {
               if(gift instanceof Spell)  //prioritize teaching spells
               {
               //if we don't have one, give it and set gifted to true
                  if(!CultimaPanel.player.hasItem(gift.getName()))
                  {
                     CultimaPanel.player.addSpell((Spell)(gift));
                     response = getRandomFrom(wiseMindSpell).replace("SPELL_NAME", gift.getName());
                     gifted = true;
                     break;
                  }
               }
            }
            if(!gifted)
            {
               for(Item gift:CultimaPanel.allMagicInventory)
               {
                  if(gift instanceof Weapon)  //no regular spells to teach, so teach a staff spell
                  {
                  //if we don't have one, give it and set gifted to true
                     if(gift.getName().toLowerCase().contains("lute") || gift.getName().toLowerCase().equals("staff"))
                        continue;
                     if(!CultimaPanel.player.hasItem(gift.getName()))
                     {
                        CultimaPanel.player.addWeapon((Weapon)(gift));
                        response = getRandomFrom(wiseMindWeapon).replace("SPELL_NAME", gift.getName());
                        gifted = true;
                        break;
                     }
                  }
               }
            }
            if(!gifted)
            {
            //give a random potion
               Potion gift = Potion.getRandomPotion();
               CultimaPanel.player.addPotion((Potion)(gift));
               response = getRandomFrom(wiseMindPotion);
            }
         }
         else  //player doesn't have a staff, so give a random potion
         {
         //give a random potion
            Potion gift = Potion.getRandomPotion();
            CultimaPanel.player.addPotion((Potion)(gift));
            response = getRandomFrom(wiseMindPotion);
         }
      }
      else if(selected.getLocationType().toLowerCase().contains("temple"))
      {
         if(typed.contains("aware"))
         {
            response = getRandomFrom(wiseAwareJob);
         }
         else if(typed.contains("magic") || typed.contains("mind") || typed.contains("train"))
         {
            int amount = amountToTrainWise(selected);
            String tempResponse = "";
            tempResponse = getRandomFrom(wiseTempleJob);
            tempResponse = tempResponse.replace("GOLD_AMOUNT",""+amount);
            response = tempResponse;
         }
         else if(typed.contains(""+amountToTrainWise(selected)))
         {
            int amount = amountToTrainWise(selected);
            if(CultimaPanel.player.getGold() >= amount && (CultimaPanel.player.getAwareness()<3 || CultimaPanel.player.getMindRaw() < 50))
            {
               selected.setHasTrained(true);
               CultimaPanel.player.pay(amount);
               selected.addGold(amount);
               if(CultimaPanel.player.getMindRaw() < 50)
               {
                  CultimaPanel.player.setMind(Math.min(50, CultimaPanel.player.getMindRaw()+Player.rollDie(3)));
                  response = getRandomFrom(wiseMind);
               }
               else //CultimaPanel.player.getAwareness()<3
               {
                  CultimaPanel.player.increaseAwareness();
                  response = getRandomFrom(wiseAware);
               }
            }
            else
               return getRandomFrom(wiseTempleNo);
         }
         else if(typed.equals("50") || typed.contains("fifty"))
         {
            if(selected.getLocationType().toLowerCase().contains("castle") || selected.getLocationType().toLowerCase().contains("tower"))
               response = getRandomFrom(fiftyResponseYes);
            else
               response = getRandomFrom(fiftyResponseNo);
         }
         else if((typed.contains("donat") || typed.contains("10") || typed.contains("ten")) && CultimaPanel.player.getGold() >= 10)
         {
            if(CultimaPanel.player.stats[Player.MONEY_DONATED]>=500)
               Achievement.earnAchievement(Achievement.PHILANTHROPIST);
         
            CultimaPanel.player.pay(10);
            selected.addGold(10);
            Player.stats[Player.MONEY_DONATED]+=10;
            CultimaPanel.player.addReputation(1);
            response = getRandomFrom(wiseThanks);
            if(diff > 100 && CultimaPanel.player.getReputation() >=500)
               response += getRandomFrom(greetings2End); 
            else
               response += "!";
            return response;   
         }
         else
            return generalResponse(selected, typed);
      }
      else     //city or village wise-man - take donations or direct player to a temple
      {
         if(typed.contains("aware"))
         {
            response = getRandomFrom(wiseAwareJob);
         }
         else if(typed.contains("magic") || typed.contains("mind") || typed.contains("train"))
         {
            response = getRandomFrom(wiseCityFind);
         }
         else if(typed.contains("temple"))
         {
            if(selected.getReputation() > 10)
               response = getRandomFrom(wiseTemple);
            else
               response = getRandomFrom(wiseTempleSecret);
         }
         else if(typed.contains("secret") && selected.getReputation() <= 10)
         {
            response = getRandomFrom(wiseTempleBribe);
         }
         else if((typed.contains("three hundred") || typed.contains("300")) && selected.getReputation() <= 10)
         {
            if(CultimaPanel.player.getGold() < 300)
               return getRandomFrom(wiseTempleNo);
            else
            {
               if(selected.getNumInfo() <= 0)
               {
                  response = getRandomFrom(allInfoTold);
               }
               else
               {
                  Location loc =  TerrainBuilder.closeLocation(CultimaPanel.allTemples);
                  int found = -1;
                  if(loc!=null)
                     found = TerrainBuilder.markMapPath(loc.getName());
                  if(found==0 || CultimaPanel.player.getAwareness()==4)
                     response = "'Tis at " + loc.getCol() + " Westerly and " + loc.getRow() + " South";
                  else if(found==1)
                  {
                     CultimaPanel.player.pay(300);
                     selected.addGold(300);
                     response = getRandomFrom(wiseTempleMarked);
                     if(diff > 100 && CultimaPanel.player.getReputation() >=500)
                        response += getRandomFrom(greetings2End); 
                     else
                        response += "!";
                     return response;   
                  }
                  else if(found==-1)
                     response = getRandomFrom(wiseTempleNotMarked);
                  selected.setNumInfo(selected.getNumInfo()-1);
               }
            }
         }
         else if((typed.contains("donat") || typed.contains("10") || typed.contains("ten")) && CultimaPanel.player.getGold() >= 10)
         {
            CultimaPanel.player.pay(10);
            selected.addGold(10);
            Player.stats[Player.MONEY_DONATED]+=10;
            if(CultimaPanel.player.stats[Player.MONEY_DONATED]>=500)
               Achievement.earnAchievement(Achievement.PHILANTHROPIST);
         
            CultimaPanel.player.addReputation(1);
            response = getRandomFrom(wiseThanks);
            if(diff > 100 && CultimaPanel.player.getReputation() >=500)
               response += getRandomFrom(greetings2End); 
            else
               response += "!";
            return response;   
         }
         else if(typed.contains("spell") || typed.contains("staff") || typed.contains("magic"))
         {
            response = getRandomFrom(wiseSpell); 
         }
         else if(typed.equals("50") || typed.contains("fifty"))
         {
            if(selected.getLocationType().toLowerCase().contains("castle") || selected.getLocationType().toLowerCase().contains("tower"))
               response = getRandomFrom(fiftyResponseYes);
            else
               response = getRandomFrom(fiftyResponseNo);
         }
         else 
            return generalResponse(selected, typed);
      }
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String barkeepResponse(NPCPlayer selected, String typed)
   {
      String[] tavernJob = {"I serve ale and INFORMATION", "ALE and guidance for the weary traveler", "A drink and GUIDANCE for the mighty adventurer", "I have ALE and information for you", "Read the sign, did you"};
      String[] tavernInfo = {"I know the TOWNSFOLK well", "I can tell thee all about the TOWNSFOLK", "The TOWNSFOLK here: a jumbeld lot", "'Tis the TOWNSFOLK I can tell you of"};
      String[] tavernHint = {"'Twas an in-game hint", "A hint to help guide thee", "Thy 4th wall is broken: 'twas an in-game hint", "That word was but a hint"};
      String[] tavernDrink = {"I thank thee", "Drink up and be merry", "'Tis the finest ale 'round", "Your patronage is welcome"};
      String[] tavernDrink2 = {"Feeling a bit better I see", "I see that ale did thee good", "I see you feel restored", "I detect a bit of rejuvenation in thee"};
      String[] noDog = {"Leave thy DOG outside: 'tis a clean tavern I run.", "'Tis no place for DOG scat: set it outside", "Please leave thy DOG outside", "I cannot serve thee with thy DOG in my tavern"};
      String[] futureGeneral = {"Thy future is within this goblet! A DRINK for a coin", "An ALE is in thy future! One coin, please", "Thy future is clear! Thou shall enjoy one ALE for coin", "Thy future is plain to see! A DRINK for thy belly! One gold coin please"};
   
      if(Utilities.distToDog(selected) < 5)
         return getRandomFrom(noDog);
   
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
   
      if(typed.contains("job"))
         response = getRandomFrom(tavernJob);
      else if(typed.contains("850") || typed.contains("75") || typed.contains("ship") || typed.contains("boat") || typed.contains("water") || typed.contains("vessel"))
         response = shipSales(selected, typed);
      else if(typed.contains("info") || typed.contains("guidance"))
         response = getRandomFrom(tavernInfo);
      else if(typed.contains("town"))
      {
         String temp = "";
         if(CultimaPanel.player.getTavernInfo() < tavernTown.length)
            temp = tavernTown[CultimaPanel.player.nextTavernInfo()];
         else
            temp = getRandomFrom(tavernTown);
         if(temp.contains("~~"))    //there is a tag for the number of the NPC referenced for the journal
         {
            String [] parts = temp.split("~~");
            response = parts[0].trim();
         }
         else
         {
            response = temp;
         } 
      }
      else if(isTownsfolk(typed))
         response =  getInfoFrom(tavernTown, typed);
      else if(typed.contains("spell") || typed.contains("odd") || typed.contains("day"))
         response = getRandomFrom(tavernHint); 
      else if(typed.contains("future"))
         response = getRandomFrom(futureGeneral); 
      else if(typed.contains("drink") || typed.contains("coin") || typed.contains("ale") || typed.contains("gold") || typed.contains("pay") || typed.contains("buy") || typed.contains("trade"))
      {
         if(CultimaPanel.player.getGold() < 1)
            return getRandomFrom(bribeNoMoney);
         else
         {
            CultimaPanel.player.pay(1);
            selected.addGold(1);
            if(CultimaPanel.player.getBody() < CultimaPanel.player.getHealthMax() && Math.random() < 0.5)
            {
               CultimaPanel.player.heal(1);
               response = getRandomFrom(tavernDrink2);   
            }  
            else
               response = getRandomFrom(tavernDrink);   
            CultimaPanel.player.addSulliedValue(5);
         }
      }
      else if(selected.getCharIndex()==MAN || selected.getCharIndex()==WOMAN)
         return manWomanResponse(selected, typed);
      else     //NPC does not understand
      {
         if(Math.random() < 0.5)
            return getRandomFrom(unknown) + " " + typed + "...";
         else
            response = getRandomFrom(unknown2);
      } 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String ironsmithResponse(NPCPlayer selected, String typed)
   {
      String[] armory = {"I smithe and TRADE the finest arms around", "I sell ARMS for all stock and size", "'Tis ARMS you seek, 'tis arms I sell", "I TRADE weaponry forged for the mightiest adventurer", "Read the sign, did you"};
      String[] armoryBuy = {"What shall it be", "Thee can buy or trade", "Many ARMS and armorments gaze upon", "Here are my wares"};
      String[] magicDiscount = {"For you, a 20% discount", "Any item has a 20% discount for an honoured coustomer like you", "You may have a 20% discount on any item", "'Tis such an honor, a 20% discount I give to you"};
      String[] noDog = {"Leave thy DOG outside: 'tis a clean shoppe I run.", "'Tis no place for DOG scat: set it outside", "Please leave thy DOG outside", "I cannot serve thee with thy DOG in my shoppe"};
      String[] oddAns  = {"Did thee not take the hint...", "'Tis an in-game hint", "I cannot spell out every hint for you...", "Does thee need every in-game hint explained..."};
      String[] buyCraftingManual = {"I can trade thee a Crafting Manual for 240 coins", "For 240 gold, thee can have thy Crafting Manual", "Thy Crafting Manual is precious. 'Twill take 240 gold to trade", "240 coins and I will trade thee my Crafting Manual"};
      String[] boughtCraftingManual = {"My vast knowledge of crafting ARMS is now in your fine manual", "Thee shall now have all the crafting knowledge you need", "With this, thee shall craft the finest of ARMS", "With this knowledge, thee shall make magic thy ARMS"};
      String[] craftArmor = {"I see you have SCALETYPE scales. I can craft them into armor for 845 gold", "Those SCALETYPE scales would make excellent armor. Only 845 coins for me to make it so", "For 845 gold, I can turn those SCALETYPE scales into the finest armor", "Most rare SCALETYPE scales you have. 'Twould make excellent armor, only 845 gold for me to build it"};
      String[] craftArmorDone = {"Here is your armor. It shall protect thee well", "You will not find armor more fine that this! I see it fits you as well", "This scale armor will make thee indestructable! Fare thee well", "Thy armor is done! May it serve thee well in combat"};
      String[] canCraftArmor = {"Slay thee a DRAGON or seaserpent and I can craft thee armor from its scales", "If thou art mighty enough to take down a DRAGON or seaserpent, bring me its scales and I can make thee armor", "I can fashion the finest armor from DRAGON or seaserpent scales", "With the scales from a DRAGON or seaserpent, I can craft mighty armor"};
      String[] futureGeneral = {"Thy future is a fine TRADE of coin for weaponry", "I can see thy future - a TRADE of my weapons for thy coins", "Thy future is clear as day! Thou shall purchase ARMS from thee", "A TRADE of gold for iron is in thy future"};
   
      if(Utilities.distToDog(selected) < 5)
         return getRandomFrom(noDog);
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
   
      if(typed.contains("job"))
         response = getRandomFrom(armory);
      else if(typed.contains("850") || typed.contains("75") || typed.contains("ship") || typed.contains("boat") || typed.contains("water") || typed.contains("vessel"))
         response = shipSales(selected, typed);
      else if(typed.contains("legend") || typed.contains("legendary"))
         response = getRandomFrom(legendaryWeaponInfo);  
      else if((selectedLoc.contains("city") || selectedLoc.contains("fort") )  && (typed.contains("craft") || typed.contains("dragon") || typed.contains("upgrade") || typed.contains("scale")))
      {
         if(CultimaPanel.player.hasScales())
         {
            String scaleType = "dragon";  //prioritize for armor we do not have
            if(!CultimaPanel.player.hasArmor("Dragonqueen-scale") && CultimaPanel.player.hasItemWithName("dragonqueen-scales"))
               scaleType = "dragonqueen";
            else if(!CultimaPanel.player.hasArmor("Dragon-scale") && CultimaPanel.player.hasItemWithName("dragon-scales"))
               scaleType = "dragon";
            else if(!CultimaPanel.player.hasArmor("Seaserpent-scale") && CultimaPanel.player.hasItemWithName("seaserpent-scales"))
               scaleType = "seaserpent";
            else  if(CultimaPanel.player.hasItemWithName("dragonqueen-scales"))
               scaleType = "dragonqueen";
            else if(CultimaPanel.player.hasItemWithName("dragon-scales"))
               scaleType = "dragon";
            else if(CultimaPanel.player.hasItemWithName("seaserpent-scales"))
               scaleType = "seaserpent";
            response = getRandomFrom(craftArmor).replace("SCALETYPE", scaleType);
         }
         else
            response = getRandomFrom(canCraftArmor);
      }
      else if((selectedLoc.contains("city") || selectedLoc.contains("fort") )  && typed.contains("845"))
      {
         if(CultimaPanel.player.hasScales())
         {
            if(CultimaPanel.player.getGold() < 845)
               return getRandomFrom(bribeNoMoney);
            else
            {   
               String scaleType = "dragon";  //prioritize for armor we do not have
               if(!CultimaPanel.player.hasArmor("Dragonqueen-scale") && CultimaPanel.player.hasItemWithName("dragonqueen-scales"))
                  scaleType = "dragonqueen";
               else if(!CultimaPanel.player.hasArmor("Dragon-scale") && CultimaPanel.player.hasItemWithName("dragon-scales"))
                  scaleType = "dragon";
               else if(!CultimaPanel.player.hasArmor("Seaserpent-scale") && CultimaPanel.player.hasItemWithName("seaserpent-scales"))
                  scaleType = "seaserpent";
               else  if(CultimaPanel.player.hasItemWithName("dragonqueen-scales"))
                  scaleType = "dragonqueen";
               else if(CultimaPanel.player.hasItemWithName("dragon-scales"))
                  scaleType = "dragon";
               else if(CultimaPanel.player.hasItemWithName("seaserpent-scales"))
                  scaleType = "seaserpent";
               CultimaPanel.player.pay(845);
               selected.addGold(845);
               response = getRandomFrom(craftArmorDone);
               if(scaleType.equals("dragon"))
               {
                  CultimaPanel.player.addArmor(Armor.getDragonScale());
                  CultimaPanel.player.removeAllItemsWithName("dragon-scales");
               }
               else if(scaleType.equals("dragonqueen"))
               {
                  CultimaPanel.player.addArmor(Armor.getDragonqueenScale());
                  CultimaPanel.player.removeAllItemsWithName("dragonqueen-scales");
               }
               else //if(scaleType.equals("seaserpent"))
               {
                  CultimaPanel.player.addArmor(Armor.getSeaserpentScale());
                  CultimaPanel.player.removeAllItemsWithName("seaserpent-scales");
               }
            }
         }
         else
            response = getRandomFrom(canCraftArmor);
      }
      else if((selectedLoc.contains("castle") || selectedLoc.contains("tower") )  && (typed.contains("craft") || typed.contains("gem") || typed.contains("upgrade")))
         response = getRandomFrom(buyCraftingManual);
      else if((selectedLoc.contains("castle") || selectedLoc.contains("tower") )  && typed.contains("240"))
      {
         if(CultimaPanel.player.getGold() < 240)
            return getRandomFrom(bribeNoMoney);
         else
         {
            CultimaPanel.player.pay(240);
            selected.addGold(240);
            response = getRandomFrom(boughtCraftingManual);   
            CultimaPanel.player.addItem(Item.getCraftingManual().getName());
            selected.removeItem(Item.getCraftingManual().getName());
         }
      }      
      else if(typed.contains("arm") || typed.contains("weapon"))
      {
         String temp = "";
         if(CultimaPanel.player.getArmsInfo() < armsInfo.length)
            temp = armsInfo[CultimaPanel.player.nextArmsInfo()];
         else
            temp = getRandomFrom(armsInfo);
         if(temp.contains("~~"))
         {   
            String [] parts = temp.split("~~"); 
            response = parts[0].trim();
         }
         else
            response = temp;  
      }
      else if(isWeapon(typed))
         response =  getInfoFrom(armsInfo, typed);
      else if(typed.contains("trap"))
      {
         if(CultimaPanel.player.getTrappingInfo() < trappingInfo.length)
            response = trappingInfo[CultimaPanel.player.nextTrappingInfo()];
         else
            response = getRandomFrom(trappingInfo);
      }
      else if(typed.contains("odd"))
         response = getRandomFrom(oddAns);
      else if(typed.contains("buy") || typed.contains("trade") || typed.contains("sell") || typed.contains("gold") || typed.contains("money") || typed.contains("coin") || typed.contains("pay"))
      {
         if(charmed)
            response = getRandomFrom(magicDiscount);
         else
            response = getRandomFrom(armoryBuy);
         CultimaPanel.invIndex = 0;
         CultimaPanel.talkSel = false;
         selected.setTalking(false);
         CultimaPanel.selected = null;
         CultimaPanel.selectedTerrain = null;
         CultimaPanel.typed = "";
         Utilities.setShoppeInventory();
         CultimaPanel.shoppeDiscount = charmed;
         CultimaPanel.menuMode = CultimaPanel.SHOPPE_ARMORY;   
      }
      else if(typed.contains("future"))
         response = getRandomFrom(futureGeneral);
      else if(selected.getCharIndex()==MAN || selected.getCharIndex()==WOMAN)
         return manWomanResponse(selected, typed);
      else     //NPC does not understand
      {
         if(Math.random() < 0.5)
            return getRandomFrom(unknown) + " " + typed + "...";
         else
            response = getRandomFrom(unknown2);
      } 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String mageResponse(NPCPlayer selected, String typed)
   {
      String[] magic = {"I HEAL, I teach SPELLS and TRADE knowledge and potions for gold", "I guide those who seeks HEALING or MAGIC, and TRADE them for coin", "I TRADE MAGIC items and HEALING for gold", "'Tis HEALING and MAGIC that is offered here, TRADED for coin", "Read the sign, did you"};
      String[] magicBuy = {"What shall it be", "Thee can buy magic items here", "Many spells and potions to gaze upon", "Here are my magic wares"};
      String[] magicDiscount = {"For you, a 20% discount", "Any item has a 20% discount for an honoured coustomer like you", "You may have a 20% discount on any item", "'Tis such an honor, a 20% discount I give to you"};
      String[] rainbowDiscount = {"The sky is cast in color! 'Tis a 50% discount on all items", "The magic of the rainbow blesses us! A 50% discount for a limited time", "In the brief moments of the rainbow's display, a 50% discount for all", "Thy rainbow is upon us! A 50% discount for you"};
      String[] magicHeal = {"I see you feel better", "I am glad to see you well again", "Wellness returns to your body", "My potions make thee well again"};
      String[] magicNoHeal = {"T'would be a waste - you are fine.", "You are fit as an oxe.", "Thee seems fit to me.", "You seem robust and fit already", "Beat it or I'll call the Brute Squad"};
      String[] numberResponse = {"I have seen a number in my dreams", "A number I feel in my mind, but cannot say nor write", "'Tis strange - I can feel its weight but not say it", "'Tis the number known by CASTLE mages - I know not its name"};
      String[] caves = {"The hermit wiseman knows of the mysterious green caves", "Seek the hermit wiseman and ask of the green caves", "Knowledge of the moss caves is known by the hermit wiseman", "The strange magic of the moss caves is known to the hermit wiseman"};
      String[] legendary = {"Seek the ironsmith to learn more of legendary weapons", "Weapons of legend are adorned with one of three GEM stones.  The ironsmith knows more", "All legendary ARMS have a hilt wrapped with Powerbands", "With the magic casting GEM and Powerband hilt, the weapons of legend give thy owner great advantage in battle"};
      String[] eggInfo = {"A DRAGON egg is difficult to harvest, lest thee tangle with fire and death", "King GARRIOTT, King of all Cultima, is said to have the Wisdomegg", "Seek King GARRIOTT of the CAPITAL to learn the secrets of the Wisdomegg", "A DRAGON egg will supplement the might of the lucky warrior to consume it", "A Wisdomegg is not from this time, but brought here will give its owner power of the mind"};
      String[] buyEgg = {"Thy coveted Wisdomegg! Take 150 gold coins in trade!", "I have 150 gold to trade for thy Wisdomegg!", "I need that Wisdomegg! Take 150 gold coins to trade!", "Take these 150 gold coins in trade for that Wisdomegg!"};
      String [] soldThanks = {"I thank thee for our trade", "Thank thee. I hope this gold brings thee happiness", "May SKARA Brae shine on thy new riches", "At last, I can grow most wise! I thank thee"};
      String[] rainbowInfo = {"Thy arching colors lift better the magic of the mages", "Thy mage shoppe items may be at a discount when the rainbow colors fall across the sky", "One casting magic under the rainbow will find it takes less manna", "Thy manna will recover quite faster when the colors of the rainbow are cast overhead", "Use thy potent spells when the rainbow is above, and less energy will be spent"};
   
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
   
      if(typed.contains("job"))
         response = getRandomFrom(magic);
      else if(typed.contains("spell") || typed.contains("magic") || typed.contains("scroll"))
      {
         String temp = "";
         if(CultimaPanel.player.getSpellIndex() < spellInfo.length)
            temp = spellInfo[CultimaPanel.player.nextSpellInfo()];
         else
            temp = getRandomFrom(spellInfo);
         if(temp.contains("~~"))
         {
            String [] parts = temp.split("~~");
            response = parts[0].trim();
         }
         else
            response = temp;   
      
      }
      else if(typed.contains("legend") || typed.contains("legendary"))
         response = getRandomFrom(legendary);
      else if(typed.contains("number"))
      {
         response = getRandomFrom(numberResponse);
      }
      else if(typed.contains("cave"))
      {
         response = getRandomFrom(caves);
      }
      else if(typed.contains("egg"))
      {
         if(CultimaPanel.player.hasItem("wisdom-egg"))
         {
            selected.setGold(selected.getGold()+150);
            response = getRandomFrom(buyEgg);
         }
         else
            response = getRandomFrom(eggInfo);
      }
      else if(CultimaPanel.player.hasItem("wisdom-egg") &&  typed.contains("150"))
      {
         selected.setGold(Math.max(0, selected.getGold()-150));
         CultimaPanel.player.addGold(150);
         CultimaPanel.player.removeItem("wisdom-egg");
         selected.addItem(Item.getWisdomEgg());
         response = getRandomFrom(soldThanks);
      }
      else if(typed.contains("potion") || typed.contains("buy")  || typed.contains("trade") || typed.contains("sell") || typed.contains("gold") || typed.contains("money") || typed.contains("coin") || typed.contains("pay"))
      {
         if(CultimaPanel.rainbowAlpha > 0)
            response = getRandomFrom(rainbowDiscount);
         else if(charmed)
            response = getRandomFrom(magicDiscount);
         else
            response = getRandomFrom(magicBuy);
         CultimaPanel.invIndex = 0;
         CultimaPanel.talkSel = false;
         selected.setTalking(false);
         CultimaPanel.selected = null;
         CultimaPanel.selectedTerrain = null;
         CultimaPanel.typed = "";
         Utilities.setShoppeInventory();
         CultimaPanel.shoppeDiscount = charmed;
         CultimaPanel.menuMode = CultimaPanel.SHOPPE_MAGIC;   
      }
      else if (typed.contains("rainbow"))
         response = getRandomFrom(rainbowInfo);
      else if(typed.contains("heal")  || typed.contains("healing") || typed.contains("cure") || typed.contains("poison"))
      {
         int cost = 10;
         if(CultimaPanel.rainbowAlpha > 0)
            cost /= 2;
         if(CultimaPanel.player.getBody() == CultimaPanel.player.getHealthMax())
         {
            if(isSpell(typed))
               response =  getInfoFrom(spellInfo, typed);
            else
               response = getRandomFrom(magicNoHeal);
         }
         else if(CultimaPanel.player.getGold() < cost)
            response = getRandomFrom(bribeNoMoney);
         else 
         {
            response = getRandomFrom(magicHeal);   
            CultimaPanel.player.heal(10);
            CultimaPanel.player.removeEffect("poison");
            CultimaPanel.player.removeEffect("curse");
            CultimaPanel.player.pay(cost);
            selected.addGold(cost);
         }
      }
      else if(isSpell(typed))
         response =  getInfoFrom(spellInfo, typed);
      else if(selected.getCharIndex()==WISE)
         return wiseResponse(selected, typed);
      else if(selected.getCharIndex()==MAN || selected.getCharIndex()==WOMAN)
         return manWomanResponse(selected, typed);
      else     //NPC does not understand
      {
         if(Math.random() < 0.5)
            return getRandomFrom(unknown) + " " + typed + "...";
         else
            response = getRandomFrom(unknown2);
      } 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String shipSales(NPCPlayer selected, String typed)
   {
      String[] noShip = {"I deal not with ships, but some shoppekeeps in the Port-town do","Seek thy shoppekeep of the Port-town for commissioning of sea vessels", "The Port-town shoppekeep deals with the acquisition of ships","'Tis the shoppekeep of the Port-town that manages the ship trade"};
      String[] shipAsk = {"'Twill be 75 gold for a boat, 850 gold for a fine ship", "75 coins will do for a boat, 850 coins for the command of a mighty ship","A ship left by Briggands awaits you for 850 coins, 75 coins for a boat", "'Twill be 850 gold to command a strong Briggand ship, 75 for a boat"};
      String[] shipYes = {"Thank thee. Thy ship awaits you in the harbor, just outside of town", "I thank you. Go to thy harbor just past the town. Thy ship will be there", "A fine trade. Look for your ship in the harbor just outiside of town", "A very good trade. Just beyond the gates of the town, your vessel awaits you in the harbor"};
      String[] cantPlaceShip = {"I am sorry. The ship has been scuttled", "Your coins back: the ship has been purchased already"};
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      boolean isCapital = Utilities.isCapitalCity(selected.getMapIndex());
      if(typed.contains("850") || typed.contains("75"))
      {
         int price = 75;
         byte shipType = BOAT;
         if(typed.contains("850"))
         {
            shipType = BRIGANDSHIP;
            price = 850;
         }
         if(selectedLoc.contains("port") || isCapital)
         {
            if(CultimaPanel.player.getGold() < price)
               return getRandomFrom(bribeNoMoney);
            boolean success = Utilities.placeShipNear(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), shipType);
            if(success)
            {
               response = getRandomFrom(shipYes);
               CultimaPanel.player.pay(price);
               selected.addGold(price);
            }
            else
               response = getRandomFrom(cantPlaceShip);
         }
         else
            response = getRandomFrom(noShip);
      }
      else if(typed.contains("ship") || typed.contains("boat") || typed.contains("water") || typed.contains("vessel"))
      {
         if(selectedLoc.contains("port") || isCapital)
            response = getRandomFrom(shipAsk);
         else
            response = getRandomFrom(noShip);
      }
      return response;
   }

   public static String butcherBakerResponse(NPCPlayer selected, String typed)
   {
      String[] rations = {"We bake the finest RATIONS here for 1 coin", "We sell MUTTON, nice and lean for 1 coin", "I offer RATIONS for the hearty traveler with 1 coin", "I trade BREADS and MEATS for 1 coin", "Read the sign, did you", "We make a nice MLT - MUTTON, lettuce and tomato sandwich"};
      String[] rationsBuy = {"Five days rations for thee...", "'Twill last a hearty traveler five moons", "Here is thy five days rations", "Thy shall enjoy these for five moons", "A good trade...thank thee"};
      String[] rationsFull = {"You can carry no more", "It seems you have all you can carry", "You are already full of rations", "You have no room for more", "Come back when you can carry more"};
      String[] rationsAsk = {"Trade a COIN for hearty food", "'Tis GOLD that we trade for goods", "Will you PAY for hearty food", "'Tis a fair price: 1 COIN"};
      String[] trapAsk = {"You may purchase a trap from me for 70 coins", "Good for trapping game, all for 70 coins", "70 coins will buy thee a trap that will last a lifetime", "My excellent traps will cost thee 70 coins"};
      String[] trapBuy = {"Excellent choice. Remember, don't set them upwind", "'Twill work wonders so long as thee is not upwind of thy game", "Keep side or downwind and 'twil work like magic", "Thank thee for thy buisness"};
      String[] trapNoRoom = {"You do not have the room to carry a trap","You need more room before you can carry a trap","Thee seems to be burdened by too much weight to carry a trap","Lighten thy load before purchasing a trap"};
      String[] futureGeneral = {"I can see thy future! Thou enjoying our delicious RATIONS", "A full belly is in thy future - GOLD for good food", "RATIONS await thee in thy future", "I can see thy future before mine eye! Thou feasing on our RATIONS"};
   
      String response = "";
   //String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(typed.contains("job"))
         response = getRandomFrom(rations);
      else if(typed.contains("850") || typed.contains("75") || typed.contains("ship") || typed.contains("boat") || typed.contains("water") || typed.contains("vessel"))
         response = shipSales(selected, typed);  
      else if(typed.contains("meat") || typed.contains("mutton")  || typed.contains("mlt") || typed.contains("bread") || typed.contains("trade"))
      {  //respond with asking for gold or coin
         response = getRandomFrom(rationsAsk);          
      }
      else if(typed.contains("ration") || typed.contains("gold") || typed.contains("money") || typed.contains("coin") || typed.contains("pay") || typed.contains("buy") || typed.contains("trade")  || typed.contains("1"))
      {  
         if(CultimaPanel.player.getGold() < 1)
            return getRandomFrom(bribeNoMoney);
         else if((!CultimaPanel.player.hasItem("holdall") && CultimaPanel.player.getRations() >= 15) || (CultimaPanel.player.hasItem("holdall") && CultimaPanel.player.getRations() >= 100) || CultimaPanel.player.isVampire())
         {//you can carry no more
            response = getRandomFrom(rationsFull);  
         }
         else
         {
            CultimaPanel.player.pay(1);
            selected.addGold(1);
            CultimaPanel.player.addRations((byte)(5));
            response = getRandomFrom(rationsBuy);       
         }
      }
      else if(typed.contains("trap"))
         response = getRandomFrom(trapAsk);    
      else if(typed.contains("future"))
         response = getRandomFrom(futureGeneral);      
      else if(typed.contains("70"))
      {
         if(CultimaPanel.player.getGold() < 70)
            return getRandomFrom(bribeNoMoney);
         boolean canPlayerCarry = CultimaPanel.player.canAddWeapon(Weapon.getJawTrap());
         if(!canPlayerCarry)
            return getRandomFrom(trapNoRoom);
         CultimaPanel.player.pay(70);
         CultimaPanel.player.addWeapon(Weapon.getJawTrap());
         selected.addGold(70); 
         response = getRandomFrom(trapBuy);
      }
      else if(typed.contains("map"))
      {
         String temp = "";
         if(CultimaPanel.player.getMappingInfo() < mappingInfo.length)
            temp = mappingInfo[CultimaPanel.player.nextMappingInfo()];
         else
            temp = getRandomFrom(mappingInfo);
         response = temp;    
      } 
      else if(selected.getCharIndex()==MAN || selected.getCharIndex()==WOMAN)
         return manWomanResponse(selected, typed);
      else     //NPC does not understand
      {
         if(Math.random() < 0.5)
            return getRandomFrom(unknown) + " " + typed + "...";
         else
            response = getRandomFrom(unknown2);
      } 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String playerWinsSwineGame(NPCPlayer selected)
   {
      String[] win = {"Inconceivable! Take thy fifty gold", "You win thy fifty gold! Inconceivable", "Thy fifty gold is yours! A fair win", "Well rolled. Thee wins thy fifty gold"};
      String[] winDemon = {"Inconceivable! Take thy magic ITEM", "You win thy magic ITEM! Inconceivable", "Thy magic ITEM is yours! A fair win", "Well rolled. Thee wins thy magic ITEM"};
   
      String response = "";
      CultimaPanel.player.clearLocationData();
      CultimaPanel.player.addExperience(100);
      Player.stats[Player.SWINE_GAMES_WON]++;
      if(CultimaPanel.player.getGold() < 50)
         Achievement.earnAchievement(Achievement.DOUBLE_UP);
      if(selected.getCharIndex()==DEMON)
      {
                  //add magic item
         Item prize = Item.getRandomStone();
         if(selected.hasItem("demons-cube") && Math.random()<0.5 && !CultimaPanel.player.hasItem("demons-cube"))
            prize = Item.getDemonsCube();
         response = getRandomFrom(winDemon).replace("ITEM", prize.getName());
         CultimaPanel.player.addItem(prize.getName());
         if(!CultimaPanel.player.hasItem("curse") && CultimaPanel.player.hasMagicStaff())
         {
            CultimaPanel.player.addWeapon(Weapon.getCurse());
            CultimaPanel.sendMessage("You learn the Curse spell!");
         }
         if(Player.inHome(CultimaPanel.player.getMapIndex(), CultimaPanel.player.getRow(), CultimaPanel.player.getCol()))
            Achievement.earnAchievement(Achievement.KNOW_WHEN_TO_HOLD_EM);
      }
      else
      {
         response = getRandomFrom(win);
         CultimaPanel.player.addGold(50);
         if(selected.getGold() > 25)
            selected.setGold(selected.getGold()-25);
         if(selected.hasItem("-swine-bounty"))  
         {
            Item bounty = selected.getItem("-swine-bounty");
            selected.removeBounty();
            CultimaPanel.player.addItem(bounty.getName());
         }
         if(selected.hasItem("loaded-cube"))
            selected.removeItem("loaded-cube"); 
      }
      selected.setIsSwinePlayer(false);  
      CultimaPanel.talkSel = false;
      selected.setTalking(false);
      CultimaPanel.selected = null;
      CultimaPanel.selectedTerrain = null;
      if(selected.getCharIndex()==DEMON)
      {
         if(Math.random() < 0.95) //demon disappears in a flash of fire
         {
            int sRow=selected.getRow(), sCol=selected.getCol(), sMi=selected.getMapIndex();
            String locType = CultimaPanel.player.getLocationType();
            Utilities.removeNPCat(sRow, sCol, sMi);
            byte[][]currMap = CultimaPanel.map.get(sMi);
            Terrain spot = CultimaPanel.allTerrain.get(Math.abs(currMap[sRow][sCol]));
            if(!spot.getName().toLowerCase().contains("purple") && !spot.getName().toLowerCase().contains("bed"))    //don't start a fire if we are in our home
               CultimaPanel.worldMonsters.add(new NPCPlayer(NPC.FIRE, sRow, sCol, sMi, locType));
            CultimaPanel.flashColor = java.awt.Color.red;
            CultimaPanel.flashFrame = CultimaPanel.numFrames;
            CultimaPanel.flashRadius = CultimaPanel.SCREEN_SIZE;
         }
         else
            selected.setMoveType(CHASE);
      }
      return response;
   }
   
   public static String playerLoseSwineGame(NPCPlayer selected)
   {
      String[] lose = {"I win! Thy gold is mine", "The game is mine, as is thy gold", "A good game, I am the victor", "Victory is mine. Try again, will thee"};
      String[] loseDemon = {"We win! Thy life is mine", "The game is ours, as is thy life", "A good game, we are the victor", "Victory is ours, as is thy life"};
      
      String response = "";
      
      CultimaPanel.player.clearLocationData();   
      if(selected.getCharIndex()==DEMON)
      {
         response = getRandomFrom(loseDemon);
               //curse, surround with fire, set demon to attack
         CultimaPanel.player.addEffectForce("curse");
         selected.setMoveType(CHASE);
         selected.modifyStats(1.2);
         Utilities.surroundWithFire();
         CultimaPanel.flashColor = java.awt.Color.red;
         CultimaPanel.flashFrame = CultimaPanel.numFrames;
         CultimaPanel.flashRadius = CultimaPanel.SCREEN_SIZE;
      }
      else
      {
         response = getRandomFrom(lose);
         selected.addGold(25);
      }
      if(Math.random() < 0.5 || selected.getCharIndex()==DEMON)
         selected.setIsSwinePlayer(false);
      else
         selected.setIsSwinePlayer(true);
         
      CultimaPanel.talkSel = false;
      selected.setTalking(false);
      CultimaPanel.selected = null;
      CultimaPanel.selectedTerrain = null;
   
      return response;
   }
   
   public static String swineGame(NPCPlayer selected, String typed)
   {  //CultimaPanel fields: swineNpcSum, swinePlayerSum, swineTurn, swineNpcNumRolls
      String response = "";
      int roll = 0;
      if(CultimaPanel.swineNpcSum >= 50 || CultimaPanel.swinePlayerSum >= 50)
      {
         if(CultimaPanel.swinePlayerSum >= 50)
            response = playerWinsSwineGame(selected);
         else
            response = playerLoseSwineGame(selected);
      }
      else if(CultimaPanel.swineTurn == 1 && (typed.contains("pass") || typed.contains("roll")))   //player rolls, then npc
      {
         if(typed.contains("pass"))
         {
            CultimaPanel.swineTurn = 2;
            CultimaPanel.sendMessage("<Pass turn to "+Utilities.shortName(selected.getName())+">");
            response = swineNpcTurn(selected);
            CultimaPanel.swinePlayerSum += CultimaPanel.swinePlayerTempSum;
            CultimaPanel.swinePlayerTempSum = 0;
         }
         else
         {
            roll = Player.rollDie(6);
            if(CultimaPanel.player.hasItem("demons-cube") && Math.random() < 0.33 && roll == 1)
               roll = Player.rollDie(6);
            else if(CultimaPanel.player.hasItem("loaded-cube") && Math.random() < 0.25 && roll == 1)
               roll = Player.rollDie(6);
            if(roll == 1)
            {
               CultimaPanel.swinePlayerTempSum = 0;
               CultimaPanel.swineTurn = 2;
               CultimaPanel.sendMessage("<Roll 1: Lost turn to "+Utilities.shortName(selected.getName())+">");
               response = swineNpcTurn(selected);
            }
            else
            {
               CultimaPanel.swinePlayerTempSum += roll;
               if(CultimaPanel.swinePlayerSum + CultimaPanel.swinePlayerTempSum >= 50)
                  response = playerWinsSwineGame(selected);
               else
                  response = ("<Roll:"+roll+" sum:"+(CultimaPanel.swinePlayerSum + CultimaPanel.swinePlayerTempSum)+">");
            }
         }
      }
      else if(CultimaPanel.swineTurn == 2)   //npc rolls, then player
      {
         response = swineNpcTurn(selected);
      }
      return response;
   }
   
   public static String swine(NPCPlayer selected, String typed)
   {
      String[] swinePlay = {"Wilt thou play Swine for 25 gold?", "A fair game of Swine can be played for 25 gold", "25 gold and we can play a round of Swine", "Tis only 25 gold for a game of Swine in this tavern"};
      String[] swineGeneral = {"Swine is a game of wagering gold against the roll of a six sided number cube", 
                           "Roll the cube and add the top-side number to thy sum",
                           "Upon rolling a one on its top-side, thy turn is done",
                           "The first player that reaches a sum of fifty is the winner",
                           "Thee can find Swine players at thy tavern, looking to earn some coins from weary travelers",
                           "Tales tell of a Demon willing to speak and play Swine. Lose the game and with it, life"};
      String response = "";
      if(selected.swinePlayer())
      {  
         if(typed.contains("swine"))
            response = getRandomFrom(swinePlay);
         else if(typed.contains("rules"))
            response = getRandomFrom(swineInfo);
         else if(typed.equals("25") && CultimaPanel.swineTurn == 0)
         {
            if(CultimaPanel.player.getGold() < 25)
               return getRandomFrom(bribeNoMoney);
            else
            {
               Player.stats[Player.SWINE_GAMES_PLAYED]++;
               CultimaPanel.player.pay(25);
               selected.addGold(25);
               CultimaPanel.swineTurn = 1;
               if(Math.random() < 0.5)
               {
                  CultimaPanel.swineTurn = 2;
                  CultimaPanel.sendMessage("<"+Utilities.shortName(selected.getName())+" will go first to pass or roll>");
               }
               else
                  CultimaPanel.sendMessage("<"+CultimaPanel.player.getShortName()+" will go first to pass or roll>"); 
               response = swineGame(selected, "roll"); 
            }
         
         }   
         else if(CultimaPanel.swineTurn != 0 && (typed.contains("roll") || typed.contains("pass")))    //game has started
            response = swineGame(selected, typed);
         else if(CultimaPanel.swineTurn == 0)
            response = getRandomFrom(swinePlay);
      }
      else
         response =  getRandomFrom(swineGeneral);
      return response;   
   }
   
   public static String swineNpcTurn(NPCPlayer selected)
   {
      String[] loseTurn = {"Drat! A one! Tis your turn to PASS or ROLL", "That cursed one! It is your go to PASS or ROLL", "Not thy one! The turn is yours to PASS or ROLL", "Plagued by a one! It is thy turn to PASS or ROLL"};
      String[] passTurn = {"I will pass. Tis your turn to PASS or ROLL", "I will pass. It is your go to PASS or ROLL", "I elect to pass. The turn is yours to PASS or ROLL", "I will take a pass. It is thy turn to PASS or ROLL"};
   
      String[] loseTurnDemon = {"A one! Tis thy turn to PASS or ROLL", "That cursed one! It is yours to PASS or ROLL", "Not thy one! Thy turn is yours to PASS or ROLL", "We are plagued by a one! It is thy turn to PASS or ROLL"};
      String[] passTurnDemon = {"We shall pass. Tis your turn to PASS or ROLL", "We will pass. Tis your go to PASS or ROLL", "We elect to pass. Thy turn is yours to PASS or ROLL", "We will take a pass. It is thy turn to PASS or ROLL"};
   
      String response = "";
      boolean done = false;
      CultimaPanel.swineNpcNumRolls = 0;
      String rollMemory = "<Roll:";
      do
      {
         int roll = Player.rollDie(6);
         if(selected.hasItem("demons-cube") && Math.random() < 0.33 && roll == 1)
            roll = Player.rollDie(6);
         else if(selected.hasItem("loaded-cube") && Math.random() < 0.25 && roll == 1)
            roll = Player.rollDie(6);
         rollMemory += roll;
         if(roll == 1)
         {
            CultimaPanel.swineTurn = 1;
            CultimaPanel.sendMessage(rollMemory+"> Sum:"+CultimaPanel.swineNpcSum);
            if(selected.getCharIndex()==DEMON)
               response = getRandomFrom(loseTurnDemon);
            else
               response = getRandomFrom(loseTurn);
            CultimaPanel.swineNpcTempSum = 0;
            done = true;
         }
         else
         {  
            CultimaPanel.swineNpcTempSum += roll;
            if(CultimaPanel.swineNpcSum + CultimaPanel.swineNpcTempSum >= 50)
            {
               response = playerLoseSwineGame(selected);
               done = true;
            }
            else
            {
               CultimaPanel.swineNpcNumRolls++;
               if(Player.rollDie(12) < CultimaPanel.swineNpcNumRolls && CultimaPanel.swineNpcSum < 40)
               {
                  CultimaPanel.swineTurn = 1;
                  if(selected.getCharIndex()==DEMON)
                     response = getRandomFrom(passTurnDemon);
                  else
                     response = getRandomFrom(passTurn);
                  CultimaPanel.swineNpcSum += CultimaPanel.swineNpcTempSum;
                  CultimaPanel.sendMessage(rollMemory+"> Sum:"+CultimaPanel.swineNpcSum);
                  done = true;
               }
               else
                  rollMemory += ","; 
            }
         }
      }while(!done);
      return response;
   }

   public static String demonSwinePlayer(NPCPlayer selected, String typed)
   {
      String[] swinePlay = {"BALK or AGREE to a game of Swine. Win a precious item, lose thy very life", "Lose thy very life or win a magic item. BALK or AGREE to a game of Swine", "A round of Swine for thee to BALK or AGREE. Lose and your life we take, win and magic we give", "Tis only thy life to lose, magic to win. BALK or AGREE to a round of Swine with us"};
      String [] unknownResp = {"Thou speaketh nonsense", "PLAYER speaks without sense", "We know not what you speaketh of", "We do not know this word you speaketh"};
      String response = "";
      if(!selected.swinePlayer())
         return response; 
      if(typed.contains("swine") || typed.contains("rules"))
         response = getRandomFrom(swineInfo);
      else if(typed.equals("agree") && CultimaPanel.swineTurn == 0)
      {
         Player.stats[Player.SWINE_GAMES_PLAYED]++;
         CultimaPanel.player.pay(25);
         selected.addGold(25);
         CultimaPanel.swineTurn = 1;
         if(Math.random() < 0.5)
         {
            CultimaPanel.swineTurn = 2;
            CultimaPanel.sendMessage("<"+Utilities.shortName(selected.getName())+" will go first to pass or roll>");
         }
         else
            CultimaPanel.sendMessage("<"+CultimaPanel.player.getShortName()+" will go first to pass or roll>"); 
         response = swineGame(selected, "roll"); 
      }  
      else if(CultimaPanel.swineTurn != 0 && (typed.contains("roll") || typed.contains("pass")))    //game has started
         response = swineGame(selected, typed);
      else if(CultimaPanel.swineTurn == 0)
         response = getRandomFrom(swinePlay);
      if (response.length()==0)
         return getRandomFrom(unknownResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";    
      return response;
   }
   
   public static void endDemonSwineGame(NPCPlayer selected)
   {
      CultimaPanel.flashColor = java.awt.Color.red;
      CultimaPanel.flashFrame = CultimaPanel.numFrames;
      CultimaPanel.flashRadius = CultimaPanel.SCREEN_SIZE;
      if(CultimaPanel.swineTurn != 0)
      {
               //trying to exit a game of swine with a demon, so curse, surround with fire, set demon to attack
         CultimaPanel.player.addEffectForce("curse");
         selected.setMoveType(NPC.CHASE);
         selected.modifyStats(1.2);
         Utilities.surroundWithFire();
      }
      else  //we haven't started a game of swine - teleport the demon out
      {
         int oldRow = selected.getRow();
         int oldCol = selected.getCol();
         int mi = CultimaPanel.player.getMapIndex();
         Utilities.removeNPCat(oldRow, oldCol, mi);
         byte[][]currMap = CultimaPanel.map.get(mi);
         Terrain spot = CultimaPanel.allTerrain.get(Math.abs(currMap[oldRow][oldCol]));
         if(!spot.getName().toLowerCase().contains("purple") && !spot.getName().toLowerCase().contains("bed"))    //don't start a fire if we are in our home
            CultimaPanel.worldMonsters.add(new NPCPlayer(NPC.FIRE, oldRow, oldCol, selected.getMapIndex(), selected.getLocationType()));
      }
   }
   
   public static void taxEvasion(NPCPlayer target)
   {
      String [] taxEvasion = {"Thou shall not evade thy taxes for long!", "Tax evasion! Guards!", "Thou can run from thy taxes, but thou can not hide for long!", "Tax evader! Thou shall pay for thy city services!"};
      CultimaPanel.sendMessage(getRandomFrom(taxEvasion));
      Utilities.alertGuards();
      CultimaPanel.player.addReputation(-5);
      CultimaPanel.player.setBounty(CultimaPanel.player.getBounty()+1);
   }
   
   public static String taxmanResponse(NPCPlayer selected, String typed)
   {
      String [] taxesPaid = {"Thou has paid for this season. I shall note it in my logs", "Thy city tax is paid this season. Thank thee", "Thy debts are now in order. I shall see thee next season!", "This season's taxes are now paid. My logs shall be noted"};
      String [] taxJob = {"I collect taxes for the King, and to run thy city services", "Tax collecting is a thankless job", "Thy city services are not cheap! Taxes must be collected!", "Thy King and city need thy taxes! My collection of them is of paramount import!", "Of two things are always certain: death, and thyself"};
      
      if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none"))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            return getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
            return Mission.taxmanMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      
      int tax = CultimaPanel.player.getTaxesOwed();
      if(typed.equals(""+tax))
      {
         if(CultimaPanel.player.getGold() < tax)
            return getRandomFrom(bribeNoMoney);
         else
         {
            CultimaPanel.player.pay(tax);
            selected.addGold(tax);
            selected.setEndChaseDay(CultimaPanel.days + 15);
            selected.setMoveType(ROAM);
            selected.setHasBeenAttacked(false);
            Utilities.standDownGuards();
            Utilities.standDownTaxmen();
            return getRandomFrom(taxesPaid);  
         }
      }
      else if(typed.contains("job") || typed.contains("tax"))
         return getRandomFrom(taxJob);
      return generalResponse(selected, typed); 
   }

   public static String manWomanResponse(NPCPlayer selected, String typed)
   {
      String[] citizen = {"I see you marking on a MAP", "I can aid you with thy MAP", "Do you seek help with thy MAP", "I can augment thy MAP"};
      String[] mapGold = {"'Twill take 5 gold to mark thy map", "Wilt thou pay 5 coins for directions?", "5 coins will serve to teach you the surroundings", "My services require 5 gold"};
      String[] mapBought = {"Thy map is complete", "I have marked thy map", "You will see here on your map", "Now gaze upon your map"};
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      if(selected.getCharIndex()==NPC.WOMAN && (selected.getLocationType().contains("castle") || selected.getLocationType().contains("tower")) && (typed.contains("dog") || typed.contains("training")))   
         response = dogTrainer(selected, typed);  
      else if(selected.getCharIndex()==NPC.WOMAN && selected.getLocationType().contains("temple") && typed.contains("wolf"))   
         response = werewolfIntel(selected, typed);
      else if(selected.getCharIndex()==NPC.MAN && (selected.getLocationType().contains("castle") || selected.getLocationType().contains("tower")) && (typed.contains("vamp")))   
         response = vampireIntel(selected, typed);
      else if(selected.swinePlayer())
         response =  swine(selected, typed); 
      else if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               response = getRandomFrom(missionPrice).replace("AMOUNT",typed);
            else  if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            response = getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
            return Mission.cityMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      else if(typed.contains("job"))
         response = getRandomFrom(citizen);    
      else if(typed.contains("map") || typed.contains("directions") || typed.contains("where")|| typed.contains("help"))
      {
         if(selected.getNumInfo()==3 || (charmed && Math.random()<0.5))   //this was a begger whom we restored, and will now map for us for free
         {
            selected.setNumInfo(0);
            if(selected.getCharIndex()==MAN)
            {
               int multiplier = (int)(Math.random()*4) + 2;
               int mapSize = CultimaPanel.SCREEN_SIZE * multiplier;
               TerrainBuilder.markMapArea(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), mapSize);
               response = getRandomFrom(mapBought);  
            }
            else  //if(selected.getCharIndex()==WOMAN
            {
               int numRows = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex()).length);
               int numCols = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex())[0].length);
               TerrainBuilder.markMapArea(CultimaPanel.player.getMapIndex(), 1, 1, numRows-1, numCols-1);
               response = getRandomFrom(mapBought);
            }
         }
         else
            response =getRandomFrom( mapGold);  
      }
      else if(selected.getCharIndex()==MAN && (typed.contains("five") || typed.equals("5")))
      {  //reveal world map of SCREEN_SIZE*2 to SCREEN_SIZE*6 dimensions
         if(CultimaPanel.player.getGold() < 5)
            return getRandomFrom(bribeNoMoney);
         else
         {
            if(selected.getNumInfo() <= 0)
               response = getRandomFrom(allInfoTold);
            else
            {
               CultimaPanel.player.pay(5);
               selected.addGold(5);
               int multiplier = (int)(Math.random()*4) + 2;
               int mapSize = CultimaPanel.SCREEN_SIZE * multiplier;
               TerrainBuilder.markMapArea(CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol(), mapSize);
               response = getRandomFrom(mapBought);  
               selected.setNumInfo(selected.getNumInfo()-1);
            }
         }
      }
      else if(selected.getCharIndex()==WOMAN && (typed.contains("five") || typed.equals("5")))
      {  //reveal city map
         if(CultimaPanel.player.getGold() < 5)
            return getRandomFrom(bribeNoMoney);
         else
         {
            if(selected.getNumInfo() <= 0)
               response = getRandomFrom(allInfoTold);
            else
            {
               CultimaPanel.player.pay(5);
               selected.addGold(5);
               int numRows = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex()).length);
               int numCols = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex())[0].length);
               TerrainBuilder.markMapArea(CultimaPanel.player.getMapIndex(), 1, 1, numRows-1, numCols-1);
               response = getRandomFrom(mapBought);  
               selected.setNumInfo(selected.getNumInfo()-1);
            }
         }
      }
      else 
         return generalResponse(selected, typed); 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String swordResponse(NPCPlayer selected, String typed)
   {
      String[] sword = {"I seek MINES to explore and BEASTS to fell", "'Tis ADVENTURE I seek", "BEAST slaying in LAIRS nearby", "CAVE exporation and brutality", "I am scribing a monster's MANUAL", "I do not mean to pry, but you don't by any chance happen to have six fingers on your right hand"};
      String[] adventure = {"I will show you on your map", "This wicked place is here on your map", "A vile place, here on your map", "It is a dangerous place, here on your map"};
      String[] noAdventure = {"I know not how to get there", "My memory escapes me", "It is not known", "You must seek it out yourself"};
      String[] knowAdventure = {"'Tis already marked on your map", "I see you know of this place", "A dangerous place that is already on your map", "You have been to this place"};
      String[] manualBuy = {"You may purchase my monster's manual for 45 gold", "My monster's manual can be traded for 45 gold", "This monster's manual can be yours for 45 coins", "A mere 45 gold coins and my monster's manual is yours"};
      String[] manualBuyNo = {"It seems as if you know as much of beasts as thee", "Thy journal is more complete than my manual", "My manual can do thee no good. It seems thee knows enough", "Thy journal on monsters seems complete to me."};
      String[] manualBought = {"A fine trade", "May my research aid you", "My hopes that thy writings aid you in combat", "Thee is now armed with knowledge that will aid you in travel and combat"};
    
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
   
      if(selected.getNumInfo()==10 && !typed.contains("mission") && !selected.isSellingHouse())
      {
         if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
         {
            Mission currMission = null;
            for(int i=0; i<CultimaPanel.missionStack.size(); i++)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(selected.getMissionInfo().equals(m.getInfo()))
               {
                  currMission = m;
                  break;
               }
            }
            if(currMission != null && typed.equals(""+currMission.getGoldReward()))
               response = getRandomFrom(missionPrice).replace("AMOUNT",typed);
            else  if(currMission != null)
               return currMission.getMiddleStory();
         }
         else
            response = getRandomFrom(mission);  
      }
      else if(typed.contains("mission"))
      {
         if(selected.isSellingHouse() && selected.getNumInfo()==10)
            return sellingHouse(selected, typed);
         if(selected.getNumInfo()==10)
            return Mission.swordMission(selected);
         else
            return getRandomFrom(noMission);  
      }
      else if(typed.contains("job"))
         response = getRandomFrom(sword);
      else if(typed.contains("beast") || typed.contains("monster"))
      {
         String temp = "";
         if(CultimaPanel.player.getMonsterInfo() < monsters.length)
            temp = monsters[CultimaPanel.player.nextMonsterInfo()];
         else
            temp = getRandomFrom(monsters);
         if(temp.contains("~~"))    //there is a tag for the number of the monster referenced for the journal
         {
            String [] parts = temp.split("~~");
            response = parts[0].trim();
         }
         else
         {
            response = temp;
         }   
      }
      else if(isMonster(typed))
         response =  getInfoFrom(monsters, typed);
      else if(typed.contains("manual"))
      {
         if(CultimaPanel.player.getMonsterInfo() >= monsters.length)
            response = getRandomFrom(manualBuyNo);
         else if(CultimaPanel.player.getGold() < 45)
            return getRandomFrom(bribeNoMoney);
         else
            response = getRandomFrom(manualBuy);   
      }
      else if(typed.equals("45"))
      {
         if(CultimaPanel.player.getMonsterInfo() >= monsters.length)
            response = getRandomFrom(manualBuyNo);
         else if(CultimaPanel.player.getGold() >= 45)
         {
            response = getRandomFrom(manualBought);
            for(int i=0; i<50; i++)
               CultimaPanel.player.nextMonsterInfo();
            CultimaPanel.player.pay(45);
            selected.addGold(45);
         }
         else
         {
            return getRandomFrom(bribeNoMoney);
         }
      }
      else if(typed.contains("adventure") || typed.contains("cave") || typed.contains("mine") || typed.contains("lair"))
      {
         if(selected.getNumInfo() <= 0)
            response = getRandomFrom(allInfoTold);
         else
         {
            Location loc =  TerrainBuilder.closeLocation(CultimaPanel.allAdventure);                            
            int found = -1;
            if(loc!=null)
               found = TerrainBuilder.markMapPath(loc.getName());     
            if(found==0 || CultimaPanel.player.getAwareness()==4)
               response = "'Tis at " + loc.getCol() + " Westerly and " + loc.getRow() + " South";
            else if(found==1)
               response = getRandomFrom(adventure);
            else
               response = getRandomFrom(noAdventure);
            selected.setNumInfo(selected.getNumInfo()-1);   
         }
      }
      else if(typed.contains("dungeon"))
         response = getRandomFrom(dungeon);
      else 
         return generalResponse(selected, typed); 
      if(diff > 100 && CultimaPanel.player.getReputation() >=500)
         response = response + getRandomFrom(greetings2End);
      return response;
   }

   public static String kingResponse(NPCPlayer selected, String typed)
   {
      String[] king = {"I rule the land with a just spirit.", "I am ruler of these parts.", "I command the armies of this land.", "'Tis I that the people of this land answer to."};
      String[] garriottJob = {"'Tis my vision from which these lands take shape!", "I am the creator of the inner workings of these magic lands!", "From my mind, the very nature of these lands have been forged!", "The very lay of the lands are designed from my mind's eye!"};
      String[] garriottMe = {"'Tis myself, King of all Cultima!", "Yes, 'tis Lord British you stand before!", "I am thy King, Richard Garriott", "Indeed, thee is before Lord British!"};
      String[] kingMission = {"I shall send you on a MISSION.", "I have a MISSION for an adventurer like you.", "You will consider a MISSION.", "A MISSION with great rewards awaits you."};
      String[] missionPrice = {"AMOUNT is quite fair for this quest.", "Yes, you heard me right: AMOUNT gold.", "Indeed: AMOUNT gold.  I shall not repeat it.", "AMOUNT gold and my personal thanks will be thy reward."};
      String[] hermitCurseHelp = {"I can PURGE thy terrible curse", "Allow me to PURGE thy curse", "Your heart will be restored if I PURGE thy curse", "Thy curse weighs heavy on thee. I can PURGE thy curse for you"};
      String[] hermitCureCurseYes = {"Your curse has been lifted. Live thy life in peace.", "Thee is human once again. Go back into the world with peace.", "The curse is cast out. Return to the world in peace.", "Your heart can rest as a human again. You may go in peace."};
      String[] hermitPayment = {"Thou must AGREE to relinquish all unnatural possessions", "AGREE you must to relieve yourself of all unnatural possessions", "Your metal possessions: they are unnatural.  Thou must AGREE to dismiss them", "You must AGREE to relieve yourself of all unnatural possessions"};
      String[] luteDestiny = {"Nigel's Lute Of Destiny: 'tis said that songs played on it can charm most two-legged beasts", "Nigel's Lute Of Destiny is able to charm even thy TROLL", "Nigel's sound is soo sweet, a charging cyclops t'would take time to listen", "Nigel's Lute Of Destiny is the most coveted of instruments: can charm even thy giants"};
      String[] number = {"Thy number is fifty!", "Fifty is thy number of counting, and thy number of counting is fifty!", "Thy number shall be known, and it is fifty!", "Thy number is known to me, and now to you.  'Tis fifty!"};
      String[] fifty = {"The CASTLE mage gives thee a clue of three: one East, one South, one level of experience...or perhaps day", "The CASTLE mage's speak a clue of three: East, South and day...or thy level of exerience", "Three numbers given by thy CASTLE Mage are East, South and day...or thy level of experience", "Listen to thy CASTLE mage whose numbers are East, South and level of experience...or day"};
      String[] magicHeal = {"I see you feel better", "I am glad to see you well again", "Wellness returns to your body", "My magic makes thee well again"};
      String[] removeBounty = {"I shall pardon thee from thy bounty","Thy bounty is a burden upon thee. I shall have it cleansed","There is a bounty upon thy very life. I order it to be purged","I order thy bounty to be cleansed. Thy burden has been purged"};
      String[] reputationFix = {"You shall have thy good name back. I will have my agents spread the word","With my command thy name is cleared. Now make thee a life of honor","Thy repuation shall be cleared with my command. Live this new life with honor","Thou has a new start on this day. Thy reputation is cleansed"};
      String[] reputationFine = {"You are living an honorable life","Thy reputation is sound","Thy life is one of good","You are known as honorable in these lands"};
      String[] eggGeneral = {"King GARRIOTT is said to have the Wisdomegg", "The King of all, Lord GARRIOTT, knows all...and likely owns the Wisdomegg", "Seek out King GARRIOTT of the CAPITAL and ask of the Wisdomegg", "He who knows all of Cultima, King GARRIOTT, is said to have the Wisdomegg"};
      String[] wisdomEgg = {"This Wisdomegg is the rarest: consume one and thy knowledge of things is complete", "To hold a Wisdomegg close to thy person is to supplement thy mind and manna", "Very few have had to choose: hold on to thy Wisdomegg, or consume it", "The Wisdomegg is from another realm or time, fermented by the portal in return to these lands", "I have had two Wisdomeggs...one which I consumed, and one that I now hold", "I found this Wisdomegg in the innards of a Dragon-like beast from another realm...quite fierce", "Investigate the green CAVE, and on an odd day, plunge into thy portal within"};
      String[] weatherResponse = {"T'would I predict, a RAIN_CHANCE in 10 chance that it will rain", "The pain in my knee predicts a RAIN_CHANCE in 10 chance that rain will fall", "The skies will open and thy rain will fall in RAIN_CHANCE of 10 spins of the wheel", "All those under the sky will feel water in RAIN_CHANCE of 10 tosses of the coin"};
      String[] rainbowInfo = {"Thy arching colors lift better the magic of the mages", "Thy mage shoppe items may be at a discount when the rainbow colors fall across the sky", "One casting magic under the rainbow will find it takes less manna", "Thy manna will recover quite faster when the colors of the rainbow are cast overhead", "Use thy potent spells when the rainbow is above, and less energy will be spent"};
      String [] deathResponse = {"Skara Brae's Sister, Death, can indeed be sent back to her underworld", "A Royal Weapon forged with the scared Pearl of Life is thy instrument to wound Death", "Skara Brae's Sister is made of Death itself, and may be confused by spells that restore life to thee", "Allow Skara Brae's Sister to embrace thee, and thou will see thy end"};
      String[] futureResponse = {"I can see in thy future a battle with a ADJECTIVE MONSTERTYPE", "In my eye I can see thy future. Thee shall meet in thy plains a ADJECTIVE MONSTERTYPE", "A ADJECTIVE MONSTERTYPE awaits thee in thy near future", "Thy future has a struggle against a ADJECTIVE MONSTERTYPE"};
      String[] futureGeneral = {"Thy future is clouded. I can not see it for thee", "In my eye I can see thy future. Thou shall seek the NUMBER and thy reddened square made of four", "Seeking thy red square awaits thee in thy near future", "Thy future has a quest of greatest import...to seek the NUMBER"};
   
      boolean isGarriott = (selected.getName().contains("Garriott"));//Is this Richard Garriott (Lord British)?
      if(isGarriott)
         Achievement.earnAchievement(Achievement.NAME_OF_THE_GAME);
      String response = "";
      String selectedLoc = selected.getLocationType().toLowerCase();
      int diff = allignmentDifference(selected);
      boolean charmed = false;
      if((CultimaPanel.player.getActiveSpell("Charm")!=null || CultimaPanel.player.hasItem("charmring")) && selected.getReputation() >= (-100 - (CultimaPanel.player.getMind()*10)) )
         charmed = true;
      if(CultimaPanel.player.getReputation() >= 1000)
         charmed = true;
      if(typed.contains("job") || typed.contains("king") || selected.getName().toLowerCase().contains(typed))
      {
         if(selected.getNumInfo() > 0)
            response = getRandomFrom(kingMission)+".";
         else if(isGarriott)
            response = getRandomFrom(garriottJob);
         else
            response = getRandomFrom(king)+".";
      }
      else if (isGarriott && CultimaPanel.player.getBounty() > 1 && typed.contains("bounty"))
      {
         response = getRandomFrom(removeBounty)+".";
         CultimaPanel.player.setBounty(1);
      }
      else if (isGarriott && typed.contains("reputation"))
      {
         if(CultimaPanel.player.getReputation() < 0)
         {
            response = getRandomFrom(reputationFix)+".";
            CultimaPanel.player.addReputation(Math.abs(CultimaPanel.player.getReputation()));
         }
         else
         {
            response = getRandomFrom(reputationFine)+".";
         }
      }
      else if (isGarriott && (typed.contains("garriot") || typed.contains("gariot") || typed.contains("richard") || (typed.contains("lord") && typed.contains("british")) || typed.contains("british")))
         response = getRandomFrom(garriottMe);
      else if (isGarriott && (typed.contains("number")))
         response = getRandomFrom(number);
      else if (isGarriott && (typed.contains("death")))
         response = getRandomFrom(deathResponse);
      else if (isGarriott && (typed.contains("fifty") || typed.contains("50")))
         response = getRandomFrom(fifty);
      else if (isGarriott && (typed.contains("rainbow")))
         response = getRandomFrom(rainbowInfo);
      else if(isGarriott && (typed.contains("craft") || typed.contains("gem") || typed.contains("upgrade")))
         response =  getRandomFrom(gemInfo);
      else if (isGarriott && (typed.contains("cure") || typed.contains("heal")  || typed.contains("healing") || typed.contains("help")) && 
               (CultimaPanel.player.getBody() < CultimaPanel.player.getHealthMax()) || (CultimaPanel.player.hasEffect()))
      {
         CultimaPanel.player.removeEffects();
         CultimaPanel.player.setBody(CultimaPanel.player.getHealthMax());
         response = "Sanctu Mani! " + getRandomFrom(magicHeal);
         Sound.castSpell();
         CultimaPanel.flashColor = new java.awt.Color(255,10,255);
         CultimaPanel.flashFrame = CultimaPanel.numFrames;
         CultimaPanel.flashRadius = CultimaPanel.SCREEN_SIZE / 4;
         int radius = Math.min(((CultimaPanel.SCREEN_SIZE/2)*CultimaPanel.SIZE),CultimaPanel.flashRadius*CultimaPanel.SIZE);
         int opacity = Math.max(75, Math.min(127, 500));
         int plX = ((CultimaPanel.SCREEN_SIZE/2)*CultimaPanel.SIZE) + (CultimaPanel.SIZE/2)  - (radius/2);
         int plY = ((CultimaPanel.SCREEN_SIZE/2)*CultimaPanel.SIZE) + (CultimaPanel.SIZE/2) - (radius/2);   
         CultimaPanel.smoke.add(new SmokePuff(plX, plY, radius, opacity, CultimaPanel.flashColor));
      }      
      else if(selected.getMissionInfo()!=null && !selected.getMissionInfo().equals("none") && FileManager.wordIsInt(typed))
      {
         Mission currMission = null;
         for(int i=0; i<CultimaPanel.missionStack.size(); i++)
         {
            Mission m = CultimaPanel.missionStack.get(i);
            if(selected.getMissionInfo().equals(m.getInfo()))
            {
               currMission = m;
               break;
            }
         }
         if(currMission != null && typed.equals(""+currMission.getGoldReward()))
            response = getRandomFrom(missionPrice).replace("AMOUNT",typed);
         else if(currMission != null)
            return currMission.getMiddleStory();
      }
      else if(typed.contains("mission"))
      {
         CultimaPanel.shoppeDiscount = charmed;
         response = Mission.kingMission(selected);
      }
      else if(isGarriott && (typed.contains("horse") || typed.contains("tame") || typed.contains("unicorn")))
      {
         if(CultimaPanel.player.getHorseInfo() < horseInfo.length)
            response = horseInfo[CultimaPanel.player.nextHorseInfo()];
         else
            response = getRandomFrom(horseInfo);
      }
      else if(isGarriott && (typed.contains("beast") || typed.contains("monster")))
      {
         String temp = "";
         if(CultimaPanel.player.getMonsterInfo() < monsters.length)
         {
            temp = monsters[CultimaPanel.player.nextMonsterInfo()];
            int numTries = 0;
            while(!temp.contains("~~") && CultimaPanel.player.getMonsterInfo() < monsters.length && numTries<1000)
            {
               temp = monsters[CultimaPanel.player.nextMonsterInfo()];
               numTries++;
            }
         }
         else
         {
            int numTries = 0;
            while(!temp.contains("~~") && numTries<1000)
            {
               temp = getRandomFrom(monsters);
               numTries++;
            }
         }
         if(temp.contains("~~"))    //there is a tag for the number of the monster referenced for the journal
         {
            String [] parts = temp.split("~~");
            response = parts[0].trim();
         }
         else
         {
            response = temp;
         }   
      }
      else if(isGarriott && isMonster(typed))
         response =  getInfoFrom(monsters, typed);
      else if(isGarriott && (typed.contains("spell") || typed.contains("magic") || typed.contains("scroll")))
      {
         String temp = "";
         if(CultimaPanel.player.getSpellIndex() < spellInfo.length)
         {
            int numTries = 0;
            while(!temp.contains("~~") && CultimaPanel.player.getSpellIndex() < spellInfo.length && numTries<1000)
            {
               temp = spellInfo[CultimaPanel.player.nextSpellInfo()];
               numTries++;
            }
         }
         else
         {
            int numTries = 0;
            while(!temp.contains("~~") && numTries<1000)
            {
               temp = getRandomFrom(spellInfo);
               numTries++;
            }
         }
         if(temp.contains("~~"))
         {
            String [] parts = temp.split("~~");
            response = parts[0].trim();
         }
         else
            response = temp;   
      
      }
      else if(isGarriott && isSpell(typed))
         response =  getInfoFrom(spellInfo, typed);
      else if(isGarriott && (typed.contains("legend") || typed.contains("legendary")))
         response = getRandomFrom(legendaryWeaponInfo);   
      else if(isGarriott && (typed.contains("arm") || typed.contains("weapon")))
      {
         String temp = "";
         if(CultimaPanel.player.getArmsInfo() < armsInfo.length)
         {
            int numTries = 0;
            while(!temp.contains("~~") && CultimaPanel.player.getArmsInfo() < armsInfo.length && numTries<1000)
            {
               temp = armsInfo[CultimaPanel.player.nextArmsInfo()];
               numTries++;
            }
         }
         else
         {
            int numTries = 0;
            while(!temp.contains("~~") && numTries<1000)
            {
               temp = getRandomFrom(armsInfo);
               numTries++;
            }
         }
         if(temp.contains("~~"))
         {   
            String [] parts = temp.split("~~"); 
            response = parts[0].trim();
         }
         else
            response = temp;  
      }
      else if(isGarriott && isWeapon(typed))
         response =  getInfoFrom(armsInfo, typed);
      else if(isGarriott && typed.contains("trap"))
      {
         if(CultimaPanel.player.getTrappingInfo() < trappingInfo.length)
            response = trappingInfo[CultimaPanel.player.nextTrappingInfo()];
         else
            response = getRandomFrom(trappingInfo);
      }
      else if(isGarriott && (typed.contains("cave") || typed.contains("underworld")))
         response = getRandomFrom(caveInfo);
      else if(isGarriott && (CultimaPanel.player.isVampire() || CultimaPanel.player.isWerewolf()) && (typed.contains("vamp") || typed.contains("bat") || typed.contains("blood") || typed.contains("curse") || typed.contains("wolf")))
      {
         response =  getRandomFrom(hermitCurseHelp);
      }
      else if(isGarriott && (CultimaPanel.player.isVampire() || CultimaPanel.player.isWerewolf()) && typed.contains("purge"))
      {
         response =  getRandomFrom(hermitPayment);
      }
      else if(isGarriott && CultimaPanel.player.isVampire() && typed.contains("agree"))
      {
         CultimaPanel.player.setGold(0);
         CultimaPanel.player.clearItemsExceptBounties();
         CultimaPanel.player.clearWeapons();
         CultimaPanel.player.clearArmor();
         Utilities.removeVampireCurse();
         response =  getRandomFrom(hermitCureCurseYes);
      }
      else if(isGarriott && CultimaPanel.player.isWerewolf() && typed.contains("agree"))
      {
         CultimaPanel.player.setGold(0);
         CultimaPanel.player.clearItemsExceptBounties();
         CultimaPanel.player.clearWeapons();
         CultimaPanel.player.clearArmor();
         Utilities.removeWerewolfCurse();
         response =  getRandomFrom(hermitCureCurseYes);
      }
      else if(isGarriott && (typed.contains("ship") || typed.contains("boat") || typed.contains("sail") || typed.contains("pirate")))
      {
         if(CultimaPanel.player.getShipInfo() < shipInfo.length)
            response = shipInfo[CultimaPanel.player.nextShipInfo()];
         else
            response = getRandomFrom(shipInfo);
      } 
      else if(isGarriott && typed.contains("destiny"))
         response = getRandomFrom(luteDestiny);
      else if(isGarriott && (typed.contains("well") && typed.contains("puzzle")))
      {
         response = getRandomFrom(wellsPuzzle);
      }
      else if(isGarriott && (typed.contains("door") && typed.contains("puzzle")))
      {
         response = getRandomFrom(doorsPuzzle);
      }
      else if(isGarriott && (typed.contains("tower") && typed.contains("puzzle")))
      {
         response = getRandomFrom(towersPuzzle);
      }
      else if(isGarriott && typed.contains("puzzle"))
      {
         response = getRandomFrom(puzzle);
      }
      else if(isGarriott && typed.contains("dungeon"))
         response = getRandomFrom(dungeon);
      else if(isGarriott && typed.contains("dog"))
      {
         if(CultimaPanel.player.getDogInfo() < dogInfo.length)
            response = dogInfo[CultimaPanel.player.nextDogInfo()];
         else
            response = getRandomFrom(dogInfo); 
      }   
      else if(isGarriott && typed.contains("vamp"))
      {
         if(CultimaPanel.player.getVampireInfo() < vampireInfo.length)
            response = vampireInfo[CultimaPanel.player.nextVampireInfo()];
         else
            response = getRandomFrom(vampireInfo); 
      }
      else if(isGarriott && typed.contains("wolf"))
      {
         if(CultimaPanel.player.getWerewolfInfo() < werewolfInfo.length)
            response = werewolfInfo[CultimaPanel.player.nextWerewolfInfo()];
         else
            response = getRandomFrom(werewolfInfo);
      }
      else if(isGarriott && (typed.contains("weather") || typed.equals("rain") || typed.contains("storm") || typed.contains("sun") || typed.contains("sky")))
      {
         int rainChance = (int)(CultimaPanel.rain_probability * 20);
         if(CultimaPanel.weather > 0)
            rainChance = 10;
         String tempResp = getRandomFrom(weatherResponse).replace("RAIN_CHANCE", ""+rainChance);
         response = tempResp;
      }
      else if(isGarriott && (typed.contains("future")))
      {
         if(selectedLoc.contains("dungeon") || selectedLoc.contains("cave") || selectedLoc.contains("mine") || selectedLoc.contains("lair"))
         {
            int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, CultimaPanel.player.getRow(), CultimaPanel.player.getCol(), CultimaPanel.player.getMapIndex());
            Location currentLoc = null;
            if(currentLocIndex >= 1)
            {
               currentLoc = CultimaPanel.allLocations.get(currentLocIndex);   
               ArrayList<Point> monsterFreq = currentLoc.getMonsterFreq();
               if(monsterFreq.size() > 0)
                  CultimaPanel.nextMonsterSpawn = (byte)(monsterFreq.get(0).getX());
               else
                  response = getRandomFrom(futureGeneral);
            }
         }
         else if(CultimaPanel.nextMonsterSpawn == -1)
         {
            CultimaPanel.nextMonsterSpawn = NPC.randomWorldMonsterSunny();
            if(CultimaPanel.weather > 0)
               CultimaPanel.nextMonsterSpawn = NPC.randomWorldMonsterRaining();                              
            if(Player.rollDie(125 - ((CultimaPanel.player.getLevel()%10)*10)) <= 1)    //chance we will spawn a monster king
            {
               CultimaPanel.nextMonsterSpawn = NPC.randomMonsterKing();
               if(CultimaPanel.player.hasDeathMission() && Math.random() < 0.5)
                  CultimaPanel.nextMonsterSpawn = NPC.DEATH;
            } 
         }
         response = getRandomFrom(futureResponse).replace("ADJECTIVE", getRandomFrom(monsterAdjective)).replace("MONSTERTYPE",characterDescription(CultimaPanel.nextMonsterSpawn));
      }
      else
         if(typed.contains("egg") || typed.contains("wisdom"))
         {
            if(isGarriott)
               response = getRandomFrom(wisdomEgg);
            else
               response = getRandomFrom(eggGeneral)+".";
         } 
         else
            return generalResponse(selected, typed);       
      return response;
   }

   public static String dogTrainer(NPCPlayer selected, String typed)
   {
      String response = "";
      if(CultimaPanel.player.getDogInfo() < dogInfo.length)
         response = dogInfo[CultimaPanel.player.nextDogInfo()];
      else
         response = getRandomFrom(dogInfo);      
      return response;     
   }

   public static String vampireIntel(NPCPlayer selected, String typed)
   {
      String response = "";
      if(CultimaPanel.player.getVampireInfo() < vampireInfo.length)
         response = vampireInfo[CultimaPanel.player.nextVampireInfo()];
      else
         response = getRandomFrom(vampireInfo);      
      return response;     
   }
   
   public static String werewolfIntel(NPCPlayer selected, String typed)
   {
      String response = "";
      if(CultimaPanel.player.getWerewolfInfo() < werewolfInfo.length)
         response = werewolfInfo[CultimaPanel.player.nextWerewolfInfo()];
      else
         response = getRandomFrom(werewolfInfo);      
      return response;     
   }

   public static String dogResponse(NPCPlayer selected, String typed)
   {
      String [] notAligned = {"Grrrrrrr...Bark!", "Woof!...Grrrrrrr!", "Grrrrrrrrrr..."};
      String [] generalDog = {"Bark!", "Woof!", "Bark! Bark!", "Woof! Woof!", "Bark! Bark! Bark!", "Woof! Woof! Woof!"};
      String response = "";
      if((!selected.hasMet() && !allignmentCompatable(selected)) || (!CultimaPanel.player.getDogName().equals("none") && !CultimaPanel.player.getDogName().equals(selected.getName())) || CultimaPanel.player.isVampire())
         return getRandomFrom(notAligned);
      if(typed.contains("food") || typed.contains("treat") || typed.contains("snack") || typed.contains("ration") || typed.contains("kibble") || typed.contains("meat"))
      {
         if(CultimaPanel.player.getRations() >= 1)
         {
            response = "<chomp...lick...>";
            CultimaPanel.player.useRation();
            selected.setHasMet(true);
            CultimaPanel.talkSel = false;
            selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
            CultimaPanel.typed = "";
            CultimaPanel.sendMessage("You give "+selected.getName()+" some food.");
            selected.setMoveTypeTemp(NPC.CHASE);
         }
      }
      else if(typed.contains("stay"))
      {
         selected.setMoveTypeTemp(NPC.STILL);
         response = "<whine...sniff...>";
      }
      else if(typed.contains("go"))
      {
         selected.setMoveTypeTemp(NPC.ROAM);
         selected.setHasMet(false);
         CultimaPanel.player.setDogBasicInfo("none");
         response = "<whine...whine...>";
      }
      else if(typed.contains("bad"))
      {
         response = "<whine...whine...>";
      }
      else if(typed.contains("come"))
      {
         selected.setMoveTypeTemp(NPC.CHASE);
         if(CultimaPanel.player.getDogName().equals("none"))
            CultimaPanel.player.stats[Player.DOGS_TRAINED]++;
         CultimaPanel.player.setDogBasicInfo(selected.basicInfo());
         response = "<pant...pant...>";
      }
      else if(typed.contains("attack"))
      {
         selected.setMoveTypeTemp(NPC.ATTACK);
         response = "Woof! Woof!";
         CultimaPanel.talkSel = false;
         selected.setTalking(false);
         CultimaPanel.selected = null;
         CultimaPanel.selectedTerrain = null;
      }
      else
         response = getRandomFrom(generalDog);
      return response;
   }
   
   public static String wellsPuzzleResponse(NPCPlayer selected, String typed)
   {
      String [] greetResp = {"Greetings PLAYER - we have been watching you!", "Greetings to the one called PLAYER!", "We greet the one known as PLAYER!", "PLAYER is given greetings by us!"};
      String [] nameAsk = {"You speak to the mighty NAME!", "You are PLAYER.  We are NAME!", "We are NAME!", "Thou has found NAME!", "PLAYER - you stand before NAME!", "We are the ones called NAME!", "PLAYER stands before NAME!", "PLAYER kneels before NAME!"};
      String [] unknownResp = {"Thou speaketh nonsense - we are here to present a PUZZLE", "PLAYER speaks without sense - we speak in PUZZLES", "We know not what you speaketh of - we are here for a PUZZLE", "We do not know this word you speaketh. Listen to our PUZZLE"};
      String [] jobAsk = {"We present PLAYER with a grand PUZZLE!", "PLAYER is worthy - we present a PUZZLE!", "A PUZZLE is presented to PLAYER!", "Wilst PLAYER take the challenge of the PUZZLE?", "This the challenge of the PUZZLE that awaits thee, PLAYER!"};
      String [] puzzleAsk = {"Read thy BOOK, and return with a full goblet ready to SWAP!", "Thy BOOK awaits, as do we when thee is ready to SWAP a full goblet!", "Thy BOOK tells our puzzle, and we wait to SWAP a full goblet!", "See the BOOK that speak our puzzle - return with a filled goblet to SWAP!"};
      String [] bookAsk = {"Thy book beneath the wells tell our puzzle! Read the RULES", "See the book beneath the wells - it details the RULES of our puzzle!", "Copy our book into thy journal - study the puzzle RULES within, PLAYER!", "Our book will speak the RULES of our puzzle into thy journal, PLAYER!"};
      String [] cupNotFilled = {"Thy goblet is empty! Choose from the wells and return when full!", "The wells await for PLAYER to fill thy goblet!", "PLAYER - fill thy goblet from thy wells before returning!", "PLAYER has an empty goblet! Fill from a well to complete the puzzle!"};
      String [] swapAskLose = {"PLAYER, you chose wrong! Meet thy fate!", "PLAYER chose poorly! Your fate is with us in the underworld!", "A poor choice! Thy fate is with us, PLAYER!", "PLAYER has failed the riddle! Thy fate is ours!"};
      String [] swapAskWin = {"Wait! What? Yeaaaargh!", "Wait! How did thee...Eeeeargh!", "'Tis a trick! Thee shall...Yeeeeaaargh!", "Trickery! Monster!  Eeeeeargh!"};
      String [] rules = {"An island rises from the water, and thee stands before us, the Puzzle Giver",
                           "Before thee are three Wells, each a different poison. Any who drinks from one and only one shall perish",
                           "Drink from Well-One, and thy poison from Well-Two or Well-Three will cure thee",
                           "Drink from Well-Two, and thy poison from Well-Three is thy cure. The poison of Well-Three has no cure",
                           "Only we can reach the waters from Well-Three, and offers the grand challenge",
                           "Each will draw water into a Goblet, meet at the center, swap Goblets and drink",
                           "We drink from your Goblet, you drink from our Goblet"
                           };
   
      if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
         return getRandomFrom(greetResp).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("name"))
         return getRandomFrom(nameAsk).replace("NAME",selected.getName()).replace("PLAYER",CultimaPanel.player.getShortName());  
      if(typed.contains("job"))
         return getRandomFrom(jobAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("riddle") || typed.contains("puzzle"))
         return getRandomFrom(puzzleAsk); 
      if(typed.contains("book"))
         return getRandomFrom(bookAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("rules") || typed.contains("well") || typed.contains("poison"))
         return getRandomFrom(rules);
      if(typed.contains("swap"))
      {
         if(CultimaPanel.player.getWellNumber()==-1)
            return getRandomFrom(cupNotFilled).replace("PLAYER",CultimaPanel.player.getShortName());   
         if(CultimaPanel.player.getWellNumber()==0)
         {  //kill the demon
            if( CultimaPanel.player.hasEffect("poison"))    //we completed the puzzle correctly
            {  //give the demon a legendary weapon
               CultimaPanel.player.removeEffect("poison");
               selected.setWeapon(Weapon.getRandomLegendaryWeapon());
               CultimaPanel.player.specificExperience[Player.MIND]++;
               CultimaPanel.player.addExperience(100);
               Sound.levelUp(60);
               CultimaPanel.player.stats[Player.PUZZLES_SOLVED]++;
               if(CultimaPanel.player.stats[Player.PUZZLES_SOLVED] >= 3 && CultimaPanel.player.readAllPuzzleBooks())
                  Achievement.earnAchievement(Achievement.PUZZLE_SLAYER);
            }
            else                                            //we partially completed the puzzle
            {
               CultimaPanel.player.addEffectForce("poison");
               CultimaPanel.player.damage((int)(CultimaPanel.player.getBody()*(Player.rollDie(75,95)/100.0))); //do 80-95% damage to player
            }
            selected.damage(selected.getBody(),"");
            selected.setHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
            CultimaPanel.player.addExperience(selected.getLevel());
            Corpse dead = selected.getCorpse();
            dead.setHitTime(CultimaPanel.numFrames + (CultimaPanel.animDelay*3/2));
            CultimaPanel.corpses.get(selected.getMapIndex()).add(dead);
            Utilities.removeNPCat(selected.getRow(), selected.getCol(), selected.getMapIndex());
            CultimaPanel.player.setWellNumber((byte)(-1));
            return getRandomFrom(swapAskWin).replace("PLAYER",CultimaPanel.player.getShortName()); 
         }
         CultimaPanel.player.addEffectForce("poison");
         CultimaPanel.player.damage((int)(CultimaPanel.player.getBody()*(Player.rollDie(80,95)/100.0))); //do 80-95% damage to player
         selected.setMoveType(CHASE);
         CultimaPanel.player.setWellNumber((byte)(-1));
         Sound.thunder();
         return getRandomFrom(swapAskLose).replace("PLAYER",CultimaPanel.player.getShortName());     
      }
      return getRandomFrom(unknownResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
   }

   public static String doorsPuzzleResponse(NPCPlayer selected, String typed)
   {
      String [] noPuzzle = {"Have at thee!", "No Puzzle this day - meet thy doom!", "Meet thy end!", "Infidel!"};
      String [] greetResp = {"Greetings PLAYER - I have been waiting for you", "Greetings to the one called PLAYER", "I greet the one known as PLAYER", "PLAYER has arrived to my inviting PUZZLE"};
      String [] nameAsk = {"You speak to the simple NAME", "I am known as the NAME", "I am the NAME", "Thou has found the NAME", "PLAYER - you stand before the NAME", "I am but a humble NAME", "PLAYER stands before the NAME"};
      String [] unknownResp = {"Thou speaketh nonsense - we are here to present a PUZZLE", "PLAYER speaks without sense - I speak in PUZZLES", "I know not what you speaketh of - you are here for a PUZZLE", "I do not know this word you speaketh. Listen to my PUZZLE"};
      String [] jobAsk = {"I present you, PLAYER, with a simple PUZZLE", "PLAYER, you are worthy of a modest PUZZLE", "A PUZZLE is presented to you, worthy PLAYER", "Wilst thou, worthy PLAYER, take the challenge of the PUZZLE?", "This, the challenge of the PUZZLE that awaits thee, PLAYER"};
      String [] puzzleAsk = {"Read thy BOOK, and return when ready to PICK a Door", "Thy BOOK awaits, as do I when thee is ready to PICK a Door", "Thy BOOK tells my modest Puzzle, and I wait for you to PICK a Door", "See the BOOK that speaks my Puzzle - return when ready to PICK a Door"};
      String [] bookAsk = {"Thy book beneath the Doors tells of my Puzzle. Read the RULES", "See the book beneath the Doors - it details the RULES of my simple Puzzle", "Copy my book into thy journal - study the Puzzle RULES within, PLAYER", "My book will speak the RULES of the Puzzle into thy journal, PLAYER"};
      String [] pickAsk = {"Pick thy Door to open: ONE, TWO or THREE", "Will thy open Door ONE, Door TWO or Door THREE", "Pick wisely: Door ONE, TWO or THREE", "Thy Puzzle starts with the picking of a Door: ONE, TWO or THREE"};
      String [] hint = {"There waits a beast behind Door-NUMBER1. Does thou want to KEEP thy pick or CHANGE to Door-NUMBER2?", "A beast waits behind Door-NUMBER1. Wilt thou KEEP thy pick or CHANGE to open Door-NUMBER2?", "Door-NUMBER1 hides a fierce beast. You may CHANGE to Door-NUMBER2 or KEEP thy pick", "Door-NUMBER1 has a beast behind it. Wilt thou CHANGE to Door-NUMBER2, or KEEP thy pick?"};
      String [] notChosen = {"Thou must PICK a door number with haste!", "A Door number must be PICKED!", "Thou needeth to PICK a Door number!", "For this Puzzle, thee needs to PICK a Door number!"};
      String [] deal = {"Let's Make a Deal: thee stops this nonsense and I will grant thee a PUZZLE!", "Monty Hall means not a thing to thee. On with the PUZZLE!", "These Doors do not hide goats, I assure you. On with the PUZZLE!", "Thy Zonk prize awaits. And now the PUZZLE!"};
      String [] rules = {  "Before thee stands Three Doors: Door-One, Door-Two, Door-Three",
                           "Behind two Doors are fierce beasts, behind one Door a treasure",
                           "You will PICK one Door to open, then I will reveal which among the other houses a beast",
                           "Thee must decide if 'tis better to keep thy original choice, or select another"
                           };
      String [] puzzleLose = {"'Twas an unwise choice!", "You did not choose wisely!", "An unfortunate selection!", "Meet thy doom!", "Meet thy destiny!", "See thy fate!"};
      String [] puzzleWin = {"A wise choice", "A wise selection", "A fortunate choice", "Thou has chosen wisely", "Thou has selected wisely"};                     
      Terrain floor = TerrainBuilder.getTerrainWithName("INR_$Black_floor");    
      int mi = CultimaPanel.player.getMapIndex();  
      byte[][]currMap = CultimaPanel.map.get(mi);
      boolean doorMonster1 = false;
      boolean doorMonster2 = false;
      boolean doorMonster3 = false;
      if(CultimaPanel.door1 != null && CultimaPanel.door2 != null && CultimaPanel.door3 != null)
      {
         NPCPlayer monster1 = Utilities.getNPCAt((int)(CultimaPanel.door1.getX()-1), (int)(CultimaPanel.door1.getY()), mi);                  
         doorMonster1 = (monster1!=null && monster1.getCharIndex()==DRAGON);
         NPCPlayer monster2 = Utilities.getNPCAt((int)(CultimaPanel.door2.getX()-1), (int)(CultimaPanel.door2.getY()), mi);                  
         doorMonster2 = (monster2!=null && monster2.getCharIndex()==DRAGON);
         NPCPlayer monster3 = Utilities.getNPCAt((int)(CultimaPanel.door3.getX()-1), (int)(CultimaPanel.door3.getY()), mi);                  
         doorMonster3 = (monster3!=null && monster3.getCharIndex()==DRAGON);
      }
      //make sure there are two and only two monsters to play the 3 doors puzzle
      boolean twoMonsters= ((doorMonster1 && doorMonster2 && !doorMonster3) ||
                           (doorMonster1 && !doorMonster2 && doorMonster3) ||
                           (!doorMonster1 && doorMonster2 && doorMonster3));
      if(!twoMonsters)
      {
         selected.setMoveType(CHASE);
         CultimaPanel.talkSel = false;
         selected.setTalking(false);
         CultimaPanel.selected = null;
         CultimaPanel.selectedTerrain = null;
         CultimaPanel.typed = "";
         return getRandomFrom(noPuzzle);
      }
      if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
         return getRandomFrom(greetResp).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("name"))
      {
         String selName = selected.getName(); 
         return getRandomFrom(nameAsk).replace("NAME",selName).replace("PLAYER",CultimaPanel.player.getShortName());  
      }
      if(typed.contains("job"))
         return getRandomFrom(jobAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("riddle") || typed.contains("puzzle"))
         return getRandomFrom(puzzleAsk); 
      if(typed.contains("book"))
         return getRandomFrom(bookAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("rules") || typed.contains("door"))
         return getRandomFrom(rules);
      if(typed.contains("pick"))
         return getRandomFrom(pickAsk);
      if(typed.contains("one") || typed.contains("1"))
      {
         String doorNum1="", doorNum2="";
         CultimaPanel.player.setDoorNumber((byte)(1));
         if(doorMonster2 && doorMonster3)
         {
            if(Math.random() < 0.5)
            {
               doorNum1 = "Two";
               doorNum2 = "Three";
               CultimaPanel.player.setDoorChange((byte)(3));
            }
            else
            {
               doorNum1 = "Three";
               doorNum2 = "Two";
               CultimaPanel.player.setDoorChange((byte)(2));
            }
         }
         else if(doorMonster2)
         {
            doorNum1 = "Two";
            doorNum2 = "Three";
            CultimaPanel.player.setDoorChange((byte)(3));
         }
         else //if(doorMonster3)
         {
            doorNum1 = "Three";
            doorNum2 = "Two";
            CultimaPanel.player.setDoorChange((byte)(2));
         }
         return getRandomFrom(hint).replace("NUMBER1",doorNum1).replace("NUMBER2",doorNum2);
      }
      else if(typed.contains("two") || typed.contains("2"))
      {
         String doorNum1="", doorNum2="";
         CultimaPanel.player.setDoorNumber((byte)(2));
         if(doorMonster1 && doorMonster3)
         {
            if(Math.random() < 0.5)
            {
               doorNum1 = "One";
               doorNum2 = "Three";
               CultimaPanel.player.setDoorChange((byte)(3));
            }
            else
            {
               doorNum1 = "Three";
               doorNum2 = "One";
               CultimaPanel.player.setDoorChange((byte)(1));
            }
         }
         else if(doorMonster1)
         {
            doorNum1 = "One";
            doorNum2 = "Three";
            CultimaPanel.player.setDoorChange((byte)(3));
         }
         else //if(doorMonster3)
         {
            doorNum1 = "Three";
            doorNum2 = "One";
            CultimaPanel.player.setDoorChange((byte)(1));
         }
         return getRandomFrom(hint).replace("NUMBER1",doorNum1).replace("NUMBER2",doorNum2);
      }
      if(typed.contains("three") || typed.contains("3"))
      {
         String doorNum1="", doorNum2="";
         CultimaPanel.player.setDoorNumber((byte)(3));
         if(doorMonster1 && doorMonster2)
         {
            if(Math.random() < 0.5)
            {
               doorNum1 = "Two";
               doorNum2 = "One";
               CultimaPanel.player.setDoorChange((byte)(1));
            }
            else
            {
               doorNum1 = "One";
               doorNum2 = "Two";
               CultimaPanel.player.setDoorChange((byte)(2));
            }
         }
         else if(doorMonster2)
         {
            doorNum1 = "Two";
            doorNum2 = "One";
            CultimaPanel.player.setDoorChange((byte)(1));
         }
         else //if(doorMonster1)
         {
            doorNum1 = "One";
            doorNum2 = "Two";
            CultimaPanel.player.setDoorChange((byte)(2));
         }
         return getRandomFrom(hint).replace("NUMBER1",doorNum1).replace("NUMBER2",doorNum2);
      }
      if(typed.contains("keep") || typed.contains("change"))
      {
         if(CultimaPanel.player.getDoorNumber()==-1)
            return getRandomFrom(notChosen);
         if(typed.contains("change"))
         {
            if(CultimaPanel.player.getDoorChange()==-1)
               return getRandomFrom(notChosen);
            CultimaPanel.player.setDoorNumber(CultimaPanel.player.getDoorChange());
         }
         CultimaPanel.talkSel = false;
         selected.setTalking(false);
         CultimaPanel.selected = null;
         CultimaPanel.selectedTerrain = null;
         CultimaPanel.typed = "";
       //teleport the sorcerer out with a flash of light
         CultimaPanel.flashColor = java.awt.Color.red;
         CultimaPanel.flashFrame = CultimaPanel.numFrames;
         CultimaPanel.flashRadius = CultimaPanel.SCREEN_SIZE;
         int oldRow = selected.getRow();
         int oldCol = selected.getCol();
         Utilities.removeNPCat(oldRow, oldCol, mi);
                
         if(CultimaPanel.player.getDoorNumber()==1)
         {
            currMap[(int)(CultimaPanel.door1.getX())][(int)(CultimaPanel.door1.getY())] = floor.getValue();
            if(doorMonster1)
            {
               Sound.thunder();
               return getRandomFrom(puzzleLose);
            }
            else
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               CultimaPanel.player.addExperience(100);
               Sound.levelUp(60);
               CultimaPanel.player.stats[Player.PUZZLES_SOLVED]++;
               if(CultimaPanel.player.stats[Player.PUZZLES_SOLVED] >= 3 && CultimaPanel.player.readAllPuzzleBooks())
                  Achievement.earnAchievement(Achievement.PUZZLE_SLAYER);
               return getRandomFrom(puzzleWin);
            }
         }
         else if(CultimaPanel.player.getDoorNumber()==2)
         {
            currMap[(int)(CultimaPanel.door2.getX())][(int)(CultimaPanel.door2.getY())] = floor.getValue();
            if(doorMonster2)
            {  
               Sound.thunder();
               return getRandomFrom(puzzleLose);
            }
            else
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               CultimaPanel.player.addExperience(100);
               Sound.levelUp(60);
               CultimaPanel.player.stats[Player.PUZZLES_SOLVED]++;
               if(CultimaPanel.player.stats[Player.PUZZLES_SOLVED] >= 3 && CultimaPanel.player.readAllPuzzleBooks())
                  Achievement.earnAchievement(Achievement.PUZZLE_SLAYER);
               return getRandomFrom(puzzleWin);
            }
         }
         else //if(CultimaPanel.player.getDoorNumber()==3)
         {
            currMap[(int)(CultimaPanel.door3.getX())][(int)(CultimaPanel.door3.getY())] = floor.getValue();
            if(doorMonster3)
            {  
               Sound.thunder();
               return getRandomFrom(puzzleLose);
            }
            else
            {
               CultimaPanel.player.specificExperience[Player.MIND]++;
               CultimaPanel.player.addExperience(100);
               Sound.levelUp(60);
               CultimaPanel.player.stats[Player.PUZZLES_SOLVED]++;
               if(CultimaPanel.player.stats[Player.PUZZLES_SOLVED] >= 3 && CultimaPanel.player.readAllPuzzleBooks())
                  Achievement.earnAchievement(Achievement.PUZZLE_SLAYER);
               return getRandomFrom(puzzleWin);
            }
         }
      }
      if(typed.contains("deal") || typed.contains("monty") || typed.contains("hall") || typed.contains("zonk") || typed.contains("goat") || typed.contains("llama"))
         return getRandomFrom(deal);
      return getRandomFrom(unknownResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
   }

   public static String towersPuzzleResponse(NPCPlayer selected, String typed)
   {
      String [] noPuzzle = {"Have at thee!", "No Puzzle this day - meet thy doom!", "Meet thy end!", "Infidel!"};
      String [] greetResp = {"Greetings PLAYER - I have been waiting for you", "Greetings to the one called PLAYER", "I greet the one known as PLAYER", "PLAYER has arrived to my inviting PUZZLE"};
      String [] nameAsk = {"You speak to the simple NAME", "I am known as the NAME", "I am the NAME", "Thou has found the NAME", "PLAYER - you stand before the NAME", "I am but a humble NAME", "PLAYER stands before the NAME"};
      String [] unknownResp = {"Thou speaketh nonsense - we are here to present a PUZZLE", "PLAYER speaks without sense - I speak in PUZZLES", "I know not what you speaketh of - you are here for a PUZZLE", "I do not know this word you speaketh. Listen to my PUZZLE"};
      String [] jobAsk = {"I present you, PLAYER, with a simple PUZZLE", "PLAYER, you are worthy of a modest PUZZLE", "A PUZZLE is presented to you, worthy PLAYER", "Wilst thou, worthy PLAYER, take the challenge of the PUZZLE?", "This, the challenge of the PUZZLE that awaits thee, PLAYER"};
      String [] puzzleAsk = {"Read thy BOOK, and return when ready to move the discs", "Thy BOOK awaits, as do I when thee is ready to move the Towers", "Thy BOOK tells my modest Puzzle, and I wait for you to move the disks", "See the BOOK that speaks my Puzzle - return when ready to move the Towers"};
      String [] bookAsk = {"Thy book beneath the Towers tells of my Puzzle. Read the RULES", "See the book beneath the Towers - it details the RULES of my simple Puzzle", "Copy my book into thy journal - study the Puzzle RULES within, PLAYER", "My book will speak the RULES of the Puzzle into thy journal, PLAYER"};
      String [] rules = {  "I present before you three peg towers, where on Tower-One sits a stack of disks, decreasing in radius to the smallest on the top",
                           "Thy goal: move the stack of disks from Tower-One to Tower-Two, only one disk at a time",
                           "Thy rule: a smaller disk can only be placed upon a larger disk. No disk can be placed upon one that is smaller"};                   
      String [] recurResponse = {"Ah, yes: recur, recu, rec, re, r", "Lest thee forget thy terminating case...", "'Tis heavy on thy memory...", "Wilt thou reapeat that, but shorter?"};
      if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
         return getRandomFrom(greetResp).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("name"))
         return getRandomFrom(nameAsk).replace("NAME",selected.getName()).replace("PLAYER",CultimaPanel.player.getShortName());  
      if(typed.contains("job"))
         return getRandomFrom(jobAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("riddle") || typed.contains("puzzle"))
         return getRandomFrom(puzzleAsk); 
      if(typed.contains("book"))
         return getRandomFrom(bookAsk).replace("PLAYER",CultimaPanel.player.getShortName());
      if(typed.contains("rules") || typed.contains("tower"))
         return getRandomFrom(rules);
      if(typed.contains("recur"))
         return getRandomFrom(recurResponse);
      return getRandomFrom(unknownResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
   }

   public static String endGameResponse(NPCPlayer selected, String typed)
   {
      String [] greetResp = {"Greetings PLAYER - we have been watching you!", "Greetings to the one called PLAYER!", "We greet the one known as PLAYER!", "PLAYER is given greetings by us!"};
      String [] nameAsk = {"You speak to the mighty NAME!", "You are PLAYER.  We are NAME!", "We are NAME!", "Thou has found NAME!", "PLAYER - you stand before NAME!", "We are the ones called NAME!", "PLAYER stands before NAME!", "PLAYER kneels before NAME!"};
      String [] jobAsk = {"We rule the Etherworld, and what ASCENDS beyond!", "We wait for you, PLAYER, to ASCEND with us!", "We watch the children of this world, and wait for one to ASCEND!", "We call for you to ASCEND with us!"};
      String [] ascendNoEvil = {"Thee must be as aligned with me - return when you are known as PLAYER the Monster!", "More work on this plain awaits you - return when others call you by PLAYER the Monster!"};
      String [] ascendNoGood = {"Thee must be as aligned with me - return when you are known as PLAYER the Legend!", "More work on this plain awaits you - return when others call you by PLAYER the Legend!"};
      String [] numberAsk = {"Does thou knowest thy number, East x South?", "Sayeth thy number, East x South!", "Thou must recite thy number, East x South!", "Thy number to recite, sayeth East x South!"};
      String [] numberNo = {"Thou knowest thy number, but PLAYER is not ready to speak it!", "Thy number is known, but thy reputation is insufficient to speak it!", "PLAYER knows the number, PLAYER is not of stature to speak it!", "Thy number is correct, PLAYER, but thy reputation dwildles before it!"};
      String [] numberYes = {"PLAYER: you will earn your reign with us when thy time has ended. Taketh the Crown of Skara Brae!", "PLAYER: thee shall ascend with us when thy time on this plane has ended. Taketh the Crown of Skara Brae!", "PLAYER - you shall rule with us when thy time is over on this world. Taketh the Crown of Skara Brae!", "PLAYER: thee shall ascend with us when thy life is ended on this plane. Taketh the Crown of Skara Brae!"};
      String [] numberYesVamp = {"PLAYER: your curse is lifted, and will earn your reign with us upon death. Taketh the Crown of Skara Brae!", "PLAYER: thy curse is purged, and thee shall ascend with us upon death. Taketh the Crown of Skara Brae!", "PLAYER - thy curse is lifted and you shall rule with us upon death. Taketh the Crown of Skara Brae!", "PLAYER: thy curse is purged and thee shall ascend with us when thy life is ended. Taketh the Crown of Skara Brae!"};
      String [] unknownResp = {"Thou speaketh nonsense", "PLAYER speaks without sense", "We know not what you speaketh of", "We do not know this word you speaketh"};
      String [] deathResponse = {"Our Sister, Death. She can be sent back to her underworld", "A Royal Weapon forged with the scared Pearl of Life is thy instrument", "Our Sister is made of Death itself, may be confused by spells that restore life to thee", "Allow our sister to embrace thee, and thou will see thy end"};
   
      if(typed.contains("hello") || typed.equals("hi") || typed.contains("greetings") || typed.contains("good day") || typed.contains("good morning") || typed.contains("good afternoon") || typed.contains("good evening"))
         return getRandomFrom(greetResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
      if(typed.contains("name"))
         return getRandomFrom(nameAsk).replace("NAME",selected.getName()).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";  
      if(typed.contains("job"))
         return getRandomFrom(jobAsk).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
      if(typed.contains("death"))
         return getRandomFrom(deathResponse) + "!";
      if(typed.contains("ascend") || typed.contains("number") || typed.equals("50") || typed.contains("fifty"))
      {
         if(CultimaPanel.player.getReputation() < 0)
         {
            if(CultimaPanel.player.getReputation() > -1000)
               return getRandomFrom(ascendNoEvil).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
            return getRandomFrom(numberAsk).replace("PLAYER",CultimaPanel.player.getShortName());
         }
         else
         {
            if(CultimaPanel.player.getReputation() < 1000)
               return getRandomFrom(ascendNoGood).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
            return getRandomFrom(numberAsk).replace("PLAYER",CultimaPanel.player.getShortName());
         }
      }
      if((typed.equals("2500") || typed.contains("twentyfivehundred") || typed.contains("twenty five hundred") || typed.contains("twentyfive hundred")
      || typed.contains("two thousand five hundred") || typed.contains("two thousand and five hundred") 
      || typed.contains("twothousandfivehundred") || typed.contains("twothousand fivehundred") || typed.contains("twothousand and fivehundred")))
      {
         if(Math.abs(CultimaPanel.player.getReputation())>=1000)
         {
            CultimaPanel.player.setBody(CultimaPanel.player.getHealthMax());
            CultimaPanel.player.setManna(CultimaPanel.player.getMannaMax());
            CultimaPanel.player.setBounty(1);
            if(!CultimaPanel.player.hasItem("blessed-crown"))
            {
               CultimaPanel.player.addGold(1000);
               CultimaPanel.player.addItem("blessed-crown");
            }
            CultimaPanel.missionStack.add(new Mission("Death"));
            CultimaPanel.sendMessage("<New mission added>");
            CultimaPanel.talkSel = false;
            CultimaPanel.selected.setTalking(false);
            CultimaPanel.selected = null;
            CultimaPanel.selectedTerrain = null;
         
            for(int i=CultimaPanel.missionStack.size()-1; i>=0; i--)
            {
               Mission m = CultimaPanel.missionStack.get(i);
               if(m.getType().equals("Main"))
               {
                  CultimaPanel.missionStack.remove(i);
                  Player.stats[Player.MISSIONS_COMPLETED]++;
                  if(Player.stats[Player.MISSIONS_COMPLETED] >= 50)
                     Achievement.earnAchievement(Achievement.TASK_MASTER);
                  CultimaPanel.player.addExperience(1000);
                  FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
                  break;
               }
            }
            if(CultimaPanel.player.isVampire())
            {
               Utilities.removeVampireCurse();
               return getRandomFrom(numberYesVamp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
            }
            if(CultimaPanel.player.isWerewolf())
            {
               Utilities.removeWerewolfCurse();
               return getRandomFrom(numberYesVamp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
            }
            return getRandomFrom(numberYes).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
         }
         return getRandomFrom(numberNo).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
      }   
      return getRandomFrom(unknownResp).replace("PLAYER",CultimaPanel.player.getShortName()) + "!";
   }
   
   public static String shiftWord(String word)
   {
      if(word==null || word.length()==0)
         return word;
      String ans="";
      int shift = 3;
      for(int i=word.length()-1; i>=0; i--)
      {
         char let = word.charAt(i);
         if(((int)(let))+shift <= 122)
            ans += (char)(let+shift);
         else
            ans += ((char)(48+(122-((int)(let)))));
      }
      return ans;
   }
   
   public static boolean isChallenge(String typed)
   {
      if(typed==null || typed.length()<4)
         return false;
      typed = typed.toLowerCase().trim();
      String test = "spxkf0nqxs0hurkz0kfwle0horkvvd01vvxs0wqxf0nfrf0nflg0nfxi0wlkv0qpdg";
      String[] parts = typed.split(" ");
      String[] parts2 = test.split(shiftWord(""+((char)(122)))); 
      for(String word: parts)
         for(String word2: parts2)
            if(shiftWord(word).contains(word2))
               return true;
      return false;
   }
}