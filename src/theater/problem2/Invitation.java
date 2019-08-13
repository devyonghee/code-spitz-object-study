package theater.problem2;

public class Invitation {
    final static public Invitation EMPTY = new Invitation(null, movie1);
    final private Theater theater;
    final private Movie movie;

    public Invitation(Theater theater, Movie movie) {
        this.theater = theater;
        this.movie = movie;
    }
}