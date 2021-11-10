import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class MorseCodeDriver {
   public static void main(String[]arg)throws IOException {
      //scanner to take user input
      Scanner input = new Scanner(System.in);   
         
      //initialize maps for morsecode & plaintext
      HashMap<String, String> morseCode = new HashMap<String, String>();
      HashMap<String, String> plainText = new HashMap<String, String>();

      //read in morsecode & plaintext arrays
      for (int i = 0; i < MorseCode.english.length; i++) {
         morseCode.put(MorseCode.english[i], MorseCode.code[i]);
      }
      for (int i = 0; i < MorseCode.code.length; i++) {
         plainText.put(MorseCode.code[i], MorseCode.english[i]);
      }
      
      //user chooses to encode or decode
      System.out.println("Would you like to encode or decode? (e/d)");
      String direction = input.nextLine();
      while (!direction.equals("e") && !direction.equals("d")) {
         System.out.println("Invalid input. Please try again.");
         direction = input.nextLine();
      }

      //user chooses to read from file or use keyboard 
      System.out.println("Would you like to read from a file or use the keyboard? (f/k)");
      String inputType = input.nextLine();
      while (!inputType.equals("f") && !inputType.equals("k")) {
         System.out.println("Invalid input. Please try again.");
         inputType = input.nextLine();
      }

      input.close();
   }

   public static String encode(String input, HashMap<String, String> morsecode) {
      String output = "";      
      input = input.toUpperCase(); //hashmaps have letters in uppercase

      for (int i = 0; i < input.length(); i++) {
         char c = input.charAt(i); //get character from input
         output += morsecode.get(String.valueOf(c)) + " "; //append morse code for given character to output
      }

      return output;
   }

   public static String decode(String input, HashMap<String, String> english )
}
      
