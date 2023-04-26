package com.example.CinemaServiceApplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(unique = false, nullable = true)
    private Integer paymentCode;

    private Boolean reserved;

    @ManyToOne
    private Place place;

    @ManyToOne
    private MovieProjection movieProjection;

}
