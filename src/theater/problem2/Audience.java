package theater.problem2;

class Audience {
    private Ticket ticket = Ticket.EMPTY;
    private Invitation invitation = Invitation.EMPTY;
    private Long amount;

    public Audience(Long amount) {
        this.amount = amount;
    }

    // 유일하게 바깥에 노출되는 메소드 다른 메소드들은 같은 패키지 내에서 사용되기 위한 접대하는 메소드이다.
    public void buyTicket(TicketSeller seller, Movie movie) {
        ticket = seller.getTicket(this);
    }

    public boolean hasAmount(Long amount) {
        return this.amount >= amount;
    }

    public boolean minusAmount(Long amount) {
        if (amount > this.amount) return false;
        this.amount -= amount;
        return true;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void removeInvitation() {
        invitation = Invitation.EMPTY;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }
}