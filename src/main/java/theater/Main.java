package theater;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {
    // 현재의 예제는 영화와 상영이라는 컨텍스트로 역할로 예매를 진행한다.
    // 상영은 본디 시간표일 뿐이므로 좌석수 등을 갖을 수 없다.
    // 극장이 상영관을 소유하게 하고 상영이 상영관과 협력하여 예매시의 잔여좌석수를 관리하도록 개선하라.
    public static void main(String[] args) {
//        Theater theater = new Theater(Money.of(100.0));
//        Movie<AmountDiscount> movie = new Movie<>(
//                "spidermain",
//                Duration.ofMinutes(120L),
//                Money.of(5000.0),
//                new SequenceAmountDiscount(Money.of(1000.0), 1)
//        );
//
//        theater.addMovie(movie);
//
//        for (int day = 7; day < 32; day++) {
//            for (int hour = 10, seq = 1; hour < 24; hour += 3, seq++) {
//                theater.addScreening(
//                        movie,
//                        new Screening(
//                                seq,
//                                LocalDateTime.of(2019, 7, day, hour, 00, 00),
//                        )
//                );
//            }
//        }
//
//        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
//        theater.contractTicketOffice(ticketOffice, 10.0);
//        Customer customer = new Customer(Money.of(0.0));
//
//        TicketSeller seller = new TicketSeller();
//        seller.setTicketOffice(ticketOffice);
//
//        Screening screening = theater.getScreening(movie).iterator().next();
//        customer.reserve(seller, theater, movie, screening, 2);
//        boolean isOk = theater.enter(customer, 2);
//        System.out.println(isOk);
    }
}
