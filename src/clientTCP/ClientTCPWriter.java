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

    private Socket echoSocket;
    private PrintWriter out = null;
    private boolean done;

    public ClientTCPWriter(Socket socket)throws IOException {
        super();
        done = false;
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
        
        try {
            while (!done)
            {
                if(stdIn.ready()) {
                    userInput = stdIn.readLine();
                    if(userInput != null){
                        if (userInput.equals("/quit"))
                            break;
                        else
                            out.println(userInput);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("write thread: " + e.getMessage());
        } finally {
            out.close();
            try {
                stdIn.close();
                echoSocket.close();
            } catch (IOException e) {
                System.out.println("close write: " + e.getMessage());
            }
        }
    }

    public void close(){
        done = true;
    }
}
