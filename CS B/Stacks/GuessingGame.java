import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class GuessingGame {
    public static MyStack<String> toStack(String word) {
        MyStack<String> ans = new MyStack();
        for (int i = 0; i < word.length(); i++)
            ans.push("" + word.charAt(i));
        return ans;
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack();

        Scanner in = new Scanner(System.in);
        String word = "";

        System.out.println("Please enter a word: ");
        word = in.nextLine().toLowerCase();

        stack = toStack(word);
        int counter = 1;
        int score = 0;

        printWord(word, counter);
        stack.pop(); 

        while (!stack.isEmpty()) {
            System.out.println("Guess a letter:");
            String letter = in.nextLine().toLowerCase();

            if (stack.peek().equals(letter)) {
                System.out.println("Right! You've gained " + counter + " points.\n");
                score += counter;
                counter++;
            } else {
                System.out.println("Wrong! You've lost " + (word.length() - counter) + " points.\n");
                score -= (word.length() - counter);
                counter++;
            }

            printWord(word, counter);
            stack.pop();
            System.out.println("Score: " + score);
        }

        System.out.println("Final score: " + score);
        in.close();
    }

    private static void printWord(String word, int counter) {
        System.out.print("Current word is: ");

        for (int i = 0; i < word.length() - counter; i++) {
            System.out.print("_ ");
        }

        for (int i = word.length() - counter; i < word.length(); i++) {
            System.out.print(word.charAt(i) + " ");
        }

        System.out.println();
    }   

}