public class Recursion {
    // main method for testing
    // actual and expected output for each test should match
    // Do not change the tests.
    // Write the methods sumDigits and printWithCommas methods
    // so that they produce the expected output.
    public static void main(String[] args) {
        System.out.println("sumDigits Tests");
        System.out.println("expected: 6");
        System.out.println("  actual: " + sumDigits(6));

        System.out.println("expected: 12");
        System.out.println("  actual: " + sumDigits(7032));

        System.out.println("expected: 15");
        System.out.println("  actual: " + sumDigits(43215));
        System.out.println();

        System.out.println("printWithCommas Tests");
        System.out.println("expected: 16");
        System.out.print("  actual: ");
        printWithCommas(16);            
        System.out.println();

        System.out.println("expected: 1,234,567");
        System.out.print("  actual: ");
        printWithCommas(1234567);            
        System.out.println();

        System.out.println("expected: 12,033,004");
        System.out.print("  actual: ");
        printWithCommas(12033004);            
        System.out.println();
    }

    /* COMPLETE THIS METHOD */
    // Recursive method.
    // returns the sum of all the digits in the provided number.
    // Must be recursive and written without loops or String variables
    public static long sumDigits(long num) {
        if(num == 0) return 0;
        return(num % 10 + sumDigits(num / 10));
    }

    /* COMPLETE THIS METHOD */
    // Recursive method.
    // Accepts a long and prints it out with commas in appropriate positions.
    // Must be recursive and written without loops or String variables
    // ( Do not convert num to a String and insert commas!!! )
    public static void printWithCommas(long num) {
        if (num >= 1000) {
            printWithCommas(num / 1000);
            System.out.print(",");
            if(num % 1000 < 100) System.out.print("0");
            if(num % 1000 < 10) System.out.print("0");
            System.out.print(num % 1000);
        } else System.out.print(num);
    }
}