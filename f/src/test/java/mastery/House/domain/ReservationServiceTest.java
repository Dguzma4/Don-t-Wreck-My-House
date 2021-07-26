package mastery.House.domain;

import mastery.House.data.DataException;
import mastery.House.data.GuestRepositoryDouble;
import mastery.House.data.HostRepositoryDouble;
import mastery.House.data.ReservationRepoDouble;
import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {


    ReservationService service = new ReservationService(
            new ReservationRepoDouble(),
            new GuestRepositoryDouble(),
            new HostRepositoryDouble());


@Test
void shouldFindByHostId(){
    String hostId = "f4d6c5e4-d207-4ce0-93b3-f1d1b397883c";
    List<Reservation> pew = service.findByHostId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");

    assertNotNull(pew);
}
    @Test
    void shouldAdd() throws DataException {
        Reservation res = new Reservation();

        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(host);

        Guest guest = new Guest();
        guest.setGuestId("123905");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2029,6,26));
        res.setEndDate(LocalDate.of(2029,6,29));
        res.setTotal(new BigDecimal(9009));


        Result<Reservation> result = service.add(res);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(6,result.getPayload().getGuest().getGuestId().length());
    }

    @Test
    public void shouldnotAddWithoutHost() throws DataException {
        Reservation res = new Reservation();
        Guest guest = new Guest();
        guest.setGuestId("12345");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2029,6,26));
        res.setEndDate(LocalDate.of(2029,6,29));
        res.setTotal(new BigDecimal(23000));

        Result<Reservation> result = service.add(res);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
        assertNotNull(result.getErrorMessages());
    }
    @Test
    public void shouldNotAddOverlappingDates() throws DataException{
        Reservation res = new Reservation();

        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(host);

        Guest guest = new Guest();
        guest.setGuestId("123905");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2029,6,26));
        res.setEndDate(LocalDate.of(2029,6,29));
        res.setTotal(new BigDecimal(9009));


        Result<Reservation> result = service.add(res);


        Reservation res2 = new Reservation();

        Host host2 = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(host);

        Guest guest2 = new Guest();
        guest.setGuestId("1239035");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2029,6,26));
        res.setEndDate(LocalDate.of(2029,6,29));
        res.setTotal(new BigDecimal(9009));

        result = service.add(res2);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
        assertNotNull(result.getErrorMessages());
    }

    @Test
    public void shouldUpdate() throws DataException {
        Reservation res = new Reservation();
        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(host);
        res.setId("2");
        Guest guest = new Guest();
        guest.setGuestId("956");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2020,12,12));
        res.setEndDate(LocalDate.of(2020,12,19));
        res.setTotal(new BigDecimal(1547));
        Result<Reservation>success = service.update(res);

        assertFalse(success.isSuccess());


    }
    @Test
    public void shouldFindResByHostEmail(){
        String email = "sbarrittrr@eventbrite.com";

        List<Reservation> reservation = service.findReservationListByHostByHostEmail(email);
        assertNotNull(reservation);

    }



}