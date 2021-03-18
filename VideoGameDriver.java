import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
public class VideoGameDriver
{
   public static void main(String[] args) throws IOException
   {
      Scanner input = new Scanner(System.in);
      ArrayList <VideoGame> games = new ArrayList <VideoGame> ();
      int retry = 0;
      while(retry != 5)
      {
         retry = mainMenu(games);
      }
      
      System.out.println(" ");
      System.out.println("Goodbye!");
   
   }
   
   public static int mainMenu(ArrayList<VideoGame> games) throws IOException
   {
      Scanner input = new Scanner(System.in);
      
      System.out.println("Enter 1 to add a Video Game\nEnter 2 to remove a Video Game\nEnter 3 to sort games by name\nEnter 4 to display games\nEnter 5 to exit");
      int choice = Integer.parseInt(input.nextLine());
      
      if(choice == 1)
      {
         System.out.println("Enter the name of the Game: ");
         String gName = input.nextLine();
         System.out.println("Enter the platform of the Game: ");
         String gPlatform = input.next();
         System.out.println("Enter the price of the Game: ");
         Double gPrice = input.nextDouble();
         
         VideoGame in = new VideoGame(gName, gPlatform, gPrice);
         games.add(in);
         System.out.println(games);
         System.out.println("New game added.");
      }
      else if(choice == 2)
      {
         System.out.println("Enter the name of the game you want to remove: ");
         String nameIn = input.next();
         
         for(int i = 0; i < games.size(); i++)
         {
            if(nameIn.equals(games.get(i).getName()))
            {
               games.remove(i);
            }
         }
         
         System.out.println("Game removed.");
      }
      else if(choice == 3)
      {
         insertionSort(games);
         System.out.println("Games sorted by name.");
      }
      else if(choice == 4)
      {
         System.out.println(games);
      }
      
      return choice;
   }
   
   public static void insertionSort(ArrayList <VideoGame> listIn)
   {
      for(int i = 1; i < listIn.size(); i++)
      {
         VideoGame key = listIn.get(i);
         int j = i - 1;
         int compared = listIn.get(j).compareTo(key);
         
         while(j >= 0 && compared > 0)
         {
            listIn.set(j + 1, listIn.get(j));
            j--;
         }
         
         listIn.set(j + 1, key);
      }
   }
   
}
