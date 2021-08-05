package mastery.House.domain;

import mastery.House.data.GuestRepositoryDouble;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    GuestService service = new GuestService(new GuestRepositoryDouble());

}