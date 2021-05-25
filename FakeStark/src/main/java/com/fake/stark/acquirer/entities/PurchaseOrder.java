package com.fake.stark.acquirer.entities;

import com.fake.stark.acquirer.utils.jsonmappers.PurchaseOrderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PurchaseOrderSerializer.class)
public class PurchaseOrder {

	private String id;
	private String status;
	private String description;
	private String shippingAddress;
	private Payment payment;

	public PurchaseOrder() {

	}

	public PurchaseOrder(final String id, final String status, final String description, final String shippingAddress,
						 final Payment payment) {

		this.id = id;
		this.status = status;
		this.description = description;
		this.shippingAddress = shippingAddress;
		this.payment = payment;
	}

	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(final String status) {

		this.status = status;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public String getShippingAddress() {

		return shippingAddress;
	}

	public void setShippingAddress(final String shippingAddress) {

		this.shippingAddress = shippingAddress;
	}

	public Payment getPayment() {

		return payment;
	}

	public void setPayment(final Payment payment) {

		this.payment = payment;
	}
}
