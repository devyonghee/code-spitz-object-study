package theater;

import java.util.HashSet;
import java.util.Set;

public class AmountCalculatorFactory implements PolicyFactory {
    public final Money money;
    private AmountCalculator cache;
    private final Set<DiscountCondition> conditions = new HashSet<>();

    public AmountCalculatorFactory(Money money) {
        this.money = money;
    }

    synchronized public Calculator getCalculator() {
        if (cache == null) cache = new AmountCalculator(money);
        return cache;
    }

    public void addCondition(DiscountCondition condition) {
        conditions.add(condition);
    }

    public void removeCondition(DiscountCondition condition) {
        conditions.remove(condition);
    }

    @Override
    public Money calculateFee(Money fee) {
        return getCalculator().calculateFee(fee);
    }

    @Override
    public Set<DiscountCondition> getConditions() {
        return conditions;
    }
}