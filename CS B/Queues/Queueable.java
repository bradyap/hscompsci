    public interface Queueable <E> 
   {
   //postcondition: returns true if queue is empty;  otherwise, returns false
       boolean isEmpty(); 
   
   //precondition: queue is [a1, a2, ..., an]with n >= 0  
   //postcondition: queue is [a1, a2, ..., an, x]	   
       void add(E x); 
      
   //precondition: queue is [a1, a2, ..., an] with n >= 1    
   //postcondition: queue is [a2, ..., an]; returns a1 
   //throws an unchecked exception if the queue is empty
       E remove(); 
   
    //precondition: queue is [a1, a2, ..., an]with n >= 1
    //postcondition: returns a1.  
    //Throws an unchecked exception if the queue is empty
       E peek(); 
   
   }