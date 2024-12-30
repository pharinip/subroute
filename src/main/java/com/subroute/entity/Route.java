package com.subroute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer routeId;


    private String routeFrom;


    private String routeTo;

    private Integer distance;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "route")
    private List<Bus> bus;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<SubRoute> subRoutes;
}