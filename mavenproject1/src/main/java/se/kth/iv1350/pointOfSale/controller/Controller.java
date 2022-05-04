package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.CheckOutCart;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;
import se.kth.iv1350.pointOfSale.model.PaymentInfoDTO;

/**
 * This is the controller of the program. All calls from the <code>Wiew</code> pass through this class.
 * 
 */
public class Controller {
        
        private InventoryHandler inventoryHandler;
        private DiscountRegister discountRegister;
        private SalesLog salesLog;
        
        private Sale sale;
        private ReceiptDTO receiptDTO;
        private CheckOutCart checkOutCart;
        private Register register;
        
        /**
         * Creates an instance of a <code>Controller</code>.
         * 
         * @param salesLog the handler that logs completed sales. 
         * @param inventoryHandler the handler that connects to the inventory system.
         * @param discountRegister the handler that connects to a discount database. 
         */
	public Controller(SalesLog salesLog, InventoryHandler inventoryHandler, DiscountRegister discountRegister) {
		this.salesLog = salesLog;
                this.inventoryHandler = inventoryHandler;
                this.discountRegister = discountRegister;
                
                this.register = new Register();       
	}
        /**
         * Starts a new <code>Sale</code>.
         */
	public void startNewSale() {
            checkOutCart = new CheckOutCart(inventoryHandler);
            sale = new Sale(checkOutCart, discountRegister);    
	}
        /**
         * Based on the input <code>ItemDTO</code>, add a corresponding <code>Item</code>to the cart and update the running total.
         * @param itemRequest a proto-item, all values null except for <code>identifier</code>.
         * @return contains relevant info of states in the program.
         */
	public SaleStateDTO nextItem(ItemDTO itemRequest){
            ItemDTO scannedItem = checkOutCart.addItem(itemRequest);
            SaleStateDTO saleStateDTO =  sale.updateRunningTotal(scannedItem);
            
            
		return saleStateDTO;
	}
        /**
         * Ends the <code>Sale</code> and make sure all logs are done.
         * @param paymentReceived received payment from customer
         * @return receipt with all information about the <code>Sale</code>
         */
	public ReceiptDTO concludeSale(double paymentReceived) {
            ItemDTO noItem = new ItemDTO(0, 0, null, null, 0, 0);
            SaleStateDTO saleStateDTO = sale.updateRunningTotal(noItem);
   
            PaymentInfoDTO paymentInfoDTO = register.calculateChange(saleStateDTO, paymentReceived);
            salesLog.logSale(saleStateDTO,paymentInfoDTO);
            
            ReceiptDTO receipt = new ReceiptDTO(saleStateDTO,paymentInfoDTO);
         
		return receipt;
	}



}
