package clientTCP;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCP {
    private static Socket echoSocket;
    private static int portnr;
    public static void main(String[] args){

        //String hostname = args[0];
        String hostname = "127.0.0.1";
        //portnr= Integer.parseInt(args[1]);
        portnr = 7896;
        try{

            echoSocket = new Socket(hostname, portnr);


            ClientTCPWriter writer = new ClientTCPWriter(echoSocket);
            writer.start();

            ClientTCPListen listener = new ClientTCPListen(echoSocket,writer);
            listener.start();
        } catch (ConnectException e) {
            System.err.println("It seems like the server is not running: " + hostname);
            System.exit(1);
        }catch(IOException e){
            System.err.println("Couldn't get I/O for the connection to " +
                    hostname);
            System.exit(1);
        }
    }
}
