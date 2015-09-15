package clientTCP;

import java.io.*;
import java.net.*;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCPWriter extends Thread{



    Socket echoSocket;
    PrintWriter out = null;

    public ClientTCPWriter(Socket socket)throws IOException{
        echoSocket = socket;
    }

    public void run(){

        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }





        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.print("input: ");
        try {
            while ((userInput = stdIn.readLine()) != null)
            {
                out.println(userInput);
                if (userInput.equals("/quit"))
                    break;

                System.out.print ("input: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

        try {
            stdIn.close();
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
