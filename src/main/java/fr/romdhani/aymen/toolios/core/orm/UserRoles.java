package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles", schema = "public")
public class UserRoles implements Serializable {
    public enum UserRole {
        USER("user"), EDITOR("editor"), ADMIN("admin"), MANAGER("manager");
        private String role;

        UserRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    public UserRoles() {
    }

    public UserRoles(UserRole userRole) {
        this.name = userRole.getRole();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    @OneToMany(mappedBy = "roles", targetEntity = UserAccount.class)
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

    private void setORM_User_account(java.util.Set value) {
        this.ORM_user_account = value;
    }

    private java.util.Set getORM_User_account() {
        return ORM_user_account;
    }

    public String toString() {
        return String.valueOf(getId());
    }

}
