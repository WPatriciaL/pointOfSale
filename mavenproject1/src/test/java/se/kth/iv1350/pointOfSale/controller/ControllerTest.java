
package se.kth.iv1350.pointOfSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.integration.AccountingHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.model.CheckOutCart;
import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;

/**
 *
 *
 */
public class ControllerTest {
    Controller controller;
    CheckOutCart checkOutCart;
    InventoryHandler inventoryHandler;
    DiscountRegister discountRegister;
    SalesLog salesLog;
    AccountingHandler accountingHandler;
    
    
    Sale sale;
    ItemDTO itemRequest;
    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        discountRegister = new DiscountRegister();
        itemRequest = new ItemDTO(5, 1, null, null, 0, 0);
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        controller = new Controller(salesLog, inventoryHandler, discountRegister);
        controller.startNewSale();
      
       
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkOutCart = null;
        discountRegister = null;
        accountingHandler = null;
        salesLog = null;
        controller = null;
        itemRequest = null;
    }
    /**
     * Test of nextItem method, of class Controller.
     */
    @Test
    public void tesIsRightItemReturned() {
        ItemDTO dto = new ItemDTO(5, 1, "Chocolate", "tasty", 20.0, 0.12);
        String expectedValue = new Item(dto).getName();
        
        SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
        String actual = saleStateDTO.getListOfItems().get(0).getName();
        assertEquals(expectedValue, actual, "Fail ");
        
      
    }
     @Test
    public void testIsRunningTotalReturned() {
       
        double expectedValue = 2 * (22.4); 
        SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
        saleStateDTO = controller.nextItem(itemRequest);
        
        double actual = saleStateDTO.getRunningTotal();
        
        assertEquals(expectedValue, actual, "The returned total price is not the same as the expected");    
    }
    
         @Test
    public void testIsVATReturnedCorrectly() {
       
        double expectedValue = 2.4; 
        SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
        double actual = saleStateDTO.getTotalVAT();
        assertEquals(expectedValue, actual, "The returned total VAT is not the same as the expected");    
    }


    
}
