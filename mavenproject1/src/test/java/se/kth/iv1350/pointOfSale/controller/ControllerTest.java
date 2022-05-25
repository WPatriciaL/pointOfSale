
package se.kth.iv1350.pointOfSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.integration.AccountingHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.model.CheckOutCart;
import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;

/**
 *
 *
 */
public class ControllerTest {
    Controller controller;
    Register INSTANCE_OF_REGISTER;
    CheckOutCart checkOutCart;
    InventoryHandler inventoryHandler;
    DiscountRegister discountRegister;
    SalesLog salesLog;
    AccountingHandler accountingHandler;
    private ItemDTO fakeITemOutOfRangeMin;
    private ItemDTO fakeITemOutOfRangeMax;
    
    
    Sale sale;
    ItemDTO itemRequest;
    
    @BeforeEach
    public void setUp() {
        Register register = Register.getRegister();
        inventoryHandler = new InventoryHandler();
        discountRegister = new DiscountRegister();
        itemRequest = new ItemDTO(5, 1, null, null, 0, 0);
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        controller = new Controller(salesLog, inventoryHandler, discountRegister,register);
        controller.startNewSale();
        fakeITemOutOfRangeMin = new ItemDTO (Integer.MIN_VALUE,1,null,null,0,0);
        fakeITemOutOfRangeMax = new ItemDTO (Integer.MAX_VALUE,1,null,null,0,0);
      
       
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
                    controller.nextItem(forbiddenItemIdentifier);
                    fail("Method did not return the expected exception.");
            } 
                catch (ConnectionException connectionException) {
                    connectionException.printStackTrace();
            }
                catch (Exception exception){
                    fail("Method throw the wrong exception.");
            }
                
}
     
 @Test
    public void testDoesMaxIntegerInputGenerateInvalidInputException(){
            try {
                controller.nextItem(fakeITemOutOfRangeMax);
                fail("Function did not return the expected exception.");
            } catch (InvalidInputException invalidInputException) {
                  invalidInputException.printStackTrace();
             
            }
            catch (Exception exception){
                
                fail("Method throw the wrong exception.");
            }
}

    
    @Test
    public void testDoesMinIntegerInputGenerateInvalidInputException(){
            try {
                controller.nextItem(fakeITemOutOfRangeMin);
                fail("Function did not return an expected exception.");
                
            } catch (InvalidInputException invalidInputException) {
               
            }
            catch (Exception exception){
                fail("Method throw the wrong exception.");
            }
}
    
    
    
    /**
     * Test of nextItem method, of class Controller.
     */
    @Test
    public void tesIsRightItemReturned() {
        ItemDTO dto = new ItemDTO(5, 1, "Chocolate", "tasty", 20.0, 0.12);
        String expectedValue = new Item(dto).getName();
        
        try {
            SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
            String actual = saleStateDTO.getListOfItems().get(0).getName();
            assertEquals(expectedValue, actual, "The returned item is not the same as expected ");
            
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
      
        
       
        
      
    }
     @Test
    public void testIsRunningTotalReturned() {
       
       
       
         try {   
            double expectedValue = 2 * (22.4); 
            SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
            saleStateDTO = controller.nextItem(itemRequest);
        
            double actual = saleStateDTO.getRunningTotal();
        
            assertEquals(expectedValue, actual, "The returned total price is not the same as the expected"); 
             
         } catch (InvalidInputException e) {
             
         }
 
    }
    
         @Test
    public void testIsVATReturnedCorrectly() {
       
             try {
                    double expectedValue = 2.4; 
                    SaleStateDTO saleStateDTO = controller.nextItem(itemRequest);
                    double actual = saleStateDTO.getTotalVAT();
                    assertEquals(expectedValue, actual, "The returned total VAT is not the same as the expected");  
                 
             } catch (InvalidInputException e) {
             }
   
    }


    
}
