package theater;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void 정상적으로_구매한_관객_입장_테스트() {
        Theater theater = new Theater(100L);
        Audience audience1 = new Audience(0L);
        Audience audience2 = new Audience(50L);
        TicketOffice ticketOffice = new TicketOffice(0L, theater);
        TicketSeller seller = new TicketSeller();
        theater.setTicket(ticketOffice, 10L);
        theater.setInvitation(audience1);
        seller.setTicketOffice(ticketOffice);
        audience1.buyTicket(seller);
        audience2.buyTicket(seller);
        boolean isOk1 = theater.enter(audience1);
        boolean isOk2 = theater.enter(audience2);
        assertThat(isOk1).isTrue();
        assertThat(isOk2).isFalse();
    }
}