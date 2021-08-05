package learn.pets.data;

import learn.pets.models.Pet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetJdbcTemplateRepositoryTest {

    PetJdbcTemplateRepository repository;

    public PetJdbcTemplateRepositoryTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        repository = context.getBean(PetJdbcTemplateRepository.class);
    }
    @BeforeAll
    static void oneTimeSetup() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DbTestConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        jdbcTemplate.update("call set_known_good_state();");
    }


    @Test
    void shouldFindAll() {
        List<Pet> all = repository.findAll();

        assertNotNull(all);
        // Anything 2 or bigger is good since pets are being added and deleted.
        assertTrue(all.size() >= 2);

        Pet expected = new Pet();
        expected.setPetId(1);
        expected.setName("Meep");
        expected.setType("Mouse");

        // Ensure Meep the Mouse is present (1 is always left alone).
        // Then confirm pet_id 2 exists, though its fields may have changed.
        assertTrue(all.contains(expected)
                && all.stream().anyMatch(i -> i.getPetId() == 2));
    }

    @Test
    void shouldAdd() {
        Pet pet = new Pet();
        pet.setName("Mer");
        pet.setType("Hamster");

        Pet actual = repository.add(pet);
        pet.setPetId(4);

        assertNotNull(actual);
        assertEquals(pet, actual);
    }
    @Test
    void shouldUpdateExisting() {
        Pet pet = new Pet();
        pet.setPetId(2);
        pet.setName("Singe");
        pet.setType("Snake");

        assertTrue(repository.update(pet));
        assertEquals(pet, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Pet pet = new Pet();
        pet.setPetId(20000);
        pet.setName("Singe");
        pet.setType("Snake");

        assertFalse(repository.update(pet));
    }

    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteById(3));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(40000));
    }


}