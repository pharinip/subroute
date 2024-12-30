package com.subroute.service;

import com.subroute.enums.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.subroute.repository.*;
import com.subroute.entity.*;
import com.subroute.dto.*;

import java.time.DayOfWeek;
import java.time.LocalDate;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

        @Autowired
        private ScheduleRepository scheduleRepository;

        @Autowired
        private BusRepository busRepository;

        @Autowired
        private RouteRepository routeRepository;

        @Autowired
        private SubRouteRepository subRouteRepository;

        public List<Schedule> findAvailableBuses(SearchDto searchDto) {

//            LocalDate journeyDate = searchDto.getJourneyDate();
//
//            Bus bus = busRepository.findByRouteFromAndRouteTo(searchDto.getFrom(), searchDto.getTo());
//
//         //   List<Schedule> schedules = scheduleRepository.findSchedulesByBusId(bus.getBusId());
//            List<Schedule> schedules = scheduleRepository.findByBus_BusId(bus.getBusId());
//
//            // 2. Filter schedules based on journey date
//            List<Schedule> availableSchedules = schedules.stream()
//                    .filter(schedule -> isAvailableOnDate(schedule, journeyDate))
//                    .toList();
//
//            System.out.println(availableSchedules);
//
//            return availableSchedules;
            LocalDate journeyDate = searchDto.getJourneyDate();
            String fromLocation = searchDto.getFrom();
            String toLocation = searchDto.getTo();

            // Find subroutes matching user's locations
            List<SubRoute> subRoutes = subRouteRepository.findBySubRouteFromAndSubRouteTo(fromLocation, toLocation);

            if (subRoutes.isEmpty()) {
                return List.of(); // No matching subroutes found
            }

            // Extract route ID from the first subroute (assuming all subroutes belong to the same main route)
            int routeId = subRoutes.get(0).getRoute().getRouteId();

            // Join Routes and Schedules to find available buses on the entire route
            List<Schedule> schedules = scheduleRepository.findByRoute_RouteId(routeId);

            // Filter based on journey date
            List<Schedule> availableSchedules = schedules.stream()
                    .filter(schedule -> isAvailableOnDate(schedule, journeyDate))
                    .collect(Collectors.toList());

            // Extract pickup location for the user's subroute
            String pickupLocation = subRoutes.stream()
                    .filter(subRoute -> subRoute.getSubRouteFrom().equals(fromLocation))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Pickup location not found"))
                            .getSubRouteFrom();

            // Attach pickup location to each schedule (adjust as needed for your display logic)
            availableSchedules.forEach(schedule -> schedule.setPickUpLocation(pickupLocation));

            return availableSchedules;
        }

    private boolean isAvailableOnDate(Schedule schedule, LocalDate journeyDate) {
        if (schedule.getFrequency() == null || schedule.getStartDate() == null) {
            return false; // Invalid schedule
        }

        // Check if journey date falls within the schedule's active period
        if (journeyDate.isBefore(schedule.getStartDate()) ||
                (schedule.getEndDate() != null && journeyDate.isAfter(schedule.getEndDate()))) {
            return false;
        }

        // Handle logic for different frequencies
        if (schedule.getFrequency() == Frequency.WEEKENDS) {
            DayOfWeek dayOfWeek = journeyDate.getDayOfWeek();
            // Allow only Saturday and Sunday
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                return false;
            }
        }

        // For daily frequency or any other custom logic, extend here
        return true;
    }


    // ... other methods
    }


