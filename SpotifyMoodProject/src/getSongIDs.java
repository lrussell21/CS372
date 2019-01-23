

import com.google.gson.*;
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
                    //System.out.println(output);
                    fullOuputString += output + "\n";
                }
                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            parseData(fullOuputString);
    }



    public void parseData(String data){

        JsonParser jsonparser = new JsonParser();
        JsonElement jsonTree = jsonparser.parse(data);
        JsonObject jsonObject = null;
        if(jsonTree.isJsonObject()){
            jsonObject = jsonTree.getAsJsonObject();
        }

        JsonElement mainJsonObject = jsonObject.get("items");
        JsonArray songListArray = mainJsonObject.getAsJsonArray();


        for(int i = 0; i < songListArray.size(); i++) {
            JsonElement individualSong = songListArray.get(i);
            JsonObject songObject = individualSong.getAsJsonObject();

            // track info
            JsonObject trackObj = songObject.get("track").getAsJsonObject();

            //System.out.println("Track: " + trackObj.get("name"));
            //System.out.println("Track: " + trackObj.get("id"));

            newSong = new songs(s, trackObj.get("id").getAsString(), "NOTSET", trackObj.get("name").getAsString(), "NOTSET", token);

            //JsonObject artistObj1 = songObject.get("artists").getAsJsonObject();

            //System.out.println("Track artist: " + artistObj1.get("name"));

            //System.out.println("--------------------------");

        }
        //artist info
        /*
        JsonElement artist = trackObj.get("artists");
        JsonObject artistObj = artist.getAsJsonObject();
        System.out.println(artistObj.get("name"));
        System.out.println(artistObj.get("id"));


        String result = trackObj.getAsString();

        System.out.println(result);
*/
        //JsonElement test2 = j.get("id");
        //JsonElement test = jsonObject.get("artists");
        //System.out.println(songId.getAsString());



    }


}
