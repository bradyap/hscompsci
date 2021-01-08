import java.util.Scanner;

public class TelephoneCall {
    public static void main(String args[]) {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter length of phone call in minutes:");
        int mins = input1.nextInt();
        input1.close();

        double price = 1.15;

        if (mins > 2) {
            double extra = (mins - 2);
            price = (1.15 + (extra * 0.50));
        }

        System.out.println("The total cost of the call is $" + price + ".");

    }

    
}