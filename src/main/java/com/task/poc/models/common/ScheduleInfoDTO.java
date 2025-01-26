package com.task.poc.models.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
