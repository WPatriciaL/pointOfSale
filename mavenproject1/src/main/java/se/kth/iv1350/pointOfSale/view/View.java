package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;
import java.lang.*;


/**
 * This is a placeholder for the user interface. It contains a hardcoded execution with 
 * calls to all system operations in the <code>Controller</code>. 
 * 
 */
public class View {
       private Controller controller;
        /**
         * Creates an instance of a <code>View</code>.
         * @param controller the only access from the user interface to the business logic goes
         * through this controller.
         */
	public View(Controller controller) {
            this.controller = controller;
		
	}
        /**
         * hardcoded execution with calls to all system operations in the <code>Controller</code>.
         */
        public void viewExecute(){
            ItemDTO firstItemBought = new ItemDTO(5, 1, null, null, 0, 0);
            ItemDTO secondItemBought = new ItemDTO(3, 1, null, null, 0, 0);
            ItemDTO thirdItemBought = new ItemDTO(3, 1, null, null, 0, 0);
            SaleStateDTO toBePrinted;
            
            ReceiptDTO receipt;
            double paymentReceived = 200;
            
            
            controller.startNewSale();
            
            toBePrinted = controller.nextItem(firstItemBought);
            System.out.println("Lates scanned item: " + toBePrinted.getScannedITemDTO().getName());
            System.out.println("Description: " + toBePrinted.getScannedITemDTO().getDescription());
            System.out.println("Running total: " + toBePrinted.getRunningTotal());
            System.out.println("Total VAT:" + toBePrinted.getTotalVAT());
            System.out.println();
       
            toBePrinted = controller.nextItem(secondItemBought);
           System.out.println("Lates scanned item: " + toBePrinted.getScannedITemDTO().getName());
            System.out.println("Description: " + toBePrinted.getScannedITemDTO().getDescription());
            System.out.println("Running total: " + toBePrinted.getRunningTotal());
            System.out.println("Total VAT:" + toBePrinted.getTotalVAT());
            System.out.println();
            
            toBePrinted = controller.nextItem(thirdItemBought);
            System.out.println("Lates scanned item: " + toBePrinted.getScannedITemDTO().getName());
            System.out.println("Description: " + toBePrinted.getScannedITemDTO().getDescription());
            System.out.println("Running total: " + toBePrinted.getRunningTotal());
            System.out.println("Total VAT:" + toBePrinted.getTotalVAT());
            System.out.println();
            
            
            receipt = controller.concludeSale(paymentReceived);
            System.out.println("Time for sale: " + receipt.getTimeForSale());
            System.out.println("Total price: " + receipt.getTotalPrice());
            System.out.println("Discount: " + receipt.getDiscount());
            System.out.println("Received Payment: " + receipt.getReceivedPayment());
            System.out.println("Change: " + receipt.getChange());
            
           
            
          
        }
        
        
}
