/**
 * This class models a building and is the base class for School and CityHall.
 * @see School
 * @see CityHall
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

public abstract class Building {
    private String name, address;

    /**
     * Initialize the Building with default values.
     */
    public Building(){
        this.name = "UNSET";
        this.address = "Homeless";
    }

    /**
     * Initalize the building with specified values.
     * @param name The building's name.
     * @param address The building's address.
     */
    public Building(String name, String address){
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the name of the building.
     * @return the name of the building.
     */
    public String getName() {
        return name;
    }

    /**
     * Lets you set the building's name.
     * @param name new building name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the building.
     * @return the address of the building.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Lets you change the building's address.
     * @param address new address for building.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
