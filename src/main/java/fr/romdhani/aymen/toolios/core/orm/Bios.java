package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class Bios implements Serializable {
    private String type;
    private String memoryCapacity;
    private String version;

    public Bios() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
