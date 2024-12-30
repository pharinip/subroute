package com.subroute.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SubRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subRouteId;

    private String subRouteName;
    private String subRouteFrom; // New field for the starting point
    private String subRouteTo;   // New field for the ending point

    private Integer subRouteDistance;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}
