package theater;

public class AudienceCountPercentDiscount extends PercentDiscount {
    private final Integer audienceCount;

    public AudienceCountPercentDiscount(double percent, Integer audienceCount) {
        super(percent);
        this.audienceCount = audienceCount;
    }

    @Override
    public boolean isSatisfiedBy(RequestOrder requestOrder) {
        return requestOrder.isOverAudienceCount(this.audienceCount);
    }
}
