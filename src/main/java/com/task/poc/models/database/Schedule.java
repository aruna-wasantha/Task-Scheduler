package com.task.poc.models.database;

import com.task.poc.models.common.ScheduleInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.util.Date;

@org.springframework.data.mongodb.core.mapping.Document(
        collection = "schedule-collection"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Schedule {
    @Id
    private String id;
    private String name;
    private Date startDateTime;
    private Date createDate;
    private Date updateDate;
    private ScheduleInfoDTO info;
}
