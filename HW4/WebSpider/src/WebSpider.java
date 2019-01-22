import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.*;

public class WebSpider {
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();

    public WebSpider(){
        String startAddress = "https://www.foxnews.com/";
        links.add(startAddress);
        boolean run = true;


        int j = 0;
        while(run){
            if(j > 100){
                run = false;
            }else{
                search(links.get(j));
                j++;
            }
        }

        //listLinks();
        //listEmails();

    }

    public void search(String address){

        try {
            URL url = new URL(address);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            int i=50;
            while ((line = rdr.readLine()) != null && i > 0) {


                // Get link if there is one.
                String line2 = line;
                Pattern email = Pattern.compile("\"mailto:(.*?)\"");
                Matcher matcher = email.matcher(line2);
                if (matcher.find()) {
                    //System.out.printf("email: %s\n", matcher.group(0));
                    //emails.add(matcher.group(1));
                }

                Pattern webLink = Pattern.compile("<a\\s*?href=\"(http:.*?)\"\n");
                Matcher webmatcher = webLink.matcher(line);
                if (webmatcher.find()) {
                    //System.out.printf("Website: %s\n", webmatcher.group(0));
                    //links.add(webmatcher.group(1));
                }

                /*
                String[] linkparts = line.split("href=");
                for(String p: linkparts) {
                    for(int j = 1; j < p.length(); j++){
                        if(p.charAt(j) == 34){
                            if (p.charAt(1) == 'h' && p.charAt(4) == 'p') {
                                links.add(p.substring(1, j));
                                System.out.printf("Added website: %s\n", p.substring(1, j));
                                break;
                            }
                        }
                    }

                }
*/
                i--;
            }
        }
        catch (Exception ex) {
            System.out.printf("Oops: %s", ex.getMessage());
        }


    }

    public void listLinks(){
        for(String p: links){
            System.out.println(p);
        }
    }

    public void listEmails(){
        System.out.printf("\n\n\n\n\n OUTPUTING EMAILS:\n");
        for(String p: emails){
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        WebSpider test = new WebSpider();
    }

}
