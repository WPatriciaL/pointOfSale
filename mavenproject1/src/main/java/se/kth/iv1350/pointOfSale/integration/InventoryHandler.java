package se.kth.iv1350.pointOfSale.integration;

import java.util.ArrayList;
import se.kth.iv1350.pointOfSale.model.Item;
import se.kth.iv1350.pointOfSale.model.SaleStateDTO;

/**
 *  A placeholder class representing an interface of an external system.
 * <code>fakeItemList</code> represents a fake list of items wich forms the layer
 * that <code>inventoryHandler</code> communicates with.
 */
public class InventoryHandler {
    
private final int lengthOfFakeItemList = 6;
    private final ItemDTO[] fakeItemList = new ItemDTO[lengthOfFakeItemList]; 
    /**
     * Creates an instance of the <code>InventoryHandler</code>.
     */
    public InventoryHandler() {
        fakeItemList[0] = new ItemDTO(0, 0, "nothing", "no description", 0, 0);
        fakeItemList[1] = new ItemDTO(1, 0, "Coffee", "perky", 25.0, 0.12);
        fakeItemList[2] = new ItemDTO(2, 0, "Fisherman's friend", "laxative", 12.5, 0.12);
        fakeItemList[3] = new ItemDTO(3, 0, "Book", "cool", 50.0, 0.06);
        fakeItemList[4] = new ItemDTO(4, 0, "Teddy", "greenish yellowish brown", 6.0, 0.25);
        fakeItemList[5] = new ItemDTO(5, 0, "Chocolate", "tasty", 20.0, 0.12);
    }
        /**
        * Gets information about the scanned item from the external inventory system.
        * Method will handle if identification number, <code>ItemIdentifier</code> ,is out of range.
        * 
        * @param itemRequest An <code>ItemDTO</code> with an identifier number that verifies the scanned item.
        * @return scannedItem An <code>ItemDTO</code> that contains all the stored information about the scanned item. 
        */     
	public ItemDTO createItemDTO(ItemDTO itemRequest) {
        int itemIdentifier = itemRequest.getIdentifier();
        ItemDTO scannedItem;

            if(itemIdentifier < 0 || itemIdentifier > 5)
                 return scannedItem = null;
            else
                 return scannedItem = new ItemDTO(itemIdentifier, itemRequest.getQuantity(), fakeItemList[itemIdentifier].getName(), fakeItemList[itemIdentifier].getDescription(), fakeItemList[itemIdentifier].getPrice(), fakeItemList[itemIdentifier].getRateOfVAT());
    }

     /**
     * Placeholder for the call to log all inventory changes in the external inventory system.
     * @param saleState the finalized info about the <code>Sale</code>.
     */
        
    public void sendInventoryInfo(SaleStateDTO saleState) {
        ArrayList<Item> listOfItemsBought = saleState.getListOfItems();
    }

}
