public class LinkedList<anyType> implements ListInterface<anyType> {
   private DListNode<anyType> head, tail; //represent head and tail of list
   private int size; //number of elements in the list

   public LinkedList() { //constructor
      head = null;
      tail = null;
      size = 0;
   }

   //post: returns true of the list is empty
   public boolean isEmpty() {
      return head == null;
   }

   //post: adds x to the front of the list
   public void addFirst(anyType x) {
      DListNode<anyType> temp = new DListNode<anyType>(x);

      if (head == null) {
         head = tail = temp; //both ends of list point to the node as it is the only node
         size++; //increment size
      } else {
         head.setPrev(temp); // prev node of old head points to new node
         temp.setNext(head); // next node of new node points to old head
         head = temp; // new node is now the 
         size++; //increment size
      }
   }

   //post: adds x to the end of the list, O(n)
   public void addLast(anyType x) {
      DListNode<anyType> temp = new DListNode<anyType>(x);

      if (head == null) {
         head = tail = temp; //both ends of list point to the node as it is the only node
         size++; //increment size
      } else {
         tail.setNext(temp); //set the next node of the tail to the new node
         temp.setPrev(tail); //set the prev node of the new node to the tail
         tail = temp; //set the tail to the new node
         size++; //increment size
      }
   }

   //pre: the head is not null
   //post: returns the head's value - returns null if the pre-condition fails,
   //O(1)
   public anyType getFirst() {
      return head.getValue();
   }

   //pre: the list is not empty
   //post: returns the lastNode's value - returns null if the pre-conditon fails
   public anyType getLast() {
      if (head == null) { //if list is empty
         return null;
      }
      return tail.getValue();
   }

   //pre: the head is not null
   //post: removes the first element from the list and returns its value
   //returns null if the pre-condition fails
   public anyType removeFirst() {
      if (head == null) { //if list is empty
         return null;
      }

      anyType temp = head.getValue(); //store the value of the head
      if (head == tail) { //if there is only one node in the list
         head = tail = null; //set head and tail to null
      } else {
         head = head.getNext(); //set head to the next node
         head.setPrev(null); //set the prev node of the new head to null
      }
      size--; //decrement size
      return temp;
   }

   //pre: the head is not null
   //post: removes the last element from the list and returns its value, O(n)
   //returns null if the pre-condition fails
   public anyType removeLast() {
      if (head == null) { //if list is empty
         return null;
      }

      anyType temp = tail.getValue(); //store the value of the tail
      if (head == tail) { //if there is only one node in the list
         head = tail = null; //set head and tail to null
      } else {
         tail = tail.getPrev(); //set tail to the prev node
         tail.setNext(null); //set the next node of the new tail to null
      }
      size--; //decrement size
      return temp;
   }

   //post: returns the number of elements
   public int size() {
      return size;
   }

   //pre: index >=0 and index < size()
   //post: returns the object at a specific index (first element is index 0)
   public anyType get(int index) {
      if (head == null) { //if list is empty
         return null;
      }

      if (index == 0) { //return first element, O(1) efficiency
         return head.getValue();
      }

      if (index == size - 1) { //get last element, O(1) efficiency
         return tail.getValue();
      }

      DListNode<anyType> curr = head; //create a temp node to traverse the list
      for (int i = 0; i < index; i++) { //traverse the list until the index is reached
         curr = curr.getNext();
      }
      return curr.getValue(); //return the value of the node at the index
   }

   //pre: index >=0 and index < size()
   //post: changes the element at a specific index to x, returning the element
   //that was originally there
   public anyType set(int index, anyType x) {
      if (head == null) { //if list is empty
         return null;
      }

      if (index == 0) { //set first element, O(1) efficiency
         anyType temp = head.getValue();
         head.setValue(x);
         return temp;
      }

      if (index == size - 1) { //set last element, O(1) efficiency
         anyType temp = tail.getValue();
         tail.setValue(x);
         return temp;
      }

      DListNode<anyType> curr = head; //create a temp node to traverse the list
      for (int i = 0; i < index; i++) { //traverse the list until the index is reached
         curr = curr.getNext();
      }
      anyType temp = curr.getValue(); //store the value of the node at the index
      curr.setValue(x); //set the value of the node at the index to x
      return temp;
   }

   //post: adds element x to the end of the list, returns true if successful
   public boolean add(anyType x) {
      addLast(x);
      return true;
   }

   //pre: index >=0 and index < size()
   //post: adds element x at index i, returns true if successful
   public boolean add(int index, anyType x) {
      if (head == null) { //if list is empty
         return false;
      }

      if (index == 0) { //add first element, O(1) efficiency
         addFirst(x);
         return true;
      }

      if (index == size - 1) { //add last element, O(1) efficiency
         addLast(x);
         return true;
      }

      DListNode<anyType> curr = head; //create a temp node to traverse the list
      for (int i = 0; i < index - 1; i++) { //traverse the list until the index before the target index is reached
         curr = curr.getNext();
      }

      DListNode<anyType> temp = new DListNode<anyType>(x); //create a new node to store the value of x
      temp.setNext(curr.getNext()); //set the next node of the new node to the next node of the node at the target index
      temp.setPrev(curr); //set the prev node of the new node to the node at the target index
      curr.setNext(temp); //set the next node of the node at the target index to the new node
      size++; //increment size
      return true;
   }

   //pre: index >=0 and index < size()
   //post: removes and returns the object at a specific index (first element is
   //index 0)
   public anyType remove(int index) {
      if (head == null) { //if list is empty
         return null;
      }

      if (index == 0) { //return first element, O(1) efficiency
         return removeFirst();
      }

      if (index == size - 1) { //get last element, O(1) efficiency
         return removeLast();
      }

      DListNode<anyType> curr = head; //create a temp node to traverse the list
      for (int i = 0; i < index; i++) { //traverse the list until the index is reached
         curr = curr.getNext();
      }

      anyType temp = curr.getValue(); //store the value of the node at the index
      curr.getPrev().setNext(curr.getNext()); //set the next node of the node before the target index to the next node of the target index
      curr.getNext().setPrev(curr.getPrev()); //set the prev node of the node after the target index to the prev node of the target index
      size--; //decrement size
      return temp;
   }

   //post: returns all elements of the list as a String
   //in the form [a0, a1, a2, . . . , an-1], O(n)
   public String toString() {
      String ans = "["; //start with left bookend
      DListNode<anyType> current = head;
      while (current != null) {
         ans += current.getValue().toString();
         current = current.getNext();
         if (current != null) { //don't add comma after the last element
            ans += ",";
         }
      }
      ans += "]"; //end with right bookend
      return ans;
   }
}