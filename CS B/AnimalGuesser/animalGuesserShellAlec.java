
import java.io.*;
import java.util.*;

public class animalGuesserShellAlec {
    // pre: "fileName" is the name of a real file containing lines of text
    // post: returns the number of lines in fileName O(n)
    public static int getFileSize(String fileName) throws IOException {
        Scanner input = new Scanner(new FileReader(fileName));
        int size = 0;
        while (input.hasNextLine()) // while there is another line in the file
        {
            size++; // add to the size
            input.nextLine(); // go to the next line in the file
        }
        input.close(); // always close the files when you are done
        return size;
    }

    // pre: "fileName" is the name of a real file containing lines of text - the
    // first line intended to be unused
    // post:returns a String array of all the elements in <filename>.txt, with index
    // 0 unused (heap) O(n)
    public static String[] readFile(String fileName) throws IOException {
        int size = getFileSize(fileName); // holds the # of elements in the file
        String[] list = new String[size]; // a heap will not use index 0;
        Scanner input = new Scanner(new FileReader(fileName));
        int i = 0; // index for placement in the array
        String line;
        while (input.hasNextLine()) // while there is another line in the file
        {
            line = input.nextLine(); // read in the next Line in the file and store it in line
            list[i] = line; // add the line into the array
            i++; // advance the index of the array
        }
        input.close();
        return list;
    }

    // pre:
    // post:displays all of the elements of the array words O(n)
    public static void showArray(String[] words) {
        for (int i = 0; i < words.length; i++)
            System.out.println(words[i] + " ");
        System.out.println();
        System.out.println("Size of array:" + words.length);
    }

    // Post: puts all the elements in the array into <filename>.txt,
    // with one element per line O(n)
    public static void writeToFile(String[] array, String filename) throws IOException {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }

    // pre: let>='A' && let <='Z' OR let>='a' && let<='z'
    // post:returns true if let is a vowel O(1)
    public static boolean isVowel(char let) {
        return (let == 'a' || let == 'e' || let == 'i' || let == 'o' || let == 'u' || let == 'A' || let == 'E'
                || let == 'I' || let == 'O' || let == 'U');
    }

    // post: returns true if a user prompt is N, No, NO, n, nO or no O(1)
    public static boolean isNo(String ans) {
        return (ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n"));
    }

    // post: returns true if a user prompt is y, Y, Yes, yes, YES, yES, or yeS O(1)
    public static boolean isYes(String ans) {
        return (ans.toLowerCase().equals("yes") || ans.toLowerCase().equals("y"));
    }

    public static String[] checkDouble(String[] array) {
        int realElementValue = 0;
        for (int i = 0; i < array.length; i++) {
            if (!(array[i].equals("0")))
                realElementValue = i;
        }

        if (realElementValue * 2 + 1 > array.length)
        {
            String[] doubleList = new String[array.length * 2];

            for (int j = 0; j < array.length; j++)
            {
                doubleList[j] = array[j];

            }

            for (int k = array.length; k < doubleList.length; k++) {
                doubleList[k] = "0";
            }
            array = doubleList;

        }

        return array;

    }

    public static void main(String argv[]) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Animal Guesser!");
        System.out.println();

        int x = 0;

        boolean t = false;
        boolean f = false;

        String[] wordsArray = readFile("animal.txt");

        while (x == 0) {

            wordsArray = checkDouble(wordsArray);

//            System.out.println("Think of an animal and see if I can guess it!");
//            System.out.println();

            for (int i = 1; i < wordsArray.length;) {
                int redo = 0;
                while (redo == 0) {
                    redo = 1;
                    System.out.println(wordsArray[i]);
                    String ans = input.nextLine();

                    t = isYes(ans);
                    f = isNo(ans);

                    if (t == false && f == false) {
                        System.out.println(" Error! incorrect input: Please try again");
                        redo = 0;
                    }

                }

                if (t == true) {
                    if (wordsArray[i * 2 + 1].equals("0")) {
                        System.out.println("success");
                        break;
                    }
                    i = i * 2 + 1;
                }

                if (f == true) {

                    if (wordsArray[i * 2].equals("0")) {
                        System.out.println("You beat me! What is your animal?");
                        String animalAnswer = input.nextLine();
                        String tempAnimal = wordsArray[i].substring(wordsArray[i].indexOf("a"));


                        System.out.println(
                                "What is a question that is true for a " + animalAnswer + " and false for " + tempAnimal);
                        String question = input.nextLine();

                        wordsArray[i] = question;
                        wordsArray[i * 2] = "Is it " + tempAnimal;
                        wordsArray[i * 2 + 1] = "Is it " + animalAnswer + "?";

                        break;
                    }
                    i = i * 2;
                }

            }

            System.out.println("Press 0 to play again, any other number to quit");
            x = Integer.parseInt(input.nextLine());

        }

        System.out.println("Thanks for playing!");

        writeToFile(wordsArray, "animal.txt");

    }
}