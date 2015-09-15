package clientTCP;

import java.io.*;
import java.net.*;

/**
 * Created by Dario on 2015-09-08.
 */
public class ClientTCPThread extends Thread{

    private int portnr;
    private String serverHostname;
    Socket echoSocket;


    public ClientTCPThread(String hostname, String port)throws IOException{
        serverHostname = hostname;
        portnr= Integer.parseInt(port);
    }

    public void run(){


        try {
            echoSocket = new Socket(serverHostname, portnr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(echoSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
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
                try {
                    System.out.println("echo: " + in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print ("input: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
