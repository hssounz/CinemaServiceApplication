package com.example.CinemaServiceApplication.dao;

import com.example.CinemaServiceApplication.entities.MovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieProjectionRepository extends JpaRepository<MovieProjection, Long> {
}
