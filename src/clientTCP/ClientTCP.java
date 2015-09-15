package clientTCP;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCP {
    private static Socket echoSocket;
    private static int portnr;
    private static String hostname;
    public static void main(String[] args){
        //portnr= Integer.parseInt(args[1]);
        //hostname = args[0];
        portnr = 7896;
        hostname = "127.0.0.1";
        try{

            echoSocket = new Socket(hostname, portnr);


            ClientTCPListen listener = new ClientTCPListen(echoSocket);
            listener.start();
            ClientTCPWriter writer = new ClientTCPWriter(echoSocket);
            writer.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
