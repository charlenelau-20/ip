import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file specified in the file path.
     *
     * @param tasks List of tasks to be saved.
     * @throws IOException If there is a problem writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line of text from the file into a Task object when loading the task list.
     * If the line is not in the correct format, null is returned.
     *
     * @param line Line of text to be parsed.
     * @return Task object if line is in the correct format or null if in incorrect format.
     */
    private Task parseTasks(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case  "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                return new Event(description, parts[3], parts[4], isDone);
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
