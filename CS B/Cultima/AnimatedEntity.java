import javax.swing.ImageIcon;
import java.util.ArrayList;
public class AnimatedEntity
{
   private String name;
	
   private ArrayList<ImageIcon>[] pictures;	   //List of images for the animation frames    
   private ArrayList<String>[] fileNames;
   private int animationIndex;  	            //this is the index for the pictures List (which animation frame we are on)
	
   public static final byte UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, DEAD = 4;
   public byte direction;                   //the direction the player is facing, and index of the 2-D array
     
	//pre: 	image.size() > 0 and contain String values of image file names
	//			ad >= 1
	//ARGS:  name, image List, animation delay
   public AnimatedEntity(String n, ArrayList<String>[] images)
   {
      name = n;
      fileNames = images;
      pictures = new ArrayList[5];  //UP, RIGHT, DOWN, LEFT, DEAD
      if(images!=null)
         for(int dir = 0; dir < pictures.length; dir++)
         {
            pictures[dir]=new ArrayList();
            for(String fileName: images[dir])
               pictures[dir].add(new ImageIcon(fileName));
         }
      animationIndex=0; 
      direction=0;
   }
   
   public AnimatedEntity(String n, ArrayList<String> images)
   {
      ArrayList<String>[] allImages = new ArrayList[5];
      for(int dir = 0; dir < allImages.length; dir++)
         allImages[dir]=new ArrayList();
      if(images!=null)
         for(String x: images)
            allImages[0].add(x);
      name = n;
      fileNames = allImages;
      pictures = new ArrayList[5];  //UP, RIGHT, DOWN, LEFT, DEAD
      if(allImages!=null)
         for(int dir = 0; dir < pictures.length; dir++)
         {
            pictures[dir]=new ArrayList();
            for(String fileName: allImages[dir])
               pictures[dir].add(new ImageIcon(fileName));
         }
      animationIndex=0; 
      direction=0;     
   }

   public AnimatedEntity(String n)
   {
      name = n;
      fileNames = null;
      pictures = new ArrayList[5];  //UP, RIGHT, DOWN, LEFT, DEAD
      animationIndex=0; 
      direction=0;
   }
   
   public ArrayList<String> getFileNames()
   {
      ArrayList<String>ans = new ArrayList<String>();
      for(int i=0; i<fileNames.length; i++)
         for(String fName:fileNames[i])
            ans.add(fName);
      return ans;
   }

   public int getAnimationIndex()
   {
      return animationIndex;
   }
	
   public void setAnimationIndex(int i)
   {
      if(i>=0 && i<pictures[direction].size())
         animationIndex = i;
   }
   
	//post:	advances the animation index and clips it to the number of animation frames
   public void advanceAnimation()
   {
      if(pictures==null || pictures[direction].size()==0)
         return;
      animationIndex = (animationIndex + 1) % pictures[direction].size();
   }
   
   public void advanceRandom()
   {
      if(pictures==null || pictures[direction].size()==0)
         return;
      animationIndex = (int)(Math.random()*pictures[direction].size());
   }
   
   public ArrayList<ImageIcon>[] getPictures()
   {
      return pictures;
   }
   
   public void setPictures(ArrayList<String>[] images)
   {
      fileNames = images;
      pictures = new ArrayList[5];
      if(images!=null)
         for(int dir = 0; dir < pictures.length; dir++)
         {
            pictures[dir]=new ArrayList();
            for(String fileName: images[dir])
               pictures[dir].add(new ImageIcon(fileName));
         }
   }
   
	//post:	returns image of the entity at index 0
   public ImageIcon getPicture()
   {
      if(pictures==null || pictures[direction].size()==0)
         return null;
      return pictures[direction].get(0);
   }

	//pre:	index is a valid index of pictures List
	//post:	returns a specific image icon of the entity
   public ImageIcon getPicture(int index)
   {
      if(pictures==null || pictures[direction].size()==0)
         return null;
      if(index < pictures[direction].size()  && index>=0)
         return pictures[direction].get(index);
      return pictures[direction].get(0);
   }

//post:	returns an image of the entity and advances the animation index
   public ImageIcon getCurrentPicture()
   {
      if(pictures==null || pictures[direction].size()==0)
         return null;
      while(pictures[direction].size()==0 && direction > 0)
         direction--;
      if(pictures[direction].size()==0)
         return null;  
      if(pictures[direction].size()==1 || animationIndex<0 || animationIndex >= pictures[direction].size())
         return pictures[direction].get(0);
      ImageIcon temp = pictures[direction].get(animationIndex);
      return temp;
   }

   //post:	returns an image of the entity and advances the animation index
   public ImageIcon getPictureAndAdvance()
   {
      if(pictures==null)
         return null;
      while(pictures[direction].size()==0 && direction > 0)
         direction--;
      if(pictures[direction].size()==0)
         return null;   
      if(pictures[direction].size()==1 || animationIndex<0 || animationIndex >= pictures[direction].size())
         return pictures[direction].get(0);
      ImageIcon temp = pictures[direction].get(animationIndex);
      advanceAnimation();
      return temp;
   }

   //post:	returns an image of the entity and advances the animation index
   public ImageIcon getPictureRandom()
   {
      if(pictures==null)
         return null;
      while(pictures[direction].size()==0 && direction > 0)
         direction--;
      if(pictures[direction].size()==0)
         return null;   
      if(pictures[direction].size()==1 || animationIndex<0 || animationIndex >= pictures[direction].size())
         return pictures[direction].get(0);
      ImageIcon temp = pictures[direction].get(animationIndex);
      advanceRandom();
      return temp;
   }

   public String getName()
   {
      return name;
   }
	 
   public void setName(String n)
   {
      name = n;
   }

   public int getDirection()
   {
      return direction;
   }

   public void setDirection(byte d)
   {
      if(d>=0 && d<pictures.length && pictures[d].size() > 0)
         direction = d;
   }

   public String toString()
   {
      return name + "," + pictures;
   }
}