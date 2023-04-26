package com.example.CinemaServiceApplication.service;

import com.example.CinemaServiceApplication.dao.*;
import com.example.CinemaServiceApplication.entities.*;
import com.example.CinemaServiceApplication.enums.MovieCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service @Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{

    private TownRepository townRepository;
    private CinemaRepository cinemaRepository;
    private HallRepository hallRepository;
    private PlaceRepository placeRepository;
    private MovieRepository movieRepository;
    private MovieProjectionRepository movieProjectionRepository;

    private MovieSessionRepository movieSessionRepository;
    private TicketRepository ticketRepository;

    public CinemaInitServiceImpl(TownRepository townRepository,
                                 CinemaRepository cinemaRepository,
                                 HallRepository hallRepository,
                                 PlaceRepository placeRepository,
                                 MovieRepository movieRepository,
                                 MovieProjectionRepository movieProjectionRepository,
                                 MovieSessionRepository movieSessionRepository,
                                 TicketRepository ticketRepository) {
        this.townRepository = townRepository;
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.placeRepository = placeRepository;
        this.movieRepository = movieRepository;
        this.movieProjectionRepository = movieProjectionRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void initTowns() {
        Stream.of("Tunis", "Marsa", "Carthage", "Lac1").forEach(town -> {
            townRepository.save(new Town(town));
        });
    }

    @Override
    public void initCinemas() {
        townRepository.findAll().forEach(town -> {
            Stream.of("Pathé", "CinéVog", "CinémaD'ART").forEach(cin -> {
                Cinema cinema = new Cinema();
                cinema.setTown(town);
                cinema.setHallsNumber(3+(int)(Math.random()*7));
                cinema.setCinemaName(cin);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initHalls() {
        cinemaRepository.findAll().forEach(cinema -> {
            for(int i = 0; i < cinema.getHallsNumber(); i++){
                Hall hall = new Hall();
                hall.setHallName("[" + cinema.getCinemaName() + "]-Hall-" + (i+1) + ".");
                hall.setCinema(cinema);
                hall.setPlacesNumber(15 + (int)(Math.random()*25));
                hallRepository.save(hall);
            }
        });
    }

    @Override
    public void initPlaces() {
    hallRepository.findAll().forEach(hall -> {
        for (int i = 0; i < hall.getPlacesNumber(); i++) {
            Place place = new Place();
            place.setNbPlace(i+1);
            place.setHall(hall);
            placeRepository.save(place);
        }
    });
    }

    @Override
    public void initMovieSessions() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "18:00", "21:00", "00:00")
                .forEach(heure -> {
                    MovieSession movieSession = new MovieSession();
                    try {
                        movieSession.setStartTime(dateFormat.parse(heure));
                        movieSessionRepository.save(movieSession);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void initMovies() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        movieRepository.save(new Movie(
                null,
                "Crazy, Stupid, Love",
                MovieCategory.ROMANCE,
                118.0,
                "Glenn Ficarra",
                "Cal, a middle-aged man, is heartbroken when his wife asks for a divorce. However, to get over his loss, he starts picking up women at a bar on the insistence of his new-found friend, Jacob.",
                "crazystupidlove.jpg",
                format.parse("2011-07-19"),
                new ArrayList<>()));

        movieRepository.save(new Movie(
                null,
                "Jigsaw",
                MovieCategory.HORROR,
                91,
                "Michael Spierig",
                "The police are at a dead end when investigating numerous ghastly murders in the city that resembles the work of a serial killer who is known to be dead since ten years.",
                "jigsaw.jpg",
                format.parse("2017-10-27"),
                new ArrayList<>()));

        movieRepository.save(new Movie(
                null,
                "Palm Springs",
                MovieCategory.COMEDY,
                90,
                "Max Barbakow",
                "Stuck in a time loop, two wedding guests develop a budding romance while living the same day over and over again.",
                "palmspring.jpg",
                format.parse("2020-07-10"),
                new ArrayList<>()));

        movieRepository.save(new Movie(
                null,
                "The Storm",
                MovieCategory.DRAMA,
                110,
                "Ben Sombogaart",
                "After surviving a devastating flood, a woman (Sylvia Hoeks) embarks on a frantic quest to find her missing baby.",
                "thestorm.jpg",
                format.parse("2009-09-17"),
                new ArrayList<>()));

        movieRepository.save(new Movie(
                null,
                "Warrior",
                MovieCategory.ACTION,
                140,
                "Gavin O'Connor",
                "Former Marine Tommy returns home and gets his father to train him for a mixed martial arts contest. But his fight has more at stake than the prize money since he is going to fight his own brother.",
                "warrior.jpg",
                format.parse("2011-09-09"),
                new ArrayList<>()));
    }

    @Override
    public void initMovieProjections() {
        double[] prices = {30,25,40,110,85};
        //LOOPING THROUGH EACH TOWN
        townRepository.findAll().forEach(town -> {
            //LOOPING THROUGH EACH CINEMA OF THE TOWN
            town.getCinemas().forEach(cinema -> {
                //LOOPING THROUGH EACH HALL OF THE CINEMA
                cinema.getHalls().forEach(hall -> {
                     movieRepository.findAll().forEach(movie -> {
                         movieSessionRepository.findAll().forEach(movieSession -> {
                             MovieProjection movieProjection = new MovieProjection();
                             movieProjection.setProjectionDate(new Date());
                             movieProjection.setMovie(movie);
                             movieProjection.setPrice(prices[new Random().nextInt(prices.length)]);
                             movieProjection.setHall(hall);
                             movieProjection.setMovieSession(movieSession);
                             movieProjectionRepository.save(movieProjection);
                         });
                     });
                });
            });

        });

    }

    @Override
    public void initTickets() {
        movieProjectionRepository.findAll().forEach(movieProjection -> {
           movieProjection.getHall().getPlaces().forEach(place -> {
               Ticket ticket = new Ticket();
               ticket.setPrice(movieProjection.getPrice());
               ticket.setPlace(place);
               ticket.setMovieProjection(movieProjection);
               ticket.setReserved(false);
               ticketRepository.save(ticket);
           });
        });
    }
}
