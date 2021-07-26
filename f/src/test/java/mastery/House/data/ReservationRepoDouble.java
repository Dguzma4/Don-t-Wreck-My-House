package mastery.House.data;

import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class ReservationRepoDouble implements ReservationRepo{


private final ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationRepoDouble(){
        Reservation res = new Reservation();

        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(host);
        host.setEmail("sbarrittrr@eventbrite.com");

        Guest guest = new Guest();
        guest.setGuestId("12345");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2020,6,26));
        res.setEndDate(LocalDate.of(2020,6,29));
        res.setTotal(new BigDecimal(200000));




    }
    @Override
    public List<Reservation> findbyHostId(String hostId) {
        return reservations.stream()
                .filter(reservation -> reservation.getHost().getId().equalsIgnoreCase(hostId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findResByHost(Host host) {
        return null;
    }

    @Override
    public Reservation createReservation(Reservation res) throws DataException {
        res.setId(String.valueOf(getMaxId(reservations)));
        reservations.add(res);
        return res;
    }

    @Override
    public boolean update(Reservation res) throws DataException {
        return false;
    }

    @Override
    public boolean deleteResById(String resId, Host host) throws DataException {
        return false;
    }

    @Override
    public int getMaxId(List<Reservation> all) {
        int maxid = 0;
        for (Reservation r : all) {
            if (parseInt(r.getId()) > maxid) {
                maxid = parseInt(r.getId());
            }
        }
        return maxid + 1;
    }


}
