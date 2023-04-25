package com.example.CinemaServiceApplication.entities;

import com.example.CinemaServiceApplication.enums.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private MovieCategory category;

    private double duration;
    private String director;
    private String description;
    private String banner;
    private Date releaseDate;

    @OneToMany(mappedBy = "movie")
    private Collection<MovieProjection> movieProjections;
}
