import java.util.ArrayList;

public class Weapon extends Item
{
   private String effect;        //does it curse, poison, set on fire, etc

   private int minDamage;
   private int maxDamage;

   private int range;

   private int[] statModifier;

   private byte imageIndex; 

   private int reloadFrames;     //the number of frames needed to reload between attacking with this weapon

   private int mannaCost;        //if this is a magic weapon
   
   public static final int CROSSBOW_RANGE = 5;
   public static final int BOW_RANGE = 8;
   public static final int LONGBOW_RANGE = 10;


   //given a weapon name in uppercase,return its weapon index
   public static byte getWeaponIndexFromName(String name)
   {
      name = name.trim().toUpperCase();
      if(name.equals("NONE")) 
         return 0;
      if(name.equals("TORCH"))
         return 1;  
      if(name.equals("STAFF")) 
         return 2;
      if(name.equals("LONGSTAFF")) 
         return 3;
      if(name.equals("SPEAR")) 
         return 4;
      if(name.equals("BOW")) 
         return 5;
      if(name.equals("CROSSBOW")) 
         return 6;
      if(name.equals("AXE")) 
         return 7;
      if(name.equals("DUALAXE")) 
         return 8;
      if(name.equals("HAMMER")) 
         return 9;
      if(name.equals("DAGGER")) 
         return 10;
      if(name.equals("SABER")) 
         return 11;
      if(name.equals("SWORDSHIELD")) 
         return 12;
      if(name.equals("DUAL")) 
         return 13;
      if(name.equals("LUTE")) 
         return 14;
      return -1;
   }
   
   //returns true if the weapon has metal parts
   public boolean isMetal()
   {
      return (imageIndex == Player.SPEAR || imageIndex == Player.CROSSBOW || imageIndex == Player.AXE 
           || imageIndex == Player.DUALAXE || imageIndex == Player.HAMMER || imageIndex == Player.DAGGER 
           || imageIndex == Player.SABER || imageIndex == Player.SWORDSHIELD || imageIndex == Player.DUAL);
   }
   
   //returns true if the weapon is mostly made of wood so it can be damaged by fire if in a barrel
   public boolean hasWood()
   {
      return (imageIndex == Player.SPEAR || imageIndex == Player.CROSSBOW || imageIndex == Player.BOW || imageIndex == Player.TORCH
           || imageIndex == Player.LUTE || imageIndex == Player.STAFF || imageIndex == Player.LONGSTAFF);
   }
   
   //given a weapon index, returns the weapon category name for the Journal
   public static String getWeaponType(byte imageIndex)
   {
      if(imageIndex == Player.NONE) 
         return "Armor:";
      if(imageIndex == Player.TORCH) 
         return "Torch:";
      if(imageIndex == Player.STAFF) 
         return "Magic Staff:";
      if(imageIndex == Player.LONGSTAFF) 
         return "Longstaff:";
      if(imageIndex == Player.SPEAR) 
         return "Spears:";
      if(imageIndex == Player.BOW) 
         return "Bows:";
      if(imageIndex == Player.CROSSBOW) 
         return "Crossbows:";
      if(imageIndex == Player.AXE)
         return "Axes:";
      if(imageIndex == Player.DUALAXE)
         return "Dual-axes:";   
      if(imageIndex == Player.HAMMER) 
         return "Hammers:";
      if(imageIndex == Player.DAGGER) 
         return "Daggers:";
      if(imageIndex == Player.SABER) 
         return "Swords:";
      if(imageIndex == Player.SWORDSHIELD) 
         return "Sword and Shield:";
      if(imageIndex == Player.DUAL) 
         return "Dual blades:";
      if(imageIndex == Player.LUTE) 
         return "Lute:";
      return "Misc notes:";
   }
   
   //given a weapon index, returns the weapon category: used for the Player toString
    public static String getWeaponCategory(byte imageIndex)
   {
      if(imageIndex == Player.NONE) 
         return "unarmed";
      if(imageIndex == Player.TORCH) 
         return "torch";
      if(imageIndex == Player.STAFF) 
         return "magic staff";
      if(imageIndex == Player.LONGSTAFF) 
         return "longstaff";
      if(imageIndex == Player.SPEAR) 
         return "spears";
      if(imageIndex == Player.BOW) 
         return "bows";
      if(imageIndex == Player.CROSSBOW) 
         return "crossbows";
      if(imageIndex == Player.AXE)
         return "axes";
      if(imageIndex == Player.DUALAXE)
         return "dual-axes";   
      if(imageIndex == Player.HAMMER) 
         return "hammers";
      if(imageIndex == Player.DAGGER) 
         return "daggers";
      if(imageIndex == Player.SABER) 
         return "swords";
      if(imageIndex == Player.SWORDSHIELD) 
         return "sword and shield";
      if(imageIndex == Player.DUAL) 
         return "dual blades";
      if(imageIndex == Player.LUTE) 
         return "lute";
      return "unknown";
   }


   public Weapon(String n, String eff, int min, int max, int rng, int[]statM, int v, byte ii, int reload, int mc)
   {
      super(n, v);
      reloadFrames = 0;
      effect = eff;
      minDamage = min;
      maxDamage = max;
      range = rng;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
      imageIndex = ii;
      reloadFrames = reload;
      mannaCost = mc;
   }
   
   public Weapon(String n, String eff, int min, int max, int rng, int[]statM, int v, byte ii, int reload, int mc, String d)
   {
      super(n, v, d);
      reloadFrames = 0;
      effect = eff;
      minDamage = min;
      maxDamage = max;
      range = rng;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
      imageIndex = ii;
      reloadFrames = reload;
      mannaCost = mc;
   }

   public int getMannaCost()
   {
      return mannaCost;
   }

   public void setMannaCost(int mc)
   {
      mannaCost = mc;
   }

   public int getReloadFrames()
   {
      return reloadFrames;
   }

   public void setReloadFrames(int rf)
   {
      reloadFrames = rf;
   }

   public String getEffect()
   {
      return effect;
   }

   public int getMinDamage()
   {
      return minDamage;
   }
   
   public void setMinDamage(int r)
   {
      minDamage = r;
   }

   public int getMaxDamage()
   {
      return maxDamage;
   }
   
   public void setMaxDamage(int r)
   {
      maxDamage = r;
   }

   public int getRange()
   {
      return range;
   }
   
   public void setRange(int r)
   {
      range = r;
   }

//[0]-might, [1]-mind, [2]-agility   
   public int[] statModifier()
   {
      return statModifier;
   }

   public void setStatMod(int [] sm)
   {
      statModifier = sm;
   }

   public byte getImageIndex()
   {
      return imageIndex;
   }

   public static Weapon getRandomWeapon()
   {
      ArrayList<Weapon> weapons = new ArrayList<Weapon>();
      weapons.add(getAxe());  
      weapons.add(getMirroredAxe());    
      weapons.add(getDualAxe());
      weapons.add(getHammer());     
      weapons.add(getSpikedHammer());
      weapons.add(getExoticHammer());
      weapons.add(getSword());
      weapons.add(getSwordBuckler());  
      weapons.add(getSwordMirrorshield());   
      weapons.add(getBlessedSword());  
      weapons.add(getShortSwords());   
      weapons.add(getBow());  
      weapons.add(getCrossbow()); 
      weapons.add(getSoulCrossbow());
      weapons.add(getPoisonBoltcaster());  
      weapons.add(getBaneBoltcaster());  
      weapons.add(getLongbow());     
      weapons.add(getSpear());     
      weapons.add(getLongstaff());     
      weapons.add(getDagger());     
      weapons.add(getPoisonDagger()); 
      weapons.add(getFrostDagger()); 
      weapons.add(getMagmaDagger());    
      return weapons.get((int)(Math.random()*weapons.size()));
   }
   
   public static Weapon getRandomWeaponSimple()
   {
      ArrayList<Weapon> weapons = new ArrayList<Weapon>();
      weapons.add(getAxe());  
      weapons.add(getAxe());  
      weapons.add(getAxe());  
      weapons.add(getAxe());  
      weapons.add(getAxe());  
      weapons.add(getDualAxe());
      weapons.add(getHammer());    
      weapons.add(getDualAxe());
      weapons.add(getHammer());    
      weapons.add(getDualAxe());
      weapons.add(getHammer());    
      weapons.add(getDualAxe());
      weapons.add(getHammer());    
      weapons.add(getDualAxe());
      weapons.add(getHammer());     
      weapons.add(getSpikedHammer());
      weapons.add(getSword());
      weapons.add(getSword());
      weapons.add(getSword());
      weapons.add(getSword());
      weapons.add(getSword());
      weapons.add(getSwordBuckler());  
      weapons.add(getShortSwords());   
      weapons.add(getBow());
      weapons.add(getBow());  
      weapons.add(getBow());  
      weapons.add(getBow());  
      weapons.add(getBow());    
      weapons.add(getCrossbow()); 
      weapons.add(getLongbow());     
      weapons.add(getSpear());     
      weapons.add(getLongstaff());     
      weapons.add(getDagger());     
      return weapons.get((int)(Math.random()*weapons.size()));
   }
   
   public static Weapon getRandomWeaponFull()
   {
      ArrayList<Weapon> weapons = new ArrayList<Weapon>();
      weapons.add(getAxe());    
      weapons.add(getMirroredAxe()); 
      weapons.add(getDualAxe());
      weapons.add(getVampyricAxes());
      weapons.add(getTorch());
      weapons.add(getToothedTorch());
      weapons.add(getSpikedClub());
      weapons.add(getGiantMace());
      weapons.add(getHammer());     
      weapons.add(getSpikedHammer());
      weapons.add(getBaneHammer());
      weapons.add(getExoticHammer());
      weapons.add(getSword());   
      weapons.add(getSwordBuckler()); 
      weapons.add(getSwordMirrorshield());     
      weapons.add(getShortSwords());       
      weapons.add(getBlessedSword());     
      weapons.add(getFlameblade());
      weapons.add(getDualblades());
      weapons.add(getSwordShield());
      weapons.add(getBow()); 
      weapons.add(getCrossbow());
      weapons.add(getSoulCrossbow());
      weapons.add(getPoisonBoltcaster()); 
      weapons.add(getBaneBoltcaster());          
      weapons.add(getLongbow()); 
      weapons.add(getFlamebow());    
      weapons.add(getSpear());  
      weapons.add(getJawTrap());
      weapons.add(getBrightHalberd());    
      weapons.add(getLongstaff()); 
      weapons.add(getIceStaff());    
      weapons.add(getSceptre());     
      weapons.add(getDagger());     
      weapons.add(getPoisonDagger());   
      weapons.add(getSoulDagger()); 
      weapons.add(getBaneDagger()); 
      weapons.add(getFrostDagger()); 
      weapons.add(getMagmaDagger()); 
      weapons.add(getLuteOfDestiny());           
      return weapons.get((int)(Math.random()*weapons.size()));
   }
   
   public static Weapon getRandomLegendaryWeapon()
   {
      ArrayList<Weapon> weapons = new ArrayList<Weapon>();
      weapons.add(getGadaTorchmace());  
      weapons.add(getForsetisAxe());    
      weapons.add(getMjolnir());
      weapons.add(getExcalibur());     
      weapons.add(getBowOfKarna());
      weapons.add(getGungnir());
      weapons.add(getAmeNoNuhoko());
      weapons.add(getKhatvanga());  
      weapons.add(getCarnwennan());   
      return weapons.get((int)(Math.random()*weapons.size()));
   }
    
   public static Weapon getNone()
   {
      int[]statM = {0, 0, 0};
      Weapon temp = new Weapon("none", "none", 1, 1, 1, statM, 0, Player.NONE, 0,0);
      return temp;
   }
   
   public static Weapon getOar()
   {
      int[]statM = {0, 0, 0};
      String d = "A wooden paddle used to row, row, row thy boat - may be used to strike upon small creatures of the water";
      return new Weapon("Boat-oar", "none", 5, 25, 1, statM, 4, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
      
   public static Weapon getTorch()
   {
      int[]statM = {0, 0, 0};
      String d = "A basic torch to bring light where there is none, and defend against smaller beasts";
      Weapon temp = new Weapon("Torch", "fire", 1, 1, 1, statM, 4, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }
   
   public static Weapon getToothedTorch()
   {
      int[]statM = {0, 0, -1};
      String d = "A heavy toothed mace wrapped with torchcloth, bringing light for thy exploing and a heavy blow in combat";
      Weapon temp = new Weapon("Toothed-torch", "fire", 30, 40, 1, statM, 2000, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }  
   
   public static Weapon getToothedTorchOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned toothed mace, cast with flame as a torch - supplemts thy strength when wielded";
      Weapon temp = new Weapon("Toothed-torch-o-might", "fire", 35, 40, 1, statM, 4000, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }  
   
   public static Weapon getToothedTorchOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned toothed mace, cast with flame as a torch - supplemts thy spellcraft when wielded";
      Weapon temp = new Weapon("Toothed-torch-o-mind", "fire", 35, 40, 1, statM, 4000, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }
   
   public static Weapon getSwiftToothedTorch()
   {
      int[]statM = {0, 0, 1};
      String d = "An azurite adorned toothed mace, cast with flame as a torch - supplemts thy agility when wielded";
      Weapon temp = new Weapon("Swift-toothed-torch", "fire", 35, 40, 1, statM, 4000, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }
   
   public static Weapon getGadaTorchmace()
   {
      int[]statM = {2, 2, 2};
      String d = "A glowing, gem adorned toothed mace, cast with flame as a torch - power surges through thee when wielded";
      Weapon temp = new Weapon("Gada-torchmace", "fire", 40, 45, 1, statM, 10000, Player.TORCH, (CultimaPanel.animDelay/2),0,d);
      return temp;
   }
       
   public static Weapon getAxe()
   {
      int[]statM = {0, 0, -1};
      String d = "Heavy, two handed axe suitable for felling enemies and wooden doors";
      Weapon temp = new Weapon("Axe", "none", 50, 70, 1, statM, 90, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getFlameAxe()
   {
      int[]statM = {0, 0, -1};
      String d = "A flamejem adorned heavy axe suitable for felling enemies and wooden doors, cast in magic fire";
      Weapon temp = new Weapon("Flameaxe", "fire", 50, 70, 1, statM, 300, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getFrostAxe()
   {
      int[]statM = {0, 0, -1};
      String d = "Icejem adorned heavy axe suitable for felling enemies and wooden doors, cast in magic frost";
      Weapon temp = new Weapon("Frostaxe", "freeze", 50, 70, 1, statM, 300, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
  
   public static Weapon getAxeOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned two handed axe suitable for felling enemies and wooden doors that supplements thy might";
      Weapon temp = new Weapon("Axe-o-might", "none", 55, 70, 1, statM, 200, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getAxeOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned two handed axe suitable for felling enemies and wooden doors that supplements thy spellcraft";
      Weapon temp = new Weapon("Axe-o-mind", "none", 55, 70, 1, statM, 200, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getSwiftAxe()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned two handed axe suitable for felling enemies and wooden doors that supplements thy agility";
      Weapon temp = new Weapon("Swiftaxe", "none", 55, 70, 1, statM, 200, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getBrightAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "A glowing two handed axe suitable for felling enemies and wooden doors that casts light where there is none";
      Weapon temp = new Weapon("Bright-axe", "none", 55, 70, 1, statM, 200, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getMirroredAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "A fine two handed axe made of a strange reflective metal";
      Weapon temp = new Weapon("Mirrored-axe", "none", 55, 75, 1, statM, 3000, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getFlameMirrorAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "A flamejem adorned two handed axe made of a strange reflective metal and cast in magic fire";
      Weapon temp = new Weapon("Flame-Mirroraxe", "fire", 55, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getFrostMirrorAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "Icejem adorned two handed axe made of a strange reflective metal and cast in magic frost";
      Weapon temp = new Weapon("Frost-Mirroraxe", "freeze", 55, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getBrightMirrorAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "A glowing two handed axe made of a strange reflective metal that casts light where there is none";
      Weapon temp = new Weapon("Bright-Mirroraxe", "none", 60, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }

   public static Weapon getMightMirrorAxe()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned two handed axe made of a strange reflective metal that augments thy might";
      Weapon temp = new Weapon("Might-Mirroraxe", "none", 60, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getMindMirrorAxe()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned two handed axe made of a strange reflective metal that augments thy spellcraft";
      Weapon temp = new Weapon("Mind-Mirroraxe", "none", 60, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getSwiftMirrorAxe()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned two handed axe made of a strange reflective metal that augments thy agility";
      Weapon temp = new Weapon("Swift-Mirroraxe", "none", 60, 75, 1, statM, 6000, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getForsetisAxe()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing two handed axe that surges with awesome power";
      Weapon temp = new Weapon("Forsetis-Axe", "none", 65, 80, 1, statM, 10000, Player.AXE, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getDualAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "A one-handed axe on each arm that can quickly strike upon beasts and wooden doors";
      Weapon temp = new Weapon("Dualaxe", "none", 10, 50, 1, statM, 65, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getDualAxeOfFire()
   {
      int[]statM = {0, 0, 0};
      String d = "Flamejem adorned axes that can quickly strike upon beasts and wooden doors and set them to fire";
      Weapon temp = new Weapon("Dualaxe-O-Fire", "fire", 10, 50, 1, statM, 300, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getDualAxeOfFrost()
   {
      int[]statM = {0, 0, 0};
      String d = "Icejem adorned axes that can quickly strike upon beasts and wooden doors and set them to frost";
      Weapon temp = new Weapon("Dualaxe-O-Frost", "freeze", 10, 50, 1, statM, 300, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getBrightDualAxe()
   {
      int[]statM = {0, 0, 0};
      String d = "Moonjem adorned axes that can quickly strike upon beasts and wooden doors and cast light where there is none";
      Weapon temp = new Weapon("Bright-Dualaxe", "none", 15, 50, 1, statM, 140, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getDualAxeOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "Ruby adorned axes that can quickly strike upon beasts and wooden doors and augment thy strength";
      Weapon temp = new Weapon("Dualaxe-O-Might", "none", 15, 50, 1, statM, 140, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getDualAxeOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "Jade adorned axes that can quickly strike upon beasts and wooden doors and augment thy spellcraft";
      Weapon temp = new Weapon("Dualaxe-O-Mind", "none", 15, 50, 1, statM, 140, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getSwiftDualAxe()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned axes that can quickly strike upon beasts and wooden doors and augment thy agility";
      Weapon temp = new Weapon("Swift-Dualaxe", "none", 15, 50, 1, statM, 140, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
      
   public static Weapon getVampyricAxes()
   {
      int[]statM = {0, -2, 0};
      String d ="Strange blackened axes that give to thee the very live they take from their victims";
      Weapon temp = new Weapon("Vampyric-Axes", "none", 10, 40, 1, statM, 4000, Player.DUALAXE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
                
   public static Weapon getHammer()
   {
      int[]statM = {0, 0, -1};
      String d = "Heavy, two handed hammer suitable for felling enemies and doors";
      Weapon temp = new Weapon("Hammer", "none", 50, 60, 1, statM, 70, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getFlameHammer()
   {
      int[]statM = {0, 0, -1};
      String d = "Heavy, flamejem adorned hammer suitable for felling enemies and doors and setting them to fire";
      Weapon temp = new Weapon("Flamehammer", "fire", 50, 60, 1, statM, 300, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getFrostHammer()
   {
      int[]statM = {0, 0, -1};
      String d = "Heavy, icejem adorned hammer suitable for felling enemies and doors and setting them to frost";
      Weapon temp = new Weapon("Frosthammer", "freeze", 50, 60, 1, statM, 300, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getBrightHammer()
   {
      int[]statM = {0, 0, 0};
      String d = "A glowing two-handed hammer suitable for felling enemies and doors and casting light where there is none";
      Weapon temp = new Weapon("Bright-hammer", "none", 55, 60, 1, statM, 150, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getSwiftHammer()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned hammer suitable for felling enemies and doors that augments thy agility";
      Weapon temp = new Weapon("Swift-hammer", "none", 55, 60, 1, statM, 150, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getHammerOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned hammer suitable for felling enemies and doors that augments thy strength";
      Weapon temp = new Weapon("Hammer-O-Might", "none", 55, 60, 1, statM, 150, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getHammerOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned hammer suitable for felling enemies and doors that augments thy spellcraft";
      Weapon temp = new Weapon("Hammer-O-Mind", "none", 55, 60, 1, statM, 150, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getSpikedHammer()
   {
      int[]statM = {0, 0, -1};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors";
      Weapon temp = new Weapon("Spiked-hammer", "none", 60, 70, 1, statM, 100, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
  
   public static Weapon getSpikedHammerOfFire()
   {
      int[]statM = {0, 0, -1};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, cast in magic flame";
      Weapon temp = new Weapon("Spiked-flamehammer", "fire", 60, 70, 1, statM, 100, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getSpikedFrostHammer()
   {
      int[]statM = {0, 0, -1};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, cast in magic frost";
      Weapon temp = new Weapon("Spiked-Frosthammer", "freeze", 60, 70, 1, statM, 100, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getBrightSpikedHammer()
   {
      int[]statM = {0, 0, 0};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, glowing to bring light where there is none";
      Weapon temp = new Weapon("Bright-Spiked-hammer", "none", 65, 70, 1, statM, 200, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getMightSpikedHammer()
   {
      int[]statM = {1, 0, 0};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, augmenting thy strength";
      Weapon temp = new Weapon("Might-Spiked-hammer", "none", 65, 70, 1, statM, 200, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getMindSpikedHammer()
   {
      int[]statM = {0, 1, 0};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, augmenting thy spellcraft";
      Weapon temp = new Weapon("Mind-Spiked-hammer", "none", 65, 70, 1, statM, 200, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }

   public static Weapon getSwiftSpikedHammer()
   {
      int[]statM = {0, 0, 1};
      String d = "A heavy toothed-hammer for dealing massive damage to enemies and doors, augmenting thy agility";
      Weapon temp = new Weapon("Swift-Spiked-hammer", "none", 65, 70, 1, statM, 200, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getBaneHammer()
   {
      int[]statM = {0, -2, 0};
      String d = "A heavy blackened-hammer, can cast a curse on its victim. Commoners will run in fear at the sight of it";
      Weapon temp = new Weapon("Banehammer", "curse", 50, 50, 1, statM, 2000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getExoticHammer()
   {
      int[]statM = {1, 0, 0};
      String d = "A large hammer made of a strange metal, strong but easy to wield";
      Weapon temp = new Weapon("Exotic-hammer", "none", 55, 65, 1, statM, 1000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getExoticHammerOfFire()
   {
      int[]statM = {1, 0, 0};
      String d = "A flamejem adorned hammer made of a strange metal, strong but easy to wield, and cast in magic flame";
      Weapon temp = new Weapon("Exotic-flamehammer", "fire", 55, 65, 1, statM, 3000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getExoticFrostHammer()
   {
      int[]statM = {1, 0, 0};
      String d = "Icejem adorned hammer made of a strange metal, strong but easy to wield, and cast in magic frost";
      Weapon temp = new Weapon("Exotic-frosthammer", "freeze", 55, 65, 1, statM, 3000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getBrightExoticHammer()
   {
      int[]statM = {1, 0, 0};
      String d = "A moonjem adorned hammer made of a strange metal, strong but easy to wield, and casting light where there is none";
      Weapon temp = new Weapon("Bright-Exotic-hammer", "none", 60, 65, 1, statM, 2000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getMightExoticHammer()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned hammer made of a strange metal, strong but easy to wield, augmenting thy strength";
      Weapon temp = new Weapon("Might-Exotic-hammer", "none", 60, 65, 1, statM, 2000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getMindExoticHammer()
   {
      int[]statM = {1, 1, 0};
      String d = "A jade adorned hammer made of a strange metal, strong but easy to wield, augmenting thy spellcraft";
      Weapon temp = new Weapon("Mind-Exotic-hammer", "none", 60, 65, 1, statM, 2000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getSwiftExoticHammer()
   {
      int[]statM = {1, 0, 1};
      String d = "Azurite adorned hammer made of a strange metal, strong but easy to wield, augmenting thy agility";
      Weapon temp = new Weapon("Swift-Exotic-hammer", "none", 60, 65, 1, statM, 2000, Player.HAMMER, CultimaPanel.animDelay/2,0,d);
      return temp;
   }
   
   public static Weapon getMjolnir()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing two handed hammer that surges with awesome power";
      Weapon temp = new Weapon("Mjolnir", "none", 65, 70, 1, statM, 10000, Player.HAMMER, CultimaPanel.animDelay/2,0, d);
      return temp;
   }
   
   public static Weapon getGiantMace()
   {
      int [] statM = {0, 0, -2};
      String d = "Unwieldy large mace, toothed at the top, suitable for massive man-beasts";
      Weapon temp = new Weapon("Giant-mace", "none", 70, 80, 1, statM, 100, Player.HAMMER, CultimaPanel.animDelay,0,d);
      return temp;
   }

   public static Weapon getSpikedClub()
   {
      int [] statM = {0, 0, -3};
      String d = "Large wooden club with sharpened spikes, suitable for massive man-beasts";
      Weapon temp = new Weapon("Spiked-club", "none", 80, 90, 1, statM, 70, Player.HAMMER, CultimaPanel.animDelay,0,d);
      return temp;
   }

   public static Weapon getSword()
   {
      int[]statM = {0, 0, 0};
      String d = "A two-handed sword, common among beast fighting adventurers";
      return new Weapon("Sword", "none", 50, 75, 1, statM, 100, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getBrightSword()
   {
      int[]statM = {0, 0, 0};
      String d = "A two-handed sword adorned with a moonjem, casting light where there is not";
      return new Weapon("Bright-sword", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getSwiftSword()
   {
      int[]statM = {0, 0, 1};
      String d = "A two-handed sword adorned with azurite, augmenting thy agility";
      return new Weapon("Swift-sword", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getSwordOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "A two-handed sword adorned with a ruby, augmenting thy strength";
      return new Weapon("Sword-o-might", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getSwordOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "A two-handed sword adorned with jade, augmenting thy spellcraft";
      return new Weapon("Sword-o-mind", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }

   public static Weapon getBlessedSword()
   {
      int[]statM = {0, 0, 0};
      String d = "A fine two-handed sword, elegant enough to impress the gods to bless thee";
      return new Weapon("Blessed-sword", "none", 50, 75, 1, statM, 1000, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getBlessedSwiftblade()
   {
      int[]statM = {0, 0, 1};
      String d = "An impressive two-handed sword adorned with azurite, augmenting thy agility";
      return new Weapon("Blessed-swiftblade", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getBlessedMightblade()
   {
      int[]statM = {1, 0, 0};
      String d = "An impressive two-handed sword adorned with a ruby, augmenting thy strength";
      return new Weapon("Blessed-mightblade", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getBlessedMindblade()
   {
      int[]statM = {0, 1, 0};
      String d = "An impressive two-handed sword adorned with a jade, augmenting thy spellcraft";
      return new Weapon("Blessed-mindblade", "none", 55, 75, 1, statM, 200, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getExcalibur()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing two handed sword that surges with awesome power";
      return new Weapon("Excalibur", "none", 60, 80, 1, statM, 10000, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }

   public static Weapon getFlameblade()
   {
      int []statM = {0,0,0};
      String d = "An impressive two-handed sword adorned with a flamejem, casting its victim to fire";
      return new Weapon("Flameblade", "fire", 60, 75, 1, statM, 2000, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getFrostblade()
   {
      int[]statM = {0, 0, 0};
      String d = "An impressive two-handed sword adorned with an icejem, casting its victim to frost";
      return new Weapon("Frostblade", "freeze", 50, 75, 1, statM, 100, Player.SABER, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getSwordBuckler()
   {
      int[]statM = {0, 0, 0};
      String d = "A combination short-sword and small shield, balanced attacking power and defensive abilities";
      Weapon temp = new Weapon("Sword-Buckler", "none", 40, 65, 1, statM, 120, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getFlamebladeBuckler()
   {
      int[]statM = {0, 0, 0};
      String d = "A small shield and flamejem adorned short-sword to set enemies to fire";
      Weapon temp = new Weapon("Flameblade-Buckler", "fire", 40, 65, 1, statM, 400, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getFrostbladeBuckler()
   {
      int[]statM = {0, 0, 0};
      String d = "A small shield and icejem adorned short-sword to set enemies to frost";
      Weapon temp = new Weapon("Frostblade-Buckler", "freeze", 40, 65, 1, statM, 400, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getBrightSwordBuckler()
   {
      int[]statM = {0, 0, 0};
      String d = "A small shield and moonjem adorned short-sword to cast light where there is none";
      Weapon temp = new Weapon("Bright-Sword-Buckler", "none", 45, 65, 1, statM, 240, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getSwordBucklerOfMight()
   {
      int[]statM = {1, 0, 0};
      String d = "A small shield and ruby adorned short-sword to augment thy strength";
      Weapon temp = new Weapon("Sword-Buckler-O-Might", "none", 45, 65, 1, statM, 240, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getSwordBucklerOfMind()
   {
      int[]statM = {0, 1, 0};
      String d = "A small shield and jade adorned short-sword to augment thy spellcraft";
      Weapon temp = new Weapon("Sword-Buckler-O-Mind", "none", 45, 65, 1, statM, 240, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getSwiftSwordBuckler()
   {
      int[]statM = {0, 0, 1};
      String d = "A small shield and azurite adorned short-sword to augment thy agility";
      Weapon temp = new Weapon("Swift-Sword-Buckler", "none", 45, 65, 1, statM, 240, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getSwordMirrorshield()
   {
      int[]statM = {0, 0, 0};
      String d = "A reflective shield and fine short-sword to balance attack and defense";
      Weapon temp = new Weapon("Sword-Mirrorshield", "none", 50, 70, 1, statM, 800, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getFlamebladeMirrorshield()
   {
      int[]statM = {0, 0, 0};
      String d = "A reflective shield and flamejem adorned short-sword to set thy foes to fire";
      Weapon temp = new Weapon("Flameblade-Mirrorshield", "fire", 50, 70, 1, statM, 3000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getFrostbladeMirrorshield()
   {
      int[]statM = {0, 0, 0};
      String d = "A reflective shield and icejem adorned short-sword to set thy foes to frost";
      Weapon temp = new Weapon("Frostblade-Mirrorshield", "freeze", 50, 70, 1, statM, 3000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getBrightSwordMirrorshield()
   {
      int[]statM = {0, 0, 0};
      String d = "A reflective shield and moonjem adorned short-sword to set light where there is none";
      Weapon temp = new Weapon("Bright-Sword-Mirrorshield", "none", 55, 70, 1, statM, 2000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getMightSwordMirrorshield()
   {
      int[]statM = {1, 0, 0};
      String d = "A reflective shield and ruby adorned short-sword to augment thy strength";
      Weapon temp = new Weapon("Might-Sword-Mirrorshield", "none", 55, 70, 1, statM, 2000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getMindSwordMirrorshield()
   {
      int[]statM = {0, 1, 0};
      String d = "A reflective shield and jade adorned short-sword to augment thy spellcraft";
      Weapon temp = new Weapon("Mind-Sword-Mirrorshield", "none", 55, 70, 1, statM, 2000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getSwiftSwordMirrorshield()
   {
      int[]statM = {0, 0, 1};
      String d = "A reflective shield and azurite adorned short-sword to augment thy agility";
      Weapon temp = new Weapon("Swift-Sword-Mirrorshield", "none", 55, 70, 1, statM, 2000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
 
   public static Weapon getSwordShield()
   {
      int[]statM = {0, 0, 0};
      String d = "A finely crafted shield and sword, certain to impress commoners";
      Weapon temp = new Weapon("Royal-sword-shield", "none", 60, 75, 1, statM, 1000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getLifeSwordShield()
   {
      int[]statM = {1, 1, 1};
      String d = "A finely crafted shield and sword adorned with a glowing life pearl - certain to impress the living and frighten the undead in this realm";
      Weapon temp = new Weapon("Royal-sword-of-life", "none", 65, 75, 1, statM, 10000, Player.SWORDSHIELD, CultimaPanel.animDelay/4,0,d);
      return temp;
   }

   public static Weapon getShortSwords()
   {
      int []statM = {0,0,0};
      String d = "Short-swords in each hand, allowing thee to strike quickly";
      return new Weapon("Short-swords", "none", 40, 65, 1, statM, 120, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }
   
   public static Weapon getBrightSwords()
   {
      int []statM = {0,0,0};
      String d = "Quick striking short-swords in each hand, adorned with moonjems to cast light where there is none";
      return new Weapon("Bright-swords", "none", 45, 65, 1, statM, 250, Player.DUAL, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getSwordsOfMight()
   {
      int []statM = {1,0,0};
      String d = "Quick striking short-swords in each hand, adorned with rubys to augment thy strength";
      return new Weapon("Swords-o-might", "none", 45, 65, 1, statM, 250, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }
   
   public static Weapon getSwordsOfMind()
   {
      int []statM = {0,1,0};
      String d = "Quick striking short-swords in each hand, adorned with jade to augment thy spellcraft";
      return new Weapon("Swords-o-mind", "none", 45, 65, 1, statM, 250, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }
   
   public static Weapon getWindSwords()
   {
      int []statM = {0,0,1};
      String d = "Quick striking short-swords in each hand, adorned with azurite to augment thy agility";
      return new Weapon("Wind-swords", "none", 45, 65, 1, statM, 250, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }

   public static Weapon getDualblades()
   {
      int []statM = {0,0,1};
      String d = "Finely crafted swords in each hand, allowing for quick and deadly strikes";
      return new Weapon("Dualblades", "none", 70, 85, 1, statM, 2000, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }

   public static Weapon getDualFlameblades()
   {
      int []statM = {0,0,0};
      String d = "Finely crafted swords in each hand adorned with flamejems, setting thy foes to fire";
      return new Weapon("Dual-flameblades", "fire", 40, 65, 1, statM, 600, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }
   
   public static Weapon getDualFrostblades()
   {
      int []statM = {0,0,0};
      String d = "Finely crafted swords in each hand adorned with icejems, setting thy foes to frost";
      return new Weapon("Dual-frostblades", "freeze", 40, 65, 1, statM, 600, Player.DUAL, (CultimaPanel.animDelay/8),0,d);
   }

   public static Weapon getCrossbow()
   {
      int[]statM = {0, 0, 0};
      String d = "Sturdy crossbow to throw a heavy bolt at moderate range";
      Weapon temp = new Weapon("Crossbow", "none", 10, 50, CROSSBOW_RANGE, statM, 180, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getFlamecaster()
   {
      int[]statM = {0, 0, 0};
      String d = "Flamejem adorned crossbow to throw a heavy bolt to cast its target on fire";
      Weapon temp = new Weapon("Flamecaster", "fire", 10, 50, CROSSBOW_RANGE, statM, 600, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getFrostcaster()
   {
      int[]statM = {0, 0, 0};
      String d = "Icejem adorned crossbow to throw a heavy bolt to cast its target to frost";
      Weapon temp = new Weapon("Frostcaster", "freeze", 10, 50, CROSSBOW_RANGE, statM, 600, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }

   public static Weapon getBrightcaster()
   {
      int[]statM = {0, 0, 0};
      String d = "Moonjem adorned crossbow to throw a heavy bolt at moderate range, glowing to cast light where there is none";
      Weapon temp = new Weapon("Brightcaster", "none", 15, 50, CROSSBOW_RANGE, statM, 400, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getMightcaster()
   {
      int[]statM = {1, 0, 0};
      String d = "Ruby adorned crossbow to throw a heavy bolt at moderate range that augments thy strength";
      Weapon temp = new Weapon("Mightcaster", "none", 15, 50, CROSSBOW_RANGE, statM, 400, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getMindcaster()
   {
      int[]statM = {0, 1, 0};
      String d = "Jade adorned crossbow to throw a heavy bolt at moderate range that augments thy spellcraft";
      Weapon temp = new Weapon("Mindcaster", "none", 15, 50, CROSSBOW_RANGE, statM, 400, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getWindcaster()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned crossbow to throw a heavy bolt at moderate range that augments thy agility";
      Weapon temp = new Weapon("Windcaster", "none", 15, 50, CROSSBOW_RANGE, statM, 400, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getPoisonBoltcaster()
   {
      int[]statM = {0, 0, 0};
      String d = "Finely crafted crossbow that throws a poison-laced bolt at moderate range";
      Weapon temp = new Weapon("Poison-boltcaster", "poison", 15, 50, CROSSBOW_RANGE, statM, 2000, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getBaneBoltcaster()
   {
      int[]statM = {0, -2, 0};
      String d = "Blackened crossbow that throws a cursing bolt at moderate range, terrifying commoners to flee at the sight of it";
      Weapon temp = new Weapon("Bane-boltcaster", "curse", 15, 50, CROSSBOW_RANGE, statM, 2000, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }
   
   public static Weapon getSoulCrossbow()
   {
      int[]statM = {0, 0, 0};
      String d = "Strange carved crossbow that throws a bolt at moderate range with a chance of possessing its victim";
      return new Weapon("Soul-crossbow", "control", 5, 30, CROSSBOW_RANGE, statM, 2000, Player.CROSSBOW, (int)(CultimaPanel.animDelay*2.5),0,d);
   }

   public static Weapon getBow()
   {
      int[]statM = {0, 0, 0};
      String d = "Wooden shortbow that can accurately deliver arrows to ranged enemies";
      Weapon temp = new Weapon("Bow", "none", 5, 40, BOW_RANGE, statM, 80, Player.BOW, CultimaPanel.animDelay,0,d);
      return temp;
   }
   
   public static Weapon getBrightBow()
   {
      int[]statM = {0, 0, 0};
      String d = "Moonjem adorned shortbow that casts light where there is none";
      Weapon temp = new Weapon("Bright-bow", "none", 10, 40, BOW_RANGE, statM, 160, Player.BOW, CultimaPanel.animDelay,0,d);
      return temp;
   }
   
   public static Weapon getMightBow()
   {
      int[]statM = {1, 0, 0};
      String d = "Ruby adorned shortbow that augments thy strength";
      Weapon temp = new Weapon("Might-bow", "none", 10, 40, BOW_RANGE, statM, 160, Player.BOW, CultimaPanel.animDelay,0,d);
      return temp;
   }

   public static Weapon getMindBow()
   {
      int[]statM = {0, 1, 0};
      String d = "Jade adorned shortbow that augments thy spellcraft";
      Weapon temp = new Weapon("Mind-bow", "none", 10, 40, BOW_RANGE, statM, 160, Player.BOW, CultimaPanel.animDelay,0,d);
      return temp;
   }

   public static Weapon getWindBow()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned shortbow that augments thy agility";
      Weapon temp = new Weapon("Wind-bow", "none", 10, 40, BOW_RANGE, statM, 160, Player.BOW, CultimaPanel.animDelay,0,d);
      return temp;
   }

   public static Weapon getFlamebow()
   {
      int[]statM = {0, 0, 0};
      String d = "Finely crafted shortbow adorned with a flamejem, casting its target to fire";
      Weapon temp = new Weapon("Flamebow", "fire", 8, 42, BOW_RANGE, statM, 2000, Player.BOW, (int)(CultimaPanel.animDelay*2.5),0, d);
      return temp;
   }
   
   public static Weapon getFrostbow()
   {
      int[]statM = {0, 0, 0};
      String d = "Finely crafted shortbow adorned with an icejem, casting its target to frost";
      Weapon temp = new Weapon("Frostbow", "freeze", 8, 42, BOW_RANGE, statM, 2000, Player.BOW, (int)(CultimaPanel.animDelay*2.5),0,d);
      return temp;
   }

   public static Weapon getLongbow()
   {
      int[]statM = {0, 0, 0};
      String d = "Wooden longbow that can deliver an arrow to far-ranged enemies";
      Weapon temp = new Weapon("Longbow", "none", 10, 45, LONGBOW_RANGE, statM, 120, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }
   
   public static Weapon getBrightLongbow()
   {
      int[]statM = {0, 0, 0};
      String d = "Moonjem adorned longbow that can deliver an arrow to far-ranged enemies and cast light where there is none";
      Weapon temp = new Weapon("Bright-longbow", "none", 15, 45, LONGBOW_RANGE, statM, 250, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }
   
   public static Weapon getMightLongbow()
   {
      int[]statM = {1, 0, 0};
      String d = "Ruby adorned longbow that can deliver an arrow to far-ranged enemies and augment thy strength";
      Weapon temp = new Weapon("Might-longbow", "none", 15, 45, LONGBOW_RANGE, statM, 250, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }
   
   public static Weapon getMindLongbow()
   {
      int[]statM = {0, 1, 0};
      String d = "Jade adorned longbow that can deliver an arrow to far-ranged enemies and augment thy spellcraft";
      Weapon temp = new Weapon("Mind-longbow", "none", 15, 45, LONGBOW_RANGE, statM, 250, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }

   public static Weapon getWindLongbow()
   {
      int[]statM = {0, 0, 1};
      String d = "Azurite adorned longbow that can deliver an arrow to far-ranged enemies and augment thy agility";
      Weapon temp = new Weapon("Wind-longbow", "none", 15, 45, LONGBOW_RANGE, statM, 250, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }
   
   public static Weapon getBowOfKarna()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing longbow that surges with awesome power";
      Weapon temp = new Weapon("Bow-of-Karna", "none", 20, 50, LONGBOW_RANGE, statM, 10000, Player.BOW, CultimaPanel.animDelay*2,0,d);
      return temp;
   }
   
   public static Weapon getSpear()
   {
      int[]statM = {0, 0, 0};
      String d = "A wooden handled spear for keeping foes at range";
      return new Weapon("Spear", "none", 10, 50, 2, statM, 60, Player.SPEAR, (CultimaPanel.animDelay/2),0, d);
   }
   
   public static Weapon getFlameSpear()
   {
      int[]statM = {0, 0, 0};
      String d = "A flamejem adorned spear for keeping foes at range and casting them to fire";
      return new Weapon("Flamespear", "fire", 10, 50, 2, statM, 300, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getFrostSpear()
   {
      int[]statM = {0, 0, 0};
      String d = "An icejem adorned spear for keeping foes at range and casting them to frost";
      return new Weapon("Frostspear", "freeze", 10, 50, 2, statM, 300, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }

   public static Weapon getBrightSpear()
   {
      int[]statM = {0, 0, 0};
      String d = "A moonjem adorned spear for keeping foes at range and casting light where there is none";
      return new Weapon("Bright-spear", "none", 15, 50, 2, statM, 120, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getMightSpear()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned spear for keeping foes at range and augmenting thy strength";
      return new Weapon("Might-spear", "none", 15, 50, 2, statM, 120, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getMindSpear()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned spear for keeping foes at range and augmenting thy spellcasting";
      return new Weapon("Mind-spear", "none", 15, 50, 2, statM, 120, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getWindSpear()
   {
      int[]statM = {0, 0, 1};
      String d = "An azurite adorned spear for keeping foes at range and augmenting thy agility";
      return new Weapon("Wind-spear", "none", 15, 50, 2, statM, 120, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getGungnir()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing spear that surges with awesome power";
      return new Weapon("Gungnir", "none", 20, 55, 2, statM, 10000, Player.SPEAR, (CultimaPanel.animDelay/2),0, d);
   }
   
   public static Weapon getHalberd()
   {
      int[]statM = {0, 0, 0};
      String d = "A wooden handled halberd for striking enemies at range";
      return new Weapon("Halberd", "none", 25, 60, 2, statM, 200, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }

   public static Weapon getHalberdOfFire()
   {
      int[]statM = {0, 0, 0};
      String d = "A flamejem adorned halberd for striking enemies at range and casting them to fire";
      return new Weapon("Halberd-O-Fire", "fire", 25, 60, 2, statM, 600, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getHalberdOfFrost()
   {
      int[]statM = {0, 0, 0};
      String d = "An icejem adorned halberd for striking enemies at range and casting them to frost";
      return new Weapon("Halberd-O-Frost", "freeze", 25, 60, 2, statM, 600, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }

   public static Weapon getBrightHalberd()
   {
      int[]statM = {0, 0, 2};
      String d = "A moonjem adorned halberd for striking enemies at range and casting light where there is none";
      return new Weapon("Bright-Halberd", "none", 25, 60, 2, statM, 2000, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getMightHalberd()
   {
      int[]statM = {1, 0, 0};
      String d = "A ruby adorned halberd for striking enemies at range and augmenting thy strength";
      return new Weapon("Might-Halberd", "none", 30, 60, 2, statM, 500, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getMindHalberd()
   {
      int[]statM = {0, 1, 0};
      String d = "A jade adorned halberd for striking enemies at range and augmenting thy spellcasting";
      return new Weapon("Mind-Halberd", "none", 30, 60, 2, statM, 500, Player.SPEAR, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getWindHalberd()
   {
      int[]statM = {0, 0, 1};
      String d = "An azurite adorned halberd for striking enemies at range and augmenting thy agility";
      return new Weapon("Wind-Halberd", "none", 30, 60, 2, statM, 500, Player.SPEAR, (CultimaPanel.animDelay/4),0,d);
   }
   
   public static Weapon getAmeNoNuhoko()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing halberd that surges with awesome power";
      return new Weapon("Ame-No-Nuhoko", "none", 35, 65, 2, statM, 10000, Player.SPEAR, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getLongstaff()
   {
      int[]statM = {0, 0, 2};
      String d = "A wooden longstaff for acrobatically striking foes at range";
      return new Weapon("Long-staff", "none", 5, 35, 2, statM, 40, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getFlamestaff()
   {
      int[]statM = {0, 0, 2};
      String d = "A flamejem adorned longstaff for acrobatically striking foes at range and casting them to fire";
      return new Weapon("Flamestaff", "fire", 5, 35, 2, statM, 200, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }

   public static Weapon getWindstaff()
   {
      int[]statM = {0, 0, 3};
      String d = "An azurite adorned longstaff for acrobatically striking foes at range and augmenting thy agility";
      return new Weapon("Windstaff", "none", 10, 35, 2, statM, 100, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getBrightstaff()
   {
      int[]statM = {0, 0, 2};
      String d = "A moonjem adorned longstaff for acrobatically striking foes at range and casting light where there was none";
      return new Weapon("Brightstaff", "none", 10, 35, 2, statM, 100, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getMightstaff()
   {
      int[]statM = {1, 0, 2};
      String d = "A ruby adorned longstaff for acrobatically striking foes at range and augmenting thy strength";
      return new Weapon("Mightstaff", "none", 10, 35, 2, statM, 100, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getMindstaff()
   {
      int[]statM = {0, 1, 2};
      String d = "A jade adorned longstaff for acrobatically striking foes at range and augmenting thy spellcasting";
      return new Weapon("Mindstaff", "none", 10, 35, 2, statM, 100, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
   
   public static Weapon getKhatvanga()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing longstaff that surges with awesome power";
      return new Weapon("Khatvanga", "none", 15, 40, 2, statM, 10000, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }
    
   public static Weapon getSceptre()
   {
      int [] statM2 = {1, 1, 1};
      String d = "A golden, jeweled staff that will impress the living upon sight";
      return new Weapon("Royal-sceptre", "none", 4, 7, 2, statM2, 1000, Player.LONGSTAFF, (CultimaPanel.animDelay/4),0,d);   
   }
   
   public static Weapon getLifeSceptre()
   {
      int [] statM2 = {1, 1, 1};
      String d = "A golden jeweled staff, adorned with a lifepearl. Will impress the living upon sight, and frighten the undead that visit our realm";
      return new Weapon("Royal-lifesceptre", "none", 5, 35, 2, statM2, 10000, Player.LONGSTAFF, (CultimaPanel.animDelay/6),0,d);   
   }
   
   public static Weapon getIceStaff()
   {
      int[]statM = {0, 0, 2};
      String d = "An icejem adorned longstaff for acrobatically striking foes at range and casting them to frost";
      return new Weapon("Bright-Icestaff", "freeze", 5, 15, 2, statM, 2000, Player.LONGSTAFF, (CultimaPanel.animDelay/8),0, d);
   }

   public static Weapon getDagger()
   {
      int[]statM = {0, 0, 1};
      String d = "An iron dagger with a wooden handle for agile striking";
      return new Weapon("Dagger", "none", 5, 50, 1, statM, 50, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getBrightDagger()
   {
      int[]statM = {0, 0, 1};
      String d = "A glowing iron dagger with a moonjem adorned handle for agile striking";
      return new Weapon("Bright-dagger", "none", 10, 50, 1, statM, 100, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getSwiftDagger()
   {
      int[]statM = {0, 0, 2};
      String d = "An iron dagger with an azurite adorned handle to augment thy agility";
      return new Weapon("Swift-dagger", "none", 10, 50, 1, statM, 100, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getDaggerOfMight()
   {
      int[]statM = {1, 0, 1};
      String d = "An iron dagger with a ruby adorned handle to augment thy strength";
      return new Weapon("Dagger-o-might", "none", 10, 50, 1, statM, 100, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getDaggerOfMind()
   {
      int[]statM = {0, 1, 1};
      String d = "An iron dagger with a jade adorned handle to augment thy spellcraft";
      return new Weapon("Dagger-o-mind", "none", 10, 50, 1, statM, 100, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getCarnwennan()
   {
      int[]statM = {2, 2, 2};
      String d = "A legendary glowing dagger that surges with awesome power";
      return new Weapon("Carnwennan", "none", 15, 55, 1, statM, 10000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }

   public static Weapon getPoisonDagger()
   {
      int[]statM = {0, 0, 2};
      String d = "A poison-laced iron dagger with a sculpted handle";
      return new Weapon("Poison-dagger", "poison", 10, 50, 1, statM, 1000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getSoulDagger()
   {
      int[]statM = {0, 0, 2};
      String d = "A black-bladed dagger that can possess its victim";
      return new Weapon("Souldagger", "control", 10, 50, 1, statM, 2000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getMagmaDagger()
   {
      int[]statM = {0, 0, 2};
      String d = "A iron dagger with a glowing orange blade and flamejem adorned handle to cast thy victims to fire";
      return new Weapon("Magmadagger", "fire", 10, 50, 1, statM, 1000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getFrostDagger()
   {
      int[]statM = {0, 0, 2};
      String d = "An iron dagger with an icejem adorned handle to cast thy victims to frost";
      return new Weapon("Frostdagger", "freeze", 10, 50, 1, statM, 1000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }
   
   public static Weapon getBaneDagger()
   {
      int[]statM = {0, -2, 2};
      String d = "A blackened dagger that will send commonors running upon sight and curse its victim";
      return new Weapon("Banedagger", "curse", 10, 50, 1, statM, 1000, Player.DAGGER, (CultimaPanel.animDelay/4),0, d);
   }

   public static Weapon getFists()
   {
      int[]statM = {0, 0, 0};
      String d = "Thy very own fists, closed to strike an enemy";
      return new Weapon("Fists", "none", 1, 1, 1, statM, 0, Player.NONE, (CultimaPanel.animDelay/8),0,d);
   }
   
   public static Weapon getVampyricBite()
   {
      int[]statM = {0, 0, 0};
      String d = "A bite from thy sharpened teeth, 'twill render an unalert victim to stillness and give thee strength from their blood";
      Weapon temp = new Weapon("Vampyric-bite", "still", 2, 3, 1, statM, 0, Player.NONE, CultimaPanel.animDelay/4,0,d);
      return temp;
   }
   
   public static Weapon getHardVampyricBite()
   {
      int[]statM = {0, 0, 0};
      Weapon temp = new Weapon("Vampyric-bite", "curse", 5, 25, 1, statM, 0, Player.NONE, CultimaPanel.animDelay/4,0);
      return temp;
   }
   
   public static Weapon getJawTrap()
   {
      int[]statM = {0, 0, 0};
      String d = "An iron-toothed snare to trap game and foe";
      Weapon temp = new Weapon("Iron-jaw-trap", "still", 10, 25, 1, statM, 0, Player.NONE, CultimaPanel.animDelay/4,0, d);
      return temp;
   }
      
   public static Weapon getThrashingBite()    //werewolf attack player
   {
      int[]statM = {0, 0, 0};
      return new Weapon("Thrashing-bite", "none", 50, 75, 1, statM, 0, Player.WOLF, (CultimaPanel.animDelay/4),0);
   }
   
   public static Weapon getThrashingJaws()    //werewolf attack npc
   {
      int[]statM = {0, 0, 0};
      return new Weapon("Thrashing-jaws", "none", 50, 75, 1, statM, 0, Player.NONE, (CultimaPanel.animDelay/4),0);
   }

   public static Weapon getLute()
   {
      int[]statM = {0, 0, 0};
      return new Weapon("Lute", "none", 1, 1, 1, statM, 0, Player.NONE, (CultimaPanel.animDelay),0);
   }

   public static Weapon getLuteOfDestiny()
   {
      int[]statM = {0, 0, 0};
      String d = "Beautifully crafted lute that can charm some with an irresistable song, compelling them to follow and dance";
      return new Weapon("Lute-O-Destiny", "none", 1, 1, 1, statM, 2000, Player.LUTE, (CultimaPanel.animDelay),0,d);
   }

   public static Weapon getStaff()
   {
      int[]statM = {0, 0, 0};
      String d = "Ancient wooden staff, carved with symbols that can direct offensive magic";
      return new Weapon("Staff", "none", 5, 25, 2, statM, 40, Player.STAFF, (CultimaPanel.animDelay/2),0,d);
   }
   
   public static Weapon getMageStaff()
   {
      int[]statM = {0, 1, 0};
      String d = "Ancient wooden staff, carved with symbols and adorned with a mannastone. Can direct offensive magic and has a chance of stunning an enemy with a strike";
      return new Weapon("Magestaff", "stun", 10, 30, 2, statM, 40, Player.STAFF, (CultimaPanel.animDelay/3),0,d);
   }
   
   public static Weapon getBlindingLight()
   {
      int[]statM = {0, 0, 0};
      String d = "Temporarily stun thy enemy with a silent but blinding flash, rendering them less aware for a moment";
      return new Weapon("Blindinglight", "stun", 0, 1, 5, statM, 200, Player.STAFF, 0,20, d);
   }

   public static Weapon getPhasewall()
   {
      int[]statM = {0, 0, 0};
      String d = "Shift into existence an opening in a wall of stone, earth or wood to allow thee to pass through";
      return new Weapon("Phasewall", "none", 0, 0, 1, statM, 800, Player.STAFF, 0,400, d);
   }
   
   public static Weapon getAdvance()
   {
      int[] statM = {0,0,0};
      String d = "Teleport thee through solid to the first open space in the direction of thy staff";
      return new Weapon("Advance", "none", 0, 0, 1, statM, 4000, Player.STAFF, 0, 160, d);
   }

   public static Weapon getCurse()
   {
      int[]statM = {0, 0, 0};
      String d = "Cast dread and confusion in the mind of thy enemy, making them less adept in combat";
      return new Weapon("Curse", "curse", 0, 0, 3, statM, 800, Player.STAFF, 0,160,d);
   }

   public static Weapon getPossess()
   {
      int[]statM = {0, 0, 0};
      String d = "Dark spellcraft that renders thy foe mindless, so it might attack its own kind";
      return new Weapon("Possess", "control", 0, 0, 3, statM, 800, Player.STAFF, 0,160,d);
   }

   public static Weapon getFireball()
   {
      int[]statM = {0, 0, 0};
      String d = "From thy magic staff, launch a ball of flame to set thy foe ablaze";
      return new Weapon("Fireball", "fire", 10, 20, 8, statM, 200, Player.STAFF, 0,40, d);
   }

   public static Weapon getSpidersWeb()
   {
      int[]statM = {0, 0, 0};
      String d = "Ensnare a beast in a magic web as from a great spider";
      Weapon temp = new Weapon("Spidersweb", "web", 0, 0, 5, statM, 8000, Player.STAFF, 0,30, d);
      return temp;
   }

   public static Weapon getIcestrike()
   {
      int[]statM = {0, 0, 0};
      String d = "Damage and slow a foe from range with piercing cold";
      return new Weapon("Icestrike", "freeze", 10, 30, 8, statM, 400, Player.STAFF, 0,80, d);
   }

   public static Weapon getLightning()
   {
      int[]statM = {0, 0, 0};
      String d = "Summon from thy staff a great bolt from a storm, striking an enemy at range";
      return new Weapon("Lightning", "none", 60, 100, 5, statM, 400, Player.STAFF, 0,80,d);
   }

   public static Weapon getDeathtouch()
   {
      int[]statM = {0, 0, 0};
      String d = "Pull the very life from a close victim from the end of thy very finger";
      return new Weapon("Deathtouch", "none", 500, 500, 1, statM, 800, Player.STAFF, 0,160,d);
   }
   
   public static Weapon getStonecast()
   {
      int[]statM = {0, 0, 0};
      String d = "Render any living creature as solid stone at the grasp of thy hand";
      return new Weapon("Stonecast", "none", 500, 500, 5, statM, 8000, Player.STAFF, 0,200,d);
   }
   
   public static Weapon getFireShield()
   {
      int[] statM = {0,0,0};
      String d = "Summon a wall of protective flame in front of thee";
      return new Weapon("Fireshield", "none", 0, 0, 3, statM, 4000, Player.STAFF, 0,200,d);
   }
   
   public static Weapon getSummonVortex()
   {
      int[] statM = {0,0,0};
      String d = "Summon a mindless spinning cyclone of air to deal massive injury to thy enemies";
      return new Weapon("Summon-Vortex", "none", 0, 0, CultimaPanel.SCREEN_SIZE/4, statM, 4000, Player.STAFF, 0,400,d);
   }
   
   public static Weapon getRaiseStone()
   {
      int[] statM = {0,0,0};
      String d = "Summon a spire like rock from the earth to block a path, or perhaps turn into a magic monolith";
      return new Weapon("Raise-Stone", "none", 0, 0, 1, statM, 2000, Player.STAFF, 0,100,d);
   }

   public static Weapon getGreatCannon()
   {
      int[]statM = {0, 0, 5};
      String d = "Fire from great range an exploding ball of iron, ending foes and likely the items they carry";
      return new Weapon("Great-cannon", "fire", 300, 400, 12, statM, 1000, Player.GREATSHIP, CultimaPanel.animDelay*4,0, d);
   }

   public static Weapon getBrigandCannon()
   {
      int[]statM = {0, 0, 5};
      String d = "Fire from great range an exploding ball of iron, ending foes and likely the items they carry";
      return new Weapon("Brigand-cannon", "fire", 200, 300, 10, statM, 1000, Player.BRIGANDSHIP, CultimaPanel.animDelay*4,0,d);
   }
   
   public static Weapon getStrikingHooves()
   {
      int[]statM = {0, 0, 2};
      String d = "The pounding hooves of a rearing steed can strike at enemies";
      return  new Weapon("Striking-hooves", "none", 5, 20, 1, statM, 0, Player.NONE, 0 ,0, d);
   }
   
   public static Weapon getBrightHorn()   //unicorn horn
   {
      int[]statM = {0, 2, 0};
      String d = "The glowing horn of a majestic unicorn - can keep foes at bay and give thee faster manna recovery";
      return  new Weapon("Bright-horn", "none", 15, 50, 2, statM, 10000, Player.LONGSTAFF, 0 ,0,d);
   }

   public static Weapon getCannonball()
   {
      return new Weapon("Cannonball", "none", 25, 50, 8, null, 0, Player.NONE, 0,0);   
   }
   
   public static Weapon getCrushingJaws()
   {
      int[]statM = {0, 0, 0};
      return new Weapon("Crushing-jaws", "none", 100, 500, 1, statM, 800, Player.NONE, 0,0);
   }

   public static Weapon getSwirlingChaos()
   {
      return new Weapon("Swirling-chaos", "none", 50, 150, 2, null, 0, Player.NONE, 0,0);
   }
   
   public static Weapon getMassiveFists()
   {
      return new Weapon("Massive-fists", "none", 50, 150, 2, null, 0, Player.NONE, 0,0);
   }

   public static Weapon getBite()
   {
      return new Weapon("Bite", "none", 3, 5, 1, null, 0, Player.NONE, 0,0);   
   }
   
   public static Weapon getBurn()
   {
      return new Weapon("Burn", "fire", 3, 5, 1, null, 0, Player.NONE, 0,0);   
   }

   public static Weapon getFangedBite()
   {
      return new Weapon("Fanged-bite", "none", 8, 15, 1, null, 0, Player.NONE, 0 ,0);   
   }

   public static Weapon getCrushingBite()
   {
      return  new Weapon("Crushing-bite", "none", 30, 50, 1, null, 0, Player.NONE, 0 ,0);   
   }

   public static Weapon getCoilingArms()
   {
      return  new Weapon("Coiling-arms", "none", 10, 25, 2, null, 0, Player.NONE, 0 ,0);   
   }

   public static Weapon getMassiveCoilingArms()
   {
      return  new Weapon("Massive-coiling-arms", "none", 30, 80, 3, null, 0, Player.NONE, 0 ,0);   
   }

   public static Weapon getHardPoisionBite()
   {
      return new Weapon("Hard-poison-bite", "poison", 30, 80, 1, null, 0, Player.NONE, 0 ,0); 
   }

   public static Weapon getPoisonBite()
   {
      return new Weapon("Poison-bite", "poison", 2, 5, 1, null, 0, Player.NONE, 0 ,0); 
   }

   public static Weapon getIceBreath()
   {
      return  new Weapon("Ice-breath", "freeze", 20, 40, 8, null, 0, Player.NONE, 0 ,0);   
   }

   public static Weapon getDemonicClaw()
   {
      return new Weapon("Demonic-claw", "curse", 25, 50, 1, null, 0, Player.NONE, 0 ,0); 
   }

   public static Weapon getDragonfire()
   {
      return new Weapon("Dragonfire", "fire", 20, 40, 9, null, 0, Player.NONE, 0, 0);
   }

//npc sorcerer's lightning attack
   public static Weapon getLightningbolt()
   {
      return new Weapon("Lightningbolt", "none", 25, 60, 5, null, 0, Player.NONE, 0, 0);
   }

   public static Weapon getDragonBolt()
   {
      return new Weapon("Lightningfire", "none", 50, 100, 9, null, 0, Player.NONE, 0, 0);
   }
   
   public static Weapon getGun()
   {
      return new Weapon("Gun", "none", 50, 75, 9, null, 0, Player.NONE, 0, 0);
   }

   public static boolean isLegendaryWeapon(String n)
   {
      n = n.toLowerCase().trim();
      return (n.equalsIgnoreCase("forsetis-axe") ||
              n.equalsIgnoreCase("mjolnir") || 
              n.equalsIgnoreCase("bow-of-karna") || 
              n.equalsIgnoreCase("excalibur") || 
              n.equalsIgnoreCase("ame-no-nuhoko") ||
              n.equalsIgnoreCase("gada-torchmace") ||
              n.equalsIgnoreCase("khatvanga") ||
              n.equalsIgnoreCase("gungnir") ||
              n.equalsIgnoreCase("carnwennan"));
   }   
   
   public static boolean isSpell(String n)
   {
      return (n.equalsIgnoreCase("phasewall") ||
           n.equalsIgnoreCase("advance") ||
           n.equalsIgnoreCase("curse") ||
           n.equalsIgnoreCase("possess") ||
           n.equalsIgnoreCase("fireball") ||
           n.equalsIgnoreCase("spidersweb") ||
           n.equalsIgnoreCase("icestrike") ||
           n.equalsIgnoreCase("lightning") ||
           n.equalsIgnoreCase("deathtouch") ||
           n.equalsIgnoreCase("fireshield") ||
           n.equalsIgnoreCase("summon-vortex") ||
           n.equalsIgnoreCase("raise-stone") ||
           n.equalsIgnoreCase("lightningbolt") ||
           n.equalsIgnoreCase("stonecast") ||
           n.equalsIgnoreCase("blindingLight"));
   }

   public static Weapon getWeaponWithName(String n)
   {
      n = n.trim();
      if(n.equalsIgnoreCase("none"))
         return getNone();
     
      if(n.equalsIgnoreCase("boat-oar"))
         return getOar();
     
      if(n.equalsIgnoreCase("torch"))
         return getTorch();
         
      if(n.equalsIgnoreCase("toothed-torch"))
         return getToothedTorch();
      if(n.equalsIgnoreCase("toothed-torch-o-might"))
         return getToothedTorchOfMight();
      if(n.equalsIgnoreCase("toothed-torch-o-mind"))
         return getToothedTorchOfMind();
      if(n.equalsIgnoreCase("swift-toothed-torch"))
         return getSwiftToothedTorch();
      if(n.equalsIgnoreCase("gada-torchmace"))        //legendary weapon
         return getGadaTorchmace();
        
      if(n.equalsIgnoreCase("axe"))
         return getAxe();
      if(n.equalsIgnoreCase("flameaxe"))
         return getFlameAxe();
      if(n.equalsIgnoreCase("frostaxe"))
         return getFrostAxe();
      if(n.equalsIgnoreCase("axe-o-might"))
         return getAxeOfMight();
      if(n.equalsIgnoreCase("bright-axe"))
         return getBrightAxe();
      if(n.equalsIgnoreCase("axe-o-mind"))
         return getAxeOfMind();
      if(n.equalsIgnoreCase("swiftaxe"))
         return getSwiftAxe();
         
      if(n.equalsIgnoreCase("mirrored-axe"))
         return getMirroredAxe();
      if(n.equalsIgnoreCase("flame-mirroraxe"))
         return getFlameMirrorAxe();
      if(n.equalsIgnoreCase("frost-mirroraxe"))
         return getFrostMirrorAxe();
      if(n.equalsIgnoreCase("bright-mirroraxe"))
         return getBrightMirrorAxe();
      if(n.equalsIgnoreCase("might-mirroraxe"))
         return getMightMirrorAxe();
      if(n.equalsIgnoreCase("mind-mirroraxe"))
         return getMindMirrorAxe();
      if(n.equalsIgnoreCase("swift-mirroraxe"))
         return getSwiftMirrorAxe(); 
      if(n.equalsIgnoreCase("forsetis-axe"))       //legendary weapon
         return getForsetisAxe();  
         
      if(n.equalsIgnoreCase("dualaxe"))
         return getDualAxe();
      if(n.equalsIgnoreCase("dualaxe-o-fire"))
         return getDualAxeOfFire();
      if(n.equalsIgnoreCase("dualaxe-o-frost"))
         return getDualAxeOfFrost();
      if(n.equalsIgnoreCase("bright-dualaxe"))
         return getBrightDualAxe();
      if(n.equalsIgnoreCase("dualaxe-o-might"))
         return getDualAxeOfMight();   
      if(n.equalsIgnoreCase("dualaxe-o-mind"))
         return getDualAxeOfMind();
      if(n.equalsIgnoreCase("swift-dualaxe"))
         return getSwiftDualAxe();
         
      if(n.equalsIgnoreCase("vampyric-axes"))
         return getVampyricAxes();
         
      if(n.equalsIgnoreCase("royal-sword-shield"))
         return getSwordShield();
      if(n.equalsIgnoreCase("royal-sword-of-life"))   
         return getLifeSwordShield();
         
      if(n.equalsIgnoreCase("sword-buckler"))
         return getSwordBuckler();
      if(n.equalsIgnoreCase("flameblade-buckler"))
         return getFlamebladeBuckler();
      if(n.equalsIgnoreCase("frostblade-buckler"))
         return getFrostbladeBuckler();
      if(n.equalsIgnoreCase("bright-sword-buckler"))
         return getBrightSwordBuckler();
      if(n.equalsIgnoreCase("swift-sword-buckler"))
         return getSwiftSwordBuckler();
      if(n.equalsIgnoreCase("sword-buckler-o-might"))
         return getSwordBucklerOfMight();
      if(n.equalsIgnoreCase("sword-buckler-o-mind"))
         return getSwordBucklerOfMind();
         
      if(n.equalsIgnoreCase("sword-mirrorshield"))
         return getSwordMirrorshield();   
      if(n.equalsIgnoreCase("flameblade-mirrorshield"))
         return getFlamebladeMirrorshield(); 
      if(n.equalsIgnoreCase("frostblade-mirrorshield"))
         return getFrostbladeMirrorshield();  
      if(n.equalsIgnoreCase("bright-sword-mirrorshield"))
         return getBrightSwordMirrorshield();
      if(n.equalsIgnoreCase("might-sword-mirrorshield"))
         return getMightSwordMirrorshield();
      if(n.equalsIgnoreCase("mind-sword-mirrorshield"))
         return getMindSwordMirrorshield();
      if(n.equalsIgnoreCase("swift-sword-mirrorshield"))
         return getSwiftSwordMirrorshield();
             
      if(n.equalsIgnoreCase("hammer"))
         return getHammer();
      if(n.equalsIgnoreCase("flamehammer"))
         return getFlameHammer();
      if(n.equalsIgnoreCase("frosthammer"))
         return getFrostHammer();
      if(n.equalsIgnoreCase("bright-hammer"))
         return getBrightHammer(); 
      if(n.equalsIgnoreCase("swift-hammer"))
         return getSwiftHammer(); 
      if(n.equalsIgnoreCase("hammer-o-might"))
         return getHammerOfMight();
      if(n.equalsIgnoreCase("hammer-o-mind"))
         return getHammerOfMind();
          
      if(n.equalsIgnoreCase("spiked-hammer"))
         return getSpikedHammer();
      if(n.equalsIgnoreCase("spiked-frosthammer"))
         return getSpikedFrostHammer();
      if(n.equalsIgnoreCase("spiked-flamehammer"))
         return getSpikedHammerOfFire();
      if(n.equalsIgnoreCase("bright-spiked-hammer"))
         return getBrightSpikedHammer();
      if(n.equalsIgnoreCase("might-spiked-hammer"))
         return getMightSpikedHammer();
      if(n.equalsIgnoreCase("mind-spiked-hammer"))
         return getMindSpikedHammer();
      if(n.equalsIgnoreCase("swift-spiked-hammer"))
         return getSwiftSpikedHammer();
         
      if(n.equalsIgnoreCase("banehammer"))
         return getBaneHammer();
         
      if(n.equalsIgnoreCase("exotic-hammer"))
         return getExoticHammer();
      if(n.equalsIgnoreCase("exotic-frosthammer"))
         return getExoticFrostHammer();
      if(n.equalsIgnoreCase("exotic-flamehammer"))
         return getExoticHammerOfFire();
      if(n.equalsIgnoreCase("bright-exotic-hammer"))
         return getBrightExoticHammer();
      if(n.equalsIgnoreCase("might-exotic-hammer"))
         return getMightExoticHammer();
      if(n.equalsIgnoreCase("mind-exotic-hammer"))
         return getMindExoticHammer();
      if(n.equalsIgnoreCase("swift-exotic-hammer"))
         return getSwiftExoticHammer();
      if(n.equalsIgnoreCase("mjolnir"))            //legendary weapon
         return getMjolnir();
         
      if(n.equalsIgnoreCase("giant-mace"))
         return getGiantMace();
      if(n.equalsIgnoreCase("spiked-club"))
         return getSpikedClub();
         
      if(n.equalsIgnoreCase("sword"))
         return getSword();
      if(n.equalsIgnoreCase("flameblade"))
         return getFlameblade();
      if(n.equalsIgnoreCase("frostblade"))
         return getFrostblade();
      if(n.equalsIgnoreCase("bright-sword"))
         return getBrightSword();
      if(n.equalsIgnoreCase("swift-sword"))
         return getSwiftSword();
      if(n.equalsIgnoreCase("sword-o-might"))
         return getSwordOfMight();
      if(n.equalsIgnoreCase("sword-o-mind"))
         return getSwordOfMind();
         
      if(n.equalsIgnoreCase("blessed-sword"))
         return getBlessedSword();
      if(n.equalsIgnoreCase("blessed-swiftblade"))
         return getBlessedSwiftblade();
      if(n.equalsIgnoreCase("blessed-mightblade"))
         return getBlessedMightblade();
      if(n.equalsIgnoreCase("blessed-mindblade"))
         return getBlessedMindblade();    
      if(n.equalsIgnoreCase("excalibur"))          //legendary weapon
         return getExcalibur();            
         
      if(n.equalsIgnoreCase("dualblades"))
         return getDualblades();
         
      if(n.equalsIgnoreCase("short-swords"))
         return getShortSwords();
      if(n.equalsIgnoreCase("dual-flameblades"))
         return getDualFlameblades();
      if(n.equalsIgnoreCase("dual-frostblades"))
         return getDualFrostblades();
      if(n.equalsIgnoreCase("bright-swords"))
         return getBrightSwords();
      if(n.equalsIgnoreCase("swords-o-mind"))
         return getSwordsOfMind();
      if(n.equalsIgnoreCase("swords-o-might"))
         return getSwordsOfMight();
      if(n.equalsIgnoreCase("wind-swords"))
         return getWindSwords();   
         
      if(n.equalsIgnoreCase("crossbow"))
         return getCrossbow();
      if(n.equalsIgnoreCase("flamecaster"))
         return getFlamecaster(); 
      if(n.equalsIgnoreCase("frostcaster"))
         return getFrostcaster();
      if(n.equalsIgnoreCase("brightcaster"))
         return getBrightcaster();    
      if(n.equalsIgnoreCase("mightcaster"))
         return getMightcaster();
      if(n.equalsIgnoreCase("mindcaster"))
         return getMindcaster();
      if(n.equalsIgnoreCase("windcaster"))
         return getWindcaster();  
         
      if(n.equalsIgnoreCase("soul-crossbow"))
         return getSoulCrossbow();
      if(n.equalsIgnoreCase("poison-boltcaster"))
         return getPoisonBoltcaster();
      if(n.equalsIgnoreCase("bane-boltcaster"))
         return getBaneBoltcaster();
         
      if(n.equalsIgnoreCase("bow"))
         return getBow();
      if(n.equalsIgnoreCase("flamebow"))
         return getFlamebow();
      if(n.equalsIgnoreCase("frostbow"))
         return getFrostbow();
      if(n.equalsIgnoreCase("bright-bow"))
         return getBrightBow();
      if(n.equalsIgnoreCase("might-bow"))
         return getMightBow();
      if(n.equalsIgnoreCase("mind-bow"))
         return getMindBow();
      if(n.equalsIgnoreCase("wind-bow"))
         return getWindBow();
         
      if(n.equalsIgnoreCase("longbow"))
         return getLongbow();
      if(n.equalsIgnoreCase("bright-longbow"))
         return getBrightLongbow();
      if(n.equalsIgnoreCase("might-longbow"))
         return getMightLongbow();
      if(n.equalsIgnoreCase("mind-longbow"))
         return getMindLongbow();
      if(n.equalsIgnoreCase("wind-longbow"))
         return getWindLongbow();
      if(n.equalsIgnoreCase("bow-of-karna"))       //legendary weapon
         return getBowOfKarna();   
         
      if(n.equalsIgnoreCase("spear"))
         return getSpear();
      if(n.equalsIgnoreCase("flamespear"))
         return getFlameSpear();
      if(n.equalsIgnoreCase("frostspear"))
         return getFrostSpear();
      if(n.equalsIgnoreCase("bright-spear"))
         return getBrightSpear();
      if(n.equalsIgnoreCase("might-spear"))
         return getMightSpear();
      if(n.equalsIgnoreCase("mind-spear"))
         return getMindSpear();
      if(n.equalsIgnoreCase("wind-spear"))
         return getWindSpear();
      if(n.equalsIgnoreCase("gungnir"))            //legendary weapon
         return getGungnir();
         
      if(n.equalsIgnoreCase("halberd"))
         return getHalberd();
      if(n.equalsIgnoreCase("halberd-o-fire"))
         return getHalberdOfFire();
      if(n.equalsIgnoreCase("halberd-o-frost"))
         return getHalberdOfFrost();
      if(n.equalsIgnoreCase("bright-halberd"))
         return getBrightHalberd();
      if(n.equalsIgnoreCase("might-halberd"))
         return getMightHalberd();
      if(n.equalsIgnoreCase("mind-halberd"))
         return getMindHalberd();
      if(n.equalsIgnoreCase("wind-halberd"))
         return getWindHalberd();
      if(n.equalsIgnoreCase("ame-no-nuhoko"))         //legendary weapon
         return getAmeNoNuhoko();
         
      if(n.equalsIgnoreCase("magestaff"))
         return getMageStaff();
      if(n.equalsIgnoreCase("staff"))
         return getStaff();
         
      if(n.equalsIgnoreCase("long-staff"))
         return getLongstaff();
      if(n.equalsIgnoreCase("flamestaff"))
         return getFlamestaff();
      if(n.equalsIgnoreCase("brightstaff"))
         return getBrightstaff();
      if(n.equalsIgnoreCase("windstaff"))
         return getWindstaff();
      if(n.equalsIgnoreCase("mightstaff"))
         return getMightstaff();
      if(n.equalsIgnoreCase("mindstaff"))
         return getMindstaff();
      if(n.equalsIgnoreCase("khatvanga"))          //legendary weapon
         return getKhatvanga();
   
      if(n.equalsIgnoreCase("bright-icestaff"))
         return getIceStaff();
         
      if(n.equalsIgnoreCase("dagger"))
         return getDagger();
      if(n.equalsIgnoreCase("bright-dagger"))
         return getBrightDagger();
      if(n.equalsIgnoreCase("swift-dagger"))
         return getSwiftDagger();
      if(n.equalsIgnoreCase("dagger-o-might"))
         return getDaggerOfMight();
      if(n.equalsIgnoreCase("dagger-o-mind"))
         return getDaggerOfMind();
      if(n.equalsIgnoreCase("carnwennan"))         //legendary weapon
         return getCarnwennan();
         
      if(n.equalsIgnoreCase("poison-dagger"))
         return getPoisonDagger();
      if(n.equalsIgnoreCase("souldagger"))
         return getSoulDagger();
      if(n.equalsIgnoreCase("banedagger"))
         return getBaneDagger();
      if(n.equalsIgnoreCase("magmadagger"))
         return getMagmaDagger();
      if(n.equalsIgnoreCase("frostdagger"))
         return getFrostDagger();
                 
      if(n.equalsIgnoreCase("royal-sceptre"))
         return getSceptre();
      if(n.equalsIgnoreCase("royal-lifesceptre"))
         return getLifeSceptre();  
             
      if(n.equalsIgnoreCase("lightningfire"))
         return getDragonBolt();
      if(n.equalsIgnoreCase("dragonfire"))
         return getDragonfire();
      if(n.equalsIgnoreCase("demonic-claw"))
         return getDemonicClaw();
      if(n.equalsIgnoreCase("ice-breath"))
         return getIceBreath();
      if(n.equalsIgnoreCase("striking-hooves"))
         return getStrikingHooves();
      if(n.equalsIgnoreCase("bright-horn"))
         return getBrightHorn();
   
      if(n.equalsIgnoreCase("bite"))
         return getBite();
      if(n.equalsIgnoreCase("burn"))
         return getBurn();         
      if(n.equalsIgnoreCase("crushing-bite"))
         return getCrushingBite();
      if(n.equalsIgnoreCase("poison-bite"))
         return getPoisonBite();
      if(n.equalsIgnoreCase("hard-poison-bite"))
         return getHardPoisionBite();
      if(n.equalsIgnoreCase("coiling-arms"))
         return getCoilingArms();
      if(n.equalsIgnoreCase("massive-coiling-arms"))
         return getMassiveCoilingArms();
      if(n.equalsIgnoreCase("fanged-bite"))
         return getFangedBite();
      if(n.equalsIgnoreCase("swirling-chaos"))
         return getSwirlingChaos();
      if(n.equalsIgnoreCase("massive-fists"))
         return getMassiveFists();
      if(n.equalsIgnoreCase("crushing-jaws"))
         return getCrushingJaws();
      if(n.equalsIgnoreCase("cannonball"))
         return getCannonball();
      if(n.equalsIgnoreCase("great-cannon"))
         return getGreatCannon();
      if(n.equalsIgnoreCase("brigand-cannon"))
         return getBrigandCannon();
                  
      if(n.equalsIgnoreCase("fists"))
         return getFists();
      if(n.equalsIgnoreCase("vampyric-bite"))    //vampire attack
         return getVampyricBite();
      if(n.equalsIgnoreCase("hard-vampyric-bite"))    //vampire attack
         return getHardVampyricBite();
         
      if(n.equalsIgnoreCase("iron-jaw-trap"))
         return getJawTrap();
      if(n.equalsIgnoreCase("thrashing-bite"))   //werewolf attack player
         return  getThrashingBite();
      if(n.equalsIgnoreCase("thrashing-jaws"))   //werewolf attack npc
         return  getThrashingJaws();
    
      if(n.equalsIgnoreCase("lute-o-destiny"))
         return getLuteOfDestiny(); 
      if(n.equalsIgnoreCase("lute"))
         return getLute();    
         
      if(n.equalsIgnoreCase("phasewall"))
         return getPhasewall();
      if(n.equalsIgnoreCase("advance"))
         return getAdvance();
      if(n.equalsIgnoreCase("curse"))
         return getCurse();
      if(n.equalsIgnoreCase("possess"))
         return getPossess();
      if(n.equalsIgnoreCase("fireball"))
         return getFireball();
      if(n.equalsIgnoreCase("spidersweb"))
         return getSpidersWeb();
      if(n.equalsIgnoreCase("icestrike"))
         return getIcestrike();
      if(n.equalsIgnoreCase("lightning"))
         return getLightning();
      if(n.equalsIgnoreCase("deathtouch"))
         return getDeathtouch();
      if(n.equalsIgnoreCase("fireshield"))
         return getFireShield();
      if(n.equalsIgnoreCase("summon-vortex"))
         return getSummonVortex();
      if(n.equalsIgnoreCase("raise-stone"))
         return getRaiseStone();
      if(n.equalsIgnoreCase("lightningbolt"))
         return getLightningbolt();
      if(n.equalsIgnoreCase("stonecast"))
         return getStonecast(); 
      if(n.equalsIgnoreCase("blindingLight"))
         return getBlindingLight();
         
      if(n.equalsIgnoreCase("gun"))
         return getGun();  
      return null;
   }

   public String toString()
   {
      String ans="";
      ans += getName()+" "+effect+" "+minDamage+" "+maxDamage+" "+range;
      for(int n: statModifier)
         ans+= n+" ";
      ans+=getValue()+" "+imageIndex+" "+reloadFrames;   
      return ans;
   }

}