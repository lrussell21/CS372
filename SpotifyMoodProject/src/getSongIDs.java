import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class getSongIDs implements Runnable {

    spotifyAPIFetcher s;
    String token;
    String playlistID;
    songs newSong;
    public getSongIDs(spotifyAPIFetcher s, String token, String list){
        this.s = s;
        this.token = token;
        playlistID = list;
    }

    public void run(){
            String fullOuputString = "";
            try {
                URL url = new URL("https://api.spotify.com/v1/playlists/" + playlistID + "/tracks");
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
                    System.out.println(output);
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
                if(p.charAt(0) != '{') {
                    try{
                        Thread.sleep(100);
                    }catch (Exception ex){ }
                    // TODO:
                    // Cant use newSong because we spam server too much
                    // so we need to get all info from this output instead of track by track.
                    //newSong = new songs(s, p.substring(0, 22), token);
                }
            }
/*
            String[] outputNameparts = fullOuputString.split("\"is_local\" : false,\n" + "      \"name\" : \"");
            for(String p: outputNameparts) {
                for(int i = 1; i < p.length(); i++){
                    if(p.charAt(i) == ',' && i < 90){
                        s.songNames.add(p.substring(0, i - 1));
                        i = p.length() + 1;
                    }
                }
            }
            */
            System.out.println("Finished");
    }


}
