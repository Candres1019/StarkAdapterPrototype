package com.smart.model.entities;

import java.util.Date;

public class CreditCard {

	private Integer id;
	private String type;
	private String maskedNumber;
	private String cardHolder;
	private Date expirationDate;
	private Date creationDate;
	private Date modificationDate;
	private Customer customer;

	public CreditCard() {

	}

	public CreditCard(final Integer id, final String type, final String maskedNumber, final String cardHolder, final Date expirationDate,
					  final Date creationDate,
					  final Date modificationDate, final Customer customer) {

		this.id = id;
		this.type = type;
		this.maskedNumber = maskedNumber;
		this.cardHolder = cardHolder;
		this.expirationDate = expirationDate;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.customer = customer;
	}

	public Integer getId() {

		return id;
	}

	public void setId(final Integer id) {

		this.id = id;
	}

	public String getType() {

		return type;
	}

	public void setType(final String type) {

		this.type = type;
	}

	public String getMaskedNumber() {

		return maskedNumber;
	}

	public void setMaskedNumber(final String maskedNumber) {

		this.maskedNumber = maskedNumber;
	}

	public String getCardHolder() {

		return cardHolder;
	}

	public void setCardHolder(final String cardHolder) {

		this.cardHolder = cardHolder;
	}

	public Date getExpirationDate() {

		return expirationDate;
	}

	public void setExpirationDate(final Date expirationDate) {

		this.expirationDate = expirationDate;
	}

	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {

		this.creationDate = creationDate;
	}

	public Date getModificationDate() {

		return modificationDate;
	}

	public void setModificationDate(final Date modificationDate) {

		this.modificationDate = modificationDate;
	}

	public Customer getCustomer() {

		return customer;
	}

	public void setCustomer(final Customer customer) {

		this.customer = customer;
	}
}

