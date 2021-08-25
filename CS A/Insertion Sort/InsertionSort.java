import java.util.Scanner;

public class InsertionSort {
    private static void printArray(Person[] people) {
        for(int i = 0; i < people.length; i++) {
            System.out.println(people[i]);
        }
    }
    private static void insertionSort(Person[]people) {
        for(int i = 1; i < people.length; i++) {
            Person key = people[i];
            int j = i - 1;
            while(j >= 0 && people[j].compareTo(key) > 0) {
                people[j + 1] = people[j];
                j--; 
            }
            people[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        //asks for num. of people and makes array
        int num = 0;
        while(num < 3 || num > 10) {
            System.out.println("Enter the number of people (3-10) in your list:");
            num = Integer.parseInt(userIn.nextLine());
        }
        Person[] people = new Person[num];
        //user fills array
        for(int i = 0; i < people.length; i++) {
            System.out.println("Person " + (i + 1) + ":");
            System.out.println("Enter age:");
            int age = Integer.parseInt(userIn.nextLine());
            System.out.println("Enter name:");
            String name = userIn.nextLine();
            System.out.println("Enter eye color:");
            String eyes = userIn.nextLine();
            people[i] = new Person(age, name, eyes);
        }
        //prints original array
        System.out.println("\nOriginal array:");
        printArray(people);
        //sorts
        insertionSort(people);
        //prints sorted array
        System.out.println("\nSorted array:");
        printArray(people);
        userIn.close();
    }
}