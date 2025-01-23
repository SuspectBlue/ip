import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PelopsII {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Hello! I'm Pelops II");
        System.out.println("What can I do for you?");

        while (true) {
            String input = br.readLine();
            System.out.println("Pelops II: " + input);

            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
        // System.out.println("Running Java version: " + System.getProperty("java.version"));
        br.close();
    }
}
