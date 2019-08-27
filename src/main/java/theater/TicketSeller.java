package theater;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public void setTicketOffice(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public Reservation reserve(Customer customer, Theater theater, RequestOrder requestOrder) {
        Reservation reservation = Reservation.NONE;
        Money price = requestOrder.calculateFee();
        if (customer.hasAmount(price)) {
            reservation = ticketOffice.reserve(theater, requestOrder);
            if (reservation != Reservation.NONE) customer.minusAmount(price);
        }
        return reservation;
    }
}
