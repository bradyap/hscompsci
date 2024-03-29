import java.util.ArrayList;

public class SparseMatrix<anyType> implements Matrixable<anyType> {
   private ArrayList<Element<anyType>> matrix;
   private int numRows;
   private int numCols;

   public SparseMatrix(int r, int c) {
      matrix = new ArrayList<Element<anyType>>();
      this.numRows = r;
      this.numCols = c;
   }

   public anyType get(int r, int c) {
      if (matrix.size() != 0) { // if matrix is not empty
         int key = getKey(r, c); 
         for (int i = 0; i < matrix.size(); i++) { // loop through matrix
            if (matrix.get(i).getKey() == key) { // if key matches
               return matrix.get(i).getValue(); // return value
            }
         }
      }
      return null; // if matrix is empty or key is not found
   }

   public anyType set(int r, int c, anyType val) {
      int key = getKey(r, c);
      Element<anyType> element = new Element<anyType>(key, val, r, c); // create new element
      for (int i = 0; i < matrix.size(); i++) { // loop through matrix
         if (matrix.get(i).getKey() == key) { // if key matches
            Element<anyType> oldElement = matrix.set(i, element); // replace old element
            return oldElement.getValue(); // return old element
         }
      }
      matrix.add(element); // if key is not found, append element to end of matrix
      return null;
   }

   public void add(int r, int c, anyType val) {
      int key = getKey(r, c);
      Element<anyType> element = new Element<anyType>(key, val, r, c); // create new element
      for (int i = 0; i < matrix.size(); i++) { // loop through matrix
         if (matrix.get(i).getKey() > key) { // if key is greater than current key
            matrix.add(i, element); // add element
            break; 
         }
      }
      matrix.add(element); // add element to end
   }

   public anyType remove(int r, int c) {
      if (matrix.size() != 0) { // if matrix is not empty
         int key = getKey(r, c); 
         for (int i = 0; i < matrix.size(); i++) { // loop through matrix
            if (matrix.get(i).getKey() == key) {  
               return matrix.remove(i).getValue();
            }
         }
      }
      return null;
   }

   public int size() {
      return matrix.size();
   }

   public boolean isEmpty() {
      return matrix.size() == 0;
   }

   public int numRows() {
      return numRows;
   }

   public int numColumns() {
      return numCols;
   }

   public String toString() {
      String out = "";
      for (int r = 0; r < numRows; r++) {
         for (int c = 0; c < numCols; c++) {
            int key = getKey(r, c);

            anyType element = null;
            for (int i = 0; i < matrix.size(); i++) {
               if (matrix.get(i).getKey() == key) {
                  element = matrix.get(i).getValue();
                  break;
               }
            } if (element != null) {
               out += element + " ";
            } else {
               out += "- ";
            }            
         }
         out += "\n";
      }
      return out;
   }

   private int getKey(int r, int c) {
      return r * numCols + c;
   }
}