public class CityTest {
    public static void main(String[] args) {
        City testCity = new City();
        testCity.listAll();
        System.out.println();

        System.out.printf("Currently inside %s...\n", testCity.cityHall.getCityHallName());
        System.out.printf(testCity.cityHall.toString());
        System.out.println();

        System.out.printf("Currently inside %s...\n", testCity.school.getSchoolName());
        System.out.printf(testCity.school.toString());
        System.out.println();

        for(int i = 0; i < testCity.cityHall.police.size(); i++) {
            if (testCity.cityHall.pay(i, 500)) {
                System.out.printf("Successfully paid employee %s who works at %s.\n",
                        testCity.cityHall.IDtoName(i), testCity.cityHall.getCityHallName());
            }
        }
        System.out.println();

        for(int i = 0; i < testCity.school.students.size(); i++) {

            if (testCity.school.pay(i, 400)) {
                System.out.printf("Successfully paid employee %s who works at %s.\n",
                        testCity.school.IDtoName(i), testCity.school.getSchoolName());
            }
        }
    }
}
