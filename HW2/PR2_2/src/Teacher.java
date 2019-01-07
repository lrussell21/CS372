
public class Teacher extends Person{
    private int gradelevel;
    private String certification;

    public Teacher(String name, int age, int phoneNum, int gradelevel, String certification){

        super(name, age, phoneNum);
        this.gradelevel = gradelevel;
        this.certification = certification;

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
