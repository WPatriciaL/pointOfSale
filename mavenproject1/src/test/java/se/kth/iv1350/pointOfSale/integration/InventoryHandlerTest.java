package se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.model.Sale;

/**
 *
 *
 */
public class InventoryHandlerTest {
    private InventoryHandler inventoryHandler;
    private ItemDTO fakeItemDTO;
    private ItemDTO fakeITemOutOfRange;
    
    public InventoryHandlerTest() {
    }

    @BeforeEach
    public void setUp() {
     inventoryHandler = new InventoryHandler();
     fakeItemDTO = new ItemDTO (1,1,null,null,0,0);
     fakeITemOutOfRange = new ItemDTO (Integer.MIN_VALUE,1,null,null,0,0);
     
    }
   
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        fakeItemDTO = null;
        fakeITemOutOfRange = null;
    }

    @Test
    public void testCreateItemDTO() {
        ItemDTO receivedItemDTO = inventoryHandler.createItemDTO(fakeItemDTO);
        assertNotNull(receivedItemDTO, "Method did not return correct instances of object ");
    }
    
    @Test
    public void testCreateItemDTOWithIdentifierOutOfRange(){
    ItemDTO receivedItemDTO = inventoryHandler.createItemDTO(fakeITemOutOfRange);
            assertNull(receivedItemDTO, "Method return instances of object from invalid identifier number");
    }
       
    @Test
    public void testDoesConstructItemFailsWhenIDOutsideRange(){
        ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
        ItemDTO scannedItem = inventoryHandler.createItemDTO(itemRequest);

        assertNull(scannedItem, "ItemDTO contains data even though it shouldn't");
    }
    
    @Test
    public void testCreateItemDTORateOfVAT(){
    ItemDTO receivedItemDTO = inventoryHandler.createItemDTO(fakeItemDTO);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 ||receivedRateOfVAT == 0.12 ||receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
    }

   
}
