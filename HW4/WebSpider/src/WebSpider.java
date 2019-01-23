import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class WebSpider {
    HashMap<String,Boolean> link = new HashMap<>();
    Set<String> email = new HashSet<>();

    public WebSpider(){
        // My high school's website has a lot of mailto emails...
        String startAddress = "https://meadhs.mead354.org/ourpages/administration/";
        link.put(startAddress, false);

        // Loops through hashmap until 100 links have been followed.
        int j = 0;
        while(true){
            if(j > 100){
                break;
            }else{
                for(Map.Entry<String,Boolean> entry: link.entrySet()){
                    if(!entry.getValue()){
                        //System.out.println(entry.getKey());
                        search(entry.getKey());
                        break;
                    }
                }
                System.out.println(j);
                j++;
            }
        }

        // Lists emails at end.
        listEmails();
    }

    public void search(String address){

        try {
            // I tried a ton of regex for link but none of them matched...
            String regexURL = "<a\\s*?href=\"(http:.*?)\"";
            String regexEmail = "mailto:(.*?)";

            URL url = new URL(address);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            int i = 1000;
            line = rdr.readLine();
            while (line != null && i > 0) {

                // Gets links
                String[] linkparts = line.split("href=");
                for(String p: linkparts) {
                    for(int j = 1; j < p.length(); j++){
                        // Makes sure http is at beginning and its not .pdf at end.
                        if (p.charAt(j) == 34 && p.charAt(1) == 'h' && p.charAt(4) == 'p' && p.charAt(j - 1) != 'f') {
                            link.put(p.substring(1, j),false);
                            //System.out.printf("Added website: %s\n", p.substring(1, j));
                            break;
                        }
                    }
                }

                // Gets emails
                String[] emailparts = line.split("mailto");
                boolean run = true;
                for(String p: emailparts) {
                    for(int j = 1; j < p.length(); j++){
                        // Checks for " and makes sure its at a mailto:
                        if(p.charAt(0) == ':' && p.charAt(j) == 34){
                            email.add(p.substring(1, j));
                            //System.out.printf("Added email: %s\n", p.substring(1, j));
                            run = false;
                            break;
                        }
                    }
                    if(!run){
                        break;
                    }
                }

                // I tried to get this to work for hours but could never get it to work properly.
                /*
                Pattern webLink = Pattern.compile(regexURL, Pattern.CASE_INSENSITIVE);
                Matcher webmatcher = webLink.matcher(line);
                if (webmatcher.matches()) {
                    System.out.printf("TEST WEBSITE: %s\n", webmatcher.matches());
                    //System.out.printf("GOT WEBSITE: %s\n", webmatcher.group(0));
                    //System.out.printf("GOT WEBSITE: %s\n", webmatcher.group(1));
                }
                while (webmatcher.find()) {
                    //System.out.printf("Website: %s\n", webmatcher.group(0));
                    //links.add(webmatcher.group(1));
                }


                Pattern email = Pattern.compile(regexEmail);
                Matcher matcher = email.matcher(line);
                //System.out.printf("EMAIL TEST: %s\n", matcher.matches());
                if (matcher.matches()) {
                    System.out.printf("TEST EMAIL: %s\n", matcher.matches());
                }
                while (matcher.find()) {
                    // System.out.printf("email: %s\n", matcher.group(1));
                    //emails.add(matcher.group(1));
                }
                */
                line = rdr.readLine();
                i--;
            }
            System.out.println("DONE");
        }
        catch (Exception ex) {
            System.out.printf("Oops: %s", ex.getMessage());
        }


    }

    public void listEmails(){
        System.out.printf("\nOUTPUTING EMAILS:\n");
        System.out.println(email);
    }

    public static void main(String[] args) {
        WebSpider spiderMan = new WebSpider();
    }

}
