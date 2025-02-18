package chitchat.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChitChatExceptionTest {
    @Test
    void testErrorMessage() {
        String errorMessage = "Test error message";
        ChitChatException exception = new ChitChatException(errorMessage);
        assertEquals(exception.getMessage(), errorMessage);
    }
}
