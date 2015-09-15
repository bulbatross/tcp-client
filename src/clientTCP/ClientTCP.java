package clientTCP;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCP {
    private static Socket echoSocket;
    private static int portnr;
    public static void main(String[] args){
        portnr= Integer.parseInt(args[1]);
        try{

            echoSocket = new Socket(args[0], portnr);


            ClientTCPListen listener = new ClientTCPListen(echoSocket);
            listener.start();
            ClientTCPWriter writer = new ClientTCPWriter(echoSocket);
            writer.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
