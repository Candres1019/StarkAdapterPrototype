package com.fake.stark.acquirer.entities;

import java.util.Date;

public class CreditCard {

	private String number;
	private String type;
	private String cardHolder;
	private Date expirationDate;
	private User user;

	public CreditCard() {

	}

	public CreditCard(final String number, final String type, final String cardHolder, final Date expirationDate, final User user) {

		this.number = number;
		this.type = type;
		this.cardHolder = cardHolder;
		this.expirationDate = expirationDate;
		this.user = user;
	}

	public String getNumber() {

		return number;
	}

	public void setNumber(final String number) {

		this.number = number;
	}

	public String getType() {

		return type;
	}

	public void setType(final String type) {

		this.type = type;
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

	public User getUser() {

		return user;
	}

	public void setUser(final User user) {

		this.user = user;
	}
}
