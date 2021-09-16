import java.util.Scanner;
import java.util.ArrayList;

public class BinaryUtils {
    public static void main(String[] args) {
        System.out.println("Pick an option:\n(1) Convert from decimal to binary.\n(2) Convert from binary to decimal.");
        Scanner input = new Scanner(System.in);
        int choice = Integer.parseInt(input.nextLine());
        while (true) {
            if (choice == 1) {
                System.out.println("Please enter a decimal number.");
                int num = Integer.parseInt(input.nextLine());
                System.out.println(toBinary(num));
                break;
            } else if (choice == 2) {
                System.out.println("Please enter a binary number.");
                int num = Integer.parseInt(input.nextLine());
                System.out.println(toDecimal(num));
                break;
            } else {
                System.out.println("Please choose a valid option.");
                choice = Integer.parseInt(input.nextLine());
            }
        }
        input.close();
    }

    public static int toDecimal(int input) {
        int binary = input;
        int out = 0;
        int n = 0;
        while (binary != 0) {
            int temp = binary % 10;
            out += temp * Math.pow(2, n);
            binary = binary / 10;
            n++;
        }
        return out;
    }

    public static int toBinary(int input) {
        ArrayList<Integer> binary = new ArrayList<Integer>();
        String out = "";
        while (input > 0) {
            binary.add(input % 2);
            input = input / 2;
        }
        for (int i = binary.size() - 1; i >= 0; i--) {
            out += binary.get(i).toString();
        }
        return Integer.parseInt(out);
    }
}

