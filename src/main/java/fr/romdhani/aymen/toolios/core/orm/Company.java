package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "company", schema = "public")
public class Company implements Serializable {
    public Company() {
    }

    private java.util.Set this_getSet(int key) {
        return null;
    }

    private void this_setOwner(Object owner, int key) {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "serial", nullable = true)
    private Long serial;

    @Column(name = "siren", nullable = true)
    private Long siren;

    @Column(name = "siret", nullable = true)
    private Long siret;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "address_id", referencedColumnName = "id")})
    private Address address;

    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    @OneToMany(mappedBy = "company", targetEntity = fr.romdhani.aymen.toolios.core.orm.UserGroup.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set ORM_user_group = new java.util.HashSet();

    private void setId(long value) {
        this.id = value;
    }

    public long getId() {
        return id;
    }

    public long getORMID() {
        return getId();
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public void setSerial(long value) {
        setSerial(new Long(value));
    }

    public void setSerial(Long value) {
        this.serial = value;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSiren(long value) {
        setSiren(new Long(value));
    }

    public void setSiren(Long value) {
        this.siren = value;
    }

    public Long getSiren() {
        return siren;
    }

    public void setSiret(long value) {
        setSiret(new Long(value));
    }

    public void setSiret(Long value) {
        this.siret = value;
    }

    public Long getSiret() {
        return siret;
    }

    public void setSerialized_properties(String value) {
        this.serialized_properties = value;
    }

    public String getSerialized_properties() {
        return serialized_properties;
    }

    public void setAddress(Address value) {
        address = value;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * This method is for internal use only.
     */
    private void setORM_Address(Address value) {
        this.address = value;
    }

    private Address getORM_Address() {
        return address;
    }

    private void setORM_User_group(java.util.Set value) {
        this.ORM_user_group = value;
    }

    private java.util.Set getORM_User_group() {
        return ORM_user_group;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
