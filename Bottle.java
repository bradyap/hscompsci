public class Bottle {

   private String color;
   private double vol;
   
   public Bottle() {
      vol = 8;
      color = "Clear";
      }
      
   public Bottle(String inColor, double inVol) {
      color = inColor;
      vol = inVol;
      }
      
   public String getColor() {
      return color.toLowerCase();
      }
      
   public double getVol() {
      return vol;
      }
      
   public String output(){
      return "The " + color.toLowerCase() + " bottle has a capacity of " + vol + " ounces.";
      }
      
   public static void main(String[] args) {
      Bottle bottleOne = new Bottle();
      Bottle bottleTwo = new Bottle("Orange", 999.223);
      
      double totalVol = bottleOne.getVol() + bottleTwo.getVol();
      System.out.println("The total volume of both bottles is " + totalVol + " ounces.");
      
      System.out.println(bottleOne.output());
      System.out.println(bottleTwo.output());
      
         
   }
}
      
     