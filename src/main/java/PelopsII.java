import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PelopsII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
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
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index));
            } else if (action[0].equals("unmark") && action.length == 2 && isNumeric(action[1])) {
                int index = Integer.parseInt(action[1]) - 1;
                list.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index));
            } else if (action[0].equals("todo")) {
                if(action.length == 1) {
                    System.out.println("Description required");
                } else {
                    String description = input.split("todo ")[1];
                    list.add(new ToDo(description));
                    int listSize = list.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                }
            } else if (action[0].equals("deadline")) {
                if(action.length == 1) {
                    System.out.println("Description required");
                } else {
                    String data = input.split("deadline ")[1];
                    String description = data.split(" /by ")[0];
                    String byDate = data.split(" /by ")[1];
                    list.add(new Deadline(description, byDate));
                    int listSize = list.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                }
            } else if(action[0].equals("event")) {
                if(action.length == 1) {
                    System.out.println("Description required");
                } else {
                    String data = input.split("event ")[1];
                    String description = data.split(" /from ")[0];
                    String fromTime = data.split(" /from ")[1].split(" /to ")[0];
                    String toTime = data.split(" /to ")[1];
                    list.add(new Event(description, fromTime, toTime));
                    int listSize = list.size();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(listSize-1));
                    System.out.println("Now you have " + listSize + (listSize == 1 ? " task in the list." : " tasks in the list."));
                }
            } else {
                System.out.println("Not a valid command.");
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
}
