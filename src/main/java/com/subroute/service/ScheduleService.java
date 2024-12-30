package com.subroute.service;

import com.subroute.dto.ScheduleDto;
import com.subroute.entity.Bus;
import com.subroute.entity.Route;
import com.subroute.entity.Schedule;
import com.subroute.enums.Frequency;
import com.subroute.repository.BusRepository;
import com.subroute.repository.RouteRepository;
import com.subroute.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.subroute.exception.IllegalArgumentException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import com.subroute.enums.Frequency;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;


    public void addSchedule(ScheduleDto scheduleDto, Integer busId, Integer routeId) {

//        Bus bus = busRepository.findById(busId).get();
//        Route route = routeRepository.findById(routeId).get();
//
//        Schedule schedule=new Schedule();
//        schedule.setBus(bus);
//        schedule.setRoute(route);
//        //schedule.setFrequency(Frequency.DAILY);
//        schedule.setFrequency(scheduleDto.getFrequency());
//        schedule.setArrivalTime(scheduleDto.getArrivalTime());
//        schedule.setDepartureTime(scheduleDto.getDepartureTime());
//        scheduleRepository.save(schedule);

        Bus bus = busRepository.findById(busId).get();
        Route route = routeRepository.findById(routeId).get();


        Schedule newSchedule = new Schedule();
        newSchedule.setBus(bus);
        newSchedule.setRoute(route);
        newSchedule.setFrequency(scheduleDto.getFrequency());
        newSchedule.setArrivalTime(scheduleDto.getArrivalTime());
        newSchedule.setDepartureTime(scheduleDto.getDepartureTime());

        // Validate that the new schedule has a valid time range (departure after arrival)
        if (scheduleDto.getDepartureTime().isBefore(scheduleDto.getArrivalTime())) {
            throw new IllegalArgumentException("Departure time must be after arrival time.");
        }

        // Check for overlapping schedules
        List<Schedule> existingSchedules = scheduleRepository.findByBus_BusIdAndRoute_RouteId(busId, routeId);

        for (Schedule existingSchedule : existingSchedules) {
            if (isOverlapping(newSchedule, existingSchedule)) {
                throw new IllegalArgumentException("Schedule overlaps with an existing schedule.");
            }
        }

        scheduleRepository.save(newSchedule);
    }

    private boolean isOverlapping(Schedule newSchedule, Schedule existingSchedule) {
        return !(newSchedule.getDepartureTime().isBefore(existingSchedule.getArrivalTime()) ||
                newSchedule.getArrivalTime().isAfter(existingSchedule.getDepartureTime()));
    }




    }


