package app.populationinfo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Person class. Holds information about person and takes care of the implemented business logic.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023
 *
 */
public class Person {
    private String identityCode;
    private String firstName;
    private String lastName;
    private LocalDate timeOfBirth;
    private LocalDate timeOfDeath;
    private List<Person> parents;
    private List<AddressInfo> addresses = new ArrayList<>();
    
    
    /**
     * Constructor.
     * 
     * @param identityCode person's personal identity code.
     * @param firstName person's first name.
     * @param lastName person's last name.
     * @param timeOfBirth date of birth of person.
     * @param parents parents of person. Can be unknown.
     */
    public Person(String identityCode, String firstName, String lastName, LocalDate timeOfBirth, List<Person> parents) {
        this.identityCode = identityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeOfBirth = timeOfBirth;
        this.parents = parents;
    }
    
    
    /**
     * Constructor with time of death.
     * 
     * @param identityCode person's personal identity code.
     * @param firstName person's first name.
     * @param lastName person's last name.
     * @param timeOfBirth date of birth of person.
     * @param timeOfDeath date of death of person.
     * @param parents parents of person. Can be unknown.
     */
    public Person(String identityCode, String firstName, String lastName, LocalDate timeOfBirth, LocalDate timeOfDeath, List<Person> parents) {
        this.identityCode = identityCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeOfBirth = timeOfBirth;
        this.timeOfDeath = timeOfDeath;
        this.parents = parents;
    }
    
    public String getIdentityCode() {
        return identityCode;
    }
    
    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getTimeOfBirth() {
        return timeOfBirth;
    }
    
    public void setTimeOfBirth(LocalDate timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }
    
    public LocalDate getTimeOfDeath() {
        return timeOfDeath;
    }
    
    public void setTimeOfDeath(LocalDate timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }
    
    public List<Person> getParents() {
        return parents;
    }
    
    public void setParents(List<Person> parents) {
        this.parents = parents;
    }
    
    public List<AddressInfo> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<AddressInfo> addresses) {
        this.addresses = addresses;
    }
    
    
    /**
     * Adds a new parent for person.
     * 
     * @param parent person to be added as a parent
     */
    public void addParent(Person parent) { 
        // Current law permits only two parents for a person
        if (parents.size() >= 2) {
            throw new IllegalArgumentException("Only two parents permitted!");
        }
        
        parents.add(parent);
    }
    
    
    /**
     * Removes given person from the list of parents.
     * 
     * @param person person to be removed from the list of parents.
     * @return true if given person is found and removed, otherwise false.
     */
    public boolean removeParent(Person person) {
        return parents.remove(person);
    }
    
    
    /**
     * Adds a new address for person. Stops the old addresses.
     * 
     * @param address new address for person.
     * @param moveInDate move in date for the new address
     * @return address info of the added address.
     */
    public AddressInfo addAddress(Address address, LocalDate moveInDate) {
        AddressInfo addressInfo = new AddressInfo(address, this, moveInDate);
        
        // Check if the person has a current active address.
        AddressInfo currentAddressInfo = findCurrentAddress();
        if (currentAddressInfo != null) {
            LocalDate moveOutDate = moveInDate.minusDays(1l);
            currentAddressInfo.setMoveOutDate(moveOutDate);
        }
        
        addresses.add(0, addressInfo);
        
        return addressInfo;
    }
    
    
    /**
     * Creates a copy of the person object.
     * 
     * @param person person to be copied.
     * @return a copy of the person.
     */
    public static Person copyOf(Person person) {
        return new Person(person.getIdentityCode(), 
                person.getFirstName(), 
                person.getLastName(), 
                person.getTimeOfBirth(), 
                person.getTimeOfDeath(), 
                person.getParents());
    }
    
    @Override
    public boolean equals(Object p2) {
        return identityCode.equals(((Person) p2).getIdentityCode());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    
    /**
     * Finds current address.
     * 
     * @return found address info if address is found, null if address is not found.
     */
    public AddressInfo findCurrentAddress() {
        for (AddressInfo addressInfo : addresses) {
            if (addressInfo.getMoveOutDate() == null) {
                return addressInfo;
            }
        }
        
        return null;
    }
    
    
    /**
     * Gets a person's address info by address.
     * 
     * @param address address to be looked for.
     * @return address info if corresponding address is found, null if not.
     */
    public AddressInfo findAddressInfoByAddress(Address address) {
        for (AddressInfo addressInfo : addresses) {
            if (addressInfo.getAddress().equals(address)) {
                return addressInfo;
            }
        }
        
        return null;
    }
}
