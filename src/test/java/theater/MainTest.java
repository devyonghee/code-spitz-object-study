package theater;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void 정상적으로_구매한_관객_입장_테스트() {
        Theater theater = new Theater();
        Movie movie = new Movie(100L);
        Movie movie2 = new Movie(100L);
        theater.setMovie(movie);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie, 10L);

        Audience audience = new Audience(100L);

        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);
        theater.setInvitation(audience, movie);

        assertThat(theater.enter(audience, movie)).isTrue();
        assertThat(theater.enter(audience, movie2)).isFalse();
    }


    @Test
    public void 돈_없는_관객_입장_테스트() {
        Theater theater = new Theater();
        Movie movie = new Movie(100L);
        theater.setMovie(movie);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie, 10L);

        Audience audience = new Audience(0L);
        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        audience.buyTicket(seller, movie);
        assertThat(theater.enter(audience, movie)).isFalse();
    }

    @Test
    public void 초대장_가진_관객_입장_테스트() {
        Theater theater = new Theater();
        Movie movie = new Movie(100L);
        theater.setMovie(movie);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie, 10L);

        Audience audience = new Audience(0L);
        TicketSeller seller = new TicketSeller();
        seller.setTicketOffice(ticketOffice);

        theater.setInvitation(audience, movie);

        audience.buyTicket(seller, movie);
        assertThat(theater.enter(audience, movie)).isTrue();
    }

    @Test
    public void 초대장_다량_보유_테스트() {
        Theater theater = new Theater();
        Movie movie1 = new Movie(100L);
        Movie movie2 = new Movie(200L);
        theater.setMovie(movie1);
        theater.setMovie(movie2);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie1, 10L);
        theater.setTicket(ticketOffice, movie2, 10L);

        Audience audience = new Audience(0L);
        TicketSeller seller = new TicketSeller();

        theater.setInvitation(audience, movie1);
        theater.setInvitation(audience, movie2);
        seller.setTicketOffice(ticketOffice);

        audience.buyTicket(seller, movie1);
        audience.buyTicket(seller, movie2);
        assertThat(theater.enter(audience, movie1)).isTrue();
        assertThat(theater.enter(audience, movie2)).isTrue();
    }


    @Test
    public void 티켓_다량_보유_테스트() {
        Theater theater = new Theater();
        Movie movie1 = new Movie(100L);
        Movie movie2 = new Movie(200L);
        theater.setMovie(movie1);
        theater.setMovie(movie2);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie1, 10L);
        theater.setTicket(ticketOffice, movie2, 10L);

        Audience audience = new Audience(500L);
        TicketSeller seller = new TicketSeller();

        seller.setTicketOffice(ticketOffice);

        audience.buyTicket(seller, movie1);
        audience.buyTicket(seller, movie2);
        assertThat(theater.enter(audience, movie1)).isTrue();
        assertThat(theater.enter(audience, movie2)).isTrue();
    }


    @Test
    public void 다양한_가격_영화_관람_테스트() {
        Theater theater = new Theater();
        Movie movie1 = new Movie(100L);
        Movie movie2 = new Movie(200L);
        theater.setMovie(movie1);
        theater.setMovie(movie2);

        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        theater.setTicket(ticketOffice, movie1, 10L);
        theater.setTicket(ticketOffice, movie2, 10L);

        Audience audience1 = new Audience(100L);
        Audience audience2 = new Audience(100L);
        TicketSeller seller = new TicketSeller();

        seller.setTicketOffice(ticketOffice);

        audience1.buyTicket(seller, movie1);
        audience2.buyTicket(seller, movie2);
        assertThat(theater.enter(audience1, movie1)).isTrue();
        assertThat(theater.enter(audience2, movie2)).isFalse();
    }

    @Test
    public void 무료_영화_테스트() {
        Movie movie = new Movie(0L);
        Theater theater = new Theater();
        theater.setMovie(movie);

        Audience audience = new Audience(0L);
        TicketSeller seller = new TicketSeller();
        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        seller.setTicketOffice(ticketOffice);

        theater.setTicket(ticketOffice, movie, 10L);

        audience.buyTicket(seller, movie);
        audience.buyTicket(seller, movie);
        audience.buyTicket(seller, movie);

        assertThat(theater.enter(audience, movie)).isTrue();
        assertThat(theater.enter(audience, movie)).isTrue();
        assertThat(theater.enter(audience, movie)).isTrue();
        assertThat(theater.enter(audience, movie)).isFalse();
    }


}
