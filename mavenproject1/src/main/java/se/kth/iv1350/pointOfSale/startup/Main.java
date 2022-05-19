package se.kth.iv1350.pointOfSale.startup;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.view.View;
import se.kth.iv1350.pointOfSale.integration.SalesLog;
import se.kth.iv1350.pointOfSale.integration.AccountingHandler;
import se.kth.iv1350.pointOfSale.integration.InventoryHandler;
import se.kth.iv1350.pointOfSale.integration.DiscountRegister;
import se.kth.iv1350.pointOfSale.integration.TotalRevenueFileOutput;
import se.kth.iv1350.pointOfSale.model.Register;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;
/**
 * Start the entire application, containing the <code>Main</code> method used to start the application.
 * 
 */
public class Main {
    
/**
 *  Method used to start the application.
 * @param args  The application does not take any command-line parameters
 */
	

	public static void main(String [] args) {
		AccountingHandler accountingHandler = new AccountingHandler();
                InventoryHandler inventoryHandler = new InventoryHandler();
                SalesLog salesLog = new SalesLog(accountingHandler,inventoryHandler);
                DiscountRegister discountRegister = new DiscountRegister();
                Register register = Register.getRegister();
                TotalRevenueView totalRevenueView = new TotalRevenueView();
                register.addRegisterObserver(totalRevenueView);
                TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
                register.addRegisterObserver(totalRevenueFileOutput);
                
                Controller controller = new Controller(salesLog,inventoryHandler, discountRegister,register);
                View view = new View(controller);    
                view.viewExecute();
	}
}
