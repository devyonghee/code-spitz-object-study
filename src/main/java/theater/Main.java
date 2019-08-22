package theater;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    //본 예제에서는 Sequence를 통한 할인조건만 구현되어 있다.
    // Period 및 한번에 예약하는 사람의 수가 일정수를
    // 넘어가면 할인해주는 Count 조건에 따른 할인조건을 구현하라.
    public static void main(String[] args) {
        Theater theater = new Theater(Money.of(100.0));
        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0),
                new SequenceAmountDiscount(Money.of(1000.0), 1)
        );

        theater.addMovie(movie);

        for (int day = 7; day < 32; day++) {
            for (int hour = 10, seq = 1; hour < 24; hour += 3, seq++) {
                theater.addScreening(
                        movie,
                        new Screening(
                                seq,
                                LocalDateTime.of(2019, 7, day, hour, 00, 00),
                                100    // 가용 가능한 좌석 수
                        )
                );
            }
        }

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);
        Customer customer = new Customer(Money.of(0.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        Screening screening = theater.getScreening(movie).iterator().next();
        customer.reserve(seller, theater, movie, screening, 2);
        boolean isOk = theater.enter(customer, 2);
        System.out.println(isOk);
    }
}
