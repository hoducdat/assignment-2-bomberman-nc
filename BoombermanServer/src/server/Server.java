package server;

import java.io.IOException;
import java.net.*;

public class Server {

    private int portNumber = 2345;
    private ServerSocket serverSocket;

    public Server() {

    }

    public Server(int portNumber) {
        this.portNumber = portNumber;
        try {
            serverSocket = new ServerSocket(portNumber);
            run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println(serverSocket.toString());
        try {
            Socket socket1 = serverSocket.accept();
            while (true) {
                // handle receive
                // handle new room
                // handle come in room
            }
        }
        catch (IOException e) {
            System.out.println("IOexception while server run");
            e.printStackTrace();
        }
    }
}
