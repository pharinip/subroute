package com.subroute.repository;

import com.subroute.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {


        // Custom query method to find Schedules by busId (foreign key)
        List<Schedule> findByBus_BusId(Integer busId);
       // @Query("SELECT s FROM Schedule s WHERE s.bus.id = :busId")
      //  List<Schedule> findSchedulesByBusId(@Param("busId") Integer busId);

       List<Schedule> findByBus_BusIdAndRoute_RouteId(Integer busId, Integer routeId);

       List<Schedule> findByRoute_RouteId(Integer routeId);

}
