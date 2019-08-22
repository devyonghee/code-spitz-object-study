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
        Theater theater = new Theater(Money.of(0.0));
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
        Customer customer2 = new Customer(Money.of(4000.0));
        Customer customer3 = new Customer(Money.of(7000.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        Screening screening = theater.getScreening(movie).iterator().next();
        customer1.reserve(seller, theater, movie, screening, 1);
        customer2.reserve(seller, theater, movie, screening, 1);
        customer3.reserve(seller, theater, movie, screening, 2);
        assertThat(theater.enter(customer1, 1)).isTrue();
        assertThat(theater.enter(customer2, 1)).isFalse();
        assertThat(theater.enter(customer3, 1)).isFalse();
    }


    @Test
    public void 영화_순서_총_할인_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0));
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

        Customer customer1 = new Customer(Money.of(4000.0));
        Customer customer2 = new Customer(Money.of(4000.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);
        Iterator<Screening> iterator = theater.getScreening(movie).stream().sorted(Comparator.comparing(screening -> screening.whenScreened)).iterator();
        Screening firstScreening = iterator.next();
        Screening secondScreening = iterator.next();

        customer1.reserve(seller, theater, movie, firstScreening, 1);
        customer2.reserve(seller, theater, movie, secondScreening, 1);
        assertThat(theater.enter(customer1, 1)).isTrue();
        assertThat(theater.enter(customer2, 1)).isFalse();
    }

    @Test
    public void 순서_퍼센트_할인_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0));
        Movie<PercentDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0),
                new SequencePercentDiscount(50, 1)
        );

        theater.addMovie(movie);

        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);

        Customer customer1 = new Customer(Money.of(2500.0));
        Customer customer2 = new Customer(Money.of(2500.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);
        Iterator<Screening> iterator = theater.getScreening(movie).stream().sorted(Comparator.comparing(screening -> screening.whenScreened)).iterator();
        Screening firstScreening = iterator.next();
        Screening secondScreening = iterator.next();

        customer1.reserve(seller, theater, movie, firstScreening, 1);
        customer2.reserve(seller, theater, movie, secondScreening, 1);
        assertThat(theater.enter(customer1, 1)).isTrue();
        assertThat(theater.enter(customer2, 1)).isFalse();
    }

    @Test
    public void 기간_총_할인_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0));
        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0),
                new PeriodAmountDiscount(Money.of(1000.0), LocalDateTime.now(), Duration.ofDays(3))
        );

        theater.addMovie(movie);

        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);

        Customer customer1 = new Customer(Money.of(4000.0));
        Customer customer2 = new Customer(Money.of(4000.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);
        Supplier<Stream<Screening>> screeningStreamSupplier = () -> theater.getScreening(movie).stream().sorted(Comparator.comparing(screening -> screening.whenScreened));

        Screening todayScreening = screeningStreamSupplier.get().filter(screening ->
                screening.whenScreened.isAfter(LocalDateTime.now()) && screening.whenScreened.isBefore(LocalDateTime.now().plus(Duration.ofDays(1)))
        ).findFirst().orElseThrow(RuntimeException::new);
        Screening pastScreening = screeningStreamSupplier.get().findFirst().orElseThrow(RuntimeException::new);

        customer1.reserve(seller, theater, movie, todayScreening, 1);
        customer2.reserve(seller, theater, movie, pastScreening, 1);
        assertThat(theater.enter(customer1, 1)).isTrue();
        assertThat(theater.enter(customer2, 1)).isFalse();
    }


    @Test
    public void 티켓_구매_수량_총_할인_관객_입장_테스트() {
        Theater theater = new Theater(Money.of(0.0));
        Movie<AmountDiscount> movie = new Movie<>(
                "spidermain",
                Duration.ofMinutes(120L),
                Money.of(5000.0),
                new AudienceCountAmountDiscount(Money.of(1000.0), 2)
        );

        theater.addMovie(movie);

        setScreening(theater, movie);

        TicketOffice ticketOffice = new TicketOffice(Money.of(0.0));
        theater.contractTicketOffice(ticketOffice, 10.0);

        Customer customer1 = new Customer(Money.of(8000.0));
        Customer customer2 = new Customer(Money.of(4000.0));

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        Screening screening = theater.getScreening(movie).iterator().next();
        customer1.reserve(seller, theater, movie, screening, 2);
        customer2.reserve(seller, theater, movie, screening, 1);
        assertThat(theater.enter(customer1, 2)).isTrue();
        assertThat(theater.enter(customer2, 1)).isFalse();
    }


    private void setScreening(Theater theater, Movie movie) {
        for (int day = 7; day < 32; day++) {
            for (int hour = 10, seq = 1; hour < 24; hour += 3, seq++) {
                theater.addScreening(
                        movie,
                        new Screening(
                                seq,
                                LocalDateTime.of(2019, 8, day, hour, 00, 00),
                                100    // 가용 가능한 좌석 수
                        )
                );
            }
        }
    }

}