package theater;

public class Reservation {
    static final Reservation NONE = new Reservation(null, null, null, null, 0);

    final Theater theater;
    final Movie movie;
    final ScreeningPlace screeningPlace;
    final Screening screening;
    final int count;

    Reservation(Theater theater, Movie movie, ScreeningPlace screeningPlace, Screening screening, int audienceCount) {
        this.theater = theater;
        this.movie = movie;
        this.screeningPlace = screeningPlace;
        this.screening = screening;
        this.count = audienceCount;
    }
}


