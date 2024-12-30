package com.subroute.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subroute.enums.Frequency;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonIgnore
    @ToString.Exclude
    private Bus bus;


    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonIgnore
    @ToString.Exclude
    private Route route;



    private LocalTime departureTime;


    private LocalTime arrivalTime;

    @Enumerated(EnumType.STRING)
    private Frequency frequency; // e.g., "Daily", "Weekdays", "Weekends", or custom

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String pickUpLocation;

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
        this.updateStartDate();
    }


    private void updateStartDate() {
        if (frequency == null) {
            return;
        }

        LocalDate now = LocalDate.now();
        switch (frequency) {
            case DAILY:
                startDate = now;
                break;
            case WEEKENDS:
                // Get the upcoming weekend (either Saturday or Sunday)
                LocalDate nextWeekend = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                startDate = nextWeekend.isBefore(now) ? nextWeekend.plusDays(7) : nextWeekend;
                break;
            default:
                // Handle other frequencies or invalid values (optional)
                break;
        }

        // Set endDate to null if frequency is "Daily" or "Weekends"
        if (frequency.equals("Daily") || frequency.equals("Weekends")) {
            endDate = null;
        }
    }

}
