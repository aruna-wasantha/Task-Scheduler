package com.task.poc.models.dto.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInfoDTO {
    private String location;
    private Category category;
    private Priority priority;
    private List<String> attendees;
    private boolean isRecurring;
    private String recurrencePattern;
    private String notes;
    private Date createdDate;
    private Date lastUpdated;
}
