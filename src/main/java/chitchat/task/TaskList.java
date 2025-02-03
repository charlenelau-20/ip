package chitchat.task;

import java.util.ArrayList;

import chitchat.exception.ChitChatException;
import chitchat.ui.Ui;

/**
 * Handles operations involving the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a chitchat.task.TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a chitchat.task.TaskList object with a preloaded task list.
     *
     * @param loadedTasks List of tasks loaded from the storage file.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task chitchat.task.Task to be added to task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to be deleted.
     * @throws ChitChatException If the index is out of bounds.
     */
    public void deleteTask(int index) throws ChitChatException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChitChatException("Please enter a valid task number!");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @throws ChitChatException If the index is out of bounds.
     */
    public void markTask(int index) throws ChitChatException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChitChatException("Please enter a valid task number!");
        }
        tasks.get(index).setDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index Index of the task to be marked as not done.
     * @throws ChitChatException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws ChitChatException {
        if (index < 0 || index >= tasks.size()) {
            throw new ChitChatException("Please enter a valid task number!");
        }
        tasks.get(index).setNotDone();
    }

    /**
     * Lists the tasks in the task list.
     *
     * @param ui chitchat.ui.Ui instance used to display the tasks.
     */
    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showLine();
            System.out.println("Your task list is empty!");
            ui.showLine();
        } else {
            ui.showLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            ui.showLine();
        }
    }

    /**
     * Checks if task list is empty.
     * Returns true if empty and false otherwise.
     *
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves and returns the task list.
     *
     * @return chitchat.task.Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
