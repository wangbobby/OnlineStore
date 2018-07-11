   //Program Name: StoreFrontPanel.java 
	//Author: Marlena Jeffery, Anthony Newberry, Yanyao Wang
	//Class: CSC110AA or CIS163AA Jalowiec 
	//Date: 04December12 
	//Description: This program represents the storefront GUI panel.
   
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.io.*;

   class StoreFrontPanel extends JPanel
   {
      Product[] product;
      String[] styleList;
      JButton[] modelJButton;
      JButton[] styleJButton;
      JTextArea[] text;
      JLabel[] images;
      JPanel[] description;
      JPanel[] styleJPanel;
      BottomPanel bottomPanel;
      JButton payButton;
      JButton cancelButton;
      JButton finishButton;
      JScrollPane bodyPane;
      int modelIndex;
      int styleIndex;
      Bill bill = new Bill();
      Dimension topAndBottomPanelDimension = new Dimension(800, 50);
            
      //Constructor: create a storefront with borderlayout
      public StoreFrontPanel()
      {
         setLayout(new BorderLayout());
            
         TopPanel topPanel = new TopPanel();
         bodyPane = new JScrollPane(new BodyPanel());  
         bottomPanel = new BottomPanel(); 
      	
         add(topPanel, BorderLayout.NORTH);
         add(bodyPane, BorderLayout.CENTER);
         add(bottomPanel, BorderLayout.SOUTH); 
      }
      
      //build the north region for storefront panel
      class TopPanel extends JPanel
      {
         private TopPanel()
         {
            setPreferredSize(topAndBottomPanelDimension);
            modelJButton = new JButton[Product.MODEL_TYPE.length];
            JPanel panel = new JPanel();
            for(int index = 0; index < Product.MODEL_TYPE.length; index++)
            {
               modelJButton[index] = new JButton(Product.MODEL_TYPE[index]);
               modelJButton[index].addActionListener(new ButtonListener());
               panel.add(modelJButton[index]);
            }
            add(panel);
         }
      }
      
      //build the center region for storefront panel
      class BodyPanel extends JPanel
      {
         private BodyPanel()
         {
            JLabel wellComeImage = new JLabel(new ImageIcon("wellcome.jpg"));
            add(wellComeImage);
            setPreferredSize(new Dimension(800, 580));  
         }
      }
      
      //build the south region for storefront panel
      class BottomPanel extends JPanel
      {
         private BottomPanel()
         {
            setPreferredSize(topAndBottomPanelDimension);
         }
      }
      
      //create button listeners
      private class ButtonListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent event) 
         {
            //respond model button clicks with the center and south region's changes
            for(int index = 0; index < Product.MODEL_TYPE.length; index++)
            {
               if(event.getSource() == modelJButton[index])
               {
                  modelIndex = index;
                  cleanPanel(bottomPanel);
                  styleList = twoToOneDimensionArray(index, Product.STYLE_TYPE);
                  bodyPane.setViewportView(stylePanelList(styleList, index));
               }
            }
            //respond style button clicks with the center and south region's changes
            for(int index = 0; index < styleJButton.length; index++)
            {
               styleIndex = index;
               if(event.getSource() == styleJButton[index])
               {
                  cleanPanel(bottomPanel);
                  bodyPane.setViewportView(createOrderPanel());
                  cancelButton = new JButton("Cancel Order");
                  cancelButton.addActionListener(new ButtonListener());
                  payButton = new JButton("Pay Order");
                  payButton.addActionListener(new ButtonListener());
                  bottomPanel.add(cancelButton);
                  bottomPanel.add(payButton);
               }
            }
            //respond pay button clicks with the center and south region's changes
            if(event.getSource() == payButton)
            {
               try
               {
                  printFile(bill.display());
               }
                  catch(IOException ioe) {System.out.println("File error. (See order.txt)");};
               Bill temp = new Bill();
               bill = temp;
               cleanPanel(bottomPanel);
               bodyPane.setViewportView(finishPanel("paid"));  
            }
            //respond cancel button clicks with the center and south region's changes
            if(event.getSource() == cancelButton)
            {
               Bill temp = new Bill();
               bill = temp;
               cleanPanel(bottomPanel);
               bodyPane.setViewportView(finishPanel("cancelled"));
            }
            //respond finish button clicks with the center region's changes
            if(event.getSource() == finishButton)
            {
               bodyPane.setViewportView(new BodyPanel());
            }
         }
      }
      
      //show the combined style panels which will be located in the center region  
      private JPanel stylePanelList(String[] string, int modelIndex)
      {
         JPanel tempJPanel = new JPanel();
         styleJPanel = new JPanel[string.length];
         styleJButton = new JButton[string.length];
         text = new JTextArea[string.length];
         description = new JPanel[string.length];
         images = new JLabel[string.length];
         product = new Product[string.length];
         
         tempJPanel.setLayout(new fitWidthFlowLayout());
         for(int index = 0; index < string.length; index++)
         {
            styleJPanel[index] = new JPanel();
            styleJButton[index] = new JButton("Order");
            styleJButton[index].addActionListener(new ButtonListener());
            text[index] = new JTextArea(); 
            description[index] = new JPanel();
            product[index] = new Product(modelIndex, index);
            images[index] = new JLabel((new ImageIcon (Product.STYLE_PICTURE[modelIndex][index])));
               
            styleJButton[index].setAlignmentX(Component.CENTER_ALIGNMENT);
            images[index].setAlignmentX(Component.CENTER_ALIGNMENT);
            styleJPanel[index].setLayout(new BoxLayout(styleJPanel[index], BoxLayout.PAGE_AXIS));
            text[index].setText(product[index].toString());
            text[index].setEditable(false);
            text[index].setBackground(new Color(238, 238, 238));
            
            description[index].add(text[index]);
            styleJPanel[index].add(images[index]);  
            styleJPanel[index].add(styleJButton[index]);
            styleJPanel[index].add(description[index]);
            tempJPanel.add(styleJPanel[index]);
         }     
         return tempJPanel;
      }
      
      //show the finish panel located in the center region
      private JPanel finishPanel(String string)
      {
         JPanel tempPanel = new JPanel();
         JPanel centerPanel = new JPanel();
         tempPanel.setLayout(new GridBagLayout());
         centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
         JLabel l1 = new JLabel("You order has been " + string + " .");
         JLabel l2 = new JLabel("Thank You!");
         JLabel l3 = new JLabel("<html>     <br><br>  </html>");
         finishButton = new JButton("Finsh");
         finishButton.addActionListener(new ButtonListener());
         l1.setFont(new Font("Dialog", 1, 15));
         l2.setFont(new Font("Dialog", 1, 15));
         l1.setAlignmentX(Component.CENTER_ALIGNMENT);
         l2.setAlignmentX(Component.CENTER_ALIGNMENT);
         finishButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         centerPanel.add(l1);
         centerPanel.add(l2);
         centerPanel.add(l3);
         centerPanel.add(finishButton);
         tempPanel.add(centerPanel);	
         
         return tempPanel;
      }
      
      //change two dimension array to one dimension array
      private String[] twoToOneDimensionArray(int modelIndex, String[][] string)
      {
         String[] oneDimension = new String[string[modelIndex].length];
         for(int index = 0; index < string[modelIndex].length; index++)
         {
            oneDimension[index] = string[modelIndex][index];
         }
         return oneDimension;
      }
      
      //show the order panel located in the center region
      private JPanel createOrderPanel()
      {
         JPanel orderPanel = new JPanel();
         JPanel textPanel = new JPanel();
         Product computer = new Product(modelIndex, styleIndex);
         bill.addProduct(computer);
         JTextArea billText = new JTextArea();  
         billText.setText(bill.displayForGUI());
         billText.setBackground(new Color(238, 238, 238));
         textPanel.add(billText);
         orderPanel.add(textPanel);
         return  orderPanel;
      }
      
      //clean components located on the panel
      private void cleanPanel(JPanel panel)
      {
         panel.removeAll();
         panel.revalidate();
         panel.repaint();
      }
            
      //output a file as a receipt
      private void printFile(String string) throws IOException
      {
         String file = "order.txt";
         FileWriter fw = new FileWriter(file);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter outFile = new PrintWriter(bw);
         outFile.print(string);
         outFile.close();
      }
      
      //modify the flowlayout so let the panels in the center region
   	//change lines following the size changes of the center region
      private class fitWidthFlowLayout extends FlowLayout 
      { 
         public fitWidthFlowLayout() 
         { 
            super(); 
         } 
         public Dimension preferredLayoutSize(Container target) 
         { 
            return computeSize(target); 
         } 
         private Dimension computeSize(Container target) 
         { 
            synchronized(target.getTreeLock()) 
            { 
               int hgap = getHgap(); 
               int vgap = getVgap(); 
               int tempWidth = target.getWidth(); 
               
               if (tempWidth == 0) 
               { 
                  tempWidth = Integer.MAX_VALUE; 
               }
               Insets insets = target.getInsets();
               if (insets == null)	
               { 
                  insets = new Insets(0, 0, 0, 0); 
               } 
               int reqdWidth = 0;
               int rowHeight = 0; 
               int maxwidth = tempWidth - (insets.left + insets.right + hgap * 2); 
               int nmembers = target.getComponentCount(); 
               Dimension dim = new Dimension(0, 0);
               dim.height = insets.top + vgap;
             
               for (int i = 0; i < nmembers; i++) 
               { 
                  Component m = target.getComponent(i); 
                  if (m.isVisible()) 
                  { 
                     Dimension d = m.getPreferredSize(); 
                     if ((dim.width == 0) || ((dim.width + d.width) <= maxwidth)) 
                     { 
                        if (dim.width > 0) 
                        { 
                           dim.width += hgap; 
                        } 
                        dim.width += d.width; 
                        rowHeight = Math.max(rowHeight, d.height);
                     } 
                     else 
                     { 
                        dim.width = d.width; 
                        dim.height += vgap + rowHeight; 
                        rowHeight = d.height; 
                     } 
                     reqdWidth = Math.max(reqdWidth, dim.width); 
                  }
               } 
               dim.height += rowHeight; 
               dim.height += insets.bottom;
               return dim;
            }
         }   
      }
   }