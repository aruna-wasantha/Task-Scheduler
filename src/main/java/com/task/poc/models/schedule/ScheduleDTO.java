package com.task.poc.models.schedule;

import com.task.poc.models.common.ScheduleInfoDTO;
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
    private Date createDate;
    private Date updateDate;
    private ScheduleInfoDTO info;
}