package fr.romdhani.aymen.toolios.core.orm;

import java.io.Serializable;

public class RAM implements Serializable {
    private String maximumInternalMemory;
    private String supportedClockSpeedMemory;
    private String supportedMemoryTypes;
    private String slotMemory;
    private boolean isMemoryWithoutBuffer;
    private int numberOfMemorySlots;

    public RAM() {
    }

    public String getMaximumInternalMemory() {
        return maximumInternalMemory;
    }

    public void setMaximumInternalMemory(String maximumInternalMemory) {
        this.maximumInternalMemory = maximumInternalMemory;
    }

    public String getSupportedClockSpeedMemory() {
        return supportedClockSpeedMemory;
    }

    public void setSupportedClockSpeedMemory(String supportedClockSpeedMemory) {
        this.supportedClockSpeedMemory = supportedClockSpeedMemory;
    }

    public String getSupportedMemoryTypes() {
        return supportedMemoryTypes;
    }

    public void setSupportedMemoryTypes(String supportedMemoryTypes) {
        this.supportedMemoryTypes = supportedMemoryTypes;
    }

    public String getSlotMemory() {
        return slotMemory;
    }

    public void setSlotMemory(String slotMemory) {
        this.slotMemory = slotMemory;
    }

    public boolean isMemoryWithoutBuffer() {
        return isMemoryWithoutBuffer;
    }

    public void setMemoryWithoutBuffer(boolean memoryWithoutBuffer) {
        isMemoryWithoutBuffer = memoryWithoutBuffer;
    }

    public int getNumberOfMemorySlots() {
        return numberOfMemorySlots;
    }

    public void setNumberOfMemorySlots(int numberOfMemorySlots) {
        this.numberOfMemorySlots = numberOfMemorySlots;
    }
}
