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
            String[] tokens = input.split(" ");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println(i + "." + list.get(i - 1));
                }
            } else if (tokens[0].equals("mark") && tokens.length == 2 && isNumeric(tokens[1])) {
                int index = Integer.parseInt(tokens[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index));
            } else if (tokens[0].equals("unmark") && tokens.length == 2 && isNumeric(tokens[1])) {
                int index = Integer.parseInt(tokens[1]) - 1;
                list.get(index).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index));
            }else {
                list.add(new Task(input));
                System.out.println("added: " + input);
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
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
