//Solution below adapted from ChatGPT
package chitchat.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import chitchat.task.Deadline;
import chitchat.task.Event;
import chitchat.task.Task;
import chitchat.task.TaskList;
import chitchat.task.Todo;

/**
 * Manages the loading and saving of tasks to and from a file ('chitchat.txt').
 */
public class Storage {
    private String filePath = "./data/chitchat.txt";

    /**
     * Initializes a Storage object with given file path.
     * The file path specifies where the task data is saved to or loaded from.
     *
     * @param filePath Path to the file where the tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.trim().isEmpty() : "File path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file specified in the file path.
     *
     * @param tasks List of tasks to be saved.
     * @throws IOException If there is a problem writing to the file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        assert tasks != null : "Task list should not be null";

        ArrayList<Task> taskList = tasks.getTasks();

        assert taskList != null : "taskList should not be null";

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                assert task != null : "Individual tasks should not be null";
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line of text from the file into a chitchat.task.Task object when loading the task list.
     * If the line is not in the correct format, null is returned.
     *
     * @param line Line of text to be parsed.
     * @return Task object if line is in the correct format or null if in incorrect format.
     */
    private Task parseTasks(String line) {
        assert line != null : "Line for parsing should not be null";
        assert !line.trim().isEmpty() : "Line for parsing should not be empty";

        try {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new Deadline(description, by, isDone);
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime to = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new Event(description, from, to, isDone);
            default:
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: Incorrect file format. Skipping entry...");
            return null;
        }
    }

    /**
     * Loads the list of tasks from the file in the specified file path.
     * If the file doesn't exist, an empty list is returned.
     *
     * @return List of Task objects loaded from the file.
     * @throws IOException If there is a problem reading from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        assert filePath != null : "File path should not be null";
        assert !filePath.trim().isEmpty() : "File path should not be empty";

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTasks(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }
}
