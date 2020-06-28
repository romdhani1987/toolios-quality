package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_request", schema="public")
public class UserRequest implements Serializable {
	public UserRequest() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="name", nullable=true, length=250)
	private String name;
	
	@Column(name="creation_timestamp", nullable=true, length=6)
	private java.sql.Timestamp creation_timestamp;
	
	@Column(name="archive_timestamp", nullable=true, length=6)
	private java.sql.Timestamp archive_timestamp;
	
	@Column(name="serialized_properties", nullable=true)
	private String serialized_properties;
	
	@ManyToMany(targetEntity= UserAccount.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="user_account_request_map", schema="public", joinColumns={ @JoinColumn(name="user_request_id") }, inverseJoinColumns={ @JoinColumn(name="user_account_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_user_account = new java.util.HashSet();
	
	@ManyToMany(targetEntity= Purchase.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="user_request_purchase_map", schema="public", joinColumns={ @JoinColumn(name="user_request_id") }, inverseJoinColumns={ @JoinColumn(name="purchase_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_purchase = new java.util.HashSet();
	
	@ManyToMany(mappedBy="ORM_user_request", targetEntity= UserAction.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_action = new java.util.HashSet();
	
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
	
	public void setCreation_timestamp(java.sql.Timestamp value) {
		this.creation_timestamp = value;
	}
	
	public java.sql.Timestamp getCreation_timestamp() {
		return creation_timestamp;
	}
	
	public void setArchive_timestamp(java.sql.Timestamp value) {
		this.archive_timestamp = value;
	}
	
	public java.sql.Timestamp getArchive_timestamp() {
		return archive_timestamp;
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
	

	private void setORM_Purchase(java.util.Set value) {
		this.ORM_purchase = value;
	}
	
	private java.util.Set getORM_Purchase() {
		return ORM_purchase;
	}
	

	private void setORM_Action(java.util.Set value) {
		this.ORM_action = value;
	}
	
	private java.util.Set getORM_Action() {
		return ORM_action;
	}

	public String toString() {
		return String.valueOf(getId());
	}
	
}
