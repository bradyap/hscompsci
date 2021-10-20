   //Rev Dr. D. R. Oberle  Feb 2015
   public class HeapTester
   {
      public static void main(String [] arg)
      {
         HeapPriorityQueue hpq = new HeapPriorityQueue();
         System.out.println(hpq);						//should be: []
         hpq.add(9);
         hpq.add(8);
         hpq.add(7);
         hpq.add(6);
         hpq.add(4);
         hpq.add(3);
         hpq.add(1);
         hpq.add(2);
         hpq.add(5);
         System.out.println(hpq);						//should be: [1, 2, 3, 5, 7, 8, 4, 9, 6]
         hpq.remove();
         System.out.println(hpq);						//should be: [2, 5, 3, 6, 7, 8, 4, 9]
         hpq.remove();
         System.out.println(hpq);						//should be: [3, 5, 4, 6, 7, 8, 9]
         hpq.remove();
         System.out.println(hpq);						//should be: [4, 5, 8, 6, 7, 9]
         hpq.remove();
         System.out.println(hpq);						//should be: [5, 6, 8, 9, 7]
         hpq.remove();
         System.out.println(hpq);						//should be: [6, 7, 8, 9]
         hpq.remove();
         System.out.println(hpq);						//should be: [7, 9, 8]
         hpq.remove();
         System.out.println(hpq);						//should be: [8, 9]
         hpq.remove();
         System.out.println(hpq);						//should be: [9]
         hpq.remove();
         System.out.println(hpq);						//should be: []
      }
   }