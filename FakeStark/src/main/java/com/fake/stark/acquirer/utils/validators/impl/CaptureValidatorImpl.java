package com.fake.stark.acquirer.utils.validators.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CaptureValidator;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("captureValidatorImpl")
public class CaptureValidatorImpl implements CaptureValidator {

	private static final String COMPLETED_DESCRIPTION = "Completed";
	private static final Integer MAX_REFUND_DAYS = 2;
	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	@Autowired
	public CaptureValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
								@Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;

		this.creditCardValidator = creditCardValidator;
	}

	@Override public TransactionStates validateCapture(final PurchaseOrder purchaseOrder) {

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
		return transactionStates;
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
}