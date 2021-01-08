
public class LoopyMethods {
    public static void main(String[] args) {
        System.out.print("Expected: ");
        System.out.println("* * * * * *");
        System.out.print("  Actual: ");
        printStars(6);
        System.out.println();
        
        System.out.print("Expected: ");
        System.out.println("8 16 24 32 40 48 56 64 72 80 88 96");
        System.out.print("  Actual: ");
        printMults(8, 100);
        System.out.println();
        
        System.out.println("Expected: 4");
        System.out.println("  Actual: " + countEs("Elmer is an elephant."));
        System.out.println("Expected: 3");
        System.out.println("  Actual: " + countEs("Isn't Elastigirl incredible?"));  
    }

    public static void printStars(int n) {
        for(int i = 0; i < n; i++) {
            System.out.print("* ");
        }
    }
    
    public static void printMults(int num, int limit) {
        int result = num;
        int mult = 2;
        while(result <= limit) {
            System.out.print(result + " ");
            result = num * mult;
            mult++;
        }
    }

    public static int countEs(String sentence) {
        char char1 = 'e';
        char char2 = 'E';
        int count = 0;

        for(int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == char1 || sentence.charAt(i) == char2) {
                count ++;
            }
        }

        return count;
    }
}