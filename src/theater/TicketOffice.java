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
        Optional<Ticket> ticket = tickets.stream().filter(tic -> tic.getMovie() == movie).findFirst();
        if (!ticket.isPresent()) return Ticket.EMPTY;
        else {
            Ticket removedTicket = tickets.remove(tickets.indexOf(ticket.get()));
            amount += removedTicket.getFee();
            return removedTicket;
        }
    }

    public Ticket getTicketWithNoFee(Movie movie) {
        Optional<Ticket> ticket = tickets.stream().filter(tic -> tic.getMovie() == movie).findFirst();
        if (!ticket.isPresent()) return Ticket.EMPTY;
        else return tickets.remove(tickets.indexOf(ticket.get()));
    }

    public Long getTicketPrice(Movie movie) {
        Optional<Ticket> ticket = tickets.stream().filter(tic -> tic.getMovie() == movie).findFirst();
        if (!ticket.isPresent()) return 0L;
        else return ticket.get().getFee();
    }
}
