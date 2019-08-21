package theater;

public class AudienceCountPercentDiscount extends PercentDiscount {
    private final Integer audienceCount;

    public AudienceCountPercentDiscount(double percent, Integer audienceCount) {
        super(percent);
        this.audienceCount = audienceCount;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening, int audienceCount) {
        return this.audienceCount.compareTo(audienceCount) <= 0;
    }
}
