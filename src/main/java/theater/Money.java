package theater;

public class Money {
    public static Money of(Double amount) {
        return new Money(amount);
    }

    private final Double amount;

    public Money(Double amount) {
        this.amount = amount;
    }

    public Money minus(Money amount) {
        return new Money(this.amount > amount.amount ? this.amount - amount.amount : 0.0);
    }

    public Money plus(Money amount) {
        return new Money(this.amount + amount.amount);
    }

    public Money multi(Double times) {
        return new Money(this.amount * times);
    }

    public boolean greaterThen(Money amount) {
        return this.amount >= amount.amount;
    }
}
