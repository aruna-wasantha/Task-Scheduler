package com.task.poc.service;

import com.task.poc.models.database.Schedule;
import com.task.poc.models.schedule.ScheduleDTO;
import com.task.poc.models.schedule.ScheduleListDTO;
import com.task.poc.models.schedule.ScheduleRequestDTO;
import com.task.poc.repository.SchedulerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service implementation for scheduling tasks.
 * This class provides methods to create, update, delete, and retrieve schedules.
 */
@Service
@Log4j2 // Log4j 2 integration using Lombok for logging
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SchedulerRepository repository;


    /**
     * Creates a new schedule based on the given request.
     *
     * @param request ScheduleRequestDTO containing schedule data
     * @return ResponseEntity with the created ScheduleDTO
     */
    @Override
    public ResponseEntity<ScheduleDTO> createSchedule(ScheduleRequestDTO request) {
        log.debug("Entering createSchedule method with request: {}", request);

        try {
            // Creating a new schedule entity from the request DTO
            Schedule schedule = mapper.map(request, Schedule.class);

            // Generate a unique ID for the schedule
            schedule.setId(UUID.randomUUID().toString());
            // set trigger date time
            schedule.setStartDateTime(request.getStartDateTime());
            // Set current date as creation date
            schedule.setCreateDate(new Date());
            // Set the current date as the last update date
            schedule.setUpdateDate(new Date());

            // Save the new schedule in the repository
            Schedule newSchedule = repository.save(schedule);

            // Map the saved entity to a DTO for returning
            ScheduleDTO scheduleDTO = mapper.map(newSchedule, ScheduleDTO.class);

            // Log info after successful schedule creation
            log.info("Successfully created schedule with ID: {}", scheduleDTO.getId());

            // Return a successful response with the created schedule DTO
            return ResponseEntity.ok(scheduleDTO);
        } catch (Exception ex) {
            // Log error if creation fails
            log.error("Failed to create schedule. Exception: ", ex);

            // Return internal server error response
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
        log.debug("Entering getAllSchedules method.");

        try {
            // Fetch all schedules from the repository
            Iterable<Schedule> schedules = repository.findAll();

            // Convert the schedules to ScheduleDTO list using stream and collect
            List<ScheduleDTO> scheduleDTOList = StreamSupport.stream(schedules.spliterator(), false)
                    .map(schedule -> mapper.map(schedule, ScheduleDTO.class))
                    .collect(Collectors.toList());

            // Create ScheduleListDTO and set the list of schedules
            ScheduleListDTO scheduleListDTO = new ScheduleListDTO();
            scheduleListDTO.setScheduleDTOList(scheduleDTOList);

            // Log info after fetching schedules successfully
            log.info("Successfully fetched all schedules. Total count: {}", scheduleDTOList.size());

            // Return a successful response with the list of schedules
            return ResponseEntity.ok(scheduleListDTO);
        } catch (Exception ex) {
            // Log error if fetching all schedules fails
            log.error("Failed to fetch all schedules. Exception: ", ex);

            // Return internal server error response
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
        log.debug("Entering getScheduleById method. Schedule ID: {}", id);

        try {
            // Fetch the schedule by ID from the repository
            Optional<Schedule> scheduleOptional = repository.findById(id);

            // Check if schedule exists
            if (scheduleOptional.isPresent()) {
                // Map the found entity to a DTO
                ScheduleDTO scheduleDTO = mapper.map(scheduleOptional.get(), ScheduleDTO.class);
                log.info("Successfully fetched schedule by ID: {}", id);
                return ResponseEntity.ok(scheduleDTO);
            } else {
                // Log warning if schedule not found
                log.warn("Schedule with ID {} not found.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            // Log error if fetching the schedule fails
            log.error("Failed to fetch schedule by ID. Exception: ", ex);

            // Return internal server error response
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
        log.debug("Entering updateSchedule method. Schedule ID: {}, Request: {}", id, request);

        try {
            // Fetch the schedule to update by ID
            Optional<Schedule> scheduleOptional = repository.findById(id);

            if (scheduleOptional.isPresent()) {
                // Map the existing schedule and update the fields
                Schedule schedule = scheduleOptional.get();
                mapper.map(request, schedule);  // Map the request data into the schedule object
                schedule.setUpdateDate(new Date()); // Update the last modified date

                // Save the updated schedule
                Schedule updatedSchedule = repository.save(schedule);

                // Map the updated schedule to a DTO for returning
                ScheduleDTO scheduleDTO = mapper.map(updatedSchedule, ScheduleDTO.class);

                log.info("Successfully updated schedule with ID: {}", id);
                return ResponseEntity.ok(scheduleDTO);
            } else {
                // Log warning if schedule not found
                log.warn("Schedule with ID {} not found for update.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            // Log error if updating the schedule fails
            log.error("Failed to update schedule. Exception: ", ex);

            // Return internal server error response
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
        log.debug("Entering deleteSchedule method. Schedule ID: {}", id);

        try {
            // Check if the schedule exists
            Optional<Schedule> scheduleOptional = repository.findById(id);

            if (scheduleOptional.isPresent()) {
                // Delete the schedule from the repository
                repository.delete(scheduleOptional.get());

                // Log info after successful deletion
                log.info("Successfully deleted schedule with ID: {}", id);

                // Return success message after deletion
                return ResponseEntity.ok("Schedule deleted successfully.");
            } else {
                // Log warning if schedule not found
                log.warn("Schedule with ID {} not found for deletion.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            // Log error if deletion fails
            log.error("Failed to delete schedule. Exception: ", ex);

            // Return internal server error response
            return ResponseEntity.internalServerError().body("Failed to delete schedule.");
        }
    }
}
