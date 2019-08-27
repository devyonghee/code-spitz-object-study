package theater;

import java.util.*;

public class TicketOffice {
    private Money amount;
    private Map<Theater, Double> commissionRate = new HashMap<>();

    public TicketOffice(Money amount) {
        this.amount = amount;
    }

    boolean contract(Theater theater, Double rate) {
        if (commissionRate.containsKey(theater)) return false;
        commissionRate.put(theater, rate);
        return true;
    }

    boolean cancel(Theater theater) {
        if (!commissionRate.containsKey(theater)) return false;
        commissionRate.remove(theater);
        return true;
    }

    Reservation reserve(Theater theater, RequestOrder requestOrder) {
        if (!commissionRate.containsKey(theater) ||
                !theater.isValidScreening(requestOrder) ||
                !screeningPlace.hasSeat(count)
        ) return Reservation.NONE;

        Reservation reservation = theater.reserve(movie, screeningPlace, screening, count);
        if (reservation != Reservation.NONE) {
            Money sales = movie.calculateFee(screening, count);
            Money commission = sales.multi(commissionRate.get(theater));
            amount = amount.plus(commission);
            theater.plusAmount(sales.minus(commission));
        }
        return reservation;
    }
}