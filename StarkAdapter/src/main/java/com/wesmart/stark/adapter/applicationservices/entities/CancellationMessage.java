package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.CancellationMessageSerializer;

@JsonSerialize(using = CancellationMessageSerializer.class)
public class CancellationMessage {

	private String correlationId;
	private String cancellationId;
	private String authorizationTraceabilityId;
	private String authorizationCode;
	private Transaction transaction;
	private CreditCard creditCard;

	public CancellationMessage() {

	}

	public CancellationMessage(final String correlationId, final String cancellationId, final String authorizationTraceabilityId,
							   final String authorizationCode, final Transaction transaction, final CreditCard creditCard) {

		this.correlationId = correlationId;
		this.cancellationId = cancellationId;
		this.authorizationTraceabilityId = authorizationTraceabilityId;
		this.authorizationCode = authorizationCode;
		this.transaction = transaction;
		this.creditCard = creditCard;
	}

	public String getCorrelationId() {

		return correlationId;
	}

	public void setCorrelationId(final String correlationId) {

		this.correlationId = correlationId;
	}

	public String getCancellationId() {

		return cancellationId;
	}

	public void setCancellationId(final String cancellationId) {

		this.cancellationId = cancellationId;
	}

	public String getAuthorizationTraceabilityId() {

		return authorizationTraceabilityId;
	}

	public void setAuthorizationTraceabilityId(final String authorizationTraceabilityId) {

		this.authorizationTraceabilityId = authorizationTraceabilityId;
	}

	public String getAuthorizationCode() {

		return authorizationCode;
	}

	public void setAuthorizationCode(final String authorizationCode) {

		this.authorizationCode = authorizationCode;
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
