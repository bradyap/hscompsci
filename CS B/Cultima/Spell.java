public class Spell extends Item
{
   private String effect;
   private int duration;
   private int mannaCost;
   private int[] statModifier;
   private int timeLeft;            //time left when the spell is invoked

   public Spell(String n, String e, int d, int mc, int[] statM, int v)
   {
      super(n, v);
      effect = e;
      duration = d;
      mannaCost = mc;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
      timeLeft = duration;   
   }
   
    public Spell(String n, String e, int d, int mc, int[] statM, int v, String descr)
   {
      super(n, v, descr);
      effect = e;
      duration = d;
      mannaCost = mc;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
      timeLeft = duration;   
   }

//return the time left of an active spell
   public int getTimeLeft()
   {
      return timeLeft;
   }

//set the time of an active spell
   public void setTimeLeft(int tl)
   {
      timeLeft = tl;
   }

//take 1 time increment off of an active spell
   public void countDown()
   {
      if(timeLeft >= 0)
         timeLeft--;
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
   
   public static Spell getShiftWind()
   {
      int[] statM = {0,0,0};
      String d = "Alter the very wind's course to affect sailing, the spread of fires, clear fog or aid hunting";
      return new Spell("Shiftwind", "none", -1, 20, statM, 50, d);
   }
   
   public static Spell getMagicMist()
   {
      int[] statM = {0,0,0};
      String d = "Summon fog to evade thy pursuers, effective against men and man-like beasts";
      return new Spell("Magicmist", "none", 50, 80, statM, 50, d);
   }
   
   public static Spell getTeleport()
   {
      int[] statM = {0,0,0};
      String d = "Change thy very location in a blink to thy map marked location or the entrance of thy current location";
      return new Spell("Teleport", "none", -1, 100, statM, 1000, d);
   }

   public static Spell getFloretLade()
   {
      int[] statM = {0,0,0};
      String d = "Restore thy manna from the magic woven flowers in thy full floretbox";
      return new Spell("Floretlade", "none", -1, 0, statM, 9999, d);
   }

   public static Spell getDisarm()
   {
      int[] statM = {0,0,0};
      String d = "Undo that which is locked or set with trap";
      return new Spell("Disarm", "none", -1, 40, statM, 100, d);
   }

   public static Spell getLight()
   {
      int[] statM = {0,0,0};
      String d = "Make light where there is not";
      return new Spell("Light", "none", 100, 40, statM, 50, d);
   }

   public static Spell getCharm()
   {
      int[] statM = {0,0,0};
      String d = "Make thee more appealing to others";
      return new Spell("Charm", "none", 50, 40, statM, 400, d);
   }

   public static Spell getFear()
   {
      int[] statM = {0,0,0};
      String d = "Take on the appearance of a fearsome beast to walk among them, or frighten commoners";
      return new Spell("Fear", "none", 25, 40, statM, 500, d);
   }

   public static Spell getHeal()
   {
      int[] statM = {0,0,0};
      String d = "Seal thy minor flesh wounds";
      return new Spell("Heal", "none", -1, 40, statM, 100, d);
   }

   public static Spell getCure()
   {
      int[] statM = {0,0,0};
      String d = "Purge a poison that boils within thee";
      return new Spell("Cure", "none", -1, 40, statM, 100, d);
   }

   public static Spell getRestore()
   {
      int[] statM = {0,0,0};
      String d = "Heal thy moderate flesh wounds and cure thy blood of poison to the limits of thy manna";
      return new Spell("Restore", "none", -1, 100, statM, 800, d);
   }

   public static Spell getKnowing()
   {
      int[] statM = {0,0,0};
      String d = "Draw knowledge of others from the ether or track the location of a mission target or treasure";
      return new Spell("Knowing", "none", 25, 20, statM, 50, d);
   }

   public static Spell getFocus()
   {
      int[] statM = {0,0,0};
      String d = "Enhance thy senses to better detect around thee what the eyes cannot see";
      return new Spell("Focus", "none", 100, 40, statM, 100, d);
   }

   public static Spell getEagleEye()
   {
      int[] statM = {0,0,0};
      String d = "Enhance thy senses and see thy map as an eagle at great heights";
      return new Spell("Eagle-eye", "none", 50, 100, statM, 600, d);
   }

   public static Spell getProtection()
   {
      int[] statM = {0,0,0};
      String d = "An armor from the ether shields thee, saising thy armor class";
      return new Spell("Protection", "none", 25, 40, statM, 50, d);
   }

   public static Spell getSpeed()
   {
      int[] statM = {0,0,5};
      String d = "Agility and control given by the spirits, aiding thy speed, thievery and ranged accuracy";
      return new Spell("Speed", "none", 25, 40, statM, 50, d);
   }
      
   public static Spell getStrength()
   {
      int[] statM = {5,0,0};
      String d = "Draw from the earth to boost thy might, supplementing thy melee skills or mining";
      return new Spell("Strength", "none", 25, 40, statM, 50, d);
   } 
   
   public static Spell getUnseen()
   {
      int[] statM = {0,0,5};
      String d = "Thou only seen as shadow while still";
      return new Spell("Unseen", "none", 25, 150, statM, 8000, d);
   }
   
   public static Spell getInvisibility()
   {
      int[] statM = {0,0,5};
      String d = "Make thee as to be completely unseen";
      return new Spell("Invisibility", "none", 25, 150, statM, 500, d);
   }

   public static Spell getAlphaMind()
   {
      int[] statM = {0,5,0};
      String d = "Pull from the ether to sharpen thy mind, aiding thy spellcraft";
      return new Spell("Alphamind", "none", 25, 150, statM, 500, d);
   }
   
   public static Spell getFireSkin()
   {
      int[] statM = {0,0,0};
      String d = "Make thy skin resistant to flame";
      return new Spell("Fireskin", "none", 25, 150, statM, 500, d);
   }

   public static Spell getFlight()
   {
      int[] statM = {0,0,0};
      String d = "Etheral wings pulls thee over the impassable, but for a shortened time";
      return new Spell("Flight", "none", 25, 200, statM, 800, d);
   }
   
   public static Spell getTimeStop()
   {
      int[] statM = {0,0,0};
      String d = "Halts the flow of time for all but thee for the durration of ten of thy steps";
      return new Spell("Timestop", "none", 10, 400, statM, 9999, d);
   }
   
   public static Spell getTempest()
   {
      int[] statM = {0,0,0};
      String d = "Summon a downpour of rain from the skies if it can be seen";
      return new Spell("Tempest", "none", -1, 80, statM, 9999, d);
   }
   
   public static Spell getFirestorm()
   {
      int[] statM = {0,0,0};
      String d = "A reign of fire falls upon all that surround thee";
      return new Spell("Firestorm", "none", 5, 200, statM, 9999, d);
   }
      
   public static Spell getRaiseEarth()
   {
      int[] statM = {0,0,0};
      String d = "Call the very water back into the ground, raising the very earth";
      return new Spell("Raise-earth", "none", -1, 400, statM, 5000, d);
   }
   
   public static Spell getRaiseWater()
   {
      int[] statM = {0,0,0};
      String d = "Summon water in the earth to the surface, dry to bog, bog to shallows, shallows to the deep";
      return new Spell("Raise-water", "none", -1, 400, statM, 5000, d);
   }
   
   public static Spell getRaiseDead()
   {
      int[] statM = {0,0,0};
      String d = "Bring the dead back to life as thy ally in battle";
      return new Spell("Raise-dead", "none", -1, 200, statM, 9999, d);
   }
   
   public static Spell getEnchantStone()
   {
      int[] statM = {0,0,0};
      String d = "Transform stone spire to magic monolith, to smite any who walk between";
      return new Spell("Enchant-stone", "none", -1, 400, statM, 1000, d);
   }

   public static Spell getTameAnimal()
   {
      int[] statM = {0,0,0};
      String d = "Forge alliance with natural creatures - fish, hare, swine, horse, elk, dog, wolf, bear and squid";
      return new Spell("Tame-animal", "none", -1, 200, statM, 9999, d);
   }
   
   public static Spell getRepel()
   {
      int[] statM = {0,0,0};
      String d = "Push and stun all away from thee with shocking force";
      return new Spell("Repel", "none", -1, 100, statM, 1000, d);
   }

   public static Spell getFlameBlast()
   {
      int[] statM = {0,0,0};
      String d = "Force and fire explodes from thy center, pushing thy enemys away with a terrible flame";
      return new Spell("Flameblast", "none", -1, 200, statM, 9999, d);
   }

   public static Spell getSpellWithName(String n)
   {
      n = n.trim();
      if(n.equalsIgnoreCase("repel"))
         return getRepel();
      if(n.equalsIgnoreCase("magicmist"))
         return getMagicMist();
      if(n.equalsIgnoreCase("flameblast"))
         return getFlameBlast();
      if(n.equalsIgnoreCase("shiftwind"))
         return getShiftWind();
      if(n.equalsIgnoreCase("teleport"))
         return getTeleport();
      if(n.equalsIgnoreCase("floretlade"))
         return getFloretLade();
      if(n.equalsIgnoreCase("disarm"))
         return getDisarm();
      if(n.equalsIgnoreCase("light"))
         return getLight();
      if(n.equalsIgnoreCase("charm"))
         return getCharm();
      if(n.equalsIgnoreCase("fear"))
         return getFear();
      if(n.equalsIgnoreCase("heal"))
         return getHeal();
      if(n.equalsIgnoreCase("cure"))
         return getCure();
      if(n.equalsIgnoreCase("restore"))
         return getRestore();
      if(n.equalsIgnoreCase("knowing"))
         return getKnowing();
      if(n.equalsIgnoreCase("focus"))
         return getFocus();
      if(n.equalsIgnoreCase("eagle-eye"))
         return getEagleEye();
      if(n.equalsIgnoreCase("protection"))
         return getProtection();
      if(n.equalsIgnoreCase("speed"))
         return getSpeed();
      if(n.equalsIgnoreCase("strength"))
         return getStrength();
      if(n.equalsIgnoreCase("unseen"))
         return getUnseen();
      if(n.equalsIgnoreCase("invisibility"))
         return getInvisibility();
      if(n.equalsIgnoreCase("alphamind"))
         return getAlphaMind();
      if(n.equalsIgnoreCase("fireskin"))
         return getFireSkin();
      if(n.equalsIgnoreCase("flight"))
         return getFlight();
      if(n.equalsIgnoreCase("tempest"))
         return getTempest(); 
      if(n.equalsIgnoreCase("firestorm"))
         return getFirestorm();
      if(n.equalsIgnoreCase("timestop"))
         return getTimeStop(); 
      if(n.equalsIgnoreCase("raise-earth"))
         return getRaiseEarth();  
      if(n.equalsIgnoreCase("raise-water"))
         return getRaiseWater(); 
      if(n.equalsIgnoreCase("raise-dead"))
         return getRaiseDead();
      if(n.equalsIgnoreCase("enchant-stone"))
         return getEnchantStone();
      if(n.equalsIgnoreCase("tame-animal"))
         return getTameAnimal();
      return null;
   }
  
   public static String getDescription(String itemName)
   {
      itemName = itemName.trim().toLowerCase();
      if(itemName.equalsIgnoreCase("repel"))
         return "Push and stun all away from thee";
      if(itemName.equalsIgnoreCase("magicmist"))
         return "Summon fog to evade thy pursuers";
      if(itemName.equalsIgnoreCase("flameblast"))
         return "Force and fire explodes from thy center";
      if(itemName.contains("shiftwind"))
         return "Alter the very wind's course";
      if(itemName.contains("teleport"))
         return "Change thy location in a blink";
      if(itemName.contains("floretlade"))
         return "Fill thy manna from magic woven florets";
      if(itemName.contains("disarm"))
         return "Undo that which is locked or set with trap";
      if(itemName.equals("light"))
         return "Make light where there is not";
      if(itemName.contains("charm"))
         return "Make thee more appealing to others";
      if(itemName.contains("fear"))
         return "Take on the appearance of a fearsome beast";
      if(itemName.contains("heal"))
         return "Seal thy flesh wounds";
      if(itemName.contains("cure"))
         return "Purge a poison that boils within";
      if(itemName.contains("restore"))
         return "Heal thy flesh and blood";
      if(itemName.contains("knowing"))
         return "Draw knowledge of others from the ether";
      if(itemName.contains("focus"))
         return "Sense around thee what the eyes cannot";
      if(itemName.contains("eagle-eye"))
         return "See thy map as an eagle at great heights"; 
      if(itemName.contains("protection"))
         return "An armor from the ether shields thee";    
      if(itemName.contains("speed"))
         return "Agility and control given by the spirits";
      if(itemName.contains("strength"))
         return "Draw from the earth to boost thy might";
      if(itemName.contains("unseen"))
         return "Thou only seen as shadow until you move";
      if(itemName.contains("invisibility"))
         return "Make thee as to be unseen";
      if(itemName.contains("alphamind"))
         return "Pull from the ether to sharpen thy mind";
      if(itemName.contains("fireskin"))
         return "Make thy skin resistant to flame";
      if(itemName.contains("flight"))
         return "Etheral wings pull you over the impassable";
      if(itemName.contains("tempest"))
         return "Summon a downpour of rain from the skies";
      if(itemName.contains("raise-earth"))
         return "Call the very water back into the ground";
      if(itemName.contains("raise-water"))
         return "Summon water in the earth to the surface";
      if(itemName.contains("raise-dead"))
         return "Bring the dead back to life as thy ally";
      if(itemName.contains("enchant-stone"))
         return "Transform stone spire to magic monolith";
      if(itemName.contains("tame-animal"))
         return "Forge alliance with natural creatures";
      if(itemName.contains("timestop"))
         return "Halts the flow of time for all but thee";
      if(itemName.contains("firestorm"))
         return "A reign of fire falls upon thy enemies";
      if(itemName.contains("fireshield"))
         return "Summon a wall of fire to block thy enemies";
      return "Unknown spell?";
   }
   
   //give the spell name, returns spell cast message
   public static String getSpellCast(String n)
   {
      n = n.trim();
      if(n.equalsIgnoreCase("repel"))
         return "Dag Mentar!";
      if(n.equalsIgnoreCase("flameblast"))
         return "In Flam Grav!";
      if(n.equalsIgnoreCase("shiftwind"))
         return "Rel Hur!";
      if(n.equalsIgnoreCase("teleport"))
         return "Sequitu!";
      if(n.equalsIgnoreCase("floretlade"))
         return "Vas Mani!";
      if(n.equalsIgnoreCase("disarm"))
         return "Appar Unem!";
      if(n.equalsIgnoreCase("light"))
         return "Lorum Luminae!";
      if(n.equalsIgnoreCase("charm"))
         return "An Xen Ex!";
      if(n.equalsIgnoreCase("fear"))
         return "In Quas Corp!";
      if(n.equalsIgnoreCase("heal"))
         return "Sanctu!";
      if(n.equalsIgnoreCase("cure"))
         return "Alcort!";
      if(n.equalsIgnoreCase("restore"))
         return "Sanctu Mani!";
      if(n.equalsIgnoreCase("knowing"))
         return "Wis Quas!";
      if(n.equalsIgnoreCase("focus"))
         return "Vieda!";
      if(n.equalsIgnoreCase("eagle-eye"))
         return "Vas Wis!";
      if(n.equalsIgnoreCase("protection"))
         return "Uus Sanct!";
      if(n.equalsIgnoreCase("speed"))
         return "Rel Tym!";
      if(n.equalsIgnoreCase("strength"))
         return "In Vas Por!";
      if(n.equalsIgnoreCase("unseen"))
         return "Sanct Lor!";
      if(n.equalsIgnoreCase("invisibility"))
         return "Sanct Lor Mas!";
      if(n.equalsIgnoreCase("alphamind"))
         return "In Vas Por!";
      if(n.equalsIgnoreCase("fireskin"))
         return "In Sanct Grav!";
      if(n.equalsIgnoreCase("flight"))
         return "Kal Ort Xen!";
      if(n.equalsIgnoreCase("tempest"))
         return "Vas An Flam!"; 
      if(n.equalsIgnoreCase("magicmist"))
         return "Vas An Hur!";
      if(n.equalsIgnoreCase("firestorm"))
         return "In Flam Hur!";
      if(n.equalsIgnoreCase("timestop"))
         return "Altair!"; 
      if(n.equalsIgnoreCase("raise-earth"))
         return "Vas Por Ylem!";  
      if(n.equalsIgnoreCase("raise-water"))
         return "Vas An Ylem!"; 
      if(n.equalsIgnoreCase("raise-dead"))
         return "In Mani Corp!";
      if(n.equalsIgnoreCase("enchant-stone"))
         return "Surmandum!";
      if(n.equalsIgnoreCase("tame-animal"))
         return "Kal Xen!";
      return "Klaatu Barada Nikto!";
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