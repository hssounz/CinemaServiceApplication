package com.example.CinemaServiceApplication.dao;

import com.example.CinemaServiceApplication.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
