package mastery.House.domain;

import mastery.House.data.HostFileRepository;
import mastery.House.data.HostRepositoryDouble;
import mastery.House.models.Host;
import mastery.House.models.Reservation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {

    HostService service = new HostService(new HostRepositoryDouble());


    @Test
    public void findAll(){
        List<Host> hosts = service.findAll();

        assertNotNull(hosts);
    }


    @Test
    public void shouldFindById(){
    Host host = service.f("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");

    assertNotNull(host);


    }


}