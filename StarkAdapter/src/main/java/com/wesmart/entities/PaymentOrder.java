package com.wesmart.entities;

import java.util.Date;
import java.util.UUID;

public class PaymentOrder {

	private UUID id;
	private String reference;
	private String description;
	private String billingAddress;
	private Date creationDate;
	private Customer customer;
	private Seller seller;

	public PaymentOrder() {

	}

	public PaymentOrder(final UUID id, final String reference, final String description, final String billingAddress,
						final Date creationDate,
						final Customer customer, final Seller seller) {

		this.id = id;
		this.reference = reference;
		this.description = description;
		this.billingAddress = billingAddress;
		this.creationDate = creationDate;
		this.customer = customer;
		this.seller = seller;
	}

	public UUID getId() {

		return id;
	}

	public void setId(final UUID id) {

		this.id = id;
	}

	public String getReference() {

		return reference;
	}

	public void setReference(final String reference) {

		this.reference = reference;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public String getBillingAddress() {

		return billingAddress;
	}

	public void setBillingAddress(final String billingAddress) {

		this.billingAddress = billingAddress;
	}

	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {

		this.creationDate = creationDate;
	}

	public Customer getCustomer() {

		return customer;
	}

	public void setCustomer(final Customer customer) {

		this.customer = customer;
	}

	public Seller getSeller() {

		return seller;
	}

	public void setSeller(final Seller seller) {

		this.seller = seller;
	}
}

