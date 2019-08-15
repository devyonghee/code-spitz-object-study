package theater;

import java.util.List;
import java.util.Optional;

class Audience {
    private List<Ticket> tickets;
    private List<Invitation> invitations;
    private Long amount;

    public Audience(Long amount) {
        this.amount = amount;
    }

    // 유일하게 바깥에 노출되는 메소드 다른 메소드들은 같은 패키지 내에서 사용되기 위한 접대하는 메소드이다.
    public void buyTicket(TicketSeller seller, Movie movie) {
        this.tickets.add(seller.getTicket(this, movie));
    }

    public boolean hasAmount(Long amount) {
        return this.amount >= amount;
    }

    public boolean minusAmount(Long amount) {
        if (amount > this.amount) return false;
        this.amount -= amount;
        return true;
    }

    public Invitation getInvitation(Movie movie) {
        Optional<Invitation> first = this.invitations.stream().filter(invitation -> invitation.isMovie(movie)).findFirst();
        return first.orElse(Invitation.EMPTY);
    }

    public void removeInvitation(Movie movie) {
        Optional<Invitation> first = this.invitations.stream().filter(invitation -> invitation.isMovie(movie)).findFirst();
        first.ifPresent(invitation -> this.invitations.remove(invitation));
    }

    public Ticket getTicket(Movie movie) {
        Optional<Ticket> first = this.tickets.stream().filter(ticket -> ticket.isMovie(movie)).findFirst();
        if (!first.isPresent()) return Ticket.EMPTY;
        return this.tickets.remove(this.tickets.indexOf(first.get()));
    }

    public void setInvitation(Invitation invitation) {
        this.invitations.add(invitation);
    }
}