import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funksjoner {

    public static String seEtterWebside(String input) throws IOException,FileNotFoundException {

        URL url = null;
        try {
            url = new URL(input);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("2");
            return "2";
        }

        URLConnection con = url.openConnection();
        InputStream is;

        is = con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        // read each line and write to System.out
        String tekst = "";
        while ((line = br.readLine()) != null) {
            tekst += line;
        }
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(tekst);
        Set<String> emails = new HashSet<String>();
        while(matcher.find()) {
            emails.add(matcher.group());
        }
        if(emails.isEmpty()){
            System.out.println("1");
            return "1";
        }
        System.out.println("0");
        return "0";



//http://student.cs.hioa.no/~s333770/InnovationCampFerdig/InnovationCampWebsite/


    }
    public static String printWebside(String input) throws IOException {
        System.out.println("heafioh");
        URL url = null;
        try {
            url = new URL(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        // read each line and write to System.out
        String tekst = "";
        while ((line = br.readLine()) != null) {
            tekst += line;
        }
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(tekst);
        Set<String> emails = new HashSet<String>();
        while(matcher.find()) {
            emails.add(matcher.group());
        }
        System.out.println(emails.toString());
        return emails.toString();
//http://student.cs.hioa.no/~s333770/InnovationCampFerdig/InnovationCampWebsite/


    }


}
