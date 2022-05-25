
package se.kth.iv1350.pointOfSale.util;
        
import java.io.FileWriter;
import java.io.IOException;

/**
 * Template superclass for objects that logs messages to files.
 */
public abstract class FileOutputter {

    private final String logName;

    /**
     * Creates an instance.
     */
    protected FileOutputter(){
        this.logName = generateLogName();
    }

    /**
     * Enters a message into the subclass' logfile. 
     */
    public void createLogEntry(){ 
            try {
            FileWriter writer = new FileWriter(logName, true);
            writer.append(addMessage());
            writer.close();

        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    /**
     * Gets an Object-specific message to print.
     * @return the message to print to the log.
     */
    protected abstract String addMessage();


    /**
     * Gets the name of the txt file to log to.
     * @return the name of the txt file to log to.
     */
    protected abstract String generateLogName();

}