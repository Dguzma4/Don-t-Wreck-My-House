package mastery.House;

import mastery.House.data.GuestFileRepository;
import mastery.House.data.HostFileRepository;
import mastery.House.data.ReservationFileRepo;
import mastery.House.domain.GuestService;
import mastery.House.domain.HostService;
import mastery.House.domain.ReservationService;
import mastery.House.models.Reservation;
import mastery.House.ui.ConsoleIO;
import mastery.House.ui.Controller;
import mastery.House.ui.View;

public class App {
    public static void main(String[] args) {


        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        ReservationFileRepo reservationFileRepo = new ReservationFileRepo("./data/reservations");
        HostFileRepository hostFileRepository = new HostFileRepository("./data/hosts.csv");
        GuestFileRepository guestFileRepository = new GuestFileRepository("./data/guests.csv");

        ReservationService reservationService = new ReservationService(reservationFileRepo, guestFileRepository, hostFileRepository);
        HostService hostService = new HostService(hostFileRepository);
        GuestService guestService = new GuestService(guestFileRepository);

        Controller controller = new Controller(reservationService,hostService,guestService,view);
        controller.run();


    }
}
