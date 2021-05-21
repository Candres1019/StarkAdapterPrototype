package com.wesmart.stark.adapter.applicationservices.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.CaptureMessageSerializer;

@JsonSerialize(using = CaptureMessageSerializer.class)
public class CaptureMessage {

	private String correlationId;

	private String captureId;

	private String authorizationTraceabilityId;

	private String authorizationCode;

	private Transaction transaction;

	private CreditCard creditCard;

	public CaptureMessage() {

	}

	public CaptureMessage(final String correlationId, final String captureId, final String authorizationTraceabilityId,
						  final String authorizationCode,
						  final Transaction transaction, final CreditCard creditCard) {

		this.correlationId = correlationId;
		this.captureId = captureId;
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

	public String getCaptureId() {

		return captureId;
	}

	public void setCaptureId(final String captureId) {

		this.captureId = captureId;
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
