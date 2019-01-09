/**
 * This class inherits from person class and models a police officer.
 * @see Person
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

public class Police extends Person {
    private Role title;
    public enum Role{Patrol, Sargent, Captain, Chief}
    private int ID;
    private static int baseID = 1;
    private double pay;

    /**
     * Initialize the Police object with specified values.
     * @param name Police's name.
     * @param age Police's age.
     * @param phoneNum Police's phone number.
     * @param title Police officer's rank / title.
     */
    public Police(String name, int age, int phoneNum, Role title){
        super(name, age, phoneNum);
        this.title = title;
        ID = baseID;
        baseID++;
    }

    /**
     * Lets you pay the Police Officer.
     * @param dollaBills Amount to pay Officer.
     */
    public void pay(double dollaBills){
        pay += dollaBills;
    }

    /**
     * Returns the Police Officer's employee ID as a string.
     * @return the Police Officer's employee ID as a string.
     */
    public String employeeID(){
        return String.valueOf(this.ID);
    }

    /**
     * Returns the Police Officer's employee ID as an int.
     * @return the Police Officer's employee ID as an int.
     */
    public int getID() {
        return ID;
    }

    /**
     * Lets you set the Officer's title.
     * @param title Officer's new title.
     */
    public void setTitle(Role title){
        this.title = title;
    }

    /**
     * Get the Officer's title.
     * @return the Officer's title.
     */
    public String getTitle(){
        if(title == Role.Patrol){
            return "Patrol";
        }else if(title == Role.Sargent){
            return "Sargent";
        }else if(title == Role.Captain){
            return "Captain";
        }else if(title == Role.Chief){
            return "Chief";
        }else{
            return "Role not initialized.";
        }
    }
}