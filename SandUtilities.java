import java.awt.Color;

public class SandUtilities
{
  //pre : c!= null
  //post: returns the inverted color from the one sent as c
   public static Color invert(Color c) {  
      return new Color(0xFFFFFF - c.getRGB());
   }
   
   //pre:   m!= null
   //post:  for each non-null element of m, changes it to its inverted color
   //       skips any color with the value skip1 and skip2, leaving them unchanged
   public static void invertColors(Color[][]m, Color skip1, Color skip2) {
      int n = m.length;
      
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (m[i][j] != null && m[i][j] != skip1 && m[i][j] != skip2) {
               m[i][j] = invert(m[i][j]);
            }
         }
      }
   }
   
   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  flips the array upside down
   public static void flipUpsideDown(Object[][]m) {
      int n = m.length;
      Object temp = new Object();

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n / 2; j++) {
            temp = m[n - j - 1][i];
            m[n - j - 1][i] = m[j][i];
            m[j][i] = temp;
         }
      }
   }
   
   public static void transpose(Object[][] m) {
      int n = m.length;
      Object temp = new Object();

      //https://en.wikipedia.org/wiki/In-place_matrix_transposition
      for (int i = 0; i < (n - 2); i++) {
         for (int j = (i + 1); j < (n - 1); j++) {
            temp = m[i][j];
            m[i][j] = m[j][i];
            m[j][i] = temp;
         }
      }
   }

   public static void reverse(Object[][] m ) {
      int n = m.length;
      Object temp = new Object();

      for(int j = 0; j < n; j++){
         for(int i = 0; i < n / 2; i++) {
            temp = m[j][i];
            m[j][i] = m[j][n - i - 1];
            m[j][n - i - 1] = temp;
         }
      }
   }

   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the left
   public static void rotateLeft(Object[][] m) {
      reverse(m);
      transpose(m);
   }

   //pre:   m is a square 2-D array (m.length==m[0].length)
   //post:  rotates the array 90 degrees to the right
   public static void rotateRight(Object[][] m) {
      transpose(m);
      reverse(m);
   }      
}