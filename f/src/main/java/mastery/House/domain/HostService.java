package mastery.House.domain;

import mastery.House.data.HostRepository;
import mastery.House.models.Host;

import java.util.List;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public Host findbyId(String hostId){
        return repository.findId(hostId);
    }

    public List<Host> findByPostalCode(String postalCode){
        return repository.findbyPostalCode(postalCode);
    }

}
