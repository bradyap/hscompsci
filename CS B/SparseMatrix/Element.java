public class Element<anyType> {

    private anyType val;
    private int key;
    private int row;
    private int col;

    public Element(int key, anyType val, int row, int col) {
        this.key = key;
        this.val = val;
        this.row = row;
        this.col = col;
    }
    
    public int getKey() {
        return key;
    }
    
    public anyType getValue() {
        return val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
