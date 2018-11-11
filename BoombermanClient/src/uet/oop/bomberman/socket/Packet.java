package uet.oop.bomberman.socket;

import java.io.Serializable;

public class Packet implements Serializable {

    private int type;
    private String data;

    public Packet(int type, String data) {
        this.type = type;
        this.data = data;
    }
}
