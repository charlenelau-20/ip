package chitchat.exception;

/**
 * Custom exception class for handling errors in chitchat.ChitChat.
 */
public class ChitChatException extends Exception {

    /**
     * Constructs a chitchat.exception.ChitChatException with a specified error message.
     *
     * @param message Error message.
     */
    public ChitChatException(String message) {
        super(message);
    }
}
