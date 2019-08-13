package theater.problem1;

public class Main {
    // 마지막 theater 예제에서 TicketOffice 는 암묵적으로 하나의 극장하고만 계약하고 있다는 가정이 있게 구현되어있다.
    // 코드 상 이 조건을 강제하도록 개선하라.
    public static void main(String[] args) {
        Theater theater = new Theater(100L);
        Audience audience1 = new Audience(0L);
        Audience audience2 = new Audience(50L);
        TicketOffice ticketOffice = new TicketOffice(theater, 0L);
        TicketSeller seller = new TicketSeller();
        theater.setTicket(ticketOffice, 10L);
        theater.setInvitation(audience1);
        seller.setTicketOffice(ticketOffice);
        audience1.buyTicket(seller);
        audience2.buyTicket(seller);
        boolean isOk1 = theater.enter(audience1);
        boolean isOk2 = theater.enter(audience2);
        System.out.println(isOk1);  // True
        System.out.println(isOk2);  // False
    }
}
