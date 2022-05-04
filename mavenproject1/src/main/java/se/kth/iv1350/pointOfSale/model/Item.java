package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.integration.ItemDTO;

/**
 * Represents an item to be bought in the <code>Sale</code> and added to the checkout cart.
 *
 */
public class Item {

        private int identifier;
        private double quantity;
        private String name;
        private String description;
        private double price;
        private double rateOfVAT;
	
        /**
         * Creates an instance of an<code>Item</code>.
         * @param scannedItem  A complete <code>ItemDTO</code> with relevant information. 
         */
	public Item(ItemDTO scannedItem) {
		  this.identifier = scannedItem.getIdentifier();
                  this.quantity = scannedItem.getQuantity();
                  this.name = scannedItem.getName();
                  this.description = scannedItem.getDescription();
                  this.price = scannedItem.getPrice();
                  this.rateOfVAT = scannedItem.getRateOfVAT();
	}
        /**
         * Set the new <code>quantity</code> of the <code>Item</code>.
         * @param quantity the new quantity of the item
         */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
         /**
         * Get the <code>identifier</code> of the <code>Item</code>.
         * @return identifier number of item
         */
	public int getIdentifier() {
		return identifier;
	}
        
        /**
         * Get the <code>quantity</code> of the <code>Item</code>.
         * @return quantity of item
         */
	public double getQuantity() {
		return quantity;
	}
        /*
         * Get the <code>name</code> of the <code>Item</code>
         * @return name of item
         */
    public String getName() {
        return name;
    }

         /*
         * Get a <code>description</code> of the <code>Item</code>
         * @return description of item
         */
    public String getDescription() {
        return description;
    }

        /*
         * Get the <code>price</code> of the <code>Item</code>
         * @return price of item
         */
    public double getPrice() {
        return price;
    }

        /*
         * Get the VAT rate of the <code>Item</code>
         * @return VAT rate of item
         */
    public double getRateOfVAT() {
        return rateOfVAT;
    }

        
}
