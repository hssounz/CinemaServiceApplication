package com.example.CinemaServiceApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customer;
    private double price;

    @Column(unique = true)
    private int paymentCode;

    private Boolean reserved;

    @ManyToOne
    private Place place;

    @ManyToOne
    private MovieProjection movieProjection;

}
