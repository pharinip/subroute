package com.subroute.repository;

import com.subroute.entity.Reservation;
import com.subroute.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
