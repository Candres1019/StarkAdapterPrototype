package com.fake.stark.acquirer.services.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fake.stark.acquirer.entities.Payment;
import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionProcessor;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("transactionProcessorImpl")
public class TransactionProcessorImpl implements TransactionProcessor {

	private static final Integer MAX_REFUND_DAYS = 2;
	private static final String COMPLETED_DESCRIPTION = "Completed";
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	@Autowired
	public TransactionProcessorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
									@Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	@Override public String processAuthorizationTransaction(final PurchaseOrder purchaseOrder) throws JsonProcessingException {

		try {
			var transactionStates = TransactionStates.AUTHORIZATION_APPROVED;
			var user = persistence.getUserById(purchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
			if (user == null) {
				transactionStates = TransactionStates.AUTHORIZATION_REJECTED_INVALID_USER;
			} else {
				var creditCard = persistence.getCreditCardByUser(user.getIdentification());
				var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
				if (creditCardValidator.validateCreditCardForAuthorization(creditCard, purchaseOrder.getPayment().getCreditCard())) {
					transactionStates = TransactionStates.AUTHORIZATION_REJECTED_CREDIT_CARD_INVALID;
				} else if (oldPurchaseOrder != null && oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
					transactionStates = TransactionStates.AUTHORIZATION_ALREADY_PROCESSED;
				} else if (null != payment) {
					transactionStates = TransactionStates.AUTHORIZATION_ALREADY_PROCESSED;
				}
			}
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.AUTHORIZATION_APPROVED)) {
				persistence.insertPayment(purchaseOrder.getPayment());
				persistence.insertPurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Authorization, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return new ObjectMapper().writeValueAsString(purchaseOrder);
	}

	@Override public String processCaptureTransaction(final PurchaseOrder purchaseOrder) throws JsonProcessingException {

		try {
			var transactionStates = TransactionStates.CAPTURE_APPROVED;
			var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
			purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
			if (oldPurchaseOrder == null) {
				transactionStates = TransactionStates.CAPTURE_REJECTED_INVALID_ID;
			} else {
				var creditCard = persistence
						.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
				var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
				if (payment == null) {
					transactionStates = TransactionStates.CAPTURE_REJECTED_PAYMENT_ID_INVALID;
				} else if (creditCardValidator
						.validateCreditCardForCapture(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
					transactionStates = TransactionStates.CAPTURE_REJECTED_CREDIT_CARD_INVALID;
				} else if (oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
					transactionStates = this.getCompletedTransactionReasonCapture(oldPurchaseOrder.getStatus());
				} else {
					purchaseOrder.getPayment()
								 .setMaxRefundDate(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(MAX_REFUND_DAYS)));
				}
			}
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.CAPTURE_APPROVED)) {
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Capture, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return new ObjectMapper().writeValueAsString(purchaseOrder);
	}

	@Override public String processVoidTransaction(final PurchaseOrder purchaseOrder) throws JsonProcessingException {

		try {
			var transactionStates = TransactionStates.VOID_APPROVED;
			var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
			if (oldPurchaseOrder == null) {
				transactionStates = TransactionStates.VOID_REJECTED_INVALID_ID;
			} else {
				var creditCard = persistence
						.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
				var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
				if (payment == null) {
					transactionStates = TransactionStates.VOID_REJECTED_PAYMENT_ID_INVALID;
				} else if (creditCardValidator
						.validateCreditCardForVoid(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
					purchaseOrder.setStatus(TransactionStates.VOID_REJECTED_CREDIT_CARD_INVALID.name());
					purchaseOrder.setDescription(TransactionStates.VOID_REJECTED_CREDIT_CARD_INVALID.getName());
				} else if (oldPurchaseOrder.getDescription().contains(COMPLETED_DESCRIPTION)) {
					transactionStates = this.getCompletedTransactionReasonVoid(oldPurchaseOrder.getStatus());
				}
			}
			purchaseOrder.setStatus(transactionStates.name());
			purchaseOrder.setDescription(transactionStates.getName());
			if (transactionStates.equals(TransactionStates.VOID_APPROVED)) {
				purchaseOrder.getPayment().setMaxRefundDate(new Date(0L));
				persistence.updatePayment(purchaseOrder.getPayment());
				persistence.updatePurchaseOrder(purchaseOrder);
			}
		} catch (Exception e) {
			purchaseOrder.setStatus(TransactionStates.ERROR_IN_THE_SERVER.name());
			purchaseOrder.setDescription("Void, " + TransactionStates.ERROR_IN_THE_SERVER.getName());
		}
		return new ObjectMapper().writeValueAsString(purchaseOrder);
	}

	private TransactionStates getCompletedTransactionReasonCapture(String status) {

		if (status.contains("CAPTURE")) {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED;
		} else if (status.contains("VOID")) {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED_V;
		} else {
			return TransactionStates.CAPTURE_ALREADY_PROCESSED_R;
		}
	}

	private TransactionStates getCompletedTransactionReasonVoid(String status) {

		if (status.contains("CAPTURE")) {
			return TransactionStates.VOID_ALREADY_PROCESSED_C;
		} else if (status.contains("VOID")) {
			return TransactionStates.VOID_ALREADY_PROCESSED;
		} else {
			return TransactionStates.VOID_ALREADY_PROCESSED_R;
		}
	}

}
