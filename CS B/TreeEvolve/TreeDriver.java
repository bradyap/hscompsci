//Rev. Dr. Douglas R Oberle - Nicholas Hodgman 2015

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TreeDriver extends JFrame
{
   public static void main(String[] args)
   {
   
   //************************
   //hold shift to make smaller change increments
   //***********************
   
      JFrame frame=new JFrame(); 
      frame.setTitle("Evolving Trees      SPACE:Advance 1 Generation     LEFT-CLICK:Random Fractal     RIGHT-CLICK:Toggle Evolution     ESC:Exit"); 
      frame.setSize(1500,1000);       
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
      //create a new panel and its data
      TreePanel panel=new TreePanel();
   
      //adds panel and sets it as visible
      frame.setContentPane(panel); 
      frame.setVisible(true);       
   }   

}