import java.util.Scanner;
import java.util.ArrayList;

public class SuperPrimes {
    /* COMPLETE THIS METHOD */
    // main method, use comments as a guide
    public static void main(String[] args) {             
        // tell the user what the program will do
        // prompt the user for the number of digits and read in response
        Scanner input = new Scanner(System.in);
        System.out.println("This program will display all superprime numbers of a certain length.");
        System.out.println("What length superprimes would you like to display?");
        int numDigits = Integer.parseInt(input.nextLine());
        input.close();

        // determine lower and upper bounds for looping
        // over numbers with that many digits
        int i = 0;
        while(countDigits(i) < numDigits) i++;
        int start = i;
        while(countDigits(i) == numDigits) i++;
        int stop = i - 1;

        // for each number, determine whether it is a superprime
        ArrayList<Integer> superprimes = new ArrayList<Integer>();
        for(int j = start; j <= stop; j++) {
            if(superPrime(j)) {
                superprimes.add(j);
            }
        }

        // and if so, output it to the screen.
        // if there are no superprimes, output "None"1
        if(superprimes.isEmpty()) {
            System.out.println("None.");
        } else {
            System.out.println("Superprimes:");
            for(int temp : superprimes) {
                System.out.println(temp);
            }
        }
    }

    /* COMPLETE THIS METHOD */
    // Recursive method.
    // return true if the provided number is superprime, false otherwise.
    public static boolean superPrime(int x) {
        if ((x < 10) && isPrime(x))
            return true;
        else if (isPrime(x))
            return superPrime(x / 10);  
        else
            return false;
    }

    /* COMPLETE THIS METHOD */
    // non-recursive method.
    // return true if the provided number is prime, false otherwise
    public static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++)
            if (num % i == 0)
                return false;
        return true;
    }

    public static int countDigits(int num) {
        return (int)Math.floor(Math.log10(num) + 1);
    }
}