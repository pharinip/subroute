package com.subroute.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer busId;


    private String busName;


    private String driverName;


    private String busType;


    private String routeFrom;


    private String routeTo;


    private Integer farePerSeat;


    private Integer seats;

    private Integer availableSeats;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Route route;
}

