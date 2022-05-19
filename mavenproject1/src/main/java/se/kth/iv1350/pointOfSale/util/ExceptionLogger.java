package se.kth.iv1350.pointOfSale.util;

import java.time.LocalDateTime;


/**
 * Writes a log given certain exceptional states. 
 */
public class ExceptionLogger extends FileOutputter{

    private Exception exception;

    /**
     * Writes the date and time, as well as the exception message to the log.
     * @param exception any exception to add to the exception log.
     */
    public void logException(Exception exception){
        this.exception = exception;
        createLogEntry();
    }

    /**
     * Gets an Object-specific message to print.
     * @return the message to print to the log.
     */
    @Override
    protected String addMessage() {
        return LocalDateTime.now().toString() + ": " + exception.getMessage() + " \n";
    }

    /**
     * Gets the name of the txt file to log to.
     * @return the name of the txt file to log to.
     */
    @Override
    protected String generateLogName() {
        return "exceptionLog.txt";
    }
}