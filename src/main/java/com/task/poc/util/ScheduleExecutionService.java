package com.task.poc.util;

import com.task.poc.models.database.Schedule;
import com.task.poc.repository.SchedulerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Service for executing scheduled tasks when their scheduled time is reached.
 * This service periodically checks for pending schedules and processes them.
 */
@Service
@Log4j2
public class ScheduleExecutionService {

    @Autowired
    private SchedulerRepository repository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5); // Thread pool for parallel execution

    /**
     * Periodically checks for pending schedules and processes them.
     * This method is executed at a fixed interval (e.g., every 30 seconds).
     */
    @Scheduled(fixedRate = 30000) // Runs every 10 seconds
    public void processPendingSchedules() {
        log.info("Starting processPendingSchedules method.");

        try {
            // Get current UTC time (Instant is already in UTC)
            Instant utcInstant = Instant.now();  // This will be in UTC
            Date utcNow = Date.from(utcInstant); // Convert Instant to Date

            // Log in UTC without timezone conversion
            log.info("Using current UTC time (ignoring local timezone): {}", utcInstant.toString());

            // Fetch all pending schedules
            List<Schedule> pendingSchedules = repository.findPendingSchedules(utcNow);

            if (pendingSchedules.isEmpty()) {
                log.info("No pending schedules found.");
                return;
            }

            log.info("Found {} pending schedules for execution.", pendingSchedules.size());

            // Submit each schedule for execution
            pendingSchedules.forEach(schedule ->
                    executorService.submit(() -> executeSchedule(schedule))
            );

        } catch (Exception ex) {
            log.error("Error occurred while processing pending schedules: ", ex);
        }
    }


    /**
     * Executes a single schedule.
     *
     * @param schedule The schedule to execute.
     */
    private void executeSchedule(Schedule schedule) {
        log.debug("Executing schedule with ID: {}", schedule.getId());

        try {
            // Log the payload (info field) as an example execution
            log.info("Executing task with payload: {}", schedule.getInfo());

            // Simulate a mock external API call or actual task execution
            mockExternalApiCall(schedule);

            // Mark the schedule as executed and update it in the database
            schedule.setExecuted(true);
            schedule.setUpdateDate(new Date());
            repository.save(schedule);

            log.info("Successfully executed schedule with ID: {}", schedule.getId());
        } catch (Exception ex) {
            // Log any errors during schedule execution
            log.error("Failed to execute schedule with ID: {}. Exception: ", schedule.getId(), ex);
        }
    }

    /**
     * Mock external API call or any task processing logic.
     *
     * @param schedule The schedule whose task is being executed.
     */
    private void mockExternalApiCall(Schedule schedule) {
        // Simulate an external API call or task execution
        log.info("Simulating external API call for schedule with ID: {}", schedule.getId());

        // Add your actual logic here (e.g., send an HTTP request or perform a specific task)
        try {
            Thread.sleep(1000); // Simulate a delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Mock external API call was interrupted.");
        }
    }
}
