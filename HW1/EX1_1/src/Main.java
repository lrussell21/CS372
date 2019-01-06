import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        int numOfRandom, randNum;
        Scanner userInput = new Scanner(System.in);
        boolean rangeTest = true;
        System.out.printf("Enter a number between 10 and 100:");
        do{
            numOfRandom = userInput.nextInt();
            if(numOfRandom < 10 || numOfRandom > 100){
                System.out.printf("Out of range! Enter a number between 10 and 100:");
            }else{
                rangeTest = false;
            }
        }while(rangeTest);

        for(int i = 0; i < numOfRandom; i++){
            //PT -- randNum = ThreadLocalRandom.current().nextInt(0, 100) + 1;
            randNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(randNum % 2 == 0){
                System.out.printf("%2d EVEN\n", randNum);
            }else{
                System.out.printf("%2d ODD\n", randNum);
            }
        }
    }
}
