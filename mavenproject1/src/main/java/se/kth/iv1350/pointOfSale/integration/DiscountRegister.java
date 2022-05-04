package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Sale;
/**
 * Class <code>DiscountRegister</code> containing information about available discounts.
 * 
 */
public class DiscountRegister {

	private double amount;
        
        /**
         * Creates an instance of the handler for the <code>DiscountRegister</code>. 
         * 
         */
	public DiscountRegister() {
		
	}

	public double getDiscount(int customerID, Sale sale) {
		return 0;
	}

}
