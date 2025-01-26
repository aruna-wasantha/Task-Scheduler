package com.task.poc.service;

import com.task.poc.models.schedule.ScheduleDTO;
import com.task.poc.models.schedule.ScheduleListDTO;
import com.task.poc.models.schedule.ScheduleRequestDTO;
import org.springframework.http.ResponseEntity;

public interface SchedulerService {

    ResponseEntity<ScheduleDTO> createSchedule(ScheduleRequestDTO request);

    ResponseEntity<ScheduleListDTO> getAllSchedules();

    ResponseEntity<ScheduleDTO> getScheduleById(String id);

    ResponseEntity<ScheduleDTO> updateSchedule(String id, ScheduleRequestDTO request);

    ResponseEntity<?> deleteSchedule(String id);
}
