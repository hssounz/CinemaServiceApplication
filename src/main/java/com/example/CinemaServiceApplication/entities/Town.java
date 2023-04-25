package com.example.CinemaServiceApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Town {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String townName;
    private double longitude,latitude,altitude;
    @OneToMany(mappedBy = "town")
    private Collection<Cinema> cinemas;
}
