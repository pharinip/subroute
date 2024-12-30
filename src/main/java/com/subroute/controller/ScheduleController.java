package com.subroute.controller;

import com.subroute.dto.ScheduleDto;
import com.subroute.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;



       @PostMapping("/addSchedule/{busId}/{routeId}")
        public String addSchedule(@RequestBody ScheduleDto scheduleDto,
                                  @PathVariable Integer busId,
                                  @PathVariable Integer routeId){

           scheduleService.addSchedule(scheduleDto,busId,routeId);
           return "sucess";

       }
}
