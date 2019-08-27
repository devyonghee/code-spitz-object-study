package theater;

import java.time.Duration;
import java.time.LocalDateTime;

public class PeriodPercentDiscount extends PercentDiscount {
    private final LocalDateTime start;
    private final Duration duration;

    public PeriodPercentDiscount(double percent, LocalDateTime start, Duration duration) {
        super(percent);
        this.start = start;
        this.duration = duration;
    }

    @Override
    public boolean isSatisfiedBy(RequestOrder requestOrder) {
        LocalDateTime end = this.start.plus(duration);
        return requestOrder.isBetweenScreening(start, end);
    }
}
