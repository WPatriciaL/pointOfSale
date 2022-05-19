
package se.kth.iv1350.pointOfSale.integration;

/**
 *Thrown when the inventory database could not be reached.
 * 
 */
public class CouldNotReachDatabaseException extends RuntimeException{
    
    /**
     * Creates an instance of <code>CouldNotReachDatabaseException</code>.
     */
    public CouldNotReachDatabaseException (){
        super("The database can't be reached.");
    
    }
    
}
