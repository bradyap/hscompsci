   import java.util.*;
   import java.io.*;

    public class stackTest
   {
   //pre:  word is not a reference to the null String
   //post: returns a Stack of single character Strings comprised of the characters in word
       public static MyStack<String> toStack(String word)
      {
         MyStack<String> ans = new MyStack();  
         for(int i=0; i<word.length(); i++)
            ans.push("" + word.charAt(i));
         return ans;
      }
   
       public static void main(String args[])
      {
         MyStack<String> wordStack = new MyStack();
         wordStack = toStack("Hello");
         while (!wordStack.isEmpty())
            System.out.println("Removing " + wordStack.pop() + " off of the top of the stack");
      
         MyStack<String> books = new MyStack();
         books.push("War & Peace");
         books.push("C++ for U++");
         books.push("Emma");
         books.push("Godel, Escher, Bach");
                   
         System.out.println();	
         System.out.println("The book on the top of the stack is " + books.peek());
         while (!books.isEmpty())
            System.out.println("Removing " + books.pop() + " off of the top of the stack");
      
      }    
   }