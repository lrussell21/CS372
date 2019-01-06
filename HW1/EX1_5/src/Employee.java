public class Employee {


    private int ID, day, month, year;
    private static int baseID = 1;
    private String name;
    private String position, supervisor;

    public Employee(){
        ID = baseID;
        baseID++;
        day = -1;
        month = -1;
        year = -1;
        name = "UNSET";
        position = "UNSET";
        supervisor = "UNSET";
    }

    public Employee(int m, int d, int y, String n, String p, String s){
        ID = baseID;
        baseID++;
        day = d;
        month = m;
        year = y;
        name = n;
        position = p;
        supervisor = s;
    }

    public int getID() {
        return ID;
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

    public String getSupervisor() { return supervisor; }

    public void setSupervisor(String supervisor) { this.supervisor = supervisor; }
    
    //PT -- override toString()?
}
