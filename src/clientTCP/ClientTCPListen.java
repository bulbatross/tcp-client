package clientTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCPListen extends Thread {


    private Socket echoSocket;


    public ClientTCPListen(Socket socket)throws IOException {
        echoSocket = socket;
    }

    public void run() {

        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            while (( in.readLine()) != null)
            {
                try {
                    System.out.println("echo: " + in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            echoSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
