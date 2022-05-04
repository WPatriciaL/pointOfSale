package se.kth.iv1350.pointOfSale.integration;

import java.util.ArrayList;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;
import se.kth.iv1350.pointOfSale.model.PaymentInfoDTO;
/**
 * 
 * Handles the communication to the system that logs all <code>Sale</code>.
 */
public class SalesLog {

	private AccountingHandler accountingHandler;
        private InventoryHandler inventoryHandler;
        private ArrayList<PaymentInfoDTO> paymentLog;
        private ArrayList<SaleStateDTO> salesLog;
        
        
        /**
         * Creates an instance of a <code>SalesLog</code>.
         * @param accountingHandler Handler of an external accounting system.
         * @param inventoryHandler  Handler of an external inventory system.
         */
	public SalesLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
            this.accountingHandler = accountingHandler;
            this.inventoryHandler = inventoryHandler;
           this.paymentLog = new ArrayList<>() ;
           this.salesLog = new ArrayList<>() ;
	}
       
        /**
         * Logs the concluded <code>Sale</code> and updates the external inventory and accouting system.
         * @param saleStateDTO final state of the <code>Sale</code>, ready to be concluded. 
         * @param paymenInfoDTO contain total price and the exchange of cash for the concluded <code>Sale</code>
         */
	public void logSale(SaleStateDTO saleStateDTO, PaymentInfoDTO paymenInfoDTO) {
            accountingHandler.sendAccountingInfo(paymenInfoDTO);
            inventoryHandler.sendInventoryInfo(saleStateDTO);
            paymentLog.add(paymenInfoDTO);
            salesLog.add(saleStateDTO);
	}

}
