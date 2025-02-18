package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    /**
     * Returns a formatted string representation of the deadline date and time.
     * The format is "d MMM yyyy h:mma" (e.g., "5 Oct 2023 3:30PM").
     *
     * @return The formatted deadline date and time string.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return formattedDateTime.toString().replace("am", "AM").replace("pm", "PM");
    }

    public static String getStoringDate(String dateTimeString) {
        return dateTimeString.replace("AM", "am").replace("PM", "pm");
    }

}
