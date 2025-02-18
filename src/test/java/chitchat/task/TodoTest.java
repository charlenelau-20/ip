package chitchat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testTodoConstructor() {
        Todo todo = new Todo("test todo");
        assertEquals("[T][ ] test todo", todo.toString());
    }
}
