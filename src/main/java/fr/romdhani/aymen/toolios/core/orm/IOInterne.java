package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class IOInterne implements Serializable {
    private String usb2Connectors;
    private String usb3Connectors;
    private String COMConnectors;
    private String ATXConnector;
    private String EATXConnector;
    private String TPMConnector;
    private String SATAConnector;
    private String supportedHardDriver;

    public IOInterne() {
    }

    public String getUsb2Connectors() {
        return usb2Connectors;
    }

    public void setUsb2Connectors(String usb2Connectors) {
        this.usb2Connectors = usb2Connectors;
    }

    public String getUsb3Connectors() {
        return usb3Connectors;
    }

    public void setUsb3Connectors(String usb3Connectors) {
        this.usb3Connectors = usb3Connectors;
    }

    public String getCOMConnectors() {
        return COMConnectors;
    }

    public void setCOMConnectors(String COMConnectors) {
        this.COMConnectors = COMConnectors;
    }

    public String getATXConnector() {
        return ATXConnector;
    }

    public void setATXConnector(String ATXConnector) {
        this.ATXConnector = ATXConnector;
    }

    public String getEATXConnector() {
        return EATXConnector;
    }

    public void setEATXConnector(String EATXConnector) {
        this.EATXConnector = EATXConnector;
    }

    public String getTPMConnector() {
        return TPMConnector;
    }

    public void setTPMConnector(String TPMConnector) {
        this.TPMConnector = TPMConnector;
    }

    public String getSATAConnector() {
        return SATAConnector;
    }

    public void setSATAConnector(String SATAConnector) {
        this.SATAConnector = SATAConnector;
    }

    public String getSupportedHardDriver() {
        return supportedHardDriver;
    }

    public void setSupportedHardDriver(String supportedHardDriver) {
        this.supportedHardDriver = supportedHardDriver;
    }
}
