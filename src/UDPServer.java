import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class UDPServer
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876); //Definerer port som mottar data
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            System.out.println("Klar til å motta data");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String motattData = new String( receivePacket.getData());
            System.out.println("Motatt: " + motattData);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            //Så behandler vi dataen som vi har motatt
             String s = seEtterWebside(motattData);
            System.out.println(s);
             sendData=s.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }

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

            try {
                is = con.getInputStream();
            }
            catch(FileNotFoundException e){
                System.err.println("Finner ikke nettadressen");
                return "2";
            }

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
        public  String printWebside(String input) throws IOException {
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

