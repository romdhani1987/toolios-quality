package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "screen", schema = "public")
public class Screen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "screen_name", nullable = true, length = 100)
    private String name;

    @Column(name = "serial_number", nullable = true, length = 100)
    private String SerialNumber;

    @Column(name = "service_tag", nullable = true, length = 100)
    private String serviceTag;

    @Column(name = "purchase_date", nullable = true, length = 6)
    private java.sql.Timestamp purchaseDate;

    @Column(name = "age", nullable = true)
    private int age;

    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_id")
    private Computer computer;

    public Screen() {
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

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSerialized_properties() {
        return serialized_properties;
    }

    public void setSerialized_properties(String serialized_properties) {
        this.serialized_properties = serialized_properties;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", serviceTag='" + serviceTag + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", age=" + age +
                ", serialized_properties='" + serialized_properties + '\'' +
                ", computer=" + computer +
                '}';
    }
}
