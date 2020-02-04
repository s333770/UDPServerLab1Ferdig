import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlientParalell implements Runnable {
    private Socket klient;
    private BufferedReader in;
    private PrintWriter ut;

    public KlientParalell(Socket klientSocket) throws IOException {
        this.klient=klientSocket;
        in=new BufferedReader(new InputStreamReader(klient.getInputStream()));
        ut=new PrintWriter(klient.getOutputStream(),true);
    }


    @Override
    public void run() {

        String lestInnString = null;
        String responsfraServer=null;
        try {
                lestInnString = in.readLine();
                responsfraServer= Funksjoner.seEtterWebside(lestInnString);
                ut.println(responsfraServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                ut.close();
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
