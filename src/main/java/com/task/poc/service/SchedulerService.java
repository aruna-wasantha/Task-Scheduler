package com.task.poc.service;

import com.task.poc.models.dto.schedule.ScheduleDTO;
import com.task.poc.models.dto.schedule.ScheduleListDTO;
import com.task.poc.models.dto.schedule.ScheduleRequestDTO;
import org.springframework.http.ResponseEntity;

public interface SchedulerService {

    ResponseEntity<ScheduleDTO> createSchedule(ScheduleRequestDTO request);

    ResponseEntity<ScheduleListDTO> getAllSchedules();

    ResponseEntity<ScheduleDTO> getScheduleById(String id);

    ResponseEntity<ScheduleDTO> updateSchedule(String id, ScheduleRequestDTO request);

    ResponseEntity<String> deleteSchedule(String id);
}
