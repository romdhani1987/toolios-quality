package fr.romdhani.aymen.toolios.core.orm;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address", schema = "public")
public class Address implements Serializable {
    public Address() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street", nullable = true, length = 250)
    private String street;

    @Column(name = "code", nullable = true, length = 250)
    private String code;

    @Column(name = "city", nullable = true, length = 250)
    private String city;

    @Column(name = "country", nullable = true, length = 250)
    private String country;

    @OneToMany(mappedBy = "address", targetEntity = Company.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set ORMCompany = new HashSet();

    @OneToMany(mappedBy = "address", targetEntity = ProviderAccount.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set ORMProviderAccount = new HashSet();

    @OneToMany(mappedBy = "address", targetEntity = UserAccount.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set ORMUserAccount = new HashSet();

    private void setId(long value) {
        this.id = value;
    }

    public long getId() {
        return id;
    }

    public long getORMID() {
        return getId();
    }

    public void setStreet(String value) {
        this.street = value;
    }

    public String getStreet() {
        return street;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public String getCountry() {
        return country;
    }

    private void setORMCompany(java.util.Set value) {
        this.ORMCompany = value;
    }

    private Set getORMCompany() {
        return ORMCompany;
    }

    private void setORMProviderAccount(Set value) {
        this.ORMProviderAccount = value;
    }

    private Set getORMProviderAccount() {
        return ORMProviderAccount;
    }

    private void setORMUserAccount(Set value) {
        this.ORMUserAccount = value;
    }

    private Set getORMUserAccount() {
        return ORMUserAccount;
    }

    @Override
    public String toString() {
        return
                street +
                        " " + code +
                        " " + city +
                        " " + country;
    }
}
