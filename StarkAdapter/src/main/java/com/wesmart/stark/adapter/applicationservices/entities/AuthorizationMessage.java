package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.AuthorizationMessageSerializer;

@JsonSerialize(using = AuthorizationMessageSerializer.class)
public class AuthorizationMessage {

	private String correlationId;

	private String authorizationId;

	private Transaction transaction;

	private CreditCard creditCard;

	public AuthorizationMessage() {

	}

	public AuthorizationMessage(final String correlationId, final String authorizationId, final Transaction transaction,
								final CreditCard creditCard) {

		this.correlationId = correlationId;
		this.authorizationId = authorizationId;
		this.transaction = transaction;
		this.creditCard = creditCard;
	}

	public String getCorrelationId() {

		return correlationId;
	}

	public void setCorrelationId(final String correlationId) {

		this.correlationId = correlationId;
	}

	public String getAuthorizationId() {

		return authorizationId;
	}

	public void setAuthorizationId(final String authorizationId) {

		this.authorizationId = authorizationId;
	}

	public Transaction getTransaction() {

		return transaction;
	}

	public void setTransaction(final Transaction transaction) {

		this.transaction = transaction;
	}

	public CreditCard getCreditCard() {

		return creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {

		this.creditCard = creditCard;
	}
}
