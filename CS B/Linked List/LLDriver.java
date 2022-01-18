   //d oberle - 2014

   import java.io.*;
   import java.util.*;

   public class LLDriver
   {
   
      public static void main(String[] arg) throws Exception
      {
         Scanner input = new Scanner(System.in);
         LinkedList<String> list = new LinkedList();		//for a LindedList set up with an ambiguous data type
         String opt = "1";											//for options chosen
         String value;												//for inputing into the list
         while (!opt.equals("0"))
         {
            System.out.print("Here is your list:");
            System.out.println(list);  						//because toString is defined for the linked List
            System.out.println("Size: " + list.size());
            if(!list.isEmpty())
            {
               System.out.println("The first element is:" + list.getFirst());
               System.out.println("The last element is:" + list.getLast());
            }
            System.out.println("(1)to add in front");
            System.out.println("(2)to add in back");
            System.out.println("(3)to remove from front");
            System.out.println("(4)to remove from back");
            System.out.println("(5)to clear the list");
            System.out.println("(6)to add at specific index");
            System.out.println("(7)to remove at specific index");
            System.out.println("(8)to get at a specific index");
            System.out.println("(9)to change at a specific index");
            System.out.println("----------------");
            System.out.println("(0) to quit");
            opt = input.next();
            if (opt.equals("1"))
            {
               System.out.println("What do you want to add");
               value = input.next();
               list.addFirst(value);
            }
            else
               if (opt.equals("2"))
               {
                  System.out.println("What do you want to add");
                  value = input.next();
                  list.addLast(value);
               }
               else
                  if (opt.equals("3"))
                     list.removeFirst();
                  else
                     if (opt.equals("4"))
                        list.removeLast();
                     else
                        if (opt.equals("5"))
                        {
                           while(!list.isEmpty())
                              list.removeFirst();
                        }	
                        else
                           if (opt.equals("6"))
                           {
                              System.out.println("What do you want to add");
                              value = input.next();
                              System.out.println("At what index");
                              int index = input.nextInt();
                              list.add(index,value);
                           }
                           else
                              if (opt.equals("7"))
                              {
                                 System.out.println("At what index");
                                 int index = input.nextInt();
                                 String old = list.remove(index);
                                 System.out.println("You removed " + old);
                              }
                              else
                                 if (opt.equals("8"))
                                 {
                                    System.out.println("What index do you want to get");
                                    int index = input.nextInt();
                                    System.out.println("You got " + list.get(index));
                                 }			
                                 else
                                    if (opt.equals("9"))
                                    {
                                       System.out.println("What do you want to change to");
                                       value = input.next();
                                       System.out.println("At what index");
                                       int index = input.nextInt();
                                       String old = list.set(index,value);
                                       System.out.println("You changed " + old + " to " + value);
                                    }
                                    else
                                       if(!opt.equals("0"))
                                          System.out.println("That is not an option");	
         
         }
      
      }
   
   }