package com.fake.stark.acquirer.services.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionProcessor;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("transactionProcessorImpl")
public class TransactionProcessorImpl implements TransactionProcessor {

	private static final Integer MAX_REFUND_DAYS = 2;
	private final Persistence persistence;

	@Autowired
	public TransactionProcessorImpl(@Qualifier("myBatisPersistence") Persistence persistence) {

		this.persistence = persistence;
	}

	@Override
	public String processAuthorizationTransaction(final PurchaseOrder purchaseOrder) throws JsonProcessingException {

		try {
			var user = persistence.getUserById(purchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			if (user == null) {
				purchaseOrder.setStatus(TransactionStates.AUTHORIZATION_REJECTED_INVALID_USER.name());
				purchaseOrder.setDescription(TransactionStates.AUTHORIZATION_REJECTED_INVALID_USER.getName());
			} else {
				var creditCard = persistence.getCreditCardByUser(user.getIdentification());
				var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
				if (creditCard == null || !creditCard.getNumber().equals(purchaseOrder.getPayment().getCreditCard().getNumber())
						|| creditCard.getExpirationDate().getTime() < System.currentTimeMillis()) {
					purchaseOrder.setStatus(TransactionStates.AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID.name());
					purchaseOrder.setDescription(TransactionStates.AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID.getName());
				} else if (null != payment) {
					purchaseOrder.setStatus(TransactionStates.AUTHORIZATION_ALREADY_PROCESSED.name());
					purchaseOrder.setDescription(TransactionStates.AUTHORIZATION_ALREADY_PROCESSED.getName());
				} else {
					purchaseOrder.setStatus(TransactionStates.AUTHORIZATION_APPROVED.name());
					purchaseOrder.setDescription(TransactionStates.AUTHORIZATION_APPROVED.getName());
					persistence.insertPayment(purchaseOrder.getPayment());
					persistence.insertPurchaseOrder(purchaseOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Authorization, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return new ObjectMapper().writeValueAsString(purchaseOrder);
	}

	@Override
	public String processCaptureTransaction(final PurchaseOrder purchaseOrder) throws JsonProcessingException {

		try {
			var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
			purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
			if (oldPurchaseOrder == null) {
				purchaseOrder.setStatus(TransactionStates.CAPTURE_REJECTED_INVALID_ID.name());
				purchaseOrder.setDescription(TransactionStates.CAPTURE_REJECTED_INVALID_ID.getName());
			} else {
				var creditCard = persistence
						.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
				var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
				if (payment == null) {
					purchaseOrder.setStatus(TransactionStates.CAPTURE_REJECTED_PAYMENT_ID_INVALID.name());
					purchaseOrder.setDescription(TransactionStates.CAPTURE_REJECTED_PAYMENT_ID_INVALID.getName());
				} else if (creditCard == null || !creditCard.getNumber().equals(purchaseOrder.getPayment().getCreditCard().getNumber())
						|| !payment.getCreditCard().getNumber().equals(purchaseOrder.getPayment().getCreditCard().getNumber())) {
					purchaseOrder.setStatus(TransactionStates.CAPTURE_REJECTED_CREDIT_CARD_INVALID.name());
					purchaseOrder.setDescription(TransactionStates.CAPTURE_REJECTED_CREDIT_CARD_INVALID.getName());
				} else if (oldPurchaseOrder.getDescription().contains("Completed")) {
					purchaseOrder.setStatus(TransactionStates.CAPTURE_ALREADY_PROCESSED.name());
					purchaseOrder.setDescription(TransactionStates.CAPTURE_ALREADY_PROCESSED.getName());
				} else {
					purchaseOrder.getPayment().setMaxRefundDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2)));
					purchaseOrder.setStatus(TransactionStates.CAPTURE_APPROVED.name());
					purchaseOrder.setDescription(TransactionStates.CAPTURE_APPROVED.getName());
					persistence.updatePayment(purchaseOrder.getPayment());
					persistence.updatePurchaseOrder(purchaseOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Capture, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return new ObjectMapper().writeValueAsString(purchaseOrder);
	}

	@Override public Object processAll() {

		List<Payment> payments = persistence.getAll();

		for (Payment payment : payments) {
			System.out.println(payment.getId());
		}

		Payment payment = persistence.getPaymentById("18261179-d3a0-4cfa-9ce9-06be03a94307");
		System.out.println(payment.getId());
		return null;
	}

}
