package mastery.House.data;

import mastery.House.models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll();

    Guest findbyId(String id);

    Guest findByEmail(String email);
}
