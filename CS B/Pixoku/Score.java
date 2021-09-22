   //Rev. Dr. Douglas R Oberle, March 2013
    public class Score implements Comparable
   {
      private String fileName;			//name of text file that contains description, dimension, and cell values
      private String solutionImage;		//image that shows over the completed puzzle
      private String player;				//initials of player with high score
      private int score;					//player score 
      private int time;						//time to complete puzzle
      private int size;						//total # of rows and columns for this puzzle
   
       public Score(String f, String si, String p, int s, int t, int z)
      {
         fileName = f;
         solutionImage = si;
         player = p;
         score = s;
         time = t;
         size = z;
      }
   
       public int compareTo(Object arg)
      {
         Score that = (Score)arg;
         if(this.score != that.score)			//if scores are different, higher score wins
            return this.score - that.score;
         if(this.time != that.time)				//if scores are same but time is different, lower time wins
            return that.time - this.time;
         return 0;									//score and time are the same, they are equal
      }
      
       public String showScore()
      {
         return "HIGH SCORE: " + player +", with " + score + " points in " + time + " sec";
      }
      
       public String toString()
      {
         return fileName + " " + solutionImage + " " + player +" " + score + " " + time + " " + size;
      }
   
       public String getFileName()
      {
         return fileName;
      }
      
       public String getSolutionImage()
      {
         return solutionImage;
      }
   
       public int getSize()
      {
         return size;
      }
   	
       public int getScore()
      {
         return score;
      }
      
       public void setScoreInfo(String f, String si, String p, int s, int t, int z)
      {
         fileName = f;
         solutionImage = si;
         player = p;
         score = s;
         time = t;
         size = z;
      }
      
       public void setScoreInfo(String p, int s, int t, int z)
      {
         player = p;
         score = s;
         time = t;
         size = z;
      }
   
       public void setScoreInfo(String p, int s, int t)
      {
         player = p;
         score = s;
         time = t;
      }
   
   }