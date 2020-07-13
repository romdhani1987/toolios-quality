package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class Processor implements Serializable {
    private String type;
    private String family;
    private String socket;

    public Processor() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
