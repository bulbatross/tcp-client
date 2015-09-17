package clientTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

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

        String msg;
        try {
            while (( msg = in.readLine()) != null)
            {
                System.out.println("echo: " + msg);

            }
        } catch(SocketException e){
            System.err.println("Server error!");
            //close all
        }catch (IOException e) {
            System.out.println("Problem when reading: "+ e.getMessage());
        } finally {
            try {
                in.close();
                echoSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
