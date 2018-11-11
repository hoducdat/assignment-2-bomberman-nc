package uet.oop.bomberman.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class Socket {

    private static java.net.Socket data;
    private static BufferedWriter bufferOut;
    private static BufferedReader bufferIn;

    public static void openBuffer() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(Socket.data.getInputStream());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Socket.data.getOutputStream());
        bufferIn = new BufferedReader(inputStreamReader);
        bufferOut = new BufferedWriter(outputStreamWriter);
    }

    public static void closeBuffer() throws IOException{
        bufferOut.close();
        bufferIn.close();
    }

    public static void close() {
        try {
            closeBuffer();
            Socket.data.close();
        }
        catch (IOException e) {
            System.out.println("IOexception while close Socket");
            e.printStackTrace();
        }
    }

    public static java.net.Socket open(String server, int port) {

        java.net.Socket socket = null;

        try {
            InetAddress inetAddress = InetAddress.getByName(server);
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);

            socket = new java.net.Socket();

            int timeOutInMs = 10 * 1000;

            socket.connect(socketAddress, timeOutInMs);

            openBuffer();

        } catch (SocketTimeoutException e) {
            System.out.println("Time out for waiting from server");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOexception while open Socket");
            e.printStackTrace();
        }
        finally {
            Socket.data = socket;
            return socket;
        }

    }
}
