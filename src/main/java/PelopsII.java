import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PelopsII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = br.readLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
        // System.out.println("Running Java version: " + System.getProperty("java.version"));
        br.close();
    }
}
