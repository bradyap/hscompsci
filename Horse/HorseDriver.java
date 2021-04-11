import java.util.Scanner;

public class HorseDriver {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Horse Race!");
        String choice = "y";
        while (choice.equals("y")) {
            race(input);
            System.out.print("Would you like to play again? [y/n]");
            choice = input.nextLine();
        }
        System.out.println("Thanks for playing!");
        input.close();
    }

    private static Boolean winCheck(Horse[] horses, int size, int number) {
        for(int i = 0; i < number; i++) {
            if(horses[i].getLoc() == size) {
                return false;
            }
        }
        return true;
    }

    private static void addHorse(Horse[] horses, int i, int size, char name) {
        if(Math.random() < 0.5) {
            horses[i] = new Filly(1, i, size, name);
        } else {
            horses[i] = new Horse(1, i, size, name);
        }
    }
    public static void race(Scanner input) {
        
        int number = 0;
        while(number == 0 || number > 36) {
            System.out.println("How many horses would you like to race? (1-36)");
            number = Integer.parseInt(input.nextLine());
        }
        Horse[] horses = new Horse[number];
        System.out.println("How long would you like the racetrack to be?");
        int size = Integer.parseInt(input.nextLine());
        for(int i = 0; i < Math.min(number, 11); i++) {
            addHorse(horses, i, size, (char)(i + '0'));
        }
        if(number > 10) {
            char alphabet = 'a';
            for(int i = 10; i < number; i++) {
                addHorse(horses, i, size, alphabet);
                alphabet ++;
            }
        }
        while (winCheck(horses, size, number)) {
            for(int i = 0; i < number; i++) {
                horses[i].raceStride();
            }
            for(int i = 0; i < number; i++) {
                System.out.println(horses[i].toString());
            }
        }
        System.out.println("The race is over! Winner(s):");
        for(int i = 0; i < number; i++) {
            if(horses[i].getLoc() == size) {
                System.out.println("Horse " + horses[i].getName());
            }
        }
    }
}
