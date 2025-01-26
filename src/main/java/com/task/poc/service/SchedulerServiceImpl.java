package com.task.poc.service;

import com.task.poc.models.dto.schedule.ScheduleDTO;
import com.task.poc.models.dto.schedule.ScheduleListDTO;
import com.task.poc.models.dto.schedule.ScheduleRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Service implementation for scheduling tasks.
 * This class provides methods to create, update, delete, and retrieve schedules.
 */
@Service
@Log4j2 // Log4j 2 integration using Lombok for logging
public class SchedulerServiceImpl implements SchedulerService {

    /**
     * Creates a new schedule based on the given request.
     *
     * @param request ScheduleRequestDTO containing schedule data
     * @return ResponseEntity with the created ScheduleDTO
     */
    @Override
    public ResponseEntity<ScheduleDTO> createSchedule(ScheduleRequestDTO request) {
        log.debug("Starting createSchedule method.");

        try {
            // Creating a new ScheduleDTO (simulating creation process)
            ScheduleDTO scheduleDTO = new ScheduleDTO();

            // Log info after successful creation
            log.info("Schedule creation successful with ID: {}", scheduleDTO.getId());

            // Returning the created schedule with a 200 OK response
            return ResponseEntity.ok(scheduleDTO);
        } catch (Exception ex) {
            // Log error with exception details if something goes wrong
            log.error("Schedule creation failed. Exception: ", ex);

            // Return an internal server error response with an empty ScheduleDTO
            return ResponseEntity.internalServerError().body(new ScheduleDTO());
        }
    }

    /**
     * Retrieves all schedules.
     *
     * @return ResponseEntity containing a list of all schedules
     */
    @Override
    public ResponseEntity<ScheduleListDTO> getAllSchedules() {
        log.debug("Starting getAllSchedules method.");

        try {
            // Simulating fetching all schedules
            ScheduleListDTO scheduleListDTO = new ScheduleListDTO();

            // Log info after successfully fetching the schedules
            log.info("Successfully fetched all schedules.");

            // Return the list of schedules
            return ResponseEntity.ok(scheduleListDTO);
        } catch (Exception ex) {
            // Log error with exception details if something goes wrong
            log.error("Failed to fetch all schedules. Exception: ", ex);

            // Return an internal server error response with an empty ScheduleListDTO
            return ResponseEntity.internalServerError().body(new ScheduleListDTO());
        }
    }

    /**
     * Retrieves a schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve
     * @return ResponseEntity containing the ScheduleDTO for the given ID
     */
    @Override
    public ResponseEntity<ScheduleDTO> getScheduleById(String id) {
        log.debug("Starting getScheduleById method. Schedule ID: {}", id);

        try {
            // Simulating fetching a schedule by its ID
            ScheduleDTO scheduleDTO = new ScheduleDTO();

            // Log info after successfully fetching the schedule by ID
            log.info("Successfully fetched schedule by ID: {}", id);

            // Return the schedule data
            return ResponseEntity.ok(scheduleDTO);
        } catch (Exception ex) {
            // Log error with exception details if fetching the schedule by ID fails
            log.error("Failed to fetch schedule by ID. Exception: ", ex);

            // Return an internal server error response with an empty ScheduleDTO
            return ResponseEntity.internalServerError().body(new ScheduleDTO());
        }
    }

    /**
     * Updates an existing schedule.
     *
     * @param id      The ID of the schedule to update
     * @param request ScheduleRequestDTO containing the updated schedule data
     * @return ResponseEntity with the updated ScheduleDTO
     */
    @Override
    public ResponseEntity<ScheduleDTO> updateSchedule(String id, ScheduleRequestDTO request) {
        log.debug("Starting updateSchedule method. Schedule ID: {}", id);

        try {
            // Simulating updating the schedule based on the request
            ScheduleDTO updatedSchedule = new ScheduleDTO();

            // Log info after successful update
            log.info("Schedule update successful for ID: {}", id);

            // Return the updated schedule
            return ResponseEntity.ok(updatedSchedule);
        } catch (Exception ex) {
            // Log error with exception details if something goes wrong
            log.error("Schedule update failed. Exception: ", ex);

            // Return an internal server error response with an empty ScheduleDTO
            return ResponseEntity.internalServerError().body(new ScheduleDTO());
        }
    }

    /**
     * Deletes a schedule by its ID.
     *
     * @param id The ID of the schedule to delete
     * @return ResponseEntity containing a success message
     */
    @Override
    public ResponseEntity<String> deleteSchedule(String id) {
        log.debug("Starting deleteSchedule method. Schedule ID: {}", id);

        try {
            // Simulating schedule deletion
            log.info("Schedule deletion successful for ID: {}", id);

            // Return a success message after deletion
            return ResponseEntity.ok("Schedule deleted successfully.");
        } catch (Exception ex) {
            // Log error with exception details if something goes wrong
            log.error("Schedule deletion failed. Exception: ", ex);

            // Return an internal server error response with an error message
            return ResponseEntity.internalServerError().body("Failed to delete schedule.");
        }
    }
}
