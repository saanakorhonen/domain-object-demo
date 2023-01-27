package app.populationinfo.util;

import java.time.LocalDate;
import java.util.ArrayList;

import app.populationinfo.model.Address;
import app.populationinfo.model.Person;

/**
 * Test utils class for methods used in tests.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class TestUtil {
    private static final String DEFAULT_IDENTITY_CODE = "12345678-AAAA";
    private static final String DEFAULT_FIRST_NAME = "Tom";
    private static final String DEFAULT_LAST_NAME = "TestPerson";
    private static final LocalDate DEFAULT_BIRTH_TIME = LocalDate.now().minusYears(20l);
    private static final LocalDate DEFAULT_DEATH_TIME = LocalDate.now().minusDays(1l);
    
    private static final String DEFAULT_ADDRESS_NAME = "TestAddress 1 A1";
    private static final String DEFAULT_AREA_CODE = "00100";
   
    
    /**
     * Creates a default person.
     * 
     * @return a person with default values and no death status.
     */
    public static Person createPerson() {
        return createPerson(false);
    }
    

    /**
     * Creates a default person.
     * 
     * @param isDead is the person deceased.
     * @return a new instance of person.
     */
    public static Person createPerson(boolean isDead) {
        LocalDate deathTime = isDead ? DEFAULT_DEATH_TIME : null;
        return createPerson(DEFAULT_IDENTITY_CODE, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_BIRTH_TIME, deathTime);
    }
    
    
    /**
     * Creates a person with given name and and if the person is deceased.
     * 
     * @param identityCode identity code of the person.
     * @param firstName first name of the person.
     * @param lastName last name of the person.
     * @param isDead is the person deceased.
     * @return a new instance of the person.
     */
    public static Person createPerson(String identityCode, String firstName, String lastName, boolean isDead) {
        LocalDate deathTime = isDead ? DEFAULT_DEATH_TIME : null;
        
        return createPerson(identityCode, firstName, lastName, DEFAULT_BIRTH_TIME, deathTime);
    }

    
    /**
     * Creates a person with name and birth time and optional death time.
     * 
     * @param identityCode identity code of the person.
     * @param firstName first name of the person.
     * @param lastName last name of the person.
     * @param birthTime birth time of the person.
     * @param deathTime death time of the person.
     * @return a new instance of test person.
     */
    public static Person createPerson(String identityCode, String firstName,
            String lastName, LocalDate birthTime,
            LocalDate deathTime) {
        // TODO Auto-generated method stub
        return new Person(identityCode, firstName, lastName, birthTime, deathTime, new ArrayList<Person>());
        
    }
    

    /**
     * Creates an address.
     * 
     * @return a new instance of address.
     */
    public static Address createAddress() {
        return createAddress(DEFAULT_ADDRESS_NAME);
    }
    

    /**
     * Creates an address with a given name.
     * 
     * @param name name of the address.
     * @return new instance of address.
     */
    public static Address createAddress(String name) {
        return new Address(name, DEFAULT_AREA_CODE);
    }
}
