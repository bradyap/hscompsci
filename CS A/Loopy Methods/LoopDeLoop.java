public class LoopDeLoop {
    public static void main(String[] args) {
        printStars(5);
        printStars(3);
        printStarsInSpace(5);
        printStarsInSpace(3);
        printStarsVictory(5);
        printStarsVictory(3);
    }

    public static void printStars(int n) {
        for (int i = 1; i <= n; i++) {
            int count = i;
            for (int j = 1; j <= count; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printStarsInSpace(int n) {
        for (int i = 1; i <= n; i++) {
            int count = i;
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= count; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printStarsVictory(int n) {
        for (int i = 1; i <= n; i++) {
            int count = i;
            for (int j = 1; j <= count; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2; j++) {
                for (int a = 1; a <= (n - i); a++) {
                    System.out.print(" ");
                }
            }
            for (int j = 1; j <= count; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}