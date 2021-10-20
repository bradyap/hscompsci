   //Rev Dr. D. R. Oberle  Feb 2015
   public interface PriorityQueue
   {
   /**
   *   postcondition: returns true if the number of
   *   elements in the priority queue is 0; otherwise,
   *   returns false   */
      boolean isEmpty(); 
   
   /**
   *   postcondition: x has been added to the priority
   *   queue; the number of elements in the priority
   *   queue is increased by 1 */
      boolean add(Comparable x); 
   
   /*
   *   postcondition: The smallest item in the priority
   *   queue is removed and returned; the number
   *   of elements in the priority queue is decreased
   *   by 1.   *   throws unchecked exception if priority queue is
   *   empty
   */
      Comparable remove(); 
    
   /**
   *    postcondition: The smallest item in the priority 
   *    queue is returned; the priority queue is
   *    unchanged
   *    throws unchecked exception if priority queue is
   *    empty
   */  
      Comparable peek();	 
   
   } 

