
package se.kth.iv1350.pointOfSale.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * 
 */
public class TotalRevenueViewTest {
    private double balance;
    TotalRevenueView totalRevenueView = new TotalRevenueView();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public TotalRevenueViewTest() {
    }
    
    @BeforeEach
    public void setUp() {
        balance = 100;
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
      totalRevenueView = null;
      System.setOut(originalOut);
    }

    /**
     * Test of notifyObserversBalanceHasChanged method, is the right balance printed to the console.
     */
    @Test
    public void testNotifyObserversBalanceHasChanged() {
        String bal = "" + balance; 

        totalRevenueView.notifyObserversBalanceHasChanged(balance);
        String output = outContent.toString();
        boolean outputContainsBalance = output.contains(bal);
        assertTrue(outputContainsBalance, "The wrong balance is printed");

    }
    
}
