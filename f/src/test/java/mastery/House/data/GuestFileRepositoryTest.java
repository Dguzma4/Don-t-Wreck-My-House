package mastery.House.data;

import mastery.House.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {
static final String SEED_FILE_PATH = "./data/guests-seed.csv";
static final String TEST_FILE_PATH ="./data/guests-test.txt";

    GuestFileRepository repository = new GuestFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);

    }

    @Test
    void shouldFindAll(){
        GuestFileRepository repo = new GuestFileRepository("./data/guests.csv");
        List<Guest> all = repo.findAll();
        assertEquals(1000,all.size());
    }


}
