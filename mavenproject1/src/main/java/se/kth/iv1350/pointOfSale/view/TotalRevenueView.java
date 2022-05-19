
package se.kth.iv1350.pointOfSale.view;
import se.kth.iv1350.pointOfSale.model.RegisterObserver;

/*
 * Represents a screen for the user to view the current total revenue since the
 * start the application.
 */
public class TotalRevenueView implements RegisterObserver {

    /*
     * Creates an instance of the <code>TotalRevenueView</code>
     */
    public TotalRevenueView(){

    }

    /*
     * This listener is made aware that the total revenue has changed.
     * @param balance the new total income.
     */
    @Override
    public void notifyObserversBalanceHasChanged(double balance) {
        showTotalRevenue(balance);
    }
    /*
     * Shows the total income, the current balance in the <code>Register<code> 
     * on the user interface.
     */
    private void showTotalRevenue(double balance){
        System.out.println("\n ** Today's total revenue is: " + balance + " money units. **");
    }


}
