package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_account", schema = "public")
public class UserAccount implements Serializable {
    public enum UserGroupType {
        USER, ADMIN
    }

    public UserAccount() {
    }

    public UserAccount(String login, String password_hash) {
        this.login = login;
        this.password_hash = password_hash;
    }

    public UserAccount(String login, String f_name, String l_name, String email, String phone_number, String password_hash, fr.romdhani.aymen.toolios.core.orm.UserRoles roles) {
        this.login = login;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.phone_number = phone_number;
        this.password_hash = password_hash;
        this.roles = roles;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "f_name", nullable = true, length = 50)
    private String f_name;

    @Column(name = "l_name", nullable = true, length = 50)
    private String l_name;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @Column(name = "phone_number", nullable = true, length = 50)
    private String phone_number;

    @Column(name = "password_hash", nullable = true, length = 50)
    private String password_hash;

    @Column(name = "creation_mode", nullable = true, length = 50)
    private String creation_mode;

    @Column(name = "serialized_properties", nullable = true)
    private String serialized_properties;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "address_id", referencedColumnName = "id")})
    private Address address;

    @ManyToOne(targetEntity = UserFunction.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "function_id", referencedColumnName = "id")})
    private UserFunction function;

    @ManyToOne(targetEntity = UserRoles.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "roles_id", referencedColumnName = "id")})
    private UserRoles roles;

    @ManyToOne(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})
    @JoinColumns(value = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
    private UserGroup group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
    private List<Computer> computers;

    @ManyToMany(targetEntity = UserOrder.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @JoinTable(name = "user_account_order_map", schema = "public", joinColumns = {@JoinColumn(name = "user_account_id")}, inverseJoinColumns = {@JoinColumn(name = "user_order_id")})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private Set ORM_user_order = new HashSet();

    @ManyToMany(targetEntity = Project.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @JoinTable(name = "user_account_project_map", schema = "public", joinColumns = {@JoinColumn(name = "user_account_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private Set ORM_project = new HashSet();

    @ManyToMany(mappedBy = "ORM_user_account", targetEntity = fr.romdhani.aymen.toolios.core.orm.UserRequest.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private Set ORM_user_request = new HashSet();

    @OneToMany(mappedBy = "supervisor", targetEntity = Project.class)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})
    @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
    private Set ORM_project1 = new HashSet();

    private void setId(long value) {
        this.id = value;
    }

    public long getId() {
        return id;
    }

    public long getORMID() {
        return getId();
    }

    public void setLogin(String value) {
        this.login = value;
    }

    public String getLogin() {
        return login;
    }

    public void setFirstName(String value) {
        this.f_name = value;
    }

    public String getFirstName() {
        return f_name;
    }

    public void setLastName(String value) {
        this.l_name = value;
    }

    public String getLastName() {
        return l_name;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String value) {
        this.phone_number = value;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPasswordHash(String value) {
        this.password_hash = value;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setCreationMode(String value) {
        this.creation_mode = value;
    }

    public String getCreationMode() {
        return creation_mode;
    }

    public void setSerializedProperties(String value) {
        this.serialized_properties = value;
    }

    public String getSerializedProperties() {
        return serialized_properties;
    }

    private void setORMUserOrder(Set value) {
        this.ORM_user_order = value;
    }

    private Set getORMUserOrder() {
        return ORM_user_order;
    }


    public void setORMProject(Set value) {
        this.ORM_project = value;
    }

    private Set getORMProject() {
        return ORM_project;
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
    private void setORMAddress(Address value) {
        this.address = value;
    }

    private Address getORMAddress() {
        return address;
    }

    public void setFunction(UserFunction value) {
        function = value;
    }

    public UserFunction getFunction() {
        return function;
    }

    /**
     * This method is for internal use only.
     */
    private void setORMFunction(UserFunction value) {
        this.function = value;
    }

    private UserFunction getORMFunction() {
        return function;
    }

    public void setRoles(UserRoles value) {
        roles = value;

    }

    public fr.romdhani.aymen.toolios.core.orm.UserRoles getRoles() {
        return roles;
    }

    /**
     * This method is for internal use only.
     */
    private void setORMRoles(UserRoles value) {
        this.roles = value;
    }

    private UserRoles getORMRoles() {
        return roles;
    }

    public void setGroup(UserGroup value) {
        group = value;

    }

    public UserGroup getGroup() {
        return group;
    }

    /**
     * This method is for internal use only.
     */
    private void setORMGroup(UserGroup value) {
        this.group = value;
    }

    private UserGroup getORMGroup() {
        return group;
    }

    private void setORMUserRequest(Set value) {
        this.ORM_user_request = value;
    }

    private Set getORMUserRequest() {
        return ORM_user_request;
    }


    private void setORMProject1(Set value) {
        this.ORM_project1 = value;
    }

    private Set getORMProject1() {
        return ORM_project1;
    }

    public void addProject(final Project project) {

        if (project != null) {
            Set<Project> projectSet = getORMProject();
            if (projectSet == null) {
                projectSet = new HashSet<Project>();

                setORMProject(projectSet);
            }
            projectSet.add(project);
        }
    }

    public void removeProject(final Project Project) {
        final Set<Project> projectSet = getORMProject();
        if (projectSet != null) {
            projectSet.remove(Project);
        }
    }

    public void addOrder(final fr.romdhani.aymen.toolios.core.orm.UserOrder userOrder) {
        if (userOrder != null) {
            Set<fr.romdhani.aymen.toolios.core.orm.UserOrder> userOrderSet = getORMUserOrder();
            if (userOrderSet == null) {
                userOrderSet = new HashSet<UserOrder>();

                setORMUserOrder(userOrderSet);
            }
            userOrderSet.add(userOrder);
        }
    }

    public void removeOrder(final UserOrder userOrder) {
        final Set<UserOrder> userOrderSet = getORMUserOrder();
        if (userOrderSet != null) {
            userOrderSet.remove(userOrder);
        }
    }

    public void addRequest(final UserRequest userRequest) {

        if (userRequest != null) {
            Set<UserRequest> userRequestSet = getORMUserRequest();
            if (userRequest == null) {
                userRequestSet = new HashSet<UserRequest>();

                setORMUserRequest(userRequestSet);
            }
            userRequestSet.add(userRequest);
        }
    }

    public void removeUserRequest(final UserRequest userRequest) {
        final Set<UserRequest> UserRequestSet = getORMUserRequest();
        if (userRequest != null) {
            UserRequestSet.remove(userRequest);
        }
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return login;
    }
}
