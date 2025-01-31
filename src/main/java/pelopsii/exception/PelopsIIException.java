package pelopsii.exception;

/**
 * Custom exception class for the Pelops II application.
 * Extends the standard Exception class.
 */
public class PelopsIIException extends Exception {

    /**
     * Constructs a PelopsIIException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public PelopsIIException(String message) {
        super(message);
    }
}