package com.example.CinemaServiceApplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class MovieProjection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date projectionDate;
    private double price;

    @ManyToOne
    private Movie movie;

    @ManyToOne @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Hall hall;

    @OneToMany(mappedBy = "movieProjection") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;

    @ManyToOne
    private MovieSession movieSession;
}
