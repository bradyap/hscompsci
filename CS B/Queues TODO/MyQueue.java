  //d oberle 2005
  
    public class MyQueue<E> implements Queueable<E>
   {
      private java.util.LinkedList<E> list;
   
       public MyQueue()
      {
         list = new java.util.LinkedList<E>();
      }
   
       public void add(E x)
      {

      }
   
       public E remove()
      {
         return null;
      }
   
       public boolean isEmpty()
      {
         return false;
      }
   
       public E peek()
      {
         return null;
      }
   
       public int size()
      {
         return -1;
      }
   
       public String toString()
      {
         return list.toString();
      }
   
   }