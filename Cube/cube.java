import java.util.Scanner;

public class cube {
   public static void main(String[] args) {
   
      int edgeLength, cubeArea;
   
      Scanner input = new Scanner(System.in);
      System.out.println("Enter edge length.");
      edgeLength = input.nextInt();
      input.close();
      cubeArea = 6 * (edgeLength * edgeLength);
      System.out.println("Cube Area: " + cubeArea);
   }
}