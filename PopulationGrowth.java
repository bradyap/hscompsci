import java.util.Scanner;

public class PopulationGrowth {
    public static void main(String args[]) {

        //takes input from user
        Scanner input = new Scanner(System.in);
        System.out.println("Input initial number of organisms:");
        int initial = input.nextInt();
        System.out.println("Input rate of growth:");
        double rate = input.nextDouble();
        System.out.println("Input time in hours required to achieve the aforementioned rate of growth:");
        double rateTime = input.nextDouble();
        System.out.println("Input time in hours that the population will grow for:");
        double growthTime = input.nextDouble();
        input.close();

        //math to calculate total growth
        double temp = growthTime / rateTime;
        double result = initial * Math.pow(rate, temp);

        //returns result to user
        System.out.println("Total population:  " + result);

    }
}