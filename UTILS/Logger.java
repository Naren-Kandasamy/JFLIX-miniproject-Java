package UTILS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_FILE = "jflix_log.txt";

    public static void log(String message) {
        String logMessage = "[" + LocalDateTime.now().format(FORMATTER) + "] " + message;
        System.out.println(logMessage);

        // Appending to a file to demonstrate File Handling
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logMessage);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
