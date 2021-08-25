import java.util.Scanner;

public class TeacherPaySchedule {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        System.out.println("Input starting salary.");
        double salary = input.nextDouble();

        System.out.println("Input yearly percentage increase.");
        double percent = input.nextDouble();

        System.out.println("Input number of years.");
        double years = input.nextDouble();

        input.close();

        int i = 1;
        while(i <= years) {

            if(i <= 25) {
                salary = salary * ((percent/100) + 1);
            }

            System.out.println("Year " + i + " salary: is $" + salary);

            i++;

        }
    }
}