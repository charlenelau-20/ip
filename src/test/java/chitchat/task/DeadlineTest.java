package chitchat.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chitchat.exception.ChitChatException;

public class DeadlineTest {
    @Test
    void testInvalidDateFormat() {
        assertThrows(ChitChatException.class, () -> {
            new Deadline("test task", "25 feb 2025 6pm");
        });
    }
}
