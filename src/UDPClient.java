import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UDPClient
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Skriv inn en nettadresse der du ønsker å finne email: ");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        //Protokoll for sending
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        //Protokoll for mottak
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("Fra server: " + modifiedSentence);




        int minInt=Integer.parseInt(modifiedSentence.trim());
        System.out.println(modifiedSentence);



        while (true) {
             if (minInt==0) {
                printWebside(sentence);
                break;
            }

            else if (minInt==1) {
                System.out.println("!!!No email address found on the page!!!’");
                break;

            } else if (minInt==2) {
                System.out.println("!!!Server couldn’t find the web page!!");
                break;
            }
        }
        clientSocket.close();
    }























    public static String printWebside(String input) throws IOException {
        System.out.println("heafioh");
        URL url = null;
        try {
            url = new URL(input);
        } catch (MalformedURLException e) {
            System.err.println("Ikke gyldig nettadresse ");
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
