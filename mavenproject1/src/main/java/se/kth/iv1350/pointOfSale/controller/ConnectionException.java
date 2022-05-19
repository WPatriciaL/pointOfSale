
package se.kth.iv1350.pointOfSale.controller;

/**
 * Exception is thrown when there is some connection error.
 */
public class ConnectionException extends RuntimeException{
    
    /**
     * an instance of <code>ConnectionException</code>.
     * @param originalException the original exception that was caught.
     */
    public ConnectionException(RuntimeException originalException){
        super("ERROR: Something went wrong with the connection. Please try again. ",originalException);
    
    }
    
}
