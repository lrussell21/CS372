/**
 * This class inherits from building and models a typical City Hall.
 * @see Building
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

import java.util.ArrayList;

public class CityHall extends Building{
    String cityHallName;
    /*Police[] police0 = { new Police("Greg", 34, 4556721, Police.Role.Patrol),
                        new Police("Noah", 24, 9184603, Police.Role.Chief),
                        new Police("James", 27, 5227937, Police.Role.Patrol),
                        new Police("Mason", 25, 8968859, Police.Role.Sargent),
                        new Police("Rodger", 43, 3752474, Police.Role.Sargent),
                        new Police("Jeff", 42, 5528436, Police.Role.Captain),
                        new Police("The Mountain", 30, 3369152, Police.Role.Chief)};
*/
    ArrayList<Police> police = new ArrayList<Police>();

    /**
     * Initialize the CityHall object with default values.
     */
    public CityHall(){
        this.cityHallName = "UNSET";
    }

    /**
     * Initialize the CityHall object with specified values.
     * @param cityHallName name of the City Hall.
     */
    public CityHall(String cityHallName){
        this.cityHallName = cityHallName;
    }

    /**
     * Allows a Police Officer to enter City Hall.
     * @param p the Police object.
     */
    public void enter(Police p){
        police.add(p);
    }

    /**
     * Allows a Police Officer to exit building.
     * @param name name of the officer.
     * @return Returns Police object.
     */
    public Police exit(String name){
        Police ret;
        if(!police.isEmpty()) {
            for(Police p : police) {
                if (p.getName() == name) {
                    ret = p;
                    police.remove(p);
                    return ret;
                }
            }
        }else{
            System.out.println("The building is empty.");
        }
        System.out.println("They are not in this building.");
        return null;
    }

    /**
     * Returns the name of the employee with the specified ID number.
     * @param employeeID The employee's ID number.
     * @return the name of the employee with the specified ID number.
     */
    public String IDtoName(int employeeID){
        String returnString;
        for(Police p : police) {
            if (p.getID() == employeeID) {
                returnString = p.getName();
                return returnString;
            }
        }
        return "NOT FOUND";
    }

    /**
     * Lets you pay a Police Officer based off their ID number and the amount of pay.
     * @param employeeID the Police's employee ID number.
     * @param pay the amount of money to be paid.
     * @return if the Police Officer was found in the ArrayList and was successfully paid.
     */
    public boolean pay(int employeeID, double pay){
        for(Police p : police) {
            if (p.getID() == employeeID) {
                p.pay(pay);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the name of the City Hall.
     * @return the name of the City Hall.
     */
    public String getCityHallName() {
        return cityHallName;
    }

    /**
     * Lets you change the name of the City Hall.
     * @param cityHallName new City Hall name.
     */
    public void setCityHallName(String cityHallName) {
        this.cityHallName = cityHallName;
    }

    /**
     * Returns a string of all people's names in City Hall.
     * @return a string of all people's names in City Hall.
     */
    public String listNames(){
        String people = "";
        for(Police s: police){
            people +=  s.getName() + "\n";
        }
        return people;
    }

    /**
     * Returns a detailed list of all the people in City Hall.
     * @return a detailed list of all the people in City Hall.
     */
    public String toString(){
        String people = "";
        for(Police s: police){
            people +=  (s.getName() + " : " + s.getTitle() + ".\n");
        }
        return people;
    }
}

/*
class CityHallTest{
    public static void main(String[] args) {
        CityHall test = new CityHall();
        Police popo = new Police("Greg", 34, 4556721, Police.Role.Patrol);
        test.enter(popo);
        System.out.printf(test.toString());
    }
}
*/