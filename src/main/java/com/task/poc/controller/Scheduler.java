package com.task.poc.controller;

import com.task.poc.models.schedule.ScheduleRequestDTO;
import com.task.poc.service.SchedulerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing schedules.
 * Provides endpoints to create, retrieve, update, and delete schedules.
 * This controller delegates business logic to the SchedulerService.
 */
@CrossOrigin(origins = "*")  // Allows requests from all origins for cross-origin resource sharing
@Log4j2 // Log4j 2 integration using Lombok for logging
@RestController  // Indicates that this class is a REST controller
@RequestMapping("api/scheduler")  // Base URL path for all methods in this controller
public class Scheduler {

    @Autowired
    private SchedulerService service;  // Injecting the SchedulerService for business logic

    /**
     * Endpoint to create a new schedule.
     * @param request The schedule data to create
     * @return ResponseEntity with the created schedule data or error message
     */
    @PostMapping()
    @ResponseBody  // Specifies that the method returns the response body directly
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDTO request) {
        log.debug("Received request to create a new schedule: {}", request);

        // Call the service method to create the schedule and return the response
        return service.createSchedule(request);
    }

    /**
     * Endpoint to retrieve all schedules.
     * @return ResponseEntity containing a list of all schedules or error message
     */
    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> retrieveAllSchedules() {
        log.debug("Received request to retrieve all schedules.");

        // Call the service method to fetch all schedules and return the response
        return service.getAllSchedules();
    }

    /**
     * Endpoint to retrieve a specific schedule by its ID.
     * @param id The ID of the schedule to retrieve
     * @return ResponseEntity containing the schedule data or error message
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> retrieveScheduleById(@PathVariable("id") String id) {
        log.debug("Received request to retrieve schedule with ID: {}", id);

        // Call the service method to fetch the schedule by ID and return the response
        return service.getScheduleById(id);
    }

    /**
     * Endpoint to update an existing schedule.
     * @param id The ID of the schedule to update
     * @param request The updated schedule data
     * @return ResponseEntity containing the updated schedule data or error message
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateSchedule(@PathVariable("id") String id, @RequestBody ScheduleRequestDTO request) {
        log.debug("Received request to update schedule with ID: {} and data: {}", id, request);

        // Call the service method to update the schedule and return the response
        return service.updateSchedule(id, request);
    }

    /**
     * Endpoint to delete a schedule by its ID.
     * @param id The ID of the schedule to delete
     * @return ResponseEntity containing a success message or error message
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteSchedule(@PathVariable String id) {
        log.debug("Received request to delete schedule with ID: {}", id);

        // Call the service method to delete the schedule and return the response
        return service.deleteSchedule(id);
    }
}
