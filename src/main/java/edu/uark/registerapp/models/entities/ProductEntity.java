package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import edu.uark.registerapp.models.api.Product;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

	public UUID getId() {
		return this.id;
	}

	@Column(name = "lookupcode")
	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public ProductEntity setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
// ----------------------------------------------------------------
// This is setting up cost, just like how it was set up for count
  @Column(name="cost")
    private float cost;

	public float getCost() {
		return this.cost;
	}

	public ProductEntity setCost(final float cost) {
		this.cost = cost;
		return this;
	}
	
//----------------------------------------------------------------	
	@Column(name = "count")
	private int count;

	public int getCount() {
		return this.count;
	}

	public ProductEntity setCount(final int count) {
		this.count = count;
		return this;
	}

  

	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	public Product synchronize(final Product apiProduct) {
		this.setCount(apiProduct.getCount());
		this.setLookupCode(apiProduct.getLookupCode());
		this.setCost(apiProduct.getCost());
		
		apiProduct.setId(this.getId());
		apiProduct.setCreatedOn(this.getCreatedOn());

		return apiProduct;
	}

	public ProductEntity() {
		this.count = -1;
		this.cost = 0;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;
	}
// -----------------------------------------------------------------------------------------
// This is the part that I changed  (Floyd Brown, 11/22/20)
// Within the parameter, I have included cost, and we are basically setting it up 
// The same way that count was initially set up in this file 
	public ProductEntity(final String lookupCode, final int count, final float cost) {
//---------------------------------------------------------------------------
// This is where we setup cost
		this.cost = cost;
//---------------------------------------------------------------------------
		this.count = count;
		this.id = new UUID(0, 0);
		this.lookupCode = lookupCode;
	}

	public ProductEntity(final Product apiProduct) {
		this.id = new UUID(0, 0);
		this.count = apiProduct.getCount();
//---------------------------------------------------------------------------------------
		this.cost = apiProduct.getCost(); 
// Here we are basically setting up cost the same way that count was set up
//----------------------------------------------------------------------------------------	
		this.lookupCode = apiProduct.getLookupCode();
	}
}