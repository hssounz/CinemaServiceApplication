package com.example.CinemaServiceApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Hall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hallName;

    @ManyToOne
    private Cinema cinema;

    private int placesNumber;
    @OneToMany(mappedBy = "hall")
    private Collection<Place> places;

    @OneToMany(mappedBy = "hall")
    private Collection<MovieProjection> movieProjections;
}
