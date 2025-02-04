package chitchat.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chitchat.exception.ChitChatException;
import chitchat.task.Deadline;
import chitchat.task.Task;
import chitchat.task.TaskList;
import chitchat.task.Todo;

public class StorageTest {
    private static final String TEMP_FILE_PATH = "test_data/chitchat_test.txt";
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        storage = new Storage(TEMP_FILE_PATH);
        taskList = new TaskList();
    }

    @Test
    void testSaveTasks() throws ChitChatException, IOException {
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Deadline("return book", "2025-02-08 1800"));

        storage.saveTasks(taskList);

        // check that file exists after saving
        File file = new File(TEMP_FILE_PATH);
        assertTrue(file.exists(), "The file should exist after saving tasks");
    }

    @Test
    void testLoadTasks() throws ChitChatException, IOException {
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Deadline("return book", "2025-02-08 1800"));

        storage.saveTasks(taskList);

        ArrayList<Task> loadedTasks = storage.loadTasks();

        // check that tasks are loaded correctly
        assertEquals(taskList.getTasks().size(), loadedTasks.size(),
                "Size of loaded task list should equal size of saved task list");

        // check that task descriptions for saved and loaded tasks match
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            assertEquals(taskList.getTasks().get(i).toFileFormat(), loadedTasks.get(i).toFileFormat(),
                    "Saved tasks and loaded tasks should be the same");
        }
    }
}
