package com.example.CinemaServiceApplication.service;

import java.text.ParseException;

public interface ICinemaInitService {
    public void initTowns();
    public void initCinemas();
    public void initHalls();
    public void initPlaces();
    public void initMovieSessions();
    public void initMovies() throws ParseException;
    public void initMovieProjections();
    public void initTickets();
}
