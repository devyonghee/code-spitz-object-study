package theater;

import java.time.LocalDateTime;

public class Screening {
    final int sequence;
    final LocalDateTime whenScreened;

    public Screening(int sequence, LocalDateTime when) {
        this.sequence = sequence;
        this.whenScreened = when;
    }

    public boolean isBetween(LocalDateTime start, LocalDateTime end) {
        return whenScreened.isAfter(start) && whenScreened.isBefore(end);
    }

    public boolean equalsSequence(int sequence) {
        return this.sequence == sequence;
    }
}
