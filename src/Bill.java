   //Program Name: Bill.java 
	//Author: Marlena Jeffery, Anthony Newberry, Yanyao Wang
	//Class: CSC110AA or CIS163AA Jalowiec 
	//Date: 04December12 
	//Description: This program record the customer selections and create the bill.

   import java.text.NumberFormat;

   public class Bill
   {
      private final double TAX_RATE = 0.08;  //purchase tax: 8%
      private Product[] order;
      int orderCount;
      
      private NumberFormat currencyFmt = NumberFormat.getCurrencyInstance();
   
      //Constructor: setup an Product array to save customer's order 
      public Bill()
      {
         order = new Product[3];
         orderCount = 0;
      }
    	
      //compute total price of ordered items 
      public double calculatePrice()
      {
         double totalPrice = 0;
         for(int index = 0; index < orderCount; index++)
         {
            totalPrice += Product.PRICE[order[index].getModel()][order[index].getStyle()];
         }
         return totalPrice;
      }
      
   	//compute total cost(price times taxes) of ordered items 
      public double calculateCost()
      {
         return calculatePrice() * (1 + TAX_RATE);
      }
      
   	//add an ordered item into the bill
      public void addProduct(Product choice)
      {
         if(orderCount == order.length)
            increaseSize();
      		
         order[orderCount] = choice;
         orderCount++;
      }  
      
   	//double the bill size when customer ordered more than three items
      private void increaseSize()
      {
         Product[] temporary = new Product[order.length * 2];
         for(int index = 0; index < order.length; index++)
         {
            temporary[index] = order[index];
         }
         order = temporary;
      }
      	
      public String display()
      {
         String report = "\n****************************************";	
         report += "\nOrdered Item:\n";
         for(int index = 0; index < orderCount; index++)
         {
            report += "----------------------------------------";
            report += "\n[ Order " + (index + 1) + " ]:";
            report += "\nModel: " + Product.MODEL_TYPE[order[index].getModel()];
            report += " (" + Product.STYLE_TYPE[order[index].getModel()][order[index].getStyle()] + ")";
            report += "\n" + currencyFmt.format(Product.PRICE[order[index].getModel()][order[index].getStyle()]) + "\n";
         }
         report += "----------------------------------------\n";
         report += orderCount + " Items Ordered. Total price: " + currencyFmt.format(calculatePrice()) + "\n";
         report += "Total Cost(with 8% tax): " +currencyFmt.format(calculateCost()) + "\n";
         report += "****************************************\n";
         return report;
      }
      
      public String displayForGUI()
      {
         String report = "-----------------------------------------------------";	
         report += "\nOrdered Item:\n";
         for(int index = 0; index < orderCount; index++)
         {
            report += "-----------------------------------------------------";
            report += "\n[ Order " + (index + 1) + " ]:";
            report += "\nModel: " + Product.MODEL_TYPE[order[index].getModel()];
            report += " (" + Product.STYLE_TYPE[order[index].getModel()][order[index].getStyle()] + ")";
            report += "\n" + currencyFmt.format(Product.PRICE[order[index].getModel()][order[index].getStyle()]) + "\n";
         }
         report += "-----------------------------------------------------\n";
         report += orderCount + " Items Ordered. Total price: " + currencyFmt.format(calculatePrice()) + "\n";
         report += "Total Cost(with 8% tax): " +currencyFmt.format(calculateCost()) + "\n";
         report += "-----------------------------------------------------\n";
         return report;
      }
   }