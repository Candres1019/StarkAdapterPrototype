package com.fake.stark.acquirer.utils.validators.impl;

import com.fake.stark.acquirer.entities.PurchaseOrder;
import com.fake.stark.acquirer.persistence.Persistence;
import com.fake.stark.acquirer.services.TransactionStates;
import com.fake.stark.acquirer.utils.validators.CreditCardValidator;
import com.fake.stark.acquirer.utils.validators.RefundValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("refundValidatorImpl")
public class RefundValidatorImpl implements RefundValidator {

	private final Persistence persistence;
	private final CreditCardValidator creditCardValidator;

	public RefundValidatorImpl(@Qualifier("myBatisPersistence") final Persistence persistence,
							   @Qualifier("creditCardValidatorImpl") final CreditCardValidator creditCardValidator) {

		this.persistence = persistence;
		this.creditCardValidator = creditCardValidator;
	}

	@Override public TransactionStates validateRefund(final PurchaseOrder purchaseOrder) {

		var transactionStates = TransactionStates.REFUND_APPROVED;
		var oldPurchaseOrder = persistence.getPurchaseOrderById(purchaseOrder.getId());
		if (oldPurchaseOrder == null) {
			transactionStates = TransactionStates.REFUND_REJECTED_INVALID_ID;
		} else {
			var creditCard = persistence
					.getCreditCardByUser(oldPurchaseOrder.getPayment().getCreditCard().getUser().getIdentification());
			var payment = persistence.getPaymentById(purchaseOrder.getPayment().getId());
			if (payment == null) {
				transactionStates = TransactionStates.REFUND_REJECTED_PAYMENT_ID_INVALID;
			} else if (creditCardValidator
					.validateCreditCardForRefund(creditCard, purchaseOrder.getPayment().getCreditCard(), payment.getCreditCard())) {
				transactionStates = TransactionStates.REFUND_REJECTED_CREDIT_CARD_INVALID;
			} else if (oldPurchaseOrder.getStatus().contains("AUTHORIZATION") || oldPurchaseOrder.getStatus().contains("VOID")
					|| oldPurchaseOrder.getStatus().contains("REFUND")) {
				transactionStates = this.getCompletedTransactionReasonRefund(oldPurchaseOrder.getStatus());
			} else if (oldPurchaseOrder.getPayment().getMaxRefundDate().getTime() < System.currentTimeMillis()) {
				transactionStates = TransactionStates.REFUND_REJECTED_MAX_REFUND_DATE_EXPIRED;
			}
		}
		return transactionStates;
	}

	private TransactionStates getCompletedTransactionReasonRefund(String status) {

		if (status.contains("AUTHORIZATION")) {
			return TransactionStates.REFUND_IN_AUTHORIZATION_APPROVAL;
		} else if (status.contains("VOID")) {
			return TransactionStates.REFUND_ALREADY_PROCESSED_V;
		} else {
			return TransactionStates.REFUND_ALREADY_PROCESSED;
		}
	}
}
