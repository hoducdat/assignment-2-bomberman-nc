package server;

public class BoombermanServer {

    public static void main(String[] args) {
        Server server = new Server(2345);
        server.run();
    }
}
