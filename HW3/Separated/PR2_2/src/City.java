/**
 * This class models a city and uses
 * @author Levi Russell
 * @version 1.00, 08 January 2019
 */

import java.util.ArrayList;

public class City {
    // Preset array of people who are in City Hall and School.
    // Will make a new class to add people for homework with interface Thurs.
    private Police[] cityHallPeople = {
            new Police("Greg", 34, 4556721, Police.Role.Patrol),
            new Police("Noah", 24, 9184603, Police.Role.Chief),
            new Police("James", 27, 5227937, Police.Role.Patrol),
            new Police("Mason", 25, 8968859, Police.Role.Sargent),
            new Police("Rodger", 43, 3752474, Police.Role.Sargent),
            new Police("Jeff", 42, 5528436, Police.Role.Captain),
            new Police("The Mountain", 30, 3369152, Police.Role.Chief)};

    private Teacher[] teach = { new Teacher("Tracy", 34, 6293728, 5, "MA Elementry Education"),
                        new Teacher("Jessica", 23, 4905616, 2, "BA Elementry Education"),
                        new Teacher("Todd", 28, 1485922, 4, "BA Elementry Education")};

    private Kid[] kid = {   new Kid("Timmy", 7, 9559277,"Twix"),
                    new Kid("Jimmy", 12, 6621997,"Skittles"),
                    new Kid("Derek", 8, 5623225,"M&M's"),
                    new Kid("Eric", 9, 3745776,"Twizzlers")};

    //People who are in the city but not City Hall or School.
    ArrayList<Person> citizens = new ArrayList<>();
    CityHall cityHall;
    School school;
    private String name;

    /**
     * Initialize the City with default values(no city name);
     */
    public City(){
        this.name = "UNSET";
        cityHall = new CityHall("Spokane City Hall");
        school = new School("Whitworth");
        for(Police s : cityHallPeople)
            cityHall.enter(s);
        for(Teacher s : teach)
            school.enter(s);
        for(Kid s: kid)
            school.enter(s);
        // Extra People so not everyone in the city is in City Hall or School.
        // These people are just a person because they are not a kid, teacher, or police officer.
        Person p1 = new Person("Callie", 30, 3244149);
        Person p2 = new Person("Weston", 32, 6850089);
        Person p3 = new Person("Bethany", 23, 6493771);
        Person p4 = new Person("Racquel", 13, 3234586);
        Person p5 = new Person("Mildred", 56, 2014165);
        Person p6 = new Person("Clinton", 49, 4731210);
        Person p7 = new Person("Barbara", 105, 2342439);
        citizens.add(p1);
        citizens.add(p2);
        citizens.add(p3);
        citizens.add(p4);
        citizens.add(p5);
        citizens.add(p6);
        citizens.add(p7);
    }

    /**
     * Initialize the city with specified values.
     * @param name name of the City.
     */
    public City(String name){
        this.name = name;
        cityHall = new CityHall("Spokane City Hall");
        school = new School("Whitworth");
        for(Police s : cityHallPeople)
            cityHall.enter(s);
        for(Teacher s : teach)
            school.enter(s);
        for(Kid s: kid)
            school.enter(s);
        // Extra People so not everyone in the city is in City Hall or School.
        // These people are just a person because they are not a kid, teacher, or police officer.
        Person p1 = new Person("Callie", 30, 3244149);
        Person p2 = new Person("Weston", 32, 6850089);
        Person p3 = new Person("Bethany", 23, 6493771);
        Person p4 = new Person("Racquel", 13, 3234586);
        Person p5 = new Person("Mildred", 56, 2014165);
        Person p6 = new Person("Clinton", 49, 4731210);
        Person p7 = new Person("Barbara", 105, 2342439);
        citizens.add(p1);
        citizens.add(p2);
        citizens.add(p3);
        citizens.add(p4);
        citizens.add(p5);
        citizens.add(p6);
        citizens.add(p7);
    }

    /**
     * Returns a string with a name of every citizen in the city.(Doesn't include people in School or City Hall)
     * @return a string with a name of every citizen in the city.
     */
    public String allCitizenNames(){
        String people = "";
        for(Person s: citizens){
            people +=  s.getName() + "\n";
        }
        return people;
    }

    /**
     * Prints every name of people in the city.
     */
    public void listAll(){
        System.out.printf("Listing all people inside City...\n");
        System.out.printf(allCitizenNames());
        System.out.printf(cityHall.listNames());
        System.out.printf(school.listNames());
    }
}


