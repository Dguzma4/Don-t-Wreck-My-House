package mastery.House.domain;

import mastery.House.data.GuestRepository;
import mastery.House.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }
    public Guest  findById(String id){ return repository.findbyId(id);
    }

    public Guest findByEmail(String email){ return repository.findByEmail(email);}
}
