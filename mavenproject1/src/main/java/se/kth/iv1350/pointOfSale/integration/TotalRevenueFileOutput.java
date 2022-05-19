
package se.kth.iv1350.pointOfSale.integration;
import se.kth.iv1350.pointOfSale.model.RegisterObserver;
import se.kth.iv1350.pointOfSale.util.FileOutputter;


/**
 * Handles the logging to a file, of the total revenue since the start of the
 * application.
 */
public class TotalRevenueFileOutput extends FileOutputter implements RegisterObserver{

    private double balance;

    /**
     * Creates an instance of the <code>TotalRevenueFileOutput</code>.
     */
    public TotalRevenueFileOutput(){

    }

    /**
     * This listener is made aware that the total revenue has changed.
     * @param balance the new total income.
     */
    @Override
    public void notifyObserversBalanceHasChanged(double balance) {
        this.balance = balance;
        createLogEntry();
    }

    /**
     * Gets an Object-specific message to print.
     * @return the message to print to the log.
     */
    @Override
    protected String addMessage(){
        return "Today's total revenue is: " + balance + " money units.\n";
    }

    /**
     * Gets the name of the txt file to log to.
     * @return the name of the txt file to log to.
     */
    
    @Override
    protected String generateLogName(){
        return "totalRevenue.txt";
    }
}