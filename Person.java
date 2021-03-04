 public class Person implements Comparable
{
   // private data fields
   private int age;
   private String name;
   private String eyeColor;
   
   // constructor
   // parameters in order are: age, name, and eye color
   public Person(int a, String n, String ec)
   {
      age = a;
      name = n;
      eyeColor = ec;
   }
   
   // accessor methods
   public int getAge()
   {
      return age;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getEyeColor()
   {
      return eyeColor;
   }
   
   // toString method
   public String toString()
   {
      return age + " " + name + " " + eyeColor;
   }
   
   // implementation of the compareTo method
   // comparison is based first on age, and then on name
   // returns a negative number if this object is "less than" the parameter
   // returns a positive number if this object is "greater than" the parameter
   // returns a 0 if this object is "equal to" the parameter
   public int compareTo(Object other)
   {
      Person o = (Person)other;
      if (age < o.getAge())
         return -1;
      else if (age > o.getAge())
         return +1;
      else // age is the same, so secondarily compare names
         // just use the return value from Strings compareto method
         return name.compareTo(o.getName());
   }
}
