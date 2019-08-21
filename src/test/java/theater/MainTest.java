package theater;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void 정상적으로_구매한_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(100.0));
        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0),
                new SequenceAmountDiscount(Money.of(1000.0), 1)
        );

        theater.addMovie(movie);

        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);
        Customer customer = new Customer(Money.of(10000.0));

        TicketSeller seller = new TicketSeller();
        Screening screening = theater.getScreening(movie).iterator().next();
        customer.reserve(seller, theater, movie, screening, 2);
        assertThat(theater.enter(customer, 2)).isTrue();
    }

    private void setScreening(Theater theater, Movie movie) {
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
    }


    @Test
    public void 돈_없는_관객_입장_테스트() {

    }

    @Test
    public void 초대장_가진_관객_입장_테스트() {

    }

    @Test
    public void 초대장_다량_보유_테스트() {

    }


    @Test
    public void 티켓_다량_보유_테스트() {

    }


    @Test
    public void 다양한_가격_영화_관람_테스트() {

    }

    @Test
    public void 무료_영화_테스트() {

    }


}