public interface ListInterface<anyType> {
   public boolean add(anyType x);				//adds element x to the end of the list

   public boolean add(int index, anyType x);	//adds element x at a particular index in list
    														   //elements to the right of that index are moved over one space to the right
   									
   public int size();								//returns the number of elements in the list
   
   public anyType set(int index, anyType x);	//changes the element at a specific index to x, returning the element that was originally there
   
   public anyType get(int index);				//returns the object at a specific index (first element is index 0)
   
   public anyType remove(int index);			// removes element from position index, moving
   															// elements at position index + 1 and higher to the
   															// left (subtracts 1 from their indices) and adjusts
   															// size;  returns the element formerly at index
}