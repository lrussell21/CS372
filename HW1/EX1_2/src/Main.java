import java.util.Scanner;

public class Main {
    static final double PI = 3.14;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        double area, r;
        System.out.println("Enter radius of circle: ");
        r = userInput.nextDouble();
        area = PI * r * r;
        System.out.printf("Area is %.2f", area);
    }
}
