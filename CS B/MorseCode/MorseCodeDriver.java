import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class MorseCodeDriver {
   public static void main(String[]arg)throws IOException {
      //scanner to take user input
      Scanner input = new Scanner(System.in);   
         
      //initialize maps for morsecode & plaintext
      HashMap<String, String> morseCode = new HashMap<String, String>();
      HashMap<String, String> plaintext = new HashMap<String, String>();

      //read in morsecode & plaintext arrays
      for (int i = 0; i < MorseCode.english.length; i++) {
         morseCode.put(MorseCode.english[i], MorseCode.code[i]);
      }
      for (int i = 0; i < MorseCode.code.length; i++) {
         plaintext.put(MorseCode.code[i], MorseCode.english[i]);
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

      //if user chooses to read from file
      if (inputType.equals("f")) {
         System.out.println("Please enter the file name:");
         String fileName = input.nextLine(); //get filename
         //read from file into string
         File file = new File(fileName);
         Scanner fileInput = new Scanner(file);
         while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            if (direction.equals("e")) { //if user wants to encode
               System.out.println(encode(line, morseCode));
            }
            else { //if user wants to decode
               System.out.println(decode(line, plaintext));
            }
         }
         fileInput.close(); //close file scanner
      }
      //if user choose to use from keyboard
      else {
         System.out.println("Please enter the text you would like to translate:");
         String line = input.nextLine(); //get text
         if (direction.equals("e")) { //if user wants to encode
            System.out.println(encode(line, morseCode));
         }
         else { //if user wants to decode
            System.out.println(decode(line, plaintext));
         }
      }

      input.close(); //close input scanner
   }


   public static String encode(String input, HashMap<String, String> morsecode) {
      String output = "";      

      input = input.toUpperCase(); //hashmaps have letters in uppercase
      char[] chars = input.toCharArray(); //convert string to char array

      for (char c : chars) { //for each char in character in input
         if (c == ' ') { //if character is a space
            output += "/ "; //add space to output
         } else { //if character is not a space
            output += morsecode.get(String.valueOf(c)); //add morsecode to output
            output += " "; //add space after morsecode
         }
      }

      return output.toLowerCase(); //return output in lowercase
   }

   public static String decode(String input, HashMap<String, String> plaintext) {
      String output = "";

      String[] chars = input.split(" "); //split input into morse code for plaintext characters

      for (String c : chars) { //for each char in character in input
         if (c.equals("/")) { //if character is a space
            output += " "; //add space to output
         } else { //if character is not a space
            output += plaintext.get(c); //add plaintext to output
         }
      }

      return output.toLowerCase(); //return output in lowercase
   }
}
