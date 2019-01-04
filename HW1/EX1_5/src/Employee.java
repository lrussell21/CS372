public class Employee {


    private int ID, day, month, year;
    private String name, position;

    public Employee(){
        ID = -1;
        day = 1;
        month = 1;
        year = 1970;
        name = "UNSET";
        position = "UNSET";
    }

    public Employee(int i, int d, int m, int y, String n, String p){
        ID = i;
        day = d;
        month = m;
        year = y;
        name = n;
        position = p;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
