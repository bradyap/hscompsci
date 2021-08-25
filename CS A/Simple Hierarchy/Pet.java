public class Pet {
   private String name;
   private int age;
   private double weight;

   public Pet(String n, int a, double w) {
      name = n;
      age = a;
      weight = w;
   }
   public String getName() {
      return name;
   }
   
   public void celebrateBirthday() {
      age++;
      System.out.println("Happy birthday to " + name + "!");
   }
   
   public void eat() {
      System.out.println(name + " is eating.");
   }
   
   public void play() {
      System.out.println(name + " is playing.");
   }

   public void sleep() {
      System.out.println(name + " is sleeping.");
   }

   //NOTE: getClass().getName() will return the class name that the object was instantiated as.
   public String toString() {
      return name + ", " + getClass().getName() + ", " + age + " years old, " + weight + " pounds";
   }
}