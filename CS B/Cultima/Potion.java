import java.util.ArrayList;

public class Potion extends Item
{
   private String effect;
   private int duration;
   private int mannaCost;
   private int[] statModifier;
   
   public static final byte HEALTH = 0;			//for assigning hot-keys for drinking potions
   public static final byte CURE = 1;
   public static final byte FOCUS = 2;
   public static final byte INVISIBILITY = 3;
   public static final byte PROTECTION = 4;
   public static final byte FIRESKIN = 5;
   public static final byte STRENGTH = 6;
   public static final byte ALPHAMIND = 7;
   public static final byte SPEED = 8;

   public static final byte NUM_POTIONS = 9;

   public Potion(String n, String e, int d, int mc, int[] statM, int v)
   {
      super(n, v);
      effect = e;
      duration = d;
      mannaCost = mc;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
   }
   
   public Potion(String n, String e, int d, int mc, int[] statM, int v, String descr)
   {
      super(n, v, descr);
      effect = e;
      duration = d;
      mannaCost = mc;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
   }

   public int getDuration()
   {
      return duration;
   }

   public int getMannaCost()
   {
      return mannaCost;
   }

//[0]-might, [1]-mind, [2]-agility   
   public int[] statModifier()
   {
      return statModifier;
   }

   public static Potion getHealth()
   {
      int [] statMod = {0,0,0};
      String d = "Seal thy minor flesh wounds";
      return new Potion("Health", "none", -1, 0, statMod, 50, d);
   }

   public static Potion getCure()
   {
      int [] statMod = {0,0,0};
      String d = "Purge a poison that boils within thee";
      return new Potion("Cure", "none", -1, 0, statMod, 50, d);
   }
   
   public static Potion getFocus()
   {
      int[] statM = {0,0,0};
      String d = "Enhance thy senses to better detect around thee what the eyes cannot see";
      return new Potion("Focus", "none", 100, 0, statM, 75, d);
   }
   
   public static Potion getProtection()
   {
      int[] statM = {0,0,0};
      String d = "An armor from the ether shields thee";
      return new Potion("Protection", "none", 0, 40, statM, 100, d);
   }

   public static Potion getSpeed()
   {
      int[] statM = {0,0,5};
      String d = "Agility and control given by the spirits";
      return new Potion("Speed", "none", 25, 0, statM, 200, d);
   }
      
   public static Potion getStrength()
   {
      int[] statM = {5,0,0};
      String d = "Draw from the earth to boost thy might";
      return new Potion("Strength", "none", 25, 0, statM, 200, d);
   } 
   
   public static Potion getInvisibility()
   {
      int[] statM = {0,0,5};
      String d = "Make thee as to be completely unseen";
      return new Potion("Invisibility", "none", 25, 0, statM, 500, d);
   }
   
   public static Potion getAlphaMind()
   {
      int[] statM = {0,5,0};
      String d = "Pull from the ether to sharpen thy mind";
      return new Potion("Alphamind", "none", 25, 0, statM, 500, d);
   }
   
   public static Potion getFireSkin()
   {
      int[] statM = {0,0,0};
      String d = "Make thy skin resistant to flame";
      return new Potion("Fireskin", "none", 25, 0, statM, 500, d);
   }

   public static Potion getPotionWithName(String n)
   {
      n = n.toLowerCase();
      if(n.equals("health"))
         return getHealth();
      if(n.equals("cure"))
         return getCure();
      if(n.equals("focus"))
         return getFocus();
      if(n.equals("protection"))
         return getProtection();
      if(n.equals("speed"))
         return getSpeed();
      if(n.equals("strength"))
         return getStrength();
      if(n.equals("invisibility"))
         return getInvisibility();
      if(n.equals("alphamind"))
         return getAlphaMind();
      if(n.equals("fireskin"))
         return getFireSkin();
      return null;
   }
   
   public static Potion getPotionWithNum(byte n)
   {
      if(n==HEALTH)
         return getHealth();
      if(n==CURE)
         return getCure();
      if(n==FOCUS)
         return getFocus();
      if(n==PROTECTION)
         return getProtection();
      if(n==SPEED)
         return getSpeed();
      if(n==STRENGTH)
         return getStrength();
      if(n==INVISIBILITY)
         return getInvisibility();
      if(n==ALPHAMIND)
         return getAlphaMind();
      if(n==FIRESKIN)
         return getFireSkin();
      return null;
   }
   
   public static Potion getRandomPotion()
   {
      ArrayList<Potion>potions = new ArrayList<Potion>();
      for(byte i=0; i<NUM_POTIONS; i++)
         potions.add(getPotionWithNum(i));
      return potions.get((int)(Math.random()*potions.size()));
   }
   
   public static Potion getRandomSimplePotion()
   {
      Potion [] potions = {getPotionWithName("health"),getPotionWithName("health"),getPotionWithName("health"),getPotionWithName("health"),getPotionWithName("health"),
                        getPotionWithName("cure"),getPotionWithName("cure"),getPotionWithName("cure"),getPotionWithName("cure"),getPotionWithName("cure"),
                        getPotionWithName("focus"),getPotionWithName("focus"),getPotionWithName("focus"),getPotionWithName("focus"),getPotionWithName("focus"),
                        getPotionWithName("protection"),getPotionWithName("protection"),getPotionWithName("protection"),getPotionWithName("protection"),getPotionWithName("protection"),
                        getPotionWithName("speed"),getPotionWithName("speed"),getPotionWithName("speed"),getPotionWithName("speed"),getPotionWithName("speed"),
                        getPotionWithName("strength"),getPotionWithName("strength"),getPotionWithName("strength"),getPotionWithName("strength"),getPotionWithName("strength"),
                        getPotionWithName("alphamind"),getPotionWithName("alphamind"),getPotionWithName("alphamind"),getPotionWithName("fireskin"),getPotionWithName("fireskin"),
                        getPotionWithName("invisibility")};
      return potions[(int)(Math.random()*potions.length)];
   }
   
   public static byte getPotionNumWithName(String n)
   {
      n = n.toLowerCase();
      if(n.equals("health"))
         return HEALTH;
      if(n.equals("cure"))
         return CURE;
      if(n.equals("focus"))
         return FOCUS;
      if(n.equals("protection"))
         return PROTECTION;
      if(n.equals("speed"))
         return SPEED;
      if(n.equals("strength"))
         return STRENGTH;
      if(n.equals("invisibility"))
         return INVISIBILITY; 
      if(n.equals("alphamind"))
         return ALPHAMIND; 
      if(n.equals("fireskin"))
         return FIRESKIN;   
      return -1;
   }

   public String toString()
   {
      String ans="";
      ans += getName()+" "+effect+" "+duration+" "+mannaCost+" ";
      for(int n: statModifier)
         ans+= n+" ";
      ans+=getValue();
      return ans;
   }

}