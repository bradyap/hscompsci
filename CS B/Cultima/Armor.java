import java.util.ArrayList;

public class Armor extends Item
{
   private int armorPoints;
   private int[] statModifier;
   
   //            name      armor  stat mod  value 
   public Armor(String n, int ap, int[]statM, int v)
   {
      super(n, v);
      armorPoints = ap;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
   }
   
   public Armor(String n, int ap, int[]statM, int v, String d)
   {
      super(n, v, d);
      armorPoints = ap;
      if(statM != null && statM.length == 3)
         statModifier = statM;
      else
         statModifier = new int[3];
   }

   public int armorPoints()
   {
      return armorPoints;
   }
   
   //[0]-might, [1]-mind, [2]-agility   
   public int[] statModifier()
   {
      return statModifier;
   }

   public String toString()
   {
      String ans="";
      ans += getName()+" "+armorPoints+" ";
      for(int n: statModifier)
         ans+= n+" ";
      ans+=getValue();
      return ans;
   
   }

   public static Armor getRandomArmor()
   {
      ArrayList<Armor> armors = new ArrayList<Armor>();
      armors.add(getLeather());   
      armors.add(getChainmail());     
      armors.add(getScalemail());     
      armors.add(getPlate()); 
      armors.add(getLeather());   
      armors.add(getChainmail());     
      armors.add(getScalemail());     
      armors.add(getPlate());
      armors.add(getExoticPlate());        
      armors.add(getBlessedArmor());     
      return armors.get((int)(Math.random()*armors.size()));
   }
   
   public static Armor getRandomArmorSimple()
   {
      ArrayList<Armor> armors = new ArrayList<Armor>();
      armors.add(getLeather());   
      armors.add(getChainmail());     
      armors.add(getScalemail());     
      armors.add(getPlate()); 
      armors.add(getWolfPelt());
      armors.add(getBearPelt());  
      armors.add(getElkPelt());    
      return armors.get((int)(Math.random()*armors.size()));
   }
 
   public static Armor getRandomArmorFull()
   {
      ArrayList<Armor> armors = new ArrayList<Armor>();
      armors.add(getLeather());   
      armors.add(getChainmail());     
      armors.add(getScalemail());     
      armors.add(getPlate()); 
      armors.add(getLeather());   
      armors.add(getChainmail());     
      armors.add(getScalemail());     
      armors.add(getPlate());
      armors.add(getExoticPlate());        
      armors.add(getBlessedArmor()); 
      armors.add(getDragonScale());
      armors.add(getDragonqueenScale());
      armors.add(getSeaserpentScale());
      armors.add(getWolfPelt());
      armors.add(getBearPelt());    
      armors.add(getElkPelt());
      armors.add(getMagesRobe()); 
      armors.add(getKnowingRobe());   
      armors.add(getHolocaustCloak()); 
      armors.add(getInvisibilityCloak());
      armors.add(getBumblePelt());
      return armors.get((int)(Math.random()*armors.size()));
   }

   public static Armor getRandomRobe()
   {
      ArrayList<Armor> armors = new ArrayList<Armor>();
      armors.add(getMagesRobe());  
      armors.add(getKnowingRobe());  
      armors.add(getHolocaustCloak()); 
      armors.add(getInvisibilityCloak());
      return armors.get((int)(Math.random()*armors.size()));
   }

   public static Armor getRandomPelt()
   {
      Armor [] pelts = {getWolfPelt(), getBearPelt(), getElkPelt()};
      return pelts[(int)(Math.random()*pelts.length)];
   }

   public static Armor getNone()
   {
      int[]statM = {0, 0, 0};
      return new Armor("none", 0, statM, 0);
   }

   public static Armor getHide()
   {
      int[]statM = {0, 0, 0};
      return new Armor("Hide", 0, statM, 0);
   }
   
   public static Armor getFur()
   {
      int[]statM = {0, 0, 1};
      return new Armor("Fur", 0, statM, 5);
   }
   
   public static Armor getWood()
   {
      int[]statM = {0, 0, 0};
      return new Armor("Wood", 2, statM, 0);
   }

   public static Armor getClothes()
   {
      int[]statM = {0, 0, 1};
      String d = "Comfortable cloth wares with no restriction of movement or weight but no protection";
      return new Armor("Clothes", 0, statM, 0, d);
   }

   public static Armor getLeather()
   {
      int[]statM = {0, 0, 0};
      String d = "Armor of flexible leather, very light and comfortable but little protection";
      return new Armor("Leather", 3, statM, 80, d);
   }
   
   public static Armor getChainmail()
   {
      int[]statM = {0, 0, -1};
      String d = "Armor made of chain links, light and of moderate protection";
      return new Armor("Chainmail", 5, statM, 120, d);
   }
   
   public static Armor getScalemail()
   {
      int[]statM = {0, 0, -2};
      String d = "Armor sewn with metal scales, balanced protection and weight";
      return new Armor("Scalemail", 7, statM, 180, d);
   }
   
   public static Armor getPlate()
   {
      int[]statM = {0, 0, -3};
      String d = "Heavy armor that offers unmatched protection but at the expense of some mobility";
      return new Armor("Plate", 9, statM, 200, d);
   }

   public static Armor getBlessedArmor()
   {
      int[]statM = {0, 0, -2};
      String d = "Armor sewn with metal scales of high quality, and blessed by the gods";
      return new Armor("Blessed-scale", 7, statM, 1000, d);
   }
   
   public static Armor getExoticPlate()
   {
      int[]statM = {0, 0, -1};
      String d = "Armor made from a strange, shiny metal - very strong for its light weight";
      return new Armor("Exotic-plate", 8, statM, 1000, d);
   }
   
   public static Armor getDragonScale()
   {
      int[]statM = {0, 0, 0};    //fire protection
      String d = "Light but strong armor made from the scales of a dragon that gives protection from flame";
      return new Armor("Dragon-scale", 7, statM, 2000, d);
   }
   
   public static Armor getDragonqueenScale()
   {
      int[]statM = {0, 0, 0};    //fire protection
      String d = "Light but strong armor made from the scales of the Dragon Queen that gives protection from flame";
      return new Armor("Dragonqueen-scale", 8, statM, 4000,d);
   }
   
   public static Armor getSeaserpentScale()
   {
      int[]statM = {0, 0, 0};    //cold protection
      String d = "Light but strong armor weaved with the scales of a seadragon that gives protection from cold";
      return new Armor("Seaserpent-scale", 7, statM, 1000, d);
   }
   
   public static Armor getWolfPelt()
   {
      int[]statM = {0, 0, 1};
      String d = "Soft wolf hair on a light hide protects thee from the cold, but not from arms";
      return new Armor("Wolf-pelt", 0, statM, 30, d);
   }
   
   public static Armor getBearPelt()
   {
      int[]statM = {0, 0, 1};
      String d = "Thick bear fur on a medium hide protects thee from the cold, but not much from arms";
      return new Armor("Bear-pelt", 1, statM, 90, d);
   }
    
   public static Armor getElkPelt()
   {
      int[]statM = {0, 0, 1};
      String d = "Coarse elk hair on a light hide protects thee from the cold, but not from arms";
      return new Armor("Elk-pelt", 0, statM, 30, d);
   }
   
   public static Armor getBumblePelt()
   {
      int[]statM = {1, 0, 1};
      String d = "Thick white fur on a tough hide, protects thee from the cold and makes thee feel light and powerful";
      return new Armor("Bumble-pelt", 4, statM, 3000, d);
   }
   
   public static Armor getMagesRobe()
   {
      int[]statM = {0, 2, 0};
      String d = "A beautiful jade hewn robe that supplements thy spellcraft";
      return new Armor("Mages-robe", 0, statM, 2000, d);
   }
   
   public static Armor getKnowingRobe()
   {
      int[]statM = {-15, 1, -15};
      String d = "A heavy and stiff ancient robe, enhances thy knowing and directs thee to mission targets, but weighs thee down terribly";
      return new Armor("Knowing-robe", 0, statM, 2000, d);
   }
   
   public static Armor getHolocaustCloak()
   {
      int[]statM = {-5, -5, -5};       //fire protection, fear
      String d = "A cloak cast in flame that will frighten commoners and resist fire but at the cost of diminished abilities";
      return new Armor("Holocaust-cloak", 0, statM, 6000, d);
   }

   public static Armor getInvisibilityCloak()
   {
      int[]statM = {-5, -5, -5};
      String d = "A dark cloak that makes thee unseen at the cost of pain and diminished abilities";
      return new Armor("Invisibility-cloak", 0, statM, 6000, d);
   }
      
   public static Armor getArmorWithName(String n)
   {
      if(n.equalsIgnoreCase("none"))
         return getNone();
      if(n.equalsIgnoreCase("clothes"))
         return getClothes(); 
      if(n.equalsIgnoreCase("leather"))
         return getLeather(); 
      if(n.equalsIgnoreCase("chainmail"))
         return getChainmail(); 
      if(n.equalsIgnoreCase("scalemail"))
         return getScalemail(); 
      if(n.equalsIgnoreCase("plate"))
         return getPlate(); 
      if(n.equalsIgnoreCase("blessed-scale"))
         return getBlessedArmor(); 
      if(n.equalsIgnoreCase("exotic-plate"))
         return getExoticPlate(); 
      if(n.equalsIgnoreCase("dragon-scale"))
         return getDragonScale();
      if(n.equalsIgnoreCase("dragonqueen-scale"))
         return getDragonqueenScale();
      if(n.equalsIgnoreCase("seaserpent-scale"))
         return getSeaserpentScale(); 
      if(n.equalsIgnoreCase("wolf-pelt"))
         return getWolfPelt();
      if(n.equalsIgnoreCase("bear-pelt"))
         return getBearPelt();
      if(n.equalsIgnoreCase("elk-pelt"))
         return getElkPelt();  
      if(n.equalsIgnoreCase("bumble-pelt"))
         return getBumblePelt(); 
      if(n.equalsIgnoreCase("mages-robe"))   
         return getMagesRobe(); 
      if(n.equalsIgnoreCase("knowing-robe"))   
         return getKnowingRobe(); 
      if(n.equalsIgnoreCase("holocaust-cloak"))
         return getHolocaustCloak();
      if(n.equalsIgnoreCase("invisibility-cloak"))
         return getInvisibilityCloak();
      return null;
   }
   
   //returns true if the armor is made of metal
   public boolean isMetal()
   {
      String n = this.getName();
      return (n.equalsIgnoreCase("chainmail") || n.equalsIgnoreCase("scalemail") || n.equalsIgnoreCase("plate")
         || n.equalsIgnoreCase("blessed-scale") || n.equalsIgnoreCase("exotic-plate"));
   }
   
   public boolean isFlamable()
   {
      String n = this.getName();
      return (n.equalsIgnoreCase("clothes") || n.contains("pelt") || n.equalsIgnoreCase("robe"));
   }
}