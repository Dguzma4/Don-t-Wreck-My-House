package mastery.House.data;

import jdk.jfr.Registered;
import mastery.House.models.Host;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface HostRepository {
    List<Host> findAll();

    Host findId(String id);

    List<Host> findbyPostalCode(String postal);

    Host findByEmail(String email);
}
