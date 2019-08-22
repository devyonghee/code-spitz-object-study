package theater;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ScreeningPlace {
    public static final ScreeningPlace EMPTY = new ScreeningPlace(null, 0);

    private final String name;
    private Integer seat;
    private Set<Screening> screenings = new HashSet<>();

    public ScreeningPlace(String name, Integer seat) {
        this.name = name;
        this.seat = seat;
    }

    public boolean hasSeat(int count) {
        return this.seat.compareTo(count) >= 0;
    }

    public void addScreening(Screening... screening) {
        this.screenings.addAll(Arrays.asList(screening));
    }

    public boolean hasScreenings(Screening screening) {
        return this.screenings.contains(screening);
    }

    public Set<Screening> getScreening() {
        return this.screenings;
    }

    void minusSeat(int count) {
        if (hasSeat(count)) seat -= count;
        else throw new RuntimeException("no seat");
    }
}
