package server;

import java.net.*

public class Server {

    private int portNumber = 12345;
    private ServerSocket serverSocket;

    public Server() {

    }

    public Server(int portNumber) {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(portNumber);
    }

    public void run() {
        while (true) {
            Socket socket = serverSocket.accept();

            // handle receive
            // handle new room
            // handle come in room
        }
    }
}
