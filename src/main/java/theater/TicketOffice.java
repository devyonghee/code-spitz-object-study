package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Theater theater, Long amount) {
        theater.setTicketOffices(this);
        this.amount = amount;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public Ticket getTicketWithFee(Movie movie) {
        Optional<Ticket> first = findTicket(movie);
        if (!first.isPresent()) return Ticket.EMPTY;

        amount += movie.getFee();
        return tickets.remove(tickets.indexOf(first.get()));
    }

    public Ticket getTicketWithNoFee(Movie movie) {
        Optional<Ticket> first = findTicket(movie);
        if (!first.isPresent()) return Ticket.EMPTY;
        return tickets.remove(tickets.indexOf(first.get()));
    }

    private Optional<Ticket> findTicket(Movie movie) {
        return tickets.stream().filter(ticket -> ticket.isUnusedMovieTicket(movie)).findFirst();
    }
}