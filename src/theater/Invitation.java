package theater;

public class Invitation {
    final static public Invitation EMPTY = new Invitation(null, null);
    final private Theater theater;
    final private Movie movie;

    public Invitation(Theater theater, Movie movie) {
        this.theater = theater;
        this.movie = movie;
    }

    public boolean isMovie(Movie movie) {
        return movie.equals(this.movie);
    }
}