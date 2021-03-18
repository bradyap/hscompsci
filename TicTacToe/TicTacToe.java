import java.util.Scanner;

public class TicTacToe {
    private static void printBoard(char[][] board) {
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
    }

    private static int getRow(Scanner input) {
        int row = 0;
        while(row < 1 || row > 3) {
            System.out.println("Please enter a row number (1 - 3):");
            row = input.nextInt();
        }
        return row - 1;
    }

    private static int getCol(Scanner input) {
        int col = 0;
        while(col < 1 || col > 3) {
            System.out.println("Please enter a column number (1 - 3):");
            col = input.nextInt();
        }
        return col - 1;
    }

    private static char check(char[][] board) {
        //check horizontal win conditions
        for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return board[i][0];
			}
        }

        //check vertical win conditions
		for(int i = 0; i < 3; i++) {
			if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
				return board[0][i];
			}
        }
        
        //check diagonal win conditions
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return board[0][0];
		}
		if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
			return board[2][0];
        }
        
        //return a dash if nobody has won yet
        return '-';
    }

    public static boolean tie(char[][] board) {
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
    }

	private static void game(Scanner input, String p1, String p2) {
        //makes board and fills it w/ -'s
        char[][] board = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
        }

        //player keeps track of who's turn it is, gameover is the while loop condition
        boolean player1 = true;
        boolean gameOver = false;

        while(!gameOver) {
            //prints game board
            printBoard(board);

            //make row/col ints and a char that is x or o depending on player
            int row = 0;
            int col = 0;
            char temp = 'x';
            if(!player1) {
			    temp = 'o';
			}

            //says who's turn it is and sets row and col equal to inputted info
            if(player1) {
				System.out.println(p1 + ", it is your turn. You are x's.");
            } 
            else {
				System.out.println(p2 + ", it is your turn. You are o's.");
            }
            while (true) {
                row = getRow(input);
                col = getCol(input);
                if(board[row][col] != '-') {
                    System.out.println("This space on the board is full. Please enter an empty space.");
                }
                else {
                    break;
                }
            }

            //sets inputted board location to temp char (char depends on player)
            board[row][col] = temp;

            //checks for winner, if no winner checks for tie, if no tie switches player and continues loop
            if(check(board) == 'x') {
				System.out.println(p1 + " is the winner!");
				gameOver = true;
            }
            else if (check(board) == 'o') {
				System.out.println(p2 + " is the winner!");
				gameOver = true;
            } 
            else if(tie(board)) {
				System.out.println("Game over. Tie!");
				gameOver = true;
            } 
            else {
				player1 = !player1;
			}
            }

            //shows board again
            printBoard(board);
        }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //users input names
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1, please enter your name:");
        String p1 = input.nextLine();
        System.out.println("Player 2, please enter your name:");
        String p2 = input.nextLine();

        String choice = "y";
        while (choice.equals("y")) {
            game(input, p1, p2);
            System.out.print("Would you like to play again? [y/n]");
            input.nextLine();
            choice = input.nextLine();
        }
        System.out.println("Thanks for playing!");
        input.close();
    }
}