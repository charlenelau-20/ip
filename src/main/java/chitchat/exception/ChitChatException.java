package chitchat.exception;

/**
 * Custom exception class for handling errors in ChitChat.
 */
public class ChitChatException extends Exception {

    /**
     * Constructs a ChitChatException with a specified error message.
     *
     * @param message Error message.
     */
    public ChitChatException(String message) {
        super(message);
    }
}
