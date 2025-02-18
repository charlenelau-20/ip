package chitchat.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chitchat.exception.ChitChatException;

public class EventTest {
    @Test
    void testInvalidDateFormat() {
        assertThrows(ChitChatException.class, () -> {
            new Event("test event", "25 feb 2025 2pm", "25 feb 2025 4pm");
        });
    }
}
