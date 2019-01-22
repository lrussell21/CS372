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
    public ArrayList<String> songIDs = new ArrayList<>();
    public ArrayList<String> songNames = new ArrayList<>();
    public ArrayList<String> songImageLinks = new ArrayList<>();


    private static final String client_id = "10187fa73dd54eb3833f69da476e6861";
    private static final String secret_id = "72bbadeb5355484db26245c552429326";
    private static final String redirectUri = "http://localhost:8888/callback/";

    private static final String refreshToken = "AQAvn4j17UIArnNmVCMbMvBsy-qprZFnS9EGflsjJ9a0-cryLjQ9DXWisv4WDvLp8HX-_zJEAcZDltGwZpHCKh_StjhPZTBXoBZmYHC790lxDgFjqhO8EGuuGYUhYw9NFOEcVQ";
    private static String token;
    double dance, happy, energy;


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


    public void getTracks(){
        String fullOuputString = "";
        try {
            URL url = new URL("https://api.spotify.com/v1/playlists/37i9dQZEVXbLRQDuF5jeBp/tracks");
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
            System.out.println("Output from Server ....");
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


        String[] outputSongIDparts = fullOuputString.split("\"uri\" : \"spotify:track:");
        for(String p: outputSongIDparts) {
            songIDs.add(p.substring(0, 22));
        }

        String[] outputNameparts = fullOuputString.split("\"is_local\" : false,\n" + "      \"name\" : \"");
        for(String p: outputNameparts) {
            for(int i = 1; i < p.length(); i++){
                if(p.charAt(i) == ',' && i < 90){
                    songNames.add(p.substring(0, i - 1));
                    i = p.length() + 1;
                }
            }

        }




        String[] outPictures = fullOuputString.split("\"height\" : 300,");
        for(String p: outPictures) {
            songImageLinks.add(p.substring(20, 84));
        }




    }


    public void setToken(String serverToken){
        token = serverToken.substring(17, 100);
        System.out.println("TOKEN: " + token);
    }


    public void getTrackData(String ID){
        String fullOuputString = "";
        try {
            URL url = new URL("https://api.spotify.com/v1/audio-features/" + ID);
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
            System.out.println("Output from Server ....");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                fullOuputString += output + "\n";
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
