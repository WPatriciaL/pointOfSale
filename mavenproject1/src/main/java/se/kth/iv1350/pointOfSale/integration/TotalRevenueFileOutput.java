
package se.kth.iv1350.pointOfSale.integration;
import java.io.FileWriter;
import java.io.IOException;
import se.kth.iv1350.pointOfSale.model.RegisterObserversTemplate;


/**
 * Handles the logging to a file, of the total revenue since the start of the
 * application.
 */
public class TotalRevenueFileOutput extends RegisterObserversTemplate {
   
    /**
     * Creates an instance of the <code>TotalRevenueFileOutput</code>.
     */
    public TotalRevenueFileOutput(){

    }
    
     /**
     * Writes today's total revenue to a log file.
     * @param balance the current total income. 
     * @throws IOException if something went wrong when trying to write to the file.
     */
    @Override
    protected void doShowTotalIncome(double balance)throws IOException{ 
            FileWriter writer = new FileWriter("totalRevenue.txt", true);
            writer.append("Today's total revenue is: " + balance + " money units.\n");
            writer.close();
        }
    
     /**
     * Handles any thrown exceptions by printing the stack trace.
     * @param exception the thrown error to be handled.
     */
    @Override
    protected void handleErrors(Exception exception){
    exception.printStackTrace();
    }
 }

    
