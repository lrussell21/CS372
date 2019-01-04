import java.util.Scanner;

public class DateTest {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int day, month, year;
        Date d;
        Date e;
        Date f = new Date();
        f.today();

        System.out.printf("Enter month: ");
        month = userInput.nextInt();
        System.out.printf("Enter day: ");
        day = userInput.nextInt();
        System.out.printf("Enter year: ");
        year = userInput.nextInt();
        d = new Date(month,day,year);

        int dif = d.difference(f);
        System.out.printf("The difference in days between today and the day entered is: " + dif + " days.\n");

        //d.today();
        System.out.printf("The date you entered 12000 days in the future is: ");
        e = d.newFutureDate(12000);
        System.out.printf("%d/%d/%d.\n", e.getMonth(), e.getDay(), e.getYear());
        //int test = d.difference(f);
        //System.out.println("Test Dif: " + test);
    }
}
