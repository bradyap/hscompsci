import java.util.ArrayList;
import java.util.Scanner;

public class RecursivePermutations {
    public static void main(String[] args) { //* method to test alg
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String in = input.nextLine();
        System.out.println(getPermutations(in));
        input.close();
    }

    public static ArrayList<String> getPermutations(String input) { //* recursively get permutations from input
        ArrayList<String> perms = new ArrayList<String>(); //* array to be returned
        if (input.length() == 1) { //* terminating case
            perms.add(input);
        } else { //* recursive case
            //? input = "abc" --> curr = "a", remaining = "bc"
            for (int i = 0; i < input.length(); i++) {  
                String curr = input.substring(i, i + 1);
                String remaining = input.substring(0, i) + input.substring(i + 1);
                //* creates arraylist full of remaing permutations
                ArrayList<String> remainingPerms = getPermutations(remaining);
                for (String temp : remainingPerms) {
                    //? adds curr = "a" + temp = "bc", "cb" to perms arraylist
                    perms.add(curr + temp);
                }
            }
        }
        return perms; //* returns arraylist of permutations
    }
}