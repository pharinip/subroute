package com.subroute.dto;

import com.subroute.entity.Bus;
import com.subroute.entity.Route;
import com.subroute.enums.Frequency;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto {


    private Integer scheduleId;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    @Enumerated(EnumType.STRING)
    private Frequency frequency; // e.g., "Daily", "Weekdays", "Weekends", or custom


}
