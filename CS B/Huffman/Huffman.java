import java.util.Scanner;
import java.io.*;

public class Huffman {

   public static void main(String[] args) throws IOException {
      //* ask for name of scheme/message
      Scanner stdin = new Scanner(System.in);
      System.out.print("Please enter the scheme/message name: ");
      String fileName = stdin.nextLine();
      stdin.close();

      //* construct filenames for message and scheme files
      File messageFile = new File("message." + fileName + ".txt");
      File schemeFile = new File("scheme." + fileName + ".txt");

      //* load message into a string
      Scanner messageScanner = new Scanner(messageFile);
      String message = messageScanner.nextLine();
      messageScanner.close();

      //* pass scanner into tree method
      Scanner schemeScanner = new Scanner(schemeFile);
      TreeNode root = huffmanTree(schemeScanner);
      schemeScanner.close();

      //* decode message given message and root of tree
      System.out.println(decode(message, root));
   }

   public static TreeNode huffmanTree(Scanner schemeScan) {
      TreeNode root = new TreeNode(null);
      
      while (schemeScan.hasNextLine()) {
         String line = schemeScan.nextLine();

         //* split line into letter and code
         String letter = line.substring(0, 1);
         String code = line.substring(1);

         TreeNode curr = root;
         for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') { //? if 0, go left
               if (curr.getLeft() == null) { //? if null, create a node
                  curr.setLeft(new TreeNode(null));
               }
               curr = curr.getLeft();
            } else { //? if 1, go right
               if (curr.getRight() == null) { //? if null, create a node
                  curr.setRight(new TreeNode(null));
               }
               curr = curr.getRight();
            }
         }

         curr.setValue(letter); //* set value of leaf node to letter associated with code
      }

      return root;
   }

   public static String decode(String text, TreeNode root) {
      String decoded = "";

      TreeNode curr = root;
      for (int i = 0; i < text.length(); i++) {
         if (text.charAt(i) == '0') { //? if 0, go left
            curr = curr.getLeft();
         } else { //? if 1, go right
            curr = curr.getRight();
         }
         if (curr.getValue() != null) { //? if leaf node value, append to decoded string
            decoded += curr.getValue();
            curr = root;
         }
      }

      return decoded;
   }
}
