package theater.problem2;

public class Ticket {
    // 이펙티브 자바 (null 객체 패턴)
    // 식별자로 Empty를 판단
    final static public Ticket EMPTY = new Ticket(null, null);

    // 절대 불변하도록 final로 theater를 잡아 둔다.
    final private Theater theater;
    final private Movie movie;

    private boolean isEntered = false;

    public Ticket(Theater theater, Movie movie) {
        this.theater = theater;
        this.movie = movie;
    }

    // isEntered
    public boolean isValid(Theater theater) {
        if (isEntered || theater != this.theater || this == EMPTY) {
            return false;
        } else {
            isEntered = true;
            return true;
        }
    }

    // pointer의 pointer의 pointer를 사용하는 중
    // 만약 Theater의 fee를 필드 값으로 잡았다면 Theater의 fee 값이 변경 되어도 반영이 안됐을 것이다.
    public Long getFee() {
        return movie.getFee();
    }
}