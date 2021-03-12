public class Person implements Comparable<Person> {
   private int age;
   private String name;
   private String eyeColor;
   
   public Person(int inAge, String inName, String inEyeColor) {
      age = inAge;
      name = inName;
      eyeColor = inEyeColor;
   }
   public int getAge() {
      return age;
   }
   public String getName() {
      return name;
   }
   public String getEyeColor() {
      return eyeColor;
   }
   public String toString() {
      return "| Age: " + age + ", Name: " + name + ", Eye color: " + eyeColor;
   }
   public int compareTo(Person input) {
      if (age < input.getAge()) {
         return -1;
      } 
      else if (age > input.getAge()) {
         return +1;
      }
      else {
         return name.compareTo(input.getName());
      }
   }
}
