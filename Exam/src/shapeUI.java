import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**
 * Outputs shape UI using shapeController and shape classes.
 */
public class shapeUI extends javax.swing.JComponent {
    JFrame frame;
    shapeController sC = new shapeController();

    private JPanel mainPanel;
    private JList shapeList;
    private JPanel listPanel;
    private JPanel displayPanel;
    private JLabel shapeLabel;
    private JTextArea shapeDetail;

    /**
     * Creates object and runs initialization function.
     */
    public shapeUI(){
        init();
    }

    /**
     * Sets up frame and contents inside window.,
     */
    private void init(){
        // Initialize the window.
        frame = new JFrame("Shape Genie");
        frame.setSize(800,400);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sC.createShapes();

        // Creates strings with shape names and ID'sC.
        String[] listNames = new String[sC.shapes.size()];
        for(int i = 0; i < sC.shapes.size(); i++){
            listNames[i] = sC.shapes.get(i).toString();
        }
        // Adds shape names and ID'sC to the JList.
        shapeList.setListData(listNames);

        // Sets Font of the JList.
        shapeList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        // Creates the scroll wheel for the JList and the background and foreground colors..
        JScrollPane shapeListScroll = new JScrollPane(shapeList);
        shapeListScroll.setBackground(Color.black);
        shapeListScroll.setForeground(Color.white);
        shapeListScroll.setPreferredSize(new Dimension(240, 350));
        shapeList.setBackground(Color.darkGray);
        shapeList.setForeground(Color.white);
        listPanel.add(shapeListScroll);

        // Adds listener to JList so when item is clicked it updates right side of window.
        // I'm able to just use the selected index as index to call for the Controller data
        // because the JList is created based off the index's in the shapeController.
        // It would possibly be better to use ID to confirm, but in this case this works perfectly fine.
        shapeList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    shapelistPanelChange(shapeList.getSelectedIndex());
                }
            }
        });

        // Sets windows background color and font.
        Color backgroundColor = Color.black;
        listPanel.setBackground(backgroundColor);
        displayPanel.setBackground(backgroundColor);
        mainPanel.setBackground(backgroundColor);
        shapeDetail.setBackground(backgroundColor);
        shapeDetail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        //shapeDetail.setCaretColor(Color.white);

        // Initialize shape detail output to first item.
        shapelistPanelChange(0);

        // Adds mainPanel to frame and sets frame as visible.
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Outputs information of item clicked on JList.
     * @param index received index of item clicked.
     */
    public void shapelistPanelChange(int index){
        // Creates 4 different possible icons for each shape.
        int ratio = 150;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon icon;
        URL circleURL = getClass().getResource("/resources/circle.png");
        Image circleImg = toolkit.getImage(circleURL);
        circleImg = circleImg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL triangleURL = getClass().getResource("/resources/triangle.png");
        Image triangleImg = toolkit.getImage(triangleURL);
        triangleImg = triangleImg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL squareURL = getClass().getResource("/resources/square.png");
        Image squareImg = toolkit.getImage(squareURL);
        squareImg = squareImg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL rectURL = getClass().getResource("/resources/rectangle.png");
        Image rectImg = toolkit.getImage(rectURL);
        rectImg = rectImg.getScaledInstance(ratio + ratio / 2, ratio, Image.SCALE_SMOOTH);

        // Based on shape sets icon to that shape. (Would use generic List but icons are hardcoded anyways...)
        if(sC.shapes.get(index) instanceof Circle){
            icon = new ImageIcon(circleImg);
        }else if(sC.shapes.get(index) instanceof Triangle){
            icon = new ImageIcon(triangleImg);
        }else if(sC.shapes.get(index) instanceof Square){
            icon = new ImageIcon(squareImg);
        }else if(sC.shapes.get(index) instanceof Rectangle){
            icon = new ImageIcon(rectImg);
        }else{
            // If no instance is found outputs a nice circle.
            icon = new ImageIcon(circleImg);
        }

        // Sets label to icon based on shape and sets text to that shapes detailed string.
        shapeLabel.setIcon(icon);
        shapeDetail.setText(sC.shapes.get(index).getDetailString());
    }
}

// Test class with main and UI object to run program.
class shapeUITest{
    public static void main(String[] args) {
        shapeUI run = new shapeUI();
    }
}
