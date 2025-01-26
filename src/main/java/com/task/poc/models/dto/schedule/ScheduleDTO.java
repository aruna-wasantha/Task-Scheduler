package com.task.poc.models.dto.schedule;

import com.task.poc.models.dto.other.ScheduleInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private String id;
    private String name;
    private Date startDateTime;
    ScheduleInfoDTO info;
}