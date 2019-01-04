public class Main {
    public static void main(String[] args) {
        Employee john = new Employee(1234, 1, 3, 2019, "John", "Water Boy");
        System.out.printf("%s(%d) is a %s and started %d/%d/%d.",   john.getName(),
                                                                    john.getID(),
                                                                    john.getPosition(),
                                                                    john.getMonth(),
                                                                    john.getDay(),
                                                                    john.getYear());
    }
}
