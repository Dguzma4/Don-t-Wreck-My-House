package mastery.House.models;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Reservation {

    private String id;
    private Host host;
    private Guest guest;
    private LocalDate startDate;
    private LocalDate endDate;
    private String guestId;
    private BigDecimal total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal total(){
        BigDecimal total = BigDecimal.ZERO;

        LocalDate start = LocalDate.parse(startDate.toString());
        LocalDate end = LocalDate.parse(endDate.toString());

        BigDecimal weekDayPay = host.getStandardRate();
        BigDecimal weekendDayPay = host.getWeekendRate();

        while (start.compareTo(end)< 0){

            if(start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    start.getDayOfWeek() == DayOfWeek.SUNDAY){
                total = total.add(weekendDayPay);
            } else {
                total = total.add(weekDayPay);
            }
            start = startDate.plusDays(1);

        }





        return total;
    }
}
