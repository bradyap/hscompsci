public class Board {
    public char[][] arr = new char[3][3];

    public Board() {
    }

    public char check() {
        //check horizontal win conditions
        for(int i = 0; i < 3; i++) {
			if(arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && arr[i][0] != '-') return arr[i][0];
        }

        //check vertical win conditions
		for(int i = 0; i < 3; i++) {
			if(arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && arr[0][i] != '-') return arr[0][i];
        }
        
        //check diagonal win conditions
		if(arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[0][0] != '-') return arr[0][0];
		if(arr[2][0] == arr[1][1] && arr[1][1] ==  arr[0][2] && arr[2][0] != '-') return arr[2][0];
        
        //check tie condition
        boolean tied = true;
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
                if(arr[i][j] == '-') tied = false;
		    }
        }
        if(tied) return 't';

        //return an empty char if nobody has won yet
        return ' ';
    }

    public void printBoard() {
        for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) System.out.print(arr[i][j] + " ");
			System.out.println();
		}
    }
}
