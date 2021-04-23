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
        int start = (int)Math.pow(10, numDigits - 1);
        int stop = (int)Math.pow(10, numDigits);

        // for each number, determine whether it is a superprime
        ArrayList<Integer> superprimes = new ArrayList<Integer>();
        for(int j = start; j < stop; j++) {
            if(superPrime(j)) superprimes.add(j);
        }

        // and if so, output it to the screen.
        // if there are no superprimes, output "None"
        if(superprimes.isEmpty()) {
            System.out.println("None.");
        } else {
            System.out.println("Superprimes:");
            for(int temp : superprimes) System.out.println(temp);
        }
    }

    /* COMPLETE THIS METHOD */
    // Recursive method.
    // return true if the provided number is superprime, false otherwise.
    public static boolean superPrime(int x) {
        if(isPrime(x)) {
            if(x < 10) return true;
            return superPrime(x / 10);
        } else return false;
    }

    /* COMPLETE THIS METHOD */
    // non-recursive method.
    // return true if the provided number is prime, false otherwise
    // https://en.wikipedia.org/wiki/Primality_test interesting article on possible optimizations
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i = i + 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}