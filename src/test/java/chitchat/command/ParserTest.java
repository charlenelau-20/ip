package chitchat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chitchat.storage.Storage;
import chitchat.task.TaskList;
import chitchat.ui.Ui;

public class ParserTest {
    private static final String TEST_FILE_PATH = "test_data/chitchat_test.txt";
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    @BeforeEach
    void setUp() throws IOException {
        System.setOut(new PrintStream(outputStream));

        storage = new Storage(TEST_FILE_PATH);
        taskList = new TaskList();
        ui = new Ui();
        parser = new Parser(taskList, ui, storage);

        File file = new File(TEST_FILE_PATH);
        file.getParentFile().mkdirs();
        if (file.exists()) {
            Files.copy(file.toPath(), Path.of(TEST_FILE_PATH + ".bak"), StandardCopyOption.REPLACE_EXISTING);
            file.delete();
        }
    }

    @Test
    void testAddTodo() {
        parser.parseCommand("todo read book");

        // check that task list contains the task
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().get(0).toString().contains("read book"));
    }

    @Test
    void testAddDeadline() {
        parser.parseCommand("deadline return book /by 2025-02-08 1800");

        // check that task list contains the task
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().get(0).toString().contains("return book"));
    }

    @Test
    void testAddEvent() {
        parser.parseCommand("event book club /from 2025-02-09 1400 /to 2025-02-09 1600");

        // check that task list contains the task
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().get(0).toString().contains("book club"));
    }

    @Test
    void testMarkUnmarkTask() {
        parser.parseCommand("todo read book");
        parser.parseCommand("mark 1");

        // check that task has been marked as done
        assertTrue(taskList.getTasks().get(0).toString().contains("[X]"));

        parser.parseCommand("unmark 1");

        // check that task has been marked as not done
        assertFalse(taskList.getTasks().get(0).toString().contains("[X]"));
    }

    @Test
    void testDeleteTask() {
        parser.parseCommand("delete 3");

        // check that task list does not contain deleted task
        assertFalse(taskList.getTasks().toString().contains("book club"));
        // check that task list contains correct number of tasks
        assertEquals(0, taskList.size());
    }

    @Test
    void testInvalidCommand() {
        parser.parseCommand("blah");

        // check that output shows error message
        String output = outputStream.toString();
        assertTrue(output.contains("Sorry, you need to use keywords"));
    }
}