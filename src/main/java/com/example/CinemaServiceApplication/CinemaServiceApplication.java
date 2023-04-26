package com.example.CinemaServiceApplication;

import com.example.CinemaServiceApplication.service.CinemaInitServiceImpl;
import com.example.CinemaServiceApplication.service.ICinemaInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ICinemaInitService cinemaInitService) {
		return args -> {
			cinemaInitService.initTowns();
			cinemaInitService.initCinemas();
			cinemaInitService.initHalls();
			cinemaInitService.initPlaces();
			cinemaInitService.initMovieSessions();
			cinemaInitService.initMovies();
			cinemaInitService.initMovieProjections();
			cinemaInitService.initTickets();
		};
	}
}
