package com.task.poc.repository;

import com.task.poc.models.database.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface SchedulerRepository extends MongoRepository<Schedule, String> {
    /**
     * Finds schedules that are pending execution (startDateTime <= currentTime and not executed).
     *
     * @param currentTime The current time to compare.
     * @return List of pending schedules.
     */
    @Query("{'startDateTime': { $lte: ?0 }, 'executed': false }")
    List<Schedule> findPendingSchedules(Date currentTime);
}
