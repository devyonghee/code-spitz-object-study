package theater;

import com.sun.xml.internal.bind.v2.model.impl.ModelBuilderI;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class RequestOrder {
    private Movie movie;
    private ScreeningPlace screeningPlace;
    private Screening screening;
    private Integer count;

    public RequestOrder(Movie movie, ScreeningPlace screeningPlace, Screening screening, Integer count) {
        this.movie = movie;
        this.screeningPlace = screeningPlace;
        this.screening = screening;
        this.count = count;
    }

    public Money calculateFee() {
        return this.movie.calculateFee(this).multi((double) this.count);
    }

    public boolean isOverAudienceCount(Integer audienceCount) {
        return this.count.compareTo(audienceCount) >= 0;
    }

    public boolean isBetweenScreening(LocalDateTime start, LocalDateTime end) {
        return screening.isBetween(start, end);
    }

    public boolean equalsScreeningSequence(int sequence) {
        return screening.equalsSequence(sequence);
    }

    public boolean isValidMovie(Set<Movie> movies) {
        return movies.contains(this.movie);
    }
}
