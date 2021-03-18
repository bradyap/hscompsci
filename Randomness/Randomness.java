public class Randomness
{
   public static void main(String[] args)
   {
      // This is the original string
      String randomString = "Will it work?";
      //print out the original string
      System.out.println("original: " + randomString);

      //call the randomStar method
      String newString = randomStar(randomString);
      
      //print out the modified string
      System.out.println("modified: " + newString);
      
      //print out the location of the "*" in the modified string
      int location = newString.indexOf("*");
      System.out.println("star is at location " + location);

   }
   
      
   //randomStar method
   // Write the randomStar method here (be sure to make it static)
   // It should accept a String as input and produce a String for output
   // The new String should NOT be printed in this method.
   // The method will generate a random number.
   // The number generated should be a valid index in the input String
   // The produced String should match the input with one exception:
   // The character at the random index should be a *.
   // start by writing the method Header here
   
   public static String randomStar(String input) {
      // generate an appropriate random number here. What should the bounds be?
      int random = (int)(Math.random()*input.length());
      
      // print out the random number in the proper format
      // (random is: n)
      System.out.println("random is: " + Integer.toString(random));
      
      // print out a line indicating what is changing in the proper format
      // (changing "a" to "*")
      System.out.println("changing \"" + input.charAt(random) + "\" to \"*\""); 
      
      // build the new String here
      String modifiedString = input.substring(0,random) + "*" + input.substring(random + 1);
      
      // send the new String back to the caller
      return String.valueOf(modifiedString);
      
      }
      
   
}