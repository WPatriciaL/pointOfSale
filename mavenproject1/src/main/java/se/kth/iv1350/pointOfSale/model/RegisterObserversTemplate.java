
package se.kth.iv1350.pointOfSale.model;

/**
 *Template superclass for objects that output the total revenue. This template observes the <code>Register</code>.
 * 
 */
public abstract class RegisterObserversTemplate implements RegisterObserver{
    
    
     /*
     * This listener is made aware that the total revenue has changed.
     * @param balance the new total income.
     */  
    @Override
    public void notifyObserversBalanceHasChanged(double balance) { 
        showTotalIncome(balance);
    }

    
    private void showTotalIncome(double balance) { 
        try {
            doShowTotalIncome (balance);
    } 
        catch (Exception exception) {
            handleErrors(exception); }
    }

    
    /**
     * Shows the total income, the current balance in the <code>Regiser</code>.
     * @param balance The current total income.
     * @throws Exception 
     */
    protected abstract void doShowTotalIncome(double balance) throws Exception;

    /**
     * Handles exception thrown.
     * @param exception any exception to be handled. 
     */
    protected abstract void handleErrors(Exception exception);
    
}

