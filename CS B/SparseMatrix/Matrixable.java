public interface Matrixable<anyType> {
   public anyType get(int r, int c); // returns the element at row r, col c

   public anyType set(int r, int c, anyType x); // changes element at (r,c), returns old value

   public void add(int r, int c, anyType x); // adds obj at row r, col c

   public anyType remove(int r, int c);

   public int size(); // returns # actual elements stored

   public int numRows(); // returns # rows set in constructor

   public int numColumns(); // returns # cols set in constructor

   /*
    * public boolean contains(anyType x); //true if x exists in list public int[]
    * getLocation(anyType x); //returns location [r,c] of where x exists in list,
    * null otherwise public Object[][] toArray(); //returns equivalent structure in
    * 2-D array form public boolean isEmpty(); //returns true if there are no
    * actual elements stored public void clear(); //clears all elements out of the
    * list
    * 
    * public void setBlank(char blank); //allows the client to set the character
    * that a blank spot in the array is //represented by in String toString()
    */
}

