package com.yash.yotaapi.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Scheduler {
    // Define the start and end date/time for the range
    private static final String START_DATE = "2024-05-07 13:26:00";
    private static final String END_DATE = "2024-05-07 13:30:00";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void checkDateTimeRange() {
        // Get the current date/time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Parse start and end date/time strings to LocalDateTime
        LocalDateTime startDate = LocalDateTime.parse(START_DATE, formatter);
        LocalDateTime endDate = LocalDateTime.parse(END_DATE, formatter);

        // Check if the current date/time falls within the range
        if (currentDateTime.isAfter(startDate) && currentDateTime.isBefore(endDate)) {
            System.out.println("Current date/time is within the range.");
        } else {
            System.out.println("Current date/time is outside the range.");
        }
    }
}
