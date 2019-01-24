/**
 * This class models a webpage reader and will store URLs and
 * emails as the Spider cralws through the web. This class
 * also implements runnable.
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebpageReader implements Runnable {
    private JLabel _label;
    private URL _url;
    private int sitecount = 0;
    private static HashMap <String, Boolean> URLStorage = new HashMap<>();
    private static Set<String> EmailStorage = new HashSet<>();
    private String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";        // This regex was found on http://blog.houen.net/java-get-url-from-string/
    private String URlregex = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?(\\/[a-z0-9])*(\\/?|(\\?[a-z0-9]=[a-z0-9](&[a-z0-9]=[a-z0-9]*)?))$";
    private String emailRegex = "\"mailto:(.*?)\"";
    private ArrayList<String> URLList = new ArrayList<>();

    /**
     * This class creates a new WebpageReader given a
     * String url
     * @param url The url that will be crawled
     */
    public WebpageReader(String url) {
        try {
            _url = new URL(url);
        }
        catch(Exception ex) {
            _url = null;
        }

    }

    /**
     * Get the URLs
     * @return url list
     */
    public static HashMap <String, Boolean> getUrls() {             // Static so it is shared between each threat
        return URLStorage;
    }

    /**
     * Print out all the emails that were gathered during the search
     */
    public static void printEmails() {
        List<String> EmailList = new ArrayList<String>(EmailStorage);
        System.out.println("List of emails gathered during search: ");
        for(int i = 0; i < EmailList.size(); i++) {
            System.out.println(EmailList.get(i));
        }
    }

    /**
     * Create the thread for the new webpage reader,
     * search through and add text to the appropriate places
     * based off the URL regex and the email regex.
     */
    public void run() {
        if(_url == null) return; {


                String line;
                try {


                    System.out.printf("reading from %s", _url.toString());
                    BufferedReader rdr = new BufferedReader(new InputStreamReader(_url.openStream()));

                    while ((line = rdr.readLine()) != null) {

                        Pattern website = Pattern.compile(regex);
                        Matcher matcher = website.matcher(line);
                        while (matcher.find()) {
                            //  System.out.println(matcher.group(0));
                            URLStorage.put(matcher.group(0), false);
                        }
                        Pattern email = Pattern.compile(emailRegex);
                        Matcher match = email.matcher(line);
                        while (match.find()) {
                            EmailStorage.add(match.group(1));
                        }

                    }


                } catch (Exception ex) {
                    System.out.printf("Oops: %s", ex.getMessage());
                }
            }
        }


    public static void main(String[] args) {
        ThreadTest a = new ThreadTest();
        WebpageReader.printEmails();

        System.out.println("Done");
    }
}
