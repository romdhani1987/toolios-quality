package fr.romdhani.aymen.toolios.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product", schema="public")
public class Product implements Serializable {
	public Product() {
	}

	
	private java.util.Set this_getSet (int key) {

		return null;
	}
	
	private void this_setOwner(Object owner, int key) {

	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="title", nullable=false, length=250)
	private String title;
	
	@Column(name="description", nullable=true, length=1000)
	private String description;
	
	@Column(name="serialized_properties", nullable=true)
	private String serialized_properties;
	
	@Column(name="price_ht", nullable=true, precision=131089, scale=0)
	private java.math.BigDecimal price_ht;
	
	@Column(name="price_ttc", nullable=true, precision=131089, scale=0)
	private java.math.BigDecimal price_ttc;
	
	@ManyToOne(targetEntity=fr.romdhani.aymen.toolios.core.orm.Unconformity.class, fetch= FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="provider_account_id", referencedColumnName="id") })
	private fr.romdhani.aymen.toolios.core.orm.Unconformity provider_account;
	
	@ManyToOne(targetEntity= ProviderAccount.class, fetch= FetchType.LAZY)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="article_category_id", referencedColumnName="id") })
	private ProviderAccount article_category;
	
	@ManyToMany(targetEntity=fr.romdhani.aymen.toolios.core.orm.Purchase.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="purchase_product_map", schema="public", joinColumns={ @JoinColumn(name="product_id") }, inverseJoinColumns={ @JoinColumn(name="purchase_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_purchase = new java.util.HashSet();
	
	@ManyToMany(targetEntity=fr.romdhani.aymen.toolios.core.orm.Unconformity.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="unconformity_product_map", schema="public", joinColumns={ @JoinColumn(name="product_id") }, inverseJoinColumns={ @JoinColumn(name="unconformity_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_unconformity = new java.util.HashSet();
	
	@ManyToMany(targetEntity= UserOrder.class)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinTable(name="user_order_product_map", schema="public", joinColumns={ @JoinColumn(name="product_id") }, inverseJoinColumns={ @JoinColumn(name="user_order_id") })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_user_order = new java.util.HashSet();
	
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
	
	public void setSerialized_properties(String value) {
		this.serialized_properties = value;
	}
	
	public String getSerialized_properties() {
		return serialized_properties;
	}
	
	public void setPrice_ht(java.math.BigDecimal value) {
		this.price_ht = value;
	}
	
	public java.math.BigDecimal getPrice_ht() {
		return price_ht;
	}
	
	public void setPrice_ttc(java.math.BigDecimal value) {
		this.price_ttc = value;
	}
	
	public java.math.BigDecimal getPrice_ttc() {
		return price_ttc;
	}
	
	private void setORM_Purchase(java.util.Set value) {
		this.ORM_purchase = value;
	}
	
	private java.util.Set getORM_Purchase() {
		return ORM_purchase;
	}

	private void setORM_Unconformity(java.util.Set value) {
		this.ORM_unconformity = value;
	}
	
	private java.util.Set getORM_Unconformity() {
		return ORM_unconformity;
	}

	private void setORM_User_order(java.util.Set value) {
		this.ORM_user_order = value;
	}
	
	private java.util.Set getORM_User_order() {
		return ORM_user_order;
	}
	

	public void setProvider_account(fr.romdhani.aymen.toolios.core.orm.Unconformity value) {
		if (provider_account != null) {

		}
		if (value != null) {

		}
	}
	
	public fr.romdhani.aymen.toolios.core.orm.Unconformity getProvider_account() {
		return provider_account;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_Provider_account(fr.romdhani.aymen.toolios.core.orm.Unconformity value) {
		this.provider_account = value;
	}
	
	private fr.romdhani.aymen.toolios.core.orm.Unconformity getORM_Provider_account() {
		return provider_account;
	}
	
	public void setArticle_category(ProviderAccount value) {
		
	}
	
	public ProviderAccount getArticle_category() {
		return article_category;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_Article_category(ProviderAccount value) {
		this.article_category = value;
	}
	
	private ProviderAccount getORM_Article_category() {
		return article_category;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
