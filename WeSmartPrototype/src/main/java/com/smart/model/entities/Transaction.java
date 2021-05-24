package com.smart.model.entities;

import java.util.Date;
import java.util.UUID;

public class Transaction {

	private UUID id;
	private String correlationId;
	private String type;
	private String currency;
	private String status;
	private Double total;
	private Double tax;
	private Double taxBase;
	private Date creationDate;
	private Date modificationDate;
	private PaymentOrder paymentOrder;

	public Transaction() {

	}

	public Transaction(final UUID id, final String correlationId, final String type, final String currency, final String status,
					   final Double total, final Double tax,
					   final Double taxBase, final Date creationDate, final Date modificationDate, final PaymentOrder paymentOrder) {

		this.id = id;
		this.correlationId = correlationId;
		this.type = type;
		this.currency = currency;
		this.status = status;
		this.total = total;
		this.tax = tax;
		this.taxBase = taxBase;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.paymentOrder = paymentOrder;
	}

	public UUID getId() {

		return id;
	}

	public void setId(final UUID id) {

		this.id = id;
	}

	public String getCorrelationId() {

		return correlationId;
	}

	public void setCorrelationId(final String correlationId) {

		this.correlationId = correlationId;
	}

	public String getType() {

		return type;
	}

	public void setType(final String type) {

		this.type = type;
	}

	public String getCurrency() {

		return currency;
	}

	public void setCurrency(final String currency) {

		this.currency = currency;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(final String status) {

		this.status = status;
	}

	public Double getTotal() {

		return total;
	}

	public void setTotal(final Double total) {

		this.total = total;
	}

	public Double getTax() {

		return tax;
	}

	public void setTax(final Double tax) {

		this.tax = tax;
	}

	public Double getTaxBase() {

		return taxBase;
	}

	public void setTaxBase(final Double taxBase) {

		this.taxBase = taxBase;
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

	public PaymentOrder getPaymentOrder() {

		return paymentOrder;
	}

	public void setPaymentOrder(final PaymentOrder paymentOrder) {

		this.paymentOrder = paymentOrder;
	}
}

