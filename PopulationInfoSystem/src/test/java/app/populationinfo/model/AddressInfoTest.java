package app.populationinfo.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import app.populationinfo.util.TestUtil;

/**
 * Test class for address info.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class AddressInfoTest {
    
    /**
     * Test verifies that two addresses can be successfully compared with each other.
     */
    @Test
    public void testAddressComparasion() {
        Person person = TestUtil.createPerson();
        AddressInfo addressInfo = person.addAddress(TestUtil.createAddress(), LocalDate.now());
        AddressInfo addressInfo2 = AddressInfo.copyOf(addressInfo);
        
        assertEquals(addressInfo2, addressInfo);
    }
    
    /**
     * Test verifies that move out days need to be the same if not null.
     */
    @Test
    public void testAddressComparasionDifferentMoveOutDAy() {
        Person person = TestUtil.createPerson();
        AddressInfo addressInfo = person.addAddress(TestUtil.createAddress(), LocalDate.now());
        AddressInfo addressInfo2 = AddressInfo.copyOf(addressInfo);
        
        addressInfo.setMoveOutDate(LocalDate.now());
        addressInfo2.setMoveOutDate(LocalDate.now().plusDays(1l));
        
        assertEquals(false, addressInfo.equals(addressInfo2));
    }
}
