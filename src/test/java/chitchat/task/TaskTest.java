package chitchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testGetStatusIcon() {
        Task task = new Task("test task");
        assertEquals(" ", task.getStatusIcon());
        task.setDone();
        assertEquals("X", task.getStatusIcon());
    }
}
