package theater;

public class SequenceAmountDiscount extends AmountDiscount {
    private final int sequence;

    public SequenceAmountDiscount(Money amount, int sequence) {
        super(amount);
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(RequestOrder requestOrder) {
        return requestOrder.equalsScreeningSequence(sequence);
    }


}
