package com.subroute.repository;

import com.subroute.entity.Bus;
import com.subroute.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Integer> {

    public Bus findByRouteFromAndRouteTo(String routeFrom,String routeTo);


}