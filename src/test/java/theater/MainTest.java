package theater;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void 일반_구매_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0),
                new ScreeningPlace("A", 80),
                new ScreeningPlace("B", 50),
                new ScreeningPlace("C", 50)
        );

        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0)
        );

        theater.addMovie(movie);

        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);

        Customer customer1 = new Customer(Money.of(5000.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        ScreeningPlace screeningPlace = theater.getScreeningPlace(movie);
        Screening screening = theater.getScreening(movie, screeningPlace).iterator().next();
        customer1.reserve(seller, theater, movie, screeningPlace ,screening, 1);
        assertThat(theater.enter(customer1, 1)).isTrue();
    }


    @Test
    public void 자리_제한_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0),
                new ScreeningPlace("A", 50),
                new ScreeningPlace("B", 50),
                new ScreeningPlace("C", 50)
        );

        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0)
        );

        theater.addMovie(movie);
        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);

        Customer customer1 = new Customer(Money.of(Double.MAX_VALUE));
        Customer customer2 = new Customer(Money.of(Double.MAX_VALUE));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        ScreeningPlace screeningPlace = theater.getScreeningPlace(movie);
        Screening screening = theater.getScreening(movie, screeningPlace).iterator().next();
        customer1.reserve(seller, theater, movie, screeningPlace ,screening, 49);
        customer2.reserve(seller, theater, movie, screeningPlace ,screening, 2);
        assertThat(theater.enter(customer1, 49)).isTrue();
        assertThat(theater.enter(customer2, 2)).isFalse();
    }

    private void setScreening(Theater theater, Movie movie) {
        for (int day = 7; day < 32; day++) {
            for (int hour = 10, seq = 1; hour < 24; hour += 3, seq++) {
                theater.addScreening(
                        movie,
                        new Screening(
                                seq,
                                LocalDateTime.of(2019, 8, day, hour, 00, 00)
                        )
                );
            }
        }
    }

}