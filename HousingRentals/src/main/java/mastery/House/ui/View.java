package mastery.House.ui;

import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class View {
    private final ConsoleIO io;

    public View(ConsoleIO io){
        this.io = io;
    }


    public MainMenuOption selectionMainMenuOption(){
        displayHeader("Main Menu");
        int min = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for(MainMenuOption option: MainMenuOption.values()){
            io.printf("%s. %s%n", option.getValue(),option.getMessage());
             max = Math.max(max,option.getValue());
        }
        String message = String.format("Select [%s-%s]: ", 0, max - 1);
        return MainMenuOption.fromValue(io.readInt(message, 0, max));
    }


//
//    public Reservation chooseReservation(List<Reservation> reservationList){
//        if(reservationList.size()==0){
//            io.println("No Reservations found");
//            return null;
//        }
//        for(Reservation reservation: reservationList.stream().limit(25).collect(Collectors.toList())){
//            io.printf("%s: %s %s%n", reservation.getId(), reservation.);
//        }
//    }


    //changed from host email to hostID because findbyHostEmail doesn't work and i do not know why
public String getHostId() {
    return io.readRequiredString("Please input Host ID .");
}
public String getGuestEmail(){
        return io.readRequiredString("Please input Guest Email");
}


public Reservation makeReservation(Host host, Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setStartDate(io.readLocalDate("Start date [MM/dd/yyyy]:"));
        reservation.setEndDate(io.readLocalDate("End date [MM/dd/yyyy]:"));
        reservation.setTotal(getTotal(reservation.getStartDate(),reservation.getEndDate(),reservation.getHost()));
        return reservation;

}

public Reservation updateReservation(List<Reservation> reservations, Guest guest, Host host){
        Reservation reservation = reservations.stream()
                .filter(reservation1 -> reservation1.getGuest().getGuestId().equalsIgnoreCase(guest.getGuestId()))
                .findFirst()
                .orElse(null);

        if (reservation == null){
            io.println("No reservation that exists to update.");
            return reservation;
        }

        LocalDate newStartDate = (io.readLocalDate(reservation.getStartDate().toString()+ "Start date [MM/dd/yyyy] "));
        LocalDate newEndDate = (io.readLocalDate(reservation.getEndDate().toString()+ "End date [MM/dd/yyy]"));

        reservation.setStartDate(newStartDate);
        reservation.setEndDate(newEndDate);
        reservation.setTotal(getTotal(newStartDate,newEndDate,host));
        return reservation;

}

public BigDecimal getTotal (LocalDate start, LocalDate end, Host host){

        BigDecimal total = BigDecimal.ZERO;


        BigDecimal weekDayPay = host.getStandardRate();
        BigDecimal weekendDayPay = host.getWeekendRate();

        while (start.compareTo(end)< 0){

            if(start.getDayOfWeek() == DayOfWeek.FRIDAY || start.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    start.getDayOfWeek() == DayOfWeek.SUNDAY){
                total = total.add(weekendDayPay);
            } else {
                total = total.add(weekDayPay);
            }

            start = start.plusDays(1);

        }


        return total;
    }


public void displayReservations(List<Reservation> reservations){
        if(reservations == null){
            io.println("null");
        }
    if(reservations.size()== 0){
            io.println("No reservations found");
            return;
        }
    for (Reservation reservation : reservations.stream().collect(Collectors.toList())){
        io.printf("%s %s %s %s %s %s %s %s %s %n", reservation.getId(), "Start date: ",
                reservation.getStartDate(),"End date: ",reservation.getEndDate()
                ,"Guest ID: ",reservation.getGuest().getGuestId(),"Total: " ,reservation.getTotal());
    }


}
    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }
    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }




    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }
    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }




//public Reservation chooseReservation(List<Reservation> reservations){
//        if(reservations.size()== 0){
//            io.println("No reservations found");
//            return null;
//        }
//    for (Reservation reservation : reservations.stream().collect(Collectors.toList())){
//        io.printf("%s: %s: %s %s, %s: %s%n", reservation.getId(), "Guest:",
//                reservation.getGuest().getLastName(),reservation.getGuest().getFirstName(),reservation.getGuest().getEmail());
//    }
//
//
//}
public String getResId(List<Reservation> reservations){
        int need = io.readInt("Please select the reservation",0,reservations.size());
        return String.valueOf(need);

}


    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

}
