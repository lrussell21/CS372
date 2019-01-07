
public class Police extends Person {
    private Role title;
    public enum Role{
        Patrol, Sargent, Captain, Chief
    }

    public Police(String name, int age, int phoneNum, Role title){
        super(name, age, phoneNum);
        this.title = title;
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
        Police test = new Police("Eric", 32, 2085555555, Police.Role.Patrol);
        System.out.printf(test.getTitle());
    }
}