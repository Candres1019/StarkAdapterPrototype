package com.wesmart.stark.adapter.applicationservices.entities;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wesmart.entities.CreditCard;
import com.wesmart.entities.Transaction;
import com.wesmart.stark.adapter.applicationservices.utils.RefundMessageSerializer;

@JsonSerialize(using = RefundMessageSerializer.class)
public class RefundMessage {

	private String correlationId;
	private String refundId;
	private String captureTraceabilityId;
	private String refundCode;
	private Transaction transaction;
	private CreditCard creditCard;
	private Date maxRefundDate;

	public RefundMessage() {

	}

	public RefundMessage(final String correlationId, final String refundId, final String captureTraceabilityId, final String refundCode,
						 final Transaction transaction, final CreditCard creditCard, final Date maxRefundDate) {

		this.correlationId = correlationId;
		this.refundId = refundId;
		this.captureTraceabilityId = captureTraceabilityId;
		this.refundCode = refundCode;
		this.transaction = transaction;
		this.creditCard = creditCard;
		this.maxRefundDate = maxRefundDate;
	}

	public String getCorrelationId() {

		return correlationId;
	}

	public void setCorrelationId(final String correlationId) {

		this.correlationId = correlationId;
	}

	public String getRefundId() {

		return refundId;
	}

	public void setRefundId(final String refundId) {

		this.refundId = refundId;
	}

	public String getCaptureTraceabilityId() {

		return captureTraceabilityId;
	}

	public void setCaptureTraceabilityId(final String captureTraceabilityId) {

		this.captureTraceabilityId = captureTraceabilityId;
	}

	public String getRefundCode() {

		return refundCode;
	}

	public void setRefundCode(final String refundCode) {

		this.refundCode = refundCode;
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

	public Date getMaxRefundDate() {

		return maxRefundDate;
	}

	public void setMaxRefundDate(final Date maxRefundDate) {

		this.maxRefundDate = maxRefundDate;
	}
}
