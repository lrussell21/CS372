
public abstract class Person {

    private int age, phoneNum;
    private String name;

    public Person(){}
    public Person(String name, int age, int phoneNum){
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
