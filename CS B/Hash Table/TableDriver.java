import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TableDriver {
    private static final String[] vowels = { "a", "e", "i", "o", "u" };
    private static final String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r",
            "s", "t", "v", "w", "x", "y", "z" };

    public static void generateData() {
        try {
            FileWriter writer = new FileWriter("hashData.txt");
            Random r = new Random();

            for (int i = 0; i <= 9999; i++) {
                int len = r.nextInt(3, 9);
                String word = "";
                Boolean vowel = false;

                for (int j = 0; j < len; j++) {
                    String letter = vowel ? vowels[r.nextInt(vowels.length)] : consonants[r.nextInt(consonants.length)];
                    word += letter;
                    vowel = !vowel;
                }

                String line = Integer.toString(i) + " " + word + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String filename, HashTable<String, Integer> table) {
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(" ");
                table.add(data[1], Integer.parseInt(data[0]));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to regenerate the data found in hashData.txt? (y/n)");
        String input = in.nextLine();
        if (input.equals("y")) {
            generateData(); // * generate new data to hashData.txt
        }

        HashTable<String, Integer> table = new HashTable<>();
        loadData("hashData.txt", table); // * load data from hashData.txt to table

        while (!input.equals("q")) {
            System.out.print("Enter a word to search for (press q to quit): ");
            input = in.nextLine();
            Integer res = table.get(input);
            String out = res == null ? "String not found."
                    : "Found at index " + res + " (see line " + (res + 1) + " in hashData.txt).";
            System.out.println(out);
        }
        in.close();
    }
}
