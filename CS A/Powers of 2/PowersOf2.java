import java.util.Scanner;

public class PowersOf2 {
    public static void main(String args[]) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an exponent.");
        int num = input.nextInt();

        while (num != -1) {
            int ans = (int)Math.pow(2, num);
            System.out.println(ans);
            System.out.println("Enter an exponent.");
            num = input.nextInt();
        }

        input.close();
    }

}