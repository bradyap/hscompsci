import java.util.Arrays;
public class GenAlgo
{
   public static int SAMPLE_SIZE = 10;
   public static int MUTATION_RATE = 5;
   public static double ALMOST_ZERO = 4.9E-323;

   public static double mutate(double x)
   {
      if(Math.random() < 0.5)
         x = x + (x * (Math.random()*MUTATION_RATE/100));
      else
         x = x - (x * (Math.random()*MUTATION_RATE/100));
      if (x < 0)
         x = 0;
      return x;
   }

   public static void main(String[]arg)
   {
      double [] nums = new double[SAMPLE_SIZE];
      int numGenerations = 0;
      nums[0] = Math.random()*100;
      while(nums[0] > ALMOST_ZERO)      
      {
         System.out.println(nums[0]);
         for(int i=1; i<nums.length; i++)
            nums[i] = mutate(nums[0]);
         Arrays.sort(nums);   
         numGenerations++;
      }
      System.out.println("Evolved after " + numGenerations + " generations.");
   
   }
}
