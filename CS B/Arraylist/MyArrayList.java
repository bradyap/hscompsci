@SuppressWarnings("unchecked")
public class MyArrayList<anyType> implements ListInterface<anyType> {
   private Object[] list;  //stores actual elements
   private int numElements;   //keeps track of the number of valid elements in the list

   public MyArrayList() {
      list = new Object[10];  //start with a buffer size of 10
      numElements = 0;
   }

   private void doubleCapacity() {  //double list size, used when an element is added that would exceed the capacity of the list
      int newSize = list.length * 2;	//double the current size 
      Object[] newList = new Object[newSize];	//create new array 
      for (int i = 0; i < list.length; i++) {   //copies old old array into new array
         newList[i] = list[i];
      }
      list = newList;   //updates list variable with new array
   }

   private void cutCapacity() { //half list size, used when an element is removed and more than 2/3 capacity is available
      int newSize = list.length / 2;	//half the current size 
      Object[] newList = new Object[newSize];	//create new array 
      for (int i = 0; i < list.length; i++) {   //copies old old array into new array
         newList[i] = list[i];
      }
      list = newList;   //updates list variable with new array
   }

   public int size() {  //return number of valid elements in the list (size of "arraylist")
      return numElements;
   }

   public boolean add(anyType x) {  //add element to end of list 
      numElements++;
      if (numElements == list.length) {   //doubles list size if out of space
         doubleCapacity();
      }
      list[numElements - 1] = x;
      return true;
   }

   public boolean add(int index, anyType x) {   //add element to specified position in list 
      numElements++;  
      if (numElements == list.length) {   //doubles list size if out of space
         doubleCapacity();
      }
      //moves elements past the index position to the right 
      for (int i = numElements; i >= index; i--) {
         list[i] = list[i - 1];
      }
      list[index] = x;
      return true;
   }

   public anyType set(int index, anyType x) { //sets index to specified element, returns original element contents
      Object temp = list[index];
      list[index] = x;
      return (anyType)temp;
   }

   public anyType get(int index) {  //returns element at specified position in list
      return (anyType)list[index];
   }

   public anyType remove(int index) { //removes element at specified position in list
      if (list.length / numElements >= 3) {
         cutCapacity();
      }
      Object temp = list[index];
      for (int i = index; i < numElements; i++) { //shifts elements past index position to the left
         list[i] = list[i + 1];
      }
      numElements--;
      return (anyType)temp;
   }

   public String toString() { //returns a string representation of valid elements in the list
      String ans = "[";
      /*if (list[0] != "null") {
         ans += list[0];   //prints  first element
      }
      for (int i = 1; i < numElements; i++) {   //append comma separated elements to string (excludes first element for pretty printing)
         if (list[i] != "null") {
            ans += ", " + list[i];
         }
      }*/
      for (int i = 0; i < list.length; i++) {
         ans += ", " + list[i];
      }
      return ans + "]";
   }
}