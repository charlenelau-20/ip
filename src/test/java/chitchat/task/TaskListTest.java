package chitchat.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chitchat.exception.ChitChatException;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testDeleteInvalidIndex() {
        assertThrows(ChitChatException.class, () -> taskList.deleteTask(0));
    }

    @Test
    void testMarkInvalidIndex() {
        assertThrows(ChitChatException.class, () -> taskList.markTask(0));
    }
}
