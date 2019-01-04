public class EmployeeTest {
    public static void main(String[] args) {
        Employee john = new Employee(3, 1, 2019, "John", "Water Boy", "The Boss Man");
        System.out.printf("%s(%d) is a %s and started %d/%d/%d and reports to %s.\n",   john.getName(),
                                                                    john.getID(),
                                                                    john.getPosition(),
                                                                    john.getMonth(),
                                                                    john.getDay(),
                                                                    john.getYear(),
                                                                    john.getSupervisor());

        Employee juan = new Employee(6, 20, 2017, "Juan", "Cookie Tester", "Greg");
        System.out.printf("%s(%d) is a %s and started %d/%d/%d and reports to %s.\n",   juan.getName(),
                juan.getID(),
                juan.getPosition(),
                juan.getMonth(),
                juan.getDay(),
                juan.getYear(),
                juan.getSupervisor());
    }
}
