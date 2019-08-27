package theater;

import java.util.*;

class Theater {
    public static final Set<Screening> EMPTY = new HashSet<>();
    private final Set<TicketOffice> ticketOffices = new HashSet<>();
    private final Set<ScreeningPlace> screeningPlaces = new HashSet<>();
    private final Map<Movie, ScreeningPlace> movies = new HashMap<>();

    private Money amount;

    public Theater(Money amount, ScreeningPlace... screeningPlaces) {
        this.amount = amount;
        this.screeningPlaces.addAll(Arrays.asList(screeningPlaces));
    }

    public void addMovie(Movie movie) {
        if (this.movies.containsKey(movie)) return;
        ScreeningPlace screeningPlace = this.screeningPlaces.stream().findAny().orElseThrow(RuntimeException::new);
        movies.put(movie, screeningPlace);
    }

    public void addScreening(Movie movie, Screening screening) {
        if (!this.movies.containsKey(movie)) return;
        this.movies.get(movie).addScreening(screening);
    }

    public void contractTicketOffice(TicketOffice ticketOffice, Double rate) {
        if (!ticketOffice.contract(this, rate)) return;
        this.ticketOffices.add(ticketOffice);
    }

    public void cancelTicketOffice(TicketOffice ticketOffice) {
        if (!this.ticketOffices.contains(ticketOffice) || !ticketOffice.cancel(this)) return;
        this.ticketOffices.remove(ticketOffice);
    }

    void plusAmount(Money amount) {
        this.amount = this.amount.plus(amount);
    }

    public ScreeningPlace getScreeningPlace(Movie movie) {
        if (!this.movies.containsKey(movie)) return ScreeningPlace.EMPTY;
        return this.movies.get(movie);
    }

    public Set<Screening> getScreening(Movie movie, ScreeningPlace screeningPlace) {
        if (!this.movies.containsKey(movie) && !this.movies.get(movie).equals(screeningPlace)) return EMPTY;
        return screeningPlace.getScreening();
    }

    boolean isValidScreening(RequestOrder requestOrder) {
        requestOrder.isValidMovie(movies);
        return movies.containsKey(movie) && movies.get(movie).hasScreenings(screening);
    }

    boolean isValidScreening(Movie movie, Screening screening) {
        return movies.containsKey(movie) && movies.get(movie).hasScreenings(screening);
    }

    public boolean enter(Customer customer, int count) {
        Reservation reservation = customer.reservation;
        return reservation != Reservation.NONE &&
                reservation.theater == this &&
                isValidScreening(reservation.movie, reservation.screening) &&
                reservation.count == count;
    }

    Reservation reserve(Movie movie, ScreeningPlace screeningPlace, Screening screening, int count) {
        if (!isValidScreening(movie, screening) || !screeningPlace.hasSeat(count)) return Reservation.NONE;
        screeningPlace.minusSeat(count);
        return new Reservation(this, movie, screeningPlace, screening, count);
    }
}