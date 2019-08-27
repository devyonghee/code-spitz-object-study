package theater;

interface DiscountCondition {
    public boolean isSatisfiedBy(RequestOrder requestOrder);
    public Money calculateFee(Money fee);
}