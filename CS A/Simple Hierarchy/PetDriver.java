public class PetDriver
{
   public static void main(String[] args)
   {
      Cat oakey = new Cat("Oakey", 6, 18.5);
      Dog roxie = new Dog("Roxie", 12, 33.6);
      Hamster scooter = new Hamster("Scooter", 1, 0.3);
      Rabbit bigBunny = new Rabbit("Big Bunny", 9, 7.0);
      Pet thing = new Pet("Mystery Creature", 5, 10.0);
   
      System.out.println(oakey);
      System.out.println(roxie);
      System.out.println(scooter);
      System.out.println(bigBunny);
      System.out.println(thing);
   
      oakey.eat();
      roxie.dig();
      scooter.celebrateBirthday();
      bigBunny.chew();
      thing.play();
      oakey.scratch();
      roxie.eat();
      scooter.runOnWheel();
      bigBunny.thump();
      thing.sleep();
   }
}