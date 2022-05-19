package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;
import se.kth.iv1350.pointOfSale.controller.ConnectionException;
import se.kth.iv1350.pointOfSale.controller.InvalidInputException;



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
            ItemDTO firstItemBought = new ItemDTO(3, 1, null, null, 0, 0);
            ItemDTO secondItemBought = new ItemDTO(4, 1, null, null, 0, 0);
            ItemDTO forbiddenItemIdentifier = new ItemDTO(404, 1, null, null, 0, 0);
            SaleStateDTO firstItemToPrint;
            SaleStateDTO secondItemToPrint;
            SaleStateDTO itemThatCausesAnException;
            ReceiptDTO receipt;
            double paymentReceived = 200;

            
            
            controller.startNewSale();
            try {
                firstItemToPrint = controller.nextItem(firstItemBought);
                printSaleInfoToScreen(firstItemToPrint);
                secondItemToPrint = controller.nextItem(secondItemBought);
                printSaleInfoToScreen(secondItemToPrint);
                receipt = controller.concludeSale(paymentReceived);
                printReceiptToScreen(receipt);     
            } 
            catch (InvalidInputException | ConnectionException e) {
                String messageToScreen = e.getMessage();
                printExceptionMessageToScreen(messageToScreen);
                
                //Throwable originalExceptionMessage = e.getCause();
                //System.out.println(originalExceptionMessage);   
            }
               
            controller.startNewSale();
            try {
                itemThatCausesAnException = controller.nextItem(forbiddenItemIdentifier);
                printSaleInfoToScreen(itemThatCausesAnException);
                receipt = controller.concludeSale(paymentReceived);
                printReceiptToScreen(receipt);
                
            } 
            catch (InvalidInputException | ConnectionException e) {
                String messageToScreen = e.getMessage();
                printExceptionMessageToScreen(messageToScreen);
                
                //Throwable originalExceptionMessage = e.getCause();
                //System.out.println(originalExceptionMessage);
           }
        }
        
        
        private void printExceptionMessageToScreen (String messageToScreen){
                System.out.println("\n \n|------------------- ERROR MESSAGE: --------------------------------|");
                System.out.println(messageToScreen); 
                System.out.println("|----------------- END OF ERROR MESSAGE ----------------------------|\n \n");
        }
        private void printSaleInfoToScreen (SaleStateDTO toBePrinted){
        System.out.println("\n Lates scanned item: " + toBePrinted.getScannedITemDTO().getName() + 
                "\n Description: " + toBePrinted.getScannedITemDTO().getDescription() + "\n Price: " + toBePrinted.getScannedITemDTO().getPrice() +
                "\n Total VAT:" + toBePrinted.getTotalVAT() +"\n Running total: " + toBePrinted.getRunningTotal());
        }
        private void printReceiptToScreen (ReceiptDTO receipt){
            System.out.println("""
                           
                           |- - - - - - - - - -  RECEIPT  - - - - - - - - - - - - - - - - - - -|
                            Time for sale: """ + receipt.getTimeForSale() + "\n Total price: " +
                            receipt.getTotalPrice() + "\n Discount: " + receipt.getDiscount() + 
                            "\n Received Payment: " + receipt.getReceivedPayment() + "\n Change: " + receipt.getChange() + 
                            "\n|- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
        }
              
}
