package mastery.House.data;

import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepoTest {

    static final String SEED_FILE_PATH ="./data/reservation-seed-f4d6c5e4-d207-4ce0-93b3-f1d1b397883c.csv";
    static final String TEST_FILE_PATH = "./data/reservation_data_test/f4d6c5e4-d207-4ce0-93b3-f1d1b397883c.csv";
    static final String TEST_DIR_PATH = "./data/reservation_data_test";

    ReservationFileRepo repo = new ReservationFileRepo(TEST_DIR_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    void shouldFindByHostId(){
        List<Reservation> reservations = repo.findbyHostId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        assertEquals(17,reservations.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Reservation res = new Reservation();

        Host hot = new Host();
        hot.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        res.setHost(hot);

        Guest guest = new Guest();
        guest.setGuestId("12345");
        res.setGuest(guest);

        res.setStartDate(LocalDate.of(2020,6,26));
        res.setEndDate(LocalDate.of(2020,6,29));
        res.setTotal(new BigDecimal(23000));


        res = repo.createReservation(res);

        assertEquals(5,res.getGuest().getGuestId().length());
    }
    @Test
    public void shouldDelete() throws DataException {
        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        boolean actual = repo.deleteResById("3",host);
        assertTrue(actual);
    }

    @Test
    public void shouldFindbyHost(){
        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        List <Reservation> actual = repo.findResByHost(host);

        assertEquals(actual.size(),17);
    }
    @Test
    public void shouldUpdateRes() throws DataException {
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
        boolean success = repo.update(res);
        assertTrue(success);
    }

    //TODO: do the negative passes

}