package mastery.House.domain;

import mastery.House.data.DataException;
import mastery.House.data.ReservationRepo;
import mastery.House.models.Host;
import mastery.House.models.Reservation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest implements ReservationRepo {

    @Override
    public List<Reservation> findbyHostId(String hostId) {
        return null;
    }

    @Override
    public List<Reservation> findResByHost(Host host) {
        return null;
    }

    @Override
    public Reservation createReservation(Reservation res) throws DataException {
        return null;
    }

    @Override
    public boolean update(Reservation res) throws DataException {
        return false;
    }

    @Override
    public boolean deleteResById(String resId, Host host) throws DataException {
        return false;
    }
}