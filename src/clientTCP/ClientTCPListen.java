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
    private ClientTCPWriter otherThread;

    public ClientTCPListen(Socket socket, ClientTCPWriter thread)throws IOException {
        super();
        echoSocket = socket;
        this.otherThread=thread;
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
                    msg = sanitazeString(msg);
                    System.out.println("echo: " + msg);
                    System.out.print("input: ");
            }
        } catch(SocketException e){
            System.out.println("Connection closed!");
        } catch (IOException e) {
            System.out.println("Problem when reading from server: " + e.getMessage());
        } finally {
            otherThread.close();
            try {
                in.close();
                echoSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    protected String sanitazeString(String msg){
        if(msg.contains("<#>")){
            String[] tmp = msg.split("<#>");
            String sanitazedString = "";
            for(int i = 0; i < tmp.length; i++){
                sanitazedString += "\n" +tmp[i];
            }
            return sanitazedString;
        } else
            return msg;
    }
}
