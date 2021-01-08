import java.util.Scanner;
public class LuckySevens
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      
      //************make d1 and d2 instances of Die objects
      Die d1 = new Die();
      Die d2 = new Die();
      //***************************************************/
      
      int dollars,count,maxDollars,countAtMax;
      
      System.out.println("How many dollars do you have? ");
      dollars = input.nextInt();
      input.close();
      
      maxDollars = dollars;
      countAtMax = 0;
      count = 0;
      
      while (dollars > 0)
      {
         count++;
         
         //********call the roll method for your Die objects
         d1.roll();
         d2.roll();
         //************************************************/
         
         //*******call the getSide method for your Die objects 
         if (d1.getSide() + d2.getSide() == 7)
         //**************************************************/
            dollars += 5;
         else
            dollars -= 1;
            
         if (dollars > maxDollars)
         {
            maxDollars = dollars;
            countAtMax = count;
         }
      }
      System.out.println("You are broke after "+count+" rolls.\nYou should have quit after "+countAtMax+" rolls when you had $"+maxDollars+".");
   }
      
}