package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class Network implements Serializable {
    private String localAreaController;
    private String  ethernetInterfaceType;
    private String ethernet;

    public Network() {
    }

    public String getLocalAreaController() {
        return localAreaController;
    }

    public void setLocalAreaController(String localAreaController) {
        this.localAreaController = localAreaController;
    }

    public String getEthernetInterfaceType() {
        return ethernetInterfaceType;
    }

    public void setEthernetInterfaceType(String ethernetInterfaceType) {
        this.ethernetInterfaceType = ethernetInterfaceType;
    }

    public String getEthernet() {
        return ethernet;
    }

    public void setEthernet(String ethernet) {
        this.ethernet = ethernet;
    }
}
