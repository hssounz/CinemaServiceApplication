package com.example.CinemaServiceApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nbPlace;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "place")
    private Collection<Ticket> tickets;
}
