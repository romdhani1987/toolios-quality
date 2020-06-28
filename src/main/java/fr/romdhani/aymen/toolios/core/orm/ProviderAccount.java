package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="provider_account", schema="public")
public class ProviderAccount implements Serializable {
	public ProviderAccount() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="login", nullable=false, length=50)
	private String login;
	
	@Column(name="f_name", nullable=true, length=50)
	private String f_name;
	
	@Column(name="l_name", nullable=true, length=50)
	private String l_name;
	
	@Column(name="email", nullable=true, length=50)
	private String email;
	
	@Column(name="phone_number", nullable=true, length=50)
	private String phone_number;
	
	@Column(name="creation_mode", nullable=true, length=50)
	private String creation_mode;
	
	@Column(name="serialized_properties", nullable=true)
	private String serialized_properties;
	
	@ManyToOne(targetEntity= Address.class, fetch= FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="address_id", referencedColumnName="id") })
	private Address address;
	
	@OneToMany(mappedBy="article_category", targetEntity= fr.romdhani.aymen.toolios.core.orm.Product.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_product = new java.util.HashSet();
	
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
	
	public void setF_name(String value) {
		this.f_name = value;
	}
	
	public String getF_name() {
		return f_name;
	}
	
	public void setL_name(String value) {
		this.l_name = value;
	}
	
	public String getL_name() {
		return l_name;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone_number(String value) {
		this.phone_number = value;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setCreation_mode(String value) {
		this.creation_mode = value;
	}
	
	public String getCreation_mode() {
		return creation_mode;
	}
	
	public void setSerialized_properties(String value) {
		this.serialized_properties = value;
	}
	
	public String getSerialized_properties() {
		return serialized_properties;
	}
	
	public void setAddress(Address value) {
		address =value;
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
	
	private void setORM_Product(java.util.Set value) {
		this.ORM_product = value;
	}
	
	private java.util.Set getORM_Product() {
		return ORM_product;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
