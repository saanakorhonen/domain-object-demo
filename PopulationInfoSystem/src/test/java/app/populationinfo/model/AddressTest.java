package app.populationinfo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import app.populationinfo.util.TestUtil;

/**
 * Address test class.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class AddressTest {
    
    /**
     * Test verifies that two addresses are copied and compared successfully.
     */
    @Test
    public void compareAddresses() {
        Address address = TestUtil.createAddress();
        Address address2 = Address.copyOf(address);
        
        assertEquals(address2, address);
    }
    
    
    /**
     * Test verifies that addresses with different area codes are not equal.
     */
    @Test
    public void compareAddressesWithDifferentAreaCodes() {
        Address address = TestUtil.createAddress();
        Address address2 = Address.copyOf(address);
        address2.setAreaCode("40100");
        
        assertEquals(false, address.equals(address2));
    }
    
    
    /**
     * Test verifies that addresses are not equal if all the attributes don't match.
     */
    @Test
    public void compareAddressesWithDifferentNames() {
        Address address = TestUtil.createAddress();
        Address address2 = Address.copyOf(address);
        address2.setName("FoofooAddress");
        
        assertEquals(false, address.equals(address2));
    }
}
