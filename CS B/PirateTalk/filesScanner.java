   import java.io.*;
   import java.util.*;
 //this will show you functions to help read in text from a file and store it
 //into an array of Strings - d.oberle
    public class filesScanner
   {
      //pre:  "fileName" is the name of a real file containing lines of text
      //post: returns the number of lines in fileName
   
       public static int getFileSize(String fileName)throws IOException
      
      {
         Scanner input = new Scanner(new FileReader(fileName));
         int size=0;
         while (input.hasNextLine())	//while there is another line in the file
         {
            size++;							//add to the size
            input.nextLine();				//go to the next line in the file
         }
         input.close();					//always close the files when you are done
         return size;
      }
   
   	//pre:  "fileName" is the name of a real file containing lines of text, words is empty
      //post:fills words up with the data from every line from the file called "fileName"
   
       public static void readFile(String[] words, String fileName)throws IOException
      {
         Scanner input = new Scanner(new FileReader(fileName));
         int i=0;									//index for placement in the array
         String line;	
         while (input.hasNextLine())		//while there is another line in the file
         {
            line=input.nextLine();			//read in the next Line in the file and store it in line
            words[i]= line;					//add the line into the array
            i++;									//advance the index of the array         
         }
         input.close();						
      }
   
     //pre: 
     //post:displays all of the elements of the array words
   
       public static void showArray(String[] words)
      {
         for (int i=0; i<words.length; i++)
            System.out.println(words[i] + " ");
         System.out.println();
         System.out.println("Size of array:" + words.length);
      }
   
    
       public static void main(String argv[])throws IOException
      {
      	//make sure that a file exists called "testFile.txt" in the same directory as this program
      	//put a couple of lines of text into testFile.txt
         int size = getFileSize("testFile.TXT");	//holds the # of elements in the file
         String[] list = new String[size];			//array created to file specified size
         readFile(list, "testFile.TXT");				
         showArray(list);
      }
   }