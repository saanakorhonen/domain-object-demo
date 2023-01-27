package app.populationinfo.model;

import java.time.LocalDate;

/**
 * Class for holding persons address info.
 * 
 * @author Saana Korhonen
 * @version 27 Jan 2023 
 *
 */
public class AddressInfo {
    private Address address;
    private Person resident;
    private LocalDate moveInDate;
    private LocalDate moveOutDate;  
    
    /**
     * Constructor.
     * 
     * @param address address attached to the info.
     * @param resident current resident.
     * @param moveInDate Date of moving in.
     */
    public AddressInfo(Address address, Person resident, LocalDate moveInDate) {
        this.address = address;
        this.resident = resident;
        this.moveInDate = moveInDate;
    }
     
    /**
     * Constructor with moving out Date.
     * 
     * @param address address attached to the info.
     * @param resident current resident.
     * @param moveInDate Date of moving in.
     * @param moveOutDate Date of moving out.
     */
    public AddressInfo(Address address, Person resident, LocalDate moveInDate, LocalDate moveOutDate) {
        this.address = address;
        this.resident = resident;
        this.moveInDate = moveInDate;
        this.moveOutDate = moveOutDate;
    }

    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Person getResident() {
        return resident;
    }
    
    public void setResident(Person resident) {
        this.resident = resident;
    }
    
    public LocalDate getMoveInDate() {
        return moveInDate;
    }
    
    public void setMoveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
    }
    
    public LocalDate getMoveOutDate() {
        return moveOutDate;
    }
    
    public void setMoveOutDate(LocalDate moveOutDate) {
        this.moveOutDate = moveOutDate;
    }
    
    /**
     * Creates a copy of address info.
     * 
     * @param addressInfo address info object to be copied.
     * @return copy of the address info object.s
     */
    public static AddressInfo copyOf(AddressInfo addressInfo) {
        return new AddressInfo(addressInfo.getAddress(),
                addressInfo.getResident(),
                addressInfo.getMoveInDate(),
                addressInfo.getMoveOutDate());
    }
    
    @Override
    public boolean equals(Object a2) {
        return address.equals(((AddressInfo) a2).getAddress()) && resident.equals(((AddressInfo) a2).getResident())
                && moveInDate.equals(((AddressInfo) a2).getMoveInDate()) 
                && ((moveOutDate != null && ((AddressInfo) a2).getMoveOutDate() != null && moveOutDate.equals(((AddressInfo) a2).getMoveOutDate()))
                || (moveOutDate == null && ((AddressInfo) a2).getMoveOutDate() == null));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
