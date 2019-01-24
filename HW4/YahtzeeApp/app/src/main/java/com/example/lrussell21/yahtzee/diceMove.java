package com.example.lrussell21.yahtzee;

import java.util.Random;

/**
 * DiceMove class to be used with threads in MainActivity
 */
public class diceMove implements Runnable{
    Random rn = new Random();
    int randomInt;

    /**
     * Creates a new diceMove object.
     */
    public diceMove(){ }

    /**
     * run class for when thread starts that generates random numbers from 1 to 6 for around 2.5 seconds.
     */
    @Override
    public void run() {
        for(int i = 0; i < 35; i++){
            randomInt = rn.nextInt(6) + 1;
            //System.out.printf("INT: %d FACE: %s\n", randomInt, _label.getName());
            try{
                Thread.sleep(75);
            }catch (Exception ex){
                System.out.println("Error!");
            }
        }
    }

    /**
     * Returns face value of dice.
     * @return value of dice.
     */
    public int getFaceValue(){
        return randomInt;
    }
}
