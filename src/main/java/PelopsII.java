import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Exceptions.PelopsIIException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

public class PelopsII {
    public static void main(String[] args) throws IOException {
        String filePath = "./data/PelopsII.txt";
        ArrayList<Task> list = new ArrayList<>();

        try (BufferedReader filereader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = filereader.readLine()) != null) {
                String[] tokens = line.split(" \\| ");
                boolean isDone = tokens[1].equals("1");
                if (tokens[0].equals("T")) {
                    list.add(new ToDo(isDone, tokens[2]));
                } else if (tokens[0].equals("D")) {
                    LocalDateTime dateTime = LocalDateTime.parse(tokens[3], DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
                    System.out.println(dateTime);
                    list.add(new Deadline(isDone, tokens[2], dateTime));
                } else if (tokens[0].equals("E")) {
                    list.add(new Event(isDone, tokens[2], tokens[3], tokens[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");


        while (true) {
            try {
                String input = br.readLine();
                String[] action = input.split(" ");

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    for(int i = 1; i <= list.size(); i++) {
                        System.out.println(i + "." + list.get(i - 1));
                    }
                } else if (action[0].equals("mark") && action.length == 2 && isNumeric(action[1])) {
                    int index = Integer.parseInt(action[1]) - 1;
                    list.get(index).markAsDone();
                    storeData(list);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                } else if (action[0].equals("unmark") && action.length == 2 && isNumeric(action[1])) {
                    int index = Integer.parseInt(action[1]) - 1;
                    list.get(index).markAsNotDone();
                    storeData(list);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index));
                } else if (action[0].equals("todo")) {
                    if(action.length == 1) {
                        throw new PelopsIIException("ToDo tasks require a description. For example: todo <description>");
                    }
                    String description = input.split("todo ")[1];
                    list.add(new ToDo(description));
                    int listSize = list.size();
                    storeData(list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                } else if (action[0].equals("deadline")) {
                    if(action.length == 1) {
                        throw new PelopsIIException("Deadline tasks must include both a description and a specified deadline time. For example: deadline <description> /by yyyy-MM-dd HHmm");
                    }
                    String data = input.split("deadline ")[1];
                    String description = data.split(" /by ")[0];
                    String byDate = data.split(" /by ")[1];
                    LocalDateTime dateTime = LocalDateTime.parse(byDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    list.add(new Deadline(description, dateTime));
                    int listSize = list.size();
                    storeData(list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                } else if(action[0].equals("event")) {
                    if(action.length == 1) {
                        throw new PelopsIIException("Event tasks must include a description, event start time and event end time. For example: event <description> /from <start_time> /to <end_time>");
                    }
                    String data = input.split("event ")[1];
                    String description = data.split(" /from ")[0];
                    String fromTime = data.split(" /from ")[1].split(" /to ")[0];
                    String toTime = data.split(" /to ")[1];
                    list.add(new Event(description, fromTime, toTime));
                    int listSize = list.size();
                    storeData(list);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                } else if (action[0].equals("delete")) {
                    if (action.length == 1) {
                        throw new PelopsIIException("You must specify an index when deleting a task");
                    }
                    if (action.length > 2) {
                        throw new PelopsIIException("You have specified too many parameters for the delete command");
                    } 
                    if (!isNumeric(action[1])) {
                        throw new PelopsIIException("The index must be a numeric number");
                    }

                    int index = Integer.parseInt(action[1]) - 1;
                    if (index < 0 || index >= list.size()) {
                        throw new PelopsIIException("The index is out of range of the task list");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.remove(index));
                    storeData(list);
                    System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
                    
                } else {
                    throw new PelopsIIException("Unrecognised command. Please input a valid command.");
                }
            } catch (PelopsIIException e) {
                System.out.println(e.getMessage());
            }
        }
        // System.out.println("Running Java version: " + System.getProperty("java.version"));
        br.close();
    }

    // Taken from https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void storeData(ArrayList<Task> taskList) throws IOException {
        String filePath = "./data/PelopsII.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(Task task : taskList) {
                writer.write(task.getDataString());
                writer.newLine();
            }
        }
    }
}
