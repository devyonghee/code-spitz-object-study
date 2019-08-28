package theater;

interface DiscountCondition {
    public boolean isSatisfiedBy(Screening screening, int audienceCount);
}