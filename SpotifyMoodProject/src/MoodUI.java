
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URI;
import java.net.URL;

public class MoodUI implements ActionListener, ItemListener {
    JFrame frame;
    spotifyAPIFetcher apiObject;
    private JPanel mainPanel;
    private JPanel songInfoPanel;
    private JPanel infoPanel;
    private JPanel userInputPanel;
    private JLabel danceabilityLabel;
    private JLabel happyLabel;
    private JLabel energyLabel;
    private JLabel songNameLabel;
    private JLabel artistLabel;
    private JLabel songImage;
    private JLabel toleranceLabel;
    private JList songList;
    private JButton songPlayButton;
    private JButton filterButton;
    private JCheckBox danceCheckBox;
    private JCheckBox happyCheckBox;
    private JCheckBox energyCheckBox;
    private JSlider toleranceSlider;
    private JSlider danceabilitySlider;
    private JSlider happySlider;
    private JSlider energySlider;

    // List model so artist name and song name can be listed.
    private DefaultListModel model = new DefaultListModel();

    /**
     * Initialize spotify api setup and output ui screen.
     */
    public MoodUI() {
        // Tries to get token from Spotify api and only initializes if token is received.
        apiObject = new spotifyAPIFetcher();
        if (apiObject.getToken()) {
            init();
        } else {
            System.out.println("CONNECTION FAILURE!!!");
        }
    }

    /**
     * Initialize frame and everything needed for program.
     */
    private void init() {
        // Init frame.
        frame = new JFrame("Moodify");
        frame.setSize(new Dimension(800, 600));
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Sets up panels and label colors.
        Color bgColor = new Color(94,186,126);
        Color fgColor = Color.black;

        infoPanel.setBackground(Color.BLACK);
        infoPanel.setForeground(fgColor);
        userInputPanel.setBackground(bgColor);
        userInputPanel.setForeground(fgColor);
        songInfoPanel.setBackground(Color.BLACK);
        songInfoPanel.setForeground(Color.white);
        infoPanel.setPreferredSize(new Dimension(400, 300));
        userInputPanel.setPreferredSize(new Dimension(400, 300));
        songInfoPanel.setPreferredSize(new Dimension(400, 600));

        songNameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        artistLabel.setFont(new Font(Font.DIALOG, Font.ITALIC, 18));
        songNameLabel.setForeground(Color.white);
        artistLabel.setForeground(Color.white);

        toleranceLabel.setPreferredSize(new Dimension(80, 15));
        danceabilitySlider.setPreferredSize(new Dimension(25, 150));
        happySlider.setPreferredSize(new Dimension(25, 150));
        energySlider.setPreferredSize(new Dimension(25, 150));

        // Add listeners to silders and buttons:

        toleranceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                toleranceLabel.setText("Tolerance: " + (int) (toleranceSlider.getValue() / 5));
                apiObject.tolerance = (double) toleranceSlider.getValue() / 500;
            }
        });

        danceabilitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                danceabilityLabel.setText("Danceability: " + danceabilitySlider.getValue());
                apiObject.dance = (double) danceabilitySlider.getValue() / 100;
            }
        });

        happySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                happyLabel.setText("Happy: " + happySlider.getValue());
                apiObject.happy = (double) happySlider.getValue() / 100;
            }
        });

        energySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                energyLabel.setText("Energy: " + energySlider.getValue());
                apiObject.energy = (double) energySlider.getValue() / 100;
            }
        });

        // Initialize list to loading while top tracks are loaded.
        String[] baseText = {"Loading..."};
        songList.setListData(baseText);

        // Sets up song list and scrollpane and adds a listener.
        songList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        JScrollPane listScroll = new JScrollPane(songList);
        listScroll.setPreferredSize(new Dimension(380, 230));
        infoPanel.add(listScroll);
        songList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    //System.out.printf("Selected song: %s\n", apiObject.allSongs.get(songList.getSelectedIndex()).getSongName());
                    //apiObject.getTrackData(apiObject.songIDs.get(songList.getSelectedIndex() + 1));
                    songInfoUpdate();

                }
            }
        });

        // Add listeners to everything.
        filterButton.addActionListener(this);
        songPlayButton.addActionListener(this);

        danceCheckBox.addItemListener(this);
        happyCheckBox.addItemListener(this);
        energyCheckBox.addItemListener(this);

        danceCheckBox.setSelected(false);
        happyCheckBox.setSelected(false);
        energyCheckBox.setSelected(false);

        frame.add(mainPanel);

        // Get top 50 US tracks and loads them in first, then starts loading all other songs in background.
        apiObject.getTopTracks();
        for (int i = 0; i < apiObject.allSongs.size(); i++) {
            apiObject.displaySongs.add(apiObject.allSongs.get(i));
            model.addElement(apiObject.allSongs.get(i).getArtist() + " - " + apiObject.allSongs.get(i).getSongName());
        }
        songList.setModel(model);
        songList.setSelectedIndex(0);
        songInfoUpdate();

        apiObject.getTrackFeatures();

        apiObject.getPlaylistIDs();
        apiObject.playlistIDToSongsThreaded();
    }

    /**
     * Checks which button is pressed and does what the button is supposed to do.
     * @param e which button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Open selected song on spotify.
        if (e.getSource() == songPlayButton) {
            System.out.println("Trying to open song...");
            // Goes to link
            int songIndex = songList.getSelectedIndex();
            String idToGoTo = apiObject.displaySongs.get(songIndex).getID();
            System.out.println(idToGoTo);
            String link = "https://open.spotify.com/track/" + idToGoTo;
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(link));
                }
            } catch (Exception ex) {
                ;
            }
        // Filters the songs based on user input.
        } else if (e.getSource() == filterButton) {
            apiObject.checkAudioFeatures();
            model.removeAllElements();
            for (int i = 0; i < apiObject.displaySongs.size(); i++) {
                model.addElement(apiObject.displaySongs.get(i).getArtist() + " - " + apiObject.displaySongs.get(i).getSongName());
            }
            System.out.println("Amount of songs: " + apiObject.displaySongs.size());
            System.out.println("Refreshed song list");
        }
    }

    /**
     * Checks check boxes for what filters to apply.
     * @param e which check box is changed.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == danceCheckBox) {
            if (danceCheckBox.isSelected()) {
                apiObject.danceCheck = true;
            } else {
                apiObject.danceCheck = false;
            }

        } else if (e.getSource() == happyCheckBox) {
            if (happyCheckBox.isSelected()) {
                apiObject.happyCheck = true;
            } else {
                apiObject.happyCheck = false;
            }

        } else if (e.getSource() == energyCheckBox) {
            if (energyCheckBox.isSelected()) {
                apiObject.energyCheck = true;
            } else {
                apiObject.energyCheck = false;
            }

        }
    }

    /**
     * Updates picture and text for song selected.
     */
    public void songInfoUpdate() {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            URL imgUrl = new URL(apiObject.displaySongs.get(songList.getSelectedIndex()).getCoverartLink());
            Image img = toolkit.getImage(imgUrl);
            img = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);

            songImage.setIcon(new ImageIcon(img));

            songNameLabel.setText(apiObject.displaySongs.get(songList.getSelectedIndex()).getSongName());
            artistLabel.setText(apiObject.displaySongs.get(songList.getSelectedIndex()).getArtist());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        MoodUI run = new MoodUI();
    }

}
