package com.example.lrussell21.yahtzee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int diceAmount = 5;
    private int diceValue = 0;
    ImageView dice1;
    ImageView dice2;
    ImageView dice3;
    ImageView dice4;
    ImageView dice5;

    Thread[] diceThreads;
    diceMove[] diceMoveArray;
    ImageView[] dices;
    Thread outputImages;
    TextView scoreOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        scoreOut = findViewById(R.id.scoreText);

        Button roll = findViewById(R.id.rollButton);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceSetup();
                for(int i = 0; i < diceAmount; i++){
                    diceThreads[i].start();
                }
                //scoreThread.start();
                outputImages.start();
            }
        });


    }

    private void diceSetup(){
        //scoreThread = new Thread(this::outputScore);
        outputImages = new Thread(this::outputImages);
        diceMoveArray = new diceMove[diceAmount];
        diceMoveArray[0] = new diceMove();
        diceMoveArray[1] = new diceMove();
        diceMoveArray[2] = new diceMove();
        diceMoveArray[3] = new diceMove();
        diceMoveArray[4] = new diceMove();

        dices = new ImageView[diceAmount];
        dices[0] = dice1;
        dices[1] = dice2;
        dices[2] = dice3;
        dices[3] = dice4;
        dices[4] = dice5;

        diceThreads = new Thread[diceAmount];
        for(int i = 0; i < diceAmount; i++){
            diceThreads[i] = new Thread(diceMoveArray[i]);
        }
    }

    private void outputImages(){
        try{
            Thread.sleep(100);
        }catch(Exception ex){

        }

        for(int j = 0; j < 35; j++) {
            //Toast.makeText(getApplicationContext(), "" + diceMoveArray[0].getFaceValue(),
            //        Toast.LENGTH_SHORT).show();
            try{
                Thread.sleep(75);
            }catch(Exception ex){

            }
            diceValue = 0;
            for (int i = 0; i < diceAmount; i++) {
                setImage(dices[i], diceMoveArray[i].getFaceValue());
                diceValue += diceMoveArray[i].getFaceValue();
            }
            scoreOut.setText("" + diceValue);
        }
    }

    private void setImage(ImageView img, int faceValue){
        if(faceValue == 1){
            img.setImageResource(R.drawable.die_face_1);
        }else if (faceValue == 2){
            img.setImageResource(R.drawable.die_face_2);
        }else if (faceValue == 3){
            img.setImageResource(R.drawable.die_face_3);
        }else if (faceValue == 4){
            img.setImageResource(R.drawable.die_face_4);
        }else if (faceValue == 5){
            img.setImageResource(R.drawable.die_face_5);
        }else if (faceValue == 6){
            img.setImageResource(R.drawable.die_face_6);
        }else{
            //System.out.println("Error");
        }
    }

    /*
    public void outputScore(){
        try {
            Thread.sleep(20);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        boolean allThreadsDone;
        do{
            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            allThreadsDone = true;
            for(int i = 0; i < diceAmount; i++){
                if(diceThreads[i].getState() != Thread.State.NEW && diceThreads[i].getState() != Thread.State.TERMINATED){
                    allThreadsDone = false;
                }
                int score = 0;
                for(int j = 0; j < diceAmount; j++){
                    score += diceMoveArray[j].getFaceValue();
                    //System.out.println("" + diceThreads[i].getState());
                    //System.out.printf("Score:%d  FACE:%d\n", score, diceMoveArray[i].getFaceValue());
                }
                scoreText.setText("Score: " + score);
            }
        }while(!allThreadsDone);
    }
    */
}
