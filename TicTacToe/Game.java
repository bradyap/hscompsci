public class Game {
    private Board board;
    private char symbol;
    private String playerOne;
    private String playerTwo;

    public Game(String p1, String p2) {
        board = new Board();
        symbol = 'x';
        playerOne = p1;
        playerTwo = p2;
    }

    //player takes turn
    public char turn(int row, int col) {
        if(board.arr[row][col] == 0) {
            board.arr[row][col] = symbol;
            return board.check();
        } else return 'e';
    }

    public void switchPlayer() {
        if(symbol == 'x') symbol = 'o';
            else symbol = 'x';
    }

    public char getSymbol() {
        return symbol;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public Board getBoard() {
        return board;
    }

    public void printGame() {
        board.printBoard();
    }
}