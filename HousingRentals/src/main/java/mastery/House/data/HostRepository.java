package mastery.House.data;

import mastery.House.models.Host;

import java.util.List;

public interface HostRepository {
    List<Host> findAll();

    Host findId(String id);

    List<Host> findbyPostalCode(String postal);

    Host findByEmail(String email);
}
