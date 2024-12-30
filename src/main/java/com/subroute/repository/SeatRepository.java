package com.subroute.repository;


import com.subroute.entity.Route;
import com.subroute.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer> {

}