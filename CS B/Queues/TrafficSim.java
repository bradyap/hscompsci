import java.io.*;
import java.util.*;

public class TrafficSim {
    public static void main(String[] args) throws IOException {
        String fName = "Traffic.txt";
        Scanner file = new Scanner(new File(fName));
        int mainTime = file.nextInt();
        double mainProbability = file.nextDouble() / 100;
        int mapleTime = file.nextInt();
        double mapleProbability = file.nextDouble() / 100;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of cycles: ");
        int cycles = in.nextInt();

        System.out.println("Sim 1");
        int counter = twoWaySim(cycles, mainTime, mainProbability, mapleTime, mapleProbability);
        System.out.println("Cars that got through: " + counter);

        System.out.println("Sim 2 w/ Optimization");

        int maxCounter = 0;
        int bestMainTime = mainTime;
        int bestMapleTime = mapleTime;

        for (int i = 0; i < 8; i++) {
            mainTime = 280 - i * 20;
            mapleTime = 120 + i * 20;
            counter = twoWaySim(cycles, mainTime, mainProbability, mapleTime, mapleProbability);

            if (counter > maxCounter) {
                maxCounter = counter;
                bestMainTime = mainTime;
                bestMapleTime = mapleTime;
            }

            System.out.println("Cars that got through: " + counter);
        }

        System.out.println("Optimal main timing: " + bestMainTime +  "\nOptimal maple timing: " + bestMapleTime + "\nMax counter: " + maxCounter);

        in.close();
    }



    private static int twoWaySim(int numCycles, int mainTime, double mainProbability, int mapleTime, double mapleProbability) {
        MyQueue<String> main = new MyQueue<String>();
        MyQueue<String> maple = new MyQueue<String>();

        System.out.println("Two way sim with main timing " + mainTime + " and maple timing " + mapleTime);        
        
        int counter = 0;
        for (int i = 0; i < numCycles; i++) {
            for (int j = 0; j < mainTime; j++) {
                System.out.println("(GREEN) Main Queue: " + main);
                System.out.println("(RED) Maple Queue: " + maple);
                if (Math.random() < mainProbability) {
                    main.add(randomLetter() + "");
                }
                if (Math.random() < mapleProbability) {
                    maple.add(randomLetter() + "");
                }
                if (!main.isEmpty()) {
                    main.remove();
                    counter++;
                }
            }

            for (int j = 0; j < mapleTime; j++) {
                System.out.println("(RED) Main Queue: " + main);
                System.out.println("(GREEN) Maple Queue: " + maple);
                if (Math.random() < mainProbability) {
                    main.add(randomLetter() + "");
                }
                if (Math.random() < mapleProbability) {
                    maple.add(randomLetter() + "");
                }
                if (!maple.isEmpty()) {
                    maple.remove();
                    counter++;
                }
            }
        }

        return counter;
    }

    private static String randomLetter() {
        int num = (int) (Math.random() * 26);

        if (Math.random() < .5) {
            return "" + (char) ('a' + num); 
        }

        return "" + (char) ('A' + num); 
    }
}
