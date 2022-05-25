
package se.kth.iv1350.pointOfSale.view;
import se.kth.iv1350.pointOfSale.model.RegisterObserversTemplate;

/*
 * Represents a screen for the user to view the current total revenue since the
 * start of the application.
 */
public class TotalRevenueView extends RegisterObserversTemplate{

    /*
     * Creates an instance of the <code>TotalRevenueView</code>
     */
    public TotalRevenueView(){

    }

    /**
     * Shows the total income, the current balance in the <code>Register<code> 
     * on the user interface.
     * @param balance the current total income.
     */
    @Override
    protected void doShowTotalIncome (double balance){
        System.out.println("\n ** Today's total revenue is: " + balance + " money units. **");
    }
    
    /**
     * Handles any thrown exceptions by printing the stack trace. 
     * @param exception the thrown error to be handled.
     */
    
    @Override
    protected void handleErrors(Exception exeption){
    exeption.printStackTrace();
    }


}
