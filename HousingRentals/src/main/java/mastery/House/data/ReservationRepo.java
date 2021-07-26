package mastery.House.data;

import mastery.House.models.Host;
import mastery.House.models.Reservation;

import java.util.List;

public interface ReservationRepo {
    List<Reservation> findbyHostId(String hostId);

    List<Reservation> findResByHost(Host host);

    Reservation createReservation(Reservation res) throws DataException;

    boolean update(Reservation res) throws DataException;

    boolean deleteResById(String resId, Host host) throws DataException;

    int getMaxId(List <Reservation> all);
}
