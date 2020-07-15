package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_action_purchase", schema="public")
public class UserActionPurchase implements Serializable {
	public UserActionPurchase() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="title", nullable=true, length=250)
	private String title;
	
	@Column(name="description", nullable=true, length=1000)
	private String description;
	
	@Column(name="creation_timestamp", nullable=true, length=6)
	private java.sql.Timestamp creation_timestamp;
	
	@Column(name="serialized_properties", nullable=true)
	private String serialized_properties;
	
	@ManyToMany(targetEntity= UserAction.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="user_action_purchase_map", schema="public", joinColumns={ @JoinColumn(name="user_action_purchase_id") }, inverseJoinColumns={ @JoinColumn(name="user_action_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_user_action = new java.util.HashSet();
	
	@ManyToMany(mappedBy="ORM_user_action_purchase", targetEntity= UserOrder.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_order = new java.util.HashSet();
	
	private void setId(long value) {
		this.id = value;
	}
	
	public long getId() {
		return id;
	}
	
	public long getORMID() {
		return getId();
	}
	
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return title;
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
	
	private void setORM_User_action(java.util.Set value) {
		this.ORM_user_action = value;
	}
	
	private java.util.Set getORM_User_action() {
		return ORM_user_action;
	}
	

	private void setORM_Order(java.util.Set value) {
		this.ORM_order = value;
	}
	
	private java.util.Set getORM_Order() {
		return ORM_order;
	}
	

	public String toString() {
		return String.valueOf(getId());
	}
	
}
