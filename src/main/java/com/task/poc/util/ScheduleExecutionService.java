package com.task.poc.util;

import com.task.poc.models.database.Schedule;
import com.task.poc.repository.SchedulerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
            log.info("Executing task with payload: {}", schedule.getInfo().toString());

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
        // Simulate an external API call for schedule with ID
        log.info("Simulating external API call for schedule with ID: {}", schedule.getId());

        // Build the URL with the schedule ID as a query parameter
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:7000/api/mock-api")
                .queryParam("id", schedule.getId())
                .toUriString();

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the request entity (nobody needed for this case)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Send a POST request to the local endpoint with the schedule ID as a query parameter
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            // Use POST method with the URL containing the query parameter
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            // Log the response (or handle as needed)
            log.info("Response from external API: {}", response.getStatusCode());

        } catch (Exception e) {
            log.error("Error while calling external API: {}", e.getMessage());
        }
    }
}
