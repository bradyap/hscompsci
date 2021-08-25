public class Computer {
    public Computer() {}

    public int[] run(Board board) {
        //tries to win in one turn
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
                if(board.arr[i][j] == '\u0000') {
                    Board newBoard = new Board(board);
                    newBoard.arr[i][j] = 'o';
                    if(newBoard.check() == 'o') return out(i, j);
                }
		    }
        }

        //tries to prevent player from winning in one turn
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
                if(board.arr[i][j] == '\u0000') {
                    Board newBoard = new Board(board);
                    newBoard.arr[i][j] = 'x';
                    if(newBoard.check() == 'x') return out(i, j);
                }
		    }
        }

        //if opponent plays in corner, play in center
        if((board.arr[0][0] == 'x' || board.arr[0][2] == 'x' || board.arr[2][0] == 'x' || board.arr[2][2] == 'x') && board.arr[1][1] == '\u0000') return out(1, 1);

        //if opponent plays in center, play in corner
        if(board.arr[1][1] == 'x') {
            if(board.arr[0][0] == '\u0000') return out(0, 0);
            else if(board.arr[0][2] == '\u0000') return out(0, 2);
            else if(board.arr[2][0] == '\u0000') return out(2, 0);
            else if(board.arr[2][2] == '\u0000') return out(2, 2);
        }

        //if opponent plays edges, play center
        if((board.arr[0][1] == 'x' || board.arr[1][0] == 'x' || board.arr[1][2] == 'x' || board.arr[2][1] == 'x') && board.arr[1][1] == '\u0000') return out(1, 1);

        //plays first availible space
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
                if(board.arr[i][j] == '\u0000') return out(i, j);
		    }
        }

        //never runs
        return new int[2];
    }

    //makes outputs easier
    private int[] out(int x, int y) {
        int[] out = new int[2];
        out[0] = x;
        out[1] = y;
        return out;
    }
}