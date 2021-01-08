import   java.util.Scanner;

public class PiEstimator {
    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of iterations used in this approximation.");
        int numDigits = input.nextInt();
        input.close();

        double pi = 0;
        double temp = 0;

        for(int i = 1; i < numDigits; i++) {
            temp = (i * 2) -1;
            if (i % 2 != 0) {
                pi = pi + (1 / temp);
            }
            else {
                pi = pi - (1 / temp);
            }
        }

        pi = pi * 4;

        System.out.println("Result: " + pi);
    }
}