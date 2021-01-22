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

    private static int[] copyArray(int[] inputArr) {
        int arrLength = inputArr.length;
        int[] newArr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            newArr[i] = inputArr[i];
        }
        return newArr;
    }

    private static boolean compareArrays(int[] arrOne, int[] arrTwo) {
        if (arrOne.length != arrTwo.length) {
            return false;
        }
        else {
            int arrLength = arrOne.length;
            for (int i = 0; i < arrLength; i++) {
                if (arrOne[i] != arrTwo[i]) {
                    return false;
                }
            }
            return true;
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

    private static void reverse(int[] inputArr, int num) throws Exception {
        int revNum = num - 1;
        int arrLength = inputArr.length;
        if (revNum < arrLength && revNum >= 2) {
            for(int i = 0; i < revNum / 2; i++) {
                replace(inputArr, i, (revNum - i));
            }
        }
        else if (revNum == 1) {
            replace(inputArr, 0, 1);
        }
        else if (revNum >= arrLength) {
            throw new Exception("Please input a number that is between 2 and " + arrLength + ".");
        }
        else {
            throw new Exception("Something went wrong. Please try again.");
        }
    }

    private static void game(Scanner input) {
        System.out.println("Input desired array size:");
        int arrLength = input.nextInt();

        int[] nums = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            nums[i] = i + 1;
        }
        int[] ogArr = copyArray(nums);

        shuffle(nums);
        System.out.println("\nBelow is the shuffled array:");
        printArray(nums);
        System.out.println();

        int turns = 2 * arrLength - 3;

        while (true) {
            int reverseNum = 0;
            System.out.println("\nInput number of elements to reverse (2-" + arrLength + "):");
            while (true) {
                try {
                    reverseNum = input.nextInt();
                    reverse(nums, reverseNum);
                    turns--;
                    break;
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            System.out.println("\nBelow is the array with the first " + reverseNum + " elements reversed.");
            printArray(nums);

            if (compareArrays(nums, ogArr)) {
                System.out.println("\n\nCongrats, you sorted the array!");
                break;
            }
            else if (turns == 0) {
                System.out.println("\n\nSorry, you are out of turns. Game Over!");
                break;
            }
            else {
                System.out.println("\nYou have " + turns + " turns remaining.");
                continue;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            game(input);
            System.out.print("Would you like to play again? [y/n]");
            input.nextLine();
            String choice = input.nextLine();
            if (choice.equals("y")) {
                continue;
            }
            else {
                System.out.println("\nThanks for playing!");
                break;
            }
        }
        input.close();
    }
}
