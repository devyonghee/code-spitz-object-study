package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Audience {
    private List<Ticket> tickets = new ArrayList<>();
    private List<Invitation> invitations = new ArrayList<>();
    private Long amount;

    public Audience(Long amount) {
        this.amount = amount;
    }

    // 유일하게 바깥에 노출되는 메소드 다른 메소드들은 같은 패키지 내에서 사용되기 위한 접대하는 메소드이다.
    public void buyTicket(TicketSeller seller, Movie movie) {
        Ticket ticket = seller.getTicket(this, movie);
        if (!ticket.equals(Ticket.EMPTY)) this.tickets.add(ticket);
    }

    public void minusAmount(Movie movie) {
        if (movie.getFee() > this.amount) return;
        this.amount -= movie.getFee();
    }

    public void removeInvitation(Movie movie) {
        Optional<Invitation> first = this.invitations.stream().filter(invitation -> invitation.isMovie(movie)).findFirst();
        first.ifPresent(invitation -> this.invitations.remove(invitation));
    }

    public Ticket getTicket(Movie movie) {
        Optional<Ticket> first = this.tickets.stream().filter(ticket -> ticket.isUnusedMovieTicket(movie)).findFirst();
        return first.orElse(Ticket.EMPTY);
    }

    public void setInvitation(Invitation invitation) {
        this.invitations.add(invitation);
    }

    public boolean hasInvitation(Movie movie) {
        Optional<Invitation> first = this.invitations.stream().filter(invitation -> invitation.isMovie(movie)).findFirst();
        return !first.orElse(Invitation.EMPTY).equals(Invitation.EMPTY);
    }

    public boolean enoughAmount(Movie movie) {
        return this.amount >= movie.getFee();
    }
}