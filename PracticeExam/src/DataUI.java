import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class DataUI extends javax.swing.JComponent implements ActionListener {
    JFrame frame;
    Data d = new Data();
    int dataIndex = 0;
    private JPanel mainPanel;
    private JPanel textArea;
    private JPanel inputArea;
    private JButton inputButton;
    private JTextField outputText;
    private JTextField inputText;
    private JButton nextButton;
    private JButton previousButton;

    public DataUI(){
        init();
    }

    public void init(){
        frame = new JFrame();
        frame.setSize(750,490);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        outputText.setPreferredSize(new Dimension(720, 400));
        //inputArea.add(button1);

        mainPanel.setBackground(Color.black);
        textArea.setBackground(Color.GRAY);
        inputArea.setBackground(Color.GRAY);

        inputText.setPreferredSize(new Dimension(160, 25));

        nextButton.addActionListener(this);
        previousButton.addActionListener(this);
        inputButton.addActionListener(this);

        frame.add(mainPanel);

        d.customerFill();
        outputText.setText(d.customers.get(dataIndex).toString());
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextButton){
            if(dataIndex < d.customers.size() - 1) {
                dataIndex++;
                outputText.setText(d.customers.get(dataIndex).toString());
            }else{
                dataIndex = 0;
                outputText.setText(d.customers.get(dataIndex).toString());
            }
        }else if(e.getSource() == previousButton){
            if(dataIndex > 0){
                dataIndex--;
                outputText.setText(d.customers.get(dataIndex).toString());
            }else{
                dataIndex = d.customers.size() - 1;
                outputText.setText(d.customers.get(dataIndex).toString());
            }
        }else if(e.getSource() == inputButton){
            try {
                dataIndex = d.indexSearch(Integer.parseInt(inputText.getText()));
                outputText.setText(d.customers.get(dataIndex).toString());
            }
            catch(Exception ex){
                System.out.println("Either not a number or out of bounds!");
            }
        }
    }
}

class testUI {
    public static void main(String[] args) {
        DataUI test = new DataUI();
    }
}
