import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;

public class MoodUI implements ActionListener {
    JFrame frame;
    spotifyAPIFetcher apiObject;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel userInputPanel;
    private JSlider danceabilitySlider;
    private JSlider happySlider;
    private JSlider energySlider;
    private JLabel danceabilityLabel;
    private JLabel happyLabel;
    private JLabel energyLabel;
    private JList songList;
    private JButton songPlayButton;
    private JButton searchButton;
    private JLabel songImage;
    private double danceValueSend, happyValueSend, energyValueSend;

    public MoodUI(){
        apiObject = new spotifyAPIFetcher();
        if(apiObject.getToken()) {
            init();
        }else{
            System.out.println("CONNECTION FAILURE!!!");
        }
    }

    private void init(){
        frame = new JFrame("Spotify Mood Project");
        frame.setSize(new Dimension(800, 400));
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Color bgColor = Color.LIGHT_GRAY;
        Color fgColor = Color.black;

        infoPanel.setBackground(bgColor);
        infoPanel.setForeground(fgColor);
        userInputPanel.setBackground(bgColor);
        userInputPanel.setForeground(fgColor);
        infoPanel.setPreferredSize(new Dimension(400, 400));
        userInputPanel.setPreferredSize(new Dimension(400, 400));

        danceabilityLabel.setForeground(fgColor);
        happyLabel.setForeground(fgColor);
        energyLabel.setForeground(fgColor);

        danceabilitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                danceabilityLabel.setText("Danceability: " + danceabilitySlider.getValue());
                danceValueSend = (double)danceabilitySlider.getValue()/100;
            }
        });

        happySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                happyLabel.setText("Happy: " + happySlider.getValue());
                happyValueSend = (double)happySlider.getValue()/100;
            }
        });

        energySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                energyLabel.setText("Energy: " + energySlider.getValue());
                energyValueSend = (double) energySlider.getValue()/100;
            }
        });


        String[] baseText = {"No Songs Yet..."};
        songList.setListData(baseText);

        songList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        JScrollPane listScroll = new JScrollPane(songList);
        listScroll.setPreferredSize(new Dimension(380, 230));
        infoPanel.add(listScroll);

        songList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    System.out.printf("Selected song: %s\n", apiObject.songNames.get(songList.getSelectedIndex()));
                    apiObject.getTrackData(apiObject.songIDs.get(songList.getSelectedIndex() + 1));

                    try{
                        Toolkit toolkit = Toolkit.getDefaultToolkit();

                        URL imgUrl = new URL(apiObject.songImageLinks.get(songList.getSelectedIndex() + 1));
                        System.out.println(apiObject.songImageLinks.get(songList.getSelectedIndex() + 1));
                        Image img = toolkit.getImage(imgUrl);
                        img = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);

                        songImage.setIcon(new ImageIcon(img));

                    }catch (Exception ex){

                    }



                }
            }
        });

        searchButton.addActionListener(this);
        songPlayButton.addActionListener(this);

        frame.add(mainPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == songPlayButton){
            System.out.println("Trying to open song...");
            // Goes to link
            int songIndex = songList.getSelectedIndex();
            String idToGoTo = apiObject.songIDs.get(songIndex + 1);
            System.out.println(idToGoTo);
            String link = "https://open.spotify.com/track/" + idToGoTo;
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(link));
                }
            }catch (Exception ex){;}

        }else if (e.getSource() == searchButton){

            apiObject.getTracks();
            songList.setListData(apiObject.songNames.toArray());
            System.out.println("Refreshed song list...");

        }
    }

    public static void main(String[] args) {
        MoodUI m = new MoodUI();
    }
}
