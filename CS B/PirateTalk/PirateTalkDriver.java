import java.io.*;
import java.util.*;
 
public class PirateTalkDriver
{

   public static Scanner input = new Scanner(System.in);

   public static void main(String[]arg)throws IOException
   {
      PirateTalk translator = new PirateTalk();
      translator.initialize();
      System.out.println("Enter a sentence, scurvy dog!");
      String sentence = input.nextLine();
      double ran = Math.random();
      if(ran<.33)
         System.out.print("Aaaarrr, ");
      else
         if(ran<.66)
            System.out.print("Haarrr, ");
         else
            System.out.print("Yaaarg, ");  
      String[] words = sentence.split(" ");			//an array of each element from the sentence, separated by a space
      for(int i=0; i<words.length; i++)
         System.out.print(translator.getWord(words[i])+ " ");
      System.out.println("!!!");	
      
   	//******TO TEST THE flip() METHOD*********//
      System.out.println(translator.flip());
   }

}