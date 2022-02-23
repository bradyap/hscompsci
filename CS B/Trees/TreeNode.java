public class TreeNode
{
   private Comparable value;  //can be any type of object that implements Comparable
   private TreeNode left;     //points to left subtree
   private TreeNode right;    //points to right subtree
   
                              //constructors
   public TreeNode(Comparable v, TreeNode l, TreeNode r)
   { 
      value = v; 
      left = l; 
      right = r; 
   }
   
   public TreeNode(Comparable v)
   { 
      value = v; 
      left = null; 
      right = null; 
   }

   public Comparable getValue() //accessor methods
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }

                           //mutator methods
   public void setValue(Comparable v) 
   { 
      value = v;
   }
   
   public void setLeft(TreeNode l) 
   { 
      left = l; 
   }
   
   public void setRight(TreeNode r) 
   { 
      right = r; 
   }
}


