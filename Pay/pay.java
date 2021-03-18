import java.util.Scanner;

public class pay {
   public static void main(String[] args) {
   
      double wage, reg, ovr, xtra, pay;
   
      Scanner hrwage = new Scanner(System.in);
      System.out.println("Please enter hourly wage.");
      wage = hrwage.nextDouble();
      hrwage.close();
      
      Scanner regu = new Scanner(System.in);
      System.out.println("Please enter regular hours worked.");
      reg = regu.nextDouble();
      regu.close();
      
      Scanner overt = new Scanner(System.in);
      System.out.println("Please enter overtime hours worked.");
      ovr = overt.nextDouble();
      overt.close();
      
      xtra = wage * 1.5;
      pay = (reg * wage) + (ovr * xtra);
      
      System.out.println("Total pay: " + pay);
      
   }
   
}