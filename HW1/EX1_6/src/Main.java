import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int day, month, year;
        Date d;
        Date f = new Date(2,  10, 2019);

        System.out.printf("Enter month: ");
        month = userInput.nextInt();
        System.out.printf("Enter day: ");
        day = userInput.nextInt();
        System.out.printf("Enter year: ");
        year = userInput.nextInt();
        d = new Date(month,day,year);
        d.today();

        int test = d.difference(f);
        System.out.println("Test Dif: " + test);
    }
}
