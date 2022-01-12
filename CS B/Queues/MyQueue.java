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
         list.addLast(x);
      }
   
       public E remove()
      {
         return list.removeFirst();
      }
   
       public boolean isEmpty()
      {
         return list.isEmpty();
      }
   
       public E peek()
      {
         return list.getFirst();
      }
   
       public int size()
      {
         return list.size();
      }
   
       public String toString()
      {
         StringBuffer buffer = new StringBuffer();
         for (int i = 0; i < list.size(); i++) {
            buffer.append(list.get(i));
         }

         return buffer.toString();
      }
   
   }