public class CityHall extends Building{
    Police[] police = { new Police("Greg", 34, 4556721, Police.Role.Patrol),
                        new Police("Noah", 24, 9184603, Police.Role.Chief),
                        new Police("James", 27, 5227937, Police.Role.Patrol),
                        new Police("Mason", 25, 8968859, Police.Role.Sargent),
                        new Police("Rodger", 43, 3752474, Police.Role.Sargent),
                        new Police("Jeff", 42, 5528436, Police.Role.Captain),
                        new Police("The Mountain", 30, 3369152, Police.Role.Chief)};
    public CityHall(){

    }

    public String toString(){
        String people = "";
        for(int i = 0; i < police.length; i++){
            people += police[i].getName() + " \n";
        }
        return people;
    }
}

class CityHallTest{
    public static void main(String[] args) {
        CityHall test = new CityHall();
        System.out.printf(test.toString());
    }
}
