
package se.kth.iv1350.pointOfSale.model;

/**
 * Observer interface that informs classes that implements it that the <code>Register</code>
 * contains a new balance.
 */
public interface RegisterObserver {

    /**
     * Called by the observed <code>Register</code> when its contents have changed.
     * @param balance the changed balance implementing observers need to be informed of.
     */
    void notifyObserversBalanceHasChanged (double balance);

}