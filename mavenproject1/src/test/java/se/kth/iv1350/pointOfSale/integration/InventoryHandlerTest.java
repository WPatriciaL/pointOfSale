package se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.controller.InvalidInputException;
import se.kth.iv1350.pointOfSale.model.Sale;

/**
 *
 *
 */
public class InventoryHandlerTest {
    private InventoryHandler inventoryHandler;
    private ItemDTO fakeItemDTO;
    private ItemDTO fakeITemOutOfRangeMin;
    private ItemDTO fakeITemOutOfRangeMax;
    
    public InventoryHandlerTest() {
    }

    @BeforeEach
    public void setUp() {
     inventoryHandler = new InventoryHandler();
     fakeItemDTO = new ItemDTO (1,1,null,null,0,0);
     fakeITemOutOfRangeMin = new ItemDTO (Integer.MIN_VALUE,1,null,null,0,0);
     fakeITemOutOfRangeMax = new ItemDTO (Integer.MAX_VALUE,1,null,null,0,0);
     
    }
   
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        fakeItemDTO = null;
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
                inventoryHandler.createItemDTO(forbiddenItemIdentifier);
                fail("Method did not return the expected exception.");
            } 
            catch (CouldNotReachDatabaseException couldNotReachDatabaseException) {
                  
            }
            catch (Exception exception){
                fail("Method throw the wrong exception.");
            }
}
    
    /**
     * 
     */
 @Test
    public void testDoesMaxIntegerInputGenerateItemNotFoundException(){
            try {
                inventoryHandler.createItemDTO(fakeITemOutOfRangeMax);
                fail("Function did not return an expected exception.");
            } catch (ItemNotFoundException itemNotFoundException) {
                boolean ItemIDExists = itemNotFoundException.getMessage().contains("" + fakeITemOutOfRangeMax.getIdentifier());
                assertTrue(ItemIDExists, "The method does not throw the expected exception. ");
            }
}
    
     /**
      * 
      */
    @Test
    public void testDoesMinIntegerInputGenerateItemNotFoundException(){
            try {
                inventoryHandler.createItemDTO(fakeITemOutOfRangeMin);
                fail("Function did not return the expected exception.");
            } catch (ItemNotFoundException itemNotFoundException) {
                boolean ItemIDExists = itemNotFoundException.getMessage().contains("" + fakeITemOutOfRangeMin.getIdentifier());
                assertTrue(ItemIDExists, "The method does not throw the expected exception. ");
            }
}
    /**
     * 
     */
    
    @Test
    public void testCreateItemDTO() {
        
        try {
            ItemDTO receivedItemDTO = inventoryHandler.createItemDTO(fakeItemDTO);
            assertNotNull(receivedItemDTO, "Method did not return correct instances of object ");
        } catch (ItemNotFoundException e) {
            
                   
        }
       
    }
   
    @Test
    public void testCreateItemDTORateOfVAT(){
        
        try {
            ItemDTO receivedItemDTO = inventoryHandler.createItemDTO(fakeItemDTO);
            double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
            assertTrue(receivedRateOfVAT == 0.25 ||receivedRateOfVAT == 0.12 ||receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
        } catch (ItemNotFoundException e) {
           
        }  
    }

}