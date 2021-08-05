package mastery.House.data;

import mastery.House.models.Guest;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface GuestRepository {
    List<Guest> findAll();

    Guest findbyId(String id);

    Guest findByEmail(String email);
}
