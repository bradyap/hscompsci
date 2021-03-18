public class VideoGame implements Comparable
{
   // private data fields
   private String name;
   private Double price;
   private String platform;
   
   // constructor
   public VideoGame(String n, String pl, Double pr)
   {
      name = n;
      platform = pl;
      price = pr;
   }
   
   // accessor methods
   public String getName()
   {
      return name;
   }
   
   public double getPrice()
   {
      return price;
   }
   
   public String getPlatform()
   {
      return platform;
   }
    
   // toString method
   public String toString()
   {
      return "Name: " + name + ", Platform: " + platform + ", Price: $" + price;
   }
   
   // implementation of the compareTo method
   // returns a negative number if this object is "less than" the parameter
   // returns a positive number if this object is "greater than" the parameter
   // returns a 0 if this object is "equal to" the parameter
   public int compareTo(Object other)
   {
      VideoGame o = (VideoGame)other;
      int compared = name.compareToIgnoreCase(o.getName());
      int compared2 = platform.compareToIgnoreCase(o.getPlatform());
      
      if (compared == -1 || compared == 1)
      {
         return compared;
      }
      else
      {
         return compared2;
      }
   }
}
