public class test {
    public static void main(String[] args) {

    }
    //i was here via ssh

    /*public void findMax(int[][] ary) {
        int max = 0;
        
        for(int i = 0; i < ary.length; i++) {
            for(int j = 0; j < ary[0].length; j++) {
                if(ary[i][j] > max) {
                    max = ary[i][j];
                }
            }
        }

        
        
        for(int i = 0; i < ary.length; i++) {
            for(int j = 0; j < ary[0].length; j++) {
                if(ary[i][j] == max){
                    System.out.println(max + " was found in row " + (i + 1) + " and column " + (j + 1) + ".");
                }
            }
        } 
    }*/

    /*public static void change(int[][] matrix) {
        int[][] temp = matrix;
        for (int p=1; p < temp.length; p++) {
            for (int q=0; q < temp[0].length; q++) {
                int swap = temp[p][q];
                temp[p][q] = temp[p-1][q];
                temp[p-1][q] = swap;
            }
        }
    }*/

    /*public static boolean checkIndexes(double[][] data, int row, int col)
    {
    int numRows = data.length;
    if (row < numRows)
    {
        int numCols = data[0].length;
        return col < numCols;
    }
    else
        return false;
    }*/

    /*String[][] chess = new String[8][8];
        String myPiece = chess[5][6];*/

    /*public static void maxScorers(Student[] students) {
        int temp = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getScore() > temp) {
                temp = students[i].getScore()
            }
        }
        System.out.println("The max score of " + temp + " was achieved by: ");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getScore() == temp) {
                System.out.print(students[i].getName() + " ");
            }
        }
    }*/

    /*public static int findAverageAge (int[] nums) {
        int temp = 0;
        for (int x : nums) {
            temp += x;
        }
        return (temp / nums.length);
    }*/

    /*public static String capitalizeMe(String input) {
        int index = 0;
        StringBuilder temp = new StringBuilder();
        temp.append(input);
        while (index != -1) {
            index = temp.indexOf("me", index);
            if (index != -1) {
                temp.replace(index, index + 2, "ME");
            }
        }
        String output = temp.toString();
        return output;
    }*/

    /*public static String getALetter(String input) {
        int ran = (int)(Math.random()*input.length());
        String output = input.substring(ran, ran + 1);
        return output;
    }*/
}
