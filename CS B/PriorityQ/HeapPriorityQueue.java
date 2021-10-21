//Rev Dr. D. R. Oberle  Feb 2015
import java.util.*;

public class HeapPriorityQueue implements PriorityQueue
{
   private static final int DFLT_CAPACITY = 1024;
   private Comparable [] items;		//index 0 will go unused
   private int numItems;            //keeps track of the number of logical elements stored in items array

   public HeapPriorityQueue(int initialCapacity)
   {
      items = new Comparable[initialCapacity + 1];
      numItems = 0;
   }

   public HeapPriorityQueue()
   {
      this(DFLT_CAPACITY);
   }

   //post: returns true if there are no logical elements stored
   public boolean isEmpty()
   {
      return (numItems == 0);
   }

   //post: returns the value of the element on the top of the heap (highest priority == low value)
   public Comparable peek()
   {
      if (numItems == 0)
      {
         throw new NoSuchElementException();
      }
   
      return items[1];
   }
   
   //      a new element has been added to the next available place in the items array
   //post: continually swaps the new element added to the end with the parent until items array is in heap order again
   private void reheapUp() {
      for (int i = numItems; i > 1 && higherPriority(items[i], items[i / 2]); i = i / 2) {
         swap(items, i, i / 2);
      }
   }

   //post:  adds object into the heap such that the heap order is maintained (highest priority == low value)
   public boolean add(Comparable obj)
   {
      numItems++;
      if (numItems >= items.length)
         doubleCapacity();
      items[numItems] = obj;
      reheapUp();
      return true;
   }
   
   //      the last element has alreay been copied into index 1
   //post: continually swaps the element that stats at index 1 with the higher priority of its possible kids until items array is in heap order
   private void reheapDown() {
      int i = 1; //start at index 1
      while (i * 2 <= numItems) { //while it has a child
         int j = i * 2; //index of child 
         if (i * 2 + 1 <= numItems) { //if right child exists
            if (higherPriority(items[i * 2 + 1], items[i * 2])) { //if right child is higher priority than left child, set child index accordingly
               j = i * 2 + 1;
            }
         }
         if (higherPriority(items[j], items[i])) { //swap if highest priority child is higher priority than current index
            swap(items, i, j);
            i = j; //set current index to new location of number
         } else {
            break;
         }
      }
   }

   //pre:  numItems > 0
   //post: removes and returns the element at the top of the heap such that the heap order is maintained (highest priority == low value)
   public Comparable remove()
   {
      if (numItems == 0)
      {
         throw new NoSuchElementException();
      }
   
      Comparable min = items[1];
      items[1] = items[numItems];
      numItems--;
      reheapDown();
      return min;
   }

   //pre: a and b are valid indexes of list
   //post:list[a] and list[b] swap values
   private static void swap(Comparable [] list, int a, int b)
   {
      Comparable temp = list[a];
      list[a] = list[b];
      list[b] = temp;
   }

   //pre: obj1 and obj2 != null
   //post:returns true if obj1 is lower priority than obj2 (highest priority == low value)
   private static boolean lowerPriority(Comparable obj1, Comparable obj2)
   {
      return (obj1.compareTo(obj2) > 0);
   }
   
   //pre: obj1 and obj2 != null
   //post:returns true if obj1 is higher priority than obj2 (highest priority == low value)
   private static boolean higherPriority(Comparable obj1, Comparable obj2)
   {
      return (obj1.compareTo(obj2) < 0);
   }

   public String toString()
   {
      String ans = "[";
      for (int i = 1; i <= numItems; i++)
      {
         ans += items[i];
         if(i <= numItems-1)
            ans += ", ";   
      }
      return ans+"]";
   }

   //post: doubles the buffer space in items
   private void doubleCapacity()
   {
      Comparable tempItems[] = new Comparable[2 * items.length - 1];
      for (int i = 0; i <= numItems; i++)
         tempItems[i] = items[i];
      items = tempItems;
   }
}

