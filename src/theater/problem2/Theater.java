package theater.problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Theater {
    final private List<TicketOffice> ticketOffices = new ArrayList<>();
    final private List<Movie> movies = new ArrayList<>();
    final private Long fee;

    public Theater(Long fee) {
        this.fee = fee;
    }

    Long getFee() {
        return this.fee;
    }

    public void setTicketOffices(TicketOffice... ticketOffices) {
        Collections.addAll(this.ticketOffices, ticketOffices);
    }

    // ticketOffices 에 Theater가 생성한 Ticket을 추가해준다.
    public void setTicket(TicketOffice ticketOffice, Movie movie, Long num) {
        if (!ticketOffices.contains(ticketOffice) || !movies.contains(movie)) return;
        while (num-- > 0) {
            // Ticket 에는 반드시 객체를 구별하기 위해서 객체 식별자를 넣어 주어야 한다.
            // name 같은 값을 넣으면 name 만으로 다른 극장을 구분하기 힘들어진다.
            ticketOffice.addTicket(new Ticket(this, movie));
        }
    }

    // Invitation 도 마찬가지로 Theater가 theater.Audience 만들어서 제공을 한다.
    public void setInvitation(Audience audience, Movie movie1) {
        audience.setInvitation(new Invitation(this, movie1));
    }

    public boolean enter(Audience audience) {
        Ticket ticket = audience.getTicket();
        return ticket.isValid(this);
    }

    public void setMovie(Movie movie) {
        this.movies.add(movie);
    }
}