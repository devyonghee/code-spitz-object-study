package theater;

import java.time.Duration;
import java.time.LocalDateTime;

public class PeriodAmountDiscount extends AmountDiscount {
    private final LocalDateTime start;
    private final Duration duration;

    public PeriodAmountDiscount(Money amount, LocalDateTime start, Duration duration) {
        super(amount);
        this.start = start;
        this.duration = duration;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening, int audienceCount) {
        LocalDateTime end = this.start.plus(duration);
        return start.isAfter(screening.whenScreened) && end.isBefore(screening.whenScreened);
    }
}
