

package edu.uark.registerapp.models.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.registerapp.models.entities.ProductEntity;

public class Product extends ApiResponse {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Product setId(final UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public Product setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
//-----------------------------------------------------------------------
// This is what Floyd Brown added 11/22/20
// This is basically creating a get and set for the cost of what is being purchased
// It is based off of the other getters and setters in the file
	private float cost;

	public float getCost() {
		return this.cost;
	}

	public Product setCost(final float cost) {
		this.cost = cost;
		return this;
	}
//-----------------------------------------------------------------------

	private int count;

	public int getCount() {
		return this.count;
	}

	public Product setCount(final int count) {
		this.count = count;
		return this;
	}



	private String createdOn;

	public String getCreatedOn() {
		return this.createdOn;
	}

	public Product setCreatedOn(final String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public Product setCreatedOn(final LocalDateTime createdOn) {
		this.createdOn =
			createdOn.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		return this;
	}

	public Product() {
		super();
//----------------------------------------		
		this.cost = 0;
// The line above is letting our cost equal 0		
//------------------------------------------
		this.count = -1;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;

		this.setCreatedOn(LocalDateTime.now());
	}

	public Product(final ProductEntity productEntity) {
		super(false);

		this.id = productEntity.getId();
		this.count = productEntity.getCount();
		this.lookupCode = productEntity.getLookupCode();
		this.cost = productEntity.getCost();

		this.setCreatedOn(productEntity.getCreatedOn());
	}
}
