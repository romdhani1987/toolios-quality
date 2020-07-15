package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_group", schema = "public")
public class UserGroup implements Serializable {
    public UserGroup() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = true, length = 250)
    private String name;

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)})
    private Company company;

    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    @OneToMany(mappedBy = "group", targetEntity = UserAccount.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private java.util.Set ORM_user_account = new java.util.HashSet();

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

    public void setSerialized_properties(String value) {
        this.serialized_properties = value;
    }

    public String getSerialized_properties() {
        return serialized_properties;
    }

    public void setCompany(Company value) {
        company = value;
    }

    public Company getCompany() {
        return company;
    }

    /**
     * This method is for internal use only.
     */
    private void setORM_Company(Company value) {
        this.company = value;
    }

    private Company getORM_Company() {
        return company;
    }

    private void setORM_User_account(java.util.Set value) {
        this.ORM_user_account = value;
    }

    private java.util.Set getORM_User_account() {
        return ORM_user_account;
    }

    @Override
    public String toString() {
        return "User group{" + name + "}";
    }
}
