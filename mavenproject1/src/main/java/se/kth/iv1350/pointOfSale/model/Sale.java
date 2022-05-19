package se.kth.iv1350.pointOfSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
/**
 * Contains information about the current state of the <code>Sale</code> in progress. 
 * 
 */
public class Sale {

        private DiscountRegister discountRegister;
        private CheckOutCart checkOutCart;
	private double totalPrice;
        private double totalVAT;
        private double receivedPayment;
        private double discount;
    

        /**
         * Creates an instance of <code>Sale</code>
         * @param checkOutCart Checkoutcart contains the <code>Item</code> in the current <code>Sale</code>.
         * @param discountRegister Handles the available discounts.
         */
	public Sale(CheckOutCart checkOutCart, DiscountRegister discountRegister) {
		this.checkOutCart = checkOutCart;
                this.discountRegister = discountRegister;
                this.totalPrice = 0.0;
                this.receivedPayment = 0.0;
                this.discount = 0.0;
                this.totalVAT = 0.0;
                
	}

        /**
         * Updates the running total with the <code>price</code> and VAT of the latest scanned item.
         * @param scannedItem the latest scanned item
         * @return contains relevant info of states in the program.
         */
	public SaleStateDTO updateRunningTotal(ItemDTO scannedItem) {
        
            totalVAT = totalVAT + calculateVAT(scannedItem);
            this.totalPrice = this.totalPrice + scannedItem.getPrice() * scannedItem.getQuantity() + calculateVAT(scannedItem);
            
            SaleStateDTO saleState = updateSaleState(scannedItem, totalVAT, totalPrice);
    
		return saleState;
	}
       /**
         * Collects data to save to a DTO for eventual transfer to View.
         * @param scannedItem The latest scanned item
         * @param totalVAT the current amount of VATin the <code>Sale</code>.
         * @param totalPrice the current running total in the <code>Sale</code>.
         * @return the latest changes to the application, to be returned to View.
         */
        private SaleStateDTO updateSaleState(ItemDTO scannedItem, double totalVAT, double totalPrice){
            
            final ArrayList<Item> shoppingList = checkOutCart.getListOfItems();
            SaleStateDTO saleState = new SaleStateDTO(scannedItem, totalVAT, totalPrice, shoppingList);
            
            return saleState;
        }
        
        
        /**
         * Calculates the VAT of the given <code>ItemDTO</code>.
         * @param scannedItem the <code>ItemDTO</code> to calculate the cost of VAT.
         * @return cost of VAT for the scanned item
         */
        private double calculateVAT(ItemDTO scannedItem){
        
           double  calculatedVAT = scannedItem.getPrice() * scannedItem.getRateOfVAT() * scannedItem.getQuantity();
            return calculatedVAT;
        }
        
        /**
         * Gets the date and time for the recently concluded <code>Sale</code>
         * @return current date and time
         */
        
	private java.time.LocalDateTime logDateAndTimeForSale() {
                LocalDateTime dateAndTimeForSale = LocalDateTime.now();
		return dateAndTimeForSale;
	}

}
