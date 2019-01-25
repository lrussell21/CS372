
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class getSongIDs implements Runnable {

    private spotifyAPIFetcher s;
    private String token;
    private String playlistID;
    private songs newSong;

    /**
     * Initialize getSongsIDs object.
     * @param s Spotify api object.
     * @param token Spotify api token
     * @param list Spotify Playlist ID
     */
    public getSongIDs(spotifyAPIFetcher s, String token, String list){
        this.s = s;
        this.token = token;
        playlistID = list;
    }

    /**
     * For use with thread. Gets Spotify playlist ID and gets all the songs in the playlist.
     */
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
                    //System.out.println(output);
                    fullOuputString += output + "\n";
                }
                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Parse Json received from Spotify
            parseData(fullOuputString);
    }

    /**
     * Parse Json with songs from Spotify.
     * @param data Json text.
     */
    public void parseData(String data){

        JsonParser jsonparser = new JsonParser();
        JsonElement jsonTree = jsonparser.parse(data);
        JsonObject jsonObject = null;
        if(jsonTree.isJsonObject()){
            jsonObject = jsonTree.getAsJsonObject();
        }

        // Split all track info into an array
        JsonElement mainJsonObject = jsonObject.get("items");
        JsonArray songListArray = mainJsonObject.getAsJsonArray();


        for(int i = 0; i < songListArray.size(); i++) {
            try {
                JsonElement individualSong = songListArray.get(i);
                JsonObject songObject = individualSong.getAsJsonObject();

                // Separate track info
                JsonObject trackObj = songObject.get("track").getAsJsonObject();

                // Artist data
                JsonArray artistData = trackObj.get("artists").getAsJsonArray();
                JsonObject artistObj = artistData.get(0).getAsJsonObject();

                // Cover art album link
                JsonObject coverArtObj = trackObj.get("album").getAsJsonObject();
                JsonArray coverArtArray = coverArtObj.get("images").getAsJsonArray();
                JsonObject coverArtLinkObj = coverArtArray.get(0).getAsJsonObject();

                // Create song object with all data from parsed Json.
                newSong = new songs(s, trackObj.get("id").getAsString(), artistObj.get("name").getAsString(), trackObj.get("name").getAsString(), coverArtLinkObj.get("url").getAsString());
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            //System.out.println("Track: " + trackObj.get("name"));
            //System.out.println("Track: " + trackObj.get("id"));
            //System.out.println("Track artist: " + artistObj.get("name"));
            //System.out.println("CoverartLink: " + coverArtLinkObj.get("url"));
            //System.out.println("--------------------------");

        }
    }
}
