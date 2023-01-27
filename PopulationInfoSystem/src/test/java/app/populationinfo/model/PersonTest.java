package app.populationinfo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import app.populationinfo.util.TestUtil;

/**
 * Person test class. Tests person's business logic.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class PersonTest {
   
    /**
     * Test verifies that copying person will result a person that's considered an equal because
     * of the identity code.
     */
    @Test
    public void testPersonComparasionWithSameIdentityCode() {
        Person person = TestUtil.createPerson();
        Person person2 = Person.copyOf(person);
        
        assertTrue(person.equals(person2));
    }
    
    
    /**
     * Test verifies that comparing two otherwise same persons with different identity code will fail.
     */
    @Test
    public void testPersonComparasionWithDifferentIdentityCode() {
        Person person = TestUtil.createPerson();
        Person person2 = Person.copyOf(person);
        
        person2.setIdentityCode("-1");
        
        assertEquals(false, person.equals(person2));
    }
    
    
    /**
     * Assert that the new address is successfully added as the current address.
     */
    @Test
    public void addAddressForPerson() {
        Person person = TestUtil.createPerson();
        Address address = TestUtil.createAddress();
        person.addAddress(address, LocalDate.now());
        
        assertEquals(address, person.findCurrentAddress().getAddress());
    }
    
    
    /**
     * Test verifies that when new address is added, old address has an added date for moving out.
     */
    @Test
    public void addSecondAddressForPerson() {
        Person person = TestUtil.createPerson();
        Address address1 = TestUtil.createAddress();
        person.addAddress(address1, LocalDate.now());
        
        assertNull(person.findCurrentAddress().getMoveOutDate());
        
        Address address2 = TestUtil.createAddress("TestAddress2");
        person.addAddress(address2, LocalDate.now().plusDays(10l));
        
        AddressInfo info = person.findAddressInfoByAddress(address1);
        
        assertNotNull(info);
        assertNotNull(info.getMoveOutDate());
    }
    
    
    /**
     * Tests adding parent successfully. Verifies that parent is added to the list of parents.
     */
    @Test
    public void addParentSuccessfully() {
        Person person = TestUtil.createPerson();
        Person parent = TestUtil.createPerson("11111", "Polly", "Parent", false);
        
        person.addParent(parent);
        
        List<Person> result = person.getParents();
        assertEquals(1, result.size());
        
        Person parentCandidate = result.get(0);
        assertEquals(parentCandidate, parent);
    }
    
    
    /**
     * Test verifies that only two parents are permitted per person.
     */
    @Test
    public void addTooManyParents() {
        Person person = TestUtil.createPerson();
        Person parent1 = TestUtil.createPerson("11111", "Polly", "Parent", false);
        Person parent2 = TestUtil.createPerson("22222", "Pat", "Parent", false);
        Person parent3 = TestUtil.createPerson("33333", "Peter", "Parent", false);
        person.addParent(parent1);
        person.addParent(parent2);
        
        assertThrows(IllegalArgumentException.class, () -> {
            person.addParent(parent3);
        });
    }
    
    
    /**
     * Verifies that parent can be successfully removed.
     */
    @Test
    public void removeParentSuccessfully() {
        Person person = TestUtil.createPerson();
        Person parent = TestUtil.createPerson("11111", "Polly", "Parent", false);
        
        person.addParent(parent);
        
        List<Person> result = person.getParents();
        assertEquals(1, result.size());
        
        Person parentCandidate = result.get(0);
        assertEquals(parentCandidate, parent);
        
        person.removeParent(parent);
        assertEquals(0, result.size());
    }
}
