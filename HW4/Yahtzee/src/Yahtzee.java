import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.lang.Thread;

public class Yahtzee implements ActionListener {
    JFrame frame;
    int diceAmount = 5;
    Thread[] diceThreads;
    diceMove[] diceMoveArray;
    Thread testThread;
    boolean run = true;
    private JPanel mainPanel;
    private JLabel dice1;
    private JLabel dice2;
    private JLabel dice3;
    private JLabel dice4;
    private JLabel dice5;
    private JButton rollButton;
    private JTextArea scoreText;

    public Yahtzee(){
        init();
    }

    public void init(){
        frame = new JFrame("Yahtzee");
        frame.setSize(new Dimension(550, 185));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        mainPanel.setBackground(Color.BLACK);
        scoreText.setPreferredSize(new Dimension(110, 30));
        scoreText.setBackground(Color.BLACK);
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
        scoreText.setText("Score: ");
        frame.add(mainPanel);

        diceSetup();

        rollButton.addActionListener(this);
    }

    private void diceSetup(){
        testThread = new Thread(this::outputScore);
        diceMoveArray = new diceMove[diceAmount];
        diceMoveArray[0] = new diceMove(dice1);
        diceMoveArray[1] = new diceMove(dice2);
        diceMoveArray[2] = new diceMove(dice3);
        diceMoveArray[3] = new diceMove(dice4);
        diceMoveArray[4] = new diceMove(dice5);


        diceThreads = new Thread[diceAmount];
        for(int i = 0; i < diceAmount; i++){
            diceThreads[i] = new Thread(diceMoveArray[i]);
        }
    }

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
        run = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rollButton && run == true){
            run = false;
            scoreText.setText("Score: ");
            for(int i = 0; i < diceAmount; i++){
                try {
                    diceSetup();
                    diceThreads[i].start();
                    //System.out.println("" + diceThreads[i].getState());
                }catch (Exception ex){
                    System.out.println("ERROR!!!");
                }
            }
            testThread.start();
        }
    }

    public static void main(String[] args) {
        Yahtzee y = new Yahtzee();
    }
}
