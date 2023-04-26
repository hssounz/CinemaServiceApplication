package com.example.CinemaServiceApplication.web;

import com.example.CinemaServiceApplication.dao.MovieRepository;
import com.example.CinemaServiceApplication.dao.TicketRepository;
import com.example.CinemaServiceApplication.entities.Movie;
import com.example.CinemaServiceApplication.entities.Ticket;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {

    MovieRepository movieRepository;
    TicketRepository ticketRepository;

    public CinemaController(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(value = "/movies/{id}/banner", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name = "id")Long id) throws ChangeSetPersister.NotFoundException, IOException {
        Movie movie = movieRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        String banner = movie.getBanner();
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+banner);
        return Files.readAllBytes(file.toPath());
    }

    @PostMapping("/payment")
    @Transactional
    public List<Ticket> payment(@RequestBody TicketAttr ticketAttr) {
            List<Ticket> tickets = new ArrayList<>();
            ticketAttr.getTickets().forEach(ticketId -> {
            Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(RuntimeException::new);
            ticket.setCustomer(ticketAttr.getCustomer());
            ticket.setReserved(true);
            ticket.setPaymentCode(ticketAttr.getPaymentCode());
            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
    return tickets;
    }
}
@Data
class TicketAttr{
    private String customer;
    private List<Long> tickets = new ArrayList<>();
    private int paymentCode;
}