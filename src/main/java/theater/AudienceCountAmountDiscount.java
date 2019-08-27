package theater;

public class AudienceCountAmountDiscount extends AmountDiscount {
    private final Integer audienceCount;

    public AudienceCountAmountDiscount(Money amount, Integer audienceCount) {
        super(amount);
        this.audienceCount = audienceCount;
    }

    @Override
    public boolean isSatisfiedBy(RequestOrder requestOrder) {
        return requestOrder.isOverAudienceCount(this.audienceCount);
    }
}
