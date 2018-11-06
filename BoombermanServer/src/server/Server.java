package server;

import java.net.*;

public class Server {

    private int portNumber = 12345;
    private ServerSocket serverSocket;

    public Server() {

    }

    public Server(int portNumber) {
        this.portNumber = portNumber;
        try {
            serverSocket = new ServerSocket(portNumber);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            // handle receive
            // handle new room
            // handle come in room
        }
    }
}
