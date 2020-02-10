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
        BufferedReader innFraBruker = new BufferedReader(new InputStreamReader(System.in));
        //Leser inn data fra bruker
        DatagramSocket clientSocket = new DatagramSocket();
        // KLientsokket,
        InetAddress IPAddresse = InetAddress.getByName("localhost");
        //Får den lokale Ip-adressen
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Skriv inn en nettadresse der du ønsker å finne email: ");
        String setning = innFraBruker.readLine(); // Leser inn data fra bruker
        sendData = setning.getBytes(); // Setter data inn som bytes

        //Protokoll for sending
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddresse, 9876);
        clientSocket.send(sendPacket); //Lager en datagrampakke og sender denne fra Ip-adresse til port

        //Protokoll for mottak
        DatagramPacket mottakData = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(mottakData);
        //Protokoll for mottak, venter her på respons

        String serverSetning = new String(mottakData.getData());
        System.out.println("Fra server: " + serverSetning);




        int minInt=Integer.parseInt(serverSetning.trim());



        //Bruker så en løkke for å håndtere responsen fra server
        while (true) {
             if (minInt==0) {
                printWebside(serverSetning);
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
