package com.fake.stark.acquirer.entities;

import java.util.Date;

public class Payment {

	private String id;
	private Double amount;
	private String currency;
	private Date maxRefundDate;
	private CreditCard creditCard;

	public Payment() {

	}

	public Payment(final String id, final Double amount, final String currency, final Date maxRefundDate, final CreditCard creditCard) {

		this.id = id;
		this.amount = amount;
		this.currency = currency;
		this.maxRefundDate = maxRefundDate;
		this.creditCard = creditCard;
	}

	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	public Double getAmount() {

		return amount;
	}

	public void setAmount(final Double amount) {

		this.amount = amount;
	}

	public String getCurrency() {

		return currency;
	}

	public void setCurrency(final String currency) {

		this.currency = currency;
	}

	public Date getMaxRefundDate() {

		return maxRefundDate;
	}

	public void setMaxRefundDate(final Date maxRefundDate) {

		this.maxRefundDate = maxRefundDate;
	}

	public CreditCard getCreditCard() {

		return creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {

		this.creditCard = creditCard;
	}
}
