import java.util.Scanner;

public class Lab62 {
    //Brady Pettengill
    
    private static void showArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                System.out.println(nums[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] even = new int[10];
        int[] odd = new int[10];
        int[] negative = new int[10];

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            int display = i + 1;
            System.out.print("#" + display + ": ");
            int in = input.nextInt();

            if (in < 0) {
                negative[i] = in;
            }
            if(in % 2 == 0) {
                even[i] = in;
            } 
            else {
                odd[i] = in;
            }
        }

        input.close();

        System.out.println("Even:");
        showArray(even);

        System.out.println("Odd:");
        showArray(odd);

        System.out.println("Negative:");
        showArray(negative);
    }
}
