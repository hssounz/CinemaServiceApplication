package com.example.CinemaServiceApplication.dao;

import com.example.CinemaServiceApplication.entities.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
}
