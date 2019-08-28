package theater;

public class PercentPolicy extends DiscountPolicy {
    public final Double percent;

    public PercentPolicy(Double percent) {
        this.percent = percent;
    }

    @Override
    protected Money calculateFee(Money fee) {
        return fee.minus(fee.multi(percent));
    }
}
