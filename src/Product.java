   //Program Name: Product.java 
	//Author: Marlena Jeffery, Anthony Newberry, Yanyao Wang
	//Class: CSC110AA or CIS163AA Jalowiec 
	//Date: 04December12 
	//Description: This program represents the product of Mac computers which will be sold in a store.
   
   import java.text.NumberFormat;
   import java.text.DecimalFormat;
   
   public class Product 
   {   
   	//constant variable
      final double GENERATOR_RATIO = 1.06;	//Use to set the frequency of cpu, the size of memory and harddrvie, the weight, and the battery
      final static String[] MODEL_TYPE = {"MacBook Air", "MacBook Pro", "iMac", "Mac Pro"};
      final static String[][] STYLE_TYPE= {{"11-inch Monitor", "13-inch Monitor"}, {"13-inch Monitor", "15-inch Monitor", "17-inch Monitor"}, 
         											 {"17-inch Monitor", "21.5-inch Monitor","24-inch Monitor","27-inch Monitor"}, 
         											 {"15-inch Monitor","17-inch Monitor","19-inch Monitor","21.5-inch Monitor","24-inch Monitor","27-inch Monitor","29-inch Monitor","32-inch Monitor"}};
      final static String[][] STYLE_PICTURE = {{"MacBookAir11.gif", "MacBookAir13.gif"}, {"MacBookPro13.gif", "MacBookPro15.gif", "MacBookPro17.gif"},
         												  {"iMac17.gif", "iMac21.gif", "iMac24.gif", "iMac27.gif"}, 
         												  {"MacPro15.gif", "MacPro17.gif", "MacPro19.gif", "MacPro21.gif", "MacPro24.gif", "MacPro27.gif", "MacPro29.gif", "MacPro32.gif"}};
      final static String[] OPTION_TYPE = {"GHz dual-core Intel Core", "GB memory", "Intel HD Graphics 4000", "GB harddrive", "pounds", "hours"};
      final static double[][] PRICE = {{999, 1199}, {1799, 2199, 2499}, {1199, 1299, 1599, 1999}, {1699, 1999, 2199, 2499, 2799, 3099, 3399, 3699}};
   
      private int model;
      private int style;
      
      private DecimalFormat decimalFmt1 = new DecimalFormat("0.#");
      private DecimalFormat decimalFmt2 = new DecimalFormat("0");
      private NumberFormat currencyFmt = NumberFormat.getCurrencyInstance();
      	
    	//Constructor: create the product with the model and style chosen by customers  
   	
      public Product(int modelChoice, int styleChoice)
      {
         
         this.model = modelChoice;
         this.style = styleChoice;
      }  
      
      public int getModel()
      {
         return this.model;
      }  
   		
      public int getStyle()
      {
         return this.style;
      }   
            
      //Use GENERATOR_RATIO and OPTION_TYPE array to create the detail for chosen products
      
      private String buildProductDetail()
      {
         String[] productDetail = new String[6];
         String detail = "";
      	
         productDetail[0] = "CPU: " + String.valueOf(decimalFmt1.format(Math.pow((1.7 * GENERATOR_RATIO), this.model))) + " " + OPTION_TYPE[0];
         productDetail[1] = "Memory: " + String.valueOf(decimalFmt2.format(Math.pow((4 * GENERATOR_RATIO), this.model))) + " " + OPTION_TYPE[1];
         productDetail[2] = "Video Card: " + OPTION_TYPE[2];
         productDetail[3] = "Harddrive: " + String.valueOf(decimalFmt2.format(Math.pow((320 * GENERATOR_RATIO), this.model))) + " " + OPTION_TYPE[3];
         productDetail[4] = "Weight: " + String.valueOf(decimalFmt1.format(Math.pow((2.4 * GENERATOR_RATIO), this.model))) + " " + OPTION_TYPE[4];
         productDetail[5] = "Battery: " + String.valueOf(decimalFmt1.format(Math.pow((5.0 * GENERATOR_RATIO), this.model))) + " " + OPTION_TYPE[5];
         
         for(int index = 0; index < productDetail.length; index++)
         {
            detail += productDetail[index] + "\n";
         }
         return detail;
      }
    	 
   	 //Return product state as a string
   	   	  
      public String toString()
      {
         String report;    
            
         report = "";
         report += "\n-----------------------------------------------------";
         report += "\nModel: " + MODEL_TYPE[model] + " (" + STYLE_TYPE[this.model][this.style] + ")";
         report += "\n-----------------------------------------------------\n" 
            	 + buildProductDetail()
            	 + "-----------------------------------------------------\n"  
            	 + "PRICE: " + currencyFmt.format(PRICE[this.model][this.style])
            	 + "\n-----------------------------------------------------\n";
         return report;
      }
   }
