package theater;

public class AmountPolicy extends DiscountPolicy {
    public final Money amount;

    public AmountPolicy(Money amount) {
        this.amount = amount;
    }

    @Override
    protected Money calculateFee(Money fee) {
        return fee.minus(amount);
    }
}
