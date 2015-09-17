package clientTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by bulbatross on 2015-09-15.
 */
public class ClientTCPWriter extends Thread {



    Socket echoSocket;
    PrintWriter out = null;
    //private ClientTCPListen otherThread;

    public ClientTCPWriter(Socket socket)throws IOException {
        super();
        echoSocket = socket;
        //this.otherThread=thread;
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
            while ((userInput = stdIn.readLine()) != null && !Thread.currentThread().isInterrupted())
            {
                out.println(userInput);

                if (userInput.equals("/quit"))
                    break;

                System.out.print ("input: ");

            }
        } catch (IOException e) {
            System.out.println("write thread: " + e.getMessage());

        } finally {
            out.close();
            //otherThread.interrupt();
            try {
                stdIn.close();
                echoSocket.close();
            } catch (IOException e) {
                System.out.println("close write: " + e.getMessage());
            }

        }
    }





}
