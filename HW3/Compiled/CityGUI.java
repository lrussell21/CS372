import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

public class CityGUI extends javax.swing.JComponent implements ActionListener, MouseMotionListener, MouseInputListener {
    CityGUILink link;
    JFrame frame;
    JLayeredPane cityAreaLP;
    Point diffDrag;


    private ArrayList<JLabel> runtimeObjects = new ArrayList<>();
    private static int runtimeObjectsIndex = 0;
    private int lastPersonIndex = -1;

    private JPanel basePanel;

    private JPanel newKid;
    private JLabel createNewKid;
    private JTextField newKidName;
    private JTextField newKidAge;
    private JTextField newKidPhone;
    private JTextField newKidCandy;
    private JButton kidCreateButton;

    private JPanel newTeacher;
    private JLabel createNewTeacher;
    private JTextField newTeacherName;
    private JTextField newTeacherAge;
    private JTextField newTeacherPhone;
    private JTextField newTeacherGradeLevel;
    private JTextField newTeacherCert;
    private JButton teacherCreateButton;

    private JPanel textArea;
    private JPanel newPolice;
    private JLabel createNewPolice;
    private JTextField newPoliceName;
    private JTextField newPoliceAge;
    private JTextField newPolicePhone;
    private JComboBox newPoliceRole;
    private JButton policeCreateButton;

    private JPanel cityArea;
    private JLabel cityBG;
    private JPanel actions;
    private JButton listAllButton;
    private JButton personDetails;
    private JButton autoGen;
    private JLabel cityHallViewIcon;
    private JLabel schoolViewIcon;
    private JTextArea cityHallText;
    private JTextArea schoolText;
    private JTextArea cityText;
    private JTextArea outputText;

    /**
     * Creates a CityGUI object that outputs the program.
     */
    public CityGUI() {
        link = new CityGUILink();
        init();
    }

    /**
     * Sets up the GUI and everything the program needs.
     */
    private void init(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon icon;

        frame = new JFrame();
        frame.setSize(1190,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Creates layered panel for main UI area.
        cityAreaLP = new JLayeredPane();
        cityAreaLP.setBackground(Color.red);
        cityAreaLP.setVisible(true);
        cityAreaLP.setPreferredSize(new Dimension(962, 600));
        // Tried to use this to fix white gap at top but didn't work.
        //cityAreaLP.setLocation(cityAreaLP.getX(), cityAreaLP.getY() + 200);
        cityAreaLP.addMouseMotionListener(this);
        cityAreaLP.addMouseListener(this);

        // Creates everything for the people creation.
        createPeopleTextBoxInit();

        // Sets size of text box on right, when I changed one it changed all of them, so that's
        // why only one Textbox for left side has setSize/
        cityHallText.setSize(190, 50);



        int height = 25;
        //newKidName.setMinimumSize(new Dimension(50, 30));
        newKidName.setPreferredSize(            new Dimension(120, height));
        newKidAge.setPreferredSize(             new Dimension(120, height));
        newKidPhone.setPreferredSize(           new Dimension(190, height));
        newKidCandy.setPreferredSize(           new Dimension(190, height));
        newTeacherName.setPreferredSize(        new Dimension(120, height));
        newTeacherAge.setPreferredSize(         new Dimension(120, height));
        newTeacherPhone.setPreferredSize(       new Dimension(190, height));
        newTeacherGradeLevel.setPreferredSize(  new Dimension(200, height));
        newTeacherCert.setPreferredSize(        new Dimension(200, height));
        newPoliceName.setPreferredSize(         new Dimension(120, height));
        newPoliceAge.setPreferredSize(          new Dimension(120, height));
        newPolicePhone.setPreferredSize(        new Dimension(190, height));

        // Buttons for controls
        listAllButton.addActionListener(this);
        personDetails.addActionListener(this);
        autoGen.addActionListener(this);

        //schoolText.setMinimumSize(new Dimension(190, 200));
        schoolText.setPreferredSize(        new Dimension(190, -1));
        cityHallText.setPreferredSize(      new Dimension(190, -1));
        cityText.setPreferredSize(          new Dimension(190, -1));
        outputText.setPreferredSize(        new Dimension(190, -1));

        // Creates background, cityhall and school icons / buttons.
        URL cityImage = getClass().getResource("/resources/city.jpg");
        Image cityimg = toolkit.getImage(cityImage);
        cityimg = cityimg.getScaledInstance(962, 600, Image.SCALE_SMOOTH);
        icon = new ImageIcon(cityimg);
        cityBG.setIcon(icon);
        cityBG.setBounds(0,0,962,600);
        cityAreaLP.add(cityBG, new Integer(-30000));

        URL cityHallImage = getClass().getResource("/resources/cityhall.png");
        Image cityHallIcon = toolkit.getImage(cityHallImage);
        cityHallIcon = cityHallIcon.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        icon = new ImageIcon(cityHallIcon);
        //cityHall1.setIcon(icon);
        this.cityHallViewIcon.setIcon(icon);
        this.cityHallViewIcon.setBounds(40,420, 250, 150);
        cityAreaLP.add(this.cityHallViewIcon, new Integer(0));

        URL schoolImage = getClass().getResource("/resources/school.png");
        Image schoolIcon = toolkit.getImage(schoolImage);
        schoolIcon = schoolIcon.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        icon = new ImageIcon(schoolIcon);
        //school1.setIcon(icon);
        this.schoolViewIcon.setIcon(icon);
        this.schoolViewIcon.setBounds(800,20, 250, 150);
        cityAreaLP.add(this.schoolViewIcon, new Integer(0));

        cityArea.add(cityAreaLP);

        frame.add(basePanel);
        frame.setVisible(true);
    }

    /**
     * Checks the JLabel People's positions to see if any of them overlap the CityHall or School icons.
     */
    public void checkPeoplePositions(){
        // Goes through all runtimeobjects(Person JLabels) and gets the person object
        // that the JLabel represents using the ID the JLabels gets when created which
        // lets the loop get that object then put it in the other locations.
        Person p;
        for(int i = 0; i < runtimeObjects.size(); i++) {
            p = link.idToPerson(Integer.parseInt(runtimeObjects.get(i).getName()));
            if (schoolViewIcon.getBounds().contains(runtimeObjects.get(i).getX(), runtimeObjects.get(i).getY())) {
                link.moveSchool(p);
                System.out.println("Moved " + p.getName() + " to school.");
            }else if (cityHallViewIcon.getBounds().contains(runtimeObjects.get(i).getX(), runtimeObjects.get(i).getY())) {
                if(p instanceof Police){
                    link.moveCityHall(p);
                } else{
                    link.moveToCity(p);
                    outputText.setText("Only Police in City Hall!!!");
                    runtimeObjects.get(i).setLocation(200, 250);
                }
            }else{
                link.moveToCity(p);
            }
        }
        peopleLocationTextUpdate();
    }

    /**
     * Updates the text on the left hand side of the window that displays City people data.
     */
    public void peopleLocationTextUpdate(){
        schoolText.setText("People in School:\n" + link.schoolPeopleNames());
        cityHallText.setText("People in City Hall:\n" + link.cityHallPeopleNames());
        cityText.setText("People in City:\n" + link.cityPeopleNames());
    }

    /**
     * A button on screen allows you to auto generate people so you don't have to type a lot to create people.
     */
    public void autoGeneratePeople(){
        Police[] policeAuto = {
                new Police("Greg", 34, 4556721, Police.Role.Patrol),
                new Police("Noah", 24, 9184603, Police.Role.Chief),
                new Police("Jeff", 42, 5528436, Police.Role.Captain),
                new Police("The Mountain", 30, 3369152, Police.Role.Chief)};

        Teacher[] teacherAuto = {
                new Teacher("Tracy", 34, 6293728, 5, "MA Elementry Education"),
                new Teacher("Jessica", 23, 4905616, 2, "BA Elementry Education"),
                new Teacher("Todd", 28, 1485922, 4, "BA Elementry Education")};

        Kid[] kidAuto = {
                new Kid("Timmy", 7, 9559277,"Twix"),
                new Kid("Jimmy", 12, 6621997,"Skittles"),
                new Kid("Derek", 8, 5623225,"M&M's"),
                new Kid("Eric", 9, 3745776,"Twizzlers")};

        for(int policeAdd = 0; policeAdd < policeAuto.length; policeAdd++){
            createPersonButtonAction(policeAuto[policeAdd]);
        }
        for(int teacherAdd = 0; teacherAdd < teacherAuto.length; teacherAdd++){
            createPersonButtonAction(teacherAuto[teacherAdd]);
        }
        for(int kidAdd = 0; kidAdd < kidAuto.length; kidAdd++){
            createPersonButtonAction(kidAuto[kidAdd]);
        }
    }

    /**
     * Initializes the create new people text boxes and buttons.
     */
    public void createPeopleTextBoxInit(){
        // Creates all the Person Creation text boxes and makes it
        // to where the text is deleted when chosen so you don't have to delete the text already in box
        // which allows more space for text input and no need for labels to take up space.

        newKidName.setText("Enter Name Here...");
        newKidName.setForeground(Color.gray);
        newKidName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newKidName.getText().equals("Enter Name Here...")) {
                    newKidName.setText("");
                    newKidName.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newKidName.getText().isEmpty()) {
                    newKidName.setForeground(Color.GRAY);
                    newKidName.setText("Enter Name Here...");
                }
            }
        });
        newKid.add(newKidName);

        newKidAge.setText("Enter Age Here...");
        newKidAge.setForeground(Color.gray);
        newKidAge.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newKidAge.getText().equals("Enter Age Here...")) {
                    newKidAge.setText("");
                    newKidAge.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newKidAge.getText().isEmpty()) {
                    newKidAge.setForeground(Color.GRAY);
                    newKidAge.setText("Enter Age Here...");
                }
            }
        });
        newKid.add(newKidAge);

        newKidPhone.setText("Enter phone number here...(no '-')");
        newKidPhone.setForeground(Color.gray);
        newKidPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newKidPhone.getText().equals("Enter phone number here...(no '-')")) {
                    newKidPhone.setText("");
                    newKidPhone.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newKidPhone.getText().isEmpty()) {
                    newKidPhone.setForeground(Color.GRAY);
                    newKidPhone.setText("Enter phone number here...(no '-')");
                }
            }
        });
        newKid.add(newKidPhone);

        newKidCandy.setText("Enter Kid's favorite Candy Here...");
        newKidCandy.setForeground(Color.gray);
        newKidCandy.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newKidCandy.getText().equals("Enter Kid's favorite Candy Here...")) {
                    newKidCandy.setText("");
                    newKidCandy.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newKidCandy.getText().isEmpty()) {
                    newKidCandy.setForeground(Color.GRAY);
                    newKidCandy.setText("Enter Kid's favorite Candy Here...");
                }
            }
        });
        newKid.add(newKidCandy);
        kidCreateButton.addActionListener(this);
        newKid.add(kidCreateButton);

        newTeacher.add(createNewTeacher);


        newTeacherName.setText("Enter Name Here...");
        newTeacherName.setForeground(Color.gray);
        newTeacherName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newTeacherName.getText().equals("Enter Name Here...")) {
                    newTeacherName.setText("");
                    newTeacherName.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newTeacherName.getText().isEmpty()) {
                    newTeacherName.setForeground(Color.GRAY);
                    newTeacherName.setText("Enter Name Here...");
                }
            }
        });
        newTeacher.add(newTeacherName);

        newTeacherAge.setText("Enter Age Here...");
        newTeacherAge.setForeground(Color.gray);
        newTeacherAge.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newTeacherAge.getText().equals("Enter Age Here...")) {
                    newTeacherAge.setText("");
                    newTeacherAge.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newTeacherAge.getText().isEmpty()) {
                    newTeacherAge.setForeground(Color.GRAY);
                    newTeacherAge.setText("Enter Age Here...");
                }
            }
        });
        newTeacher.add(newTeacherAge);

        newTeacherPhone.setText("Enter phone number here...(no '-')");
        newTeacherPhone.setForeground(Color.gray);
        newTeacherPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newTeacherPhone.getText().equals("Enter phone number here...(no '-')")) {
                    newTeacherPhone.setText("");
                    newTeacherPhone.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newTeacherPhone.getText().isEmpty()) {
                    newTeacherPhone.setForeground(Color.GRAY);
                    newTeacherPhone.setText("Enter phone number here...(no '-')");
                }
            }
        });
        newTeacher.add(newTeacherPhone);

        newTeacherGradeLevel.setText("Enter Teaching Grade Level Here...");
        newTeacherGradeLevel.setForeground(Color.gray);
        newTeacherGradeLevel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newTeacherGradeLevel.getText().equals("Enter Teaching Grade Level Here...")) {
                    newTeacherGradeLevel.setText("");
                    newTeacherGradeLevel.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newTeacherGradeLevel.getText().isEmpty()) {
                    newTeacherGradeLevel.setForeground(Color.GRAY);
                    newTeacherGradeLevel.setText("Enter Teaching Grade Level Here...");
                }
            }
        });
        newTeacher.add(newTeacherGradeLevel);

        newTeacherCert.setText("Enter Teacher's Certification Here...");
        newTeacherCert.setForeground(Color.gray);
        newTeacherCert.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newTeacherCert.getText().equals("Enter Teacher's Certification Here...")) {
                    newTeacherCert.setText("");
                    newTeacherCert.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newTeacherCert.getText().isEmpty()) {
                    newTeacherCert.setForeground(Color.GRAY);
                    newTeacherCert.setText("Enter Teacher's Certification Here...");
                }
            }
        });
        newTeacher.add(newTeacherCert);
        newTeacher.add(teacherCreateButton);
        teacherCreateButton.addActionListener(this);

        newPolice.add(createNewPolice);

        newPoliceName.setText("Enter Name Here...");
        newPoliceName.setForeground(Color.gray);
        newPoliceName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newPoliceName.getText().equals("Enter Name Here...")) {
                    newPoliceName.setText("");
                    newPoliceName.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newPoliceName.getText().isEmpty()) {
                    newPoliceName.setForeground(Color.GRAY);
                    newPoliceName.setText("Enter Name Here...");
                }
            }
        });
        newPolice.add(newPoliceName);

        newPoliceAge.setText("Enter Age Here...");
        newPoliceAge.setForeground(Color.gray);
        newPoliceAge.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newPoliceAge.getText().equals("Enter Age Here...")) {
                    newPoliceAge.setText("");
                    newPoliceAge.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newPoliceAge.getText().isEmpty()) {
                    newPoliceAge.setForeground(Color.GRAY);
                    newPoliceAge.setText("Enter Age Here...");
                }
            }
        });
        newPolice.add(newPoliceAge);

        newPolicePhone.setText("Enter phone number here...(no '-')");
        newPolicePhone.setForeground(Color.gray);
        newPolicePhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newPolicePhone.getText().equals("Enter phone number here...(no '-')")) {
                    newPolicePhone.setText("");
                    newPolicePhone.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (newPolicePhone.getText().isEmpty()) {
                    newPolicePhone.setForeground(Color.GRAY);
                    newPolicePhone.setText("Enter phone number here...(no '-')");
                }
            }
        });
        newPolice.add(newPolicePhone);

        newPoliceRole.addItem("Patrol");
        newPoliceRole.addItem("Sargent");
        newPoliceRole.addItem("Captain");
        newPoliceRole.addItem("Chief");
        newPolice.add(newPoliceRole);
        newPolice.add(policeCreateButton);
        policeCreateButton.addActionListener(this);
    }

    /**
     * Listens for button presses.
     * @param e is the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Does different things based on which button on screen is pressed.

        if(e.getSource() == kidCreateButton) {
            try {
                Kid retKid = new Kid(newKidName.getText(), Integer.parseInt(newKidAge.getText()), Integer.parseInt(newKidPhone.getText()), newKidCandy.getText());
                createPersonButtonAction(retKid);
            }catch(Exception x){
                outputText.setText("1 or more Input Fields Incorrect!");
            }

        }
        else if(e.getSource() == teacherCreateButton){
            try {
                Teacher retTeacher = new Teacher(newTeacherName.getText(), Integer.parseInt(newTeacherAge.getText()), Integer.parseInt(newTeacherPhone.getText()), Integer.parseInt(newTeacherGradeLevel.getText()), newTeacherCert.getText());
                createPersonButtonAction(retTeacher);
            }catch(Exception x) {
                outputText.setText("1 or more Input Fields Incorrect!");
            }

        }
        else if(e.getSource() == policeCreateButton){
            try {
                if (newPoliceRole.getSelectedItem() == "Patrol") {
                    Police retPolice = new Police(newPoliceName.getText(), Integer.parseInt(newPoliceAge.getText()), Integer.parseInt(newPolicePhone.getText()), Police.Role.Patrol);
                    createPersonButtonAction(retPolice);
                } else if (newPoliceRole.getSelectedItem() == "Sargent") {
                    Police retPolice = new Police(newPoliceName.getText(), Integer.parseInt(newPoliceAge.getText()), Integer.parseInt(newPolicePhone.getText()), Police.Role.Sargent);
                    createPersonButtonAction(retPolice);
                } else if (newPoliceRole.getSelectedItem() == "Captain") {
                    Police retPolice = new Police(newPoliceName.getText(), Integer.parseInt(newPoliceAge.getText()), Integer.parseInt(newPolicePhone.getText()), Police.Role.Captain);
                    createPersonButtonAction(retPolice);
                } else if (newPoliceRole.getSelectedItem() == "Chief") {
                    Police retPolice = new Police(newPoliceName.getText(), Integer.parseInt(newPoliceAge.getText()), Integer.parseInt(newPolicePhone.getText()), Police.Role.Chief);
                    createPersonButtonAction(retPolice);
                }
            }catch(Exception x){
                outputText.setText("1 or more Input Fields Incorrect!");
            }
        }
        else if(e.getSource() == listAllButton){
            //System.out.printf(link.mainCity.allCitizenNames());
            outputText.setText("All People:\n" + link.mainCity.allCitizenNames());
        }
        else if(e.getSource() == autoGen){
            autoGeneratePeople();
        }
        else if(e.getSource() == personDetails){
            outputText.setText(link.personToString(lastPersonIndex));
        }
    }

    /**
     * Creates a creates a JLabel based on a person and adds them to the CityGUILink (Controller).
     * @param p Person that is to be based off JLabel and added to city.
     */
    public void createPersonButtonAction(Person p){
        // Creates a person object and links the person object to a JLabel through the unique person ID.

        int ratio = 50;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon icon;
        URL kidURL = getClass().getResource("/resources/kid.png");
        Image kidimg = toolkit.getImage(kidURL);
        kidimg = kidimg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL teacherURL = getClass().getResource("/resources/teacher.png");
        Image teacherimg = toolkit.getImage(teacherURL);
        teacherimg = teacherimg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL policeURL = getClass().getResource("/resources/police.png");
        Image policeimg = toolkit.getImage(policeURL);
        policeimg = policeimg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        URL personURL = getClass().getResource("/resources/person.png");
        Image personimg = toolkit.getImage(personURL);
        personimg = personimg.getScaledInstance(ratio, ratio, Image.SCALE_SMOOTH);

        // Changes icon based on if a kid, teacher, or police object were made.
        if(p instanceof Kid) {
            icon = new ImageIcon(kidimg);
        }else if(p instanceof Teacher){
            icon = new ImageIcon(teacherimg);
        }else if(p instanceof Police){
            icon = new ImageIcon(policeimg);
        }else{
            // This one should never be chosen, just a backup in case of a bug.
            icon = new ImageIcon(personimg);
        }

        link.createPerson(p);
        JLabel t = new JLabel(p.getName() + "(" +  p.getpID() + ")");
        t.setBounds(450, 300,ratio + 50 + p.getName().length() * 6,ratio);
        t.setIcon(icon);
        t.setName("" + p.getpID());
        t.setBackground(Color.white);
        t.setForeground(Color.black);
        t.setFont(new Font(t.getFont().getName(), Font.BOLD, 14));
        runtimeObjects.add(t);
        cityAreaLP.add(runtimeObjects.get(runtimeObjectsIndex), new Integer(200));
        runtimeObjectsIndex++;

        cityAreaLP.revalidate();
        cityAreaLP.repaint();
        peopleLocationTextUpdate();
    }

    /**
     * Listens for when mouse is dragged.
     * @param e MouseEvent data.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        JLabel label = null;
        for (int i=0; i<runtimeObjects.size(); i++) {
            if (runtimeObjects.get(i).getBounds().contains(e.getPoint())) {
                label = runtimeObjects.get(i);
                lastPersonIndex = i;
            }
        }
        if (label != null) {
            if (diffDrag == null)
                diffDrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
            label.setBounds(e.getX() - diffDrag.x, e.getY()-diffDrag.y, label.getBounds().width, label.getBounds().height);
            //System.out.printf("moved label '" + label.getText() + "' to <%d, %d>\n", e.getX() - diffDrag.x, e.getY()-diffDrag.y);
        }
    }

    /**
     * Checks if mouse has moved with no buttons pressed.
     * @param e MouseEvent data.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        diffDrag = null;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * When mouse is released checks if People are inside CityHall or School.
     * @param e MouseEvent data.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        checkPeoplePositions();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

class CityGUITest{
    public static void main(String[] args) {
        CityGUI test = new CityGUI();
    }
}
