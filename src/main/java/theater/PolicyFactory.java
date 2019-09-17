package theater;


import java.util.Set;

public interface PolicyFactory extends Calculator {
    default Money calculateFee(Screening screening, int count, Money fee) {
        for (DiscountCondition condition : getConditions()) {
            if (condition.isSatisfiedBy(screening, count)) return calculateFee(fee);
        }
        return fee;
    }

    Set<DiscountCondition> getConditions();
}
