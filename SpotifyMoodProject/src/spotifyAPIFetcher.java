import jdk.nashorn.internal.runtime.ECMAException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class spotifyAPIFetcher {
    public ArrayList<songs> allSongs = new ArrayList<>();
    public ArrayList<String> songIDs = new ArrayList<>();
    public ArrayList<songs> displaySongs = new ArrayList<>();
    //public ArrayList<String> songNames = new ArrayList<>();
    //public ArrayList<String> songImageLinks = new ArrayList<>();

    String[] categories = {"pop","mood","edm_dance","decades","hiphop","chill","workout","party","focus","sleep","rock","dinner","jazz","rnb","romance","indie_alt","gaming","soul","classical"};
    public ArrayList<String> playlistIDs = new ArrayList<>();

    private static final String client_id = "10187fa73dd54eb3833f69da476e6861";
    private static final String secret_id = "72bbadeb5355484db26245c552429326";
    private static final String redirectUri = "http://localhost:8888/callback/";

    private static final String refreshToken = "AQAvn4j17UIArnNmVCMbMvBsy-qprZFnS9EGflsjJ9a0-cryLjQ9DXWisv4WDvLp8HX-_zJEAcZDltGwZpHCKh_StjhPZTBXoBZmYHC790lxDgFjqhO8EGuuGYUhYw9NFOEcVQ";

    private static String token;
    public double dance, happy, energy;


    public spotifyAPIFetcher(){

    }

    public void sendValues(double dance, double happy, double energy){
        this.dance = dance;
        this.happy = happy;
        this.energy = energy;
    }

    public boolean getToken(){

        String spoturl = "https://accounts.spotify.com/api/token";
        String urlParameters = "grant_type=client_credentials";
        String tokenStringReceive = "";
        try {

            URL url = new URL(spoturl + "?" + urlParameters);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((client_id + ":" + secret_id).getBytes()));


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }


            try {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                System.out.println("Output from Server ....");
                while ((output = br.readLine()) != null) {
                    tokenStringReceive += output;
                    //System.out.println(output);
                }
                setToken(tokenStringReceive);
            }catch (Exception e){
                System.out.printf(e.getMessage());
            }
            conn.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    public void setToken(String serverToken){
        token = serverToken.substring(17, 100);
        System.out.println("TOKEN: " + token);

    }

    public void getTopTracks(){
        getSongIDs temp = new getSongIDs(this, token,"37i9dQZEVXbLRQDuF5jeBp");
        temp.run();
    }

    public void getCategoryIDs(){
        for(int cats = 0; cats < categories.length - 11; cats++) {

            String fullOuputString = "";
            try {
                URL url = new URL("https://api.spotify.com/v1/browse/categories/" + categories[cats] + "/playlists");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + token);

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output;
                //System.out.println("Output from Server ....");
                while ((output = br.readLine()) != null) {
                    //System.out.println(output);
                    fullOuputString += output + "\n";
                }
                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            String[] categoryIdParts = fullOuputString.split("\"id\" : \"");
            for(String p: categoryIdParts) {
                for(int i = 1; i < p.length(); i++){
                    if(p.charAt(0) != 's' && p.charAt(i) == ',' && i < 30){
                        //System.out.println("Added: " + p.substring(0, i - 1));
                        playlistIDs.add(p.substring(0, i - 1));
                        break;
                    }
                }
            }

        }
    }


    public void playlistIDToSongsThreaded(){
        Thread s = new Thread(this::playlistIDToSongss);
        s.start();
    }

    public void playlistIDToSongss(){
        getSongIDs testThreadSong;
        for(int i = 0; i < 50; i++){
            try {
                Thread.sleep(100);
                // TODO SWITCH THIS BACK
                testThreadSong = new getSongIDs(this, token, playlistIDs.get(i));
                Thread s = new Thread(testThreadSong);
                //System.out.println("Started thread: " + i);
                s.start();
            }catch (Exception ex){}
        }
        try{
            Thread.sleep(30000);
        }catch (Exception ex){}
        System.out.println("NUMBER OF SONGS: " + allSongs.size());
        multiThreadAudioFeatures();
    }

    public void multiThreadAudioFeatures(){
        Thread mtaf = new Thread(this::getAudioFeatures);
        mtaf.start();
    }

    private void getAudioFeatures(){
        for(int i = 0; i < allSongs.size(); i++) {
            if((double)(i/allSongs.size()) * 100 % 10 == 0){
                System.out.println("Loading: " + i);
            }
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
            }
            try {
                allSongs.get(i).parseTrackFeatures();
            }catch(Exception ex){System.out.printf("ERROR!:", ex.getMessage());}
        }
        System.out.println("All song data retrieved!");
    }

    public void checkAudioFeatures(){
        displaySongs.clear();
        for(songs song: allSongs){
            if(this.dance  <= song.getDanceability() + 0.1 && this.dance >= song.getDanceability() -0.1){
                if(this.happy <= song.getHappy() + 0.1 && this.happy >= song.getDanceability() -0.1) {
                    if(this.energy <= song.getEnergy() + 0.1 && this.energy >= song.getEnergy() -0.1) {
                        displaySongs.add(song);
                    }
                }
            }
        }
    }

}
