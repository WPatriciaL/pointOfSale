
package se.kth.iv1350.pointOfSale.controller;

/**
 *Exception is thrown when the input from the user is invalid.
 * 
 */
public class InvalidInputException extends Exception {
    
    
    /**
     *an instance of <code>InvalidInputException</code>.
     * @param originalException the original exception that was caught.
     */
    public InvalidInputException(Exception originalException){
        super("ERROR: You've entered something wrong. Please try again.", originalException);
       
        
    
    
    }
    
}
