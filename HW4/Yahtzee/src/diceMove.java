import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

/**
 * Dice class that rolls dice and changes output picture.
 */
public class diceMove implements Runnable{
    private JLabel _label;
    Random rn = new Random();
    ImageIcon face1;
    ImageIcon face2;
    ImageIcon face3;
    ImageIcon face4;
    ImageIcon face5;
    ImageIcon face6;

    /**
     * Initialize diceMove objects for each label.
     * @param label label that will have dice icon.
     */
    public diceMove(JLabel label){
        this._label = label;
        images();
        _label.setIcon(randomDieSide());
    }

    /**
     * Run function for Threads to call that rolls the dice.
     */
    @Override
    public void run() {
        for(int i = 0; i < 35; i++){
            _label.setIcon(randomDieSide());
            try{
                Thread.sleep(75);
            }catch (Exception ex){
                System.out.println("Error!");
            }
        }
    }

    /**
     * Sets a random number from 1 to 6 then sets label name to that number for easy counting.
     * @return the dice face object that corresponds to the random number generated.
     */
    private ImageIcon randomDieSide(){
        int randomInt = rn.nextInt(6) + 1;
        //System.out.println(randomInt);
        if(randomInt == 1){
            _label.setName("1");
            return face1;
        }else if(randomInt == 2){
            _label.setName("2");
            return face2;
        }else if(randomInt == 3){
            _label.setName("3");
            return face3;
        }else if(randomInt == 4){
            _label.setName("4");
            return face4;
        }else if(randomInt == 5){
            _label.setName("5");
            return face5;
        }else if(randomInt == 6){
            _label.setName("6");
            return face6;
        }else{
            return null;
        }
    }

    /**
     * Gets face value of dice.
     * @return
     */
    public int getFaceValue(){
        return Integer.parseInt(_label.getName());
    }

    /**
     * Creates all the dice images.
     */
    public void images(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int ratio = 100;
        URL dice1URL = getClass().getResource("/resources/die_face_1.png");
        Image dice1img = toolkit.getImage(dice1URL);
        dice1img = dice1img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL dice2URL = getClass().getResource("/resources/die_face_2.png");
        Image dice2img = toolkit.getImage(dice2URL);
        dice2img = dice2img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL dice3URL = getClass().getResource("/resources/die_face_3.png");
        Image dice3img = toolkit.getImage(dice3URL);
        dice3img = dice3img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL dice4URL = getClass().getResource("/resources/die_face_4.png");
        Image dice4img = toolkit.getImage(dice4URL);
        dice4img = dice4img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL dice5URL = getClass().getResource("/resources/die_face_5.png");
        Image dice5img = toolkit.getImage(dice5URL);
        dice5img = dice5img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL dice6URL = getClass().getResource("/resources/die_face_6.png");
        Image dice6img = toolkit.getImage(dice6URL);
        dice6img = dice6img.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);
        face1 = new ImageIcon(dice1img);
        face2 = new ImageIcon(dice2img);
        face3 = new ImageIcon(dice3img);
        face4 = new ImageIcon(dice4img);
        face5 = new ImageIcon(dice5img);
        face6 = new ImageIcon(dice6img);
    }
}
