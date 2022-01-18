//this assumes a LinkedList object and ListNode object set for an ambiguous data type for the ListNode value.
//returning an object from a LinkedList of type <anyType> will not require casting
   public interface ListInterface<anyType>
   {
      boolean add(anyType x);				//adds element x to the end of the list
      
      boolean add(int index, anyType x);	//adds element x at a particular index
      
      anyType remove(int index);			//removes and returns the element at a particular index
       
      int size();								//returns the number of elements in the list
       
      anyType set(int index, anyType x);	//changes the element at a specific index to x, returning the element that was originally there
       
      anyType get(int index);				//returns the object at a specific index (first element is index 0)
       
   }
