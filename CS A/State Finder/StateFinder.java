import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class StateFinder {
    public static void main(String[] args) throws IOException {
        //makes and fills state array from states file
        State[] states = new State[51];
        Scanner inFile = new Scanner(new FileReader("states.txt"));
        //fills array from file until file ends
        int i = 0;
        while(inFile.hasNextLine()) {
            String name = inFile.nextLine();
            String abbrev = inFile.nextLine();
            String capital = inFile.nextLine();
            states[i] = new State(abbrev, name, capital);
            i++;
        }
        inFile.close();
        //asks for and handles user input
        Scanner userIn = new Scanner(System.in);
        int j =  0;
        while(j != -1) {
            System.out.println("Enter 1 to search by state name\nEnter 2 to search by abbreviation\nEnter 3 to search by capital\nEnter -1 to quit");
            //input validation
            try {
                j = Integer.parseInt(userIn.nextLine());
            } 
            catch(Exception e) {
                System.out.println("\nPlease enter one of the number options.");
                continue;
            }
            if(j == -1) {
                break;
            }
            else if(j == 1) {
                System.out.println("Enter state name:");
                System.out.println("\n" + binSearch(states, userIn.nextLine()));
                j = 0;
            }
            else if(j == 2) {
                System.out.println("Enter state abbreviation:");
                System.out.println("\n" + linSearch(states, userIn.nextLine(), 0));
                j = 0;
            }
            else if(j == 3) {
                System.out.println("Enter state capital:");
                System.out.println("\n" + linSearch(states, userIn.nextLine(), 1));
                j = 0;
            }
            System.out.println("\nPlease enter one of the number options.");
        }
        userIn.close();
    }
    private static String binSearch(State[] states, String key) { 
        int i = 0;
        int len = states.length - 1; 
        while (i <= len) { 
            int mid = i + (len - i) / 2; 
            int out = key.compareToIgnoreCase(states[mid].getName()); 
            if (out == 0) {
                return states[mid].toString();
            }
            else if (out > 0) {
                i = mid + 1; 
            }
            else if (out < 0) {
                len = mid - 1;
            }   
        } 
        return "Sorry, state not found"; 
    }

    private static String linSearch(State[] states, String key, int opt) {
        if (opt == 0) {
            for(int i = 0; i < states.length; i++) {
                if(states[i].getAbbrev().equalsIgnoreCase(key)){
                    return states[i].toString();
                }
            }
        }
        if (opt == 1) {
            for(int i = 0; i < states.length; i++) {
                if(states[i].getCapital().equalsIgnoreCase(key)){
                    return states[i].toString();
                }
            }
        }
        return "Sorry, state not found"; 
    }
}