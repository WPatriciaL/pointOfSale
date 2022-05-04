package se.kth.iv1350.pointOfSale.model;
/**
 * Handles all logic concerning the exchange of cash at the conclusion of a <code>Sale</code>.
 * 
 */
public class Register {

	private double balance;
        private double change;

        /**
         * Creates an instance of a <code>Register</code>.
         * 
         */
	public Register() {	
            this.balance = 0;
	}

        
        /**
         * Calculate eventual <code>change</code> after payment is received.
         * @param saleStateDTO final state of the <code>Sale</code>, ready to be concluded. 
         * @param paymentReceived amount received from customer must be greater or equal than the total price.
         * @return contain total price and the exchange of cash for the concluded <code>Sale</code>
         */
	public PaymentInfoDTO calculateChange(SaleStateDTO saleStateDTO, double paymentReceived) {
            double totalPrice = saleStateDTO.getRunningTotal();
            change = (paymentReceived - totalPrice);
            updateBalance(totalPrice);
          PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO(totalPrice, paymentReceived, change);
            
            return paymentInfoDTO;
	}
        
        /**
         * Updates the <code>balance</code> in the <code>Register</code> after concluded <code>Sale</code>.
         * 
         * @param totalPrice amount to update balance with
         */
	private void updateBalance(double totalPrice) {
            balance = balance + totalPrice;
	}

}
