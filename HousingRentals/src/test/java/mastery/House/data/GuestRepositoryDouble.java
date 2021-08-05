package mastery.House.data;

import mastery.House.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository{



    private final static Guest GUEST = new Guest();
    private final ArrayList<Guest> guests = new ArrayList<>();

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public Guest findbyId(String id) {
        return null;
    }

    @Override
    public Guest findByEmail(String email) {
        return null;
    }
}
