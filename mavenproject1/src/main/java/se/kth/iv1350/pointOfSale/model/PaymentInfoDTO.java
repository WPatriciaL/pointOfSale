
package se.kth.iv1350.pointOfSale.model;

/**
 *A DTO concerning information about the exchange of cash.
 * 
 */
public class PaymentInfoDTO {
    private final double totalPrice;
    private final double paymentReceived;
    private final double change;
    
    
    /**
     * Creates an instance of a <code>PaymentInfoDTO</code>
     * 
     * @param totalprice total cost of the entire <code>Sale</code>
     * @param paymentReceived received payment from customer 
     * @param change amount to give to customer
     */
    public PaymentInfoDTO(double totalPrice, double paymentReceived, double change){
        this.totalPrice = totalPrice;
        this.paymentReceived = paymentReceived;
        this.change = change;
    
    }
    /**
     * Get the amount of <code>Change</code> in recently concluded <code>Sale</code>.
     * @return amount of change.
     */
    public double getChange() {
        return change;
    }
    /**
     * Get total price of the entire <code>Sale</code>.
     * @return  total price of sale.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    /**
     * Get the amount of received payment from the customer.
     * @return amount of received payment.
     */
    public double getPaymentReceived() {
        return paymentReceived;
    }
    
    
    
}
