
package se.kth.iv1350.pointOfSale.integration;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;

/**
 *
 * @author patricialagerhult
 */
public class TotalRevenueFileOutputTest {
        private double balance;
        TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
        private final PrintStream originalOut = System.out;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public TotalRevenueFileOutputTest() {
    }
      
    @BeforeEach
     public void setUp() {
        balance = 100;
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
           totalRevenueFileOutput = null;
           balance = 0;
           System.setOut(originalOut);
    }

    /**
     * Test of notifyObserversBalanceHasChanged method, of class TotalRevenueFileOutput.
     * @throws 
     */
    @Test
    public void testNotifyObserversBalanceHasChanged(){
                StringBuilder sb = new StringBuilder();
                String bal = "" + balance; 
               totalRevenueFileOutput.notifyObserversBalanceHasChanged (balance);
        
            try {
                FileReader fileReader = new FileReader("totalRevenue.txt");
                int data = fileReader.read();
                while(data != -1){
                    data = fileReader.read();
                    sb.append((char) data);
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TotalRevenueFileOutputTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(IOException e){
            e.printStackTrace();
            }
            
           
            String output = sb.toString();
            
            boolean outputContainsBalance = output.contains(bal);
            assertTrue(outputContainsBalance, "The wrong balance is printed");
            
        
        
      
    }
    
}
