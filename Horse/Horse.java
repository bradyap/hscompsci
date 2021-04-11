import java.util.Arrays;

public class Horse {
    private int loc, size, index;
    private char name;

    public Horse() {
        loc = 1;
        index = 0;
        size = 15;
    }

    public Horse(int inLoc, int inIndex, int inSize, char inName) {
        loc = inLoc;
        index = inIndex;
        size = inSize;
        name = inName;
    }
    
    public int getLoc() {
        return loc;
    }
    
    public int getIndex() {
        return index;
    }

    public char getName() {
        return name;
    }

    public void advance() {
        if (loc < size) {
            loc ++;
        }
    }

    public void raceStride() {
        if (((int)(Math.random()*100) + 1) < 50) {
            advance();
        }
    }

    public String toString() {
        char[] chars = new char[size];
        Arrays.fill(chars, '-');
        chars[(loc - 1)] = name;
        String str = new String(chars);
        String output = "|" + str + "|";
        return output;
    }
}
