package com.example.CinemaServiceApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cinemaName;
    private double longitude,latitude,altitude;
    private int hallsNumber;
    @OneToMany(mappedBy = "cinema")
    private Collection<Hall> halls;
    @ManyToOne
    private Town town;
}
