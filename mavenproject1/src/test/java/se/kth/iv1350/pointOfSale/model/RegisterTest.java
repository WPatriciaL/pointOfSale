
package se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;

/**
 *
 * 
 */
public class RegisterTest {
    Register register;
    SaleStateDTO saleStateDTO;
    
    public RegisterTest() {
    }

    @BeforeEach
    public void setUp() {
       register = new Register();
       saleStateDTO = new SaleStateDTO(null, 0, 50, null);
        
        
    }
    
    @AfterEach
    public void tearDown() {
        register = null;
        saleStateDTO = null;
    }
    

    /**
     * Test if the right amount of change is calculated correctly
     */
    @Test
    public void testCalculateChange() {
        double paymentReceived = 76.2;
        saleStateDTO = new SaleStateDTO(null, 0, 20, null);
        double actual = register.calculateChange(saleStateDTO, paymentReceived).getChange();
        double expResult = 56.2;
        assertEquals(expResult, actual, "The calculated change is incorrect");
    }
    
      /**
     * Test if the right amount of change is calculated correctly when given the maximum allowed amount
     */
    @Test
    public void testCalculateChangeIfPaymentIsHuge() {
        double paymentReceived = Double.MAX_VALUE;
        saleStateDTO = new SaleStateDTO(null, 0, 20, null);
        double actual = register.calculateChange(saleStateDTO, paymentReceived).getChange();
        double expResult = Double.MAX_VALUE - 20;
        assertEquals(expResult, actual, "The calculated change is incorrect");
    } 
    
    
}
