package se.kth.iv1350.pointOfSale.model;

import java.util.ArrayList;
import se.kth.iv1350.pointOfSale.integration.TotalRevenueFileOutput;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;

/**
 * Handles all logic concerning the exchange of cash at the conclusion of a <code>Sale</code>.
 */
public class Register {

    private double balance;
    private double change;
    private final ArrayList<RegisterObserver> registerObservers;
    private static final Register INSTANCE_OF_REGISTER = new Register();
        
    /**
     * Creates a <code>Register</code> singleton.
     */
   private Register() {
        this.balance = 0;
        this.registerObservers = new ArrayList<>();
    }
   
   /**
    * Get the only existing instane of this singleton.
    * @return the <code>Register</code>
    */
   public static Register getRegister(){
   return INSTANCE_OF_REGISTER;
    }
   
    /**
     * Calculate eventual <code>change</code> after payment is received.
     * @param saleState the final state of the <code>Sale</code>, ready to be concluded.
     * @param paymentReceived amount received from customer must be >= the total price.
     * @return information about the exchanged cash.
     */
    public PaymentInfoDTO calculateChange(SaleStateDTO saleState, double paymentReceived) {
        double totalPrice = saleState.getRunningTotal();
        change = (paymentReceived - totalPrice);
        
        updateBalance(totalPrice);
        PaymentInfoDTO paymentInfo = new PaymentInfoDTO(totalPrice, paymentReceived, change);            
        return paymentInfo;
    }
        
    /**
     * Updates the <code>balance</code> in the <code>Register</code> after concluded <code>Sale</code>.
     * @param totalPrice amount to update balance with
     */
    private void updateBalance(double totalPrice) {
            balance = balance + totalPrice;
            notifyObservers();
    }
    
    /**
     * Adds the indicated observer to be notified when the <code>Register</code> 
     * balance has changed.
     * @param observer The observer that will be notified when the Register 
     * balance changes
     */
    public void addRegisterObserver(RegisterObserver observer){
       registerObservers.add(observer);
    }
    
    private void notifyObservers(){
        for(RegisterObserver observer : registerObservers){
            observer.notifyObserversBalanceHasChanged(this.balance);
            
        }
    }
    

}