import java.util.Scanner;
import java.util.ArrayList;

public class PersonDriver {
    private static int askUser(Scanner input) {
        int choice = 0;
        while(choice > 5 || choice < 1) {
            System.out.println("MENU:");
            System.out.println("Enter 1 to add a person.\nEnter 2 to remove a person.\nEnter 3 to sort people by age and name.\nEnter 4 to display people.\nEnter 5 to exit.");
            choice = Integer.parseInt(input.nextLine());
        }
        return choice;
    }
    private static Person makePerson(Scanner input) {
        System.out.println("Enter age:");
        int age = Integer.parseInt(input.nextLine());
        System.out.println("Enter name:");
        String name = input.nextLine();
        System.out.println("Enter eye color:");
        String eyes = input.nextLine();
        return new Person(age, name, eyes);
    }
    private static void nameRemove(Scanner input, ArrayList<Person> people) {
        System.out.println("Enter name of person to remove.");
        String name = input.nextLine();
        for(int i = 0; i < people.size(); i++) {
            if(name.equals(people.get(i).getName())) {
                people.remove(i);
                System.out.println("Person named \"" + name + "\" removed.");
                return;
            }
        }
        System.out.println("Could not find person with the inputted name. No arraylist entries have been removed.");
    }
    private static void insertionSort(ArrayList<Person> people) {
        for(int i = 1; i < people.size(); i++) {
            Person key = people.get(i);
            int j = i - 1;
            while(j >= 0 && people.get(j).compareTo(key) > 0) {
                people.set((j + 1), people.get(j));
                j--; 
            }
            people.set((j + 1), key);
        }
    }
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();
        Scanner input = new Scanner(System.in);
        Boolean running = true;
        while(running == true)
            switch(askUser(input)) {
                case 1:
                    people.add(makePerson(input));
                    break; 
                case 2:
                    nameRemove(input, people);
                    break;
                case 3:
                    insertionSort(people);
                    break;
                case 4:
                    System.out.println(people);
                    break;
                case 5:
                    System.out.println("Exiting..");
                    running = false;
                    break;
            }
        input.close();
    }
}