/**
 * This class inherits from building and models a school.
 * @see Building
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

import java.util.ArrayList;

public class School extends Building {
    private String schoolName;
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    ArrayList<Kid> students = new ArrayList<Kid>();

    /**
     * Initalize the school with default values.
     */
    public School(){
        this.schoolName = "UNSET";
    }

    /**
     * Initalize the school with specified values.
     * @param schoolName school's name.
     */
    public School(String schoolName){
        this.schoolName = schoolName;
    }

    /**
     * Allows a Teacher to enter the school.
     * @see Teacher
     * @param p the teacher object.
     */
    void enter(Teacher p){
        teachers.add(p);
    }

    /**
     * Allows a Kid to enter the school.
     * @see Kid
     * @param p the kid object.
     */
    void enter(Kid p){
        students.add(p);
    }

    /**
     * Lets a student(Kid) or teacher leave the school.
     * @param name the student or teacher's name.
     * @return Returns Kid or Teacher object.
     */
    public Person exit(String name){
        Teacher retTeacher;
        Kid retKid;
        for(Teacher p : teachers) {
            if (p.getName() == name) {
                retTeacher = p;
                teachers.remove(p);
                return retTeacher;
            }
        }
        for(Kid p : students) {
            if (p.getName() == name) {
                retKid = p;
                students.remove(p);
                return retKid;
            }
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
        for(Teacher t : teachers) {
            if (t.getID() == employeeID) {
                returnString = t.getName();
                return returnString;
            }
        }
        return "NOT FOUND";
    }

    /**
     * Lets you pay a teacher based off their ID number and the amount of pay.
     * @param employeeID the teacher's employee ID number.
     * @param pay the amount of money to be paid.
     * @return if the teacher was found in the ArrayList and was successfully paid.
     */
    public boolean pay(int employeeID, double pay){
        for(Teacher t : teachers) {
            if (t.getID() == employeeID) {
                t.pay(pay);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the school's name.
     * @return the school's name.
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Lets you change the school's name.
     * @param schoolName school's new name.
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * Puts all the Teacher's and Student's(Kid's) name into a string.
     * @return the name of all Teacher's and Student's(Kid's) in the school.
     */
    public String listNames(){
        String people = "";
        for(Teacher s: teachers){
            people +=  s.getName() + "\n";
        }
        for(Kid s: students){
            people +=  s.getName() + "\n";
        }
        return people;
    }

    /**
     * Returns a string with all the details of the student's(Kid's) and teacher's.
     * @return a string with all the details of the student's(Kid's) and teacher's.
     */
    public String toString(){
        String people = "";
        for(Teacher s: teachers){
            people +=  (s.getName() + "(" + s.employeeID() + ") with a " + s.getCertification() + ".\n");
        }
        for(Kid s: students){
            people +=  (s.getName() + " who likes " + s.getFavCandy() + ".\n");
        }
        return people;
    }

}

/*
class SchoolTest{
    public static void main(String[] args) {
        School test = new School();
        Teacher t = new Teacher("Todd", 28, 1485922, 4, "BA Elementry Education");
        Kid k = new Kid("Timmy", 7, 9559277,"Twix");
        test.enter(t);
        test.enter(k);
        System.out.printf(test.toString());
    }
}
*/