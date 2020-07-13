package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

/**
 *
 * @author aromdhani
 */
public class MotherBoard implements Serializable {
    private String name;
    private String brand;
    private String type;
    private IOInterne ioInterne;
    private IOPorts ioPorts;
    private Bios bios;
    private ComponentDimension componentDimension;
    private Network network;
    private RAM ram;
    private Processor processor;
    private LegalGuarantees legalGuarantees;
    private UserAccount owner;
    private UserAccount validator;

    public MotherBoard() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IOInterne getIoInterne() {
        return ioInterne;
    }

    public void setIoInterne(IOInterne ioInterne) {
        this.ioInterne = ioInterne;
    }

    public IOPorts getIoPorts() {
        return ioPorts;
    }

    public void setIoPorts(IOPorts ioPorts) {
        this.ioPorts = ioPorts;
    }

    public Bios getBios() {
        return bios;
    }

    public void setBios(Bios bios) {
        this.bios = bios;
    }

    public ComponentDimension getComponentDimension() {
        return componentDimension;
    }

    public void setComponentDimension(ComponentDimension componentDimension) {
        this.componentDimension = componentDimension;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public LegalGuarantees getLegalGuarantees() {
        return legalGuarantees;
    }

    public void setLegalGuarantees(LegalGuarantees legalGuarantees) {
        this.legalGuarantees = legalGuarantees;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public UserAccount getValidator() {
        return validator;
    }

    public void setValidator(UserAccount validator) {
        this.validator = validator;
    }
}
