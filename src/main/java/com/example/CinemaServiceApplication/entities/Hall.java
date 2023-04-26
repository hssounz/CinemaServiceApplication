package com.example.CinemaServiceApplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ManyToOne @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cinema cinema;

    private int placesNumber;
    @OneToMany(mappedBy = "hall") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;

    @OneToMany(mappedBy = "hall") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<MovieProjection> movieProjections;
}
