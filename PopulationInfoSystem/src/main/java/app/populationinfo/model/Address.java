package app.populationinfo.model;

/**
 * Class for holding address information and business logic.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class Address {
    private String name;
    private String areaCode;
    
    /**
     * Constructor.
     * 
     * @param name name of the address.
     * @param areaCode area code of the address.
     */
    public Address(String name, String areaCode) {
        this.name = name;
        this.areaCode = areaCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAreaCode() {
        return areaCode;
    }
    
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    /**
     * Creates a copy of the address.
     * 
     * @param address address to be copied.
     * @return copy of the original address.
     */
    public static Address copyOf(Address address) {
        return new Address(address.getName(), address.getAreaCode());
    }
    
    @Override
    public boolean equals(Object a2) {
        return name.equals(((Address) a2).getName()) && areaCode.equals(((Address) a2).getAreaCode());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
