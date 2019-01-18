package com.example.lrussell21.yahtzee;


import java.util.Random;

public class diceMove implements Runnable{
    Random rn = new Random();
    int randomInt;

    public diceMove(){ }

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

    public int getFaceValue(){
        return randomInt;
    }
}
