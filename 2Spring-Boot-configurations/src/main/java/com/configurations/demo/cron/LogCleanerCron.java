package com.configurations.demo.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class LogCleanerCron {
    private static final String LOG_FILE_PATH = "C:/Users/ACNusrHoneyP/Desktop/logs/configurations.log";

    @Scheduled(cron = "*/30 * * * * *") // Runs every 30 seconds - JUST FOR AN EXAMPLE
    public void clearLogs() {
        try {
            File logFile = new File(LOG_FILE_PATH);
            if (logFile.exists()) {
                new FileWriter(LOG_FILE_PATH, false).close(); // Opening a file in write mode without appending automatically truncates (clears) the file
                System.out.println("[LOG CLEANER] Logs cleared successfully at: " + new java.util.Date());
            } else {
                System.out.println("[LOG CLEANER] Log file not found.");
            }
        } catch (IOException e) {
            System.out.println("[LOG CLEANER] Failed to clear logs: " + e.getMessage());
        }
    }
}
