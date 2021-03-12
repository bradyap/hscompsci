import java.util.Scanner;
import java.util.ArrayList;

public class Lab73 {
    public static void main(String[] args) {
        //creates arraylists
        ArrayList<Integer> even = new ArrayList<Integer>();
        ArrayList<Integer> odd = new ArrayList<Integer>();
        ArrayList<Integer> negative = new ArrayList<Integer>();
        //scans for input and fills arraylists accordingly
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int display = i + 1;
            System.out.print("#" + display + ": ");
            int in = input.nextInt();
            if (in < 0) {
                negative.add(in);
            }
            else if(in % 2 == 0) {
                even.add(in);
            }
            else {
                odd.add(in);
            }
        }
        input.close();
        //prints arraylists
        System.out.println("\nEven:\n" + even + "\nOdd:\n" + odd + "\nNegative:\n" + negative);
    }
}