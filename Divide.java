import java.util.Scanner;

public class Divide {
   public static void main(String args[]) {
      Scanner num1 = new Scanner(System.in);
      System.out.println("Enter the first number...");
      int numOne = num1.nextInt();
      num1.close();
      
      Scanner num2 = new Scanner(System.in);
      System.out.println("Enter the second number...");
      int numTwo = num2.nextInt();
      num2.close();

      if (numOne > numTwo) {
         System.out.println((numOne / numTwo) + "r" + (numOne % numTwo));
      }
      else if (numTwo > numOne)  {
         System.out.println((numTwo / numOne) + "r" + (numTwo % numOne));
      }
      else if (numOne == numTwo) {
         System.out.println((numOne / numTwo) + "r" + (numOne % numTwo));
      }  
   
   }
   
}
