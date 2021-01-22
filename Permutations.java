import java.util.Scanner;

public class Permutations {
    private static void replace(int[] inputArr, int ogInt, int secondInt) {
        int temp = inputArr[ogInt];
        inputArr[ogInt] = inputArr[secondInt];
        inputArr[secondInt] = temp;
    }

    private static void printArray(int[] inputArr) {
        int arrLength = inputArr.length;
        for (int i = 0; i < arrLength; i++) {
            System.out.print(String.valueOf(inputArr[i]) + " ");
        }
    }

    public static void shuffle(int[] inputArr) {
        int arrLength = inputArr.length;
        for (int i = 0; i < arrLength; i++) {
            int randOne = (int)(Math.random() * arrLength);
            int randTwo = (int)(Math.random() * arrLength);
            replace(inputArr, randOne, randTwo);
        }
    }

    private static void reverse(int[] inputArr, int num) {

    }

    private static void game() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input desired array size:");
        int arrSize = input.nextInt();

        int[] nums = new int[arrSize];
        int arrLength = nums.length;
        for (int i = 0; i < arrLength; i++) {
            nums[i] = i + 1;
        }
        shuffle(nums);

        int turns = 2 * arrLength -3;
        System.out.println("Below is the shuffled array:");
        printArray(nums);

        System.out.println("Input number of elements to reverse (2-" + arrLength);
        int reverseNum = input.nextInt();

        input.close();
    }
    public static void main(String[] args) {
        game();
    }
}
