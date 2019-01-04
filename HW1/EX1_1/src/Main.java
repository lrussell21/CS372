import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        int numOfRandom, randNum;
        Scanner userInput = new Scanner(System.in);
        boolean rangeTest = true;
        System.out.println("Enter a number between 10 and 100:");
        do{
            numOfRandom = userInput.nextInt();
            if(numOfRandom < 10 || numOfRandom > 100){
                System.out.println("Out of range! Enter a number between 10 and 100:");
            }else{
                rangeTest = false;
            }
        }while(rangeTest);

        for(int i = 0; i < numOfRandom; i++){
            randNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randNum % 2 == 0){
                System.out.println(randNum + " EVEN");
            }else{
                System.out.println(randNum + " ODD");
            }
        }
    }
}
