public class MyArrayListDriver
{
   public static void main(String[]args)
   {
      MyArrayList<String> words = new MyArrayList<String>();
      System.out.println(words);		//should show []
      words.add("A");
      words.add("B");
      words.add("C");
      System.out.println(words);		//should show [A, B, C]
      for(int i=0; i<10; i++)
         words.add(""+i);
      System.out.println(words);		//should show [A, B, C, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
      System.out.println(words.size());	//should show 13
      System.out.println(words.get(2));	//should show C  
      words.set(2, "Z");
      String temp = words.remove(0);
      System.out.println(temp);				//should show A
      words.remove(3);
      System.out.println(words);		//should show [B, Z, 0, 2, 3, 4, 5, 6, 7, 8, 9]
      words.add(2, "X");
      System.out.println(words);		//should show [B, Z, X, 0, 2, 3, 4, 5, 6, 7, 8, 9]
   }
}