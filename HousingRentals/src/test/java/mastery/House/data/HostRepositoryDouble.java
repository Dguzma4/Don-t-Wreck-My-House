package mastery.House.data;

import mastery.House.models.Host;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostRepositoryDouble implements HostRepository{

    public HostRepositoryDouble () {
        Host host = new Host();
        host.setId("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");
        host.setEmail("sbarrittrr@eventbrite.com");

    }

    private final ArrayList<Host> hosts = new ArrayList<>();
    @Override
    public List<Host> findAll() {
       return hosts;
    }

    @Override
    public Host findId(String id) {
        return hosts.stream().filter(host -> host.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Host> findbyPostalCode(String postal) {
        return null;
    }

    @Override
    public Host findByEmail(String email) {
        return hosts.stream()
                .filter(host -> host.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
