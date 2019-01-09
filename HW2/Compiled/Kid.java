/**
 * This class inherits from person class and models a kid.
 * @see Person
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

public class Kid extends Person {
    private String favCandy;

    /**
     * Initialize the Kid object with specified values.
     * @param name name of the Kid.
     * @param age age of the Kid.
     * @param phoneNum Kid's phone number.
     * @param favCandy Kid's favorite candy.
     */
    public Kid(String name, int age, int phoneNum,String favCandy){
        super(name, age, phoneNum);
        this.favCandy = favCandy;
    }

    /**
     * Returns Kid's favorite candy.
     * @return Kid's favorite candy.
     */
    public String getFavCandy() {
        return favCandy;
    }

    /**
     * Lets you set Kid's favorite candy.
     * @param favCandy new favorite candy.
     */
    public void setFavCandy(String favCandy) {
        this.favCandy = favCandy;
    }
}
