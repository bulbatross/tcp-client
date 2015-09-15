package clientTCP;

import java.io.IOException;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCP {
    public static void main(String[] args){
        try{
            ClientTCPThread client;
            client = new ClientTCPThread(args[0],args[1]);
            client.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
