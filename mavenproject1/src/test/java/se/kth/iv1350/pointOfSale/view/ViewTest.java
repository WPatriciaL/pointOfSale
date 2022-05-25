package se.kth.iv1350.pointOfSale.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.AccountingHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.model.ReceiptDTO;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;

/**
 *
 * 
 */
public class ViewTest {
    private InventoryHandler inventoryHandler;
    private DiscountRegister discountRegister;
    private AccountingHandler accountingHandler;
    private SalesLog salesLog;
    private Controller controller;
    private View view; 
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ItemDTO itemRequest;
    private ItemDTO anotherItemRequest;
    private SaleStateDTO saleState;
    private ReceiptDTO receipt;
   
    @BeforeEach
    public void setUp() {
        Register register = Register.getRegister();
        inventoryHandler = new InventoryHandler();
        discountRegister = new DiscountRegister();
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        controller = new Controller(salesLog, inventoryHandler, discountRegister,register);
        view = new View (controller);
        
        System.setOut(new PrintStream(outContent));
        controller.startNewSale();
        saleState = null;
        itemRequest = new ItemDTO(4, 1, null, null, 0, 0);
        anotherItemRequest = new ItemDTO(3, 1, null, null, 0, 0);
       
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        discountRegister = null;
        accountingHandler = null;
        salesLog = null;
        controller = null;
        view = null;
        saleState = null;
        itemRequest = null;
        anotherItemRequest = null;
        receipt = null;
        
        System.setOut(originalOut);
    }

    /**
     * Test of the <code>printExceptionMessageToScreen</code> method.
     * Test if the right exception message is printed to the screen when an <code>InvalidInputException</code> is caught. 
     */
    @Test
    public void testPrintInvalidInputExceptionMessageToScreen() {
        String expected = "ERROR: You've entered something wrong. Please try again."; 
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainInvalidExceptionMessage = output.contains(expected);
        
        assertTrue(outputContainInvalidExceptionMessage, "The output does not contain the expected exception message.");
    }
    
    
    /**
     * Test of the <code>printExceptionMessageToScreen</code> method.
     * Test if the right exception message is printed to the screen when an <code>ConnectionException</code> is caught. 
     */
    @Test
    public void testPrintConnectionExceptionMessageToScreen() {
        String expected = "ERROR: Something went wrong with the connection. Please try again."; 
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainConnectionExceptionMessage = output.contains(expected);
        
        assertTrue(outputContainConnectionExceptionMessage, "The output does not contain the expected exception message.");
    }
    
    
     /**
     * Test of the <code>printSaleInfoToScreen</code> method.
     * Tests if the expected name of a given <code>Item</code> is printed to the screen.
     */
    @Test
    public void testPrintItemNameToScreen() {
        try {
            saleState = controller.nextItem(itemRequest);
        } 
        catch (Exception exception) {
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedName = saleState.getScannedITemDTO().getName(); 
        
        view.viewExecute();
        
        String output = outContent.toString();
        
        boolean outputContainsName = output.contains(expectedName);
        assertTrue(outputContainsName, "The output does not contain the expected name.");

    }
   
     /**
     * Test of the <code>printSaleInfoToScreen</code> method.
     * Test if the expected description of a given <code>Item</code> is printed to the screen.
     */
    @Test
    public void testPrintItemDescriptionToScreen() {
        try {
            saleState = controller.nextItem(itemRequest);
        } 
        catch (Exception exception) {
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedDescription = saleState.getScannedITemDTO().getDescription(); 
        
        view.viewExecute();
        
        String output = outContent.toString();
        
        boolean outputContainsDescription = output.contains(expectedDescription);
        assertTrue(outputContainsDescription, "The output does not contain the expected description.");
    }
    
    
    /**
     * Test of the <code>printSaleInfoToScreen</code> method.
     * Test if the expected price of a given item is printed to the screen.
     */
    @Test
    public void testPrintItemPriceToScreen() {
        try {
            saleState = controller.nextItem(itemRequest);
        } 
        catch (Exception e) {
            fail("Some exception was thrown when it wasn't expected");
        }
        
        String expectedPrice =""+ saleState.getScannedITemDTO().getPrice(); 
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsPrice = output.contains(expectedPrice);
        assertTrue(outputContainsPrice, "The output does not contain the expected price.");
    }
    
    /**
     * Test of the <code>printSaleInfoToScreen</code> method.
     * Test if the expected total VAT of the current<code>Sale</code> is printed to the screen.
     */
    @Test
    public void testPrintTotalVatToScreen() {
        try {
            saleState = controller.nextItem(itemRequest);
        } 
        catch (Exception e) {
            fail("Some exception was thrown when it wasn't expected");
        }
        
        String expectedVAT =""+ saleState.getTotalVAT(); 
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsVAT = output.contains(expectedVAT);
        assertTrue(outputContainsVAT, "The output does not contain the expected VAT.");
    }
    
    /**
     * Test of the <code>printSaleInfoToScreen</code> method.
     * Test if the expected running total of the current<code>Sale</code> is printed to the screen.
     */
    @Test
    public void testPrintRunningTotalToScreen() {
        try {
            saleState = controller.nextItem(itemRequest);
        } 
        catch (Exception e) {
            fail("Some exception was thrown when it wasn't expected");
        }
        
        String expectedRunningTotal =""+ saleState.getRunningTotal(); 
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsRunningTotal = output.contains(expectedRunningTotal);
        assertTrue(outputContainsRunningTotal, "The output does not contain the expected VAT.");
    }
    
    
    /**
     * Test of the <code>printReceiptToScreen</code> method.
     * Test if the right date and time for the concluded sale is printed correctly to the screen.
     */
    @Test
    public void testViewIfReceiptDateAndTimeIsPrintedCorrectly(){
        try{
            double receivedpayment = 200;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(receivedpayment);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedDateAndTime = "" + receipt.getTimeForSale();

        view.viewExecute();

        String output = outContent.toString();
        boolean outputContainsDateAndTime = output.contains(expectedDateAndTime);
        assertTrue(outputContainsDateAndTime, "The date and time for the concluded sale is not printed to System.out");
    }
    
    /**
     * Test of the <code>printReceiptToScreen</code> method.
     * Test if the total price from the <code>Sale</code> is printed correctly to the screen.
     */
    @Test
    public void testViewIfReceiptTotalPriceIsPrintedCorrectly(){
        try{
            double receivedpayment = 200;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(receivedpayment);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedTotalPrice = "" + receipt.getTotalPrice();

        view.viewExecute();

        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedTotalPrice);
        assertTrue(outputContainsMessage, "Total price at concluded sale is not printed to System.out");
    }
    
     /**
     * Test of the <code>printReceiptToScreen</code> method.
     * Test if the discount from the <code>Sale</code> is printed correctly to the screen. 
     */
    @Test
    public void testViewIfReceiptDiscountIsPrintedCorrectly(){
        try{
            double receivedPayment = 200;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(receivedPayment);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedDiscount = "" + receipt.getDiscount();

        view.viewExecute();

        String output = outContent.toString();
        boolean outputContainsDiscount = output.contains(expectedDiscount);
        assertTrue(outputContainsDiscount, "Total discount at concluded sale is not printed to System.out");
    }
    
    /**
     * Test of the <code>printReceiptToScreen</code> method.
     * Test if the received payment is printed correctly to the screen. 
     */
    @Test
    public void testViewIfReceiptReceivedPaymentIsPrintedCorrectly(){
        try{
            double receivedPayment = 200;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(receivedPayment);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedReceivedPayment = "" + receipt.getReceivedPayment();

        view.viewExecute();

        String output = outContent.toString();
        boolean outputContainsReceivedPayment= output.contains(expectedReceivedPayment);
        assertTrue(outputContainsReceivedPayment, "Payment received at concluded sale is not printed to System.out");
    }
    
    /**
     * Test of the <code>printReceiptToScreen</code> method.
     * Test if the expected change is printed correctly to the screen. 
     */
    @Test
    public void testViewIfReceiptChangeIsPrintedCorrectly(){
        try{
            double receivedPayment = 200;
            controller.nextItem(itemRequest);
            controller.nextItem(anotherItemRequest);
            receipt = controller.concludeSale(receivedPayment);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedChange = "" + receipt.getChange();

        view.viewExecute();

        String output = outContent.toString();
        boolean outputContainsChange = output.contains(expectedChange);
        assertTrue(outputContainsChange, "Total change at concluded sale is not printed to System.out");
    }
}

