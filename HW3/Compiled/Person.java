/**
 * This class is a base class used for inheritance for the Police, Teacher, and Kid classes.
 * @see Police
 * @see Kid
 * @see Teacher
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

public class Person {

    private int age, phoneNum, pID;
    private static int baseID = 0;
    private String name;

    /**
     * Initialize the Person object to default values.
     */
    public Person(){
        this.name = "UNSET";
        this.age = -1;
        this.phoneNum = 1111111;
    }

    /**
     * Initialize the Person object to given values.
     * @param name is the Person object's name.
     * @param age is the Person object's age.
     * @param phoneNum is the Person object's phone number.
     */
    public Person(String name, int age, int phoneNum){
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.pID = baseID;
        baseID++;
    }

    /**
     * Returns age of Person object.
     * @return the age of the Person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Lets you change Person's age.
     * @param age is new age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns Person's phone number.
     * @return the phone number of the Person.
     */
    public int getPhoneNum() {
        return phoneNum;
    }

    /**
     * Lets you change Person's phone number.
     * @param phoneNum new phone number.
     */
    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Returns name of Person.
     * @return the name of the Person.
     */
    public String getName() {
        return name;
    }

    /**
     * Lets you change Person's name.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns Person's unique ID.
     * @return Person's unique ID.
     */
    public int getpID() {
        return pID;
    }
}
