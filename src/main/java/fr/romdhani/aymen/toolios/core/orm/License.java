package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "license", schema = "public")
public class License implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "license_name", nullable = true, length = 100)
    private String name;

    @Column(name = "license_type", nullable = true, length = 100)
    private String type;

    @Column(name = "serial_number", nullable = true, length = 100)
    private String serialNumber;

    @Column(name = "purchase_date", nullable = true, length = 6)
    private java.sql.Timestamp purchaseDate;

    @Column(name = "expiration_date", nullable = true, length = 6)
    private java.sql.Timestamp expirationDate;

    @Column(name = "serialized_properties", nullable = true)
    private String serializedProperties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    public License() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "License{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", expirationDate=" + expirationDate +
                ", serializedProperties='" + serializedProperties + '\'' +
                ", computer=" + computer +
                '}';
    }
}
