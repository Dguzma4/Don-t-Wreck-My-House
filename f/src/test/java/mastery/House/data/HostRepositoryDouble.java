package mastery.House.data;

import mastery.House.models.Host;

import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository{

    private final ArrayList<Host> hosts = new ArrayList<>();
    @Override
    public List<Host> findAll() {
       return hosts;
    }

    @Override
    public Host findId(String id) {
        return null;
    }

    @Override
    public List<Host> findbyPostalCode(String postal) {
        return null;
    }

    @Override
    public Host findByEmail(String email) {
        return null;
    }
}
