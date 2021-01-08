import java.util.Scanner;

public class kineticEnergy {
   public static void main(String[] args) {
   
      double mass, velocity, ke, momentum;
      
      Scanner massscan = new Scanner(System.in);
      System.out.println("Please enter object mass.");
      mass = massscan.nextDouble();
      massscan.close();
      
      Scanner velocityscan = new Scanner(System.in);
      System.out.println("Please enter object velocity.");
      velocity = velocityscan.nextDouble();
      velocityscan.close();
      
      ke = 1.0 / 2.0 * mass * (Math.pow(velocity, 2));
      momentum = mass * velocity;
      
      System.out.println("Kinetic energy: " + ke + "\nMomentum: " +  momentum);
   }
}