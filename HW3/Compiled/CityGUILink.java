import java.util.ArrayList;

public class CityGUILink {
    City mainCity;
    ArrayList<Person> cityPeople = new ArrayList<>();
    ArrayList<Person> cityHallPeople = new ArrayList<>();
    ArrayList<Person> schoolPeople = new ArrayList<>();

    /**
     * Creates a new CityGUILink object that constructs a City Object.
     */
    public CityGUILink(){
        mainCity = new City("My City");
    }

    /**
     * Adds a person to the controller and City object.
     * @param k Person Object.
     */
    public void createPerson(Person k){
        cityPeople.add(k);
        mainCity.addPerson(k);
    }

    /**
     * Takes a person and puts them into school ArrayList.
     * @param k Person Object.
     */
    public void moveSchool(Person k){
        schoolPeople.add(k);
        // Change this in future to actually go to school class.
        mainCity.addPerson(k);
    }

    /**
     * Takes a person and puts them into CityHall ArrayList.
     * @param k Person Object.
     */
    public void moveCityHall(Person k){
        cityHallPeople.add(k);
        // Change this in future to actually go to cityHall class.
        mainCity.addPerson(k);
    }

    /**
     * Takes a person and puts them into City ArrayList.
     * @param k Person Object.
     */
    public void moveToCity(Person k){
        cityPeople.add(k);
        mainCity.addPerson(k);
    }

    /**
     * Returns a string with all people in the City's name.
     * @return a string with all people in the City's name.
     */
    public String cityPeopleNames(){
        String people = "";
        for(Person s: cityPeople){
            people +=  s.getName() + " | ";
        }
        return people;
    }

    /**
     * Returns a string with all people's name currently in City Hall.
     * @return a string with all people's name currently in City Hall.
     */
    public String cityHallPeopleNames(){
        String people = "";
        for(Person s: cityHallPeople){
            people +=  s.getName() + " | ";
        }
        return people;
    }

    /**
     * Returns a string with all people's name currently in the School.
     * @return a string with all people's name currently in the School.
     */
    public String schoolPeopleNames(){
        String people = "";
        for(Person s: schoolPeople){
            people +=  s.getName() + " | ";
        }
        return people;
    }

    /**
     * Returns a Person object which of which has a Unique ID that matches one given.
     * @param id Unique ID of person object requested.
     * @return Person Object that has same Unique ID as one given or returns NULL.
     */
    public Person idToPerson(int id){
        Person ret;
        for(Person p : cityPeople){
            if(p.getpID() == id){
                ret = p;
                cityPeople.remove(p);
                mainCity.removePerson(p);
                return ret;
            }
        }

        for(Person o : cityHallPeople){
            if(o.getpID() == id){
                ret = o;
                cityHallPeople.remove(o);
                mainCity.removePerson(o);
                return ret;
            }
        }

        for(Person q : schoolPeople){
            if(q.getpID() == id){
                ret = q;
                schoolPeople.remove(q);
                mainCity.removePerson(q);
                return ret;
            }
        }
        System.out.println("Person with ID not found.");
        return null;
    }

    /**
     * Returns person's name given Unique ID.
     * @param id Person's unique ID.
     * @return Person's name who has same unique ID as one given.
     */
    public String personToString(int id){
        String ret = "";
        Person person = new Person();
        for(Person p : cityPeople){
            if(p.getpID() == id){
                person = p;
            }
        }
        for(Person o : cityHallPeople){
            if(o.getpID() == id){
                person = o;
            }
        }
        for(Person q : schoolPeople){
            if(q.getpID() == id){
                person = q;
            }
        }
        if(person instanceof Kid){
            ret = person.getName() + " is " + person.getAge() + " and likes " + ((Kid) person).getFavCandy() + ".";
        }else if(person instanceof Teacher){
            ret = person.getName() + " is " + person.getAge() + " and teaches grade " + ((Teacher) person).getGradelevel() + " with certification: " + ((Teacher) person).getCertification() + ".";
        }else if(person instanceof Police){
            ret = person.getName() + " is " + person.getAge() + " and is a " + ((Police) person).getTitle() + " Officer.";
        }else{
            ret = "";
        }
        return ret;
    }
}
