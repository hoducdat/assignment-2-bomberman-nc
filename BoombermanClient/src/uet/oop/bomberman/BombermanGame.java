package uet.oop.bomberman;

import uet.oop.bomberman.gui.Frame;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BombermanGame {

	public static Socket socket;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		socket = uet.oop.bomberman.socket.Socket.open(InetAddress.getLocalHost().getHostAddress(), 2345);
		System.out.println(InetAddress.getLocalHost().getHostAddress());

		new Frame();

		uet.oop.bomberman.socket.Socket.close();
	}
}
