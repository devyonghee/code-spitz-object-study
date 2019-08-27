package theater;

public class Customer {
    Reservation reservation = Reservation.NONE;
    private Money amount;

    public Customer(Money amount) {
        this.amount = amount;
    }

    public void reserve(TicketSeller seller, Theater theater, Movie movie, ScreeningPlace screeningPlace, Screening screening, int count) {
        RequestOrder requestOrder = new RequestOrder(movie, screeningPlace, screening, count);
        reservation = seller.reserve(this, theater, requestOrder);
    }

    boolean hasAmount(Money amount) {
        return this.amount.greaterThen(amount);
    }

    void minusAmount(Money amount) {
        this.amount = this.amount.minus(amount);
    }
}
