   //Program Name: StoreFront.java 
	//Author: Marlena Jeffery, Anthony Newberry, Yanyao Wang
	//Class: CSC110AA or CIS163AA Jalowiec 
	//Date: 04December12 
	//Description: This program represents the storefront of Mac computers.
	
   import java.awt.*;
   import javax.swing.*;

   public class StoreFront extends JFrame
   {
      public static void main(String[] args)
      {
         JFrame frame = new JFrame("Mac Store");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
         StoreFrontPanel sfp = new StoreFrontPanel();
         frame.getContentPane().add(sfp);
      	
         frame.pack();
         frame.setVisible(true);
      }
   }