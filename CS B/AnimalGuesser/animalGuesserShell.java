import java.io.*;
import java.util.*;
//The Animal Guessing Program:	d oberle, 2006
//This game will attempt to guess an animal that the user is thinking of by asking yes/no questions.
//If the program does not know the animal, it will add it to its data base, making it a little smarter for the next user.
//Implement an array as a heap.  Each index has a parent at (index/2), a left child as (index*2) and a right child at index*2+1.
//The index path following a "no" response will go to the left child (left subtree).  
//The index path following a "yes" response will go to the right child (right subtree).

public class animalGuesserShell
{
      //pre:  "fileName" is the name of a real file containing lines of text
      //post: returns the number of lines in fileName O(n)
   public static int getFileSize(String fileName)throws IOException
   {
      Scanner input = new Scanner(new FileReader(fileName));
      int size=0;
      while (input.hasNextLine())				//while there is another line in the file
      {
         size++;										//add to the size
         input.nextLine();							//go to the next line in the file
      }
      input.close();									//always close the files when you are done
      return size;
   }
   
   	//pre:  "fileName" is the name of a real file containing lines of text - the first line intended to be unused
      //post:returns a String array of all the elements in <filename>.txt, with index 0 unused (heap) O(n)
   public static String[] readFile(String fileName)throws IOException
   {
      int size = getFileSize(fileName);		//holds the # of elements in the file
      String[] list = new String[size];		//a heap will not use index 0;
      Scanner input = new Scanner(new FileReader(fileName));
      int i=0;											//index for placement in the array
      String line;	
      while (input.hasNextLine())				//while there is another line in the file
      {
         line=input.nextLine();					//read in the next Line in the file and store it in line
         list[i]= line;								//add the line into the array
         i++;											//advance the index of the array         
      }
      input.close();	
      return list;					
   }
   
     //pre: 
     //post:displays all of the elements of the array words O(n)
   public static void showArray(String[] words)
   {
      for (int i=0; i<words.length; i++)
         System.out.println(words[i] + " ");
      System.out.println();
      System.out.println("Size of array:" + words.length);
   }
   
   //Post: puts all the elements in the array into <filename>.txt,
   //      with one element per line O(n)
   public static void writeToFile(String[] array, String filename) throws IOException
   {
      System.setOut(new PrintStream(new FileOutputStream(filename)));
      for(int i = 0; i < array.length; i++) 
         System.out.println(array[i]);
   }
   
   //pre: let>='A' && let <='Z'  OR    let>='a' && let<='z'
   //post:returns true if let is a vowel O(1)
   public static boolean isVowel(char let)
   {
      return (let=='a' || let=='e' || let=='i' || let=='o' || let=='u' || let=='A' || let=='E' || let=='I' || let=='O' || let=='U');
   }
   
    //post: returns true if a user prompt is N, No, NO, n, nO or no O(1)
   public static boolean isNo(String ans)
   {
      return (ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n"));
   }
   
      //post: returns true if a user prompt is y, Y, Yes, yes, YES, yES, or yeS O(1)
   public static boolean isYes(String ans)
   {
      return (ans.toLowerCase().equals("yes") || ans.toLowerCase().equals("y"));
   }

   public static String[] modify(String[] array) {
      int realElementValue = 0;
      for (int i = 0; i < array.length; i++) {
         if (!(array[i].equals("0"))) {
            if (i * 2 + 1 > array.length) {
               String[] doubleList = new String[array.length * 2];

               for (int j = 0; j < array.length; j++) {
                  doubleList[j] = array[j];

               }

               for (int k = array.length; k < doubleList.length; k++) {
                  doubleList[k] = "0";
               }
               array = doubleList;
            }
         }
      }
      return array;
   }
   
   public static void main(String argv[])throws IOException {
      Scanner input = new Scanner(System.in);
      String[] words = modify(readFile("animal.txt"));

      System.out.println("Welcome to Animal Guesser!\nThink of an animal - I will ask you a series of yes or no questions, lets see if I can guess the animal!");

      boolean playing = true;
      while (playing) {
         boolean t = false;
         boolean f = false;

         for (int i = 1; i < words.length;) {
            while (true) {
               System.out.print(words[i] + "\n");
               String in = input.nextLine();
               
               t = isYes(in);
               f = isNo(in);

               if (!t && !f) {
                  System.out.println("Invalid input. Please try again.");
                  continue;
               }

               break;
            }

            if (t) {
               if (words[i * 2 + 1].equals("0")) {
                  System.out.println("Animal guessed - sucks to suck!");
                  break;
               }
               i = i * 2 + 1;
            }

            if (f) {
               if (words[i * 2].equals("0")) {
                  System.out.println("You win! What was your animal?");

                  String animalIn = input.nextLine();
                  String otherAnimal = words[i].substring(words[i].indexOf("a"), words[i].length() - 1);

                  System.out.println("Please enter a question that is true for a(n) " + animalIn + " and false for " + otherAnimal + ".");
                  String question = input.nextLine();

                  words[i] = question;
                  words[i * 2] = "Is it a " + otherAnimal + "?";
                  words[i * 2 + 1] = "Is it a " + animalIn + "?";

                  break;
               }
               i = i * 2;
            }
         }

         System.out.println("Input \"y\" to play again, \"n\" to quit.");
         if (!input.nextLine().toLowerCase().equals("y")) {
            playing = false;
         }

      } 

      System.out.println("Thanks for playing Animal Guesser!");
      writeToFile(words, "animal.txt");
      input.close();

   }
}