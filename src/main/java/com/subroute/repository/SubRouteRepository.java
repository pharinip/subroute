package com.subroute.repository;

import com.subroute.entity.Route;
import com.subroute.entity.SubRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRouteRepository extends JpaRepository<SubRoute,Integer> {




    List<SubRoute> findBySubRouteFromAndSubRouteTo(String fromSubroute, String toSubroute);

}