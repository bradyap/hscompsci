import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        //users input names
        System.out.println("Welcome to Tic Tac Toe!\nPlayer 1, please enter your name:");
        String playerOne = input.nextLine();
        System.out.println("Player 2, please enter your name:");
        String playerTwo = input.nextLine();

        String choice = "y";
        while (choice.equals("y")) {
            Game game = new Game(playerOne, playerTwo);
            Gui gui = new Gui(game);
            while(gui.visible) Thread.sleep(1000);
            System.out.print("Would you like to play again? [y/n]");
            input.nextLine();
            choice = input.nextLine();
        }
        System.out.println("Thanks for playing!");
        input.close();
    }
}
