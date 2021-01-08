import java.util.Arrays;

public class Horse {
    private int loc;
    private int index;

    public Horse() {
        loc = 1;
        index = 0;
    }

    public Horse(int inLoc, int inIndex) {
        if (1 <= inLoc && inLoc <= 15) {
            loc = inLoc;
            index = inIndex;
        }
        else {
            System.out.println("Please try again. Make sure the input for location is between 1 and 15.");
        }
    }
    
    public int getLoc() {
        return loc;
    }

    public int getIndex() {
        return index;
    }

    public void advance() {
        if (loc < 15) {
            loc ++;
        }
    }

    public void raceStride() {
        if (((int)(Math.random()*100) + 1) < 50) {
            advance();
        }
    }

    public String toString() {
        char[] chars = new char[15];
        Arrays.fill(chars, '-');
        chars[(loc - 1)] = (char)(index + '0');
        String str = new String(chars);
        String output = "|" + str + "|";
        return output;
    }
}
