import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class songs {
    spotifyAPIFetcher s;
    private double danceability, happy, energy;
    private String artist, songName, ID, token, coverartLink;

    public songs(spotifyAPIFetcher s,String ID, String token){
        this.s = s;
        this.ID = ID;
        this.token = token;
        addSong();
        s.allSongs.add(this);
    }

    private void addSong(){
        String fullOuputString = "";
        try {
            URL url = new URL("https://api.spotify.com/v1/tracks/" + ID);
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
            //System.out.println("-------------------------------------------\n\n\n\n\n");
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


        String[] outputNameparts = fullOuputString.split("\"name\" : \"");
        for(String p: outputNameparts) {
            for(int i = 1; i < p.length(); i++){
                if(p.charAt(0) != '{' && p.charAt(i) == ','){
                    songName = p.substring(0, i - 1);
                    break;
                }
            }
        }

        boolean run = true;
        String[] outputArtistNameparts = fullOuputString.split("\"name\" : \"");
        for(String p: outputArtistNameparts) {
            for(int i = 1; i < p.length(); i++){
                if(p.charAt(0) != '{' && p.charAt(i) == ','){
                    artist = p.substring(0, i - 1);
                    run = false;
                    break;
                }
            }
            if(!run){
                break;
            }
        }


        String[] outPictures = fullOuputString.split("\"height\" : 300,");
        for(String p: outPictures) {
            if(p.charAt(0) != '{') {
                coverartLink = p.substring(16, 80);
                break;
            }
        }

    }

    public String getArtist() {
        return artist;
    }

    public String getSongName() {
        return songName;
    }

    public String getID() {
        return ID;
    }

    public String getCoverartLink() {
        return coverartLink;
    }

    public String toString(){
        return artist + " - " + songName + " : " + ID;
    }


}
