import java.util.Scanner;

public class RightTriangle {
   public static void main(String args[]) {
      Scanner side1 = new Scanner(System.in);
      System.out.println("Enter length of side one...");
      double sideOne = side1.nextInt();
      side1.close();
      
      Scanner side2 = new Scanner(System.in);
      System.out.println("Enter length of side two...");
      double sideTwo = side2.nextInt();
      side2.close();

      Scanner side3 = new Scanner(System.in);
      System.out.println("Enter length of side three...");
      double sideThree = side3.nextInt();
      side3.close();
      
      double sideOneSq = Math.pow(sideOne, 2);
      double sideTwoSq = Math.pow(sideTwo, 2);
      double sideThreeSq = Math.pow(sideThree, 2);
      
      if (sideOneSq == (sideTwoSq + sideThreeSq)) {
         System.out.println("This triangle is a right triangle.");
      }
         else if (sideTwoSq == (sideOneSq + sideThreeSq)) {
            System.out.println("This triangle is a right triangle.");
         }
         else if (sideThreeSq == (sideOneSq + sideTwoSq)) {
            System.out.println("This triangle is a right triangle.");
         }
         else {
            System.out.println("This triangle is not a right triangle.");
         }


   }
 
}