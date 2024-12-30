package com.subroute.controller;

import com.subroute.dto.SearchDto;
import com.subroute.entity.Schedule;
import com.subroute.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/bookReservation")
    public ResponseEntity<?> bookReservation(@RequestBody SearchDto searchDto) {
        List<Schedule> availableBuses = reservationService.findAvailableBuses(searchDto);

      if (availableBuses.size() == 0) {

          return new ResponseEntity<>("No buses found for the given date", HttpStatus.ACCEPTED);
       }
        return new ResponseEntity<>(availableBuses, HttpStatus.OK);
  }
}
