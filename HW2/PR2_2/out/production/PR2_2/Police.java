
public class Police extends Person {
    private Role title;
    public enum Role{Patrol, Sargent, Captain, Chief}
    private int ID;
    private static int baseID = 1;
    private double pay;

    public Police(String name, int age, int phoneNum, Role title){
        super(name, age, phoneNum);
        this.title = title;
        ID = baseID;
        baseID++;
    }

    public void pay(double dollaBills){
        pay += dollaBills;
    }
    public String employeeID(){
        return ID;
    }

    public void setTitle(Role title){
        this.title = title;
    }

    public String getTitle(){
        if(title == Role.Patrol){
            return "Patrol";
        }else if(title == Role.Sargent){
            return "Sargent";
        }else if(title == Role.Captain){
            return "Captain";
        }else if(title == Role.Chief){
            return "Chief";
        }else{
            return "Role not initialized.";
        }
    }
}

class PoTest{
    public static void main(String[] args) {
        Police test = new Police("Eric", 32, 5555555, Police.Role.Patrol);
        System.out.printf(test.getTitle());
    }
}