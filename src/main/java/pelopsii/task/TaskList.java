package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pelopsii.exception.PelopsIIException;

/**
 * Represents a list of tasks.
 * Provides methods to manage and manipulate the tasks in the list.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object and loads tasks from the given data string.
     *
     * @param data The string containing task data, with each task on a new line,
     *             and fields within each task separated by " | ".
     * @throws PelopsIIException If there is an issue loading the data.
     */
    public TaskList(String data) throws PelopsIIException {
        taskList = new ArrayList<>();
        loadData(data);
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Loads tasks from a data string.
     * Parses each line of the data string to create Task objects,
     * adding them to the task list.
     *
     * @param data The string containing task data.
     * @throws PelopsIIException If the data format is invalid or there's an issue creating tasks.
     */
    private void loadData(String data) throws PelopsIIException {
        String[] tasks = data.split("\n");
        for (String task : tasks) {
            if (task.equals("")) continue;
            String[] tokens = task.split(" \\| ");
            boolean isDone = tokens[1].equals("1");
            if (tokens[0].equals("T")) {
                this.taskList.add(new ToDo(isDone, tokens[2]));
            } else if (tokens[0].equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(tokens[3], DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
                this.taskList.add(new Deadline(isDone, tokens[2], dateTime));
            } else if (tokens[0].equals("E")) {
                this.taskList.add(new Event(isDone, tokens[2], tokens[3], tokens[4]));
            }
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param pos The position of the task to retrieve (1-based index).
     * @return The task at the specified position.
     */
    public Task getTaskByPosition(int pos) {
        return taskList.get(pos - 1);
    }

    /**
     * Marks the task at the specified position as done.
     *
     * @param pos The position of the task to mark (1-based index).
     * @throws PelopsIIException If the position is out of range.
     */
    public void mark(int pos) throws PelopsIIException {
        int index = pos - 1;
        if (index < 0 || index >= this.getSize()) {
            throw new PelopsIIException("The index specified is out of range");
        }
        taskList.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified position as not done.
     *
     * @param pos The position of the task to unmark (1-based index).
     * @throws PelopsIIException If the position is out of range.
     */
    public void unmark(int pos) throws PelopsIIException {
        int index = pos - 1;
        if (index < 0 || index >= this.getSize()) {
            throw new PelopsIIException("The index specified is out of range");
        }
        taskList.get(index).markAsNotDone();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes the task at the specified position from the list.
     *
     * @param pos The position of the task to delete (1-based index).
     * @return The task that was deleted.
     * @throws PelopsIIException If the position is out of range.
     */
    public Task deleteTask(int pos) throws PelopsIIException {
        int index = pos - 1;
        if (index < 0 || index >= this.getSize()) {
            throw new PelopsIIException("The index specified is out of range");
        }
        return taskList.remove(index);
    }

    /**
     * Finds tasks whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A string containing the list of tasks whose descriptions contain the keyword,
     *         or an empty string if no matching tasks are found. Each matching task is
     *         preceded by its 1-based index in the task list, and is on a new line.
     * @throws PelopsIIException If there is an error accessing or processing the task list.
     */
    public String find(String keyword) throws PelopsIIException {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for(Task task : taskList) {
            if(task.description.contains(keyword)) {
                sb.append(counter + "." + task).append("\n");
                counter++;
            }
        }
        return sb.toString();
    }

    public String getSaveData() {
        StringBuilder sb = new StringBuilder();
        for(Task task : taskList) {
            sb.append(task.getDataString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the task list,
     * with each task on a new line, preceded by its position in the list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= taskList.size(); i++) {
            sb.append(i + "." + taskList.get(i - 1)).append("\n");
        }
        return sb.toString();
    }
}