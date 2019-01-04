import java.util.Scanner;

public class NumberConversionTest {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        NumberConversion convert = new NumberConversion();
        double lbs, in, kg, m, bmi;
        System.out.printf("Enter height(in): ");
        in = userInput.nextDouble();
        System.out.printf("Enter weight(lbs): ");
        lbs = userInput.nextDouble();

        kg = convert.lbsTokg(lbs);
        m = convert.inTom(in);
        bmi = kg / (m * m);
        System.out.printf("BMI is %.2f", bmi);
    }
}
