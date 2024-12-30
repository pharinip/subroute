package com.subroute.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatId;


    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    // Getters and Setters
}

