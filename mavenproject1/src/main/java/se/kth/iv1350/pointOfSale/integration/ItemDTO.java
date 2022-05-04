package se.kth.iv1350.pointOfSale.integration;
/**
 * Represent an <code>Item</code> that can be bought in the sale.
 * 
 */
public class ItemDTO {
        private int identifier;
        private double quantity;
        private String name;
        private String description;
        private double price;
        private double rateOfVAT;
        
 /**
 * Creates an instance of <code>ItemDTO</code>
 * 
 * @param identifier An unique number for each <code>ItemDTO</code>
 * @param quantity Amount of <code>ItemDTO</code> of the same type.
 * @param name The <code>ItemDTO</code> colloquial identifier.
 * @param description Describes valuable information about the <code>ItemDTO</code>. 
 * @param price The cost of the <code>ItemDTO</code>.
 * @param rateOfVAT The VAT rate of the <code>ItemDTO</code> where 1 is 100%.
 */
public ItemDTO (int identifier, double quantity, String name, String description, double price, double rateOfVAT) {
		
        this.identifier = identifier;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rateOfVAT = rateOfVAT;
                
               
	}
       
        /**
         * Get the <code>price</code> of the <code>ItemDTO</code>
         * @return price of item
         */
	public double getPrice() {
		return price;
	}
        
        /**
         * Get the <code>name</code> of the  <code>ItemDTO</code>
         * @return name of item
         */
	public String getName() {
		return name;
	}
        
        /**
         * Get a <code>description</code> of the  <code>ItemDTO</code>
         * @return description of item
         */
	public String getDescription() {
		return description;
	}
        
        /**
         * Get the VAT rate of the  <code>ItemDTO</code>
         * @return VAT rate of item
         */
	public double getRateOfVAT() {
		return rateOfVAT;
	}
        /**
         * Get the <code>identifier</code> number of the  <code>ItemDTO</code>
         * @return identifier number of item
         */
	public int getIdentifier() {
		return identifier;
	}
        
        /**
         * Get the  <code>quantity</code> of the  <code>ItemDTO</code>
         * @return quantity of item
         */
	public double getQuantity() {
		return quantity;
	}

}
