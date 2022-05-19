package se.kth.iv1350.pointOfSale.model;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.integration.CouldNotReachDatabaseException;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.ItemNotFoundException;


public class CheckOutCartTest {
    
    InventoryHandler inventoryHandler; 
    CheckOutCart checkoutCart;
    private ItemDTO fakeITemOutOfRangeMin;
    private ItemDTO fakeITemOutOfRangeMax;
    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        checkoutCart = new CheckOutCart(inventoryHandler);
        fakeITemOutOfRangeMin = new ItemDTO (Integer.MIN_VALUE,1,null,null,0,0);
        fakeITemOutOfRangeMax = new ItemDTO (Integer.MAX_VALUE,1,null,null,0,0);
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkoutCart = null;
        fakeITemOutOfRangeMin = null;
        fakeITemOutOfRangeMax = null;
        
    }

    
     /**
     * Test if the right exception is thrown when the method receives a "forbidden item", 
     * which represents a state where the database cannot be accessed.
     */
         @Test
        public void testForbiddenItemIDGenerateCouldNotReachDatabaseException(){
            ItemDTO forbiddenItemIdentifier = new ItemDTO(404, 1, null, null, 0, 0);
                try {
                    checkoutCart.addItem(forbiddenItemIdentifier);
                    fail("Method did not return the expected exception.");
            } 
                catch (CouldNotReachDatabaseException couldNotReachDatabaseException) {
                    couldNotReachDatabaseException.printStackTrace();
            }
                catch (Exception exception){
                    fail("Method throw the wrong exception.");
            }
                
}

     @Test
    public void testDoesMaxIntegerInputGenerateItemNotFoundException(){
            try {
                checkoutCart.addItem(fakeITemOutOfRangeMax);
                fail("Function did not return the expected exception.");
            } catch (ItemNotFoundException itemNotFoundException) {
                boolean ItemIDExists = itemNotFoundException.getMessage().contains("" + fakeITemOutOfRangeMax.getIdentifier());
                assertTrue(ItemIDExists, "The method does not throw the expected exception. ");
            }
}
    
    @Test
    public void testDoesMinIntegerInputGenerateItemNotFoundException(){
            try {
                checkoutCart.addItem(fakeITemOutOfRangeMin);
                fail("Function did not return the expected exception.");
            } catch (ItemNotFoundException itemNotFoundException) {
                boolean ItemIDExists = itemNotFoundException.getMessage().contains("" + fakeITemOutOfRangeMin.getIdentifier());
                assertTrue(ItemIDExists, "The method does not throw the expected exception. ");
            }
}
    
    /**
     * Test of AddItem method, of class CheckoutCart. Tests if a valid ItemDTO is produced when given a valid input identifier.
     */
    @Test 
    public void testAddItemReturnCorrectly() {
        try {
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
        assertNotNull(scannedItem, "failed to return data in return object"); 
        
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
       
    }
    
    /**
     * Test of AddItem method. Tests if the returned ItemDTO gives a valid rateOfVAT.
     */
    @Test
    public void testAddItemDTORateOfVAT(){
        
        try {
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO receivedItemDTO = checkoutCart.addItem(itemRequest);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 || receivedRateOfVAT == 0.12 || receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
       
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }
    
}