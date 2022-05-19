
package se.kth.iv1350.pointOfSale.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




class ObserverTester implements RegisterObserver{
    
    
    @Override
    public void notifyObserversBalanceHasChanged(double balance) {
        System.out.println("works");
    }
}
/**
 *
 * 
 */
public class RegisterTest {
    Register register;
    SaleStateDTO saleStateDTO;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
       register = Register.getRegister();
       saleStateDTO = new SaleStateDTO(null, 0, 50, null);
       System.setOut(new PrintStream(outContent)); 
    }
    
    @AfterEach
    public void tearDown() {
        register = null;
        saleStateDTO = null;
         System.setOut(originalOut);
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

 /**
     * Test of addRegisterObserver method. Tests if a <code>RegisterObserver</code> actually is added to the list.
     */
    @Test
    public void testIsAnObserverAdded (){

        ObserverTester observerTester = new ObserverTester();
        register.addRegisterObserver(observerTester);

        testCalculateChange();

        String output = outContent.toString();
        boolean outputContainsBalance = output.contains("works");
        assertTrue(outputContainsBalance, "The observer wasn't added to registerObserver");
    }
    }
  

