package theater;


public class DiscountPolicy {
    private final PolicyFactory factory;

    public DiscountPolicy(PolicyFactory factory) {
        this.factory = factory;
    }

    public Money calculateFee(Screening screening, int count, Money fee) {
        return factory.calculateFee(screening, count, fee);
    }
}
