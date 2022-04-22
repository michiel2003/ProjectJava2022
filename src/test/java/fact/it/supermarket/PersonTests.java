package fact.it.supermarket;

import fact.it.supermarket.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PersonTests {

    /**
     * Test of constructor en getters
     */
    @Test
    public void testConstructorEnGetters() {

        Person leonie = new Person("Leonie", "Pelgroms");
        assertEquals("Leonie", leonie.getFirstName());
        assertEquals("Pelgroms", leonie.getSurname());
        Person person = new Person();
        assertNull(person.getFirstName());
        assertNull(person.getSurname());
    }

    /**
     * Test of setFirstName method, of class Persoon.
     */
    @Test
    public void testSetVoornaam() {
        Person person = new Person();
        person.setFirstName("Frans");
        assertEquals("Frans", person.getFirstName());
    }

    /**
     * Test of setFirstName method, of class Persoon.
     */
    @Test
    public void testSetFamilienaam() {
        Person person = new Person();
        person.setSurname("Caers");
        assertEquals("Caers", person.getSurname());
    }

    /**
     * Test of toString method, of class Persoon.
     */
    @Test
    public void testToString() {
        Person leonie = new Person("Leonie", "Pelgroms");
        assertEquals("PELGROMS Leonie", leonie.toString());
        Person frans = new Person("Frans", "Caers");
        assertEquals("CAERS Frans", frans.toString());
    }

}
