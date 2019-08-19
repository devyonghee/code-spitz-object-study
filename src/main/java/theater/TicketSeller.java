package theater;

import java.security.InvalidParameterException;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public void setTicketOffice(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public Ticket getTicket(Audience audience, Movie movie) {
        try {
            if (movie.isFree()) {
                return ticketOffice.getTicketWithNoFee(movie);
            }

            if (audience.hasInvitation(movie)) {
                Ticket ticket = ticketOffice.getTicketWithNoFee(movie);
                if (ticket != Ticket.EMPTY) audience.removeInvitation(movie);
                return ticket;
            }

            if (audience.enoughAmount(movie)) {
                Ticket ticket = ticketOffice.getTicketWithFee(movie);
                if (ticket != Ticket.EMPTY) audience.minusAmount(movie);
                return ticket;
            }

        } catch (InvalidParameterException ignored) {
        }

        return Ticket.EMPTY;
    }
}
