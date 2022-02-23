import java.io.*;

public class Tree {
   private TreeNode myRoot;
   
   public Tree() {
      myRoot = null;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:adds x to the tree such that the tree is still an in-order Binary Search Tree
   public void add(Comparable x) {

      myRoot = addHelper(myRoot, x);
   }
   
   private TreeNode addHelper(TreeNode root, Comparable x) {
      if (root == null) {
         return new TreeNode(x);
      }
      if (x.compareTo(root.myItem) < 0) {
         root.myLeft = addHelper(root.myLeft, x);
      }
      else if (x.compareTo(root.myItem) > 0) {
         root.myRight = addHelper(root.myRight, x);
      }
      return root;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:removes x from the tree such that the tree is still an in-order Binary Search Tree
   public void remove(Comparable x){
      myRoot = removeHelper(myRoot, x);
   }

   private TreeNode removeHelper(TreeNode root, Comparable x) {          
      if (root == null) {
         return null;
      }
      if (x.compareTo(root.myItem) < 0) {
         root.myLeft = removeHelper(root.myLeft, x);
      }
      else if (x.compareTo(root.myItem) > 0) {
         root.myRight = removeHelper(root.myRight, x);
      }
      else {
         if (root.myLeft == null) {
            return root.myRight;
         }
         else if (root.myRight == null) {
            return root.myLeft;
         }
         else {
            root.myItem = findMin(root.myRight).myItem;
            root.myRight = removeHelper(root.myRight, root.myItem);
         }
      }
      return root;
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in prefix order
   public void showPreOrder() {
      preOrderHelper(myRoot);
      System.out.println();
   }
   
   private void preOrderHelper(TreeNode root) {
      if (root != null) {
         System.out.print(root.myItem + " ");
         preOrderHelper(root.myLeft);
         preOrderHelper(root.myRight);
      }
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in infix order
   public void showInOrder() {
      inOrderHelper(myRoot);
      System.out.println();
   }
   
   private void inOrderHelper(TreeNode root) {
      if(root!=null) {
         inOrderHelper(root.getLeft());
         System.out.print(root.getValue() + " ");    
         inOrderHelper(root.getRight());
      }
   }
      
   //pre: root points to an in-order Binary Search Tree
   //post:shows the elements of the tree such that they are displayed in postfix order
   public void showPostOrder()
   {
      postOrderHelper(myRoot);
      System.out.println();
      
   }

   private void postOrderHelper(TreeNode root) {
      if (root != null) {
         postOrderHelper(root.myLeft);
         postOrderHelper(root.myRight);
         System.out.print(root.myItem + " ");
      }
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:returns whether or not x is found in the tree
   public boolean contains(Comparable x) {
      if (searchHelper(myRoot, x)==null)
         return false;
      return true;
   }
   
   private TreeNode searchHelper(TreeNode root, Comparable x) {
      if (root == null) {
         return null;
      }
      if (x.compareTo(root.myItem) < 0) {
         return searchHelper(root.myLeft, x);
      }
      else if (x.compareTo(root.myItem) > 0) {
         return searchHelper(root.myRight, x);
      }
      else {
         return root;
      }
   }
   
   //pre: root points to an in-order Binary Search Tree
   //post:returns a reference to the parent of the node that contains x, returns null if no such node exists
   //THIS WILL BE CALLED IN THE METHOD removeRecur
   private TreeNode searchParent(TreeNode root, Comparable x) { 
      return null;
   }
   
   //post: determines if root is a leaf or not O(1)
   private boolean isLeaf(TreeNode root) { 
      return (root.myLeft == null && root.myRight == null);
   }
      
   //post: returns true if only one child O(1)
   private boolean oneKid(TreeNode root) {
      return (root.myLeft != null && root.myRight == null) || (root.myLeft == null && root.myRight != null);
   }
      
   //pre: root points to an in-order Binary Search Tree
   //post:returns the number of nodes in the tree
   public int size() {
      return sizeHelper(myRoot);
   }
   
   private int sizeHelper(TreeNode root) {
      if (root == null) {
         return 0;
      }
      return 1 + sizeHelper(root.myLeft) + sizeHelper(root.myRight);
   }
         
   public int height() {
      return heightHelper(myRoot);
   }

   //pre: root points to an in-order Binary Search Tree
   //post:returns the height (depth) of the tree
   
   public int heightHelper(TreeNode root) {
      if (root == null) {
         return 0;
      }
      return 1 + Math.max(heightHelper(root.myLeft), heightHelper(root.myRight));
   }
   
   //EXTRA CREDIT
   //pre: root points to an in-order Binary Search Tree
   //post:returns true if p is an ancestor of c, false otherwise
   
   public boolean isAncestor(TreeNode root, Comparable p, Comparable c) {
      return false;
   }
   
   //EXTRA CREDIT
   //pre: root points to an in-order Binary Search Tree
   //post:shows all elements of the tree at a particular depth
   public void printLevel(TreeNode root, int level) {
      
   }

  //Nothing to see here...move along.
   private String temp;
   private void inOrderHelper2(TreeNode root)   {
      if(root!=null)
      {
         inOrderHelper2(root.getLeft());
         temp += (root.getValue() + ", "); 
         inOrderHelper2(root.getRight());
      }
   }

   public String toString() {
      temp="";
      inOrderHelper2(myRoot);
      if(temp.length() > 1)
         temp = temp.substring(0, temp.length()-2);
      return "[" + temp + "]";
   }
}