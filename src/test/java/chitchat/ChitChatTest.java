package chitchat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChitChatTest {
    private ChitChat chitChat;

    @BeforeEach
    void setUp() {
        chitChat = new ChitChat("data/test-chitchat.txt");
    }

    @Test
    void testChitChatInitialization() {
        // check that ChitChat initializes correctly
        assertNotNull(chitChat, "ChitChat instance should not be null");
    }

    @Test
    void testStorageLoadFailure() {
        // check that ChitChat still initializes properly with invalid file path
        chitChat = new ChitChat("invalid/path.txt");
        assertNotNull(chitChat, "ChitChat should still initialize when file fails to load");
    }
}
