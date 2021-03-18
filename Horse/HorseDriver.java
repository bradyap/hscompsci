public class HorseDriver {
    
    public static void main(String[] args) {
        race();
    }
    
    public static void race() {
        Horse horse0 = new Horse(1, 0);
        Horse horse1 = new Horse(1, 1);
        Horse horse2 = new Horse(1, 2);
        while (horse0.getLoc() < 15 && horse1.getLoc() < 15 && horse2.getLoc() < 15) {
            horse0.raceStride();
            horse1.raceStride();
            horse2.raceStride();
            System.out.println(horse0.toString());
            System.out.println(horse1.toString());
            System.out.println(horse2.toString());
        }

        int i = 0;
        if (horse0.getLoc() == 15) {
            i += 1;
        }
        if (horse1.getLoc() == 15) {
            i += 2;
        }
        if (horse2.getLoc() == 15) {
            i += 4;
        }
        switch(i) {
            case 1:
                System.out.println("The race is over! Horse #0 takes home the trophy!");
                break; 
            case 2:
                System.out.println("The race is over! Horse #1 takes home the trophy!");
                break;
            case 3:
                System.out.println("The race is over! It's a tie! Horse #0 and Horse #1 are tied for the victory!");
                break;
            case 4:
                System.out.println("The race is over! Horse #2 takes home the trophy!");
                break;
            case 5:
                System.out.println("The race is over! It's a tie! Horse #0 and Horse #2 are tied for the victory!");
                break;
            case 6:
                System.out.println("The race is over! It's a tie! Horse #1 and Horse #2 are tied for the victory!");
                break;
            case 7:
                System.out.println("The race is over! It's a tie! Horse #0, Horse #1, and Horse #2 are all tied for the victory!");
                break;
        }
    }
}
