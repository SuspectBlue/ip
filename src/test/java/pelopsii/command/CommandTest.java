package pelopsii.command;
import pelopsii.exception.PelopsIIException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandTest {
    @Test
    public void mark_command_exceptionThrown() {
        try {
            assertEquals(0, new MarkCommand("mark"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("You must specify the position of the task to mark.", error.getMessage());
        }
    }

    @Test
    public void unmark_command_exceptionThrown() {
        try {
            assertEquals(0, new UnmarkCommand("unmark"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("You must specify the position of the task to unmark.", error.getMessage());
        }
    }

    @Test
    public void todo_command_exceptionThrown() {
        try {
            assertEquals(2, new TodoCommand("todo"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("ToDo tasks require a description. For example: todo <description>", error.getMessage());
        }
    }

    @Test
    public void deadline_command_exceptionThrown() {
        try {
            assertEquals(2, new DeadlineCommand("deadline"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("Deadline tasks must include both a description and a specified deadline time. For example: deadline <description> /by yyyy-MM-dd HHmm", error.getMessage());
        }
    }

    @Test
    public void event_command_exceptionThrown() {
        try {
            assertEquals(2, new EventCommand("event"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("Event tasks must include a description, event start time and event end time. For example: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm", error.getMessage());
        }
    }

    @Test
    public void delete_command_exceptionThrown() {
        try {
            assertEquals(0, new DeleteCommand("delete"));
            fail();
        } catch (PelopsIIException error) {
            assertEquals("You must specify an index when deleting a task", error.getMessage());
        }
    }
}
