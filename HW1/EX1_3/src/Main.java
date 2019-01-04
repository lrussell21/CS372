import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String s;
        int num = 0;
        System.out.println("Enter a number: ");
        s = userInput.nextLine();

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) < 48 || s.charAt(i) > 57){
                if(i == 0){
                    System.out.println("You did not enter a number.");
                }
                break;
            }else{
                num += (s.charAt(i) - 48);
                if(!(i == s.length() - 1)){
                    num *= 10;
                }
            }
        }
        if(num != 0) {
            System.out.println("You entered " + num);
        }
    }
}
