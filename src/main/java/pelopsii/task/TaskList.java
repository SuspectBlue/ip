package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pelopsii.exception.PelopsIIException;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(String data) throws PelopsIIException {
        taskList = new ArrayList<>();
        System.out.println(data);
        loadData(data);
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    private void loadData(String data) throws PelopsIIException {
            String[] tasks = data.split("\n");
            for (String task : tasks) {
                if (task.equals("")) continue;
                String[] tokens = task.split(" \\| ");
                System.out.println("String 1" + task.toString());
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

    public int getSize() {
        return taskList.size();
    }

    public Task getTaskByPosition(int pos) {
        return taskList.get(pos - 1);
    }

    public void mark(int pos) throws PelopsIIException {
        int index = pos - 1;
        if (index < 0 || index >= this.getSize()) {
            throw new PelopsIIException("The index specified is out of range");
        }
        taskList.get(index).markAsDone();
    }

    public void unmark(int pos) throws PelopsIIException {
        int index = pos - 1;
        if (index < 0 || index >= this.getSize()) {
            throw new PelopsIIException("The index specified is out of range");
        }
        taskList.get(index).markAsNotDone();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= taskList.size(); i++) {
            sb.append(i + "." + taskList.get(i - 1)).append("\n");
        }
        return sb.toString();
    }
}
