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

    public char turn(int row, int col) {
        board.arr[row][col] = symbol;
        if(symbol == 'x') symbol = 'o';
        else symbol = 'x';
        return board.check();
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

    public void printGame() {
        board.printBoard();
    }
}