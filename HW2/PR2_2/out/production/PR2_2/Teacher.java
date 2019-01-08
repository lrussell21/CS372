
public class Teacher extends Person implements Employee{
    private int gradelevel, ID;
    private double pay = 0.0;
    private String certification;
    private static int baseID = 1;

    public Teacher(String name, int age, int phoneNum, int gradelevel, String certification){
        super(name, age, phoneNum);
        this.gradelevel = gradelevel;
        this.certification = certification;
        ID = baseID;
        baseID++;
    }

    public void pay(double dollaBills){
        pay += dollaBills;
    }
    public String employeeID(){
        return ID;
    }

    public int getGradelevel() {
        return gradelevel;
    }

    public void setGradelevel(int gradelevel) {
        this.gradelevel = gradelevel;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }
}
