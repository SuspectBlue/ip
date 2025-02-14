package pelopsii.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void todo_task_success() {
        Assertions.assertEquals("[T][ ] todo", new ToDo("todo").toString());
    }

    @Test
    public void deadline_task_success() {
        Assertions.assertEquals("[D][ ] deadline 1 (by: 30 May 2025 11:00PM)", 
        new Deadline("deadline 1", LocalDateTime.parse("2025-05-30 2300", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))).toString());
    }

    @Test
    public void event_task_success() {
        Assertions.assertEquals("[E][ ] event (from: 30 May 2025 11:00PM to: 31 May 2025 11:00PM)",
                new Event("event", LocalDateTime.parse("2025-05-30 2300", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), LocalDateTime.parse("2025-05-31 2300", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))).toString());
    }
}
