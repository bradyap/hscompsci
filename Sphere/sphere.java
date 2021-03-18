import java.util.Scanner;

public class sphere {
   public static void main(String[] args) {
      double radius, diameter, circumference, surfaceArea, volume;
      
      Scanner input = new Scanner(System.in);
      System.out.println("Input sphere radius.");
      radius = input.nextDouble();
      input.close();
      diameter = radius * 2;
      circumference = 2 * Math.PI * radius;
      surfaceArea = 4 * Math.PI * (Math.pow(radius, 2));
      volume = 4.0/3.0 * Math.PI * (Math.pow(radius, 3));
      System.out.println("Diameter: " + diameter + "\nCircumference: " + circumference + "\nSurface Area: " + surfaceArea + "\nVolume: " + volume);
   }
}