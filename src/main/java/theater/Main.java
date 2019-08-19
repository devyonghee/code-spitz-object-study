package theater;

public class Main {
    // 마지막 theater예제에서 Theater는 단 하나의 영화만 고정가격으로 상영 중이다.
    // 다양한 가격의 영화를 상영할 수 있게 개선하라. (Movie클래스가 새롭게 필요하고 또한 이에 따라 초대, 티켓, 티켓 오피스등의 총괄적인 변화가 일어남)
    // 무료 상영 영화
    // 관람객이 다양한 티켓을 가질 수 있도록
    // 초대권, 특정 영화만 가능하도록 변경
    // 티켓, 특정 영화에 종속
    public static void main(String[] args) {
        Movie movie1 = new Movie(100L);
        Theater theater = new Theater();
        Audience audience1 = new Audience(0L);
        Audience audience2 = new Audience(50L);
        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        TicketSeller seller = new TicketSeller();
        theater.setMovie(movie1);
        theater.setTicket(ticketOffice, movie1, 10L);
        theater.setInvitation(audience1, movie1);
        seller.setTicketOffice(ticketOffice);
        audience1.buyTicket(seller, movie1);
        audience2.buyTicket(seller, movie1);
        boolean isOk1 = theater.enter(audience1, movie1);
        boolean isOk2 = theater.enter(audience2, movie1);
        System.out.println(isOk1);  // True
        System.out.println(isOk2);  // False
    }
}