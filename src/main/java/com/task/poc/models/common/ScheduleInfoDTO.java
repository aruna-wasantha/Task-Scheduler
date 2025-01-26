package com.task.poc.models.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleInfoDTO {
    private String location;
    private Category category;
    private Priority priority;
    private List<String> attendees;
    private boolean isRecurring;
    private String recurrencePattern;
    private String notes;
}
