package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_order", schema="public")
public class UserOrder implements Serializable {
	public UserOrder() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable=true, length=250)
	private String name;
	
	@Column(name="description", nullable=true, length=1000)
	private String description;
	
	@Column(name="creation_timestamp", nullable=true, length=6)
	private java.sql.Timestamp creation_timestamp;
	
	@Column(name="serialized_properties", nullable=true)
	private String serialized_properties;
	
	@ManyToMany(targetEntity= fr.romdhani.aymen.toolios.core.orm.UserActionPurchase.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="user_action_purchase_order_map", schema="public", joinColumns={ @JoinColumn(name="order_id") }, inverseJoinColumns={ @JoinColumn(name="user_action_purchase_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_user_action_purchase = new java.util.HashSet();
	
	@ManyToMany(mappedBy="ORM_user_order", targetEntity= UserAccount.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_user_account = new java.util.HashSet();
	
	@ManyToMany(mappedBy="ORM_user_order", targetEntity= fr.romdhani.aymen.toolios.core.orm.Product.class)
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
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setCreation_timestamp(java.sql.Timestamp value) {
		this.creation_timestamp = value;
	}
	
	public java.sql.Timestamp getCreation_timestamp() {
		return creation_timestamp;
	}
	
	public void setSerialized_properties(String value) {
		this.serialized_properties = value;
	}
	
	public String getSerialized_properties() {
		return serialized_properties;
	}
	
	private void setORM_User_action_purchase(java.util.Set value) {
		this.ORM_user_action_purchase = value;
	}
	
	private java.util.Set getORM_User_action_purchase() {
		return ORM_user_action_purchase;
	}
	

	private void setORM_User_account(java.util.Set value) {
		this.ORM_user_account = value;
	}
	
	private java.util.Set getORM_User_account() {
		return ORM_user_account;
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
