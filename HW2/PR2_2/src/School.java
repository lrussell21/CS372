public class School extends Building {
    Person[] people = { new Teacher("Tracy", 34, 6293728, 5, "MA Elementry Education"),
                        new Teacher("Jessica", 23, 4905616, 2, "BA Elementry Education"),
                        new Teacher("Todd", 28, 1485922, 4, "BA Elementry Education"),
                        new Kid("Timmy", 7, 9559277,"Twix"),
                        new Kid("Jimmy", 12, 6621997,"Skittles")};
    public School(){

    }

    public String toString(){
        String names = "";
        for(int i = 0; i < people.length; i++){
            names += people[i].getName() + " \n";
        }
        return names;
    }
}

class SchoolTest{
    public static void main(String[] args) {
        School test = new School();
        System.out.printf(test.toString());
    }
}