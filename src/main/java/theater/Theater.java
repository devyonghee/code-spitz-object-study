package theater;

import java.util.*;

class Theater {
    public static final Set<Screening> EMPTY = new HashSet<>();
    private final Set<TicketOffice> ticketOffices = new HashSet<>();
    private final Map<Movie, Set<Screening>> movies = new HashMap<>();
    private Money amount;

    public Theater(Money amount) {
        this.amount = amount;
    }

    public boolean addMovie(Movie movie) {
        if (this.movies.containsKey(movie)) return false;
        movies.put(movie, new HashSet<>());
        return true;
    }

    public boolean addScreening(Movie movie, Screening screening) {
        if (!this.movies.containsKey(movie)) return false;
        return movies.get(movie).add(screening);
    }

    public boolean contractTicketOffice(TicketOffice ticketOffice, Double rate) {
        if (!ticketOffice.contract(this, rate)) return false;
        return this.ticketOffices.add(ticketOffice);
    }

    public boolean cancelTicketOffice(TicketOffice ticketOffice) {
        if (!this.ticketOffices.contains(ticketOffice) || !ticketOffice.cancel(this)) return false;
        return this.ticketOffices.remove(ticketOffice);
    }

    void plusAmount(Money amount) {
        this.amount = this.amount.plus(amount);
    }

    public Set<Screening> getScreening(Movie movie) {
        if (!this.movies.containsKey(movie) || this.movies.get(movie).size() == 0) return EMPTY;
        return this.movies.get(movie);
    }

    boolean isValidScreening(Movie movie, Screening screening) {
        return movies.containsKey(movie) && movies.get(movie).contains(screening);
    }

    public boolean enter(Customer customer, int count) {
        Reservation reservation = customer.reservation;
        return reservation != Reservation.NONE &&
                reservation.theater != this &&
                isValidScreening(reservation.movie, reservation.screening) &&
                reservation.count == count;
    }

    Reservation reserve(Movie movie, Screening screening, int count) {
        if (!isValidScreening(movie, screening) || !screening.hasSeat(count)) return Reservation.NONE;
        screening.reserveSeat(count);
        return new Reservation(this, movie, screening, count);
    }
}