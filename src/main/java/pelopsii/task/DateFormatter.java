package pelopsii.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting and storing date-time strings.
 */
public class DateFormatter {

    /**
     * Formats a {@link LocalDateTime} object into a string representation.
     * The output format is "d MMM yyyy h:mma" (e.g., "5 Feb 2025 3:45PM").
     *
     * @param dateTime The {@link LocalDateTime} instance to be formatted.
     * @return A formatted date-time string with "AM" and "PM" in uppercase.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
        String formattedDateTime = dateTime.format(outputFormatter);
        return formattedDateTime.replace("am", "AM").replace("pm", "PM");
    }

    /**
     * Converts a formatted date-time string back to a storable format.
     * This method ensures "AM" and "PM" are converted to lowercase.
     *
     * @param dateTimeString The formatted date-time string.
     * @return A string with "AM" replaced with "am" and "PM" replaced with "pm".
     */
    public static String getStoringDate(String dateTimeString) {
        return dateTimeString.replace("AM", "am").replace("PM", "pm");
    }
}

