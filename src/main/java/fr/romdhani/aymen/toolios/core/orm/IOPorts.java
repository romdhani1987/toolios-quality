package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class IOPorts implements Serializable {
    private int numberPSports;
    private int numberUSB2Ports;
    private int numberEthernetPort;
    private int numberOfHeadphone;
    private String microphoneJack;
    private int numberOfVGAPorts;
    private int numberCOMPorts;
    private int numberDVIDPorts;
    private int numberUSB3Ports;

    public IOPorts() {
    }

    public int getNumberPSports() {
        return numberPSports;
    }

    public void setNumberPSports(int numberPSports) {
        this.numberPSports = numberPSports;
    }

    public int getNumberUSB2Ports() {
        return numberUSB2Ports;
    }

    public void setNumberUSB2Ports(int numberUSB2Ports) {
        this.numberUSB2Ports = numberUSB2Ports;
    }

    public int getNumberEthernetPort() {
        return numberEthernetPort;
    }

    public void setNumberEthernetPort(int numberEthernetPort) {
        this.numberEthernetPort = numberEthernetPort;
    }

    public int getNumberOfHeadphone() {
        return numberOfHeadphone;
    }

    public void setNumberOfHeadphone(int numberOfHeadphone) {
        this.numberOfHeadphone = numberOfHeadphone;
    }

    public String getMicrophoneJack() {
        return microphoneJack;
    }

    public void setMicrophoneJack(String microphoneJack) {
        this.microphoneJack = microphoneJack;
    }

    public int getNumberOfVGAPorts() {
        return numberOfVGAPorts;
    }

    public void setNumberOfVGAPorts(int numberOfVGAPorts) {
        this.numberOfVGAPorts = numberOfVGAPorts;
    }

    public int getNumberCOMPorts() {
        return numberCOMPorts;
    }

    public void setNumberCOMPorts(int numberCOMPorts) {
        this.numberCOMPorts = numberCOMPorts;
    }

    public int getNumberDVIDPorts() {
        return numberDVIDPorts;
    }

    public void setNumberDVIDPorts(int numberDVIDPorts) {
        this.numberDVIDPorts = numberDVIDPorts;
    }

    public int getNumberUSB3Ports() {
        return numberUSB3Ports;
    }

    public void setNumberUSB3Ports(int numberUSB3Ports) {
        this.numberUSB3Ports = numberUSB3Ports;
    }
}
