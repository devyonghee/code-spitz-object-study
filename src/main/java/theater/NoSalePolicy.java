package theater;

public class NoSalePolicy extends DiscountPolicy {
    @Override
    protected Money calculateFee(Money fee) {
        return fee;
    }
}
