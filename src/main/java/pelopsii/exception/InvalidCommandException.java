package pelopsii.exception;

/**
 * Custom exception class for the Pelops II application.
 * Extends the standard Exception class.
 */
public class InvalidCommandException extends PelopsIIException {

    /**
     * Constructs a InvalidCommandException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}