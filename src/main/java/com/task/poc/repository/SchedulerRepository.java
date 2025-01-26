package com.task.poc.repository;

import com.task.poc.models.database.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchedulerRepository extends MongoRepository<Schedule, String> {
}
