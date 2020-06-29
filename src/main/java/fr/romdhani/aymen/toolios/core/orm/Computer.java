package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author aromdhani
 */
@Entity
@Table(name = "computer", schema = "public")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "computer_name", nullable = true, length = 100)
    private String name;

    @Column(name = "serial_number", nullable = true, length = 100)
    private String SerialNumber;

    @Column(name = "processor", nullable = true, length = 100)
    private String processor;

    @Column(name = "ram", nullable = true, length = 100)
    private String ram;

    @Column(name = "service_tag", nullable = true, length = 100)
    private String serviceTag;

    @Column(name = "os", nullable = true, length = 100)
    private String os;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "computer")
    private List<License> licenses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "computer")
    private List<Screen> screens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Column(name = "age", nullable = true)
    private int age;

    @Column(name = "shifting", nullable = true)
    private boolean shifting;

    @Column(name = "purchase_date", nullable = true, length = 6)
    private java.sql.Timestamp purchaseDate;


    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    public Computer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isShifting() {
        return shifting;
    }

    public void setShifting(boolean shifting) {
        this.shifting = shifting;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSerialized_properties() {
        return serialized_properties;
    }

    public void setSerialized_properties(String serialized_properties) {
        this.serialized_properties = serialized_properties;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", serviceTag='" + serviceTag + '\'' +
                ", os='" + os + '\'' +
                ", licenses=" + licenses +
                ", screens=" + screens +
                ", age=" + age +
                ", shifting=" + shifting +
                ", purchaseDate=" + purchaseDate +
                ", serialized_properties='" + serialized_properties + '\'' +
                '}';
    }
}
