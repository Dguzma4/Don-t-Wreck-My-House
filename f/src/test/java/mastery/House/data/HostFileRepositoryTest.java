package mastery.House.data;

import mastery.House.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/hosts-seed.csv";
    static final String TEST_FILE_PATH = "./data/hosts-test.txt";
    HostFileRepository repository = new HostFileRepository(TEST_FILE_PATH);



    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath,testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll(){
        HostFileRepository repo = new HostFileRepository("./data/hosts.csv");
        List<Host> all = repo.findAll();
        assertEquals(1000,all.size());
    }
    @Test
    void shouldFindById(){
    Host host = repository.findId("8248a3e0-ad7c-43c0-bebf-34745a07e99e");
    assertEquals("sbarrittrr@eventbrite.com",host.getEmail());

    }
    @Test
    void shouldFindByPostalCode(){
        List<Host> host = repository.findbyPostalCode("89714");
        assertEquals(2,host.size());
    }

    @Test
    void shouldFindByEmail(){
        Host host = repository.findByEmail("sbarrittrr@eventbrite.com");
        assertEquals(host.getId(),"8248a3e0-ad7c-43c0-bebf-34745a07e99e");
    }
}