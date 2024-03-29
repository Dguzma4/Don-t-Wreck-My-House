package mastery.House.ui;

import mastery.House.data.DataException;
import mastery.House.domain.GuestService;
import mastery.House.domain.HostService;
import mastery.House.domain.ReservationService;
import mastery.House.domain.Result;
import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Controller {

    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;
    private final View view;


    public Controller(ReservationService reservationService, HostService hostService, GuestService guestService, View view) {
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome To the Housing Market");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("GoodBye. ");
    }


    private void runAppLoop() throws DataException {
        MainMenuOption option;

        do {
            option = view.selectionMainMenuOption();
            switch (option) {
                case EXIT:
                    break;
                case EDIT_RESERVATION:
                    editReservations();
                    break;
                case MAKE_RESERVATOIN:
                    makeReservation();
                    break;
                case DELETE_RESERVATOPM:
                    deleteReservation();
                    break;
                case VIEW_RESERVATIONS_FOR_HOST:
                    showReservations();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void makeReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATOIN.getMessage());
        Host host = getHost();
        if (host == null) {
            return;
        }
        List<Reservation> use = reservationService.findByHostId(host.getId());
        view.displayReservations(use);
        Guest guest = getGuest();
        if (guest == null) {
            System.out.println("no guests here");
            return;
        }
        view.displayReservations(reservationService.findByHostId(host.getId()));
        Reservation reservation = view.makeReservation(host, guest);

        Result<Reservation> result = reservationService.add(reservation);
        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            view.displayNewReservation(reservation);
            String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
            view.displayStatus(true, successMessage);
        }
    }

    private void deleteReservation() throws DataException {
        view.displayHeader(MainMenuOption.DELETE_RESERVATOPM.getMessage());
        Host host = getHost();
        List<Reservation> res = reservationService.findByHostId(host.getId());
        view.displayReservations(res);
        String resId = view.getResId(res);
        Result<Reservation> result = reservationService.deleteResById(resId, hostService.findbyId(host.getId()));

        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Reservation %s deleted", resId);
            view.displayStatus(true, successMessage);
        }
    }

    private void showReservations() {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS_FOR_HOST.getMessage());
        Host host = getHost();
        List<Reservation> reservations = reservationService.findByHostId(host.getId());
        view.displayReservations(reservations);
        view.enterToContinue();
    }

    private void editReservations() throws DataException {
        view.displayHeader(MainMenuOption.EDIT_RESERVATION.getMessage());
        Host host = getHost();
        Guest guest = getGuest();
        List<Reservation> reservations = reservationService.findByHostId(host.getId());
        view.displayReservations(reservations);

        Reservation updatedRes = view.updateReservation(reservations,guest,host);
        Result<Reservation> result = reservationService.update(updatedRes);

        if (!result.isSuccess()) {
            view.displayStatus(false, result.getErrorMessages());
        } else {
            String successMessage = String.format("Reservation %s updated.", updatedRes.getId());
            view.displayStatus(true, successMessage);
        }

    }


    //support methods
//    private Reservation getReservation(){
//        String hostEmail = view.getHostId();
//        List<Reservation> reservations = reservationService.findReservationListByHostByHostEmail(hostEmail);
//        view.displayHeader("Reservations from : " + hostEmail);
//        return
//
//    }
    private Host getHost() {
        String hostId = view.getHostId();
        Host host = hostService.findbyId(hostId);
        if(host == null){
            System.out.println("null");
        }
        return host;
    }

    private Guest getGuest() {
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        return guest;
    }
}
