package theater;

public class Movie {

    final private Long fee;

    public Movie(Long fee) {
        this.fee = fee;
    }

    public Long getFee() {
        return fee;
    }

    public boolean isFree() {
        return this.fee <= 0;
    }
}