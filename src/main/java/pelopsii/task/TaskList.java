package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pelopsii.exception.PelopsIIException;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(String data) throws PelopsIIException {
        taskList = new ArrayList<>();
        loadData(data);
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    private void loadData(String data) throws PelopsIIException {
            String[] tasks = data.split("\n");
            for (String task : tasks) {
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
