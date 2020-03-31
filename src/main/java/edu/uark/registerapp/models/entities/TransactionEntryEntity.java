package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="transactionentry")
public class TransactionEntryEntity {
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

	public UUID getId() {
		return this.id;
	}

    @Column(name="transactionid")
    private UUID transactionId;

	public UUID getTransactionId() {
		return this.transactionId;
	}

	public TransactionEntryEntity setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
	}

    @Column(name="productid")
    private UUID productId;

	public UUID getProductId() {
		return this.productId;
	}

	public TransactionEntryEntity setProductId(final UUID productId) {
		this.productId = productId;
		return this;
	}

    @Column(name="quantity")
    private double quantity;

	public double getQuantity() {
		return this.quantity;
	}

	public TransactionEntryEntity setQuantity(final double quantity) {
		this.quantity = quantity;
		return this;
	}

    @Column(name="price")
    private long price;

	public long getPrice() {
		return this.price;
	}

	public TransactionEntryEntity setPrice(final long price) {
		this.price = price;
		return this;
	}

	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	public TransactionEntryEntity() {
		this.price = 0L;
		this.quantity = 0D;
		this.id = new UUID(0, 0);
		this.productId = new UUID(0, 0);
		this.transactionId = new UUID(0, 0);
	}

	public TransactionEntryEntity(
		final UUID transactionId,
		final UUID productId,
		final double quantity,
		final long price
	) {

		this.price = price;
		this.id = new UUID(0, 0);
		this.quantity = quantity;
		this.productId = productId;
		this.transactionId = transactionId;
	}
}
