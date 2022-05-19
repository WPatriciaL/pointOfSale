package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.integration.CouldNotReachDatabaseException;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.CheckOutCart;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.ItemNotFoundException;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;
import se.kth.iv1350.pointOfSale.model.PaymentInfoDTO;
import se.kth.iv1350.pointOfSale.util.ExceptionLogger;

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
        private ExceptionLogger exceptionLogger;
        
        
        
        /**
         * Creates an instance of a <code>Controller</code>.
         * 
         * @param salesLog the handler that logs completed sales. 
         * @param inventoryHandler the handler that connects to the inventory system.
         * @param discountRegister the handler that connects to a discount database. 
         * @param register the active <code>Register</code> for this point of sale. 
         */
	public Controller(SalesLog salesLog, InventoryHandler inventoryHandler, DiscountRegister discountRegister,Register register) {
		this.salesLog = salesLog;
                this.inventoryHandler = inventoryHandler;
                this.discountRegister = discountRegister;
                this.register = register;
                this.exceptionLogger = new ExceptionLogger();
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
         * @return contains relevant info on the states in the program.
         * @throws InvalidInputException Thrown when an <code>ItemDTO</code> identifier does not match an identifier in the inventory database.
         * @throws ConnectionException  Thrown when there is some connection problem in the integration layer.
         */
	public SaleStateDTO nextItem(ItemDTO itemRequest) throws InvalidInputException,ConnectionException{
            try {
                ItemDTO scannedItem = checkOutCart.addItem(itemRequest);
                SaleStateDTO saleStateDTO =  sale.updateRunningTotal(scannedItem);
                return saleStateDTO;
                
            } catch (ItemNotFoundException itemNotFoundException) {
                    throw new InvalidInputException(itemNotFoundException);
            }
            
              catch (CouldNotReachDatabaseException couldNotReachDatabaseException){
                exceptionLogger.logException(couldNotReachDatabaseException);
                    throw new ConnectionException(couldNotReachDatabaseException);
            }      
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
