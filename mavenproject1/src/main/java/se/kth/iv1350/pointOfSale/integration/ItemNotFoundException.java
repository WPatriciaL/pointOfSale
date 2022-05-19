
package se.kth.iv1350.pointOfSale.integration;
import java.lang.Exception;

/**
 *Exception is thrown when an <code>ItemDTO</code> identifier does not match an identifier in the inventory database.
 * 
 */
public class ItemNotFoundException extends Exception {
  
    /**
     * Creates an instance of ItemNotFoundException
     * @param itemRequest the scanned item with an ID that could not be found. 
     */
    
    public ItemNotFoundException (ItemDTO itemRequest){
        super("ERROR: Item with ID:" + itemRequest.getIdentifier() + " could not be found");
       
    }
    
    
}
