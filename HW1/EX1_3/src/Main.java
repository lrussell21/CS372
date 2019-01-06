import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String s;
        int num = 0;
        System.out.printf("Enter a number: ");
        s = userInput.nextLine();

        for(int i = 0; i < s.length(); i++){
            //PT -- if(s.charAt(i) < '0' || s.charAt(i) > '9'){
            if(s.charAt(i) < 48 || s.charAt(i) > 57){
                    System.out.println("ERROR!! Letter detected. Program aborted.");
                num /= 10;
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
