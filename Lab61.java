public class Lab61 {
    
    private static double getAvg(double[] nums) {
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        return sum / nums.length;
    }

    private static void printBig(double[] nums, double avg) {
        System.out.println("Values greater than average: ");
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > avg) {
                System.out.print(String.valueOf(nums[i]) + " ");
            }
        }
    }

    private static void printAll(double[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println("#" + i + ": " + String.valueOf(nums[i]));
        }
    }

    public static void main(String[] args) {
        double[] nums = new double[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.round((Math.random() * 100) * 100.0) / 100.0;
        }
        printAll(nums);
        double avg = getAvg(nums);
        System.out.println("\nAverage: " + String.valueOf(avg));
        printBig(nums, avg);
    }
}
